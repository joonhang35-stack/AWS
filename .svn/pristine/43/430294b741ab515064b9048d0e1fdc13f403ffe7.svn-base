package com.bcs.zsg.bank.service;

import java.util.List;

import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankAddrVO;
import com.bcs.zsg.bank.vo.BankContactVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface BankAcctService {

	/**
	 * 
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param bankAcctVO
	 * @param bankAddrVO
	 * @param bankContactList
	 * @throws BusinessException
	 */
	public void addBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, List<BankContactVO> bankContactList) throws BusinessException;

	/**
	 * 
	 * @param bankAcctVO
	 * @param bankAddrVO
	 * @param bankContactAUDVO
	 * @throws BusinessException
	 */
	public void updBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, AddUpdDelVO bankContactAUDVO) throws BusinessException;

	/**
	 * 
	 * @param bankAcctVO
	 * @param bankAddrVO
	 * @param bankContactList
	 * @throws BusinessException
	 */
	public void delBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, List<BankContactVO> bankContactList) throws BusinessException;

}
