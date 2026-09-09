package com.bcs.zsg.acct.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.EInvoiceSchedulerService;
import com.bcs.zsg.acct.vo.EInvoiceDocumentVO;
import com.bcs.zsg.acct.vo.PendingEInvoiceVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class EInvoiceSchedulerBOImpl implements EInvoiceSchedulerBO {

	@Autowired
	private EInvoiceSchedulerService eInvoiceSchedulerService;

	@Override
	public void insertPendingEInvoice(List<InvoiceVO> invoiceList) throws BusinessException {
		eInvoiceSchedulerService.insertPendingEInvoice(invoiceList);
	}
	
	@Override
	public List<PendingEInvoiceVO> getPendingEInvoiceList(Date dtProcess) {
		return eInvoiceSchedulerService.getPendingEInvoiceList(dtProcess);
	}
	
	@Override
	public List<InvoiceVO> getPendingInvoiceForEInvoiceByDate(Date dtProcess) throws BusinessException {
		return eInvoiceSchedulerService.getPendingInvoiceForEInvoiceByDate(dtProcess);
	}
	
	@Override
	public void updatePendingEInvoiceStatus(List<InvoiceVO> resList) throws BusinessException {
		eInvoiceSchedulerService.updatePendingEInvoiceStatus(resList);
	}
	
	@Override
	public int getPendingEInvoiceListSize(Map<String, Object> params) throws BusinessException {
		return eInvoiceSchedulerService.getPendingEInvoiceListSize(params);
	}
	
	@Override
	public List<PendingEInvoiceVO> getPendingEInvoiceList(Map<String, Object> params) throws BusinessException {
		return eInvoiceSchedulerService.getPendingEInvoiceList(params);
	}
	
	@Override
	public void notifyNonSubmittedEInvoice(List<InvoiceVO> resList) throws Exception {
		eInvoiceSchedulerService.notifyNonSubmittedEInvoice(resList);
	}
	
	@Override
	public void notifyInvalidEInvoice(EInvoiceDocumentVO docVO) throws Exception {
		eInvoiceSchedulerService.notifyInvalidEInvoice(docVO);
	}
}
