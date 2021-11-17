<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>


<c:set var="actCom" value="${ForwardConst.ACT_COM.getValue()}"/>
<c:set var="actOpi" value="${ForwardConst.ACT_OPI.getValue()}"/>
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}"/>
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}"/>
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}"/>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>コメント 一覧</h2>
        <table id="comment_list">
            <tbody>
                <tr>
                    <th class="opinion_name">報告者</th>
                    <th class="opinion_date">ご意見を頂戴した日付</th>
                    <th class="opinion_overview">概要</th>
                    <th class="comment_name">コメント作成者</th>
                    <th class="comment_content">コメント内容</th>
                    <th class="comment_action">操作</th>
                </tr>
               <c:forEach var="comment" items="${comments}" varStatus="status">
                <fmt:parseDate value="${comment.opinion.opinionDate}" pattern="yyyy-MM-dd" var="opinionDay" type="date" /> <%--文字列を日付データに変換 --%>


                <tr>
                    <td class="opinion_name"><c:out value="${comment.opinion.employee.name}" /></td>
                    <td class="opinion_date"><fmt:formatDate value='${opinionDay}' pattern='yyyy-MM-dd' /></td>
                    <td class="opinion_overview">${comment.opinion.overView}></td>
                    <td class="comment_name"><c:out value="${comment.employee.name}" /></td>
                    <td class="comment_content">${comment.content}</td>
                    <td class="comment_action">
                        <c:choose>
                            <c:when test="${comment.opinion.deleteFlag == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()}">
                                （報告が削除されています）
                            </c:when>
                            <c:otherwise>
                                <a href=" <c:url value='?action=${actOpi}&command=${commShow}&id=${comment.opinion.id}' />">報告内容・コメントの詳細を見る</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
               </c:forEach>
            </tbody>
        </table>


        <div id="pagination">
            (全 ${comments_count} 件) <br />
            <c:forEach var="i" begin="1" end="${((comments_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actCom}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>