//������
$(function(){
	setTimeout(function(){
		//����ԭʼλ��
		var orLeft,orWidth; 
		$('#magic_nav').append('<li id="margic_line"></li>');
		//��ȡ����
		var $line = $('#margic_line');
		//����current���ÿ�,λ��;ͬʱ��ԭʼλ��
		var $cur = $('.current');
		$line
			.width($cur.width())
			.css('left',$cur.position().left)
			.data('orLeft',$line.position().left)
			.data('orWidth',$line.width());
		$('#magic_nav li').find('a').hover(function(){
			$tar = $(this);
			//��ȡ����Ŀ��Ŀ��(����padding)
			var _w = $tar.innerWidth();
			//��ȡ����Ŀ���λ�ã������߿����벿�ֲ��ף�
			var _l = $tar.position().left - 38;
			//���û��뵱ǰʱ����Ŀ�λ��
			$line.stop().animate({
				left:_l,
				width:_w
			});
		},function(){
			//����ʱ�ָ���ԭλ��
			$line.stop().animate({
				left:$line.data('orLeft'),
				width:$line.data('orWidth')
			});	
		});
	},300);
})