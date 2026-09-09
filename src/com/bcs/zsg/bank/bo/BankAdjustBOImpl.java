package com.bcs.zsg.bank.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.service.BankAdjustService;
import com.bcs.zsg.bank.service.DepositService;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public class BankAdjustBOImpl implements BankAdjustBO {

	/**@Autowired
	private PaymentService paymentService;
	 **/
	@Autowired
	private DepositService depositService;
	@Autowired
	private BankAdjustService bankAdjustService;

	@Override
	public void addBankAdjust(CashBookVO cashBookVO, Long idCompany, BankAcctVO bankAcctSearchVO, LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, LookupItemVO lookupItemGnDp, LookupItemVO lookupItemBTCD, AcctVO acctVO) throws BusinessException, QueueException {
		checkTaxCodeGroup(cashBookVO);
		bankAdjustService.addBankAdjust(cashBookVO, idCompany, bankAcctSearchVO, lookupItemBT, lookupItemCBT, lookupItemGnDp, lookupItemBTCD, acctVO);
	}

	@Override
	public List<LookupItemVO> getLookUpItemBTTList(String bankTransType) throws BusinessException {
		return bankAdjustService.getLookUpItemBTTList(bankTransType);
	}

	@Override
	public AcctVO getAcctList(Long acctId) throws BusinessException {
		return bankAdjustService.getAcctList(acctId);
	}

	@Override
	public void updateAdjust(CashBookVO cashBookVO, AcctTransViewVO acctTransCashBookVO, BankAcctVO bankAcctSearchVO, LookupItemVO lookupItemCBT, LookupItemVO lookupItemGnDp, LookupItemVO lookupItemBT, LookupItemVO lookupItemBTCD) throws BusinessException {
		checkTaxCodeGroup(cashBookVO);
		bankAdjustService.updateAdjust(cashBookVO, acctTransCashBookVO, bankAcctSearchVO, lookupItemCBT, lookupItemGnDp, lookupItemBT, lookupItemBTCD);
	}

	@Override
	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo,
			String typeCd, String sysCode, Long CompId) throws BusinessException {
		return bankAdjustService.getAcctTransViewTableList(sysNo,typeCd,sysCode,CompId);
	}

	@Override
	public List<CashBookVO> getCashBookBankAdjustList(String bankAdjust, Long bankId, SearchParamVO searchParamVO) throws BusinessException {
		return bankAdjustService.getCashBookBankAdjustList(bankAdjust,bankId, searchParamVO);
	}
	
	/**
	 * Check whether tax code missing or not
	 * @param exOrderBillVO
	 * @param acctTransVO
	 * @throws BusinessException
	 */
	private void checkTaxCodeGroup(CashBookVO cashBookVO) throws BusinessException {
		// check account trans missing tax code
		int flag = 0;
		for (AcctTransVO vo : cashBookVO.getAcctTransList()) {
			if (StringUtils.isEmpty(vo.getTaxCode())) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) cashBookVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_Y);
		else cashBookVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_N);
	}

}
