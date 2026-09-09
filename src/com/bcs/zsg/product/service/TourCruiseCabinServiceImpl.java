package com.bcs.zsg.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.product.dao.TourCruiseCabinDAO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourCruiseCabinDiscountVO;
import com.bcs.zsg.product.vo.TourCruiseCabinVO;
import com.bcs.zsg.product.vo.TourDepItemVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class TourCruiseCabinServiceImpl implements TourCruiseCabinService {

	@Autowired
	private TourCruiseCabinDAO tourCruiseCabinDAO;
	
	@Override
	public void addTourCruiseCabinList(List<TourCruiseCabinVO> tourCruiseCabinList, TourDepartureVO tourDepVO) throws BusinessException {
		List<Long> companyId = new ArrayList<Long>();
		
		if (CollectionUtils.isNotEmpty(tourDepVO.getTourDepItemList())) {
			for (TourDepItemVO itemVO : tourDepVO.getTourDepItemList()) {
				if (!companyId.contains(itemVO.getCompanyVO().getId())) {
					for (TourCruiseCabinVO cabinVO : tourCruiseCabinList) {
						cabinVO.setId(null);
						cabinVO.setIdCompany(itemVO.getCompanyVO().getId());
						cabinVO.setIdTourDep(tourDepVO.getId());
						cabinVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
						
						// to solve "Tour departure still show 'Available' after fully booked issue"
						if(cabinVO.getCabinAllotment() == 0) {
							cabinVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_FULL);
						}
						else {
							cabinVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_AVAILABLE);
						}
						
						addTourCruiseCabin(cabinVO);
					}
					companyId.add(itemVO.getCompanyVO().getId());
				}
			}
		}
	}
	
	@Override
	public void updTourCruiseCabinList(List<TourCruiseCabinVO> tourCruiseCabinList, TourDepartureVO tourDepVO) throws BusinessException {
		if (CollectionUtils.isNotEmpty(tourDepVO.getTourDepItemList())) {
			// delete all tour cruise cabin 1st
			tourCruiseCabinDAO.deleteByTourDep(tourDepVO.getId());
			
			List<Long> companyId = new ArrayList<Long>();
			
			for (TourDepItemVO itemVO : tourDepVO.getTourDepItemList()) {
				if (!companyId.contains(itemVO.getCompanyVO().getId())) {
					for (TourCruiseCabinVO cabinVO : tourCruiseCabinList) {
						// insert cruise cabin
						if (cabinVO.getId() == null) {
							cabinVO.setIdCompany(itemVO.getCompanyVO().getId());
							cabinVO.setIdTourDep(tourDepVO.getId());
							cabinVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
							
							// to solve "Tour departure still show 'Available' after fully booked issue"
							if(cabinVO.getCabinAllotment() == 0) {
								cabinVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_FULL);
							}
							else {
								cabinVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_AVAILABLE);
							}
							
							addTourCruiseCabin(cabinVO);
							
						// update cruise cabin
						} else {
							
							// to solve "Tour departure still show 'Available' after fully booked issue"
							if(cabinVO.getCabinAllotment() == 0) {
								cabinVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_FULL);
							}
							else {
								cabinVO.setTourStatusCd(ProductConstant.TOUR_STATUS_CD_AVAILABLE);
							}
							
							cabinVO.setIdCompany(itemVO.getCompanyVO().getId());
							updTourCruiseCabin(cabinVO);
						}
					}
					companyId.add(itemVO.getCompanyVO().getId());
				}
			}
		}
	}

	@Override
	public void addTourCruiseCabin(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException {
		tourCruiseCabinDAO.insert(tourCruiseCabinVO);
		
		// insert discount level 1 & 2
		TourCruiseCabinDiscountVO discountVO = new TourCruiseCabinDiscountVO();
		discountVO.setIdCruiseCabin(tourCruiseCabinVO.getId());
		discountVO.setDiscountAmt(0);
		discountVO.setDiscountPax(0);
		
		if (tourCruiseCabinVO.getDiscountLvl1() != 0) {
			discountVO.setIdCruiseCabin(tourCruiseCabinVO.getId());
			discountVO.setDiscountAmt(tourCruiseCabinVO.getDiscountLvl1());
			discountVO.setDiscountPax(tourCruiseCabinVO.getDiscountLvl1Pax());
		}
		tourCruiseCabinDAO.insert(discountVO);
		
		discountVO = new TourCruiseCabinDiscountVO();
		discountVO.setIdCruiseCabin(tourCruiseCabinVO.getId());
		discountVO.setDiscountAmt(0);
		discountVO.setDiscountPax(0);
		
		if (tourCruiseCabinVO.getDiscountLvl2() != 0) {
			discountVO.setIdCruiseCabin(tourCruiseCabinVO.getId());
			discountVO.setDiscountAmt(tourCruiseCabinVO.getDiscountLvl2());
			discountVO.setDiscountPax(tourCruiseCabinVO.getDiscountLvl2Pax());
		}
		tourCruiseCabinDAO.insert(discountVO);
	}

	@Override
	public void updTourCruiseCabin(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException {
		tourCruiseCabinDAO.updateByCode(tourCruiseCabinVO);
		
		// update discount level 1 & 2
		List<TourCruiseCabinDiscountVO> discountList = tourCruiseCabinDAO.getTourCruiseCabinDiscountList(tourCruiseCabinVO.getId());
		
		TourCruiseCabinDiscountVO discountVO = discountList.get(0);
		discountVO.setDiscountAmt(tourCruiseCabinVO.getDiscountLvl1());
		discountVO.setDiscountPax(tourCruiseCabinVO.getDiscountLvl1Pax());
		tourCruiseCabinDAO.update(discountVO);
		
		discountVO = discountList.get(1);
		discountVO.setDiscountAmt(tourCruiseCabinVO.getDiscountLvl2());
		discountVO.setDiscountPax(tourCruiseCabinVO.getDiscountLvl2Pax());
		tourCruiseCabinDAO.update(discountVO);
	}

	@Override
	public List<TourCruiseCabinVO> getTourCruiseCabinList(Long idTourDep, Long idCompany) throws BusinessException {
		return tourCruiseCabinDAO.getTourCruiseCabinList(idTourDep, idCompany);
	}

	@Override
	public boolean isTourCruiseCabinBooked(Long idTourCruiseCabin) throws BusinessException {
		return tourCruiseCabinDAO.isTourCruiseCabinBooked(idTourCruiseCabin);
	}

	@Override
	public List<TourCruiseCabinDiscountVO> getTourCruiseCabinDiscountList(Long idTourCruiseCabin) throws BusinessException {
		return tourCruiseCabinDAO.getTourCruiseCabinDiscountList(idTourCruiseCabin);
	}

	@Override
	public void updateTourCruiseCabinAcctCode(TourCruiseCabinVO tourCruiseCabinVO) throws BusinessException {
		tourCruiseCabinDAO.updateTourCruiseCabinAcctCode(tourCruiseCabinVO);
	}

	/* ############################ For API - Start ############################ */
	@Override
	public List<TourCruiseCabinVO> getTourCruiseCabinListForApi(Long idTourDep, Long idCompany) throws BusinessException {
		return tourCruiseCabinDAO.getTourCruiseCabinListForApi(idTourDep, idCompany);
	}
	/* ############################ For API - End   ############################ */
}
