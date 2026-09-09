package com.bcs.zsg.bank.dao;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookBalVO;
import com.bcs.zsg.bank.vo.CashBookSumVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.bo.CommonObject;
import com.bcs.zsg.common.dao.BaseCommonDAOLMImpl;
import com.bcs.zsg.common.dao.CommonDAOHelper;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.MODULE;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.BaseContext;

public class CashBookDAOImpl extends BaseCommonDAOLMImpl implements CashBookDAO {
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getCashBookList(java.lang.Long)
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
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<?> getCashBookList(SearchParamVO searchParamVO, Object... method) throws BusinessException {
		if(method.length > 0) {
			String sqlQuery = getCashBookListRecon(searchParamVO) + " order by dtTrans, id";
			Query query = createSQLQuery(sqlQuery);
			return manualCashBookSumVOMapping(query.list());
		}
		return getCashBookListNormal(searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getNormalCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	private List<CashBookVO> getCashBookListNormal(SearchParamVO searchParamVO) throws BusinessException {
		Criteria criteria = createCriteria(CashBookVO.class);
		addFilter(criteria, searchParamVO, false);
		if (searchParamVO.getObj3() == null) {
			criteria.addOrder(Order.asc("dtTrans"));
			criteria.addOrder(Order.asc("id"));
		} else {
			Map<String, String> map = (Map<String, String>) searchParamVO.getObj3();
			String sortCol = map.get("sortCol");
			String sortOrder = map.get("sortOrder");
			if (CommonConstant.SORT_ASC.equalsIgnoreCase(sortOrder)) {
				if (sortCol.equals("idSysNo")) {
					criteria.addOrder(Order.asc("sysPrefix"));
					criteria.addOrder(Order.asc("sysNo"));
				}
				else if (sortCol.equals("idDtTrans")) criteria.addOrder(Order.asc("dtTrans"));
				else if (sortCol.equals("idTransTypeCd")) criteria.addOrder(Order.asc("transTypeCd"));
				else if (sortCol.equals("idDeposit")) criteria.addOrder(Order.asc("debit"));
				else if (sortCol.equals("idCredit")) criteria.addOrder(Order.asc("credit"));
				else if (sortCol.equals("idPayee")) criteria.addOrder(Order.asc("payee"));
				else if (sortCol.equals("idRefNo")) criteria.addOrder(Order.asc("refNo"));
				else if (sortCol.equals("idRemarks")) criteria.addOrder(Order.asc("remarks"));
				else if (sortCol.equals("idStatusCode")) criteria.addOrder(Order.asc("statusCode"));
				else if (sortCol.equals("idClr")) criteria.addOrder(Order.asc("isClear"));
				else if (sortCol.equals("idMrk")) criteria.addOrder(Order.asc("isMark"));
				else if (sortCol.equals("idUpdBy")) criteria.addOrder(Order.asc("updatedBy"));
				else if (sortCol.equals("idUpdDate")) criteria.addOrder(Order.asc("updatedDate"));
				
			} else {
				if (sortCol.equals("idSysNo")) {
					criteria.addOrder(Order.desc("sysPrefix"));
					criteria.addOrder(Order.desc("sysNo"));
				}
				else if (sortCol.equals("idDtTrans")) criteria.addOrder(Order.desc("dtTrans"));
				else if (sortCol.equals("idTransTypeCd")) criteria.addOrder(Order.desc("transTypeCd"));
				else if (sortCol.equals("idDeposit")) criteria.addOrder(Order.desc("debit"));
				else if (sortCol.equals("idCredit")) criteria.addOrder(Order.desc("credit"));
				else if (sortCol.equals("idPayee")) criteria.addOrder(Order.desc("payee"));
				else if (sortCol.equals("idRefNo")) criteria.addOrder(Order.desc("refNo"));
				else if (sortCol.equals("idRemarks")) criteria.addOrder(Order.desc("remarks"));
				else if (sortCol.equals("idStatusCode")) criteria.addOrder(Order.desc("statusCode"));
				else if (sortCol.equals("idClr")) criteria.addOrder(Order.desc("isClear"));
				else if (sortCol.equals("idMrk")) criteria.addOrder(Order.desc("isMark"));
				else if (sortCol.equals("idUpdBy")) criteria.addOrder(Order.desc("updatedBy"));
				else if (sortCol.equals("idUpdDate")) criteria.addOrder(Order.desc("updatedDate"));
			}
		}
		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getReconCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	public String getCashBookListRecon(SearchParamVO searchParamVO) throws BusinessException {
		String sqlQuery = genCashBookQuery(searchParamVO);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sqlQuery += " AND (date(csh.dt_Trans) between '" + sdf.format(searchParamVO.getFromDate()) + "'";
		sqlQuery += " AND '" + sdf.format(searchParamVO.getToDate()) + "')";
		
		if (StringUtils.isNotEmpty((String) searchParamVO.getObj2()) ) {
			if("1".equals(searchParamVO.getObj2())) sqlQuery += " AND (csh.is_Clear=0 OR (csh.is_Clear=1 AND DATE(dt_clear) != '" + sdf.format(searchParamVO.getToDate()) + "'))";
			else if("2".equals(searchParamVO.getObj2())) sqlQuery += " AND csh.debit>0.00";
			else if("4".equals(searchParamVO.getObj2())) sqlQuery += " AND csh.credit>0.00";
			else if("5".equals(searchParamVO.getObj2())) sqlQuery += " AND csh.is_Clear=1 AND DATE(dt_clear) = '" + sdf.format(searchParamVO.getToDate()) + "'";
		}
		
		return sqlQuery;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getPrevCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<?> getPrevCashBookList(SearchParamVO searchParamVO, Object... method) throws BusinessException {
		if(method.length > 0) {
			String sqlQuery = getPrevCashBookListRecon(searchParamVO) + " order by dtTrans, id";
			Query query = createSQLQuery(sqlQuery);
			return manualCashBookSumVOMapping(query.list());
		}
		return getPrevCashBookListNormal(searchParamVO);
	}
	
	/**
	 * 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public List<CashBookVO> getPrevCashBookListNormal(SearchParamVO searchParamVO) throws BusinessException {
		Criteria criteria = createCriteria(CashBookVO.class);
		addFilter(criteria, searchParamVO, true);
		criteria.addOrder(Order.asc("dtTrans"));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	/**
	 * 
	 * @param searchParamVO
	 * @return
	 * @throws BusinessException
	 */
	public String getPrevCashBookListRecon(SearchParamVO searchParamVO) throws BusinessException {
		String sqlQuery = genCashBookQuery(searchParamVO);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sqlQuery += " AND date(csh.dt_trans)<'" + sdf.format(searchParamVO.getFromDate()) + "'";
		//sqlQuery += " AND (csh.is_clear=0 OR (csh.is_clear = 1 AND date(dt_clear) = '" + sdf.format(searchParamVO.getToDate()) + "'))";
		
		if (StringUtils.isNotEmpty((String) searchParamVO.getObj2()) ) {
			if("1".equals(searchParamVO.getObj2())) sqlQuery += " AND (csh.is_clear=0 OR (csh.is_clear = 1 AND date(dt_clear) > '" + sdf.format(searchParamVO.getToDate()) + "'))";
			else if("2".equals(searchParamVO.getObj2())) sqlQuery += " AND csh.debit>0 AND (csh.is_clear=0 OR (csh.is_clear = 1 AND date(dt_clear) >= '" + sdf.format(searchParamVO.getToDate()) + "'))";
			else if("3".equals(searchParamVO.getObj2())) sqlQuery += " AND (csh.is_clear=0 OR (csh.is_clear = 1 AND date(dt_clear) >= '" + sdf.format(searchParamVO.getToDate()) + "'))";
			else if("4".equals(searchParamVO.getObj2())) sqlQuery += " AND csh.credit>0 AND (csh.is_clear=0 OR (csh.is_clear = 1 AND date(dt_clear) >= '" + sdf.format(searchParamVO.getToDate()) + "'))";
			else if("5".equals(searchParamVO.getObj2())) sqlQuery += " AND csh.is_Clear=1 AND DATE(dt_clear) = '" + sdf.format(searchParamVO.getToDate()) + "'";
		}
		
		return sqlQuery;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getCashBookBal(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CashBookBalVO getCashBookBal(SearchParamVO searchParamVO) throws BusinessException {
		Criteria criteria = createCriteria(CashBookVO.class);
		criteria.add(Restrictions.eq("idBank", Long.parseLong(searchParamVO.getObj1().toString())));
		criteria.add(Restrictions.between("dtTrans", searchParamVO.getFromDate(), searchParamVO.getToDate()));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.sum("debit"));
		projList.add(Projections.sum("credit"));
		criteria.setProjection(projList);
		
		List<Object> results = criteria.list();

		CashBookBalVO vo = new CashBookBalVO();
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo.setTotalDebit((Double) row[0]);
			vo.setTotalCredit((Double) row[1]);
		}
		return vo;
	}

	/*
	 * 
	 * @param _criteria
	 * @param searchParamVO
	 * @param isCashBookPrev
	 */
	private void addFilter(Criteria _criteria, SearchParamVO searchParamVO, boolean isCashBookPrev) {
		_criteria.add(Restrictions.eq("idBank", Long.parseLong(searchParamVO.getObj1().toString())));
		
		if(isCashBookPrev) {
			_criteria.add(Restrictions.le("dtTrans", searchParamVO.getFromDate()));
			_criteria.add(Restrictions.eq("isClear", false));
		} else {
			_criteria.add(Restrictions.between("dtTrans", searchParamVO.getFromDate(), searchParamVO.getToDate()));
			
			if (StringUtils.isNotEmpty((String) searchParamVO.getObj2()) ) {
				if("1".equals(searchParamVO.getObj2())) _criteria.add(Restrictions.eq("isClear", false));
				else if("2".equals(searchParamVO.getObj2())) _criteria.add(Restrictions.gt("debit", 0.00));
				else if("4".equals(searchParamVO.getObj2())) _criteria.add(Restrictions.gt("credit", 0.00));
				else if("5".equals(searchParamVO.getObj2())) _criteria.add(Restrictions.eq("isClear", true));
			}
		}
	}

	/*
	 * 
	 * @param searchParamVO
	 * @return
	 */
	private String genCashBookQuery(SearchParamVO searchParamVO) {
		return "SELECT csh.ID as id, csh.id_bank as idBank, csh.id_supplier as idSupplier, " +
				"	csh.id_customer as idCustomer, csh.dt_trans as dtTrans, csh.sys_cd as sysCode, csh.sys_prefix as sysPrefix, " +
				"	csh.sys_no as sysNo, csh.ref_no as refNo, csh.trans_type_cd as transTypeCd, csh.payee as payee, csh.debit as debit, " +
				"	IFNULL(sum.debit_sum, 0.00) as debitSum, csh.credit as credit, csh.is_clear as isClear, csh.is_mark as isMark, " +
				"	csh.type_cd as typeCd, csh.group_no as groupNo, csh.remarks as remarks, csh.dt_clear, csh.dt_mark " +
				"FROM cash_book csh LEFT OUTER JOIN vw_cash_book_sum sum ON csh.group_no = sum.group_no " +
				"WHERE csh.id_Bank=" + searchParamVO.getObj1().toString() + " AND status_cd = '" + BaseConstant.STATUS_ACTIVE + "'";
	}
	
	/*
	 * 
	 * @param listResultQuery
	 * @return
	 */
	private List<CashBookSumVO> manualCashBookSumVOMapping(List<Object[]> listResultQuery) {
		List<CashBookSumVO> listFinalResult = new ArrayList<CashBookSumVO>();
		for (Object[] objRow : listResultQuery) {
			listFinalResult.add(new CashBookSumVO(((Number)objRow[0]).longValue(), 
											((Number)objRow[1]).longValue(), 
											((Number)(objRow[2] == null ? 0.00 : objRow[2])).longValue(), 
											((Number)(objRow[3] == null ? 0.00 : objRow[3])).longValue(), 
											(Date) objRow[4],
											(String) objRow[5], 
											(String) objRow[6], 
											(String) objRow[7], (String) objRow[8],
											(String) objRow[9], (String) objRow[10], 
											(Double) objRow[11], (Double) objRow[12], (Double) objRow[13],
											(Boolean) objRow[14], (Boolean) objRow[15], 
											(String) objRow[16], (String) objRow[17],
											objRow[18] == null ? null : (String) objRow[18],
											objRow[19] == null ? null : (Date) objRow[19],
											objRow[20] == null ? null : (Date) objRow[20]));
		}
		return listFinalResult;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.BaseCommonDAOLMImpl#getLazyRecordList(java.lang.Object, int, int, java.lang.String, org.primefaces.model.SortOrder)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S> List<?> getLazyRecordList(S _commonObj, int _first, int _pageSize, String _sortField, SortOrder _sortOrder)
			throws BusinessException {
		CommonObject objResolver = (CommonObject)_commonObj;

		if(objResolver.getListSource(1).equals(MODULE.BANK.RECON)) {
			//Bank Reconciliation module using CUSTOMIZE query.
			String sqlQuery;
			if(objResolver.getListSource(2).equals(CommonConstant.CASHBOOK_NORMAL)) 
				sqlQuery = getCashBookListRecon((SearchParamVO)objResolver.getListSource(3));
			else
				sqlQuery = getPrevCashBookListRecon((SearchParamVO)objResolver.getListSource(3));

			Query query = CommonDAOHelper.addLazyDataModelFiltering(getSession(), sqlQuery, _first, _pageSize,  
														new String[]{"dtTrans", "id"}, 
														new SortOrder[] {SortOrder.ASCENDING, SortOrder.ASCENDING});
		
			return manualCashBookSumVOMapping(query.list());
		}
		
		//Default lazyload for cashbook
    	if(_sortField == null)	_sortField = "dtTrans";

		Criteria criteria = createCriteria((Class<?>)objResolver.getListSource(0));

		if(objResolver.getListSource(2).equals(CommonConstant.CASHBOOK_NORMAL)) 
			addFilter(criteria, (SearchParamVO)objResolver.getListSource(3), false);
		else
			addFilter(criteria, (SearchParamVO)objResolver.getListSource(3), true);
		
		criteria = CommonDAOHelper.addLazyDataModelFiltering(criteria, _first, _pageSize, _sortField, _sortOrder);
		criteria.addOrder(Order.asc("id"));
		
		return criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.BaseCommonDAOLMImpl#getRowCount(java.lang.Object)
	 */
	@Override
	public <S> int getRowCount(S _commonObj) throws BusinessException {
		CommonObject objResolver = (CommonObject)_commonObj;
		Criteria criteria = getSession().createCriteria((Class<?>)objResolver.getListSource(0));
		if(objResolver.getListSource(2).equals(CommonConstant.CASHBOOK_NORMAL)) 
			addFilter(criteria, (SearchParamVO)objResolver.getListSource(3), false);
		else addFilter(criteria, (SearchParamVO)objResolver.getListSource(3), true);
		return CommonDAOHelper.getUniqueCount(CommonDAOHelper.getRowCountFilter(criteria)).intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.BaseCommonDAOImpl#deleteRecordByID(java.util.List)
	 */
	@Override
	public <S> void deleteRecordByID(List<S> _listVO) throws BusinessException,
			SecurityException, IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.BaseCommonDAOImpl#getRecordByID(long, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <S> S getRecordByID(long _ID, Class<S> _VOClass) throws BusinessException {
		Criteria criteria = createCriteria(_VOClass);
		criteria.add(Restrictions.eq("id", _ID));
		return (S) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.BaseCommonDAOImpl#getRecordListByID(long, java.lang.Class)
	 */
	@Override
	public <S> List<S> getRecordListByID(long _ID, Class<S> _VOClass) throws BusinessException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.common.dao.BaseCommonDAOImpl#getRecordList(java.lang.Object)
	 */
	@Override
	public <S> List<?> getRecordList(S _commonObj) throws BusinessException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#updCashBookClear(java.lang.Object[], java.util.List)
	 */
	@Override
	public void updCashBookClear(List<CashBookSumVO> selectedCBList, List<CashBookSumVO> unselectedCBList) throws BusinessException {
		Query query;
		StringBuilder sb;
		if (CollectionUtils.isNotEmpty(selectedCBList)) {
			sb = new StringBuilder();
			sb.append("UPDATE cash_book SET is_clear = ?, dt_clear = ?, dt_upd = NOW(), upd_by = ? WHERE id IN (");
			for (int i = 0 ; i < selectedCBList.size() ; i++) {
				CashBookSumVO vo = selectedCBList.get(i);
				sb.append(vo.getId());
				if (i != selectedCBList.size() - 1) sb.append(", ");
			}
			sb.append(")");
			query = createSQLQuery(sb.toString());
			query.setBoolean(0, true);
			query.setDate(1, selectedCBList.get(0).getDtClear());
			query.setString(2, BaseContext.getUserFullName());
			query.executeUpdate();
		}
		
		if (CollectionUtils.isNotEmpty(unselectedCBList)) {
			sb = new StringBuilder();
			sb.append("UPDATE cash_book SET is_clear = ?, dt_clear = NULL, dt_upd = NOW(), upd_by = ? WHERE id IN (");
			for (int i = 0 ; i < unselectedCBList.size() ; i++) {
				CashBookSumVO vo = unselectedCBList.get(i);
				sb.append(vo.getId());
				if (i != unselectedCBList.size() - 1) sb.append(", ");
			}
			sb.append(")");
			query = createSQLQuery(sb.toString());
			query.setBoolean(0, false);
			query.setString(1, BaseContext.getUserFullName());
			query.executeUpdate();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getCashBookById(java.lang.Long)
	 */
	@Override
	public CashBookVO getCashBookById(Long id) throws BusinessException {
		Criteria criteria = createCriteria(CashBookVO.class);
		criteria.add(Restrictions.eq("id", id));
		return (CashBookVO) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.dao.CashBookDAO#getCashBookBySysNo(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CashBookVO getCashBookBySysNo(Long idCompany, String sysCode, String sysNo, String transTypeCd) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT").
			append(" c.id, c.id_bank, c.id_supplier, c.id_customer, c.dt_trans, c.sys_cd, c.sys_prefix, c.sys_no, c.ref_no, c.trans_type_cd, c.payee, c.remarks").
			append(" , c.debit, c.credit, c.is_clear, c.is_mark, c.dt_clear, c.dt_mark, c.type_cd, c.group_no, c.status_cd, c.dt_created, c.created_by, c.dt_upd, c.upd_by").
			append(", b.name, b.id_acct").
			append(" FROM cash_book c, bank b WHERE c.id_bank = b.id AND b.id_company = :idCompany AND c.sys_cd = :sysCode AND c.sys_no = :sysNo"); // AND c.status_cd = 'A'
		if (transTypeCd != null) sb.append(" AND c.trans_type_cd = :transTypeCd");
		
		Query query = createSQLQuery(sb.toString());
		query.setLong("idCompany", idCompany);
		query.setString("sysCode", sysCode);
		query.setString("sysNo", sysNo);
		if (transTypeCd != null) query.setString("transTypeCd", transTypeCd);
		
		CashBookVO vo = null;
		try {
			Object[] row = (Object[]) query.uniqueResult();
			vo = new CashBookVO();
			vo.setId(((Number) row[0]).longValue());
			vo.setIdBank(((Number) row[1]).longValue());
			vo.setIdSupplier(row[2] == null ? null : ((Number) row[2]).longValue());
			vo.setIdCustomer(row[3] == null ? null : ((Number) row[3]).longValue());
			vo.setDtTrans((Date) row[4]);
			vo.setSysCode((String) row[5]);
			vo.setSysPrefix((String) row[6]);
			vo.setSysNo((String) row[7]);
			vo.setRefNo(row[8] == null ? null : (String) row[8]);
			vo.setTransTypeCd((String) row[9]);
			vo.setPayee((String) row[10]);
			vo.setRemarks(row[11] == null ? null : (String) row[11]);
			vo.setDebit((Double) row[12]);
			vo.setCredit((Double) row[13]);
			vo.setIsClear((Boolean) row[14]);
			vo.setIsMark((Boolean) row[15]);
			vo.setDtClear(row[16] == null ? null : (Date) row[16]);
			vo.setDtMark(row[17] == null ? null : (Date) row[17]);
			vo.setTypeCd((String) row[18]);
			vo.setGroupNo(row[19] == null ? null : (String) row[19]);
			vo.setStatusCode((String) row[20]);
			vo.setCreatedDate((Date) row[21]);
			vo.setCreatedBy((String) row[22]);
			vo.setUpdatedDate((Date) row[23]);
			vo.setUpdatedBy((String) row[24]);
			vo.setTemp((String) row[25]);
			vo.setIdAcct(((Number) row[26]).longValue());
			
		} catch (Exception e) {
			// record not found
			//e.printStackTrace();
			return null;
		}
		return vo;
	}
	
	@Override
	public void updateCashBookEInvoiceData(Long idCashBook, String docUuid, String submissionUid, String status) throws BusinessException {
		String updatedBy = BaseContext.getUserFullName();
		if (StringUtils.isBlank(updatedBy)) updatedBy = BaseContext.getLoginId();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" update cash_book set ");
		if (StringUtils.isNotBlank(docUuid))		sb.append(" e_invoice_document_uuid = :docUuid, ");
		if (StringUtils.isNotBlank(submissionUid))	sb.append(" e_invoice_submission_uid = :submissionUid, ");
		if (StringUtils.isNotBlank(status))			sb.append(" e_invoice_status = :status, ");
		
		sb.append(" upd_by = :updatedBy, ");
		sb.append(" dt_upd = now() ");
		
		sb.append(" where id = :id ");
		
		Query query = createSQLQuery(sb.toString());
		if (StringUtils.isNotBlank(docUuid))		query.setParameter("docUuid", docUuid);
		if (StringUtils.isNotBlank(submissionUid))	query.setParameter("submissionUid", submissionUid);
		if (StringUtils.isNotBlank(status))			query.setParameter("status", status);
		query.setParameter("id", idCashBook);
		query.setParameter("updatedBy", updatedBy);
		query.executeUpdate();
	}
}
