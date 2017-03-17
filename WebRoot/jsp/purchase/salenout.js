/*
 * 销售出库
 */
Ext.onReady(function(){

	Ext.QuickTips.init();
	
	var vFields=[{name:'id',type:'int'},
	             {name:'vcNo',type:'string'},
	             {name:'dtBs',type:'string'},
	             {name:'ikh',type:'int'},
	             {name:'khName',type:'string'},
	             {name:'vcTel',type:'string'},
	             {name:'isaleType',type:'int'},
	             {name:'isettlement',type:'int'},
	             {name:'settlementName',type:'string'},
	             {name:'ipay',type:'int'},
	             {name:'userId',type:'int'},
	             {name:'userName',type:'string'},
	             {name:'vcAddress',type:'string'},
	             {name:'ilogistics',type:'int'},
	             {name:'iwl',type:'int'},
	             {name:'wlName',type:'string'},
	             {name:'logisticsName',type:'string'},
	             {name:'vcYunNo',type:'string'},
	             {name:'deOutMoney',type:'string'},
	             {name:'deDiscount',type:'string'},
	             {name:'deOkOutMoney',type:'string'},
	             {name:'deOweMoney',type:'string'},
	             {name:'deOtherMoeny',type:'string'},
	             {name:'vcRemark',type:'string'},
	             {name:'icbill',type:'int'},
	             {name:'fidel',type:'int'},
	             {name:'vcPhone',type:'string'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'salenout_getInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vFields
	});
	/**作废 记录**/
	vGridStore.on('load',function(store,records){
		var girdcount=0;
		store.each(function(r){
			if(r.get('fidel')=='1'){
				vGridPanel.getView().getRow(girdcount).style.backgroundColor='#FF8484';
			}
			girdcount=girdcount+1;
		});
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'应收单状态',width:80,dataIndex:'icbill',renderer:function(a){if(a==undefined)return '';return a=='1'?'已生成':'未生成';}},
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'客户名称',width:160,dataIndex:'khName'},
        	          {header:'销售金额',width:80,align:'right',dataIndex:'deOutMoney'},
        	          {header:'上欠',width:60,align:'right',dataIndex:'deOweMoney'},
        	          {header:'运单号/时间/联系人/送货地址',width:160,dataIndex:'vcYunNo'},
        	          {header:'配送方式',width:60,dataIndex:'logisticsName'},
        	          {header:'物流公司',width:100,dataIndex:'wlName'},
        	          {header:'备注',width:120,dataIndex:'vcRemark'},
        	          {header:'单据编号',width:140,dataIndex:'vcNo',renderer:function(a,b,c){if(a==undefined)return '';return c.data['fidel']=='1'?"已作废/"+a:a;}},
        	          {header:'结算方式',width:100,dataIndex:'settlementName'},
        	          {header:'结算状态',width:100,dataIndex:'ipay',renderer:function(a){if(a==undefined)return '';return a=='1'?'已结算':'未结算';}},
        	          {header:'销售类型',width:100,dataIndex:'isaleType',renderer:function(a){if(a==undefined)return '';return a=='1'?'零售':'批发'}},
        	          {header:'制单人',width:100,dataIndex:'userName'},
        	          {header:'联系电话',hidden:true,width:100,dataIndex:'vcTel'},
        	          {header:'地址',width:160,hidden:true,dataIndex:'vcAddress'},
        	          {header:'折扣率',hidden:true,width:100,dataIndex:'deDiscount'},
        	          {header:'实收金额',hidden:true,width:100,dataIndex:'deOkOutMoney'},
        	          {header:'其他金额',hidden:true,width:100,dataIndex:'deOtherMoeny'},
        	          {header:'ikh',hidden:true,dataIndex:'ikh'},
        	          {header:'isettlement',hidden:true,dataIndex:'isettlement'},
        	          {header:'ilogistics',hidden:true,dataIndex:'ilogistics'},
        	          {header:'iwl',hidden:true,dataIndex:'iwl'}]
        }),
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vGridStore,
            displayInfo: true
        })
	});
//	vGridStore.on('load',function(){
//		gridSum(vGridPanel);
//	});
	
	/*工具栏按钮*/
	var vToobar=new Ext.Toolbar({
	    items: [{
        	text:'添加',
        	iconCls:'btn-add',
        	handler: function(){
        		funcEdit("add"); 
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的单据');
				}else{
					if(record.get('icbill')=='1'){
						Ext.Msg.alert('信息提示','已生成应收单的记录不允许操作');
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的记录不允许操作');
						return;
					}
					funcEdit("edit",record); 
				}
        	}
        },'-',{
        	text:'查看',
        	iconCls:'btn-list',
        	handler: function(){
        	var record= vGridPanel.getSelectionModel().getSelected(); 
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要查看的单据');
			}else{
				funcEdit("read",record); 
			}
        	}
        },'-',{
        	text:'作废',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要作废的单据');  
				}else{
					if(record.get('icbill')=='1'){
						Ext.Msg.alert('信息提示','已生成应收单的记录不允许操作');
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的记录不允许操作');
						return;
					}
					Ext.MessageBox.confirm('作废提示', '是否要作废该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "salenout_deleteInfo.do",
					   			params:{ id : record.get("id") },
					   			success : function(res) {
					   				//vGridStore.reload();
					   				var json = Ext.decode(res.responseText);
					   				if(json.errors){
					   					Ext.Msg.alert('错误提示',json.errors);
					   					return;
					   				}
					   				funcinitgrid();
					   			},
					   			failure : function(res){
					   				
					   				
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	text:'生成应收单',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要生成的单据');  
				}else{
					if(record.get('icbill')=='1'){
						Ext.Msg.alert('信息提示','已生成的单据不允许再次生成');  
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的单据不允许再次生成');  
						return;
					}
					Ext.MessageBox.confirm('信息提示', '确定把选择的单据生成应收单吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "salenout_saveArInfo.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				//vGridStore.reload();
					   				funcinitgrid();
					   				Ext.Msg.alert('信息提示','生成成功!');
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	text:'添加拼包',
        	iconCls:'btn-add2',
        	handler: function(){
        	var record= vGridPanel.getSelectionModel().getSelected(); 
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要添加拼包的单据');
			}else{
				if(record.get('ipay')=='1' || record.get('fidel')=='1'){
					Ext.Msg.alert('信息提示','已结算或已作废的记录不允许进行此操作');
					return;
				}
				
				/*添加拼包*/
				var vSpellName=new Ext.form.TextField({name:'vcSpllName',width:180,fieldLabel:'拼包人',allowBlank:false,maxLength:10});
				var vMoney=new Ext.form.NumberField({name:'dlMoney',width:180,fieldLabel:'金额',decimalPrecision:2,allowBlank:false});
				var vRemark=new Ext.form.TextArea({name:'vcRemark',width:180,height:80,fieldLabel:'备注',maxLength:100});
				
				/*表单界面 布局*/
		        var vPanelForm=new Ext.Panel({
		        	frame: true,
		            border: false,
		            labelAlign: "right",
		            layout: "column",
		            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
		            items: [{
		                columnWidth: 1,
		                items: [vSpellName,vMoney,vRemark]
		            }]
		        });
		        var addForm = new Ext.form.FormPanel({
		        	frame: true,
		            border: false,
		            layout: "column",
		            defaults: { frame: false, border: false },
		            items:[vPanelForm]
		        });
		        /*添加*/
		        var addWindow = new Ext.ux.Window({
		    		title : '拼包添加',
		    		closeAction:'close',
		    		height:230,
		    		width:320,
		    		collapsible : true,
		    		items : [addForm],
		    		buttons : [{
		    			text : '保存',
		    			handler : function() {
		    				if (addForm.getForm().isValid()) {
		    					addForm.getForm().submit({
		    						url : 'salenout_saveSpll.do?id='+record.get('id'),
		    						success : function(form, action) {
		    							Ext.Msg.alert('信息提示',action.result.message);
		    							addWindow.close();
		    						},
		    						failure : function(form, action) {
		    							if(action.result.errors){
		    								Ext.Msg.alert('信息提示',action.result.errors);
		    							}else{
		    								Ext.Msg.alert('信息提示',action.result.message);
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
		    				addWindow.close();
		    			}
		    		}]
		    	});
		        addWindow.show();
			}
        	}
        },'-',{
        	text:'查看拼包',
        	iconCls:'btn-list',
        	handler:function(){
        	var record= vGridPanel.getSelectionModel().getSelected(); 
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要添加拼包的单据');
			}else{
				var vid=record.get('id');
				var vno=record.get('vcNo');
				var vfield1=[{name:'id',type:'int'},
				            {name:'salenId',type:'int'},
				            {name:'salenNo',type:'string'},
				            {name:'khId',type:'int'},
				            {name:'khName',type:'string'},
				            {name:'dtBs',type:'string'},
				            {name:'vcSpllName',type:'string'},
				            {name:'dlMoney',type:'string'},
				            {name:'ipayState',type:'int'},
				            {name:'dtPay',type:'string'},
				            {name:'vcRemark',type:'string'},
				            {name:'userId',type:'int'},
				            {name:'userName',type:'string'}];
				
				/*网格的数据源*/
				var vGridStore1= new Ext.data.JsonStore({
					url:'spk_getInfo.do',
					root:'root',
					totalProperty: 'total',
				    autoLoad: {params:{start:0, limit:20,id:vid}},
				    fields: vfield1
				});
				
				/*网格面板*/
				var vGridPanel1= new Ext.ux.GridPanel({
					region:'center',
					store:vGridStore1,
			        iconCls:'',
			        cm: new Ext.grid.ColumnModel({
			        	defaults: {sortable: true,align:'conter'},
			        	columns:[ new Ext.grid.RowNumberer(),
			        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
			        	          {header:'客户',width:100,dataIndex:'khName'},
			        	          {header:'拼包人',width:120,dataIndex:'vcSpllName'},
			        	          {header:'金额',width:100,dataIndex:'dlMoney'},
			        	          {header:'结算状态',width:100,dataIndex:'ipayState',renderer:function(a){return a=='1'?'已结算':'未结算'}},
			        	          {header:'结算日期',width:120,dataIndex:'dtPay',renderer:function(a){if(a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
			        	          {header:'备注',width:200,dataIndex:'vcRemark'},
			        	          {header:'制单人',width:100,dataIndex:'userName'}]
			        }),
			        bbar: new Ext.PagingToolbar({
			            pageSize: 20,
			            store: vGridStore1,
			            displayInfo: true
			        })
				});
				
				
				var addWindow111 = new Ext.ux.Window({
		    		title : vno+'单号的拼包清单',
		    		closeAction:'close',
		    		height:320,
		    		collapsible : true,
		    		items : [vGridPanel1]
		    	});
				addWindow111.show();
        	}
        }
        },'-',{
        	text:'添加送货',
        	iconCls:'btn-add2',
        	handler: function(){
        	var record= vGridPanel.getSelectionModel().getSelected(); 
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要添加送货的单据');
			}else{
				if(record.get('ipay')=='1' || record.get('fidel')=='1'){
					Ext.Msg.alert('信息提示','已结算或已作废的记录不允许进行此操作');
					return;
				}
				/*添加送货*/
				//var vSpellName=new Ext.form.TextField({name:'vcSpllName',width:180,fieldLabel:'送货人',allowBlank:false,maxLength:10});
				var vRemark=new Ext.form.TextArea({id:'vcRemark',name:'vcRemark',width:180,height:60,fieldLabel:'备注',maxLength:100});
		 		var vAddress=new Ext.form.TextArea({id:'vcAddress',name:'vcAddress',width:180,height:40,fieldLabel:'送货地址',maxLength:100});
		 		var vMoney=new Ext.form.NumberField({name:'dlMoney',width:180,fieldLabel:'金额',decimalPrecision:2,allowBlank:false,value:record.get('deOutMoney')});
		 		
		 		if(record.get('logisticsName').indexOf('送货')>-1){
					vAddress.setValue(record.get('vcYunNo'));
				}
				/*送货人*/
		 		var storePs = new Ext.data.JsonStore({
		 			fields: ['value', 'text'],
		            url : "ps_findPsComb.do",
		            autoLoad: {},
		            root : "root"
		 		});
		 		var vSpellName= new Ext.ux.LinkComboBox({
		        	name:'vcSpllName',
		        	fieldLabel :'送货人',
		        	store: storePs,
		        	valueField : 'value',
		            displayField:'text',
		            typeAhead: true,
		            mode: 'local',
		            forceSelection: false,
		            triggerAction: 'all',
		            selectOnFocus:false,
		            allowBlank:false
		        });
				
				/*表单界面 布局*/
		        var vPanelForm=new Ext.Panel({
		        	frame: true,
		            border: false,
		            labelAlign: "right",
		            layout: "column",
		            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
		            items: [{
		                columnWidth: 1,
		                items: [vSpellName,vMoney,vAddress,vRemark]
		            }]
		        });
		        var addForm = new Ext.form.FormPanel({
		        	frame: true,
		            border: false,
		            layout: "column",
		            defaults: { frame: false, border: false },
		            items:[vPanelForm]
		        });
		        /*添加*/
		        var addWindow = new Ext.ux.Window({
		    		title : '送货添加',
		    		closeAction:'close',
		    		height:245,
		    		width:320,
		    		collapsible : true,
		    		items : [addForm],
		    		buttons : [{
		    			text : '保存',
		    			handler : function() {
		    				if (addForm.getForm().isValid()) {
		    					addForm.getForm().submit({
		    						url : 'salenout_saveSh.do?id='+record.get('id'),
		    						success : function(form, action) {
		    							Ext.Msg.alert('信息提示',action.result.message);
		    							addWindow.close();
		    						},
		    						failure : function(form, action) {
		    							if(action.result.errors){
		    								Ext.Msg.alert('信息提示',action.result.errors);
		    							}else{
		    								Ext.Msg.alert('信息提示',action.result.message);
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
		    				addWindow.close();
		    			}
		    		}]
		    	});
		        addWindow.show();
				
			}
        	}
        },'-',{
	    	text:'打印',
        	iconCls:'btn-print',
        	handler: function(){
		    	var record= vGridPanel.getSelectionModel().getSelected();
	        	if(!record){
	        		Ext.Msg.alert('信息提示','请选择要打印的记录');
	        		return;
	        	}
				Ext.Ajax.request({
					url : "print_getSingInfo.do?key=tbsalenout1",
					success : function(response) {
						var v=response.responseText;
						if(v.length==0){
							Ext.Msg.alert('信息提示','打印模板读取错误');
							return;
						}
						/**
						 * 调用打印 
						 * */
						
						window.open('../../gridReport/MyPrint.jsp?data=salenout_getPrintXmlData.do?id='+record.get('id')+'&report='+v);
						//window.open('../../gridReport/MyPrint.jsp?data=spk_getXmlData.do?id='+record.get('id')+'&report='+v);
					}
				});
        	}
	    }]
	});
	
	/*查询条件控件*/
	var vStartDate=new Ext.form.DateField({name:'dts',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	/*付款状态*/
    var vPayState1= new Ext.form.ComboBox({
    	width:80,
    	name:'ipay',
    	fieldLabel :'收款状态',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['1','已收'],['0','未收'],['-1','全部']]
    		   }),
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        value:-1,
        maxLength:10,
        editable:false
    });
    /*销售类型*/
    var vSaleType1= new Ext.form.ComboBox({
    	width:80,
    	name:'isaleType',
    	fieldLabel :'销售类型',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['1','零售'],['0','批发'],['-1','全部']]
    		   }),
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        value:-1,
        maxLength:10,
        editable:false
    });
    
    /*客户*/
    var storeKh1=new Ext.data.JsonStore({
    	fields: ['value', 'text'],
        url : "kh_findKhComb.do",
        //autoLoad: {},
        root : "root"
    });
    var vKhCombo1= new Ext.ux.LinkComboBox({
    	width:180,
    	name:'ikh',
    	fieldLabel :'客户',
    	store: storeKh1,
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        emptyText:'编号/名称...回车检索'
    });
    
    /*结算方式*/
    var storeSettle1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ut_getInfo.do?type=jsfs",
        autoLoad: {},
        root : "root"
    });
    var vSettleCombo1= new Ext.form.ComboBox({
    	width:100,
    	name:'isettlement',
    	fieldLabel :'结算方式',
    	store: storeSettle1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true
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
    
    /**条件区域**/
	var vToobar1=new Ext.Toolbar({
	    items: ['日期范围：',vStartDate,'到',vEndDate,'-','客户：',vKhCombo1,'-','配送方式：',vLogisCombo1]
	});
	
	var vToobar2=new Ext.Toolbar({
		items:['物流公司：',vWlCombo1,'-','结算方式：',vSettleCombo1,'-','收款状态：',vPayState1,'-','销售类型',vSaleType1,'-','关键字：',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:160,
        	maxLength:100,
        	emptyText:'单号/金额/备注/运单号/送货地址',
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
//	    			ikh:vKhCombo1.getValue(),
//	    			isettlement:vSettleCombo1.getValue(),
//	    			ipay:vPayState1.getValue(),
//	    			isaleType:vSaleType1.getValue()}});
        		funcinitgrid();
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
	
	
	
	/**
	 * 新增或编辑
	 * */
	function funcEdit(type,row){
		
		/*客户的行对象*/
		var khRow=null;
		
		var vId=new Ext.form.Hidden({name:'id'});
		var vNo=new Ext.form.TextField({hidden:true,name:'vcNo',readOnly:true,fieldLabel:'单据编号',width:150,maxLenght:15,value:'自动生成'});
		var vDtBs=new Ext.form.DateField({name:'dtBs',value:new Date(),fieldLabel:'业务日期',width:150,format:'Y-m-d',allowBlank:false});
		var vTel=new Ext.form.TextField({name:'vcTel',fieldLabel:'电话',width:150,maxLength:50});
		var vAddress=new Ext.form.TextField({name:'vcAddress',fieldLabel:'地址',width:500});
		var vYunNo=new Ext.form.TextField({id:'vcYunNo',name:'vcYunNo',fieldLabel:'运单号',width:150,tabIndex:4});
		var vdeOutMoney=new Ext.form.NumberField({name:'deOutMoney',fieldLabel:'<font color=red>总金额</font>',width:150,allowBlank:false,decimalPrecision:2,readOnly:true,style:'background:#F6F6F6'});
		var vdeDiscount=new Ext.form.NumberField({hidden:true,name:'deDiscount',fieldLabel:'折扣率',width:150,decimalPrecision:2});
		var vdeOkOutMoney=new Ext.form.NumberField({hidden:true,name:'deOkOutMoney',fieldLabel:'实收金额',width:150,allowBlank:false,decimalPrecision:2});
		var vdeOweMoney=new Ext.form.NumberField({tabIndex:7,name:'deOweMoney',fieldLabel:'上欠',width:150,decimalPrecision:2});
		var vdeOtherMoeny=new Ext.form.NumberField({hidden:true,name:'deOtherMoeny',fieldLabel:'其他金额',width:150,decimalPrecision:2});
		var vRemark=new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:500,maxLength:100});
		var vPrint=new Ext.form.CheckboxGroup({name:'isaleprint1',fieldLabel:'保存后',items:[{boxLabel:'打印',name:'s1',checked:true},{boxLabel:'更新客户',name:'s2',checked:true}]});
		var vPhone=new Ext.form.TextField({name:'vcPhone',fieldLabel:'手机号码',width:150,maxLength:50});
		
		/*付款状态*/
        var vPayState= new Ext.form.ComboBox({
        	hidden:true,
        	width:150,
        	name:'ipay',
        	fieldLabel :'收款状态',
        	store: new Ext.data.SimpleStore({
		        		fields: ['value', 'text'],
		        		data:[['1','已收'],['0','未收']]
        		   }),
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:1,
            maxLength:10,
            editable:false,
            hiddenName:'ipay' /*设定了 跟name值一样的话 就会提交value值*/
        });
        /*销售类型*/
        var vSaleType= new Ext.form.ComboBox({
        	width:150,
        	tabIndex:5,
        	name:'isaleType',
        	fieldLabel :'销售类型',
        	store: new Ext.data.SimpleStore({
		        		fields: ['value', 'text'],
		        		data:[['1','零售'],['0','批发']]
        		   }),
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:0,
            maxLength:10,
            editable:false,	
            allowBlank:false,
            hiddenName:'isaleType' /*设定了 跟name值一样的话 就会提交value值*/
        });
        
        /*客户*/
        var storeKh=new Ext.data.JsonStore({
        	fields: ['value', 'text'],
            url : "kh_findKhComb.do",
            autoLoad: {params:{keyid:type=='add'?0:row.get('ikh')}},
            root : "root"
        });
        /*客户的送货地址*/
        var vshippingAddress='';
        
        var vKhCombo= new Ext.ux.LinkComboBox({
        	width:120,
        	tabIndex:1,
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
            allowBlank:false,
            hiddenName:'ikh',
            emptyText:'编号/名称...回车检索',
            allowBlank:false,
            listeners:{
        		change:function(){
        			var v=vKhCombo.getValue();
        			if(v==null || v=='0' || !v){
        				return;
        			}
        			Ext.Ajax.request({
        				url:'kh_getKhInfo.do?khid='+v,
        				success:function(res){
        					var json = Ext.decode(res.responseText);
        					vTel.setValue(json.root[0].lxtel); /*address*/
        					vAddress.setValue(json.root[0].province+''+json.root[0].city+''+json.root[0].distincts+''+json.root[0].address);
        					vRemark.setValue(json.root[0].bz);
        					vdeOweMoney.setValue(json.root[0].deOwe);
        					vPhone.setValue(json.root[0].mobile);
        					vshippingAddress = json.root[0].shipping_address;
        					/*配送方式 结算方式*/
        					for(var i=0;i<storeSettle.getCount();i++){
        						var row = storeSettle.getAt(i);
        						if(json.root[0].shippint_clear.length==0){
        							vSettleCombo.setValue('');
        							break;
        						}
        						if(row.get('vcName').indexOf(json.root[0].shippint_clear)>-1){
        							vSettleCombo.setValue(row.get('id'));
        							break;
        						}
        					}
        					for(var i=0;i<storeLogis.getCount();i++){
        						var row = storeLogis.getAt(i);
        						if(json.root[0].shipping_type.length==0){
        							vLogisCombo.setValue('');
        							break;
        						}
        						if(row.get('vcName').indexOf(json.root[0].shipping_type)>-1){
        							vLogisCombo.setValue(row.get('id'));
        							break;
        						}
        					}
        					for(var i=0;i<storeWl.getCount();i++){
        						var row = storeWl.getAt(i);
        						if(json.root[0].shipping_log.length==0){
        							vWlCombo.setValue('');
        							break;
        						}
        						if(row.get('vcName').indexOf(json.root[0].shipping_log)>-1){
        							vWlCombo.setValue(row.get('id'));
        							break;
        						}
        					}
        					
        					var vValue = vLogisCombo.getRawValue();
        					if(vValue.indexOf('送货')>-1){
        						vYunNo.setValue(vshippingAddress);
        					}
        					
        					/*赋值行对象 用户修改客户用*/
        					khRow=new Ext.data.Record({
        						khid:json.root[0].khid,
        						khnum:json.root[0].khnum,
        						khname:json.root[0].khname,
        						lxren:json.root[0].lxren,
        						lxtel:json.root[0].lxtel,
        						country:json.root[0].country,
        						address:json.root[0].address,
        						bz:json.root[0].bz,
        						country:json.root[0].country,
        						province:json.root[0].province,
        						city:json.root[0].city,
        						distinct:json.root[0].distincts,
        						mobile:json.root[0].mobile,
        						fax:json.root[0].fax,
        						qq:json.root[0].qq,
        						email:json.root[0].email,
        						shipping_address:json.root[0].shipping_address,
        						shipping_type:json.root[0].shipping_type,
        						credit:json.root[0].credit,
        						deOwe:json.root[0].deOwe,
        						shippintClear:json.root[0].shippint_clear,
        						shippingLog:json.root[0].shipping_log
        					});
        				}
        			});
        		}
        	}
        });
        
        var vchenge=new Ext.form.Hidden({id:'Changes',listeners:{
        	'disable':function(){
        	var v=vKhCombo.getValue();
			if(v==null || v=='0' || !v){
				return;
			}
			Ext.Ajax.request({
				url:'kh_getKhInfo.do?khid='+v,
				success:function(res){
					var json = Ext.decode(res.responseText);
					vTel.setValue(json.root[0].lxtel); /*address*/
					var vAddress1 = json.root[0].shipping_address;
					if(json.root[0].shipping_address==null || json.root[0].shipping_address.length<=0){
						vAddress1=json.root[0].address;
					}
					vAddress.setValue(json.root[0].province+''+json.root[0].city+''+json.root[0].distincts+''+vAddress1);
					vRemark.setValue(json.root[0].bz);
					vdeOweMoney.setValue(json.root[0].deOwe);
					vPhone.setValue(json.root[0].mobile);
					/*配送方式 结算方式*/
					for(var i=0;i<storeSettle.getCount();i++){
						var row = storeSettle.getAt(i);
						if(json.root[0].shippint_clear.length==0){
							vSettleCombo.setValue('');
							break;
						}
						if(row.get('vcName').indexOf(json.root[0].shippint_clear)>-1){
							vSettleCombo.setValue(row.get('id'));
							break;
						}
					}
					for(var i=0;i<storeLogis.getCount();i++){
						var row = storeLogis.getAt(i);
						if(json.root[0].shipping_type.length==0){
							vLogisCombo.setValue('');
							break;
						}
						if(row.get('vcName').indexOf(json.root[0].shipping_type)>-1){
							vLogisCombo.setValue(row.get('id'));
							break;
						}
					}
					for(var i=0;i<storeWl.getCount();i++){
						var row = storeWl.getAt(i);
						if(json.root[0].shipping_log.length==0){
							vWlCombo.setValue('');
							break;
						}
						if(row.get('vcName').indexOf(json.root[0].shipping_log)>-1){
							vWlCombo.setValue(row.get('id'));
							break;
						}
					}
					
					/*赋值行对象 用户修改客户用*/
					khRow=new Ext.data.Record({
						khid:json.root[0].khid,
						khnum:json.root[0].khnum,
						khname:json.root[0].khname,
						lxren:json.root[0].lxren,
						lxtel:json.root[0].lxtel,
						country:json.root[0].country,
						address:json.root[0].address,
						bz:json.root[0].bz,
						country:json.root[0].country,
						province:json.root[0].province,
						city:json.root[0].city,
						distinct:json.root[0].distincts,
						mobile:json.root[0].mobile,
						fax:json.root[0].fax,
						qq:json.root[0].qq,
						email:json.root[0].email,
						shipping_address:json.root[0].shipping_address,
						shipping_type:json.root[0].shipping_type,
						credit:json.root[0].credit,
						deOwe:json.root[0].deOwe,
						shippintClear:json.root[0].shippint_clear,
						shippingLog:json.root[0].shipping_log
					});
				}
			});
        	}
        }});
        
        /*结算方式*/
        var storeSettle=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ut_getInfo.do?type=jsfs",
            autoLoad: {},
            root : "root"
        });
        var vSettleCombo= new Ext.form.ComboBox({
        	width:150,
        	tabIndex:6,
        	name:'isettlement',
        	fieldLabel :'结算方式',
        	store: storeSettle,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            editable:false,
            allowBlank:false,
            hiddenName:'isettlement'
        });
        
        /*配送方式*/
        var storeLogis=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ut_getInfo.do?type=psfs",
            autoLoad: {},
            root : "root"
        });
        
        var vLogisCombo= new Ext.form.ComboBox({
        	width:150,
        	tabIndex:2,
        	name:'ilogistics',
        	fieldLabel :'配送方式',
        	store: storeLogis,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            editable:false,
            allowBlank:false,
            hiddenName:'ilogistics',
            listeners:{
        		'select':function(box){
        			var vValue = vLogisCombo.getRawValue();
        			var vType = -1;
        			if(vValue.indexOf('物流')>-1){
        				vType=1;
        				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="运单号：";
        			}else if(vValue.indexOf('快递')>-1){
        				vType=0;
        				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="运单号：";
        			}else if(vValue.indexOf('送货')>-1){
        				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="送货地址：";
        				vYunNo.setValue(vshippingAddress);
        			}else if(vValue.indexOf('自提')>-1){
        				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="时间：";
        			}else if(vValue.indexOf('专车')>-1){
        				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="联系人：";
        			}
        			storeWl.load({params:{vctype:vType}});
        		}
        	}
        });
        
        /*物流公司*/
        var storeWl=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ut_getInfo.do?type=wlgs",
            autoLoad: {},
            root : "root"
        });
        var vWlCombo= new Ext.form.ComboBox({
        	width:150,
        	tabIndex:3,
        	name:'iwl',
        	fieldLabel :'物流公司',
        	store: storeWl,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            hiddenName:'iwl'
        });
        
        
        /*********销售明细网格***********/
        var vfiled=[{name:'id',type:'int'},
                    {name:'salenoutId',type:'int'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityId',type:'int'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'icount',type:'string'},
                    {name:'vcDw',type:'string'},
                    {name:'dePriceMoney',type:'string'},
                    {name:'deSumMoney',type:'string'},
                    {name:'warehouseId',type:'int'},
                    {name:'warehouseName',type:'string'},
                    {name:'deDiscount',type:'string'}];
        /*网格的数据源*/
    	var vGridStoreDel= new Ext.data.JsonStore({
    		url:'salenout_getInfoDel.do?id='+(row==null?0:row.get("id")),
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfiled
    	});
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		id:'gridPanel1',
    		title:'商品明细',
    		height:265,
    		width:680,
    		region:'center',
    		store:vGridStoreDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'商品名称',width:120,dataIndex:'commodityName'},
            	          {header:'单价',width:100,dataIndex:'dePriceMoney'},
            	          {header:'数量',width:100,dataIndex:'icount'},
            	          {header:'单位',width:100,dataIndex:'vcDw'},
            	          {header:'折扣率',width:100,dataIndex:'deDiscount'},
            	          {header:'金额',widht:100,dataIndex:'deSumMoney'},
            	          {header:'批次',width:100,dataIndex:'vcBatch'},
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'辅助标识',width:100,dataIndex:'vcSn'},
            	          {header:'仓库',width:80,dataIndex:'warehouseName'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'salenoutId',hidden:true,dataIndex:'salenoutId'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'}]
            }),
            tbar:[{
            	text:'添加',
            	iconCls:'btn-add',
            	handler: function(){
            		funcEditDel();
            	}
            },'-',{
            	id:'btnDelete11',
            	text:'删除',
            	iconCls:'btn-remove',
            	handler: function(){
	            	var record= vGridPaneldel.getSelectionModel().getSelected();
					if(!record){
	                	Ext.Msg.alert('信息提示','请选择要删除的商品信息');  
					}else{
						//alert(record.get("id"));
						Ext.MessageBox.confirm('删除提示', '是否要删除该商品吗？', function(c) {
						   if(c=='yes'){
							   if(record.get("id").length==0 || record.get("id")=='0'){
								   vGridStoreDel.remove(record);
								   funcSumMoney1();
								   return;
							   }
							   /**做物理删除 同时修改库存记录**/
							   Ext.Ajax.request({
						   			url : "salenout_deleteDelInfo.do",
						   			params:{ iddel : record.get("id") },
						   			success : function() {
						   				vGridStoreDel.remove(record);
						   				funcSumMoney1();
						   			}
						   		});
						    }
						});
					}
            	}
            }]
    	});
    	
    	
    	/*表单界面 布局*/
        var vPanelForm=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 65, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.33,
                items: [vId,vNo,{
                	items:[{layout:'column',
         					items:[{
          						layout:'form',
          						width : 200,
          						items:[vKhCombo]
          					},{
          						width : 20,
          						height : 20,
          						xtype:'button',
          						iconCls : 'btn-edit',
          						handler:function(){
          							if(khRow==null){
          								Ext.Msg.alert('信息提示','您没有选择任何客户');
          								return;
          							}
          							funcEdit1('edit',khRow);
          						}
          					}]}]
                },vSaleType,vSettleCombo,vPayState,vTel]
            },{
                columnWidth: 0.33,
                items: [vLogisCombo,vWlCombo,vYunNo,vdeDiscount,vdeOtherMoeny,vdeOkOutMoney,vPhone]
            },{
            	columnWidth: 0.33,
                items: [vDtBs,vdeOweMoney,vdeOutMoney,vPrint]
            },{
            	columnWidth: 1,items:[vAddress,vRemark]
            }]
        });
    	
        var addForm = new Ext.form.FormPanel({
        	id:'gridSpxx',
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            items:[vPanelForm,vGridPaneldel]
        });
    	
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '销售编辑',
    		closeAction:'close',
    		width:720,
    		height:520,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			id:'btnSave1',
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					var vJsons=funcConvertGrid(); /*得到网格的json字符串*/
    					
    					if(vJsons.length<5){
    						Ext.Msg.alert('信息提示','请添加商品信息');
    						return;
    					}
    					var bprint=0;
    					var bupdate=0;
    					/*查看复选框勾选状态*/
    					for (var i = 0; i < vPrint.items.length; i++){
    						var v=vPrint.items.itemAt(i);
    						if (v.checked && v.name=='s1'){ /*打印*/
    							bprint=1
    		                }
    						if (v.checked && v.name=='s2'){ /*更新*/
    							bupdate=1
    		                }
    					}
    					/*记忆配送方式和结算方式*/
    					//parent.cookies_xsps=vLogisCombo.getValue();
    					//parent.cookies_xsjs=vSettleCombo.getValue();
    					//parent.cookies_xsydh=document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML;
    					addForm.getForm().submit({
    						url : 'salenout_saveSInfo.do?bupdatekh='+bupdate, 
    						params:{jsonInfo:vJsons,isettlementname:vSettleCombo.getRawValue(),ilogisticsname:vLogisCombo.getRawValue(),iwlname:vWlCombo.getRawValue()},
    						success : function(form, action) {
    							if(bprint=='0'){
    								Ext.Msg.alert('信息提示',action.result.message);
    								funcinitgrid();
    							}else{
    								funcinitgrid();
    								Ext.Ajax.request({
    									url : "print_getSingInfo.do?key=tbsalenout1",
    									success : function(response) {
    										var v=response.responseText;
    										if(v.length==0){
    											Ext.Msg.alert('信息提示','打印模板读取错误');
    											return;
    										}
    										/**
    										 * 调用打印 
    										 * */
    										window.open('../../gridReport/MyPrint.jsp?data=salenout_getPrintXmlData.do?id='+action.result.newid+'&report='+v+'&save=1');
    										//window.open('../../gridReport/MyPrint.jsp?data=spk_getXmlData.do?id='+record.get('id')+'&report='+v);
    									}
    								});
    							}
    							addWindow.close();
    							//vGridStore.reload();
    						},
    						failure : function(form, action) {
    							if(action.result.errors){
    								Ext.Msg.alert('信息提示',action.result.errors);
    							}else{
    								Ext.Msg.alert('信息提示',action.result.message);
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
    				addWindow.close();
    			}
    		}]
    	});
        addWindow.show();
        
        /*判断是新增还是修改*/
        if(type=='add'){
        	addForm.getForm().reset();
        	/*设置配送方式和结算方式*/
//        	if(parent.cookies_xsjs!=undefined && parent.cookies_xsjs!=0){
//	        	storeSettle.on('load',function(){
//	    			vSettleCombo.setValue(parent.cookies_xsjs);
//	    		});
//        	}
//        	if(parent.cookies_xsps!=undefined && parent.cookies_xsps!=0){
//	    		storeLogis.on('load',function(){
//	    			vLogisCombo.setValue(parent.cookies_xsps);
//	    		});
//	    		document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML=parent.cookies_xsydh;
//        	}
        }else{
        	if(row!=null){
        		addForm.getForm().loadRecord(row);
        		/*赋值*/
        		vDtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeKh.on('load',function(){
        			vKhCombo.setValue(row.get("ikh"));
        		});
        		storeSettle.on('load',function(){
        			vSettleCombo.setValue(row.get("isettlement"));
        		});
        		storeLogis.on('load',function(){
        			vLogisCombo.setValue(row.get("ilogistics"));
        		});

        		storeWl.on('load',function(){
        			vWlCombo.setValue(row.get("iwl"));
        		});
        		
        		/*赋值修改客户的行对象*/
        		Ext.Ajax.request({
    				url:'kh_getKhInfo.do?khid='+row.get("ikh"),
    				success:function(res){
    					var json = Ext.decode(res.responseText);
    					
    					/*赋值行对象 用户修改客户用*/
    					khRow=new Ext.data.Record({
    						khid:json.root[0].khid,
    						khnum:json.root[0].khnum,
    						khname:json.root[0].khname,
    						lxren:json.root[0].lxren,
    						lxtel:json.root[0].lxtel,
    						country:json.root[0].country,
    						address:json.root[0].address,
    						bz:json.root[0].bz,
    						country:json.root[0].country,
    						province:json.root[0].province,
    						city:json.root[0].city,
    						distinct:json.root[0].distincts,
    						mobile:json.root[0].mobile,
    						fax:json.root[0].fax,
    						qq:json.root[0].qq,
    						email:json.root[0].email,
    						shipping_address:json.root[0].shipping_address,
    						shipping_type:json.root[0].shipping_type,
    						credit:json.root[0].credit,
    						deOwe:json.root[0].deOwe,
    						shippintClear:json.root[0].shippint_clear,
    						shippingLog:json.root[0].shipping_log
    					});
    				}
    			});
        		
        		/**设定 界面的运单号显示情况**/
        		var vValue=row.get('logisticsName');
        		if(vValue.indexOf('物流')>-1){
    				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="运单号：";
    			}else if(vValue.indexOf('快递')>-1){
    				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="运单号：";
    			}else if(vValue.indexOf('送货')>-1){
    				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="送货地址：";
    			}else if(vValue.indexOf('自提')>-1){
    				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="时间：";
    			}else if(vValue.indexOf('专车')>-1){
    				document.getElementById("vcYunNo").parentNode.previousSibling.innerHTML ="联系人：";
    			}
        	}
        }
        if(type=='read') /*查看 禁用保存按钮*/{
        	Ext.getCmp('btnSave1').disable();
        	Ext.getCmp('btnDelete11').disable();
        }
        
        
	}
	
	
	/**
	 * 明细项的添加
	 * */
	function funcEditDel(){
		var vNo=new Ext.form.TextField({name:'vcNo',fieldLabel:'商品编号',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vName=new Ext.form.TextField({name:'vcName',fieldLabel:'商品名称',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vBatch=new Ext.form.TextField({name:'vcBatch',fieldLabel:'批次',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vBs=new Ext.form.TextField({name:'vcBs',fieldLabel:'辅助标识',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vXlName=new Ext.form.TextField({name:'vcXlName',fieldLabel:'系列',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vGg=new Ext.form.TextField({name:'vcGg',fieldLabel:'规格',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vJyMoney=new Ext.form.TextField({name:'dbSuggMoney',fieldLabel:'建议售价',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vWareHouseName=new Ext.form.TextField({name:'vcWareHouseName',fieldLabel:'仓库',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vWareHouseId=new Ext.form.Hidden({name:'wareHouseId'});
		var vCommodityId=new Ext.form.Hidden({name:'commodityId'});
		var vDw=new Ext.form.TextField({name:'vcDw',fieldLabel:'单位',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vCount= new Ext.form.NumberField({id:'vCount1',name:'vCount',fieldLabel:'数量',width:180,decimalPrecision:2,allowBlank:false,
			listeners:{
			'change':function(a){
				funcSumMoney();
			}}
		});
		var vJhMoney=new Ext.form.NumberField({name:'vJhMoney',fieldLabel:'最后进价',width:180,decimalPrecision:2,readOnly:true,style:'background:#F6F6F6'});
		var vMoney=new Ext.form.NumberField({id:'vMoney1',name:'vMoney',fieldLabel:'售价',decimalPrecision:2,width:180,allowBlank:false,
			listeners:{
			'change':function(a){
				funcSumMoney();
			}}
		});
		var vSumMoney=new Ext.form.NumberField({id:'vSumMoney1',name:'vSumMoney',fieldLabel:'总金额',decimalPrecision:2,width:180,allowBlank:false,readOnly:true,style:'background:#F6F6F6'});
		var vYhMoney=new Ext.form.NumberField({id:'vYhMoney1',emptyText:'输入值范围：0到1',allowBlank:false,value:1,maxValue:1,minValue:0,name:'vYhMoney',fieldLabel:'折扣率',decimalPrecision:2,width:180,
			listeners:{
			'change':function(a){
				funcSumMoney();
			}}
		});
		/**
		 * 进货单选择网格
		 * */
        var vfiled=[{name:'id',type:'int'},
                    {name:'storageId',type:'int'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityId',type:'int'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'commodityRemark',type:'string'},
                    {name:'xlName',type:'string'},
                    {name:'dePurchaseMoney',type:'string'},
                    {name:'icount',type:'string'},
                    {name:'deSumMoney',type:'string'},
                    {name:'vcDw',type:'string'},
                    {name:'vcColor',type:'string'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'warehouseId',type:'int'},
                    {name:'warehouseName',type:'string'},
                    {name:'dlQty',type:'string'},
                    {name:'vcFactoryNo',type:'string'},
                    {name:'dbSuggMoney',type:'dbSuggMoney'}];
        
    	/*网格的数据源*/
    	var vGridStoreDel= new Ext.data.JsonStore({
    		url:'ph_getInfoDel1.do',
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {params:{start:0, limit:10}},
    	    fields: vfiled
    	});
    	
        
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		width:568,
    		height:220,
    		region:'center',
    		store:vGridStoreDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'商品名称',width:140,dataIndex:'commodityName'},
            	          {header:'厂家编码',width:100,dataIndex:'vcFactoryNo'},
            	          {header:'库存数量',width:80,dataIndex:'dlQty'},
            	          {header:'单位',width:40,dataIndex:'vcDw'},
            	          {header:'最后进价',width:80,dataIndex:'dePurchaseMoney'},
            	          {header:'批次',width:40,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:80,dataIndex:'vcSn'},
            	          {header:'建议售价',width:80,dataIndex:'dbSuggMoney'},
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'颜色',width:100,dataIndex:'vcColor'},
            	          {header:'系列',width:100,dataIndex:'xlName'},
            	          {header:'仓库',width:80,dataIndex:'warehouseName'},
            	          //{header:'进货数量',width:100,dataIndex:'icount'},
            	          //{header:'金额',widht:100,dataIndex:'deSumMoney'},
            	          {header:'备注',width:160,dataIndex:'commodityRemark'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'storageId',hidden:true,dataIndex:'storageId'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'}]
            }),
            tbar:[{
            	xtype:'textfield',
            	id:'keyWord1',
            	width:160,
            	maxLength:100,
            	emptyText:'商品/编码/厂家编码/备注',
            	listeners:{
            		specialKey : function(field, e){

            			if (e.getKey() == e.ENTER){
            				vGridStoreDel.setBaseParam('key',Ext.getCmp('keyWord1').getValue());
            				vGridStoreDel.load({params:{start:0, limit:10}});
            			}
            		}
            	}
            },'-',{
                	text:'查询',
                	iconCls:'btn-list',
                	handler: function(){
			            vGridStoreDel.setBaseParam('key',Ext.getCmp('keyWord1').getValue());
						vGridStoreDel.load({params:{start:0, limit:10}});
                	}
            }],
            listeners: {
                "rowclick": function (grid, rowIndex, event) {
            			var row = grid.getSelectionModel().getSelections();
                        /*赋值控件*/
            			vNo.setValue(row[0].data.commodityNo);
            			vName.setValue(row[0].data.commodityName);
            			vBatch.setValue(row[0].data.vcBatch);
            			vBs.setValue(row[0].data.vcSn);
            			vXlName.setValue(row[0].data.xlName);
            			vGg.setValue(row[0].data.commodityGg);
            			vWareHouseName.setValue(row[0].data.warehouseName);
            			vWareHouseId.setValue(row[0].data.warehouseId);
            			vDw.setValue(row[0].data.vcDw);
            			vCommodityId.setValue(row[0].data.commodityId);
            			vJhMoney.setValue(row[0].data.dePurchaseMoney);
            			vJyMoney.setValue(row[0].data.dbSuggMoney);
            			
            			/*让文本框得到焦点*/
            			vCount.focus(false,100);
            	}
            },
            bbar: new Ext.PagingToolbar({
                pageSize: 10,
                store: vGridStoreDel,
                displayInfo: true
            })
    	});
		
    	/*表单界面 布局*/
        var vPanelForm1=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.5,
                items: [vNo,vName,vCount,vMoney,vYhMoney,vDw,vWareHouseId,vSumMoney]
            },{
                columnWidth: 0.5,
                items: [vBs,vXlName,vBatch,vJyMoney,vGg,vWareHouseName,vCommodityId,vJhMoney]
            }]
        });
        
        var addForm1 = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            height:490,
            defaults: { frame: false, border: false },
            items:[vGridPaneldel,vPanelForm1]
        });
        
        /*添加*/
        var addWindow1 = new Ext.ux.Window({
    		title : '销售选择购进商品',
    		closeAction:'close',
    		width:600,
    		height:495,
    		collapsible : true,
    		items : [addForm1],
    		buttons : [{
    			id:'btnSave1',
    			text : '确定',
    			handler : function() {
    				if (addForm1.getForm().isValid()) {
    					var row = vGridPaneldel.getSelectionModel().getSelections();
    					if(row.length==0){
    						Ext.Msg.alert('信息提示','请选择商品信息');
    						return;
    					}
    					if(vCount.getValue()==0){
    						Ext.Msg.alert('信息提示','数量不能为0');
    						return;
    					}
    					if(vCount.getValue() > row[0].data.dlQty){
    						Ext.Msg.alert('信息提示','销售数量不能大于库存数量');
    						return;
    					}
    					
    					var gridRowRecord=new Ext.data.Record({
    						commodityNo:vNo.getValue(),
    						commodityName:vName.getValue(),
    						commodityGg:vGg.getValue(),
    						xlName:vXlName.getValue(),
    						vcBatch:vBatch.getValue(),
    						vcSn:vBs.getValue(),
    						warehouseName:vWareHouseName.getRawValue(),
    						icount:vCount.getValue(),
    						vcDw:vDw.getValue(),
    						dePriceMoney:vMoney.getValue(),
    						deSumMoney:vSumMoney.getValue(),
    						deDiscount:vYhMoney.getValue(),
    						id:'0',
    						salenoutId:'0',
    						commodityId:vCommodityId.getValue(),
    						warehouseId:vWareHouseId.getValue()
    					});
    					
    					/**添加行记录**/
    					Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
    					funcSumMoney1();/*合计金额*/
    					addWindow1.close();
    						
    				}
    			}
    		}, {
    			text : '取消',
    			handler : function() {
    				addWindow1.close();
    			}
    		},{
    			text : '保存并新增',
    			handler : function() {
    			if (addForm1.getForm().isValid()) {
					var row = vGridPaneldel.getSelectionModel().getSelections();
					if(row.length==0){
						Ext.Msg.alert('信息提示','请选择商品信息');
						return;
					}
					if(vCount.getValue()==0){
						Ext.Msg.alert('信息提示','数量不能为0');
						return;
					}
					if(vCount.getValue() > row[0].data.dlQty){
						Ext.Msg.alert('信息提示','销售数量不能大于库存数量');
						return;
					}
					var gridRowRecord=new Ext.data.Record({
						commodityNo:vNo.getValue(),
						commodityName:vName.getValue(),
						commodityGg:vGg.getValue(),
						xlName:vXlName.getValue(),
						vcBatch:vBatch.getValue(),
						vcSn:vBs.getValue(),
						warehouseName:vWareHouseName.getRawValue(),
						icount:vCount.getValue(),
						vcDw:vDw.getValue(),
						dePriceMoney:vMoney.getValue(),
						deSumMoney:vSumMoney.getValue(),
						deDiscount:vYhMoney.getValue(),
						id:'0',
						salenoutId:'0',
						commodityId:vCommodityId.getValue(),
						warehouseId:vWareHouseId.getValue()
					});
					
					/**添加行记录**/
					Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
					funcSumMoney1();/*合计金额*/
					vMoney.setValue(0);
					vCount.setValue(0);
					//vYhMoney.setValue(0);
					vSumMoney.setValue(0);
				}
    			}
    		}]
    	});
        addWindow1.show();
	}
	
	/**
	 * 合计金额
	 * */
	function funcSumMoney(){
		var vCount = Ext.getCmp('vCount1').getValue();
		var vMoney=Ext.getCmp('vMoney1').getValue();
		var vYhMoney=Ext.getCmp('vYhMoney1').getValue();
		if(vCount.length==0)vCount=0;
		if(vMoney.length==0)vMoney=0;
		if(vYhMoney.length==0)vYhMoney=0;
		Ext.getCmp('vSumMoney1').setValue(parseFloat(vCount)*parseFloat(vMoney)*parseFloat(vYhMoney));
	}
	
	/**
	 * 合计网格金额
	 * */
	function funcSumMoney1(){
		var gs = Ext.getCmp('gridPanel1').getStore();
		var iSum=0;
		for(var i=0;i<gs.getCount();i++){
			iSum +=parseFloat(gs.getAt(i).data['deSumMoney']);
			/*因为无需小数*/
			//iSum +=parseInt(gs.getAt(i).data['deSumMoney']); 
		}
		var panel = Ext.getCmp('gridSpxx').getForm();
		panel.findField('deOutMoney').setValue(Math.round(iSum));
		panel.findField('deOkOutMoney').setValue(Math.round(iSum));
	}
	
	/**
	 * 把网格的行记录变成json数据 
	 * 传入后台 写入到数据库
	 * */
    function funcConvertGrid(){
    	var gs = Ext.getCmp('gridPanel1').getStore(); /*得到操作网格数据源*/

    	var vJson='[';
		for(var i=0;i<gs.getCount();i++){
			if(vJson.length>1){
				vJson+=",{";
				vJson+="'commodityNo':'"+gs.getAt(i).data["commodityNo"]+"',";
				vJson+="'commodityName':'"+gs.getAt(i).data["commodityName"]+"',";
				vJson+="'commodityGg':'"+gs.getAt(i).data["commodityGg"]+"',";
				vJson+="'vcBatch':'"+gs.getAt(i).data["vcBatch"]+"',";
				vJson+="'vcSn':'"+gs.getAt(i).data["vcSn"]+"',";
				vJson+="'warehoseName':'"+gs.getAt(i).data["warehoseName"]+"',";
				vJson+="'icount':'"+gs.getAt(i).data["icount"]+"',";
				vJson+="'vcDw':'"+gs.getAt(i).data["vcDw"]+"',";
				vJson+="'dePriceMoney':'"+gs.getAt(i).data["dePriceMoney"]+"',";
				vJson+="'deSumMoney':'"+gs.getAt(i).data["deSumMoney"]+"',";
				vJson+="'vcRemark':'"+gs.getAt(i).data["vcRemark"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"',";
				vJson+="'salenoutId':'"+gs.getAt(i).data["salenoutId"]+"',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'warehouseId':'"+gs.getAt(i).data["warehouseId"]+"',";
				vJson+="'deDiscount':'"+gs.getAt(i).data["deDiscount"]+"'";
				vJson+="}";
			}else{
				vJson+="{";
				vJson+="'commodityNo':'"+gs.getAt(i).data["commodityNo"]+"',";
				vJson+="'commodityName':'"+gs.getAt(i).data["commodityName"]+"',";
				vJson+="'commodityGg':'"+gs.getAt(i).data["commodityGg"]+"',";
				vJson+="'vcBatch':'"+gs.getAt(i).data["vcBatch"]+"',";
				vJson+="'vcSn':'"+gs.getAt(i).data["vcSn"]+"',";
				vJson+="'warehoseName':'"+gs.getAt(i).data["warehoseName"]+"',";
				vJson+="'icount':'"+gs.getAt(i).data["icount"]+"',";
				vJson+="'vcDw':'"+gs.getAt(i).data["vcDw"]+"',";
				vJson+="'dePriceMoney':'"+gs.getAt(i).data["dePriceMoney"]+"',";
				vJson+="'deSumMoney':'"+gs.getAt(i).data["deSumMoney"]+"',";
				vJson+="'vcRemark':'"+gs.getAt(i).data["vcRemark"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"',";
				vJson+="'salenoutId':'"+gs.getAt(i).data["salenoutId"]+"',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'warehouseId':'"+gs.getAt(i).data["warehouseId"]+"',";
				vJson+="'deDiscount':'"+gs.getAt(i).data["deDiscount"]+"'";
				vJson+="}";
			}
		}
		vJson+=']';
		return vJson;
    }
	
	
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('ikh',vKhCombo1.getValue());
		vGridStore.setBaseParam('isettlement',vSettleCombo1.getValue());
		vGridStore.setBaseParam('ipay',vPayState1.getValue());
		vGridStore.setBaseParam('isaleType',vSaleType1.getValue());
		vGridStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		vGridStore.setBaseParam('dts',vStartDate.getValue());
		vGridStore.setBaseParam('dte',vEndDate.getValue());
		vGridStore.setBaseParam('ilogistics',vLogisCombo1.getValue());
		vGridStore.setBaseParam('iwl',vWlCombo1.getValue());
		vGridStore.load({params:{
			start:0, 
			limit:20
		}});
	}
	
	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'销售管理',
			frame:true,
			region:'center',
			layout:'border',
			border:false,
			items:[vPanel,vGridPanel]
		}]
	});
    
    
    /**
     * 功能：新增或修改客户
     * 作者：RC
     * 日期：2015-07-09
     * */
    function funcEdit1(type,gridrow){
    	/*客户表单字段*/
    	var vKhId= new Ext.form.Hidden({name:'khid'});
        var vKhName=new Ext.form.TextField({name : 'khname',fieldLabel : '客户名称',width:180,maxLength:50,allowBlank: false});
        var vLxRen=new Ext.form.TextField({name : 'lxren',fieldLabel : '联系人',width:180,maxLength:15});
        var vLxTel=new Ext.form.TextField({name : 'lxtel',fieldLabel:'联系电话',width:180,maxLength:50});
        var vKhNum=new Ext.form.TextField({name:'khnum',emptyText:'不填写将自动生成',fieldLabel:'客户编号',width:180,maxLenth:10});
        var vdeOweMoney=new Ext.form.NumberField({name:'deOwe',value:0,fieldLabel:'上欠',width:180,decimalPrecision:2});
        
        var storePro = new Ext.data.JsonStore({
            fields: ['id', 'vcName'],
            url : "kh_getProData.do",
            autoLoad:true,
            root : "root"
        });
        var storeCity=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "kh_getCityData.do",
            autoLoad: {params:{proid:''}},
            root : "root"
        });
        var storeArea=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "kh_getAreaData.do",
            autoLoad: {params:{cityid:''}},
            root : "root"
        });
        
        /*省份下拉*/
        var vProCombo= new Ext.form.ComboBox({
        	name:'province',
        	fieldLabel :'省份',
        	store: storePro,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            maxLength:10,
            listeners:{
        	'select':function(box){
        		storeCity.load({ /*多级联动 需重新传入参数*/
        			params:{proid:box.getValue()}
        		});
        	}
        }
        });
        /*城市下拉*/
        var vCityCombo= new Ext.form.ComboBox({
        	name:'city',
        	fieldLabel :'城市',
        	store: storeCity,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            maxLength:10,
            listeners:{
            	'select':function(box){
        				storeArea.load({ /*多级联动 需重新传入参数*/
            			params:{cityid:box.getValue()}
            		});
            	}
        }
        });
        /*区县下拉*/
        var vDizCombo= new Ext.form.ComboBox({
        	name:'distinct',
        	fieldLabel :'区县',
        	store: storeArea,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            maxLength:10
        });
        var vAddress=new Ext.form.TextField({name:'address',maxLength:100,fieldLabel:'详细地址',width:470});
        var vMobile=new Ext.form.TextField({name:'mobile',fieldLabel:'手机',width:180,maxLength:25});
        var vFax=new Ext.form.TextField({name:'fax',fieldLabel:'传真',width:180,maxLength:25});
        var vQq=new Ext.form.TextField({name:'qq',fieldLabel:'QQ',width:180,maxLength:25});
        var vEmail=new Ext.form.TextField({name:'email',fieldLabel:'邮箱',width:180,maxLength:25});
        var vCredit=new Ext.form.NumberField({name:'credit',fieldLabel:'信用度',width:180,minValue:0,maxValue:100,value:0});
        var vShipping_address=new Ext.form.TextField({name:'shipping_address',fieldLabel:'送货地址',width:470,maxLength:100});
        var vShipping_type=new Ext.form.TextField({name:'shipping_type',fieldLabel:'配送方式',width:180,maxLength:100});
        var vShipping_clear=new Ext.form.TextField({name:'shippintClear',fieldLabel:'结算方式',width:180,maxLength:100});
        var vShipping_log=new Ext.form.TextField({name:'shippingLog',fieldLabel:'物流公司',width:180,maxLength:100});
        var vBz= new Ext.form.TextField({name:'bz',fieldLabel:'备注',width:470,maxLength:100});
        
        /*客户表单界面*/
        var vPanelForm=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.5,
                items: [vKhId,vKhNum,vLxRen,vProCombo,vMobile,vQq,vCredit,vdeOweMoney,vShipping_type]
            },
            {
                columnWidth: 0.5,
                items: [vKhName,vLxTel,vCityCombo,vDizCombo,vFax,vEmail,vShipping_clear,vShipping_log]
            },
            {
                columnWidth: 1, items: [vAddress,vShipping_address,vBz]
            }]
        });
        
        var addForm = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            height:370,
            defaults: { frame: false, border: false },
            items:[vPanelForm]
        });
        
        
    	//增加客户窗口
        var addWindow = new Ext.ux.Window({
    		title : '客户编辑',
    		width:610,
    		height:380,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'kh_saveOrUpdateKh.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.hide();
    							//store.reload();
    							Ext.getCmp('Changes').setDisabled(true);
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
	
    /***
     * 重新刷新数据
     * */
	function ResInfo(){
		
	}
	
	/**
	 * 网格设置合计
	 * */
	function gridSum(grid){
    	var sum1=0;
    	var sum2=0;
    	grid.store.each(function(row){
    		 sum1+=parseInt(row.data.deOutMoney);
    		 sum2+=parseInt(row.data.deOweMoney);
    	});
    	grid.store.insert(grid.getStore().getCount(), new Ext.data.Record({deOutMoney:sum1,deOweMoney:sum2,khName:'合计'}));
    }
	
	/*查询网格*/
    funcinitgrid();
});