package com.bcs.zsg.maintenance.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.SystemNumberGenerationService;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public class SystemNumberGenerationBOImpl implements SystemNumberGenerationBO 
{
	@Autowired
	private SystemNumberGenerationService systemNumberGenerationService;

	@Override
	public void addSystemNumberGeneration(
			SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException{
		// TODO Auto-generated method stub
		systemNumberGenerationService.addSystemNumberGeneration(systemNumberGenerationVO);
	}

	@Override
	public void deleteSystemNumberGeneration(
			SystemNumberGenerationVO systemNumberGenerationVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		systemNumberGenerationService.deleteSystemNumberGeneration(systemNumberGenerationVO);
	}

	@Override
	public void updateSystemNumberGeneration(
			SystemNumberGenerationVO systemNumberGenerationVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		systemNumberGenerationService.updateSystemNumberGeneration(systemNumberGenerationVO);
	}

	@Override
	public List<SystemNumberGenerationVO> getSystemNumberGenerationList(Long idCompany)
			throws BusinessException {
		// TODO Auto-generated method stub
		return systemNumberGenerationService.getSystemNumberGenerationList(idCompany);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO#getSystemNumberGeneration(java.lang.String, java.lang.Long)
	 */
	@Override
	public SystemNumberGenerationVO getSystemNumberGeneration(String sysCd, Long idCompany) throws BusinessException {
		return systemNumberGenerationService.getSystemNumberGeneration(sysCd, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO#getTestSystemNumberGeneration(java.lang.String, long)
	 */
	@Override
	public SystemNumberGenerationVO getTestSystemNumberGeneration(String sysCd, long idCompany) throws BusinessException {
		return systemNumberGenerationService.getTestSystemNumberGeneration(sysCd, idCompany);
	}
}
