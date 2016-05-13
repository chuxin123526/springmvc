<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/jquery-1.11.2.js"></script>
</head>
<script type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function init() 
	{
		var url = "http://localhost:8080/springmvc/example/user/testJson";
		$.ajax({
			url:url ,
			contentType : "application/json",
			data : "{'id':'1','name':'believe'}" , 
			type:"POST" , 
			dataType : "json" ,
			success : function(data)
			{
				alert(data.id) ; 
				alert(data.name) ; 
			}
		}

		);
		
	
		
	}

	);
</script>
<body>
	<div class="div" id="div"></div>

</body>
</html>