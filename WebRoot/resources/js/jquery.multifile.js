(function($){
	$.fn.multifile = function(opt){
		var input = $(this);
		var container = input.parent();
		addFile = function(){
			var div = $('<div style="height:24px; margin:3px 0px; line-height:24px; vertical-align:middle;"></div>');
			var fInput = $('<input type="file"/>')
						.attr('name', input.attr('name'))
						.attr('class', input.attr('class'))
						.css({'margin-right': '8px'});
			var button = $('<a href="#" style="font-size:20px;" class="l-btn"></a>')
						.html('<span class="l-btn-left"><span class="l-btn-text"><b>+</b></span></span>')
						.click(function(){
							var fBtn = $(this);
							if(fBtn.find('b').html() == '+'){
								fBtn.html('<span class="l-btn-left"><span class="l-btn-text"><b>x</b></span></span>');
								addFile();
							}else{
								$(this).parent().remove();
							}
						});
			div.append(fInput).append(button).appendTo(container);
		}
		addFile();
		input.remove();
		// 先在第一个右边添加一个+的按钮
		//var button = $('<a href="#" style="font-size:20px;" class="l-btn"></a>');
		//var addSpan = $('<span class="l-btn-left"><span class="l-btn-text><b>+</b></span></span>"');
		//addSpan.appendTo(button);
		//button.click(addFile);
		
		
	}
})(jQuery);