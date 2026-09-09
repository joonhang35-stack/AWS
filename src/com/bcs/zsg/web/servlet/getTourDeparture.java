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

import com.bcs.zsg.web.object.tempObjClass;;

@WebServlet("/getTourDeparture")
public class getTourDeparture extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getTourDeparture() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			List<tempObjClass> tempObjClassList =  new ArrayList<tempObjClass>();
			
			try{ 
				establishConnection(); 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			      sql = "SELECT id,name FROM tour_dep";
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()){
			          //Retrieve by column name
			          int id  = rs.getInt("dt_dep");
			          String name = rs.getString("name"); 
			          //Display values
			          tempObjClass  tmpObjClass = new tempObjClass();
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
