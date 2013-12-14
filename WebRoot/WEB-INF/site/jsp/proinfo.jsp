<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="${ctx}/category/cate-${product.productCategory.id}/p1.html">${product.productCategory.categoryName}</a></h2></dt>
            <dd class="pinfo">
                <div class="pro_lf">
                    <div id="gallery"><a href="${ctx}/${product.oneImage}" class="bimg"><img id="bimg"  src="${ctx}/${product.oneImage}" width="260" height="260" alt="" /></a></div>
                    <ul class="simg public_ul">
                    	<c:forEach var="img" items="${product.productImages}">
                    	<li><img src="${ctx}/${img.productImage}" href="${ctx}/${img.productImage}" alt="${product.productName}" title="${product.productName}" /></li>
                    	</c:forEach>
                    </ul>
                </div>
                <div class="items">
                    <h1>${product.productName}</h1>
                    <div class="pro_con">
                        <table cellspacing="1" cellpadding="4" class="tbl1">
                            <tr>
                                <th>Product Name</th>
                                <td>${product.productName}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="share">
                        <%-- AddThis Button BEGIN --%>
                        <!-- 
                        <div class="addthis_toolbox addthis_default_style addthis_32x32_style">
                            <a class="addthis_button_preferred_1"></a>
                            <a class="addthis_button_preferred_2"></a>
                            <a class="addthis_button_preferred_3"></a>
                            <a class="addthis_button_preferred_4"></a>
                            <a class="addthis_button_preferred_5"></a>
                            <a class="addthis_button_preferred_6"></a>
                            <a class="addthis_button_preferred_7"></a>
                            <a class="addthis_button_compact"></a>
                            <a class="addthis_counter addthis_bubble_style"></a>
                        </div>
                        <script type="text/javascript">var addthis_config = {"data_track_addressbar":false};</script>
                        <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-5010ab416e09b645"></script>
                         -->
                        <%-- AddThis Button END --%>
                    </div>
                    <div class="link">
                        <a href="#inquiry" class="inq" onclick="document.getElementById('subject').focus();">Send Inquiry Now</a>
                    </div>
                </div>
                <div class="clear"></div>

                <div class="clear"></div>
                <h3>Product Description</h3>
                <div class="desc">${product.productDescription}</div>
            </dd>
        </dl>
        <%-- 产品信息结束 --%>
        <%-- 相关产品开始 --%>
        <dl class="dl dl4">
            <dt><h2><a class="a4">Related Product</a></h2></dt>
            <dd>
                <ul class="bpl public_ul">
                	<c:forEach items="${relateList}" var="relate">
                	<li><a href="${ctx}/proinfo/pro-${relate.id}.htm" title="${relate.productName}"><img src="${ctx}/${relate.oneImage}" alt="${relate.productName}" /></a><a class="tl" href="${ctx}/proinfo/pro-${relate.id}.htm" title="${relate.productName}">${relate.productName}</a></li>
                	</c:forEach>
                </ul>
                <div class="clear"></div>
            </dd>
        </dl>
        <%-- 相关产品结束 --%>
        <%-- 留言开始 --%>
        <dl class="dl dl4">
    <dt><h2><a class="a7">Send Inquiry Now!</a></h2></dt>
    <dd>
        <form id="messageform1" name="messageform1" action="${ctx}/message/post.htm" method="post" onsubmit="return mf1SubmitCheck(this);">
            <input type="hidden" id="pid" name="pid" value="0" />
            <table cellspacing="0" cellpadding="4" class="form">
                <tr>
                    <th>Subject : <span>*</span></th>
                    <td><input type="text" id="subject" name="msgSubject" vformat="required" style="width:500px;" /></td>
                </tr>
                <tr>
                    <th>Email : <span>*</span></th>
                    <td><input type="text" id="email" name="msgEmail" style="width:300px;" vformat="required/email" /></td>
                </tr>
                <tr>
                    <th style="padding-right:12px;">Name : </th>
                    <td><input type="text" id="name" name="msgName" style="width:200px;" /></td>
                </tr>
                <tr>
                    <th>Message : <span>*</span></th>
                    <td><textarea id="message" name="msgContent" style="width:600px;height:80px;" vformat="required"></textarea></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input class="post" type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </dd>
</dl>
<%-- 内容主体结束 --%>
<script type="text/javascript">
    var pname = '${product.productName}';
    var askinqtitle = 'Inquiry for 『#1』';
    var askinqcontent = 'We are interested in product 『#1』. Please give me price and details';
    document.getElementById('subject').value =  askinqtitle.replace('#1', pname);
    document.getElementById('message').value = askinqcontent.replace('#1', pname);
</script>
<script type="text/javascript">
    var fm_posting = 'posting...', fm_thanks = 'Thanks for your inquiry! We will contact you soon.';
</script>

<script type="text/javascript">
    var mf1valid = null;
    function mf1SubmitCheck(f){
        if(mf1valid == null || !mf1valid.Check()){
            return false;
        }
        $(f).ajaxSubmit({ beforeSubmit: function(){
            $('input.post').text(fm_posting);
        }, complete: function(res){
            var data = eval('(' + res.responseText + ')');
            if(!data.success){
                alert(data.msg);
            }else{
                alert(fm_thanks);
                $('#messageform1').clearForm();
            }
        }});
        return false;
    }
</script>
<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox({imageContainer: $('.simg img')});
    });
    function simgs(){
        $('.simg li').click(function(){
            $('.simg li.focus').removeClass('focus');
            $(this).addClass('focus');
            var s = $('img', this).attr('src');
            $('a.bimg').attr('href', s);
            $('img#bimg').attr('src', s);
        });
        $('.simg li:eq(0)').trigger('click');
    }
    mf1valid = $('#messageform1').FormValidtor(); simgs();;
</script>