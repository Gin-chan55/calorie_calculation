<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h1>新規登録</h1>
            <p>
            アカウントをお持ちですか？
            <button type="submit">こちらからログイン</button>
            </p>

            <label for="email">メールアドレス</label><br />
            <input type="email" id="email"/><br />
            <br /><br />

           <label for="password">パスワード</label><br />
           <input type="password" />
           <br /><br />

           <label for="password">パスワード再入力</label><br />
           <input type="password" />
           <br /><br />

           <label for="tentacles">身長</label><br />
           <input type="number" >
           <br /><br />

           <label for="tentacles">体重</label><br />
           <input type="number" >
           <br /><br />

        <button type="submit">登録</button>

    </c:param>
</c:import>