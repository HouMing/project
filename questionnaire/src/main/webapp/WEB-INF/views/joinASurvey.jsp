<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <base src="/questionnaire/" />
  <div id="qlist">
    <form id="form_tmp" method="POST" action="addAnswer">
      <input hidden="true" readonly="true" name="survey_oid" value="${survey_oid }"/>
      <input hidden="true" readonly="true" name="user_oid" value="${user_oid }" />
      <c:forEach items="${questions }" var="item" varStatus="i">
      <div class="question">
        <div class="li">
          <span class="lb">问题${i.count}</span><span class="description">${item.description }</span>
        </div>
        <div class="li">
          <input hidden="true" readonly="true" name="oid" value="${item.oid }"/>
          <span class="lb">A.</span><input type="radio" name="${item.oid }" value="itemA">${item.itemA }</radio><br>
          <span class="lb">B.</span><input type="radio" name="${item.oid }" value="itemB">${item.itemB }</radio><br>
          <span class="lb">C.</span><input type="radio" name="${item.oid }" value="itemC">${item.itemC }</radio><br>
          <span class="lb">D.</span><input type="radio" name="${item.oid }" value="itemD">${item.itemD }</radio>
        </div>
      </div>
      </c:forEach>
      <input type="submit" value="提交"></input>
    </form>
  </div>
</html>
<script>
</script>
