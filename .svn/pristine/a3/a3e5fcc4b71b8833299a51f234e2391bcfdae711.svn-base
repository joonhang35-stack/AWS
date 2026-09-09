package com.bcs.zsg.bank.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.purchase.vo.SupplierVO;

public interface PaymentDAO extends BaseDAO {

	public List<AcctTransVO> getAcctTransList(Long compId) throws BusinessException;

	public List<CashBookVO> getCashBookList() throws BusinessException;

	public List<BankAcctVO> getBankAcctList() throws BusinessException;

	public List<AcctVO> getAcctList(Long idCompany, String strAutoComplete) throws BusinessException;

	public AcctVO getAcctDescList(String acctCd, String acctSubCd, Long compId) throws BusinessException;

	public BankAcctVO getBankAcctSearchList(Long idBank) throws BusinessException;

	public List<AcctVO> getAcctCodeList(Long idAccount) throws BusinessException;

	public List<AcctTransViewVO> getAcctTransListView() throws BusinessException;

	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo,
			String transTypeCd, String sysCode, String statusActive, Long compId) throws BusinessException;

	public List<AcctTransViewVO> getAcctTransSysNoList(String sysNo, String sysCode, Long compId) throws BusinessException; 
	
	public LookupItemVO getlookupItemBT(String cd) throws BusinessException;

	public LookupItemVO getlookupItemCBT(String gd) throws BusinessException;

	public SupplierVO getSupplier(Long supplierId, Long idCompany) throws BusinessException;

	public AcctVO getAcctSearchList(Long idAcct) throws BusinessException;

	public List<CashBookVO> getBankCashBookList(String sysNumCdBankPmnt, Long idCompany, SearchParamVO searchParamVO, Map<String, Object> params) throws BusinessException;
	
	public int getBankCashBookListSize(String sysNumCdBankPmnt, Long idCompany, SearchParamVO searchParamVO, Map<String, Object> params) throws BusinessException;

	public int getCashBookListSize(Map<String, Object> params) throws BusinessException;
	public List<CashBookVO> getCashBookList(Map<String, Object> params) throws BusinessException;
	
}
