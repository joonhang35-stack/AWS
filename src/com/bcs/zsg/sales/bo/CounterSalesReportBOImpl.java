package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.CounterSalesReportService;
import com.bcs.zsg.sales.vo.CounterSalesReportVO;

public class CounterSalesReportBOImpl implements CounterSalesReportBO {
	
	@Autowired
	private CounterSalesReportService counterSalesReportService;
	
	@Override
	public List<CounterSalesReportVO> getCounterSalesReportList(Map<String, Object> params) throws BusinessException {
		return counterSalesReportService.getCounterSalesReportList(params);
	}
}
