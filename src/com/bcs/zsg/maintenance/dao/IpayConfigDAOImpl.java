package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.IpayConfigVO;

public class IpayConfigDAOImpl extends BaseHibernateDAO implements IpayConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<IpayConfigVO> getIpayConfigVOList() throws BusinessException {
		Criteria criteria = createCriteria(IpayConfigVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		return criteria.list();
	}
	
	@Override
	public IpayConfigVO getIpayConfigVO(Long id) throws BusinessException {
		Criteria criteria = createCriteria(IpayConfigVO.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		
		return  (IpayConfigVO) criteria.uniqueResult();
	}
	
}
