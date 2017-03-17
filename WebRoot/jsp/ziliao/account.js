/**
 * 账户
 * */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var fieds=[{name:'id',type:'int'},
	           {name:'vcNo',type:'string'},
	           {name:'vcName',type:'string'},
	           {name:'itype',type:'int'},
	           {name:'vcRemark',type:'string'}];
	
	var store = new Ext.data.JsonStore({
	    url: 'ut_getInfo.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{type:'zh'}},
	    fields: fieds
	});
	
    var grid = new Ext.ux.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true },
			columns:[new Ext.grid.RowNumberer(),
			    {header: '账户编号',width:100, align:'center',dataIndex:'vcNo'},
			    {header: '账户名称', width: 150, align:'center',dataIndex: 'vcName'},
			    {header: '账户类型',hidden:true,width:100,align:'center',dataIndex:'itype',renderer:function(a){return a=="1"?"收入":"支出";}},
			    {header: '备注', width: 200, align:'center',dataIndex: 'vcRemark'},
	            {header: 'ID',hidden:true, width:80, align:'center',dataIndex: 'id'}]
        }),
        title:'账户管理',
        tbar:[{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		funcEdit("add");
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','您没有选择任何记录');
				}else{
					if(record.get('vcRemark').indexOf('系统必须')>-1){
						Ext.Msg.alert('信息提示','系统必须,不能修改');
						return;
					}
					funcEdit("edit",record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的账户');  
				}else{
					if(record.get('vcRemark').indexOf('系统必须')>-1){
						Ext.Msg.alert('信息提示','系统必须,不能删除');
						return;
					}
					Ext.MessageBox.confirm('删除提示', '是否删除该账户？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "ut_delete.do?type=zh",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        }],
        bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: store,
            displayInfo: true
        })
    });
    
    
    function funcEdit(type,gridrow){
    	var vId= new Ext.form.Hidden({name:'id'});
    	var vType=new Ext.form.Hidden({name:'type',value:'zh'});
    	var vVcNo= new Ext.form.TextField({name:'vcNo',fieldLabel:'账户编号',width:260,maxLength:10,allowBlank: false});
    	var vVcName= new Ext.form.TextField({name:'vcName',fieldLabel:'账户名称',width:260,maxLength:25,allowBlank: false});
    	var vRemark= new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:260,maxLength:50});
    	var data=[['1','收入'],['0','支出']];
        var vstore = new Ext.data.SimpleStore({
        	fields: ['value', 'text'],
        	data : data
        });

    	
    	 var vTypeCombo= new Ext.form.ComboBox({
    		 hidden:true,
    		 width:260,
         	 name:'itype',
         	 fieldLabel :'收支类型',
         	 store: vstore,
         	 valueField : 'value',
             displayField:'text',
             typeAhead: true,
             mode: 'local',
             forceSelection: true,
             triggerAction: 'all',
             selectOnFocus:true,
             editable : false,
             maxLength:10,
             hiddenName:'itype'
         });
    	
    	var addForm = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            labelAlign: "right",
            labelWidth:80,
            defaults: { frame: false, border: false },
            items:[vId,vType,vVcNo,vVcName,vTypeCombo,vRemark]
        });
    	//增加客户窗口
        var addWindow = new Ext.ux.Window({
    		title : '账户编辑',
    		width:390,
    		height:200,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ut_saveOrUpdate.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.hide();
    							store.reload();
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
        	if(gridrow!=null){
        		addForm.getForm().loadRecord(gridrow);
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
			items:grid
		}]
	});
    
    
    
    
    
    
    
    
    
    
});