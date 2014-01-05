<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="${ctx}/${key}-product/${st:replace(product.category.categoryName, regs)}-${product.category.id}/p.htm">${product.category.categoryName}</a></h2></dt>
            <dd class="pinfo">
                <div class="pro_lf">
                    <div id="gallery"><a href="${ctx}/${product.image}" class="bimg"><img id="bimg"  src="${ctx}/${product.image}" width="260" height="260" alt="${key} ${product.productName}" /></a></div>
                    <ul class="simg public_ul">
                    	<c:forEach var="img" items="${product.images}">
                    	<li><img src="${ctx}/${img.attachment.path}" href="${ctx}/${img.attachment.path}" alt="${product.productName}" title="${product.productName}" /></li>
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
                            <c:forEach items="${product.properties}" var="property">
                            <tr>
                                <th>${property.propertyName}</th>
                                <td>${property.propertyValue}</td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="share">
                    </div>
                    <div class="link">
                        <a href="#inquiry" class="inq" onclick="document.getElementById('subject').focus();">Send Inquiry Now</a>
                        <c:if test="${not empty product.buyLink}">
                        <a href="http://winsmok.en.alibaba.com${product.buyLink}" class="buy">Buy Now</a>
                        </c:if>
                    </div>
                </div>
                <div class="clear"></div>
                <h3>Product Description</h3>
                <div class="desc">${product.description}</div>
            </dd>
        </dl>
        <dl class="dl dl4">
		    <div id="inquiry">
		    <dt><h2><a class="a7">Send Inquiry Now!</a></h2></dt>
		    <dd>
		        <form id="messageform1" name="messageform1" action="${ctx}/message/post.htm" method="post" onsubmit="return mf1SubmitCheck(this);">
		            <input type="hidden" id="pid" name="pid" value="0" />
		            <table cellspacing="0" cellpadding="4" class="form">
		                <tr>
		                    <th>Subject : <span>*</span></th>
		                    <td><input type="text" id="subject" name="subject" vformat="required" style="width:500px;" /></td>
		                </tr>
		                <tr>
		                    <th>Email : <span>*</span></th>
		                    <td><input type="text" id="email" name="email" style="width:300px;" vformat="required/email" /></td>
		                </tr>
		                <tr>
		                    <th style="padding-right:12px;">Name : </th>
		                    <td><input type="text" id="name" name="custName" style="width:200px;" /></td>
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
		    </div>
		</dl>
<script type="text/javascript" src="${ctx}/resources/jquery.extend.js"></script>
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
    $(document).ready(function(){
    	$('.detail-content img[data-src]').each(function(){
    		$(this).attr('src', '${ctx}/' + $(this).attr('data-src'));
    	});
    });
</script>