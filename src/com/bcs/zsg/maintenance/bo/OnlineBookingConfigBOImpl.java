package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.OnlineBookingConfigService;
import com.bcs.zsg.maintenance.vo.OnlineBookingConfigVO;

public class OnlineBookingConfigBOImpl implements OnlineBookingConfigBO {
	
	@Autowired
	private OnlineBookingConfigService onlineBookingConfigService;

	@Override
	public List<OnlineBookingConfigVO> getOnlineBookingConfigVOList() throws BusinessException {
		return onlineBookingConfigService.getOnlineBookingConfigVOList();
	}
	
	@Override
	public OnlineBookingConfigVO getOnlineBookingConfigVO(String code, Long idCompany) throws BusinessException {
		return onlineBookingConfigService.getOnlineBookingConfigVOList(code, idCompany);
	}
	
	@Override
	public OnlineBookingConfigVO getOnlineBookingConfigVO(Long id) throws BusinessException {
		return onlineBookingConfigService.getOnlineBookingConfigVOList(id);
	}

	@Override
	public void updateOnlineBookingConfig(OnlineBookingConfigVO onlineBookingConfigVO) throws BusinessException {
		onlineBookingConfigService.updateOnlineBookingConfig(onlineBookingConfigVO);
	}
	
	@Override
	public void delete(OnlineBookingConfigVO vo) throws BusinessException {
		onlineBookingConfigService.delete(vo);
	}
	
	@Override
	public List<OnlineBookingConfigVO> getOnlineBookingConfigList(Long idCompany)
			throws BusinessException {
		// TODO Auto-generated method stub
		return onlineBookingConfigService.getOnlineBookingConfigList(idCompany);
	}
}
