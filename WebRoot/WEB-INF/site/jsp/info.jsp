<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a12" title="${title}">${title}</a></h2></dt>
            <dd class="cinfo">
             <div class="desc">
                ${info.content}
                <div class="clear"></div>
             </div>
                <div class="clear"></div>
            </dd>
        </dl>
        <dl class="dl dl4">
    <dt><h2><a class="a7">Send Inquiry Now!</a></h2></dt>
    <dd>
        <form id="messageform1" name="messageform1" action="message/post.htm" method="post" onsubmit="return mf1SubmitCheck(this)">
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
</dl>
<script type="text/javascript" src="${ctx}/resources/jquery.extend.js"></script>
<script type="text/javascript">
	var fm_posting = 'posting...', fm_thanks = 'Thanks for your inquiry! We will contact you soon.';
    mf1valid = $('#messageform1').FormValidtor();
    function mf1SubmitCheck(f){
        if(mf1valid == null || !mf1valid.Check()){
            return false;
        }
        $(f).ajaxSubmit({ beforeSubmit: function(){
            $('input.post').text(fm_posting);
        }, complete: function(res){
            var data = eval("("+res.responseText+")");
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
        