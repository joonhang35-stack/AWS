package com.bcs.zsg.bank.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.bo.BankAcctBO;
import com.bcs.zsg.bank.bo.CashBookBO;
import com.bcs.zsg.bank.bo.ReconciliationBO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.BankReconVO;
import com.bcs.zsg.bank.vo.CashBookBalVO;
import com.bcs.zsg.bank.vo.CashBookSumVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.bo.CommonObject;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.helper.MODULE;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;

public class ReconciliationBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient BankAcctBO bankAcctBO;
	@Autowired
	private transient ReconciliationBO reconBO;
	@Autowired
	private transient CashBookBO cashBookBO;
	
	private BankReconVO bankReconVO;
	private CashBookBalVO cashBookBalVO;
	private BankAcctViewVO bankAcctViewVO;
	
	private List<BankAcctViewVO> bankAcctList;
	private List<BankReconVO> bankReconList;
	private List<CashBookSumVO> cashBookList;
	private List<CashBookSumVO> prevCashBookList;
	private List<CashBookSumVO> selectedCBList;
	private List<CashBookSumVO> unselectedCBList;
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
	private final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	private boolean isAddReconForm;
	private TrackingLogUtils trackingLogUtils;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		bankReconVO = new BankReconVO();
		cashBookBalVO = new CashBookBalVO();
		
		cashBookList = null;
		prevCashBookList = null;
		selectedCBList = new ArrayList<CashBookSumVO>();
		unselectedCBList = new ArrayList<CashBookSumVO>();
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			trackingLogUtils = new TrackingLogUtils(this.getClass());
			
			initSearchParam();
			resetForm();
			bankAcctList = bankAcctBO.getBankAcctList(getSessionInfoBean().getCompanyVO().getId());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add reconciliation
	 */
	public void addRecon() {
		try {
			trackingLogUtils.startLogs();
			
			if (!validateBank()) return;
			
			recalculateBankReconBal();
			if(reconBO.insertVO(bankReconVO)) {
				cashBookBO.updCashBookClear(selectedCBList, unselectedCBList);
				//loadBankReconList();
				//resetForm();
				searchBankRecon(true);
				selectedCBList = new ArrayList<CashBookSumVO>();
				unselectedCBList = new ArrayList<CashBookSumVO>();
				successResult();
				
			} else warningResult(CommonErrConstant.ERR_BANK_INSERT_DUPLICATE);
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("addRecon");
		}
	}

	/**
	 * Update reconciliation
	 */
	public void updRecon() {
		try {
			trackingLogUtils.startLogs();
			
			if (!validateBank()) return;
			
			//Set the latest value for date range to ensure changes is reflected in DB
			setVOSaveValue();
			recalculateBankReconBal();
			reconBO.updateVO(bankReconVO);
			cashBookBO.updCashBookClear(selectedCBList, unselectedCBList);
			//resetForm();
			//loadBankReconList();
			searchBankRecon(true);
			selectedCBList = new ArrayList<CashBookSumVO>();
			unselectedCBList = new ArrayList<CashBookSumVO>();
			resetFilteredObjList();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("updRecon");
		}
	}
	
	/**
	 * Delete recon
	 */
	public void deleteRecord() {
		try {
			List<BankReconVO> listDel= new ArrayList<BankReconVO>();
			listDel.add(bankReconVO);
			reconBO.deleteRecord(listDel);
			
			resetForm();
			loadBankReconList();
			successResult("ack.common.success.delete");
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Cancel recon
	 */
	public void cancelRecon() {
		try {
			resetForm();
			loadBankReconList();
			resetFilteredObjList();
			isAddReconForm = false;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle bank selection
	 */
	public void handleBankSelect() {
		try {
			loadBankReconList();
			resetFilteredObjList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Search bank recon
	 */
	public void searchBankRecon(boolean isRestart) {
		try {
			trackingLogUtils.startLogs();
			
			searchParamVO.setCompanyVO(getSessionInfoBean().getCompanyVO());
			
			if (searchParamVO.getObj2() == null) searchParamVO.setObj2("3");
			cashBookList = cashBookBO.getCashBookList(searchParamVO, MODULE.BANK.RECON);
			prevCashBookList = cashBookBO.getPrevCashBookList(searchParamVO, MODULE.BANK.RECON);
			
			if (CollectionUtils.isNotEmpty(cashBookList)) {
				//selectedObjs = new Object[cashBookList.size()];
				for (CashBookSumVO vo : cashBookList) {
					if (vo.getIsClear() && vo.getDtClear() != null && dateFormat.format(vo.getDtClear()).equals(dateFormat.format(searchParamVO.getToDate()))) {
						//selectedCBList.add(vo);
					} else vo.setIsClear(false);
					
					// set selected
					if (CollectionUtils.isNotEmpty(selectedCBList)) {
						for (CashBookSumVO selectedVO : selectedCBList) {
							if (vo.getId().equals(selectedVO.getId())) {
								vo.setDtClear(selectedVO.getDtClear());
								vo.setIsClear(selectedVO.getIsClear());
								break;
							}
						}
					}
					// set unselected
					if (CollectionUtils.isNotEmpty(unselectedCBList)) {
						for (CashBookSumVO unselectedVO : unselectedCBList) {
							if (vo.getId().equals(unselectedVO.getId())) {
								vo.setDtClear(unselectedVO.getDtClear());
								vo.setIsClear(unselectedVO.getIsClear());
								break;
							}
						}
					}
				}
			}
			
			if (CollectionUtils.isNotEmpty(prevCashBookList)) {
				for (CashBookSumVO vo : prevCashBookList) {
					if (vo.getIsClear() && vo.getDtClear() != null && dateFormat.format(vo.getDtClear()).equals(dateFormat.format(searchParamVO.getToDate()))) {
						//selectedCBList.add(vo);
					} else vo.setIsClear(false);
					
					// set selected
					if (CollectionUtils.isNotEmpty(selectedCBList)) {
						for (CashBookSumVO selectedVO : selectedCBList) {
							if (vo.getId().equals(selectedVO.getId())) {
								vo.setDtClear(selectedVO.getDtClear());
								vo.setIsClear(selectedVO.getIsClear());
								break;
							}
						}
					}
					// set unselected
					if (CollectionUtils.isNotEmpty(unselectedCBList)) {
						for (CashBookSumVO unselectedVO : unselectedCBList) {
							if (vo.getId().equals(unselectedVO.getId())) {
								vo.setDtClear(unselectedVO.getDtClear());
								vo.setIsClear(unselectedVO.getIsClear());
								break;
							}
						}
					}
				}
			}
			
			cashBookBalVO = cashBookBO.getCashBookBal(searchParamVO);
			if (isRestart && bankReconVO.getId() != null) bankReconVO = reconBO.getBankRecon(bankReconVO, searchParamVO);
			if (bankReconVO == null) bankReconVO = new BankReconVO();
			bankReconVO = reconBO.getBankReconCashBookBal(searchParamVO, bankReconVO);
			//bankReconVO.setCbCr(bankReconVO.getCashBookCr());
			//bankReconVO.setCbDr(bankReconVO.getCashBookDr());
			//bankReconVO.setCbBal(bankReconVO.getCashBookBal());
			
			bankReconVO.setCashBookBal(reconBO.getPrevBankReconCashBookBal(searchParamVO, bankReconVO));
			//BigDecimal cbBal = new BigDecimal(bankReconVO.getCashBookBal()).subtract(new BigDecimal(bankReconVO.getCbCr().toString())).add(new BigDecimal(bankReconVO.getCbDr().toString()));
			//bankReconVO.setCbBal(cbBal.doubleValue());
			//bankReconVO.setCashBookCr(0.0);
			//bankReconVO.setCashBookDr(0.0);
			
			BigDecimal cbBal = new BigDecimal(bankReconVO.getCashBookBal().toString())
									.add(new BigDecimal(bankReconVO.getCbBal().toString()))
									.subtract(new BigDecimal(bankReconVO.getCbCr().toString()))
									.add(new BigDecimal(bankReconVO.getCbDr().toString()));
			bankReconVO.setCbBal(cbBal.doubleValue());
			
			if (bankReconVO.getId() == null) {
				bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCashBookBal().toString()).doubleValue());
			}
			setVOSaveValue();
			
		} catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("searchBankRecon");
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void rowSelectCheckBox(CashBookSumVO vo) {
		try {
			if (vo.getIsClear()) {
				vo.setDtClear(searchParamVO.getToDate());
				selectedCBList.add(vo);
				unselectedCBList.remove(vo);
				
				if (vo.getCredit() > 0) {
					bankReconVO.setCbCr(new BigDecimal(bankReconVO.getCbCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setCbDiffCr(new BigDecimal(bankReconVO.getCbDiffCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setObCr(new BigDecimal(bankReconVO.getObCr().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setNoOutsCr(bankReconVO.getNoOutsCr() - 1);
				} else {
					bankReconVO.setCbDr(new BigDecimal(bankReconVO.getCbDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setCbDiffDr(new BigDecimal(bankReconVO.getCbDiffDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setObDr(new BigDecimal(bankReconVO.getObDr().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setNoOutsDr(bankReconVO.getNoOutsDr() - 1);
				}
			} else {
				vo.setDtClear(null);
				selectedCBList.remove(vo);
				unselectedCBList.add(vo);
				
				if (vo.getCredit() > 0) {
					bankReconVO.setCbCr(new BigDecimal(bankReconVO.getCbCr().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setCbDiffCr(new BigDecimal(bankReconVO.getCbDiffCr().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setObCr(new BigDecimal(bankReconVO.getObCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
					bankReconVO.setNoOutsCr(bankReconVO.getNoOutsCr() + 1);
				} else {
					bankReconVO.setCbDr(new BigDecimal(bankReconVO.getCbDr().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setCbDiffDr(new BigDecimal(bankReconVO.getCbDiffDr().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setObDr(new BigDecimal(bankReconVO.getObDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
					bankReconVO.setNoOutsDr(bankReconVO.getNoOutsDr() + 1);
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	/*public void rowChecked(SelectEvent event) {
		try {
			CashBookSumVO vo = (CashBookSumVO) event.getObject();
			vo.setIsClear(true);
			vo.setDtClear(searchParamVO.getToDate());
			selectedCBList.add(vo);
			unselectedCBList.remove(vo);
			
			if (vo.getCredit() > 0) {
				bankReconVO.setCbCr(new BigDecimal(bankReconVO.getCbCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setCbDiffCr(new BigDecimal(bankReconVO.getCbDiffCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setObCr(new BigDecimal(bankReconVO.getObCr().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setNoOutsCr(bankReconVO.getNoOutsCr() - 1);
			} else {
				bankReconVO.setCbDr(new BigDecimal(bankReconVO.getCbDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setCbDiffDr(new BigDecimal(bankReconVO.getCbDiffDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setObDr(new BigDecimal(bankReconVO.getObDr().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setNoOutsDr(bankReconVO.getNoOutsDr() - 1);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}*/
	
	/**
	 * 
	 * @param event
	 */
	/*public void rowUnChecked(UnselectEvent event) {
		try {
			CashBookSumVO vo = (CashBookSumVO) event.getObject();
			vo.setIsClear(false);
			vo.setDtClear(null);
			selectedCBList.remove(vo);
			unselectedCBList.add(vo);
			
			if (vo.getCredit() > 0) {
				bankReconVO.setCbCr(new BigDecimal(bankReconVO.getCbCr().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setCbDiffCr(new BigDecimal(bankReconVO.getCbDiffCr().toString()).subtract(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setObCr(new BigDecimal(bankReconVO.getObCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
				bankReconVO.setNoOutsCr(bankReconVO.getNoOutsCr() + 1);
			} else {
				bankReconVO.setCbDr(new BigDecimal(bankReconVO.getCbDr().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setCbDiffDr(new BigDecimal(bankReconVO.getCbDiffDr().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setObDr(new BigDecimal(bankReconVO.getObDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setCbBal(new BigDecimal(bankReconVO.getCbBal().toString()).subtract(new BigDecimal(vo.getDebit().toString())).doubleValue());
				bankReconVO.setNoOutsDr(bankReconVO.getNoOutsDr() + 1);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}*/
	
	public double roundDoubleValue(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
	
	/**********
	 * HELPER *
	 **********/

	/*
	 * 
	 * @throws BusinessException
	 */
	private void loadBankReconList() throws BusinessException {
		trackingLogUtils.startLogs();
		bankReconList = reconBO.getBankReconList(searchParamVO, bankAcctList);
		trackingLogUtils.endLogs("loadBankReconList");
	}

	/*
	 * 
	 * @param cashbookType
	 * @param isRowCount
	 * @return
	 */
	private CommonObject genFilterItem(String cashbookType, boolean isRowCount) {
		CommonObject tmpComObj = new CommonObject("Reconciliation");
    			
		List<Object> tmpListResult = new ArrayList<Object>();
		if(isRowCount) 
			tmpListResult.add(CashBookVO.class);
		else
			tmpListResult.add(CashBookSumVO.class);
		tmpListResult.add(MODULE.BANK.RECON);
		tmpListResult.add(cashbookType);
		tmpListResult.add(searchParamVO);
		tmpComObj.setListSource(tmpListResult);
		
		return tmpComObj;
	}
	
	/*
	 * 
	 */
	private void setVOSaveValue() {
		bankReconVO.setIdBank(Long.parseLong((String) searchParamVO.getObj1()));
		bankReconVO.setDtStart(searchParamVO.getFromDate());
		bankReconVO.setDtEnd(searchParamVO.getToDate());
		bankReconVO.setYear(yearFormat.format(searchParamVO.getFromDate()));
	}
	
	/*
	 * 
	 * @throws BusinessException
	 */
	private void recalculateBankReconBal() throws BusinessException {
		/*if (CollectionUtils.isNotEmpty(selectedCBList)) {
			for (CashBookSumVO vo : selectedCBList) {
				if (vo.getCredit() > 0) bankReconVO.setCashBookCr(new BigDecimal(bankReconVO.getCashBookCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
				else bankReconVO.setCashBookDr(new BigDecimal(bankReconVO.getCashBookDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
			}
			bankReconVO.setCashBookBal(new BigDecimal(bankReconVO.getCashBookBal().toString()).add(new BigDecimal(bankReconVO.getCashBookDr().toString())).
								subtract(new BigDecimal(bankReconVO.getCashBookCr().toString())).doubleValue());
		}*/
		
		if (CollectionUtils.isNotEmpty(cashBookList)) {
			if (searchParamVO.getObj2().equals("2")) bankReconVO.setCashBookDr(0.0);
			else if (searchParamVO.getObj2().equals("4")) bankReconVO.setCashBookCr(0.0);
			else if (searchParamVO.getObj2().equals("3") || searchParamVO.getObj2().equals("5")) {
				bankReconVO.setCashBookCr(0.0);
				bankReconVO.setCashBookDr(0.0);
			}
			
			for (CashBookSumVO vo : cashBookList) {
				if (vo.getIsClear() && monthFormat.format(vo.getDtClear()).equals(monthFormat.format(searchParamVO.getToDate()))) {
					if (vo.getCredit() > 0) bankReconVO.setCashBookCr(new BigDecimal(bankReconVO.getCashBookCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
					else bankReconVO.setCashBookDr(new BigDecimal(bankReconVO.getCashBookDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
				}
			}
		}
		
		if (CollectionUtils.isNotEmpty(prevCashBookList)) {
			for (CashBookSumVO vo : prevCashBookList) {
				if (vo.getIsClear() && monthFormat.format(vo.getDtClear()).equals(monthFormat.format(searchParamVO.getToDate()))) {
					if (vo.getCredit() > 0) {
						bankReconVO.setCashBookCr(new BigDecimal(bankReconVO.getCashBookCr().toString()).add(new BigDecimal(vo.getCredit().toString())).doubleValue());
						
					} else {
						bankReconVO.setCashBookDr(new BigDecimal(bankReconVO.getCashBookDr().toString()).add(new BigDecimal(vo.getDebit().toString())).doubleValue());
					}
				}
			}
		}
		bankReconVO.setCashBookBal(new BigDecimal(bankReconVO.getCashBookBal().toString()).add(new BigDecimal(bankReconVO.getCashBookDr().toString())).
				subtract(new BigDecimal(bankReconVO.getCashBookCr().toString())).doubleValue());
	}
	
	/*
	 * 
	 * @return
	 */
	private boolean validateBank() {
		if (bankReconVO.getIdBank() == null) {
			warningResult("ERR_NO_CASHBOOK_DATA");
			return false;
		}
		return true;
	}
	
	/**
	 *
	 */
	public class LDMCashBookListVO extends LazyDataModel<CashBookSumVO> implements Serializable {  
		private static final long serialVersionUID = 1L;
		protected List<CashBookSumVO> cashBookListVO = new ArrayList<CashBookSumVO>();  
        
		public LDMCashBookListVO() {   } 
		
		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@SuppressWarnings("unchecked")
		@Override  
	    public List<CashBookSumVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
	        try {
	        	cashBookListVO = (List<CashBookSumVO>)cashBookBO.getLazyRecordList(genFilterItem(CommonConstant.CASHBOOK_NORMAL, false),
	        			first, pageSize, sortField, sortOrder);
	        	
		    } catch (Throwable t) {
				errorResult(t);
			}
	        return cashBookListVO;
	    }
	    
		public List<CashBookSumVO> getCashBookListVO() {
			return cashBookListVO;
		}
		public void setCashBookListVO(List<CashBookSumVO> cashBookListVO) {
			this.cashBookListVO = cashBookListVO;
		}
	}        
	
	/**
	 *
	 */
	public class LDMPrevCashBookListVO extends LDMCashBookListVO implements Serializable {  
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
		 */
		@SuppressWarnings("unchecked")
		@Override  
	    public List<CashBookSumVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
	        try {
	        	cashBookListVO = (List<CashBookSumVO>)cashBookBO.getLazyRecordList(genFilterItem(CommonConstant.CASHBOOK_PREV, false), 
	        							first, pageSize, sortField, sortOrder);
	        	
		    } catch (Throwable t) {
				errorResult(t);
			}
	        return cashBookListVO;
	    }
	}
	
	/**
	 * Print Bank Reconciliation 
	 */
	public void printBankReconciliation(String xlsOrPDf) {
		try {
			Date now = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd'_'HHmmss");
			SimpleDateFormat ftDate = new SimpleDateFormat("dd-MMM-yyyy");
			BigDecimal subTotalCurrentC = new BigDecimal("0.0");
			BigDecimal subTotalCurrentD = new BigDecimal("0.0");
			BigDecimal subTotalPrevC = new BigDecimal("0.0");
			BigDecimal subTotalPrevD = new BigDecimal("0.0");
			BigDecimal grandTotalC = new BigDecimal("0.0");
			BigDecimal grandTotalD = new BigDecimal("0.0");

			List <BankReconVO> bankReconList = new ArrayList<BankReconVO>();

			bankReconList.add(bankReconVO);

			if (CollectionUtils.isEmpty(bankReconList)) throw new BusinessException("No result.");

			HashMap map = new HashMap();
			map.put("companyName", getSessionInfoBean().getCompanyVO().getName());
			map.put("bankName", "");
			map.put("accountNo", "");
			for (BankAcctViewVO vo : bankAcctList) {
				if ((vo.getId()+"").equals(searchParamVO.getObj1())) {
					map.put("bankName", vo.getName());
					map.put("accountNo", vo.getAcctNo());
				}
			}
			map.put("fromDate", ftDate.format(searchParamVO.getFromDate()));
			map.put("toDate", ftDate.format(searchParamVO.getToDate()));
			/*if (searchParamVO.getObj2().equals("1")) {
				map.put("type", "All Outstanding");
			} else if (searchParamVO.getObj2().equals("2")) {
				map.put("type", "All Debit");
			} else if (searchParamVO.getObj2().equals("3")) {
				map.put("type", "All Transactions");
			} else if (searchParamVO.getObj2().equals("4")) {
				map.put("type", "All Credit");
			} else if (searchParamVO.getObj2().equals("5")) {
				map.put("type", "All Cleared");
			} else {
				map.put("type", "");
			}*/
			map.put("cashBookList", cashBookList);
			map.put("prevCashBookList", prevCashBookList);
			for(CashBookSumVO vo : cashBookList) {
				subTotalCurrentC = subTotalCurrentC.add(BigDecimal.valueOf(vo.getCredit()));
				subTotalCurrentD = subTotalCurrentD.add(BigDecimal.valueOf(vo.getDebit()));
			}
			for(CashBookSumVO vo : prevCashBookList) {
				subTotalPrevC = subTotalPrevC.add(BigDecimal.valueOf(vo.getCredit()));
				subTotalPrevD = subTotalPrevD.add(BigDecimal.valueOf(vo.getDebit()));
			}
			grandTotalC = grandTotalC.add(subTotalCurrentC).add(subTotalPrevC);
			grandTotalD = grandTotalD.add(subTotalCurrentD).add(subTotalPrevD);
			map.put("subTotalCurrentC", subTotalCurrentC);
			map.put("subTotalCurrentD", subTotalCurrentD);
			map.put("subTotalPrevC", subTotalPrevC);
			map.put("subTotalPrevD", subTotalPrevD);
			map.put("grandTotalC", grandTotalC);
			map.put("grandTotalD", grandTotalD);
			if (xlsOrPDf.equals("PDF")) map.put("isExcel", false);
			else map.put("isExcel", true);
			
			String jasperFileName = CommonConstant.JAS_RPT_BC;
			String reportName = CommonConstant.PDF_RPT_BC;
			
			JasperPrint jasperPrint = ReportUtils.getJasperPrint(bankReconList, map, jasperFileName);
//			ReportUtils.printReport(jasperPrint, reportName);
			if (xlsOrPDf.equals("PDF")) {
				ReportUtils.printReport(jasperPrint, reportName);
			} else {
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie(
					"cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());
			

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the bankReconVO
	 */
	public BankReconVO getBankReconVO() {
		return bankReconVO;
	}

	/**
	 * @param bankReconVO the bankReconVO to set
	 */
	public void setBankReconVO(BankReconVO vo) {
		try {
			//this.bankReconVO = (BankReconVO) bankReconVO.clone();
			bankReconVO = reconBO.getBankRecon(vo, null);
			bankReconVO.setDateInFinPeriodClosed(LookupItemUtils.isDateInFinPeriodClosed(getSessionInfoBean().getCompanyVO().getId(), bankReconVO.getDtStart()));
			searchParamVO.setObj1(bankReconVO.getIdBank().toString());
			searchParamVO.setFromDate(bankReconVO.getDtStart());
			searchParamVO.setToDate(bankReconVO.getDtEnd());
			searchBankRecon(false);
			isAddReconForm = true;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleDeleteVO(BankReconVO bankReconVO) {
		this.bankReconVO = bankReconVO;
	}
	
	/**
	 * @return the cashBookBalVO
	 */
	public CashBookBalVO getCashBookBalVO() {
		return cashBookBalVO;
	}

	/**
	 * @param cashBookBalVO the cashBookBalVO to set
	 */
	public void setCashBookBalVO(CashBookBalVO cashBookBalVO) {
		this.cashBookBalVO = cashBookBalVO;
	}

	/**
	 * @return the bankAcctViewVO
	 */
	public BankAcctViewVO getBankAcctViewVO() {
		return bankAcctViewVO;
	}

	/**
	 * @param bankAcctViewVO the bankAcctViewVO to set
	 */
	public void setBankAcctViewVO(BankAcctViewVO bankAcctViewVO) {
		this.bankAcctViewVO = bankAcctViewVO;
	}

	/**
	 * @return the bankAcctList
	 */
	public List<BankAcctViewVO> getBankAcctList() {
		return bankAcctList;
	}

	/**
	 * @return the bankReconList
	 */
	public List<BankReconVO> getBankReconList() {
		return bankReconList;
	}

	/**
	 * @return the cashBookList
	 */
	public List<CashBookSumVO> getCashBookList() {
		return cashBookList;
	}

	/**
	 * @param cashBookList the cashBookList to set
	 */
	public void setCashBookList(List<CashBookSumVO> cashBookList) {
		this.cashBookList = cashBookList;
	}

	/**
	 * @return the prevCashBookList
	 */
	public List<CashBookSumVO> getPrevCashBookList() {
		return prevCashBookList;
	}

	/**
	 * @param prevCashBookList the prevCashBookList to set
	 */
	public void setPrevCashBookList(List<CashBookSumVO> prevCashBookList) {
		this.prevCashBookList = prevCashBookList;
	}

	/**
	 * @return the unselectedCBList
	 */
	public List<CashBookSumVO> getUnselectedCBList() {
		return unselectedCBList;
	}

	/**
	 * @param unselectedCBList the unselectedCBList to set
	 */
	public void setUnselectedCBList(List<CashBookSumVO> unselectedCBList) {
		this.unselectedCBList = unselectedCBList;
	}

	/**
	 * @return the isAddReconForm
	 */
	public boolean isAddReconForm() {
		return isAddReconForm;
	}

	/**
	 * @param isAddReconForm the isAddReconForm to set
	 */
	public void setAddReconForm(boolean isAddReconForm) {
		try {
			resetForm();
			initSearchParam();
			this.isAddReconForm = isAddReconForm;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
}
