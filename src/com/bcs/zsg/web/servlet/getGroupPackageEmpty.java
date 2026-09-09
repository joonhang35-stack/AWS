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

@WebServlet("/getGroupPackageEmpty")
public class getGroupPackageEmpty extends WebServiceServlet {

	   /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public getGroupPackageEmpty() {
	        super();
	        // TODO Auto-generated constructor stub
	    }


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String pkgID = request.getParameter("pkgID");

			List<objGroupPackage> tempObjClassList =  new ArrayList<objGroupPackage>();
			
			try{ 
				establishConnection();
				objGroupPackage tmpObjClass; 
		     

			    Statement stmt = conn.createStatement();
			      String sql;
			      sql = "select * from tour_pkg ";
			      sql += " where id='" + pkgID + "'";
			      ResultSet rs = stmt.executeQuery(sql);
			      while(rs.next()){
			          //Retrieve by column name  
			         String pkgName = rs.getString("name_en"); 
			          
			          
			         Date dtDep = Date.valueOf(rs.getString("dt_dep").substring(0,10)); 
			          
			         //Display values
			 	     tmpObjClass = new objGroupPackage(); 
			 	     tmpObjClass.setPkgName(pkgName);
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
