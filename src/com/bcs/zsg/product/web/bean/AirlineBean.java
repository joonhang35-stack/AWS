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
import com.bcs.zsg.product.bo.AirlineBO;
import com.bcs.zsg.product.bo.InvoiceAndExchangeOrderBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.AirlineScheduleChargeVO;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;

public class AirlineBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient AirlineBO airlineBO;
	@Autowired
	protected transient InvoiceAndExchangeOrderBO invEoBO;

	private AirlineVO airlineVO;
	private AirlineScheduleVO airlineScheduleVO;
	private AirlineScheduleItemVO airlineScheduleItemVO;
	private AirlineScheduleChargeVO extraItemChargeVO;

	private List<AirlineVO> airlineList;
	private List<AirlineScheduleVO> airlineScheduleList;
	private List<AirlineScheduleChargeVO> addItemChargeList;
	private List<AirlineScheduleChargeVO> updItemChargeList;
	private List<AirlineScheduleChargeVO> delItemChargeList;
	private List<InvoiceAndExchangeOrderVO> invEoItemList;

	private String scheduleSearch;
	private String typeCode;

	private boolean itemChargeAdd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		scheduleSearch = "";
		airlineVO = new AirlineVO();
		airlineVO.setTypeCode(typeCode);
		resetScheduleForm();
		// set list
		// airlineScheduleList = new ArrayList<AirlineScheduleVO>();
	}

	/**
	 * Reset airline schedule form
	 */
	public void resetScheduleForm() {
		airlineScheduleVO = new AirlineScheduleVO();
		if (ProductConstant.TOUR_DEP_ITM_TYPE_CRUISE.equals(airlineVO.getTypeCode())) {
			airlineScheduleVO.setTktValidity(0);
		}
		resetItemForm();
	}

	/**
	 * Reset airline schedule item form
	 */
	public void resetItemForm() {
		airlineScheduleItemVO = new AirlineScheduleItemVO();
		// set list
		addItemChargeList = new ArrayList<AirlineScheduleChargeVO>();
		updItemChargeList = new ArrayList<AirlineScheduleChargeVO>();
		delItemChargeList = new ArrayList<AirlineScheduleChargeVO>();
		resetItemChargeForm();
	}

	/**
	 * Reset airline schedule item charge form
	 */
	public void resetItemChargeForm() {
		setItemChargeAdd(false);
		extraItemChargeVO = new AirlineScheduleChargeVO();
		if (airlineScheduleVO.getExtraItemChargeList() == null)  airlineScheduleVO.setExtraItemChargeList(new ArrayList<AirlineScheduleChargeVO>());
	}
	
	public void resetItemChargeList() {
		// set list
		addItemChargeList = new ArrayList<AirlineScheduleChargeVO>();
		updItemChargeList = new ArrayList<AirlineScheduleChargeVO>();
		delItemChargeList = new ArrayList<AirlineScheduleChargeVO>();
	}

	/**
	 * Initialization
	 */
	public void init() {
		try {
			resetForm();
			airlineList = airlineBO.getAirlineList();
			
			InvoiceAndExchangeOrderVO searchFilter = new InvoiceAndExchangeOrderVO();
			searchFilter.setItemTypeList(Arrays.asList(ProductConstant.INV_ITM_TYPE_AIRLINE));
			searchFilter.setFilterAcctMgrUse(false);
			searchFilter.setIsShow(true);
			invEoItemList = invEoBO.getInvoiceAndExchangeOrderList(getSessionInfoBean().getCompanyVO().getId(), searchFilter, true, true);

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void init(String typeCode) {
		this.typeCode = typeCode;

		try {
			resetForm();
			airlineList = airlineBO.getAirlineListByTypeCode(typeCode);

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	/**
	 * Add airline
	 */
	public void addAirline() {
		try {
			airlineBO.addAirline(airlineVO);
			init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update airline
	 */
	public void updAirline() {
		try {
			airlineBO.updAirline(airlineVO);
			init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Delete airline
	 */
	public void delAirline() {
		try {
			airlineBO.delAirline(airlineVO);
			init();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Add airline schedule
	 */
	public void addAirlineSchedule() {
		try {
			// add airline schedule
			airlineScheduleVO.setIdAirline(airlineVO.getId());

//			// add item charge to list
//			addNormalItemChargeToList();
			
			airlineBO.addAirlineSchedule(airlineScheduleVO);

			// get schedule list
			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());
//			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	/**
	 * Delete airline schedule
	 */
	public void delAirlineSchedule() {
		try {
			airlineBO.delAirlineSchedule(airlineScheduleVO);
			// get schedule list
			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());
			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			resetScheduleForm();
			errorResult(t);
		}
	}

	/**
	 * Delete airline schedule Item
	 */
	public void delAirlineScheduleItem() {
		try {
			airlineBO.delAirlineScheduleItem(airlineScheduleItemVO);
			// get schedule list
			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());
			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			resetScheduleForm();
			errorResult(t);
		}
	}

	/**
	 * Add airline schedule item
	 */
	public void addAirlineScheduleItem() {
		try {
			airlineScheduleItemVO.setIdAirlineSchedule(airlineScheduleVO.getId());

			// add airline schedule item
			airlineBO.addAirlineScheduleItem(airlineScheduleItemVO);
			// get schedule list
			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());
			resetItemForm();
//			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update airline schedule item
	 */
	public void updAirlineScheduleItem() {
		try {
			// update airline schedule item charge
			// airlineBO.updAirlineScheduleCharge(airlineScheduleVO, addItemChargeList,
			// updItemChargeList, delItemChargeList);
			// update airline schedule item
			airlineBO.updAirlineScheduleItem(airlineScheduleItemVO);
			// get schedule list
			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());
			resetScheduleForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update airline schedule item
	 */
	public void updAirlineSchedule() {
		try {

			// update airline schedule item charge
			airlineBO.updAirlineScheduleCharge(airlineScheduleVO, addItemChargeList, updItemChargeList,
					delItemChargeList);

//			// add item charge to list
//			addNormalItemChargeToList();

			// update airline schedule item
			airlineBO.updAirlineSchedule(airlineScheduleVO);

			// get schedule list
//			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());
			resetScheduleForm();
			// resetItemForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onExtraItemChargeAdd() {
		setItemChargeAdd(true);
		extraItemChargeVO = new AirlineScheduleChargeVO();
	}

	public void onExtraItemChargeSelect(AirlineScheduleChargeVO vo) {
		setItemChargeAdd(false);
		extraItemChargeVO = vo;
	}
	
	/**
	 * Add item chareges
	 * 
	 * @param flag
	 */
	public void addExtraItemCharge() {
		try {
			extraItemChargeVO.setIdAirlineSchedule(airlineScheduleVO.getId());
			addItemChargeList.add(extraItemChargeVO);
			if (airlineScheduleVO.getExtraItemChargeList() == null)  airlineScheduleVO.setExtraItemChargeList(new ArrayList<AirlineScheduleChargeVO>());
			airlineScheduleVO.getExtraItemChargeList().add(extraItemChargeVO);
			
			resetItemChargeForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Update item charge
	 * 
	 * @param flag
	 */
	public void updExtraItemCharge() {
		try {
			if (extraItemChargeVO.getId() != null) {
				updItemChargeList.remove(extraItemChargeVO);
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
	public void delExtraItemCharge(AirlineScheduleChargeVO vo) {
		try {
			if (vo.getId() != null) {
				if (vo.getId() != null) delItemChargeList.add(vo);
				else addItemChargeList.remove(vo);
			}
			airlineScheduleVO.getExtraItemChargeList().remove(vo);
			successResult();
			
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
			errorResult(t);
		}
	}

	/**
	 * Airline schedule
	 */
	public void handleAirlineSchedule(AirlineVO vo) {
		try {
			scheduleSearch = "";
			airlineVO = vo;
			airlineScheduleList = airlineBO.getAirlineScheduleList(vo.getId());

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * 
	 * @param vo
	 */
	public void populateScheduleChargeList(AirlineScheduleVO vo) {
		airlineScheduleVO = vo;
		try {

//			this.setAirlineScheduleVO(airlineScheduleVO);
			// set fees and charges
			List<AirlineScheduleChargeVO> chargeList = airlineBO.getAirlineScheduleChargeList(vo.getId());
			if (CollectionUtils.isNotEmpty(chargeList)) {
				// if (CollectionUtils.isNotEmpty(normalChargeList))
				// airlineScheduleItemVO.setNormalItemChargeList(normalChargeList);
				if (CollectionUtils.isNotEmpty(chargeList))
					airlineScheduleVO.setExtraItemChargeList(chargeList);
			}
			
			resetItemChargeList();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onAddAirlineSchedule() {
		resetScheduleForm();
	}

	public void handleAirlineScheduleItemSelect(AirlineScheduleItemVO vo) {
		airlineScheduleItemVO = vo;
	}

	public void resetArlineSchedule() {
		try {
			airlineScheduleList = airlineBO.getAirlineScheduleList(airlineVO.getId());

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		airlineScheduleVO = new AirlineScheduleVO();
	}

	public void searchSchedule() {
		try {
			FacesMessage msg = null;
			airlineScheduleList = airlineBO.getAirlineScheduleListByDesc(airlineVO.getId(), scheduleSearch);
			scheduleSearch = "";
			if (CollectionUtils.isEmpty(airlineScheduleList)) {
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
//		if (airlineScheduleVO.getNormalItemChargeList() == null) airlineScheduleVO.setNormalItemChargeList(new ArrayList<AirlineScheduleChargeVO>());
//		
//		// add apt adt
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_APT_ADT);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_APT_ADT);
//		if (normalItemChargeVO.getAmtAptAdt() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtAptAdt());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add apt chd
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_APT_CHD);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_APT_CHD);
//		if (normalItemChargeVO.getAmtAptChd() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtAptChd());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add fuel adt
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_FUEL_ADT);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_FUEL_ADT);
//		if (normalItemChargeVO.getAmtFuelAdt() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtFuelAdt());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add fuel chd
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_FUEL_CHD);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_FUEL_CHD);
//		if (normalItemChargeVO.getAmtFuelChd() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtFuelChd());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add trvl ins
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_TRVL_INS);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_TRVL_INS);
//		if (normalItemChargeVO.getAmtTrvlIns() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtTrvlIns());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add visa
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_VISA);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_VISA);
//		if (normalItemChargeVO.getAmtVisa() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtVisa());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add a/c
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_AC);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_AC);
//		if (normalItemChargeVO.getAmtAC() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtAC());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//		// add tipping
//		extraItemChargeVO = new AirlineScheduleChargeVO();
//		extraItemChargeVO.setTypeCd(ProductConstant.TYPE_TIPPING);
//		extraItemChargeVO.setTypeDesc(ProductConstant.TYPE_TIPPING);
//		if (normalItemChargeVO.getAmtTipping() == null) extraItemChargeVO.setAmount(0.0);
//		else extraItemChargeVO.setAmount(normalItemChargeVO.getAmtTipping());
//		airlineScheduleVO.getNormalItemChargeList().add(0, extraItemChargeVO);
//	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the airlineVO
	 */
	public AirlineVO getAirlineVO() {
		return airlineVO;
	}

	/**
	 * @param airlineVO the airlineVO to set
	 */
	public void setAirlineVO(AirlineVO airlineVO) {
		this.airlineVO = airlineVO;
	}

	/**
	 * @return the airlineScheduleVO
	 */
	public AirlineScheduleVO getAirlineScheduleVO() {
		return airlineScheduleVO;
	}

	/**
	 * @param airlineScheduleVO the airlineScheduleVO to set
	 */
	public void setAirlineScheduleVO(AirlineScheduleVO airlineScheduleVO) {
		this.airlineScheduleVO = airlineScheduleVO;
	}

	/**
	 * @return the airlineScheduleItemVO
	 */
	public AirlineScheduleItemVO getAirlineScheduleItemVO() {
		return airlineScheduleItemVO;
	}

	/**
	 * @param airlineScheduleItemVO the airlineScheduleItemVO to set
	 */
	public void setAirlineScheduleItemVO(AirlineScheduleItemVO airlineScheduleItemVO) {
		this.airlineScheduleItemVO = airlineScheduleItemVO;
	}

	/**
	 * @return the extraItemChargeVO
	 */
	public AirlineScheduleChargeVO getExtraItemChargeVO() {
		return extraItemChargeVO;
	}

	/**
	 * @param extraItemChargeVO the extraItemChargeVO to set
	 */
	public void setExtraItemChargeVO(AirlineScheduleChargeVO extraItemChargeVO) {
		this.extraItemChargeVO = extraItemChargeVO;
	}

	/**
	 * @return the airlineList
	 */
	public List<AirlineVO> getAirlineList() {
		return airlineList;
	}

	/**
	 * @param airlineList the airlineList to set
	 */
	public void setAirlineList(List<AirlineVO> airlineList) {
		this.airlineList = airlineList;
	}

	/**
	 * @return the airlineScheduleList
	 */
	public List<AirlineScheduleVO> getAirlineScheduleList() {
		return airlineScheduleList;
	}

	/**
	 * @param airlineScheduleList the airlineScheduleList to set
	 */
	public void setAirlineScheduleList(List<AirlineScheduleVO> airlineScheduleList) {
		this.airlineScheduleList = airlineScheduleList;
	}

	/**
	 * @return the addItemChargeList
	 */
	public List<AirlineScheduleChargeVO> getAddItemChargeList() {
		return addItemChargeList;
	}

	/**
	 * @param addItemChargeList the addItemChargeList to set
	 */
	public void setAddItemChargeList(List<AirlineScheduleChargeVO> addItemChargeList) {
		this.addItemChargeList = addItemChargeList;
	}

	/**
	 * @return the updItemChargeList
	 */
	public List<AirlineScheduleChargeVO> getUpdItemChargeList() {
		return updItemChargeList;
	}

	/**
	 * @param updItemChargeList the updItemChargeList to set
	 */
	public void setUpdItemChargeList(List<AirlineScheduleChargeVO> updItemChargeList) {
		this.updItemChargeList = updItemChargeList;
	}

	/**
	 * @return the delItemChargeList
	 */
	public List<AirlineScheduleChargeVO> getDelItemChargeList() {
		return delItemChargeList;
	}

	/**
	 * @param delItemChargeList the delItemChargeList to set
	 */
	public void setDelItemChargeList(List<AirlineScheduleChargeVO> delItemChargeList) {
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

	public boolean isItemChargeAdd() {
		return itemChargeAdd;
	}

	public void setItemChargeAdd(boolean itemChargeAdd) {
		this.itemChargeAdd = itemChargeAdd;
	}
}
