<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<c:forEach items="${page}" var="category">
		<c:if test="${fn:length(category.products) > 0}">
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="${ctx}/category/cate-${category.id}/p1.htm" title="${category.categoryName}">${category.categoryName}</a> (<span>${category.size}</span>)</h2></dt>
            <dd>
                <ul class="bpl public_ul">
                	<c:forEach items="${category.products}" var="pro">
                    <li>
                        <a href="${ctx}/proinfo/pro-${pro.id}.htm" title="${pro.productName}"><img src="${ctx}/${pro.oneImage}" alt="${pro.productName}" /></a>
                        <a class="tl" href="${ctx}/proinfo/pro-${pro.id}.htm" title="${pro.productName}">${pro.productName}</a>
                    </li>
                    </c:forEach>
                </ul>
                <div class="clear"></div>
            </dd>
        </dl>
        </c:if>
		</c:forEach>
        <jsp:include page="common/page.jsp"></jsp:include>
