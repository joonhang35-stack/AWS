package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;

public interface HotelDAO extends BaseDAO {

	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public HotelVO getHotelById(Long id) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<HotelVO> getHotelList() throws BusinessException;

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
