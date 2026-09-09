package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.sales.dao.OnlineBookingDAO;
import com.bcs.zsg.sales.vo.OnlineBookingCustomerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPassengerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPaymentVO;

public class OnlineBookingServiceImpl implements OnlineBookingService {

	@Autowired
	private OnlineBookingDAO onlineBookingDAO;
	
	@Override
	public int getOnlineBookingPaymentListSize(Map<String, Object> params) throws BusinessException {
		return onlineBookingDAO.getOnlineBookingPaymentListSize(params);
	}
	
	@Override
	public List<OnlineBookingPaymentVO> getOnlineBookingPaymentList(Map<String, Object> params) throws BusinessException {
		return onlineBookingDAO.getOnlineBookingPaymentList(params);
	}

	@Override
	public void updateVO(OnlineBookingPaymentVO onlineBookingPaymentVO, String updatedBy) {
		onlineBookingDAO.update(onlineBookingPaymentVO, updatedBy);		
	}

	@Override
	public OnlineBookingCustomerVO getOnlineBookingCustomerVO(Long idBooking) throws BusinessException {
		return onlineBookingDAO.getOnlineBookingCustomerVO(idBooking);
	}

	@Override
	public void deleteOnlineBookingPayment(OnlineBookingPaymentVO onlineBookingPaymentVO) {
		onlineBookingPaymentVO.setStatusCd(BaseConstant.STATUS_DELETED);
		onlineBookingDAO.update(onlineBookingPaymentVO);
	}

	@Override
	public List<OnlineBookingPaymentVO> getAutogenerateInvoicePaymentList(Map<String, Object> params) {
		return onlineBookingDAO.getAutogenerateInvoicePaymentList(params);
	}

	@Override
	public OnlineBookingPassengerVO getOnlineBookingPassenger(Map<String, Object> params) throws BusinessException {
		return onlineBookingDAO.getOnlineBookingPassenger(params);
	}
	
	
}
