package com.bcs.zsg.bank.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.service.BankAcctService;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankAddrVO;
import com.bcs.zsg.bank.vo.BankContactVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;

public class BankAcctBOImpl implements BankAcctBO {

	@Autowired
	private BankAcctService bankAcctService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.BankAcctBO#getBankAcctList(java.lang.Long)
	 */
	@Override
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException {
		return bankAcctService.getBankAcctList(idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.BankAcctBO#addBank(com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.bank.vo.BankAddrVO, java.util.List)
	 */
	@Override
	public void addBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, List<BankContactVO> bankContactList) throws BusinessException {
		bankAcctService.addBank(bankAcctVO, bankAddrVO, bankContactList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.BankAcctBO#updBank(com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.bank.vo.BankAddrVO, java.util.List)
	 */
	@Override
	public void updBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, AddUpdDelVO bankContactAUDVO) throws BusinessException {
		bankAcctService.updBank(bankAcctVO, bankAddrVO, bankContactAUDVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.BankAcctBO#delBank(com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.bank.vo.BankAddrVO, java.util.List)
	 */
	@Override
	public void delBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, List<BankContactVO> bankContactList) throws BusinessException {
		bankAcctService.delBank(bankAcctVO, bankAddrVO, bankContactList);
	}

}
