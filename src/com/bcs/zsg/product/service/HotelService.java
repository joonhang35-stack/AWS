package com.bcs.zsg.product.service;

import java.util.List;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;

public interface HotelService {
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public HotelVO getHotelById(Long id) throws BusinessException;

	/**
	 * 
	 * @param hotelVO
	 * @throws BusinessException
	 */
	public void addHotel(HotelVO hotelVO) throws BusinessException;

	/**
	 * 
	 * @param hotelVO
	 * @throws BusinessException
	 */
	public void updHotel(HotelVO hotelVO) throws BusinessException;

	/**
	 * 
	 * @param addContList
	 * @param updContList
	 * @param delContList
	 */
	public void updContactList(List<HotelContactVO> addContList, List<HotelContactVO> updContList, List<HotelContactVO> delContList);

	/**
	 * 
	 * @param hotelVO
	 * @throws BusinessException
	 */
	public void delHotel(HotelVO hotelVO) throws BusinessException;

	/**
	 * 
	 * @param idRegion
	 * @return
	 * @throws BusinessException
	 */
	public List<HotelVO> getHotelList(Long idRegion) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public List<HotelAddressVO> getHotelAddrList(Long id) throws BusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public List<HotelContactVO> getHotelContList(Long id) throws BusinessException;

}
