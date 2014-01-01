<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%-- 分页开始 --%>
        <div id="pager">
	        <ul class="public_ul">
		        <li>Total <span>${page.totalCount}</span> Records</li>
		        <c:choose>
		        <c:when test="${page.pageNo eq 1}">
		        <li><a style="color:#666;">First</a></li>
		        <li><a style="color:#666;">Prev</a></li>
		        </c:when>
		        <c:otherwise>
		        <li><a href="p1.htm">First</a></li>
		        <li><a href="p${page.pageNo-1}.htm">Prev</a></li>
		        </c:otherwise>
		        </c:choose>
		        <li class="no">
		        	<c:if test="${page.leftPoints}">
		        		<a href="p1.htm">1</a>
		        		<a>...</a>
		        	</c:if>
		        	<c:forEach begin="${page.pageStart}" end="${page.pageEnd}" var="i">
		        	<c:choose>
			        <c:when test="${page.pageNo eq i}">
			        <a class="dq">${i}</a>
			        </c:when>
			        <c:otherwise>
			        <a href="p${i}.htm">${i}</a>
			        </c:otherwise>
			        </c:choose>
		        	</c:forEach>
		        	<c:if test="${page.rightPoints}">
		        		<a>···</a>
		        		<a href="p${page.pageSize}.htm">${page.pageSize}</a>
		        	</c:if>
		        <li>
		        <c:choose>
		        <c:when test="${page.pageNo lt page.pageSize}">
		        <li><a href="p${page.pageNo+1}.htm">Next</a></li>
		        <li><a href="p${page.pageSize}.htm">Last</a></li>
		        </c:when>
		        <c:otherwise>
		        <li><a style="color:#666;">Next</a></li>
		        <li><a style="color:#666;">Last</a></li>
		        </c:otherwise>
		        </c:choose>
	        </ul>  
        </div>
        <%-- 分页结束 --%>