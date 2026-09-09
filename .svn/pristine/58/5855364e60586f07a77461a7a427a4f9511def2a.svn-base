package com.bcs.zsg.maintenance.bo;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public interface SystemNumberGenerationBO 
{

	public void addSystemNumberGeneration(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException;
	public void deleteSystemNumberGeneration(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException;
	public void updateSystemNumberGeneration(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException;

	public List<SystemNumberGenerationVO> getSystemNumberGenerationList(Long idCompany) throws BusinessException;
	
 
	/**
	 * 
	 * @param sysCd
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public SystemNumberGenerationVO getSystemNumberGeneration(String sysCd, Long idCompany) throws BusinessException;
	
	/**
	 * For testing purpose
	 * @param string
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public SystemNumberGenerationVO getTestSystemNumberGeneration(String string, long idCompany) throws BusinessException;
}
