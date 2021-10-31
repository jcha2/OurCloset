package web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import java.util.*;

import model.DBUtil;

/**
 * Servlet implementation class DoWrite
 */
@WebServlet("/doWrite")
public class DoWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoWrite() {
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

		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		if (month < 10)
			month = Integer.parseInt("0" + month);
		if (day < 10)
			day = Integer.parseInt("0" + day);
		int today = year * 10000 + month * 100 + day;

		String category = request.getParameter("category");
		HttpSession session = request.getSession();
		String emailid = (String) session.getAttribute("emailid");
		String membername = (String) session.getAttribute("membername");
		String items = request.getParameter("items");
		String priceStr = request.getParameter("price");
		String periodstartStr = request.getParameter("periodstart");
		String periodendStr = request.getParameter("periodend");

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		if (items.length() == 0 || priceStr.length() == 0 || periodstartStr.length() == 0
				|| periodendStr.length() == 0) {
			JOptionPane.showMessageDialog(null, "입력되지 않은 값이 있습니다.");
			response.sendRedirect("boardwrite.jsp");
		} else {
			int price = Integer.parseInt(priceStr);
			int periodstart = Integer.parseInt(periodstartStr);
			int periodend = Integer.parseInt(periodendStr);
			if (price < 0) {
				JOptionPane.showMessageDialog(null, "가격은 0 이상으로 입력해주세요.");
				response.sendRedirect("boardwrite.jsp");
			} else if (periodstart > periodend || periodstart < today || periodstartStr.length() > 8
					|| periodendStr.length() > 8) {
				JOptionPane.showMessageDialog(null, "기간은 YYYYMMDD 로 8글자이고\n오늘 이후여야하며,\n기간의 끝이 시작보다 작을 수 없습니다.");
				response.sendRedirect("boardwrite.jsp");
			} else {
				DBUtil.writeBoard(conn, category, membername, items, priceStr, periodstartStr, periodendStr, emailid);
				JOptionPane.showMessageDialog(null, "작성이 완료되었습니다.");
				if (category.equals("여성")) {
					response.sendRedirect("women.jsp");
				} else if (category.equals("남성")) {
					response.sendRedirect("men.jsp");
				} else {
					response.sendRedirect("etc.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
