package web;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import model.DBUtil;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String emailid = request.getParameter("emailid");
		String passwd = request.getParameter("passwd");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		ResultSet rs = DBUtil.login(conn, emailid);

		if (rs != null) {
			try {
				if (rs.next()) { // existing user
					String checkpw = rs.getString(2);
					HttpSession session = request.getSession();
					if (checkpw.equals(passwd)) {
						// valid user and passwd

						session.setAttribute("emailid", rs.getString(1));
						session.setAttribute("passwd", rs.getString(2));
						session.setAttribute("membername", rs.getString(3));
						session.setAttribute("cphone", rs.getString(4));
						session.setAttribute("address", rs.getString(5));
						session.setAttribute("zip", rs.getString(6));

						response.sendRedirect("index.jsp");
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
						response.sendRedirect("login.jsp");
					}

				} else {
					// invalid user
					JOptionPane.showMessageDialog(null, "가입하지 않은 사용자입니다.");
					response.sendRedirect("login.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// TODO Auto-generated method stub

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
