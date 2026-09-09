package com.bcs.zsg.acct.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.EInvoiceDocumentVO;
import com.bcs.zsg.acct.vo.PendingEInvoiceVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface EInvoiceSchedulerService {
	public void insertPendingEInvoice(List<InvoiceVO> invoiceList) throws BusinessException;

	public List<PendingEInvoiceVO> getPendingEInvoiceList(Date dtProcess);

	public List<InvoiceVO> getPendingInvoiceForEInvoiceByDate(Date dtProcess) throws BusinessException;

	public void updatePendingEInvoiceStatus(List<InvoiceVO> resList) throws BusinessException;

	public int getPendingEInvoiceListSize(Map<String, Object> params) throws BusinessException;

	public List<PendingEInvoiceVO> getPendingEInvoiceList(Map<String, Object> params) throws BusinessException;

	public void notifyNonSubmittedEInvoice(List<InvoiceVO> resList) throws BusinessException, Exception;

	public void notifyInvalidEInvoice(EInvoiceDocumentVO docVO) throws Exception;
}
