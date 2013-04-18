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
        <a class="button" href="javascript:action('edit', '#form_${survey.oid }')">编辑问卷</a>
        <a class="button" href="javascript:action('dele', '#form_${survey.oid }')">删除问卷</a>
        <a class="button" href="javascript:action('sttc', '#form_${survey.oid }')">统计结果</a>
        </p>
      </form>
    </div>
    </c:forEach>
  </div>
    <form id="new_survey">
    <div class="survey">
    <p class="name">调查名称<input type="text" name="name" value="未填写"/></p>
    <a class="name button" href="javascript:new_survey();">新建问卷</a>
    </div>
    </form>
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
function new_survey() {
	$.ajax({
		url:"createSurvey",
		type:"POST",
		data:$("#new_survey").serialize(),
		success:function(data) {
			var ret = JSON.parse(data);
			if (ret.result === "保存成功") {
				 $.ajax({
					 url:"manage",
					 type:'GET',
					 success:function(data){
						 $("#main").empty();
						 $("#main").append(data);
					 }
				 });
			} else {
				alert(ret.msg);
			}
		}
	});
}

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