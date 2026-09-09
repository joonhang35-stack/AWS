package com.bcs.zsg.cfg.sec.dao;

import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface PasswordResetDAO extends BaseDAO {
	public PasswordResetVO getPasswordResetVOByEmail(String emailAddress) throws BusinessException;
	
	public PasswordResetVO getPasswordResetVOByLoginId(String loginId) throws BusinessException;
	
	public PasswordResetVO getPasswordResetVOByEncodedPass(String encodedPass) throws BusinessException;
}
