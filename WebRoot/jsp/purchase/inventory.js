/*
 *库存盘点 
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var fields=[{name:'id',type:'int'},
	            {name:'vcNo',type:'string'},
	            {name:'dtBs',type:'string'},
	            {name:'warehouseId',type:'int'},
	            {name:'warehouseName',type:'string'},
	            {name:'vcRemark',type:'string'},
	            {name:'userId',type:'int'},
	            {name:'userName',type:'string'},
	            {name:'istate',type:'int'},
	            {name:'checkUserId',type:'int'},
	            {name:'checkUserName',type:'string'},
	            {name:'dtCheck',type:'string'},
	            {name:'isOk',type:'int'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'ivt_getInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: fields
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'单据编号',width:100,dataIndex:'vcNo'},
        	          {header:'盘点状态',width:100,dataIndex:'isOk',renderer:function(a){return a=='1'?'已完成':'未完成'}},
        	          {header:'审核状态',width:100,dataIndex:'istate',renderer:function(a){return a=='1'?'已审核':'未审核'}},
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'盘点仓库',width:120,dataIndex:'warehouseName'},
        	          {header:'备注',width:200,dataIndex:'vcRemark'},
        	          {header:'审核人',width:100,dataIndex:'checkUserName'},
        	          {header:'审核时间',width:120,dataIndex:'dtCheck',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d H:i'); }},
        	          {header:'制单人',width:100,dataIndex:'userName'}]
        }),
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: vGridStore,
            displayInfo: true
        })
	});
	
	/*开始时间和结束时间*/
	var vStartDate=new Ext.form.DateField({name:'dts',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	var vEndDate=new Ext.form.DateField({name:'dte',width:100,value:new Date().add(Date.DAY, 0),format:'Y-m-d'});
	
	/**盘点仓库**/
    var storeOut1=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "ck_getCkInfo.do",
        autoLoad: {},
        root : "root"
    });
    var vOutCombo1= new Ext.form.ComboBox({
    	width:120,
    	name:'warehouseId',
    	fieldLabel :'盘点仓库',
    	store: storeOut1,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        hiddenName:'warehouseId'
    });

	/*工具栏按钮*/
	var vToobar=new Ext.Toolbar({
	    items: [{
        	text:'添加',
        	iconCls:'btn-add',
        	handler: function(){
        		funcEdit("add"); 
        	}
        },{
        	text:'修改',
        	hidden:true,
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的单据');
				}else{
					if(record.get('istate')=='1' || record.get('isOk')=='1'){
						Ext.Msg.alert('信息提示','已审核或已完成的单据不允许修改');
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
        	text:'审核',
        	iconCls:'btn-list',
        	handler:function(){
        	var record= vGridPanel.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要审核的单据');  
			}else{
				if(record.get('istate')=='1' || record.get('isOk')=='1'){
					Ext.Msg.alert('信息提示','单据的状态不正确');
					return;
				}
				Ext.MessageBox.confirm('审核提示', '确定要审核选择的单据吗？', function(c) {
				   if(c=='yes'){
					   Ext.Ajax.request({
				   			url : "ivt_saveCheckInfo.do",
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
        	text:'盘点完成',
        	iconCls:'btn-list',
        	handler:function(){
        	var record= vGridPanel.getSelectionModel().getSelected();
			if(!record){
            	Ext.Msg.alert('信息提示','请选择要盘点完成的单据');  
			}else{
				if(record.get('istate')=='0' || record.get('isOk')=='1'){
					Ext.Msg.alert('信息提示','单据的状态不正确');
					return;
				}
				Ext.MessageBox.confirm('盘点完成提示', '确定要盘点完成选择的单据吗？', function(c) {
				   if(c=='yes'){
					   Ext.Ajax.request({
				   			url : "ivt_saveOkInfo.do",
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
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的单据');  
				}else{
					if(record.get('istate')=='1' || record.get('isOk')=='1'){
						Ext.Msg.alert('信息提示','已审核或已完成的单据不允许删除');
						return;
					}
					Ext.MessageBox.confirm('删除提示', '是否要删除该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "ivt_deleteInfo.do",
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
        },'-','日期范围：',vStartDate,'到',vEndDate,'-',{
    	xtype:'textfield',
    	id:'keyWord',
    	width:160,
    	maxLength:100,
    	emptyText:'商品名称/备注',
    	listeners:{
    		specialKey:function(field, e){
    			if (e.getKey() == e.ENTER){
    				funcinitgrid();
    			}
    		}
    	}
        },'-','盘点库存：',vOutCombo1,'-',{
        	text:'查询',
        	iconCls:'btn-list',
        	handler: function(){
//        		vGridStore.load({params:{
//        			start:0, 
//        			limit:20,
//        			key:Ext.getCmp('keyWord').getValue(),
//        			warehouseId:vOutCombo1.getValue()
//        		}});
        		funcinitgrid();
        	}
        }]
	});
	
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 30,
		items:[vToobar]
	});
	
	/**
	 * 新增或修改
	 * */
	function funcEdit(type,row){
		var vId=new Ext.form.Hidden({name:'id'});
		var vNo=new Ext.form.TextField({name:'vcNo',hidden:true,readOnly:true,fieldLabel:'单据编号',width:180,maxLenght:15,value:'自动生成'});
		var vDtBs=new Ext.form.DateField({name:'dtBs',value:new Date(),fieldLabel:'业务日期',width:180,format:'Y-m-d',allowBlank:false});
		var vRemark=new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:470,maxLength:100});
		
		/**盘点仓库**/
        var storeOut=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ck_getCkInfo.do",
            autoLoad: {},
            root : "root"
        });
        var vOutCombo= new Ext.form.ComboBox({
        	name:'warehouseId',
        	fieldLabel :'盘点仓库',
        	store: storeOut,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            hidden:true,
            hiddenName:'warehouseId'
        });
        /**设置默认值**/
        storeOut.on('load',function(){
        	vOutCombo.setValue(storeOut.getAt(0).get('id'));
	    });
        
        /***明细网格***/
        var vfield=[{name:'id',type:'int'},
                    {name:'inventoryId',type:'int'},
                    {name:'commodityId',type:'int'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'warehouseId',type:'int'},
                    {name:'dlBefore',type:'string'},
                    {name:'dlActual',type:'string'},
                    {name:'vcRemark',type:'string'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'stockId',type:'int'}];
       
        /*网格的数据源*/
    	var vGridStoreDel= new Ext.data.JsonStore({
    		url:'ivt_getInfoDel.do?id='+(row==null?0:row.get("id")),
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfield
    	});
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		id:'gridPanel1',
    		title:'盘点明细',
    		height:220,
    		width:568,
    		region:'center',
    		store:vGridStoreDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品名称',width:120,dataIndex:'commodityName'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'批次',width:100,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:100,dataIndex:'vcSn'},
            	          {header:'账面数量',width:100,dataIndex:'dlBefore'},
            	          {header:'实际数量',width:100,dataIndex:'dlActual'},
            	          {header:'inventoryId',hidden:true,dataIndex:'inventoryId'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'},
            	          {header:'stockId',hidden:true,dataIndex:'stockId'}]
            }),
            tbar:[{
            	text:'添加',
            	iconCls:'btn-add',
            	handler: function(){
//            		var vwarehouse=vOutCombo.getValue();
//            		if(vwarehouse==null || vwarehouse.length==0){
//            			Ext.Msg.alert('信息提示','请选择需要盘点的仓库');
//            			return;
//            		}
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
								   return;
							   }
							   /**做物理删除 同时修改库存记录**/
							   Ext.Ajax.request({
						   			url : "ivt_deleteInfoDel.do",
						   			params:{ iddel : record.get("id") },
						   			success : function() {
						   				vGridStoreDel.remove(record);
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
                columnWidth: 0.5,
                items: [vId,vNo,vDtBs]
            },{
                columnWidth: 0.5,
                items: [vOutCombo]
            },{
            	columnWidth: 1,items:[vRemark]
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
    		title : '库存盘点编辑',
    		closeAction:'close',
    		width:600,
    		height:410,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			id:'btnSave1',
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					var vJsons=funcConvertGrid(); /*得到网格的json字符串*/
    					if(vJsons.length<5){
    						Ext.Msg.alert('信息提示','请添加库存信息');
    						return;
    					}
    					addForm.getForm().submit({
    						url : 'ivt_saveInfo.do', 
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
        		/*赋值*/
        		vDtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeOut.on('load',function(){
        			vOutCombo.setValue(row.get("warehouseId"));
        		});
        	}
        }
        if(type=='read') /*查看 禁用保存按钮*/{
        	Ext.getCmp('btnSave1').disable();
        	Ext.getCmp('btnDelete11').disable();
        }
        
        
        
        
        
		
	}
	
	/**
	 * 明细项
	 * */
	function funcEditDel(house){
		var vNo=new Ext.form.TextField({name:'vcNo',fieldLabel:'商品编号',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vName=new Ext.form.TextField({name:'vcName',fieldLabel:'商品名称',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vBatch=new Ext.form.TextField({name:'vcBatch',fieldLabel:'批次',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vSn=new Ext.form.TextField({name:'vcSn',fieldLabel:'辅助标识',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vGg=new Ext.form.TextField({name:'vcGg',fieldLabel:'商品规格',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vdlBefore=new Ext.form.NumberField({name:'dlBefore',fieldLabel:'账面数量',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vdlActual=new Ext.form.NumberField({name:'dlActual',fieldLabel:'实际数量',width:180,allowBlank:false});
		var vCommodityId=new Ext.form.Hidden({name:'commodityId'});
		var vId=new Ext.form.Hidden({name:'id'});
		var vWareHouseId=new Ext.form.Hidden({name:'warehouseId'});
		var vDw=new Ext.form.TextField({name:'vcDw',fieldLabel:'单位',width:180,readOnly:true,style:'background:#F6F6F6'});

        /**库存网格**/
        var vfiled=[{name:'id',type:'int'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityId',type:'int'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'vcDw',type:'string'},
                    {name:'dlQty',type:'string'},
                    {name:'warehouseId',type:'int'},
                    {name:'warehouseName',type:'string'}];
        /*网格的数据源*/
    	var vGridAllocaDel= new Ext.data.JsonStore({
    		url:'alt_getStockInfoDel.do?type=pd',
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfiled
    	});
        
    	/*网格面板*/
    	var vGridPaneldel1= new Ext.ux.GridPanel({
    		height:180,
    		width:568,
    		region:'center',
    		store:vGridAllocaDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'仓库名称',width:100,dataIndex:'warehouseName'},
            	          {header:'商品名称',width:120,dataIndex:'commodityName'},
            	          {header:'商品规格',width:80,dataIndex:'commodityGg'},
            	          {header:'批次',width:80,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:80,dataIndex:'vcSn'},
            	          {header:'当前库存数',width:80,dataIndex:'dlQty'},
            	          {header:'单位',width:80,dataIndex:'vcDw'},
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'warehouseId',hidden:true,dataIndex:'warehouseId'}]
            }),
            tbar:[{
            	xtype:'textfield',
            	id:'keyWord1',
            	width:160,
            	maxLength:100,
            	emptyText:'名称/编号/厂家编码/备注',
            	listeners:{
            		specialKey:function(field, e){
            			if (e.getKey() == e.ENTER){
            				vGridAllocaDel.load({params:{
                				key:Ext.getCmp('keyWord1').getValue()}});
            			}
            		}
            	}
            },'-',{
                	text:'查询',
                	iconCls:'btn-list',
                	handler: function(){
            			vGridAllocaDel.load({params:{
            				key:Ext.getCmp('keyWord1').getValue()}});
                	}
            }],
            listeners: {
                "rowclick": function (grid, rowIndex, event) {
            			var row = grid.getSelectionModel().getSelections();
                        /*赋值控件*/
            			vNo.setValue(row[0].data.commodityNo);
            			vName.setValue(row[0].data.commodityName);
            			vBatch.setValue(row[0].data.vcBatch);
            			vSn.setValue(row[0].data.vcSn);
            			vWareHouseId.setValue(row[0].data.warehouseId);
            			vDw.setValue(row[0].data.vcDw);
            			vCommodityId.setValue(row[0].data.commodityId);
            			vdlBefore.setValue(row[0].data.dlQty);
            			vGg.setValue(row[0].data.commodityGg);
            			vId.setValue(row[0].data.id);
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
                items: [vNo,vName,vdlBefore,vdlActual]
            },{
                columnWidth: 0.5,
                items: [vSn,vDw,vBatch,vDw,vWareHouseId,vGg]
            }]
        });
        
        var addForm1 = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            height:370,
            defaults: { frame: false, border: false },
            items:[vGridPaneldel1,vPanelForm1]
        });

        /*添加*/
        var addWindow1 = new Ext.ux.Window({
    		title : '库存记录',
    		closeAction:'close',
    		width:600,
    		height:375,
    		collapsible : true,
    		items : [addForm1],
    		buttons : [{
    			id:'btnSave1',
    			text : '确定',
    			handler : function() {
    				if (addForm1.getForm().isValid()) {
    					var row = vGridPaneldel1.getSelectionModel().getSelections();
    					if(row.length==0){
    						Ext.Msg.alert('信息提示','请选择商品信息');
    						return;
    					}
    					var gridRowRecord=new Ext.data.Record({
    						commodityNo:vNo.getValue(),
    						commodityName:vName.getValue(),
    						commodityGg:vGg.getValue(),
    						vcBatch:vBatch.getValue(),
    						vcSn:vSn.getValue(),
    						dlBefore:vdlBefore.getValue(),
    						dlActual:vdlActual.getValue(),
    						id:'0',
    						inventoryId:'0',
    						stockId:vId.getValue(),
    						commodityId:vCommodityId.getValue(),
    						warehouseId:vWareHouseId.getValue()
    					});
    					
    					/**添加行记录**/
    					Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
    					addWindow1.close();
    				}
    			}
    		}, {
    			text : '取消',
    			handler : function() {
    				addWindow1.close();
    			}
    		},{
    			text:'保存并新增',
    			handler:function(){
    			if (addForm1.getForm().isValid()) {
						var row = vGridPaneldel1.getSelectionModel().getSelections();
						if(row.length==0){
							Ext.Msg.alert('信息提示','请选择商品信息');
							return;
						}
						var gridRowRecord=new Ext.data.Record({
							commodityNo:vNo.getValue(),
							commodityName:vName.getValue(),
							commodityGg:vGg.getValue(),
							vcBatch:vBatch.getValue(),
							vcSn:vSn.getValue(),
							dlBefore:vdlBefore.getValue(),
							dlActual:vdlActual.getValue(),
							id:'0',
							inventoryId:'0',
							stockId:vId.getValue(),
							commodityId:vCommodityId.getValue(),
							warehouseId:vWareHouseId.getValue()
						});
						
						/**添加行记录**/
						Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
						//addWindow1.close();
						vdlActual.setValue(0);
					}
    			}
    		}]
    	});
        addWindow1.show();
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
				vJson+="'inventoryId':'0',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'warehouseId':'"+gs.getAt(i).data["warehouseId"]+"',";
				vJson+="'dlBefore':'"+gs.getAt(i).data["dlBefore"]+"',";
				vJson+="'dlActual':'"+gs.getAt(i).data["dlActual"]+"',";
				vJson+="'stockId':'"+gs.getAt(i).data["stockId"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"'";
				vJson+="}";
			}else{
				vJson+="{";
				vJson+="'commodityNo':'"+gs.getAt(i).data["commodityNo"]+"',";
				vJson+="'commodityName':'"+gs.getAt(i).data["commodityName"]+"',";
				vJson+="'commodityGg':'"+gs.getAt(i).data["commodityGg"]+"',";
				vJson+="'vcBatch':'"+gs.getAt(i).data["vcBatch"]+"',";
				vJson+="'vcSn':'"+gs.getAt(i).data["vcSn"]+"',";
				vJson+="'inventoryId':'0',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'warehouseId':'"+gs.getAt(i).data["warehouseId"]+"',";
				vJson+="'dlBefore':'"+gs.getAt(i).data["dlBefore"]+"',";
				vJson+="'dlActual':'"+gs.getAt(i).data["dlActual"]+"',";
				vJson+="'stockId':'"+gs.getAt(i).data["stockId"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"'";
				vJson+="}";
			}
		}
		vJson+=']';
		return vJson;
    }
    
	/*查询网格*/
	function funcinitgrid(){
		vGridStore.setBaseParam('warehouseId',vOutCombo1.getValue());
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
			title:'库存盘点',
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