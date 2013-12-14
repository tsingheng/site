<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a7">Send Inquiry Now!</a></h2></dt>
            <dd class="pinfo">
                <form id="messageform1" name="messageform1" action="message/post.htm" method="post" onsubmit="return mf1SubmitCheck(this)" enctype="multipart/form-data">
                    <input type="hidden" id="pid" name="pid" value="-1" />
                    <h3>Send Inquiry</h3>
                    <table class="form">
                        <tr>
                            <th style="width:80px;">Subject : <span>*</span></th>
                            <td><input type="text" id="subject" name="msgSubject" vformat="required" style="width:500px;" /></td>
                        </tr>
                        <tr>
                            <th>Email : <span>*</span></th>
                            <td><input type="text" id="email" name="msgEmail" style="width:300px;" vformat="required/email" /></td>
                        </tr>
                        <tr>
                            <th style="padding-right:12px;">Name : </th>
                            <td><input type="text" id="name" name="msgNme" style="width:200px;" /></td>
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
                            <td id="fileDiv">
                                <div id="file1"><input type="file" name="file1"/> <a href="#" id="a1" onclick="addFile('1');" style="font-size:20px;"><b>+</b></a></div>
                            </td>
                        </tr>
                        <tr>
                            <th>Company : </th>
                            <td><input type="text" id="company" name="customerCompany" style="width:350px;" /></td>
                        </tr>
                        <tr>
                            <th>Phone : </th>
                            <td><input type="text" id="customerPhone" name="tel" style="width:200px;" /></td>
                        </tr>
                        <tr>
                            <th>Fax : </th>
                            <td><input type="text" id="fax" name="customerFax" style="width:200px;" /></td>
                        </tr>
                        <tr>
                            <th>Country : </th>
                            <td><input type="text" id="country" name="cutomerCountry" style="width:120px;" /></td>
                        </tr>
                        <tr>
                            <th>Address : </th>
                            <td><input type="text" id="address" name="customerAddress" style="width:500px;" /></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input class="post" type="submit" value="Submit"></td>
                        </tr>
                    </table>
                </form>
            </dd>
        </dl>
<!-- 内容主体结束 -->
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
        }, contentType: 'multipart/form-data'});
        return false;
    }
    var i = 1;
    function addFile(id){
    	i = i + 1;
		$("#a"+id).html("<b>X</b>");
		$("#a"+id).css("font-size", "16px");
		$("#a"+id).attr("onclick", "removeFile("+id+")");
		var fileDiv = "<div id=\"file"+i+"\"><input type=\"file\" name=\"file"+i+"\"/> <a href=\"#\" id=\"a"+i+"\" onclick=\"addFile("+i+");\" style=\"font-size:20px;\"><b>+</b></a></div>";
		$("#fileDiv").append(fileDiv);
    }
    function removeFile(id){
    	$("#file"+id).remove();
    }
</script>