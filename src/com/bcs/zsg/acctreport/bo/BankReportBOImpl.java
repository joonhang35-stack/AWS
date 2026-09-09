package com.bcs.zsg.acctreport.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acctreport.service.BankReportService;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.core.exception.BusinessException;

public class BankReportBOImpl implements BankReportBO{
@Autowired
private transient BankReportService bankAccountReportService;
	@Override
	public List<BankAcctVO> getBankAcctList() throws BusinessException {

		return bankAccountReportService.getBankAcctList();
	}

}
