package com.bcs.zsg.maintenance.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;



public interface SystemNumberGenerationDAO extends BaseDAO 
{

	public List<SystemNumberGenerationVO> getSystemNumberGenerationList(Long idCompany) throws BusinessException;

	public List<SystemNumberGenerationVO> getSysGenDefaultList(int isDefault);

	/**
	 * 
	 * @param systemNumberGenerationVO
	 * @return
	 * @throws BusinessException
	 */
	public SystemNumberGenerationVO getSysNumGen(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException;

	/**
	 * 
	 * @param sysCd
	 * @param idCompany
	 * @return
	 * @throws BusinessException
	 */
	public SystemNumberGenerationVO getSystemNumberGeneration(String sysCd, Long idCompany) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public SystemNumberGenerationVO getTestSystemNumberGeneration(String sysCd, Long id) throws BusinessException;
}