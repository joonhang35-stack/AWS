package com.bcs.zsg.product.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.bo.TourMICEBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class TourMICEBean extends TourPackageBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient TourMICEBO miceBO;
	
	private List<TourDepartureViewVO> tourDepViewList;
	private TourDepartureViewVO tourDepViewHistVO;
	private RegionVO regionVO;
	private CountryVO countryVO;
	
	/**
	 * 
	 */
	public void init() {
		try {
			initSearchParam();
			searchParamVO.setObj1(ProductConstant.TYPE_MICE);
			// get category list
			tourCatViewList = tourCatBO.getTourCatViewList(searchParamVO);
			regionList = regionBO.getRegionList();
			countryList = regionBO.getCountryList();
			
			loadAirlineList();
			resetRegionForm();
			resetCountryForm();
			resetForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void resetRegionForm() {
		regionVO = new RegionVO();
	}
	
	/**
	 * 
	 */
	public void resetCountryForm() {
		countryVO = new CountryVO();
	}
	
	/**
	 * Add tour mice
	 */
	public void addTourMICE() {
		try {
			if (CollectionUtils.isNotEmpty(tourDepViewList)) {
				for (TourDepartureViewVO vo : tourDepViewList) {
					if (vo.getCode().equals(tourDepVO.getCode())) throw new BusinessException(CommonErrConstant.ERR_TOUR_DEP_EXISTED);
				}
			}
			
			tourPkgVO.setIdTourTheme(1L);
			tourPkgVO.setIdTourCat(Long.parseLong((String) searchParamVO.getObj2()));
			tourPkgVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourPkgVO.setReserved3(getSessionInfoBean().getEmployeeVO().getDepartment());
			if (CollectionUtils.isNotEmpty(tourPkgVO.getRegionList())) {
				StringBuilder sb = new StringBuilder();
				for (RegionVO vo : tourPkgVO.getRegionList()) {
					sb.append(vo.getId()).append(",");
				}
				tourPkgVO.setRegions(sb.substring(0, sb.length() - 1));
			}
			if (CollectionUtils.isNotEmpty(tourPkgVO.getCountryList())) {
				StringBuilder sb = new StringBuilder();
				for (CountryVO vo : tourPkgVO.getCountryList()) {
					sb.append(vo.getId()).append(",");
				}
				tourPkgVO.setCountries(sb.substring(0, sb.length() - 1));
			}
			tourPkgBO.insertVO(tourPkgVO);
			tourDepVO.setIdTourPkg(tourPkgVO.getId());
			tourDepVO.setNumDays(tourPkgVO.getNumDays());
			tourDepVO.setNumNights(tourPkgVO.getNumNights());
			tourDepVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_AVAILABLE);
			tourDepVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourPkgBO.insertVO(tourDepVO);
			resetForm();
			handleTourMICECatSelect();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Updte tour mice
	 */
	public void updTourMICE() {
		try {
			if (!tourDepVO.getCode().equalsIgnoreCase(tourDepViewHistVO.getCode())) {
				if (CollectionUtils.isNotEmpty(tourDepViewList)) {
					for (TourDepartureViewVO vo : tourDepViewList) {
						if (vo.getCode().equals(tourDepVO.getCode())) throw new BusinessException(CommonErrConstant.ERR_TOUR_DEP_EXISTED);
					}
				}
			}
			
			if (CollectionUtils.isNotEmpty(tourPkgVO.getRegionList())) {
				StringBuilder sb = new StringBuilder();
				for (RegionVO vo : tourPkgVO.getRegionList()) {
					sb.append(vo.getId()).append(",");
				}
				tourPkgVO.setRegions(sb.substring(0, sb.length() - 1));
			} else tourPkgVO.setRegions(null);
			
			if (CollectionUtils.isNotEmpty(tourPkgVO.getCountryList())) {
				StringBuilder sb = new StringBuilder();
				for (CountryVO vo : tourPkgVO.getCountryList()) {
					sb.append(vo.getId()).append(",");
				}
				tourPkgVO.setCountries(sb.substring(0, sb.length() - 1));
			} else tourPkgVO.setCountries(null);
			
			tourPkgBO.updateVO(tourPkgVO);
			tourDepVO.setNumDays(tourPkgVO.getNumDays());
			tourDepVO.setNumNights(tourPkgVO.getNumNights());
			tourPkgBO.updateVO(tourDepVO);
			resetForm();
			handleTourMICECatSelect();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete tour mice
	 */
	public void delTourMICE() {
		try {
			if (tourPkgBO.isInvEOIssued(tourDepVO, null)) throw new BusinessException(CommonErrConstant.ERR_MICE_ISSUED_INV_EO);
			tourDepVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
			tourPkgBO.updateVO(tourDepVO);
			tourPkgVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
			tourPkgBO.updateVO(tourPkgVO);
			resetForm();
			handleTourMICECatSelect();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void addRegion() {
		try {
			if (tourPkgVO.getRegionList() == null) tourPkgVO.setRegionList(new ArrayList<RegionVO>());
			for (RegionVO vo : regionList) {
				if (vo.getId().equals(regionVO.getId())) {
					tourPkgVO.getRegionList().add(vo);
					break;
				}
			}
			resetRegionForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	/*public void updRegion() {
		try {
			for (RegionVO vo : regionList) {
				if (vo.getId().equals(regionVO.getId())) {
					regionVO = vo;
					break;
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}*/
	
	/**
	 * 
	 */
	public void delRegion(RegionVO vo) {
		try {
			tourPkgVO.getRegionList().remove(vo);
			resetRegionForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void addCountry() {
		try {
			if (tourPkgVO.getCountryList() == null) tourPkgVO.setCountryList(new ArrayList<CountryVO>());
			for (CountryVO vo : countryList) {
				if (vo.getId().equals(countryVO.getId())) {
					tourPkgVO.getCountryList().add(vo);
					break;
				}
			}
			//tourPkgVO.getCountryList().add(countryVO);
			resetCountryForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void delCountry(CountryVO vo) {
		try {
			tourPkgVO.getCountryList().remove(vo);
			resetCountryForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param flag
	 */
	public void handleTourMICECatSelect() {
		try {
			if (searchParamVO.getObj2() != null) tourDepViewList = miceBO.getTourMICEList(searchParamVO, getSessionInfoBean().getEmployeeVO());
			else tourDepViewList = null;
			resetFilteredObjList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 */
	public void handleRegionSelect() {
		try {
			//countryList = tourPkgBO.getCountryList(tourPkgVO.getIdRegion());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void handleTourDepSelect(TourDepartureViewVO vo) {
		try {
			tourPkgVO = vo.getTourPkgVO();
			tourDepVO = (TourDepartureVO) vo.clone();
			tourDepVO.setIdTourPkg(tourPkgVO.getId());
			tourDepViewHistVO = (TourDepartureViewVO) vo.clone();
			
			if (StringUtils.isNotEmpty(tourPkgVO.getRegions())) tourPkgVO.setRegionList(miceBO.getMICERegionList(tourPkgVO.getRegions()));
			if (StringUtils.isNotEmpty(tourPkgVO.getCountries())) tourPkgVO.setCountryList(miceBO.getMICECountryList(tourPkgVO.getCountries()));
			//handleRegionSelect();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the tourDepViewList
	 */
	public List<TourDepartureViewVO> getTourDepViewList() {
		return tourDepViewList;
	}

	/**
	 * @param tourDepViewList the tourDepViewList to set
	 */
	public void setTourDepViewList(List<TourDepartureViewVO> tourDepViewList) {
		this.tourDepViewList = tourDepViewList;
	}

	/**
	 * @return the regionVO
	 */
	public RegionVO getRegionVO() {
		return regionVO;
	}

	/**
	 * @param regionVO the regionVO to set
	 */
	public void setRegionVO(RegionVO regionVO) {
		this.regionVO = regionVO;
	}

	/**
	 * @return the countryVO
	 */
	public CountryVO getCountryVO() {
		return countryVO;
	}

	/**
	 * @param countryVO the countryVO to set
	 */
	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}
}
