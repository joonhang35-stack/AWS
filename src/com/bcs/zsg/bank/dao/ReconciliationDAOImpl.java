package com.bcs.zsg.bank.dao;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.dao.ChartOfAcctDAO;
import com.bcs.zsg.bank.vo.BankReconVO;
import com.bcs.zsg.common.bo.CommonObject;
import com.bcs.zsg.common.dao.BaseCommonDAOLMImpl;
import com.bcs.zsg.common.helper.CommonVOHelper;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;

public class ReconciliationDAOImpl extends BaseCommonDAOLMImpl implements ReconciliationDAO {

	@Autowired
	private ChartOfAcctDAO chartOfAcctDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.ReconciliationDAO#getBankReconList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankReconVO> getBankReconList(SearchParamVO searchParamVO, List<Long> bankIdList) throws BusinessException {
		Criteria criteria = createCriteria(BankReconVO.class);
		if (bankIdList != null) criteria.add(Restrictions.in("idBank", bankIdList));
		else criteria.add(Restrictions.eq("idBank", Long.parseLong((String) searchParamVO.getObj3())));
		criteria.addOrder(Order.desc("dtStart"));
		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.ReconciliationDAO#getBankReconCashBookBal(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public BankReconVO getBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Query query = createSQLQuery(new StringBuilder().append("SELECT ").
									append("(SELECT SUM(credit) FROM cash_book WHERE id_bank = ? AND credit != 0 AND date(dt_trans) <= ? AND status_cd = ? AND is_clear IS TRUE AND date(dt_clear) = ?) AS 'cbCr',").
									append("(SELECT SUM(debit) FROM cash_book WHERE id_bank = ? AND debit != 0 AND date(dt_trans) <= ? AND status_cd = ? AND is_clear IS TRUE AND date(dt_clear) = ?) AS 'cbDr',").
									append("(SELECT SUM(credit) FROM cash_book WHERE id_bank = ? AND credit > 0 AND date(dt_trans) <= ? AND status_cd = ? AND (is_clear IS FALSE OR (is_clear IS TRUE AND date(dt_clear) > ?))) AS 'obCr',").
									append("(SELECT SUM(debit) FROM cash_book WHERE id_bank = ? AND debit > 0 AND date(dt_trans) <= ? AND status_cd = ? AND (is_clear IS FALSE OR (is_clear IS TRUE AND date(dt_clear) > ?))) AS 'obDr',").
									append("(SELECT COUNT(id) FROM cash_book WHERE id_bank = ? AND credit > 0 AND date(dt_trans) <= ? AND status_cd = ? AND (is_clear IS FALSE OR (is_clear IS TRUE AND date(dt_clear) > ?))) AS 'noOutsCr',").
									append("(SELECT COUNT(id) FROM cash_book WHERE id_bank = ? AND debit != 0 AND date(dt_trans) <= ? AND status_cd = ? AND (is_clear IS FALSE OR (is_clear IS TRUE AND date(dt_clear) > ?))) AS 'noOutsDr',").
									append("(SELECT (CASE WHEN SUM(debit) IS NULL THEN 0 ELSE SUM(debit) END) - (CASE WHEN SUM(credit) IS NULL THEN 0 ELSE SUM(credit) END) FROM cash_book WHERE id_bank = ? AND status_cd = ? AND is_clear IS TRUE AND DATE(dt_clear) < DATE(?)) AS 'cbBal'").toString());
		query.setString(0, (String) searchParamVO.getObj1());
		query.setString(1, dateFormat.format(searchParamVO.getToDate()));
		query.setString(2, BaseConstant.STATUS_ACTIVE);
		query.setString(3, dateFormat.format(searchParamVO.getToDate()));
		query.setString(4, (String) searchParamVO.getObj1());
		query.setString(5, dateFormat.format(searchParamVO.getToDate()));
		query.setString(6, BaseConstant.STATUS_ACTIVE);
		query.setString(7, dateFormat.format(searchParamVO.getToDate()));
		query.setString(8, (String) searchParamVO.getObj1());
		query.setString(9, dateFormat.format(searchParamVO.getToDate()));
		query.setString(10, BaseConstant.STATUS_ACTIVE);
		query.setString(11, dateFormat.format(searchParamVO.getToDate()));
		query.setString(12, (String) searchParamVO.getObj1());
		query.setString(13, dateFormat.format(searchParamVO.getToDate()));
		query.setString(14, BaseConstant.STATUS_ACTIVE);
		query.setString(15, dateFormat.format(searchParamVO.getToDate()));
		query.setString(16, (String) searchParamVO.getObj1());
		query.setString(17, dateFormat.format(searchParamVO.getToDate()));
		query.setString(18, BaseConstant.STATUS_ACTIVE);
		query.setString(19, dateFormat.format(searchParamVO.getToDate()));
		query.setString(20, (String) searchParamVO.getObj1());
		query.setString(21, dateFormat.format(searchParamVO.getToDate()));
		query.setString(22, BaseConstant.STATUS_ACTIVE);
		query.setString(23, dateFormat.format(searchParamVO.getToDate()));
		query.setString(24, (String) searchParamVO.getObj1());
		query.setString(25, BaseConstant.STATUS_ACTIVE);
		query.setString(26, dateFormat.format(searchParamVO.getFromDate()));
		Object[] obj = (Object[]) query.uniqueResult();
		
		bankReconVO.setCbCr(obj[0] != null ? (double) obj[0] : 0);
		bankReconVO.setCbDr(obj[1] != null ? (double) obj[1] : 0);
		bankReconVO.setObCr(obj[2] != null ? (double) obj[2] : 0);
		bankReconVO.setObDr(obj[3] != null ? (double) obj[3] : 0);
		bankReconVO.setNoOutsCr(((Number) obj[4]).intValue());
		bankReconVO.setNoOutsDr(((Number) obj[5]).intValue());
		bankReconVO.setCbBal(obj[6] != null ? (double) obj[6] : 0);
		return bankReconVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.ReconciliationDAO#getPrevBankReconCashBookBal(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.bank.vo.BankReconVO)
	 */
	@Override
	public double getPrevBankReconCashBookBal(SearchParamVO searchParamVO, BankReconVO bankReconVO) throws BusinessException {
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Query query = createSQLQuery(new StringBuilder().append("SELECT cashbook_bal FROM bank_recon WHERE id_bank = ? AND DATE(dt_start) < ? ORDER BY dt_start DESC").toString());
		query.setString(0, (String) searchParamVO.getObj1());
		query.setString(1, dateFormat.format(searchParamVO.getFromDate()));
		query.setMaxResults(1);
		Double cashBookBal = (Double) query.uniqueResult();*/
		
		// Get beginning balance
		//if (cashBookBal == null) {
			Date beginDate = chartOfAcctDAO.getFirtFinPeriod(searchParamVO.getCompanyVO().getId(), searchParamVO.getFromDate());
			
			Query query = createSQLQuery(new StringBuilder().append("SELECT CASE WHEN a.debit_begin_bal > 0 THEN a.debit_begin_bal ELSE (-a.credit_begin_bal) END ").
								append("FROM account_bal a, bank b WHERE b.id = ? AND b.id_acct = a.id_acct AND a.dt_begin_bal = ? ORDER BY a.id").toString());
			query.setString(0, (String) searchParamVO.getObj1());
			query.setDate(1, beginDate);
			query.setMaxResults(1);
			Double cashBookBal = (Double) query.uniqueResult();
			
			// 1st Financial period date can not use to find the account balance (Eg: fin date 2014 but the account open on 2023 (a.dt_begin_bal = ? have issues))
			if (cashBookBal == null) {
				query = createSQLQuery(new StringBuilder().append("SELECT CASE WHEN a.debit_begin_bal > 0 THEN a.debit_begin_bal ELSE (-a.credit_begin_bal) END ").
						append("FROM account_bal a, bank b WHERE b.id = ? AND b.id_acct = a.id_acct AND a.dt_begin_bal = ").
						append("(SELECT MIN(a.dt_begin_bal) FROM account_bal a, bank b WHERE b.id = ? AND b.id_acct = a.id_acct ORDER BY a.dt_begin_bal) ").
						append("ORDER BY a.id").toString());
				query.setString(0, (String) searchParamVO.getObj1());
				query.setString(1, (String) searchParamVO.getObj1());
				query.setMaxResults(1);
				cashBookBal = (Double) query.uniqueResult();
			}
			
		//}
		return cashBookBal.doubleValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.ReconciliationDAO#getBankRecon(java.lang.Long)
	 */
	@Override
	public BankReconVO getBankRecon(BankReconVO bankReconVO, SearchParamVO searchParamVO) throws BusinessException {
		Criteria criteria = createCriteria(BankReconVO.class);
		criteria.add(Restrictions.eq("id", bankReconVO.getId()));
		return (BankReconVO) criteria.uniqueResult();
	}

	@Override
	public <S> List<?> getLazyRecordList(S _commonObj, int _first,
			int _pageSize, String _sortField, SortOrder _sortOrder)
			throws BusinessException {
		return null;
	}

	@Override
	public <S> int getRowCount(S _commonObj) throws BusinessException {
		return 0;
	}

	@Override
	public <S> void deleteRecordByID(List<S> _listVO) throws BusinessException,
			SecurityException, IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		
		List<Long> listID = CommonVOHelper.getObject().getAllSelectedID(
				BankReconVO.class, _listVO, "getId", Long.class);

		Query qry = createQuery("DELETE FROM " + BankReconVO.class.getName() + " WHERE id IN (:idList) ");
		qry.setParameterList("idList", listID);
		qry.executeUpdate();
	}

	@Override
	public <S> S getRecordByID(long _ID, Class<S> _VOClass)
			throws BusinessException {
		return null;
	}

	@Override
	public <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass)
			throws BusinessException {
		return null;
	}

	@Override
	public <S> List<?> getRecordList(S _commonObj) throws BusinessException {
		CommonObject objResolver = (CommonObject)_commonObj;
		Criteria criteria = getSession().createCriteria((Class<?>)objResolver.getListSource(0));
		
		if(objResolver.getObjectName().contains("insert")) {
			criteria.add(Restrictions.eq("idBank", (Long)objResolver.getListSource(1)));
			criteria.add(Restrictions.eq("month", (String)objResolver.getListSource(2)));
			criteria.add(Restrictions.eq("year", (String)objResolver.getListSource(3)));
		}
		return criteria.list();
	}
}
