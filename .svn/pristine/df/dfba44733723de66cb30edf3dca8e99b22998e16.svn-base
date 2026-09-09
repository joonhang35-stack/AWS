package com.bcs.zsg.maintenance.bo;

import java.io.IOException;
import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CustPointVO;
import com.bcs.zsg.maintenance.vo.CustVoucherVO;

public interface CustPointBO {
	
	public CustPointVO getCustomerLoyaltyPoint(Integer customerId) throws IOException;
	public List<CustVoucherVO> getVoucherList() throws IOException;
	public void convertCustomerVoucher(CustPointVO custPointVO) throws BusinessException, IOException;
	public void redeemCustomerVoucher(CustPointVO custPointVO) throws BusinessException, IOException;
}
