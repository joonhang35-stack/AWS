package com.bcs.zsg.product.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.product.bo.HotelBO;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class HotelBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient HotelBO hotelBO;
	@Autowired
	private transient RegionBO regionBO;
	
	private HotelVO hotelVO;
	private HotelAddressVO hotelAddrVO;
	private HotelContactVO hotelContVO;
	
	private List<HotelVO> hotelList;
	private List<CountryVO> countryList; 
	private List<HotelContactVO> addContList;
	private List<HotelContactVO> updContList;
	private List<HotelContactVO> delContList;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		hotelVO = new HotelVO();
		hotelAddrVO = new HotelAddressVO();
		resetContactForm();
		resetContactList();
		// set default value
		hotelAddrVO.setTypeCd("0");
	}
	
	/**
	 * Reset contact
	 */
	public void resetContactForm() {
		hotelContVO = new HotelContactVO();
	}
	
	/**
	 * Reset contact list
	 */
	public void resetContactList() {
		addContList = new ArrayList<HotelContactVO>();
		updContList = new ArrayList<HotelContactVO>();
		delContList = new ArrayList<HotelContactVO>();
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			resetForm();
			hotelList = hotelBO.getHotelList();
			countryList = regionBO.getCountryList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add hotel
	 */
	public void addHotel() {
		try {
			if (StringUtils.isNotEmpty(hotelAddrVO.getAddr1())) {
				hotelVO.setHotelAddrList(new ArrayList<HotelAddressVO>());
				hotelVO.getHotelAddrList().add(hotelAddrVO);
			}
			hotelBO.addHotel(hotelVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update hotel
	 */
	public void updHotel() {
		try {
			// update contact
			hotelBO.updContactList(addContList, updContList, delContList);
			// add address list
			hotelVO.setHotelAddrList(new ArrayList<HotelAddressVO>());
			hotelVO.getHotelAddrList().add(hotelAddrVO);
			hotelBO.updHotel(hotelVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete hotel
	 */
	public void delHotel() {
		try {
			hotelBO.delHotel(hotelVO);
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add contact list
	 */
	public void addContact() {
		try {
			if (hotelVO.getHotelContList() == null) hotelVO.setHotelContList(new ArrayList<HotelContactVO>());
			hotelVO.getHotelContList().add(hotelContVO);
			resetContactForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Remove contact from list
	 */
	public void delContFromList(HotelContactVO vo) {
		try {
			for (int i = hotelVO.getHotelContList().size() - 1 ; i >= 0 ; i--) {
				HotelContactVO vo1 = hotelVO.getHotelContList().get(i);
				if (vo1.getTypeCd().equals(vo.getTypeCd()) && vo1.getNumber().equals(vo.getNumber())) {
					hotelVO.getHotelContList().remove(i);
					break;
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void addAddContList() {
		try {
			hotelContVO.setIdHotel(hotelVO.getId());
			addContList.add(hotelContVO);
			addContact();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add updated contact to list
	 */
	public void addUpdContList() {
		try {
			updContList.add(hotelContVO);
			resetContactForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add deleted contact to list
	 */
	public void addDelContList(HotelContactVO vo) {
		try {
			delContList.add(vo);
			delContFromList(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void handleHotelSelect(HotelVO vo) {
		try {
			hotelVO = vo;
			hotelVO.setHotelAddrList(hotelBO.getHotelAddrList(hotelVO.getId()));
			hotelVO.setHotelContList(hotelBO.getHotelContList(hotelVO.getId()));
			if (CollectionUtils.isNotEmpty(hotelVO.getHotelAddrList())) hotelAddrVO = hotelVO.getHotelAddrList().get(0);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the hotelBO
	 */
	public HotelBO getHotelBO() {
		return hotelBO;
	}

	/**
	 * @param hotelBO the hotelBO to set
	 */
	public void setHotelBO(HotelBO hotelBO) {
		this.hotelBO = hotelBO;
	}

	/**
	 * @return the hotelVO
	 */
	public HotelVO getHotelVO() {
		return hotelVO;
	}

	/**
	 * @param hotelVO the hotelVO to set
	 */
	public void setHotelVO(HotelVO hotelVO) {
		this.hotelVO = hotelVO;
	}

	/**
	 * @return the hotelAddrVO
	 */
	public HotelAddressVO getHotelAddrVO() {
		return hotelAddrVO;
	}

	/**
	 * @param hotelAddrVO the hotelAddrVO to set
	 */
	public void setHotelAddrVO(HotelAddressVO hotelAddrVO) {
		this.hotelAddrVO = hotelAddrVO;
	}

	/**
	 * @return the hotelContVO
	 */
	public HotelContactVO getHotelContVO() {
		return hotelContVO;
	}

	/**
	 * @param hotelContVO the hotelContVO to set
	 */
	public void setHotelContVO(HotelContactVO hotelContVO) {
		this.hotelContVO = hotelContVO;
	}

	/**
	 * @return the hotelList
	 */
	public List<HotelVO> getHotelList() {
		return hotelList;
	}

	/**
	 * @param hotelList the hotelList to set
	 */
	public void setHotelList(List<HotelVO> hotelList) {
		this.hotelList = hotelList;
	}

	/**
	 * @return the countryList
	 */
	public List<CountryVO> getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the addContList
	 */
	public List<HotelContactVO> getAddContList() {
		return addContList;
	}

	/**
	 * @param addContList the addContList to set
	 */
	public void setAddContList(List<HotelContactVO> addContList) {
		this.addContList = addContList;
	}

	/**
	 * @return the updContList
	 */
	public List<HotelContactVO> getUpdContList() {
		return updContList;
	}

	/**
	 * @param updContList the updContList to set
	 */
	public void setUpdContList(List<HotelContactVO> updContList) {
		this.updContList = updContList;
	}

	/**
	 * @return the delConList
	 */
	public List<HotelContactVO> getDelContList() {
		return delContList;
	}

	/**
	 * @param delConList the delConList to set
	 */
	public void setDelContList(List<HotelContactVO> delContList) {
		this.delContList = delContList;
	}

}
