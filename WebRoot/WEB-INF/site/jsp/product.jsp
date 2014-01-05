<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<c:if test="${fn:length(page.result) gt 0}">
		<c:forEach items="${page.result}" var="category">
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="${ctx}/${key}-product/${category.id}/${st:replace(category.categoryName, regs)}-p.htm" title="${category.categoryName}">${category.categoryName}</a> (<span>${category.proCount}</span>)</h2></dt>
            <dd>
                <ul class="bpl public_ul">
                	<c:forEach items="${category.products}" var="pro">
                    <li>
                        <a href="${ctx}/${key}-product/info/${st:replace(pro.productName, regs)}-${pro.id}.htm" title="${pro.productName}"><img src="${ctx}/${pro.image}" alt="${key} ${pro.productName}" /></a>
                        <a class="tl" href="${ctx}/${key}-product/info/${st:replace(pro.productName, regs)}-${pro.id}.htm" title="${pro.productName}">${pro.productName}</a>
                    </li>
                    </c:forEach>
                    <br clear="all"/>
                </ul>
            </dd>
        </dl>
		</c:forEach>
        <jsp:include page="common/page.jsp"></jsp:include>
        </c:if>
        <c:if test="${fn:length(page.result) eq 0}">
        <div class="nodata">There are currently no data to show.</div>
        </c:if>
