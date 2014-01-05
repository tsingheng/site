<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="st" uri="http://www.shangtech.net/tags"%>
<c:set value="<%=request.getContextPath()%>" var="ctx"></c:set>
<c:set value="winsmoke_electronic_cigarette" var="key"></c:set>
<c:set value='<%=new String[]{" |/|%|,", "_", "!", "", "-", "_"}%>' var="regs"></c:set>
