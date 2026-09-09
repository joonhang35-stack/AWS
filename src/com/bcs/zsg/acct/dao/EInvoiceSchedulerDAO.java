package com.bcs.zsg.acct.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.PendingEInvoiceVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface EInvoiceSchedulerDAO extends BaseDAO {
	public PendingEInvoiceVO getPendingEInvoiceVOById(Long id) throws BusinessException;

	public PendingEInvoiceVO getPendingEInvoiceVO(Long refId, String processStatus, String submitStatus, Date dtProcess)
			throws BusinessException;

	public List<PendingEInvoiceVO> getPendingEInvoiceList(Date dtProcess);

	public List<PendingEInvoiceVO> getPendingEInvoiceList(Map<String, Object> params) throws BusinessException;

	public int getPendingEInvoiceListSize(Map<String, Object> params) throws BusinessException;
}
