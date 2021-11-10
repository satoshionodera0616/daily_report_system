<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actOpi" value="${ForwardConst.ACT_OPI.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">


        <h2>ご意見・ご要望 編集ページ</h2>
        <form method="POST" action="<c:url value='?action=${actOpi}&command=${commUpd}' />">
            <c:import url="_form.jsp" />
        </form>


        <p>
            <a href="#" onclick="confirmDestroy();">この報告を削除する</a>
        </p>
        <form method="POST" action="<c:url value='?action=${action}&command=${commDel}' />">
            <input type="hidden" name="${AttributeConst.OPI_ID.getValue()}" value="${opinion.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        </form>
        <script>
            function confirmDestroy(){
                if(confirm("本当に削除してよろしいですか？")){
                    document.forms[1].submit();
                }
            }
        </script>


        <p>
            <a href="<c:url value='?action=Opinion&command=index' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>