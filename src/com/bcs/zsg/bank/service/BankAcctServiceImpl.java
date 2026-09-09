package com.bcs.zsg.bank.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.ChartOfAcctDAO;
import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.bank.dao.BankAcctDAO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankAddrVO;
import com.bcs.zsg.bank.vo.BankContactVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;

public class BankAcctServiceImpl implements BankAcctService {

	@Autowired
	private BankAcctDAO bankAcctDAO;
	@Autowired
	private ChartOfAcctDAO chartOfAcctDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.BankAcctService#getBankAcctList(java.lang.Long)
	 */
	@Override
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException {
		List<BankAcctViewVO> bankAcctList = bankAcctDAO.getBankAcctList(idCompany);
		if (CollectionUtils.isNotEmpty(bankAcctList)) {
			AcctBalVO acctBalVO = null;
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			
			for (BankAcctViewVO vo : bankAcctList) {
				Date date = chartOfAcctDAO.getFirtFinPeriod(idCompany, cal.getTime());
				acctBalVO = chartOfAcctDAO.getAcctBeginBalByDate(vo.getAcctViewVO().getId(), date); //chartOfAcctDAO.getAcctBeginBalVO(vo.getAcctViewVO().getId());
				if (acctBalVO == null) acctBalVO = chartOfAcctDAO.getAcctBeginBalVO(vo.getAcctViewVO().getId());
				vo.setCurrentBal(acctBalVO.getDebitBeginBal() - acctBalVO.getCreditBeginBal() + bankAcctDAO.getBankCurrentBal(vo.getId(), acctBalVO.getDtBeginBal()));
				cal.setTime(acctBalVO.getDtBeginBal());
			}
		}
		
		return bankAcctList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.BankAcctService#addBank(com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.bank.vo.BankAddrVO, java.util.List)
	 */
	@Override
	public void addBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, List<BankContactVO> bankContactList) throws BusinessException {
		// insert bank info
		bankAcctDAO.insert(bankAcctVO);
		// insert bank address
		bankAddrVO.setIdBank(bankAcctVO.getId());
		bankAddrVO.setTypeCd("OFFICE");
		bankAcctDAO.insert(bankAddrVO);
		// insert bank contacts
		if (CollectionUtils.isNotEmpty(bankContactList)) {
			for (BankContactVO vo : bankContactList) {
				vo.setIdBank(bankAcctVO.getId());
				bankAcctDAO.insert(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.BankAcctService#updBank(com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.bank.vo.BankAddrVO, java.util.List)
	 */
	@Override
	public void updBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, AddUpdDelVO bankContactAUDVO) throws BusinessException {
		// update bank info
		bankAcctDAO.update(bankAcctVO);
		// update bank address
		bankAcctDAO.update(bankAddrVO);
		// add new bank contact
		if (CollectionUtils.isNotEmpty(bankContactAUDVO.getAddList())) {
			for (Object obj : bankContactAUDVO.getAddList()) {
				BankContactVO vo = (BankContactVO) obj;
				vo.setIdBank(bankAcctVO.getId());
				bankAcctDAO.insert(vo);
			}
		}
		// update bank contact
		if (CollectionUtils.isNotEmpty(bankContactAUDVO.getUpdList())) {
			for (Object obj : bankContactAUDVO.getUpdList()) bankAcctDAO.update((BankContactVO) obj);
		}
		// delete bank contact
		if (CollectionUtils.isNotEmpty(bankContactAUDVO.getDelList())) {
			for (Object obj : bankContactAUDVO.getDelList()) bankAcctDAO.delete((BankContactVO) obj);
		}
		
		// update bank contacts
		/*if (CollectionUtils.isNotEmpty(bankContactList)) {
			for (BankContactVO vo : bankContactList) {
				bankAcctDAO.update(vo);
			}
		}*/
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.BankAcctService#delBank(com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.bank.vo.BankAddrVO, java.util.List)
	 */
	@Override
	public void delBank(BankAcctVO bankAcctVO, BankAddrVO bankAddrVO, List<BankContactVO> bankContactList) throws BusinessException {
		// delete bank contacts 
		if (CollectionUtils.isNotEmpty(bankContactList)) { 
			for (BankContactVO vo : bankContactList) {
				bankAcctDAO.delete(vo);
			}
		}
		// delete bank address
		bankAcctDAO.delete(bankAddrVO);
		// delete bank
		bankAcctDAO.delete(bankAcctVO);
	}

}
