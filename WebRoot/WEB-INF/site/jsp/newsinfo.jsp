<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<dl class="dl dl4">
            <dt><h2><a class="a12" title="News">News</a></h2></dt>
            <dd class="ninfo">
                <h1 class="tle">${news.title}</h1>
                <p class="time"><fmt:formatDate pattern="yyyy-MM-dd" value="${news.insertTime}"/></p>
                <div class="pic"></div>
                <div class="desc">${news.content}</div>
        	</dd>
        </dl>
        <%--
        <!--相关新闻-->
        <c:if test="${fn:length(relateNews)>0}">
        <dl class="dl dl4">
            <dt><h2><a class="a12">Related News</a></h2></dt>
            <dd>
                <ul class="ls ones">
                	<c:forEach items="${relateNews}" var="relate">
                	<li><a href="${ctx}/newsinfo/news-${relate.id}.htm" title="${relate.infoTitle}">${relate.infoTitle}</a></li>
                	</c:forEach>
            	</ul>
            </dd>
        </dl>
        </c:if>
        --%>