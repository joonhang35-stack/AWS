package com.bcs.zsg.crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CRMCommonConstant;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.crm.vo.CRMAddressVO;
import com.bcs.zsg.crm.vo.CRMContactVO;
import com.bcs.zsg.crm.vo.CRMEmailVO;
import com.bcs.zsg.crm.vo.CRMLangVO;
import com.bcs.zsg.crm.vo.CustomerPosInfoVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.sales.dao.CustomerDAO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonEmailVO;
import com.bcs.zsg.sales.vo.PersonLangVO;

public class CustomerPosInfoDAOImpl extends BaseHibernateDAO implements CustomerPosInfoDAO {
	@Autowired
	private CustomerDAO customerDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public CustomerPosInfoVO getCustomerPosInfo(CustomerVO customerVO) throws BusinessException {

	    Criteria criteria = createCriteria(CustomerPosInfoVO.class);
	    criteria.add(Restrictions.eq("idCustomer", customerVO.getId()));
	    criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
	    criteria.setMaxResults(1);

	    CustomerPosInfoVO vo = (CustomerPosInfoVO) criteria.uniqueResult();
	    CustomerVO fullCustomerVO = customerDAO.getCustomerDetail(vo.getIdCustomer());

	    if (fullCustomerVO != null && fullCustomerVO.getPersonVO() != null) {
	        vo.setCustomerNo(fullCustomerVO.getCode());
	        vo.setFirstName(fullCustomerVO.getPersonVO().getGivenName());
	        vo.setLastName(fullCustomerVO.getPersonVO().getLastName());
	        vo.setNickName(fullCustomerVO.getPersonVO().getNickName());
	        vo.setGender(fullCustomerVO.getPersonVO().getSex());
	        vo.setRace(fullCustomerVO.getPersonVO().getRace());
	        vo.setDob(fullCustomerVO.getPersonVO().getDob());
	        vo.setSalutation(fullCustomerVO.getPersonVO().getSalutation());
	        if (StringUtils.isNotBlank(fullCustomerVO.getCrmId())) {
	            vo.setCrmId(fullCustomerVO.getCrmId());
	        }
	        List<CRMContactVO> contactList = new ArrayList<>();
	        if (fullCustomerVO.getPersonContactList() != null) {
	            for (PersonContactVO pcVO : fullCustomerVO.getPersonContactList()) {
	            	if (CommonConstant.LOOKUP_ITM_CNTC_TYPE_MOBILE.equalsIgnoreCase(pcVO.getTypeCd())) {
		                CRMContactVO contactVO = new CRMContactVO();
		                contactVO.setType(pcVO.getTypeCd());
		                contactVO.setNumber(pcVO.getNumber());
		                contactVO.setStatus(pcVO.getStatusCode());
		                contactVO.setCountryCode(pcVO.getCountryCd());
		                contactVO.setIdCountry(pcVO.getIdCountryCd());
		                contactList.add(contactVO);
	            	}
	            }
	        }
	        vo.setContacts(contactList);
	        List<CRMLangVO> langList = new ArrayList<>();
	        if (fullCustomerVO.getPersonLangList() != null) {
		        for (PersonLangVO langVO : fullCustomerVO.getPersonLangList()) {
		        	CRMLangVO crmLangVO = new CRMLangVO();
		            crmLangVO.setType(langVO.getLangCd());
		            langList.add(crmLangVO);
		        }
	        }
		    vo.setSpokenLanguage(langList);
	        List<CRMAddressVO> addressList = new ArrayList<>();
	        if (fullCustomerVO.getAddressList() != null) {
	            for (AddressVO addVO : fullCustomerVO.getAddressList()) {
	                CRMAddressVO crmAddVO = new CRMAddressVO();
		            crmAddVO.setAddr1(StringUtils.isBlank(addVO.getAddr1()) ? "none" : addVO.getAddr1());
		            crmAddVO.setAddr2(addVO.getAddr2());
		            crmAddVO.setAddr3(addVO.getAddr3());
		            crmAddVO.setCity(addVO.getCity());
		            crmAddVO.setState(addVO.getState());
		            crmAddVO.setPostcode(addVO.getPostcode());
	                crmAddVO.setCountryId(addVO.getCountryId());
	                addressList.add(crmAddVO);
	            }
	        }
	        vo.setAddresses(addressList);
	        List<CRMEmailVO> emailList = new ArrayList<>();
	        List<PersonEmailVO> personEmailList = createCriteria(PersonEmailVO.class).add(Restrictions.eq("idPerson", fullCustomerVO.getPersonVO().getId())).add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE)).list();
	        if (personEmailList != null) {
	            for (PersonEmailVO peVO : personEmailList) {
	                CRMEmailVO emailVO = new CRMEmailVO();
	                emailVO.setEmail(peVO.getEmail());
	                emailVO.setIsPrimary(peVO.getIsPrimary() ? 1 : 0);
	                emailVO.setStatus(peVO.getStatusCode());
	                emailList.add(emailVO);
	            }
	        }
	        vo.setEmails(emailList);
	        if (fullCustomerVO.getIdentityList() != null) {
	            for (IdentityVO identityVO : fullCustomerVO.getIdentityList()) {
	                if (CommonConstant.LOOKUP_ITM_ID_NRIC.equals(identityVO.getIdType())) {
	                    vo.setNric(identityVO.getIdNo());
	                }
	                if (CommonConstant.LOOKUP_ITM_ID_PASSPRT.equals(identityVO.getIdType())) {
	                    vo.setPassportNo(identityVO.getIdNo());
	                }
	            }
	        }
	    }
	    return vo;
	}
	
	@Override
	public List<CustomerPosInfoVO> getCustomerPosInfoList() throws BusinessException {
		Criteria criteria = createCriteria(CustomerPosInfoVO.class);
	    criteria.add(Restrictions.eq("statusCode", CommonConstant.STATUS_CD_ACTIVE));
	    criteria.add(Restrictions.eq("postingStatus", CRMCommonConstant.CRM_INV_POSTING_PENDING));
	    criteria.setMaxResults(1000);
	    List<CustomerPosInfoVO> ls = criteria.list();
	    for (CustomerPosInfoVO vo : ls) {
	        CustomerVO customerVO = customerDAO.getCustomerDetail(vo.getIdCustomer());
	        List<CRMEmailVO> emailList = new ArrayList<CRMEmailVO>();
	        List<CRMContactVO> contactList = new ArrayList<CRMContactVO>();
	        List<CRMAddressVO> addressList = new ArrayList<CRMAddressVO>();
	        List<CRMLangVO> langList = new ArrayList<CRMLangVO>();
	        vo.setCustomerNo(customerVO.getCode());
	        vo.setStatus(CommonConstant.STATUS_CD_ACTIVE);     
	        vo.setTotalCount(ls.size());
	        if (StringUtils.isNotBlank(customerVO.getCrmId())) {
	            vo.setCrmId(customerVO.getCrmId());
	        }
	        List<PersonEmailVO> personEmailList = createCriteria(PersonEmailVO.class).add(Restrictions.eq("idPerson", customerVO.getPersonVO().getId())).add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE)).list();
	        if (customerVO.getPersonVO() != null) {
	        	vo.setFirstName(customerVO.getPersonVO().getGivenName());
		        vo.setLastName(customerVO.getPersonVO().getLastName());
		        vo.setNickName(customerVO.getPersonVO().getNickName());
		        vo.setGender(customerVO.getPersonVO().getSex());
		        vo.setRace(customerVO.getPersonVO().getRace());
		        vo.setDob(customerVO.getPersonVO().getDob());
		        vo.setNric(customerVO.getPersonVO().getNric());
	        	vo.setSalutation(customerVO.getPersonVO().getSalutation());
        		for (PersonContactVO pcVO : customerVO.getPersonContactList()) {
    	            CRMContactVO contactVO = new CRMContactVO();
    	            contactVO.setType(pcVO.getTypeCd());
    	            contactVO.setNumber(pcVO.getNumber());
    	            contactVO.setStatus(pcVO.getStatusCode());
    	            contactList.add(contactVO);
    	        }
    	        vo.setContacts(contactList);
	        	for (AddressVO addVO : customerVO.getAddressList()) {
		        	CRMAddressVO crmAddVO = new CRMAddressVO();
		            crmAddVO.setType(addVO.getAddrType());
		            crmAddVO.setAddr1(StringUtils.isBlank(addVO.getAddr1()) ? "none" : addVO.getAddr1());
		            crmAddVO.setAddr2(addVO.getAddr2());
		            crmAddVO.setAddr3(addVO.getAddr3());
		            crmAddVO.setCity(addVO.getCity());
		            crmAddVO.setState(addVO.getState());
		            crmAddVO.setPostcode(addVO.getPostcode());
		            crmAddVO.setCountryId(addVO.getCountryId());
		            addressList.add(crmAddVO);
		        }
		        vo.setAddresses(addressList);
		        for (PersonLangVO langVO : customerVO.getPersonLangList()) {
		        	CRMLangVO crmLangVO = new CRMLangVO();
		            crmLangVO.setType(langVO.getLangCd());
		            langList.add(crmLangVO);
		        }
		        vo.setSpokenLanguage(langList);
		        for (PersonEmailVO peVO : personEmailList) {
		            CRMEmailVO emailVO = new CRMEmailVO();
		            emailVO.setEmail(peVO.getEmail());
		            emailVO.setIsPrimary(peVO.getIsPrimary() ? 1 : 0);
		            emailVO.setStatus(peVO.getStatusCode());
		            emailList.add(emailVO);
		        }
		        vo.setEmails(emailList);
	        }
	        if (customerVO.getIdentityList() != null) {
	            for (IdentityVO identityVO : customerVO.getIdentityList()) {
	                if (CommonConstant.LOOKUP_ITM_ID_NRIC.equals(identityVO.getIdType()))  vo.setNric(identityVO.getIdNo());
	                if (CommonConstant.LOOKUP_ITM_ID_PASSPRT.equals(identityVO.getIdType())) vo.setPassportNo(identityVO.getIdNo());
	            }
	        }
	    }    
	    return ls;
	}
	
	@Override
	public void updateCustomerPosInfoStatus(List<CustomerPosInfoVO> updList) throws BusinessException {
	    Session session = this.getSessionFactory().openSession();

	    try {
	        session.getTransaction().setTimeout(60);
	        session.beginTransaction();

	        for (int i = 0; i < updList.size(); i++) {
	            session.saveOrUpdate(updList.get(i));

	            if (i % 50 == 0) {
	                session.flush();
	                session.clear();
	            }
	        }

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session.getTransaction() != null) {
	            session.getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
	@Override
	public void insertCustomerPosInfo(CustomerPosInfoVO vo) throws BusinessException{
		Session session = this.getSessionFactory().openSession();
	    try {
	        session.getTransaction().setTimeout(60);
	        session.beginTransaction();
	        session.save(vo);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session.getTransaction() != null) {
	            session.getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}
}
