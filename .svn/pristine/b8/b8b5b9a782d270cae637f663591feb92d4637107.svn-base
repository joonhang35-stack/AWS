package com.bcs.zsg.db.bterp.dao.tourdep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class TourDepDAOImpl extends BaseHibernateDAO implements TourDepDAO {

	private Map<String, String> filters;
	
	@SuppressWarnings({ "unchecked", "unused" })
	private StringBuilder genListFilter(StringBuilder _sb, Map<String, Object> params, String callerMethod) {
		filters = (Map<String, String>) params.get("filters");
		
		if (!filters.isEmpty()) {
			_sb.append(" AND (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				 _sb = filterListInvoiceHistoryView(it.next(), _sb);
				
				if (filters.size() > 1 && it.hasNext()) _sb.append(" AND ");
			}
			_sb.append(") ");
		}
		return _sb;
	}
	
	private StringBuilder filterListInvoiceHistoryView(Entry<String, String> entry, StringBuilder _sb) {
		return _sb;
	}

	@Override
	public List<TourDepartureVO> getTourDepCodeByInvoiceCategory() throws BusinessException {
		return getTourDepCodeByInvoiceCategory(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureVO> getTourDepCodeByInvoiceCategory(String category) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT DISTINCT (tdp.code) ")
			.append(" FROM tour_dep tdp INNER JOIN invoice inv ")
			.append("	ON inv.id_tour_dep = tdp.id")
			.append(StringUtils.isBlank(category) ? "" : " AND inv.cat_cd = '" + category + "' ")
			.append(" ORDER BY tdp.code ASC ");
		
		List<Object> queryResultList = createSQLQuery(sb.toString()).list();
		List<TourDepartureVO> returnResultList = new ArrayList<TourDepartureVO>();

		for (Iterator<Object> it = queryResultList.iterator() ; it.hasNext() ;) {
			TourDepartureVO vo = new TourDepartureVO();
			vo.setCode((String) it.next());

			returnResultList.add(vo);
		}
		
		return returnResultList;
	}
	
}
