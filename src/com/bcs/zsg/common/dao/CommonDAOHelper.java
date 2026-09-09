package com.bcs.zsg.common.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.vo.RowCountVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;

public class CommonDAOHelper extends BaseHibernateDAO {

	private static CommonDAOHelper objCommonDAOHelper;
	public static final String ORDER_BY = " ORDER BY ";
	public static enum SORT_BY { ASC, DESC };
	
	private CommonDAOHelper(){
	}
	
	public static CommonDAOHelper getObject(){
		if(objCommonDAOHelper == null)
			objCommonDAOHelper = new CommonDAOHelper();
		return objCommonDAOHelper;
	}

	public static Long getRowCount(Criteria _criteria) throws BusinessException {
		return getUniqueCount(createClassCount(_criteria));
	}

	public static Criteria getRowCountFilter(Criteria _criteria) throws BusinessException {
		return createClassCount(_criteria);
	}

	public static Long getUniqueCount(Criteria _criteria) throws BusinessException {
		return (Long)((RowCountVO)_criteria.uniqueResult()).getRowCount();
	}
	
	private static Criteria createClassCount (Criteria _criteria) {
		return  _criteria.setProjection(Projections.projectionList()
		    .add(Projections.alias(Projections.rowCount(), "rowCount")))
		  .setResultTransformer(new AliasToBeanResultTransformer(RowCountVO.class));
	}
	
	public static Criteria addLazyDataModelFiltering(Criteria _criteria, int _first, int _pageSize, String _sortField, SortOrder _sortOrder) {

		 if (_sortField != null && !_sortField.isEmpty()) {
			 if (_sortOrder == SortOrder.ASCENDING)
				_criteria.addOrder(Order.asc(_sortField));
			 else
				 _criteria.addOrder(Order.desc(_sortField));
		 }
		 
		 return _criteria.setFirstResult(_first).setMaxResults(_pageSize);
	}

	public static Query addLazyDataModelFiltering(Session objSession, String _sqlQuery, int _first, int _pageSize, String _sortField, SortOrder _sortOrder) 
				throws BusinessException {
		return addLazyDataModelFiltering(objSession, _sqlQuery, _first, _pageSize, new String[]{_sortField}, new SortOrder[]{_sortOrder});
	}
	
	public static Query addLazyDataModelFiltering(Session objSession, String _sqlQuery, int _first, int _pageSize, String[] _sortField, SortOrder[] _sortOrder) 
				throws BusinessException {
		try {
			if (_sortField.length > 0) {
				if (_sortField.length != _sortOrder.length) 
					throw new Exception("Total sort field is not the same as the total sort order array.");
				_sqlQuery += ORDER_BY;
				
				for(int i=0; i < _sortField.length ; i++ ) {
					if (_sortField[i] != null && !_sortField[i].isEmpty()) {
						_sqlQuery +=  _sortField[i] + " " + (_sortOrder[i] == SortOrder.ASCENDING ? SORT_BY.ASC : SORT_BY.DESC);
						if((i+1) < _sortField.length)	_sqlQuery += ", ";
					}
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return objSession.createSQLQuery(_sqlQuery).setFirstResult(_first).setMaxResults(_pageSize);
	}

	public static Criteria addFilterDate(Criteria _criteria, Date _date, String _dateCol) {
		if(!(_date == null)) {
			_criteria.add(Restrictions.eq(_dateCol, _date));
		}
		return _criteria;
	}

	public static Criteria addFilterNumberIN(Criteria _criteria, List<String> _numberList, String _dateCol) {
		if(_numberList.size() > 0) {
			if(_numberList.size() == 1 && _numberList.contains(null) || _numberList.contains(""))
				return _criteria;
			_criteria.add(Restrictions.in(_dateCol, _numberList));
		}
		return _criteria;
	}
}
