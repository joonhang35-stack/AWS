package com.bcs.zsg.cfg.sec.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.service.PasswordResetService;
import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.core.exception.BusinessException;

public class PasswordResetBOImpl implements PasswordResetBO {

	@Autowired
	private PasswordResetService passwordResetService;
	
	@Override
	public PasswordResetVO getPasswordResetVOByEmail(String emailAddress) throws BusinessException{
		return passwordResetService.getPasswordResetVOByEmail(emailAddress);
	}

	@Override
	public PasswordResetVO getPasswordResetVOByEncodedPass(String encodedPass) throws BusinessException{
		return passwordResetService.getPasswordResetVOByEncodedPass(encodedPass);
	}

	@Override
	public void delete(PasswordResetVO vo) throws BusinessException {
		passwordResetService.delete(vo);
	}
}
