package com.bcs.zsg.db.bterp.dao.acct;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;

public class AccountDAOImpl extends BaseHibernateDAO implements AccountDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<AcctVO> getAcctList(Long idCompany, String strAutoComplete) throws BusinessException {
		Query query = createSQLQuery("SELECT id FROM account WHERE CONCAT(code,'-',sub_code) like '" 
										+ strAutoComplete + "%'");
		
		List<Object> results = query.list();
		List<Long> acctIdList = new ArrayList<Long>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			BigInteger id = (BigInteger) it.next();
			acctIdList.add(id.longValue());
		}
		
		if (CollectionUtils.isNotEmpty(acctIdList)) {
			Criteria criteria = createCriteria(AcctVO.class);
			criteria.add(Restrictions.eq("idCompany",idCompany));
			criteria.add(Restrictions.in("id", acctIdList));
			criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
			return criteria.list();
		}
		
		return null;
	}
	
	@Override
	public List<AcctVO> getAcctListAutoComplete(Long idCompany, String strAutoComplete) throws BusinessException {
		return getAcctListAutoComplete(idCompany, strAutoComplete, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcctVO> getAcctListAutoComplete(Long idCompany, String strAutoComplete, boolean restrictNoSubCode) 
				throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, code, sub_code, description, sub_description, tax_code FROM account ")
			.append(" WHERE id_company=").append(idCompany)
			.append(" AND status_cd='").append(BaseConstant.STATUS_ACTIVE).append("'");
		
		if(restrictNoSubCode)
			sb.append(" AND sub_code='' AND code like '");
		else
			sb.append(" AND CONCAT(code, '-', sub_code) like '");
			
		sb.append(strAutoComplete).append("%'");

		Query query = createSQLQuery(sb.toString());

		List<Object> results = query.list();
		List<AcctVO> acctList = new ArrayList<AcctVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			AcctVO vo = new AcctVO();
			vo.setId(((BigInteger) row[0]).longValue()); 
			vo.setCode((String) row[1]);
			vo.setSubCode((String) row[2]);
			vo.setDesc((String) row[3]);
			vo.setSubDesc((String) row[4]);
			vo.setTaxCode((String) row[5]);
			
			acctList.add(vo);
		}
		
		return acctList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.ChartOfAcctDAO#getAcctVO(java.lang.Long)
	 */
	@Override
	public AcctVO getAcctVO(Long idAcct) throws BusinessException {
		Criteria criteria = createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("id", idAcct));
		return (AcctVO) criteria.uniqueResult();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.dao.ChartOfAcctDAO#isAcctValid(com.bcs.zsg.acct.vo.AccountVO)
	 * CODE IS SAME AS ChartOfAcctDAO#isAcctValid
	 */
	@Override
	public boolean isAcctValid(AcctVO acctVO) throws BusinessException {
		Criteria criteria = createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("idCompany", acctVO.getIdCompany()));
		criteria.add(Restrictions.eq("code", acctVO.getCode()));
		if(acctVO.getSubCode() != null)
			criteria.add(Restrictions.eq("subCode", acctVO.getSubCode()));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return (criteria.uniqueResult() == null) ? false : true;
	}

	
	@Override
	public int getAcctListSize(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT count(1) ")
			.append("FROM account A ")
			.append("WHERE id_company='").append(params.get("idCompany"))
			.append("' AND status_cd = '").append(BaseConstant.STATUS_ACTIVE).append("' ")
			.append(genAcctListFilter(params));
		
		Query query = createSQLQuery(sb.toString());
		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	private String genAcctListFilter(Map<String, Object> params) {
		if(ObjectUtils.equals(params.get("filters"), null))
			return "";
		
		StringBuilder _sb = new StringBuilder();
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		boolean restrictNoSubCode = (Boolean) (params.get("restrictNoSubCode") == null ? false : params.get("restrictNoSubCode"));
		
		if (!filters.isEmpty()) {
			_sb.append(" AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("code".equals(entry.getKey())) {
					if(restrictNoSubCode) {
						_sb.append(" sub_code='' AND ")
							.append("A.code LIKE '%").append(entry.getValue()).append("%'");
					} else {
						_sb.append("CONCAT(A.code, '-', A.sub_code) LIKE '%").append(entry.getValue()).append("%'");
					}
				} else if ("subCode".equals(entry.getKey())) {
					_sb.append("A.sub_code LIKE '%").append(entry.getValue()).append("%'");
				} else if ("desc".equals(entry.getKey())) {
					if(restrictNoSubCode) {
						_sb.append("A.description LIKE '%").append(entry.getValue()).append("%'");
					} else {
						_sb.append("CONCAT(A.description, '-', A.sub_description) LIKE '%")
							.append(entry.getValue()).append("%'");
					}
				} else if ("subDesc".equals(entry.getKey())) {
					_sb.append("A.sub_description LIKE '%").append(entry.getValue()).append("%'");
				}
				
				if (filters.size() > 1 && it.hasNext()) _sb.append(" AND ");
			}
			_sb.append(") ");
		}
		return _sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AcctVO> getAcctList(Map<String, Object> params) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		boolean getAcctCatCols = (ObjectUtils.equals(params.get("getAcctCatCols"), null) ? false : (boolean)params.get("getAcctCatCols"));
		boolean getCreateUpdateColumn =  (ObjectUtils.equals(params.get("getCreateUpdateCols"), null) ? false : (boolean)params.get("getCreateUpdateCols"));
		
		sb.append("SELECT id, A.code, sub_code, description, sub_description ");

		if(getAcctCatCols) {
			sb.append(", id_company, id_acct_cat, id_acct_sub_cat, remarks ");
		}
		if(getCreateUpdateColumn) {
			sb.append(", status_cd, dt_created, created_by, dt_upd, upd_by ");
		}
		sb.append(" , tax_code, B.rate ");

		sb.append("FROM account A ")
			.append("LEFT JOIN (SELECT code AS 'code1', rate FROM tax_code) B ON A.tax_code = B.code1 ")
			.append("WHERE id_company='").append(params.get("idCompany"))
			.append("' AND status_cd = '").append(BaseConstant.STATUS_ACTIVE).append("' ")
			.append(genAcctListFilter(params))
			.append(genOrderBy(params));
		
		Query query = createSQLQuery(sb.toString());
		if(!ObjectUtils.equals(params.get("first"), null))
			query.setFirstResult((int) params.get("first"));
		if(!ObjectUtils.equals(params.get("pageSize"), null))
			query.setMaxResults((int) params.get("pageSize"));
		
		List<Object> results = query.list();
		List<AcctVO> acctList = new ArrayList<AcctVO>();

		int countRow = 4;
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			AcctVO vo = new AcctVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setCode((String) row[1]);
			vo.setSubCode((String) row[2]);
			vo.setDesc((String) row[3]);
			vo.setSubDesc((String) row[4]);

			countRow = 4;
			if(getAcctCatCols) {
				vo.setIdCompany(((BigInteger) row[5]).longValue());
				vo.setIdAcctCat(((BigInteger) row[6]).longValue());
				vo.setIdAcctSubCat(((BigInteger) row[7]).longValue());
				vo.setRemarks((String) row[8]);
				countRow = 8;
			}
			
			if(getCreateUpdateColumn) {
		        if (row[countRow + 1] != null) vo.setStatusCode((String) row[countRow + 1]);
				if (row[countRow + 2] != null) vo.setCreatedDate((Date) row[countRow + 2]);
				if (row[countRow + 3] != null) vo.setCreatedBy((String) row[countRow + 3]);
				if (row[countRow + 4] != null) vo.setUpdatedDate((Date) row[countRow + 4]);
				if (row[countRow + 5] != null) vo.setUpdatedBy((String) row[countRow + 5]);
				countRow = 13;
			}
			
			if (row[countRow + 1] != null) {
				vo.setTaxCode((String) row[countRow + 1]);
				vo.setTaxRate((Float) row[countRow + 2]);
			}
			
			acctList.add(vo);
		}
		
		return acctList;
	}
	
	@Override
	public AcctVO getAccountLiabilityById(Long Id) {
		Criteria criteria = getSession().createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("id", Id));
		return (AcctVO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	private String genOrderBy(Map<String, Object> params) {
		if(ObjectUtils.equals(params.get("sortField"), null))
			return "";
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		
		StringBuilder sb = new StringBuilder();
		sb.append(" ORDER BY ");

		boolean restrictNoSubCode = (Boolean) (params.get("restrictNoSubCode") == null ? false : params.get("restrictNoSubCode"));
		
		String sortField = (String) params.get("sortField");
		String sortDirection = "";
		
		if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) {
			sortDirection = " ASC";
		} else {
			sortDirection = " DESC";
		}
		
		String colCode = "CAST(code AS decimal)";
		String colSubCode = "CAST(sub_code AS decimal)";
		
		if (sortField == null) {
			if(restrictNoSubCode) {
				if (!filters.containsKey("desc")) {
					sb.append(colCode).append(", description");
				} else {
					sb.append(getFilterDesc(filters.get("desc")));
				}
			} else {
				if (!filters.containsKey("desc")) {
					sb.append(colCode).append(", ").append(colSubCode).append(", description");
				} else {
					sb.append(getFilterDesc(filters.get("desc")));
				}
			}
		} else {
			if ("code".equals(sortField)) {
				if(restrictNoSubCode) {
					sb.append(colCode);
				} else {
					sb.append(colCode).append(sortDirection).append(", ").append(colSubCode);
				}
			} else if ("subCode".equals(sortField)) {
				sb.append(colSubCode);
			} else if ("desc".equals(sortField)) {
				if(restrictNoSubCode) {
					if (!filters.containsKey("desc")) {
						sb.append("description");
					} else {
						sb.append(getFilterDesc(filters.get("desc")));
					}
				} else {
					if (!filters.containsKey("desc")) {
						sb.append("description");
					} else {
						sb.append(getFilterDesc(filters.get("desc")));
					}
				}
			} else if ("subDesc".equals(sortField)) {
				if (!filters.containsKey("sub_description")) {
					sb.append("sub_description");
				} else {
					sb.append("case when sub_description like '").append(filters.get("sub_description")).append("%' then sub_description else concat('{0} ', sub_description) end");
				}
			}
			
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" ASC");
			else sb.append(" DESC");
		}
		return sb.toString();
	}
	
	private String getFilterDesc(String filterValue) {
		return "case when description like '" + filterValue + "%' then description else concat('{0} ', description) end";
	}
}
