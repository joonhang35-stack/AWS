package com.bcs.zsg.maintenance.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;

public interface IpayConfigService {
	
	public List<IpayConfigVO> getIpayConfigVOList() throws BusinessException;
	public IpayConfigVO getIpayConfigVOList(Long id) throws BusinessException;
	
	public void updateIpayConfig(IpayConfigVO ipayConfigVO) throws BusinessException;
	public void delete(IpayConfigVO vo) throws BusinessException;
}
