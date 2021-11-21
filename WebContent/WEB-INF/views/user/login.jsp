<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError }">
            <div id="flush_error">
                メールアドレスかパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>
        <h2>ログイン</h2>
        <form method="POST" action="${pageContext.request.contextPath}/login">
            <label for="address">メールアドレス</label>
            <br />
            <input type="email" id="address" name="address" />
            <br />
            <br />
            <label for="password">パスワード</label>
            <br />
            <input type="password" name="password" />
            <br />
            <input type="hidden" name="_token" value="${_token }" />
            <button type="submit">ログイン</button>
        </form>
        <form method="GET" action="${pageContext.request.contextPath}/new">
            <button type="submit">新規登録</button>
        </form>
    </c:param>
</c:import>