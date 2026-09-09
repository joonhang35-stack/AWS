package com.bcs.zsg.acct.web.bean;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.bo.FinancialPeriodBO;
import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.acct.vo.AcctBalViewVO;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.cfg.sec.helper.UserRoleHelper;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.bo.GSTBO;
import com.bcs.zsg.gst.helper.GSTType;

public class ChartOfAcctBean extends AppBackingBean {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient FinancialPeriodBO finPeriodBO;
	@Autowired
	private transient GSTBO gstBO;
	
	private AcctCatVO acctCatVO;
	private AcctSubCatVO acctSubCatVO;
	private AcctVO acctVO;
	private AcctVO prevAcctVO;
	private AcctViewVO acctViewVO;
	private AcctBalVO acctBalVO;
	private AcctBalViewVO acctBalViewVO;
	private AcctVO parentAcctVO;
	
	private List<AcctCatVO> acctCatList;
	private List<AcctSubCatVO> acctSubCatList;
	private List<AcctVO> acctList;
	private List<AcctViewVO> acctViewList;
	private List<AcctViewVO> filteredAcctViewList;
	
	private int option;
	
	private Boolean chartOfAcctAuth = false;
	private Boolean isClosedAcct = false;
	
	private List<String> yearList;
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	//private static final int YEAR_PERIOD = 5;
	
	private List<TaxCodeVO> inputTaxCodeVOList;
	private List<TaxCodeVO> outputTaxCodeVOList;
	private Map<String, TaxCodeVO> hmTaxCodeVO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		acctCatVO = new AcctCatVO();
		acctSubCatVO = new AcctSubCatVO();
		acctVO = new AcctVO();
		acctBalVO = new AcctBalVO();
		option = 0;
		
		acctBalVO.setDebitBeginBal(0.0);
		acctBalVO.setCreditBeginBal(0.0);
		
//		clearFilters();
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			// Initialize search param
			initSearchParam();
			
			// Initialize year list
			yearList = chartOfAcctBO.getFinPeriodYearList(getSessionInfoBean().getCompanyVO().getId());
			searchParamVO.setObj1(yearList.get(yearList.size() - 1));
			
			List<FinancialPeriodVO> finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			if (CollectionUtils.isNotEmpty(finPeriodList)) {
				searchParamVO.setFromDate(finPeriodList.get(0).getDtStart());
				searchParamVO.setToDate(finPeriodList.get(finPeriodList.size() - 1).getDtEnd());
			}
			
			resetForm();
			loadAccCatList();
			
			chartOfAcctAuth = UserRoleHelper.checkIsAccountManager(this.getUserInfo().getRoleList());
			inputTaxCodeVOList = gstBO.getTaxCodeList(GSTType.INPUT);
			outputTaxCodeVOList = gstBO.getTaxCodeList(GSTType.OUTPUT);

			hmTaxCodeVO = new HashMap<String, TaxCodeVO>();
			for(TaxCodeVO taxCodeVO : inputTaxCodeVOList)
				hmTaxCodeVO.put(taxCodeVO.getCode(), taxCodeVO);

			for(TaxCodeVO taxCodeVO : outputTaxCodeVOList)
				hmTaxCodeVO.put(taxCodeVO.getCode(), taxCodeVO);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add account category
	 */
	public void addAcctCat() {
		try {
			// add account category
			chartOfAcctBO.addAcctCat(acctCatVO);
			// refresh account category list
			loadAccCatList();
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add account sub category
	 */
	public void addAcctSubCat() {
		try {
			// add account sub category
			acctSubCatVO.setIdCompany(getSessionInfoBean().getCompanyVO().getId());
			acctSubCatVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			chartOfAcctBO.addAcctSubCat(acctSubCatVO);
			loadAccCatList();
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update account sub category
	 */
	public void updAcctSubCat() {
		try {
			chartOfAcctBO.updateVO(acctSubCatVO);
			loadAccCatList();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete account sub category
	 */
	public void delAcctSubCat() {
		try {
			chartOfAcctBO.delAcctSubCat(acctSubCatVO);
			loadAccCatList();
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add account
	 */
	public void addAcct() {
		try {
			// add account
			acctVO.setIdCompany(getSessionInfoBean().getCompanyVO().getId());
			acctVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
			if(acctVO.getGstType().equals(BaseConstant.PAD_DASH))
				acctVO.setTaxCode(null);
			chartOfAcctBO.addAcct(acctVO, acctBalVO);
			// refresh account category list
			loadAccCatList();
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update account
	 */
	public void updAcct() {
		try {

			if(acctVO.getGstType().equals(BaseConstant.PAD_DASH))
				acctVO.setTaxCode(null);
			chartOfAcctBO.updAcct(acctVO, prevAcctVO, acctBalVO);
			// refresh account category list
			loadAccCatList();
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete account
	 */
	public void delAcct() {
		try {
			chartOfAcctBO.delAcct(acctVO, acctBalVO);
			// refresh account category list
			loadAccCatList();
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void updBeginBal() {
		try {
			/*acctBalVO.setDebitCloseBal(acctBalVO.getDebitBeginBal());
			acctBalVO.setCreditCloseBal(acctBalVO.getCreditBeginBal());
			acctBalVO.setDebitCurrentBal(acctBalVO.getDebitBeginBal());
			acctBalVO.setCreditCurrentBal(acctBalVO.getCreditBeginBal());*/
			//chartOfAcctBO.updateVO(acctBalVO);
			chartOfAcctBO.updBeginBal(acctBalVO);
			handleBeginBal();
			loadAccCatList();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void clearFilters() {
        // Try to clear the datatable filters
        this.filteredAcctViewList = null;        
    }
	
	/**
	 * Handle account category selection
	 */
	public void handleAcctCatSelect() {
		try {
			acctSubCatList = chartOfAcctBO.getAcctSubCatList(acctVO.getIdAcctCat(), getSessionInfoBean().getCompanyVO().getId());
			acctList = chartOfAcctBO.getAcctList(getSessionInfoBean().getCompanyVO().getId(), acctVO.getIdAcctCat());
			if (acctVO.getIdParentAcct() == null) {
				parentAcctVO = new AcctVO();
			}
			
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Handle account selection
	 */
	public void handleAcctSelect() {
		try {
			int flag = 0;
			
			for (AcctVO vo : acctList) {
				if (vo.getCode().equals(acctVO.getCode())) {
					acctVO.setDesc(vo.getDesc());
					flag = 1;
					break;
				}
			}
			
			// check whether account is selected or not
			if (flag == 0) {
				acctVO.setSubCode("");
				//acctVO.setDesc(null);
			}
			
			parentAcctVO = new AcctVO();
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Handle account click for edit/delete purpose
	 * @param vo
	 */
	public void handleAcctClicked(AcctViewVO vo) {
		try {
			acctVO = new AcctVO();
			acctVO.setId(vo.getId());
			acctVO.setIdCompany(vo.getIdCompany());
			//acctVO.setIdAcctCat(vo.getAcctCatVO().getId());
			//acctVO.setIdAcctSubCat(vo.getAcctSubCatVO().getId());
			acctVO.setIdAcctCat(vo.getIdAcctCat());
			acctVO.setIdAcctSubCat(vo.getIdAcctSubCat());
			acctVO.setIdParentAcct(vo.getIdParentAcct());
			acctVO.setCode(vo.getCode());
			acctVO.setSubCode(vo.getSubCode());
			acctVO.setDesc(vo.getDesc());
			acctVO.setSubDesc(vo.getSubDesc());
			acctVO.setTaxCode(vo.getTaxCode());
			acctVO.setTypeCode(vo.getTypeCode());
			acctVO.setIsPreSales(vo.getIsPreSales());
			acctVO.setStatusCode(vo.getStatusCode());
			acctVO.setCreatedDate(vo.getCreatedDate());
			acctVO.setCreatedBy(vo.getCreatedBy());
			// clone account info before changed
			prevAcctVO = (AcctVO) acctVO.clone();
			parentAcctVO = chartOfAcctBO.getAcctVO(acctVO.getIdParentAcct());
			
			// set account balance
			//acctBalVO = vo.getAcctBalSet().iterator().next();
			acctBalVO = chartOfAcctBO.getAcctBeginBalVO(acctVO.getId());
			// return sub category list and account list
			handleAcctCatSelect();
			handleDebitCreditInput();
			
			if(acctVO.getTaxCode() != null) {
				acctVO.setGstType("OUT");
				for(TaxCodeVO taxCodeVO : inputTaxCodeVOList) {
					if(acctVO.getTaxCode().equals(taxCodeVO.getCode())) {
						acctVO.setGstType("IN");
						break;
					}
				}
			} else {
				acctVO.setGstType(null);
			}
			
			isClosedAcct = chartOfAcctBO.isClosedAcct(vo.getIdCompany(), vo.getId());
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Handle debit/credit input
	 */
	public void handleDebitCreditInput() {
		try {
			if (acctBalVO.getDebitBeginBal() != 0) option = 1;
			else if (acctBalVO.getCreditBeginBal() != 0) option = -1;
			else option = 0;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle begining balance
	 */
	public void handleBeginBal() {
		try {
			acctViewList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), "all", searchParamVO);
			if (CollectionUtils.isNotEmpty(acctViewList)) acctBalViewVO = getAcctTotalBeginBal(acctViewList);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Year select event
	 */
	public void handleSelectYear() {
		try {
			List<FinancialPeriodVO> finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			if (CollectionUtils.isNotEmpty(finPeriodList)) {
				searchParamVO.setFromDate(finPeriodList.get(0).getDtStart());
				searchParamVO.setToDate(finPeriodList.get(finPeriodList.size()-1).getDtEnd());
			} else {
				throw new BusinessException(CommonErrConstant.ERR_ACCT_CHART_NOT_FIN_PERIOD);
			}
			
			loadAccCatList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onSelectParentAcct() {
		try {
			acctViewList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), null);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void onParentAcctSelected(SelectEvent event) {
		try {
			if (parentAcctVO == null) {
				parentAcctVO = new AcctVO();
			}
			
			AcctViewVO acctViewVO = (AcctViewVO) event.getObject();
			setParentAcctVO(acctViewVO);
			acctVO.setIdParentAcct(acctViewVO.getId());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public String getGSTRate(String taxCode) {
		if(StringUtils.isEmpty(taxCode))
			return "";
		
		return (String) (hmTaxCodeVO.containsKey(taxCode) ? "(" + hmTaxCodeVO.get(taxCode).getRate() + " %)": "");
	}
	
	/**********
	 * HELPER *
	 **********/
	
	/*
	 * 
	 * @param voList
	 * @return
	 */
	private AcctBalViewVO getAcctTotalBeginBal(List<AcctViewVO> voList) {
		double totalDebit = 0;
		double totalCredit = 0;
		for (AcctViewVO v : voList) {
			totalDebit += v.getDebitBeginBal();
			totalCredit += v.getCreditBeginBal();
		}
		
		AcctBalViewVO vo = new AcctBalViewVO();
		vo.setTotalBeginDebit(totalDebit);
		vo.setTotalBeginCredit(totalCredit);
		return vo;
	}
	
	/*
	 * 
	 * @throws BusinessException
	 */
	private void loadAccCatList() throws BusinessException {
		acctCatList = chartOfAcctBO.getAcctCatList(getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
	}
	
	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the acctCatVO
	 */
	public AcctCatVO getAcctCatVO() {
		return acctCatVO;
	}

	/**
	 * @param acctCatVO the acctCatVO to set
	 */
	public void setAcctCatVO(AcctCatVO acctCatVO) {
		this.acctCatVO = acctCatVO;
	}

	/**
	 * @return the acctSubCatVO
	 */
	public AcctSubCatVO getAcctSubCatVO() {
		return acctSubCatVO;
	}

	/**
	 * @param acctSubCatVO the acctSubCatVO to set
	 */
	public void setAcctSubCatVO(AcctSubCatVO acctSubCatVO) {
		this.acctSubCatVO = acctSubCatVO;
	}

	/**
	 * @return the acctVO
	 */
	public AcctVO getAcctVO() {
		return acctVO;
	}

	/**
	 * @param acctVO the acctVO to set
	 */
	public void setAcctVO(AcctVO acctVO) {
		this.acctVO = acctVO;
	}

	/**
	 * @return the prevAcctVO
	 */
	public AcctVO getPrevAcctVO() {
		return prevAcctVO;
	}

	/**
	 * @param prevAcctVO the prevAcctVO to set
	 */
	public void setPrevAcctVO(AcctVO prevAcctVO) {
		this.prevAcctVO = prevAcctVO;
	}

	/**
	 * @return the acctViewVO
	 */
	public AcctViewVO getAcctViewVO() {
		return acctViewVO;
	}

	/**
	 * @param acctViewVO the acctViewVO to set
	 */
	public void setAcctViewVO(AcctViewVO acctViewVO) {
		this.acctViewVO = acctViewVO;
	}

	/**
	 * @return the acctBalVO
	 */
	public AcctBalVO getAcctBalVO() {
		return acctBalVO;
	}

	/**
	 * @param acctBalVO the acctBalVO to set
	 */
	public void setAcctBalVO(AcctBalVO acctBalVO) {
		this.acctBalVO = acctBalVO;
	}

	/**
	 * @return the acctBalViewBO
	 */
	public AcctBalViewVO getAcctBalViewVO() {
		return acctBalViewVO;
	}

	/**
	 * @param acctBalViewBO the acctBalViewBO to set
	 */
	public void setAcctBalViewVO(AcctBalViewVO acctBalViewVO) {
		this.acctBalViewVO = acctBalViewVO;
	}

	/**
	 * @return the acctCatList
	 */
	public List<AcctCatVO> getAcctCatList() {
		return acctCatList;
	}

	/**
	 * @param acctCatList the acctCatList to set
	 */
	public void setAcctCatList(List<AcctCatVO> acctCatList) {
		this.acctCatList = acctCatList;
	}

	/**
	 * @return the acctSubCatList
	 */
	public List<AcctSubCatVO> getAcctSubCatList() {
		return acctSubCatList;
	}

	/**
	 * @param acctSubCatList the acctSubCatList to set
	 */
	public void setAcctSubCatList(List<AcctSubCatVO> acctSubCatList) {
		this.acctSubCatList = acctSubCatList;
	}

	/**
	 * @return the acctList
	 */
	public List<AcctVO> getAcctList() {
		return acctList;
	}

	/**
	 * @param acctList the acctList to set
	 */
	public void setAcctList(List<AcctVO> acctList) {
		this.acctList = acctList;
	}

	/**
	 * @return the acctViewList
	 */
	public List<AcctViewVO> getAcctViewList() {
		return acctViewList;
	}

	/**
	 * @param acctViewList the acctViewList to set
	 */
	public void setAcctViewList(List<AcctViewVO> acctViewList) {
		this.acctViewList = acctViewList;
	}

	/**
	 * @return the option
	 */
	public int getOption() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(int option) {
		this.option = option;
	}

	/**
	 * @return the chartOfAcctAuth
	 */
	public Boolean getChartOfAcctAuth() {
		return chartOfAcctAuth;
	}

	/**
	 * @param chartOfAcctAuth the chartOfAcctAuth to set
	 */
	public void setChartOfAcctAuth(Boolean chartOfAcctAuth) {
		this.chartOfAcctAuth = chartOfAcctAuth;
	}

	/**
	 * @return the isClosedAcct
	 */
	public Boolean getIsClosedAcct() {
		return isClosedAcct;
	}

	/**
	 * @param isClosedAcct the isClosedAcct to set
	 */
	public void setIsClosedAcct(Boolean isClosedAcct) {
		this.isClosedAcct = isClosedAcct;
	}
	
	/**
	 * @return the yearList
	 */
	public List<String> getYearList() {
		return yearList;
	}

	/**
	 * @param yearList the yearList to set
	 */
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	/**
	 * @return the inputTaxCodeVOList
	 */
	public List<TaxCodeVO> getInputTaxCodeVOList() {
		return inputTaxCodeVOList;
	}

	/**
	 * @return the outputTaxCodeVOList
	 */
	public List<TaxCodeVO> getOutputTaxCodeVOList() {
		return outputTaxCodeVOList;
	}

	/**
	 * @return the filteredAcctViewList
	 */
	public List<AcctViewVO> getFilteredAcctViewList() {
		return filteredAcctViewList;
	}

	/**
	 * @param filteredAcctViewList the filteredAcctViewList to set
	 */
	public void setFilteredAcctViewList(List<AcctViewVO> filteredAcctViewList) {
		this.filteredAcctViewList = filteredAcctViewList;
	}

	public AcctVO getParentAcctVO() {
		return parentAcctVO;
	}

	public void setParentAcctVO(AcctVO parentAcctVO) {
		this.parentAcctVO = parentAcctVO;
	}
}