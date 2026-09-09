package com.bcs.zsg.product.bo;

import java.util.List;

import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.vo.RoomingListLandOperatorContVO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorVO;

public interface RoomingListLandOperatorBO {
	/**
	 * 
	 * @param roomingListLandOperatorVO
	 * @throws BusinessException
	 */
	public void addLandOperatorSupplier(List<RoomingListLandOperatorVO> roomingListLandOprVOList, GenAddUpdDelVO<RoomingListLandOperatorVO> landOprAUDList) throws BusinessException;
	public List<RoomingListLandOperatorVO> getSupplierList(Long idTourDep, boolean isExcel) throws BusinessException;
	public List<RoomingListLandOperatorContVO> getContactList(Long idLandOperator) throws BusinessException;
}
