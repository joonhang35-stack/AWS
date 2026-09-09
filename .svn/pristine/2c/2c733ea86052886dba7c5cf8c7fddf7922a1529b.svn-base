package com.bcs.zsg.acctreport.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acctreport.service.AccountReportService;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourThemeVO;

public class AccountReportBOImpl implements AccountReportBO {

	@Autowired
	private transient AccountReportService acctRptService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acctreport.bo.AccountReportBO#getAccountReport(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO, java.lang.String)
	 */
	@Override
	public List<?> getAccountReport(Long idCompany, SearchParamVO searchParamVO, String reportType) throws BusinessException {
		return acctRptService.getAccountReport(idCompany, searchParamVO, reportType);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acctreport.bo.AccountReportBO#getTourThemeList()
	 */
	public List<TourThemeVO> getTourThemeList() throws BusinessException {
		return acctRptService.getTourThemeList();
	}
}
