<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>


<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2>ご意見・ご要望一覧</h2>

        <table id="">
            <tbody>
                <tr>
                    <th class="">報告者</th>
                    <th class="">ご意見を頂戴した日付</th>
                    <th class="">概要</th>
                    <th class="">操作</th>
                </tr>

            </tbody>
        </table>

        <p><a href="<c:url value='' />">新規報告の作成</a>
    </c:param>
</c:import>