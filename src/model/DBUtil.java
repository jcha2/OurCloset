package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static ResultSet findUser(Connection con, String emailid, String membername) {
		String sqlSt = "SELECT * FROM MEMBER WHERE EmailID=";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt + "'" + emailid + "' OR MemberName='" + membername + "'")) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet login(Connection con, String emailid) {
		String sqlSt = "SELECT * FROM MEMBER WHERE EmailID =";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt + "'" + emailid + "'")) {
				return st.getResultSet();
			}
			System.out.println("Login Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void addUser(Connection con, String emailid, String passwd, String membername, String cphone,
			String address, String zip) {
		String sqlSt = "INSERT INTO MEMBER(EmailId,Passwd,MemberName,CPhone,Address,Zip) VALUES(";

		Statement st;
		try {
			st = con.createStatement();
			st.execute(sqlSt + "'" + emailid + "','" + passwd + "','" + membername + "','" + cphone + "','" + address
					+ "','" + zip + "');");
			System.out.println("Insert Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void editUser(Connection con, String emailid, String passwd, String cphone, String address,
			String zip) {
		String sqlSt = "UPDATE MEMBER SET passwd=";

		Statement st;
		try {
			st = con.createStatement();
			st.execute(sqlSt + "'" + passwd + "',cphone='" + cphone + "', address='" + address + "',zip='" + zip
					+ "' where emailid= '" + emailid + "';");
			System.out.println("Edit Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeBoard(Connection con, String category, String membername, String items, String price,
			String periodstart, String periodend, String emailid) {

		String sqlSt = "INSERT INTO BORROWBOARD(category,membername,items,price,periodstart,periodend,emailid,boardnum) VALUES(";

		Statement st, stmt;

		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet fornum = stmt.executeQuery("SELECT boardnum FROM BORROWBOARD order by boardnum DESC;");
			if (fornum.next()) {
				String numstr = fornum.getString(1);
				int num = Integer.parseInt(numstr);
				if (num == 0) {
					num = 1;
				} else {
					num = num + 1;
				}

				st = con.createStatement();
				st.execute(sqlSt + "'" + category + "','" + membername + "','" + items + "','" + price + "','"
						+ periodstart + "','" + periodend + "','" + emailid + "','" + num + "');");
				System.out.println("Write Success");
			} else {
				st = con.createStatement();
				st.execute(sqlSt + "'" + category + "','" + membername + "','" + items + "','" + price + "','"
						+ periodstart + "','" + periodend + "','" + emailid + "','" + 1 + "');");
				System.out.println("Write Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet searchPosting(Connection con, String search, String searchCate) {
		String sqlSt = "SELECT * FROM BORROWBOARD WHERE items LIKE";

		Statement st;
		
		try {
		 if(searchCate.equals("모두")) {
		    st = con.createStatement();
			if (st.execute(sqlSt + "'%"+search+"%';")) {
				return st.getResultSet();
			}  
		 }else {
		    st = con.createStatement();
		    if(st.execute(sqlSt  + "'%"+search+"%' and category='" + searchCate + "';")){
		        return st.getResultSet();
		    }
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getPosting(Connection con) {
		String sqlSt = "SELECT * FROM BORROWBOARD;";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getWomenPosting(Connection con) {
		String sqlSt = "SELECT * FROM BORROWBOARD WHERE category='여성';";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getMenPosting(Connection con) {
		String sqlSt = "SELECT * FROM BORROWBOARD WHERE category='남성';";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getEtcPosting(Connection con) {
		String sqlSt = "SELECT * FROM BORROWBOARD WHERE category='기타';";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt)) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getMyPosting(Connection con, String emailid) {
		String sqlSt = "SELECT * FROM BORROWBOARD WHERE emailid =";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt + "'" + emailid + "';")) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void erasePost(Connection con, String emailid, int boardnum) {
		String sqlSt = "DELETE FROM BORROWBOARD WHERE emailid=";

		Statement st;

		try {
			st = con.createStatement();
			st.execute(sqlSt + "'" + emailid + "' and boardnum='" + boardnum + "';");
			System.out.println("Delete Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void makeRequest(Connection con, String items, String sendern, String receivern, String reqnote,
			String datetime, String fromn, String ton, int boardnum) {
		String sqlSt = "INSERT INTO REQUEST(items,sendern,receivern,reqnote,datetime,fromn,ton,boardnum) VALUES(";

		Statement st;
		try {
			st = con.createStatement();
			st.execute(sqlSt + "'" + items + "','" + sendern + "','" + receivern + "','" + reqnote + "','" + datetime+ "','" + fromn+ "','" + ton
				+ "','"+boardnum+ "');");
			System.out.println("Insert Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static ResultSet findRequest(Connection con, String items, String fromn, String ton) {
		String sqlSt = "SELECT * FROM REQUEST WHERE ton =";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt + "'" + ton + "' and fromn='" + fromn+"' and items='"+ items+"' ORDER BY dateTime ASC;")) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet findMyRequest(Connection con, String membername) {
		String sqlSt = "SELECT DISTINCT fromn, items, boardnum FROM REQUEST WHERE ton =";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt + "'" + membername + "';")) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet findMySend(Connection con, String membername) {
		String sqlSt = "SELECT DISTINCT ton, items, boardnum FROM REQUEST WHERE fromn =";

		Statement st;

		try {
			st = con.createStatement();

			if (st.execute(sqlSt + "'" + membername + "';")) {
				return st.getResultSet();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
