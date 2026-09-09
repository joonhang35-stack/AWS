package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.IpayConfigService;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;

public class IpayConfigBOImpl implements IpayConfigBO {
	
	@Autowired
	private IpayConfigService ipayConfigService;

	@Override
	public List<IpayConfigVO> getIpayConfigVOList() throws BusinessException {
		return ipayConfigService.getIpayConfigVOList();
	}
	
	@Override
	public IpayConfigVO getIpayConfigVO(Long id) throws BusinessException {
		return ipayConfigService.getIpayConfigVOList(id);
	}

	@Override
	public void updateIpayConfig(IpayConfigVO ipayConfigVO) throws BusinessException {
		ipayConfigService.updateIpayConfig(ipayConfigVO);
	}
	
	@Override
	public void delete(IpayConfigVO vo) throws BusinessException {
		ipayConfigService.delete(vo);
	}
}
