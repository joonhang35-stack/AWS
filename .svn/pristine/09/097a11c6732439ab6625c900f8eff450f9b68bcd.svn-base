package com.bcs.zsg.bank.bo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.service.CashBookService;
import com.bcs.zsg.bank.service.ReconciliationService;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankReconVO;
import com.bcs.zsg.bank.vo.CashBookSumVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.bo.CommonObject;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public class ReconciliationBOImpl implements ReconciliationBO {

	@Autowired
	private ReconciliationService reconService;

	@Autowired
	private CashBookService cashBookService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.ReconciliationBO#insertVO(com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public boolean insertVO(BaseVO vo) throws BusinessException {
		CommonObject tmpComObj = new CommonObject("Reconciliation:insertVO");
		List<Object> tmpListResult = new ArrayList<Object>();
		tmpListResult.add(BankReconVO.class);
		tmpListResult.add(((BankReconVO)vo).getIdBank());
		tmpListResult.add(((BankReconVO)vo).getMonth());
		tmpListResult.add(((BankReconVO)vo).getYear());
		tmpComObj.setListSource(tmpListResult);
		
		if(reconService.getRecordList(tmpComObj).size() > 0) return false;
		else {
			reconService.insertVO(vo);
			//reconService.recalBankRecon(vo);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.ReconciliationBO#updateVO(com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		//Additional handling to update cashbook table instead of class CashBookSumVO which is used for viewing.
		if(vo.getClass().equals(CashBookSumVO.class)) {
			CashBookVO sourceVO = cashBookService.getRecordByID(vo.getId(), CashBookVO.class);
			sourceVO.setIsClear(((CashBookSumVO)vo).getIsClear());
			reconService.updateVO(sourceVO);
		} else reconService.updateVO(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.ReconciliationBO#getBankReconList(com.bcs.zsg.common.vo.SearchParamVO, java.util.List)
	 */
	@Override
	public List<BankReconVO> getBankReconList(SearchParamVO searchParamVO, List<BankAcctViewVO> bankAcctList) throws BusinessException {
		return reconService.getBankReconList(searchParamVO, bankAcctList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.ReconciliationBO#getBankReconCashBookBal(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public BankReconVO getBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException {
		return reconService.getBankReconCashBookBal(searchParamVO, bankReconVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.ReconciliationBO#getPrevBankReconCashBookBal(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public double getPrevBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException {
		return reconService.getPrevBankReconCashBookBal(searchParamVO, bankReconVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.ReconciliationBO#getBankRecon(com.bcs.zsg.bank.vo.BankReconVO, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public BankReconVO getBankRecon(BankReconVO bankReconVO, SearchParamVO searchParamVO) throws BusinessException {
		return reconService.getBankRecon(bankReconVO, searchParamVO);
	}

	@Override
	public <S> List<?> getLazyRecordList(S _commonObj, int _first,
			int _pageSize, String _sortField, SortOrder _sortOrder)
			throws BusinessException {
		return null;
	}

	@Override
	public <S> int getRowCount(S _commonObj) throws BusinessException {
		return 0;
	}

	@Override
	public <S> S getRecordByID(long _ID, Class<S> _VOClass)
			throws BusinessException {
		return null;
	}

	@Override
	public <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass)
			throws BusinessException {
		return null;
	}

	@Override
	public <S> List<?> getRecordList(S _commonObj) throws BusinessException {
		return null;
	}

	@Override
	public <S> void deleteRecord(List<S> _listVO) throws BusinessException,
			SecurityException, IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		reconService.deleteRecordByID(_listVO);
	}
}
