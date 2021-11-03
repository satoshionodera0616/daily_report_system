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
<fmt:parseDate value="" pattern="yyyy-MM-dd" var="" type="date" />
<label for="">ご意見を頂戴した日付</label><br />
<input type="date" name="" value="<fmt:formatDate value='' pattern='yyyy-MM-dd' />" />
<br /><br />


<label for="name">報告者</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="">概要</label><br />
<input type="text" name="" value="" />
<br /><br />


<label for="">内容</label><br />
<textarea name="" rows="10" cols="50"></textarea>
<br /><br />
<input type="hidden" name="" value="" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>