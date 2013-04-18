<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <base src="/questionnaire/" />
  <body>
    <c:forEach items="${surveys}" var="survey">
    <div class="survey">
      <form id="form_${survey.oid }">
        <input name="oid" type="text" value="${survey.oid}" readonly="true" hidden="true"/>
        <input name="name" type="text" value="${survey.name}" readonly="true" hidden="true"/>
        <p class="name"><label name="name">${survey.name}</label></p>
        <p class="name">
        <label name="name">问题总数:<strong>${survey.lq.size()}</strong></label>
        <label name="name">参与人数:<strong>${survey.lr.size()}</strong></label>
        </p>
        <p class="name">
        <a class="button" href="javascript:action('join', '#form_${survey.oid }')">填写问卷</a>
        </p>
      </form>
    </div>
    </c:forEach>
  </div>
</body>
<script>
function action(ac, form) {
	 $.ajax({
		 url:ac,
		 type:'POST',
		 data:$(form).serialize(),
		 success:function(data){
			 $("#main").empty();
			 $("#main").append(data);
		 }
	 });
}
</script>
</html>