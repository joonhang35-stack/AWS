package com.bcs.zsg.product.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.dao.HotelDAO;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;

public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDAO hotelDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.dao.HotelDAO#getHotelById(java.lang.Long)
	 */
	@Override
	public HotelVO getHotelById(Long id) throws BusinessException {
		return hotelDAO.getHotelById(id);
	}	

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#addHotel(com.bcs.zsg.product.vo.HotelVO)
	 */
	@Override
	public void addHotel(HotelVO hotelVO) throws BusinessException {
		hotelDAO.insert(hotelVO);
		
		// insert hotel address
		if (CollectionUtils.isNotEmpty(hotelVO.getHotelAddrList())) {
			for (HotelAddressVO vo : hotelVO.getHotelAddrList()) {
				vo.setIdHotel(hotelVO.getId());
				hotelDAO.insert(vo);
			}
		}
		// insert hotel contact list
		if (CollectionUtils.isNotEmpty(hotelVO.getHotelContList())) {
			for (HotelContactVO vo : hotelVO.getHotelContList()) {
				vo.setIdHotel(hotelVO.getId());
				hotelDAO.insert(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#updHotel(com.bcs.zsg.product.vo.HotelVO)
	 */
	@Override
	public void updHotel(HotelVO hotelVO) throws BusinessException {
		// update or delete address
		if (CollectionUtils.isNotEmpty(hotelVO.getHotelAddrList())) {
			for (HotelAddressVO vo : hotelVO.getHotelAddrList()) {
				if (vo.getId() != null) {
					if (StringUtils.isEmpty(vo.getAddr1())) hotelDAO.delete(vo);
					else hotelDAO.update(vo);
				} else {
					vo.setIdHotel(hotelVO.getId());
					hotelDAO.insert(vo);
				}
			}
		}
		// update hotel
		hotelDAO.update(hotelVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#updContactList(java.util.List, java.util.List)
	 */
	@Override
	public void updContactList(List<HotelContactVO> addContList, List<HotelContactVO> updContList, List<HotelContactVO> delContList) {
		// add contacts
		if (CollectionUtils.isNotEmpty(addContList)) {
			for (HotelContactVO vo : addContList) {
				hotelDAO.insert(vo);
			}
		}
		// update contacts
		if (CollectionUtils.isNotEmpty(updContList)) {
			for (HotelContactVO vo : updContList) {
				hotelDAO.update(vo);
			}
		}
		// delete contacts
		if (CollectionUtils.isNotEmpty(delContList)) {
			for (HotelContactVO vo : delContList) {
				hotelDAO.delete(vo);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#delHotel(com.bcs.zsg.product.vo.HotelVO)
	 */
	@Override
	public void delHotel(HotelVO hotelVO) throws BusinessException {
		// delete contacts
		if (CollectionUtils.isNotEmpty(hotelVO.getHotelContList())) {
			for (HotelContactVO vo : hotelVO.getHotelContList()) {
				hotelDAO.delete(vo);
			}
		}
		// delete address
		if (CollectionUtils.isNotEmpty(hotelVO.getHotelAddrList())) {
			for (HotelAddressVO vo : hotelVO.getHotelAddrList()) {
				hotelDAO.delete(vo);
			}
		}
		// delete hotel
		hotelDAO.delete(hotelVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#getHotelList(java.lang.Long)
	 */
	@Override
	public List<HotelVO> getHotelList(Long idRegion) throws BusinessException {
		if (idRegion == null) return hotelDAO.getHotelList();
		return hotelDAO.getHotelList(idRegion);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#getHotelAddrList(java.lang.Long)
	 */
	@Override
	public List<HotelAddressVO> getHotelAddrList(Long id) throws BusinessException {
		return hotelDAO.getHotelAddrList(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.HotelService#getHotelContList(java.lang.Long)
	 */
	@Override
	public List<HotelContactVO> getHotelContList(Long id) throws BusinessException {
		return hotelDAO.getHotelContList(id);
	}

}
