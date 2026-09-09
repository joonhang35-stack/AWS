package com.bcs.zsg.product.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class TourMICEDAOImpl extends BaseHibernateDAO implements TourMICEDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourPackageDAO#getTourMICEList(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TourDepartureViewVO> getTourMICEList(SearchParamVO searchParamVO, EmployeeVO employeeVO) throws BusinessException {
		Criteria criteria = createCriteria(TourPackageVO.class);
		criteria.add(Restrictions.eq("idTourCat", Long.parseLong((String) searchParamVO.getObj2())));
		criteria.add(Restrictions.eq("typeCd", ProductConstant.TYPE_MICE));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		if (employeeVO.getDepartment().contains("mice")) criteria.add(Restrictions.like("reserved3", "%mice%"));
		else criteria.add(Restrictions.like("reserved3", "%muslim%"));
		List<TourPackageVO> list = criteria.list();
		
		if (CollectionUtils.isNotEmpty(list)) {
			Criteria criteria2 = createCriteria(TourDepartureViewVO.class);
			criteria2.add(Restrictions.in("tourPkgVO", list));
			criteria2.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
			return criteria2.list();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourMICEDAO#getMICERegionList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RegionVO> getMICERegionList(String regions) throws BusinessException {
		String[] regionList = regions.split(",");
		Long[] idList = new Long[regionList.length];
		for (int i = 0 ; i < regionList.length ; i++) {
			idList[i] = Long.parseLong(regionList[i]);
		}
		
		Criteria criteria = createCriteria(RegionVO.class);
		criteria.add(Restrictions.in("id", idList));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourMICEDAO#getMICECountryList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CountryVO> getMICECountryList(String countries) throws BusinessException {
		String[] countryList = countries.split(",");
		Long[] idList = new Long[countryList.length];
		for (int i = 0 ; i < countryList.length ; i++) {
			idList[i] = Long.parseLong(countryList[i]);
		}
		
		Criteria criteria = createCriteria(CountryVO.class);
		criteria.add(Restrictions.in("id", idList));
		return criteria.list();
	}
}
