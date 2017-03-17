/*
 * 库存查询
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vfield=[{name:'id',type:'int'},
	            {name:'commodityId',type:'int'},
	            {name:'warehouseId',type:'int'},
	            {name:'vcBatch',type:'string'},
	            {name:'vcSn',type:'string'},
	            {name:'dlQty',type:'string'},
	            {name:'vcDw',type:'string'},
	            {name:'commodityNo',type:'string'},
	            {name:'commodityName',type:'string'},
	            {name:'commodityType',type:'string'},
	            {name:'commodityGg',type:'string'},
	            {name:'commodityColor',type:'string'},
	            {name:'dbAverageMoney',type:'string'},
	            {name:'dbSuggMoney',type:'string'},
	            {name:'warehouseName',type:'warehouseName'},
	            {name:'dlSumMoney',type:'string'},
	            {name:'vcRemark',type:'string'},
	            {name:'vcFactoryNo',type:'string'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'dly_getStockInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vfield
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'仓库',width:60,dataIndex:'warehouseName'},
        	          {header:'商品编码',width:100,dataIndex:'commodityNo'},
        	          {header:'商品名称',width:120,dataIndex:'commodityName'},
        	          {header:'厂家编码',width:100,dataIndex:'vcFactoryNo'},
        	          {header:'库存数量',width:70,align:'right',dataIndex:'dlQty',renderer:function(a){return Ext.ux.ConvertFloat(a,2);}},
        	          {header:'单位',width:40,dataIndex:'vcDw'},
        	          {header:'批次',width:50,dataIndex:'vcBatch'},
        	          {header:'辅助标识',width:80,dataIndex:'vcSn'},
        	          {header:'成本均价',width:70,align:'right',dataIndex:'dbAverageMoney',renderer:function(a){return Ext.ux.ConvertFloat(a,2);}},
        	          {header:'建议售价',width:70,align:'right',dataIndex:'dbSuggMoney',renderer:function(a){return Ext.ux.ConvertFloat(a,2);}},
        	          {header:'库存总值',width:80,align:'right',dataIndex:'dlSumMoney',renderer:function(a){return Ext.ux.ConvertFloat(a,2);}},
        	          {header:'类别',width:80,dataIndex:'commodityType'},
        	          {header:'规格',width:60,dataIndex:'commodityGg'},
        	          {header:'颜色',width:60,dataIndex:'commodityColor'},
        	          {header:'备注',width:180,dataIndex:'vcRemark'},
        	          {header:' ',width:30,dataIndex:'1'}]
        }),
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vGridStore,
            displayInfo: true
        })
	});
	
    /*仓库*/
    var storeCk=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ck_getCkInfo.do",
        autoLoad: {},
        root : "root"
    });
    var vCkCombo= new Ext.form.ComboBox({
    	width:100,
    	name:'warehouseId',
    	fieldLabel :'仓库',
    	store: storeCk,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        hiddenName:'warehouseId'
    });
    
    /*类别*/
    var storelb=new Ext.data.JsonStore({
    	fields: ['value', 'text'],
        url : "splb_findCombox.do",
        autoLoad: {},
        root : "root"
    });
    var vlbCombo= new Ext.form.ComboBox({
    	width:100,
    	name:'lbid',
    	fieldLabel :'类别',
    	store: storelb,
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        hiddenName:'lbid'
    });
    
    /*数字范围*/
	var vMinCount=new Ext.form.NumberField({name:'minCount',width:80,decimalPrecision:2});
	var vMaxCount=new Ext.form.NumberField({name:'maxCount',width:80,decimalPrecision:2});
    
	
	/*工具栏按钮*/
	var vToobar=new Ext.Toolbar({
	    items: ['仓库：',vCkCombo,'-','类别：',vlbCombo,'-',
	            '库存数量：',vMinCount,'到',vMaxCount,'-',
	            '关键字：',{
        	xtype:'textfield',
        	id:'keyWord',
        	maxLength:200,
        	emptyText:'商品/编号/厂家编码/备注',
        	listeners:{
        		specialKey:function(field, e){
        			if (e.getKey() == e.ENTER){
        				funcinitgrid();
        			}
        		}
        	}
        	},'-',{
        	text:'查询',
        	iconCls:'btn-list',
        	handler: function(){
//	    		vGridStore.load({params:{
//	    			start:0, 
//    				limit:20,
//	    			key:Ext.getCmp('keyWord').getValue(),
//	    			warehouseId:vCkCombo.getValue(),
//	    			minCount:vMinCount.getValue(),
//	    			maxCount:vMaxCount.getValue()}});
        		funcinitgrid();
        	}
	    }]
	});
	
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('warehouseId',vCkCombo.getValue());
		vGridStore.setBaseParam('minCount',vMinCount.getValue());
		vGridStore.setBaseParam('maxCount',vMaxCount.getValue());
		vGridStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		vGridStore.setBaseParam('lbid',vlbCombo.getValue());
		vGridStore.load({params:{
			start:0, 
			limit:20
		}});
	}
	
	
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 28,
		items:[vToobar]
	});
	
	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'库存查询',
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
