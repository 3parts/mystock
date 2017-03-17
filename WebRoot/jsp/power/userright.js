/**
 * 用户权限管理
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	var nodeId='0'; /*选中节点的ID*/
	
	/******左边树形面板*******/
	var tree = new Ext.tree.TreePanel({
		title:'企业信息',
		height:300,
        split : true,
		useArrows: true,
        autoScroll:true,
        animate:true,
        enableDD:false,
        containerScroll: true,
        frame:true,
        checked:false,
        dataUrl: 'qy_LoadTreeInfo.do',
        root: {
            text: '所有企业',
            draggable: false,
            id: '0'
        },
        listeners:{
        	click:function(n){
        		nodeId=n.id;
        		vuserStore.load({params:{companyid:nodeId,keyword:Ext.getCmp('keyWord1').getValue()}});
        	}
        },
        tbar:[{
        	xtype:'textfield',
        	id:'keyWord',
        	width:300,
        	maxLength:100,
        	selectOnFocus : true, 
        	emptyText:'请输入关键字...回车查询',
        	listeners : {  
            specialKey : function(field, e) {  
                if (e.getKey() == Ext.EventObject.ENTER) {/*响应回车*/
                	var vk = field.getValue();
                	tree.loader.dataUrl = 'qy_LoadTreeInfo.do?treekey='+vk+''; /*重新指定参数*/
                	tree.root.reload();
                	
                }  
            }  
        }  
        }]
	});
	tree.root.expand();
	
	/**右边树形面板**/
	var tree1 = new Ext.tree.TreePanel({
		title:'用户所能操作的企业数据',
		//region:'east',
		height:300,
        split : true,
		useArrows: true,
        autoScroll:true, 
        animate:true,
        enableDD:false,
        containerScroll: true,
        checked:true,
        frame:true,
        dataUrl: 'qy_LoadTreeInfo1.do',
        root: {
            text: '所有企业',
            draggable: false,
            id: '0',
            checked:false
        },
        tbar:[{
        	text:'保存用户权限',
        	iconCls:'btn-save',
        	handler: function(){
	        	var row = vGridPanel.getSelectionModel().getSelections();
	        	if(row.length!=1){Ext.Msg.alert('提示','请选择一个用户进行操作！');}
	        	Ext.MessageBox.confirm('提示', '确定要保存用户['+row[0].data.username+']的权限设置吗？', function(c){
	        		if(c=='yes'){
	        			Ext.Msg.wait('提示','正在进行操作，请稍候');  
	        			var vunits="";
	        			var vchecks=tree1.getChecked();
	        			for(var i=0;i<vchecks.length;i++){vunits += vchecks[i].id + ",";}
	        			Ext.Ajax.request({
	        				url:'ur_saveUserRight.do',
	        				params:{userid:row[0].data.userid,units:vunits},
				   			success : function(response) {
				   			    var Result=Ext.decode(response.responseText);
				   			    Ext.Msg.alert('信息提示',Result.msg);
				   			}
	        			});
	        		}
	        	});
        	
        	}
        }],
        listeners:{
        	checkChange:function(node, checked){
        		Ext.ux.SetChildNodeChecked(node);
    		}
        }
	});
	tree1.root.expand();
	
	/******用户网格面板**********/
	var vuserFild=[{name:'logincode',type:'string'},
	              {name:'username',type:'string'},
	              {name:'userid',type:'int'}];
	var vuserStore= new Ext.data.JsonStore({
		url:'user_findUser.do',
		root:'root',
		autoLoad: {},
		totalProperty: 'total',
	    fields: vuserFild
	});
	
	var vGridPanel= new Ext.ux.GridPanel({
		store:vuserStore,
		height:300,
		title:'用户信息',
		cm:new Ext.grid.ColumnModel({
			defaults: {sortable: true,align:'conter'},
			columns:[new Ext.grid.RowNumberer(),
			         {header:'用户名称',width:100,dataIndex: 'logincode'},
			         {header:'登录名称',width:100,dataIndex:'username'},
			         {header:'ID',width:80,dataIndex:'userid'}]
			
		}),
		tbar:{
        	xtype:'textfield',
        	id:'keyWord1',
        	maxLength:100,
        	emptyText:'请输入关键字...回车查询',
        	listeners : {  
                specialKey : function(field, e) {  
                    if (e.getKey() == Ext.EventObject.ENTER) {/*响应回车*/
                    	vuserStore.load({params:{companyid:nodeId,keyword:field.getValue()}});
                    	
                    }  
                }  
            }
        },
        listeners: {
            "rowclick": function (grid, rowIndex, event) {
        			var row = grid.getSelectionModel().getSelections();
                    tree1.loader.dataUrl = 'qy_LoadTreeInfo1.do?userid='+row[0].data.userid+''; /*重新指定参数*/
                	tree1.root.reload();
                	tree1.root.expand();
        }
        }
	});
	
	var panel1=new Ext.Panel({
		border:true,
		title:'面板',
		hieght:600,
		items:[]
	});
	
	 /****************主面板********************************/
	 var panel = new Ext.Panel({
	        layout: "column",
	        autoScroll: false,
	        autoWidth: true,
	        frame: false,
	        border: false,
	        defaults: { border: false },
	        items: [{ columnWidth: 0.5, items: [tree] },
	                { columnWidth: 0.5, items: [tree1]}, 
	                { columnWidth: 1, items: [vGridPanel]}]
	        
	    });
	 
	 
		//布局
	    new Ext.Viewport({
			layout:'border',
			items:[{
				region:'center',
				layout:'fit',
				border:false,
				items:panel
			}]
		});
});