package com.bcs.zsg.maintenance.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.OnlineBookingConfigVO;

public interface OnlineBookingConfigService {
	
	public List<OnlineBookingConfigVO> getOnlineBookingConfigVOList() throws BusinessException;
	public OnlineBookingConfigVO getOnlineBookingConfigVOList(String code, Long idCompany) throws BusinessException;
	public OnlineBookingConfigVO getOnlineBookingConfigVOList(Long id) throws BusinessException;
	
	public void updateOnlineBookingConfig(OnlineBookingConfigVO onlineBookingConfigVO) throws BusinessException;
	public void delete(OnlineBookingConfigVO vo) throws BusinessException;
	public List<OnlineBookingConfigVO> getOnlineBookingConfigList(Long idCompany) throws BusinessException;
}
