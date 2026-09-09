package com.bcs.zsg.db.bterp.dao.gst;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.gst.GSTGAFVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTMappingStateVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTReportVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryTaxVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;

public interface GSTDAO extends BaseDAO {

	public List<TaxCodeVO> getTaxCodeList(String taxType, String gstType) throws BusinessException;
	
	public List<String> getNonClaimableTaxList() throws BusinessException;
	
	public List<GSTSummaryVO> getMonthlySummaryList(Map<String, Object> params) throws BusinessException;

	public double getTaxCodeTotal(Map<String, Object> params) throws BusinessException;

	public List<GSTSummaryTaxVO> getTaxSummaryVOList(Map<String, Object> params) throws BusinessException;

	public GSTSummaryVO getMonthSummary(Map<String, Object> params) throws BusinessException;

	public List<TaxCodeVO> getTaxSummaryList(Map<String, Object> params, boolean includeCurrentMonth) throws BusinessException;
	
	public TaxCodeVO getTaxCode(Map<String, Object> params) throws BusinessException;
	
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
	 * @param idCompany
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * @throws BusinessException
	 */
	public List<GSTGAFVO> getSupplyList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException;
	
	/**
	 * @param idCompany
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * @throws BusinessException
	 */
	public List<GSTGAFVO> getPurchasesList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException;
	
	/**
	 * @param idCompany
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * @throws BusinessException
	 */
	public List<GSTGAFVO> getLedgerList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException;

	/**
	 * @param dateTo
	 * @return
	 * @throws BusinessException
	 */
	public GSTMappingStateVO getGSTMappingState(Date dateTo, String gstField) throws BusinessException;
	
}
