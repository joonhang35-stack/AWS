package com.bcs.zsg.sales.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.dao.InvoiceItemReportUsageDAO;

import javax.faces.model.SelectItem;

public class InvoiceItemReportUsageBOImpl implements InvoiceItemReportUsageBO {

	@Autowired
	private transient InvoiceItemReportUsageDAO invoiceItemReportUsageDAO;

	@Override
	public List<AcctViewVO> getInvoiceItemReportUsageList(Long companyId, List<String> itemCodes, Date fromDate, Date toDate,
			List<String> statusCds, List<String> invCatCds, List<String> orderSourceCds, List<String> regionIds, List<String> countryIds) throws BusinessException {
		return invoiceItemReportUsageDAO.getInvoiceItemReportUsageList(companyId, itemCodes, fromDate, toDate,
				statusCds, invCatCds, orderSourceCds, regionIds, countryIds);
	}

	@Override
	public List<SelectItem> getStatusList() throws BusinessException {
		return invoiceItemReportUsageDAO.getStatusList();
	}
}
