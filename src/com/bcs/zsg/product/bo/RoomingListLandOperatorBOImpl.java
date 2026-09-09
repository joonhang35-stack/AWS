package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.service.RoomingListLandOperatorService;
import com.bcs.zsg.product.vo.RoomingListLandOperatorContVO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorVO;

public class RoomingListLandOperatorBOImpl implements RoomingListLandOperatorBO{
	
	@Autowired
	private RoomingListLandOperatorService roomingListLandOperatorService;
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.RoomingListLandOperatorBO#addLandOperatorSupplier(com.bcs.zsg.product.vo.RoomingListLandOperatorVO)
	 */
	@Override
	public void addLandOperatorSupplier(List<RoomingListLandOperatorVO> roomingListLandOprVOList, GenAddUpdDelVO<RoomingListLandOperatorVO> landOprAUDList) throws BusinessException {
		roomingListLandOperatorService.addLandOperatorSupplier(roomingListLandOprVOList, landOprAUDList);
	}
	

	@Override
	public List<RoomingListLandOperatorVO> getSupplierList(Long idTourDep, boolean isExcel)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListLandOperatorService.getSupplierList(idTourDep, isExcel);
	}
	
	@Override
	public List<RoomingListLandOperatorContVO> getContactList(Long idLandOperator)
			throws BusinessException {
		// TODO Auto-generated method stub
		return roomingListLandOperatorService.getContactList(idLandOperator);
	}
}
