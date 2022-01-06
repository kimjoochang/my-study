<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="Addtodo.css">
<title>할일등록</title>
</head>
<body>
	<div class= "wrap">
		<article class="add-title">할일 등록</article>
		<article class="input">
			<form name="todolist" action="./AddServlet" method="post">
				어떤일인가요?<br>
				<input type="text" name="todo" size="40" maxlength="24"><br>
				<br>
				
				누가 할일인가요?<br>
				<input type="text" name="name" size="10" maxlength="6"><br>
				<br>
				우선순위를 선택하세요<br>
				<input type = "radio" name="priority" value="1">1순위
				<input type = "radio" name="priority" value="2">2순위
				<input type = "radio" name="priority" value="3">3순위
				<br>
				<input type = 'submit'>
			</form>
		</article>
		<article class="lastbox">
			<div class="before"><a href= "MainPage.jsp">< 이전</a></div>
			<div class="submit">제출</div>
			<div class="clear">내용 지우기</div>
		</article>
	</div>
</body>
</html>