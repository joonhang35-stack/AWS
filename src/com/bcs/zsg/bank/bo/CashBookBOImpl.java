package com.bcs.zsg.bank.bo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.service.CashBookService;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookBalVO;
import com.bcs.zsg.bank.vo.CashBookSumVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;

public class CashBookBOImpl implements CashBookBO {

	@Autowired
	private CashBookService cashBookService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#getCashBookList(java.lang.Long)
	 */
	@Override
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException {
		return cashBookService.getBankAcctList(idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#getCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public <T> List<T> getCashBookList(SearchParamVO searchParamVO, Object... method) throws BusinessException {
		return cashBookService.getCashBookList(searchParamVO, method);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#getPrevCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public <T> List<T> getPrevCashBookList(SearchParamVO searchParamVO, Object... method) throws BusinessException {
		return cashBookService.getPrevCashBookList(searchParamVO, method);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#getCashBookBal(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public CashBookBalVO getCashBookBal(SearchParamVO searchParamVO) throws BusinessException {
		return cashBookService.getCashBookBal(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#updCashBook(com.bcs.zsg.bank.vo.CashBookVO)
	 */
	@Override
	public void updCashBook(CashBookVO cashBookVO) throws BusinessException {
		cashBookService.updCashBook(cashBookVO);
	}

	@Override
	public <S> List<?> getLazyRecordList(S _commonObj, int _first,
			int _pageSize, String _sortField, SortOrder _sortOrder)
			throws BusinessException {
		return cashBookService.getLazyRecordList(_commonObj, _first, _pageSize, _sortField, _sortOrder);
	}

	@Override
	public <S> int getRowCount(S _commonObj) throws BusinessException {
		return cashBookService.getRowCount(_commonObj);
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
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#updCashBookClear(java.lang.Object[], java.util.List)
	 */
	@Override
	public void updCashBookClear(List<CashBookSumVO> selectedCBList, List<CashBookSumVO> unselectedCBList) throws BusinessException {
		cashBookService.updCashBookClear(selectedCBList, unselectedCBList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#getCashBookVOById(java.lang.Long)
	 */
	@Override
	public CashBookVO getCashBookById(Long id) throws BusinessException {
		return cashBookService.getCashBookById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.CashBookBO#getCashBookBySysNo(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CashBookVO getCashBookBySysNo(Long idCompany, String sysCode, String sysNo, String transTypeCd) throws BusinessException {
		return cashBookService.getCashBookBySysNo(idCompany, sysCode, sysNo, transTypeCd);
	}

}
