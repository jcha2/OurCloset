package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBUtil;

/**
 * Servlet implementation class Borrowed
 */
@WebServlet("/borrowed")
public class Borrowed extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Borrowed() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		HttpSession session = request.getSession();
		String emailid = (String) session.getAttribute("emailid");
		String membername = (String) session.getAttribute("membername");
		String boardnums = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardnums);

		DBUtil.erasePost(conn, emailid, boardnum);

		GregorianCalendar calender = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		String dateTime = dateFormat.format(calender.getTime());

		String items = request.getParameter("items");
		String sendern = request.getParameter("sendern");
		String fromn = request.getParameter("fromn");
		String ton = request.getParameter("ton");
		String reqnote = fromn + "님께 " + items + "를(을) 빌리기로 했습니다. 요청해주셔서 감사합니다.";

		ResultSet fr = DBUtil.findMyRequest(conn, membername);
		try {
			while (fr.next()) {
				if (fr.getString(3).equals(boardnums)) {
					DBUtil.makeRequest(conn, items, sendern, fr.getString(1), reqnote, dateTime, fr.getString(1), ton,
							boardnum);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("myrequest.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
