package com.bcs.zsg.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcs.zsg.web.object.tempObjClass;

@WebServlet("/getCountryV2")
public class getCountryV2 extends WebServiceServlet  {

       
   /**
     * @see HttpServlet#HttpServlet()
     */
    public getCountryV2() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String isMuslim = request.getParameter("isMuslim");
		if(isMuslim == null)
			isMuslim = "0";
		
			List<tempObjClass> tempObjClassList =  new ArrayList<tempObjClass>();
			
			try{ 
				establishConnection();
			tempObjClass tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			    String sqlExtra = "";
			    if(isMuslim.equals("0") )
			    {
			    	sqlExtra = " and id_tour_cat IN ( select id from tour_cat where description ='West' or description ='Asia' or description ='Golf'  or description='DATO LEE-SAN SERIES')";
			    } 
			    if(isMuslim.equals("1") )
			    {
			    	sqlExtra = " or ( id_tour_cat IN ( select id from tour_cat where description ='West' ) and ";
			    	sqlExtra += "t.id in (select DISTINCT p.id_tour_theme from tour_pkg p where p.is_muslim_pkg = '0' ";
			    	sqlExtra += " and p.id IN (select d.id_tour_pkg from tour_dep d where dt_dep > now() and d.status_cd = 'AC' and d.tour_status_cd IN ('A','F','L'))) )";
				 }
			    
			      String sql;
			      sql = "SELECT id,name FROM Country where id IN (select DISTINCT t.id_country from tour_theme t where ";
			      sql += "t.id in (select DISTINCT p.id_tour_theme from tour_pkg p where p.is_muslim_pkg = '" + isMuslim +"' ";
			    	sql += " and p.id IN (select d.id_tour_pkg from tour_dep d where dt_dep > now() and d.status_cd = 'AC' and d.tour_status_cd IN ('A','F','L')))";
			    	sql += sqlExtra;	   
			     sql += " ) and isOnline='1' order by name asc";
			     
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()){
			          //Retrieve by column name
			          int id  = rs.getInt("id");
			          String name = rs.getString("name"); 
			          //Display values
			 	     tmpObjClass = new tempObjClass();
			 		tmpObjClass.setTempStr1(String.valueOf(id));
					tmpObjClass.setTempStr2(name); 
					tempObjClassList.add(tmpObjClass);
			       }
			       //STEP 6: Clean-up environment
			       rs.close();
			       stmt.close();
			       closeConnection(); 
			       
			WebServicePretender webServicePretender = new WebServicePretender(); 
			response.setContentType("text/xml");
			PrintWriter out = response.getWriter();
			out.println(webServicePretender.convertToXML(tempObjClassList)); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 
	}


}
