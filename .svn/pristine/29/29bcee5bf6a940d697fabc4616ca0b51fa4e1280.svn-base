package com.bcs.zsg.product.bo;

import java.util.List;

import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.product.vo.RoomingListVO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.sales.vo.IdentityDetailVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonLangVO;
import com.bcs.zsg.sales.vo.PersonMealVO;

public interface RoomingListBO {
	public RoomingListVO getRoomingListByCategory(Long idTourDep, String strCategory) throws BusinessException ;
	public void insertVO(BaseVO baseVO) throws BusinessException ;
	public void updRoomingList(AddUpdDelVO roomingListAUDVO) throws BusinessException ;

	public List<RoomingListVO> getPICList(Long idTourDep) throws BusinessException;
	public List<RoomingListVO> getTourLeaderList(Long idTourDep) throws BusinessException;
	public List<RoomingListVO> getTourManagerList(Long idTourDep) throws BusinessException;
	public List<RoomingListVO> getIdHotelList(Long idTourDep) throws BusinessException;
	
	public AddressVO getAddressVO(Long personId) throws BusinessException;
	public List<PersonContactVO> getPersonContList(Long personId) throws BusinessException;
	public List<InvoiceVO> getInvoiceList(Long tourDepId) throws BusinessException;
	public PersonVO getPerson(Long persCorpId) throws BusinessException;
	public List<InvoicePaxVO> getInvoicePax(Long invId) throws BusinessException;
	public IdentityVO getPersonIdentity(Long id, String typeCd) throws BusinessException;
	public IdentityDetailVO getPersonIdentityDetails(Long id) throws BusinessException;
	public List<PersonLangVO> getpersonLang(Long id) throws BusinessException;
	public List<PersonMealVO> getpersonMeal(Long id) throws BusinessException;
	public CorporateVO getCorporateVO(Long personId) throws BusinessException;
	public CorAddressVO getCorpAddrVO(Long id) throws BusinessException;
	public List<Object> getuniqueList() throws BusinessException;
	

}
