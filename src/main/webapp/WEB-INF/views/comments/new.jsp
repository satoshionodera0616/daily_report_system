<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>


<c:set var="actCOM" value="${ForwardConst.ACT_COM.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>コメント 記入ページ</h2>
            <table id="opinion_list" >
                <tr><th class="opinion_name_comment_new">報告者</th><td><c:out value="${opinion.employee.name}" /></td></tr>

                <tr><th class="opinion_date_comment_new">ご意見を頂戴した日付</th>
                <fmt:parseDate value="${opinion.opinionDate}" pattern="yyyy-MM-dd" var="opinionDay" type="date"/>
                <td><fmt:formatDate value='${opinionDay}' pattern='yyyy-MM-dd' /></td>
                </tr>

                <tr><th class="opinion_overview_comment_new">概要</th><td><c:out value="${opinion.overView}" /></td></tr>

                <tr><th class="opinion_content_comment_new">内容</th><td><pre><c:out value="${opinion.content}" /></pre></td></tr>
                <tr><th class="opinion_createdat_comment_new">登録日時</th>
                <fmt:parseDate value="${opinion.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>

                <tr><th class="opinion_updatedat_comment_new">更新日時</th>
                <fmt:parseDate value="${opinion.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </table>


        <form method="POST" action="<c:url value='?action=${actCOM}&command=${commCrt}' />">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="<c:url value='?action=${actCOM}&command=${commIdx}' />">一覧に戻る</a></p>
    </c:param>
</c:import>