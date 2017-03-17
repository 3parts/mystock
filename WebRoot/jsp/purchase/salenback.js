/*
 * 销售退库
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vFields=[{name:'id',type:'int'},
	             {name:'vcNo',type:'string'},
	             {name:'dtBs',type:'string'},
	             {name:'khId',type:'int'},
	             {name:'khName',type:'string'},
	             {name:'ipayType',type:'int'},
	             {name:'deMoney',type:'string'},
	             {name:'deOkMoney',type:'string'},
	             {name:'deOtherMoney',type:'string'},
	             {name:'warehouseId',type:'int'},
	             {name:'warehouseName',type:'string'},
	             {name:'vcRemark',type:'string'},
	             {name:'userId',type:'int'},
	             {name:'userName',type:'string'},
	             {name:'icbill',type:'int'},
	             {name:'fidel',type:'int'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'salenback_getInfo.do',
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
        	          {header:'应收单状态',width:80,dataIndex:'icbill',renderer:function(a){return a=='1'?'已生成':'未生成'; }},
        	          {header:'结算状态',width:100,dataIndex:'ipayType',renderer:function(a){return a=='1'?'已结算':'未结算'}},
        	          {header:'单据编号',width:140,dataIndex:'vcNo',renderer:function(a,b,c){return c.data['fidel']=='1'?"已作废/"+a:a;}},
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'客户名称',width:100,dataIndex:'khName'},
        	          {header:'总金额',align:'right',width:100,dataIndex:'deMoney'},
        	          {header:'实付金额',align:'right',hidden:true,width:100,dataIndex:'deOkMoney'},
        	          {header:'其他金额',align:'right',hidden:true,width:100,dataIndex:'deOtherMoney'},
        	          {header:'仓库名称',width:100,hidden:true,dataIndex:'warehouseName'},
        	          {header:'备注',width:180,dataIndex:'vcRemark'},
        	          {header:'制单人',width:100,dataIndex:'userName'},
        	          {header:'khId',hidden:true,dataIndex:'khId'},
        	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'},
        	          {header:'userId',hidden:true,dataIndex:'userId'}]
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
					   			url : "salenback_deleteInfo.do",
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
						Ext.Msg.alert('信息提示','已作废的单据不允许操作');  
						return;
					}
					Ext.MessageBox.confirm('信息提示', '确定把选择的单据生成应收单吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "salenback_saveArInfo.do",
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
        },{
	    	text:'打印',
        	iconCls:'btn-print',
        	handler: function(){
		    	var record= vGridPanel.getSelectionModel().getSelected();
	        	if(!record){
	        		Ext.Msg.alert('信息提示','请选择要打印的记录');
	        		return;
	        	}
				Ext.Ajax.request({
					url : "print_getSingInfo.do?key=tbsalenback",
					success : function(response) {
						var v=response.responseText;
						if(v.length==0){
							Ext.Msg.alert('信息提示','打印模板读取错误');
							return;
						}
						/**
						 * 调用打印 
						 * */
						window.open('../../gridReport/MyPrint.jsp?data=salenback_getPrintXmlData.do?id='+record.get('id')+'&report='+v);
						//window.open('../../gridReport/MyPrint.jsp?data=spk_getXmlData.do?id='+record.get('id')+'&report='+v);
					}
				});
        	}
	    }]
	});
	
	/*查询条件控件*/
	/*付款状态*/
    var vPayState1= new Ext.form.ComboBox({
    	width:80,
    	name:'ipayType',
    	fieldLabel :'付款状态',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['1','已付'],['0','未付'],['-1','全部']]
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
    	width:120,
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
    
    /*开始时间和结束时间*/
	var vStartDate=new Ext.form.DateField({name:'dts',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
    
    /**条件区域**/
	var vToobar1=new Ext.Toolbar({
	    items: ['日期范围：',vStartDate,'到',vEndDate,'-','客户：',vKhCombo1,'-','收款状态：',vPayState1,'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	maxLength:100,
        	emptyText:'单据编号/客户名称/备注',
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
//	    			khId:vKhCombo1.getValue(),
//	    			ipayType:vPayState1.getValue()}});
        		funcinitgrid();
        	}
	    }]
	});
	
	
	
	
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 55,
		items:[vToobar,vToobar1]
	});
	
	
	
	/**
	 * 新增或编辑
	 * */
	function funcEdit(type,row){
		var vId=new Ext.form.Hidden({name:'id'});
		var vNo=new Ext.form.TextField({name:'vcNo',hidden:true,readOnly:true,fieldLabel:'单据编号',width:180,maxLenght:15,value:'自动生成'});
		var vDtBs=new Ext.form.DateField({name:'dtBs',value:new Date(),fieldLabel:'业务日期',width:140,format:'Y-m-d',allowBlank:false});
		var vdeOutMoney=new Ext.form.NumberField({name:'deMoney',fieldLabel:'总金额',width:140,allowBlank:false,decimalPrecision:2,readOnly:true,style:'background:#F6F6F6'});
		var vdeOkOutMoney=new Ext.form.NumberField({name:'deOkMoney',hidden:true,fieldLabel:'实付金额',width:140,allowBlank:false,decimalPrecision:2});
		var vdeOtherMoeny=new Ext.form.NumberField({name:'deOtherMoney',hidden:true,fieldLabel:'其他金额',width:140,decimalPrecision:2});
		var vRemark=new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:360});
		var vPrint=new Ext.form.CheckboxGroup({name:'isaleprint1',fieldLabel:'保存后',items:[{boxLabel:'打印',name:'s1',checked:false}]});
		
		/*付款状态*/
        var vPayState= new Ext.form.ComboBox({
        	hidden:true,
        	name:'ipayType',
        	fieldLabel :'付款状态',
        	store: new Ext.data.SimpleStore({
		        		fields: ['value', 'text'],
		        		data:[['1','已付'],['0','未付']]
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
            hiddenName:'ipayType' /*设定了 跟name值一样的话 就会提交value值*/
        });
       
        /*仓库*/
        var storeCk=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ck_getCkInfo.do",
            //autoLoad: {},
            root : "root"
        });
        var vCkCombo= new Ext.form.ComboBox({
        	hidden:true,
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
            //allowBlank:false,
            hiddenName:'warehouseId'
        });
        
        /*客户*/
        var storeKh=new Ext.data.JsonStore({
        	fields: ['value', 'text'],
            url : "kh_findKhComb.do",
            autoLoad: {params:{keyid:type=='add'?0:row.get('khId')}},
            root : "root"
        });
        var vKhCombo= new Ext.ux.LinkComboBox({
        	name:'khId',
        	width:140,
        	fieldLabel :'客户',
        	store: storeKh,
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            hiddenName:'khId',
            emptyText:'编号/名称...回车检索'
        });
        
        /*********销售明细网格***********/
        var vfiled=[{name:'id',type:'int'},
                    {name:'salenbackId',type:'int'},
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
    		url:'salenback_getInfoDel.do?id='+(row==null?0:row.get("id")),
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfiled
    	});
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		id:'gridPanel1',
    		title:'商品明细',
    		height:230,
    		width:665,
    		region:'center',
    		store:vGridStoreDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'商品名称',width:120,dataIndex:'commodityName'},
            	          {header:'数量',width:60,dataIndex:'icount'},
            	          {header:'单位',width:60,dataIndex:'vcDw'},
            	          {header:'单价',width:60,dataIndex:'dePriceMoney'},
            	          {header:'折扣率',width:50,dataIndex:'deDiscount'},
            	          {header:'金额',widht:60,dataIndex:'deSumMoney'},
            	          {header:'批次',width:100,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:100,dataIndex:'vcSn'},
            	          {header:'仓库',width:80,dataIndex:'warehouseName'},
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'salenbackId',hidden:true,dataIndex:'salenbackId'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'}]
            }),
            tbar:[{
            	text:'添加',
            	iconCls:'btn-add',
            	handler: function(){
            		var v=vKhCombo.getValue();
            		if(v==null || v.length==0){
            			Ext.Msg.alert('信息提示','请先选择客户');
            			return;
            		}
            		funcEditDel(v);
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
						Ext.MessageBox.confirm('删除提示', '是否要删除该商品吗？', function(c) {
						   if(c=='yes'){
							   if(record.get("id").length==0 || record.get("id")=='0'){
								   vGridStoreDel.remove(record);
								   funcSumMoney1();
								   return;
							   }
							   /**做物理删除 同时修改库存记录**/
							   Ext.Ajax.request({
						   			url : "salenback_deleteDelInfo.do",
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
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.33,
                items: [vKhCombo,vId,vNo]
            },{
                columnWidth: 0.33,
                items: [vDtBs,vCkCombo,vdeOtherMoeny,vPayState]
            },{
            	columnWidth: 0.33,
                items: [vdeOutMoney,vdeOkOutMoney]
            },{
            	columnWidth: 0.66,items:[vRemark]
            },{
            	columnWidth: 0.33,items:[vPrint]
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
    		title : '销退编辑',
    		closeAction:'close',
    		width:700,
    		height:385,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			id:'btnSave1',
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					var vJsons=funcConvertGrid(); /*得到网格的json字符串*/
    					//alert(vJsons);
    					if(vJsons.length<5){
    						Ext.Msg.alert('信息提示','请添加商品信息');
    						return;
    					}
    					addForm.getForm().submit({
    						url : 'salenback_saveSInfo.do', 
    						params:{jsonInfo:vJsons},
    						success : function(form, action) {
    							//vGridStore.reload();
    							/*是否打印打印*/
    							var v = vPrint.items.itemAt(0);
    							if(v.checked){
    								Ext.Ajax.request({
    									url : "print_getSingInfo.do?key=tbsalenback",
    									success : function(response) {
    										var v=response.responseText;
    										if(v.length==0){
    											Ext.Msg.alert('信息提示','打印模板读取错误');
    											return;
    										}
    										/**
    										 * 调用打印 
    										 * */
    										window.open('../../gridReport/MyPrint.jsp?data=salenback_getPrintXmlData.do?id='+action.result.newid+'&report='+v+'&save=1');
    										//window.open('../../gridReport/MyPrint.jsp?data=spk_getXmlData.do?id='+record.get('id')+'&report='+v);
    									}
    								});
    							}
    							Ext.Msg.alert('信息提示',action.result.message);
    							funcinitgrid();
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
        
        /*判断是新增还是修改*/
        if(type=='add'){
        	addForm.getForm().reset()
        }else{
        	if(row!=null){
        		addForm.getForm().loadRecord(row);
        		/*赋值*/
        		vDtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeKh.on('load',function(){
        			vKhCombo.setValue(row.get("khId"));
        		});
        		storeCk.on('load',function(){
        			vCkCombo.setValue(row.get("warehouseId"));
        		});
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
	function funcEditDel(vKhId){
		var vNo=new Ext.form.TextField({name:'vcNo',fieldLabel:'商品编号',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vName=new Ext.form.TextField({name:'vcName',fieldLabel:'商品名称',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vBatch=new Ext.form.TextField({name:'vcBatch',fieldLabel:'批次',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vBs=new Ext.form.TextField({name:'vcBs',fieldLabel:'辅助标识',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vGg=new Ext.form.TextField({name:'vcGg',fieldLabel:'规格',width:180,readOnly:true,style:'background:#F6F6F6'});
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
		var vJhMoney=new Ext.form.NumberField({name:'vJhMoney',fieldLabel:'销售价',width:180,decimalPrecision:2,readOnly:true,style:'background:#F6F6F6'});
		var vMoney=new Ext.form.NumberField({id:'vMoney1',name:'vMoney',fieldLabel:'销退价',decimalPrecision:2,width:180,allowBlank:false,
			listeners:{
			'change':function(a){
				funcSumMoney();
			}}
		});
		var vSumMoney=new Ext.form.NumberField({id:'vSumMoney1',name:'vSumMoney',fieldLabel:'销退总金额',decimalPrecision:2,width:180,allowBlank:false,readOnly:true,style:'background:#F6F6F6'});
		var vYhMoney=new Ext.form.NumberField({id:'vYhMoney1',allowBlank:false,emptyText:'输入值范围：0到1',value:1,name:'vYhMoney',fieldLabel:'折扣率',decimalPrecision:2,width:180,
			listeners:{
			'change':function(a){
				funcSumMoney();
			}}
		});
		/**
		 * 进货单选择网格
		 * */
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
    		url:'salenout_getInfoDel1.do?ikh='+vKhId,
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfiled
    	});
    	
        
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		height:180,
    		width:568,
    		region:'center',
    		store:vGridStoreDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'商品名称',width:100,dataIndex:'commodityName'},
            	          {header:'销售数量',width:60,dataIndex:'icount'},
            	          {header:'单位',width:50,dataIndex:'vcDw'},
            	          {header:'销售单价',width:60,dataIndex:'dePriceMoney'},
            	          {header:'折扣率',width:60,dataIndex:'deDiscount'},
            	          {header:'销售金额',widht:60,dataIndex:'deSumMoney'},
            	          {header:'批次',width:60,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:60,dataIndex:'vcSn'},
            	          {header:'仓库',width:80,dataIndex:'warehouseName'},
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'salenoutId',hidden:true,dataIndex:'salenoutId'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'}]
            }),
            tbar:[{
            	xtype:'textfield',
            	id:'keyWord1',
            	width:160,
            	maxLength:100,
            	emptyText:'商品/编号/厂家编码/备注',
            	listeners:{
            		specialKey : function(field, e){
            			if (e.getKey() == e.ENTER){
            				vGridStoreDel.load({params:{key:Ext.getCmp('keyWord1').getValue()}});
            			}
            		}
            	}
            },'-',{
                	text:'查询',
                	iconCls:'btn-list',
                	handler: function(){
            			vGridStoreDel.load({params:{key:Ext.getCmp('keyWord1').getValue()}});
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
            			vGg.setValue(row[0].data.commodityGg);
            			vWareHouseName.setValue(row[0].data.warehouseName);
            			vWareHouseId.setValue(row[0].data.warehouseId);
            			vDw.setValue(row[0].data.vcDw);
            			vCommodityId.setValue(row[0].data.commodityId);
            			vJhMoney.setValue(row[0].data.dePriceMoney);
            			/*得到焦点*/
            			vCount.focus(false,100);
            	}
            }
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
                items: [vNo,vName,vWareHouseId,vCount,vMoney,vYhMoney,vSumMoney]
            },{
                columnWidth: 0.5,
                items: [vBs,vGg,vWareHouseName,vJhMoney,vCommodityId,vBatch,vDw]
            }]
        });
        
        var addForm1 = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            height:450,
            defaults: { frame: false, border: false },
            items:[vGridPaneldel,vPanelForm1]
        });
        
        /*添加*/
        var addWindow1 = new Ext.ux.Window({
    		title : '销售选择购进商品',
    		closeAction:'close',
    		width:600,
    		height:455,
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
    					if(vCount.getValue() > row[0].data.icount){
    						Ext.Msg.alert('信息提示','销退数量不能大于销售数量');
    						return;
    					}
    					var gridRowRecord=new Ext.data.Record({
    						commodityNo:vNo.getValue(),
    						commodityName:vName.getValue(),
    						commodityGg:vGg.getValue(),
    						vcBatch:vBatch.getValue(),
    						vcSn:vBs.getValue(),
    						warehouseName:vWareHouseName.getRawValue(),
    						icount:vCount.getValue(),
    						vcDw:vDw.getValue(),
    						dePriceMoney:vMoney.getValue(),
    						deSumMoney:vSumMoney.getValue(),
    						deDiscount:vYhMoney.getValue(),
    						id:'0',
    						salenbackId:'0',
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
		}
		var panel = Ext.getCmp('gridSpxx').getForm();
		panel.findField('deMoney').setValue(Math.round(iSum));
		panel.findField('deOkMoney').setValue(Math.round(iSum));
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
				vJson+="'salenbackId':'"+gs.getAt(i).data["salenbackId"]+"',";
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
				vJson+="'salenbackId':'"+gs.getAt(i).data["salenbackId"]+"',";
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
		vGridStore.setBaseParam('khId',vKhCombo1.getValue());
		vGridStore.setBaseParam('ipayType',vPayState1.getValue());
		vGridStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		vGridStore.setBaseParam('dts',vStartDate.getValue());
		vGridStore.setBaseParam('dte',vEndDate.getValue());
		vGridStore.load({params:{
			start:0, 
			limit:20
		}});
	}
	
	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'销退管理',
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