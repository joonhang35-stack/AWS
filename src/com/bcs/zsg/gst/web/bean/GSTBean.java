package com.bcs.zsg.gst.web.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.helper.UserRoleHelper;
import com.bcs.zsg.common.helper.DatesUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.common.service.LookupService;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.helper.DateUtils;
import com.bcs.zsg.db.bterp.vo.CompanyTaxVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTMappingStateVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryTaxVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTSummaryVO;
import com.bcs.zsg.db.bterp.vo.gst.GSTVO;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.bo.GSTBO;
import com.bcs.zsg.gst.helper.ConstantGST;
import com.bcs.zsg.gst.helper.GSTProcessStatus;
import com.bcs.zsg.gst.helper.GSTType;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class GSTBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	private List<TaxCodeVO> inputTaxCodeVOList;
	private List<TaxCodeVO> outputTaxCodeVOList;
	private GSTVO gstVO;
	private String fileName, gstReturnFileTxt;
	private StringBuilder gstGAFTxt;
	private Date dateFrom, dateTo;
	private Date mappingDate;
	
	private List<GSTSummaryVO> monthlySummaryList;
	private GSTSummaryVO viewSummaryVO, selectedSummaryVO;
	
	private int activeTabIndex;
	private String processMessage;
	private boolean authorizedUser, singleMonth, isValidPeriod;
	private CompanyTaxVO companyTaxVO;
	
	private List<GSTSummaryTaxVO> formOutputTaxList;
	private List<GSTSummaryTaxVO> formOutputTaxAdjustmentList;
	private List<GSTSummaryTaxVO> formInputTaxList;
	private List<GSTSummaryTaxVO> formInputTaxAdjustmentList;
	private List<GSTSummaryTaxVO> formExemptedSupplyList;
	private List<GSTSummaryTaxVO> formTotalAmountList;
	
	@Autowired
	private transient GSTBO gstBO;
	@Autowired
	private transient CorporateProfileBO companyBO;
	@Autowired 
	private transient LookupService lookupService;
	
	@Override
	public void resetForm() {
		dateTo = new Date();
		dateFrom = dateTo;
		fileName = "";
		gstReturnFileTxt = "";
		setActiveTabIndex(0);
		
		setSelectedSummaryVO(new GSTSummaryVO());
		viewSummaryVO = null;

		monthlySummaryList = new ArrayList<GSTSummaryVO>();
	}
	
	public void init() {
		try {
			resetForm();

			companyTaxVO = companyBO.getCompanyTax(getSessionInfoBean().getCompanyVO().getId());
			
			if(companyTaxVO == null)
				companyTaxVO = new CompanyTaxVO();

			isValidPeriod = true;
			
			if(companyTaxVO.getGstFilling() == null) {
				isValidPeriod = false;
			} else {
				
				//If today's date is smaller than GST valid filing period, disable all button
				if(!DatesUtils.isSameMonthYear(dateFrom, companyTaxVO.getGstFilling()) &&
						!DatesUtils.isDateMonthYearGreaterOrEqual(dateFrom, companyTaxVO.getGstFilling())) {
					isValidPeriod = false;
				}  else {
					dateTo = DateUtils.addMonths(new Date(), -1);
					dateFrom = dateTo;
				}
			}
			authorizedUser = UserRoleHelper.checkIsAccountManager(this.getUserInfo().getRoleList());
			updateDateRange(true, true);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void initCode() {
		try {
			setInputTaxCodeVOList(gstBO.getTaxCodeList(GSTType.INPUT));
			setOutputTaxCodeVOList(gstBO.getTaxCodeList(GSTType.OUTPUT));
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void updateDateRange(boolean refreshList) throws BusinessException {
		updateDateRange(refreshList, false);
	}
	
	public void updateDateRange(boolean refreshList, boolean init) throws BusinessException {

		if(!DatesUtils.isDateMonthYearGreaterOrEqual(dateFrom, companyTaxVO.getGstFilling())) {
			//Set the dateFrom period from the GST filling date
			dateFrom = DatesUtils.getDayTimeStart(companyTaxVO.getGstFilling()).getTime();
		} else {
			dateFrom = DateUtils.getStartDateByMonth(dateFrom);
		}
		if(dateFrom.after(dateTo))
			dateTo = DateUtils.getEndDateByMonth(dateFrom);
		else
			dateTo = DateUtils.getEndDateByMonth(dateTo);
		
		singleMonth = DatesUtils.isSameMonthYear(dateFrom, dateTo);
		
		// TODO set default date from / date to quarterly
		if (init == true && "3".equals(companyTaxVO.getPeriod()))
			dateFrom = DatesUtils.getDayTimeStart(com.bcs.zsg.core.helper.DateUtils.getStartDateByMonth(DateUtils.addMonths(dateTo, -2))).getTime();
		
		
		if(refreshList) {
			if(isValidPeriod)
				monthlySummaryList = gstBO.getMonthlySummaryList(getSessionInfoBean().getCompanyVO().getId(), dateFrom, dateTo);
			
			getMappingDate();
			if(DatesUtils.isDateGreaterOrEqual(dateFrom, mappingDate)) {
				updateTaxSummaryList2();
			} else {
				updateTaxSummaryList();
			}
		}
		
		// TODO set quarterly as single month
		if ("3".equals(companyTaxVO.getPeriod()) && monthlySummaryList != null && monthlySummaryList.size() == 1) singleMonth = true;
	}
	
	private void getMappingDate() {
		String strDate = lookupService.getSysParamValue("GST", "GST_MAPPING_DATE");
	    try {
	    	mappingDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void generatePDF() {
		String gstPDFFolder = "/pdf/gst/";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		try {
			initTaxReturnData("GST03");
			
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			
			PdfReader reader = new PdfReader(servletContext.getRealPath(gstPDFFolder + "GST03.pdf"));
			
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(new File(servletContext.getRealPath(gstPDFFolder) + "/" + fileName + ".pdf")));
			
			AcroFields form = stamper.getAcroFields();
			gstVO = gstBO.getTaxReturnData(getSessionInfoBean().getCompanyVO().getId(), dateFrom, dateTo, getSummaryIdList());

			DecimalFormat df = new DecimalFormat("#0.00");
			DecimalFormat dfPercentage = new DecimalFormat("#.##");  
			
			//form.setField("chkA", vo.isAmendment() ? "Yes" : "no");
			form.setField("GSTNo", gstVO.getGstNo());
			form.setField("GSTNo1", gstVO.getGstNo());		// Header top
			form.setField("NameBusiness", gstVO.getNameOfBussiness());
			form.setField("StartDate", sdf.format(gstVO.getDateStart()));
			form.setField("EndDate", sdf.format(gstVO.getDateEnd()));
			form.setField("PaymentDate", sdf.format(gstVO.getDatePayment()).toString());
			form.setField("totalVal", df.format(gstVO.getTotalSupply()));						//5a , c1
			form.setField("totalOut", df.format(gstVO.getTotalOutputTax()));					//5b , c2
			
			form.setField("totalAcquisition", df.format(gstVO.getTotalAcquisitions()));		//6a , c3
			form.setField("totalInputTax", df.format(gstVO.getTotalInputTax()));			//6b , c4
			form.setField("amountClaimable", df.format(gstVO.getAmountClaimable()));
			form.setField("amountPayable", df.format(gstVO.getAmountPayable()));
			form.setField("chkBox", gstVO.isCarryForwardRefund() ? "1" : "2");					//9 , b5
			
			form.setField("totalSupp", df.format(gstVO.getTotalLocalZeroRatedSupplies()));	//10 , c6
			form.setField("totalExport", df.format(gstVO.getTotalExportSupplies()));		//11 , c7
			form.setField("totalExempt", df.format(gstVO.getTotalExemptSupplies()));		//12 , c8
			form.setField("totalGranted", df.format(gstVO.getTotalSuppliesGrantedRelief()));//13 , c9
			form.setField("totalGoods", df.format(gstVO.getTotalGoodsImportedApproved()));	//14 , c10
			form.setField("totalSuspend", df.format(gstVO.getTotalSuspendedUnder14()));		//15 , 
			form.setField("totalCapital", df.format(gstVO.getTotalCapitalGoodsAcquired()));	//16 , c11
			form.setField("totalRelief", df.format(gstVO.getTotalBadDebtRelief()));			//17 , c12
			form.setField("totalRecovered", df.format(gstVO.getTotalBadDebtRecovered()));	//18 , c13
			
			//ITEM 19
			if(!StringUtils.isEmpty(gstVO.getMsicCode1())) {
				form.setField("code1", gstVO.getMsicCode1());								//19 , i14
				form.setField("output1", df.format(gstVO.getMsicOutputTax1()));			//19 , c15
				form.setField("prcnt1", dfPercentage.format(gstVO.getMsicPercentage1()));
			}
			if(!StringUtils.isEmpty(gstVO.getMsicCode2())) {
				form.setField("code2", gstVO.getMsicCode2());								//19 , i16
				form.setField("output2", df.format(gstVO.getMsicOutputTax2()));			//19 , c17
				form.setField("prcnt2", dfPercentage.format(gstVO.getMsicPercentage2()));
			}
			if(!StringUtils.isEmpty(gstVO.getMsicCode3())) {
				form.setField("code3", gstVO.getMsicCode3());								//19 , i18
				form.setField("output3", df.format(gstVO.getMsicOutputTax3()));			//19 , c19
				form.setField("prcnt3", dfPercentage.format(gstVO.getMsicPercentage3()));
			}
			if(!StringUtils.isEmpty(gstVO.getMsicCode4())) {
				form.setField("code4", gstVO.getMsicCode4());								//19 , i20
				form.setField("output4", df.format(gstVO.getMsicOutputTax4()));			//19 , c21
				form.setField("prcnt4", dfPercentage.format(gstVO.getMsicPercentage4()));
			}
			if(!StringUtils.isEmpty(gstVO.getMsicCode5())) {
				form.setField("code5", gstVO.getMsicCode5());								//19 , i22
				form.setField("output5", df.format(gstVO.getMsicOutputTax5()));			//19 , c23
				form.setField("prcnt5", dfPercentage.format(gstVO.getMsicPercentage5()));
			}
			form.setField("output6", df.format(gstVO.getMsicOutputTax6()));			//19 , c24
			form.setField("prcnt6", dfPercentage.format(gstVO.getMsicPercentage6()));
			
			form.setField("output7", df.format(gstVO.getMsicOutputTax7()));

//			form.setField("NameApp", vo.getAuthorizedPerson());
//			form.setField("NewIC", vo.getIcNoNew());
//			form.setField("OldIC", vo.getIcNoOld());
//			form.setField("PassNo", vo.getPassportNo());
//			form.setField("Nationality", vo.getNationality());
//			form.setField("Date", sdf.format(vo.getDate()).toString());
//			form.setField("Signature", vo.getSignature());
//			
//			form.setField("DateRec", sdf.format(vo.getReceivedDate()).toString());
//			form.setField("DatePos", sdf.format(vo.getPostmarkDate()).toString());

			stamper.setFormFlattening(true);
			stamper.close();
			
			RequestContext context = RequestContext.getCurrentInstance();
	        context.addCallbackParam("file", fileName);
	        
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	private void initTaxReturnData(String filePrefix) throws BusinessException {

		SimpleDateFormat sdfMonthYear = new SimpleDateFormat("MMyyyy", Locale.ENGLISH);
		
		updateDateRange(false);
		
		String strFromYearMonth = sdfMonthYear.format(dateFrom);
		String strToYearMonth = sdfMonthYear.format(dateTo);
		
		if(!strFromYearMonth.equals(strToYearMonth)) {
			fileName = strFromYearMonth + "_" + strToYearMonth;
		} else {
			fileName = strToYearMonth;
		}
		
		fileName = filePrefix + "_" + fileName;
		
	}
	
	public void downloadTaxReturn() {
		StringBuilder sbText = new StringBuilder();
		
		try {
			initTaxReturnData("GST03");
			
			//File Format
			//c1|c2|c3|c4|b5|c6|c7|c8|c9|c10|c11|c12|c13|i14|c15|i16|c17|i18|c19|i20|c21|i22|c23|c24
			
			DecimalFormat df = new DecimalFormat("#0.00"); 

			gstVO = gstBO.getTaxReturnData(getSessionInfoBean().getCompanyVO().getId(), dateFrom, dateTo, getSummaryIdList());
			
			sbText.append(df.format(gstVO.getTotalSupply())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalOutputTax())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalAcquisitions())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalInputTax())).append(BaseConstant.PAD_PIPE)
				.append(gstVO.isCarryForwardRefund() ? "1" : "0").append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalLocalZeroRatedSupplies())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalExportSupplies())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalExemptSupplies())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalSuppliesGrantedRelief())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalGoodsImportedApproved())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalCapitalGoodsAcquired())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalBadDebtRelief())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getTotalBadDebtRecovered())).append(BaseConstant.PAD_PIPE)
				.append(gstVO.getMsicCode1()).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getMsicOutputTax1())).append(BaseConstant.PAD_PIPE)
				.append(gstVO.getMsicCode2()).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getMsicOutputTax2())).append(BaseConstant.PAD_PIPE)
				.append(gstVO.getMsicCode3()).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getMsicOutputTax3())).append(BaseConstant.PAD_PIPE)
				.append(gstVO.getMsicCode4()).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getMsicOutputTax4())).append(BaseConstant.PAD_PIPE)
				.append(gstVO.getMsicCode5()).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getMsicOutputTax5())).append(BaseConstant.PAD_PIPE)
				.append(df.format(gstVO.getMsicOutputTax6())).append(BaseConstant.PAD_PIPE);
			
		} catch (Throwable t) {
			errorResult(t);
		}
		gstReturnFileTxt = sbText.toString();
		
    }
	
	public StreamedContent getDownloadTaxReturnStream() {
		if(StringUtils.isEmpty(gstReturnFileTxt))
			return null;
		
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    String downloadFileName = fileName.concat(".txt");
		    
			return new DefaultStreamedContent(new ByteArrayInputStream(gstReturnFileTxt
													.getBytes(StandardCharsets.UTF_8)), 
												externalContext.getMimeType(downloadFileName), downloadFileName);
		} catch (Throwable t) {
			errorResult(t);
		}
		return null;
	}

	public void downloadGAF() {
		StringBuilder sbText = new StringBuilder();

		try {
			initTaxReturnData("GAF");

			//gstVO = gstBO.getGAFData(getSessionInfoBean().getCompanyVO().getId(), dateFrom, dateTo, getSummaryIdList());
			sbText = gstBO.getGAFData(getSessionInfoBean().getCompanyVO().getId(), dateFrom, dateTo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
		gstGAFTxt = sbText;
	}
	
	public StreamedContent getDownloadGAFStream() {
		if(StringUtils.isEmpty(gstGAFTxt))
			return null;
		
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    String downloadFileName = fileName.concat(".txt");
			return new DefaultStreamedContent(new ByteArrayInputStream(gstGAFTxt.toString().getBytes(StandardCharsets.UTF_8)), 
												externalContext.getMimeType(downloadFileName), downloadFileName);
		} catch (Throwable t) {
			errorResult(t);
		}
		return null;
	}
	
	public void selectProcessPeriod(GSTSummaryVO selectedSummaryVO, String action) {
		if(action.equals("L"))
			processMessage = "lock";
		else if(action.equals("U"))
			processMessage = "un-lock";
		else if(action.equals("S"))
			processMessage = "submit";
		
		this.setSelectedSummaryVO(selectedSummaryVO);
	}
	
	public void refreshPeriod(GSTSummaryVO selectedSummaryVO) {
		
		this.setSelectedSummaryVO(selectedSummaryVO);
		processGSTPeriod(false);
	}
	
	public void processGSTPeriod(boolean isUserAction) {
		try {
			boolean isLockUnlock = false, isSubmit = false;
			
			if(isUserAction) {
				if(processMessage.equals("submit"))
					isSubmit = true;
				else
					isLockUnlock = true;
			}
			
			gstBO.processGSTPeriod(getSessionInfoBean().getUserVO().getName(), selectedSummaryVO, isLockUnlock, isSubmit);
			updateDateRange(true);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public String getProcessStatusDescription(String processStatus) {
		if(GSTProcessStatus.PENDING.getValue().equals(processStatus))
			return GSTProcessStatus.PENDING.toString();
		else if(GSTProcessStatus.LOCKED.getValue().equals(processStatus))
			return GSTProcessStatus.LOCKED.toString();
		else if(GSTProcessStatus.SUBMIT.getValue().equals(processStatus))
			return GSTProcessStatus.SUBMIT.toString();
		else
			return "";
	}
	
	public void onTabChange(TabChangeEvent event) {
		TabView tabView = (TabView) event.getComponent();
		setActiveTabIndex(tabView.getChildren().indexOf(event.getTab()));
		
    }
	
	private void updateTaxSummaryList() throws BusinessException {
		
		formOutputTaxList = new ArrayList<GSTSummaryTaxVO>();
		formOutputTaxAdjustmentList = new ArrayList<GSTSummaryTaxVO>();
		formInputTaxList = new ArrayList<GSTSummaryTaxVO>();
		formInputTaxAdjustmentList = new ArrayList<GSTSummaryTaxVO>();
		formExemptedSupplyList = new ArrayList<GSTSummaryTaxVO>();
		formTotalAmountList = new ArrayList<GSTSummaryTaxVO>();
		
		if(!isValidPeriod)
			return;
	
		viewSummaryVO = gstBO.getTaxSummary(getSessionInfoBean().getCompanyVO().getId(), 
									dateFrom, dateTo, getSummaryIdList());
		
		BigDecimal inAmountTotal = new BigDecimal("0.00");
		BigDecimal inTaxTotal = new BigDecimal("0.00");
		BigDecimal inAdjustAmountTotal = new BigDecimal("0.00");
		BigDecimal inAdjustTaxTotal = new BigDecimal("0.00");
		
		BigDecimal inNonClaimAmountTotal = new BigDecimal("0.00");
		BigDecimal inNonClaimTaxTotal = new BigDecimal("0.00");

		BigDecimal outNonClaimAmountTotal = new BigDecimal("0.00");
		BigDecimal outNonClaimTaxTotal = new BigDecimal("0.00");

		BigDecimal exempSuppTaxTotal = new BigDecimal("0.00");
		BigDecimal exempSuppAmountTotal = new BigDecimal("0.00");
		
		BigDecimal outAmountTotal = new BigDecimal("0.00");
		BigDecimal outTaxTotal = new BigDecimal("0.00");
		BigDecimal outAdjustAmountTotal = new BigDecimal("0.00");
		BigDecimal outAdjustTaxTotal = new BigDecimal("0.00");
		
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getInputClaimableVOList()) {
			
			if(tmpSummaryTaxVO.getTaxCode().equals(GSTType.IN_TX.getValue()) 
					|| tmpSummaryTaxVO.getTaxCode().equals(GSTType.IN_IM.getValue())
					|| tmpSummaryTaxVO.getTaxCode().equals(GSTType.IN_TX_E43.getValue())
					|| tmpSummaryTaxVO.getTaxCode().equals(GSTType.IN_TX_RE.getValue()) ) {

				inAmountTotal = inAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
				inTaxTotal = inTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
				
				formInputTaxList.add(tmpSummaryTaxVO);
				
			} else if(tmpSummaryTaxVO.getTaxCode().equals(GSTType.IN_AJP.getValue())) {

				inAdjustAmountTotal = inAdjustAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
				inAdjustTaxTotal = inAdjustTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
				
				formInputTaxAdjustmentList.add(tmpSummaryTaxVO);
			}
		}
		
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getInputNonClaimableVOList()) {
			
			inNonClaimTaxTotal = inNonClaimTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
			inNonClaimAmountTotal = inNonClaimAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
		}
		
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getOutputVOList()) {
			
			if(tmpSummaryTaxVO.getTaxCode().equals(GSTType.OUT_SR.getValue()) 
					|| tmpSummaryTaxVO.getTaxCode().equals(GSTType.OUT_DS.getValue()) ) {
				
				outAmountTotal = outAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
				outTaxTotal = outTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
				
				formOutputTaxList.add(tmpSummaryTaxVO);
			
			} else if(tmpSummaryTaxVO.getTaxCode().equals(GSTType.OUT_AJS.getValue())) {
				
				outAdjustAmountTotal = outAdjustAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
				outAdjustTaxTotal = outAdjustTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
				
				formOutputTaxAdjustmentList.add(tmpSummaryTaxVO);
				
			} else if(tmpSummaryTaxVO.getTaxCode().equals(GSTType.OUT_ES.getValue()) 
					|| tmpSummaryTaxVO.getTaxCode().equals(GSTType.OUT_ES43.getValue())) {
				
				exempSuppTaxTotal = exempSuppTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
				exempSuppAmountTotal = exempSuppAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));

				formExemptedSupplyList.add(tmpSummaryTaxVO);
			}
		}

		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getOutputNonClaimableVOList()) {
			outNonClaimTaxTotal = outNonClaimTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
			outNonClaimAmountTotal = outNonClaimAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
		}
		
		//DONT CHANGE THE SEQUENCE.
		// SEQ = [0] OUTPUT SUB TOTAL, [1] OUTPUT ADJUSTMENT TOTAL, [2] OUTPUT GRAN TOTAL
		//       [3] INPUT SUB TOTAL, [4] INPUT ADJUSTMENT TOTAL, [5] INPUT GRAN TOTAL
		//       [6] INPUT EXAMPTED TOTAL
		//       [7] INPUT CLAIMABLE TOTAL
		//       [8] OUTPUT NON CLAIMABLE TOTAL

		//OUTPUT TAX
		updateTotalAmountList(outAmountTotal.doubleValue(), outTaxTotal.doubleValue());	// [0]
		updateTotalAmountList(outAdjustAmountTotal.doubleValue(), 
									outAdjustTaxTotal.doubleValue());					// [1]
		updateTotalAmountList(outAmountTotal.add(outAdjustAmountTotal).doubleValue(), 
									outTaxTotal.add(outAdjustTaxTotal).doubleValue());	// [2]

		//INPUT TAX
		updateTotalAmountList(inAmountTotal.doubleValue(), inTaxTotal.doubleValue());	// [3]
		updateTotalAmountList(inAdjustAmountTotal.doubleValue(), 
									inAdjustTaxTotal.doubleValue());					// [4]
		updateTotalAmountList(inAmountTotal.add(inAdjustAmountTotal).doubleValue(), 
									inTaxTotal.add(inAdjustTaxTotal).doubleValue());	// [5]

		updateTotalAmountList(exempSuppAmountTotal.doubleValue(), exempSuppTaxTotal.doubleValue());		// [6]

		updateTotalAmountList(inNonClaimAmountTotal.doubleValue(), inNonClaimTaxTotal.doubleValue());	// [7]
		
		updateTotalAmountList(outNonClaimAmountTotal.doubleValue(), outNonClaimTaxTotal.doubleValue());	// [8]
	}
	
	private void updateTaxSummaryList2() throws BusinessException {
		
		formOutputTaxList = new ArrayList<GSTSummaryTaxVO>();
		formOutputTaxAdjustmentList = new ArrayList<GSTSummaryTaxVO>();
		formInputTaxList = new ArrayList<GSTSummaryTaxVO>();
		formInputTaxAdjustmentList = new ArrayList<GSTSummaryTaxVO>();
		formExemptedSupplyList = new ArrayList<GSTSummaryTaxVO>();
		formTotalAmountList = new ArrayList<GSTSummaryTaxVO>();
		
		if(!isValidPeriod)
			return;
	
		viewSummaryVO = gstBO.getTaxSummary(getSessionInfoBean().getCompanyVO().getId(), 
									dateFrom, dateTo, getSummaryIdList());
		
		BigDecimal inAmountTotal = new BigDecimal("0.00");
		BigDecimal inTaxTotal = new BigDecimal("0.00");
		BigDecimal inAdjustAmountTotal = new BigDecimal("0.00");
		BigDecimal inAdjustTaxTotal = new BigDecimal("0.00");
		
		BigDecimal inNonClaimAmountTotal = new BigDecimal("0.00");
		BigDecimal inNonClaimTaxTotal = new BigDecimal("0.00");

		BigDecimal outNonClaimAmountTotal = new BigDecimal("0.00");
		BigDecimal outNonClaimTaxTotal = new BigDecimal("0.00");

		BigDecimal exempSuppTaxTotal = new BigDecimal("0.00");
		BigDecimal exempSuppAmountTotal = new BigDecimal("0.00");
		
		BigDecimal outAmountTotal = new BigDecimal("0.00");
		BigDecimal outTaxTotal = new BigDecimal("0.00");
		BigDecimal outAdjustAmountTotal = new BigDecimal("0.00");
		BigDecimal outAdjustTaxTotal = new BigDecimal("0.00");
		
		
		GSTMappingStateVO gstMappingStateVO = null;
		List<String> taxCodeList = new ArrayList<>();
		List<String> taxCodeList6A = new ArrayList<>();
		List<String> taxCodeList6B = new ArrayList<>();
		List<String> taxCodeList5A = new ArrayList<>();
		List<String> taxCodeList5B = new ArrayList<>();
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getInputClaimableVOList()) {
			/*########################################## INPUT ##########################################*/
			gstMappingStateVO = gstBO.getGSTMappingState(tmpSummaryTaxVO.getDateTo(), ConstantGST.GST_FIELD_6A);
			taxCodeList6A = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList6A) {
				if (tmpSummaryTaxVO.getTaxCode().equals(vo)) {
					inAmountTotal = inAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
					inTaxTotal = inTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
					
					// Compare is the list contain same tax code, add in list when new
					compareList(formInputTaxList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
				}
			}
			
			gstMappingStateVO = gstBO.getGSTMappingState(tmpSummaryTaxVO.getDateTo(), ConstantGST.GST_FIELD_6B);
			taxCodeList6B = convertTaxCode(gstMappingStateVO.getTaxCode());
			// Remove those in same tax code, to find out the adjustment tax code
			taxCodeList6B.removeAll(taxCodeList6A);
			for (String vo : taxCodeList6B) {
				if (tmpSummaryTaxVO.getTaxCode().equals(vo)) {
					inAdjustAmountTotal = inAdjustAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
					inAdjustTaxTotal = inAdjustTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
					
					// Compare is the list contain same tax code, add in list when new
					compareList(formInputTaxAdjustmentList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
				}
			}
			
			gstMappingStateVO = gstBO.getGSTMappingState(tmpSummaryTaxVO.getDateTo(), ConstantGST.GST_FIELD_12);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (tmpSummaryTaxVO.getTaxCode().equals(vo)) {
					exempSuppTaxTotal = exempSuppTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
					exempSuppAmountTotal = exempSuppAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));

					// Compare is the list contain same tax code, add in list when new
					compareList(formExemptedSupplyList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
				}
			}
		}
		
		List<GSTSummaryTaxVO> tempGstSummaryTaxVOList = new ArrayList<>();
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getInputNonClaimableVOList()) {
			inNonClaimTaxTotal = inNonClaimTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
			inNonClaimAmountTotal = inNonClaimAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
			
			compareList(tempGstSummaryTaxVOList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
		}
		// Refresh list to grouped data
		viewSummaryVO.setInputNonClaimableVOList(tempGstSummaryTaxVOList);
		
		/*########################################## OUTPUT ##########################################*/
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getOutputVOList()) {
			
			gstMappingStateVO = gstBO.getGSTMappingState(tmpSummaryTaxVO.getDateTo(), ConstantGST.GST_FIELD_5A);
			taxCodeList5A = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList5A) {
				if (tmpSummaryTaxVO.getTaxCode().equals(vo)) {
					outAmountTotal = outAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
					outTaxTotal = outTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
					
					// Compare is the list contain same tax code, add in list when new
					compareList(formOutputTaxList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
				}
			}
			
			gstMappingStateVO = gstBO.getGSTMappingState(tmpSummaryTaxVO.getDateTo(), ConstantGST.GST_FIELD_5B);
			taxCodeList5B = convertTaxCode(gstMappingStateVO.getTaxCode());
			// Remove those in same tax code, to find out the adjustment tax code
			taxCodeList5B.removeAll(taxCodeList5A);
			for (String vo : taxCodeList5B) {
				if (tmpSummaryTaxVO.getTaxCode().equals(vo)) {
					outAdjustAmountTotal = outAdjustAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
					outAdjustTaxTotal = outAdjustTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
					
					// Compare is the list contain same tax code, add in list when new
					compareList(formOutputTaxAdjustmentList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
				}
			}
			
			gstMappingStateVO = gstBO.getGSTMappingState(tmpSummaryTaxVO.getDateTo(), ConstantGST.GST_FIELD_12);
			taxCodeList = convertTaxCode(gstMappingStateVO.getTaxCode());
			for (String vo : taxCodeList) {
				if (tmpSummaryTaxVO.getTaxCode().equals(vo)) {
					exempSuppTaxTotal = exempSuppTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
					exempSuppAmountTotal = exempSuppAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));

					// Compare is the list contain same tax code, add in list when new
					compareList(formExemptedSupplyList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
				}
			}
		}

		tempGstSummaryTaxVOList = new ArrayList<>();
		for(GSTSummaryTaxVO tmpSummaryTaxVO : viewSummaryVO.getOutputNonClaimableVOList()) {
			outNonClaimTaxTotal = outNonClaimTaxTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getTaxAmount())));
			outNonClaimAmountTotal = outNonClaimAmountTotal.add(new BigDecimal(String.valueOf(tmpSummaryTaxVO.getAmount())));
			
			// Compare is the list contain same tax code, add in list when new
			compareList(tempGstSummaryTaxVOList, tmpSummaryTaxVO, tmpSummaryTaxVO.getAmount(), tmpSummaryTaxVO.getTaxAmount());
		}
		// Refresh list to grouped data
		viewSummaryVO.setOutputNonClaimableVOList(tempGstSummaryTaxVOList);
		
		//DONT CHANGE THE SEQUENCE.
		// SEQ = [0] OUTPUT SUB TOTAL, [1] OUTPUT ADJUSTMENT TOTAL, [2] OUTPUT GRAN TOTAL
		//       [3] INPUT SUB TOTAL, [4] INPUT ADJUSTMENT TOTAL, [5] INPUT GRAN TOTAL
		//       [6] INPUT EXAMPTED TOTAL
		//       [7] INPUT CLAIMABLE TOTAL
		//       [8] OUTPUT NON CLAIMABLE TOTAL

		//OUTPUT TAX
		updateTotalAmountList(outAmountTotal.doubleValue(), outTaxTotal.doubleValue());	// [0]
		updateTotalAmountList(outAdjustAmountTotal.doubleValue(), 
									outAdjustTaxTotal.doubleValue());					// [1]
		updateTotalAmountList(outAmountTotal.add(outAdjustAmountTotal).doubleValue(), 
									outTaxTotal.add(outAdjustTaxTotal).doubleValue());	// [2]

		//INPUT TAX
		updateTotalAmountList(inAmountTotal.doubleValue(), inTaxTotal.doubleValue());	// [3]
		updateTotalAmountList(inAdjustAmountTotal.doubleValue(), 
									inAdjustTaxTotal.doubleValue());					// [4]
		updateTotalAmountList(inAmountTotal.add(inAdjustAmountTotal).doubleValue(), 
									inTaxTotal.add(inAdjustTaxTotal).doubleValue());	// [5]

		updateTotalAmountList(exempSuppAmountTotal.doubleValue(), exempSuppTaxTotal.doubleValue());		// [6]

		updateTotalAmountList(inNonClaimAmountTotal.doubleValue(), inNonClaimTaxTotal.doubleValue());	// [7]
		
		updateTotalAmountList(outNonClaimAmountTotal.doubleValue(), outNonClaimTaxTotal.doubleValue());	// [8]
	}
	
	private List<String> convertTaxCode(String input) {
		List<String> taxCodeSel = new LinkedList<String>();
		
		if (input!=null) {
			taxCodeSel = new LinkedList<String>(Arrays.asList(input.replace(" ", "").split(",")));
		}
		
		return taxCodeSel;
	}
	
	private void compareList(List<GSTSummaryTaxVO> summaryVOList, GSTSummaryTaxVO summaryVO, double amount, double taxAmount) {
		
		boolean isPresent = false;
		for (GSTSummaryTaxVO tempVO : summaryVOList) {
			if (tempVO.getTaxCode().equals(summaryVO.getTaxCode())) {
				tempVO.setAmount(new BigDecimal(String.valueOf(tempVO.getAmount())).add(new BigDecimal(String.valueOf(amount))).doubleValue());
				tempVO.setTaxAmount(new BigDecimal(String.valueOf(tempVO.getTaxAmount())).add(new BigDecimal(String.valueOf(taxAmount))).doubleValue());
				isPresent = true;
				break;
			}
		}
		
		if (!isPresent) {
			summaryVOList.add(summaryVO);
		}
	}
	
	private void updateTotalAmountList(double amount, double taxAmount) {
		GSTSummaryTaxVO gstVO = new GSTSummaryTaxVO();
		gstVO.setAmount(amount);
		gstVO.setTaxAmount(taxAmount);
		
		formTotalAmountList.add(gstVO);
		
	}
	
	private List<Long> getSummaryIdList() {
		List<Long> summaryIdList = new ArrayList<Long>();
		if(monthlySummaryList.size() > 0) 
			for(GSTSummaryVO gstSummaryVO : monthlySummaryList) {
				
				if(gstSummaryVO.getId() != null)
					summaryIdList.add(gstSummaryVO.getId());
				
			}
		
		return summaryIdList; 
	}
	
	public String formatDate(Date dateToFormat) {
		if(dateToFormat != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(dateToFormat); 
		} else 
			return "";
	}
	
	public GSTVO getGstVO() {
		return gstVO;
	}

	public void setGstVO(GSTVO gstVO) {
		this.gstVO = gstVO;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}


	public List<TaxCodeVO> getInputTaxCodeVOList() {
		return inputTaxCodeVOList;
	}


	public void setInputTaxCodeVOList(List<TaxCodeVO> inputTaxCodeVOList) {
		this.inputTaxCodeVOList = inputTaxCodeVOList;
	}


	public List<TaxCodeVO> getOutputTaxCodeVOList() {
		return outputTaxCodeVOList;
	}


	public void setOutputTaxCodeVOList(List<TaxCodeVO> outputTaxCodeVOList) {
		this.outputTaxCodeVOList = outputTaxCodeVOList;
	}


	public List<GSTSummaryVO> getMonthlySummaryList() {
		return monthlySummaryList;
	}


	public void setMonthlySummaryList(List<GSTSummaryVO> monthlySummaryList) {
		this.monthlySummaryList = monthlySummaryList;
	}

	public GSTSummaryVO getSelectedSummaryVO() {
		return selectedSummaryVO;
	}

	public void setSelectedSummaryVO(GSTSummaryVO selectedSummaryVO) {
		this.selectedSummaryVO = selectedSummaryVO;
	}

	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}

	public String getProcessMessage() {
		return processMessage;
	}

	public void setProcessMessage(String processMessage) {
		this.processMessage = processMessage;
	}

	public boolean getAuthorizedUser() {
		return authorizedUser;
	}

	public void setAuthorizedUser(boolean authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	public boolean getSingleMonth() {
		return singleMonth;
	}

	public void setSingleMonth(boolean singleMonth) {
		this.singleMonth = singleMonth;
	}

	public CompanyTaxVO getCompanyTaxVO() {
		return companyTaxVO;
	}

	public void setCompanyTaxVO(CompanyTaxVO companyTaxVO) {
		this.companyTaxVO = companyTaxVO;
	}

	public boolean getIsValidPeriod() {
		return isValidPeriod;
	}

	public void setIsValidPeriod(boolean isValidPeriod) {
		this.isValidPeriod = isValidPeriod;
	}
	
	public GSTSummaryVO getViewSummaryVO() {
		return viewSummaryVO;
	}

	public void setViewSummaryVO(GSTSummaryVO viewSummaryVO) {
		this.viewSummaryVO = viewSummaryVO;
	}

	public List<GSTSummaryTaxVO> getFormOutputTaxList() {
		return formOutputTaxList;
	}

	public void setFormOutputTaxList(List<GSTSummaryTaxVO> formOutputTaxList) {
		this.formOutputTaxList = formOutputTaxList;
	}

	public List<GSTSummaryTaxVO> getFormInputTaxList() {
		return formInputTaxList;
	}

	public void setFormInputTaxList(List<GSTSummaryTaxVO> formInputTaxList) {
		this.formInputTaxList = formInputTaxList;
	}
	
	public List<GSTSummaryTaxVO> getFormExemptedSupplyList() {
		return formExemptedSupplyList;
	}

	public void setFormExemptedSupplyList(
			List<GSTSummaryTaxVO> formExemptedSupplyList) {
		this.formExemptedSupplyList = formExemptedSupplyList;
	}
	
	public List<GSTSummaryTaxVO> getFormOutputTaxAdjustmentList() {
		return formOutputTaxAdjustmentList;
	}

	public void setFormOutputTaxAdjustmentList(
			List<GSTSummaryTaxVO> formOutputTaxAdjustmentList) {
		this.formOutputTaxAdjustmentList = formOutputTaxAdjustmentList;
	}

	public List<GSTSummaryTaxVO> getFormInputTaxAdjustmentList() {
		return formInputTaxAdjustmentList;
	}

	public void setFormInputTaxAdjustmentList(
			List<GSTSummaryTaxVO> formInputTaxAdjustmentList) {
		this.formInputTaxAdjustmentList = formInputTaxAdjustmentList;
	}

	public List<GSTSummaryTaxVO> getFormTotalAmountList() {
		return formTotalAmountList;
	}

	public void setFormTotalAmountList(List<GSTSummaryTaxVO> formTotalAmountList) {
		this.formTotalAmountList = formTotalAmountList;
	}
	
}