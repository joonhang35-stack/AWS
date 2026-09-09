package com.bcs.zsg.web.servlet;
 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcs.zsg.web.object.objTicketing;

@WebServlet("/getAirlineListWithPkg")
public class getAirlineListWithPkg extends WebServiceServlet{

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getAirlineListWithPkg() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

			List<objTicketing> tempObjClassList =  new ArrayList<objTicketing>();

			try{
			establishConnection();
			objTicketing tmpObjClass; 
	     

		    Statement stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT a.code, a.description, t.remarks , t.destination , t.price_to as fare_from, t.dt_book_start, t.dt_book_end,t.dt_travel_start,t.dt_travel_end,t.description as ticketDesc FROM Airline a, ticketing t where a.id = t.id_airline and a.id in ( select DISTINCT t2.id_airline from ticketing t2 where t2.status_cd='AC' and  t.dt_book_end > now()) order by a.code, destination ";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		          //Retrieve by column name
		          String code  = rs.getString("code");
		          String description = rs.getString("description"); 
		          String remark = rs.getString("remarks"); 
		          String destination = rs.getString("destination"); 
		          String fareFrom = rs.getString("fare_from"); 
		          
		          Date bookDateStart = Date.valueOf(rs.getString("dt_book_start").substring(0,10)); 
		          Date bookDateEnd = Date.valueOf(rs.getString("dt_book_end").substring(0,10)); 
		          Date travelDateStart = Date.valueOf(rs.getString("dt_travel_start").substring(0,10)); 
		          Date travelDateEnd = Date.valueOf(rs.getString("dt_travel_end").substring(0,10)); 

		          String ticketDesc = rs.getString("ticketDesc"); 
		          
		          //Display values
		 	     tmpObjClass = new objTicketing();
		 		tmpObjClass.setAirlineCode(code);
		 		 tmpObjClass.setAirlineDesc(description); 
		 		 tmpObjClass.setRemark(remark); 
		 		 tmpObjClass.setDestination(destination);
		 		 tmpObjClass.setFareFrom(fareFrom); 
		 		 tmpObjClass.setBookDateStart(bookDateStart); 
		 		 tmpObjClass.setBookDateEnd(bookDateEnd);
		 		 tmpObjClass.setTravelDateStart(travelDateStart); 
		 		 tmpObjClass.setTravelDateEnd(travelDateEnd); 
		 		tmpObjClass.setTicketDesc(ticketDesc);
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
