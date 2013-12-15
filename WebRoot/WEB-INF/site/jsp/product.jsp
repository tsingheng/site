<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<c:forEach items="${page.result}" var="category">
		<c:if test="${fn:length(category.products) > 0}">
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="${ctx}/product/${category.id}/p.htm" title="${category.categoryName}">${category.categoryName}</a> (<span>${category.proCount}</span>)</h2></dt>
            <dd>
                <ul class="bpl public_ul">
                	<c:forEach items="${category.products}" var="pro">
                    <li>
                        <a href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}"><img src="${ctx}/${pro.oneImage}" alt="${pro.productName}" /></a>
                        <a class="tl" href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}">${pro.productName}</a>
                    </li>
                    </c:forEach>
                    <br clear="all"/>
                </ul>
            </dd>
        </dl>
        </c:if>
		</c:forEach>
        <jsp:include page="common/page.jsp"></jsp:include>
