package com.bcs.zsg.product.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.SortOrder;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.BaseContext;
import com.bcs.zsg.product.vo.CampaignCountryPackageVO;
import com.bcs.zsg.product.vo.CampaignCountryVO;
import com.bcs.zsg.product.vo.CampaignVO;
import com.bcs.zsg.product.vo.TourPackageVO;

public class CampaignDAOImpl extends BaseHibernateDAO implements CampaignDAO {
	
	@Override
	public List<CampaignVO> getCampaignList(Long idCompany) throws BusinessException {
		Criteria criteria = createCriteria(CampaignVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
	
	@Override
	public void terminateCampaignCountry(Long idCampaign) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE campaign_country SET status_cd = :statusCd");
		sb.append(", dt_upd = NOW(), upd_by = :updatedBy");
		sb.append(" WHERE id_campaign = :idCampaign");
		
		Query query = createSQLQuery(sb.toString());
		query.setString("statusCd", BaseConstant.STATUS_DELETED);
		query.setString("updatedBy", BaseContext.getUserFullName() == null ? BaseContext.getLoginId() : BaseContext.getUserFullName());
		query.setLong("idCampaign", idCampaign);
		query.executeUpdate();
	}
	
	@Override
	public void terminateCampaignCountryPkg(Long idCampaign) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE campaign_country_pkg p ");
		sb.append(" LEFT JOIN campaign_country c ON p.id_campaign_country = c.id ");
		sb.append(" SET p.status_cd = :statusCd ");
		sb.append(" , p.dt_upd = NOW(), p.upd_by = :updatedBy ");
		sb.append(" WHERE c.id_campaign = :idCampaign ");
		
		Query query = createSQLQuery(sb.toString());
		query.setString("statusCd", BaseConstant.STATUS_DELETED);
		query.setString("updatedBy", BaseContext.getUserFullName() == null ? BaseContext.getLoginId() : BaseContext.getUserFullName());
		query.setLong("idCampaign", idCampaign);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignCountryVO> getCampaignCountryList(Long idCampaign) throws BusinessException {
		Criteria criteria = createCriteria(CampaignCountryVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idCampaign", idCampaign));
		criteria.addOrder(Order.asc("seq"));
		criteria.addOrder(Order.asc("createdDate"));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CampaignCountryPackageVO> getCampaignCountryPkgList(Long idCampaignCountry) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ccp.id, ccp.id_campaign_country, ccp.id_tour_pkg, p.code, p.name_en, p.num_days, p.num_nights, p.year, ccp.seq,");
		sb.append(" ccp.status_cd, ccp.dt_created, ccp.created_by, ccp.dt_upd, ccp.upd_by, ");
		sb.append(" CONCAT(p.num_days, 'D/', p.num_nights, 'N ', COALESCE(p.name_en, ''), ' ', COALESCE(p.name_zh, ''), ' ', COALESCE(p.name_other, '')) as pkg_desc ");
		sb.append(" FROM campaign_country_pkg ccp ");
		sb.append(" LEFT JOIN tour_pkg p ON ccp.id_tour_pkg = p.id ");
		sb.append(" WHERE ccp.id_campaign_country = :idCampaignCountry ");
		sb.append(" AND ccp.status_cd = :statusCd ");
		sb.append(" ORDER BY CASE WHEN ccp.seq IS NULL THEN 1 ELSE 0 END ASC, ");
		sb.append(" ccp.seq ASC, ccp.dt_created ASC, p.code ASC ");
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("idCampaignCountry", idCampaignCountry);
		query.setParameter("statusCd", BaseConstant.STATUS_ACTIVE);
		
		List<Object> results = query.list();
		List<CampaignCountryPackageVO> ls = new ArrayList<CampaignCountryPackageVO>();

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			CampaignCountryPackageVO vo = new CampaignCountryPackageVO();
			
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setIdCampaignCountry(((BigInteger) row[1]).longValue());
			vo.setIdTourPkg(((BigInteger) row[2]).longValue());
			
			vo.setTourPkgVO(new TourPackageVO());
			vo.getTourPkgVO().setId(((BigInteger) row[2]).longValue());
			vo.getTourPkgVO().setCode((String) row[3]);
			vo.getTourPkgVO().setNameEn((String) row[4]);
			if (row[5] != null)	vo.getTourPkgVO().setNumDays(((Short) row[5]).intValue());
			if (row[6] != null)	vo.getTourPkgVO().setNumNights(((Short) row[6]).intValue());
			vo.getTourPkgVO().setYear((String) row[7]);
			if (row[8] != null) vo.setSeq(((Number) row[8]).intValue());
			vo.setStatusCode((String) row[9]);
			vo.setCreatedDate((Date) row[10]);
			vo.setCreatedBy((String) row[11]);
			vo.setUpdatedDate((Date) row[12]);
			vo.setUpdatedBy((String) row[13]);
			vo.getTourPkgVO().setCampaignPkgDesc((String) row[14]);

			ls.add(vo);
		}
		return ls;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getTourPkgListSize(Map<String, Object> params) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(p.id) ");
		sb.append(" from tour_pkg p ");
		sb.append(" where p.status_cd = :statusCode ");
		
		if (params.get("excludedPkgIdList") != null)	sb.append(" and p.id not in :excludedPkgIdList ");
		if (params.get("typeList") != null)	sb.append(" and p.type_cd in (:typeList) "); 
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("code".equals(entry.getKey())) sb.append("p.code like '%").append(entry.getValue()).append("%'");
				else if ("nameEn".equals(entry.getKey())) sb.append("CONCAT(p.num_days, 'D/', p.num_nights, 'N ', COALESCE(p.name_en, ''), ' ', COALESCE(p.name_zh, ''), ' ', COALESCE(p.name_other, '')) like '%").append(entry.getValue()).append("%'");
				else if ("campaignPkgDesc".equals(entry.getKey())) sb.append("CONCAT(p.num_days, 'D/', p.num_nights, 'N ', COALESCE(p.name_en, ''), ' ', COALESCE(p.name_zh, ''), ' ', COALESCE(p.name_other, '')) like '%").append(entry.getValue()).append("%'");
				else if ("year".equals(entry.getKey())) sb.append("p.year like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(")");
		}
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("statusCode", CommonConstant.STATUS_CD_ACTIVE);
		
		if (params.get("excludedPkgIdList") != null)	query.setParameterList("excludedPkgIdList", (List<Long>) params.get("excludedPkgIdList"));
		if (params.get("typeList") != null)  query.setParameterList("typeList", (List<String>) params.get("typeList"));
		
		return ((BigInteger) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourPackageVO> getTourPkgList(Map<String, Object> params) throws BusinessException {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select p.id, p.code, p.name_en, p.name_zh, p.name_other, p.name_other_zh, p.num_days, p.num_nights, p.year, ");
		sb.append(" CONCAT(p.num_days, 'D/', p.num_nights, 'N ', COALESCE(p.name_en, ''), ' ', COALESCE(p.name_zh, ''), ' ', COALESCE(p.name_other, '')) as pkg_desc ");
		sb.append(" from tour_pkg p ");
		sb.append(" where p.status_cd = :statusCode ");
		
		if (params.get("excludedPkgIdList") != null)	sb.append(" and p.id not in :excludedPkgIdList ");
		if (params.get("typeList") != null)	sb.append(" and p.type_cd in (:typeList) "); 
		
		Map<String, String> filters = (Map<String, String>) params.get("filters");
		if (!filters.isEmpty()) {
			sb.append("and (");
			for (Iterator<Entry<String, String>> it = filters.entrySet().iterator() ; it.hasNext() ;) {
				Entry<String, String> entry = it.next();
				if ("code".equals(entry.getKey())) sb.append("p.code like '%").append(entry.getValue()).append("%'");
				else if ("nameEn".equals(entry.getKey())) sb.append("CONCAT(p.num_days, 'D/', p.num_nights, 'N ', COALESCE(p.name_en, ''), ' ', COALESCE(p.name_zh, ''), ' ', COALESCE(p.name_other, '')) like '%").append(entry.getValue()).append("%'");
				else if ("campaignPkgDesc".equals(entry.getKey())) sb.append("CONCAT(p.num_days, 'D/', p.num_nights, 'N ', COALESCE(p.name_en, ''), ' ', COALESCE(p.name_zh, ''), ' ', COALESCE(p.name_other, '')) like '%").append(entry.getValue()).append("%'");
				else if ("year".equals(entry.getKey())) sb.append("p.year like '%").append(entry.getValue()).append("%'");
				if (filters.size() > 1 && it.hasNext()) sb.append(" and ");
			}
			sb.append(")");
		}
	
		sb.append(" order by ");
		String sortField = (String) params.get("sortField");
		
		if (sortField == null) {
			sb.append(" p.id desc ");
		}
		else {
			if ("code".equals(sortField)) sb.append("p.code");
			else if ("year".equals(sortField)) sb.append("p.year");
			
			if (CommonConstant.SORT_ASC.equals(((SortOrder) params.get("sortOrder")).toString())) sb.append(" asc ");
			else sb.append(" desc ");
		}
		
//		System.out.println("CampaignDAOImpl.getTourPkgList()");
//		System.out.println(sb.toString());
		
		Query query = createSQLQuery(sb.toString());
		query.setParameter("statusCode", CommonConstant.STATUS_CD_ACTIVE);
		
		if (params.get("excludedPkgIdList") != null)	query.setParameterList("excludedPkgIdList", (List<Long>) params.get("excludedPkgIdList"));
		if (params.get("first") != null && (Integer) params.get("first") >= 0)	query.setFirstResult((Integer) params.get("first"));
		if (params.get("pageSize") != null && (Integer) params.get("pageSize") >= 0)	query.setMaxResults((Integer) params.get("pageSize"));
		if (params.get("typeList") != null)  query.setParameterList("typeList", (List<String>) params.get("typeList"));
		List<Object> results = query.list();
		List<TourPackageVO> tourPkgList = new ArrayList<TourPackageVO>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			TourPackageVO vo = new TourPackageVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setCode((String) row[1]);
			vo.setNameEn((String) row[2]);
			vo.setNameZh((String) row[3]);
			vo.setNameOther((String) row[4]);
			vo.setNameOtherZh((String) row[5]);
			if (row[6] != null)	vo.setNumDays(((Short) row[6]).intValue());
			if (row[7] != null)	vo.setNumNights(((Short) row[7]).intValue());
			vo.setYear((String) row[8]);
			vo.setCampaignPkgDesc((String) row[9]);
			
			tourPkgList.add(vo);
		}
		return tourPkgList;
	}
	
	@Override
	public List<CampaignVO> getCampaignList(Long idCompany, Date dtFrom, Date dtTo) throws BusinessException {
		Criteria criteria = createCriteria(CampaignVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("idCompany", idCompany));
		if (dtFrom != null)	criteria.add(Restrictions.ge("startDt", dtFrom));
		if (dtTo != null)	criteria.add(Restrictions.le("endDt", dtTo));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
}
