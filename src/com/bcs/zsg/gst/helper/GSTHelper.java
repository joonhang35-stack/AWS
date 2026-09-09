package com.bcs.zsg.gst.helper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.service.GSTService;

@Component
public class GSTHelper {
	
	private static GSTHelper gstHelper;
	
	@Autowired
	private GSTService gstService;
	
	@PostConstruct
    public static GSTHelper getInstance() {
    	if(gstHelper == null)
    		gstHelper = new GSTHelper();
    	
    	return gstHelper;
    }
    
	public AcctTransVO updateAcctTrans(AcctTransVO vo, String taxCode) throws BusinessException {
		TaxCodeVO taxCodeVO = gstService.getTaxCode(taxCode);
		vo.setTaxCode(taxCodeVO.getCode());
		vo.setTaxRate(taxCodeVO.getRate());
		return vo; 
	}
}
