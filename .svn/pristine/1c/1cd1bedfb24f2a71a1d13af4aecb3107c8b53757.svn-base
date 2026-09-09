package com.bcs.zsg.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.ProductReportDAO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.sales.vo.InvoiceVO;

public class ProductReportServiceImpl implements ProductReportService{
	
	@Autowired
	private ProductReportDAO productReportDAO;
	
	@Override
	public TourDepartureVO getTourDepVOWithRoomingInfoForReport(Long idTourDep) throws BusinessException {
		return productReportDAO.getTourDepVOWithRoomingInfoForReport(idTourDep);
	}
	
	@Override
	public List<InvoiceVO> getTourProfitLossReport(Map<String, Object> params) throws BusinessException {
		return productReportDAO.getTourProfitLossReport(params);
	}
}
