<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.ForwardConst" %>


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>ご意見・ご要望 報告ページ</h2>


        <form method="" action="<c:url value='?action= &command= ' />">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='?action= &command= ' />">一覧に戻る</a></p>
    </c:param>
</c:import>
