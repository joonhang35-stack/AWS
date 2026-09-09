package com.bcs.zsg.sales.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.SurveyReportVO;
import com.bcs.zsg.sales.vo.SurveyVO;

public class SurveyReportDAOImpl extends BaseHibernateDAO implements SurveyReportDAO{
	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyVO> getSurveyReportList(Map<String, Object> params) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT l.description as description, IFNULL(t.sum_quantity, 0) as quantity ");
		sb.append("FROM lookup_item l ");
		sb.append("LEFT JOIN (select count(bs.id) as sum_quantity, bs.code from tour_booking_survey bs "); 
			sb.append("INNER JOIN tour_booking b ON b.id = bs.id_booking ");
			if (params.get("dateFrom") != null && params.get("dateTo") != null)
				sb.append("where DATE(b.dt_created) BETWEEN DATE('" + sdf.format((Date) params.get("dateFrom")) + "') AND DATE('" + sdf.format((Date) params.get("dateTo")) + "') ");
		sb.append("group by code) t on l.code = t.code ");
		sb.append("where l.lookup_cat_cd = 'survey_type'");
		
		SQLQuery query = (SQLQuery) createSQLQuery(sb.toString());
		query.addScalar("description");
		query.addScalar("quantity", IntegerType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(SurveyReportVO.class));
		return query.list();
	}
}
