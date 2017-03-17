/*
 *应收 
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	var vFields=[{name:'iType',type:'int'},
	             {name:'vcName',type:'string'},
	             {name:'dlMoney',type:'string'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'ome_getBalanceInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vFields
	});
	/*开始时间和结束时间*/
	var vStartDate=new Ext.form.DateField({name:'dts',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'账户类型',hidden:true,width:100,dataIndex:'iType',renderer:function(a){return a=='1'?'收入':'支出'}},
        	          {header:'账户',width:160,dataIndex:'vcName'},
        	          {header:'余额',width:180,dataIndex:'dlMoney'}]
        }),
        tbar:['开始日期：',vStartDate,'到',vEndDate,'-',{
        	text:'查询',
        	iconCls:'btn-list',
        	handler: function(){
        		funcinitgrid();
        	}
    }]
	});
	
	/*查询网格*/
	function funcinitgrid(){
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
			title:'资金余额',
			frame:true,
			region:'center',
			layout:'border',
			border:false,
			items:[vGridPanel]
		}]
	});
	
    funcinitgrid();
});