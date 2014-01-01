<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<%-- 产品开始--%>
		<dl class="dl dl4">
            <dt><h2><a class="a12" title="${cateName}">${cateName}</a></h2></dt>
            <dd>
                <ul class="lps public_ul">
                	<c:forEach items="${page.result}" var="pro">
                	<li>
                        <a class="pimg" href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}"><img src="${ctx}/${pro.image}" alt="${pro.productName}" /></a>
                        <div class="ptxt">
                            <h3 class="tle" title="${pro.productName}"><a href="${ctx}/product/info/${pro.id}.htm">${pro.productName}</a></h3>
                            <div class="desc">${pro.desc}</div>
                            <div class="time"><fmt:formatDate value="${pro.insertTime}" pattern="yyyy-MM-dd"/></div>
                            <a class="more" href="${ctx}/product/info/${pro.id}.htm" title="${pro.productName}">Read More</a>
                        </div>
                        <br clear="all"/>
                    </li>
                    <br clear="all"/>
                	</c:forEach>
                	<br clear="all"/>
                </ul>
            </dd>
        </dl>
        <%-- 产品结束--%>
        <%-- 分页开始--%>
        <jsp:include page="common/page.jsp"></jsp:include>
        <%-- 分页结束--%>