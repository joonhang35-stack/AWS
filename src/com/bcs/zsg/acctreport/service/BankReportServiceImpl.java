package com.bcs.zsg.acctreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acctreport.dao.BankReportDAO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.core.exception.BusinessException;

public class BankReportServiceImpl implements BankReportService{
@Autowired
private transient BankReportDAO bankAccountReportDAO;
	@Override
	public List<BankAcctVO> getBankAcctList() throws BusinessException {
	
		return bankAccountReportDAO.getBankAcctList();
	}
	
}
