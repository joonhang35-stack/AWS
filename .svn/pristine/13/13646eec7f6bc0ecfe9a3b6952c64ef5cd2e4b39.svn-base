package com.bcs.zsg.purchase.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.dao.accttrans.AccountTransDAO;
import com.bcs.zsg.db.bterp.dao.view.supplier.SupplierViewDAO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.dao.PurchaseDAO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.ContactVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.RemarksVO;
import com.bcs.zsg.purchase.vo.SuppPersonInChargeVO;
import com.bcs.zsg.purchase.vo.SupplierVO;

public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDAO purchaseDAO;

	@Autowired
	private SupplierViewDAO supplierViewDAO;
	
	@Autowired
	private AccountTransDAO accountTransDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getSupplierList()
	 */
	@Override
	public List<SupplierVO> getSupplierList(Long companyId, boolean isSimple) throws BusinessException {
		
		List<SupplierVO> supplierList = supplierViewDAO.getSuppNameList(companyId);
		String temp=null,contactNo=null;
		
		if (!isSimple) {
			PersonVO personVO = new PersonVO();
			AddressVO addressVO = new AddressVO();
			CorporateVO corporateVO = new CorporateVO();
			List<ContactVO> contactList = new ArrayList<ContactVO>();
			List<CorContactVO> corContactList = new ArrayList<CorContactVO>();
			List<IdentityVO> identityList = new ArrayList<IdentityVO>();
			List<RemarksVO> remarksList = new ArrayList<RemarksVO>();
			
			if(!(supplierList.size() == 0)){
				for(int i = 0; i < supplierList.size(); i++){
					Long personId = supplierList.get(i).getPersonId();
					if(supplierList.get(i).getSupplierType().equals("SC")){
						supplierList.get(i).setSupplierType("Sundry Creditors");
					}else{
						supplierList.get(i).setSupplierType("Trade Creditors");
					}
					personVO = purchaseDAO.getPerson(personId);
					supplierList.get(i).setPersonVO(personVO);
	
					if(supplierList.get(i).getCode().equals("C")){
						corporateVO = purchaseDAO.getCompany(personId);
						CorAddressVO corAddressVO = purchaseDAO.getCorAddress(corporateVO.getId());
						corContactList = purchaseDAO.getCorContactList(corporateVO.getId());
						if(corContactList.size() !=0){
							for(CorContactVO vo:corContactList)
							{	
								temp=vo.getContactType()+"|"+vo.getContactNo();
								if(contactNo==null)
								{
									contactNo=temp;
								}
								else
								{	
								contactNo=contactNo+","+temp;
								}
								temp=null;
							}
							supplierList.get(i).setContactNo(contactNo);
							supplierList.get(i).setCorContactList(corContactList);
						}
						supplierList.get(i).setCorporateVO(corporateVO);
						supplierList.get(i).setAddressVO(new AddressVO());
						if(!(corAddressVO == null)){
							
							supplierList.get(i).getAddressVO().setAddr1(corAddressVO.getAddr1());
							supplierList.get(i).getAddressVO().setAddr2(corAddressVO.getAddr2());
							supplierList.get(i).getAddressVO().setAddr3(corAddressVO.getAddr3());
							supplierList.get(i).getAddressVO().setCity(corAddressVO.getCity());
							supplierList.get(i).getAddressVO().setState(corAddressVO.getState());
							supplierList.get(i).getAddressVO().setPostcode(corAddressVO.getPostcode());
							supplierList.get(i).getAddressVO().setCountryId(corAddressVO.getCountryId());
							
							supplierList.get(i).setCorAddressVO(corAddressVO);
						}
						remarksList = purchaseDAO.getRemarksList(corporateVO.getId());
						supplierList.get(i).setVenderType(true);
					}else {
						addressVO = purchaseDAO.getAddress(personId);
						contactList = purchaseDAO.getContactList(personId);
						if(contactList.size() !=0){
							
							for(ContactVO vo:contactList)
							{
								temp=vo.getContactType()+"|"+vo.getContactNo();
								if(contactNo==null)
								{
									contactNo=temp;
								}
								else
								{
									contactNo=contactNo+","+temp;
								}	
							}	
							supplierList.get(i).setContactNo(contactNo);
							supplierList.get(i).setContactList(contactList);
						}
						supplierList.get(i).setAddressVO(addressVO);
						supplierList.get(i).setVenderType(false);
						remarksList = purchaseDAO.getRemarksList(personId);
					}
					
					identityList = purchaseDAO.getIdentityList(personId);
					
					if(identityList.size() !=0){
						supplierList.get(i).setIdentityList(identityList);
					}
					
					if(remarksList.size() !=0){
						DateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						for(int k = 0; k < remarksList.size(); k++){
							remarksList.get(k).setStrTimestamp(dtformat.format(remarksList.get(k).getTimestamp()));
						}
						
						supplierList.get(i).setRemarksList(remarksList);
					}
				}
			}
		}
		
		return supplierList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getSupplierList()
	 */
	@Override
	public List<IdentityVO> getIdentityList() throws BusinessException {
		List<IdentityVO> identityList = purchaseDAO.getIdentityList();
		
		return identityList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getSupplierList()
	 */
	@Override
	public List<LookupItemVO> getPymtTermList(String pymtTerm) throws BusinessException {
		List<LookupItemVO> pymtTermList = purchaseDAO.getPymtTermList(pymtTerm);
		
		return pymtTermList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getCountryList()
	 */
	@Override
	public List<CountryVO> getCountryList() throws BusinessException {
		List<CountryVO> countryList = purchaseDAO.getCountryList();
		
		return countryList;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.dao.PurchaseService#getCorContactList()
	 */
	@Override
	public List<CorContactVO> getCorContactList(Long corporateId) throws BusinessException{
		List<CorContactVO> corContactVOList = purchaseDAO.getCorContactList(corporateId);
		return corContactVOList;
	}
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#insert(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void insertPersonal(SupplierVO supplierVO, SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		
		PersonVO personVO = new PersonVO();
		AddressVO addressVO = new AddressVO();
		
		personVO.setSalutation(supplierVO.getSalutation());
		personVO.setLastName(supplierVO.getLastName());
		personVO.setGivenName(supplierVO.getGivenName());
		personVO.setEmail(supplierVO.getEmail());
		personVO.setDesignation(supplierVO.getDesignation());
		
		Long personId = (Long) purchaseDAO.insert(personVO);
		supplierVO.setSysNo(systemNumberGenerationVO.getNextnumber().toString());
		supplierVO.setCompanyId(systemNumberGenerationVO.getIdCompany());
		supplierVO.setPersonId(personId);
//		supplierVO.setCode("P");
		supplierVO.setStatus("A");
		purchaseDAO.insert(supplierVO);
		
		if(!(supplierVO.getIdentityList() == null)){
			for(int i=0; i < supplierVO.getIdentityList().size();i++){
				IdentityVO identityVO = new IdentityVO();
				identityVO.setPersonId(personId);
				identityVO.setIdType(supplierVO.getIdentityList().get(i).getIdType());
				identityVO.setIdNo(supplierVO.getIdentityList().get(i).getIdNo());
				identityVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				purchaseDAO.insert(identityVO);
			}
		}
		
		addressVO.setPersonId(personId);
		addressVO.setAddr1(supplierVO.getAdd1());
		addressVO.setAddr2(supplierVO.getAdd2());
		addressVO.setAddr3(supplierVO.getAdd3());
		addressVO.setCity(supplierVO.getCity());
		addressVO.setState(supplierVO.getState());
		addressVO.setPostcode(supplierVO.getPostcode());
		addressVO.setCountryId(supplierVO.getCountryId());
		purchaseDAO.insert(addressVO);

		if(!(supplierVO.getContactList() == null)){
			for(int i=0; i < supplierVO.getContactList().size();i++){
				ContactVO contactVO = new ContactVO();
				contactVO.setPersonId(personId);
				contactVO.setContactType(supplierVO.getContactList().get(i).getContactType());
				contactVO.setContactNo(supplierVO.getContactList().get(i).getContactNo());
				contactVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				purchaseDAO.insert(contactVO);
			}
		}
		if(!(supplierVO.getRemarksList() == null)){
			for(int i=0; i < supplierVO.getRemarksList().size();i++){
				RemarksVO remarksVO = new RemarksVO();
				remarksVO.setPersonCorpId(personId);
				remarksVO.setRemarksDetails(supplierVO.getRemarksList().get(i).getRemarksDetails());
				remarksVO.setTimestamp(supplierVO.getRemarksList().get(i).getTimestamp());
				purchaseDAO.insert(remarksVO);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#insert(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void insertCompany(SupplierVO supplierVO, SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException {
		
		PersonVO personVO = new PersonVO();
		CorAddressVO corAddressVO = new CorAddressVO();
		CorporateVO corporateVO = new CorporateVO();
		
//		personVO.setSalutation(supplierVO.getSalutation());
//		personVO.setLastName(supplierVO.getLastName());
//		personVO.setGivenName(supplierVO.getGivenName());
//		personVO.setEmail(supplierVO.getEmail());
//		personVO.setDesignation(supplierVO.getDesignation());
		
		if (supplierVO.getPersonVO() != null) {
			personVO = supplierVO.getPersonVO();
		}
		
		Long personId = (Long) purchaseDAO.insert(personVO);
		supplierVO.setSysNo(systemNumberGenerationVO.getNextnumber().toString());
		supplierVO.setCompanyId(systemNumberGenerationVO.getIdCompany());
		supplierVO.setPersonId(personId);
		supplierVO.setStatus("A");
		purchaseDAO.insert(supplierVO);
		
		if (supplierVO.getCorporateVO() != null) {
			corporateVO = supplierVO.getCorporateVO();
		}
		corporateVO.setPersonId(personId);
		Long corporateId = (Long) purchaseDAO.insert(corporateVO);
		
		if (supplierVO.getCorAddressVO() != null) {
			corAddressVO = supplierVO.getCorAddressVO();
		}
		corAddressVO.setCorporateId(corporateId);
		purchaseDAO.insert(corAddressVO);

//		if(!(supplierVO.getContactList() == null)){
//			for(int i=0; i < supplierVO.getContactList().size(); i++){
//				CorContactVO corContactVO = new CorContactVO();
//				corContactVO.setCorporateId(corporateId);
//				corContactVO.setContactType(supplierVO.getContactList().get(i).getContactType());
//				corContactVO.setContactNo(supplierVO.getContactList().get(i).getContactNo());
//				corContactVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
//				purchaseDAO.insert(corContactVO);
//			}
//		}
		
		if (CollectionUtils.isNotEmpty(supplierVO.getCorContactList())) {
			for (CorContactVO vo : supplierVO.getCorContactList()) {
				vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
				vo.setCorporateId(corporateId);
				purchaseDAO.insert(vo);
			}
		}
		
		if(!(supplierVO.getRemarksList() == null)){
			for(int i=0; i < supplierVO.getRemarksList().size(); i++){
				RemarksVO remarksVO = new RemarksVO();
				remarksVO.setPersonCorpId(corporateId);
				remarksVO.setRemarksDetails(supplierVO.getRemarksList().get(i).getRemarksDetails());
				remarksVO.setTimestamp(supplierVO.getRemarksList().get(i).getTimestamp());
				purchaseDAO.insert(remarksVO);
			}
		}
		
		if (supplierVO.getSuppPicList()!= null && CollectionUtils.isNotEmpty(supplierVO.getSuppPicList())) {
			for (SuppPersonInChargeVO vo : supplierVO.getSuppPicList()) {
				vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
				vo.setSupplierId(supplierVO.getSupplierId());
				purchaseDAO.insert(vo);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#update(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void updatePersonal(SupplierVO supplierVO, AddressVO addressVO, PersonVO personVO, CorporateVO corporateVO, AddUpdDelVO contactAUDVO, AddUpdDelVO corContactAUDVO, AddUpdDelVO identityAUDVO, AddUpdDelVO remarksAUDVO) throws BusinessException {

		purchaseDAO.update(supplierVO);
		purchaseDAO.update(personVO);
		
		
		if(supplierVO.getCode().equals("C")){
			purchaseDAO.update(corporateVO);
			purchaseDAO.update(supplierVO.getCorAddressVO());
		}else{
			purchaseDAO.update(addressVO);
		}
		
		if (contactAUDVO.getAddList().size() > 0) {
			for (Object vo : contactAUDVO.getAddList()) {
				ContactVO contactVO = (ContactVO) vo;
				purchaseDAO.insert(contactVO);
			}
		}
		
		if (contactAUDVO.getUpdList().size() > 0) {
			for (Object vo : contactAUDVO.getUpdList()) {
				ContactVO contactVO = (ContactVO) vo;
				purchaseDAO.update(contactVO);
			}
		}
		
		if (contactAUDVO.getDelList().size() > 0) {
			for (Object vo : contactAUDVO.getDelList()) {
				ContactVO contactVO = (ContactVO) vo;
				purchaseDAO.delete(contactVO);
			}
		}
		
//		if (corContactAUDVO.getAddList().size() > 0) {
//			for (Object vo : corContactAUDVO.getAddList()) {
//				CorContactVO corContactVO = (CorContactVO) vo;
//				corContactVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
//				purchaseDAO.insert(corContactVO);
//			}
//		}
//		
//		if (corContactAUDVO.getUpdList().size() > 0) {
//			for (Object vo : corContactAUDVO.getUpdList()) {
//				CorContactVO corContactVO = (CorContactVO) vo;
//				purchaseDAO.update(corContactVO);
//			}
//		}
//		
//		if (corContactAUDVO.getDelList().size() > 0) {
//			for (Object vo : corContactAUDVO.getDelList()) {
//				CorContactVO corContactVO = (CorContactVO) vo;
//				purchaseDAO.delete(corContactVO);
//			}
//		}
		
		if (corporateVO.getId() != null) {
			purchaseDAO.terminateSupplierCorContact(corporateVO.getId());
			if (supplierVO.getCorContactList() != null && CollectionUtils.isNotEmpty(supplierVO.getCorContactList())) {
				for (CorContactVO vo : supplierVO.getCorContactList()) {
					vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
					if (null == vo.getId()) {
						vo.setCorporateId(corporateVO.getId());
						purchaseDAO.insert(vo);
					} else {
						purchaseDAO.update(vo);
					}
				}
			}
		}
		
		if (identityAUDVO.getAddList().size() > 0) {
			for (Object vo : identityAUDVO.getAddList()) {
				IdentityVO identityVO = (IdentityVO) vo;
				identityVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				purchaseDAO.insert(identityVO);
			}
		}
		
		if (identityAUDVO.getUpdList().size() > 0) {
			for (Object vo : identityAUDVO.getUpdList()) {
				IdentityVO identityVO = (IdentityVO) vo;
				purchaseDAO.update(identityVO);
			}
		}
		
		if (identityAUDVO.getDelList().size() > 0) {
			for (Object vo : identityAUDVO.getDelList()) {
				IdentityVO identityVO = (IdentityVO) vo;
				purchaseDAO.delete(identityVO);
			}
		}
		
		if (remarksAUDVO.getAddList().size() > 0) {
			for (Object vo : remarksAUDVO.getAddList()) {
				RemarksVO remarksVO = (RemarksVO) vo;
				purchaseDAO.insert(remarksVO);
			}
		}
		
		if (remarksAUDVO.getUpdList().size() > 0) {
			for (Object vo : remarksAUDVO.getUpdList()) {
				RemarksVO remarksVO = (RemarksVO) vo;
				purchaseDAO.update(remarksVO);
			}
		}
		
		if (remarksAUDVO.getDelList().size() > 0) {
			for (Object vo : remarksAUDVO.getDelList()) {
				RemarksVO remarksVO = (RemarksVO) vo;
				purchaseDAO.delete(remarksVO);
			}
		}
		
		purchaseDAO.terminateSupplierPIC(supplierVO.getId());
		if (supplierVO.getSuppPicList()!= null && CollectionUtils.isNotEmpty(supplierVO.getSuppPicList())) {
			for (SuppPersonInChargeVO vo : supplierVO.getSuppPicList()) {
				vo.setStatusCode(BaseConstant.STATUS_ACTIVE);
				if (null == vo.getId()) {
					vo.setSupplierId(supplierVO.getId());
					purchaseDAO.insert(vo);
				} else {
					purchaseDAO.update(vo);
				}
			}
		}

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#delete(com.bcs.zsg.purchase.vo.PurchaseVO)
	 */
	@Override
	public void delSuppl(SupplierVO supplierVO, AddressVO addressVO, PersonVO personVO, CorporateVO corporateVO) throws BusinessException {
		
		if(!(supplierVO.getIdentityList() == null)){
			for(int i = 0; i < supplierVO.getIdentityList().size(); i++){
				IdentityVO identityVO = supplierVO.getIdentityList().get(i);
				purchaseDAO.delete(identityVO);
			}
		}
		
		if(!(supplierVO.getRemarksList() == null)){
			for(int i = 0; i < supplierVO.getRemarksList().size(); i++){
				RemarksVO remarksVO = supplierVO.getRemarksList().get(i);
				purchaseDAO.delete(remarksVO);
			}
		}
		
		if (supplierVO.getSuppPicList()!= null && CollectionUtils.isNotEmpty(supplierVO.getSuppPicList())) {
			purchaseDAO.terminateSupplierPIC(supplierVO.getId());
		}
		
		if(supplierVO.getCode().equals("C")){
			/*
			CorAddressVO corAddressVO = supplierVO.getCorAddressVO();
			if(!(supplierVO.getCorContactList() == null)){
				for(int i = 0; i < supplierVO.getCorContactList().size(); i++){
					CorContactVO corContactVO = supplierVO.getCorContactList().get(i);
					purchaseDAO.delete(corContactVO);
				}
			}
			if(!(supplierVO.getCorAddressVO() == null)){
				purchaseDAO.delete(corAddressVO);
			}
			
			purchaseDAO.delete(corporateVO);
			*/
		}else{
			if(!(supplierVO.getContactList() == null)){
				for(int i = 0; i < supplierVO.getContactList().size(); i++){
					ContactVO contactVO = supplierVO.getContactList().get(i);
					purchaseDAO.delete(contactVO);
				}
			}
			purchaseDAO.delete(addressVO);
		}
		
		supplierVO.setStatus("I");
		purchaseDAO.update(supplierVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getSupplierListSize(java.util.Map)
	 */
	@Override
	public int getSupplierListSize(Map<String, Object> params) throws BusinessException {
		return purchaseDAO.getSupplierListSize(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getSupplierList(java.util.Map)
	 */
	@Override
	public List<SupplierVO> getSupplierList(Map<String, Object> params) throws BusinessException {
		List<SupplierVO> supplierList = purchaseDAO.getSupplierList(params);
		
		return supplierList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#getSupplierDetails(java.lang.Long)
	 */
	@Override
	public SupplierVO getSupplierDetails(Long id) {
		SupplierVO supplierVO = purchaseDAO.getSupplierById(id);
		supplierVO.setPersonVO(purchaseDAO.getPerson(supplierVO.getPersonId()));
		supplierVO.setCorporateVO(purchaseDAO.getCompany(supplierVO.getPersonId()));
		supplierVO.setCorAddressVO(purchaseDAO.getCorAddress(supplierVO.getCorporateVO().getId()));
		supplierVO.setCorContactList(purchaseDAO.getCorContactList(supplierVO.getCorporateVO().getId()));
		supplierVO.setRemarksList(purchaseDAO.getRemarksList(supplierVO.getCorporateVO().getId()));
		supplierVO.setSuppPicList(purchaseDAO.getSuppPicList(id));
		return supplierVO;
	}
	
	@Override
	public SupplierVO getSupplierDetails(SupplierVO supplierVO) throws BusinessException {
		supplierVO.setPersonVO(purchaseDAO.getPerson(supplierVO.getPersonId()));
		List<RemarksVO> remarksVOList = new ArrayList<>();
		
		String temp = null, contactNo = null;
		if(StringUtils.equals(supplierVO.getCode(), "C")) {
			supplierVO.setCorporateVO(purchaseDAO.getCompany(supplierVO.getPersonId()));
			supplierVO.setCorAddressVO(purchaseDAO.getCorAddress(supplierVO.getCorporateVO().getId()));
			supplierVO.setCorContactList(purchaseDAO.getCorContactList(supplierVO.getCorporateVO().getId()));
			remarksVOList = purchaseDAO.getRemarksList(supplierVO.getCorporateVO().getId());
			
			if(CollectionUtils.isNotEmpty(supplierVO.getCorContactList())){
				for(CorContactVO vo: supplierVO.getCorContactList()) {	
					temp=vo.getContactType()+"|"+vo.getContactNo();
					if (contactNo == null) contactNo = temp;
					else contactNo=contactNo + "," + temp;
					temp = null;
				}
				supplierVO.setContactNo(contactNo);
			}
			supplierVO.setAddressVO(new AddressVO());
			if(supplierVO.getCorAddressVO() != null){
				supplierVO.getAddressVO().setAddr1(supplierVO.getCorAddressVO().getAddr1());
				supplierVO.getAddressVO().setAddr2(supplierVO.getCorAddressVO().getAddr2());
				supplierVO.getAddressVO().setAddr3(supplierVO.getCorAddressVO().getAddr3());
				supplierVO.getAddressVO().setCity(supplierVO.getCorAddressVO().getCity());
				supplierVO.getAddressVO().setState(supplierVO.getCorAddressVO().getState());
				supplierVO.getAddressVO().setPostcode(supplierVO.getCorAddressVO().getPostcode());
				supplierVO.getAddressVO().setCountryId(supplierVO.getCorAddressVO().getCountryId());
			}
			supplierVO.setVenderType(true);
		} else {
			AddressVO addressVO = purchaseDAO.getAddress(supplierVO.getPersonId());
			List<ContactVO> contactList = purchaseDAO.getContactList(supplierVO.getPersonId());
			if(CollectionUtils.isNotEmpty(contactList)) {
				for(ContactVO vo:contactList) {
					temp=vo.getContactType()+"|"+vo.getContactNo();
					if (contactNo == null) contactNo=temp;
					else contactNo=contactNo+","+temp;
				}	
				supplierVO.setContactNo(contactNo);
				supplierVO.setContactList(contactList);
			}
			supplierVO.setAddressVO(addressVO);
			supplierVO.setVenderType(false);
			remarksVOList = purchaseDAO.getRemarksList(supplierVO.getPersonId());
		}
		
		supplierVO.setIdentityList(purchaseDAO.getIdentityList(supplierVO.getPersonId()));
		
		if(remarksVOList.size() !=0){
			DateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int k = 0; k < remarksVOList.size(); k++){
				remarksVOList.get(k).setStrTimestamp(dtformat.format(remarksVOList.get(k).getTimestamp()));
			}
			
			supplierVO.setRemarksList(remarksVOList);
		}
		
		return supplierVO;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#updateAcctTransDestinationSpplr(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateAcctTransDestinationSpplr(Long companyId, Long supplierId) throws BusinessException {
		//Update Account Transaction
		accountTransDAO.updateAcctTransDestinationSpplr(companyId, supplierId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.purchase.service.PurchaseService#updateAcctTransAcctIdSupplr(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateAcctTransAcctIdSupplr(Long companyId, Long supplierId) throws BusinessException {
		//Update Account Transaction
		accountTransDAO.updateAcctTransAcctIdSupplr(companyId, supplierId);
	}

	@Override
	public List<SupplierVO> getSupplierList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return purchaseDAO.getSupplierList(idCompany, searchParamVO);
	}
}
