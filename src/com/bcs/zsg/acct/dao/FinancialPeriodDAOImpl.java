package com.bcs.zsg.acct.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.gst.helper.GSTProcessStatus;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class FinancialPeriodDAOImpl extends BaseHibernateDAO implements FinancialPeriodDAO {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#getfinPeriodList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FinancialPeriodVO> getFinPeriodList(Long idCompany, String year) throws BusinessException {
		Criteria criteria = createCriteria(FinancialPeriodVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.add(Restrictions.eq("year", year));
		criteria.addOrder(Order.asc("seqNo"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#delFinPeriod(java.lang.Long, java.lang.String)
	 */
	@Override
	public void delFinPeriod(Long idCompany, String year) throws BusinessException {
		Query query = createQuery("DELETE FROM FinancialPeriodVO WHERE idCompany = :idCompany AND year = :year");
		query.setLong("idCompany", idCompany);
		query.setString("year", year);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#getFinPeriod(java.lang.Long)
	 */
	@Override
	public FinancialPeriodVO getFinPeriod(Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(FinancialPeriodVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		//criteria.add(Restrictions.eq("status", 1));
		criteria.add(Restrictions.or(Restrictions.eq("status", 1), Restrictions.eq("status", 0)));
		criteria.addOrder(Order.asc("year"));
		criteria.addOrder(Order.asc("seqNo"));
		criteria.setMaxResults(1);
		return (FinancialPeriodVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#isClosedPeriodExisted(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean isClosedPeriodExisted(Long idCompany, String year) throws BusinessException {
		Criteria criteria = createCriteria(FinancialPeriodVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.add(Restrictions.eq("year", year));
		//criteria.add(Restrictions.eq("status", 0));
		criteria.add(Restrictions.or(Restrictions.eq("status", 0), Restrictions.eq("status", -1)));
		return (criteria.list() != null && criteria.list().size() > 0) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#getFinPeriodLock(java.lang.Long)
	 */
	@Override
	public FinancialPeriodLockVO getFinPeriodLock(Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(FinancialPeriodLockVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		FinancialPeriodLockVO finPeriodLockVO = (FinancialPeriodLockVO) criteria.uniqueResult();
		
		/*criteria = createCriteria(FinancialPeriodVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.add(Restrictions.eq("status", true));
		criteria.addOrder(Order.asc("dtStart"));
		criteria.setMaxResults(1);
		FinancialPeriodVO finPeriodVO = (FinancialPeriodVO) criteria.uniqueResult();
		
		if (finPeriodLockVO.getDtStart().before(finPeriodVO.getDtStart())) {
			finPeriodLockVO.setDtStart(finPeriodVO.getDtStart());
			if (finPeriodLockVO.getDtEnd().before(finPeriodVO.getDtStart())) finPeriodLockVO.setDtEnd(finPeriodVO.getDtStart());
		}*/
		return finPeriodLockVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#calculateFinPeriodBalance(com.bcs.zsg.maintenance.vo.CompanyVO, java.lang.String, com.bcs.zsg.acct.vo.FinancialPeriodVO)
	 */
	@Override
	public void calculateFinPeriodBalance(CompanyVO companyVO, String userName, FinancialPeriodVO finPeriodVO) throws BusinessException {
		Date minDate = null;
		Date maxDate = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select min(dt_start), max(dt_end) from financial_period where year = (select year from financial_period ").
			append("where date('" + sdf.format(finPeriodVO.getDtStart()) + "') between date(dt_start) and date(dt_end) and id_company = ?) and id_company = ?");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong(0, companyVO.getId());
		query.setLong(1, companyVO.getId());
		Object[] obj = (Object[]) query.uniqueResult();
		if (obj[0] != null) minDate = (Date) obj[0];
		if (obj[1] != null) maxDate = (Date) obj[1];
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(finPeriodVO.getDtStart());
		
		// close by monthly period
		if (finPeriodVO.getPeriodType().equals(0)) {
			updateAcctBal(companyVO, userName, minDate, maxDate, finPeriodVO.getDtStart(), finPeriodVO.getDtEnd());
			
			// set to date
			cal.add(Calendar.MONTH, 1);
			
			sb = new StringBuilder();
			sb.append("select b.id from account_bal b, account a where b.id_acct = a.id").
				append(" and a.id_company = ").append(companyVO.getId()).
				append(" and date(dt_begin_bal) = '").append(sdf.format(cal.getTime())).append("'").
				append(" and a.status_cd = 'A' and b.type_cd = 'C'");
			query = createSQLQuery(sb.toString());
			query.setMaxResults(1);
			
			System.out.println("################## calculateFinPeriodBalance: " +
					"\n minDate: " + minDate +
					"\n maxDate: " + maxDate +
					"\n getDtStart(): " + finPeriodVO.getDtStart() +
					"\n getDtEnd(): " + finPeriodVO.getDtEnd() +
					"\n newDate: " + sdf.format(cal.getTime())
					);
			
			if (query.uniqueResult() == null) {
				//System.out.println("******* do insert " + cal.getTime());
				insertNewAcctBal(companyVO, userName, minDate, maxDate, finPeriodVO.getDtStart(), finPeriodVO.getDtEnd(), cal.getTime());
			} else {
				//System.out.println("******* do update " + cal.getTime());
				updateNewAcctBal(companyVO, userName, minDate, maxDate, finPeriodVO.getDtStart(), finPeriodVO.getDtEnd(), cal.getTime());
			}
		} else { // close by quater period
			Calendar currCal = Calendar.getInstance();
			Date dtStart = null;
			Date dtEnd = null;
			
			for (int i = 0 ; i < 3 ; i++) {
				currCal.setTime(finPeriodVO.getDtStart());
				currCal.add(Calendar.MONTH, i);
				dtStart = currCal.getTime();
				
				currCal.set(Calendar.DATE, currCal.getActualMaximum(Calendar.DAY_OF_MONTH));
				dtEnd = currCal.getTime();
				updateAcctBal(companyVO, userName, minDate, maxDate, dtStart, dtEnd);
				
				// set to date
				cal.add(Calendar.MONTH, 1);
				insertNewAcctBal(companyVO, userName, minDate, maxDate, dtStart, dtEnd, cal.getTime());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.FinancialPeriodDAO#isMonthIncludedPrevPeriod(java.lang.Long, java.lang.String, int)
	 */
	@Override
	public boolean isMonthIncludedPrevPeriod(Long idCompany, String year, int month, int option) throws BusinessException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		StringBuilder sb = new StringBuilder();
		sb.append("FROM FinancialPeriodVO WHERE idCompany = :idCompany AND ");
		if (option == 2 ) sb.append("DATE(dtStart) = DATE(:dtMonth)");
		else sb.append("DATE(:dtMonth) BETWEEN DATE(dtStart) AND DATE(dtEnd)");
		
		Query query = createQuery(sb.toString());
		query.setLong("idCompany", idCompany);
		query.setDate("dtMonth", cal.getTime());
		return query.uniqueResult() == null ? false : true;
	}
	
	/*
	 * Update account balance
	 * @param minDate
	 * @param maxDate
	 * @param dtStart
	 * @param dtEnd
	 * @return
	 */
	private void updateAcctBal(CompanyVO companyVO, String userName, Date minDate, Date maxDate, Date dtStart, Date dtEnd) {
		StringBuilder sb = new StringBuilder();
		/*sb.append("update account_bal m join ( ").
			append("select n.id, n.id_acct_bal, ").
				append("case when balance >= 0 then balance else 0 end as debit, ").
				append("case when balance < 0 then -1 * balance else 0 end as credit ").
			append("from ( ").
				append("select a.id, ").
				append("(select b.id from account_bal b where b.id_acct = a.id and b.dt_begin_bal between date('" + sdf.format(minDate) + "') and date('" + sdf.format(maxDate) + "') order by b.dt_begin_bal desc limit 1) as id_acct_bal, ").
				append("(select (b.debit_begin_bal - b.credit_begin_bal) from account_bal b where b.id_acct = a.id and b.dt_begin_bal between date('" + sdf.format(minDate) + "') and date('" + sdf.format(maxDate) + "') order by b.dt_begin_bal desc limit 1) + ").
				append("(select case when (sum(t.debit) - sum(t.credit)) is null then 0 else (sum(t.debit) - sum(t.credit)) end ").
				append("from account_trans t where t.id_company = a.id_company and t.id_acct = a.id and t.status_cd = 'A' and ").
				append("date(dt_trans) between date('" + sdf.format(dtStart) + "') and date('" + sdf.format(dtEnd) + "')) as 'balance' ").
				append("from account a ").
				append("where a.id_company = :idCompany and a.status_cd = 'A' ").
			append(") n where n.id_acct_bal is not null ) k on m.id = k.id_acct_bal ").
			append("set m.dt_current_bal = date('" + sdf.format(dtEnd) + "'), m.debit_current_bal = k.debit, m.credit_current_bal = k.credit, ").
			append("m.dt_close_bal = date('" + sdf.format(dtEnd) + "'), m.debit_close_bal = k.debit, m.credit_close_bal = k.credit, ").
			append("m.dt_upd = sysdate(), m.upd_by = :updBy");*/
		
		sb.append("update account_bal m join (").
			append("select a.id, ifnull(t.sum, 0) as 'sum', bc.id as 'idAcctBal', bc.beginBal from account a ").
			append("left join (").
			append("select id_acct, (sum(debit) - sum(credit)) as 'sum' from account_trans ").
			append("where id_company = :idCompany and status_cd = 'A' and date(dt_trans) between '" + sdf.format(dtStart) + "' and '" + sdf.format(dtEnd) + "' group by id_acct").
			append(") t on t.id_acct = a.id ").
			append("left join (").
			
			
//			append("select * from (").
//			append("select b.id, b.id_acct, (b.debit_begin_bal - b.credit_begin_bal) as 'beginBal' from account_bal b, account c ").
//			append("where c.id_company = :idCompany and b.id_acct = c.id and c.status_cd = 'A' and b.dt_begin_bal between '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "'").
//			append("order by b.dt_begin_bal desc").
//			append(") cc group by cc.id_acct").
			
			append("SELECT b.id, b.id_acct, (b.debit_begin_bal - b.credit_begin_bal) AS 'beginBal' ").
			append("FROM account_bal b ").
			append("JOIN account c ON b.id_acct = c.id ").
			append("JOIN ( ").
				append("SELECT id_acct, MAX(dt_begin_bal) AS max_date ").
				append("FROM account_bal ").
				append("WHERE dt_begin_bal BETWEEN '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' ").
				append("GROUP BY id_acct ").
			append(") max_dates ON b.id_acct = max_dates.id_acct AND b.dt_begin_bal = max_dates.max_date ").
			append("WHERE c.id_company = :idCompany ").
			append("ORDER BY b.id_acct ").
			
			
			append(") bc on bc.id_acct = a.id ").
			append("where a.id_company = :idCompany and a.status_cd = 'A'").
			append(") n set ").
			append("m.dt_current_bal = date('" + sdf.format(dtEnd) + "'), ").
			append("m.debit_current_bal = (case when n.beginBal + n.sum >= 0 then n.beginBal + n.sum else 0 end), ").
			append("m.credit_current_bal = (case when n.beginBal + n.sum < 0 then -(n.beginBal + n.sum) else 0 end), ").
			append("m.dt_close_bal = date('" + sdf.format(dtEnd) + "'), ").
			append("m.debit_close_bal = (case when n.beginBal + n.sum >= 0 then n.beginBal + n.sum else 0 end), ").
			append("m.credit_close_bal = (case when n.beginBal + n.sum < 0 then -(n.beginBal + n.sum) else 0 end), ").
			append("m.dt_upd = sysdate(), m.upd_by = :updBy ").
			append("where n.idAcctBal is not null and m.id = n.idAcctBal");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idCompany", companyVO.getId());
		query.setString("updBy", userName);
		query.executeUpdate();
		
		// update current p/l
		sb = new StringBuilder();
		/*sb.append("update account_bal b join (").
			append("select ").
				append("(select id from account_bal where id_acct = :idAcctCurrentPL and date(dt_begin_bal) between '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' order by dt_begin_bal desc limit 1) as 'idAcctBal', ").
				append("(select -(debit_begin_bal - credit_begin_bal) from account_bal where id_acct = :idAcctCurrentPL and date(dt_begin_bal) between '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' order by dt_begin_bal desc limit 1) as 'beginBalance', ").
				append("(sum(t.debit) - sum(t.credit)) as 'balance' ").
			append("from account_trans t ").
			append("join (select a.id from account a, account_cat c where a.id_acct_cat = c.id and c.code in ('I', 'X', 'P', 'OI') and a.id_company = :idCompany and a.status_cd = 'A') t1 on t.id_acct = t1.id ").
			append("where t.id_company = :idCompany and t.status_cd = 'A' and date(t.dt_trans) between '" + sdf.format(dtStart) + "' and '" + sdf.format(dtEnd) + "'").
			append(") s on b.id =  s.idAcctBal ").
			append("set b.dt_current_bal = '" + sdf.format(dtEnd) + "', ").
			append("b.debit_current_bal = case when (beginBalance + balance) < 0 then -(beginBalance + balance) else 0 end, ").
			append("b.credit_current_bal = case when (beginBalance + balance) >= 0 then (beginBalance + balance) else 0 end, ").
			append("b.dt_close_bal = '" + sdf.format(dtEnd) + "', ").
			append("b.debit_close_bal = case when (beginBalance + balance) < 0 then -(beginBalance + balance) else 0 end, ").
			append("b.credit_close_bal = case when (beginBalance + balance) >= 0 then (beginBalance + balance) else 0 end, ").
			append("b.dt_upd = sysdate(), b.upd_by = :updBy");*/
		
		sb.append("update account_bal b join (").
		append("select ").
			append("(select id from account_bal where id_acct = :idAcctCurrentPL and date(dt_begin_bal) between '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' order by dt_begin_bal desc limit 1) as 'idAcctBal', ").
			append("(select (debit_begin_bal - credit_begin_bal) from account_bal where id_acct = :idAcctCurrentPL and date(dt_begin_bal) between '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' order by dt_begin_bal desc limit 1) as 'beginBalance', ").
			append("(sum(t.debit) - sum(t.credit)) as 'balance' ").
		append("from account_trans t ").
		append("join (select a.id from account a, account_cat c where a.id_acct_cat = c.id and c.code in ('I', 'X', 'P', 'OI') and a.id_company = :idCompany and a.status_cd = 'A') t1 on t.id_acct = t1.id ").
		append("where t.id_company = :idCompany and t.status_cd = 'A' and date(t.dt_trans) between '" + sdf.format(dtStart) + "' and '" + sdf.format(dtEnd) + "'").
		append(") s on b.id =  s.idAcctBal ").
		append("set b.dt_current_bal = '" + sdf.format(dtEnd) + "', ").
		append("b.debit_current_bal = case when (beginBalance + balance) >= 0 then (beginBalance + balance) else 0 end, ").
		append("b.credit_current_bal = case when (beginBalance + balance) < 0 then -(beginBalance + balance) else 0 end, ").
		append("b.dt_close_bal = '" + sdf.format(dtEnd) + "', ").
		append("b.debit_close_bal = case when (beginBalance + balance) >= 0 then (beginBalance + balance) else 0 end, ").
		append("b.credit_close_bal = case when (beginBalance + balance) < 0 then -(beginBalance + balance) else 0 end, ").
		append("b.dt_upd = sysdate(), b.upd_by = :updBy");
		
		query = createSQLQuery(sb.toString());
		query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
		query.setLong("idCompany", companyVO.getId());
		query.setString("updBy", userName);
		query.executeUpdate();
	}
	
	/*
	 * 
	 * @param idCompany
	 * @param userName
	 * @param minDate
	 * @param maxDate
	 * @param dtStart
	 * @param dtEnd
	 * @param newDate
	 */
	private void updateNewAcctBal(CompanyVO companyVO, String userName, Date minDate, Date maxDate, Date dtStart, Date dtEnd, Date newDate) {
		StringBuilder sb = new StringBuilder();
		
		String condition1 = "and date('" + sdf.format(newDate) + "') > date('" + sdf.format(maxDate) + "') ";
		
		sb.append("update account_bal b ").
			append("join (").
			append("	select a.id, c.code, a.type_cd from account a left join (select id, code from account_cat) c on a.id_acct_cat = c.id where a.id_company = :idCompany").
			append(") m on b.id_acct = m.id ").
			append("join (").
			append("	select id_acct, debit_current_bal, credit_current_bal, debit_close_bal, credit_close_bal from account_bal where date(dt_begin_bal) = '").append(sdf.format(dtStart)).append("'").
			append(") b1 on b1.id_acct = m.id set ").
			append("b.debit_begin_bal = (case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else b1.debit_current_bal end),").
			append("b.credit_begin_bal = (case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else b1.credit_current_bal end),").
			append("b.debit_current_bal = (case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else b1.debit_current_bal end),").
			append("b.credit_current_bal = (case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else b1.credit_current_bal end),").
			append("b.debit_close_bal = (case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else b1.debit_close_bal end),").
			append("b.credit_close_bal = (case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else b1.credit_close_bal end),").
			append("b.dt_upd = sysdate(), b.upd_by = :updBy").
			append(" where date(b.dt_begin_bal) = '").append(sdf.format(newDate)).append("'");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idCompany", companyVO.getId());
		query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
		query.setString("updBy", userName);
		query.executeUpdate();

		// update current p/l
		if (newDate.before(maxDate)) {
			sb = new StringBuilder();
			sb.append("update account_bal b ").
				append("join (").
				append("	select id from account where id_company = :idCompany and id = :idAcctCurrentPL ").
				append(") m on b.id_acct = m.id ").
				append("join (").
				append("	select id_acct, debit_current_bal, credit_current_bal, debit_close_bal, credit_close_bal from account_bal where date(dt_begin_bal) = '").append(sdf.format(dtStart)).append("'").
				append(") b1 on b1.id_acct = m.id set ").
				append("b.debit_begin_bal = b1.debit_current_bal,").
				append("b.credit_begin_bal = b1.credit_current_bal,").
				append("b.debit_current_bal = b1.debit_current_bal,").
				append("b.credit_current_bal = b1.credit_current_bal,").
				append("b.debit_close_bal = b1.debit_close_bal,").
				append("b.credit_close_bal = b1.credit_close_bal,").
				append("b.dt_upd = sysdate(), b.upd_by = :updBy").
				append(" where date(b.dt_begin_bal) = '").append(sdf.format(newDate)).append("'");
			
			query = createSQLQuery(sb.toString());
			query.setLong("idCompany", companyVO.getId());
			query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
			query.setString("updBy", userName);
			query.executeUpdate();
		}
		
		// update accumulated p/l
		if (newDate.after(maxDate)) {
			sb = new StringBuilder();
			sb.append("select debit_current_bal, credit_current_bal, debit_close_bal, credit_close_bal from account_bal where date(dt_begin_bal) = '").
				append(sdf.format(dtStart)).append("' and id_acct = :idAcctCurrentPL");
			
			query = createSQLQuery(sb.toString());
			query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
			Object[] obj = (Object[]) query.uniqueResult();
			double debitCurr = (double) obj[0];
			double creditCurr = (double) obj[1];
			//double debitClose = (double) obj[2];
			//double creditClose = (double) obj[3];
			
			sb = new StringBuilder();
			sb.append("select (debit_begin_bal - credit_begin_bal) from account_bal where id_acct = :idAcctAccumulatedPL and date(dt_begin_bal) = '").append(sdf.format(minDate)).append("'");
			query = createSQLQuery(sb.toString());
			query.setLong("idAcctAccumulatedPL", companyVO.getIdAcctAccumulatedPL());
			obj = (Object[]) query.uniqueResult();
			double beginBal = (double) obj[0];
			
			// profit
			if (beginBal < 0) {
				if (creditCurr > 0) creditCurr = -beginBal + creditCurr;
				
				if (debitCurr > 0) {
					debitCurr = -beginBal - debitCurr;
					if (debitCurr < 0) debitCurr = -debitCurr;
					else {
						creditCurr = debitCurr;
						debitCurr = 0;
					}
				}
			// loss
			} else {
				if (debitCurr > 0) debitCurr = -beginBal + debitCurr;
				
				if (creditCurr > 0) {
					creditCurr = -beginBal - creditCurr;
					if (creditCurr < 0) creditCurr = -creditCurr;
					else {
						debitCurr = creditCurr;
						creditCurr = 0;
					}
				}
			}
			
			sb = new StringBuilder();
			sb.append("update account_bal set ").
				append("debit_begin_bal = :debitCurr,").
				append("credit_begin_bal = :creditCurr,").
				append("debit_current_bal = :debitCurr,").
				append("credit_current_bal = :creditCurr,").
				append("debit_close_bal = :debitClose,").
				append("credit_close_bal = :creditClose,").
				append("dt_upd = sysdate(), upd_by = :updBy").
				append(" where id_acct = :idAcctAccumulatedPL and date(dt_begin_bal) = '").append(sdf.format(newDate)).append("'");
			
			query = createSQLQuery(sb.toString());
			query.setLong("idAcctAccumulatedPL", companyVO.getIdAcctAccumulatedPL());
			query.setDouble("debitCurr", debitCurr);
			query.setDouble("creditCurr", creditCurr);
			query.setDouble("debitClose", debitCurr);
			query.setDouble("creditClose", creditCurr);
			query.setString("updBy", userName);
			query.executeUpdate();
		}
	}
	
	/*
	 * Insert new account balance
	 * @param idCompany
	 * @param userName
	 * @param minDate
	 * @param maxDate
	 * @param dtStart
	 * @param dtEnd
	 * @param newDate
	 */
	private void insertNewAcctBal(CompanyVO companyVO, String userName, Date minDate, Date maxDate, Date dtStart, Date dtEnd, Date newDate) {
		StringBuilder sb = new StringBuilder();
		/*sb.append("insert into account_bal (id_acct, ").
			append("dt_begin_bal, debit_begin_bal, credit_begin_bal, ").
			append("dt_current_bal, debit_current_bal, credit_current_bal, ").
			append("dt_close_bal, debit_close_bal, credit_close_bal, ").
			append("dt_created, created_by, dt_upd, upd_by) ").
			append("(select m.id, ").
				append("'" + sdf.format(newDate) + "', ").
				append("case when (m.acctCat in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) and month('" + sdf.format(newDate) + "') = month('" + sdf.format(minDate) + "') then 0 else (case when balance >= 0 then balance else 0 end) end as 'debit_begin_bal', ").
				append("case when (m.acctCat in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) and month('" + sdf.format(newDate) + "') = month('" + sdf.format(minDate) + "') then 0 else (case when balance < 0 then -1 * balance else 0 end) end as 'credit_begin_bal', ").
				append("'" + sdf.format(newDate) + "', ").
				append("case when (m.acctCat in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) and month('" + sdf.format(newDate) + "') = month('" + sdf.format(minDate) + "') then 0 else (case when balance >= 0 then balance else 0 end) end as 'debit_current_bal', ").
				append("case when (m.acctCat in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) and month('" + sdf.format(newDate) + "') = month('" + sdf.format(minDate) + "') then 0 else (case when balance < 0 then -1 * balance else 0 end) end as 'credit_current_bal', ").
				append("'" + sdf.format(newDate) + "', ").
				append("case when (m.acctCat in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) and month('" + sdf.format(newDate) + "') = month('" + sdf.format(minDate) + "') then 0 else (case when balance >= 0 then balance else 0 end) end as 'debit_close_bal', ").
				append("case when (m.acctCat in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) and month('" + sdf.format(newDate) + "') = month('" + sdf.format(minDate) + "') then 0 else (case when balance < 0 then -1 * balance else 0 end) end as 'credit_close_bal', ").
				append("sysdate(), :createdBy, sysdate(), :updBy ").
			append("from ( ").
				append("select a.id, ").
				append("(select b.id from account_bal b where b.id_acct = a.id and b.dt_begin_bal between date('" + sdf.format(minDate) + "') and date('" + sdf.format(maxDate) + "') order by b.dt_begin_bal desc limit 1) as id_acct_bal, ").
				append("(select (b.debit_begin_bal - b.credit_begin_bal) from account_bal b where b.id_acct = a.id and b.dt_begin_bal between date('" + sdf.format(minDate) + "') and date('" + sdf.format(maxDate) + "') order by b.dt_begin_bal desc limit 1) + ").
				append("(select case when (sum(t.debit) - sum(t.credit)) is null then 0 else (sum(t.debit) - sum(t.credit)) end ").
				append("from account_trans t where t.id_company = a.id_company and t.id_acct = a.id and t.status_cd = 'A' and ").
				append("date(dt_trans) between date('" + sdf.format(dtStart) + "') and date('" + sdf.format(dtEnd) + "')) as 'balance', ac.code as 'acctCat' ").
			append("from account a ").
			append("left join (select id, code from account_cat) ac on ac.id = a.id_acct_cat ").
			append("where a.id_company = :idCompany and a.status_cd = 'A' ").
			append(") m where m.id_acct_bal is not null)");*/
		
		String condition1 = "and date('" + sdf.format(newDate) + "') > date('" + sdf.format(maxDate) + "') ";
		
		sb.append("insert into account_bal (id_acct, ").
		append("dt_begin_bal, debit_begin_bal, credit_begin_bal, ").
		append("dt_current_bal, debit_current_bal, credit_current_bal, ").
		append("dt_close_bal, debit_close_bal, credit_close_bal, ").
		append("dt_created, created_by, dt_upd, upd_by, type_cd) ").
		append("(select m.id_acct, ").
			append("'" + sdf.format(newDate) + "', ").
			append("case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else m.debit_current_bal end as 'debit_begin_bal', ").
			append("case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else m.credit_current_bal end as 'credit_begin_bal', ").
			append("'" + sdf.format(newDate) + "', ").
			append("case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else m.debit_current_bal end as 'debit_current_bal', ").
			append("case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else m.credit_current_bal end as 'credit_current_bal', ").
			append("'" + sdf.format(newDate) + "', ").
			append("case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else m.debit_close_bal end as 'debit_close_bal', ").
			append("case when (m.code in ('I', 'X', 'P', 'OI') or m.id = :idAcctCurrentPL) " + condition1 + " and m.type_cd = 'N' then 0 else m.credit_close_bal end as 'credit_close_bal', ").
			append("sysdate(), :createdBy, sysdate(), :updBy, 'C' ").
		append("from (select * from (").
//				append("select * from (").
//					append("select id_acct, debit_current_bal, credit_current_bal, debit_close_bal, credit_close_bal from account_bal b, account a ").
//					append("where b.id_acct = a.id and a.id_company = :idCompany and b.dt_begin_bal between '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' order by b.dt_begin_bal desc").
//				append(") a group by id_acct").
				
				append("SELECT b.id_acct, b.debit_current_bal, b.credit_current_bal, b.debit_close_bal, b.credit_close_bal ").
				append("FROM account_bal b ").
				append("JOIN account c ON b.id_acct = c.id ").
				append("JOIN ( ").
					append("SELECT id_acct, MAX(dt_begin_bal) AS max_date ").
					append("FROM account_bal ").
					append("WHERE dt_begin_bal BETWEEN '" + sdf.format(minDate) + "' and '" + sdf.format(maxDate) + "' ").
					append("GROUP BY id_acct ").
				append(") max_dates ON b.id_acct = max_dates.id_acct AND b.dt_begin_bal = max_dates.max_date ").
				append("WHERE c.id_company = :idCompany ").
//				append("ORDER BY b.id_acct ").
			
			
			append(") v left join (").
			append("select a.id, c.code, a.type_cd from account a, account_cat c where a.id_acct_cat = c.id and a.id_company = :idCompany").
			append(") e on e.id = v.id_acct) m)");
	
	Query query = createSQLQuery(sb.toString());
	query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
	query.setLong("idCompany", companyVO.getId());
	query.setString("createdBy", userName);
	query.setString("updBy", userName);
	query.executeUpdate();

	// update current p/l
	if (newDate.before(maxDate)) {
		sb = new StringBuilder();
		sb.append("update account_bal b ").
			append("join (").
			append("	select id from account where id_company = :idCompany and id = :idAcctCurrentPL ").
			append(") m on b.id_acct = m.id ").
			append("join (").
			append("	select id_acct, debit_current_bal, credit_current_bal, debit_close_bal, credit_close_bal from account_bal where date(dt_begin_bal) = '").append(sdf.format(dtStart)).append("'").
			append(") b1 on b1.id_acct = m.id set ").
			append("b.debit_begin_bal = b1.debit_current_bal,").
			append("b.credit_begin_bal = b1.credit_current_bal,").
			append("b.debit_current_bal = b1.debit_current_bal,").
			append("b.credit_current_bal = b1.credit_current_bal,").
			append("b.debit_close_bal = b1.debit_close_bal,").
			append("b.credit_close_bal = b1.credit_close_bal,").
			append("b.dt_upd = sysdate(), b.upd_by = :updBy").
			append(" where date(b.dt_begin_bal) = '").append(sdf.format(newDate)).append("'");
		
		query = createSQLQuery(sb.toString());
		query.setLong("idCompany", companyVO.getId());
		query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
		query.setString("updBy", userName);
		query.executeUpdate();
	}
	
	// update accumulated p/l
	if (newDate.after(maxDate)) {
			sb = new StringBuilder();
			sb.append("select debit_current_bal, credit_current_bal, debit_close_bal, credit_close_bal from account_bal where date(dt_begin_bal) = '").
				append(sdf.format(dtStart)).append("' and id_acct = :idAcctCurrentPL");
			
			query = createSQLQuery(sb.toString());
			query.setLong("idAcctCurrentPL", companyVO.getIdAcctCurrentPL());
			Object[] obj = (Object[]) query.uniqueResult();
			double debitCurr = (double) obj[0];
			double creditCurr = (double) obj[1];
			//double debitClose = (double) obj[2];
			//double creditClose = (double) obj[3];
			
			sb = new StringBuilder();
			sb.append("select (debit_begin_bal - credit_begin_bal) from account_bal where id_acct = :idAcctAccumulatedPL and date(dt_begin_bal) = '").append(sdf.format(minDate)).append("'");
			System.out.println("*** " + companyVO.getIdAcctAccumulatedPL() + "|" + sb.toString());
			query = createSQLQuery(sb.toString());
			query.setLong("idAcctAccumulatedPL", companyVO.getIdAcctAccumulatedPL());
			Number number = (Number) query.uniqueResult();
			double beginBal = number.doubleValue();
			
			// profit
			if (beginBal < 0) {
				if (creditCurr > 0) creditCurr = -beginBal + creditCurr;
				
				if (debitCurr > 0) {
					debitCurr = -beginBal - debitCurr;
					if (debitCurr < 0) debitCurr = -debitCurr;
					else {
						creditCurr = debitCurr;
						debitCurr = 0;
					}
				}
			// loss
			} else {
				if (debitCurr > 0) debitCurr = beginBal + debitCurr;
				
				if (creditCurr > 0) {
					creditCurr = beginBal - creditCurr;
					if (creditCurr < 0) creditCurr = -creditCurr;
					else {
						debitCurr = creditCurr;
						creditCurr = 0;
					}
				}
			}
			
			sb = new StringBuilder();
			sb.append("update account_bal set ").
				append("debit_begin_bal = :debitCurr,").
				append("credit_begin_bal = :creditCurr,").
				append("debit_current_bal = :debitCurr,").
				append("credit_current_bal = :creditCurr,").
				append("debit_close_bal = :debitClose,").
				append("credit_close_bal = :creditClose,").
				append("dt_upd = sysdate(), upd_by = :updBy").
				append(" where id_acct = :idAcctAccumulatedPL and date(dt_begin_bal) = '").append(sdf.format(newDate)).append("'");
			
			query = createSQLQuery(sb.toString());
			query.setLong("idAcctAccumulatedPL", companyVO.getIdAcctAccumulatedPL());
			query.setDouble("debitCurr", debitCurr);
			query.setDouble("creditCurr", creditCurr);
			query.setDouble("debitClose", debitCurr);
			query.setDouble("creditClose", creditCurr);
			query.setString("updBy", userName);
			query.executeUpdate();
		}
	}

	@Override
	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT 1 FROM financial_period WHERE id_company = :idCompany AND (status = 0 or status = -1) AND ")
			.append("DATE(:dtMonth) BETWEEN DATE(dt_start) AND DATE(dt_end)");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idCompany", idCompany);
		query.setDate("dtMonth", date);
		
		return query.uniqueResult() == null ? false : true;
	}
	
	@Override
	public FinancialPeriodVO getFinPeriodLock(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {

		Criteria criteria = createCriteria(FinancialPeriodVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.add(Restrictions.eq("dtStart", dateFrom));
		criteria.add(Restrictions.eq("dtEnd", dateTo));
		criteria.setMaxResults(1);
		return (FinancialPeriodVO) criteria.uniqueResult();
	}
	
	@Override
	public Integer getFinPeriodClosedStatus(Long idCompany, Date date) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT status FROM financial_period WHERE id_company = :idCompany AND ")
			.append("DATE(:dtMonth) BETWEEN DATE(dt_start) AND DATE(dt_end)");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idCompany", idCompany);
		query.setDate("dtMonth", date);
		
		// Financial Period Status:
		// 1 : Open, 0 : Close, -1 : Close2 (2nd Level Close for GST)
		Object status = query.uniqueResult();
		if (status != null) {
			return ((Integer) status).intValue();
		} else {
			return 1;
		}
	}

	@Override
	public String getGSTProcessStatus(Long idCompany, Date date, boolean isDateBetween) throws BusinessException {
		/*Criteria criteria = createCriteria(GSTSummaryVO.class);
		criteria.add(Restrictions.eq("idCompany", idCompany));
		if (isDateBetween) criteria.add(Restrictions.sqlRestriction("'".concat(date.toString()).concat("' BETWEEN date_from AND date_to")));
		else {
			criteria.add(Restrictions.ge("dateFrom", date));
			criteria.add(Restrictions.ge("dateTo", date));
		}
		GSTSummaryVO vo = (GSTSummaryVO) criteria.uniqueResult();
		if (vo == null) return GSTProcessStatus.PENDING.getValue();
		else return vo.getProcessed();*/
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT processed FROM gst_summary WHERE id_company = :idCompany ");
		if (isDateBetween) {
			sb.append("AND DATE(:checkDate) BETWEEN DATE(date_from) AND DATE(date_to) ");
		} else {
			sb.append("AND DATE(date_from) >= DATE(:checkDate) AND DATE(date_to) >= DATE(:checkDate) ");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idCompany", idCompany);
		query.setDate("checkDate", date);
		
		// GST Period Status:
		// P : Pending, L : Lock, S : Submit
		Object status = query.uniqueResult();
		if (status != null) {
			return String.valueOf(status);
		} else {
			return GSTProcessStatus.PENDING.getValue();
		}
	}
}
