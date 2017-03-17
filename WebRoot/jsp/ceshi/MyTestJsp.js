/*!
 * 当前库存查询
 */
Ext.onReady(function(){

//	var uploadForm=new Ext.FormPanel({      
//		id:'uploadForm',      
//		width:520,      
//		frame:true,      
//		fileUpload: true,        
//		autoHeight:true,      
//		bodyStyle:'10px 10px 0px 10px',      
//		labelWidth:50,      
//		enctype: 'multipart/form-data',       
//		defaults:{anchor: '95%',allowBlank: false},      
//		items:[{xtype:'fileuploadfield',              
//			emptyText: '请选择上传文件...',               
//			fieldLabel: '文件：',               
//			id:'uploadFile',              
//			name: 'upload',               
//			allowBlank: false,                 
//			blankText: '文件名称不能为空.',                 
//			buttonCfg: { text: '选择...'                
//				}          
//		}],
//		buttons: [{text: '上传', 
//				handler: function(){ 
//					if(uploadForm.getForm().isValid()){                              
//						uploadForm.getForm().submit({                                  
//							url:'qy_upLoadImg.do',                                  
//							method:'POST',                                  
//							waitTitle: '请稍后',                                  
//							waitMsg: '正在上传文档文件 ...',                                  
//							success: function(fp, action){                                      
//							Ext.MessageBox.alert('信息', action.result.msg);                                        
//							Ext.getCmp("uploadFile").reset();          // 指定文件字段的id清空其内容                                     
//							addwin.hide();                                      
//							grid.store.load({params:{start : 0,limit : combo.value}});                                  
//							},                                  
//							failure: function(fp, action){                                      
//								Ext.MessageBox.alert('警告', action.result.msg);                                        
//								addwin.hide();                                  
//								}                              
//							});                          
//						}                     
//					}                  
//		},{                      
//			text: '重置',                      
//			handler: function(){                          
//				uploadForm.getForm().reset();                      
//			}                  
//		}]
//	});  
	
//	addwin = new Ext.Window({      
//		title : '上传新文档',      
//		closable : true,      
//		width : 520,      
//		autoHeight: true,      
//		border : false,      
//		plain : true,      
//		modal : true,      
//		layout : 'fit',      
//		bodyStyle : 'padding:5px;',      
//		maximizable : false,// 禁止最大化      
//		closeAction : 'hide',      
//		closable : true,// 是否有关闭     
//		collapsible : true,// 可折叠         
//		items : [uploadForm]  
//	});
//	
//	
//	alert("d");
//	
//	
//	addwin.show();
	
	//布局
    new Ext.Viewport({
		layout:'border',
		items:[{
			region:'center',
			layout:'fit',
			border:false,
			items:[uploadForm1]
		}]
	});

});