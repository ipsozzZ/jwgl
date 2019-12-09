$.extend($.fn.textbox.defaults.rules,{
	NotEmpty : { // 非空字符串验证。 easyui 原装required 不能验证空格
				validator : function(value, param) {
					return $.trim(value).length>0;
				}, 
				message : '该输入项为必输项'
			},
	integer : {// 验证整数
				validator : function(value) {
					return /^[+]?[0-9]+\d*$/i.test(value);
				},
				message : 'please enter number'
			},			
	 english : {// 验证英语
				validator : function(value) {
					return /^[A-Za-z]+$/i.test(value);
				},
				message : '请输入英文'
			},
	email:{//验证邮箱
				validator:function(value){
					return /^([_a-z0-9-]+)(\.[_a-z0-9-]+)*@([a-z0-9-]+)(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/i.test(value);
				},
					message:'please enter a valid email'
	},
	number:{//验证只能全为数字
				validator:function(value){
			           var reg = /^[0-9]*$/;
			           return reg.test(value);
				}
	},
});
