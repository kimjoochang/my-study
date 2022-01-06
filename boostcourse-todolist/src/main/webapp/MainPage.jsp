<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="MainPage.css">
<title>메인화면</title>
</head>
<body>
	<div class="wrap">
		<header class="header">
		<div class="slant">나의 해야할 일들</div>
		<div class="add-list"><a href= "AddTodo.jsp">새로운 TODO 등록</a></div>
		</header>
		
		<main class="main">
			<article class="title">
				<div class ="title-box1">To Do</div>
				<div class ="title-box2">Doing</div>
				<div class ="title-box3">Done</div>
			</article>
		
			<article class="content">
				<section class="moving-box1">1</section>
				<section class="moving-box2">2</section>
				<section class="done-box3">3</section>
			</article>
		</main>
		
	</div>
</body>
</html>