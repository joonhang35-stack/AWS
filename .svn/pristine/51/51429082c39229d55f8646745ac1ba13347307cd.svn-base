package com.bcs.zsg.product.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.RoomingListVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.sales.vo.IdentityDetailVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonLangVO;
import com.bcs.zsg.sales.vo.PersonMealVO;

public class RoomingListDAOImpl  extends BaseHibernateDAO implements RoomingListDAO {

	public RoomingListVO getRoomingListByCategory(Long idTourDep, String strCategory) throws BusinessException {
		//Criteria criteria = createCriteria(RoomingListVO.class);
		Criteria criteria = getSession().createCriteria(RoomingListVO.class);
		criteria.add(Restrictions.eq("tourDepId", idTourDep));
		criteria.add(Restrictions.eq("category", strCategory)); 
		return (RoomingListVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomingListVO> getPICList(Long idTourDep) throws BusinessException {
		//Criteria criteria = createCriteria(RoomingListVO.class);
		Criteria criteria = getSession().createCriteria(RoomingListVO.class);
		criteria.add(Restrictions.eq("tourDepId", idTourDep));
		criteria.add(Restrictions.eq("category", ProductConstant.ROOMING_LIST_CAT_PIC)); 
		return (List<RoomingListVO>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomingListVO> getTourLeaderList(Long idTourDep) throws BusinessException {
		//Criteria criteria = createCriteria(RoomingListVO.class);
		Criteria criteria = getSession().createCriteria(RoomingListVO.class);
		criteria.add(Restrictions.eq("tourDepId", idTourDep));
		criteria.add(Restrictions.eq("category", ProductConstant.ROOMING_LIST_CAT_TOUR_LEADER)); 
		return (List<RoomingListVO>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomingListVO> getTourManagerList(Long idTourDep) throws BusinessException {
		//Criteria criteria = createCriteria(RoomingListVO.class);
		Criteria criteria = getSession().createCriteria(RoomingListVO.class);
		criteria.add(Restrictions.eq("tourDepId", idTourDep));
		criteria.add(Restrictions.eq("category", ProductConstant.ROOMING_LIST_CAT_TOUR_MANAGER));
		return (List<RoomingListVO>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoomingListVO> getIdHotelList(Long idTourDep) throws BusinessException {
		//Criteria criteria = createCriteria(RoomingListVO.class);
		Criteria criteria = getSession().createCriteria(RoomingListVO.class);
		criteria.add(Restrictions.eq("tourDepId", idTourDep));
		criteria.add(Restrictions.eq("category", ProductConstant.ROOMING_LIST_CAT_HOTEL));
		return (List<RoomingListVO>) criteria.list();
	}
	
	@Override
	public AddressVO getAddressVO(Long personId) throws BusinessException {
		Criteria criteria = getSession().createCriteria(AddressVO.class);
		criteria.add(Restrictions.eq("personId",personId));
		return (AddressVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonContactVO> getPersonContList(Long personId)
			throws BusinessException {
		Criteria criteria = getSession().createCriteria(PersonContactVO.class);
		criteria.add(Restrictions.eq("personId",personId));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceVO> getInvoiceList(Long tourDepId)
			throws BusinessException {
		Criteria criteria = getSession().createCriteria(InvoiceVO.class);
		criteria.add(Restrictions.eq("tourDepId",tourDepId));
		return criteria.list();
	}
	
	@Override
	public PersonVO getPerson(Long persCorpId) throws BusinessException {
		
		Criteria criteria = getSession().createCriteria(PersonVO.class);
		criteria.add(Restrictions.eq("Id",persCorpId));
		return (PersonVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InvoicePaxVO> getInvoicePax(Long invId) throws BusinessException {
		Criteria criteria = getSession().createCriteria(InvoicePaxVO.class);
		criteria.add(Restrictions.eq("invId",invId));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IdentityVO getPersonIdentity(Long id,String typeCd) throws BusinessException {
		
		Criteria criteria = getSession().createCriteria(IdentityVO.class);
		criteria.add(Restrictions.eq("personId",id));
		criteria.add(Restrictions.eq("idType",typeCd));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		List<IdentityVO> identityVOList = criteria.list();
		return CollectionUtils.isNotEmpty(identityVOList) ? identityVOList.get(0) : null;
	}
	@Override
	public IdentityDetailVO getPersonIdentityDetails(Long id)
			throws BusinessException {
		
		Criteria criteria = getSession().createCriteria(IdentityDetailVO.class);
		criteria.add(Restrictions.eq("personIdentityId",id));
		return (IdentityDetailVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonLangVO> getpersonLang(Long id) throws BusinessException {
		
		Criteria criteria = getSession().createCriteria(PersonLangVO.class);
		criteria.add(Restrictions.eq("personId",id));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonMealVO> getpersonMeal(Long id) throws BusinessException {
		Criteria criteria = getSession().createCriteria(PersonMealVO.class);
		criteria.add(Restrictions.eq("personId",id));
		return criteria.list();
	}
	
	@Override
	public CorporateVO getCorporateVO(Long personId) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CorporateVO.class);
		criteria.add(Restrictions.eq("personId",personId));
		return (CorporateVO) criteria.uniqueResult();
	}
	
	@Override
	public CorAddressVO getCorpAddrVO(Long id) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CorAddressVO.class);
		criteria.add(Restrictions.eq("Id",id));
		return (CorAddressVO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getuniqueList() throws BusinessException {
		Query query = createSQLQuery("SELECT DISTINCT li.remarks FROM lookup_item li where li.lookup_cat_cd='room_type'");
		List<Object> results = query.list();
		return results;
	}
}
