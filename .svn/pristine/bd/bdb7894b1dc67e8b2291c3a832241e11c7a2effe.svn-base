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

import com.bcs.zsg.web.object.objGroupPackage;

@WebServlet("/getGroupPackageListMuslim")
public class getGroupPackageListMuslim extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getGroupPackageListMuslim() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


	    public List<objGroupPackage> run(HttpServletRequest request, HttpServletResponse response)
	    {

			String isMuslim = request.getParameter("isMuslim");
			if(isMuslim == null)
				isMuslim = "0";
			String countryID = request.getParameter("countryID");
			List<objGroupPackage> tempObjClassList =  new ArrayList<objGroupPackage>(); 
			try{ 
				establishConnection();
				objGroupPackage tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			    String	sqlExtra = " or ( t.id_tour_cat IN ( select id from tour_cat where description ='West' ) and ";
			    sqlExtra += "t.id in (select DISTINCT p.id_tour_theme from tour_pkg p where c.id = '" + countryID +"' ";
		        sqlExtra += ")  and pkg.id IN (select d.id_tour_pkg from tour_dep d where dt_dep > now() and d.status_cd = 'AC' and d.tour_status_cd IN ('A','F','L')) )";
				
			      sql = "select pkg.id,pkg.name_en,  pkg.is_muslim_pkg, pkg.deposit, pkg.bag_deduction,pkg.is_theme_tour, c.name as country, pkg.year,pkg.num_days,pkg.num_nights, pkg.season_cd, t.description as part from  tour_pkg pkg inner join tour_theme t on pkg.id_tour_theme  = t.id inner join country c on t.id_country = c.id where (c.id = '" + countryID +"' and pkg.is_muslim_pkg = '" + isMuslim + "' and EXISTS (select id from tour_dep d where d.dt_dep > now() and d.id_tour_pkg = pkg.id and d.status_cd = 'AC' and d.tour_status_cd IN ('A','F','L')) ) ";
			    		 sql += sqlExtra;
			    		  sql+=  "order by pkg.year, part, pkg.season_cd, pkg.num_days, pkg.num_nights";
			      ResultSet rs = stmt.executeQuery(sql);
			       
			      while(rs.next()){
			          //Retrieve by column name
			    	  String id =  rs.getString("id");
			    	  String country =  rs.getString("country");
			       //  Date dtDep = Date.valueOf(rs.getString("dt_dep").substring(0,10)); 
			          String num_days  = rs.getString("num_days");
			          String num_nights = rs.getString("num_nights"); 
			          //String description = rs.getString("description");  
			          String name_en = rs.getString("name_en");  
			          String seasonCode = rs.getString("season_cd");  
			          String part = rs.getString("part");
			          String isThemeTour = rs.getString("is_theme_tour");   
			          String isMuslimPkg = rs.getString("is_muslim_pkg");   
			          String deposit = rs.getString("deposit");   	
			          String bagDeduction = rs.getString("bag_deduction");   
			          //String discount = rs.getString("tfair_discount");      
			          //String cnaAdt = rs.getString("cna_adt");      
			          //String cpaAdt = rs.getString("cpa_adt");      
			          //String csiAdt = rs.getString("csi_adt");    

			          String year = rs.getString("year");  
			               //Display values
			 	      tmpObjClass = new objGroupPackage();
			 		  tmpObjClass.setID(Integer.parseInt(id));
			 	//	  tmpObjClass.setDescription(   description );  
			 	//	  tmpObjClass.setDepDate(dtDep);  
			 		  tmpObjClass.setNumDays(num_days);
			 		  tmpObjClass.setNumNights(num_nights);
			 		  tmpObjClass.setYear(year);
			 		  tmpObjClass.setCountry(country);
			 		  tmpObjClass.setSeasonCode(seasonCode);
			 		  tmpObjClass.setPart(part);
			 		  tmpObjClass.setIsThemeTour(isThemeTour); 
			 		  tmpObjClass.setIsMuslim(isMuslimPkg);
			 		  tmpObjClass.setDeposit(deposit);
			 		  tmpObjClass.setBagDeduction(bagDeduction);
			 		  //tmpObjClass.setDiscount(discount);
			 		  //tmpObjClass.setCnaAdt(cnaAdt);
			 		  //tmpObjClass.setCpaAdt(cpaAdt);
			 		  //tmpObjClass.setCsiAdt(csiAdt);
			 		  tmpObjClass.setNameEn(name_en);
			 		  
			 		 tempObjClassList.add(tmpObjClass);
			       }
			       //STEP 6: Clean-up environment
			       
			       rs.close(); 
			       stmt.close(); 
			       closeConnection();  
			       return tempObjClassList;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
	    }
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			List<objGroupPackage> tempObjClassList =  new ArrayList<objGroupPackage>(); 
			try{ 
				
				tempObjClassList = run(request,response);
				WebServicePretender webServicePretender = new WebServicePretender();
				//System.out.println(webServicePretender.convertToXML(tempObjClassList));
				
			//	String temp = "<PublicWebServletResponse xmlns=\"http://\">\r<return>\r<tempStr1>243</tempStr1>\r<tempStr2>Alaska</tempStr2>\r</return>\r<return>\r<tempStr1>208</tempStr1>\r<tempStr2>Switzerland</tempStr2>\r</return>\n<return>\n<tempStr1>210</tempStr1>\r<tempStr2>Taiwan</tempStr2>\r</return>\r<return>\r<tempStr1>220</tempStr1>\r<tempStr2>Turkey</tempStr2>\r</return>\r<return>\r<tempStr1>249</tempStr1>\r<tempStr2>USA</tempStr2>\r</return>\r<return>\r<tempStr1>257</tempStr1>\r<tempStr2>Western Europe</tempStr2>\r</return>\r</PublicWebServletResponse>";
			//	System.out.println("halo1");
			//	System.out.println(temp);
 
			       
				response.setContentType("text/xml");
				PrintWriter out = response.getWriter();
				out.println(webServicePretender.convertToXML(tempObjClassList));
				
		//	out.println(temp);
				//resultDataFinder.splitFileByGame();
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
