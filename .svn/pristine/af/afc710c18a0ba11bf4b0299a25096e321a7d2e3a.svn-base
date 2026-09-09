package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.OnlineBookingConfigVO;

public class OnlineBookingConfigDAOImpl extends BaseHibernateDAO implements OnlineBookingConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<OnlineBookingConfigVO> getOnlineBookingConfigVOList() throws BusinessException {
		Criteria criteria = createCriteria(OnlineBookingConfigVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		return criteria.list();
	}
	
	@Override
	public OnlineBookingConfigVO getOnlineBookingConfigVO(String code, Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(OnlineBookingConfigVO.class);
		criteria.add(Restrictions.eq("code", code));
		criteria.add(Restrictions.eq("idCompany", idCompany));
		return (OnlineBookingConfigVO) criteria.uniqueResult();
	}
	
	@Override
	public OnlineBookingConfigVO getOnlineBookingConfigVO(Long id) throws BusinessException {
		Criteria criteria = createCriteria(OnlineBookingConfigVO.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		
		return  (OnlineBookingConfigVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OnlineBookingConfigVO> getOnlineBookingConfigList(Long idCompany)
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = createCriteria(OnlineBookingConfigVO.class);
		criteria.add(Restrictions.eq("idCompany",idCompany));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}
	
}
