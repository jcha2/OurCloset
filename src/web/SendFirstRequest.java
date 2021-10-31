package web;

import java.io.IOException;
import java.sql.Connection;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;

/**
 * Servlet implementation class SendRequest
 */
@WebServlet("/sendFirstRequest")
public class SendFirstRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendFirstRequest() {
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

		GregorianCalendar calender = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		String dateTime = dateFormat.format(calender.getTime());

		String items = request.getParameter("items");
		String sendern = request.getParameter("sendern");
		String receivern = request.getParameter("receivern");
		String fromn = request.getParameter("fromn");
		String ton = request.getParameter("ton");
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String reqnote = fromn + " ¥‘¿Ã " + ton + " ¥‘ø°∞‘ " + items + " ∏¶ ∫Ù∑¡¡÷∞Ì ΩÕæÓ«’¥œ¥Ÿ.";

		ServletContext sc = getServletContext();
		Connection conn = (Connection) sc.getAttribute("DBconnection");

		DBUtil.makeRequest(conn, items, sendern, receivern, reqnote, dateTime, fromn, ton, boardnum);
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
