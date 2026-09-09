package com.bcs.zsg.sales.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.service.SurveyReportService;
import com.bcs.zsg.sales.vo.SurveyVO;

public class SurveyReportBOImpl implements SurveyReportBO{
	@Autowired
	private SurveyReportService surveyReportService;
	
	@Override
	public List<SurveyVO> getSurveyReportList(Map<String, Object> params) throws BusinessException{
		return surveyReportService.getSurveyReportList(params);
	}
}
