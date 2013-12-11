<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
    <%@ include file="/components/keyword.jsp" %>
    <script type="text/javascript" src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/banner.js"></script>
  </head>
  
  <body>
  <div class="wrap">
  	<div id="header">
  		<div id="logo"><a href=""><img src="${ctx}/resources/images/logo.png"/></a></div>
  		<div id="topr">
  			<div class="cname"></div>
  			<div class="clist">
  				<ul class="public_ul">
				    <li><a href="callto://winsmoke" class="ico_tel">winsmoke</a></li>
				    <li><a class="ico_mail" href="mailto:winsmoke@163.com">winsmoke@163.com</a></li>
				</ul>
  			</div>
  			<br clear="all"/>
  			<div class="nav">
  				<ul class="menu public_ul" id="magic_nav">
				    <li class="current"><a href="/index.htm">Home</a></li>
				    <li><a href="/product/p.htm">Products</a></li>
				    <li><a href="/about.htm">About Us</a></li>
				    <li><a href="/fctphoto/p.htm">Factory Display</a></li>
				    <li><a href="/contact.htm">Contact Us</a></li>
				    <li><a href="/news/p.htm">Company News</a></li>
				    <li><a href="/message.htm">Message</a></li>
				</ul>
  			</div>
  		</div>
  	</div>
  	<div id="banner">
  		<ul class="slide-items public_ul">
  			<li>
  				<a href="">
  				<img src="http://img12.360buyimg.com/da/g14/M05/06/01/rBEhV1Klnn4IAAAAAADt1SfQ8ykAAGrIwCcxV0AAO3t465.jpg"/>
  				</a>
  			</li>
  			<li>
  				<a href="">
  				<img src="http://img14.360buyimg.com/da/g14/M08/06/0F/rBEhV1KoCFgIAAAAAADY9vQF9HYAAGwagICytAAANkO664.jpg"/>
  				</a>
  			</li>
  			<li>
  				<a href="">
  				<img src="http://img12.360buyimg.com/da/g14/M05/06/01/rBEhV1Klnn4IAAAAAADt1SfQ8ykAAGrIwCcxV0AAO3t465.jpg"/>
  				</a>
  			</li>
  			<li>
  				<a href="">
  				<img src="http://img14.360buyimg.com/da/g14/M08/06/0F/rBEhV1KoCFgIAAAAAADY9vQF9HYAAGwagICytAAANkO664.jpg"/>
  				</a>
  			</li>
  			<li>
  				<a href="">
  				<img src="http://img12.360buyimg.com/da/g14/M05/06/01/rBEhV1Klnn4IAAAAAADt1SfQ8ykAAGrIwCcxV0AAO3t465.jpg"/>
  				</a>
  			</li>
  		</ul>
  		<div class="slide-controls">
  			<span>1</span><span>2</span><span>3</span><span>4</span><span>5</span>
  		</div>
  	</div>
  	<div id="hun"><a href="/index.htm">Home</a></div>
  	<div id="body">
  		<div class="left">
  			<dl class="dl dl1">
				<dt><h2><a class="a6">Product Categories</a></h2></dt>
				<dd>
		            <ul class="ls ls0 public_ul">
		            	<li><a href="/category/cate-40/p1.htm" title="Metal injection bottle">Metal injection bottle</a>(<span>1</span>)</li>
		            	<li><a href="/category/cate-41/p1.htm" title="Protank">Protank</a>(<span>1</span>)</li>
		            	<li><a href="/category/cate-23/p1.htm" title="BCC">BCC</a>(<span>2</span>)</li>
		            	<li><a href="/category/cate-12/p1.htm" title="EGO-W">EGO-W</a>(<span>1</span>)</li>
		           </ul>
		       	 </dd>
			</dl>
	
			<dl class="dl dl1">
			    <dt><h2><a class="a6">Contact Details</a></h2></dt>
			    <dd>
			        <ul class="ppt public_ul">
			        	
			        	<li>Contact:<span>Mr.Hunk</span></li>
			        	
			        	<li>MSN:<span>winsmoke100@msn.com</span></li>
			        	
			        	<li>Skype:<span>winsmoke</span></li>
			        	
			        	<li>Email:<span>winsmoke@163.com</span></li>
			        	
			        	<li>Mob: <span>+86-18664330742</span></li>
			        	
			        </ul>
			    </dd>
			</dl>
	
			<dl class="dl dl1">
			    <dt><h2><a class="a7">Chat Now!</a></h2></dt>
			    <dd>
			        <div class="chat">
			            <a href="msnim:chat?contact=winsmoke100@msn.com" title="winsmoke100@msn.com" class="msn"></a>
			            <a href="callto://winsmoke" title="winsmoke" class="skype"></a>
			            <a href="mailto:winsmoke@163.com" title="winsmoke@163.com" class="mail"></a>
			        </div>
			        <div class="chat"><a href="message.htm" title="message.htm" class="contact"></a></div>
			    </dd>
			</dl>
  			
  		</div>
  		<div class="right">
  			<tiles:insertAttribute name="site-content"/>
  		</div>
  		<div id="footer">
			<div class="f_div">
		        <div class="f_lnk"><a href="#" title="bbc" target="_blank"></a></div>
		        <div class="f_cp"></div>
		    </div>
		</div>
  	</div>
  </div>
  </body>
</html>
