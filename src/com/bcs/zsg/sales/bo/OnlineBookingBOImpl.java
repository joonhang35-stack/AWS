package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.OnlineBookingService;
import com.bcs.zsg.sales.vo.OnlineBookingCustomerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPassengerVO;
import com.bcs.zsg.sales.vo.OnlineBookingPaymentVO;

public class OnlineBookingBOImpl implements OnlineBookingBO {
	
	@Autowired
	private OnlineBookingService onlineBookingService;
	
	@Override
	public int getOnlineBookingPaymentListSize(Map<String, Object> params) throws BusinessException {
		return onlineBookingService.getOnlineBookingPaymentListSize(params);
	}
	
	@Override
	public List<OnlineBookingPaymentVO> getOnlineBookingPaymentList(Map<String, Object> params) throws BusinessException {
		return onlineBookingService.getOnlineBookingPaymentList(params);
	}

	@Override
	public void updateVO(OnlineBookingPaymentVO onlineBookingPaymentVO, String updatedBy) {
		onlineBookingService.updateVO(onlineBookingPaymentVO, updatedBy);
	}

	@Override
	public OnlineBookingCustomerVO getOnlineBookingCustomerByBookingId(Long idBooking) throws BusinessException {
		return onlineBookingService.getOnlineBookingCustomerVO(idBooking);
	}

	@Override
	public void deleteOnlineBookingPayment(OnlineBookingPaymentVO onlineBookingPaymentVO) {
		onlineBookingService.deleteOnlineBookingPayment(onlineBookingPaymentVO);		
	}

	@Override
	public List<OnlineBookingPaymentVO> getAutogenerateInvoicePaymentList(Map<String, Object> params)
			throws BusinessException {
		return onlineBookingService.getAutogenerateInvoicePaymentList(params);
	}

	@Override
	public OnlineBookingPassengerVO getOnlineBookingPassenger(Map<String, Object> params) throws BusinessException {
		return onlineBookingService.getOnlineBookingPassenger(params);
	}
	
	
}
