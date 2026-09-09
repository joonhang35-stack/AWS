package com.bcs.zsg.crm.dao;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.dao.BaseHibernateDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.sales.dao.InvoiceDAO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class CRMUploadSalesDAOImpl extends BaseHibernateDAO implements CRMUploadSalesDAO {
	
	@Autowired
	private InvoiceDAO invoiceDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public InvoiceVO getInvoicePaxVO(InvoiceVO invoiceVO) throws BusinessException {
		Criteria criteria = createCriteria(InvoicePaxVO.class);
		criteria.add(Restrictions.eq("invId", invoiceVO.getId()));
		criteria.add(Restrictions.eq("statusCode", BaseConstant.STATUS_ACTIVE));
		criteria.addOrder(Order.asc("id"));
		invoiceVO.setInvoicePaxList(criteria.list());
		if (CollectionUtils.isNotEmpty(invoiceVO.getInvoicePaxList())) {
			for (InvoicePaxVO vo : invoiceVO.getInvoicePaxList()) {
				vo.setCustDetailsVO(invoiceDAO.getCustDetails(vo.getCustId(), true, true));
			}
		} else invoiceVO.setInvoicePaxList(new ArrayList<InvoicePaxVO>());
		
		return invoiceVO;
	}
}
