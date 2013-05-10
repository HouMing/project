<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <body>
    <table id="dg" width="100px" height="100px">
    <div id="toolbar" />  
      <thead>
        <tr >
          <th data-options="field:'userName'" width="150px">学号</th>
          <th data-options="field:'studentName'" width="50px">姓名</th>
          <th data-options="field:'telephone'" width="150px">电话</th>
          <th data-options="field:'email'" width="150px">邮件</th>
          <th data-options="field:'weibo'" width="150px">微博</th>
          <th data-options="field:'introduce'" width="150px">介绍</th>
          <th data-options="field:'classroomName'" width="150px">班级</th>
          <th data-options="field:'departmentName'" width="150px">院系</th>
        </tr>
      </thead>
    </table>
</body>
</html>
 <script type="text/javascript">
    $(function(){
      $('#dg').datagrid(
          {
view: detailview,
title : '学生表',
width: '1000px',
collapsible:true,
pagination : true,
fitColumns : false,
url : 'getStudents',
detailFormatter:function(index,row) {
	return '<div class="ddv"></div>';
},
onExpandRow: function(index,row){
	var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
  ddv.panel({
border:false,
cache:true,
href:'getStudent?userName='+row.userName,
onLoad:function(){
$('#dg').datagrid('fixDetailRowHeight',index);
$('#dg').datagrid('selectRow',index);
$('#dg').datagrid('getRowDetail',index).find('form').form('load',row);
}
});
$('#dg').datagrid('fixDetailRowHeight',index);
$('#dg').datagrid('fixDetailRowHeight',index);
}
});
    });

    function saveItem(index){  
        var row = $('#dg').datagrid('getRows')[index];  
        var url = row.isNewRecord ? 'save_user.php' : 'update_user.php?id='+row.id;  
        $('#dg').datagrid('getRowDetail',index).find('form').form('submit',{  
            url: url,  
            onSubmit: function(){  
                return $(this).form('validate');  
            },  
            success: function(data){  
                data = eval('('+data+')');  
                data.isNewRecord = false;  
                $('#dg').datagrid('collapseRow',index);  
                $('#dg').datagrid('updateRow',{  
                    index: index,  
                    row: data  
                });  
            }  
        });  
    }  
    function cancelItem(index){  
        var row = $('#dg').datagrid('getRows')[index];  
        if (row.isNewRecord){  
            $('#dg').datagrid('deleteRow',index);  
        } else {  
            $('#dg').datagrid('collapseRow',index);  
        }  
    }  
    function destroyItem(){  
        var row = $('#dg').datagrid('getSelected');  
        if (row){  
            $.messager.confirm('Confirm','Are you sure you want to remove this user?',function(r){  
                if (r){  
                    var index = $('#dg').datagrid('getRowIndex',row);  
                    $.post('destroy_user.php',{id:row.id},function(){  
                        $('#dg').datagrid('deleteRow',index);  
                    });  
                }  
            });  
        }  
    }  
    function newItem(){  
        $('#dg').datagrid('appendRow',{isNewRecord:true});  
        var index = $('#dg').datagrid('getRows').length - 1;  
        $('#dg').datagrid('expandRow', index);  
        $('#dg').datagrid('selectRow', index);  
    }

$(function() {
	var add = document.createElement("a");
	var del = document.createElement("a");
	$(add).linkbutton({
		text : '添加学生',
		iconCls : 'icon-add',
		iconAlign : 'left',
		plain : true
	});
	$(add).bind("click", newItem);
	
	$(del).linkbutton({
		text : '删除学生',
		iconCls : 'icon-remove',
		iconAlign : 'left',
		plain : true
	});
	$(del).bind("click", destroyItem);
	$("#toolbar").append(add);
	$("#toolbar").append(del);
});
</script>