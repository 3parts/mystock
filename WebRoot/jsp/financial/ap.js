/*
 *应付
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	var vFields=[{name:'id',type:'int'},
	             {name:'vcNo',type:'string'},
	             {name:'dtBs',type:'string'},
	             {name:'gysId',type:'int'},
	             {name:'gysName',type:'string'},
	             {name:'sourceId',type:'int'},
	             {name:'sourceType',type:'int'},
	             {name:'sourceNo',type:'string'},
	             {name:'logisticsId',type:'int'},
	             {name:'logisticsName',type:'string'},
	             {name:'settlementId',type:'int'},
	             {name:'settlementName',type:'string'},
	             {name:'dlMoney',type:'string'},
	             {name:'istate',type:'string'},
	             {name:'vcAuditor',type:'string'},
	             {name:'dtJsDate',type:'string'},
	             {name:'vcRemark',type:'string'},
	             {name:'dtWrite',type:'string'},
	             {name:'userId',type:'int'},
	             {name:'userName',type:'string'},
	             {name:'companyId',type:'int'},
	             {name:'fidel',type:'int'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'ap_getInfo.do',
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
        	          {header:'结算状态',width:100,dataIndex:'istate',renderer:function(a){return a=='1'?'已结算':'未结算'}},
        	          {header:'应付单号',width:140,dataIndex:'vcNo',renderer:function(a,b,c){return c.data['fidel']=='1'?'已作废/'+a:a; }},
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0||a=='null')return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'单据来源',width:80,dataIndex:'sourceType',renderer:function(a){return a=='1'?'购退':'购进'}},
        	          {header:'购进/退单号',width:120,dataIndex:'sourceNo'},
        	          {header:'供应商',width:120,dataIndex:'gysName'},
        	          {header:'结算方式',width:100,dataIndex:'settlementName'},
        	          {header:'配送方式',width:100,dataIndex:'logisticsName'},
        	          {header:'应付金额',width:100,dataIndex:'dlMoney'},
        	          {header:'结算日期',width:100,dataIndex:'dtJsDate',renderer:function(a){if(a==null||a.length==0||a=='null')return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'经办人',width:100,dataIndex:'vcAuditor'},
        	          {header:'制单日期',width:120,dataIndex:'dtWrite',renderer:function(a){if(a==null||a.length==0||a=='null')return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }}]
        }),
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vGridStore,
            displayInfo: true
        })
	});
	
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
					if(record.get('istate')=='1'){
						Ext.Msg.alert('信息提示','已结算的记录不允许进行此操作');
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的记录不允许进行此操作');
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
					if(record.get('istate')=='1'){
						Ext.Msg.alert('信息提示','已结算的记录不允许进行此操作');
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的记录不能进行操作');
						return;
					}
					Ext.MessageBox.confirm('作废提示', '是否要作废该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "ap_deleteInfo.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				//vGridStore.reload();
					   				funcinitgrid();
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	text:'生成支出',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要生成的单据');  
				}else{
					if(record.get('istate')=='1'){
						Ext.Msg.alert('信息提示','已生成的单据不允许再次生成');  
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的记录不能进行操作');
						return;
					}
					funcOpenIn(record);
				}
        	}
        }]
	});
	
	/**查询控件**/
	var vStartDate=new Ext.form.DateField({name:'dts',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:120,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	/*客户*/
    var storeKh1=new Ext.data.JsonStore({
    	fields: ['value', 'text'],
        url : "gys_findGysComb.do",
        //autoLoad: {},
        root : "root"
    });
    var vKhCombo1= new Ext.ux.LinkComboBox({
    	width:160,
    	fieldLabel :'供应商',
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
    
    /*配送方式*/
    var storeLogistics1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ut_getInfo.do?type=psfs",
        //autoLoad: {},
        root : "root"
    });
    var vLogisticsCombo1= new Ext.form.ComboBox({
    	width:100,
    	fieldLabel :'配送方式',
    	store: storeLogistics1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true
    });
    
    /*结算方式*/
    var storeSettlement1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ut_getInfo.do?type=jsfs",
        autoLoad: {},
        root : "root"
    });
    var vSettlementCombo1= new Ext.form.ComboBox({
    	width:100,
    	fieldLabel :'结算方式',
    	store: storeSettlement1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true
    });
    /*结算状态*/
    var vJsState1= new Ext.form.ComboBox({
    	width:100,
    	fieldLabel :'结算状态',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['-1','全部'],['1','已结算'],['0','未结算']]
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
        value:-1
    });
	
	
	/**条件区域**/
	var vToobar1=new Ext.Toolbar({
	    items: ['业务日期：',vStartDate,'到',vEndDate,'-','供应商：',vKhCombo1]
	});
	
	/**工具栏2**/
	var vToobar2=new Ext.Toolbar({
		items:['结算方式：',vSettlementCombo1,'-','结算状态：',vJsState1,
		       '-',{
        	xtype:'textfield',
        	id:'keyWord',
        	maxLength:100,
        	emptyText:'金额',
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
//	    			limit:20,
//	    			istate:vJsState1.getValue(),
//	    			logisticsId:vLogisticsCombo1.getValue(),
//	    			settlementId:vSettlementCombo1.getValue(),
//	    			key:Ext.getCmp('keyWord').getValue(),
//	    			dtSDate:vStartDate.getValue(),
//	    			dtEDate:vEndDate.getValue(),
//	    			khId:vKhCombo1.getValue()
//	    		}});
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
	
	/**新增或编辑**/
	function funcEdit(type,row){
		var vid=new Ext.form.Hidden({name:'id'});
		var vNo= new Ext.form.TextField({hidden:true,name:'vcNo',fieldLabel:'单据编号',width:180,maxLength:20,readOnly:true,value:'自动生成'});
		var vdtBs= new Ext.form.DateField({name:'dtBs',value:new Date(),fieldLabel:'业务日期',format:'Y-m-d',width:180,allowBlank:false});
		var vdlMoney=new Ext.form.NumberField({name:'dlMoney',fieldLabel:'金额',maxLength :10,width:180,allowBlank:false,decimalPrecision:2});
//		var vdtJsDate= new Ext.form.DateField({name:'dtJsDate',fieldLabel:'结算日期',format:'Y-m-d',width:180});
		var vRemark=new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:470,maxLength:100});
		var vsourceId=new Ext.form.Hidden({name:'sourceId'});
		var vsourceType=new Ext.form.Hidden({name:'sourceType'});
		var vsourceNo=new Ext.form.TextField({name:'sourceNo',fieldLabel:'购进单号',width:125,allowBlank:false,readOnly:true,style:"background:#F6F6F6"});

		/*供应商*/
	    var storeKh=new Ext.data.JsonStore({
	    	fields: ['value', 'text'],
	        url : "gys_findGysComb.do",
	        autoLoad: {params:{keyid:type=='add'?-1:row.get('gysId')}},
	        root : "root"
	    });
	    var vKhCombo= new Ext.ux.LinkComboBox({
	    	name:'gysId',
	    	fieldLabel :'供应商',
	    	store: storeKh,
	    	valueField : 'value',
	        displayField:'text',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        hiddenName:'gysId',
	        allowBlank:false,
	        emptyText:'编号/名称...回车检索'
	    });
	    /*配送方式*/
	    var storeLogistics=new Ext.data.JsonStore({
	    	fields: ['id', 'vcName'],
	        url : "ut_getInfo.do?type=psfs",
	        autoLoad: {},
	        root : "root"
	    });
	    var vLogisticsCombo= new Ext.form.ComboBox({
	    	name:'logisticsId',
	    	fieldLabel :'配送方式',
	    	store: storeLogistics,
	    	valueField : 'id',
	        displayField:'vcName',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        hiddenName:'logisticsId'
	    });
	    
	    /*结算方式*/
	    var storeSettlement=new Ext.data.JsonStore({
	    	fields: ['id', 'vcName'],
	        url : "ut_getInfo.do?type=jsfs",
	        autoLoad: {},
	        root : "root"
	    });
	    var vSettlementCombo= new Ext.form.ComboBox({
	    	name:'settlementId',
	    	fieldLabel :'结算方式',
	    	store: storeSettlement,
	    	valueField : 'id',
	        displayField:'vcName',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        hiddenName:'settlementId'
	    });
	    
//	    /*结算状态*/
//        var vJsState= new Ext.form.ComboBox({
//        	name:'istate',
//        	fieldLabel :'结算状态',
//        	store: new Ext.data.SimpleStore({
//		        		fields: ['value', 'text'],
//		        		data:[['1','已结算'],['0','未结算']]
//        		   }),
//        	valueField : 'value',
//            displayField:'text',
//            typeAhead: true,
//            mode: 'local',
//            forceSelection: true,
//            triggerAction: 'all',
//            selectOnFocus:true,
//            value:0,
//            maxLength:10,
//            editable:false,
//            hiddenName:'istate' /*设定了 跟name值一样的话 就会提交value值*/
//        });
        
        /*经手人*/
        var storeAur=new Ext.data.JsonStore({
        	fields: ['userid', 'username'],
            url : "user_findUser.do?isuser=1",
            //autoLoad: {},
            root : "root"
        });
        var vAurCombo= new Ext.form.ComboBox({
        	name:'vcAuditor',
        	fieldLabel :'经办人',
        	store: storeAur,
        	valueField : 'userid',
            displayField:'username',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:parent.log_name,
            readOnly:true,style:"background:#F6F6F6"
        });
        
        /*表单界面 布局*/
        var vPanelForm=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.5,
                items: [vNo,vid,vdtBs,vdlMoney,vAurCombo,vSettlementCombo]
            },{
                columnWidth: 0.5,
                items: [vsourceId,{items:[
                                          {
                          					layout:'column',
                          					items:[{
                          						layout:'form',
                          						width : 218,
                          						items:[vsourceNo]
                          					},{
                          						width : 20,
                          						height : 20,
                          						xtype:'button',
                          						iconCls : 'btn-list',
                          						handler:function(){
                          							funOpen();
                          						}
                          					}]
                          				}]},vKhCombo,vLogisticsCombo]
            },{
                columnWidth: 1, items: [vRemark,vsourceType]
            }]
        });
	    
        var addForm = new Ext.form.FormPanel({
        	id:'controlpanel',
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            items:[vPanelForm]
        });
	    
	    
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '应付编辑',
    		closeAction:'close',
    		width:610,
    		height:260,
    		collapsible : true,
    		items : [addForm],
    		buttons : [/*{
    			id:'btnSave2',
    			text : '保存并新增',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ar_saveInfo.do',
    						success : function(form, action) {
//    							//Ext.Msg.alert('信息提示',action.result.message);
//    							//addWindow.close();
//    							vGridStore.reload();
//    							//addForm.getForm().reset()
//    							vid.setValue("");
//    							vNo.setValue("");
//    							vdlMoney.setValue("");
//    							vcRemark.setValue("");
//    							addWindow.show();
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
    		},*/{
    			id:'btnSave1',
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ap_saveInfo.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.close();
    							//vGridStore.reload();
    							funcinitgrid();
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
    				addWindow.close();
    			}
    		}]
    	});
        addWindow.show();
        /*判断是新增还是修改*/
        if(type=='add'){
        	addForm.getForm().reset()
        }else{
        	if(row!=null){
        		addForm.getForm().loadRecord(row);
        		vdtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeKh.on('load',function(){
        			vKhCombo.setValue(row.get("gysId"));
        		});
        		storeLogistics.on('load',function(){
        			vLogisticsCombo.setValue(row.get("logisticsId")=='0'?'':row.get("logisticsId"));
        		});
        		storeSettlement.on('load',function(){
        			vSettlementCombo.setValue(row.get("settlementId")=='0'?'':row.get("settlementId"));
        		});
        	}
        }
		
        if(type=='read') /*查看 禁用保存按钮*/{
        	Ext.getCmp('btnSave1').disable();
        }
		
	
	}
	
	/**
	 * 打开销售或销退的单据
	 * */
	function funOpen(){
		
		var vfield=[{name:'id',type:'int'},
		            {name:'vcType',type:'string'},
		            {name:'vcNo',type:'string'},
		            {name:'dtBs',type:'string'},
		            {name:'dlMoney',type:'string'},
		            {name:'gysId',type:'int'}];
		
		/*网格的数据源*/
		var vStore= new Ext.data.JsonStore({
			url:'ph_getStorageInfo.do',
			root:'root',
			totalProperty: 'total',
		    autoLoad: {params:{start:0, limit:20}},
		    fields: vfield
		});
		
		/*网格面板*/
		var vGridPaneldel= new Ext.ux.GridPanel({
			region:'center',
			store:vStore,
			height:260,
	        iconCls:'',
	        cm: new Ext.grid.ColumnModel({
	        	defaults: {sortable: true,align:'conter'},
	        	columns:[ new Ext.grid.RowNumberer(),
	        	          {header:'单据类型',width:80,dataIndex:'vcType'},
	        	          {header:'单据编号',width:100,dataIndex:'vcNo'},
	        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
	        	          {header:'金额',width:120,dataIndex:'dlMoney'},
	        	          {header:'gysId',hidden:true,dataIndex:'khId'}]
	        }),
	        bbar: new Ext.PagingToolbar({
	            pageSize: 20,
	            store: vStore,
	            displayInfo: true
	        }),
	        tbar:[{
            	xtype:'textfield',
            	id:'keyWord1',
            	width:160,
            	maxLength:100,
            	emptyText:'请输入关键字...'
            },'-',{
                	text:'查询',
                	iconCls:'btn-list',
                	handler: function(){
            			vStore.load({params:{start:0, limit:20,key:Ext.getCmp('keyWord1').getValue()}});
                	}
            }]
		});
		var addForm1 = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            height:450,
            defaults: { frame: false, border: false },
            items:[vGridPaneldel]
        });
		/*添加*/
        var addWindow1 = new Ext.ux.Window({
    		title : '选择购进单',
    		closeAction:'close',
    		width:600,
    		height:355,
    		collapsible : true,
    		items : [addForm1],
    		buttons : [{
    			id:'btnSave3',
    			text : '确定',
    			handler : function() {
    				if (addForm1.getForm().isValid()) {
    					var record= vGridPaneldel.getSelectionModel().getSelected();
    					if(!record){
    						Ext.Msg.alert('信息提示','请选择购进的单据');
    					}else{
    						var panel = Ext.getCmp('controlpanel').getForm();
    						panel.findField('sourceNo').setValue(record.get('vcNo'));
    						panel.findField('sourceId').setValue(record.get('id'));
    						panel.findField('sourceType').setValue(record.get('vcType')=='购进'?0:1);
    						panel.findField('gysId').setValue(record.get('gysId'));
    						panel.findField('dtBs').setValue(new Date(parseInt(record.get("dtBs"))));
    						panel.findField('dlMoney').setValue(record.get('vcType')=='购进'?record.get('dlMoney'):-record.get('dlMoney'));
    						addWindow1.close();
    					}
    				}
    			}
    		}, {
    			text : '取消',
    			handler : function() {
    				addWindow1.close();
    			}
    		}]
    	});

        addWindow1.show();
		
	}
	
	/**
	 * 生成支出
	 * */
	function funcOpenIn(row){
		var vsourceId=new Ext.form.Hidden({name:'sourceId',value:row.get('id')});
		var vid=new Ext.form.Hidden({name:'id'});
		var vNo= new Ext.form.TextField({hidden:true,name:'vcNo',fieldLabel:'单据编号',width:180,maxLength:20});
		var vdtBs= new Ext.form.DateField({name:'dtBs',fieldLabel:'业务日期',value:new Date(),format:'Y-m-d',width:180,allowBlank:false});
		var vdlMoney=new Ext.form.NumberField({name:'dlMoney',fieldLabel:'金额',maxLength :10,width:180,allowBlank:false,decimalPrecision:2,value:row.get('dlMoney')});
		var vcRemark=new Ext.form.TextField({name:'vcRemark',maxLength:25,fieldLabel:'备注',width:470});
		/*账目类型*/
	    var storeZmlx=new Ext.data.JsonStore({
	    	fields: ['id', 'vcName'],
	        url : "ut_getInfo.do?type=zmlx&itype=0", /**支出**/
	        autoLoad: {},
	        root : "root"
	    });
	    var vZmlxCombo= new Ext.form.ComboBox({
	    	name:'accountTypeId',
	    	fieldLabel :'账目类型',
	    	store: storeZmlx,
	    	valueField : 'id',
	        displayField:'vcName',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        maxLength:10,
	        hiddenName:'accountTypeId',
	        allowBlank: false
	    });
	    /**设置默认值**/
	    storeZmlx.on('load',function(){
	    	vZmlxCombo.setValue(storeZmlx.getAt(0).get('id'));
	    });
	    
	    /*账户*/
	    var storeZh=new Ext.data.JsonStore({
	    	fields: ['id', 'vcName'],
	        url : "ut_getInfo.do?type=zh", /**支出账户--暂时不用&itype=0*/
	        autoLoad: {},
	        root : "root"
	    });
	    var vZhCombo= new Ext.form.ComboBox({
	    	name:'accountId',
	    	fieldLabel :'支出账户',
	    	store: storeZh,
	    	valueField : 'id',
	        displayField:'vcName',
	        typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        maxLength:10,
	        hiddenName:'accountId',
	        allowBlank: false
	    });
	    /**设置默认值**/
	    storeZh.on('load',function(){
	    	vZhCombo.setValue(storeZh.getAt(0).get('id'));
	    });
	    
	    /*经手人*/
        var storeAur=new Ext.data.JsonStore({
        	fields: ['userid', 'username'],
            url : "user_findUser.do",
            //autoLoad: {},
            root : "root"
        });
        var vAurCombo= new Ext.form.ComboBox({
        	name:'vcAuditor',
        	fieldLabel :'经手人',
        	store: storeAur,
        	valueField : 'userid',
            displayField:'username',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            allowBlank: false,
            value:parent.log_name,
            readOnly:true,style:"background:#F6F6F6"
        });
        
        /*表单界面 布局*/
        var vPanelForm=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.5,
                items: [vid,vNo,vdtBs,vdlMoney,vsourceId,vAurCombo]
            },{
                columnWidth: 0.5,
                items: [vZmlxCombo,vZhCombo]
            },{
                columnWidth: 1, items: [vcRemark]
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
    		title : '生成支出',
    		closeAction:'close',
    		width:610,
    		height:220,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			id:'btnSave1',
    			text : '确定生成',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'ome_saveInfo.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.close();
    							//vGridStore.reload();
    							funcinitgrid();
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
    				addWindow.close();
    			}
    		}]
    	});
        addWindow.show();
		
	}
	
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('settlementId',vSettlementCombo1.getValue());
		vGridStore.setBaseParam('istate',vJsState1.getValue());
		vGridStore.setBaseParam('logisticsId',vLogisticsCombo1.getValue());
		vGridStore.setBaseParam('gysId',vKhCombo1.getValue());
		vGridStore.setBaseParam('dtSDate',vStartDate.getValue());
		vGridStore.setBaseParam('dtEDate',vEndDate.getValue());
		vGridStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		vGridStore.load({params:{
			start:0, 
			limit:20
		}});
	}
	
	
	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'应付管理',
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