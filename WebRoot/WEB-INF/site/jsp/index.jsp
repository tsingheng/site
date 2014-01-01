<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<script type="text/javascript" src="${ctx}/resources/js/jquery.KinSlideshow-1.2.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/index.js"></script>
		<div id="banner-2">
			<c:forEach items="${scroll_image}" var="pro">
			<a href="${ctx}/product/info/${pro.id}.htm"><img src="${ctx}/${pro.image}" alt="" width="475" height="270" /></a>
            </c:forEach>
        </div>
		<dl class="dl dl1 rh_top">
            <dt><h2><a class="a9">Hot Product</a></h2></dt>
            <dd id="scroll_hot" style="height:230px;overflow:hidden;padding:0px 5px;">
            	<div id="in_scroll">
                <ul class="spl public_ul" id="source">
                	<c:forEach items="${hot_product}" var="pro">
					<li><a href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}"><img src="${ctx}/${pro.image}" alt="${pro.productName}" /></a><a class="tl" href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}">${pro.productName}</a></li>
					</c:forEach>
               </ul>
               <ul class="spl public_ul" id="copy"></ul>
               </div>
            </dd>
        </dl>
		<br clear="all"/>
		<dl class="dl dl1">
            <dt><h2><a class="a8">Top Product</a></h2></dt>
            <dd>
                <ul class="bpl  public_ul">
                	<c:forEach items="${top_product}" var="pro">
					<li><a href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}"><img src="${ctx}/${pro.image}" alt="${pro.productName}" /></a><a class="tl" href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}">${pro.productName}</a></li>
					</c:forEach>
                </ul>
                <br clear="all"/>
            </dd>
        </dl>
		<dl class="dl dl1">
            <dt><h2><a class="a5" title="Company Info">Company Info</a></h2></dt>
            <dd>
                <a class="cimg" href="${ctx}/info/about.htm" title="Shenzhen Winsmoke Technology Co., Limited"><img src="${ctx}/${image.attachment.path}" align="Shenzhen Winsmoke Technology Co., Limited" /></a>
                <div class="ctxt">
                    <h3><a href="${ctx}/info/about.htm" title="Company Info">About Us</a></h3>
                    <p>${fn:substring(about, 0, 300)}...</p>
                    <div><a class="more" href="${ctx}/info/about.htm" title="Company Info">Read More</a></div>
                    <h3><a href="${ctx}/news/p.htm" title="Company News">Company News</a></h3>
                    <ul class="ls public_ul">
                    	<c:forEach items="${newslist}" var="news">
                    	<li><a href="${ctx}/news/info/${news.id}.htm" title="${news.title}">${news.title}</a></li>
                    	</c:forEach>
                    </ul>
                    <div><a class="more" href="${ctx}/photo/factory/p.htm" title="Factory Display">Factory Display</a><a class="more" href="${ctx}/news/p.htm" title="News">News</a></div>
                </div>
                <br clear="all"/>
            </dd>
        </dl>
		