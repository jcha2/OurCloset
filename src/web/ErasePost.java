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
 * Servlet implementation class ErasePost
 */
@WebServlet("/erasePost")
public class ErasePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ErasePost() {
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

		HttpSession session = request.getSession();
		String emailid = (String) session.getAttribute("emailid");
		String membername = (String) session.getAttribute("membername");
		String boardnums = request.getParameter("postnum");
		int boardnum = Integer.parseInt(boardnums);

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		ResultSet fmr = DBUtil.findMyRequest(conn, membername);
		try {
			while (fmr.next()) {
				if (fmr.getString(3).equals(boardnums)) {
					GregorianCalendar calender = new GregorianCalendar();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

					String dateTime = dateFormat.format(calender.getTime());

					String reqnote = "이 게시글은 삭제되었습니다.";

					DBUtil.makeRequest(conn, fmr.getString(2), membername, fmr.getString(1), reqnote, dateTime,
							fmr.getString(1), membername, boardnum);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.erasePost(conn, emailid, boardnum);

		response.sendRedirect("myposting.jsp");

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
