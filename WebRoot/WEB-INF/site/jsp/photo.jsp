<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<%@ include file="/components/common.jsp" %>
		<!-- 产品分类开始 -->
		<dl class="dl dl4">
            <dt><h2><a class="a12" href="#" title="Factory Display">Factory Display</a></h2></dt>
            <dd>
                <ul class="bpl public_ul">
                	<c:forEach items="${page.result}" var="photo">
                	<li>
                        <img style="cursor:pointer;" src="${ctx}/${photo.attachment.path}" href="${ctx}/${photo.attachment.path}" alt="${photo.title}" />
                        <a class="tl" title="${key} ${photo.title}">${photo.title}</a>
                    </li>
                	</c:forEach>
                </ul>
                <br clear="all"/>
            </dd>
        </dl>
        <script type="text/javascript">
        $(".bpl img").lightBox();
        </script>
        <!-- 产品分类结束 -->
        <!-- 分页开始 -->
        <jsp:include page="common/page.jsp"/>
        <!-- 分页结束 -->