<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<%-- 分页开始 --%>
        <div id="pager">
	        <ul class="public_ul">
		        <li>Total <span>${total}</span> Records</li>
		        <c:choose>
		        <c:when test="${pageNo eq 1}">
		        <li><a style="color:#666;">First</a></li>
		        <li><a style="color:#666;">Prev</a></li>
		        </c:when>
		        <c:otherwise>
		        <li><a href="p1.htm">First</a></li>
		        <li><a href="p${pageNo-1}.htm">Prev</a></li>
		        </c:otherwise>
		        </c:choose>
		        <li class="no">
		        	<c:forEach begin="1" end="${pageNum}" var="i">
		        	<c:choose>
			        <c:when test="${pageNo eq i}">
			        <a class="dq">${i}</a>
			        </c:when>
			        <c:otherwise>
			        <a href="p${i}.htm">${i}</a>
			        </c:otherwise>
			        </c:choose>
		        	</c:forEach>
		        <li>
		        <c:choose>
		        <c:when test="${pageNo lt pageNum}">
		        <li><a href="p${pageNo+1}.htm">Next</a></li>
		        <li><a href="p${pageNum}.htm">Last</a></li>
		        </c:when>
		        <c:otherwise>
		        <li><a style="color:#666;">Next</a></li>
		        <li><a style="color:#666;">Last</a></li>
		        </c:otherwise>
		        </c:choose>
	        </ul>  
        </div>
        <%-- 分页结束 --%>