<%@ page contentType = "text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp"%>
<body>
	<div id="loginWin" class="easyui-window" title="login"
		style="width: 350px; height: 188px; padding: 5px;" minimizable="false"
		maximizable="false" resizable="false" collapsible="false">
		<div class="easyui-layout" fit="true">
			<form id="loginForm">
				<div region="center" border="false"
					style="padding: 5px; background: #fff; border: 1px solid #ccc;">
					<div style="padding: 5px 0;">
						<label for="login">username:</label> <input type="text"
							name="userName"></input>
					</div>
					<div style="padding: 5px 0;">
						<label for="password">password:</label> <input type="password"
							name="password" />
					</div>
					<div style="padding: 5px 0; text-align: center; color: red;"
						id="showMsg" />
				</div>
				<div region="south" border="false"
					style="text-align: right; padding: 5px 0;">
					<a class="easyui-linkbutton" iconCls="icon-ok" onclick="login()">login</a>
				</div>
		</div>
		</form>
	</div>
	<div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
</html>
<script>
	function login() {
		if ($("input[name='userName']").val() == ""
				|| $("input[name='password']").val() == "") {
			$("#showMsg").html("用户名或密码为空，请输入");
			$("input[name='userName']").focus();
		} else {
			$("input[name='password']").attr("value", hex_md5($("input[name='password']").val()));
			$.ajax({
				type : "POST",
				url : "login.ac",
				data : $("#loginForm").serialize(), //序列化               
				error : function(response) {
					$.messager.alert('Warning', response.responseText);
				},
				success : function(data) {
        json = JSON.parse(data);
        if(json.result == "yes") {
					window.location = "com/main.ac";
        } else {
        	window.reload();
        }
				}
			});
		}
	}
</script>
