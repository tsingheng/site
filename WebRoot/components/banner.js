//滑块条
$(function(){
	setTimeout(function(){
		//储存原始位置
		var orLeft,orWidth; 
		$('#magic_nav').append('<li id="margic_line"></li>');
		//获取滑块
		var $line = $('#margic_line');
		//根据current设置宽,位置;同时存原始位置
		var $cur = $('.current');
		$line
			.width($cur.width())
			.css('left',$cur.position().left)
			.data('orLeft',$line.position().left)
			.data('orWidth',$line.width());
		$('#magic_nav li').find('a').hover(function(){
			$tar = $(this);
			//获取滑入目标的宽度(包含padding)
			var _w = $tar.innerWidth();
			//获取滑入目标的位置（减掉边框和左半部分补白）
			var _l = $tar.position().left - 38;
			//设置滑入当前时滑块的宽位置
			$line.stop().animate({
				left:_l,
				width:_w
			});
		},function(){
			//滑出时恢复到原位置
			$line.stop().animate({
				left:$line.data('orLeft'),
				width:$line.data('orWidth')
			});	
		});
	},300);
})