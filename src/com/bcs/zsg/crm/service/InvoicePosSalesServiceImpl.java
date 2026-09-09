package com.bcs.zsg.crm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CRMCommonConstant;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.crm.dao.InvoicePosSalesDAO;
import com.bcs.zsg.crm.vo.ContactDetailsVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesBillingVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesItemVO;
import com.bcs.zsg.crm.vo.InvoicePosSalesVO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.sales.dao.CustomerDAO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.InvoiceItemVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonEmailVO;

public class InvoicePosSalesServiceImpl implements InvoicePosSalesService {
	
	@Autowired
	private InvoicePosSalesDAO invoicePosSalesDAO;
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	public void processInvoicePosSales(InvoiceVO invoiceVO) throws BusinessException {
		if (invoiceVO == null)
			return;
		
		// Will post the invoice which contain booking and category type is tour only
		if (invoiceVO.getAmount() <= 0 && invoiceVO.getBookingId() == null || !StringUtils.equals(invoiceVO.getCatCd(), CommonConstant.LOOKUP_ITM_INV_CAT_TOUR) ||
				Boolean.FALSE == invoiceVO.getIsApplePoint()) {
			return;
		}
		
		int tourFareCount = 0, ftSglCount = 0, ftTwnCount = 0, ftCtwCount = 0, ftCwbCount = 0, ftCnbCount = 0;
		int passangerCount = 0, pssgSglCount = 0, pssgTwnCount = 0, pssgCtwCount = 0, pssgCwbCount = 0, pssgCnbCount = 0;
		BigDecimal tourFareTotal = BigDecimal.ZERO;
		if (CollectionUtils.isNotEmpty(invoiceVO.getInvoiceItemList())) {
			for (InvoiceItemVO itemVO : invoiceVO.getInvoiceItemList()) {
				if ((ProductConstant.TOUR_DEP_ITM_CD_FT_SGL.equals(itemVO.getCode()) ||
					ProductConstant.TOUR_DEP_ITM_CD_FT_TWN.equals(itemVO.getCode()) ||
					ProductConstant.TOUR_DEP_ITM_CD_FT_CTW.equals(itemVO.getCode()) ||
					ProductConstant.TOUR_DEP_ITM_CD_FT_CWB.equals(itemVO.getCode()) ||
					ProductConstant.TOUR_DEP_ITM_CD_FT_CNB.equals(itemVO.getCode())) &&
						itemVO.getUnitPrice() > 0) {
					tourFareCount += itemVO.getQty();
					tourFareTotal = tourFareTotal.add(BigDecimal.valueOf(itemVO.getAmount().doubleValue()));
					
					if (ProductConstant.TOUR_DEP_ITM_CD_FT_SGL.equals(itemVO.getCode())) ftSglCount += itemVO.getQty();
					if (ProductConstant.TOUR_DEP_ITM_CD_FT_TWN.equals(itemVO.getCode())) ftTwnCount += itemVO.getQty();
					if (ProductConstant.TOUR_DEP_ITM_CD_FT_CTW.equals(itemVO.getCode())) ftCtwCount += itemVO.getQty();
					if (ProductConstant.TOUR_DEP_ITM_CD_FT_CWB.equals(itemVO.getCode())) ftCwbCount += itemVO.getQty();
					if (ProductConstant.TOUR_DEP_ITM_CD_FT_CNB.equals(itemVO.getCode())) ftCnbCount += itemVO.getQty();
				}
			}
		} else {
			return;
		}
		
		if (tourFareTotal.compareTo(BigDecimal.ZERO) <= 0) return;
		
		if (CollectionUtils.isNotEmpty(invoiceVO.getInvoicePaxList())) {
			for (InvoicePaxVO paxVO : invoiceVO.getInvoicePaxList()) {
				if (ProductConstant.TOUR_DEP_ITM_CD_FT_SGL.equals(paxVO.getItemCode())) pssgSglCount += 1;
				if (ProductConstant.TOUR_DEP_ITM_CD_FT_TWN.equals(paxVO.getItemCode())) pssgTwnCount += 1;
				if (ProductConstant.TOUR_DEP_ITM_CD_FT_CTW.equals(paxVO.getItemCode())) pssgCtwCount += 1;
				if (ProductConstant.TOUR_DEP_ITM_CD_FT_CWB.equals(paxVO.getItemCode())) pssgCwbCount += 1;
				if (ProductConstant.TOUR_DEP_ITM_CD_FT_CNB.equals(paxVO.getItemCode())) pssgCnbCount += 1;
			}
			
			passangerCount += pssgSglCount + pssgTwnCount + pssgCtwCount + pssgCwbCount + pssgCnbCount;
		}
		
		String postingStatus = CRMCommonConstant.CRM_INV_POSTING_PENDING;
		boolean isIncompleted = false;
		String incompletedRemarks = "";
		
		if (tourFareCount != passangerCount) {
			isIncompleted = true;
			incompletedRemarks = "Tour Fare is not tally with total passanger.";
			if (ftSglCount != 0 && ftSglCount != pssgSglCount) incompletedRemarks += "\nFT_SGL x " + ftSglCount + " Pax x " + pssgSglCount;
			if (ftTwnCount != 0 && ftTwnCount != pssgTwnCount) incompletedRemarks += "\nFT_TWN x " + ftTwnCount + " Pax x " + pssgTwnCount;
			if (ftCtwCount != 0 && ftCtwCount != pssgCtwCount) incompletedRemarks += "\nFT_CTW x " + ftCtwCount + " Pax x " + pssgCtwCount;
			if (ftCwbCount != 0 && ftCwbCount != pssgCwbCount) incompletedRemarks += "\nFT_CWB x " + ftCwbCount + " Pax x " + pssgCwbCount;
			if (ftCnbCount != 0 && ftCnbCount != pssgCnbCount) incompletedRemarks += "\nFT_CNB x " + ftCnbCount + " Pax x " + pssgCnbCount;
			
			postingStatus = CRMCommonConstant.CRM_INV_POSTING_INCOMPLETED;
		}
		
		InvoicePosSalesVO invPosSalesVO = invoicePosSalesDAO.getInvoicePosSales(invoiceVO);
	
		if (invPosSalesVO == null) {
			InvoicePosSalesVO posSalesVO = new InvoicePosSalesVO();
			posSalesVO.setIdCompany(invoiceVO.getCompanyId());
			posSalesVO.setIdInvoice(invoiceVO.getId());
			posSalesVO.setIdCustomer(invoiceVO.getCustomerId());
			
			posSalesVO.setIdBooking(invoiceVO.getBookingId());
			posSalesVO.setInvoiceDt(invoiceVO.getInvoiceDt());
			posSalesVO.setInvNo(invoiceVO.getCode());
			posSalesVO.setPsNo(invoiceVO.getPsNo());
			posSalesVO.setDocTypeCd(invoiceVO.getDocTypeCd());
			posSalesVO.setDepartureDt(invoiceVO.getDepartureDt());
			posSalesVO.setCatCd(invoiceVO.getCatCd());
			posSalesVO.setOrderCd(invoiceVO.getOrderCd());
			posSalesVO.setEarningPointsAmt(tourFareTotal.doubleValue());
			posSalesVO.setAmount(invoiceVO.getAmount());
			posSalesVO.setPostingStatus(postingStatus);
			posSalesVO.setPaymentStatus(invoiceVO.getStatusCd());
			posSalesVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			if (isIncompleted) posSalesVO.setRemarks(incompletedRemarks);
			
			Long idPosSales = (Long) invoicePosSalesDAO.insert(posSalesVO);
			
			if (!isIncompleted) {
				InvoicePosSalesItemVO salesItemVO = null;
				for (InvoicePaxVO paxVO : invoiceVO.getInvoicePaxList()) {
					if (StringUtils.isEmpty(paxVO.getItemCode())) continue;
					salesItemVO = new InvoicePosSalesItemVO();
					salesItemVO.setIdPosSales(idPosSales);
					if (paxVO.getCustDetailsVO() != null) salesItemVO.setCrmId(paxVO.getCustDetailsVO().getCrmId());
					salesItemVO.setIdCustomer(paxVO.getCustId());
					salesItemVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					
					for (InvoiceItemVO itemVO : invoiceVO.getInvoiceItemList()) {
						if (StringUtils.isEmpty(itemVO.getCode()) || StringUtils.isEmpty(paxVO.getItemCode())) break;
						
						if (StringUtils.equals(itemVO.getCode(), paxVO.getItemCode())) {
							salesItemVO.setCode(itemVO.getCode());
							salesItemVO.setDesc(itemVO.getDesc());
							salesItemVO.setSubDesc(itemVO.getSubDesc());
							salesItemVO.setAmount(itemVO.getUnitPrice());
							break;
						}
					}
					
					invoicePosSalesDAO.insert(salesItemVO);
				}
			}
		} else if (CRMCommonConstant.CRM_INV_POSTING_INCOMPLETED.equals(invPosSalesVO.getPostingStatus())) {
			invPosSalesVO.setIdCustomer(invoiceVO.getCustomerId());
			invPosSalesVO.setIdBooking(invoiceVO.getBookingId());
			invPosSalesVO.setInvoiceDt(invoiceVO.getInvoiceDt());
			invPosSalesVO.setInvNo(invoiceVO.getCode());
			invPosSalesVO.setPsNo(invoiceVO.getPsNo());
			invPosSalesVO.setDocTypeCd(invoiceVO.getDocTypeCd());
			invPosSalesVO.setDepartureDt(invoiceVO.getDepartureDt());
			invPosSalesVO.setCatCd(invoiceVO.getCatCd());
			invPosSalesVO.setOrderCd(invoiceVO.getOrderCd());
			invPosSalesVO.setEarningPointsAmt(tourFareTotal.doubleValue());
			invPosSalesVO.setAmount(invoiceVO.getAmount());
			invPosSalesVO.setPostingStatus(postingStatus);
			invPosSalesVO.setPaymentStatus(invoiceVO.getStatusCd());
			if (isIncompleted) invPosSalesVO.setRemarks(incompletedRemarks);
			else invPosSalesVO.setRemarks("");
			
			invoicePosSalesDAO.update(invPosSalesVO);
			
			System.out.println("####################id: " + invPosSalesVO.getId() + " isIncompleted: " + isIncompleted + " total pax: " + invPosSalesVO.getInvPosSalesItemSet().size());
			
			if (!isIncompleted) {
				InvoicePosSalesItemVO salesItemVO = null;
				for (InvoicePaxVO paxVO : invoiceVO.getInvoicePaxList()) {
					if (StringUtils.isEmpty(paxVO.getItemCode())) continue;
					salesItemVO = new InvoicePosSalesItemVO();
					salesItemVO.setIdPosSales(invPosSalesVO.getId());
					if (paxVO.getCustDetailsVO() != null) salesItemVO.setCrmId(paxVO.getCustDetailsVO().getCrmId());
					salesItemVO.setIdCustomer(paxVO.getCustId());
					salesItemVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
					
					for (InvoiceItemVO itemVO : invoiceVO.getInvoiceItemList()) {
						if (StringUtils.isEmpty(itemVO.getCode()) || StringUtils.isEmpty(paxVO.getItemCode())) break;
						
						if (StringUtils.equals(itemVO.getCode(), paxVO.getItemCode())) {
							salesItemVO.setCode(itemVO.getCode());
							salesItemVO.setDesc(itemVO.getDesc());
							salesItemVO.setSubDesc(itemVO.getSubDesc());
							salesItemVO.setAmount(itemVO.getUnitPrice());
							break;
						}
					}
					
					invoicePosSalesDAO.insert(salesItemVO);
				}
			}
		}
	}
	
	@Override
	public List<InvoicePosSalesVO> getInvoicePosSalesList() throws BusinessException {
		return invoicePosSalesDAO.getInvoicePosSalesList();
	}

	@Override
	public InvoicePosSalesBillingVO getBillingDetails(Long idCustomer) throws BusinessException {
		InvoicePosSalesBillingVO vo = new InvoicePosSalesBillingVO();
		
		CustomerVO custVO = invoicePosSalesDAO.getCustomerVO(idCustomer);
		
		if (custVO == null) return vo;
		
		vo.setCrmId(custVO.getCrmId());
		vo.setRefCustomerId(custVO.getId());
		vo.setCustomerNo(custVO.getCode());
		StringBuilder name = new StringBuilder();
		if (StringUtils.isNotBlank(custVO.getPersonVO().getSalutation())) {
			String salutation = LookupItemUtils.getLookupItemDesc(CommonConstant.LOOKUP_CAT_CD_SALUTATN, custVO.getPersonVO().getSalutation());
			name.append(salutation + " ")
					.append(custVO.getPersonVO().getLastName()).append(" ")
					.append(custVO.getPersonVO().getGivenName());
		} else {
			name.append(custVO.getPersonVO().getLastName()).append(" ").append(custVO.getPersonVO().getGivenName());
			
		}
		vo.setBillingName(name.toString());
		
		if (custVO.getPcTypeCd().equals("C")) {
			vo.setCompanyName(custVO.getCorporateVO().getCompanyName());
			vo.setBillingAddr1(custVO.getCorBillAddressVO().getAddr1());
			vo.setBillingAddr2(custVO.getCorBillAddressVO().getAddr2());
			vo.setBillingAddr3(custVO.getCorBillAddressVO().getAddr3());
			vo.setCity(custVO.getCorBillAddressVO().getCity());
			vo.setState(custVO.getCorBillAddressVO().getState());
			vo.setPostcode(custVO.getCorBillAddressVO().getPostcode());
			vo.setTaxNo(custVO.getPersonVO().getTaxIdNo());
			if (CollectionUtils.isNotEmpty((custVO.getPersonVO().getEmailList()))) {
				vo.setEmail(custVO.getPersonVO().getEmailList().get(0).getEmail());
				
				for (PersonEmailVO emailVO : custVO.getPersonVO().getEmailList()) {
					if (Boolean.TRUE.equals(emailVO.getIsPrimary())) {
						vo.setEmail(emailVO.getEmail());
						break;
					}
				}
			}
			if (CollectionUtils.isNotEmpty((custVO.getCorContactList()))) {
				List<ContactDetailsVO> contactDetailsList = new ArrayList<ContactDetailsVO>();
				ContactDetailsVO contactVO = new ContactDetailsVO();
				for (CorContactVO cVO : custVO.getCorContactList()) {
					contactVO = new ContactDetailsVO();
					contactVO.setPhoneNo(cVO.getContactNo());
					contactVO.setType(cVO.getContactType());
					contactDetailsList.add(contactVO);
				}
				vo.setContactDetailsList(contactDetailsList);
			}
		} else {
			vo.setCompanyName("");
			vo.setBillingAddr1(custVO.getBillAddressVO().getAddr1());
			vo.setBillingAddr2(custVO.getBillAddressVO().getAddr2());
			vo.setBillingAddr3(custVO.getBillAddressVO().getAddr3());
			vo.setCity(custVO.getBillAddressVO().getCity());
			vo.setState(custVO.getBillAddressVO().getState());
			vo.setPostcode(custVO.getBillAddressVO().getPostcode());
			vo.setTaxNo(custVO.getPersonVO().getTaxIdNo());
			if (CollectionUtils.isNotEmpty((custVO.getPersonVO().getEmailList()))) {
				vo.setEmail(custVO.getPersonVO().getEmailList().get(0).getEmail());
				
				for (PersonEmailVO emailVO : custVO.getPersonVO().getEmailList()) {
					if (Boolean.TRUE.equals(emailVO.getIsPrimary())) {
						vo.setEmail(emailVO.getEmail());
						break;
					}
				}
			}
			if (CollectionUtils.isNotEmpty((custVO.getPersonContactList()))) {
				List<ContactDetailsVO> contactDetailsList = new ArrayList<ContactDetailsVO>();
				ContactDetailsVO contactVO = new ContactDetailsVO();
				for (PersonContactVO cVO : custVO.getPersonContactList()) {
					contactVO = new ContactDetailsVO();
					contactVO.setPhoneNo(cVO.getNumber());
					contactVO.setType(cVO.getTypeCd());
					contactDetailsList.add(contactVO);
				}
				vo.setContactDetailsList(contactDetailsList);
			}
		}
		
		if (CollectionUtils.isNotEmpty((custVO.getPersonVO().getEmailList()))) {
	}
			
		return vo;
	}
	
	@Override
	public List<InvoicePosSalesItemVO> getInvoicePosSalesItemList(Long idPosSales) throws BusinessException {
//		return invoicePosSalesDAO.getInvoicePosSalesItemList(idPosSales);
		
		List<InvoicePosSalesItemVO> ls = invoicePosSalesDAO.getInvoicePosSalesItemList(idPosSales);
		
		for (InvoicePosSalesItemVO vo : ls) {
			vo.setCustomerVO(customerDAO.getCustomerInfo(vo.getIdCustomer()));
		}
		
		return ls;
	}
	

	@Override
	public void updateInvoicePosSalesStatus(List<InvoicePosSalesVO> invoicePosSalesList) throws BusinessException {
		invoicePosSalesDAO.updateInvoicePosSalesStatus(invoicePosSalesList);
	}

	@Override
	public List<InvoicePosSalesVO> getInvoicePosSalesList(Map<String, Object> params) throws BusinessException {
		return invoicePosSalesDAO.getInvoicePosSalesList(params);
	}

	@Override
	public int getInvoicePosSalesListSize(Map<String, Object> params) throws BusinessException {
		return invoicePosSalesDAO.getInvoicePosSalesListSize(params);
	}
	
}
