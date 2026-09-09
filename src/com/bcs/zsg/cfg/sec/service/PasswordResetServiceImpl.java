package com.bcs.zsg.cfg.sec.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.dao.PasswordResetDAO;
import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.core.exception.BusinessException;

public class PasswordResetServiceImpl implements PasswordResetService {
	
	@Autowired
	private PasswordResetDAO passwordResetDAO;

	@Override
	public PasswordResetVO getPasswordResetVOByEmail(String emailAddress) throws BusinessException {
		return passwordResetDAO.getPasswordResetVOByEmail(emailAddress);
	}

	@Override
	public PasswordResetVO getPasswordResetVOByEncodedPass(String encodedPass) throws BusinessException {
		return passwordResetDAO.getPasswordResetVOByEncodedPass(encodedPass);
	}
	
	@Override
	public void delete(PasswordResetVO vo) throws BusinessException {
		passwordResetDAO.delete(vo);
	}
}
