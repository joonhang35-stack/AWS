package com.bcs.zsg.product.web.bean;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.ChartOfAcctBO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.bo.TourCatBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCatVO;
import com.bcs.zsg.product.vo.TourCatViewVO;

public class TourCatBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient TourCatBO tourCatBO;
	@Autowired
	private transient ChartOfAcctBO chartOfAcctBO;
	@Autowired
	private transient RegionBO regionBO;
	
	private TourCatVO tourCatVO;
	
	private List<TourCatViewVO> tourCatViewList;
	private List<AcctViewVO> acctViewList;
	private List<RegionVO> regionList;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		tourCatVO = new TourCatVO();
		tourCatVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		// set default type code
		if (!ProductConstant.TYPE_ALL.equals(searchParamVO.getObj1())) tourCatVO.setTypeCd((String) searchParamVO.getObj1());
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			initSearchParam();
			searchParamVO.setObj1(ProductConstant.TYPE_ALL);
			resetForm();
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			regionList = regionBO.getRegionList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add tour category
	 */
	public void addTourCat() {
		try {
			tourCatVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourCatBO.addTourCat(tourCatVO);
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update tour category
	 */
	public void updTourCat() {
		try {
			tourCatBO.updTourCat(tourCatVO);
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete tour category
	 */
	public void delTourCat() {
		try {
			tourCatBO.delTourCat(tourCatVO);
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle type code selection
	 */
	public void handleTypeCdSelect() {
		try {
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			if (!ProductConstant.TYPE_ALL.equals(searchParamVO.getObj1())) tourCatVO.setTypeCd((String) searchParamVO.getObj1());
			else tourCatVO.setTypeCd(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleAcctSelect(SelectEvent event) {
		try {
			AcctViewVO acctViewVO = (AcctViewVO) event.getObject();
			tourCatVO.setIdAcct(acctViewVO.getId());
			if (StringUtils.isEmpty(acctViewVO.getSubCode())) tourCatVO.setAcctCd(acctViewVO.getCode());
			else tourCatVO.setAcctCd(acctViewVO.getCode() + "-" + acctViewVO.getSubCode());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void handleTourCatClicked(TourCatViewVO vo) {
		try {
			tourCatVO = new TourCatVO();
			tourCatVO.setId(vo.getId());
			//tourCatVO.setIdAcct(vo.getAcctVO().getId());
			tourCatVO.setIdRegion(vo.getIdRegion());
			tourCatVO.setTypeCd(vo.getTypeCd());
			tourCatVO.setDesc(vo.getDesc());
			tourCatVO.setRemarks(vo.getRemarks());
			tourCatVO.setCreatedDate(vo.getCreatedDate());
			tourCatVO.setCreatedBy(vo.getCreatedBy());
			tourCatVO.setStatusCode(vo.getStatusCode());
			//if (StringUtils.isEmpty(vo.getAcctVO().getSubCode())) tourCatVO.setAcctCd(vo.getAcctVO().getCode());
			//else tourCatVO.setAcctCd(vo.getAcctVO().getCode() + "-" + vo.getAcctVO().getSubCode());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Load Account List
	 */
	public void loadAcctList() {
		try {
			acctViewList = chartOfAcctBO.getAcctViewList(getSessionInfoBean().getCompanyVO().getId(), null);
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the tourCatVO
	 */
	public TourCatVO getTourCatVO() {
		return tourCatVO;
	}

	/**
	 * @param tourCatVO the tourCatVO to set
	 */
	public void setTourCatVO(TourCatVO tourCatVO) {
		this.tourCatVO = tourCatVO;
	}

	/**
	 * @return the tourCatViewList
	 */
	public List<TourCatViewVO> getTourCatViewList() {
		return tourCatViewList;
	}

	/**
	 * @param tourCatViewList the tourCatViewList to set
	 */
	public void setTourCatViewList(List<TourCatViewVO> tourCatViewList) {
		this.tourCatViewList = tourCatViewList;
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
	 * @return the regionList
	 */
	public List<RegionVO> getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(List<RegionVO> regionList) {
		this.regionList = regionList;
	}

}
