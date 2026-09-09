package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.dao.IpayConfigDAO;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;

public class IpayConfigServiceImpl implements IpayConfigService {

	@Autowired
	private IpayConfigDAO ipayConfigDAO;
	
	@Override
	public List<IpayConfigVO> getIpayConfigVOList() throws BusinessException {
		return ipayConfigDAO.getIpayConfigVOList();
	}
	
	@Override
	public IpayConfigVO getIpayConfigVOList(Long id) throws BusinessException {
		return ipayConfigDAO.getIpayConfigVO(id);
	}
	
	@Override
	public void updateIpayConfig(IpayConfigVO ipayConfigVO) throws BusinessException {
		if (ipayConfigVO.getId() == null) {
			ipayConfigVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			ipayConfigDAO.insert(ipayConfigVO);
		} else {
			ipayConfigDAO.update(ipayConfigVO);
		}
	}
	
	@Override
	public void delete(IpayConfigVO vo) throws BusinessException {
		vo.setStatusCode(BaseConstant.STATUS_DELETED);
		ipayConfigDAO.update(vo);
	}
}
