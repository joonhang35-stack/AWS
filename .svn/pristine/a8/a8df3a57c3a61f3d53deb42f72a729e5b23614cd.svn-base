package com.bcs.zsg.product.web.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.bo.CruiseBO;
import com.bcs.zsg.product.bo.InvoiceAndExchangeOrderBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.CruiseScheduleChargeVO;
import com.bcs.zsg.product.vo.CruiseScheduleItemVO;
import com.bcs.zsg.product.vo.CruiseScheduleVO;
import com.bcs.zsg.product.vo.CruiseVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;

public class CruiseBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient CruiseBO cruiseBO;
	@Autowired
	protected transient InvoiceAndExchangeOrderBO invEoBO;

	private CruiseVO cruiseVO;
	private CruiseScheduleVO cruiseScheduleVO;
	private CruiseScheduleItemVO cruiseScheduleItemVO;
	private CruiseScheduleChargeVO extraItemChargeVO;

	private List<CruiseVO> cruiseList;
	private List<CruiseScheduleVO> cruiseScheduleList;
	private List<CruiseScheduleChargeVO> addItemChargeList;
	private List<CruiseScheduleChargeVO> updItemChargeList;
	private List<CruiseScheduleChargeVO> delItemChargeList;
	private List<InvoiceAndExchangeOrderVO> invEoItemList;

	private String scheduleSearch;
	private String typeCode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		scheduleSearch = "";
		cruiseVO = new CruiseVO();
		cruiseVO.setTypeCode(typeCode);
		resetScheduleForm();
		// set list
		// cruiseScheduleList = new ArrayList<CruiseScheduleVO>();
	}

	/**
	 * Reset cruise schedule form
	 */
	public void resetScheduleForm() {
		cruiseScheduleVO = new CruiseScheduleVO();
		if (ProductConstant.TOUR_DEP_ITM_TYPE_CRUISE.equals(cruiseVO.getTypeCode())) {
			cruiseScheduleVO.setTktValidity(0);
		}
		resetItemForm();
	}

	/**
	 * Reset cruise schedule item form
	 */
	public void resetItemForm() {
		cruiseScheduleItemVO = new CruiseScheduleItemVO();
		// set list
		addItemChargeList = new ArrayList<CruiseScheduleChargeVO>();
		updItemChargeList = new ArrayList<CruiseScheduleChargeVO>();
		delItemChargeList = new ArrayList<CruiseScheduleChargeVO>();
		resetItemChargeForm();
	}

	/**
	 * Reset cruise schedule item charge form
	 */
	public void resetItemChargeForm() {
		extraItemChargeVO = new CruiseScheduleChargeVO();
	}

	/**
	 * Initialization
	 */
	public void init() {
		try {
			resetForm();
			cruiseList = cruiseBO.getCruiseList();
			
			InvoiceAndExchangeOrderVO searchFilter = new InvoiceAndExchangeOrderVO();
			searchFilter.setItemTypeList(Arrays.asList(ProductConstant.INV_ITM_TYPE_CRUISE, ProductConstant.INV_ITM_CAT_AIRLINE));
			searchFilter.setFilterAcctMgrUse(false);
			searchFilter.setIsShow(true);
			invEoItemList = invEoBO.getInvoiceAndExchangeOrderList(getSessionInfoBean().getCompanyVO().getId(), searchFilter, true, true);

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void init(String typeCode) {
		this.typeCode = typeCode;

		try {
			resetForm();
			cruiseList = cruiseBO.getCruiseListByTypeCode(typeCode);
			
			InvoiceAndExchangeOrderVO searchFilter = new InvoiceAndExchangeOrderVO();
			searchFilter.setItemTypeList(Arrays.asList(ProductConstant.INV_ITM_TYPE_CRUISE, ProductConstant.INV_ITM_CAT_AIRLINE));
			searchFilter.setFilterAcctMgrUse(false);
			searchFilter.setIsShow(true);
			invEoItemList = invEoBO.getInvoiceAndExchangeOrderList(getSessionInfoBean().getCompanyVO().getId(), searchFilter, true, true);

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	/**
	 * Add cruise
	 */
	public void addCruise() {
		try {
			cruiseBO.addCruise(cruiseVO);
			init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update cruise
	 */
	public void updCruise() {
		try {
			cruiseBO.updCruise(cruiseVO);
			init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Delete cruise
	 */
	public void delCruise() {
		try {
			cruiseBO.delCruise(cruiseVO);
			init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Add cruise schedule
	 */
	public void addCruiseSchedule() {
		try {
			// add cruise schedule
			cruiseScheduleVO.setIdCruise(cruiseVO.getId());

//			// add item charge to list
//			addNormalItemChargeToList();
			
			cruiseScheduleVO.setAmtTotalCharge(0.0);
			if (cruiseScheduleVO.isSumAmtApt())		cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtAptAdt());
			if (cruiseScheduleVO.isSumAmtApt())		cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtAptChd());
			if (cruiseScheduleVO.isSumAmtFuel())		cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtFuelAdt());
			if (cruiseScheduleVO.isSumAmtFuel())		cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtFuelChd());
			if (cruiseScheduleVO.isSumAmtTrvlIns())	cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtTrvlIns());
			if (cruiseScheduleVO.isSumAmtVisa())		cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtVisa());
			if (cruiseScheduleVO.isSumAmtAC())			cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtAC());
			if (cruiseScheduleVO.isSumAmtTipping())	cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtTipping());
			if (cruiseScheduleVO.isSumAmtDeviation())	cruiseScheduleVO.addAmtTotalCharge(cruiseScheduleVO.getAmtDeviation());
//			if (cruiseScheduleVO.getExtraItemChargeList() != null) {
//				for(CruiseScheduleChargeVO vo : cruiseScheduleVO.getExtraItemChargeList()) {
//					if (vo.getIsSumIntoTotal()) {
//						cruiseScheduleVO.addAmtTotalCharge(vo.getAmount());
//					}
//				} 
//			}
			
			cruiseBO.addCruiseSchedule(cruiseScheduleVO);

			// get schedule list
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());
//			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	/**
	 * Delete cruise schedule
	 */
	public void delCruiseSchedule() {
		try {
			cruiseBO.delCruiseSchedule(cruiseScheduleVO);
			// get schedule list
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());
			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			resetScheduleForm();
			errorResult(t);
		}
	}

	/**
	 * Delete cruise schedule Item
	 */
	public void delCruiseScheduleItem() {
		try {
			cruiseBO.delCruiseScheduleItem(cruiseScheduleItemVO);
			// get schedule list
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());
			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			resetScheduleForm();
			errorResult(t);
		}
	}

	/**
	 * Add cruise schedule item
	 */
	public void addCruiseScheduleItem() {
		try {
			cruiseScheduleItemVO.setIdCruiseSchedule(cruiseScheduleVO.getId());

			// add cruise schedule item
			cruiseBO.addCruiseScheduleItem(cruiseScheduleItemVO);
			// get schedule list
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());
			resetItemForm();
//			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update cruise schedule item
	 */
	public void updCruiseScheduleItem() {
		try {
			// update cruise schedule item charge
			// cruiseBO.updCruiseScheduleCharge(cruiseScheduleVO, addItemChargeList,
			// updItemChargeList, delItemChargeList);
			// update cruise schedule item
			cruiseBO.updCruiseScheduleItem(cruiseScheduleItemVO);
			// get schedule list
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());
			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update cruise schedule item
	 */
	public void updCruiseSchedule() {
		try {

			// update cruise schedule item charge
			cruiseBO.updCruiseScheduleCharge(cruiseScheduleVO, addItemChargeList, updItemChargeList,
					delItemChargeList);

//			// add item charge to list
//			addNormalItemChargeToList();

			// update cruise schedule item
			cruiseBO.updCruiseSchedule(cruiseScheduleVO);

			// get schedule list
//			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());
			resetScheduleForm();
			// resetItemForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Add item chareges
	 * 
	 * @param flag
	 */
	public void addExtraItemCharge(int flag) {

		try {
			if (flag == 0) {
				if (cruiseScheduleVO.getExtraItemChargeList() == null)
					cruiseScheduleVO.setExtraItemChargeList(new ArrayList<CruiseScheduleChargeVO>());
				cruiseScheduleVO.getExtraItemChargeList().add(extraItemChargeVO);
				resetItemChargeForm();
				successResult();

			} else {
				extraItemChargeVO.setIdCruiseSchedule(cruiseScheduleVO.getId());
				addItemChargeList.add(extraItemChargeVO);
				addExtraItemCharge(0);
			}

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update item charge
	 * 
	 * @param flag
	 */
	public void updExtraItemCharge(int flag) {
		try {
			if (flag == 0) {

			} else {
				updItemChargeList.add(extraItemChargeVO);
			}
			resetItemChargeForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Delete item charge
	 * 
	 * @param vo
	 * @param flag
	 */
	public void delExtraItemCharge(CruiseScheduleChargeVO vo, int flag) {
		try {
			if (flag == 0) {
				for (int i = cruiseScheduleVO.getExtraItemChargeList().size() - 1; i >= 0; i--) {
					if (cruiseScheduleVO.getExtraItemChargeList().get(i).getTypeDesc()
							.equalsIgnoreCase(vo.getTypeDesc())) {
						cruiseScheduleVO.getExtraItemChargeList().remove(i);
						break;
					}
				}
			} else {
				delItemChargeList.add(vo);
				delExtraItemCharge(vo, 0);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleInvEoItemSelect() {
		try {
			String[] codes = extraItemChargeVO.getTypeCd().split("-");
			for (InvoiceAndExchangeOrderVO vo : invEoItemList) {
				if (codes[0].trim().equals(vo.getCode())) {
					extraItemChargeVO.setTypeCd(codes[0].trim());
					extraItemChargeVO.setTypeDesc(vo.getDescription());
					break;
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	/**
	 * Cruise schedule
	 */
	public void handleCruiseSchedule(CruiseVO vo) {
		try {
			scheduleSearch = "";
			cruiseVO = vo;
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(vo.getId());

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * 
	 * @param vo
	 */
	public void populateScheduleChargeList(CruiseScheduleVO vo) {
		cruiseScheduleVO = vo;
		try {

//			this.setCruiseScheduleVO(cruiseScheduleVO);
			// set fees and charges
			List<CruiseScheduleChargeVO> chargeList = cruiseBO.getCruiseScheduleChargeList(vo.getId());
			if (CollectionUtils.isNotEmpty(chargeList)) {
				// if (CollectionUtils.isNotEmpty(normalChargeList))
				// cruiseScheduleItemVO.setNormalItemChargeList(normalChargeList);
				if (CollectionUtils.isNotEmpty(chargeList))
					cruiseScheduleVO.setExtraItemChargeList(chargeList);
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void handleCruiseScheduleItemSelect(CruiseScheduleItemVO vo) {
		cruiseScheduleItemVO = vo;
	}

	public void resetArlineSchedule() {
		try {
			cruiseScheduleList = cruiseBO.getCruiseScheduleList(cruiseVO.getId());

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		cruiseScheduleVO = new CruiseScheduleVO();
	}

	public void searchSchedule() {
		try {
			FacesMessage msg = null;
			cruiseScheduleList = cruiseBO.getCruiseScheduleListByDesc(cruiseVO.getId(), scheduleSearch);
			scheduleSearch = "";
			if (CollectionUtils.isEmpty(cruiseScheduleList)) {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please key in a valid schedule.",
						"Please key in a valid schedule.");
			}
			if (msg != null)
				FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**********
	 * HELPER *
	 **********/

//	/**
//	 * 
//	 */
//	private void addNormalItemChargeToList() {
//		if (cruiseScheduleVO.getNormalItemChargeList() == null) cruiseScheduleVO.setNormalItemChargeList(new ArrayList<CruiseScheduleChargeVO>());
//		
//		// add apt adt
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_APT_ADT);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_APT_ADT);
//		if (normalItemChargeVO.getAmtAptAdt() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtAptAdt());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add apt chd
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_APT_CHD);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_APT_CHD);
//		if (normalItemChargeVO.getAmtAptChd() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtAptChd());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add fuel adt
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_FUEL_ADT);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_FUEL_ADT);
//		if (normalItemChargeVO.getAmtFuelAdt() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtFuelAdt());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add fuel chd
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_FUEL_CHD);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_FUEL_CHD);
//		if (normalItemChargeVO.getAmtFuelChd() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtFuelChd());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add trvl ins
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_TRVL_INS);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_TRVL_INS);
//		if (normalItemChargeVO.getAmtTrvlIns() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtTrvlIns());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add visa
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_VISA);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_VISA);
//		if (normalItemChargeVO.getAmtVisa() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtVisa());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add a/c
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_AC);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_AC);
//		if (normalItemChargeVO.getAmtAC() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtAC());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add tipping
//		extraItemChargeVO = new CruiseScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_TIPPING);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_TIPPING);
//		if (normalItemChargeVO.getAmtTipping() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtTipping());
//		cruiseScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the cruiseVO
	 */
	public CruiseVO getCruiseVO() {
		return cruiseVO;
	}

	/**
	 * @param cruiseVO the cruiseVO to set
	 */
	public void setCruiseVO(CruiseVO cruiseVO) {
		this.cruiseVO = cruiseVO;
	}

	/**
	 * @return the cruiseScheduleVO
	 */
	public CruiseScheduleVO getCruiseScheduleVO() {
		return cruiseScheduleVO;
	}

	/**
	 * @param cruiseScheduleVO the cruiseScheduleVO to set
	 */
	public void setCruiseScheduleVO(CruiseScheduleVO cruiseScheduleVO) {
		this.cruiseScheduleVO = cruiseScheduleVO;
	}

	/**
	 * @return the cruiseScheduleItemVO
	 */
	public CruiseScheduleItemVO getCruiseScheduleItemVO() {
		return cruiseScheduleItemVO;
	}

	/**
	 * @param cruiseScheduleItemVO the cruiseScheduleItemVO to set
	 */
	public void setCruiseScheduleItemVO(CruiseScheduleItemVO cruiseScheduleItemVO) {
		this.cruiseScheduleItemVO = cruiseScheduleItemVO;
	}

	/**
	 * @return the extraItemChargeVO
	 */
	public CruiseScheduleChargeVO getExtraItemChargeVO() {
		return extraItemChargeVO;
	}

	/**
	 * @param extraItemChargeVO the extraItemChargeVO to set
	 */
	public void setExtraItemChargeVO(CruiseScheduleChargeVO extraItemChargeVO) {
		this.extraItemChargeVO = extraItemChargeVO;
	}

	/**
	 * @return the cruiseList
	 */
	public List<CruiseVO> getCruiseList() {
		return cruiseList;
	}

	/**
	 * @param cruiseList the cruiseList to set
	 */
	public void setCruiseList(List<CruiseVO> cruiseList) {
		this.cruiseList = cruiseList;
	}

	/**
	 * @return the cruiseScheduleList
	 */
	public List<CruiseScheduleVO> getCruiseScheduleList() {
		return cruiseScheduleList;
	}

	/**
	 * @param cruiseScheduleList the cruiseScheduleList to set
	 */
	public void setCruiseScheduleList(List<CruiseScheduleVO> cruiseScheduleList) {
		this.cruiseScheduleList = cruiseScheduleList;
	}

	/**
	 * @return the addItemChargeList
	 */
	public List<CruiseScheduleChargeVO> getAddItemChargeList() {
		return addItemChargeList;
	}

	/**
	 * @param addItemChargeList the addItemChargeList to set
	 */
	public void setAddItemChargeList(List<CruiseScheduleChargeVO> addItemChargeList) {
		this.addItemChargeList = addItemChargeList;
	}

	/**
	 * @return the updItemChargeList
	 */
	public List<CruiseScheduleChargeVO> getUpdItemChargeList() {
		return updItemChargeList;
	}

	/**
	 * @param updItemChargeList the updItemChargeList to set
	 */
	public void setUpdItemChargeList(List<CruiseScheduleChargeVO> updItemChargeList) {
		this.updItemChargeList = updItemChargeList;
	}

	/**
	 * @return the delItemChargeList
	 */
	public List<CruiseScheduleChargeVO> getDelItemChargeList() {
		return delItemChargeList;
	}

	/**
	 * @param delItemChargeList the delItemChargeList to set
	 */
	public void setDelItemChargeList(List<CruiseScheduleChargeVO> delItemChargeList) {
		this.delItemChargeList = delItemChargeList;
	}

	public String getScheduleSearch() {
		return scheduleSearch;
	}

	public void setScheduleSearch(String scheduleSearch) {
		this.scheduleSearch = scheduleSearch;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public List<InvoiceAndExchangeOrderVO> getInvEoItemList() {
		return invEoItemList;
	}

	public void setInvEoItemList(List<InvoiceAndExchangeOrderVO> invEoItemList) {
		this.invEoItemList = invEoItemList;
	}

}
