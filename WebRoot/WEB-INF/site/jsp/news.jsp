<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="news/p${pageNo}.htm" title="News">News</a></h2></dt>
            <dd>
            	<ul class="lts public_ul">
            		<c:forEach items="${page}" var="news">
            		<li>
						<h2 title="${news.infoTitle}"><a href="${ctx}/newsinfo/news-${news.id}.htm" title="${news.infoTitle}">${news.infoTitle}</a></h2>
						<span><fmt:formatDate pattern="yyyy-MM-dd" value="${news.insertTime}"/></span>
					</li>
            		</c:forEach>
            		<li></li>
            	</ul>
            </dd>
        </dl>
        <jsp:include page="common/page.jsp"></jsp:include> 
