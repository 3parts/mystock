/*
 * 信封打印
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vFields=[{name:'id',type:'int'},
	             {name:'dtBs',type:'string'},
	             {name:'vcNo',type:'string'},
	             {name:'khName',type:'string'},
	             {name:'deOutMoney',type:'string'},
	             {name:'icount',type:'int'},
	             {name:'dlSpellMoney',type:'string'},
	             {name:'dlSumMoney',type:'string'},
	             {name:'userName',type:'string'},
	             {name:'psName',type:'string'},
	             {name:'wlName',type:'string'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'spk_getprintInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vFields
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
	
	/*网格面板*/
	var vsm = new Ext.grid.CheckboxSelectionModel({ singleSelect: false }); /*复选框*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'center'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          new Ext.grid.CheckboxSelectionModel(),
        	          {header:'业务日期',width:100,dataIndex:'dtBs'},
        	          {header:'销售单号',width:120,dataIndex:'vcNo'},
        	          {header:'客户',width:120,dataIndex:'khName'},
        	          {header:'销售金额',align:'right',width:100,dataIndex:'deOutMoney'},
        	          {header:'拼包数',align:'right',width:100,dataIndex:'icount'},
        	          {header:'拼包金额',align:'right',width:100,dataIndex:'dlSpellMoney'},
        	          {header:'总金额',width:120,align:'right',dataIndex:'dlSumMoney'},
        	          {header:'配送方式',width:100,dataIndex:'psName'},
        	          {header:'物流公司',width:100,dataIndex:'wlName'},
        	          {header:'制单人',width:100,dataIndex:'userName'},
        	          {header:'id',hidden:true,dataIndex:'id'}]
        }),
        sm:vsm,
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vGridStore,
            items:['-','每页',pageCom,'条'],
            displayInfo: true
        })
	});
	
	var vPrint=new Ext.form.Checkbox({
		name:'s1',
		boxLabel:'打印金额为0'
	});
	
	 /*配送方式*/
    var storeLogis1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ut_getInfo.do?type=psfs",
        autoLoad: {},
        root : "root"
    });
    
    var vLogisCombo1= new Ext.form.ComboBox({
    	width:100,
    	name:'ilogistics',
    	fieldLabel :'配送方式',
    	store: storeLogis1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        hiddenName:'ilogistics'
    });
    
    /*物流公司*/
    var storeWl1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ut_getInfo.do?type=wlgs",
        autoLoad: {},
        root : "root"
    });
    var vWlCombo1= new Ext.form.ComboBox({
    	width:100,
    	name:'iwl',
    	fieldLabel :'物流公司',
    	store: storeWl1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        hiddenName:'iwl'
    });
	
	var vStartDate=new Ext.form.DateField({name:'dts',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
    /**条件区域**/
	var vToobar1=new Ext.Toolbar({
	    items: ['日期范围：',vStartDate,'到',vEndDate,'配送方式：',vLogisCombo1,'-',
	            '物流公司：',vWlCombo1,'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	maxLength:100,
        	emptyText:'编号/客户/制单人',
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
//	    		vGridStore.load({params:{
//	    			start:0,
//	    			limit:20,
//	    			key:Ext.getCmp('keyWord').getValue(),
//	    			dtSDate:vStartDate.getValue(),
//	    			dtEDate:vEndDate.getValue()
//	    		}});
        		funcinitgrid();
        	}
	    },'-',{
	    	text:'打印',
        	iconCls:'btn-print',
        	handler: function(){
	    	    var rows = vGridPanel.getSelectionModel().getSelections();
	    	    var ids = '';
	    	    if(rows==null || rows.length<1){
	    	    	Ext.Msg.alert('信息提示','请勾选要打印的记录');
	    	    	return;
	    	    }
	    	    /*遍历 行记录*/
	    	    for(var i=0;i<rows.length;i++){
	    	    	if(ids.length<=0){
	    	    		ids=rows[i].get('id');
	    	    	}else{
	    	    		ids+=','+rows[i].get('id');
	    	    	}
	    	    }
	    	    Ext.Ajax.request({
					url : "print_getSingInfo.do?key=tbsalenout",
					success : function(response) {
						var v=response.responseText;
						if(v.length==0){
							Ext.Msg.alert('信息提示','打印模板读取错误');
							return;
						}
						var v1=0;
						if(vPrint.getValue()){
							v1=1;
						}
						// 调用打印
						window.open('../../gridReport/MyPrint.jsp?data=spk_getXmlData.do?ids='+ids+'-'+v1+'&report='+v);
					}
				});
	    	    
	    	    
	    	    
	    	    
		    	/*var record= vGridPanel.getSelectionModel().getSelected();
	        	if(!record){
	        		Ext.Msg.alert('信息提示','请选择要打印的记录');
	        		return;
	        	}
				Ext.Ajax.request({
					url : "print_getSingInfo.do?key=tbsalenout",
					success : function(response) {
						var v=response.responseText;
						if(v.length==0){
							Ext.Msg.alert('信息提示','打印模板读取错误');
							return;
						}
						// 调用打印
						window.open('../../gridReport/MyPrint.jsp?data=spk_getXmlData.do?id='+record.get('id')+'&report='+v);
					}
				});*/
        	}
	    },'-',vPrint]
	});
	
	
	
	
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 25,
		items:[vToobar1]
	});
	
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('dtSDate',vStartDate.getValue());
		vGridStore.setBaseParam('dtEDate',vEndDate.getValue());
		vGridStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		vGridStore.setBaseParam('iwl',vWlCombo1.getValue());
		vGridStore.setBaseParam('ilogistics',vLogisCombo1.getValue());
		vGridStore.load({params:{
			start:0, 
			limit:pageCom.getValue()
		}});
	}

	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'信封打印',
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