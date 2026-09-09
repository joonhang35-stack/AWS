package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.DepositMasterConfigVO;

public class DepositMasterConfigDAOImpl extends BaseHibernateDAO implements DepositMasterConfigDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<DepositMasterConfigVO> getDepositMasterConfigVOList() throws BusinessException {
		Criteria criteria = createCriteria(DepositMasterConfigVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}

}
