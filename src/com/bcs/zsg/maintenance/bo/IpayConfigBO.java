package com.bcs.zsg.maintenance.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;

public interface IpayConfigBO {
	
	public List<IpayConfigVO> getIpayConfigVOList() throws BusinessException;
	public IpayConfigVO getIpayConfigVO(Long id) throws BusinessException;
	
	public void updateIpayConfig(IpayConfigVO ipayConfigVO) throws BusinessException;
	public void delete(IpayConfigVO vo) throws BusinessException;
}
