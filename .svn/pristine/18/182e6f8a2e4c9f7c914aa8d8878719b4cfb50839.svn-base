package com.bcs.zsg.bank.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;

public class BankAcctDAOImpl extends BaseHibernateDAO implements BankAcctDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.BankAcctDAO#getBankAcctList(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(BankAcctViewVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.addOrder(Order.asc("seqNo"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.BankAcctDAO#getBankCurrentBal(java.lang.Long)
	 */
	@Override
	public double getBankCurrentBal(Long idAcct, Date fromDate) throws BusinessException {
		//Query query = createSQLQuery("SELECT (SUM(debit) - SUM(credit)) FROM account_trans WHERE id_acct = :idAcct AND status_cd = :statusCd");
		Query query = createSQLQuery("SELECT (SUM(debit) - SUM(credit)) FROM cash_book WHERE id_bank = :idAcct AND status_cd = :statusCd AND DATE(dt_trans) >= DATE(:fromDate)");
		query.setParameter("idAcct", idAcct);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		query.setParameter("fromDate", fromDate);
		Double result = (Double) query.uniqueResult();
		return (result == null) ? 0 : result;
	}

}
