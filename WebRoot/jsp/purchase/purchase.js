/**
 * 进货管理
 * */
Ext.onReady(function(){
		
	Ext.QuickTips.init();

	var vTaFields=[{name:'id',type:'int'},
	               {name:'vcNo',type:'string'},
	               {name:'gysId',type:'int'},
	               {name:'dtReceived',type:'string'},
	               {name:'dtBs',type:'string'},
	               {name:'deShouldPayMoney',type:'string'},
	               {name:'deActualPayMoney',type:'string'},
	               {name:'ipayState',type:'int'},
	               {name:'userId',type:'int'},
	               {name:'vcRemark',type:'string'},
	               {name:'companyId',type:'int'},
	               {name:'gysName',type:'string'},
	               {name:'username',type:'string'},
	               {name:'icbill',type:'int'},
	               {name:'fidel',type:'int'}];
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'ph_getInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vTaFields
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
        	          {header:'应付单状态',width:80,dataIndex:'icbill',renderer:function(a){return a=='1'?'已生成':'未生成';}},
        	          {header:'结算状态',width:80,dataIndex:'ipayState',renderer:function(a){return a=='1'?'已结算':'未结算';}},
        	          {header:'单据编号',width:150,dataIndex:'vcNo',renderer:function(a,b,c){
        	        	  return c.data['fidel']=='1'?"已作废/"+a:a;}
        	          },
        	          {header:'供应商',width:120,dataIndex:'gysName'},
        	          {header:'业务日期',width:80,hidden:true,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'收货日期',width:80,dataIndex:'dtReceived',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'应付金额',hidden:true,align:'right',width:120,dataIndex:'deShouldPayMoney'},
        	          {header:'总金额',width:120,align:'right',dataIndex:'deActualPayMoney'},
        	          {header:'备注',width:200,dataIndex:'vcRemark'},
        	          {header:'制单人',width:80,dataIndex:'username'}]
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
						Ext.Msg.alert('信息提示','已作废的单据不能进行操作');
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
						Ext.Msg.alert('信息提示','已生成应付单的记录不允许操作');
						return;
					}
					if(record.get('fidel')=='1'){
						Ext.Msg.alert('信息提示','已作废的记录不允许操作');
						return;
					}
					Ext.MessageBox.confirm('作废提示', '是否要作废该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "ph_deleteInfo.do",
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
        	text:'生成应付单',
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
						Ext.Msg.alert('信息提示','已作废的单据不能进行操作');
						return;
					}
					Ext.MessageBox.confirm('信息提示', '确定把选择的单据生成应付单吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "ph_saveApInfo.do",
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
        }]
	});
	
	var vStartDate=new Ext.form.DateField({name:'dts',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	
	/*付款状态*/
    var vGenderCombo1= new Ext.form.ComboBox({
    	name:'ipayState',
    	width:80,
    	fieldLabel :'付款状态',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['-1','全部'],['1','已付'],['0','未付']]
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
        editable:false,
        hiddenName:'ipayState' /*设定了 跟name值一样的话 就会提交value值*/
    });
    
    /*供应商*/
    var store11=new Ext.data.JsonStore({
    	fields: ['value', 'text'],
        url : "gys_findGysComb.do",
        //autoLoad: {},
        root : "root"
    });
    var vGysCombo11= new Ext.ux.LinkComboBox({
    	width:100,
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
    
	
	/*查询条件*/
	var vToobar1=new Ext.Toolbar({
	    items: ['日期范围：',vStartDate,'到',vEndDate,'-','供应商',vGysCombo11,'-','付款状态：',vGenderCombo1
        ,'-','关键字：',{
        	xtype:'textfield',
        	id:'keyWord',
        	maxLength:100,
        	emptyText:'单据编号/供应商/备注',
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
//        			vGridStore.load({params:{
//        				start:0, 
//        				limit:20,
//        				key:Ext.getCmp('keyWord').getValue(),
//        				ipayState:vGenderCombo1.getValue(),
//        				gysId:vGysCombo11.getValue()}});
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
	
	
	/**编辑表单**/
	function funcEdit(type,row){
		var vid=new Ext.form.Hidden({name:'id'});
		var vNo= new Ext.form.TextField({hidden:true,name:'vcNo',readOnly:true,fieldLabel:'单据编号',width:140,maxLenght:15,value:'自动生成'});
		var vDtBs= new Ext.form.DateField({name:'dtBs',hidden:true,value:new Date(),fieldLabel:'业务日期',width:140,format:'Y-m-d',allowBlank:false});
		var vDtReceived=new Ext.form.DateField({name:'dtReceived',value:new Date(),fieldLabel:'收货日期',width:140,format:'Y-m-d',allowBlank:false});
		var vShouldPayMoney=new Ext.form.NumberField({name:'deShouldPayMoney',fieldLabel:'总金额',maxLength :10,width:140,allowBlank:false,decimalPrecision:2,readOnly:true,style:'background:#F6F6F6'});
		var vActualPayMoney=new Ext.form.NumberField({hidden:true,name:'deActualPayMoney',fieldLabel:'实付金额',maxLength:10,width:140,allowBlank:false,decimalPrecision:2});
		var vcRemark=new Ext.form.TextField({name:'vcRemark',maxLength:100,fieldLabel:'备注',width:576});
		/*付款状态*/
        var vGenderCombo= new Ext.form.ComboBox({
        	hidden:true,
        	name:'ipayState',
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
            allowBlank:false,
            editable:false,
            hiddenName:'ipayState' /*设定了 跟name值一样的话 就会提交value值*/
        });
        /*供应商*/
        var store=new Ext.data.JsonStore({
        	fields: ['value', 'text'],
            url : "gys_findGysComb.do",
            autoLoad: {params:{keyid:type=='add'?0:row.get('gysId')}},
            root : "root"
        });
        var vGysCombo= new Ext.ux.LinkComboBox({
        	width:140,
        	name:'gysId',
        	fieldLabel :'供应商',
        	store: store,
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            allowBlank:false,
            hiddenName:'gysId',
            emptyText:'编号/名称...回车检索'
        });
        
        /**进货明细网格**/
        var vfiled=[{name:'id',type:'int'},
                    {name:'storageId',type:'int'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityId',type:'int'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'xlName',type:'string'},
                    {name:'dePurchaseMoney',type:'string'},
                    {name:'icount',type:'string'},
                    {name:'deSumMoney',type:'string'},
                    {name:'vcDw',type:'string'},
                    {name:'vcColor',type:'string'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'vcRemark',type:'string'},
                    {name:'warehouseId',type:'int'},
                    {name:'warehouseName',type:'string'}];
        
    	/*网格的数据源*/
    	var vGridStoreDel= new Ext.data.JsonStore({
    		url:'ph_getInfoDel.do?id='+(row==null?0:row.get("id")),
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfiled
    	});
    	
        
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		title:'商品明细',
    		id:'gridPanel1',
    		height:230,
    		width:665,
    		region:'center',
    		store:vGridStoreDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'商品名称',width:120,dataIndex:'commodityName'},
            	          {header:'批次',width:100,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:100,dataIndex:'vcSn'},
            	          {header:'数量',width:100,dataIndex:'icount'},
            	          {header:'单位',width:100,dataIndex:'vcDw'},
            	          {header:'采购价',width:100,dataIndex:'dePurchaseMoney'},
            	          {header:'金额',widht:100,dataIndex:'deSumMoney'},
            	          {header:'仓库',width:80,dataIndex:'warehouseName'},
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'颜色',width:100,dataIndex:'vcColor'},
            	          {header:'系列',width:100,dataIndex:'xlName'},
            	          {header:'备注',hidden:true,width:160,dataIndex:'vcRemark'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'storageId',hidden:true,dataIndex:'storageId'},
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
						Ext.MessageBox.confirm('删除提示', '是否要删除该商品吗？', function(c) {
						   if(c=='yes'){
							   if(record.get("id").length==0 || record.get("id")=='0'){
								   vGridStoreDel.remove(record);
								   funcSumMoney();
								   return;
							   }
							   /**做物理删除 同时删除库存记录**/
							   Ext.Ajax.request({
						   			url : "ph_deleteDelInfo.do",
						   			params:{ iddel : record.get("id") },
						   			success : function() {
						   				vGridStoreDel.remove(record);
						   				funcSumMoney();
						   			}
						   		});
						    }
						});
					}
            	}
            },'-',{
            	text:'导入',
            	hidden:true,
            	id:'btnIml',
            	iconCls:'btn-excel',
            	handler: function(){
            		Ext.ux.UpLoadWidow('ph_saveImp.do','商品明细导入','chenged');
            	}
            },'-',{
            	text:'导入格式下载',
            	hidden:true,
            	iconCls:'btn-doing',
            	handler: function(){
            		//window.open('../../excel/导入_商品购进.xls');
            	}
            }/**/]
    	});
    	/*导入完成发生的事件*/
    	var vChenge=new Ext.form.Hidden({id:'chenged',listeners:{
			disable:function(s){
    			var json = Ext.decode('{'+vChenge.getValue()+'}');
    			vGridStoreDel.removeAll(true); /*清除行*/
    			for(var i=0;i<json.data.length;i++){
    				var v=json.data[i];
    				var gridRowRecord=new Ext.data.Record({
    					commodityNo:v.commodityNo,
    					commodityName:v.commodityName,
    					commodityGg:v.commodityGg,
    					xlName:v.xlName,
    					vcBatch:v.vcBatch,
    					vcSn:v.vcSn,
    					vcColor:v.vcColor,
    					warehouseName:v.warehouseName,
    					icount:v.icount,
    					vcDw:v.vcDw,
    					dePurchaseMoney:v.dePurchaseMoney,
    					deSumMoney:v.deSumMoney,
    					vcRemark:v.vcRemark,
    					id:'0',
    					storageId:'0',
    					commodityId:v.commodityId,
    					warehouseId:v.warehouseId
    				});
                	/**添加行记录**/
    				Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
    			}
    			funcSumMoney();/*合计金额*/
    			if(json.err!=null && json.err.length>0){
    				var reg=new RegExp(',','g');
    				Ext.Msg.alert('信息提示','本次导入存在失败的记录，原因如下：<br><span style="color:red">'+json.err.replace(reg,"<br>")+"</span>");
    			}
    			
			}
		}});
        
    	/*表单界面 布局*/
        var vPanelForm=new Ext.Panel({
        	id:'panelFields',
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.33,
                items: [vGysCombo]
            },{
                columnWidth: 0.33,
                items: [vid,vNo,vDtBs,vDtReceived,vGenderCombo]
            },{
            	 columnWidth: 0.33,
                 items: [vActualPayMoney,vShouldPayMoney]
            },{
            	columnWidth: 1,items:[vcRemark]
            }]
        });
    	
        var addForm = new Ext.form.FormPanel({
        	id:'gridSpxx',
        	frame: true,
            border: false,
            layout: "column",
            height:380,
            defaults: { frame: false, border: false },
            items:[vPanelForm,vGridPaneldel]
        });
    	
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '购进编辑',
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
    					if(vJsons.length<5){
    						Ext.Msg.alert('信息提示','请添加商品信息');
    						return;
    					}
    					addForm.getForm().submit({
    						url : 'ph_savePhInfo.do', 
    						params:{jsonInfo:vJsons},
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
        }else{
        	if(row!=null){
        		Ext.getCmp('btnIml').disable(); /*修改禁用导入按钮*/
        		addForm.getForm().loadRecord(row);
        		/*赋值*/
        		vDtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		vDtReceived.setValue(new Date(parseInt(row.get("dtReceived"))));
        		store.on('load',function(){
        			vGysCombo.setValue(row.get("gysId"));
        		});
        	}
        }
        if(type=='read') /*查看 禁用保存按钮*/{
        	Ext.getCmp('btnSave1').disable();
        	Ext.getCmp('btnDelete11').disable();
        }
    	
	}
	
	/**
	 * 进货商品添加 
	 **/
	function funcEditDel(){
		var vspxxId= new Ext.form.Hidden({name:'spxxid'});
		var vXlName=new Ext.form.Hidden({name:'xlName'});
		var vFeaNo = new Ext.form.TextField({id:'feaNo',name:'feaNo',readOnly:true,style:'background:#F6F6F6',fieldLabel:'厂家编码',width:230});
		var vNo=new Ext.form.TextField({id:'spNo',name:'vcNo',readOnly:true,style:"background:#F6F6F6",fieldLabel:'商品编号',width:230});
		var vName=new Ext.form.TextField({name:'vcName',hidden:true,id:'spName',readOnly:true,style:"background:#F6F6F6",fieldLabel:'商品名称',width:230});
		var vBatch=new Ext.form.TextField({name:'vcBatch',fieldLabel:'批次',maxLength:25,allowBlank:false,width:230});
		var vDw=new Ext.form.TextField({name:'vcDw',id:'spDw',fieldLabel:'单位',readOnly:true,style:"background:#F6F6F6",width:230});
		var vColor=new Ext.form.TextField({name:'vcColor',id:'spColor',fieldLabel:'颜色',readOnly:true,style:"background:#F6F6F6",width:230});
		var vGg=new Ext.form.TextField({name:'vcGg',id:'spGg',fieldLabel:'规格',readOnly:true,style:"background:#F6F6F6",width:230});
		var vSn=new Ext.form.TextField({name:'vcSn',fieldLabel:'辅助标识',maxLength:25,width:230,emptyText:'不填写将自动生成'});
		var vPuMoney=new Ext.form.NumberField({name:'dePurchaseMoney',fieldLabel:'采购价',decimalPrecision:2,maxLength:10,allowBlank:false,width:230,value:0,
			listeners:{
				'change':function(a){
					vSumMoney.setValue(a.getValue()*vCount.getValue());
			}
		}});
		var vCount=new Ext.form.NumberField({name:'iCount',fieldLabel:'数量',allowBlank:false,decimalPrecision:2,width:230,value:0,listeners:{
			'change':function(a){
				vSumMoney.setValue(a.getValue()*vPuMoney.getValue());
			}
		}});
		var vSumMoney=new Ext.form.NumberField({name:'deSumMoney',fieldLabel:'总金额',allowBlank:false,decimalPrecision:2,width:230,value:0,readOnly:true,style:"background:#F6F6F6"});
		var vRemark=new Ext.form.TextField({hidden:true,name:'vcRemark',fieldLabel:'备注',maxLength:100,width:470});
		
		/**仓库**/
		var storeck=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ck_getCkInfo.do",
            autoLoad: {},
            root : "root"
        });
        var vCkCombo= new Ext.form.ComboBox({
        	width:230,
        	allowBlank:false,
        	name:'ckInfo',
        	fieldLabel :'存放仓库',
        	store: storeck,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            maxLength:10,
            editable:false
        });
        /**设置默认值**/
        storeck.on('load',function(){
        	vCkCombo.setValue(storeck.getAt(0).get('id'));
	    });
        
        var vspid=0;
        
        /*商品*/
        var storesp=new Ext.data.JsonStore({
        	fields: ['id', 'vcNo' ,'vcName','vcDw','vcColor','vcGg','spxxid','dbLastMoney','vcBatch','vcFactoryNo'],
            url : "spxx_findPageSpxx.do?start=0&limit=60",
            //autoLoad: {},
            root : "root"
        });
        var vSpCombo= new Ext.ux.LinkComboBox({
        	allowBlank:false,
        	width:200,
        	name:'ckInfo',
        	fieldLabel :'商品',
        	store: storesp,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            emptyText:'编号/名称/厂家编码...回车检索',
            allowBlank:false,
            listeners:{
        		select:function(a,row){
        		   vNo.setValue(row.get('vcNo'));
		     	   vName.setValue(row.get('vcName'));
		     	   vFeaNo.setValue(row.get('vcFactoryNo'));
		     	   vDw.setValue(row.get('vcDw'));
		     	   vColor.setValue(row.get('vcColor'));
		     	   vGg.setValue(row.get('vcGg'));
		     	   vspxxId.setValue(row.get('id'));
		     	   vXlName.setValue(row.get('typeName'));
		     	   vPuMoney.setValue(row.get('dbLastMoney'));
		     	  vspid=row.get('id');
		     	   vBatch.focus(false,100);
        		}
        	}
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
                items: [{layout:"column",
						items:[{
							layout:"form",
							width : 280,
							items:[vSpCombo]
						},{
							
							xtype:"button",
							iconCls : "btn-edit",
							handler:function(){
								//funOpen();
								funceditspxx(vspid);
							}
						}]
					},vBatch,vSn,vCount,vPuMoney,vspxxId,vXlName,vCkCombo]
            },{
                columnWidth: 0.5,
                items: [vNo,vFeaNo,vName,vDw,vGg,vColor,vSumMoney]
            },{
            	columnWidth:1,
            	items:[vRemark]
            }]
        });
        
        var addForm = new Ext.form.FormPanel({
        	id:'SpxxInfo',
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            items:[vPanelForm]
        });
		
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '添加商品',
    		closeAction:'close',
    		width:680,
    		height:270,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					if(parseFloat(vCount.getValue())<=0){
    						Ext.Msg.alert('信息提示','数量不能为0');
		            		   return;
    					}
    					/**判断商品、批次、辅助标识是否重复**/
    					Ext.Ajax.request({
				   			url : "ph_getExtsAddSpxx.do",
				   			params:{ spid : vspxxId.getValue(),vcbach:vBatch.getValue(),vcsn:vSn.getValue() },
				   			success : function(res) {
				   				var json = Ext.decode(res.responseText);
				   				if(json.message!='ok'){
				   					Ext.Msg.alert('信息提示','选择的商品已存在该批次和辅助标识，请确认后再填写');
				   					return;
				   				}
				   				
				   				var gridRowRecord=new Ext.data.Record({
		    						commodityNo:vNo.getValue(),
		    						commodityName:vName.getValue(),
		    						commodityGg:vGg.getValue(),
		    						xlName:vXlName.getValue(),
		    						vcBatch:vBatch.getValue(),
		    						vcSn:vSn.getValue(),
		    						vcColor:vColor.getValue(),
		    						warehouseName:vCkCombo.getRawValue(),
		    						icount:vCount.getValue(),
		    						vcDw:vDw.getValue(),
		    						dePurchaseMoney:vPuMoney.getValue(),
		    						deSumMoney:vSumMoney.getValue(),
		    						vcRemark:vRemark.getValue(),
		    						id:'0',
		    						storageId:'0',
		    						commodityId:vspxxId.getValue(),
		    						warehouseId:vCkCombo.getValue()
		    					});
		    	            	/**添加行记录**/
		    					Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
		    					funcSumMoney();/*合计金额*/
		    					addWindow.close();
				   				
				   			}
				   		});
    				}
    			}
    		}, {
    			text : '取消',
    			handler : function() {
    				addWindow.close();
    			}
    		},{
    			text:'保存并新增',
    			handler:function(){
	    			if (addForm.getForm().isValid()) {
						if(parseFloat(vCount.getValue())<=0){
							Ext.Msg.alert('信息提示','数量不能为0');
		            		   return;
						}
						/**判断商品、批次、辅助标识是否重复**/
						Ext.Ajax.request({
				   			url : "ph_getExtsAddSpxx.do",
				   			params:{ spid : vspxxId.getValue(),vcbach:vBatch.getValue(),vcsn:vSn.getValue() },
				   			success : function(res) {
				   				var json = Ext.decode(res.responseText);
				   				if(json.message!='ok'){
				   					Ext.Msg.alert('信息提示','选择的商品已存在该批次和辅助标识，请确认后再填写');
				   					return;
				   				}
				   				
				   				var gridRowRecord=new Ext.data.Record({
		    						commodityNo:vNo.getValue(),
		    						commodityName:vName.getValue(),
		    						commodityGg:vGg.getValue(),
		    						xlName:vXlName.getValue(),
		    						vcBatch:vBatch.getValue(),
		    						vcSn:vSn.getValue(),
		    						vcColor:vColor.getValue(),
		    						warehouseName:vCkCombo.getRawValue(),
		    						icount:vCount.getValue(),
		    						vcDw:vDw.getValue(),
		    						dePurchaseMoney:vPuMoney.getValue(),
		    						deSumMoney:vSumMoney.getValue(),
		    						vcRemark:vRemark.getValue(),
		    						id:'0',
		    						storageId:'0',
		    						commodityId:vspxxId.getValue(),
		    						warehouseId:vCkCombo.getValue()
		    					});
		    	            	/**添加行记录**/
		    					Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
		    					funcSumMoney();/*合计金额*/
		    					//addWindow.hide();
		    					/**数量、金额清零**/
		    					vCount.setValue(0);
		    					vSumMoney.setValue(0);
				   			}
				   		});
					}
    			}
    		}]
    	});
        addWindow.show();
		
	}
	
	/**
	 * 打开查询商品的窗口
	 * */
	function funOpen(){
		var v_lbid="0", v_lbname="", v_start=0, v_limit=20;
		var SpxxObj = [
		       		{ name:'id', type:'int'},
		       		{ name:'vcNo', type:'string'},
		       		{ name:'vcName', type:'string'},
		       		{ name:'typeId', type:'int'},
		       		{ name:'typeName', type:'string'},
		       		{ name:'vcDw', type:'string'},
		       		{ name:'vcFactoryNo', type:'string'},
		       		{ name:'vcFactoryName', type:'string'},
		       		{ name:'vcColor', type:'string'},
		       		{ name:'vcGg', type:'string'},
		       		{ name:'dbSuggMoney', type:'string'},
		       		{ name:'dbLowMoney', type:'string'},
		       		{ name:'dbAverageMoney', type:'string'},
		       		{ name:'dbLastMoney', type:'string'},
		       		{ name:'vcRemark', type:'string'}
		       		
		       	];
		       	//商品数据
		       	var store = new Ext.data.JsonStore({
		       	    url: 'spxx_findPageSpxx.do',
		       	    root: 'root',
		       	    totalProperty: 'total',
		       	    fields: SpxxObj,
		       	    listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}
		       	});
		       	
		       	//商品列表
		        var grid = new Ext.ux.GridPanel({
		        	store: store,
		            cm: new Ext.grid.ColumnModel({
		            	defaults: {	menuDisabled : false},
		       				columns:[new Ext.grid.RowNumberer(),
		       				{header: '商品编号', width: 80, sortable:true, dataIndex: 'vcNo'},
		       	            {header: '商品名称', width: 100, sortable:true, dataIndex: 'vcName'},
		       	            {header: '商品系列', width: 80, sortable:true, dataIndex: 'typeName'},
		       	            {header: '单位', width: 60, sortable:true, dataIndex: 'vcDw'},
		       	            {header: '厂家编码', width: 80, sortable:true, dataIndex: 'vcFactoryNo'},
		       	            {header: '厂家名称', width: 100, sortable:true,dataIndex: 'vcFactoryName'},
		       	            {header: '颜色', width: 60, sortable:true,dataIndex: 'vcColor'},
		       	            {header: '规格', width: 60, sortable:true,dataIndex: 'vcGg'},
		       	            {header: '建议售价', width: 100, sortable:true, dataIndex: 'dbSuggMoney'},
		       	            {header: '最低售价', width: 100, sortable:true, dataIndex: 'dbLowMoney'},
		       	            {header: '平均成本', width: 100, sortable:true, dataIndex: 'dbAverageMoney'},
		       	            {header: '最后一次进价', width: 100, sortable:true, dataIndex: 'dbLastMoney'},
		       	            {header: '备注', width: 150, sortable:true, dataIndex: 'vcRemark'},
		       	            {header: 'ID', width: 80, sortable:true, dataIndex: 'id'}]
		              	}),
		       		region:'center',
		       		title:'商品信息',
		               iconCls:'menu-53',
		               tbar:[{
		            	   xtype:'textfield',
		               		maxLength:160,
		               		emptyText:'请输入关键字...回车键查询',
		               		listeners:{
		            	   specialKey : function(field, e){
		            	   if (e.getKey() == Ext.EventObject.ENTER) {//响应回车  
		            		   store.load({params:{
		            			   start:v_start,
		            			   limit:v_limit,
		            			   search:field.getValue()
		            		   }});
	                        }  
		               	   }
		               }
		               },{
		               	text:'选择',
		               	iconCls:'btn-add',
		               	handler: function(){
		            	   var row= grid.getSelectionModel().getSelected();
		            	   if(!row){
		            		   Ext.Msg.alert('信息提示','请选择一条商品信息');
		            		   return;
		            	   }
//		            	   var json=Ext.decode(vJson);
//		            	   alert(json.id);
		            	   /*给父窗体赋值*/ 
		            	   var x = Ext.getCmp('SpxxInfo').getForm();
		            	   x.findField('vcNo').setValue(row.get('vcNo'));
		            	   x.findField('vcName').setValue(row.get('vcName'));
		            	   x.findField('vcDw').setValue(row.get('vcDw'));
		            	   x.findField('vcColor').setValue(row.get('vcColor'));
		            	   x.findField('vcGg').setValue(row.get('vcGg'));
		            	   x.findField('spxxid').setValue(row.get('id'));
		            	   x.findField('xlName').setValue(row.get('typeName'));
		            	   x.findField('dePurchaseMoney').setValue(row.get('dbLastMoney'));
		            	   x.findField('vcBatch').focus(false,100);
		            	   vWindow.hide();
		               	}
		               }],
		               
		               bbar: new Ext.PagingToolbar({
		                   pageSize: 20,
		                   store: store,
		                   displayInfo: true
		               })
		  });
		  grid.addListener('rowdblclick', function(grid){
			  var row= grid.getSelectionModel().getSelected();
	       	   if(!row){
	       		   Ext.Msg.alert('信息提示','请选择一条商品信息');
	       		   return;
	       	   }
	//       	   var json=Ext.decode(vJson);
	//       	   alert(json.id);
	       	   /*给父窗体赋值*/ 
	       	   var x = Ext.getCmp('SpxxInfo').getForm();
	       	   x.findField('vcNo').setValue(row.get('vcNo'));
	       	   x.findField('vcName').setValue(row.get('vcName'));
	       	   //x.findField('vcName').setValue(row.get('vcName'));
	       	   x.findField('vcDw').setValue(row.get('vcDw'));
	       	   x.findField('vcColor').setValue(row.get('vcColor'));
	       	   x.findField('vcGg').setValue(row.get('vcGg'));
	       	   x.findField('spxxid').setValue(row.get('id'));
	       	   x.findField('xlName').setValue(row.get('typeName'));
	       	   x.findField('dePurchaseMoney').setValue(row.get('dbLastMoney'));
	       	   x.findField('vcBatch').focus(false,100);
	       	   vWindow.hide();
		  });
		
		//商品类别树
		var tree = new Ext.tree.TreePanel({
			title:'商品系列',
			region : 'west',
			width : 180,
	        minSize: 150,
	        maxSize: 300,
	        split : true,
			useArrows: true,
	        autoScroll:true,
	        animate:true,
	        enableDD:false,
	        containerScroll: true,
	        frame:true,
	        dataUrl: 'splb_findSplbTree.do',
	        root: {
	            nodeType: 'async',
	            text: '所有系列',
	            draggable: false,
	            id: '0'
	        },
	        buttonAlign : 'left',
	        listeners:{
	        	click:function(n){
	        		//更新商品数据
	        		store.load({params:{start:v_start, limit:v_limit,lbid:n.id}});
	        	}
	        }
		});
		
		//展开节点
		tree.getRootNode().expand();
		
		var vWindow=new Ext.Window({
			layout:'fit',
			width:600,
			height:300,
			items:[{
				frame:true,
				layout:'border',
				items:[tree,grid]
			}]
		});
		
		vWindow.show();
	}
	
	/**
	 * 合计金额
	 * */
	function funcSumMoney(){
		var gs = Ext.getCmp('gridPanel1').getStore();
		var iSum=0;
		for(var i=0;i<gs.getCount();i++){
			iSum +=parseFloat(gs.getAt(i).data['deSumMoney']);
		}
		var panel = Ext.getCmp('gridSpxx').getForm();
		panel.findField('deShouldPayMoney').setValue(Math.round(iSum));
		panel.findField('deActualPayMoney').setValue(Math.round(iSum));
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
				vJson+="'xlName':'"+gs.getAt(i).data["xlName"]+"',";
				vJson+="'vcBatch':'"+gs.getAt(i).data["vcBatch"]+"',";
				vJson+="'vcSn':'"+gs.getAt(i).data["vcSn"]+"',";
				vJson+="'warehoseName':'"+gs.getAt(i).data["warehoseName"]+"',";
				vJson+="'icount':'"+gs.getAt(i).data["icount"]+"',";
				vJson+="'vcDw':'"+gs.getAt(i).data["vcDw"]+"',";
				vJson+="'dePurchaseMoney':'"+gs.getAt(i).data["dePurchaseMoney"]+"',";
				vJson+="'deSumMoney':'"+gs.getAt(i).data["deSumMoney"]+"',";
				vJson+="'vcRemark':'"+gs.getAt(i).data["vcRemark"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"',";
				vJson+="'vcColor':'"+gs.getAt(i).data["vcColor"]+"',";
				vJson+="'storageId':'"+gs.getAt(i).data["storageId"]+"',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'warehoseId':'"+gs.getAt(i).data["warehouseId"]+"'";
				vJson+="}";
			}else{
				vJson+="{";
				vJson+="'commodityNo':'"+gs.getAt(i).data["commodityNo"]+"',";
				vJson+="'commodityName':'"+gs.getAt(i).data["commodityName"]+"',";
				vJson+="'commodityGg':'"+gs.getAt(i).data["commodityGg"]+"',";
				vJson+="'xlName':'"+gs.getAt(i).data["xlName"]+"',";
				vJson+="'vcBatch':'"+gs.getAt(i).data["vcBatch"]+"',";
				vJson+="'vcSn':'"+gs.getAt(i).data["vcSn"]+"',";
				vJson+="'warehoseName':'"+gs.getAt(i).data["warehoseName"]+"',";
				vJson+="'icount':'"+gs.getAt(i).data["icount"]+"',";
				vJson+="'vcDw':'"+gs.getAt(i).data["vcDw"]+"',";
				vJson+="'dePurchaseMoney':'"+gs.getAt(i).data["dePurchaseMoney"]+"',";
				vJson+="'deSumMoney':'"+gs.getAt(i).data["deSumMoney"]+"',";
				vJson+="'vcRemark':'"+gs.getAt(i).data["vcRemark"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"',";
				vJson+="'vcColor':'"+gs.getAt(i).data["vcColor"]+"',";
				vJson+="'storageId':'"+gs.getAt(i).data["storageId"]+"',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'warehoseId':'"+gs.getAt(i).data["warehouseId"]+"'";
				vJson+="}";
			}
		}
		vJson+=']';
		return vJson;
	}
	
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('gysId',vGysCombo11.getValue());
		vGridStore.setBaseParam('ipayState',vGenderCombo1.getValue());
		vGridStore.setBaseParam('key',Ext.getCmp('keyWord').getValue());
		vGridStore.setBaseParam('dts',vStartDate.getValue());
		vGridStore.setBaseParam('dte',vEndDate.getValue());
		vGridStore.load({params:{
			start:0, 
			limit:20
		}});
	}
	
	/**
	 * 功能：根据id修改商品
	 * 作者：RC
	 * 创建日期：2015-07-14
	 * */
	function funceditspxx(spid){
		if(spid==0){
			Ext.Msg.alert('信息提示','您没有选择任何商品');
			return;
		}
		Ext.Ajax.request({
			url:'ph_getSpxxInfo.do?spid='+spid,
			success:function(res){
				var json = Ext.decode(res.responseText);
				var vNo = new  Ext.form.TextField({name:'vcNo',fieldLabel:'编号',width:180,value:json.root[0].vcNo});
				var vName= new Ext.form.TextField({name:'vcName',fieldLabel:'名称',width:180,value:json.root[0].vcName});
				var vFactoryNo=new Ext.form.TextField({name:'vcFactoryNo',fieldLabel:'厂家编码',width:180,value:json.root[0].vcFactoryNo});
				var vFactoryName=new Ext.form.TextField({name:'vcFactoryName',fieldLabel:'厂家名称',width:180,value:json.root[0].vcFactoryName});
				var vColor=new Ext.form.TextField({name:'vcColor',fieldLabel:'颜色',width:180,value:json.root[0].vcColor});
				var vGg=new Ext.form.TextField({name:'vcGg',fieldLabel:'规格',width:180,value:json.root[0].vcGg});
				var vRemark=new Ext.form.TextArea({name:'vcRemark',fieldLabel:'备注',width:180,hieght:60,value:json.root[0].vcRemark});
				/*表单界面 布局*/
		        var vPanelForm=new Ext.Panel({
		        	frame: true,
		            border: false,
		            labelAlign: "right",
		            layout: "column",
		            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
		            items: [{
		                columnWidth: 1,
		                items: [vNo,vName,vFactoryNo,vFactoryName,vColor,vGg,vRemark]
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
		    		title : '商品修改',
		    		closeAction:'close',
		    		height:320,
		    		width:320,
		    		collapsible : true,
		    		items : [addForm],
		    		buttons : [{
		    			text : '保存',
		    			handler : function() {
		    				if (addForm.getForm().isValid()) {
		    					addForm.getForm().submit({
		    						url : 'ph_saveSpxx.do?id='+spid,
		    						success : function(form, action) {
		    							Ext.Msg.alert('信息提示',action.result.message);
		    							/*更新 界面显示*/
		    							Ext.getCmp('spNo').setValue(vNo.getValue());
		    							Ext.getCmp('spName').setValue(vName.getValue());
		    							//Ext.getCmp('spDw').setValue(vDw.getValue());
		    							Ext.getCmp('spColor').setValue(vColor.getValue());
		    							Ext.getCmp('spGg').setValue(vGg.getValue());
		    							Ext.getCmp('feaNo').setValue(vFactoryNo.getValue());
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
		});
	}
	
	
	
	  //布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			title:'购进管理',
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
