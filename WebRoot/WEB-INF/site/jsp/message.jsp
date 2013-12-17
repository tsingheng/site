<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a7">Send Inquiry Now!</a></h2></dt>
            <dd class="pinfo">
                <form id="messageform1" name="messageform1" action="${ctx}/message/post.htm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="pid" name="pid" value="-1" />
                    <h3>Send Inquiry</h3>
                    <table class="form">
                        <tr>
                            <th style="width:80px;">Subject : <span>*</span></th>
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
                            <th>Message :  <span>*</span></th>
                            <td><textarea id="message" name="msgContent" style="width:600px;height:80px;" vformat="required"></textarea></td>
                        </tr>
                    </table>
                    <h3 style="margin-top:15px;">More Options (more details, help us can much easier to contact you!)</h3>
                    <table cellspacing="0" cellpadding="4" class="form">
                        <tr>
                            <th style="width:80px;">Attach file : </th>
                            <td id="filetd">
                                <div><input type="file" name="file"/><a href="#nogo" class="add">&nbsp;</a></div>
                            </td>
                        </tr>
                        <tr>
                            <th>Company : </th>
                            <td><input type="text" id="company" name="company" style="width:350px;" /></td>
                        </tr>
                        <tr>
                            <th>Phone : </th>
                            <td><input type="text" id="tel" name="tel" style="width:200px;" /></td>
                        </tr>
                        <tr>
                            <th>Fax : </th>
                            <td><input type="text" id="fax" name="fax" style="width:200px;" /></td>
                        </tr>
                        <tr>
                            <th>Country : </th>
                            <td><input type="text" id="country" name="country" style="width:120px;" /></td>
                        </tr>
                        <tr>
                            <th>Address : </th>
                            <td><input type="text" id="address" name="address" style="width:500px;" /></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input class="post" type="button" value="Submit" onclick="mf1SubmitCheck();"></td>
                        </tr>
                    </table>
                </form>
            </dd>
        </dl>
<!-- 内容主体结束 -->
<script type="text/javascript" src="${ctx}/resources/jquery.extend.js"></script>
<script type="text/javascript">
	var fm_posting = 'posting...', fm_thanks = 'Thanks for your inquiry! We will contact you soon.';
    var mf1valid = $('#messageform1').FormValidtor();
    function mf1SubmitCheck(){
        if(mf1valid == null || !mf1valid.Check()){
            return false;
        }
        $('#messageform1').ajaxSubmit({ beforeSubmit: function(){
            $('input.post').text(fm_posting);
        }, complete: function(res){
            var data = eval("("+res.responseText+")");
            if(!data.success){
                alert(data.msg);
            }else{
                alert(fm_thanks);
                $('#messageform1').clearForm();
            }
        }, contentType: 'multipart/form-data'});
        return false;
    }
    $(document).ready(function(){
    	$('#filetd a.add').click(function(){
    		addFile($(this));
    	});
    	function addFile(a){
    		a.unbind().removeClass('add').addClass('remove').click(function(){
    			a.parent().remove();
    		});
    		var div = $('<div></div>');
    		var input = $('<input type="file" name="file"/>');
    		var add = $('<a href="#nogo" class="add">&nbsp;</a>');
    		add.click(function(){
    			addFile(add);
    		});
    		div.append(input).append(add).appendTo($('#filetd'));
    	}
    });
</script>