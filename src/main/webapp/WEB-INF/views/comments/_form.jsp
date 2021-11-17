<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>


<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>


    </div>
</c:if>
<br /><br />

<label for="${AttributeConst.COM_CONTENT.getValue()}">報告内容についてコメント</label><br />
<textarea name="${AttributeConst.COM_CONTENT.getValue()}" rows="10" cols="50">${comment.content}</textarea>
<br /><br />

<input type="hidden" name="${AttributeConst.OPI_ID.getValue()}" value="${opinion.id}" />
<input type="hidden" name="${AttributeConst.COM_ID.getValue()}" value="${comment.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>