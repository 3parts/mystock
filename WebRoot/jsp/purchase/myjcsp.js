/**
 * 功能：进出商品
 * 作者：RC
 * 日期：2015-07-21
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
    
    /*客户*/
    var storeKh=new Ext.data.JsonStore({
    	fields: ['value', 'text'],
        url : "kh_findKhComb.do",
        //autoLoad: {},
        root : "root"
    });
    var vKhCombo= new Ext.ux.LinkComboBox({
    	width:120,
    	name:'ikh',
    	fieldLabel :'客户',
    	store: storeKh,
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        emptyText:'编号/名称...回车检索'
    });
    
    /*供应商*/
    var store11=new Ext.data.JsonStore({
    	fields: ['value', 'text'],
        url : "gys_findGysComb.do",
        //autoLoad: {},
        root : "root"
    });
    var vGysCombo= new Ext.ux.LinkComboBox({
    	width:120,
    	name:'gysId',
    	fieldLabel :'供应商',
    	store: store11,
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        hiddenName:'gysId',
        emptyText:'编号/名称...回车检索'
    });
    
    /**条件区域**/
	var vToobar1=new Ext.Toolbar({
	    items: ['日期范围：',vStartDate,'到',vEndDate,'-','供应商：',vGysCombo]
	});
	var vToobar2=new Ext.Toolbar({
	    items: ['客户：',vKhCombo,'-','单类：',vTypeCombo,'-','关键字：',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:120,
        	maxLength:100,
        	emptyText:'编号/名称/制单人',
        	listeners:{
	    		specialKey:function(field, e){
	    			if (e.getKey() == e.ENTER){
	    				funcinitgrid();
	    			}
	    		}
    		}},'-',{
        	text:'查询',
        	iconCls:'btn-list',
        	handler: function(){
        		funcinitgrid();
        	}
	    }]
	});
	
	/*工具栏按钮*/
	var vToobar=new Ext.Toolbar({
		items:[{
        	text:'打印',
        	iconCls:'btn-print',
        	handler: function(){
				var xml = funcInitPrintData();
				Ext.Ajax.request({
					url : "print_getSingInfo.do?key=jcsp",
					success : function(response) {
						var v=response.responseText;
						if(v.length==0){
							Ext.Msg.alert('信息提示','打印模板读取错误');
							return;
						}
						// 调用打印
						openWindowWithPost('../../gridReport/MyPrint.jsp?inqu=1&report='+v,'进出商品打印','xml',xml);
						//window.open('../../gridReport/MyPrint.jsp?inqu=1&xml='+xml+'&);
					}
				});
				
        	}
        }]
	});
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 80,
		items:[vToobar,vToobar1,vToobar2]
	});
    
	var pageCom=new Ext.form.ComboBox({
		store: new Ext.data.SimpleStore({   
            fields: ['text', 'value'],   
            data: [['10', 10], ['20', 20], ['50', 50], ['100', 100], ['200', 200],['1000',1000]]   
        }),
        mode: 'local',   
        displayField: 'text',   
        valueField: 'value',   
        editable: false,   
        allowBlank: false,   
        triggerAction: 'all',
        value:20,
        width:60,
        listeners:{
			'select':function(comboBox){
		        comboBox.ownerCt.pageSize=parseInt(comboBox.getValue());
				funcinitgrid();
			}
		}
	});
    
    var store = new Ext.data.JsonStore({
		url:'other_getJcNoInfo.do',
		root:'root',
		totalProperty: 'total',
		fields:[{name:'dtBs',type:'string'},
		        {name:'typeNo',type:'string'},
		        {name:'billId',type:'int'},
		        {name:'billName',type:'string'},
		        {name:'vcNo',type:'string'},
		        {name:'commodityId',type:'int'},
		        {name:'spName',type:'string'},
		        {name:'iCount',type:'string'},
		        {name:'vcDw',type:'string'},
		        {name:'dePurchaseMoney',type:'string'},
		        {name:'zkMoney',type:'string'},
		        {name:'deSumMoney',type:'string'},
		        {name:'psInfo',type:'int'},
		        {name:'psName',type:'string'},
		        {name:'wlInfo',type:'int'},
		        {name:'wlName',type:'string'},
		        {name:'jsInfo',type:'int'},
		        {name:'jsName',type:'string'},
		        {name:'vcBatch',type:'string'},
		        {name:'vcSn',type:'string'},
		        {name:'warehouseId',type:'int'},
		        {name:'warehouseName',type:'string'},
		        {name:'iPayState',type:'int'},
		        {name:'vcRemark',type:'string'},
		        {name:'username',type:'string'}]
	});
    /*网格面板*/
    var vsm = new Ext.grid.CheckboxSelectionModel({ singleSelect: false }); /*复选框*/
	var grid= new Ext.ux.GridPanel({
		region:'center', 
		store:store,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'center'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          new Ext.grid.CheckboxSelectionModel(),
        	          {header:'业务日期',width:80,dataIndex:'dtBs',renderer:function(a){if(a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'单类',width:80,dataIndex:'typeNo'},
        	          {header:'单据编号',width:100,dataIndex:'vcNo'},
        	          {header:'供应商或客户',width:130,dataIndex:'billName'},
        	          {header:'商品名称',width:120,dataIndex:'spName'},
        	          {header:'数量',width:80,dataIndex:'iCount',align:'right'},
        	          {header:'单位',width:80,dataIndex:'vcDw'},
        	          {header:'单价',width:80,dataIndex:'dePurchaseMoney',align:'right'},
        	          {header:'折扣',width:80,dataIndex:'zkMoney'},
        	          {header:'金额',width:100,dataIndex:'deSumMoney',align:'right'},
        	          {header:'配送方式',width:80,dataIndex:'psName'},
        	          {header:'物流公司',width:100,dataIndex:'wlName'},
        	          {header:'结算方式',width:80,dataIndex:'jsName'},
        	          {header:'结算状态',width:80,dataIndex:'iPayState',renderer:function(a){if(a==undefined) return ""; return a=='1'?'已结算':'未结算';}},
        	          {header:'批次',width:80,dataIndex:'vcBatch'},
        	          {header:'辅助标识',width:80,dataIndex:'vcSn'},
        	          {header:'仓库',width:100,dataIndex:'warehouseName'},
        	          {header:'备注',hidden:true,width:100,dataIndex:'vcRemark'},
        	          {header:'制单人',width:80,dataIndex:'username'},
        	          {header:' ',width:30,dataIndex:'a'}]
        }),
        sm:vsm,
        bbar: new Ext.PagingToolbar({
            pageSize: pageCom.getValue(),
            store: store,
            items:['-','每页',pageCom,'条'],
            displayInfo: true
        })
	});
	
	store.on('load',function(){
		gridSum(grid);
	});

	
	
	/**
	 * 功能：加载网格
	 * 作者：RC
	 * 日期：2015-07-17
	 * */
	function funcinitgrid(){
		store.setBaseParam('vctype',vTypeCombo.getValue());
		store.setBaseParam('khId',vKhCombo.getValue());
		store.setBaseParam('gysId',vGysCombo.getValue());
		store.setBaseParam('dts',vStartDate.getValue());
		store.setBaseParam('dte',vEndDate.getValue());
		store.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		store.load({params:{
			start:0, 
			limit:pageCom.getValue()
		}});
	}
	
	/**
	 * 功能：post提交key value
	 * 作者：RC
	 * 创建日期：2015-07-25
	 * */
	function openWindowWithPost(url, name, key, value) {
	    var newWindow = window.open(url, name);
	    if (!newWindow)
	        return false;
	    var html = "";
	    html += "<html><head></head><body><form id='formid' method='post' action='" + url + "'>";
	    html += "<input type='hidden' name='" + key + "' value='" + value + "'/>";
	    html += "</form><script type='text/javascript'>";
	    html += "if(navigator.userAgent.indexOf('Trident')=='-1'){document.charset='utf-8';}";
	    html += "document.getElementById('formid').submit();";
	    html += "<\/script></body></html>".toString().replace(/^.+?\*|\\(?=\/)|\*.+?$/gi, ""); 
	    newWindow.document.write(html);
	    return newWindow;
	}
	
	/**
	 * 功能：获得打印的数据
	 * 作者：RC
	 * 日期：2015-07-24
	 * */
	function funcInitPrintData(){
		var rows = grid.getSelectionModel().getSelections();
		if(rows==null || rows.length<1){
	    	Ext.Msg.alert('信息提示','请勾选要打印的记录');
	    	return;
	    }
		var colmns = grid.getColumnModel();
		/**凭借xml打印数据**/
		var vXml = "<xml>";
		for(var i=0;i<rows.length;i++){
			
		}
		
		for(var i=0;i<rows.length;i++){
			vXml+="<row>";
			/**拼接结算金额和未结算金额**/
			if(rows[i].get('iPayState')=='1'){
				vXml+="<结算金额>"+rows[i].get('deSumMoney')+"</结算金额>";
				vXml+="<未结算金额>0</未结算金额>";
			}else{
				vXml+="<结算金额>0</结算金额>";
				vXml+="<未结算金额>"+rows[i].get('deSumMoney')+"</未结算金额>";
			}
			for(var j=0;j<colmns.getColumnCount(false);j++){
				var header = colmns.getColumnHeader(j);
				if(header==undefined) continue;
				var value = rows[i].get(colmns.getDataIndex(j));
				if(value==undefined) continue;
				if(header=='结算状态'){
					value=value=='1'?'已结算':'未结算';
				}
				if(header=='业务日期'){
					value=(new Date(parseInt(value))).format('Y-m-d');
				}
				vXml+="<"+header+">"+value+"</"+header+">";
			}
			vXml+="</row>";
		}
		vXml+="</xml>";
		return vXml;
	}
    
	
	/**
	 * 网格设置合计
	 * */
	function gridSum(grid){
    	var sum1=0;
    	var sum2=0;
    	grid.store.each(function(row){
    		 sum1+=parseFloat(row.data.deSumMoney);
    		 sum2+=parseFloat(row.data.iCount);
    	});
    	grid.store.insert(grid.getStore().getCount(), new Ext.data.Record({dtBs:'',iCount:sum2.toFixed(2),deSumMoney:sum1.toFixed(2),typeNo:'合计'}));
    }
	
	//布局
    new Ext.Viewport({
		layout:'border',
		items:[{
			region:'center',
			layout:'border',
			border:false,
			items:[vPanel,grid]
		}]
	});
    
    funcinitgrid();
});