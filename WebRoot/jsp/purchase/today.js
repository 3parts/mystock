/**
 * 功能：今日报表
 * 作者：RC
 * 日期：2015-07-17
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	/*开始时间和结束时间*/
	var vStartDate=new Ext.form.DateField({name:'dts',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	/*单据状态*/
    var vTypeCombo= new Ext.form.ComboBox({
    	width:80,
    	fieldLabel :'单据类型',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['全部','全部'],['销售','销售'],['销退','销退'],['购进','购进'],['购退','购退']]
    		   }),
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        value:'全部',
        editable:false
    });
	
	
	var store = new Ext.data.JsonStore({
		url:'other_getToDayInfo.do',
		root:'root',
		totalProperty: 'total',
		fields:[{name:'dtBs',type:'string'},
		        {name:'vctype',type:'string'},
		        {name:'vcNo',type:'string'},
		        {name:'qty',type:'string'},
		        {name:'money',type:'string'},
		        {name:'cbmoney',type:'string'},
		        {name:'sdt',type:'string'},
		        {name:'edt',type:'string'}]
	});
	
	/*网格面板*/
	var grid= new Ext.ux.GridPanel({
		region:'center',
		store:store,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'center'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'开始日期',width:100,dataIndex:'sdt',renderer:function(a){if(a!=undefined && a.length>0) return a; return new Date(vStartDate.getValue()).format('Y-m-d');}},
        	          {header:'截止日期',width:100,dataIndex:'edt',renderer:function(a){if(a!=undefined && a.length>0) return a; return new Date(vEndDate.getValue()).format('Y-m-d');}},
        	          {header:'业务日期',width:100,hidden:true,dataIndex:'dtBs',renderer:function(a){if( a==undefined || a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'单据类型',width:80,dataIndex:'vctype'},
        	          {header:'单据编号',width:180,dataIndex:'vcNo',hidden:true},
        	          {header:'数量',width:80,align:'right',dataIndex:'qty'},
        	          {header:'金额',width:100,align:'right',dataIndex:'money'},
        	          {header:'毛利',width:100,align:'right',dataIndex:'cbmoney',renderer:function(a,b,c){
        	        	  var money = c.get('money')-a;
        	        	  //return Ext.ux.ConvertFloat(money,2);
        	        	 if(c.get('vctype')==undefined)return "";
        	        	  return c.get('vctype')=='销售'?Ext.ux.ConvertFloat(money,2):0;
        	          }},
        	          {header:'',width:30,dataIndex:'1'}]
        }),
        tbar:['日期范围：',vStartDate,'到',vEndDate,'-',{
        	text:'查询',
        	iconCls:'btn-list',
        	handler:function(){
        		funcinitgrid();
        	}
        }],
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: store,
            displayInfo: true
        })
	});
	
	/**
	 * 功能：加载网格
	 * 作者：RC
	 * 日期：2015-07-17
	 * */
	function funcinitgrid(){
		//store.setBaseParam('vctype',vTypeCombo.getValue());
		store.setBaseParam('dts',vStartDate.getValue());
		store.setBaseParam('dte',vEndDate.getValue());
		store.load({params:{
			start:0, 
			limit:20
		}});
		
		/*后台查询库存数量和库存总值*/
		Ext.Ajax.request({
			url:'other_getStockMoney.do',
			success:function(res){
				var json = Ext.decode(res.responseText);
				grid.store.insert(grid.getStore().getCount(), new Ext.data.Record({sdt:' ',edt:' ',money:' ',cbmoney:' '}));
		    	grid.store.insert(grid.getStore().getCount(), new Ext.data.Record({sdt:'库存总数：',edt:parseFloat(json.root[0].n).toFixed(2),qty:'库存总值：',money:parseFloat(json.root[0].m).toFixed(2)}));
			}
		});
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
    
	/*查询网格*/
    funcinitgrid();
});