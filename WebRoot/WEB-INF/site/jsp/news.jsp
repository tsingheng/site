<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="news/p${page.pageNo}.htm" title="News">News</a></h2></dt>
            <dd>
            	<c:if test="${fn:length(page.result) gt 0}">
            	<ul class="lts public_ul">
            		<c:forEach items="${page.result}" var="news">
            		<li>
						<h2 title="${news.title}"><a href="${ctx}/news/info/${news.id}.htm" title="${news.title}">${news.title}</a></h2>
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${news.insertTime}"/></span>
					</li>
            		</c:forEach>
            	</ul>
            	</c:if>
            	<c:if test="${fn:length(page.result) eq 0}">
            	<div class="nodata">There are currently no data to show.</div>
            	</c:if>
            </dd>
        </dl>
        <c:if test="${fn:length(page.result) gt 0}">
        <jsp:include page="common/page.jsp"></jsp:include> 
		</c:if>