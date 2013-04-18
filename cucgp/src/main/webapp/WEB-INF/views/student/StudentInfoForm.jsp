<%@ page contentType="text/html;charset=UTF-8"%>
<form method="post">
	<table class="dv-table" style="width:100%;border:1px solid #ccc;padding:5px;margin-top:5px;">
		<tr>
			<td>学号</td>
			<td><input readonly="true" name="userName" class="easyui-invalidatebox"></input></td>
			<td>姓名</td>
			<td><input name="studentName" class="easyui-validatebox"></input></td>
		</tr>
		<tr>
			<td>班级</td>
			<td><input name="classroomName"></input></td>
			<td>院系</td>
			<td><input name="departmentName" class="easyui-validatebox"></input></td>
		</tr>
		<tr>
			<td>电话</td>
			<td><input name="telephone"></input></td>
			<td>邮件</td>
			<td><input name="email" class="easyui-validatebox" validType="email"></input></td>
		</tr>
		<tr>
			<td>微博</td>
			<td><input name="weibo" class="easyui-validatebox"></input></td>
		</tr>
		<tr>
		  <td>介绍</td>
		  <td>
		  <input name="introduce" class="easyui-validatebox" type="text" style="width:200px;"></input>
		  </td>
		</tr>
	</table>
	<div style="padding:5px 0;text-align:right;padding-right:30px">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveItem(1)">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelItem(1)">Cancel</a>
	</div>
</form>