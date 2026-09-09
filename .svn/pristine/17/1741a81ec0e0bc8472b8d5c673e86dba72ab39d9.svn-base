package com.bcs.zsg.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCatViewVO;
import com.bcs.zsg.product.vo.TourThemeVO;

public class TourCatDAOImpl extends BaseHibernateDAO implements TourCatDAO {

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourCatDAO#getTourCatViewList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TourCatViewVO> getTourCatViewList(SearchParamVO searchParamVO) throws BusinessException {
		Criteria criteria = createCriteria(TourCatViewVO.class);
		if (!ProductConstant.TYPE_ALL.equals(searchParamVO.getObj1())) 
			criteria.add(Restrictions.eq("typeCd", searchParamVO.getObj1()));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		criteria.addOrder(Order.asc("desc"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.TourCatDAO#isTourCatUsed(java.lang.Long)
	 */
	@Override
	public boolean isTourCatUsed(Long idTourCat) throws BusinessException {
		Criteria criteria = createCriteria(TourThemeVO.class);
		criteria.add(Restrictions.eq("idTourCat", idTourCat));
		criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
		return (CollectionUtils.isEmpty(criteria.list())) ? false : true;
	}

}
