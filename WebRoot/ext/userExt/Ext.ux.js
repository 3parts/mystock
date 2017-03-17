/**
 * 基于Ext的一些扩展控件及方法
 * 2015-03-25
 */
Ext.ux = {
		/*********普通Ext Grid************/
	    GridPanel: Ext.extend(Ext.grid.GridPanel, {
	        frame: true,            /*填充*/
	        columnLines: true,      /*添加列线*/
	        stripeRows: true,       /*隔行深度显示*/
	        loadMask: { msg: "数据加载中..", removeMask: true },
	        autoScroll: true,       /*滚动条*/
	        border: false,          /*边框模式*/
	        enableColumnResize: true,
	        enableColumnMove: true, /*允许拖放列*/
	        autoHeight: false,
	        viewConfig: { columnsText: "显示/隐藏列", sortAscText: "正序排列", sortDescText: "倒序排列", forceFit: false },
	        listeners: {
	            "beforerender": function (grid) {
	        	    var reg=new RegExp(".do","g")
	        	    var vsig = this.store.url.replace(reg,'');
	        		this.gridSignId=parent.soTabId+"_"+vsig.split('?')[0]; /*设置网格的唯一ID(利用每个网格的 url)*/
	        		/*读取以前的设置*/
	        		Ext.ux.GridPanelConfig.GetColumnWidthFromCookies(this); /*加载控件时，加载以前保存的设置的列宽度信息*/
	        		
	            },
	            "columnresize": function (columnIndex, newSize) {
	            	Ext.ux.GridPanelConfig.SetColumnWidthToCookies(this, columnIndex, newSize); /*拖动列宽度的时候保存列的宽度*/
	            },
	            "columnmove":function(){
	            	//Ext.ux.GridPanelConfig.SetColumnWidthToCookies(this);
	            },
	            /**达到悬浮提示**/
	            render:function(grid){
	            	var view = grid.getView();
	            	grid.tip = new Ext.ToolTip({  
	                    target: view.mainBody,
	                    delegate: '.x-grid3-cell',
	                    trackMouse: true,
	                    renderTo: document.body,
	                    anchor: 'top',  
	                    listeners: { 
	                        beforeshow: function updateTipBody(tip) { 
	                            var rowIndex = view.findRowIndex(tip.triggerElement);  
	                            var cellIndex = view.findCellIndex(tip.triggerElement);
	                           
	                            //前三列或大于第八列内容不提示  
	                            //if(cellIndex < 3 || cellIndex >8)return false;
	                            var cell = view.getCell(rowIndex, cellIndex);  
	                            tip.body.dom.innerHTML = cell.innerHTML;  
	                        }  
	                    }  
	                }); 
	            }
	            
	        }
	    }), 
	    
	    /**GridPanel相关设置**/
	    GridPanelConfig: {
	        /*装载网格时，将有设置的列宽度设置好*/
	        GetColumnWidthFromCookies: function (gridPanel) {
	            var cm = gridPanel.colModel.columns;
	            try {
	            	var cookies = Ext.ux.getCookie(parent.projectCode);
	            	//alert(cookies);
	            	var jsoncookies=eval('('+cookies+')');
	            	
		            if(jsoncookies==null){jsoncookies={};}
	            	var v=eval("jsoncookies."+gridPanel.gridSignId);
	            	if(v==null || v==undefined || v.length==0)return;
	            	var json = eval('('+v+')');
	            	/**读取到 cookies 设置已保存的信息**/
	            	for(var i=0;i<cm.length;i++){
	            		var col = gridPanel.colModel.columns[i];
	            		if (col.dataIndex == null || col.dataIndex.length==0) { continue; }
	            		col.width=eval('json.'+col.dataIndex+'Width'); /*宽度*/
	            		col.hidden=eval('json.'+col.dataIndex+'Hidden'); /*是否隐藏*/
	            		//col.colIndex = eval('json.'+col.dataIndex+'Index'); /*列显示顺序*/
	            	}
	            } catch (e) { }
	        },
	        /*网格列宽度发生变化时，保存设置*/
	        SetColumnWidthToCookies: function (gridPanel, columnIndex, newSize) {
	        	var vjson='{';
	        	/*遍历列宽字符串*/
	        	for(var i=0;i<gridPanel.colModel.columns.length;i++){
	        		var col = gridPanel.colModel.columns[i];
	        		if (col.dataIndex == null || col.dataIndex.length==0) { continue; }
	        		if(vjson.length==1){
	        			vjson+='';
	        		}else{
	        			vjson+=',';
	        		}
	        		//vjson+=col.dataIndex+"Index:"+i+',';
	        		vjson+=col.dataIndex+'Width:'+col.width+',';
	        		vjson+=col.dataIndex+'Hidden:'+(col.hidden==undefined?false:col.hidden);
	        	}
	        	vjson+='}';
	            /*保存列宽 -- 【拼接列宽字符串】设置cookies*/
	            var cookies = Ext.ux.getCookie(parent.projectCode);
	            var jsoncookies=eval('('+cookies+')');
	            if(jsoncookies==null){jsoncookies={};}
	            var gridcookies="jsoncookies."+gridPanel.gridSignId+"='"+vjson+"'";
	            eval(gridcookies);
	            var v = Ext.encode(jsoncookies);
	            Ext.ux.setCookie(parent.projectCode,v,365);
	        }
	    },
	    
	    /*****Grid单元格图标样式和文字*************/
	    GridColumnIconTextCell: function (iconCls, text, showTitle) {
	        iconCls = iconCls || "";
	        var str_iconcell = "<table style='border-collapse:collapse;padding:-3px;margin:0px;border:none;height:13px;'>" +
	                           " <tr>" +
	                           "  <td style='width:16px;border:none;'>" +
	                           "   <div style='overflow:hidden;border:none;width:16px;height:16px;' class='" + iconCls + "'></div>" +
	                           "  </td>" +
	                           "  <td style='border:none;padding-left:3px;' nowrap " + (showTitle == true ? "title='" + text + "'" : "") + " >" + text + "</td>" +
	                           " </tr>" +
	                           "</table>";
	        return str_iconcell;
	    },
	    
	    /************树形控件的基类************/
	    TreePanel: Ext.extend(Ext.tree.TreePanel, { 
	    	autoScroll: true, 
	    	animate: true, 
	    	border: false, 
	    	lines: true, 
	    	enableDD: false, 
	    	containerScroll: true, 
	    	rootVisible: false 
	    }),
	    
	    
	    /*************窗体扩展***********/
	    Window: Ext.extend(Ext.Window, {
	        title: '无标题',
	        layout: 'fit',
	        width: 600,
	        height: 400,
	        closeAction:'hide',
	        autoHeight: false,
	        shadow: true,
	        border: true,
	        frame: true,
	        resizable: false,
	        collapsible: false,     /*允许缩放条*/
	        modal: true,            /*弹出模态窗体*/
	        allowFront: false,      /*是否允许单击将窗口置顶*/
	        closable: true,
	        collapsible : true,
	        bodyStyle: 'padding:5px 5px 5px 5px',
	        animCollapse: true,
	        buttonAlign: 'center',
	        iconCls: "windowsignicon"
	    }),
	    /****设置树子节点的选中状态********/
	    SetChildNodeChecked:function(node){
	    	var isChecked = node.attributes.checked;
	        var childCount = node.childNodes.length;
	        for (var i = 0; i < childCount; i++) {
	        	var child = node.childNodes[i];
	            var checkBox = child.getUI().checkbox;
	            child.attributes.checked = isChecked;
	            checkBox.checked = isChecked;
	            checkBox.indeterminate = false;
	        }
	    },
	    
	    
	    TreeCombo:Ext.extend(Ext.form.ComboBox, { 
	    	constructor: function (cfg) { 
	    		cfg = cfg || {}; 
	    		Ext.ux.TreeCombo.superclass.constructor.call(this, Ext.apply({ 
	    			maxHeight: 300, 
	    			editable: false, 
	    			mode: 'local', 
	    			triggerAction: 'all', 
	    			rootVisible: false, 
	    			selectMode: 'all' 
	    		}, cfg)); 
	    	}, 
	    	store: new Ext.data.SimpleStore({ 
	    		fields: [], 
	    		data: [[]] 
	    	}), 
	    	// 重写onViewClick，使展开树结点是不关闭下拉框 
	    	onViewClick: function (doFocus) { 
	    		var index = this.view.getSelectedIndexes()[0], s = this.store, r = s.getAt(index); 
	    		if (r) { 
	    			this.onSelect(r, index); 
	    		} 
	    		if (doFocus !== false) { 
	    			this.el.focus(); 
	    		} 
	    	}, 
	    	tree: null, 
	    	//隐藏值 
	    	hiddenValue: null, 
	    	getHiddenValue: function () { 
	    		return this.hiddenValue; 
	    	}, 
	    	getValue: function () { //增加适用性，与原来combo组件一样 
	    		return this.hiddenValue; 
	    	}, 
	    	setHiddenValue: function (code, dispText) { 
	    		this.setValue(code); 
	    		Ext.form.ComboBox.superclass.setValue.call(this, dispText); 
	    		this.hiddenValue = code; 
	    	}, 
	    	initComponent: function () { 
	    		var _this = this; 
	    		var tplRandomId = 'deptcombo_' + Math.floor(Math.random() * 1000) + this.tplId 
	    		this.tpl = "<div style='height:" + _this.maxHeight + "px' id='" + tplRandomId + "'></div>" 
	    		this.tree = new Ext.tree.TreePanel({ 
	    			border: false, 
	    			enableDD: false, 
	    			enableDrag: false, 
	    			rootVisible: _this.rootVisible || false, 
	    			autoScroll: true, 
	    			trackMouseOver: true, 
	    			height: _this.maxHeight, 
	    			lines: true, 
	    			singleExpand: true, 
	    			root: new Ext.tree.AsyncTreeNode({ 
	    				id: _this.rootId, 
	    				text: _this.rootText, 
	    				iconCls: 'ico-root', 
	    				expanded: true, 
	    				leaf: false, 
	    				border: false, 
	    				draggable: false, 
	    				singleClickExpand: false, 
	    				hide: true 
	    			}), 
	    			loader: new Ext.tree.TreeLoader({ 
	    				nodeParameter: 'ID', 
	    				requestMethod: 'GET', 
	    				dataUrl: _this.url 
	    			}) 
	    		}); 
	    		this.tree.on('click', function (node) { 
	    			if ((_this.selectMode == 'leaf' && node.leaf == true) || _this.selectMode == 'all') { 
	    				if (_this.fireEvent('beforeselect', _this, node)) { 
	    					_this.fireEvent('select', _this, node); 
	    				} 
	    			} 
	    		}); 
	    		this.on('select', function (obj, node) { 
	    			var dispText = node.text; 
	    			var code = node.id; 
	    			obj.setHiddenValue(code, dispText); 
	    			obj.collapse(); 
	    		}); 
	    		this.on('expand', function () { 
	    			this.tree.render(tplRandomId); 
	    		}); 
	    		Ext.ux.TreeCombo.superclass.initComponent.call(this); 
	    	} 
	    }),
	    
	    /**
	     * 上传文件通用窗口
	     * data:action 地址
	     * title:标题
	     * chenged:禁用发生的事件的控件Id(监听(listeners)文本框(Ext.form.Hidden)的[disable]事件)
	     * */
	    UpLoadWidow:function(data,title,chenged,bimage){
	    	var vtitle=title||'文件上传';
			var uploadForm=new Ext.FormPanel({
				id:'uploadForm',
				autoScroll : true,
				width:450,
				height:90,
				frame:true,
				fileUpload: true,        
				autoHeight:true,
				bodyStyle:'10px 10px 0px 10px',
				labelWidth:50,
				enctype: 'multipart/form-data',
				defaults:{allowBlank: false},
				items:[{xtype:'fileuploadfield',
					emptyText: '请选择上传文件...',
					fieldLabel: '文件：',
					id:'uploadFile',
					name: 'upload',
					allowBlank: false,
					blankText: '文件名称不能为空.',
					buttonCfg: { text: '选择...',hidden:true }
				}],
				buttons: [{text: '确定', 
						handler: function(){ 
							if(bimage){
								var vgeshi=".jpg|.gif|.jpeg|.png";
								var vupload = Ext.getCmp('uploadFile').getValue();
								if(vupload.indexOf('.jpg')<=-1 && vupload.indexOf('.gif')<=-1 && vupload.indexOf('.jpeg')<=-1 && vupload.indexOf('.png')<=-1){
									Ext.MessageBox.alert('信息','请选择系统支持的图片文件('+vgeshi+')');
									return;
								}
							}
							if(uploadForm.getForm().isValid()){
								uploadForm.getForm().submit({
									url:data,
									method:'POST',
									waitTitle: '请稍后',
									waitMsg: '正在操作 ...',
									success: function(fp, action){
										Ext.getCmp(chenged).setValue(action.result.msg);
										Ext.getCmp(chenged).setDisabled(true);
										addwin.close();
									},
									failure: function(fp, action){
										Ext.MessageBox.alert('错误', action.result.errors);
										addwin.close();
									}
									});
								}
							}
				},{
					text: '重置',
					handler: function(){
						uploadForm.getForm().reset();
					}
				}]
			});
			var addwin=new Ext.ux.Window({
				title : vtitle,
	    		closeAction:'close',
	    		width:450,
	    		height:120,
	    		collapsible : true,
	    		items : [uploadForm]
			});
			addwin.show();
	    },
	    /**
	     * 模糊查询下拉框
	     * 回车 重新从后台检索数据 参数(key)
	     * */
	    LinkComboBox:Ext.extend(Ext.form.ComboBox,{
	    	typeAhead: true,
	        mode: 'local',
	        forceSelection: true,
	        triggerAction: 'all',
	        selectOnFocus:true,
	        loadingText: 'loading...',
	    	enableKeyEvents:true,
	    	listeners:{
//	    		beforequery:function(e){
//	    			var combo = e.combo;
//	    			if(!e.forceAll){
//	    				var value = e.query;
//	    				combo.store.filterBy(function(record,id){
//	    					var text = record.get(combo.displayField);
//	    					return (text.indexOf(value)!=-1);
//	    				});
//	    				combo.expand();
//	    				return false;
//	    			}
//	    		},
	    		keydown:function(a,e){ /*回车后台加载数据*/
	    			if(e.getKey()==e.ENTER){
	    				a.store.load({params:{key:a.getRawValue()}});
	    			}
	    		}
	    	}
	    }),
	    /**
	     * 转换日期格式
	     * vdate 日期格式字符串
	     * sdf 格式：Y-m-d H:i
	     * */
	    ConvertDate:function(vdate,sdf){
	    	if(vdate==null || vdate.length==0|| vdate=='undefined'){
	    		return "";
	    	}
	    	var newsdf=sdf||'Y-m-d';
	    	if(vdate instanceof Date){
	    		return vdate.format(newsdf);
	    	}
	    	if(vdate.length==21){
	    		vdate = vdate.substring(0,19);
	    	}
	    	var str = vdate.replace(/-/g, '/');
	    	return (new Date(str)).format(newsdf);
	    },
	    /********
	     * 根据URL 在tabpanel控件上打开一个新的标签页
	     * url 打开的超链接地址(是在index.jsp页面角度)
	     * title 标题
	     * id 唯一的标识
	     * cls 图标样式
	     * */
	    OpenTabPanelUrl:function(url,title,id,cls){
	    	var vtabpanel = Ext.getCmp('mainTab');
	    	var newtab=vtabpanel.getItem(id);
	    	/*判断tab是否存在*/
	    	if(!newtab){
		    	newtab = vtabpanel.add({
		    		id:id,
		    		iconCls:cls,
		    		title:title,
		    		autoScroll:true,
		    		html:'<iframe src="'+url+'" width="100%" height="100%" scrolling="auto" frameborder="0" ></iframe>',
		    		closable:true
		    	});
	    	}
	    	vtabpanel.setActiveTab(newtab);
	    },
	    /********************	
	     * 主页TAB页右键菜单
	     *********************/
	    TabCloseMenu: function () {
	        var tabs, menu, ctxItem;
	        this.init = function (tp) { tabs = tp; tabs.on('contextmenu', onContextMenu); }
	        var onContextMenu = function (ts, item, e) {
	            if (!menu) {
	                menu = new Ext.menu.Menu([
	                         { id: tabs.id + '-close', text: '关闭当前标签', iconCls: "close-one", handler: function () { tabs.remove(ctxItem); } },
	                         { id: tabs.id + '-close-others', text: '关闭其他标签', iconCls: "close-other", handler: function () { tabs.items.each(function (item) { if (item.closable && item != ctxItem) { tabs.remove(item); } }); } },
	                         { id: tabs.id + '-close-all', text: '关闭全部标签', iconCls: "close-all", handler: function () { tabs.items.each(function (item) { if (item.closable) { tabs.remove(item); } }); } }]);
	            }
	            ctxItem = item;
	            var items = menu.items;
	            items.get(tabs.id + '-close').setDisabled(!item.closable);
	            var disableOthers = true;
	            tabs.items.each(function () { if (this != item && this.closable) { disableOthers = false; return false; } });
	            items.get(tabs.id + '-close-others').setDisabled(disableOthers);
	            var disableAll = true;
	            tabs.items.each(function () { if (this.closable) { disableAll = false; return false; } });
	            items.get(tabs.id + '-close-all').setDisabled(disableAll);
	            menu.showAt(e.getPoint());
	        }
	    },
	    /*************
	     * 转换小数
	     * val:目标值
	     * num:保留的小数位数
	     * **************/
	    ConvertFloat:function(val,num){
	    	if(val==null || val.length==0){
	    		val=0;
	    	}
	    	var fix=num||2;
	    	return parseFloat(val).toFixed(fix);
	    },
	    /*设置cookies*/
	    setCookie: function (name, value, days) {
	        var Days = (days == null ? 30 : days);
	        var exp = new Date();
	        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	    },
	    /*获取cookies*/
	    getCookie: function (name) {
	        var arr;
	        var reg = new RegExp("(^|)" + name + "=([^;]*)(;|$)");
	        if (arr = document.cookie.match(reg)) {
	            return unescape(arr[2]);
	        } else {
	            return null;
	        }
	    },
	    /*删除cookies*/
	    deleteCookie: function (name) {
	        var exp = new Date();
	        exp.setTime(exp.getTime() - 1);
	        var cval = hSys.getCookie(name);
	        if (cval != null) document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	    }
	    

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}