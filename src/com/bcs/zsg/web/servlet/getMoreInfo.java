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

import com.bcs.zsg.web.object.objGroupPackage;

@WebServlet("/getMoreInfo")
public class getMoreInfo extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getMoreInfo() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 
			String depID = request.getParameter("depID");
			List<objGroupPackage> tempObjClassList =  new ArrayList<objGroupPackage>();
			
			try{ 
				establishConnection();
				objGroupPackage tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			      sql = "select d.id,REPLACE(p.high_light, '\n', '%$n$%') as high_light,d.num_days,d.num_nights,d.id_airline,d.description,p.name_en as pkgName,d.dt_dep,d.full_twn as adult_twin, d.full_sgl as adult_single, d.dt_dep,d.full_ctw as ctw, d.full_ceb as cwb, d.full_cnb as cnb, d.full_remarks,d.grnd_remarks,d.grnd_twn as grdTwin,d.grnd_sgl as grdSingle, d.grnd_ctw as grdCtw, d.grnd_ceb as grdCwb, d.grnd_cnb as grdCnb,d.misc_adt as misc,  d.misc_chd as miscChd , it.name as itineryEn,it2.name as itineryCn , it3.name as itineryAgtEn,it4.name as itineryAgtCn  from tour_dep d inner join tour_pkg p on d.id_tour_pkg = p.id  left join tour_itinery it on it.id_tour_dep = d.id and it.lang_cd = 'EN' and it.type_cd = 'CUST' left join tour_itinery it2 on it2.id_tour_dep = d.id and it2.lang_cd = 'ZH' and it2.type_cd = 'CUST' left join tour_itinery it3 on it3.id_tour_dep = d.id and it3.lang_cd = 'EN' and it3.type_cd = 'AGENT' left join tour_itinery it4 on it4.id_tour_dep = d.id and it4.lang_cd = 'ZH' and it4.type_cd = 'AGENT' ";
			      
			      sql += " where d.id='" + depID + "'";
			      ResultSet rs = stmt.executeQuery(sql);
			       
			       int i =0;
			     
			       while(rs.next()){
			          //Retrieve by column name
			    	
			    	   String id =  rs.getString("id");  
			    	  String idAirline =  rs.getString("id_airline");  
			          String num_days  = rs.getString("num_days");
			          String num_nights = rs.getString("num_nights"); 
			          String description = rs.getString("description"); 
			          String pkgName = rs.getString("pkgName"); 
			          String fullRemarks = rs.getString("full_remarks") == null ? "" : rs.getString("full_remarks"); 
			          String grdRemarks = rs.getString("grnd_remarks") == null ? "" : rs.getString("grnd_remarks"); 
			          String misc = String.valueOf(rs.getInt("misc")); 
			          String miscChd = String.valueOf(rs.getInt("miscChd"));
			          String itineryEn = rs.getString("itineryEn"); 
			          String itineryCn = rs.getString("itineryCn"); ;
			          String itineryAgtEn = rs.getString("itineryAgtEn"); 
			          String itineryAgtCn = rs.getString("itineryAgtCn");  
			          String highlight = rs.getString("high_light");  
			          
			          String adultTwin = String.valueOf(rs.getInt("adult_twin")); 
			          String adultSingle = String.valueOf(rs.getInt("adult_single")); 
			          String ctw = String.valueOf(rs.getInt("ctw")); 
			          String cwb = String.valueOf(rs.getInt("cwb")); 
			          String cnb = String.valueOf(rs.getInt("cnb")); 
			          String grdTwin = String.valueOf(rs.getInt("grdTwin")); 
			          String grdSingle = String.valueOf(rs.getInt("grdSingle")); 
			          String grdCtw = String.valueOf(rs.getInt("grdCtw")); 
			          String grdCwb = String.valueOf(rs.getInt("grdCwb")); 
			          String grdCnb = String.valueOf(rs.getInt("grdCnb")); 
			          Date dtDep = Date.valueOf(rs.getString("dt_dep").substring(0,10));  
			         //Display values
			 	     tmpObjClass = new objGroupPackage();
			 		 tmpObjClass.setID(Integer.parseInt(id));  
			 		 tmpObjClass.setAirline(idAirline);
			 		 tmpObjClass.setDescription(num_days + "Days " + num_nights + "Nights [" + description + "]"); 
				 	 tmpObjClass.setPkgName(pkgName);

			 		 tmpObjClass.setDepDate(dtDep);
			 		 tmpObjClass.setAdultTwin(adultTwin);
			 		 tmpObjClass.setAdultSingle(adultSingle);

			 		 tmpObjClass.setCtw( ctw);
			 		 tmpObjClass.setCwb(cwb);
			 		 tmpObjClass.setCnb(cnb);
			 		 tmpObjClass.setGrdCtw(grdCtw);
			 		 tmpObjClass.setGrdCwb(grdCwb);
			 		 tmpObjClass.setGrdCnb(grdCnb);
			 		 tmpObjClass.setFullRemarks(fullRemarks);
			 		 
			 		 tmpObjClass.setGrdTwin(grdTwin);
			 		 tmpObjClass.setGrdSingle(grdSingle);
			 		 tmpObjClass.setGrdRemarks(grdRemarks);
			 		 tmpObjClass.setMiscTaxes(misc);
			 		 tmpObjClass.setMiscChd(miscChd);

			 		 tmpObjClass.setItenaryEng(itineryEn);
			 		 tmpObjClass.setItenaryCn(itineryCn);
			 		 tmpObjClass.setItenaryAgtEng(itineryAgtEn);
			 		 tmpObjClass.setItenaryAgtCn(itineryAgtCn);
			 		 tmpObjClass.setHighlight(highlight);
			 		
			 		 tempObjClassList.add(tmpObjClass);
			 		 i++;
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
