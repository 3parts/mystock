/**
 * 主页
 * */ 
Ext.onReady(function () {
	var vCenterHeight=400; /*当前可见body的高度*/
	/***********
	 * 布局【上】
	 ***********/
	var vNorth=new Ext.Panel({
		cls: 'app-header',
        height: 70,
        html: '<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">'+
		  '<tr><td width="254px" style="background:url(../../img/top_left.png)">&nbsp;</td>'+
		  '<td style="background:url(../../img/top_center.jpg)">&nbsp;</td>'+
		  '<td width="544px" style="background:url(../../img/top_right.jpg)"><div style="position:relative;z-index:99; float:right;margin-top:30px;margin-right:20px;"><a href="../../login.jsp">安全退出</a></div><div style="position:relative; float:right;z-index:99;margin-top:30px;margin-right:20px; ">帮助</div><div style="position:relative; float:right;z-index:99;margin-top:30px;margin-right:20px;">'+log_companyname+'</div><div style="position:relative; float:right;z-index:99;margin-top:30px;margin-right:20px;">欢迎您！'+log_name+'</div></td>'+			  '</tr>'+
		'</table>',
        margins: '5 5 5 5'
	});
	/************
	 * 布局【下】
	 * *********/
	var vSouth=new Ext.Panel({
		tbar : [{
					text : '退出系统',
					iconCls : 'btn-logout',
					handler : function() {
						App.Logout();
					}
				},'-',{
					tag:'div',
					html:" 欢迎您！"+log_name
				},'->','-', {
					xtype : 'combo',
					mode : 'local',
					editable : false,
					value : '切换皮肤',
					width : 100,
					triggerAction : 'all',
					store : [['ext-all', '默认风格'], 
							['xtheme-gray', '银白风格'],
							['xtheme-purple', '紫色风格'], 
							['xtheme-olive', '绿色风格'],
							['xtheme-darkgray', '灰色风格']],
					listeners : {
						scope : this,
						select : function(d, b, c) {
							if (d.value != "") {
								var xthemem = "../../ext/resources/css/"+ d.value + ".css";
								App.setCookie("mystock-xtheme",xthemem,7);
								Ext.util.CSS.swapStyleSheet("theme", xthemem);
							}
						}
					}
				},'->','-',{
					tag:'div',
					id:'clock',
					html:''
				}]
	});
	
	/************
	 * 布局【中】
	 ************/
	var vCenter=new Ext.TabPanel({
		id:'mainTab',
		minTabWidth: 120,
		enableTabScroll: true,/*TAB标签超宽时自动出现滚动按钮*/
		activeTab:0,
		plugins: new Ext.ux.TabCloseMenu(), /*加入右键菜单*/
		defaults:{autoScroll:true},
		items:[{id:'yth',iconCls:'menu-1',title:'云通货',html:'<iframe src="../../web/yuntonghuo.html" width="100%" height="100%" scrolling="auto" frameborder="0" ></iframe>'}],
		listeners:{
			afterlayout:function(){
				/**设置布局(解决自动适应屏幕的问题)**/
				var item = vCenter.getActiveTab();
				if(!item)return;
				item.setHeight(vCenterHeight);
			},
			bodyresize:function(a,w,h){
				vCenterHeight=h;
			}
		}
	});
	
	
	var vbtn=new Ext.Button({
		text:'添加',
		handler:function(){
		
			
		}
	});
	
	/**
	 * 打开新的tab页
	 * */
	window.addNewTabPanel=function(url,title,id,cls){
		/*查看是否有权限进行操作*/
		Ext.Ajax.request({
			url:'qy_getOpenUrl.do',
			params:{openurl:url},
			success:function(res){
				if(res.responseText=='ok'){ /*有权限打开*/
					
					/*赋值当前tab页的ID*/
					window.soTabId = id;
					
					var loadMarsk = new Ext.LoadMask(Ext.getBody(),{  
				        msg:'请稍后...', 
				        removeMask:true// 完成后移除  
				     });
					loadMarsk.show();
					Ext.ux.OpenTabPanelUrl(url,title,id,cls);
					setTimeout(function(){loadMarsk.hide();},500);
				}else{
					Ext.Msg.alert('信息提示','您没有权限进行此操作');
				}
			},
			failure:function(){
				Ext.Msg.alert('信息提示','服务器异常，请稍后重试');
			}
		});
	}
	
	/**
	 * 记录当前所述的tab页
	 * */
	window.soTabId = 0;
	
	/**
	 * 项目代码
	 * */
	window.projectCode = 'xzd';
	
	/********************
	 * 加载组织架构权限
	 ********************/
	Ext.Ajax.request({
		url:'ur_setUserUnitRight.do'
	});

	/************************
	 *******主体布局*********
	 ***********************/
	new Ext.Viewport({
		title: "Viewport",
		layout: "border",
		defaults: {bodyStyle: "background-color: #FFFFFF;",frame: true},
		items: [{region:"west",width:208,split:true,title:'功能导航',collapsible:true,html:'<iframe src="../../web/index.html" width="100%" height="100%" scrolling="auto" frameborder="0" ></iframe>'},
		        {region:"north",height:80,items:[vNorth]},
		        {region:"center",layout:"fit",split:true,items:[vCenter]},
		        {region:"south",height:38,items:[vSouth]}
		        ]
		});
	
	/***************
	 * 添加时钟******
	 * ***************/
	var clock = new Clock();
	clock.display(document.getElementById("clock"));
});