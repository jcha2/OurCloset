package web;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import model.DBUtil;

/**
 * Servlet implementation class DoJoin
 */
@WebServlet("/doJoin")
public class DoJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoJoin() {
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
		String passwd2 = request.getParameter("passwd2");
		String membername = request.getParameter("membername");
		String cphone = request.getParameter("cphone");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");
		ResultSet fu = DBUtil.findUser(conn, emailid, membername);

		if (emailid.length() == 0 || passwd.length() == 0 || passwd2.length() == 0 || membername.length() == 0) {
			JOptionPane.showMessageDialog(null, "���̵�, �н�����, �̸��� �ʼ� �Է°��Դϴ�.");
			response.sendRedirect("join.jsp");
		} else {
			if (fu != null) {
				try {
					if (fu.next()) {
						JOptionPane.showMessageDialog(null, "�̹� ���Ե� ���̵��̰ų�, �̹� �����ϴ� �����Դϴ�.");
						response.sendRedirect("join.jsp");
					} else {
						if (passwd.equals(passwd2)) {
							DBUtil.addUser(conn, emailid, passwd, membername, cphone, address, zip);
							JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
							response.sendRedirect("login.jsp");
						} else {
							JOptionPane.showMessageDialog(null, "�н����� Ȯ�ο� �����߽��ϴ�.");
							response.sendRedirect("join.jsp");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
