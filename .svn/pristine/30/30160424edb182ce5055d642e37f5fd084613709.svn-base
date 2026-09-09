package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.dao.CounterSalesReportDAO;
import com.bcs.zsg.sales.vo.CounterSalesReportVO;

public class CounterSalesReportServiceImpl implements CounterSalesReportService {
	
	@Autowired
	private CounterSalesReportDAO counterSalesReportDAO;
	
	@Override
	public List<CounterSalesReportVO> getCounterSalesReportList(Map<String, Object> params) throws BusinessException {
		return counterSalesReportDAO.getCounterSalesReportList(params);
	}
}
