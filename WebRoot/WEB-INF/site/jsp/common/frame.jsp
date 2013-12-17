<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>${site_title}</title>
    <%@ include file="/components/keyword.jsp" %>
    <script type="text/javascript" src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/banner.js"></script>
  </head>
  
  <body>
  <div class="wrap">
  	<div id="header">
  		<div id="logo"><a href=""><img src="${ctx}/resources/images/logo.png"/></a></div>
  		<div id="topr">
  			<div class="cname">${site_head}</div>
  			<div class="clist">
  				<ul class="public_ul">
				    <li><a href="callto://${skype }" class="ico_tel">${skype}</a></li>
				    <li><a class="ico_mail" href="mailto:${email}">${email}</a></li>
				</ul>
  			</div>
  			<br clear="all"/>
  			<div class="nav">
  				<ul class="menu public_ul" id="magic_nav">
				    <li<c:if test="${type eq 'index'}"> class="current"</c:if>><a href="/index.htm">Home</a></li>
				    <li<c:if test="${type eq 'product'}"> class="current"</c:if>><a href="/product/p.htm">Products</a></li>
				    <li<c:if test="${type eq 'about'}"> class="current"</c:if>><a href="/info/about.htm">About Us</a></li>
				    <li<c:if test="${type eq 'factory'}"> class="current"</c:if>><a href="/photo/factory/p.htm">Factory Display</a></li>
				    <li<c:if test="${type eq 'contact'}"> class="current"</c:if>><a href="/info/contact.htm">Contact Us</a></li>
				    <li<c:if test="${type eq 'news'}"> class="current"</c:if>><a href="/news/p.htm">Company News</a></li>
				    <li<c:if test="${type eq 'message'}"> class="current"</c:if>><a href="/message.htm">Message</a></li>
				</ul>
  			</div>
  		</div>
  	</div>
  	<div id="banner">
  		<ul class="slide-items public_ul">
  			<c:forEach items="${banners}" var="banner">
  			<li>
  				<a href="${banner.link}">
  				<img src="${ctx}/${banner.attachment.path}"/>
  				</a>
  			</li>
  			</c:forEach>
  		</ul>
  		<div class="slide-controls">
  			<c:forEach items="${banners}" var="banner" varStatus="i"><span>${i.index+1}</span></c:forEach>
  		</div>
  	</div>
  	<div id="hun"><a href="/index.htm">Home</a></div>
  	<div id="body">
  		<div class="left">
  			<dl class="dl dl1">
				<dt><h2><a class="a6">Product Categories</a></h2></dt>
				<dd>
		            <ul class="ls ls0 public_ul">
		            	<c:forEach items="${gcates}" var="cate">
		            	<c:if test="${cate.proCount gt 0}">
		            	<li><a href="${ctx}/product/${cate.id}/p.htm" title="${cate.categoryName}">${cate.categoryName}</a>(<span>${cate.proCount}</span>)</li>
		            	</c:if>
		            	</c:forEach>
		           </ul>
		       	 </dd>
			</dl>
	
			<dl class="dl dl1">
			    <dt><h2><a class="a6">Contact Details</a></h2></dt>
			    <dd>
			        <dl class="ppt public_ul">
			        	
			        	<li><span class="name">Contact:</span><span class="value">Mr.Hunk</span></li>
			        	
			        	<li><span class="name">MSN:</span><span>winsmoke100@msn.com</span></li>
			        	
			        	<li><span class="name">Skype:</span><span>winsmoke</span></li>
			        	
			        	<li><span class="name">Email:</span><span>winsmoke@163.com</span></li>
			        	
			        	<li><span class="name">Mobile:</span><span>+86-18664330742</span></li>
			        	
			        </dl>
			    </dd>
			</dl>
	
			<dl class="dl dl1">
			    <dt><h2><a class="a7">Chat Now!</a></h2></dt>
			    <dd>
			        <div class="chat">
			            <a href="msnim:chat?contact=${msc}" title="${msn}" class="msn"></a>
			            <a href="callto://${skype}" title="${skype}" class="skype"></a>
			            <a href="mailto:${email}" title="${email}" class="mail"></a>
			        </div>
			        <div class="chat"><a href="${ctx}/message.htm" title="Contact Now" class="contact"></a></div>
			    </dd>
			</dl>
  			
  		</div>
  		<div class="right">
  			<tiles:insertAttribute name="site-content"/>
  		</div>
  		<div id="footer">
			<div class="f_div">
		        <div class="f_lnk">${site_foot}</div>
		    </div>
		</div>
  	</div>
  </div>
  </body>
</html>
