package com.bcs.zsg.common.service;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.component.security.vo.UserVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public interface LookupItemService {

	/**
	 * 
	 * @param category
	 * @return
	 */
	public List<LookupItemVO> getLookupItemList(String category);

	/**
	 * 
	 * @param category
	 * @param typeCd
	 * @return
	 */
	public LookupItemVO getLookupItemVO(String category, String typeCd);

	/**
	 * 
	 * @param category
	 * @param typeCd
	 * @return
	 */
	public String getLookupItemDesc(String category, String typeCd);

	/**
	 * 
	 * @param category
	 * @return
	 */
	public List<GlobalConfigVO> getGlobalConfigList(String category);

	/**
	 * 
	 * @param category
	 * @param code
	 * @return
	 */
	public GlobalConfigVO getGlobalConfigVO(String category, String code);

	/**
	 * 
	 * @param category
	 * @param code
	 * @return
	 */
	public String getGlobalConfigValue(String category, String code);

	/**
	 * 
	 * @param idCompany
	 * @return
	 */
	public FinancialPeriodLockVO getFinPeriodLockVO(Long idCompany);
	
	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException;
	
	public Integer getFinPeriodClosedStatus(Long idCompany, Date date) throws BusinessException;
	
	/**
	 * 
	 * @param userVO
	 * @return
	 */
	public boolean isNotificationAvailable(UserVO userVO);

	/**
	 * 
	 * @param idCompany
	 * @param code 
	 * @return
	 */
	public SystemNumberGenerationVO getSysNumGenVO(Long idCompany, String code);

	public String getGSTProcessStatus(Long idCompany, Date date) throws BusinessException;
	
	public boolean getFinAndGSTPeriodClosedStatus(Long idCompany, Date date, boolean isAccountManager) throws BusinessException;
}
