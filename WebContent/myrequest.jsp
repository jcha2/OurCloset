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
<!-- [if lt IE 9]>
        <script src="html5shiv.js"></script>
   <![endif]-->
</head>
<%
	try {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		String membername = (String) session.getAttribute("membername");

		ResultSet fmr = DBUtil.findMyRequest(conn, membername);
		ResultSet fms = DBUtil.findMySend(conn, membername);
%>
<body>
	<div align="center">
		<h2 style="overflow: hidden;">
			<a href="index.jsp">OUR CLOSET</a>
		</h2>
	</div>
	<div align="right"
		style="border: 2px solid #FFB2D9; height: 5%; overflow: hidden;">
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
	<div
		style="padding: .5%; background-color: #FFB2D9; height: 10%; overflow: hidden;">
		<a href="index.jsp" style="font-size: 1.5em;">BORROW</a> <a
			href="women.jsp">WOMEN</a><a href="men.jsp">MEN</a><a href="etc.jsp">ETC</a>
	</div>
	<div style="padding: .5%; overflow: hidden;">
		<button onclick="location.href='mypage.jsp'">뒤로</button>
	</div>
	<div align="center" style="padding: 3%">
		<%=session.getAttribute("membername")%>님의 메세지함<br />
		<div>
			<%
				if (session.getAttribute("emailid") == null) {
			%><b>로그인부터 해주세요.</b> <a href="login.jsp">로그인 하러 가기</a>
			<%
				} else {
			%>
			<div>
				받은 요청<br />
				<table style="text-align: center;">
					<tr>
						<th style="width: 10%;">고유 번호</th>
						<th style="width: 20%;">내가 빌림 받을 물품</th>
						<th style="width: 10%;">요청자</th>
						<th style="width: 50%;">대화창</th>
					</tr>
					<%
						for (int i = 1; fmr.next(); i++) {
					%>
					<tr style="background: lightgray;">
						<td><%=fmr.getString(3)%></td>
						<td><%=fmr.getString(2)%></td>
						<td><%=fmr.getString(1)%></td>
						<td><form method="get" action="messanger.jsp">
								<input type="hidden" name="sendern"
									value="<%=fmr.getString(1)%>"> <input type="hidden"
									name="items" value="<%=fmr.getString(2)%>"> <input
									type="hidden" name="receivern" value="<%=membername%>">
								<input type="hidden" name="fromn" value="<%=fmr.getString(1)%>">
								<input type="hidden" name="ton" value="<%=membername%>">
								<input type="hidden" name="boardnum"
									value="<%=fmr.getString(3)%>"> <input type="submit"
									value="대화창">
							</form></td>
					</tr>
					<%
						}
					%>

				</table>
			</div>
			<br /> <br />
			<div>
				보낸 요청<br />
				<table style="text-align: center;">
					<tr>
						<th style="width: 10%;">고유 번호</th>
						<th style="width: 20%;">내가 빌려줄 물품</th>
						<th style="width: 10%;">요청자</th>
						<th style="width: 50%;">대화창</th>
					</tr>
					<%
						for (int i = 1; fms.next(); i++) {
					%>
					<tr style="background: lightgray;">
						<td><%=fms.getString(3)%></td>
						<td><%=fms.getString(2)%></td>
						<td><%=fms.getString(1)%></td>
						<td><form method="get" action="messanger.jsp">
								<input type="hidden" name="receivern"
									value="<%=fms.getString(1)%>"> <input type="hidden"
									name="items" value="<%=fms.getString(2)%>"><input
									type="hidden" name="sendern" value="<%=membername%>"><input
									type="hidden" name="fromn" value="<%=membername%>"> <input
									type="hidden" name="ton" value="<%=fms.getString(1)%>">
								<input type="hidden" name="boardnum"
									value="<%=fms.getString(3)%>"> <input type="submit"
									value="대화창">
							</form></td>
					</tr>
					<%
						}
							}
						} catch (

						Exception e) {
							e.printStackTrace();
						}
					%>

				</table>
			</div>

		</div>
	</div>

</body>
</html>
