<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>


<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actOpi" value="${ForwardConst.ACT_OPI.getValue()}" />

<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id ="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報管理システムへようこそ</h2>
        <h3>【自分の日報 一覧】</h3>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_action"><a href="<c:url value='?action=${actRep}&command=${commShow}&id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actTop}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actRep}&command=${commNew}' />">新規日報の登録</a></p>
        <br /><br />


        <h3>【自分の報告 一覧】</h3>
        <table id="opinion_list">
            <tbody>
                <tr>
                    <th class="opinion_name">報告者</th>
                    <th class="opinion_date">ご意見を頂戴した日付</th>
                    <th class="opinion_overView">概要</th>
                    <th class="opinion_action">操作</th>
                </tr>
                <c:forEach var="opinion" items="${opinions}" varStatus="status">
                    <fmt:parseDate value="${opinion.opinionDate}" pattern="yyyy-MM-dd" var="opinionDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="opinion_name"><c:out value="${opinion.employee.name}" /></td>
                        <td class="opinion_date"><fmt:formatDate value="${opinionDay}" pattern='yyyy-MM-dd' /></td>
                        <td class="opinion_overView">${opinion.overView}</td>
                        <td class="opinion_action">
                            <c:choose>
                                <c:when test="${opinion.deleteFlag == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='?action=${actOpi}&command=${commShow}&id=${opinion.id}' />">詳細を見る</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${opinions_count} 件）<br />
            <c:forEach var ="i" begin="1" end="${((opinions_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actTop}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='?action=${actOpi}&command=${commNew}' />">新規報告の作成</a></p>

<c:if test="${sessionScope.login_employee.adminFlag == AttributeConst.ROLE_ADMIN.getIntegerValue()}">
        <h3>自分が作成したコメント 一覧</h3>
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
                    <td class="opinion_overview"><c:out value="${comment.opinion.overView}" /></td>
                    <td class="comment_name"><c:out value="${comment.employee.name}" /></td>
                    <td class="comment_content"><div class="comment_width"><c:out value="${comment.content}" /></div></td>
                    <td class="comment_action">
                        <c:choose>
                            <c:when test="${comment.opinion.deleteFlag == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()}">
                                <p>(報告が削除されています)</p>
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
</c:if>
    </c:param>
</c:import>