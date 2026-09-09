package com.bcs.zsg.sales.dao;

import java.util.Date;
import java.util.List;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.core.exception.BusinessException;

import javax.faces.model.SelectItem;

public interface InvoiceItemReportUsageDAO {

	List<AcctViewVO> getInvoiceItemReportUsageList(Long companyId, List<String> itemCodes, Date fromDate, Date toDate,
			List<String> statusCds, List<String> invCatCds, List<String> orderSourceCds, List<String> regionIds, List<String> countryIds) throws BusinessException;

	List<SelectItem> getStatusList() throws BusinessException;

}
