/**
 * 仓库管理
 * */

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var fieds=[{name:'id',type:'int'},
	           {name:'vcNo',type:'string'},
	           {name:'vcName',type:'string'},
	           {name:'vcAddress',type:'string'},
	           {name:'vcRemark',type:'string'}];
	
	var store = new Ext.data.JsonStore({
	    url: 'ck_getCkInfo.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{}},
	    fields: fieds
	});

    var grid = new Ext.ux.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true },
			columns:[new Ext.grid.RowNumberer(),
			    {header: '仓库编号',width:100, align:'center',dataIndex:'vcNo'},
			    {header: '仓库名称', width: 150, align:'center',dataIndex: 'vcName'},
	            {header: '仓库地址', width:300, align:'center', dataIndex: 'vcAddress'},
	            {header: '备注', width: 200, align:'center',dataIndex: 'vcRemark'},
	            {header: 'ID',hidden:true, width:80, align:'center',dataIndex: 'id'}]
        }),
        title:'仓库管理',
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
                	Ext.Msg.alert('信息提示','请选择要删除的仓库');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该仓库？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "ck_delete.do",
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
    	var vVcNo= new Ext.form.TextField({name:'vcNo',fieldLabel:'仓库编号',width:260,maxLength:10,allowBlank: false});
    	var vVcName= new Ext.form.TextField({name:'vcName',fieldLabel:'仓库名称',width:260,maxLength:25,allowBlank: false});
    	var vVcAddress= new Ext.form.TextArea({name:'vcAddress',fieldLabel:'仓库地址',width:260,maxLength:100,height:60});
    	var vRemark= new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:260,maxLength:50});
    	
    	var addForm = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            labelAlign: "right",
            labelWidth:70,
            defaults: { frame: false, border: false },
            items:[vId,vVcNo,vVcName,vVcAddress,vRemark]
        });
    	//增加客户窗口
        var addWindow = new Ext.ux.Window({
    		title : '仓库编辑',
    		width:390,
    		height:260,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ck_saveorupdate.do',
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