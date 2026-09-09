package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.OnlineBookingCustomerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPassengerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPaymentVO;

public interface OnlineBookingBO {
	
	public List<OnlineBookingPaymentVO> getOnlineBookingPaymentList(Map<String, Object> params) throws BusinessException;
	
	public List<OnlineBookingPaymentVO> getAutogenerateInvoicePaymentList(Map<String, Object> params) throws BusinessException;

	public void updateVO(OnlineBookingPaymentVO onlineBookingPaymentVO, String updatedBy);

	public OnlineBookingCustomerVO getOnlineBookingCustomerByBookingId(Long idBooking) throws BusinessException;

	public void deleteOnlineBookingPayment(OnlineBookingPaymentVO onlineBookingPaymentVO);

	public int getOnlineBookingPaymentListSize(Map<String, Object> params) throws BusinessException;

	public OnlineBookingPassengerVO getOnlineBookingPassenger(Map<String, Object> params) throws BusinessException;

}
