package com.bcs.zsg.common.service;

import static com.bcs.zsg.common.helper.LookupItemConstant.CACHE_KEY_PREFIX;
import static com.bcs.zsg.core.helper.BaseConstant.PAD_SLASH;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.FinancialPeriodDAO;
import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.common.dao.LookupItemDAO;
import com.bcs.zsg.common.helper.LookupItemConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.cache.service.CacheService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.gst.helper.GSTProcessStatus;
import com.bcs.zsg.gst.service.GSTService;
import com.bcs.zsg.history.service.HistoryService;
import com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public class LookupItemServiceImpl implements LookupItemService {
	private static final Logger logger = LoggerFactory.getLogger(LookupItemServiceImpl.class);

	@Autowired
	protected LookupItemDAO lookupItemDAO;
	@Autowired
	protected FinancialPeriodDAO finPeriodDAO;
	@Autowired
	protected SystemNumberGenerationDAO sysNumGenDAO;
	
	@Autowired
	protected CacheService cacheService;
	@Autowired
	protected HistoryService historyService;

	@Autowired
	protected GSTService gstService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getLookupItemList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LookupItemVO> getLookupItemList(String category) {
		String cacheKey = CACHE_KEY_PREFIX + PAD_SLASH + category;
		List<LookupItemVO> lookupItemList = cacheService.get(cacheKey, List.class);
		
		// if cache not empty
		if (lookupItemList != null) return lookupItemList;
		
		// retrieve main list (lookup item)
		lookupItemList = lookupItemDAO.getLookupItemList(category);
		
		// still not found, log a warning.
		if (CollectionUtils.isEmpty(lookupItemList)) logger.warn("[getLookupItemList: not found category]={}", category);
		else {
			// cache result
			cacheService.put(cacheKey, Collections.unmodifiableList(lookupItemList));
			logger.info("[getLookupItemList: category, list]={},{}", new Object[] {category, CollectionUtils.getSize(lookupItemList)});
		}
		return lookupItemList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getLookupItemVO(java.lang.String, java.lang.String)
	 */
	@Override
	public LookupItemVO getLookupItemVO(String category, String typeCd) {
		List<LookupItemVO> lookupItemList = getLookupItemList(category);
		if (CollectionUtils.isNotEmpty(lookupItemList)) {
			for (LookupItemVO vo : lookupItemList) {
				if (vo.getCode().toLowerCase().equals(typeCd.toLowerCase())) return vo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getLookupItemDesc(java.lang.String, java.lang.String)
	 */
	@Override
	public String getLookupItemDesc(String category, String typeCd) {
		LookupItemVO vo = getLookupItemVO(category, typeCd);
		return (vo != null) ? vo.getDescription() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getGlobalConfigList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GlobalConfigVO> getGlobalConfigList(String category) {
		String cacheKey = CACHE_KEY_PREFIX + PAD_SLASH + category;
		List<GlobalConfigVO> globalConfigList = cacheService.get(cacheKey, List.class);
		
		// if cache not empty
		if (globalConfigList != null) return globalConfigList;
		
		// retrieve main list (global config)
		globalConfigList = lookupItemDAO.getGlobalConfigList();
		
		// still not found, log a warning.
		if (CollectionUtils.isEmpty(globalConfigList)) logger.warn("[getGlobalConfigList: not found category]={}", category);
		else {
			// cache result
			cacheService.put(cacheKey, Collections.unmodifiableList(globalConfigList));
			logger.info("[getGlobalConfigList: category, list]={},{}", new Object[] {category, CollectionUtils.getSize(globalConfigList)});
		}
		
		return globalConfigList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getGlobalConfigVO(java.lang.String)
	 */
	@Override
	public GlobalConfigVO getGlobalConfigVO(String category, String code) {
		List<GlobalConfigVO> globalConfigList = getGlobalConfigList(category);
		if (CollectionUtils.isNotEmpty(globalConfigList)) {
			for (GlobalConfigVO vo : globalConfigList) {
				if (vo.getCode().equals(code)) return vo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getGlobalConfigValue(java.lang.String, java.lang.String)
	 */
	@Override
	public String getGlobalConfigValue(String category, String code) {
		GlobalConfigVO vo = getGlobalConfigVO(category, code);
		return (vo != null) ? vo.getValue() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getFinPeriodLockVO(java.lang.Long)
	 */
	@Override
	public FinancialPeriodLockVO getFinPeriodLockVO(Long idCompany) {
		String cacheKey = CACHE_KEY_PREFIX + PAD_SLASH + idCompany + PAD_SLASH + LookupItemConstant.FIN_PERIOD_LOCK;
		FinancialPeriodLockVO vo = cacheService.get(cacheKey, FinancialPeriodLockVO.class);
		
		if (vo != null) return vo;
		try {
			// retrieve main vo
			vo = finPeriodDAO.getFinPeriodLock(idCompany);
			// still not found, log a warning.
			if (vo == null) logger.warn("[getFinPeriodLockVO: not found vo]={}", LookupItemConstant.FIN_PERIOD_LOCK);
			else {
				// cache result
				cacheService.put(cacheKey, vo);
				logger.info("[getFinPeriodLockVO: company id, vo]={},{}", new Object[] {idCompany, vo});
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vo;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#isNotificationAvailable(com.bcs.zsg.component.security.vo.UserVO)
	 */
	@Override
	public boolean isNotificationAvailable(UserVO userVO) {
		try {
			SearchParamVO vo = new SearchParamVO();
			// set today's date
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			vo.setFromDate(cal.getTime());
			
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			vo.setToDate(cal.getTime());
			
			return historyService.isNotificationAvailable(userVO, vo);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.service.LookupItemService#getSysNumList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SystemNumberGenerationVO getSysNumGenVO(Long idCompany, String code) {
		try {
			String cacheKey = "SYS_NUM_GEN";
			Map<Long, List<SystemNumberGenerationVO>> map = cacheService.get(cacheKey, Map.class);
			
			if (map == null) map = new HashMap<Long, List<SystemNumberGenerationVO>>();
			if (!map.containsKey(idCompany)) map.put(idCompany, sysNumGenDAO.getSystemNumberGenerationList(idCompany));
			// cache result
			cacheService.put(cacheKey, map);
			
			List<SystemNumberGenerationVO> list = map.get(idCompany);
			for (SystemNumberGenerationVO vo : list) {
				if (code.equals(vo.getCode())) return vo;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException {
		return finPeriodDAO.isDateInFinPeriodClosed(idCompany, date);
	}
	
	public Integer getFinPeriodClosedStatus(Long idCompany, Date date) throws BusinessException {
		return finPeriodDAO.getFinPeriodClosedStatus(idCompany, date);
	}
	
	public String getGSTProcessStatus(Long idCompany, Date date) throws BusinessException {
		//return gstService.getGSTProcessStatus(idCompany, date);
		return finPeriodDAO.getGSTProcessStatus(idCompany, date, true);
	}
	
	/**
	 * Get Financial and GST Period Closed Status
	 */ 
	public boolean getFinAndGSTPeriodClosedStatus(Long idCompany, Date date, boolean isAccountManager) {
		boolean rtn = false;
		try {
			String gstProcessStatus = getGSTProcessStatus(idCompany, date);
			Integer finPeriodClosedStatus = getFinPeriodClosedStatus(idCompany, date);
			
			if (finPeriodClosedStatus == -1) {
				rtn = true;
			} else if (
					(gstProcessStatus.equals(GSTProcessStatus.SUBMIT.getValue()) && !isAccountManager) ||
					(gstProcessStatus.equals(GSTProcessStatus.LOCKED.getValue()) && !isAccountManager) ||
					(finPeriodClosedStatus == 0 && !isAccountManager)) {
				rtn = true;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return rtn;
	}
}
