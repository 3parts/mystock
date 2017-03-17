/**
 * 企业管理
 */
Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
	var vTaFields=[{name:'id',type:'int'},
	               {name:'vcNumber',type:'string'},
	               {name:'vcName',type:'string'},
	               {name:'dtBegeindate',type:'date',mapping : 'dtBegeindate.time', dateFormat : 'time'},
	               {name:'dtEnddate',type:'date',mapping : 'dtEnddate.time', dateFormat : 'time'},
	               {name:'VState',type:'string'},
	               {name:'vcCity',type:'string'},
	               {name:'vcAddress',type:'string'},
	               {name:'vcContact',type:'string'},
	               {name:'vcTel',type:'string'},
	               {name:'vcMobile',type:'string'},
	               {name:'vcFax',type:'string'},
	               {name:'vcQq',type:'string'},
	               {name:'vcEmail',type:'string'},
	               {name:'vcWeixin',type:'string'},
	               {name:'vcAccountnum',type:'string'},
	               {name:'vcAccountname',type:'string'},
	               {name:'vcBank',type:'string'},
	               {name:'vcLogo',type:'string'},
	               {name:'vcRemark',type:'string'},
	               {name:'vcWeb',type:'string'},
	               {name:'fwx',type:'string'},
	               {name:'printInfo1',type:'string'},
	               {name:'printInfo2',type:'string'},
	               {name:'printInfo3',type:'string'}];
	
	/*企业数据源*/
	var vConpanyStore= new Ext.data.JsonStore({
		url:'qy_findPageInfo.do',
		root:'root',
		totalProperty: 'total',
	    autoLoad: {params:{start:0, limit:15}},
	    fields: vTaFields
	});
	
	/*网格面板*/
	var vGridPanel= new Ext.ux.GridPanel({
		store:vConpanyStore,
		title:'企业管理',
        iconCls:'menu-51',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'conter'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'状态',width:80,dataIndex:'VState',renderer:function(a){return a=="1"?"启用":"禁用";}},
        	          {header:'企业编号',width:100,dataIndex: 'vcNumber'},
        	          {header:'企业名称',width:180,dataIndex:'vcName'},
        	          {header:'开户日期',width:100,renderer : Ext.util.Format.dateRenderer('Y-m-d'),dataIndex:'dtBegeindate'},
        	          {header:'到期日期',width:100,renderer : Ext.util.Format.dateRenderer('Y-m-d'),dataIndex:'dtEnddate'},
        	          {header:'城市',width:100,dataIndex:'vcCity'},
        	          {header:'详细地址',width:160,dataIndex:'vcAddress'},
        	          {header:'联系人',width:80,dataIndex:'vcContact'},
        	          {header:'联系电话',width:100,dataIndex:'vcTel'},
        	          {header:'联系手机',width:100,dataIndex:'vcMobile'},
        	          {header:'传真',width:100,dataIndex:'vcFax'},
        	          {header:'QQ',width:100,dataIndex:'vcQq'},
        	          {header:'微信',width:100,dataIndex:'fwx'},
        	          {header:'邮箱',width:100,dataIndex:'vcEmail'},
        	          {header:'银行账号',width:150,dataIndex:'vcAccountnum'},
        	          {header:'银行户名',width:100,dataIndex:'vcAccountname'},
        	          {header:'开户行',width:150,dataIndex:'vcBank'},
        	          {header:'Logo地址',width:300,dataIndex:'vcLogo'},
        	          {header:'企业网址',width:150,dataIndex:'vcWeb'},
        	          {header:'备注',width:200,dataIndex:'vcRemark'},
        	          {header:'ID',hidden:true,width:60,dataIndex:'id'}]
        }),
        tbar:[{
        	text:'添加',
        	iconCls:'btn-add',
        	hidden:true,
        	handler: function(){
        		funcEdit("add"); 
        	}
        },'-',{
        	text:'修改',
        	iconCls:'btn-edit',
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected(); 
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要修改的企业');
				}else{
					funcEdit("edit",record); 
				}
        	}
        },'-',{
        	text:'删除',
        	iconCls:'btn-remove',
        	hidden:true,
        	handler: function(){
        		var record= vGridPanel.getSelectionModel().getSelected();
				if(!record){
                	Ext.Msg.alert('信息提示','请选择要删除的企业');  
				}else{
					if(record.get("VState")=='1'){
						Ext.Msg.alert('信息提示','启用的企业不允许删除!');
						return;
					}
					Ext.MessageBox.confirm('删除提示', '是否要删除该企业？', function(c) {
					   if(c=='yes'){
						   Ext.Ajax.request({
					   			url : "qy_deleteQyInfo.do",
					   			params:{ id : record.get("id") },
					   			success : function() {
					   				vConpanyStore.reload();
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
        	emptyText:'请输入关键字...'
        },'-',{
            	text:'查询',
            	iconCls:'btn-list',
            	handler: function(){
        			vConpanyStore.load({params:{start:0, limit:15,key:Ext.getCmp('keyWord').getValue()}});
            	}
        }],
        
        bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: vConpanyStore,
            displayInfo: true
        })
	});
	/*企业表单控件*/
	function funcEdit(type,gridrow){
		var vid=new Ext.form.Hidden({name:'id'});
		var vNumber= new Ext.form.TextField({name:'vcNumber',fieldLabel:'企业编号',width:180,maxLength:25,allowBlank: false});
		var vName= new Ext.form.TextField({name:'vcName',fieldLabel:'企业名称',width:180,maxLength:25,allowBlank:false});
		var dtBegeinDate= new Ext.form.DateField({name:'dtBegeindate',fieldLabel:'开户日期',format:'Y-m-d',readOnly:true,width:180,allowBlank:false});
		var dtEnddate= new Ext.form.DateField({name:'dtEnddate',fieldLabel:'到期日期',format:'Y-m-d',readOnly:true,width:180,allowBlank:false});
		var vcAddress=new Ext.form.TextField({name:'vcAddress',maxLength:25,fieldLabel:'详细地址',width:470});
		var vcContact=new Ext.form.TextField({name:'vcContact',maxLength:10,fieldLabel:'联系人',width:180});
		var vcTel=new Ext.form.TextField({name:'vcTel',maxLength:25,fieldLabel:'联系电话',width:180});
		var vcMobile=new Ext.form.TextField({name:'vcMobile',maxLength:25,fieldLabel:'联系手机',width:180});
		var vcFax=new Ext.form.TextField({name:'vcFax',maxLength:25,fieldLabel:'传真',width:180});
		var vcQq=new Ext.form.TextField({name:'vcQq',maxLength:25,fieldLabel:'QQ',width:180});
		var vcwx=new Ext.form.TextField({name:'fwx',maxLength:25,fieldLabel:'微信',width:180});
		var vcEmail=new Ext.form.TextField({name:'vcEmail',maxLength:25,fieldLabel:'邮箱',width:180});
		var vcWeixin=new Ext.form.TextField({name:'vcWeixin',maxLength:25,fieldLabel:'微信',width:180});
		var vcAccountnum=new Ext.form.TextField({name:'vcAccountnum',maxLength:25,fieldLabel:'银行账户',width:180});
		var vcAccountname=new Ext.form.TextField({name:'vcAccountname',maxLength:25,fieldLabel:'银行户名',width:180});
		var vcBank=new Ext.form.TextField({name:'vcBank',maxLength:25,fieldLabel:'开户行',width:180});
		var vcLogo=new Ext.form.TextField({name:'vcLogo',maxLength:100,fieldLabel:'URL',width:470});
		var vcWeb=new Ext.form.TextField({name:'vcWeb',maxLength:25,fieldLabel:'公司网址',width:470});
		var vcRemark=new Ext.form.TextField({name:'vcRemark',maxLength:50,fieldLabel:'备注',width:470});
		var printInfo1=new Ext.form.TextField({name:'printInfo1',maxLength:100,fieldLabel:'附加资料1',width:470});
		var printInfo2=new Ext.form.TextField({name:'printInfo2',maxLength:100,fieldLabel:'附加资料2',width:470});
		var printInfo3=new Ext.form.TextField({name:'printInfo3',maxLength:100,fieldLabel:'附加资料3',width:470});
		
		var vChenge=new Ext.form.Hidden({id:'chenged',listeners:{
			disable:function(s){
			Ext.Msg.alert('信息提示','上传成功！');
			Ext.getCmp('upbutton').setDisabled(true);
			vcLogo.setValue(vChenge.getValue());/*赋值控件*/
			Ext.getCmp('showimg').update('<img width="150" height="80" src="'+vChenge.getValue()+'" />');
			}
		}});
		
		var vUploadbutton=new Ext.Button({
			id:'upbutton',
			text:'上传',
			width:80,
			height:25,
			handler:function(){
				Ext.ux.UpLoadWidow('qy_upLoadImg.do?vcLogo='+vcLogo.getValue(),'企业Log图片上传','chenged',true);
			}
		});
		/*显示图片的面板*/
		var vShowImg=new Ext.Panel({
			id:'showimg',
			width:180,
			fieldLabel:'图片',
			height:80,
			border:true,
			margins:'5 5 5 5',
			html:'<img width="150" height="80" src="" />'
		});
		
		var storeArea=new Ext.data.JsonStore({
        	fields: ['id', 'vcName'],
            url : "kh_getCityData.do",
            autoLoad: {},
            root : "root"
        });
		/*城市下拉*/
        var vDizCombo= new Ext.form.ComboBox({
        	name:'vcCity',
        	fieldLabel :'城市',
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
        var data=[['1','启用'],['0','禁用']];
        var store = new Ext.data.SimpleStore({
        	fields: ['value', 'text'],
        	data : data
        });
        
        /*状态*/
        var vStateCombo= new Ext.form.ComboBox({
        	name:'VState',
        	readOnly:true,
        	fieldLabel :'状态',
        	store: store,
        	valueField : 'value',
            displayField:'text',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            selectOnFocus:true,
            value:1,
            maxLength:10,
            hiddenName:'VState' /*设定了 跟name值一样的话 就会提交value值*/
        });
        
        /*企业表单界面 布局*/
        var vPanelForm=new Ext.Panel({
        	frame: true,
            border: false,
            labelAlign: "right",
            layout: "column",
            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
            items: [{
                columnWidth: 0.5,
                items: [vid,vNumber,vName,vDizCombo]
            },
            {
                columnWidth: 0.5,
                items: [dtBegeinDate,dtEnddate,vStateCombo]
            },
            {
                columnWidth: 1, items: [vcAddress]
            },
            {
                columnWidth: 0.5,
                items: [vcContact,vcTel,vcMobile,vcFax,vcQq]
            },
            {
                columnWidth: 0.5,
                items: [vcEmail,vcAccountname,vcAccountnum,vcBank,vcwx]
            },
            {
            	columnWidth:0.5,items:[vShowImg]
            },
            {
            	columnWidth:0.5,items:[vUploadbutton]
            },
            {
                columnWidth: 1, items: [vcLogo,vcWeb,vcRemark,printInfo1,printInfo2,printInfo3]
            }]
        });
        
        var addForm = new Ext.FormPanel({
        	autoScroll:true,
        	height:400,
        	frame: true,
            border: false,
            layout: "column",
            defaults: { frame: false, border: false },
            fileUpload: false,
            items:[vPanelForm]
        });
        
        /*添加*/
        var addWindow = new Ext.ux.Window({
    		title : '企业编辑',
    		closeAction:'close',
    		width:610,
    		height:485,
    		collapsible : true,
    		items : [addForm],
    		buttons : [{
    			text : '保存',
    			handler : function() {
    				if (addForm.getForm().isValid()) {
    					addForm.getForm().submit({
    						url : 'qy_saveQydata.do',
    						success : function(form, action) {
    							Ext.Msg.alert('信息提示',action.result.message);
    							addWindow.close();
    							vConpanyStore.reload();
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
        	if(gridrow!=null){
        		addForm.getForm().loadRecord(gridrow);
        		Ext.getCmp('showimg').update('<img width="150" height="80" src="'+vcLogo.getValue()+'" />');
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
			items:vGridPanel
		}]
	});
	
	
});