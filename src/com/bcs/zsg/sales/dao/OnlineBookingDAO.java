package com.bcs.zsg.sales.dao;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.OnlineBookingCustomerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPassengerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPaymentVO;

public interface OnlineBookingDAO extends BaseDAO {

	public List<OnlineBookingPaymentVO> getOnlineBookingPaymentList(Map<String, Object> params) throws BusinessException;

	public OnlineBookingCustomerVO getOnlineBookingCustomerVO(Long idBooking) throws BusinessException;

	public int getOnlineBookingPaymentListSize(Map<String, Object> params) throws BusinessException;

	public List<OnlineBookingPaymentVO> getAutogenerateInvoicePaymentList(Map<String, Object> params);

	public OnlineBookingPassengerVO getOnlineBookingPassenger(Map<String, Object> params) throws BusinessException;
	
}
