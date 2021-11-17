<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actOpi" value="${ForwardConst.ACT_OPI.getValue()}" />
<c:set var="actCom" value="${ForwardConst.ACT_COM.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">


        <h2>ご意見・ご要望 詳細ページ</h2>


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
            <p>
                <a href="<c:url value='?action=${actOpi}&command=${commIdx}' />">ご意見・ご要望一覧に戻る</a>
            </p>
        <br /><br />


        <h3>【この報告に対するコメント 一覧】</h3>
        <table id="comment_list">
            <tbody>
                <tr>
                    <th>作成者</th>
                    <th>コメント</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="comment" items="${comments}" varStatus="status">
                    <tr class="row${status.count % 2}">

                        <td><c:out value="${comment.employee.name}" /></td>
                        <td>${comment.content}</td>
                        <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()
                            || sessionScope.login_employee.id == comment.employee.id} ">
                            <td><a href="<c:url value='?action=${actCom}&command=${commEdt}&id=${comment.id}' />">このコメントを編集する</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
            <p>
                <a href="<c:url value='?action=${actCom}&command=${commNew}&id=${opinion.id}' />">この報告にコメントする</a>
            </p>
            <p>
                <a href="<c:url value='?action=${actCom}&command=${commIdx}' />">コメント一覧に戻る</a>
            </p>
        </c:if>
    </c:param>
</c:import>