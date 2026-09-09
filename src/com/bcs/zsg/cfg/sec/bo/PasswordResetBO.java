package com.bcs.zsg.cfg.sec.bo;

import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface PasswordResetBO {
	public PasswordResetVO getPasswordResetVOByEmail(String emailAddress) throws BusinessException;
	
	public PasswordResetVO getPasswordResetVOByEncodedPass(String encodedPass) throws BusinessException;
	
	public void delete(PasswordResetVO vo) throws BusinessException;
}
