package com.bcs.zsg.maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.dao.SystemNumberGenerationDAO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public class SystemNumberGenerationServiceImpl implements SystemNumberGenerationService {
	
	
	@Autowired
	private SystemNumberGenerationDAO systemNumberGenerationDAO;

	@Override
	public void addSystemNumberGeneration(
			SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		// TODO Auto-generated method stub
		systemNumberGenerationDAO.insert(systemNumberGenerationVO);
	}

	@Override
	public void deleteSystemNumberGeneration(
			SystemNumberGenerationVO systemNumberGenerationVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		systemNumberGenerationDAO.delete(systemNumberGenerationVO);
	}

	@Override
	public void updateSystemNumberGeneration(
			SystemNumberGenerationVO systemNumberGenerationVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		
		systemNumberGenerationDAO.update(systemNumberGenerationVO);
	}

	@Override
	public List<SystemNumberGenerationVO> getSystemNumberGenerationList(Long idCompany)
			throws BusinessException {
		// TODO Auto-generated method stub
		return systemNumberGenerationDAO.getSystemNumberGenerationList(idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.SystemNumberGenerationService#getSysNumGen(com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO)
	 */
	@Override
	public SystemNumberGenerationVO getSysNumGen(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		SystemNumberGenerationVO vo = systemNumberGenerationDAO.getSysNumGen(systemNumberGenerationVO);
		vo.setNextnumber(vo.getNextnumber() + 1);
		systemNumberGenerationDAO.update(vo, "SYSTEM");
		vo.setNextnumber(vo.getNextnumber() - 1);
		vo.setGotNextNumber(true);
		return vo;
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.SystemNumberGenerationService#getSystemNumberGeneration(java.lang.String, java.lang.Long)
	 */
	@Override
	public SystemNumberGenerationVO getSystemNumberGeneration(String sysCd, Long idCompany) throws BusinessException {
		return systemNumberGenerationDAO.getSystemNumberGeneration(sysCd, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.service.SystemNumberGenerationService#getTestSystemNumberGeneration(java.lang.String, long)
	 */
	@Override
	public SystemNumberGenerationVO getTestSystemNumberGeneration(String sysCd, long idCompany) throws BusinessException {
		SystemNumberGenerationVO vo = (SystemNumberGenerationVO) (systemNumberGenerationDAO.getTestSystemNumberGeneration(sysCd, idCompany)).clone();
		vo.setNextnumber(vo.getNextnumber() - 1);
		vo.setGotNextNumber(true);
		return vo;
	}
}
