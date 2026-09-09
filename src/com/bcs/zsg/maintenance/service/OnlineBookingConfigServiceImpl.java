package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.dao.OnlineBookingConfigDAO;
import com.bcs.zsg.maintenance.vo.OnlineBookingConfigVO;

public class OnlineBookingConfigServiceImpl implements OnlineBookingConfigService {

	@Autowired
	private OnlineBookingConfigDAO onlineBookingConfigDAO;
	
	@Override
	public List<OnlineBookingConfigVO> getOnlineBookingConfigVOList() throws BusinessException {
		return onlineBookingConfigDAO.getOnlineBookingConfigVOList();
	}
	
	@Override
	public OnlineBookingConfigVO getOnlineBookingConfigVOList(String code, Long idCompany) throws BusinessException {
		return onlineBookingConfigDAO.getOnlineBookingConfigVO(code, idCompany);
	}
	
	@Override
	public OnlineBookingConfigVO getOnlineBookingConfigVOList(Long id) throws BusinessException {
		return onlineBookingConfigDAO.getOnlineBookingConfigVO(id);
	}
	
	@Override
	public void updateOnlineBookingConfig(OnlineBookingConfigVO onlineBookingConfigVO) throws BusinessException {
		if (onlineBookingConfigVO.getId() == null) {
			onlineBookingConfigVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			onlineBookingConfigDAO.insert(onlineBookingConfigVO);
		} else {
			onlineBookingConfigDAO.update(onlineBookingConfigVO);
		}
	}
	
	@Override
	public void delete(OnlineBookingConfigVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_DELETED);
		onlineBookingConfigDAO.update(vo);
	}
	
	@Override
	public List<OnlineBookingConfigVO> getOnlineBookingConfigList(Long idCompany)
			throws BusinessException {
		// TODO Auto-generated method stub
		return onlineBookingConfigDAO.getOnlineBookingConfigList(idCompany);
	}
}
