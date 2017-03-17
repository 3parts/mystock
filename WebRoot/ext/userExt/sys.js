/**
 * 系统全局公共对象集合
 */
var hSys = {
		/*******************/
	    /****系统参数信息****/
	    /*******************/
		tabMenuId: 0,  /*当前焦点页面ID*/
		
		/*****************/
		/****系统方法*****/
		/*****************/
		/*间断执行*/
	    RunAfterLater: function (_function, delay) {
	        var _time = (delay == null ? 1000 : delay);
	        var a = setInterval(function () {
	            _function();
	            clearInterval(a);
	        }, _time);
	    }
		
};