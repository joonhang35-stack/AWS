package com.bcs.zsg.gst.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.gst.GSTMappingStateVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTReportVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.helper.GSTType;
import com.bcs.zsg.gst.service.GSTService;

public class GSTBOImpl implements GSTBO {

	@Autowired
	private GSTService gstService;

	@Override
	public List<TaxCodeVO> getTaxCodeList(GSTType gstType) throws BusinessException {
		return gstService.getTaxCodeList(gstType);
	}
	
	@Override
	public GSTVO getTaxReturnData(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {
		return gstService.getTaxReturnData(idCompany, dateFrom, dateTo, summaryId);
	}
	
	@Override
	public GSTVO getGAFData(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {
		return gstService.getTaxReturnData(idCompany, dateFrom, dateTo, summaryId);
	}
	
	@Override
	public StringBuilder getGAFData(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException{
		return gstService.getGAFData(idCompany, dateFrom, dateTo);
	}
	
	@Override
	public List<GSTSummaryVO> getMonthlySummaryList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		return gstService.getMonthlySummaryList(idCompany, dateFrom, dateTo);
	}
	

	@Override
	public GSTSummaryVO getTaxSummary(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {
		return gstService.getTaxSummary(idCompany, dateFrom, dateTo, summaryId);
	}
	
	@Override
	public boolean isGSTPeriod(Long idCompany, Date dateFrom) throws BusinessException {
		return gstService.isGSTPeriod(idCompany, dateFrom);
	}
	@Override
	public void processGSTPeriod(String loginName, GSTSummaryVO gstSummaryVO, boolean isLockUnlock, boolean isSubmit) throws BusinessException {
		gstService.processGSTMonthClose(gstSummaryVO.getIdCompany(), loginName, gstSummaryVO.getProcessed(), 
											gstSummaryVO.getDateFrom(), gstSummaryVO.getDateTo(), isLockUnlock, isSubmit);
	}
	
	/*
	 * /(non-Javadoc)
	 * @see com.bcs.zsg.gst.bo.GSTBO#getGSTTaxList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO, java.lang.String)
	 */
	@Override
	public List<GSTReportVO> getGSTTaxList(Long idCompany, SearchParamVO searchParamVO, String type) throws BusinessException {
		return gstService.getGSTTaxList(idCompany, searchParamVO, type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.gst.bo.GSTBO#getGSTSmmryAcqInputTaxList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<GSTReportVO> getGSTSmmryAcqInputTaxList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return gstService.getGSTSmmryAcqInputTaxList(idCompany, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.gst.bo.GSTBO#getGSTYearlyList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<GSTReportVO> getGSTYearlyList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return gstService.getGSTYearlyList(idCompany, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.gst.bo.GSTBO#getTaxCode(java.lang.String)
	 */
	@Override
	public TaxCodeVO getTaxCode(String taxCode) throws BusinessException {
		return gstService.getTaxCode(taxCode);
	}

	@Override
	public GSTMappingStateVO getGSTMappingState(Date dateTo, String gstField) throws BusinessException {
		return gstService.getGSTMappingState(dateTo, gstField);
	}
}
