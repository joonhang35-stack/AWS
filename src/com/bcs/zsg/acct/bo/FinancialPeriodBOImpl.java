package com.bcs.zsg.acct.bo;

import static com.bcs.zsg.common.helper.LookupItemConstant.CACHE_KEY_PREFIX;
import static com.bcs.zsg.core.helper.BaseConstant.PAD_SLASH;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.FinancialPeriodService;
import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.cache.service.CacheService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;

public class FinancialPeriodBOImpl implements FinancialPeriodBO {

	@Autowired
	private FinancialPeriodService finPeriodService;
	@Autowired
	protected CacheService cacheService;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		finPeriodService.insertVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		finPeriodService.updateVO(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#getfinPeriodList(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<FinancialPeriodVO> getFinPeriodList(Long idCompany, String year) throws BusinessException {
		return finPeriodService.getFinPeriodList(idCompany, year);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#addFinPeriod(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public void addFinPeriod(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		finPeriodService.addFinPeriod(idCompany, searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#getfinPeriod(java.lang.Long)
	 */
	@Override
	public FinancialPeriodVO getFinPeriod(Long idCompany) throws BusinessException {
		FinancialPeriodVO finPeriodVO = finPeriodService.getFinPeriod(idCompany);
		if (finPeriodVO == null) throw new BusinessException(CommonErrConstant.ERR_ACCT_CLOSE_PERIOD_UNAVAILABLE);
		return finPeriodVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#updFinPeriod(java.lang.Long, java.lang.String, com.bcs.zsg.acct.vo.FinancialPeriodVO)
	 */
	@Override
	public void updFinPeriod(Long idCompany, String userName, FinancialPeriodVO finPeriodVO) throws BusinessException {
		//finPeriodVO.setStatus(false);
		//updateVO(finPeriodVO);
		finPeriodService.updFinPeriod(idCompany, userName, finPeriodVO);
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + idCompany + PAD_SLASH + LookupItemConstant.FIN_PERIOD_LOCK);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#getFinPeriodLock(java.lang.Long)
	 */
	@Override
	public FinancialPeriodLockVO getFinPeriodLock(Long idCompany) throws BusinessException {
		return finPeriodService.getFinPeriodLock(idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.FinancialPeriodBO#updateFinPeriodLock(com.bcs.zsg.acct.vo.FinancialPeriodLockVO)
	 */
	@Override
	public void updateFinPeriodLock(FinancialPeriodLockVO finPeriodLockVO) throws BusinessException {
		updateVO(finPeriodLockVO);
		cacheService.remove(CACHE_KEY_PREFIX + PAD_SLASH + finPeriodLockVO.getIdCompany() + PAD_SLASH + LookupItemConstant.FIN_PERIOD_LOCK);
	}

	@Override
	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException {
		return finPeriodService.isDateInFinPeriodClosed(idCompany, date);
	}
}
