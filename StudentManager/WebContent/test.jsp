<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="static/easyui/css/demo.css">
	<script type="text/javascript" src="static/easyui/jquery.min.js"></script>

<!-- 	<script type="text/javascript" src="static/easyui/js/test.js"></script> -->
	<script type="text/javascript">
		$(function(){

		})
	</script>
</head>
<body>
	<form id="testForm">
		<input class="easyui-textbox" type="text" data-options="validType:'integer'" />
		<input type="button" value="提交" id="testButton"/>
			<input type="radio" name="search_key" value="teacher_name"  checked="checked"/>姓名
			<input type="radio" name="search_key" value="teacher_jno"/>编号	
	</form>
	
	<div>

	</div>
	
	<script type="text/javascript">
		$(function(){
			$('input:radio[name="search_key"]').click(function(){
				var checkValue = $('input:radio[name="search_key"]:checked').val(); 
// 				$(this).prop("checked",true);
// 				$(this).siblings().removeProp("checked");
				$(this).attr("checked","checked");
				$(this).siblings().removeAttr("checked");
				alert(checkValue);
			});
			
			
			 var checkedValue=$("input[name='search_key']:checked").val();
				
				$("#testButton").click(function(){
					alert(checkedValue);
				});
		})
	</script>
</body>
</html>