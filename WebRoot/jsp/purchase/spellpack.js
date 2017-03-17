/*
 * 拼包
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vfield=[{name:'id',type:'int'},
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
	            {name:'userName',type:'string'},
	            {name:'vcPayMan',type:'string'}];
	
	/*网格的数据源*/
	var vGridStore= new Ext.data.JsonStore({
		url:'spk_getInfo.do',
		root:'root',
		totalProperty: 'total',
	    //autoLoad: {params:{start:0, limit:20}},
	    fields: vfield
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		region:'center',
		store:vGridStore,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'销售单',width:100,dataIndex:'salenNo'},
        	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'客户',width:100,dataIndex:'khName'},
        	          {header:'拼包人',width:140,dataIndex:'vcSpllName'},
        	          {header:'金额',width:100,dataIndex:'dlMoney'},
        	          {header:'结算状态',width:100,dataIndex:'ipayState',renderer:function(a){return a=='1'?'已结算':'未结算'}},
        	          {header:'结算人',width:100,dataIndex:'vcPayMan'},
        	          {header:'结算日期',width:120,dataIndex:'dtPay',renderer:function(a){if(a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
        	          {header:'备注',width:200,dataIndex:'vcRemark'},
        	          {header:'制单人',width:100,dataIndex:'userName'},
        	          {header:'id',hidden:true,dataIndex:'id'},
        	          {header:'salenId',hidden:true,dataIndex:'salenId'},
        	          {header:'khId',hidden:true,dataIndex:'khId'},
        	          {header:'userId',hidden:true,dataIndex:'userId'}]
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
	
	/*单据状态*/
    var vTypeCombo= new Ext.form.ComboBox({
    	width:80,
    	fieldLabel :'单据类型',
    	store: new Ext.data.SimpleStore({
	        		fields: ['value', 'text'],
	        		data:[['-1','全部'],['0','未结算'],['1','已结算']]
    		   }),
    	valueField : 'value',
        displayField:'text',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        value:-1,
        editable:false
    });
	
	/*工具栏按钮*/
	var vToobar=new Ext.Toolbar({
	    items: [{
        	text:'添加',
        	iconCls:'btn-add',
        	handler: function(){
        		funcEdit("add"); 
        	}
        },'-'/*,{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的单据');
				}else{
					funcEdit("edit",record); 
				}
        	}
        },'-'*/,{
        	text:'结算/反结算',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected(); 
        		if(!record){
                	Ext.Msg.alert('信息提示','请选择要操作的单据');  
				}else{
					Ext.MessageBox.confirm('信息提示', '是否要'+(record.get('ipayState')=='1'?'反结算':'结算')+'该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "spk_saveStateInfo.do",
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
					if(record.get('ipayState')=='1'){
						Ext.Msg.alert('信息提示','已结算的单据不允许删除');
						return;
					}
					Ext.MessageBox.confirm('删除提示', '是否要删除该单据吗？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "spk_deleteInfo.do",
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
        },'-','日期范围：',vStartDate,'到',vEndDate,'-','状态：',vTypeCombo,'-',{
        	xtype:'textfield',
        	id:'keyWord',
        	maxLength:100,
        	emptyText:'客户/拼包人/金额',
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
	    		//vGridStore.load({params:{start:0,limit:20,key:Ext.getCmp('keyWord').getValue()}});
        		funcinitgrid();
        	}
	    }]
	});
	
	/**装载工具栏和查询条件区域**/
	var vPanel= new Ext.form.FormPanel({
		region: 'north',
		layout : "form",
		height: 28,
		items:[vToobar]
	});
	
	/**
	 * 编辑
	 * */
 	function funcEdit(type,row){
 		var vId=new Ext.form.Hidden({name:'id'});
 		var vNo=new Ext.form.TextField({id:'vcNo',name:'vcNo',readOnly:true,style:"background:#F6F6F6",fieldLabel:'销售单号',allowBlank:false,emptyText:'请选择...',width:125});
 		var vdtBs=new Ext.form.DateField({name:'dtBs',value:new Date(),width:180,fieldLabel:'业务日期',allowBlank:false,format:'Y-m-d'});
 		var vSpellName=new Ext.form.TextField({name:'vcSpllName',width:180,fieldLabel:'拼包人',allowBlank:false,maxLength:10});
 		var vMoney=new Ext.form.NumberField({name:'dlMoney',width:180,fieldLabel:'金额',decimalPrecision:2,allowBlank:false});
 		var vRemark=new Ext.form.TextArea({name:'vcRemark',width:180,height:80,fieldLabel:'备注',maxLength:100});
 		var vSalenId=new Ext.form.Hidden({id:'salenId',name:'salenId'});
 		
        /*客户*/
        var storeKh=new Ext.data.JsonStore({
        	fields: ['value', 'text'],
            url : "kh_findKhComb.do",
            autoLoad: {params:{keyid:type=='add'?-1:row.get('khId')}},
            root : "root"
        });
        var vKhCombo= new Ext.ux.LinkComboBox({
        	id:'khId1',
        	name:'khId',
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
            hiddenName:'khId',
            emptyText:'编号/名称...回车检索'
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
                items: [{items:[
                {
					layout:'column',
					items:[{
						layout:'form',
						width : 218,
						items:[vNo]
					},{
						width : 20,
						height : 20,
						xtype:'button',
						iconCls : 'btn-list',
						handler:function(){
							funOpen();
						}
					}]
				}]},vdtBs,vSpellName,vKhCombo,vMoney,vRemark,vSalenId,vId]
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
    		title : '拼包编辑',
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
    						url : 'spk_saveInfo.do',
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
        		vNo.setValue(row.get('salenNo'));
        		vdtBs.setValue(new Date(parseInt(row.get("dtBs"))));
        		storeKh.on('load',function(){
        			vKhCombo.setValue(row.get("khId"));
        		});
        	}
        }
 		
 	}
	/**
	 * 打开销售单
	 * */
 	function funOpen(){
 		
 		var vfields=[{name:'id',type:'int'},
 		             {name:'vcNo',type:'string'},
 		             {name:'dtBs',type:'string'},
 		             {name:'khName',type:'string'},
 		             {name:'vcAddress',type:'string'},
 		             {name:'vcRemark',type:'string'},
 		             {name:'iKh',type:'int'}];
 		
 		var vGridSalenDel= new Ext.data.JsonStore({
    		url:'salenout_getSalenDelInfo.do?strType=pb',
    		root:'root',
    		totalProperty: 'total',
    	    autoLoad: {},
    	    fields: vfields
    	});
 		
 		/*网格面板*/
    	var vGridPaneldel= new Ext.ux.GridPanel({
    		id:'gridPanel1',
    		height:220,
    		width:568,
    		region:'center',
    		store:vGridSalenDel,
            iconCls:'',
            cm: new Ext.grid.ColumnModel({
            	defaults: {sortable: true,align:'conter'},
            	columns:[ new Ext.grid.RowNumberer(),
            	          {header:'销售编号',width:100,dataIndex:'vcNo'},
            	          {header:'业务日期',width:100,dataIndex:'dtBs',renderer:function(a){if(a=='null'||a.length==0)return ""; else return (new Date(parseInt(a))).format('Y-m-d'); }},
            	          {header:'客户',width:120,dtaIndex:'khName'},
            	          {header:'地址',width:180,dataIndex:'vcAddress'},
            	          {header:'备注',width:180,dataIndex:'vcRemark'},
            	          {header:'id',hidden:true,dataIndex:'id'},
            	          {header:'iKh',hidden:true,dataIndex:'iKh'}]
            }),
            tbar:[{
            	xtype:'textfield',
            	id:'keyWord1',
            	maxLength:100,
            	emptyText:'销售单号/备注/客户',
            	listeners:{
        		specialKey:function(field, e){
        			if (e.getKey() == e.ENTER){
        				vGridSalenDel.load({params:{start:0,limit:20,key:Ext.getCmp('keyWord1').getValue()}});
        			}
        		}
        	}},'-',{
            	text:'查询',
            	iconCls:'btn-list',
            	handler: function(){
            		vGridSalenDel.load({params:{start:0,limit:20,key:Ext.getCmp('keyWord1').getValue()}});
            	}
    	    }]
    	});
 		
        var addForm = new Ext.form.FormPanel({
        	id:'gridSpxx',
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            items:[vGridPaneldel]
        });
        
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '销售单选择',
    		closeAction:'close',
    		height:320,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			id:'btnSave1',
    			text : '确定',
    			handler : function() {
	    			var record= vGridPaneldel.getSelectionModel().getSelected(); 
					if(!record){
	                	Ext.Msg.alert('信息提示','请选择销售单据');
					}else{
						/**给控件赋值**/
						Ext.getCmp('khId1').setValue(record.get('iKh'));
						Ext.getCmp('vcNo').setValue(record.get('vcNo'));
						Ext.getCmp('salenId').setValue(record.get('id'));
						addWindow.close();
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
		vGridStore.setBaseParam('ipayState',vTypeCombo.getValue());
		vGridStore.setBaseParam('dts',vStartDate.getValue());
		vGridStore.setBaseParam('dte',vEndDate.getValue());
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
			title:'拼包登记',
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