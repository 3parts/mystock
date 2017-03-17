/**
 * 客户管理
 * */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var KhObj = [
		{ name:'khid', type:'int'},
		{ name:'khnum',type:'string'},
		{ name:'khname', type:'string'},
		{ name:'lxren', type:'string'},
		{ name:'lxtel', type:'string'},
		{ name:'country',type:'string'},
		{ name:'address',type:'string'},
		{ name:'bz', type:'string'},
		{ name:'country',type:'string'},
		{ name:'province',type:'string'},
		{ name:'city',type:'string'},
		{ name:'distinct',type:'string'},
		{ name:'mobile',type:'string'},
		{ name:'fax',type:'string'},
		{ name:'qq',type:'string'},
		{ name:'email',type:'string'},
		{ name:'shipping_address',type:'string'},
		{ name:'shipping_type',type:'string'},
		{ name:'credit',type:'int'},
		{name:'deOwe',type:'string'},
		{name:'shippintClear',type:'string'},
		{name:'shippingLog',type:'string'}
	];
	
	//客户数据
	var store = new Ext.data.JsonStore({
	    url: 'kh_findPageKh.do',
	    root: 'root',
	    totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:20}},
	    fields: KhObj
	});
	
	//客户列表
    var grid = new Ext.ux.GridPanel({
        store: store,
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true },
			columns:[new Ext.grid.RowNumberer(),
			    {header: '客户编号',width:100,align:'center',dataIndex:'khnum'},
			    {header: '客户名称', width: 150,align:'center', dataIndex: 'khname'},
	            {header: '联系人', width: 80,align:'center', dataIndex: 'lxren'},
	            {header: '联系电话', width: 100, align:'center',dataIndex: 'lxtel'},
	            {header: '省份',width:80,align:'center',dataIndex:'province'},
	            {header: '城市',width:80,align:'center',dataIndex:'city'},
	            {header: '区/县',width:80,align:'center',dataIndex:'distinct'},
	            {header: '详细地址', width: 150, align:'center',dataIndex: 'address'},
	            {header: '联系手机',width:120,align:'center',dataIndex:'mobile'},
	            {header: '传真',width:100,align:'center',dataIndex:'fax'},
	            {header: 'QQ',width:80,align:'center',dataIndex:'qq'},
	            {header: '邮箱',width:120,align:'center',dataIndex:'email'},
	            {header: '送货地址',width:200,align:'center',dataIndex:'shipping_address'},
	            {header: '配送方式',width:100,align:'center',dataIndex:'shipping_type'},
	            {header:'结算方式',width:100,align:'center',dataIndex:'shippintClear'},
	            {header:'物流公司',width:100,align:'center',dataIndex:'shippingLog'},
	            {header: '信用度',width:60,align:'center',dataIndex:'credit'},
	            {header: '备注', width:200,align:'center',dataIndex: 'bz'},
	            {header:'上欠',dataIndex:'deOwe'},
	            {header: 'ID',hidden:true,width:50,dataIndex:'khid'}]
        }),
        title:'客户管理',
        iconCls:'menu-51',
        tbar:[{
        	text:'增加',
        	iconCls:'btn-add',
        	handler: function(){
        		funcEdit("add");
        		//addWindow.show();
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的客户');
				}else{
					funcEdit("edit",record);
					//addForm.getForm().loadRecord(record);
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	handler: function(){
        		var record= grid.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的客户');  
				}else{
					Ext.MessageBox.confirm('删除提示', '是否删除该客户？', function(c) {
					   if(c=='yes'){
					   		Ext.Ajax.request({
					   			url : "kh_deleteKh.do",
					   			params:{ khid : record.get("khid") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
					    }
					});
				}
        	}
        },'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	width:220,
        	maxLength:100,
        	emptyText:'名称/编号/联系人/联系电话/手机/传真',
        	listeners:{
        		specialKey:function(field, e){
        			if (e.getKey() == e.ENTER){
        				store.setBaseParam('key',Ext.getCmp('keyWord').getValue());
            			store.load({
            				params:{start:0, limit:20}
            			});
        			}
        		}
        	}
        },'-',{
            	text:'查询',
            	iconCls:'btn-list',
            	handler: function(){
        			store.setBaseParam('key',Ext.getCmp('keyWord').getValue());
        			store.load({
        				params:{start:0, limit:20}
        			});
            	}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: store,
            displayInfo: true
        })
    });
    function funcEdit(type,gridrow){
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
    		height:400,
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
    							store.reload();
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

});