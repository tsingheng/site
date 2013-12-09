(function($){
	var _ajax = $.ajax;
	$.ajax = function(opt){
		var error = function(XMLHttpRequest, textStatus, errorThrown){
			showMsg('系统发送请求出错');
		}
		var _opt = opt;
		if(opt.success){
			var success = opt.success;
			_opt = $.extend(opt, {
				success: function(data, textStatus){
					if(opt.dataType == 'json')
						$.myAjaxSuccess(data, textStatus, success);
					else{
						if(data.substr(0, 1)=='{' && data.substr(data.length-1, 1)=='}'){
							var result = eval('('+data+')');
							if(result.auth!=undefined&&!result.auth){
								alert(result.msg);
								if(data.reload)
									window.location.reload();
								return false;
							}
						}
						success(data, textStatus);
					}
				},
				error: function(XMLResponse){
					alert(XMLResponse.responseText);
				}
			});
		}
		_ajax(_opt);
	}
	$.myAjaxSuccess = function(data, textStatus, success){
		if(data.auth != undefined && !data.auth){
			alert(data.msg);
			if(data.reload)
				window.location.reload();
		}else{
			success(data, textStatus);
		}
	}
})(jQuery);