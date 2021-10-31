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
 * Servlet implementation class DoJoin
 */
@WebServlet("/doEdit")
public class DoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoEdit() {
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

		HttpSession session = request.getSession();
		String emailid = (String) session.getAttribute("emailid");
		String passwd = request.getParameter("passwd");
		String passwd2 = request.getParameter("passwd2");
		String cphone = request.getParameter("cphone");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		if (passwd.length() == 0 || passwd2.length() == 0) {
			JOptionPane.showMessageDialog(null, "패스워드는 필수 입력값입니다.");
			response.sendRedirect("mypageedit.jsp");
		} else {
			try {
				if (passwd.equals(passwd2)) {
					DBUtil.editUser(conn, emailid, passwd, cphone, address, zip);
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
					session.setAttribute("passwd", passwd);
					session.setAttribute("cphone", cphone);
					session.setAttribute("address", address);
					session.setAttribute("zip", zip);
					response.sendRedirect("mypage.jsp");
				} else {
					JOptionPane.showMessageDialog(null, "패스워드 확인에 실패했습니다.");
					response.sendRedirect("mypageedit.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

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
