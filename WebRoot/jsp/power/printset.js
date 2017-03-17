/**
 * 模板打印
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vTaFields=[{name:'id',type:'int'},
	               {name:'itype',type:'int'},
	               {name:'vcNo',type:'string'},
	               {name:'vcName',type:'string'},
	               {name:'vcTable',type:'string'},
	               {name:'vccode',type:'string'},
	               {name:'vcRemark',type:'string'}];
	
	/*企业数据源*/
	var vConpanyStore= new Ext.data.JsonStore({
		url:'print_getInfo.do',
		root:'root',
		totalProperty: 'total',
		autoLoad: {},
	    fields: vTaFields
	});
	
	var vChenge=new Ext.form.Hidden({id:'chenged',listeners:{
		disable:function(s){
			if(vChenge.getValue()!='ok'){
				Ext.Msg.alert('信息提示',vChenge.getValue());
			}else{
				Ext.Msg.alert('信息提示','导入成功！');
				vConpanyStore.load({params:{key:Ext.getCmp('keyWord').getValue()}});
			}
		}
	}});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		store:vConpanyStore,
		title:'模板管理',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'模板类型',width:80,dataIndex:'itype',renderer:function(a){return a=="1"?"打印":"报表";}},
        	          {header:'编号',width:120,dataIndex:'vcNo'},
        	          {header:'名称',width:120,dataIndex:'vcName'},
        	          {header:'所属表',width:100,dataIndex:'vcTable'},
        	          {header:'文件名称',width:160,dataIndex:'vccode'},
        	          {header:'备注',width:200,dataIndex:'vcRemark'},
        	          {header:'id',hidden:true,dataIndex:'id'}]
        }),
        tbar:[{
        	text:'设计',
        	iconCls:'btn-edit',
        	handler: function(){
        	var record= vGridPanel.getSelectionModel().getSelected();
        	if(!record){
        		Ext.Msg.alert('信息提示','请选择要设计的模板');
        		return;
        	}
        	window.open('../../gridReport/MyDesign.jsp?report='+record.get("vccode")+'&id='+record.get("id"));
        	}
        },'-',{
        	text:'导入',
        	iconCls:'btn-give',
        	handler: function(){
	        	var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
	            	Ext.Msg.alert('信息提示','请选择一条记录进行操作');  
				}else{
					Ext.ux.UpLoadWidow('print_uploadFile.do?id='+record.get('id'),'上传grf打印模板','chenged',false);
				}
        	
        	}
        },'-',{
        	text:'导出',
        	iconCls:'btn-excelicon',
        	handler: function(){
	        	var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
	            	Ext.Msg.alert('信息提示','请选择一条记录进行操作');  
				}else{
					window.open("print_downloadFile.do?vccode="+record.get('vccode')+"&vcName="+record.get('vcName'));
				}
        	}
        },'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:160,
        	maxLength:100,
        	emptyText:'请输入关键字...'
        },'-',{
            	text:'查询',
            	iconCls:'btn-list',
            	handler: function(){
        			vConpanyStore.load({params:{key:Ext.getCmp('keyWord').getValue()}});
            	}
        }]
	});
	
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