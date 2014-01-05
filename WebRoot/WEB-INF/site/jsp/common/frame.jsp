<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/components/common.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title><c:if test="${not empty mi_title}">${mi_title} | </c:if>${site_title}</title>
    <%@ include file="/components/keyword.jsp" %>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <script type="text/javascript" src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/banner.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery.lightbox-0.5.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/jquery.lightbox-0.5.css" media="screen" />
	<script type="text/javascript">var ctx = "${ctx}";</script>
  </head>
  
  <body>
  <div class="wrap">
  	<div id="header">
  		<div id="logo"><a href="http://www.winsmoke.com/"><img alt="${site_title}" src="${ctx}/resources/images/logo.png"/></a></div>
  		<div id="topr">
  			<div class="cname">${site_head}</div>
  			<div class="clist">
  				<ul class="public_ul">
				    <li><a href="callto://${skype}" class="ico tel">${skype}</a></li>
				    <li><a class="ico mail" href="mailto:${email}">${email}</a></li>
				</ul>
  			</div>
  			<br clear="all"/>
  			<div class="nav">
  				<ul class="menu public_ul" id="magic_nav">
				    <li<c:if test="${type eq 'index'}"> class="current"</c:if>><a href="/index.htm">Home</a></li>
				    <li<c:if test="${type eq 'product'}"> class="current"</c:if>><a href="/${key}-product/p.htm">Products</a></li>
				    <li<c:if test="${type eq 'about'}"> class="current"</c:if>><a href="/${key}-info/about-${key}.htm">About Us</a></li>
				    <li<c:if test="${type eq 'factory'}"> class="current"</c:if>><a href="/${key}-photo/factory-${key}/p.htm">Factory Display</a></li>
				    <li<c:if test="${type eq 'contact'}"> class="current"</c:if>><a href="/${key}-info/contact-${key}.htm">Contact Us</a></li>
				    <li<c:if test="${type eq 'news'}"> class="current"</c:if>><a href="/${key}-news/p.htm">Company News</a></li>
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
  				<img alt="${site_title}" src="${ctx}/${banner.attachment.path}"/>
  				</a>
  			</li>
  			</c:forEach>
  		</ul>
  		<div class="slide-controls">
  			<c:forEach items="${banners}" var="banner" varStatus="i"><span>${i.index+1}</span></c:forEach>
  		</div>
  	</div>
  	<%-- <div id="hun"><a href="/index.htm">Home</a></div>--%>
  	<div id="body">
  		<div class="left">
  			<dl class="dl dl1">
				<dt><h2><a class="a6">Product Categories</a></h2></dt>
				<dd>
		            <ul class="ls ls0 public_ul category">
		            	<c:forEach items="${gcates}" var="cate">
		            	<li class="
		            				<c:if test="${category.id eq cate.id}">on </c:if>
		            				<c:if test="${category.parent.id eq cate.id}">cp </c:if>
		            		">
		            		<a <c:if test="${fn:length(cate.children) eq 0}">
		            			href="${ctx}/${key}-product/${st:replace(cate.categoryName, regs)}-${cate.id}/p.htm"
		            			</c:if>
		            			<c:if test="${fn:length(cate.children) gt 0}">
		            			href="#nogo"
		            			</c:if>
		            			title="${cate.categoryName}">
		            			${cate.categoryName}
		            			<c:if test="${fn:length(cate.children) eq 0}">(<span>${cate.proCount}</span>)</c:if>
		            		</a>
		            		<c:if test="${fn:length(cate.children) gt 0}">
		            		<ul class="subcate">
		            			<c:forEach items="${cate.children}" var="child">
		            			<li<c:if test="${child.id eq category.id}"> class="on"</c:if>><a href="${ctx}/${key}-product/${st:replace(child.categoryName, regs)}-${child.id}/p.htm" title="${child.categoryName}">${child.categoryName}(<span>${child.proCount}</span>)</a></li>
		            			</c:forEach>
		            		</ul>
		            		</c:if>
		            	</li>
		            	</c:forEach>
		           </ul>
		       	 </dd>
			</dl>
	
			<dl class="dl dl1">
			    <dt><h2><a class="a6">Contact Details</a></h2></dt>
			    <dd>
			        <dl class="ppt public_ul">
			        	${site_contact}
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
