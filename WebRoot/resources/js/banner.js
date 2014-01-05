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
	var time = 3000, banner = $('#banner'), item = banner.find('li:first'), control = banner.find('span:first'), curr = 1, max = banner.find('li').length;
	var rint;
	if(item && control){
		control.addClass('curr');
		item.show();
		rint = setInterval(function(){
			rotate();
		}, time);
	}
	banner.find('span').click(function(){
		control.removeClass('curr');
		clearInterval(rint);
		control = $(this);
		curr = parseInt(control.html());
		item.hide();
		item = banner.find('li:eq('+(curr-1)+')');
		control.addClass('curr');
		item.fadeIn();
		rint = setInterval(function(){
			rotate();
		}, time);
	});
	function rotate(){
		control.removeClass('curr');
		item.hide();
		curr++;
		if(curr <= max){
			item = item.next();
			control = control.next();
		}else{
			curr = 1;
			item = banner.find('li:first');
			control = banner.find('span:first');
		}
		item.fadeIn();
		control.addClass('curr');
	}
	var speed = 30, scrollHot = $('#scroll_hot'), source = scrollHot.find('#source'), copy = scrollHot.find('#copy'), html = source.html();
	if(source.height()>0){
		while(source.height()<scrollHot.height()){
			source.append(html);
		}
	}
	var height = source.height()-scrollHot.height();
	copy.html(source.html());
	var mar = setInterval(function(){
		scroll();
	}, speed);
	scrollHot.hover(function(){
		clearInterval(mar);
	}, function(){
		mar = setInterval(function(){
			scroll();
		}, speed);
	});
	function scroll(){
		if(scrollHot.scrollTop()>=2*source.height()-scrollHot.height()){
			scrollHot.scrollTop(height);
		}else{
			scrollHot.scrollTop(scrollHot.scrollTop()+1);
		}
	}
	$('.category>li>a').each(function(){
		var me = $(this);
		var li = me.parent();
		if(li.find('.subcate li').length > 0){
			if(li.hasClass('cp')){
				li.data('open', true);
			}else{
				li.data('open', false);
			}
			me.click(function(){
				if(li.data('open')){
					li.find('.subcate').slideUp();
				}else{
					li.find('.subcate').slideDown();
				}
				li.data('open', !li.data('open'));
			});
		}
	});
});
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m = s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js', 'ga');
ga('create', 'UA-46809295-1', 'winsmoke.com');
ga('send', 'pageview');
