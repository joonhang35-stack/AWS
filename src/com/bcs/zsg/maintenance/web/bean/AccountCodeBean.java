package com.bcs.zsg.maintenance.web.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.FunctionUtils;
import com.bcs.zsg.common.helper.ReportUtils;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.db.bterp.vo.gst.TaxCodeVO;
import com.bcs.zsg.gst.bo.GSTBO;
import com.bcs.zsg.gst.helper.GSTType;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.maintenance.bo.AccountCodeBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.purchase.bo.BillPymtBO;
import com.bcs.zsg.purchase.vo.CountryVO;
import net.sf.jasperreports.engine.JasperPrint;

import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class AccountCodeBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	@Autowired
	protected transient AccountCodeBO accountCodeBO;
	@Autowired
	protected transient TourPackageBO tourPkgBO;
	@Autowired
	protected transient BillPymtBO billPymtBO;
	@Autowired
	protected transient RegionBO regionBO;
	@Autowired
	private transient GSTBO gstBO;


	protected TrackingLogUtils trackingLogUtils;
	//private AccountCodeVO accountCodeVO;
	private List<TourDepItemVO> tourDepItemVOList;
	private List<TourDepartureVO> tourDepVOList;
	//private TourDepItemVO tourDepItemVO;
	private TourDepartureVO tourDepVO;
	private TourDepartureVO selectedTourDepVO;
	private TourDepItemVO selectedTourDepItemVO;
	private TourPackageVO tourPkgVO;
	private TourThemeVO tourThemeVO;
	private CountryVO countryVO;

	private CompanyVO companyVO;
	//private String countryName;

	protected AcctCatVO expenditureVO;
	protected AcctCatVO assetVO;
	protected AcctCatVO liabilityVO;
	//private AcctVO acctVO;
	private List<CountryVO> countryList;
	private List<CompanyVO> companyList;
	private List<AcctVO> acctList;
	//private List<AcctVO> acctAutoCompleteList;
	//private List<AcctVO> acctViewList;
	private String tourCode;

	private String accCodeSubCode;
	protected AcctTransVO acctTransViewVO;
	protected AddUpdDelVO tourDepItemAUDVO;

	private List<TaxCodeVO> inputTaxCodeVOList;
	private List<TaxCodeVO> outputTaxCodeVOList;

	// Search Option
	private List<String> yearList;
	private List<String> selectedYearList;
	private boolean allLocked;

	@Override
	public void resetForm() {
		setTourDepItemVOList(new ArrayList<TourDepItemVO>());
		tourDepItemAUDVO = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());

		//tourDepItemVO = new TourDepItemVO();
		accCodeSubCode = "";
		companyVO = new CompanyVO();

		searchParamVO = new SearchParamVO();
		searchParamVO.setObj1("XLS");

		resetYearList();
	}

	public void resetYearList() {
		yearList = new ArrayList<>();
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		for (int i = -3; i <= 2; i++) {
			cal.setTime(new Date());
			cal.add(Calendar.YEAR, i);
			String nextYear = yearFormat.format(cal.getTime());
			yearList.add(nextYear);
		}
	}

	public void refreshList() {
		try {
		 	tourDepVOList = accountCodeBO.getTourDepListWithItem();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws BusinessException {
		try {
			trackingLogUtils = new TrackingLogUtils(this.getClass());

			resetForm();
			//acctViewList = accountCodeBO.getAccCodeSubCodeList(4);

			tourCode = "";
			companyList = accountCodeBO.getCompanyList(getSessionInfoBean().getEmployeeVO().getSecUser());

			acctTransViewVO = new AcctTransVO();
		 	acctList = new ArrayList<AcctVO>();
		 	companyVO = new CompanyVO();
			countryVO = new CountryVO();
			//countryName = "";
			refreshList();

			// GST Code
			inputTaxCodeVOList = gstBO.getTaxCodeList(GSTType.INPUT);
			outputTaxCodeVOList = gstBO.getTaxCodeList(GSTType.OUTPUT);

		} catch (Throwable t) {
			errorResult(t);
		}

	}

	public void populateAccList(Long companyId) {
		try {
			liabilityVO = billPymtBO.getLiability();
			expenditureVO = billPymtBO.getIncome();
			assetVO = billPymtBO.getIncome();
			acctList = billPymtBO.getAcctListByComp(liabilityVO.getId(), assetVO.getId(), companyId);
			System.out.println("#################acctList: " + acctList.size());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void handleSubAcctSelect(SelectEvent event) {
		try {
			refreshAccountCodeList((AcctVO) event.getObject());

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void handleGSTSelect(SelectEvent event) {
		try {
			TaxCodeVO vo = (TaxCodeVO) event.getObject();
			selectedTourDepItemVO.setTaxCode(vo.getCode());
			selectedTourDepItemVO.setTaxRate(vo.getRate());
			selectedTourDepItemVO.setTaxAmount(getUnitPrice(selectedTourDepItemVO.getAmount(), selectedTourDepItemVO.getTaxRate()));

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();
        String tempStr;
        try {
        	for (int i = 1 ; i < acctList.size() ; i++) {
        		AcctVO vo = acctList.get(i);
        		tempStr = vo.getCode() + (StringUtils.isNotEmpty(vo.getSubCode()) ? "-" + vo.getSubCode() : "");
        		if (tempStr.startsWith(query)) results.add(tempStr);
        	}

        } catch(Exception e) {
        	errorResult(e);
        }
        return results;
    }

	public void handleAccountCodeKeyUp(TourDepItemVO vo) {
		AcctVO tempAcctVO = new AcctVO();
		String strTemp;
		try {
			if(vo.getAcctCodeSubCode() != null) {
				tempAcctVO = accountCodeBO.getAccountVObyAccCodeSubCode(companyVO.getId(), vo.getAcctCodeSubCode());

				if(tempAcctVO != null) {
					strTemp= tempAcctVO.getDesc();
					if(tempAcctVO.getSubDesc() != null && !tempAcctVO.getSubDesc().equals("")) strTemp += ", " + tempAcctVO.getSubDesc();
					vo.setAcctDesc(strTemp);
				} else {
					vo.setAcctDesc("<font color='red'>No data found !</font>");
				}
				selectedTourDepItemVO = vo;
				refreshAccountCodeList(tempAcctVO);
			}
		} catch(Exception e) {
			errorResult(e);
		}
	}

	public void refreshAccountCodeList(AcctVO tempAcctVO) {
		try {
			for(int i = 0 ; i < tourDepItemVOList.size() ; i++) {
				if (selectedTourDepItemVO.getGrouping() > 0) {
					if (selectedTourDepItemVO.getGrouping() == tourDepItemVOList.get(i).getGrouping()) {
						tourDepItemVOList.get(i).setAcctId(tempAcctVO.getId());
						tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode());
						tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc());
						if (StringUtils.isNotEmpty(tempAcctVO.getSubCode())) {
							tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode() + "-" + tempAcctVO.getSubCode());
							tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc() + "-" + tempAcctVO.getSubDesc());
						}

						tourDepItemVOList.get(i).setTaxCode(tempAcctVO.getTaxCode());
						tourDepItemVOList.get(i).setTaxRate(getTaxRate(tempAcctVO.getTaxCode()));
						tourDepItemVOList.get(i).setTaxAmount(getUnitPrice(tourDepItemVOList.get(i).getAmount(), tourDepItemVOList.get(i).getTaxRate()));
					}
				} else {
					if (selectedTourDepItemVO.getCode().equals(tourDepItemVOList.get(i).getCode())) {
						tourDepItemVOList.get(i).setAcctId(tempAcctVO.getId());
						tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode());
						tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc());
						if (StringUtils.isNotEmpty(tempAcctVO.getSubCode())) {
							tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode() + "-" + tempAcctVO.getSubCode());
							tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc() + "-" + tempAcctVO.getSubDesc());
						}

						tourDepItemVOList.get(i).setTaxCode(tempAcctVO.getTaxCode());
						tourDepItemVOList.get(i).setTaxRate(getTaxRate(tempAcctVO.getTaxCode()));
						tourDepItemVOList.get(i).setTaxAmount(getUnitPrice(tourDepItemVOList.get(i).getAmount(), tourDepItemVOList.get(i).getTaxRate()));
						break;
					}
				}
//				if (selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_SGL) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_TWN) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_CTW) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_CWB) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_CNB) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_INFT) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_SGL) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_TWN) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_CTW) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_CWB) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_CNB) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_INFT) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_DISC) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.AIRLINE_ITM_CD_FUEL_ADT) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.AIRLINE_ITM_CD_FUEL_CHD) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.AIRLINE_ITM_CD_TIPPING) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.AIRLINE_ITM_CD_DEVIATION)) {
//
//					if (tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_SGL) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_TWN) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_CTW) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_CWB) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_CNB) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_FT_INFT) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_SGL) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_TWN) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_CTW) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_CWB) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_CNB) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_GA_INFT) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.TOUR_DEP_ITM_CD_DISC) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.AIRLINE_ITM_CD_FUEL_ADT) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.AIRLINE_ITM_CD_FUEL_CHD) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.AIRLINE_ITM_CD_TIPPING) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.AIRLINE_ITM_CD_DEVIATION)) {
//
//						tourDepItemVOList.get(i).setAcctId(tempAcctVO.getId());
//						tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode());
//						tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc());
//						if (StringUtils.isNotEmpty(tempAcctVO.getSubCode())) {
//							tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode() + "-" + tempAcctVO.getSubCode());
//							tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc() + "-" + tempAcctVO.getSubDesc());
//						}
//
//						tourDepItemVOList.get(i).setTaxCode(tempAcctVO.getTaxCode());
//						tourDepItemVOList.get(i).setTaxRate(getTaxRate(tempAcctVO.getTaxCode()));
//						tourDepItemVOList.get(i).setTaxAmount(getUnitPrice(tourDepItemVOList.get(i).getAmount(), tourDepItemVOList.get(i).getTaxRate()));
//					}
//				} else if (selectedTourDepItemVO.getCode().equals(ProductConstant.AIRLINE_ITM_CD_APT_ADT) ||
//						selectedTourDepItemVO.getCode().equals(ProductConstant.AIRLINE_ITM_CD_APT_CHD)) {
//
//					if (tourDepItemVOList.get(i).getCode().equals(ProductConstant.AIRLINE_ITM_CD_APT_ADT) ||
//							tourDepItemVOList.get(i).getCode().equals(ProductConstant.AIRLINE_ITM_CD_APT_CHD)) {
//						tourDepItemVOList.get(i).setAcctId(tempAcctVO.getId());
//						tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode());
//						tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc());
//						if (StringUtils.isNotEmpty(tempAcctVO.getSubCode())) {
//							tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode() + "-" + tempAcctVO.getSubCode());
//							tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc() + "-" + tempAcctVO.getSubDesc());
//						}
//
//						tourDepItemVOList.get(i).setTaxCode(tempAcctVO.getTaxCode());
//						tourDepItemVOList.get(i).setTaxRate(getTaxRate(tempAcctVO.getTaxCode()));
//						tourDepItemVOList.get(i).setTaxAmount(getUnitPrice(tourDepItemVOList.get(i).getAmount(), tourDepItemVOList.get(i).getTaxRate()));
//					}
//				} else {
//
//					if(tourDepItemVOList.get(i).getId().equals(selectedTourDepItemVO.getId())) {
//						tourDepItemVOList.get(i).setAcctId(tempAcctVO.getId());
//						tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode());
//						tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc());
//						if (StringUtils.isNotEmpty(tempAcctVO.getSubCode())) {
//							tourDepItemVOList.get(i).setAcctCodeSubCode(tempAcctVO.getCode() + "-" + tempAcctVO.getSubCode());
//							tourDepItemVOList.get(i).setAcctDesc(tempAcctVO.getDesc() + "-" + tempAcctVO.getSubDesc());
//						}
//
//						tourDepItemVOList.get(i).setTaxCode(tempAcctVO.getTaxCode());
//						tourDepItemVOList.get(i).setTaxRate(getTaxRate(tempAcctVO.getTaxCode()));
//						tourDepItemVOList.get(i).setTaxAmount(getUnitPrice(tourDepItemVOList.get(i).getAmount(), tourDepItemVOList.get(i).getTaxRate()));
//					}
//				}
			}
		} catch(Exception e) {
			errorResult(e);
		}
	}

	public boolean validateAccountCode() {
		int i;
		boolean foundError = false;
		String strTemp = "";
		AcctVO acctVOTemp;
		try {
			for(i = 0 ; i < tourDepItemVOList.size() ; i++) {
				strTemp = tourDepItemVOList.get(i).getAcctCodeSubCode();
				if(strTemp != null && !strTemp.equals("")) {
					acctVOTemp = accountCodeBO.getAccountVObyAccCodeSubCode(getSessionInfoBean().getCompanyVO().getId(), strTemp);
					if(acctVOTemp == null) foundError= true;
				}
			}
		} catch(Exception e) {}

		if (foundError) {
			FacesMessage msg  = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid account code provided", "");
			if (msg != null) FacesContext.getCurrentInstance().addMessage(null, msg);
	        RequestContext.getCurrentInstance().addCallbackParam("validateAccountCodeError", true);
	        return false;
		}
        return true;
	}

	public void saveAccountCode() {
		try {
			int i;

			if(validateAccountCode()) {
				tourDepItemAUDVO = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
				for(i=0;i<tourDepItemVOList.size();i++) {
					tourDepItemAUDVO.getUpdList().add(tourDepItemVOList.get(i));
				}
				accountCodeBO.updAccountCode(tourDepItemAUDVO);

				tourDepItemVOList = tourPkgBO.getTourDepItemList(tourDepVO.getId());

				Long longTemp;
				boolean foundEmpty = false;
				for(i = 0 ; i < tourDepItemVOList.size() ; i++) {
					longTemp = null;
					longTemp = tourDepItemVOList.get(i).getAcctId();

					if(longTemp == null) {
						foundEmpty =true;
						break;
					}
				}
				if(StringUtils.isNotEmpty(tourDepVO.getIdDepartment())) {
					TourDepartureVO tourDepartureVO = new TourDepartureVO();
					tourDepartureVO = tourPkgBO.getTourDepById(tourDepVO.getId());
					tourDepartureVO.setIdDepartment(tourDepVO.getIdDepartment());
					tourPkgBO.updateVO((TourDepartureVO)tourDepartureVO);
				}
				if(foundEmpty == false) {
					TourDepartureVO tempDepVO = new TourDepartureVO();
					tempDepVO = tourPkgBO.getTourDepById(tourDepVO.getId());
					tourPkgBO.updateVO((TourDepartureVO)tempDepVO);
				}
				refreshList();
				if (filteredObjList != null && !filteredObjList.isEmpty()) {
					List<TourDepartureVO> list = new ArrayList<TourDepartureVO>();
					for (Object obj : filteredObjList) {
						if (obj instanceof TourDepartureVO) {
							TourDepartureVO fvo = (TourDepartureVO) obj;
							if (tourDepVOList != null) {
								for (TourDepartureVO rvo : tourDepVOList) {
									if (rvo.getId() != null && rvo.getId().equals(fvo.getId())) {
										fvo = rvo;
										break;
									}
								}
							}
							list.add(fvo);
						}
					}
					if (!list.isEmpty()) {
						tourDepVOList = list;
					}
				}
				resetFilteredObjList();
			 	successResult();
			}
		} catch(Exception e) {
			errorResult(e);
		}
	}

	public void handleCompanySelect() {
		handleAccountDepSelect( selectedTourDepVO, companyVO.getId().toString());
	}

	public void handleAccountDepSelect(TourDepartureVO vo) {
		handleAccountDepSelect(vo, "");
	}
	/**
	 * Handle Exchange Order selection
	 */
	public void handleAccountDepSelect(TourDepartureVO vo, String companyId) {
		try {
//			resetFilteredObjList();
			selectedTourDepVO = vo;
			tourDepVO = new TourDepartureVO();
			tourPkgVO = new TourPackageVO();
			tourThemeVO = new TourThemeVO();
			countryVO = new CountryVO();
			countryVO.setCountry("");

			tourDepVO = vo;
			tourPkgVO = accountCodeBO.getTourPkgById(vo.getIdTourPkg());
			tourThemeVO= accountCodeBO.getTourThemeById(tourPkgVO.getIdTourTheme());

			if(tourThemeVO.getIdCountry() != null && !tourThemeVO.getIdCountry().equals("") && tourThemeVO.getIdCountry() != 0) {
				countryVO = regionBO.getCountryById(tourThemeVO.getIdCountry());
				//countryName = countryVO.getCountry();
			}

			AcctVO acctVO = null;
			companyList = tourPkgBO.getTourDepItemCompanyList(tourDepVO.getId());

			Long tempLong;
			//To get the first data of company list
			if(companyList.size() > 0) {
				if(companyId.equals("")) {
					tempLong = companyList.get(0).getId();
					companyVO.setId(tempLong);
				} else tempLong = Long.parseLong(companyId);

				//If company is found, do the actions below
				tourDepItemVOList = tourPkgBO.getTourDepItemList(tourDepVO.getId(), tempLong);

				populateAccList(tempLong);
				//tourDepItemVO = new TourDepItemVO();

				List<AccountCodeConfigVO> accountCodeConfigVOList = accountCodeBO.getAccountCodeConfigList();

				// Default The account if not set before
				for (TourDepItemVO itemVO : tourDepItemVOList) {
					for (AccountCodeConfigVO configVO : accountCodeConfigVOList) {
						if (StringUtils.isBlank(configVO.getCode())) {
							if (StringUtils.contains(StringUtils.upperCase(itemVO.getDesc()), "DEPARTURE LEVY") &&
									StringUtils.contains(StringUtils.upperCase(configVO.getDescription()), "DEPARTURE LEVY")) {
								itemVO.setGrouping(configVO.getGrouping());

								if (itemVO.getAcctId() == null)
									itemVO.setAcctId(configVO.getIdAcct());

								if (itemVO.getIsLock() == null)
									itemVO.setIsLock(configVO.getIsLock());

								break;

							} else if (StringUtils.contains(StringUtils.upperCase(itemVO.getDesc()), "FAMILY") &&
									StringUtils.contains(StringUtils.upperCase(configVO.getDescription()), "FAMILY")) {
								itemVO.setGrouping(configVO.getGrouping());

								if (itemVO.getAcctId() == null)
									itemVO.setAcctId(configVO.getIdAcct());

								if (itemVO.getIsLock() == null)
									itemVO.setIsLock(configVO.getIsLock());

								break;

							} else if (itemVO.getDesc().equalsIgnoreCase(configVO.getDescription())) {
								itemVO.setGrouping(configVO.getGrouping());

								if (itemVO.getAcctId() == null)
									itemVO.setAcctId(configVO.getIdAcct());

								if (itemVO.getIsLock() == null)
									itemVO.setIsLock(configVO.getIsLock());

								break;
							}
						} else {
							if (StringUtils.equals(itemVO.getCode(), configVO.getCode())) {
								itemVO.setGrouping(configVO.getGrouping());

								if (itemVO.getAcctId() == null)
									itemVO.setAcctId(configVO.getIdAcct());

								if (itemVO.getIsLock() == null)
									itemVO.setIsLock(configVO.getIsLock());

								break;
							}
						}
					}
				}

				Collections.sort(tourDepItemVOList, new Comparator<TourDepItemVO>() {
					@Override
					public int compare(TourDepItemVO u1, TourDepItemVO u2) {
						int count = 0;

						if (u1.getGrouping() > u2.getGrouping())
							count = 1;
						else if (u1.getGrouping() < u2.getGrouping())
							count = -1;

						return count;
					}
				});

				for(int i = 0; i < tourDepItemVOList.size(); i++) {
				    tempLong = tourDepItemVOList.get(i).getAcctId();
				    if (tempLong != null) {
				    	acctVO = accountCodeBO.getAccountVO(tempLong);

				    	tourDepItemVOList.get(i).setAcctCodeSubCode(acctVO.getCode());
				    	tourDepItemVOList.get(i).setAcctDesc(acctVO.getDesc());
				    	if (StringUtils.isNotEmpty(acctVO.getSubCode())) {
				    		tourDepItemVOList.get(i).setAcctCodeSubCode(tourDepItemVOList.get(i).getAcctCodeSubCode() + "-" + acctVO.getSubCode());
				    		tourDepItemVOList.get(i).setAcctDesc(tourDepItemVOList.get(i).getAcctDesc() + "-" + acctVO.getSubDesc());
				    	}

				    	//tourDepItemVOList.get(i).setTaxCode(acctVO.getTaxCode());
						//tourDepItemVOList.get(i).setTaxRate(getTaxRate(acctVO.getTaxCode()));

				    	if (StringUtils.isNotEmpty(tourDepItemVOList.get(i).getTaxCode())) {
							//BigDecimal unitPrice = new BigDecimal(tourDepItemVOList.get(i).getAmount().toString());
							//BigDecimal taxRate = new BigDecimal(String.valueOf(tourDepItemVOList.get(i).getTaxRate())).divide(new BigDecimal("100"));
							//tourDepItemVOList.get(i).setTaxAmount(unitPrice.multiply(taxRate).add(unitPrice).doubleValue());
				    		tourDepItemVOList.get(i).setTaxAmount(getUnitPrice(tourDepItemVOList.get(i).getAmount(), tourDepItemVOList.get(i).getTaxRate()));
				    	}
				    }
		     	}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void selectTourDepItem(TourDepItemVO vo){
		selectedTourDepItemVO = vo;
	}

	public boolean isAllLocked() {
		if (tourDepItemVOList == null || tourDepItemVOList.isEmpty()) {
			return false;
		}
		for (TourDepItemVO vo : tourDepItemVOList) {
			if (vo.getIsLock() == null || !vo.getIsLock()) {
				return false;
			}
		}
		return true;
	}

	public void setAllLocked(boolean allLocked) {
		this.allLocked = allLocked;
	}

	public void toggleAllLocks() {
		if (tourDepItemVOList != null) {
			for (TourDepItemVO vo : tourDepItemVOList) {
				vo.setIsLock(allLocked);
			}
		}
	}

	public void AccountCodeItemSelectAcc(TourDepItemVO vo) {
		try {
			//Add codes here to set th tourDepVO.AcctVO = selection
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void printAccountCodeList() {
		try {
			trackingLogUtils.startLogs();

			String xlsOrCsv = (String) searchParamVO.getObj1();

			if (selectedYearList != null) searchParamVO.setObj3(selectedYearList);

			HashMap<String, Object> map = new HashMap<String, Object>();

			List<TourDepartureVO> list = (List<TourDepartureVO>) accountCodeBO.getTourDepListAccountCode(searchParamVO);
			map.put("tableList", list);

			if (CollectionUtils.isEmpty(list)) throw new BusinessException(CommonErrConstant.ERR_NO_RESULT);

			String jasperFileName = CommonConstant.JAS_RPT_TOUR_DEP_ACCT_CODE;
			String reportName = CommonConstant.PDF_RPT_TOUR_DEP_ACCT_CODE;

			if (xlsOrCsv.equals("CSV")) {
				List<Map<String, String>> customerMap = new ArrayList<>();
				customerMap = getTourDepAcctCodeMap(list);
				ReportUtils.printReportCSV(customerMap, reportName);
			} else {
				JasperPrint jasperPrint = ReportUtils.getJasperPrint(list, map, jasperFileName);
				ReportUtils.printReportExcel(jasperPrint, reportName);
			}

			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("cookie.pdf.exporting", "true", Collections.<String, Object>emptyMap());

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("printAccountCodeList");
		}
	}

	public List<Map<String, String>> getTourDepAcctCodeMap(List<TourDepartureVO> depMap) {
		List<Map<String, String>> depList = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

		// CSV Title
		map.put("A", "Tour Code");
		map.put("B", "Dep Date");
		map.put("C", "Tour Category");
		map.put("D", "Country");
		map.put("E", "Package Name");
		map.put("F", "Package Name (Chinese)");
		map.put("G", "Tour Account");
		map.put("H", "Insurance Account");
		map.put("I", "Visa Account");
		map.put("J", "Status");
		map.put("K", "Tour Status");
		depList.add(map);

		for (TourDepartureVO vo : depMap) {
			map = new HashMap<>();
			map.put("A", FunctionUtils.escapeSpecialCharacters(vo.getCode()));
			map.put("B", FunctionUtils.escapeSpecialCharacters(sdf.format(vo.getDtDep())));
			map.put("C", FunctionUtils.escapeSpecialCharacters(vo.getTourCategoryName()));
			map.put("D", FunctionUtils.escapeSpecialCharacters(vo.getCountryName()));
			map.put("E", FunctionUtils.escapeSpecialCharacters(vo.getNameEn()));
			map.put("F", FunctionUtils.escapeSpecialCharacters(vo.getNameZh()));
			map.put("G", FunctionUtils.escapeSpecialCharacters(vo.getTourAcc()));
			map.put("H", FunctionUtils.escapeSpecialCharacters(vo.getInsurAcc()));
			map.put("I", FunctionUtils.escapeSpecialCharacters(vo.getVisaAcc()));
			map.put("J", FunctionUtils.escapeSpecialCharacters(vo.getAccCodeStatusCd()));
			map.put("K", FunctionUtils.escapeSpecialCharacters(vo.getTourStatusDesc()));
			depList.add(map);
		}
		return depList;
	}

	/**********
	 * HELPER *
	 **********/

	private double getUnitPrice(Double amount, Float taxRate) {
		BigDecimal amountIncludeTax = new BigDecimal(amount.toString());

		if (taxRate != null && taxRate != 0.00) {
			BigDecimal bTaxRate = new BigDecimal(String.valueOf(taxRate));
			bTaxRate = bTaxRate.setScale(2, BigDecimal.ROUND_HALF_UP);

			//FORMULA: 106 [AMOUNT] * 6 [TAXRATE] / 106
			BigDecimal bAmount = amountIncludeTax.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(bTaxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
			bTaxRate = bTaxRate.add(new BigDecimal("100"));
			BigDecimal bTaxAmount = bAmount.divide(bTaxRate, 2, RoundingMode.HALF_UP);
			bAmount = amountIncludeTax.subtract(bTaxAmount);
			return bAmount.doubleValue();
		}
		return amountIncludeTax.doubleValue();
	}

	private float getTaxRate(String taxCode) {
		for (TaxCodeVO vo : outputTaxCodeVOList) {
			if (vo.getCode().equals(taxCode)) return vo.getRate();
		}
		return 0;
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setSelectedTourDepItemVO(TourDepItemVO selectedTourDepItemVO) {
		this.selectedTourDepItemVO = selectedTourDepItemVO;
	}

	public TourDepItemVO getSelectedTourDepItemVO() {
		return selectedTourDepItemVO;
	}

	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	public void setAccCodeSubCode(String accCodeSubCode) {
		this.accCodeSubCode = accCodeSubCode;
	}

	public String getAccCodeSubCode() {
		return accCodeSubCode;
	}

	public void resetGLAcctForm() {
		acctTransViewVO = new AcctTransVO();
	}

	public AcctTransVO getAcctTransViewVO() {
		return acctTransViewVO;
	}

	public void setAcctTransViewVO(AcctTransVO acctTransViewVO) {
		this.acctTransViewVO = acctTransViewVO;
	}

	public void setTourDepItemVOList(List<TourDepItemVO> tourDepItemVOList) {
		this.tourDepItemVOList = tourDepItemVOList;
	}
	public List<TourDepItemVO> getTourDepItemVOList() {
		return tourDepItemVOList;
	}

	public void setCountryList(List<CountryVO> countryVOList) {
		this.countryList = countryVOList;
	}
	public List<CountryVO> getCountryList() {
		return countryList;
	}


	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}
	public CountryVO getCountryVO() {
		return countryVO;
	}

	public List<AcctVO> getAcctList() {
		return acctList;
	}

	public void setAcctList(List<AcctVO> acctList) {
		this.acctList = acctList;
	}

	public void setCompanyList(List<CompanyVO> companyList) {
		this.companyList = companyList;
	}
	public List<CompanyVO> getCompanyList() {
		return companyList;
	}

	public void settourDepVOList(List<TourDepartureVO> tourDepVOList) {
		this.tourDepVOList = tourDepVOList;
	}
	public List<TourDepartureVO> gettourDepVOList() {
		return tourDepVOList;
	}

	public void setTourDepVO(TourDepartureVO tourDepVO) {
		this.tourDepVO = tourDepVO;
	}
	public TourDepartureVO getTourDepVO() {
		return tourDepVO;
	}


	public void setTourPkgVO(TourPackageVO tourPkgVO) {
		this.tourPkgVO = tourPkgVO;
	}
	public TourPackageVO getTourPkgVO() {
		return tourPkgVO;
	}

	public void setTourThemeVO(TourThemeVO tourThemeVO) {
		this.tourThemeVO = tourThemeVO;
	}
	public TourThemeVO getTourThemeVO() {
		return tourThemeVO;
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

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public List<String> getSelectedYearList() {
		return selectedYearList;
	}

	public void setSelectedYearList(List<String> selectedYearList) {
		this.selectedYearList = selectedYearList;
	}

}
