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

import com.bcs.zsg.web.object.objFlightSchedule;

@WebServlet("/getFlightSchedule")
public class getFlightSchedule extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getFlightSchedule() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
			String depID = request.getParameter("depID");
			List<objFlightSchedule> tempObjClassList =  new ArrayList<objFlightSchedule>();
			
			try{ 
				establishConnection();
				objFlightSchedule tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			  
			      sql = "select  asi.id, asi.type_cd, asi.flight_cd,asi.from_airport_cd, asi.to_airport_cd, asi.etd,asi.eta, asi.remarks,d.airline_schedule_items,d.airline_schdl_itms_w_seq  from airline_schedule_item asi inner join airline_schedule asch on asi.id_airline_schedule = asch.id inner join tour_dep d on d.id_airline_schedule = asi.id_airline_schedule where d.id='" + depID + "' and d.is_show_airline='1' ";
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      int i = 0;
			      while(rs.next()){
			    	  i++;
			    	  
			    	   
			    	  
			          //Retrieve by column name  
				         String ID = rs.getString("id"); 
			         String type = rs.getString("type_cd"); 
			         String flightCode = rs.getString("flight_cd"); 
			         String flightFrom = rs.getString("from_airport_cd"); 
			         String flightTo = rs.getString("to_airport_cd"); 

			         String estDeparture = rs.getString("etd"); 
			         String estArrival = rs.getString("eta"); 
			         String remarks = rs.getString("remarks"); 
			         String ASI = rs.getString("airline_schedule_items"); 
			         String ASIseq = rs.getString("airline_schdl_itms_w_seq"); 
			           
			         
			         //Display values
			 	     tmpObjClass = new objFlightSchedule(); 
			 	     tmpObjClass.setID(Integer.parseInt(ID));
			 	     tmpObjClass.setType(type);
			 	     tmpObjClass.setFlightCode(flightCode);
			 	     tmpObjClass.setFlightFrom(flightFrom);
			 	     tmpObjClass.setFlightTo(flightTo);
			 	     tmpObjClass.setEstDeparture(estDeparture);
			 	     tmpObjClass.setEstArrival(estArrival);
			 	     tmpObjClass.setRemarks(remarks);
			 	     tmpObjClass.setASI(ASI);
			 	    tmpObjClass.setASIseq(ASIseq);
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
