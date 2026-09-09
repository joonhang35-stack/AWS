package com.bcs.zsg.web.servlet; 


import java.io.*; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/GST03.pdf")
public class GSTWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   /**
     * @see HttpServlet#HttpServlet()
     */
    public GSTWebServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PdfReader reader = new PdfReader(getServletContext().getRealPath("/WEB-INF/pdf/GST03.pdf"));
		
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfStamper stamper = new PdfStamper(reader, out);
			
			AcroFields form = stamper.getAcroFields();
			form.setField("chkA", "X");
			form.setField("NameBusiness", "Restoran Ah Huat");
			form.setField("StartDate", new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse("01/04/2015").toString());
			form.setField("EndDate", new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse("30/04/2015").toString());
			form.setField("PaymentDate", new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse("30/04/2015").toString());
			
			stamper.setFormFlattening(true);
			stamper.close();
			
			response.reset();
			response.setContentType("application/pdf");
			response.setContentLength(reader.getFileLength());
			response.setHeader("Content-disposition", "inline; filename=\"" + getServletContext().getRealPath("/resources/signed.pdf") + "\"");

	        response.getOutputStream().write(out.toByteArray());
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{ 	 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	 
	}
}

