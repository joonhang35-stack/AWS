package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.AppSettingVO;

public class AppSettingDAOImpl extends BaseHibernateDAO implements AppSettingDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AppSettingVO> getAppSettingList(String module) throws BusinessException {
		Criteria criteria = createCriteria(AppSettingVO.class);
		criteria.add(Restrictions.eq("module", module));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		return criteria.list();
	}
	
	@Override
	public AppSettingVO getAppSettingByCode(String code) throws BusinessException {
		Criteria criteria = createCriteria(AppSettingVO.class);
		criteria.add(Restrictions.eq("code", code));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.setMaxResults(1);
		
		return (AppSettingVO) criteria.uniqueResult();
	}
}
