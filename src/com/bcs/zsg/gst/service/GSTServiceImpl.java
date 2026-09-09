package com.bcs.zsg.gst.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.zsg.acct.service.FinancialPeriodService;
import com.bcs.zsg.common.helper.AppConfigConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.common.service.LookupService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.dao.gst.GSTDAO;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTGAFVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTMappingStateVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTReportVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryTaxVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.helper.ConstantGST;
import com.bcs.zsg.gst.helper.GSTProcessStatus;
import com.bcs.zsg.gst.helper.GSTTask;
import com.bcs.zsg.gst.helper.GSTType;
import com.bcs.zsg.gst.helper.TaxCodeType;
import com.bcs.zsg.maintenance.helper.MaintConstant;
import com.bcs.zsg.maintenance.service.CorporateProfileService;
import com.bcs.zsg.maintenance.vo.CompanyVO;

@Service("gstService")
public class GSTServiceImpl implements GSTService, Runnable {
	private final static Logger logger = LoggerFactory.getLogger(GSTServiceImpl.class);
	
	@Autowired
	private GSTDAO gstDAO;

	@Autowired
	private CorporateProfileService companyService;

	@Autowired
	private FinancialPeriodService finPeriodService;
	
	@Autowired 
	private transient LookupService lookupService;
	
	private GSTTask gstTask;
	private Map<String, Object> taskParams;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	DecimalFormat df = new DecimalFormat("#0.00");
	private Date mappingDate;
	private Date GSTBPDate;
	
	private GSTGAFVO gstGAFVO = new GSTGAFVO();
	
	@Override
	public List<TaxCodeVO> getTaxCodeList(GSTType gstType) throws BusinessException {
		return gstDAO.getTaxCodeList(TaxCodeType.GST.getValue(), gstType.getValue());
	}
	
	@Override
	public List<String> getNonClaimableTaxList() throws BusinessException {
		return gstDAO.getNonClaimableTaxList();
	}
	
	@Override
	public GSTVO getTaxReturnData(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {

		CompanyTaxVO companyTaxVO = companyService.getCompanyTax(idCompany);
		
		if(companyTaxVO == null)
			throw new BusinessException(CommonErrConstant.ERR_GST_NO_FILLING_TAX);
		
		if(!DatesUtils.isDateMonthYearGreaterOrEqual(dateFrom, companyTaxVO.getGstFilling())) 
			throw new BusinessException(CommonErrConstant.ERR_GST_INVALID_FILLING_PERIOD);
		
		GSTSummaryVO gstSummaryVO = calculateMonthSummary(companyTaxVO, idCompany, dateFrom, dateTo, summaryId);
		
		CompanyVO companyVO = companyService.getCompanyDetails(idCompany);
		
		GSTVO gstVO = new GSTVO();
		gstVO.setGstNo(companyTaxVO.getTaxNumber());
		gstVO.setNameOfBussiness(companyVO.getName());
		
		getMappingDate();
		if(DatesUtils.isDateGreaterOrEqual(dateFrom, mappingDate)) {
			generateFormVO2(gstSummaryVO, gstVO, idCompany);
		} else {
			generateFormVO(gstSummaryVO, gstVO, idCompany);
		}
		//generateFormVO(gstSummaryVO, gstVO, idCompany);
		
		gstVO.setMsicCode1("");
		gstVO.setMsicOutputTax1(0);
		gstVO.setMsicPercentage1(0);
		
		gstVO.setMsicCode2("");
		gstVO.setMsicOutputTax2(0);
		gstVO.setMsicPercentage2(0);
		
		gstVO.setMsicCode3("");
		gstVO.setMsicOutputTax3(0);
		gstVO.setMsicPercentage3(0);
		
		gstVO.setMsicCode4("");
		gstVO.setMsicOutputTax4(0);
		gstVO.setMsicPercentage4(0);
		
		gstVO.setMsicCode5("");
		gstVO.setMsicOutputTax5(0);
		gstVO.setMsicPercentage5(0);

		gstVO.setMsicOutputTax6(gstSummaryVO.getOutputTax());
		gstVO.setMsicPercentage6(gstSummaryVO.getOutputTax() > 0.00 ? 100.00 : 0);
		
		gstVO.setMsicOutputTax7(gstSummaryVO.getOutputTax());
		
		return gstVO;
	}

	private void generateFormVO(GSTSummaryVO gstSummaryVO, GSTVO gstVO, Long idCompany) throws BusinessException {
		
		gstVO.setDateStart(gstSummaryVO.getDateFrom());
		gstVO.setDateEnd(gstSummaryVO.getDateTo());
		gstVO.setDatePayment(getGracePeriod(gstVO.getDateEnd()));

		gstVO.setTotalSupply(gstSummaryVO.getOutputAmount());
		gstVO.setTotalOutputTax(gstSummaryVO.getOutputTax());
		gstVO.setTotalAcquisitions(gstSummaryVO.getInputAmount());
		gstVO.setTotalInputTax(gstSummaryVO.getInputTax());

		if(gstSummaryVO.getRefundCarryForward() != null)
			gstVO.setCarryForwardRefund(gstSummaryVO.getRefundCarryForward().equals(BaseConstant.YES) ? true : false);
		else 
			gstVO.setCarryForwardRefund(false);
		
		gstVO.setAmountClaimable(gstSummaryVO.getAmountClaimable());
		gstVO.setAmountPayable(Math.abs(gstSummaryVO.getAmountPayable()));
		
		Map<String, Double> hmTaxItem = new HashMap<String, Double>();
		final String TaxSuffix = "_TAX";
		
		for(GSTSummaryTaxVO taxCodeVO : gstSummaryVO.getAllTaxCodeVOList()) {
			hmTaxItem.put(taxCodeVO.getTaxCode(), taxCodeVO.getAmount());
			hmTaxItem.put(taxCodeVO.getTaxCode() + TaxSuffix, taxCodeVO.getTaxAmount());
		}
		
		gstVO.setTotalLocalZeroRatedSupplies(checkNull(hmTaxItem, GSTType.OUT_ZRL));
		gstVO.setTotalExportSupplies(checkNull(hmTaxItem, GSTType.OUT_ZRE));
		gstVO.setTotalExemptSupplies(checkNull(hmTaxItem, GSTType.OUT_ES43) + checkNull(hmTaxItem, GSTType.OUT_ES));
		gstVO.setTotalSuppliesGrantedRelief(checkNull(hmTaxItem, GSTType.OUT_RS));
		
		gstVO.setTotalGoodsImportedApproved(checkNull(hmTaxItem, GSTType.IN_IS));
		gstVO.setTotalSuspendedUnder14(checkNull(hmTaxItem, GSTType.IN_IS));
		
		//Special change to get only Fixed Asset data
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCompany", idCompany);
		params.put("dateFrom", gstSummaryVO.getDateFrom());
		params.put("dateTo", gstSummaryVO.getDateTo());
		params.put("accountType", "FA");
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		
		double totalFixedAsset = gstDAO.getTaxCodeTotal(params);
		gstVO.setTotalCapitalGoodsAcquired(totalFixedAsset);
		
		gstVO.setTotalBadDebtRelief(checkNull(hmTaxItem, GSTType.IN_AJP) + checkNull(hmTaxItem, GSTType.IN_AJP, TaxSuffix));
		gstVO.setTotalBadDebtRecovered(checkNull(hmTaxItem, GSTType.OUT_AJS) + checkNull(hmTaxItem, GSTType.OUT_AJS, TaxSuffix));
		
	}
	
	private void generateFormVO2(GSTSummaryVO gstSummaryVO, GSTVO gstVO, Long idCompany) throws BusinessException {
		
		gstVO.setDateStart(gstSummaryVO.getDateFrom());
		gstVO.setDateEnd(gstSummaryVO.getDateTo());
		gstVO.setDatePayment(getGracePeriod(gstVO.getDateEnd()));

		gstVO.setTotalSupply(gstSummaryVO.getOutputAmount());
		gstVO.setTotalOutputTax(gstSummaryVO.getOutputTax());
		gstVO.setTotalAcquisitions(gstSummaryVO.getInputAmount());
		gstVO.setTotalInputTax(gstSummaryVO.getInputTax());

		if(gstSummaryVO.getRefundCarryForward() != null)
			gstVO.setCarryForwardRefund(gstSummaryVO.getRefundCarryForward().equals(BaseConstant.YES) ? true : false);
		else 
			gstVO.setCarryForwardRefund(false);
		
		gstVO.setAmountClaimable(gstSummaryVO.getAmountClaimable());
		gstVO.setAmountPayable(Math.abs(gstSummaryVO.getAmountPayable()));
		
		GSTMappingStateVO gstMappingStateVO = null;
		List<String> taxCodeList = null;
		for(GSTSummaryTaxVO taxCodeVO : gstSummaryVO.getAllTaxCodeVOList()) {
			
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_10);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalLocalZeroRatedSupplies(
						new BigDecimal(String.valueOf(gstVO.getTotalLocalZeroRatedSupplies())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalLocalZeroRatedSupplies(taxCodeVO.getAmount());												//10)	Total Value of Local Zero-Rated Supplies (ZRL)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_11);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalExportSupplies(
						new BigDecimal(String.valueOf(gstVO.getTotalExportSupplies())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalExportSupplies(taxCodeVO.getAmount());														//11)	Total Value of Export Supplies (ZRE)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_12);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalExemptSupplies(
						new BigDecimal(String.valueOf(gstVO.getTotalExemptSupplies())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalExemptSupplies(taxCodeVO.getAmount());														//12)	Total Value of Exempt Supplies
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_13);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalSuppliesGrantedRelief(
						new BigDecimal(String.valueOf(gstVO.getTotalSuppliesGrantedRelief())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalSuppliesGrantedRelief(taxCodeVO.getAmount());													//13)	Total Value of Supplies Granted GST Relief (RS)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_14);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalGoodsImportedApproved(
						new BigDecimal(String.valueOf(gstVO.getTotalGoodsImportedApproved())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalGoodsImportedApproved(taxCodeVO.getAmount());													//14)	Total Value of Goods Imported Under Approved Trader Scheme (IS)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_15);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalSuspendedUnder14(
						new BigDecimal(String.valueOf(gstVO.getTotalSuspendedUnder14())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalSuspendedUnder14(taxCodeVO.getAmount());														//15)	Total Value of GST Suspended under item 14 (IS)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_16);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalCapitalGoodsAcquired(
						new BigDecimal(String.valueOf(gstVO.getTotalCapitalGoodsAcquired())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount()))).doubleValue()
					);
					//gstVO.setTotalCapitalGoodsAcquired(taxCodeVO.getAmount());														//16)	Total Value of Capital Goods Acquired (TX)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_17);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalBadDebtRelief(
						new BigDecimal(String.valueOf(gstVO.getTotalBadDebtRelief())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount())))
						.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount()))).doubleValue()
					);
					//gstVO.setTotalBadDebtRelief(taxCodeVO.getAmount() + taxCodeVO.getTaxAmount());								//17)	Total value of Bad Debt Relief Inclusive Tax (AJP)
				}
			}
			gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_18);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (taxCodeVO.getTaxCode().equals(vo)) {
					gstVO.setTotalBadDebtRecovered(
						new BigDecimal(String.valueOf(gstVO.getTotalBadDebtRecovered())).add(new BigDecimal(String.valueOf(taxCodeVO.getAmount())))
						.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount()))).doubleValue()
					);
					//gstVO.setTotalBadDebtRecovered(taxCodeVO.getAmount() + taxCodeVO.getTaxAmount());							//18)	Total value of Bad Debt Recovered Inclusive Tax (AJS)
				}
			}
		}
	}
	
	private double checkNull(Map<String, Double> hm, GSTType gstType) {
		return checkNull(hm, gstType, null);
	}
	private double checkNull(Map<String, Double> hm, GSTType gstType, String taxSurffix) {
		String key = gstType.getValue() + (taxSurffix == null ? "" : taxSurffix);
		
		return (hm.containsKey(key) ? hm.get(key) : 0.00);
	}
	
	@Override
	public List<GSTSummaryVO> getMonthlySummaryList(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		Map<String, Object> params = initCommonParam(idCompany, dateFrom, dateTo, false);
		
		return gstDAO.getMonthlySummaryList(params);
	}
	
	private Map<String, Object> initCommonParam(Long idCompany, Date dateFrom, Date dateTo, boolean isDateBetween) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idCompany", idCompany);
		
		if(isDateBetween)
			params.put("dateBetween", dateFrom);	//Takes one parameter only
		else {
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
		}
		return params;
	}

	@Override
	public GSTSummaryVO getTaxSummary(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {
		
		GSTSummaryVO gstSummaryVO = new GSTSummaryVO();
		gstSummaryVO.setDateFrom(dateFrom);
		gstSummaryVO.setDateTo(dateTo);
		gstSummaryVO.setGstVO(new GSTVO());
		
		getMappingDate();
		if(DatesUtils.isDateGreaterOrEqual(dateFrom, mappingDate)) {
			calculateTaxCodeTotal2(gstSummaryVO, getTaxSummaryList(idCompany, dateFrom, dateTo, summaryId));
			generateFormVO2(gstSummaryVO, gstSummaryVO.getGstVO(), idCompany);
		} else {
			calculateTaxCodeTotal(gstSummaryVO, getTaxSummaryList(idCompany, dateFrom, dateTo, summaryId));
			generateFormVO(gstSummaryVO, gstSummaryVO.getGstVO(), idCompany);
		}
		
		return gstSummaryVO;
	}
	
	private void getMappingDate() {
		String strDate = lookupService.getSysParamValue("GST", "GST_MAPPING_DATE");
	    try {
	    	mappingDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			logger.error("Mapping date retrieve error.", e);
		}
	}
	
	private void getGSTBPDate() {
		String strDate = lookupService.getSysParamValue("GST", "GST_BP_DATE");
	    try {
	    	GSTBPDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			logger.error("GST BP date retrieve error.", e);
		}
	}
	
	private List<TaxCodeVO> getTaxSummaryList(Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {
		Map<String, Object> params = initCommonParam(idCompany, dateFrom, dateTo, false);
		params.put("taxType", TaxCodeType.GST.getValue());
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);

		//When more than 1 month of period query/summaryId is greater than 1
		if(summaryId.size() > 0) {
			params.put("summaryId", summaryId);
		}

		//Check if same month year with today's date
		// - To include real time data from account trans table or just query data from gst summary tax table
		if(summaryId.size() == 0 || DatesUtils.isSameMonthYear(new Date(), dateFrom) || DatesUtils.isSameMonthYear(new Date(), dateTo)) {
			// TODO HS commented
			//params.put("dateFrom", com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth((Date) params.get("dateTo")));
			getGSTBPDate();
			params.put("bpDate", GSTBPDate);
			
			return gstDAO.getTaxSummaryList(params, true);
		} else {
			if(summaryId.size() > 0)
				return gstDAO.getTaxSummaryList(params, false);
			else
				return new ArrayList<TaxCodeVO>();
		}
		
	}

	@Override
	public boolean isGSTPeriod(Long idCompany, Date dateFrom) throws BusinessException {
		CompanyTaxVO companyTaxVO =  companyService.getCompanyTax(idCompany);
		
		if (companyTaxVO == null)
			return false;
		
		if (DatesUtils.isDateGreaterOrEqual(dateFrom, companyTaxVO.getValidFrom()) && DatesUtils.isDateLessOrEqual(dateFrom, companyTaxVO.getValidTo())) 
			return true;
		
		return false;
	}
	
	@Override
	public String getGSTProcessStatus(Long idCompany, Date dateFrom) throws BusinessException {
		GSTSummaryVO gstSummaryVO = gstDAO.getMonthSummary(initCommonParam(idCompany, dateFrom, dateFrom, true));
		
		String rtn = GSTProcessStatus.PENDING.getValue();
		
		if(gstSummaryVO != null) {
			rtn = gstSummaryVO.getProcessed();
		}
		
		return rtn;
	}
	
    public void processCompanyMonthClose() throws BusinessException {
    	//Gets the previous month
    	/*Date dateFrom = DatesUtils.getDayTimeStart(com.bcs.zsg.core.helper.DateUtils
    									.getStartDateByMonth(DateUtils.addMonths(new Date(), -1))).getTime();
    	Date dateTo = com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(dateFrom);
    	
    	Date date2MonthFrom = com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth(DateUtils.addMonths(dateFrom, -1));
    	Date date2MonthTo = com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(date2MonthFrom);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
    	List<CompanyVO> companyVOList = companyService.getCompanyList();

		logger.info(" --------- GST Month Close scheduled task [START] --------- ");
		logger.info(" ------ " + sdf.format(dateFrom) + " to " + sdf.format(dateTo) + " ------ ");
		
    	for(CompanyVO companyVO : companyVOList) {
			logger.info(" Processing company [" + companyVO.getName() + "]... ");
			
			//Close month after grace period
			updateGSTMonthClose(companyVO.getId(), "SYSTEM", GSTProcessStatus.PENDING.getValue(),
									date2MonthFrom, date2MonthTo, false, false, companyTaxVO);
			
			//For adding entry for previous month
			updateGSTMonthClose(companyVO.getId(), "SYSTEM", GSTProcessStatus.PENDING.getValue(),
									dateFrom, dateTo, false, false, companyTaxVO);
		
    	}
    	
		logger.info(" --------- GST Month Close scheduled task [END] --------- ");*/
		
    	// TODO --- changed by HS ---
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
    	List<CompanyVO> companyVOList = companyService.getCompanyList();

		logger.info(" --------- GST Month Close scheduled task [START] --------- ");
		
    	for(CompanyVO companyVO : companyVOList) {
			logger.info(" Processing company [" + companyVO.getId() + " - " + companyVO.getName() + "]... ");
			
			CompanyTaxVO companyTaxVO = companyService.getCompanyTax(companyVO.getId());
			
			if (companyTaxVO != null) {
				int taxablePeriod = StringUtils.isEmpty(companyTaxVO.getPeriod()) ? 1 : Integer.parseInt(companyTaxVO.getPeriod());
				
				Date dateFrom = DatesUtils.getDayTimeStart(com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth(DateUtils.addMonths(new Date(), -taxablePeriod))).getTime();
				Date dateTo = com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(DateUtils.addMonths(new Date(), -1));
	
//				Date date2MonthFrom = com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth(DateUtils.addMonths(dateFrom, -taxablePeriod));
//				Date date2MonthTo = com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(DateUtils.addMonths(dateFrom, -1));
				Date date2MonthFrom = com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth(DateUtils.addMonths(dateFrom, -1));
				Date date2MonthTo = com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(DateUtils.addMonths(dateTo, -1));
				
				logger.info(" ------ " + sdf.format(dateFrom) + " to " + sdf.format(dateTo) + " ------ ");
				logger.info(" ------ " + sdf.format(date2MonthFrom) + " to " + sdf.format(date2MonthTo) + " ------ ");
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("idCompany", companyVO.getId());
				params.put("dateFromEq", date2MonthFrom);
				params.put("dateToEq", date2MonthTo);
				
				GSTSummaryVO gstSummaryVO = gstDAO.getMonthSummary(params);
				if (gstSummaryVO != null) {
					//Close month after grace period
					updateGSTMonthCloseByGracePeriod(companyVO.getId(), "SYSTEM", GSTProcessStatus.PENDING.getValue(),
										date2MonthFrom, date2MonthTo, false, false, companyTaxVO);
				}
					
				gstSummaryVO = gstDAO.getMonthSummary(initCommonParam(companyVO.getId(), dateFrom, dateFrom, true));
				if (gstSummaryVO == null) {
					//For adding entry for previous month
					updateGSTMonthClose(companyVO.getId(), "SYSTEM", GSTProcessStatus.PENDING.getValue(),
											dateFrom, dateTo, false, false, companyTaxVO);
				}
			}
    	}
		logger.info(" --------- GST Month Close scheduled task [END] --------- ");
		// --- end changed by HS ---
	}
    
    
		    
	@Override
	public void processGSTMonthClose(Long idCompany, String loginName, String processStatus, 
										Date dateFrom, Date dateTo, boolean isLockUnlock, boolean isSubmit) throws BusinessException {
		CompanyTaxVO companyTaxVO = companyService.getCompanyTax(idCompany);
		// TODO HS commented
		/*updateGSTMonthClose(idCompany, loginName, processStatus, 
				com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth(financialMonth),
				com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(financialMonth), isLockUnlock, isSubmit, companyTaxVO);*/
		updateGSTMonthClose(idCompany, loginName, processStatus, dateFrom, dateTo, isLockUnlock, isSubmit, companyTaxVO);
	}
	
	private Date getGracePeriod(Date dateTo) {
		int gracePeriod = Integer.valueOf(LookupItemUtils.getGlobalConfigValue(MaintConstant.GLOBAL_CD_GLOBAL, 
				MaintConstant.GLOBAL_CD_GST_GAF_GRACE_PERIOD));
		
		if(gracePeriod == 30) {
			//30 stands for max of month
			return com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(DateUtils.addMonths(dateTo, 1));
		} else
			return DateUtils.addDays(dateTo, gracePeriod);
		
	}
	
	private void updateGSTMonthCloseByGracePeriod(Long idCompany, String loginName, String processStatus, 
		Date dateFrom, Date dateTo, boolean isLockUnlock, boolean isSubmit, CompanyTaxVO companyTaxVO) throws BusinessException {
		
		GSTSummaryVO gstSummaryVO = calculateMonthSummary(companyTaxVO, idCompany, dateFrom, dateTo, new ArrayList<Long>());
		
		//End process when summaryVO is null
		if(gstSummaryVO == null)
			return;
		
		//Update GST SUMMARY Database
		if(new Date().after(getGracePeriod(dateTo))) {
			//gstSummaryVO.setProcessed(GSTProcessStatus.SUBMIT.getValue());
		} else {
			if(isLockUnlock) {
				if(GSTProcessStatus.PENDING.getValue().equals(gstSummaryVO.getProcessed()))
					gstSummaryVO.setProcessed(GSTProcessStatus.LOCKED.getValue());
				else
					gstSummaryVO.setProcessed(GSTProcessStatus.PENDING.getValue());	
			} else {
				gstSummaryVO.setProcessed(processStatus);
			}
		}

		gstSummaryVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		
		if(gstSummaryVO.getId() == null) {
			gstSummaryVO.setIdCompany(idCompany);
			gstDAO.insert(gstSummaryVO, loginName);

			GSTSummaryVO savedVO = gstDAO.getMonthSummary(initCommonParam(idCompany, dateFrom, dateTo, false));
			gstSummaryVO.setId(savedVO.getId());
			
		} else {
			gstDAO.update(gstSummaryVO, loginName);
			
			Map<String, Object> paramTaxSummary = new HashMap<String, Object>();
			paramTaxSummary.put("idGSTSummary", gstSummaryVO.getId());
			paramTaxSummary.put("statusCode", BaseConstant.STATUS_ACTIVE);
			
			List<GSTSummaryTaxVO> deleteList = gstDAO.getTaxSummaryVOList(paramTaxSummary);
			
			for(GSTSummaryTaxVO summaryTaxVO : deleteList) {
				summaryTaxVO.setStatusCode(BaseConstant.STATUS_DELETED);
				gstDAO.delete(summaryTaxVO);
			}
		}
		
		//Add in updated latest amount for all tax code
		insertSummaryTaxVO(gstSummaryVO, gstSummaryVO.getAllTaxCodeVOList(), loginName);
	}
	
	//Heavy Process
	private void updateGSTMonthClose(Long idCompany, String loginName, String processStatus, 
		Date dateFrom, Date dateTo, boolean isLockUnlock, boolean isSubmit, CompanyTaxVO companyTaxVO) throws BusinessException {
		
		GSTSummaryVO gstSummaryVO = calculateMonthSummary(companyTaxVO, idCompany, dateFrom, dateTo, new ArrayList<Long>());
		
		//End process when summaryVO is null
		if(gstSummaryVO == null)
			return;
		
		//Update GST SUMMARY Database
		if(isSubmit) {
			//gstSummaryVO.setProcessed(GSTProcessStatus.SUBMIT.getValue());
		} else {
			if(isLockUnlock) {
				if(GSTProcessStatus.PENDING.getValue().equals(gstSummaryVO.getProcessed()))
					gstSummaryVO.setProcessed(GSTProcessStatus.LOCKED.getValue());
				else
					gstSummaryVO.setProcessed(GSTProcessStatus.PENDING.getValue());	
			} else {
				gstSummaryVO.setProcessed(processStatus);
			}
		}

		gstSummaryVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		
		if(gstSummaryVO.getId() == null) {
			gstSummaryVO.setIdCompany(idCompany);
			gstDAO.insert(gstSummaryVO, loginName);

			GSTSummaryVO savedVO = gstDAO.getMonthSummary(initCommonParam(idCompany, dateFrom, dateTo, false));
			gstSummaryVO.setId(savedVO.getId());
			
		} else {
			gstDAO.update(gstSummaryVO, loginName);
			
			Map<String, Object> paramTaxSummary = new HashMap<String, Object>();
			paramTaxSummary.put("idGSTSummary", gstSummaryVO.getId());
			paramTaxSummary.put("statusCode", BaseConstant.STATUS_ACTIVE);
			
			List<GSTSummaryTaxVO> deleteList = gstDAO.getTaxSummaryVOList(paramTaxSummary);
			
			for(GSTSummaryTaxVO summaryTaxVO : deleteList) {
				summaryTaxVO.setStatusCode(BaseConstant.STATUS_DELETED);
				gstDAO.delete(summaryTaxVO);
			}
		}
		
		//Add in updated latest amount for all tax code
		insertSummaryTaxVO(gstSummaryVO, gstSummaryVO.getAllTaxCodeVOList(), loginName);
		
	}
	
	private void insertSummaryTaxVO(GSTSummaryVO gstSummaryVO, List<GSTSummaryTaxVO> insertList, String loginName) {
		for(GSTSummaryTaxVO summaryTaxVO : insertList) {
			summaryTaxVO.setIdGSTSummary(gstSummaryVO.getId());
			summaryTaxVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			gstDAO.insert(summaryTaxVO, loginName);
		}
		
	}
	
	private GSTSummaryVO calculateMonthSummary(CompanyTaxVO companyTaxVO, Long idCompany, Date dateFrom, Date dateTo, List<Long> summaryId) throws BusinessException {
		
		//Only Process GST if period greater than GST filling date
		if(companyTaxVO == null || !DatesUtils.isDateMonthYearGreaterOrEqual(dateFrom, companyTaxVO.getGstFilling())) 
			return null;
		
		GSTSummaryVO gstSummaryVO = gstDAO.getMonthSummary(initCommonParam(idCompany, dateFrom, dateTo, false));
		//Update gst summary, gst summary tax Table
		if(gstSummaryVO == null) {
			gstSummaryVO = new GSTSummaryVO();
			gstSummaryVO.setIdCompany(idCompany);
			gstSummaryVO.setDateFrom(dateFrom);
			gstSummaryVO.setDateTo(dateTo);
		}
		// TODO HS commented
		/*GSTSummaryVO previousMonthSummaryVO = gstDAO.getMonthSummary(initCommonParam(idCompany, 
																DateUtils.addMonths(dateFrom, -1), // TODO 00000
																com.bcs.zsg.core.helper.DateUtils
																	.getEndDateByMonth(DateUtils.addMonths(dateTo, -1)), false));*/
		
		int taxablePeriod = StringUtils.isEmpty(companyTaxVO.getPeriod()) ? 1 : Integer.parseInt(companyTaxVO.getPeriod());
		GSTSummaryVO previousMonthSummaryVO = gstDAO.getMonthSummary(initCommonParam(idCompany, 
				DateUtils.addMonths(dateFrom, -taxablePeriod),
				com.bcs.zsg.core.helper.DateUtils.getEndDateByMonth(DateUtils.addMonths(dateFrom, -1)), false));
		
		//Get the previous closing balance
		if(previousMonthSummaryVO == null)
			gstSummaryVO.setOpeningBalance(0.00);
		else
			gstSummaryVO.setOpeningBalance(previousMonthSummaryVO.getClosingBalance());
		
		getMappingDate();
		if(DatesUtils.isDateGreaterOrEqual(dateFrom, mappingDate)) {
			calculateTaxCodeTotal2(gstSummaryVO, getTaxSummaryList(idCompany, dateFrom, dateTo, summaryId));
		} else {
			calculateTaxCodeTotal(gstSummaryVO, getTaxSummaryList(idCompany, dateFrom, dateTo, summaryId));
		}
		//Get latest amount based on tax code
		//calculateTaxCodeTotal(gstSummaryVO, getTaxSummaryList(idCompany, dateFrom, dateTo, summaryId));
		
		//Update Carry Forward Key and Closing Balance
		gstSummaryVO.setRefundCarryForward(companyTaxVO.getRefundCarryForward());
		
		//Closing Balance amount are claimable amount park at custom
		if(gstSummaryVO.getAmountPayable() > 0.00 ||
				gstSummaryVO.getRefundCarryForward().equals(BaseConstant.NO))
			gstSummaryVO.setClosingBalance(0.00);
		else
			gstSummaryVO.setClosingBalance(gstSummaryVO.getAmountClaimable());
		
		return gstSummaryVO;
	}
	
	private void calculateTaxCodeTotal(GSTSummaryVO gstSummaryVO, List<TaxCodeVO> taxCodeSumVOList) throws BusinessException {
		BigDecimal inputTaxTotal = new BigDecimal(0.00);
		BigDecimal outputTaxTotal = new BigDecimal(0.00);
		BigDecimal inputAmountTotal = new BigDecimal(0.00);
		BigDecimal outputAmountTotal = new BigDecimal(0.00);
		
		gstSummaryVO.setInputClaimableVOList(new ArrayList<GSTSummaryTaxVO>());
		gstSummaryVO.setInputNonClaimableVOList(new ArrayList<GSTSummaryTaxVO>());
		gstSummaryVO.setOutputVOList(new ArrayList<GSTSummaryTaxVO>());
		gstSummaryVO.setOutputNonClaimableVOList(new ArrayList<GSTSummaryTaxVO>());
		
		for(TaxCodeVO taxCodeVO : taxCodeSumVOList) {
			if(taxCodeVO.getRate() > 0.00) {
				if(taxCodeVO.getSubType().equals(GSTType.INPUT.getValue())) {
					if(taxCodeVO.getCode().equals(GSTType.IN_TX.getValue()) 
							|| taxCodeVO.getCode().equals(GSTType.IN_IM.getValue())
							|| taxCodeVO.getCode().equals(GSTType.IN_TX_E43.getValue())
							|| taxCodeVO.getCode().equals(GSTType.IN_TX_RE.getValue())) {
						inputTaxTotal = inputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));
						inputAmountTotal = inputAmountTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTotalAmount())));
					}
					if(taxCodeVO.getCode().equals(GSTType.IN_AJP.getValue()) ) {
						inputTaxTotal = inputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));
					}
				} else {
					if(taxCodeVO.getCode().equals(GSTType.OUT_SR.getValue()) 
							|| taxCodeVO.getCode().equals(GSTType.OUT_DS.getValue())) {
						outputTaxTotal = outputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));
						outputAmountTotal = outputAmountTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTotalAmount())));
					}
					if(taxCodeVO.getCode().equals(GSTType.OUT_AJS.getValue()) ) {
						outputTaxTotal = outputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));
					}
				}
			}
			
			GSTSummaryTaxVO summaryTaxVO = new GSTSummaryTaxVO();
			summaryTaxVO.setType(taxCodeVO.getSubType());
			summaryTaxVO.setTaxCode(taxCodeVO.getCode());
			summaryTaxVO.setAmount(taxCodeVO.getTotalAmount());
			summaryTaxVO.setRate(taxCodeVO.getRate());
			summaryTaxVO.setTaxAmount(taxCodeVO.getTaxAmount());
			
			if(taxCodeVO.getSubType().equals(GSTType.INPUT.getValue())) {
				
				if(taxCodeVO.getCode().equals(GSTType.IN_BL.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_NR.getValue()) 
						|| taxCodeVO.getCode().equals(GSTType.IN_ZP.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_EP.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_OP.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_TX_N43.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_GP.getValue())) {

					gstSummaryVO.getInputNonClaimableVOList().add(summaryTaxVO);
				} else {
					gstSummaryVO.getInputClaimableVOList().add(summaryTaxVO);
				}
			} else {
				if(taxCodeVO.getCode().equals(GSTType.OUT_GS.getValue())
						|| taxCodeVO.getCode().equals(GSTType.OUT_OS.getValue()) ) {
					
					gstSummaryVO.getOutputNonClaimableVOList().add(summaryTaxVO);
				} else {
					gstSummaryVO.getOutputVOList().add(summaryTaxVO);
				}
			}
		}
		
		gstSummaryVO.setOutputAmount(outputAmountTotal.doubleValue());
		gstSummaryVO.setOutputTax(outputTaxTotal.doubleValue());
		
		gstSummaryVO.setInputAmount(inputAmountTotal.doubleValue());
		gstSummaryVO.setInputTax(inputTaxTotal.doubleValue());
		
		//Update Amount Payable/Claimable 
		// Logic = Previous Close Balance + input tax - output tax
		// Previous Close Balance will always be positive
		BigDecimal amountDifferent = new BigDecimal(String.valueOf(gstSummaryVO.getInputTax()))
												.add(new BigDecimal(String.valueOf(gstSummaryVO.getOpeningBalance())))
												.subtract(new BigDecimal(String.valueOf(gstSummaryVO.getOutputTax())));
		
		if(amountDifferent.doubleValue() > 0.00) {
			gstSummaryVO.setAmountClaimable(amountDifferent.doubleValue());
			gstSummaryVO.setAmountPayable(0.00);
		} else {
			gstSummaryVO.setAmountClaimable(0.00);
			gstSummaryVO.setAmountPayable(Math.abs(amountDifferent.doubleValue()));
		}
	}
	
	private void calculateTaxCodeTotal2(GSTSummaryVO gstSummaryVO, List<TaxCodeVO> taxCodeSumVOList) throws BusinessException {
		BigDecimal inputTaxTotal = new BigDecimal(0.00);
		BigDecimal outputTaxTotal = new BigDecimal(0.00);
		BigDecimal inputAmountTotal = new BigDecimal(0.00);
		BigDecimal outputAmountTotal = new BigDecimal(0.00);
		
		gstSummaryVO.setInputClaimableVOList(new ArrayList<GSTSummaryTaxVO>());
		gstSummaryVO.setInputNonClaimableVOList(new ArrayList<GSTSummaryTaxVO>());
		gstSummaryVO.setOutputVOList(new ArrayList<GSTSummaryTaxVO>());
		gstSummaryVO.setOutputNonClaimableVOList(new ArrayList<GSTSummaryTaxVO>());
		
		GSTMappingStateVO gstMappingStateVO = null;
		List<String> taxCodeList5A = new ArrayList<>();
		List<String> taxCodeList5B = new ArrayList<>();
		List<String> taxCodeList6A = new ArrayList<>();
		List<String> taxCodeList6B = new ArrayList<>();
		for(TaxCodeVO taxCodeVO : taxCodeSumVOList) {
			
			if (taxCodeVO.getSubType().equals(GSTType.INPUT.getValue())) {
				
				gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_6A);
				taxCodeList6A = convertTaxCode(gstMappingStateVO.getTaxCode());
				for (String vo : taxCodeList6A) {
					if (taxCodeVO.getCode().equals(vo)) {
						//inputTaxTotal = inputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));			//6b
						inputAmountTotal = inputAmountTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTotalAmount())));	//6a
					}
				}
				
				gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_6B);
				taxCodeList6B = convertTaxCode(gstMappingStateVO.getTaxCode());
				// Remove those in same tax code, to find out the adjustment tax code
				for (String vo : taxCodeList6B) {
					if (taxCodeVO.getCode().equals(vo)) {
						inputTaxTotal = inputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));			//6b
					}
				}
			} else {
				// GST-03 Field 5b
				gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_5A);
				taxCodeList5A = convertTaxCode(gstMappingStateVO.getTaxCode());
				for (String vo : taxCodeList5A) {
					if (taxCodeVO.getCode().equals(vo)) {
						//outputTaxTotal = outputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));         //5b
						outputAmountTotal = outputAmountTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTotalAmount()))); //5a
					}
				}
				
				gstMappingStateVO = gstDAO.getGSTMappingState(taxCodeVO.getDateTo(), ConstantGST.GST_FIELD_5B);
				taxCodeList5B = convertTaxCode(gstMappingStateVO.getTaxCode());
				// Remove those in same tax code, to find out the adjustment tax code
				for (String vo : taxCodeList5B) {
					if (taxCodeVO.getCode().equals(vo)) {
						outputTaxTotal = outputTaxTotal.add(new BigDecimal(String.valueOf(taxCodeVO.getTaxAmount())));         //5b
					}
				}
			}
			
			GSTSummaryTaxVO summaryTaxVO = new GSTSummaryTaxVO();
			summaryTaxVO.setDateFrom(taxCodeVO.getDateFrom());
			summaryTaxVO.setDateTo(taxCodeVO.getDateTo());
			summaryTaxVO.setType(taxCodeVO.getSubType());
			summaryTaxVO.setTaxCode(taxCodeVO.getCode());
			summaryTaxVO.setAmount(taxCodeVO.getTotalAmount());
			summaryTaxVO.setRate(taxCodeVO.getRate());
			summaryTaxVO.setTaxAmount(taxCodeVO.getTaxAmount());
			
			if(taxCodeVO.getSubType().equals(GSTType.INPUT.getValue())) {
				if(taxCodeVO.getCode().equals(GSTType.IN_BL.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_NR.getValue()) 
						|| taxCodeVO.getCode().equals(GSTType.IN_ZP.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_EP.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_OP.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_TX_N43.getValue())
						|| taxCodeVO.getCode().equals(GSTType.IN_GP.getValue())) {

					gstSummaryVO.getInputNonClaimableVOList().add(summaryTaxVO);
				} else {
					gstSummaryVO.getInputClaimableVOList().add(summaryTaxVO);
				}
			} else {
				if(taxCodeVO.getCode().equals(GSTType.OUT_GS.getValue())
						|| taxCodeVO.getCode().equals(GSTType.OUT_OS.getValue())
						|| taxCodeVO.getCode().equals(GSTType.OUT_NS.getValue())) {
					
					gstSummaryVO.getOutputNonClaimableVOList().add(summaryTaxVO);
				} else {
					gstSummaryVO.getOutputVOList().add(summaryTaxVO);
				}
			}
		}
		
		gstSummaryVO.setOutputAmount(outputAmountTotal.doubleValue());
		gstSummaryVO.setOutputTax(outputTaxTotal.doubleValue());
		
		gstSummaryVO.setInputAmount(inputAmountTotal.doubleValue());
		gstSummaryVO.setInputTax(inputTaxTotal.doubleValue());
		
		//Update Amount Payable/Claimable 
		// Logic = Previous Close Balance + input tax - output tax
		// Previous Close Balance will always be positive
		BigDecimal amountDifferent = new BigDecimal(String.valueOf(gstSummaryVO.getInputTax()))
												.add(new BigDecimal(String.valueOf(gstSummaryVO.getOpeningBalance())))
												.subtract(new BigDecimal(String.valueOf(gstSummaryVO.getOutputTax())));
		
		if(amountDifferent.doubleValue() > 0.00) {
			gstSummaryVO.setAmountClaimable(amountDifferent.doubleValue());
			gstSummaryVO.setAmountPayable(0.00);
		} else {
			gstSummaryVO.setAmountClaimable(0.00);
			gstSummaryVO.setAmountPayable(Math.abs(amountDifferent.doubleValue()));
		}
	}
	
	private List<String> convertTaxCode(String input) {
		List<String> taxCodeSel = new ArrayList<String>();
		
		if (input!=null) {
			taxCodeSel = new LinkedList<String>(Arrays.asList(input.replace(" ", "").split(",")));
		}
		return taxCodeSel;
	}

	@Override
	public TaxCodeVO getTaxCode(String taxCode) throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("taxType", TaxCodeType.GST.getValue());
		params.put("code", taxCode);
		params.put("statusCode", BaseConstant.STATUS_ACTIVE);
		
		return gstDAO.getTaxCode(params);
	}
	
	@Override
	public void run() {
		
		if(gstTask == null)
			return;
		
		try {
			Long idCompany = (Long)taskParams.get("idCompany");
			Date dateFrom = (Date)taskParams.get("financialMonth");
			String loginName = (String)taskParams.get("loginName");
			
			if(gstTask.equals(GSTTask.MONTHLY_CLOSED)) {
				//processGSTMonthClose(idCompany, loginName, GSTProcessStatus.PENDING.getValue(), dateFrom, false, false);
			}
			
		} catch (Exception e) {
			logger.error("GST Task Run Error[" + gstTask.toString() + "]", e);
		}
	}
	
	@Override
	public StringBuilder getGAFData(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		// C Company
		// P Purchase
		// S Supply
		// L Ledger
		// F Footer
		StringBuilder sbReturn = new StringBuilder();
		StringBuilder sbCompany = new StringBuilder();
		StringBuilder sbPurchase = new StringBuilder();
		StringBuilder sbSupply = new StringBuilder();
		StringBuilder sbLedger = new StringBuilder();
		StringBuilder sbFooter = new StringBuilder();
		
		gstGAFVO = new GSTGAFVO();
		
		sbReturn.append(generateCompany(idCompany, dateFrom, dateTo));
		sbReturn.append(generatePurchases(idCompany, dateFrom, dateTo));
		sbReturn.append(generateSupply(idCompany, dateFrom, dateTo));
		sbReturn.append(generateLedger(idCompany, dateFrom, dateTo));
		sbReturn.append(generateFooter());
		
		return sbReturn;
	}
	
	private StringBuilder generateCompany(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		CompanyVO companyVO = companyService.getCompanyDetails(idCompany);
		CompanyTaxVO companyTaxVO = companyService.getCompanyTax(idCompany);
		sb.append("C").append(BaseConstant.PAD_PIPE)			
			.append(companyVO.getName()).append(BaseConstant.PAD_PIPE)
			.append(companyVO.getConumber()).append(BaseConstant.PAD_PIPE)
			.append(companyTaxVO.getTaxNumber()).append(BaseConstant.PAD_PIPE)
			.append(sdf.format(dateFrom)).append(BaseConstant.PAD_PIPE)
			.append(sdf.format(dateTo)).append(BaseConstant.PAD_PIPE)
			.append(sdf.format(new Date())).append(BaseConstant.PAD_PIPE)
			.append(AppConfigConstant.getSoftwareVersion()).append(BaseConstant.PAD_PIPE)
			.append(AppConfigConstant.getGAFVersion()).append(BaseConstant.PAD_PIPE)
			.append(BaseConstant.PAD_LINE_BREAK);
		
		return sb;
	}
	
	private StringBuilder generatePurchases(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		List<GSTGAFVO> list = gstDAO.getPurchasesList(idCompany, dateFrom, dateTo);
		
		BigDecimal amt = new BigDecimal("0.00");
		BigDecimal gstAmt = new BigDecimal("0.00");
		gstGAFVO.setPurcCount(list.size());
		
		for (GSTGAFVO vo : list) {
			sb.append(vo.getRecordId()).append(BaseConstant.PAD_PIPE)			
			.append(vo.getSupplierName()).append(BaseConstant.PAD_PIPE)
			.append(vo.getSupplierBRN()).append(BaseConstant.PAD_PIPE)
			.append(vo.getSupplierGSTNo()).append(BaseConstant.PAD_PIPE)
			.append(sdf.format(vo.getInvoiceDt())).append(BaseConstant.PAD_PIPE)
			.append(sdf.format(vo.getPostingDt())).append(BaseConstant.PAD_PIPE)
			.append(vo.getInvoiceNo()).append(BaseConstant.PAD_PIPE)
			.append(vo.getImpDecNo()).append(BaseConstant.PAD_PIPE)
			.append(vo.getLineNo()).append(BaseConstant.PAD_PIPE)
			.append(vo.getDescription()).append(BaseConstant.PAD_PIPE)
			.append(vo.getAmount()).append(BaseConstant.PAD_PIPE)
			.append(vo.getTaxAmount()).append(BaseConstant.PAD_PIPE)
			.append(vo.getTaxCode()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCurCode()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCurAmount()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCurTaxAmount()).append(BaseConstant.PAD_PIPE)
			.append(BaseConstant.PAD_LINE_BREAK);
			
			amt = amt.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getAmount())));
			gstAmt = gstAmt.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getTaxAmount())));
		}
		
		gstGAFVO.setPurcTtlAmt(amt.doubleValue());
		gstGAFVO.setPurcTtlGSTAmt(gstAmt.doubleValue());

		return sb;
	}
	
	private StringBuilder generateSupply(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		List<GSTGAFVO> list = gstDAO.getSupplyList(idCompany, dateFrom, dateTo);
		
		BigDecimal amt = new BigDecimal("0.00");
		BigDecimal gstAmt = new BigDecimal("0.00");
		gstGAFVO.setSuppCount(list.size());
		
		for (GSTGAFVO vo : list) {
			sb.append(vo.getRecordId()).append(BaseConstant.PAD_PIPE)			
			.append(vo.getCustName()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCustBRN()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCustGSTNo()).append(BaseConstant.PAD_PIPE)
			.append(sdf.format(vo.getInvoiceDt())).append(BaseConstant.PAD_PIPE)
			.append(vo.getInvoiceNo()).append(BaseConstant.PAD_PIPE)
			.append(vo.getExpDecNo()).append(BaseConstant.PAD_PIPE)
			.append(vo.getLineNo()).append(BaseConstant.PAD_PIPE)
			.append(vo.getDescription()).append(BaseConstant.PAD_PIPE)
			.append(vo.getAmount()).append(BaseConstant.PAD_PIPE)
			.append(vo.getTaxAmount()).append(BaseConstant.PAD_PIPE)
			.append(vo.getTaxCode()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCountry()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCurCode()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCurAmount()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCurTaxAmount()).append(BaseConstant.PAD_PIPE)
			.append(BaseConstant.PAD_LINE_BREAK);
			
			amt = amt.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getAmount())));
			gstAmt = gstAmt.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getTaxAmount())));
		}
		
		gstGAFVO.setSuppTtlAmt(amt.doubleValue());
		gstGAFVO.setSuppTtlGSTAmt(gstAmt.doubleValue());

		return sb;
	}
	
	private StringBuilder generateLedger(Long idCompany, Date dateFrom, Date dateTo) throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		List<GSTGAFVO> list = gstDAO.getLedgerList(idCompany, dateFrom, dateTo);
		
		String acctCode = "";
		BigDecimal bal = new BigDecimal("0.00");
		BigDecimal debit = new BigDecimal("0.00");
		BigDecimal credit = new BigDecimal("0.00");
		
		BigDecimal ttlBal = new BigDecimal("0.00");
		BigDecimal ttlDebit = new BigDecimal("0.00");
		BigDecimal ttlCredit = new BigDecimal("0.00");
		gstGAFVO.setLedgerCount(list.size());
		
		for (GSTGAFVO vo : list) {
			if (!acctCode.equals(vo.getAcctCd())) {
				ttlBal = ttlBal.add(bal);
				bal = new BigDecimal("0.00");
			}
			
			debit = new BigDecimal(new DecimalFormat("#0.00").format(vo.getDebit()));
			credit = new BigDecimal(new DecimalFormat("#0.00").format(vo.getCredit()));
			bal = bal.add(debit.subtract(credit));
			vo.setBalance(bal.doubleValue());
			
			sb.append(vo.getRecordId()).append(BaseConstant.PAD_PIPE)			
			.append(sdf.format(vo.getTransDt())).append(BaseConstant.PAD_PIPE)
			.append(vo.getAcctCd()).append(BaseConstant.PAD_PIPE)
			.append(vo.getAcctType()).append(BaseConstant.PAD_PIPE)
			.append(vo.getAcctDesc()).append(BaseConstant.PAD_PIPE)
			.append(vo.getTransDesc()).append(BaseConstant.PAD_PIPE)
			.append(vo.getEntityName()).append(BaseConstant.PAD_PIPE)
			.append(vo.getTransId()).append(BaseConstant.PAD_PIPE)
			.append(vo.getSourceDocId()).append(BaseConstant.PAD_PIPE)
			.append(vo.getSourceType()).append(BaseConstant.PAD_PIPE)
			.append(vo.getDebit()).append(BaseConstant.PAD_PIPE)
			.append(vo.getCredit()).append(BaseConstant.PAD_PIPE)
			.append(vo.getBalance()).append(BaseConstant.PAD_PIPE)
			.append(BaseConstant.PAD_LINE_BREAK);
			
			
			ttlDebit = ttlDebit.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getDebit())));
			ttlCredit = ttlCredit.add(new BigDecimal(new DecimalFormat("#0.00").format(vo.getCredit())));
			
			acctCode = vo.getAcctCd();
		}
		// For Last Account
		ttlBal = ttlBal.add(bal);
		
		gstGAFVO.setBalanceTtl(ttlBal.doubleValue());
		gstGAFVO.setDebitTtl(ttlDebit.doubleValue());
		gstGAFVO.setCreditTtl(ttlCredit.doubleValue());

		return sb;
	}
	
	private StringBuilder generateFooter() throws BusinessException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("F").append(BaseConstant.PAD_PIPE)			
		.append(gstGAFVO.getPurcCount()).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getPurcTtlAmt())).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getPurcTtlGSTAmt())).append(BaseConstant.PAD_PIPE)
		.append(gstGAFVO.getSuppCount()).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getSuppTtlAmt())).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getSuppTtlGSTAmt())).append(BaseConstant.PAD_PIPE)
		.append(gstGAFVO.getLedgerCount()).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getDebitTtl())).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getCreditTtl())).append(BaseConstant.PAD_PIPE)
		.append(new DecimalFormat("#0.00").format(gstGAFVO.getBalanceTtl())).append(BaseConstant.PAD_PIPE)
		.append(BaseConstant.PAD_LINE_BREAK);

		return sb;
	}
	
	public void setClosedMonthParams(Long idCompany, String loginName, Date financialMonth) {
		
		taskParams = new HashMap<String, Object>();
		taskParams.put("idCompany", idCompany);
		taskParams.put("loginName", loginName);
		taskParams.put("financialMonth", financialMonth);

		setGstTask(GSTTask.MONTHLY_CLOSED);
	}
	
	public GSTTask getGstTask() {
		return gstTask;
	}

	public void setGstTask(GSTTask gstTask) {
		this.gstTask = gstTask;
	}

	public Map<String, Object> getTaskParams() {
		return taskParams;
	}

	public void setTaskParams(Map<String, Object> taskParams) {
		this.taskParams = taskParams;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.gst.service.GSTService#getGSTTaxList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO, java.lang.String)
	 */
	@Override
	public List<GSTReportVO> getGSTTaxList(Long idCompany, SearchParamVO searchParamVO, String type) throws BusinessException {
		getGSTBPDate();
		searchParamVO.setObj7(GSTBPDate);
		return gstDAO.getGSTTaxList(idCompany, searchParamVO, type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.gst.service.GSTService#getGSTSmmryAcqInputTaxList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<GSTReportVO> getGSTSmmryAcqInputTaxList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		getGSTBPDate();
		searchParamVO.setObj7(GSTBPDate);
		return gstDAO.getGSTSmmryAcqInputTaxList(idCompany, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.gst.service.GSTService#getGSTYearlyList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<GSTReportVO> getGSTYearlyList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		getGSTBPDate();
		searchParamVO.setObj7(GSTBPDate);
		return gstDAO.getGSTYearlyList(idCompany, searchParamVO);
	}
	
	public GSTMappingStateVO getGSTMappingState(Date dateTo, String gstField) throws BusinessException {
		return gstDAO.getGSTMappingState(dateTo, gstField);
	}
}
