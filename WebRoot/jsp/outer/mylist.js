/**
 * 功能：企业列表
 * 作者：RC
 * 日期：2015-07-25
 */
Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var store = new Ext.data.JsonStore({
		url:'outer_getInfo.do',
		root:'root',
		autoLoad: {params:{start:0, limit:20}},
		totalProperty: 'total',
		fields:[{name:'fid',type:'int'},
		        {name:'fname',type:'string'},
		        {name:'fopendate',type:'string'},
		        {name:'ftodate',type:'string'},
		        {name:'fcity',type:'string'},
		        {name:'faddress',type:'string'},
		        {name:'fman',type:'string'},
		        {name:'ftel',type:'string'},
		        {name:'fqq',type:'string'},
		        {name:'femail',type:'string'},
		        {name:'fwx',type:'string'},
		        {name:'fbankname',type:'string'},
		        {name:'fbanknum',type:'string'},
		        {name:'fbank',type:'string'},
		        {name:'fweb',type:'string'},
		        {name:'fremark',type:'string'},
		        {name:'fwrite',type:'string'},
		        {name:'ftable',type:'string'},
		        {name:'fstatus',type:'int'}]
	});
	
	/*网格面板*/
	var grid= new Ext.ux.GridPanel({
		region:'center',
		title:'企业申请列表',
		store:store,
        iconCls:'',
        cm: new Ext.grid.ColumnModel({
        	defaults: {sortable: true,align:'center'},
        	columns:[ new Ext.grid.RowNumberer(),
        	          {header:'状态',width:80,dataIndex:'fstatus',renderer:function(a){
        				if(a=='-1'){ 
        					return "<span style='color:red'>未通过</span>"
        				}else if(a==-2){
        					return "<span style='color:red'>已禁用</span>"
        				}else if(a=='0'){
        					return "待审核"
        				}else {
        					return "<span style='color:green'>已通过</span>";
        				}}},
        	          {header:'企业名称',width:120,dataIndex:'fname'},
        	          {header:'开户日期',width:100,dataIndex:'fopendate'},
        	          {header:'到期日期',width:100,dataIndex:'ftodate'},
        	          {header:'城市',width:80,dataIndex:'fcity'},
        	          {header:'详细地址',width:180,dataIndex:'faddress'},
        	          {header:'联系人',width:80,dataIndex:'fman'},
        	          {header:'联系手机',width:100,dataIndex:'ftel'},
        	          {header:'qq',width:100,dataIndex:'fqq'},
        	          {header:'邮箱',width:100,dataIndex:'femail'},
        	          {header:'微信',width:100,dataIndex:'fwx'},
        	          {header:'银行户名',width:100,dataIndex:'fbankname'},
        	          {header:'银行账号',width:100,dataIndex:'fbanknum'},
        	          {header:'开户行',width:100,dataIndex:'fbank'},
        	          {header:'公司网址',width:100,dataIndex:'fweb'},
        	          {header:'备注',width:100,dataIndex:'fremark'},
        	          {header:'申请日期',width:100,dataIndex:'fwrite'},
        	          {header:'企业数据库',width:100,align:'left',dataIndex:'ftable'}]
        }),
        tbar:[{
        	text:'审核通过',
        	iconCls:'btn-ok',
        	handler:function(){
        		var row= grid.getSelectionModel().getSelected(); 
        		if(!row){
        			Ext.Msg.alert('信息提示','您没有选择任何记录');
        		}else{
        			if(row.get('fstatus')!='0'){
        				Ext.Msg.alert('信息提示','请选择状态为待审核的的记录');
        				return;
        			}
        			var fid=new Ext.form.Hidden({name:'fid',value:row.get('fid')});
        			var ftable=new Ext.form.TextField({name:'ftable',regex:/^[a-z]+$/,regexText:'数据库名称只能为小写字母',width:180,fieldLabel:'企业数据库',allowBlank:false});
        			/*表单界面 布局*/
    		        var vPanelForm=new Ext.Panel({
    		        	frame: true,
    		            border: false,
    		            labelAlign: "right",
    		            layout: "column",
    		            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
    		            items: [{
    		                columnWidth: 1,
    		                items: [ftable,fid]
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
    		    		title : '审核通过',
    		    		closeAction:'close',
    		    		height:120,
    		    		width:320,
    		    		collapsible : true,
    		    		items : [addForm],
    		    		buttons : [{
    		    			text : '确定',
    		    			handler : function() {
    		    				if (addForm.getForm().isValid()) {
    		    					Ext.MessageBox.confirm("确认提示","确认为【"+row.get('fname')+"】创建名为【"+ftable.getValue()+"】的数据库吗？初始账号/密码：admin/admin",function(c){
    		    						if(c!='yes')return;
    		    						addForm.getForm().submit({
        		    						url : 'outer_saveCheck.do',
        		    						success : function(form, action) {
        		    							Ext.Msg.alert('信息提示',action.result.message);
        		    							if(action.result.message!='数据库已存在'){
        		    								addWindow.close();
        		    								store.reload();
        		    							}
        		    						},
        		    						failure : function(form, action) {
        		    							if(action.result.errors){
        		    								Ext.Msg.alert('信息提示',action.result.errors);
        		    							}else{
        		    								Ext.Msg.alert('信息提示',action.result.message);
        		    							}
        		    						},
        		    						waitTitle : '提交',
        		    						waitMsg : '正在初始化数据库,,请稍后...'
        		    					});
    		    						
    		    						
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
        	text:'审核不通过',
        	iconCls:'btn-remove',
        	handler:function(){
        	var row= grid.getSelectionModel().getSelected(); 
    		if(!row){
    			Ext.Msg.alert('信息提示','您没有选择任何记录');
    		}else{
    			if(row.get('fstatus')!='0'){
    				Ext.Msg.alert('信息提示','请选择状态为待审核的的记录');
    				return;
    			}
    			Ext.MessageBox.confirm("确认提示","确认审核不通过吗？",function(c){
    				if(c=='yes'){
    					Ext.Ajax.request({
				   			url : "outer_saveNoCheck.do",
				   			params:{ fid : row.get("fid") },
				   			success : function() {
				   				store.reload();
				   			}
				   		});
    				}
    			});
    		}}
        },'-',{
        	text:'禁用',
        	iconCls:'btn-red',
        	handler:function(){
	        	var row= grid.getSelectionModel().getSelected(); 
	    		if(!row){
	    			Ext.Msg.alert('信息提示','您没有选择任何记录');
	    		}else{
	    			if(row.get('fstatus')!='1'){
	    				Ext.Msg.alert('信息提示','请选择状态为 已审核 的的记录');
	    				return;
	    			}
	    			Ext.MessageBox.confirm("确认提示","您即将禁用企业,禁用后企业帐套将无法登陆系统,确认这样操作吗？",function(c){
	    				if(c=='yes'){
	    					Ext.Ajax.request({
					   			url : "outer_saveDisable.do",
					   			params:{ fid : row.get("fid") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
	    				}
	    			});
	    		}
        	}
        },'-',{
        	text:'启用',
        	iconCls:'btn-ok',
        	handler:function(){
	        	var row= grid.getSelectionModel().getSelected(); 
	    		if(!row){
	    			Ext.Msg.alert('信息提示','您没有选择任何记录');
	    		}else{
	    			if(row.get('fstatus')!='-2'){
	    				Ext.Msg.alert('信息提示','请选择状态为 已禁用 的的记录');
	    				return;
	    			}
	    			Ext.MessageBox.confirm("确认提示","您即将启用企业,启用后企业帐套将恢复登录,确认这样操作吗？",function(c){
	    				if(c=='yes'){
	    					Ext.Ajax.request({
					   			url : "outer_saveEnabled.do",
					   			params:{ fid : row.get("fid") },
					   			success : function() {
					   				store.reload();
					   			}
					   		});
	    				}
	    			});
	    		}
        	}
        },'-',{
        	text:'延期',
        	iconCls:'btn-moneyout',
        	handler:function(){
        		var row= grid.getSelectionModel().getSelected();
        		if(!row){
        			Ext.Msg.alert('信息提示','您没有选择任何记录');
        		}else{
        			if(row.get('fstatus')!='1'){
	    				Ext.Msg.alert('信息提示','请选择状态为 启用  的的记录');
	    				return;
	    			}
        			var fid=new Ext.form.Hidden({name:'fid',value:row.get('fid')});
        			var ftodate=new Ext.form.DateField({name:'ftodate',fieldLabel:'延期至',format:'Y-m-d',value:new Date().add(Date.YEAR,+1),width:180,allowBlank:false});
        			
        			/*表单界面 布局*/
    		        var vPanelForm=new Ext.Panel({
    		        	frame: true,
    		            border: false,
    		            labelAlign: "right",
    		            layout: "column",
    		            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
    		            items: [{
    		                columnWidth: 1,
    		                items: [ftodate,fid]
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
    		    		title : '企业帐套延期',
    		    		closeAction:'close',
    		    		height:120,
    		    		width:320,
    		    		collapsible : true,
    		    		items : [addForm],
    		    		buttons : [{
    		    			text : '确定',
    		    			handler : function() {
    		    				if (addForm.getForm().isValid()) {
    		    					Ext.MessageBox.confirm("确认提示","确认为【"+row.get('fname')+"】企业的到期日期延期至："+new Date(ftodate.getValue()).format('Y-m-d'),function(c){
    		    						if(c!='yes')return;
    		    						addForm.getForm().submit({
        		    						url : 'outer_saveDate.do',
        		    						success : function(form, action) {
    		    								addWindow.close();
        		    							Ext.Msg.alert('信息提示','操作成功!');
        		    							store.reload();
        		    						},
        		    						failure : function(form, action) {
        		    							if(action.result.errors){
        		    								Ext.Msg.alert('信息提示',action.result.errors);
        		    							}else{
        		    								Ext.Msg.alert('信息提示',action.result.message);
        		    							}
        		    						},
        		    						waitTitle : '提交',
        		    						waitMsg : '正在操作,请稍后...'
        		    					});
    		    						
    		    						
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
        	text:'删除',
        	iconCls:'btn-delete',
        	handler:function(){
	        	var row= grid.getSelectionModel().getSelected(); 
	    		if(!row){
	    			Ext.Msg.alert('信息提示','您没有选择任何记录');
	    		}else{
	    			if(row.get('fstatus')!='-2' && row.get('fstatus')!='-1'){
	    				Ext.Msg.alert('信息提示','请选择状态为 禁用  的的记录');
	    				return;
	    			}
	    			Ext.MessageBox.confirm("确认提示","您将删除企业,删除后企业数据将无法恢复,确认这样操作吗？",function(c){
	    				if(c=='yes'){
	    					Ext.MessageBox.confirm("删除提示","即将删除企业数据,是否继续？",function(a){
	    						if(a=='yes'){
	    							Ext.Ajax.request({
	    					   			url : "outer_saveDelete.do",
	    					   			params:{ fid : row.get("fid") },
	    					   			success : function() {
	    					   				store.reload();
	    					   			}
	    					   		});
	    						}
	    					});
	    				}
	    			});
	    		}
        }
        },'-',{
        	text:'刷新',
        	iconCls:'btn-refresh',
        	handler:function(){
        		store.reload();
        	}
        },'->',{
        	text:'修改密码',
        	iconCls:'btn-edit',
        	handler:function(){
        		/**修改管理员密码**/
        		var ps=new Ext.form.TextField({name:'ps',inputType: 'password',width:180,fieldLabel:'原密码',allowBlank:false});
        		var ps1=new Ext.form.TextField({name:'ps1',inputType: 'password',width:180,fieldLabel:'新密码',allowBlank:false});
        		var ps2=new Ext.form.TextField({name:'ps2',inputType: 'password',width:180,fieldLabel:'确认密码',allowBlank:false});
        		
        		/*表单界面 布局*/
		        var vPanelForm=new Ext.Panel({
		        	frame: true,
		            border: false,
		            labelAlign: "right",
		            layout: "column",
		            defaults: { xtype: 'panel', layout: "form", labelWidth: 70, labelAlign: "right", frame: false, border: false },
		            items: [{
		                columnWidth: 1,
		                items: [ps,ps1,ps2]
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
		    		title : '修改管理员admin的密码',
		    		closeAction:'close',
		    		height:220,
		    		width:320,
		    		collapsible : true,
		    		items : [addForm],
		    		buttons : [{
		    			text : '保存',
		    			handler : function() {
		    				if (addForm.getForm().isValid()) {
		    					if(ps1.getValue()!=ps2.getValue()){
		    						Ext.Msg.alert('信息提示',"您两次填写的密码不一致");
		    						return;
		    					}
		    					addForm.getForm().submit({
		    						url : 'outer_savePsd.do',
		    						success : function(form, action) {
		    							Ext.Msg.alert('信息提示',action.result.message);
		    							if(action.result.message=='修改成功'){
		    								addWindow.close();
		    							}
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
        },'-',{
        	text:'退出',
        	iconCls:'btn-close',
        	handler:function(){
        		window.location.href = "../../login.jsp";
        	}
        }],
        bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: store,
            displayInfo: true
        })
	});
	
	
	
	/***********
	 * 布局【上】
	 ***********/
	var vNorth=new Ext.Panel({
		cls: 'app-header',
        height: 70,
        html: '<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">'+
		  '<tr><td width="254px" style="background:url(../../img/top_left.png)">&nbsp;</td>'+
		  '<td style="background:url(../../img/top_center.jpg)">&nbsp;</td>'+
		  '<td width="544px" style="background:url(../../img/top_right.jpg)"></td>'+'</tr>'+
		'</table>',
        margins: '5 5 5 5'
	});
	
	
	/************************
	 *******主体布局*********
	 ***********************/
	new Ext.Viewport({
		title: "Viewport",
		layout: "border",
		defaults: {bodyStyle: "background-color: #FFFFFF;",frame: true},
		items: [{region:"north",height:80,items:[vNorth]},
		        {region:"center",layout:"fit",items:[grid]}]
		});
});