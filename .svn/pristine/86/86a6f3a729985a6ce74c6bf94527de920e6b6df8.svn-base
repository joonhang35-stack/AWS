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

import com.bcs.zsg.web.object.objMiscCharge;

@WebServlet("/getMiscCharge")
public class getMiscCharge extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getMiscCharge() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
			String depID = request.getParameter("depID");

			List<objMiscCharge> tempObjClassList =  new ArrayList<objMiscCharge>();
			
			try{ 
				establishConnection();
				objMiscCharge tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			   
			      sql = "select d.id,a.code as airline, asch.visa, asch.ac,asch.tipping, asch.apt_adt, asch.apt_chd, asch.trvl_ins,asch.fuel_adt, asch.fuel_chd,asch.tkt_validity,asch.deviation,d.full_remarks, d.grnd_remarks,(select sum(i1.amount) from tour_dep_item i1 where i1.id_tour_dep = d.id and i1.code not in ('VISA','AC','TIPPING','APT_ADT','APT_CHD','TRVL_INS','FUEL_ADT','FUEL_CHD','DEVIATION','FT_CTW','FT_CWB','FT_CNB','GA_SGL','GA_TWN','GA_CTW','GA_CWB','GA_CNB','DISC','FT_TWN','FT_SGL')) as others from tour_dep d inner join airline_schedule asch on asch.id = d.id_airline_schedule inner join airline a on a.id = asch.id_airline ";
			    sql += " where d.id='" + depID + "'";
			    ResultSet rs = stmt.executeQuery(sql);
			     
			      while(rs.next()){
			          //Retrieve by column name
			    	  String id =  rs.getString("id");   
			    	  String airline =  rs.getString("airline");   
			    	  String visa =  String.valueOf(rs.getInt("visa"));    
			    	  String ac =  String.valueOf(rs.getInt("ac"));   
			    	  String aptAdt =  String.valueOf(rs.getInt("apt_adt"));   
			    	  String aptChd =  String.valueOf(rs.getInt("apt_chd"));    
			    	  String trvlIns =  String.valueOf(rs.getInt("trvl_ins"));   
			    	  String fuelAdt =  String.valueOf(rs.getInt("fuel_adt"));   
			    	  String fuelChd =  String.valueOf(rs.getInt("fuel_chd"));    
			    	  String tktValidity =  String.valueOf(rs.getInt("tkt_validity"));   
			    	  String deviation =  String.valueOf(rs.getInt("deviation"));   
			    	  String tipping = String.valueOf(rs.getInt("tipping"));   
			    	  String others = String.valueOf(rs.getInt("others"));   
			    	  String fullRemarks = rs.getString("full_remarks");   
			    	  String groundRemarks = rs.getString("grnd_remarks");   
			               //Display values
			 	     tmpObjClass = new objMiscCharge();
			 		 tmpObjClass.setID(Integer.parseInt(id));   
			 		 tmpObjClass.setAirline(airline);    
			 		tmpObjClass.setVisa(visa);
			 		tmpObjClass.setAc(ac);
			 		tmpObjClass.setAptAdt(aptAdt);
			 		tmpObjClass.setAptChd(aptChd);
			 		tmpObjClass.setTrvlIns(trvlIns);
			 		tmpObjClass.setFuelAdt(fuelAdt);
			 		tmpObjClass.setFuelChd(fuelChd);
			 		tmpObjClass.setTktValidity(tktValidity);
			 		tmpObjClass.setDeviation(deviation);
			 		tmpObjClass.setTipping(tipping);
			 		tmpObjClass.setOthers(others);
			 		tmpObjClass.setFullRemarks(fullRemarks);
			 		tmpObjClass.setGroundRemarks(groundRemarks);
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
