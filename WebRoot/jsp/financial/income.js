/*
 * 收入
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vFields=[{name:'id',type:'int'},
	             {name:'dtBs',type:'string'},
	             {name:'vcNo',type:'string'},
	             {name:'accountTypeId',type:'int'},
	             {name:'accountTypeName',type:'string'},
	             {name:'dlMoney',type:'string'},
	             {name:'accountId',type:'int'},
	             {name:'accountName',type:'string'},
	             {name:'vcAuditor',type:'string'},
	             {name:'vcRemark',type:'string'},
	             {name:'dtWrite',type:'string'},
	             {name:'fidel',type:'int'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'ime_getInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vFields
	});
	/**作废 记录**/
	vGridStore.on('load',function(store,records){
		var girdcount=0;
		store.each(function(r){
			if(r.get('fidel')=='1'){
				vGridPanel.getView().getRow(girdcount).style.backgroundColor='#FF8484';
			}
			girdcount=girdcount+1;
		});
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'单据编号',width:140,dataIndex:'vcNo',renderer:function(a,b,c){return c.data['fidel']=='1'?'已作废/'+a:a;}},
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'账目类型',width:100,dataIndex:'accountTypeName'},
        	          {header:'收入账户',width:100,dataIndex:'accountName'},
        	          {header:'金额',width:120,dataIndex:'dlMoney'},
        	          {header:'备注',width:200,dataIndex:'vcRemark'},
        	          {header:'经手人',widht:100,dataIndex:'vcAuditor'},
        	          {header:'制单时间',width:120,dataIndex:'dtWrite',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d H:i'); }},
        	          {header:'id',hidden:true,dataIndex:'id'},
        	          {header:'accountTypeId',hidden:true,dataIndex:'accountTypeId'},
        	          {header:'accountId',hidden:true,dataIndex:'accountId'}]
        }),
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vGridStore,
            displayInfo: true
        })
	});
	
	/*工具栏按钮*/
	var vToobar=new Ext.Toolbar({
	    items: [{
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
                	Ext.Msg.alert('信息提示','请选择要修改的单据');
				}else{
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的单据不能进行此操作');
						return;
					}
					funcEdit("edit",record); 
				}
        	}
        },'-',{
        	text:'查看',
        	iconCls:'btn-list',
        	handler: function(){
        	var record= vGridPanel.getSelectionModel().getSelected(); 
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要查看的单据');
			}else{
				funcEdit("read",record); 
			}
        	}
        },'-',{
        	text:'作废',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要作废的单据');  
				}else{
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的单据不能进行此操作');
						return;
					}
					Ext.MessageBox.confirm('作废提示', '是否要作废该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "ime_deleteInfo.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				//vGridStore.reload();
					   				funcinitgrid();
					   			}
					   		});
					    }
					});
				}
        	}
        }/*,'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:160,
        	maxLength:100,
        	emptyText:'请输入关键字...'
        },'-',{
            	text:'查询',
            	iconCls:'btn-list',
            	handler: function(){
        			vGridStore.load({params:{start:0, limit:20,key:Ext.getCmp('keyWord').getValue()}});
            	}
        }*/]
	});
	
	var vStartDate=new Ext.form.DateField({name:'dts',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
    /*账目类型*/
    var storeZmlx1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ut_getInfo.do?type=zmlx&itype=1", /**收入**/
        autoLoad: {},
        root : "root"
    });
    var vZmlxCombo1= new Ext.form.ComboBox({
    	fieldLabel :'账目类型',
    	store: storeZmlx1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        maxLength:10
    });
	
	/**条件区域**/
	var vToobar1=new Ext.Toolbar({
	    items: ['日期范围：',vStartDate,'到',vEndDate,'-','账目类型：',vZmlxCombo1,'-',{
        	text:'查询',
        	iconCls:'btn-list',
        	handler: function(){
//	    		vGridStore.load({params:{
//	    			start:0,
//	    			limit:20,
//	    			accountTypeId:vZmlxCombo1.getValue(),
//	    			dtSDate:vStartDate.getValue(),
//	    			dtEDate:vEndDate.getValue()
//	    		}});
	    	funcinitgrid();
        	}
	    }]
	});
	
	
	
	
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 55,
		items:[vToobar,vToobar1]
	});
	
	/**
	 * 新增或编辑
	 * */
	function funcEdit(type,row){
		var vid=new Ext.form.Hidden({name:'id'});
		var vNo= new Ext.form.TextField({hidden:true,name:'vcNo',fieldLabel:'单据编号',width:180,maxLength:20});
		var vdtBs= new Ext.form.DateField({name:'dtBs',value:new Date(),fieldLabel:'业务日期',format:'Y-m-d',width:180,allowBlank:false});
		var vdlMoney=new Ext.form.NumberField({name:'dlMoney',fieldLabel:'金额',maxLength :10,width:180,allowBlank:false,decimalPrecision:2});
		var vcRemark=new Ext.form.TextField({name:'vcRemark',maxLength:25,fieldLabel:'备注',width:470});
		/*账目类型*/
	    var storeZmlx=new Ext.data.JsonStore({
	    	fields: ['id', 'vcName'],
	    	url : "ut_getInfo.do?type=zmlx&itype=1", /**收入**/
	        autoLoad: {},
	        root : "root"
	    });
	    var vZmlxCombo= new Ext.form.ComboBox({
	    	name:'accountTypeId',
	    	fieldLabel :'账目类型',
	    	store: storeZmlx,
	    	valueField : 'id',
	        displayField:'vcName',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        maxLength:10,
	        hiddenName:'accountTypeId',
	        allowBlank: false
	    });
	    /*账户*/
	    var storeZh=new Ext.data.JsonStore({
	    	fields: ['id', 'vcName'],
	        url : "ut_getInfo.do?type=zh", /**收入账户--暂时不用 &itype=1*/
	        autoLoad: {},
	        root : "root"
	    });
	    var vZhCombo= new Ext.form.ComboBox({
	    	name:'accountId',
	    	fieldLabel :'收入账户',
	    	store: storeZh,
	    	valueField : 'id',
	        displayField:'vcName',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        maxLength:10,
	        hiddenName:'accountId',
	        allowBlank: false
	    });
	    
	    /*经手人*/
        var storeAur=new Ext.data.JsonStore({
        	fields: ['userid', 'username'],
            url : "user_findUser.do?isuser=1",
            //autoLoad: {},
            root : "root"
        });
        var vAurCombo= new Ext.form.ComboBox({
        	name:'vcAuditor',
        	fieldLabel :'经手人',
        	store: storeAur,
        	valueField : 'userid',
            displayField:'username',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            allowBlank: false,
            value:parent.log_name,
            readOnly:true,style:"background:#F6F6F6"
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
                items: [vid,vNo,vdtBs,vdlMoney,vAurCombo]
            },{
                columnWidth: 0.5,
                items: [vZmlxCombo,vZhCombo]
            },{
                columnWidth: 1, items: [vcRemark]
            }]
        });
	    
        var addForm = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            items:[vPanelForm]
        });
        
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '收入编辑',
    		closeAction:'close',
    		width:610,
    		height:220,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			id:'btnSave2',
    			text : '保存并新增',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ime_saveInfo.do',
    						success : function(form, action) {
    							//Ext.Msg.alert('信息提示',action.result.message);
    							//addWindow.close();
    							//vGridStore.reload();
    							funcinitgrid();
    							//addForm.getForm().reset()
    							vid.setValue("");
    							vNo.setValue("");
    							vdlMoney.setValue("");
    							vcRemark.setValue("");
    							
    							vdlMoney.enable();
    				        	vZhCombo.enable();
    							
    							addWindow.show();
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
    		},{
    			id:'btnSave1',
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ime_saveInfo.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.close();
    							//vGridStore.reload();
    							funcinitgrid();
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
    				addWindow.close();
    			}
    		}]
    	});
        addWindow.show();
        /*判断是新增还是修改*/
        if(type=='add'){
        	addForm.getForm().reset()
        }else{
        	/*禁用 金额和账户*/
        	vdlMoney.disable();
        	vZhCombo.disable();
        	
        	if(row!=null){
        		addForm.getForm().loadRecord(row);
        		vdtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeZmlx.on('load',function(){
        			vZmlxCombo.setValue(row.get("accountTypeId"));
        		});
        		storeZh.on('load',function(){
        			vZhCombo.setValue(row.get("accountId"));
        		});
        	}
        }
		
        if(type=='read') /*查看 禁用保存按钮*/{
        	Ext.getCmp('btnSave1').disable();
        	Ext.getCmp('btnSave2').disable();
        }
		
	}
	
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('accountTypeId',vZmlxCombo1.getValue());
		vGridStore.setBaseParam('dtSDate',vStartDate.getValue());
		vGridStore.setBaseParam('dtEDate',vEndDate.getValue());
		vGridStore.load({params:{
			start:0, 
			limit:20
		}});
	}

	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'收入管理',
			frame:true,
			region:'center',
			layout:'border',
			border:false,
			items:[vPanel,vGridPanel]
		}]
	});
	
    /*查询网格*/
    funcinitgrid();
	
	
});