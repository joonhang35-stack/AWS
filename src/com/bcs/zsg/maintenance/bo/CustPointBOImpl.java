package com.bcs.zsg.maintenance.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.service.CustPointService;
import com.bcs.zsg.maintenance.vo.CustPointVO;
import com.bcs.zsg.maintenance.vo.CustVoucherVO;

public class CustPointBOImpl implements CustPointBO{
	@Autowired
	private CustPointService custPointService;
	
	@Override
	public CustPointVO getCustomerLoyaltyPoint(Integer customerId) throws IOException{
		return custPointService.getCustomerLoyaltyPoint(customerId);
	}
	
	@Override
	public List<CustVoucherVO> getVoucherList() throws IOException{
		return custPointService.getVoucherList();
	}
	
	@Override
	public void convertCustomerVoucher(CustPointVO custPointVO) throws BusinessException, IOException{
		custPointService.convertCustomerVoucher(custPointVO);
	}
	
	@Override
	public void redeemCustomerVoucher(CustPointVO custPointVO) throws BusinessException, IOException{
		custPointService.redeemCustomerVoucher(custPointVO);
	}
}
