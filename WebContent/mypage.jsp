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
	<div align="right" style="border: 2px solid #FFB2D9; height: 5%;overflow: hidden;">
		<%
			if (session.getAttribute("emailid") == null) {
		%><a href="login.jsp">LOGIN</a> | <a href="join.jsp">JOIN US</a>
		<%
			} else {
		%>
		Welcome,
		<%=session.getAttribute("membername")%>
		(<%=session.getAttribute("emailid")%>)
		<button onclick="location.href='mypage.jsp'">MY PAGE</button>
		<form method="post" action="doLogout" style="display: inline;">
			<input type="submit" value=" LOGOUT " />
		</form>
		<%
			}
		%>
	</div>
	<div style="padding: .5%; background-color: #FFB2D9; height: 10%;overflow: hidden;">
		<a href="index.jsp" style="font-size: 1.5em;">BORROW</a> <a
			href="women.jsp">WOMEN</a><a href="men.jsp">MEN</a><a href="etc.jsp">ETC</a>
	</div>
	<div align="center" style="padding: 3%">
		My Page<br /> <br />
		<div style="border: 2px solid #000000; padding: 5%">
			<%
				if (session.getAttribute("emailid") == null) {
			%><b>로그인부터 해주세요.</b> <a href="login.jsp">로그인 하러 가기</a>
			<%
				} else {
			%><button onclick="location.href='myrequest.jsp'">나의 메세지함</button>
			<button onclick="location.href='myposting.jsp'">내가 쓴 글</button>
			<br /> ID:
			<%=session.getAttribute("emailid")%>
			<br />Nickname:
			<%=session.getAttribute("membername")%>
			<br /> Phone:
			<%=session.getAttribute("cphone")%>
			<br /> Address:
			<%=session.getAttribute("address")%>
			<%=session.getAttribute("zip")%>
			<br />
			<button onclick="location.href='mypageedit.jsp'">내 정보 수정</button>
			<%
				}
			%>
		</div>
	</div>

</body>
</html>
