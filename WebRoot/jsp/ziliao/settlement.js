/**
 * 结算方式
 * */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var fieds=[{name:'id',type:'int'},
	           {name:'vcNo',type:'string'},
	           {name:'vcName',type:'string'},
	           {name:'vcRemark',type:'string'}];
	
	var store = new Ext.data.JsonStore({
	    url: 'ut_getInfo.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{type:'jsfs'}},
	    fields: fieds
	});
	
    var grid = new Ext.ux.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true },
			columns:[new Ext.grid.RowNumberer(),
			    {header: '结算方式编号',width:100, align:'center',dataIndex:'vcNo'},
			    {header: '结算方式名称', width: 150, align:'center',dataIndex: 'vcName'},
	            {header: '备注', width: 200, align:'center',dataIndex: 'vcRemark'},
	            {header: 'ID',hidden:true, width:80, align:'center',dataIndex: 'id'}]
        }),
        title:'结算方式管理',
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
					funcEdit("edit",record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的结算方式');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该结算方式？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "ut_delete.do?type=jsfs",
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
    	var vType=new Ext.form.Hidden({name:'type',value:'jsfs'});
    	var vVcNo= new Ext.form.TextField({name:'vcNo',fieldLabel:'结算方式编号',width:260,maxLength:10,allowBlank: false});
    	var vVcName= new Ext.form.TextField({name:'vcName',fieldLabel:'结算方式名称',width:260,maxLength:25,allowBlank: false});
    	var vRemark= new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:260,maxLength:50});
    	
    	var addForm = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            labelAlign: "right",
            labelWidth:80,
            defaults: { frame: false, border: false },
            items:[vId,vType,vVcNo,vVcName,vRemark]
        });

        var addWindow = new Ext.ux.Window({
    		title : '结算方式编辑',
    		width:390,
    		height:175,
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