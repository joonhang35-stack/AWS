package com.bcs.zsg.db.bterp.dao.view.accttransview;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;

public class AccountTransViewDAOImpl extends BaseHibernateDAO implements AccountTransViewDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AcctTransViewVO> getAccountTransViewList(AcctTransViewVO acctTransViewVO) throws BusinessException {
		Criteria criteria = createCriteria(AcctTransViewVO.class);
		criteria.add(Restrictions.eq("companyId", acctTransViewVO.getCompanyId()));
		criteria.add(Restrictions.eq("sysCode", acctTransViewVO.getSysCode()));
		criteria.add(Restrictions.eq("sysNo", acctTransViewVO.getSysNo()));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		//if (acctTransViewVO.getSysPrefix() == null) criteria.add(Restrictions.isNull("sysPrefix"));
		//else criteria.add(Restrictions.eq("sysPrefix", acctTransViewVO.getSysPrefix()));
		
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.db.bterp.dao.view.accttransview.AccountTransViewDAO#getAccountTransViewList(java.lang.Long, java.lang.Long, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AcctTransViewVO> getAccountTransViewList(Long idCompany, AcctViewVO acctViewVO, SearchParamVO searchParamVO) 
			throws BusinessException {
		Criteria criteria = createCriteria(AcctTransViewVO.class);
		criteria.add(Restrictions.eq("companyId", idCompany));
		
		if (acctViewVO != null) 
			criteria.add(Restrictions.eq("acctViewVO", acctViewVO));
		
		if (searchParamVO != null) 
			criteria.add(Restrictions.between("transDt", searchParamVO.getFromDate(), searchParamVO.getToDate()));
		
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}

}
