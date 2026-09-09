package com.bcs.zsg.bank.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.ChartOfAcctDAO;
import com.bcs.zsg.acct.helper.AccountHelper;
import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.dao.BankAdjustDAO;
import com.bcs.zsg.bank.dao.DepositDAO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;
import com.bcs.zsg.zextra.backend.helper.SysNumGenUtil;

public class BankAdjustServiceImpl implements BankAdjustService {

	@Autowired
	private DepositDAO depositDAO;
	@Autowired
	private BankAdjustDAO bankAdjsutDAO;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SystemNumberGenerationDAO sysNumGenDAO;
	@Autowired
	private ChartOfAcctDAO chartOfAcctDAO;

	@Override
	public void addBankAdjust(CashBookVO cashBookVO, Long idCompany, BankAcctVO bankAcctSearchVO,
			LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT,
			LookupItemVO lookupItemGnDp,LookupItemVO lookupItemBTCD, AcctVO acctVO) throws BusinessException, QueueException {

		SystemNumberGenerationVO sysNumGenVO = new SystemNumberGenerationVO();
		sysNumGenVO.setCode(CommonConstant.SYS_NUM_CD_BANK_ADJM);
		sysNumGenVO.setIdCompany(idCompany);
		sysNumGenVO = SysNumGenUtil.getSysNumber(SysNumGenUtil.getIdx(sysNumGenVO));
		
		// ################ Cash Book ################ Start
		cashBookVO.setSysCode(sysNumGenVO.getCode());
		cashBookVO.setSysPrefix(sysNumGenVO.getPrefixid());
		cashBookVO.setSysNo(String.valueOf(sysNumGenVO.getNextnumber()));
		cashBookVO.setIsClear(false);
		cashBookVO.setIsMark(false);
		//cashBookVO.setPayee("");
		cashBookVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
			cashBookVO.setDebit(0.00);
			cashBookVO.setCredit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			cashBookVO.setTypeCd(lookupItemCBT.getCode());
			cashBookVO.setTransTypeCd(lookupItemBT.getCode());
		} else {
			cashBookVO.setDebit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			cashBookVO.setCredit(0.00);
			cashBookVO.setTypeCd(lookupItemGnDp.getCode());
			cashBookVO.setTransTypeCd(lookupItemBTCD.getCode());
		}
		depositDAO.insert(cashBookVO);
		// ################ Cash Book ################ End

		// ################ Account Transaction [Bank] ################ Start
		AcctTransVO acctTransVO1 = new AcctTransVO();

		acctTransVO1.setCompanyId(idCompany);
		acctTransVO1.setAcctId(bankAcctSearchVO.getIdAcct());
		acctTransVO1.setRefNo(cashBookVO.getRefNo());
		if (StringUtils.isEmpty(acctVO.getSubCode())) {
			acctTransVO1.setCode(acctVO.getCode());
			acctTransVO1.setDesc(acctVO.getDesc());
		} else {	
			acctTransVO1.setCode(acctVO.getCode() + "-" + acctVO.getSubCode());
			acctTransVO1.setDesc(acctVO.getDesc()+","+acctVO.getSubDesc());
		}
		acctTransVO1.setTransDt(cashBookVO.getDtTrans());
		acctTransVO1.setSysPrefix(sysNumGenVO.getPrefixid());
		acctTransVO1.setSysCode(sysNumGenVO.getCode());
		acctTransVO1.setSysNo(String.valueOf(sysNumGenVO.getNextnumber()));
		acctTransVO1.setTransDt(cashBookVO.getDtTrans());
		if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
			acctTransVO1.setDebit(0.00);
			acctTransVO1.setCredit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			acctTransVO1.setType(lookupItemBT.getCode());
		} else {
			acctTransVO1.setDebit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			acctTransVO1.setCredit(0.00);
			acctTransVO1.setType(lookupItemBTCD.getCode());
		}
		acctTransVO1.setSource(sysNumGenVO.getPrefixid() + "-" + acctTransVO1.getSysNo() + "-"+ bankAcctSearchVO.getName());
		acctTransVO1.setDestination(cashBookVO.getRemarks());
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, acctTransVO1);
		
		AccountHelper.roundingAndTaxUpdate(cashBookVO.getAmountCalcViewVO(), acctTransVO1);
		// ################ Account Transaction [Bank] ################ End
		
		// ################ Account Transaction List ################ Start
		if (CollectionUtils.isNotEmpty(cashBookVO.getAcctTransList())) {
			AcctTransVO acctTransVO = new AcctTransVO();
			for (AcctTransViewVO vo : cashBookVO.getAcctTransList()) {
				acctTransVO = new AcctTransVO();
				
				acctTransVO.setCompanyId(idCompany);
				acctTransVO.setRefNo(cashBookVO.getRefNo());
				acctTransVO.setTransDt(cashBookVO.getDtTrans());
				acctTransVO.setSysPrefix(sysNumGenVO.getPrefixid());
				acctTransVO.setSysCode(sysNumGenVO.getCode());
				acctTransVO.setSysNo(String.valueOf(sysNumGenVO.getNextnumber()));
				acctTransVO.setTransDt(cashBookVO.getDtTrans());
				
				if (StringUtils.isEmpty(vo.getAcctViewVO().getSubCode())) {
					acctTransVO.setCode(vo.getCode());
				} else {	
					acctTransVO.setCode(vo.getCode() + "-" + acctVO.getSubCode());
				}
				acctTransVO.setDesc(vo.getDesc());
				acctTransVO.setAcctId(vo.getAcctId());
				if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
					acctTransVO.setCredit(0.00);
					acctTransVO.setDebit(vo.getAmount());
					acctTransVO.setType(lookupItemCBT.getCode());
				} else {
					acctTransVO.setCredit(vo.getAmount());
					acctTransVO.setDebit(0.00);
					acctTransVO.setType(lookupItemGnDp.getCode());
				}
				acctTransVO.setSource(sysNumGenVO.getPrefixid() + "-" + acctTransVO.getSysNo() + "-" + bankAcctSearchVO.getName());
				acctTransVO.setDestination(cashBookVO.getRemarks());
				
				acctTransVO = AccountHelper.updateAcctTransTaxRelated(acctTransVO, vo);
				
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, acctTransVO);
			}
		}
		// ################ Account Transaction List ################ Start
	}

	@Override
	public void updateAdjust(CashBookVO cashBookVO, AcctTransViewVO acctTransCashBookVO, BankAcctVO bankAcctSearchVO,
			LookupItemVO lookupItemCBT, LookupItemVO lookupItemGnDp,
			LookupItemVO lookupItemBT, LookupItemVO lookupItemBTCD) throws BusinessException {
		SystemNumberGenerationVO sysNumGenVO = sysNumGenDAO.getSystemNumberGeneration(cashBookVO.getSysCode(), bankAcctSearchVO.getIdCompany());
		
		// TODO Auto-generated method stub
		cashBookVO.setIdBank(bankAcctSearchVO.getId());
		if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
			cashBookVO.setDebit(0.00);
			cashBookVO.setCredit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			cashBookVO.setTypeCd(lookupItemCBT.getCode());
			cashBookVO.setTransTypeCd(lookupItemBT.getCode());
		} else {
			cashBookVO.setDebit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			cashBookVO.setCredit(0.00);
			cashBookVO.setTypeCd(lookupItemGnDp.getCode());
			cashBookVO.setTransTypeCd(lookupItemBTCD.getCode());
		}
		bankAdjsutDAO.update(cashBookVO);
		
		acctTransCashBookVO.setTransDt(cashBookVO.getDtTrans());
		acctTransCashBookVO.setRefNo(cashBookVO.getRefNo());
		acctTransCashBookVO.setCompanyId(bankAcctSearchVO.getIdCompany());
		acctTransCashBookVO.setAcctId(bankAcctSearchVO.getIdAcct());
		if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
			acctTransCashBookVO.setDebit(0.00);
			acctTransCashBookVO.setCredit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			acctTransCashBookVO.setType(lookupItemBT.getCode());
		} else {
			acctTransCashBookVO.setDebit(cashBookVO.getAmountCalcViewVO().getTotalBalance().doubleValue());
			acctTransCashBookVO.setCredit(0.00);
			acctTransCashBookVO.setType(lookupItemBTCD.getCode());
		}
		acctTransCashBookVO.setDestination(cashBookVO.getRemarks());
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD,acctTransCashBookVO);

		// terminate account trans
		//chartOfAcctDAO.terminateAcctTrans(cashBookVO.getSysCode(), cashBookVO.getSysNo(), cashBookVO.getTypeCd(), bankAcctSearchVO.getIdCompany());
		
		AccountHelper.roundingAndTaxUpdate(cashBookVO.getAmountCalcViewVO(), acctTransCashBookVO);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("companyId", acctTransCashBookVO.getCompanyId());
		params.put("sysCode", acctTransCashBookVO.getSysCode());
		params.put("sysNo", acctTransCashBookVO.getSysNo());
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		params.put("type", cashBookVO.getTypeCd());
		List<AcctTransVO> acctTransList = accountService.getAccountTransList(params);
		
		// add / update account trans
		if (CollectionUtils.isNotEmpty(cashBookVO.getAcctTransList())) {
			for (AcctTransViewVO vo : cashBookVO.getAcctTransList()) {
				
				if (vo.getId() == null) {
					vo.setCompanyId(bankAcctSearchVO.getIdCompany());
					vo.setAcctId(vo.getAcctViewVO().getId());
					vo.setTransDt(cashBookVO.getDtTrans());
					vo.setSysCode(sysNumGenVO.getCode());
					vo.setSysPrefix(cashBookVO.getSysPrefix());
					vo.setSysNo(cashBookVO.getSysNo());
					vo.setRefNo(cashBookVO.getRefNo());
					vo.setSource(sysNumGenVO.getPrefixid() + "-" + vo.getSysNo() + "-" + bankAcctSearchVO.getName());
					vo.setDestination(cashBookVO.getRemarks());
					
					if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
						vo.setCredit(0.00);
						vo.setDebit(vo.getAmount());
						vo.setType(lookupItemCBT.getCode());
					} else {
						vo.setCredit(vo.getAmount());
						vo.setDebit(0.00);
						vo.setType(lookupItemGnDp.getCode());
					}
					accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD, vo);
					
				} else {
					if (CollectionUtils.isNotEmpty(acctTransList)) {
						for (int i = acctTransList.size() - 1 ; i >= 0 ; i--) {
							AcctTransVO vo1 = acctTransList.get(i);
							
							if (vo1.getId().longValue() == vo.getId().longValue()) {
								vo1.setAcctId(vo.getAcctViewVO().getId());
								vo1.setTransDt(cashBookVO.getDtTrans());
								vo1.setRefNo(cashBookVO.getRefNo());
								if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
									vo1.setCredit(0.00);
									vo1.setDebit(vo.getAmount());
									vo1.setType(lookupItemCBT.getCode());
								} else {
									vo1.setCredit(vo.getAmount());
									vo1.setDebit(0.00);
									vo1.setType(lookupItemGnDp.getCode());
								}
								vo1.setDestination(cashBookVO.getRemarks());
								vo1.setTaxCode(vo.getTaxCode());
								vo1.setTaxRate(vo.getTaxRate());
								vo1.setTaxAmount(vo.getTaxAmount());
								vo1.setCode(vo.getCode());
								vo1.setDesc(vo.getDesc());
								accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD, vo1);
								acctTransList.remove(i);
								break;
							}
						}
					} else {
						vo.setAcctId(vo.getAcctViewVO().getId());
						vo.setTransDt(cashBookVO.getDtTrans());
						vo.setRefNo(cashBookVO.getRefNo());
						if(cashBookVO.getAdjmType().equals("gnrl_pymt")) {
							vo.setCredit(0.00);
							vo.setDebit(vo.getAmount());
							vo.setType(lookupItemCBT.getCode());
						} else {
							vo.setCredit(vo.getAmount());
							vo.setDebit(0.00);
							vo.setType(lookupItemGnDp.getCode());
						}
						//vo.setSource(sysNumGenVO.getPrefixid() + "-" + vo.getSysNo() + "-"+ bankAcctSearchVO.getName());
						vo.setDestination(cashBookVO.getRemarks());
						accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD, vo);
					}
				}
			}
		}
		
		// terminate account trans
		if (CollectionUtils.isNotEmpty(acctTransList)) {
			for (AcctTransVO vo1 : acctTransList) {
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL, vo1);
			}
		}
	}

	@Override
	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo,
			String typeCd, String sysCode, Long CompId) throws BusinessException {
		// TODO Auto-generated method stub
		return bankAdjsutDAO.getAcctTransViewTableList(sysNo,typeCd,sysCode,CompId);
	}

	@Override
	public List<CashBookVO> getCashBookBankAdjustList(String bankAdjust,  Long bankId, SearchParamVO searchParamVO) throws BusinessException {
		return bankAdjsutDAO.getCashBookBankAdjustList(bankAdjust,bankId, searchParamVO);
	}

	@Override
	public List<LookupItemVO> getLookUpItemBTTList(String bankTransType)
			throws BusinessException {
		return bankAdjsutDAO.getLookUpItemBTTList(bankTransType);
	}

	@Override
	public AcctVO getAcctList(Long acctId) throws BusinessException {
		return bankAdjsutDAO.getAcctList(acctId);
	}
}
