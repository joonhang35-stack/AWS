package com.bcs.zsg.maintenance.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class AccountCodeDAOImpl extends BaseHibernateDAO implements AccountCodeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepItemVO> getTourDepItemVOList(Long tourDepId) throws BusinessException { 
		Criteria criteria=createCriteria(TourDepItemVO.class);

		criteria.add(Restrictions.eq("id_tour_dep", tourDepId));
		criteria.add(Restrictions.ne("amount", 0.00));
		criteria.addOrder(Order.asc("id")); 
		return criteria.list();
	}

	public AcctVO getAccountVO(Long idAcct) throws BusinessException  { 


		Criteria criteria = createCriteria(AcctVO.class);
		criteria.add(Restrictions.eq("id", idAcct));
		criteria.setMaxResults(1);
		return (AcctVO) criteria.uniqueResult();
		 
	}

	public AcctVO getAccountVObyAccCodeSubCode(Long idCompany, String strTemp) throws BusinessException  { 
		String tempStrAccCode = "";
		String tempStrAccSubCode = "";
		Criteria criteria = createCriteria(AcctVO.class);
		
		if(strTemp != null) {
			if(strTemp.indexOf("-") >0) {
				 tempStrAccCode = strTemp.substring(0,strTemp.indexOf( "-" )); 
				 tempStrAccSubCode = strTemp.substring(strTemp.indexOf( "-" )+1,strTemp.length()); 
			     criteria.add(Restrictions.eq("subCode", tempStrAccSubCode));
			} else {
				tempStrAccCode = strTemp;
			}
		}
		criteria.add(Restrictions.eq("code", tempStrAccCode));
		criteria.add(Restrictions.eq("idCompany", idCompany));
		criteria.setMaxResults(1);
		return (AcctVO) criteria.uniqueResult();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourPackageDAO#getTourDepById(java.lang.Long)
	 */
	@Override
	public TourDepartureVO getTourDepById(Long idTourDep) throws BusinessException {
		Criteria criteria = createCriteria(TourDepartureVO.class);
		criteria.add(Restrictions.eq("id", idTourDep)); 
		criteria.setMaxResults(1);
		return (TourDepartureVO) criteria.uniqueResult();
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourPackageDAO#getTourDepById(java.lang.Long)
	 */
	@Override
	public TourPackageVO getTourPkgById(Long idTourPkg) throws BusinessException {
		Criteria criteria = createCriteria(TourPackageVO.class);
		criteria.add(Restrictions.eq("id", idTourPkg)); 
		criteria.setMaxResults(1);
		return (TourPackageVO) criteria.uniqueResult();
	}

	@Override
	public TourThemeVO getTourThemeById(Long idTourTheme) throws BusinessException {
		Criteria criteria = createCriteria(TourThemeVO.class);
		criteria.add(Restrictions.eq("id", idTourTheme)); 
		criteria.setMaxResults(1);
		return (TourThemeVO) criteria.uniqueResult();
	}
	
	@Override
	public CountryVO getCountryById(Long idCountry) throws BusinessException {
		Criteria criteria = createCriteria(CountryVO.class);
		criteria.add(Restrictions.eq("id", idCountry)); 
		criteria.setMaxResults(1);
		return (CountryVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CountryVO> getCountryList() throws BusinessException {
		Criteria criteria = createCriteria(CountryVO.class); 
		criteria.addOrder(Order.asc("id"));  
		return (List<CountryVO>) criteria.list();
		//return (List<CountryVO>) criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.dao.CompanyDAO#getCompanyList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyVO> getCompanyList(String secUser) throws BusinessException {
		Query query = createSQLQuery("SELECT id_company FROM employee WHERE u_sec_user = :secUser");
		query.setString("secUser", secUser);
		List<Object> results = query.list();
		List<Long> companyIdList = new ArrayList<Long>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			BigInteger id = (BigInteger) it.next();
			companyIdList.add(id.longValue());
		}
		
		if (CollectionUtils.isNotEmpty(companyIdList)) {
			Criteria criteria = createCriteria(CompanyVO.class);
			criteria.add(Restrictions.in("id", companyIdList));
			criteria.addOrder(Order.asc("name"));
			return criteria.list();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepItemVO> getTourDepItemNotComplete(Long idTourDep) throws BusinessException {
		//Query query = createSQLQuery("SELECT * FROM tour_dep_item where id_tour_dep = '" + idTourDep + "' and amount != 0 and id_acct is null"); 
		Query query = createSQLQuery("SELECT * FROM tour_dep_item where id_tour_dep = '" + idTourDep + "' and id_acct is null"); 
		List<Object> results = query.list();
 
		List<TourDepItemVO> tourDepItemVOList = new ArrayList<TourDepItemVO>();
		
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			TourDepItemVO tourDepItemVO = new TourDepItemVO();
			tourDepItemVO.setId(((BigInteger) row[0]).longValue());
			tourDepItemVO.setIdCompany(((BigInteger) row[1]).longValue()); 
			tourDepItemVOList.add(tourDepItemVO);
		}

		return tourDepItemVOList;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.maintenance.dao.AccountCodeDAOImpl#getTourDepList(java.lang.Long)
	 */
	@Override
	public List<TourDepartureVO> getTourDepList(Long idTourPkg) throws BusinessException {
		return getTourDepListWithOrder(idTourPkg,"");
		/*
		Criteria criteria = createCriteria(TourDepartureVO.class);
		criteria.add(Restrictions.gt("dtDep", new Date()));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		if (idTourPkg != null) criteria.add(Restrictions.eq("idTourPkg", idTourPkg));
		criteria.addOrder(Order.asc("dtDep"));
		criteria.addOrder(Order.asc("code"));
		return criteria.list();
		*/
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureVO> getTourDepListWithOrder(Long idTourPkg,String orderBy) throws BusinessException {
		Criteria criteria = createCriteria(TourDepartureVO.class);
		criteria.add(Restrictions.gt("dtDep", new Date()));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		if (idTourPkg != null) criteria.add(Restrictions.eq("idTourPkg", idTourPkg));
		
		if(!orderBy.equals(""))
		{
			criteria.addOrder(Order.asc(orderBy));
		}
		criteria.addOrder(Order.asc("dtDep"));
		criteria.addOrder(Order.asc("code"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.dao.CompanyDAO#getAccCodeSubCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureVO> getTourDepListWithItem() throws BusinessException {
		String sql = "select d.id,d.id_tour_pkg,d.id_dpt,d.dt_dep,d.code,(SELECT DISTINCT id_tour_dep FROM tour_dep_item where id_tour_dep = d.id and tour_dep_item.id_acct is null) as A2,ac2.code as visaAcct,ac3.code as trvAcct,ac4.code as tourAcc,ac4.description as tourDesc,";
		sql += "pkg.name_en , pkg.name_zh, ";
		sql += "(select GROUP_CONCAT(c.name SEPARATOR ', ') from tour_theme_country ttc, country c where ttc.id_country = c.id and ttc.id_tour_theme = pkg.id_tour_theme) as country, ";
		sql += "tc.description, ";
		sql += "(SELECT distinct d.id FROM tour_dep_item where id_tour_dep = d.id and id_acct is null) as notComplete ";
		sql += ",ac4.sub_code,ac4.sub_description,ac2.sub_code as visaSubAcct,ac3.sub_code as trvSubAcct ";
		sql += "from tour_dep d inner join tour_dep_item di2 on di2.id_tour_dep = d.id and di2.code='VISA' inner join tour_dep_item di3 on di3.id_tour_dep = d.id and di3.code='TRVL_INS' inner join tour_dep_item di4 on  di4.id_tour_dep = d.id and di4.code='FT_SGL' left join  account ac2 on ac2.id=di2.id_acct left join account ac3 on ac3.id=di3.id_acct left join account ac4 on ac4.id=di4.id_acct ";
		sql += "left join tour_pkg pkg on pkg.id = d.id_tour_pkg left join tour_theme t on t.id = pkg.id_tour_theme ";
		sql +="left join tour_cat tc on tc.id = t.id_tour_cat ";
		sql += "where d.status_cd = 'AC' and d.id in (select distinct id_tour_dep from tour_dep_item) ";
		sql += "group by d.code, d.dt_dep order by A2 desc ,d.dt_dep, d.code ";
		Query query = createSQLQuery(sql);
				
		List<Object> results = query.list();
		List<TourDepartureVO> tourDepList = new ArrayList<TourDepartureVO>();
		TourDepartureVO vo;
		String temp = "";
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo = new TourDepartureVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setIdTourPkg(((BigInteger) row[1]).longValue());  
			vo.setIdDepartment((String) row[2]);
			vo.setDtDep((Date) row[3]);  
			vo.setCode((String) row[4]);
			vo.setVisaAcc((String) row[6]);
			if(row[17] != null && StringUtils.isNotEmpty((String) row[17])) vo.setVisaAcc(vo.getVisaAcc() + "-" + (String) row[17]);
			vo.setInsurAcc((String) row[7]); 
			if(row[18] != null && StringUtils.isNotEmpty((String) row[18])) vo.setInsurAcc(vo.getInsurAcc() + "-" + (String) row[18]);
			temp = "";
//			if(row[8] != null) temp = (String)row[8];
//			if(row[9] != null) temp += " / " + (String)row[9];
			
			if (row[15] != null && StringUtils.isNotEmpty((String) row[15])) {
				temp += (String)row[8] + "-" + (String)row[15];
			} else {
				if(row[8] != null) temp += (String)row[8];
			}
			
			if (row[16] != null && StringUtils.isNotEmpty((String) row[16])) {
				temp += " / " + (String)row[9] + " - " + (String)row[16];
			} else {
				if(row[9] != null) temp += " / " + (String)row[9];
			}
			
			vo.setTourAcc(temp); 
			vo.setPackageName((String) row[10] + " " + (String) row[11]);
			vo.setCountryName((String) row[12]);
			vo.setTourCategoryName((String) row[13]);
			if(row[14] != null) {
				vo.setAccCodeStatusCd("N");
			} else {
				vo.setAccCodeStatusCd("Y");
			}
			tourDepList.add(vo);
		}
		return tourDepList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureVO> getTourDepListAccountCode(SearchParamVO searchParamVO) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select ").
			append("d.id, d.id_tour_pkg, d.code as 'Tour Code', d.dt_dep as 'Dep Date', ").
			append("tc.description as 'Tour Category', ").
			append("(select GROUP_CONCAT(c.name SEPARATOR ', ') from tour_theme_country ttc, country c where ttc.id_country = c.id and ttc.id_tour_theme = pkg.id_tour_theme) as country, ").
			append("pkg.name_en as 'Package Name Eng', pkg.name_zh as 'Package Name Chinese', ").
			append("ac4.code as 'Account Code', ac4.description as 'Account Desc', d.status_cd as 'Tour Status', ").
			append("ac2.code as visaAcct, ac3.code as trvAcct, ").
			append("(SELECT distinct d.id FROM tour_dep_item where id_tour_dep = d.id and id_acct is null) as notComplete ").
			append(",ac4.sub_code,ac4.sub_description,ac2.sub_code as visaSubAcct,ac3.sub_code as trvSubAcct ").
		append("from tour_dep d ").
			append("inner join tour_dep_item di2 on di2.id_tour_dep = d.id and di2.code='VISA' ").
			append("inner join tour_dep_item di3 on di3.id_tour_dep = d.id and di3.code='TRVL_INS' ").
			append("inner join tour_dep_item di4 on di4.id_tour_dep = d.id and di4.code = 'FT_SGL' ").
			append("left join account ac2 on ac2.id = di2.id_acct ").
			append("left join account ac3 on ac3.id = di3.id_acct ").
			append("left join account ac4 on ac4.id = di4.id_acct ").
			append("left join tour_pkg pkg on pkg.id = d.id_tour_pkg ").
			append("left join tour_theme t on t.id = pkg.id_tour_theme ").
//			append("left join country c on c.id = t.id_country ").
			append("left join tour_cat tc on tc.id = t.id_tour_cat ").
		append("where d.id in (select distinct id_tour_dep from tour_dep_item) ");
		
		if (searchParamVO.getFromDate() != null)
			sb.append("and DATE(d.dt_dep) >= DATE(:dtFrom) ");
		if (searchParamVO.getToDate() != null)
			sb.append("and DATE(d.dt_dep) <= DATE(:dtTo) ");
		
		List<String> yearList = new ArrayList<String>();
		
		if (searchParamVO.getObj3() != null) yearList = (List<String>) searchParamVO.getObj3();
		
		if (CollectionUtils.isNotEmpty(yearList)) {
			sb.append("and year(d.dt_dep) in (");
			for (int i = 0; i < yearList.size(); i++) {
				if (i > 0) sb.append(",");
				sb.append("'").append(yearList.get(i)).append("'");
			}
			sb.append(") ");
		}
		
		if(searchParamVO.getObj2() != null) {
			if ("true".equals(searchParamVO.getObj2())) {
			} else {
				sb.append("and d.status_cd = 'AC' ");
			}
		} else {
			sb.append("and d.status_cd = 'AC' ");
		}
		
		sb.append("group by d.code, d.dt_dep ").
		append("order by d.dt_dep, d.code ");
		
		Query query = createSQLQuery(sb.toString());
		if (searchParamVO.getFromDate() != null) query.setParameter("dtFrom", (Date) searchParamVO.getFromDate());
		if (searchParamVO.getToDate() != null) query.setParameter("dtTo", (Date) searchParamVO.getToDate());
				
		List<Object> results = query.list();
		List<TourDepartureVO> tourDepList = new ArrayList<TourDepartureVO>();
		TourDepartureVO vo;
		String temp = "";
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo = new TourDepartureVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setIdTourPkg(((BigInteger) row[1]).longValue());
			vo.setCode((String) row[2]);
			vo.setDtDep((Date) row[3]);
			vo.setTourCategoryName((String) row[4]);
			vo.setCountryName((String) row[5]);
			vo.setPackageName((String) row[6] + " " + (String) row[7]);
			vo.setNameEn((String) row[6]);
			vo.setNameZh((String) row[7]);
			temp = "";
//			if(row[8] != null) temp = (String)row[8];
//			if(row[9] != null) temp += " / " + (String)row[9];
			if (row[14] != null && StringUtils.isNotEmpty((String) row[14])) {
				temp += (String)row[8] + "-" + (String)row[14];
			} else {
				if(row[8] != null) temp += (String)row[8];
			}
			
			if (row[15] != null && StringUtils.isNotEmpty((String) row[15])) {
				temp += " / " + (String)row[9] + " - " + (String)row[15];
			} else {
				if(row[9] != null) temp += " / " + (String)row[9];
			}
			vo.setTourAcc(temp); 
			vo.setStatusCode((String) row[10]);
			vo.setVisaAcc((String) row[11]);
			if(row[16] != null && StringUtils.isNotEmpty((String) row[16])) vo.setVisaAcc(vo.getVisaAcc() + "-" + (String) row[16]);
			vo.setInsurAcc((String) row[12]); 
			if(row[17] != null && StringUtils.isNotEmpty((String) row[17])) vo.setInsurAcc(vo.getInsurAcc() + "-" + (String) row[17]);
			if(row[13] != null) {
				vo.setAccCodeStatusCd(LookupItemUtils.getLookupItemDesc("acc_code_status", "N"));
			} else {
				vo.setAccCodeStatusCd(LookupItemUtils.getLookupItemDesc("acc_code_status", "Y"));
			}
			vo.setStatusCode((String) row[10]);
			if ("AC".equals(vo.getStatusCode())) {
				vo.setTourStatusDesc("Active");
			} else {
				vo.setTourStatusDesc("Inactive");
			}
			
			tourDepList.add(vo);
		}
		return tourDepList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureVO> getTourDepCruiseListWithItem() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		sb.append("select d.id, d.id_tour_pkg, d.dt_dep, d.code, p.name_en, p.name_zh, tc.description,").
			append(" (SELECT distinct id_tour_dep FROM tour_dep_item where id_tour_dep = d.id and id_acct is null) as notComplete,").
			append(" (SELECT distinct id_tour_dep FROM tour_cruise_cabin where status_cd = 'A' and id_tour_dep = d.id and id_acct is null) as notCompleteCabin").
			append(" from tour_dep d, tour_pkg p, tour_theme t, tour_cat tc").
			append(" where d.id_tour_pkg = p.id and p.type_cd = 'CRUISE' and d.status_cd = 'AC'").
			append(" and p.id_tour_theme = t.id and t.id_tour_cat = tc.id").
			append(" order by notComplete is null, notCompleteCabin, d.dt_dep, d.code");
		
		Query query = createSQLQuery(sb.toString());
		List<Object> results = query.list();
		List<TourDepartureVO> tourDepList = new ArrayList<TourDepartureVO>();
		TourDepartureVO vo;

		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			Object[] row = (Object[]) it.next();
			vo = new TourDepartureVO();
			vo.setId(((BigInteger) row[0]).longValue());
			vo.setIdTourPkg(((BigInteger) row[1]).longValue());  
			vo.setDtDep((Date) row[2]);  
			vo.setCode((String) row[3]);
			vo.setPackageName((String) row[4] + " " + (String) row[5]);
			vo.setTourCategoryName((String) row[6]);
			if(row[7] != null || row[8] != null) {
				vo.setAccCodeStatusCd("N");
			} else {
				vo.setAccCodeStatusCd("Y");
			}
			tourDepList.add(vo);
		}
		return tourDepList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.dao.CompanyDAO#getAccCodeSubCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AcctVO> getAccCodeSubCode(String strAutoComplete,String companyId) throws BusinessException {
		Query query = createSQLQuery("SELECT id FROM account where CONCAT(code,'-',sub_code) like '" + strAutoComplete + "%' and id_acct_cat='4' and id_company='" +companyId + "'");
		//query.setString("strAutoComplete", strAutoComplete);
		List<Object> results = query.list();
		List<Long> acctIdList = new ArrayList<Long>();
		for (Iterator<Object> it = results.iterator() ; it.hasNext() ;) {
			BigInteger id = (BigInteger) it.next();
			acctIdList.add(id.longValue());
		}
		
		if (CollectionUtils.isNotEmpty(acctIdList)) {
			Criteria criteria = createCriteria(AcctVO.class);
			criteria.add(Restrictions.in("id", acctIdList));  
			return criteria.list();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.company.dao.CompanyDAO#getAccCodeSubCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<AcctVO> getAccCodeSubCodeList(int id_acct_cat) throws BusinessException {
		Criteria criteria=createCriteria(AcctVO.class); 
		criteria.add(Restrictions.eq("idAcctCat", Long.parseLong(String.valueOf(id_acct_cat))));
		criteria.addOrder(Order.asc("id")); 
		return criteria.list();
	}
	
	
	/****
	 * Account Code Config
	 */
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AccountCodeConfigVO> getAccountCodeConfigList() throws BusinessException {
		Criteria criteria=createCriteria(AccountCodeConfigVO.class); 
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AccountCodeConfigVO> getAccountCodeConfigList(String typeCode) throws BusinessException {
		Criteria criteria = createCriteria(AccountCodeConfigVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.add(Restrictions.eq("typeCode", typeCode));
		return criteria.list();
	}

	@Override
	public AccountCodeConfigVO getAccountCodeConfig(Map<String, Object> params) throws BusinessException {
		Criteria criteria = createCriteria(AccountCodeConfigVO.class);
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		if (!params.isEmpty()) {
			for (Entry<String, Object> entry : params.entrySet()) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		return (AccountCodeConfigVO) criteria.uniqueResult();
	}
	
}