package com.bcs.zsg.cfg.sec.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.cfg.sec.vo.PasswordResetVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;

public class PasswordResetDAOImpl extends BaseHibernateDAO implements PasswordResetDAO{
	
	@Override
	public PasswordResetVO getPasswordResetVOByEmail(String emailAddress) throws BusinessException {
		Criteria criteria = createCriteria(PasswordResetVO.class);
		criteria.add(Restrictions.eq("emailAddress", emailAddress));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setMaxResults(1);
		
		return (PasswordResetVO) criteria.uniqueResult();
	}

	@Override
	public PasswordResetVO getPasswordResetVOByLoginId(String loginId) throws BusinessException {
		Criteria criteria = createCriteria(PasswordResetVO.class);
		criteria.add(Restrictions.eq("loginId", loginId));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setMaxResults(1);
		
		return (PasswordResetVO) criteria.uniqueResult();
	}
	
	@Override
	public PasswordResetVO getPasswordResetVOByEncodedPass(String encodedPass) throws BusinessException {
		Criteria criteria = createCriteria(PasswordResetVO.class);
		criteria.add(Restrictions.eq("encodedPassword", encodedPass));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setMaxResults(1);
		
		return (PasswordResetVO) criteria.uniqueResult();
	}
}
