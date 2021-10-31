<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*, model.DBUtil, java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<base href="OurCloset"></base>
<title>OUR CLOSET</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/ourcloset.css" />
<script>
	function check() {
		var check = confirm("삭제하시겠습니까?");
		if (check) {
			alert("삭제 되었습니다.");
			return check;
		} else {
			alert("삭제취소 되었습니다.");
			return check;
		}
	}
</script>
<!-- [if lt IE 9]>
        <script src="html5shiv.js"></script>
   <![endif]-->
</head>
<%
	try {
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String emailid = (String) session.getAttribute("emailid");
		ResultSet rs = DBUtil.getMyPosting(conn, emailid);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
%>
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
		My Postings<br /> <br />
		<div style="margin: 1%">
			<table style="text-align: center;">
				<tr>
					<th style="width: 5%;">고유번호</th>
					<th style="width: 5%;">카테고리</th>
					<th style="width: 10%;">ID</th>
					<th style="width: 20%;">빌리고 싶은 물품</th>
					<th style="width: 10%;">가격</th>
					<th style="width: 20%;">빌릴 기간</th>
					<th style="width: 5%;">삭제</th>

				</tr>
				<%
					for (int i = 1; rs.next(); i++) {
				%>
				<tr style="background: lightgray;">
					<td><%=rs.getString(8)%></td>
					<td><%=rs.getString(1)%></td>
					<td><%=rs.getString(2)%></td>
					<td><%=rs.getString(3)%></td>
					<td><%=rs.getString(4)%></td>
					<td><%=rs.getString(5)%> ~ <%=rs.getString(6)%></td>
					<td>
						<form method="post" action="erasePost" onsubmit="return check()">
							<input type="hidden" name="postnum" value="<%=rs.getString(8)%>">
							<input type="submit" value="DELETE">
						</form>
					</td>

				</tr>
				<%
					}
				%>
			</table>
		</div>
		<%
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>

	</div>

</body>
</html>
