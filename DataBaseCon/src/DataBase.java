

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataBase
 */
@WebServlet("/DataBase")
public class DataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		try
		{
		
		int eno=Integer.parseInt(request.getParameter("eno"));
		String name=request.getParameter("name");
		String dob=request.getParameter("dob");
		int sal=Integer.parseInt(request.getParameter("sal"));
		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			pw.println("Driver Accepted<br>");
			
			Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost\\sqlexpress;Database=Employee1","sa","12345");
			pw.println("Connected<br>");
			
			Statement st=con.createStatement();
			
			
				//int ans=st.executeUpdate("insert Emp values(106,'Sam','02/14/2000',40000)");
			int ans=st.executeUpdate("insert Emp values("+eno+",'"+name+"','"+dob+"',"+sal+")");
			
				if(ans>0)
					pw.println("Inserted");
			 
			
		    ResultSet rs=st.executeQuery("select * from Emp"); 
			pw.println("<table border=3> <th>EmpID</th> <th>Emp Name</th> <th>D.O.B</th> <th>Salary</th>");
			while(rs.next()==true)
			{
				pw.println("<tr><td>"+rs.getString("eno")+"</td><td>"+rs.getString("name")+"</td><td>"+rs.getString("dob")+"</td><td>"+rs.getString("salary")+"</td></tr>");
			}
			
			rs.close();		
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/htmlfile/test1.html");
			rd.include(request, response);
		   pw.println("Error: <font color=red>"+e);	
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);		
		
	}

}
