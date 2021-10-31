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
<script>
	function check() {
		var check = confirm("글이 자동으로 삭제되며,\n글과 관련된 모든 대화창이 닫힙니다.");
		if (check) {
			alert("거래를 완료했습니다.");
			return check;
		} else {
			return check;
		}

	}
</script>
</head>
<%
	try {
		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String fromn = request.getParameter("fromn");
		String ton = request.getParameter("ton");
		String items = request.getParameter("items");
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		int check = 0;

		String myname = (String) session.getAttribute("membername");
		String receivern, sendern;
		if (fromn.equals(myname)) {
			receivern = ton;
			sendern = fromn;
		} else {
			receivern = fromn;
			sendern = ton;
		}

		ResultSet rs = DBUtil.findRequest(conn, items, fromn, ton);
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
		<button onclick="location.href='myrequest.jsp'">뒤로</button>
	</div>
	<div align="center" style="padding: 3%">
		<%=session.getAttribute("membername")%>님의 메세지함<br />
		<%
			if (session.getAttribute("emailid") == null) {
		%><b>로그인부터 해주세요.</b> <a href="login.jsp">로그인 하러 가기</a>
		<%
			} else {
		%>
		<div>
			빌리는 사람 :
			<%=ton%><br /> 빌려주는 사람 :
			<%=fromn%><br />빌림 거래 물품 :
			<%=items%><br /> <br />
			<div style="border: 2px solid #000000; padding: 2%">
				<%
					for (int i = 1; rs.next(); i++) {
				%>
				<h5 <%if (rs.getString(2).equals(receivern)) {%> align="left"
					 <%} else {%> align="right" style="background: lightgray;"<%}%>><%=rs.getString(2)%>:
					<%=rs.getString(4)%>
					_______
					<%
					String dateTime = rs.getString(5);
								String datetime = dateTime.substring(0, 4) + "/" + dateTime.substring(4, 6) + "/"
										+ dateTime.substring(6, 8) + " " + dateTime.substring(8, 10) + ":"
										+ dateTime.substring(10, 12);
				%>
					<%=datetime%>
				</h5>
				<%
					if (rs.getString(4).contains("를(을) 빌리기로 했습니다. 요청해주셔서 감사합니다.")
										|| rs.getString(4).contains("이 게시글은 삭제되었습니다.")) {
									check = 1;
								}
							}
							if (check == 1) {
				%>
				<h3>거래가 끝났거나 삭제된 물품입니다.</h3>
				<%
					} else {
				%>
				<form method="get" action="sendMessage">
					<textarea rows="4" cols="50" placeholder="최대 200자 입니다."
						name="reqnote"></textarea>
					<input type="submit" value="보내기"> <input type="hidden"
						name="sendern" value="<%=sendern%>"><input type="hidden"
						name="items" value="<%=items%>"> <input type="hidden"
						name="receivern" value="<%=receivern%>"> <input
						type="hidden" name="fromn" value="<%=fromn%>"> <input
						type="hidden" name="ton" value="<%=ton%>"><input
						type="hidden" name="boardnum" value=<%=boardnum%>>
				</form>
				<%
					if (ton.equals(myname)) {
				%>
				<form method="get" action="borrowed" onclick="return check()">
					<input type="submit" value="이 사람과 거래하시겠습니까?"> <input
						type="hidden" name="sendern" value="<%=sendern%>"><input
						type="hidden" name="items" value="<%=items%>"> <input
						type="hidden" name="receivern" value="<%=receivern%>"> <input
						type="hidden" name="fromn" value="<%=fromn%>"> <input
						type="hidden" name="ton" value="<%=ton%>"> <input
						type="hidden" name="boardnum" value=<%=boardnum%>>
				</form>
				<%
					}
							}
				%>
			</div>


			<%
				}

				} catch (

				Exception e) {
					e.printStackTrace();
				}
			%>

		</div>
	</div>

</body>
</html>
