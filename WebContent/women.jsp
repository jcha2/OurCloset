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
		var check = confirm("요청을 보내시겠습니까?");
		if (check) {
			alert("요청을 보냈습니다.");
			return check;
		} else {
			alert("요청을 보내지 않았습니다.")
			return check;
		}
	}

	function login() {
		alert("요청을 보내시려면 로그인이 필요합니다.");
		location.replace("login.jsp");
	}

	function erase() {
		var erase = confirm("삭제하시겠습니까?");
		if (erase) {
			alert("삭제 되었습니다.");
			return erase;
		} else {
			alert("삭제 취소 되었습니다.");
			return erase;
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ResultSet rs = DBUtil.getWomenPosting(conn);
		String sendern = (String) session.getAttribute("membername");
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
	<div
		style="padding: .5%; background-color: #FFB2D9; height: 10%; overflow: hidden;">
		<a href="index.jsp" style="font-size: 1.5em;">BORROW</a> <a
			href="women.jsp">WOMEN</a><a href="men.jsp">MEN</a><a href="etc.jsp">ETC</a>
	</div>
	<div style="padding: .5%;">
		빌려주세요>>>카테고리>>><a href="women.jsp">여성</a>
		<form method="get" action="searchResult.jsp">
			<input type="hidden" name="searchCate" value="여성" /> <input
				type="search" placeholder="물품을 검색하세요." name="search" /><input
				type="submit" value="검색" />
		</form>
	</div>
	<div align="right" style="height: 5%; overflow: hidden;">
		<a href="boardwrite.jsp">글쓰기</a>
	</div>
	<div>
		<table style="text-align: center;">
			<tr>
				<th style="width: 5%;">고유번호</th>
				<th style="width: 5%;">카테고리</th>
				<th style="width: 10%;">ID</th>
				<th style="width: 20%;">빌리고 싶은 물품</th>
				<th style="width: 10%;">가격</th>
				<th style="width: 20%;">빌릴 기간</th>
				<th style="width: 5%;">요청</th>
			</tr>
			<%
				for (int i = 1; rs.next(); i++) {
						String emailid = (String) session.getAttribute("emailid");
			%>
			<tr style="background: lightgray;">
				<td><%=rs.getString(8)%></td>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getString(5)%> ~ <%=rs.getString(6)%></td>
				<td>
					<%
						ResultSet fr = DBUtil.findRequest(conn, rs.getString(3), sendern, rs.getString(2));
								if (session.getAttribute("emailid") != null) {
									if (fr.next()) {
					%>SENT<%
						} else {
										if (!emailid.equals(rs.getString(7))) {
					%>
					<form method="post" action="sendFirstRequest"
						onsubmit="return check()">
						<input type="hidden" name="items" value="<%=rs.getString(3)%>">
						<input type="hidden" name="receivern" value="<%=rs.getString(2)%>">
						<input type="hidden" name="sendern" value="<%=sendern%>">
						<input type="hidden" name="fromn" value="<%=sendern%>"> <input
							type="hidden" name="ton" value="<%=rs.getString(2)%>"> <input
							type="hidden" name="boardnum" value=<%=rs.getString(8)%>>
						<input type="submit" value="보내기">
					</form> <%
 	} else {
 %>
					<form method="post" action="erasePost" onsubmit="return erase()">
						<input type="hidden" name="postnum" value="<%=rs.getString(8)%>">
						<input type="submit" value="글 삭제">
					</form> <%
 	}
 				}
 			} else {
 %>
					<button onclick="login()">로그인</button> <%
 	}
 %>
				</td>
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

</body>
</html>
