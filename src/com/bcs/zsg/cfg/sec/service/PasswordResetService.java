package com.bcs.zsg.cfg.sec.service;

import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface PasswordResetService {
	public PasswordResetVO getPasswordResetVOByEmail(String emailAddress) throws BusinessException;
	
	public PasswordResetVO getPasswordResetVOByEncodedPass(String encodedPass) throws BusinessException;
	
	public void delete(PasswordResetVO vo) throws BusinessException;
}
