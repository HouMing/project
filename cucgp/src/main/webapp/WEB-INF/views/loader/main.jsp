<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <%@ include file="../header.jsp"%>
  <body>
    <div class="easyui-layout" style="width: 1340px; height: 900px">
      <div id="info-box" data-options="region:'north',title:'个人状态，系统状态，短消息等',split:true" style="width: 100px; height: 100px"></div>
      <div id="container" data-options="region:'center',title:'',split:true" style="width: 100px; height: 100px"></div>
      <div id="ac-box" class="menu-content" data-options="region:'west',title:'',split:true" style="width: 200px; height: 100px">
        <ul id="actions">
        </ul>
      </div>
    </div>
  </div>
</body>
</html>
<script>
$(document).ready(function() {
    var actions = JSON.parse('${actions}');
    for ( var t in actions) {
      newAction(actions[t]);
    }
});
</script>
