package com.bcs.zsg.bank.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.LookupItemVO;


public class BankAdjustDAOImpl extends BaseHibernateDAO implements BankAdjustDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LookupItemVO> getLookUpItemBTTList(String bankTransType)
			throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(LookupItemVO.class);
		criteria.add(Restrictions.eq("lookupCatCd",bankTransType));
		return criteria.list();
	}

	@Override
	public AcctVO getAcctList(Long acctId) throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("id",acctId));
		return (AcctVO)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo,
			String typeCd, String sysCode, Long CompId) throws BusinessException {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(AcctTransViewVO.class);
		criteria.add(Restrictions.eq("sysNo", sysNo));
		criteria.add(Restrictions.eq("sysCode", sysCode));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("type", typeCd));
		criteria.add(Restrictions.eq("companyId", CompId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashBookVO> getCashBookBankAdjustList(String bankAdjust, Long bankId, SearchParamVO searchParamVO) throws BusinessException {
		/*Criteria criteria = getSession().createCriteria(CashBookVO.class);
		criteria.add(Restrictions.eq("sysCode", bankAdjust));
		criteria.add(Restrictions.eq("idBank",bankId));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();*/
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.id, c.sys_no, c.dt_trans, c.trans_type_cd, c.payee, c.credit, c.ref_no, c.remarks, b.name, c.type_cd, c.sys_cd, c.id_bank, c.debit ").
			append("FROM cash_book c ").
			append("LEFT JOIN bank b ON b.id = c.id_bank ").
			append("WHERE c.sys_cd = :sysCode AND c.status_cd = :statusCode ");
		
		// filtered by transaction date
		if (searchParamVO.getObj2() == null || !CommonConstant.SEARCH_ALL_DATES.equals(searchParamVO.getObj2().toString())) {
			sb.append("AND dt_trans BETWEEN ");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(searchParamVO.getFromDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			sb.append("'").append(dateFormat.format(cal.getTime())).append("' AND ");
			
			cal.setTime(searchParamVO.getToDate());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			sb.append("'").append(dateFormat.format(cal.getTime())).append("' ");
		}
		
		// filtered by bank
		if (!searchParamVO.getObj1().toString().equals(CommonConstant.SEARCH_ALL_BANKS) && bankId != null) {
			sb.append("AND id_bank = '").append(bankId).append("' ");
		}
		
		// filtered by payment number
		if (searchParamVO.getObj3() != null && StringUtils.isNotEmpty(searchParamVO.getObj3().toString())) {
			sb.append("AND sys_no = '").append(searchParamVO.getObj3()).append("' ");
		}
		
		sb.append("ORDER by id DESC");
		System.out.println("[" + sb.toString() + "]");
		Query query = createSQLQuery(sb.toString());
		query.setString("sysCode", bankAdjust);
		query.setString("statusCode", BaseConstant.STATUS_ACTIVE);
		List<Object[]> results = query.list();
		List<CashBookVO> list = new ArrayList<CashBookVO>();
		
		if (CollectionUtils.isNotEmpty(results)) {
			for (Object obj[] : results) {
				CashBookVO vo = new CashBookVO();
				vo.setId(((Number) obj[0]).longValue());
				vo.setSysNo((String) obj[1]);
				vo.setDtTrans((Date) obj[2]);
				vo.setTransTypeCd((String) obj[3]);
				vo.setPayee((String) obj[4]);
				vo.setCredit((Double) obj[5]);
				vo.setRefNo((String) obj[6]);
				vo.setRemarks((String) obj[7]);
				vo.setBankName((String) obj[8]);
				vo.setTypeCd(obj[9] == null ? null : (String) obj[9]);
				vo.setSysCode((String) obj[10]);
				vo.setIdBank(((Number) obj[11]).longValue());
				vo.setDebit((Double) obj[12]);
				list.add(vo);
			}
		}
		return list;
	}




}
