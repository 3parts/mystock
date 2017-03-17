/**
 * 调拨
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var fiedls=[{name:'id',type:'int'},
	            {name:'vcNo',type:'string'},
	            {name:'outId',type:'int'},
	            {name:'inId',type:'int'},
	            {name:'dtBs',type:'string'},
	            {name:'dlCount',type:'string'},
	            {name:'dlMoney',type:'string'},
	            {name:'istate',type:'string'},
	            {nae:'userId',type:'int'},
	            {name:'vcRemark',type:'string'},
	            {name:'outName',type:'string'},
	            {name:'inName',type:'string'},
	            {name:'userName',type:'string'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'alt_getInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: fiedls
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
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a==null||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'调出仓库',width:120,dataIndex:'outName'},
        	          {header:'调入仓库',width:100,dataIndex:'inName'},
        	          {header:'数量',width:100,dataIndex:'dlCount'},
        	          //{header:'金额',width:120,dataIndex:'dlMoney'},
        	          {header:'备注',width:280,dataIndex:'vcRemark'},
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
        },{
        	text:'删除',
        	hidden:true,
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的单据');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否要删除该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "alt_deleteInfo.do",
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
    	emptyText:'单据编号/仓库/制单人',
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
        		//vGridStore.load({params:{start:0, limit:20,key:Ext.getCmp('keyWord').getValue()}});
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
		var vDtBs=new Ext.form.DateField({name:'dtBs',fieldLabel:'业务日期',width:180,value:new Date(),format:'Y-m-d',allowBlank:false});
		var vdlCount=new Ext.form.NumberField({name:'dlCount',fieldLabel:'数量',width:180,allowBlank:false,readOnly:true,style:'background:#F6F6F6',value:0});
		var vRemark=new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:470,maxLength:100});
		/**调出仓库**/
        var storeOut=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ck_getCkInfo.do",
            autoLoad: {},
            root : "root"
        });
        var vOutCombo= new Ext.form.ComboBox({
        	name:'outId',
        	fieldLabel :'调出仓库',
        	store: storeOut,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            allowBlank:false,
            hiddenName:'outId'
        });
        
        /**调入仓库**/
        var storeIn=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ck_getCkInfo.do",
            autoLoad: {},
            root : "root"
        });
        var vInCombo= new Ext.form.ComboBox({
        	name:'inId',
        	fieldLabel :'调入仓库',
        	store: storeIn,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            allowBlank:false,
            hiddenName:'inId'
        });
        /***明细网格***/
        var vfield=[{name:'id',type:'int'},
                    {name:'allocationId',type:'int'},
                    {name:'commodityId',type:'int'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'dlCount',type:'string'},
                    {name:'vcDw',type:'string'},
                    {name:'dlMoney',type:'string'},
                    {name:'outId',type:'int'},
                    {name:'inId',type:'int'},
                    {name:'vcRemark',type:'string'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'outName',type:'string'},
                    {name:'inName',type:'string'},
                    {name:'comstockId',type:'int'},
                    {name:'gostockId',type:'int'}];
       
        /*网格的数据源*/
    	var vGridStoreDel= new Ext.data.JsonStore({
    		url:'alt_getInfoDel.do?id='+(row==null?0:row.get("id")),
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfield
    	});
    	/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		id:'gridPanel1',
    		title:'库存明细',
    		height:210,
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
            	          {header:'调出仓库',width:120,dataIndex:'outName'},
            	          {header:'调入仓库',width:120,dataIndex:'inName'},
            	          {header:'数量',width:100,dataIndex:'dlCount'},
            	          {header:'单位',width:100,dataIndex:'vcDw'},
            	          //{header:'金额',width:120,dataIndex:'dlMoney'},
            	          {header:'备注',width:180,dataIndex:'vcRemark'},
            	          {header:'allocationId',hidden:true,dataIndex:'allocationId'},
            	          {header:'commodityId',hidden:true,dataIndex:'commodityId'},
            	          {header:'outId',hidden:true,dataIndex:'outId'},
            	          {header:'inId',hidden:true,dataIndex:'inId'},
            	          {header:'comstockId',hidden:true,dataIndex:'comstockId'},
            	          {header:'gostockId',hidden:true,dataIndex:'gostockId'}]
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
								   funcSumCount();
								   return;
							   }
							   /**做物理删除 同时修改库存记录**/
							   Ext.Ajax.request({
						   			url : "alt_deleteInfoDel.do",
						   			params:{ idDel : record.get("id") },
						   			success : function() {
						   				vGridStoreDel.remove(record);
						   				funcSumCount();
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
                items: [vId,vNo,vDtBs,vdlCount]
            },{
                columnWidth: 0.5,
                items: [vOutCombo,vInCombo]
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
    		title : '调拨编辑',
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
    					if(vOutCombo.getValue()==vInCombo.getValue()){
    						Ext.Msg.alert("信息提示","同一仓库的商品不须调拨");
    						return;
    					}
    					var vJsons=funcConvertGrid(); /*得到网格的json字符串*/
    					if(vJsons.length<5){
    						Ext.Msg.alert('信息提示','请添加库存信息');
    						return;
    					}
    					addForm.getForm().submit({
    						url : 'alt_saveInfo.do', 
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
        	addForm.getForm().reset()
        }else{
        	if(row!=null){
        		addForm.getForm().loadRecord(row);
        		/*赋值*/
        		vDtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeOut.on('load',function(){
        			vOutCombo.setValue(row.get("outId"));
        		});
        		storeIn.on('load',function(){
        			vInCombo.setValue(row.get("inId"));
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
	function funcEditDel(){
		var vNo=new Ext.form.TextField({name:'vcNo',fieldLabel:'商品编号',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vName=new Ext.form.TextField({name:'vcName',fieldLabel:'商品名称',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vBatch=new Ext.form.TextField({name:'vcBatch',fieldLabel:'批次',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vSn=new Ext.form.TextField({name:'vcSn',fieldLabel:'辅助标识',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vGg=new Ext.form.TextField({name:'vcGg',fieldLabel:'规格',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vWareHouseName=new Ext.form.TextField({name:'vcWareHouseName',fieldLabel:'当前仓库',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vWareHouseId=new Ext.form.Hidden({name:'warehouseId'});
		var vCommodityId=new Ext.form.Hidden({name:'commodityId'});
		var vComstockId=new Ext.form.Hidden({name:'comstockId'});
		var vDw=new Ext.form.TextField({name:'vcDw',fieldLabel:'单位',width:180,readOnly:true,style:'background:#F6F6F6'});
		var vCount=new Ext.form.NumberField({name:'dlCount',fieldLabel:'调出数量',width:180,allowBlank:false,readOnly:true,style:'background:#F6F6F6'});
		var vRemark=new Ext.form.TextField({name:'vcRemark',fieldLabel:'备注',width:470});
		
		/**调入仓库**/
        var storeIn1=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "ck_getCkInfo.do",
            autoLoad: {},
            root : "root"
        });
        var vInCombo1= new Ext.form.ComboBox({
        	name:'inId',
        	fieldLabel :'调入仓库',
        	store: storeIn1,
        	valueField : 'id',
            displayField:'vcName',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            allowBlank:false,
            hiddenName:'inId'
        });
        
        /**库存网格**/
        var vfiled=[{name:'id',type:'int'},
                    {name:'commodityNo',type:'string'},
                    {name:'commodityId',type:'int'},
                    {name:'commodityName',type:'string'},
                    {name:'commodityGg',type:'string'},
                    {name:'vcBatch',type:'string'},
                    {name:'vcSn',type:'string'},
                    {name:'vcDw',type:'string'},
                    {name:'warehouseId',type:'int'},
                    {name:'warehouseName',type:'string'},
                    {name:'dlQty',type:'string'}];
        /*网格的数据源*/
    	var vGridAllocaDel= new Ext.data.JsonStore({
    		url:'alt_getStockInfoDel.do',
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
            	          {header:'商品编号',width:100,dataIndex:'commodityNo'},
            	          {header:'商品名称',width:120,dataIndex:'commodityName'},
            	          {header:'商品规格',width:100,dataIndex:'commodityGg'},
            	          {header:'批次',width:100,dataIndex:'vcBatch'},
            	          {header:'辅助标识',width:100,dataIndex:'vcSn'},
            	          {header:'所在仓库',width:80,dataIndex:'warehouseName'},
            	          {header:'单位',width:100,dataIndex:'vcDw'},
            	          {header:'当前库存数',width:100,dataIndex:'dlQty'},
            	          {header:'id',hidden:true,dataIndex:'id'},
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
            		specialKey:function(field, e){
            			if (e.getKey() == e.ENTER){
            				vGridAllocaDel.load({params:{key:Ext.getCmp('keyWord1').getValue()}});
            			}
            		}
            	}
            },'-',{
                	text:'查询',
                	iconCls:'btn-list',
                	handler: function(){
            			vGridAllocaDel.load({params:{key:Ext.getCmp('keyWord1').getValue()}});
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
            			vGg.setValue(row[0].data.commodityGg);
            			vWareHouseName.setValue(row[0].data.warehouseName);
            			vWareHouseId.setValue(row[0].data.warehouseId);
            			vDw.setValue(row[0].data.vcDw);
            			vCommodityId.setValue(row[0].data.commodityId);
            			vCount.setValue(row[0].data.dlQty);
            			vComstockId.setValue(row[0].data.id);
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
                items: [vNo,vName,vBatch,vDw,vWareHouseId,vCount,vInCombo1]
            },{
                columnWidth: 0.5,
                items: [vSn,vGg,vWareHouseName,vDw,vComstockId]
            },{
            	columnWidth:1,
            	items:[vRemark]
            }]
        });
        
        var addForm1 = new Ext.form.FormPanel({
        	frame: true,
            border: false,
            layout: "column",
            height:450,
            defaults: { frame: false, border: false },
            items:[vGridPaneldel1,vPanelForm1]
        });

        /*添加*/
        var addWindow1 = new Ext.ux.Window({
    		title : '选择进行调入的库存商品',
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
    					var row = vGridPaneldel1.getSelectionModel().getSelections();
    					if(row.length==0){
    						Ext.Msg.alert('信息提示','请选择商品信息');
    						return;
    					}
    					if(vCount.getValue() > row[0].data.dlQty){
    						Ext.Msg.alert('信息提示','调出数量不能大于库存数量');
    						return;
    					}
    					if(vWareHouseId.getValue()==vInCombo1.getValue()){
    						Ext.Msg.alert('信息提示','调入仓库不能同当前仓库一样');
    						return;
    					}
    					var gridRowRecord=new Ext.data.Record({
    						commodityNo:vNo.getValue(),
    						commodityName:vName.getValue(),
    						commodityGg:vGg.getValue(),
    						vcBatch:vBatch.getValue(),
    						vcSn:vSn.getValue(),
    						outName:vWareHouseName.getValue(),
    						outId:vWareHouseId.getValue(),
    						inName:vInCombo1.getRawValue(),
    						inId:vInCombo1.getValue(),
    						dlCount:vCount.getValue(),
    						vcDw:vDw.getValue(),
    						id:'0',
    						allocationId:'0',
    						gostockId:'0',
    						commodityId:vCommodityId.getValue(),
    						vcRemark:vRemark.getValue(),
    						comstockId:vComstockId.getValue()
    					});
    					
    					/**添加行记录**/
    					Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
    					funcSumCount();
    					addWindow1.close();
    						
    				}
    			}
    		}, {
    			text : '取消',
    			handler : function() {
    				addWindow1.close();
    			}
    		},{
    			text:'保存并新增 ',
    			handler:function(){
	    			if (addForm1.getForm().isValid()) {
						var row = vGridPaneldel1.getSelectionModel().getSelections();
						if(row.length==0){
							Ext.Msg.alert('信息提示','请选择商品信息');
							return;
						}
						if(vCount.getValue() > row[0].data.dlQty){
							Ext.Msg.alert('信息提示','调出数量不能大于库存数量');
							return;
						}
						if(vWareHouseId.getValue()==vInCombo1.getValue()){
							Ext.Msg.alert('信息提示','调入仓库不能同当前仓库一样');
							return;
						}
						var gridRowRecord=new Ext.data.Record({
							commodityNo:vNo.getValue(),
							commodityName:vName.getValue(),
							commodityGg:vGg.getValue(),
							vcBatch:vBatch.getValue(),
							vcSn:vSn.getValue(),
							outName:vWareHouseName.getValue(),
							outId:vWareHouseId.getValue(),
							inName:vInCombo1.getRawValue(),
							inId:vInCombo1.getValue(),
							dlCount:vCount.getValue(),
							vcDw:vDw.getValue(),
							id:'0',
							allocationId:'0',
							gostockId:'0',
							commodityId:vCommodityId.getValue(),
							vcRemark:vRemark.getValue(),
							comstockId:vComstockId.getValue()
						});
						
						/**添加行记录**/
						Ext.getCmp('gridPanel1').getStore().add(gridRowRecord);
						funcSumCount();
						Ext.Msg.alert('信息提示','新增成功!');
						//addWindow1.close();
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
				vJson+="'outName':'"+gs.getAt(i).data["outName"]+"',";
				vJson+="'outId':'"+gs.getAt(i).data["outId"]+"',";
				vJson+="'inName':'"+gs.getAt(i).data["inName"]+"',";
				vJson+="'inId':'"+gs.getAt(i).data["inId"]+"',";
				vJson+="'vcDw':'"+gs.getAt(i).data["vcDw"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"',";
				vJson+="'vcRemark':'"+gs.getAt(i).data["vcRemark"]+"',";
				vJson+="'dlCount':'"+gs.getAt(i).data["dlCount"]+"',";
				vJson+="'dlMoney':'0',";
				vJson+="'comstockId':'"+gs.getAt(i).data['comstockId']+"',";
				vJson+="'commodityId':'"+gs.getAt(i).data['commodityId']+"',";
				vJson+="'gostockId':'"+gs.getAt(i).data["gostockId"]+"',";
				vJson+="'allocationId':'"+gs.getAt(i).data["allocationId"]+"'";
				vJson+="}";
			}else{
				vJson+="{";
				vJson+="'commodityNo':'"+gs.getAt(i).data["commodityNo"]+"',";
				vJson+="'commodityName':'"+gs.getAt(i).data["commodityName"]+"',";
				vJson+="'commodityGg':'"+gs.getAt(i).data["commodityGg"]+"',";
				vJson+="'vcBatch':'"+gs.getAt(i).data["vcBatch"]+"',";
				vJson+="'vcSn':'"+gs.getAt(i).data["vcSn"]+"',";
				vJson+="'outName':'"+gs.getAt(i).data["outName"]+"',";
				vJson+="'outId':'"+gs.getAt(i).data["outId"]+"',";
				vJson+="'inName':'"+gs.getAt(i).data["inName"]+"',";
				vJson+="'inId':'"+gs.getAt(i).data["inId"]+"',";
				vJson+="'vcDw':'"+gs.getAt(i).data["vcDw"]+"',";
				vJson+="'id':'"+gs.getAt(i).data["id"]+"',";
				vJson+="'vcRemark':'"+gs.getAt(i).data["vcRemark"]+"',";
				vJson+="'dlCount':'"+gs.getAt(i).data["dlCount"]+"',";
				vJson+="'dlMoney':'0',";
				vJson+="'comstockId':'"+gs.getAt(i).data['comstockId']+"',";
				vJson+="'commodityId':'"+gs.getAt(i).data["commodityId"]+"',";
				vJson+="'gostockId':'"+gs.getAt(i).data["gostockId"]+"',";
				vJson+="'allocationId':'"+gs.getAt(i).data["allocationId"]+"'";
				vJson+="}";
			}
		}
		vJson+=']';
		return vJson;
    }
    
	/**
	 * 合计数量
	 * */
	function funcSumCount(){
		var gs = Ext.getCmp('gridPanel1').getStore();
		var iSum=0;
		for(var i=0;i<gs.getCount();i++){
			iSum +=parseFloat(gs.getAt(i).data['dlCount']);
		}
		var panel = Ext.getCmp('gridSpxx').getForm();
		panel.findField('dlCount').setValue(iSum);
	}
	
	/*查询网格*/
	function funcinitgrid(){
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
			title:'调拨管理',
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