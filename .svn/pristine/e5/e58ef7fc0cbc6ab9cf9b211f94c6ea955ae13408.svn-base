package com.bcs.zsg.common.web.bean;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.component.common.web.swf.bean.WebBackingBean;
import com.bcs.zsg.component.security.helper.SecurityConstant;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.vo.CompanyNameVO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.security.web.bean.BterpSessionInfoBean;

public abstract class AppBackingBean extends WebBackingBean {
	private static final long serialVersionUID = 1L;

	/* SearchParamVO instant */
	protected SearchParamVO searchParamVO;
	
	protected int runningNumber = 1;
	protected int beginNumber;
	protected List<Object> filteredObjList;
	protected Object[] selectedObjs;
	protected Object oriObj;
	protected SimpleDateFormat yearMonth = new SimpleDateFormat("yyyy-MM");
	protected String gstProccessStatus;
	
	protected String psPrefixVal;
	protected String invPrefixVal;

	public void onCopyClick() {  
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Details copied!",  null);  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	/**
	 * Get session info bean
	 * @return
	 */
	public BterpSessionInfoBean getSessionInfoBean() {
		return getSessionInfo(BterpSessionInfoBean.class);
	}
	
	/**
	 * Initialize search param
	 * @throws BusinessException
	 */
	protected void initSearchParam() throws BusinessException {
		searchParamVO = new SearchParamVO();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// set from date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		searchParamVO.setFromDate(cal.getTime());
		// set to date
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		searchParamVO.setToDate(cal.getTime());
	}
	
	public void initPrefixVal() {
		invPrefixVal = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_INVC).getPrefixid();
		psPrefixVal = LookupItemUtils.getSysNumGenVO(getSessionInfoBean().getCompanyVO().getId(), CommonConstant.SYS_NUM_CD_PAX_STMT).getPrefixid();
	}
	
	/**
	 * 
	 * @throws BusinessException
	 */
	protected void setSearchDate() throws BusinessException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(searchParamVO.getFromDate());
		// set from date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		searchParamVO.setFromDate(cal.getTime());
		// set to date
		cal.setTime(searchParamVO.getToDate());
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		// if from date is grather than to date, change to date to current month end date
		if (searchParamVO.getFromDate().after(cal.getTime())) {
			cal.setTime(searchParamVO.getFromDate());
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
		}
		searchParamVO.setToDate(cal.getTime());
	}
	
	public void validateEffectiveCompanyName(CompanyVO companyVO, Date date) {
		if (companyVO == null)
			return;
		
		companyVO.setLetterHeadUrl("../img/letter-head-header.png");
		
		if (CollectionUtils.isNotEmpty(companyVO.getCompanyNameList())) {
			for (CompanyNameVO vo : companyVO.getCompanyNameList()) {
				if (isWithinPeriod(date, vo.getValidFrom(), vo.getValidTo())) {
					companyVO.setName(vo.getName());
					companyVO.setShortName(vo.getShortName());
					
					if (StringUtils.isNotBlank(vo.getLetterHeadUrl()))
						companyVO.setLetterHeadUrl(vo.getLetterHeadUrl());
				}
			}
		}
	}
	
	private static final boolean isWithinPeriod(Date checkDate, Date periodStart, Date periodEnd) {
		return periodStart.getTime() <= checkDate.getTime() && (periodEnd != null ? periodEnd.getTime() >= checkDate.getTime() : true); 
	}
	
	/**
	 * Check whether is super user
	 * @return
	 */
	public boolean isSuperUser() {
		for (UserRoleViewVO vo : getUserInfo().getRoleList()) {
			if (StringUtils.equals(vo.getRoleCode(), SecurityConstant.ROLE_CD_SUPER_USER)) return true;
		}
		return false;
	}
	
	/**
	 * Integer decimal format
	 * @param pattern
	 * @param number
	 * @return
	 */
	public String getIntegerDecimalFormat(String pattern, int number) {
		return new DecimalFormat(pattern).format(number);
	}
	
	/**
	 * Double decimal format
	 * @param pattern
	 * @param number
	 * @return
	 */
	public String getDoubleDecimalFormat(String pattern, double number) {
		return new DecimalFormat(pattern).format(number);
	}
	
	/**
	 * Long decimal format
	 * @param pattern
	 * @param number
	 * @return
	 */
	public String getLongDecimalFormat(String pattern, long number) {
		return new DecimalFormat(pattern).format(number);
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public String getStringID(long number) {
		String pattern = "000000";
		String no = String.valueOf(number);
		if (no.length() == pattern.length()) return no;
		else return pattern.substring(0, (pattern.length() - no.length())) + no;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public Integer castStrToInt(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String urlEncoder(String s) throws Exception {
		return URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public String urlDecoder(String s) throws Exception {
		return URLDecoder.decode(s, StandardCharsets.UTF_8.toString());
	}
	
	/**
	 * 
	 * @param redirectUrl
	 * @throws Exception
	 */
	public void sendRedirect(String redirectUrl) throws Exception {
		sendRedirect(null, null, null, redirectUrl);
	}
	
	/**
	 * 
	 * @param attr
	 * @param attrValue
	 * @param vo
	 * @param redirectUrl
	 * @throws Exception
	 */
	public void sendRedirect(String attr, Object attrValue, BaseVO vo, String redirectUrl) throws Exception {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		
		HttpServletRequest req = (HttpServletRequest) ec.getRequest();
		if (StringUtils.isNotEmpty(attr)) req.getSession().setAttribute(attr, attrValue);
		if (vo != null) getSessionInfoBean().getCacheMap().put(req.getSession().getId() + "@" + ((attrValue != null) ? attrValue : ""), vo);
		
		HttpServletResponse res = (HttpServletResponse) ec.getResponse();
		res.sendRedirect(ec.getRequestContextPath() + redirectUrl);
	}
	
	/**
	 * 
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public BaseVO getVOFromSession(String attr) throws Exception {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest req = (HttpServletRequest) ec.getRequest();
		BaseVO vo = (BaseVO) getSessionInfoBean().getCacheMap().get(req.getSession().getId() + "@" + req.getSession().getAttribute(attr));
		
		// clear data from cache map
		getSessionInfoBean().getCacheMap().remove(req.getSession().getId() + "@" + req.getSession().getAttribute(attr));
		
		return vo;
	}
	
	/**
	 * 
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	public String getParamValue(String param) throws Exception {
		return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(param);
	}
	
	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public String dateFormat(Date date, String pattern) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * Reset filtered object list
	 */
	public void resetFilteredObjList() {
		filteredObjList = null;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public long getStringToLong(String code) {
		return Long.parseLong(code);
	}
	
	/**
	 * 
	 * @throws BusinessException
	 */
	public void checkFinGSTClosed(String action, Date oriDate, Date changedDate, boolean isAcctMgr) throws BusinessException {
		if (LookupItemUtils.getFinAndGSTPeriodClosedStatus(this.getSessionInfoBean().getCompanyVO().getId(), changedDate, isAcctMgr)) {
			if (CommonConstant.ACTION_CD_ADD.equals(action)) throw new BusinessException(CommonErrConstant.ERR_GST_FINANCIAL_CLOSED);
			else if (CommonConstant.ACTION_CD_UPD.equals(action)) {
				if (!yearMonth.format(changedDate).equals(yearMonth.format(oriDate))) throw new BusinessException(CommonErrConstant.ERR_GST_FINANCIAL_CLOSED);
			}
		}
	}
	
	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the searchParamVO
	 */
	public SearchParamVO getSearchParamVO() {
		return searchParamVO;
	}

	/**
	 * @param searchParamVO the searchParamVO to set
	 */
	public void setSearchParamVO(SearchParamVO searchParamVO) {
		this.searchParamVO = searchParamVO;
	}

	/**
	 * @return the runningNumber
	 */
	public int getRunningNumber() {
		return runningNumber++;
	}

	/**
	 * @param runningNumber the runningNumber to set
	 */
	public void setRunningNumber(int runningNumber) {
		this.runningNumber = runningNumber;
	}

	/**
	 * @return the beginNumber
	 */
	public int getBeginNumber() {
		runningNumber = 1;
		return beginNumber;
	}

	/**
	 * @param beginNumber the beginNumber to set
	 */
	public void setBeginNumber(int beginNumber) {
		this.beginNumber = beginNumber;
	}

	/**
	 * @return the filteredObjList
	 */
	public List<Object> getFilteredObjList() {
		return filteredObjList;
	}

	/**
	 * @param filteredObjList the filteredObjList to set
	 */
	public void setFilteredObjList(List<Object> filteredObjList) {
		this.filteredObjList = filteredObjList;
	}

	/**
	 * @return the selectedObjs
	 */
	public Object[] getSelectedObjs() {
		return selectedObjs;
	}

	/**
	 * @param selectedObjs the selectedObjs to set
	 */
	public void setSelectedObjs(Object[] selectedObjs) {
		this.selectedObjs = selectedObjs;
	}

	/**
	 * @return the oriObj
	 */
	public Object getOriObj() {
		return oriObj;
	}

	/**
	 * @param oriObj the oriObj to set
	 */
	public void setOriObj(Object oriObj) {
		this.oriObj = oriObj;
	}

	/**
	 * @return the gstProccessStatus
	 */
	public String getGstProccessStatus() {
		return gstProccessStatus;
	}

	/**
	 * @param gstProccessStatus the gstProccessStatus to set
	 */
	public void setGstProccessStatus(String gstProccessStatus) {
		this.gstProccessStatus = gstProccessStatus;
	}

	public String getPsPrefixVal() {
		return psPrefixVal;
	}

	public void setPsPrefixVal(String psPrefixVal) {
		this.psPrefixVal = psPrefixVal;
	}

	public String getInvPrefixVal() {
		return invPrefixVal;
	}

	public void setInvPrefixVal(String invPrefixVal) {
		this.invPrefixVal = invPrefixVal;
	}
	
	public SelectItem[] getBooleanFilterOptions() {
	    return new SelectItem[] {
	    	new SelectItem(null, "All"),
	        new SelectItem(1, "Yes"),
	        new SelectItem(0, "No")
	    };
	}
}
