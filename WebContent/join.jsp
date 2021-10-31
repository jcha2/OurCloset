<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<base href="OurCloset"></base>
<title>OUR CLOSET</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ourcloset.css" />
<!-- [if lt IE 9]>
        <script src="html5shiv.js"></script>
   <![endif]-->
</head>
<body>
	<div align="center">
		<h2 style="overflow: hidden;">
			<a href="index.jsp">OUR CLOSET</a>
		</h2>
	</div>
	<div align="right" style="border: 2px solid #FFB2D9; overflow: hidden;">
		<a href="login.jsp">LOGIN</a> | <a href="join.jsp">JOIN US</a>
	</div>
	<div
		style="padding: .5%; background-color: #FFB2D9; height: 10%; overflow: hidden;">
		<a href="index.jsp" style="font-size: 1.5em;">BORROW</a> <a
			href="women.jsp">WOMEN</a><a href="men.jsp">MEN</a><a href="etc.jsp">ETC</a>
	</div>
	<div align="center" style="padding: 3%">
		JOIN IN: <br /> <br />
		<div style="border: 2px solid #000000; padding: 3%">
			<form method="post" action="doJoin">
				*ID(EMAIL) : <input type="email" name="emailid" /> <br />
				*PASSWORD : <input type="password" name="passwd" /><br /> *CONFIRM
				PASSWORD : <input type="password" name="passwd2" /><br />
				*NICKNAME : <input type="text" name="membername" /><br /> PHONE
				NUMBER : <input type="tel" name="cphone" /><br /> ADDRESS : <input
					type="text" name="address" /><br /> ZIP : <input type="number"
					name="zip" /><br /> <br /> <input type="reset" value="RESET" />
				<input type="submit" value="JOIN" /> <br /> <br />
			</form>
			<a href="index.jsp">cancel</a>
		</div>
	</div>
</body>
</html>
