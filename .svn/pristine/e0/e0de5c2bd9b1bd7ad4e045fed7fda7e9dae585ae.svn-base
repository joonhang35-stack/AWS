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

@WebServlet("/getGroupPackageWithSearch")
public class getGroupPackageWithSearch extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getGroupPackageWithSearch() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


			String searchKey = request.getParameter("searchKey");
			String pkgID = request.getParameter("pkgID");
			List<objGroupPackage> tempObjClassList =  new ArrayList<objGroupPackage>();
			
			try{ 
				establishConnection();
				objGroupPackage tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			      sql = "select d.id,c.name as country,d.full_ctw as ctw, d.full_ceb as cwb, d.full_cnb as cnb,d.misc_adt as misc,  d.misc_chd as miscChd,d.cna_adt,d.cpa_adt,d.csi_adt,d.tfair_discount,d.num_days,d.num_nights,d.description,a.code,d.tour_status_cd,d.dt_dep,d.full_twn as adult_twin, d.full_sgl as adult_single , it.name as itineryEn,it2.name as itineryCn , pkg.name_en as pkgName from tour_dep d inner join airline a on d.id_airline = a.id inner join tour_pkg pkg on pkg.id = d.id_tour_pkg  left join tour_itinery it on it.id_tour_dep = d.id and it.lang_cd = 'EN' and it.type_cd = 'CUST' left join tour_itinery it2 on it2.id_tour_dep = d.id and it2.lang_cd = 'ZH' and it2.type_cd = 'CUST'";
			      sql += " inner join tour_theme tt on pkg.id_tour_theme = tt.id inner join country c on tt.id_country = c.id";
			      	sql += " where d.tour_status_cd IN ('A','F','L') and d.status_cd='AC'  and dt_dep > now() and pkg.status_cd ='AC' and d.description like '%" + searchKey + "%'";
			      	sql += " order by c.name ";
			      	ResultSet rs = stmt.executeQuery(sql); 
			      while(rs.next()){
			          //Retrieve by column name
			    	  String id =  rs.getString("id");
			          String country  = rs.getString("country");
			          String num_days  = rs.getString("num_days");
			          String num_nights = rs.getString("num_nights"); 
			          String description = rs.getString("description"); 
			           
			          String code = rs.getString("code"); 
			          String tour_status_cd = rs.getString("tour_status_cd"); 
			          String adultTwin = String.valueOf(rs.getInt("adult_twin")); 
			          String adultSingle = String.valueOf(rs.getInt("adult_single")); 
			          String misc = String.valueOf(rs.getInt("misc")); 
			          String miscChd = String.valueOf(rs.getInt("miscChd"));
			          String itineryEn = rs.getString("itineryEn"); 
			          String itineryCn = rs.getString("itineryCn"); 
			          String pkgName = rs.getString("pkgName"); 
			          String discount = String.valueOf(rs.getInt("tfair_discount"));      
			          String cnaAdt = String.valueOf(rs.getInt("cna_adt"));      
			          String cpaAdt = String.valueOf(rs.getInt("cpa_adt"));      
			          String csiAdt = String.valueOf(rs.getInt("csi_adt"));   
			          String ctw = String.valueOf(rs.getInt("ctw")); 
			          String cwb = String.valueOf(rs.getInt("cwb")); 
			          String cnb = String.valueOf(rs.getInt("cnb")); 
			           
			          
			          Date dtDep = Date.valueOf(rs.getString("dt_dep").substring(0,10)); 
			          
			               //Display values
			 	     tmpObjClass = new objGroupPackage();
			 		tmpObjClass.setID(Integer.parseInt(id));
			 		 
			 		 tmpObjClass.setCountry(country);
			 		 tmpObjClass.setDescription(description); 
			 		 tmpObjClass.setAirline(code); 
			 		 tmpObjClass.setStatus(tour_status_cd); 
			 		 tmpObjClass.setDepDate(dtDep);
			 		 tmpObjClass.setAdultTwin(adultTwin);
			 		 tmpObjClass.setAdultSingle(adultSingle);
			 		 tmpObjClass.setMiscTaxes(misc);
			 		 tmpObjClass.setMiscChd(miscChd);
			 		 tmpObjClass.setItenaryEng(itineryEn);
			 		 tmpObjClass.setItenaryCn(itineryCn);
				 	    tmpObjClass.setPkgName(pkgName);
				 		  tmpObjClass.setDiscount(discount);
				 		  tmpObjClass.setCnaAdt(cnaAdt);
				 		  tmpObjClass.setCpaAdt(cpaAdt);
				 		  tmpObjClass.setCsiAdt(csiAdt);
					 		 tmpObjClass.setCtw( ctw); 
						 		tmpObjClass.setCwb(cwb);
						 		tmpObjClass.setCnb(cnb);
			 		 
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
