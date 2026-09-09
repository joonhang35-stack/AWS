package com.bcs.zsg.common.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bcs.zsg.core.exception.BusinessException;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class ReportUtils {

	public static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd'_'HHmmss");
	
	/**
	 * 
	 * @param beanCollection
	 * @param jasperFileName
	 * @return
	 */
	public static JasperPrint getJasperPrint(Collection<?> beanCollection, String jasperFileName) throws BusinessException, JRException {
		return getJasperPrint(beanCollection, new HashMap(), jasperFileName);
	}
	
	/**
	 * 
	 * @param beanCollection
	 * @param hashMap
	 * @param jasperFileName
	 * @return
	 */
	public static JasperPrint getJasperPrint(Collection<?> beanCollection, Map<String, Object> hashMap, String jasperFileName) throws BusinessException, JRException {
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanCollection);
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + jasperFileName + ".jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, hashMap, beanCollectionDataSource);
		
		return jasperPrint;
	}
	
	/**
	 * 
	 * @param hashMap
	 * @param jasperFileName
	 * @return
	 * @throws BusinessException
	 * @throws JRException
	 */
	public static JasperPrint getJasperPrint(Map<String, Object> hashMap, String jasperFileName) throws BusinessException, JRException {
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + jasperFileName + ".jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, hashMap, new JREmptyDataSource());
		
		return jasperPrint;
	}
	
	/**
	 * 
	 * @param jasperPrint
	 * @param reportName
	 * @return
	 */
	public static void printReport(JasperPrint jasperPrint, String reportName) throws BusinessException, JRException, IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.setContentType("application/pdf"); 
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + reportName + ft.format(new Date()) + ".pdf");
		httpServletResponse.setCharacterEncoding("UTF-8");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		jasperPrint.setProperty("net.sf.jasperreports.export.pdf.owner.password", "Appl3_1996@KL");
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public static void printReport2(JasperPrint jasperPrint, String reportName, String password) throws BusinessException, JRException, IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.setContentType("application/pdf"); 
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + reportName + ".pdf");
		httpServletResponse.setCharacterEncoding("UTF-8");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		if (password!=null && password.length()>0) {
			jasperPrint.setProperty("net.sf.jasperreports.export.pdf.owner.password", password);
		}
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	/**
	 * 
	 * @param jasperPrint
	 * @param reportName
	 * @return
	 */
	public static void printReportExcel(JasperPrint jasperPrint, String reportName) throws BusinessException, JRException, IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		//httpServletResponse.setContentType("application/vnd.ms-excel");
		httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + reportName + ft.format(new Date()) + ".xlsx");
		httpServletResponse.setCharacterEncoding("UTF-8");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
        //exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); //Convert cell type as report (eg: '350.00 to 350.00)
        exporter.exportReport();
        servletOutputStream.flush();
        servletOutputStream.close();
        servletOutputStream = null;
        
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	/**
	 * 
	 * @param data
	 * @param reportName
	 * @return
	 */
	public static void printReportCSV(List<Map<String, String>> listMap, String reportName) throws BusinessException, JRException, IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.setContentType("text/csv");
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + reportName + ft.format(new Date()) + ".csv");
		httpServletResponse.setCharacterEncoding("UTF-8");
		
		StringBuilder sbData = new StringBuilder();
		for (Map<String, String> map : listMap) {
			TreeMap<String, String> sorted = new TreeMap<>(map);
			sbData = new StringBuilder();
		    for (Map.Entry<String, String> entry : sorted.entrySet()) {
		        sbData.append(entry.getValue()).append(",");
		    }
		    httpServletResponse.getWriter().write(sbData + "\n");
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	/**
	 * 
	 * @param jasperPrint1
	 * @param jasperPrint2
	 * @return
	 */
	public static JasperPrint mergeReport(JasperPrint jasperPrint1, JasperPrint jasperPrint2) throws BusinessException {
		List pages = jasperPrint2.getPages();
		for (int j = 0; j < pages.size(); j++) {
			JRPrintPage object = (JRPrintPage) pages.get(j);
			jasperPrint1.addPage(object);
		}
		
		return jasperPrint1;
	}

	/**
	 * 
	 * @param jasperPrint
	 * @return byte[]
	 */
	public static byte[] exportReportToByte(JasperPrint jasperPrint) throws JRException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		return stream.toByteArray();
	}
	
	public static String generateReportFileName(String reportName) {
		String fileName = "";
		if (reportName != null) {
			fileName = reportName +  ft.format(new Date());
		} else {
			fileName = "Report" +  ft.format(new Date());
		}
        return fileName;
	}
	
}
