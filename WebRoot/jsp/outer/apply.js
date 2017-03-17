/**
 * 功能：申请
 * 作者：RC
 * 创建日期：2015-06-18
 */
Ext.onReady(function(){
	
	/**********
	 * 可见屏幕宽度
	 * *********/
	var vLabelWidth=document.body.clientWidth/2-100;
	
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
	
	
	/**************
	 * 布局【中】***
	 * ************/
	var fid=new Ext.form.Hidden({name:'fid'});
	var fname=new Ext.form.TextField({name:'fname',fieldLabel:'企业名称',width:260,maxLength:25,allowBlank:false});
	var fopendate=new Ext.form.DateField({name:'fopendate',fieldLabel:'开户日期',format:'Y-m-d',value:new Date(),width:260,allowBlank:false});
	var ftodate=new Ext.form.DateField({name:'ftodate',fieldLabel:'到期日期',format:'Y-m-d',value:new Date().add(Date.YEAR,+1),width:260,allowBlank:false});
	var faddress=new Ext.form.TextField({name:'faddress',maxLength:100,fieldLabel:'详细地址',width:260});
	var fman=new Ext.form.TextField({name:'fman',maxLength:10,fieldLabel:'联系人',width:260,allowBlank:false});
	var ftel=new Ext.form.TextField({name:'ftel',maxLength:20,fieldLabel:'联系电话',width:260,allowBlank:false});
	var fqq=new Ext.form.TextField({name:'fqq',maxLength:20,fieldLabel:'QQ',width:260});
	var femail=new Ext.form.TextField({name:'femail',maxLength:20,fieldLabel:'邮箱',width:260});
	var fwx=new Ext.form.TextField({name:'fwx',maxLength:20,fieldLabel:'微信',width:260});
	var fbank=new Ext.form.TextField({name:'fbank',maxLength:30,fieldLabel:'开户行',width:260});
	var fbankname=new Ext.form.TextField({name:'fbankname',maxLength:20,fieldLabel:'户名',width:260});
	var fbanknum=new Ext.form.TextField({name:'fbanknum',maxLength:30,fieldLabel:'账户',width:260});
	var fweb=new Ext.form.TextField({name:'fweb',maxLength:50,fieldLabel:'企业网址',width:260});
	var fremark=new Ext.form.TextArea({name:'fremark',maxLength:50,fieldLabel:'备注',width:260,height:60});
	
	var storeArea=new Ext.data.JsonStore({
    	fields: ['id', 'vcName'],
        url : "kh_getCityData.do",
        autoLoad: {},
        root : "root"
    });
	/*城市下拉*/
    var fcity= new Ext.form.ComboBox({
    	name:'fcity',
    	fieldLabel :'城市',
    	store: storeArea,
    	valueField : 'id',
        displayField:'vcName',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        selectOnFocus:true,
        width:260,
        maxLength:10
    });
	var vCenter = new Ext.form.FormPanel({
		frame: true,
        border: false,
        autoScroll:true,
        labelWidth:vLabelWidth,
        labelAlign: "right",
        buttonAlign : 'center',
        defaults: { frame: false, border: false },
		items:[fname,fopendate,ftodate,fcity,faddress,fman,
		       ftel,fqq,femail,fwx,fbank,fbankname,fbanknum,fweb,fremark],
		       buttons : [{
	    			text : '确认申请',
	    			style:{marginBottom: '80px'},
	    			handler : function() {
	    				if (vCenter.getForm().isValid()) {
	    					vCenter.getForm().submit({
	    						url : 'outer_saveInfo.do',
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
	    			text : '重置',
	    			style:{marginBottom: '80px'},
	    			handler : function() {
	    				vCenter.getForm().reset();
	    			}
	    		}]
	});
	
	
	
	
	
	
	
	
	/************************
	 *******主体布局*********
	 ***********************/
	new Ext.Viewport({
		title: "Viewport",
		layout: "border",
		defaults: {bodyStyle: "background-color: #FFFFFF;",frame: true},
		items: [{region:"north",height:80,items:[vNorth]},
		        {region:"center",layout:"fit",items:[vCenter]}]
		});
	
	
});