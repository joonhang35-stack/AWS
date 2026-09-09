package com.bcs.zsg.crm.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.crm.dao.CRMMembershipDAO;
import com.bcs.zsg.crm.sec.vo.CRMMembershipVO;
import com.bcs.zsg.crm.sec.vo.KeycloakUserVO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.PersonContactVO;

public class CRMMembershipServiceImpl implements CRMMembershipService {
	
	@Autowired
	private CRMMembershipDAO crmMembershipDAO;
	
	@Override
	public int getCRMMembershipListSize(Map<String, Object> params) throws BusinessException {
		return crmMembershipDAO.getCRMMembershipListSize(params);
	}
	
	@Override
	public List<CRMMembershipVO> getCRMMembershipList(Map<String, Object> params) throws BusinessException {
		return crmMembershipDAO.getCRMMembershipList(params);
	}
	
	@Override
	public void delCRMMembership(CRMMembershipVO vo) {
		crmMembershipDAO.updateCRMMembershipStatus(vo.getId(), BaseConstant.STATUS_DELETED);
	}
	
	@Override
	public void unlinkCRMMembership(CRMMembershipVO vo) {
		crmMembershipDAO.unlinkCRMMembership(vo.getId(), null);
	}
	
	@Override
	public CRMMembershipVO getCRMMembership(Long customerId) throws BusinessException {
		return crmMembershipDAO.getCRMMembership(customerId);
	}
	
	@Override
	public void insertCRMMembership(CustomerVO custVO, PersonContactVO contactVO, String keycloakUserId, KeycloakUserVO userVO) throws BusinessException {
		CRMMembershipVO membershipVO = new CRMMembershipVO();
		membershipVO.setIdCompany(custVO.getCompanyId());
		membershipVO.setIdCustomer(custVO.getId());
		membershipVO.setKeycloakId(keycloakUserId);
		membershipVO.setKeycloakUsername(userVO.getEmail());
		membershipVO.setEmail(userVO.getEmail());
		membershipVO.setMemberStatusCd("C");
		membershipVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		
		// mobile
		if (StringUtils.isNotBlank(contactVO.getPerMobileCountryCd()) && StringUtils.isNotBlank(contactVO.getPerMobileNumber())) {
			membershipVO.setContactCountryCode(contactVO.getPerMobileCountryCd());
			membershipVO.setContact(contactVO.getPerMobileNumber());
		}
		// office
		else if (StringUtils.isNotBlank(contactVO.getPerOfficeCountryCd()) && StringUtils.isNotBlank(contactVO.getPerOfficeNumber())) {
		    membershipVO.setContactCountryCode(contactVO.getPerOfficeCountryCd());
		    membershipVO.setContact(contactVO.getPerOfficeNumber());
		}
		// home
		else if (StringUtils.isNotBlank(contactVO.getPerHomeCountryCd()) && StringUtils.isNotBlank(contactVO.getPerHomeNumber())) {
		    membershipVO.setContactCountryCode(contactVO.getPerHomeCountryCd());
		    membershipVO.setContact(contactVO.getPerHomeNumber());
		}
		// fax
		else if (StringUtils.isNotBlank(contactVO.getPerFaxCountryCd()) && StringUtils.isNotBlank(contactVO.getPerFaxNumber())) {
		    membershipVO.setContactCountryCode(contactVO.getPerFaxCountryCd());
		    membershipVO.setContact(contactVO.getPerFaxNumber());
		} else {
			throw new BusinessException(CommonErrConstant.ERR_CUST_NO_CONTACT);
		}
		
		crmMembershipDAO.insert(membershipVO);
	}
}
