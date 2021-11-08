<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>


<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>


    </div>
</c:if>
<fmt:parseDate value="${opinion.opinionDate}" pattern="yyyy-MM-dd" var="opinionDay" type="date" />
<label for="${AttributeConst.OPI_DATE.getValue()}">ご意見を頂戴した日付</label><br />
<input type="date" name="${AttributeConst.OPI_DATE.getValue()}" value="<fmt:formatDate value='${opinionDay}' pattern='yyyy-MM-dd' />" />
<br /><br />


<label for="name">報告者</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="${AttributeConst.OPI_OVERVIEW.getValue()}">概要</label><br />
<input type="text" name="${AttributeConst.OPI_OVERVIEW.getValue()}" value="${opinion.overView}" />
<br /><br />


<label for="${AttributeConst.OPI_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.OPI_CONTENT.getValue()}" rows="10" cols="50">${opinion.content}</textarea>
<br /><br />
<input type="hidden" name="${AttributeConst.OPI_ID.getValue()}" value="${opinion.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>