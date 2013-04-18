<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <base src="/questionnaire/" />
  <div id="qlist">
    <c:forEach items="${questions }" var="item" varStatus="i">
    <div class="question">
      <form id="form_${item.oid }">
        <div class="li">
          <span class="lb">问题${i.count}</span><input class="description" name="description" type="text" value="${item.description }" />
        </div>
        <div class="li">
          <span class="lb">A.</span><input class="item" name="itemA" type="text" value="${item.itemA }" />
        </div>
        <div class="li">
          <span class="lb">B.</span><input class="item" name="itemB" type="text" value="${item.itemB }" />
        </div>
        <div class="li">
          <span class="lb">C.</span><input class="item" name="itemC" type="text" value="${item.itemC }" />
        </div>
        <div class="li">
          <span class="lb">D.</span><input class="item" name="itemD" type="text" value="${item.itemD }" />
        </div>
        <div class="li">
          <a class="bt" href="javascript:updateQuestion('#form_${item.oid }')">保存更改 </a>
          <a class="bt" href="javascript:deleteQuestion('#form_${item.oid }')">删除问题 </a>
        </div>
        <input hidden="true" readonly="true" name="oid" value="${item.oid }"/>
        <input hidden="true" readonly="true" name="survey_oid" value="${survey_oid }"/>
      </form>
    </div>
    </c:forEach>
  </div>
  <div class="question" style="hidden:true;display:none;">
    <form class="form_hidden">
      <div class="li">
        <span class="lb">问题</span><input class="description" name="description" type="text" value="这是一个问题" />
      </div>
      <div class="li">
        <span class="lb">A.</span><input class="item" name="itemA" type="text" value="A" />
      </div>
      <div class="li">
        <span class="lb">B.</span><input class="item" name="itemB" type="text" value="B" />
      </div>
      <div class="li">
        <span class="lb">C.</span><input class="item" name="itemC" type="text" value="C" />
      </div>
      <div class="li">
        <span class="lb">D.</span><input class="item" name="itemD" type="text" value="D" />
      </div>
      <div class="li">
        <a class="bt" href="javascript:updateQuestion()">保存更改 </a>
      </div>
      <input hidden="true" readonly="true" name="oid" value="-1"/>
      <input hidden="true" readonly="true" name="survey_oid" value="${survey_oid}"/>
    </form>
  </div>
  <button onclick="javascript:addForm()">新建问题</button>
</html>
<script>
  function addForm() {
  var form = $(".form_hidden").clone();
  form.attr("id", "form_new");
  form.style = "display:inline";
  $("#qlist").append(form);
  }
  function updateQuestion(form) {
  if (form == undefined) {
	  $.ajax({
		  url: "updateQuestion",
		  type: "POST",
		  data: $("#form_new").serialize(),
		  success: function(data) {
			  alert(data);
			  $("#form_new").empty();
		  }
	  })
  } else {
  $.ajax({
  url: "updateQuestion",
  type: "POST",
  data: $(form).serialize(),
  success: function(msg) {
  alert(msg);
  }
  });
  }
  }

  function deleteQuestion(form) {
  $.ajax({
  url: "deleteQuestion",
  type: "POST",
  data: $(form).serialize(),
  success: function(msg) {
  alert(msg);
  }
  });
  }
</script>
