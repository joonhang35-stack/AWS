package com.bcs.zsg.purchase.helper;

import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;
import com.bcs.zsg.purchase.vo.PayeeViewVO;

public class PurchaseUtils {
	public static void mapPayeeViewToBill(PayeeViewVO fromVO, ExOrderBillVO toVO) {
		toVO.setIdPayee(fromVO.getIdPayee());
		toVO.setPayee(fromVO.getPayeeName());
		toVO.setPayeeType(fromVO.getPayeeType());
	}
	
	public static void mapPayeeViewToCashBook(PayeeViewVO fromVO, CashBookVO toVO) {
		toVO.setIdPayee(fromVO.getIdPayee());
		toVO.setPayee(fromVO.getPayeeName());
		toVO.setPayeeType(fromVO.getPayeeType());
	}
}
