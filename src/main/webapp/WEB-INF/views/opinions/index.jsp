<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>


<c:set var="actOPI" value="${ForwardConst.ACT_OPI.getValue()}"/>
<c:set var="commIdx" value="${FrowardConst.CMD_INDEX.getValue()}"/>
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}"/>
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}"/>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ご意見・ご要望一覧</h2>
        <table id="opinion_list">
            <tbody>
                <tr>
                    <th class="opinion_name">報告者</th>
                    <th class="opinion_date">ご意見を頂戴した日付</th>
                    <th class="opinion_overview">概要</th>
                    <th class="opinion_action">操作</th>
                </tr>
                <c:forEach var="opinion" items="${opinions}" varStatus="status">
                    <fmt:parseDate value="${opinion.opinionDate}" pattern="yyyy-MM-dd" var="opinionDay" type="date" />


                    <tr>
                        <td class="opinion_name"><c:out value="${opinion.employee.name}" /></td>
                        <td class="opinion_date"><fmt:formatDate value='${opinionDay }' pattern='yyyy-MM-dd' /></td>
                        <td class="opinion_overview">${opinion.overview}</td>
                        <td class="opinion_action"><a href="<c:url value='?action=${actOPI}&command=${commShow}&id=${opinion.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <div id="pagination">
            (全 ${opinion_count} 件) <br />
            <c:forEach var="i" begin="1" end="${((opinion_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actOpi}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actOpi}&command=${commNew}' />">新規報告の作成</a></p>

    </c:param>
</c:import>