package com.bcs.zsg.gst.bo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.gst.GSTMappingStateVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTReportVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.helper.GSTType;

public interface GSTBO {
	
	public List<TaxCodeVO> getTaxCodeList(GSTType gstType) throws BusinessException;

	public GSTVO getTaxReturnData(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException;
	public GSTVO getGAFData(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException;
	public StringBuilder getGAFData(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException;
	
	public List<GSTSummaryVO> getMonthlySummaryList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException;
	
	public GSTSummaryVO getTaxSummary(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException;
	
	public boolean isGSTPeriod(Long idCompany, Date dateFrom) throws BusinessException;
	
	public void processGSTPeriod(String loginName, GSTSummaryVO gstSummaryVO, boolean isLockUnlock, boolean isSubmit) throws BusinessException;
	
	/**
	 * Get GST Input Tax Summary Report (or bottom summary)
	 * Get GST Output Tax Summary Report (or bottom summary)
	 * Get GST Input Tax Summary - All Report
	 * Get GST Output Tax Summary - All Report
	 * @param idCompany 
	 * @param searchParamVO 
	 * @param type 
	 * @return
	 * @throws BusinessException
	 */
	public List<GSTReportVO> getGSTTaxList(Long idCompany, SearchParamVO searchParamVO, String type) throws BusinessException;
	
	/**
	 * @param idCompany 
	 * @param searchParamVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<GSTReportVO> getGSTSmmryAcqInputTaxList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * @param idCompany 
	 * @param searchParamVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<GSTReportVO> getGSTYearlyList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException;
	
	/**
	 * 
	 * @param taxCode
	 * @return
	 * @throws BusinessException
	 */
	public TaxCodeVO getTaxCode(String taxCode) throws BusinessException;
	
	public GSTMappingStateVO getGSTMappingState(Date dateTo, String gstField) throws BusinessException;
}
