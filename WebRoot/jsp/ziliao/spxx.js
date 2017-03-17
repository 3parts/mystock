/*!
 * 商品管理
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
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
		{ name:'vcRemark', type:'string'},
		{ name:'dlqty', type:'string'}
		
	];
	
	//商品数据
	var store = new Ext.data.JsonStore({
	    url: 'spxx_findPageSpxx.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:v_start, limit:v_limit}},
	    fields: SpxxObj
	    //listeners:{beforeload:function(a){a.baseParams={start:v_start, limit:v_limit};}}
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
	            {header: '当前库存', width: 80, align:'right',sortable:true,dataIndex:'dlqty',renderer:function(a){return Ext.ux.ConvertFloat(a,2);}},
	            {header: '颜色', width: 60, sortable:true,dataIndex: 'vcColor'},
	            {header: '规格', width: 60, sortable:true,dataIndex: 'vcGg'},
	            {header: '建议售价', width: 100, sortable:true, dataIndex: 'dbSuggMoney'},
	            {header: '最低售价', width: 100, sortable:true, dataIndex: 'dbLowMoney'},
	            {header: '平均成本', width: 100, sortable:true, dataIndex: 'dbAverageMoney'},
	            {header: '最后一次进价', width: 100, sortable:true, dataIndex: 'dbLastMoney'},
	            {header: '备注', width: 150, sortable:true, dataIndex: 'vcRemark'},
	            {header: 'ID',hidden:true, width: 80, sortable:true, dataIndex: 'id'}]
        }),
		region:'center',
		title:'商品信息',
        iconCls:'menu-53',
        tbar:[{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		addWindow.show();
        		addForm.getForm().reset();
        		//addForm.getForm().findField("addupdate").setValue("add");
        		if(v_lbid!="0"){
					addForm.getForm().findField("typeId").setValue(v_lbid);
					addForm.getForm().findField("typeName").setValue(v_lbname);
				}
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的商品');
				}else{
	        		addWindow.show();
					addForm.getForm().loadRecord(record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的商品');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该商品？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "spxx_deleteSpxx.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				//store.reload();
					   				funcinitgrid();
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:160,
        	maxLength:100,
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
        			//store.load({params:{start:v_start, limit:v_limit,lbid:v_lbid,search:Ext.getCmp('keyWord').getValue()}});
        			funcinitgrid();
        		}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: store,
            displayInfo: true
        }),
        
        listeners:{
        	rowdblclick:function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(record){
	        		addWindow.show();
					addForm.getForm().loadRecord(record);
				}
        	}
        }
    });
    
    //商品单位下拉数据
    var spdwStore = new Ext.data.JsonStore({
	    /*xtype:'jsonstore',
		url: 'spdw_findAllSpdw.do',
	    root: 'root',
	    totalProperty: 'total',
	    fields: [{name:'dwid',type:'int'},'dwname'],
		autoLoad:true*/
    	xtype:'jsonstore',
		url: 'ut_getInfo.do?type=jldw',
	    root: 'root',
	    totalProperty: 'total',
	    fields: [{name:'id',type:'int'},'vcName'],
		autoLoad:true
    	
	});
	
	//商品表单
    var addForm = new Ext.FormPanel({
		layout : 'form',
		frame:true,
		labelWidth:60,
		border : false,
		padding : '15 10 0 8',
		defaults : {
			anchor : '100%'
		},
		items:[{
			layout:'column',
			items:[{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					layout:'column',
					items:[{
						layout:'form',
						width : 218,
						items:[{
							id:'lbtext',
							width : 145,
							xtype : 'textfield',
							name:'typeName',
							fieldLabel:'所属系列',
							allowBlank : false,
							maxLength :50,
							enableKeyEvents:true,
							listeners:{
								focus:function(){
									splbTreeWindow.show();
								},
								blur:function(){
									addForm.getForm().clearInvalid();
								}
							}
						}]
					},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							splbTreeWindow.show();
						}
					}]
				},{
					xtype : 'textfield',
					name:'vcName',
					fieldLabel:'商品名称',
					allowBlank : false,
					maxLength :20
				},{
					xtype : 'textfield',
					name:'vcGg',
					fieldLabel:'规格',
					maxLength :20
				},{
					xtype : 'textfield',
					name:'vcColor',
					fieldLabel:'颜色',
					maxLength :20
				},{
					xtype : 'numberfield',
					name:'dbSuggMoney',
					fieldLabel:'建议售价',
					maxLength :10
				},{
					xtype : 'numberfield',
					name:'dbAverageMoney',
					fieldLabel:'平均成本',
					maxLength :10,
					disabled:true
				}]
			},{
				columnWidth:.5,
				layout:'form',
				defaults:{
					anchor : '95%'
				},
				items:[{
					xtype : 'textfield',
					name:'vcNo',
					fieldLabel:'商品编码',
					//allowBlank : false,
					emptyText:'不填写将自动生成',
					maxLength :10
				},{
					/*layout:'column',
					items:[{
						layout:'form',
						width : 218,
						items:[{
							width : 145,
							xtype:'combo',
							name:'vcDw',
							fieldLabel:'单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位',
							mode: 'local',
							triggerAction: 'all',
							valueField :'dwid',
							displayField: 'dwname',
							allowBlank : false,
							editable : false,
							store : spdwStore
						}]
					},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							SpdwWindow.show();
						}
					}]*/
					/*计量单位*/
					//width : 145,
					xtype:'combo',
					name:'vcDw',
					fieldLabel:'单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位',
					mode: 'local',
					triggerAction: 'all',
					valueField :'id',
					value:1,
					displayField: 'vcName',
					allowBlank : false,
					editable : false,
					store : spdwStore
				},{
					xtype : 'textfield',
					name:'vcFactoryNo',
					fieldLabel:'厂家编码',
					maxLength :25
				},{
					xtype : 'textfield',
					name:'vcFactoryName',
					fieldLabel:'生产厂商',
					maxLength :50
				},{
					xtype : 'numberfield',
					name:'dbLowMoney',
					fieldLabel:'最低售价',
					maxLength :10
				},{
					xtype : 'numberfield',
					name:'dbLastMoney',
					fieldLabel:'最后一次进价',
					maxLength :10,
					disabled:true
				}]
			}]
		},{
			xtype:'textarea',
			name:'vcRemark',
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			height:100,
			maxLength :200
		},{
			xtype : 'hidden',
		    name : 'typeId'
		},{
			xtype : 'hidden',
			name : 'id'
		}]
	});
    
	//增加商品窗口
    var addWindow = new Ext.Window({
		title : '增加商品',
		width:550,
		height:370,
		closeAction:'hide',
		modal : true,
		border:false,
		resizable : false,
		layout : 'fit',
		buttonAlign : 'center',
		items : [addForm],
		buttons : [{
			text : '新增下一商品',
			handler : function() {
				submitSpxx(true);
			}
		},{
			text : '保存',
			handler : function() {
				submitSpxx(false);
			}
		}, {
			text : '取消',
			handler : function() {
				addWindow.hide();
			}
		}]
	});
	
	var submitSpxx = function(next){
		if (addForm.getForm().isValid()) {
			addForm.getForm().submit({
				url : 'spxx_saveOrUpdateSpxx.do',
				success : function(form, action) {
					if(action.result.ok){
					    if(next){
					    	addForm.getForm().reset();
				       		addForm.getForm().findField("typeId").setValue(v_lbid);
							addForm.getForm().findField("typeName").setValue(v_lbname);
					    }else{
							Ext.Msg.alert('信息提示',action.result.message);
							addWindow.hide();
					    }
						//store.reload({params:{lbid:v_lbid}});
					    funcinitgrid();
					}else{
						Ext.Msg.alert('信息提示',action.result.message);
					}
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
        /*buttons:[{
        	text:'新增系列',
        	handler:function(){
        		splbWindow.show();
        		splbForm.getForm().reset();
        	}
        },{
        	text:'删除系列',
        	disabled:true,
        	handler:function(){
        		if(v_lbid){
	        		var node = tree.getNodeById(v_lbid);
	        		var pnode = node.parentNode;
					Ext.MessageBox.confirm('删除提示', '是否删除"'+node.text+'"系列？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "splb_deleteSplb.do",
					   			params:{ lbid : v_lbid },
					   			success : function(o) {
					   				if(o.responseText=="false"){
					   					Ext.Msg.alert("信息提示","该系列有商品信息不能删除");
					   				}else{
						   				v_lbid = "0";     //设为默认节点
						   				v_lbname = "";
						   				tree.buttons[1].setDisabled(true);  //禁用删除按钮
						   				pnode.removeChild(node);	//删除节点
						   				if(pnode.childNodes.length==0){	//如果无子节点则修改属性
						   					pnode.leaf = true;
						   				}
						   				var wtree = splbTreeWindow.get(0);
						   				wtree.getLoader().load(wtree.root); //更新类别编辑窗口的树
					   				}
					   			}
					   		});
					    }
					});
				}
        	}
        }],*/
        tbar:[{
			text:'增加',
			iconCls:'btn-add',
			handler: function(){
	        	splbWindow.show();
	    		splbForm.getForm().reset();
    		}
		},'-',{
    		text:'修改',
    		iconCls:'btn-edit',
    		handler:function(){
				if(v_lbid=='0'){
					Ext.Msg.alert('信息提示','选择的节点不能修改');
					return;
				}
			
				//商品类别表单
				var splbForm = new Ext.FormPanel({
					layout : 'form',
					baseCls: 'x-plain',
					labelWidth:60,
					border : false,
					padding : 20,
					items:[{
						xtype:'textfield',
						anchor : '100%',
						name:'lbname',
						fieldLabel:'商品系列',
						allowBlank : false,
						maxLength :20,
						value:v_lbname
					},{
						xtype : 'hidden',
					    name : 'lbid',
					    value:v_lbid
					}]
				});
			
			
				//增加商品类别窗口
			    var splbWindow1 = new Ext.Window({
					title : '修改系列',
					width:250,
					height:140,
					closeAction:'hide',
					modal : true,
					resizable : false,
					layout : 'fit',
					buttonAlign : 'center',
					items : [splbForm],
					buttons : [{
						text : '保存',
						handler : function() {
							if (splbForm.getForm().isValid()) {
								splbForm.getForm().submit({
									url : 'splb_saveOrUpdateSplb.do',
									success : function(form, action) {
										console.dir(form);
										splbWindow1.hide();
										/**刷新节点**/
										tree.root.reload();
										tree.getRootNode().expand();
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
							splbWindow1.hide();
						}
					}]
				});
			    splbWindow1.show();
			}
    	},'-',{
    		text:'删除',
    		iconCls:'btn-remove',
    		disabled:true,
    		id:'splbdelete',
    		handler:function(){
    		if(v_lbid){
	        		var node = tree.getNodeById(v_lbid);
	        		var pnode = node.parentNode;
					Ext.MessageBox.confirm('删除提示', '是否删除"'+node.text+'"系列？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "splb_deleteSplb.do",
					   			params:{ lbid : v_lbid },
					   			success : function(o) {
					   				if(o.responseText=="false"){
					   					Ext.Msg.alert("信息提示","该系列有商品信息不能删除");
					   				}else{
						   				v_lbid = "0";     //设为默认节点
						   				v_lbname = "";
						   				tree.buttons[1].setDisabled(true);  //禁用删除按钮
						   				pnode.removeChild(node);	//删除节点
						   				if(pnode.childNodes.length==0){	//如果无子节点则修改属性
						   					pnode.leaf = true;
						   				}
						   				var wtree = splbTreeWindow.get(0);
						   				wtree.getLoader().load(wtree.root); //更新类别编辑窗口的树
					   				}
					   			}
					   		});
					    }
					});
				}
    		}
    	}],
        listeners:{
        	click:function(n){
        		v_lbid = n.id;
        		v_lbname = n.text;
        		//设置删除按钮状态
        		if(!n.leaf||v_lbid=="0"){
        			//tree.tbar[2].setDisabled(true);
        			Ext.getCmp('splbdelete').setDisabled(true);
        		}else{
        			//tree.tbar[2].setDisabled(false);
        			Ext.getCmp('splbdelete').setDisabled(false);
        		}
        		
        		//更新商品数据
        		//store.load({params:{start:v_start, limit:v_limit,lbid:v_lbid}});
        		funcinitgrid();
        	}
        }
	});
	
	//展开节点
	tree.getRootNode().expand();
	
	//商品类别表单
	var splbForm = new Ext.FormPanel({
		layout : 'form',
		baseCls: 'x-plain',
		labelWidth:60,
		border : false,
		padding : 20,
		items:[{
			xtype:'textfield',
			anchor : '100%',
			name:'lbname',
			fieldLabel:'商品系列',
			allowBlank : false,
			maxLength :20
		},{
			xtype : 'hidden',
		    name : 'lbid'
		}]
	});
    
	//增加商品类别窗口
    var splbWindow = new Ext.Window({
		title : '增加系列',
		width:250,
		height:140,
		closeAction:'hide',
		modal : true,
		resizable : false,
		layout : 'fit',
		buttonAlign : 'center',
		items : [splbForm],
		buttons : [{
			text : '保存',
			handler : function() {
				if (splbForm.getForm().isValid()) {
					splbForm.getForm().submit({
						url : 'splb_saveOrUpdateSplb.do',
						params:{pid : v_lbid},
						success : function(form, action) {
							splbWindow.hide();
							/**刷新节点**/
							tree.root.reload();
							tree.getRootNode().expand();
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
				splbWindow.hide();
			}
		}]
	});
    
	//商品类别树窗口
    var splbTreeWindow = new Ext.Window({
		width:200,
		height:300,
		closeAction:'hide',
		layout : 'fit',
		buttonAlign : 'center',
		items : [{
			xtype:'treepanel',
			useArrows: true,
	        autoScroll:true,
	        enableDD:false,
	        containerScroll: true,
	        dataUrl: 'splb_findSplbTree.do',
	        root: {
	            nodeType: 'async',
	            text: '所有系列',
	            draggable: false,
	            id: '0'
	        },
	        listeners:{
	        	click:function(n){
	        		if(n.id=="0"){
	        			splbTreeWindow.buttons[0].setDisabled(true);
	        		}else{
		        		splbTreeWindow.buttons[0].setDisabled(false);
		        		v_lbid = n.id;
		        		v_lbname = n.text;
	        		}
	        	},
	        	dblclick:function(n){
	        		if(n.id=="0"){
	        			splbTreeWindow.buttons[0].setDisabled(true);
	        		}else{
		        		splbTreeWindow.buttons[0].setDisabled(false);
		        		v_lbid = n.id;
			        	v_lbname = n.text;
		        		splbTreeWindow.hide();
						addForm.getForm().findField("typeId").setValue(v_lbid);
						addForm.getForm().findField("typeName").setValue(v_lbname);
	        		}
	        	}
	        }
		}],
		listeners:{
			beforeshow:function(){
				var xy = Ext.getCmp("lbtext").getPosition();
				splbTreeWindow.setPosition(xy[0],xy[1]+25);
			},
        	show:function(){
        		this.items.get(0).getRootNode().expand();
        	}
        },
		buttons : [{
			width:60,
			text : '选择',
			disabled : true,
			handler : function() {
				splbTreeWindow.hide();
				addForm.getForm().findField("typeId").setValue(v_lbid);
				addForm.getForm().findField("typeName").setValue(v_lbname);
			}
		}, {
			width:60,
			text : '取消',
			handler : function() {
				splbTreeWindow.hide();
				v_lbid = "0";
		        v_lbname = "";
			}
		}]
	});
	
	//商品单位编辑窗口
	var SpdwWindow = new Ext.Window({
		xtype:'window',
		title:'单位',
		width:250,
		height:250,
		resizable : false,
		closeAction:'hide',
		layout:'fit',
		items:[{
			xtype:'grid',
			store:spdwStore,
			columns:[new Ext.grid.RowNumberer(),{
					header:'单位名称',
					sortable:true,
					menuDisabled : true,
					dataIndex:'dwname',
					width:190
				}],
			tbar:[{
					text:'增加',
					handler:function(){
						SpdwAddWin.show();
					}
				},'-',{
					text:'删除',
					handler:function(){
						var grid = SpdwWindow.get(0);
						var record= grid.getSelectionModel().getSelected();
						if(!record){
		                	Ext.Msg.alert('信息提示','请选择要删除的单位');  
						}else{
							Ext.MessageBox.confirm('删除提示', '是否删除该单位？', function(c) {
							   if(c=='yes'){
							   		Ext.Ajax.request({
							   			url : "spdw_deleteSpdw.do",
							   			params:{ dwid : record.get("dwid") },
							   			success : function() {
							   				grid.getStore().load();
							   			}
							   		});
							    }
							});
						}
					}
				},'-',{
					text:'确定',
					handler:function(){
						var record= SpdwWindow.get(0).getSelectionModel().getSelected();
						if(!record){
		                	Ext.Msg.alert('信息提示','请选择单位');  
						}else{
							addForm.getForm().findField("dw").setValue(record.get("dwname"));
							SpdwWindow.hide();
						}
					}
				},'-',{
					text:'取消',
					handler:function(){
						SpdwWindow.hide();
					}
				}]
		}]
	});
	
	//商品单位添加窗口
	var SpdwAddWin = new Ext.Window({
		title:'增加单位',
		width:250,
		height:140,
		resizable : false,
		closeAction:'hide',
		layout:'fit',
		fbar:[{
				text:'保存',
				handler:function(){
					var form = SpdwAddWin.get(0).getForm();
					if (form.isValid()) {
							form.submit({
								url : 'spdw_saveOrUpdateSpdw.do',
								success : function(form, action) {
									SpdwAddWin.hide();
									spdwStore.load();
								},
								failure : function(form, action) {
									if(action.result.errors){
										Ext.Msg.alert('信息提示',action.result.errors);
									}else{
										Ext.Msg.alert('信息提示','连接失败');
									}
								}
							});
						}
				}
			},{
				text:'取消',
				handler:function(){
					SpdwAddWin.hide();
				}
		}],
		items:[{
				id:'dwform',
				xtype:'form',
				baseCls: 'x-plain',
				labelWidth:60,
				padding:20,
				layout:'form',
				items:[{
						xtype:'textfield',
						name:'dwname',
						fieldLabel:'单位名称',
						allowBlank : false,
						maxLength :20,
						anchor:'100%'
				}]
		}]
	});
	
	/*查询网格*/
	function funcinitgrid(){
		store.setBaseParam('lbid',v_lbid);
		store.setBaseParam('search',Ext.getCmp('keyWord').getValue());
		store.load({params:{
			//lbid:v_lbid,
			//search:Ext.getCmp('keyWord').getValue(),
			start:v_start, 
			limit:v_limit
		}});
	}
	
	//布局
    new Ext.Viewport({
		layout:'fit',
		items:[{
			frame:true,
			layout:'border',
			items:[tree,grid]
		}]
	});

});