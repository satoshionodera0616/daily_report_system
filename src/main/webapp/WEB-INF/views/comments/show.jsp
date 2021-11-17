<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actOpi" value="${ForwardConst.ACT_OPI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">


        <h2> 詳細ページ</h2>


        <table>
            <tbody>
                <tr>
                    <th>報告者</th>
                    <td><c:out value="${opinion.employee.name}" /></td>
                </tr>
                <tr>
                    <th>ご意見を頂戴した日付</th>
                    <fmt:parseDate value="${opinion.opinionDate}" pattern="yyyy-MM-dd" var="opinionDay" type="date"/>
                    <td><fmt:formatDate value='${opinionDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>概要</th>
                    <td><c:out value="${opinion.overView}" /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${opinion.content}" /></pre></td> <%--pre 改行を改行のままで表示 中の文字列表示が小文字になる --%>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${opinion.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${opinion.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>


        <c:if test="${sessionScope.login_employee.id == opinion.employee.id}">
            <p>
                <a href="<c:url value='?action=${actOpi}&command=${commEdt}&id=${opinion.id}' />">報告内容を編集する</a>
            </p>
        </c:if>
        <br /><br />


        <h3>【この報告に対するコメント 一覧】</h3>
        <table>
            <tbody>
                <tr>
                    <th>作成者</th>
                    <th>コメントされた日付</th>
                    <th>コメント</th>
                    <th>操作</th>
                </tr>
            </tbody>
        </table>


        <p>
            <a href="<c:url value='?action=${actOpi}&command=${commIdx}' />">一覧に戻る</a>
        </p>

    </c:param>
</c:import>