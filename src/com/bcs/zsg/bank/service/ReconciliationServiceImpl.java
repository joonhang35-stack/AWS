package com.bcs.zsg.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.dao.ReconciliationDAO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankReconVO;
import com.bcs.zsg.common.dao.BaseCommonDAO;
import com.bcs.zsg.common.dao.BaseCommonDAOLM;
import com.bcs.zsg.common.service.BaseCommonServiceLMImpl;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public class ReconciliationServiceImpl extends BaseCommonServiceLMImpl implements ReconciliationService {

	@Autowired
	private ReconciliationDAO reconDAO;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.ReconciliationService#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		reconDAO.insert(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.ReconciliationService#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		reconDAO.update(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.ReconciliationService#getBankReconList(com.bcs.zsg.common.vo.SearchParamVO, java.util.List)
	 */
	@Override
	public List<BankReconVO> getBankReconList(SearchParamVO searchParamVO, List<BankAcctViewVO> bankAcctList) throws BusinessException {
		if (searchParamVO.getObj3() == null || searchParamVO.getObj3().equals(0L)) {
			/*List<Long> bankIdList = null;
			if (CollectionUtils.isNotEmpty(bankAcctList)) {
				bankIdList = new ArrayList<Long>();
				for (BankAcctViewVO vo : bankAcctList) {
					bankIdList.add(vo.getId());
				}
				return reconDAO.getBankReconList(searchParamVO, bankIdList);
			}*/
			return null;
		}
		return reconDAO.getBankReconList(searchParamVO, null);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.ReconciliationService#getBankReconCashBookBal(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public BankReconVO getBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException {
		return reconDAO.getBankReconCashBookBal(searchParamVO, bankReconVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.ReconciliationService#getPrevBankReconCashBookBal(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public double getPrevBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException {
		return reconDAO.getPrevBankReconCashBookBal(searchParamVO, bankReconVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.ReconciliationService#getBankRecon(com.bcs.zsg.bank.vo.BankReconVO, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public BankReconVO getBankRecon(BankReconVO bankReconVO, SearchParamVO searchParamVO) throws BusinessException {
		return reconDAO.getBankRecon(bankReconVO, searchParamVO);
	}

	@Override
	protected BaseCommonDAOLM getLMDAO() {
		return reconDAO;
	}

	@Override
	protected BaseCommonDAO getDAO() {
		return getLMDAO();
	}

}
