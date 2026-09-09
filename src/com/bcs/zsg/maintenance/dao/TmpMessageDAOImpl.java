package com.bcs.zsg.maintenance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.TmpMessageVO;

public class TmpMessageDAOImpl extends BaseHibernateDAO implements TmpMessageDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.TmpMessageDAO#getTmpMsgList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TmpMessageVO> getTmpMsgList(String catCode) throws BusinessException {
		Criteria criteria = createCriteria(TmpMessageVO.class);
		//criteria.add(Restrictions.eq("catCode", catCode));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.asc("seqNo"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.TmpMessageDAO#isCodeDuplicated(com.bcs.zsg.maintenance.vo.TmpMessageVO)
	 */
	@Override
	public boolean isCodeDuplicated(TmpMessageVO tmpMsgVO) throws BusinessException {
		Criteria criteria = createCriteria(TmpMessageVO.class);
		criteria.add(Restrictions.eq("catCode", tmpMsgVO.getCatCode()));
		criteria.add(Restrictions.eq("code", tmpMsgVO.getCode()));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		if (tmpMsgVO.getId() != null) criteria.add(Restrictions.ne("id", tmpMsgVO.getId()));
		criteria.setMaxResults(1);
		return (criteria.uniqueResult() == null) ? false : true;
	}

	@Override
	public List<TmpMessageVO> getTNCMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		Criteria criteria = createCriteria(TmpMessageVO.class);
		criteria.add(Restrictions.eq("catCode", catCode));
		CompanyVO vo = new CompanyVO();
		vo.setId(companyId);
		criteria.add(Restrictions.eq("companyVO", vo));
		criteria.add(Restrictions.like("code", "INVTNC", MatchMode.START));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.asc("seq"));
		return criteria.list();
	}

	@Override
	public List<TmpMessageVO> getBankDetailsMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		Criteria criteria = createCriteria(TmpMessageVO.class);
		criteria.add(Restrictions.eq("catCode", catCode));
		CompanyVO vo = new CompanyVO();
		vo.setId(companyId);
		criteria.add(Restrictions.eq("companyVO", vo));
		criteria.add(Restrictions.like("code", "BANKDETAIL", MatchMode.START));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.asc("seq"));
		return criteria.list();
	}

	@Override
	public List<TmpMessageVO> getFooterMsgWithCompanyId(String catCode, Long companyId) throws BusinessException {
		Criteria criteria = createCriteria(TmpMessageVO.class);
		criteria.add(Restrictions.eq("catCode", catCode));
		CompanyVO vo = new CompanyVO();
		vo.setId(companyId);
		criteria.add(Restrictions.eq("companyVO", vo));
		criteria.add(Restrictions.eq("code", "FOOTER"));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.asc("seq"));
		return criteria.list();
	}

}
