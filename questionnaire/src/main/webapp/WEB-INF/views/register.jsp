<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <base src="/questionnaire/" />
  <body>
    <div class="form">
    <form action="register" method="POST">
      <p>
      <label class="input text"> 登录名 </label>
      </p>
      <p>
      <input name="username" type="text" />
      </p>
      <p>
      <label class="input text"> 密码 </label>
      </p>
      <p>
      <input name="password" type="password" />
      </p>
      <label class="input text"> 密码 </label>
      </p>
      <p>
      <input name="password" type="password" />
      </p>
      <p>
      <input class="submit text" type="submit" value="注册" />
      </p>
    </form>
  </div>
  </body>
</html>
