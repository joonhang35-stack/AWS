package com.bcs.zsg.sales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.dao.SurveyReportDAO;
import com.bcs.zsg.sales.vo.SurveyVO;

public class SurveyReportServiceImpl implements SurveyReportService{
	@Autowired
	private SurveyReportDAO surveyReportDAO;
	
	@Override
	public List<SurveyVO> getSurveyReportList(Map<String, Object> params) throws BusinessException{
		return surveyReportDAO.getSurveyReportList(params);
	}
}
