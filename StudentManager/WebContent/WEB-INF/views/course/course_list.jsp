<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>管理员列表</title>
	<link rel="stylesheet" type="text/css" href="../static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../static/easyui/css/demo.css">
	<script type="text/javascript" src="../static/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../static/easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		var table;
		
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'专业列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"get_list?t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'id',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true}, 
 		        {field:'cno',title:'课程编号',width:150,sortable:true},
 		        {field:'tid',title:'开课教师id',width:150},
 		        {field:'name',title:'课程名称',width:150},
 		        {field:'intro',title:'课程介绍',width:400}
	 		]], 
	        toolbar: "#toolbar"
	    }); 
		
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	    
	    //设置工具类按钮
	    $("#add").click(function(){
	    	table = $("#addTable");
	    	$("#addDialog").dialog("open");
	    });
	    //修改
	    $("#edit").click(function(){
	    	table = $("#editTable");
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	    
	  	//设置添加窗口
	    $("#addDialog").dialog({
	    	title: "添加专业",
	    	width: 350,
	    	height: 350,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-note',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var data=$("#addForm").serialize();
							$.ajax({
								type: "post",
								url: "add",
								data: {'cno':$("#add_cno").val(),'teacher_jno':$("#teacher_jno").val(),
									'name':$("#add_name").val(),'intro':$("#add_intro").val()},
								dataType:"json",
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_cno").textbox('setValue', "");
										$("#add_tid").textbox('setValue',"");
										$("#add_name").textbox('setValue', "");
										$("#add_intro").textbox('setValue', "");
										
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
										
									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
			],
			onClose: function(){
				$("#add_cno").textbox('setValue', "");
				$("#add_tid").textbox('setValue',"");
				$("#add_name").textbox('setValue', "");
				$("#add_intro").textbox('setValue', "");
			
			}
	    });
	  	
	  	
	  $("#search").click(function(){
		  $('#dataList').datagrid('reload',{
			  name:$("#search_course").val()
		  })
	  });
	  
	  
	 	//设置编辑窗口
	    $("#editDialog").dialog({
	    	title: "修改专业信息",
	    	width: 350,
	    	height: 350,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-edit',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "edit",
								data: $("#editForm").serialize(),
								dataType:"json",
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","更新成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//刷新表格
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");
										
							  			
									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				}
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_id").val(selectRow.id);
				$("#edit_cno").textbox('setValue', selectRow.cno);
				$("#edit_tid").textbox('setValue',selectRow.tid);
				$("#edit_name").textbox('setValue', selectRow.name);
				$("#edit_intro").textbox('setValue',selectRow.intro);
			}
	    });
	   
	    //删除
	    $("#delete").click(function(){
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	var selectLength = selectRows.length;
        	if(selectLength == 0){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var ids = [];
            	$(selectRows).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "将删除与此专业的数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "delete",
							data: {ids: ids},
							dataType:"json",
							success: function(data){
								if(data.type == "success"){
									$.messager.alert("消息提醒","删除成功","info");
									//刷新表格
									$("#dataList").datagrid("reload");
									$("#dataList").datagrid("uncheckAll");
								} else{
									$.messager.alert("消息提醒",data.msg,"warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
			<div style="float: left;" class="datagrid-btn-separator"></div>
		<div><input type="text" id="search_course" class="easyui-textbox"  name="search_course"/>
			<a id="search" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a></div>
	</div>
	
	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px;">  
   		<form id="addForm" method="post">
	    	<table id="addTable" border=0 style="width:800px; table-layout:fixed;" cellpadding="6" >
	    		<tr>
	    			<td style="width:40px">课程编号:</td>
	    			<td colspan="3">
	    				<input id="add_cno"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cno" data-options="required:true, validType:'repeat', missingMessage:'请输入课程编号' ,validType:'integer'" />
	    			</td>
	    			<td style="width:80px"></td>
	    		</tr>
	    		<tr>
	    			<td>开课教师编号:</td>
	    			<td colspan="4">
 	    				<input id="teacher_jno" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="teacher_jno" data-options="required:true, missingMessage:'请填写教师编号' " />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>课程名称:</td>
	    			<td colspan="4"><input id="add_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name"/></td>
	    		</tr>
	    		<tr>
	    			<td>简介:</td>
	    			<td colspan="4"><input id="add_intro" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="intro"/></td>
	    		</tr>
	    	</table>
	    </form>
	</div>


	
	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px"> 
    	<form id="editForm" method="post">
    	<input type="hidden" name="id" id="edit_id"/>
	    	<table id="editTable" border=0 style="width:800px; table-layout:fixed;" cellpadding="6" >
	    		<tr>
	    			<td style="width:40px">课程编号:</td>
	    			<td colspan="3"><input id="edit_cno" data-options="readonly: true" class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cno" data-options="required:true, missingMessage:'请输入课程编号' " /></td>
	    			<td style="width:80px"></td>
	    		</tr>
	    		<tr>
	    			<td>开课教师id:</td>
	    			<td><input id="edit_tid" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="tid" data-options="required:true, missingMessage:'请填写开课教师id'" /></td>
	    		</tr>
	    		<tr>
	    			<td>课程名称:</td>
	    			<td><input id="edit_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'请填写课程名称'" /></td>
	    		</tr>
	    		<tr>
	    			<td>简介:</td>
	    			<td><input id="edit_intro" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="intro"/></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	
</body>
</html>