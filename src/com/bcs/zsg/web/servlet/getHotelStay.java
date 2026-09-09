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

import com.bcs.zsg.web.object.objHotelStay;

@WebServlet("/getHotelStay")
public class getHotelStay extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getHotelStay() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String depID = request.getParameter("depID");

			List<objHotelStay> tempObjClassList =  new ArrayList<objHotelStay>();
			
			try{ 
				establishConnection();
				objHotelStay tmpObjClass;  

			    Statement stmt = conn.createStatement();
			      String sql;
			     // sql = "SELECT code, description FROM Airline where id in ( select id_airline from tour_dep where status_cd='AC' and id_tour_pkg in ( select id from tour_pkg where type_cd ='TICKETING' and dt_book_end > now()))";
			      sql = "select h.id, (th.dt_to - th.dt_from) as days,h.name as hotel, h.star_cd,h.website,h.remarks  from hotel h inner join tour_hotel th on th.id_hotel = h.id where th.id_tour_dep='" + depID + "' ";
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()){

					 
			          //Retrieve by column name  
			    	  String id =  rs.getString("id");   
			         String days = rs.getString("days"); 
			         String hotel = rs.getString("hotel"); 
			         String website = rs.getString("website");  
			         String star = rs.getString("star_cd"); 
			         String remarks = rs.getString("remarks"); 
			          
			         //Display values
			 	     tmpObjClass = new objHotelStay(); 
				 	 tmpObjClass.setID(Integer.parseInt(id));
			 	     tmpObjClass.setDays(days); 
			 	     tmpObjClass.setHotel(hotel); 
			 	     tmpObjClass.setWebsite(website); 
			 	     tmpObjClass.setStar(star); 
			 	     tmpObjClass.setRemarks(remarks); 
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
