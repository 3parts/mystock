
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var vFileds=[{name:'id',type:'int'},
	             {name:'vcNo',type:'string'},
	             {name:'vcName',type:'string'},
	             {name:'igender',type:'int'},
	             {name:'vcNation',type:'string'},
	             {name:'vcIdCard',type:'string'},
	             {name:'vcAddress',type:'string'},
	             {name:'positionId',type:'int'},
	             {name:'positionName',type:'string'},
	             {name:'icomminSsion',type:'int'},
	             {name:'vcTel',type:'string'},
	             {name:'istate',type:'int'},
	             {name:'dtEntry',type:'string'},
	             {name:'dtQuit',type:'string'},
	             {name:'vcQuitReason',type:'string'},
	             {name:'vcRemark',type:'string'}];
	
	/*网格的数据源*/
	var vConpanyStore= new Ext.data.JsonStore({
		url:'ps_getPageInfo.do',
		root:'root',
		totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:20}},
	    fields: vFileds
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		store:vConpanyStore,
		title:'员工资料',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'状态',width:80,dataIndex:'istate',renderer:function(a){return a=='1'?'在职':'离职';}},
        	          {header:'员工编号',width:100,dataIndex: 'vcNo'},
        	          {header:'员工名称',width:180,dataIndex:'vcName'},
        	          {header:'性别',width:80,dataIndex:'igender',renderer:function(a){return a=='1'?'男':'女';}},
        	          {header:'民族',width:80,dataIndex:'vcNation'},
        	          {header:'身份证',width:100,dataIndex:'vcIdCard'},
        	          {header:'现居住地',width:200,dataIndex:'vcAddress'},
        	          {header:'职位',width:80,dataIndex:'positionName'},
        	          {header:'是否提成',width:80,dataIndex:'icomminSsion',renderer:function(a){return a=='1'?'是':'否';}},
        	          {header:'联系电话',width:120,dataIndex:'vcTel'},
        	          {header:'入职日期',width:100,dataIndex:'dtEntry',renderer:function(a){
        	        	  return (new Date(parseInt(a))).format('Y-m-d');
        	          }},
        	          {header:'离职日期',width:100,dataIndex:'dtQuit',renderer:function(a){
        	        	  return a==null|| a.length==0?"":(new Date(parseInt(a))).format('Y-m-d');
        	          }},
        	          {header:'离职原因',width:200,dataIndex:'vcQuitReason'},
        	          {header:'备注',width:160,dataIndex:'vcRemark'},
        	          {header:'ID',hidden:true,width:60,dataIndex:'id'},
        	          {header:'positionId',hidden:true,dataIndex:'positionId'}]
        }),
        tbar:[{
        	text:'添加',
        	iconCls:'btn-add',
        	handler: function(){
        		funcEdit("add"); 
        		
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的员工');
				}else{
					funcEdit("edit",record); 
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的员工');  
				}else{
					if(record.get("istate")=='1'){
						Ext.Msg.alert('信息提示','在职的员工不允许删除!');
						return;
					}
					Ext.MessageBox.confirm('删除提示', '是否要删除该员工？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "ps_deleteInfo.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				vConpanyStore.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:160,
        	maxLength:100,
        	emptyText:'请输入关键字...',
        	listeners:{
        		specialKey:function(field, e){
        			if (e.getKey() == e.ENTER){
        				vConpanyStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
        				vConpanyStore.load({
            				params:{start:0, limit:20}
            			});
        			}
        		}
        	}
        },'-',{
            	text:'查询',
            	iconCls:'btn-list',
            	handler: function(){
		        	vConpanyStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
					vConpanyStore.load({
						params:{start:0, limit:20}
					});
            	}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vConpanyStore,
            displayInfo: true
        })
	});
	
	/**编辑窗体**/
	function funcEdit(type,row){
		
		var vId=new Ext.form.Hidden({name:'id'});
		var vVcNo=new Ext.form.TextField({name:'vcNo',fieldLabel:'员工编号',width:180,maxLength:10,allowBlank: false});
		var vVcName=new Ext.form.TextField({name:'vcName',fieldLabel:'员工名称',width:180,maxLeng:25,allowBlank:false});
		var vVcNation=new Ext.form.TextField({name:'vcNation',fieldLabel:'民族',width:180,maxLeng:10});
		var vIdCard=new Ext.form.TextField({name:'vcIdCard',fieldLabel:'身份证号',width:180,maxLeng:25});
		var vAddress=new Ext.form.TextField({name:'vcAddress',fieldLabel:'现居住地',width:470,maxLeng:100});
		var vDtEntry= new Ext.form.DateField({name:'dtEntry',fieldLabel:'入职日期',format:'Y-m-d',width:180,allowBlank:false});
		var vTel=new Ext.form.TextField({name:'vcTel',fieldLabel:'联系电话',width:180,maxLeng:20});
		var vDtQuit= new Ext.form.DateField({name:'dtQuit',fieldLabel:'离职日期',format:'Y-m-d',width:180});
		var vQuitReason=new Ext.form.TextField({name:'vcQuitReason',fieldLabel:'离职原因',width:470,maxLeng:100});
		var vRemark= new Ext.form.TextArea({name:'vcRemark',fieldLabel:'备注',width:470,height:60,maxLeng:100});
        
		/*性别*/
        var vGenderCombo= new Ext.form.ComboBox({
        	name:'igender',
        	fieldLabel :'性别',
        	store: new Ext.data.SimpleStore({
		        		fields: ['value', 'text'],
		        		data:[['1','男'],['0','女']]
        		   }),
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:1,
            maxLength:10,
            hiddenName:'igender' /*设定了 跟name值一样的话 就会提交value值*/
        });

        /*状态*/
        var vStateCombo= new Ext.form.ComboBox({
        	name:'istate',
        	fieldLabel :'状态',
        	store: new Ext.data.SimpleStore({
		        		fields: ['value', 'text'],
		        		data:[['1','在职'],['0','离职']]
        		   }),
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:1,
            maxLength:10,
            hiddenName:'istate' /*设定了 跟name值一样的话 就会提交value值*/
        });
        
        /*是否有提成*/
        var vComminCombo= new Ext.form.ComboBox({
        	name:'icomminSsion',
        	fieldLabel :'是否提成',
        	store: new Ext.data.SimpleStore({
		        		fields: ['value', 'text'],
		        		data:[['1','是'],['0','否']]
        		   }),
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:1,
            maxLength:10,
            hiddenName:'icomminSsion' /*设定了 跟name值一样的话 就会提交value值*/
        });
        
        /*职位*/
        var store=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ut_getInfo.do?type=zw",
            autoLoad: {},
            root : "root"
        });
        var vPosCombo= new Ext.form.ComboBox({
        	name:'positionId',
        	fieldLabel :'职位',
        	store: store,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            maxLength:10,
            hiddenName:'positionId'
        });
        
        
        /*表单界面 布局*/
        var vPanelForm=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.5,
                items: [vId,vVcNo,vGenderCombo,vIdCard]
            },{
                columnWidth: 0.5,
                items: [vVcName,vVcNation,vDtEntry]
            },{
                columnWidth: 1, items: [vAddress]
            },{
                columnWidth: 0.5,
                items: [vPosCombo,vTel,vDtQuit]
            },{
                columnWidth: 0.5,
                items: [vComminCombo,vStateCombo]
            },{
                columnWidth: 1, items: [vQuitReason,vRemark]
            }]
        });
        var addForm = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            items:[vPanelForm]
        });

        var addWindow = new Ext.ux.Window({
    		title : '员工编辑',
    		closeAction:'hide',
    		width:610,
    		height:380,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ps_saveOrUpdate.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.hide();
    							vConpanyStore.reload();
    						},
    						failure : function(form, action) {
    							if(action.result.errors){
    								Ext.Msg.alert('信息提示',action.result.errors);
    							}else{
    								Ext.Msg.alert('信息提示','连接失败');
    							}
    						},
    						waitTitle : '提交',
    						waitMsg : '正在保存数据，稍后...'
    					});
    				}
    			}
    		}, {
    			text : '取消',
    			handler : function() {
    				addWindow.hide();
    			}
    		}]
    	});
        addWindow.show();
        /*判断是新增还是修改*/
        if(type=='add'){
        	addForm.getForm().reset()
        }else{
        	if(row!=null){
        		addForm.getForm().loadRecord(row);
        		vDtEntry.setValue(new Date(parseInt(row.get("dtEntry"))));
        		if(row.get("dtQuit").length!=0){
        			vDtQuit.setValue(new Date(parseInt(row.get("dtQuit"))));
        		}
        		store.on('load',function(){
        			vPosCombo.setValue(row.get("positionId"));
        		});
        	}
        }
	
	
	}
	
	
	  //布局
    new Ext.Viewport({
		layout:'border',
		items:[{
			region:'center',
			layout:'fit',
			border:false,
			items:vGridPanel
		}]
	});
});