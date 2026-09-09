package com.bcs.zsg.product.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.bo.RoomTypeBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.TourItineryVO;
import com.bcs.zsg.product.vo.TourPackageRoomPriceVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.product.vo.TourThemeVO;
import com.bcs.zsg.product.vo.RoomTypeVO;

public class TourFreeNEasyBean extends TourPackageBean {

	private static final long serialVersionUID = 1L;

	protected transient RoomTypeBO roomTypeBO;
	
	private List<TourPackageVO> tourPkgList;
	private List<RoomTypeVO> roomTypeList;

	protected boolean roomPriceAdd;
	protected TourPackageRoomPriceVO roomPriceVO;
	protected AddUpdDelVO roomPriceVOList;
 
	public TourFreeNEasyBean() {
		roomPriceVO = new TourPackageRoomPriceVO(); 
		//setTourRoomTypeList(new ArrayList<RoomTypeVO>());
		roomTypeList = new ArrayList<RoomTypeVO>();
		try{
		//loadRoomTypeList();  
		}catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * @param tourPackageRemarksList the tourPackageRemarksList to set
	 */
	public void setTourRoomTypeList(List<RoomTypeVO> tourPackageRoomTypeList) {
		this.roomTypeList = tourPackageRoomTypeList;
	}
	
	/**
	 * Load RoomType list
	 * @throws BusinessException
	 */
	protected void loadRoomTypeList() throws BusinessException{  
		roomTypeList = tourPkgBO.getRoomTypeList();
		 
	}
	
	/**
	 * Handle airline selection
	 */
	public void handleRoomTypeSelect(SelectEvent event) {
		try { 
			RoomTypeVO roomTypeVO = (RoomTypeVO) event.getObject(); 
			roomPriceVO.setRoomName(roomTypeVO.getDesc()); 
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add tour package
	 */
	public void addTourPkg() {
		try {
			// insert tour package
			tourPkgVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourPkgVO.setTypeCd("FNE");
			tourPkgVO.setReserved1(tourItineryVO.getPath());
			tourPkgVO.setReserved2(tourItineryVO.getName());
			tourPkgBO.addTourPkg(tourPkgVO); 
			
			// set tour departure info & insert
			tourDepVO.setIdTourPkg(tourPkgVO.getId());
			tourDepVO.setDtDep(tourPkgVO.getDtTravelEnd());
			tourDepVO.setDesc(tourPkgVO.getNameEn());
			tourDepVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourDepVO.setTourStatusCd("A"); 
			tourPkgBO.addTourDep(tourDepVO);
			
			// set tour itinery info & insert 
			//tourItineryVO.setIdTourDep(tourDepVO.getId());
			//if(tourItineryVO.getName() != null) tourPkgBO.insertVO(tourItineryVO);
			
			// upload file
			fileUploadUtils.uploadFile();
 
			handleTourCatSelect(true); 
			resetForm(); 
			resetFNEForm();
			
			successResult();
 
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update tour package
	 */
	public void updTourPkg() {
		try {
			/*if (tourItineryVO != null) {
				if (tourItineryVO.getId() != null) tourPkgBO.updateVO(tourItineryVO);
				else if (tourItineryVO.getName() != null) {
					tourItineryVO.setIdTourDep(tourDepVO.getId());
					tourPkgBO.insertVO(tourItineryVO);
					// upload file
					fileUploadUtils.uploadFile();
				}
			}*/
			
			tourPkgVO.setReserved1(tourItineryVO.getPath());
			tourPkgVO.setReserved2(tourItineryVO.getName());
			tourPkgBO.updateVO(tourPkgVO);
			
			tourPkgBO.updateVO(tourDepVO);
			tourPkgBO.updTourFNEPackage(tourPkgVO,roomPriceVOList);
			
			// upload file
			fileUploadUtils.uploadFile();
			
			handleTourCatSelect(true);
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete tour package
	 */
	public void delTourPkg() {
		try {
			if (tourItineryVO != null && tourItineryVO.getId() != null) delTourItinery();
			tourPkgBO.deleteVO(tourDepVO);
			tourPkgBO.deleteVO(tourPkgVO);
			handleTourCatSelect(true);
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}


	/**
	 * Handle tour category selection
	 */
	public void handleTourFNECatSelect(boolean flag) {
		try {
			 handleTourCatSelect(flag);
			 	
			//loadRoomTypeList(); 
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete tour Itinery
	 */
	public void delTourItinery() {
		try {
			tourPkgBO.deleteVO(tourItineryVO);
			// delete file
			fileUploadUtils.deleteFile(tourItineryVO.getPath(), tourItineryVO.getName());
			// reset tourItineryVO
			tourItineryVO = new TourItineryVO();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle option change
	 */
	public void handleOptionChange() {
		try {
			if (tourPkgVO.getIsOptional()) tourPkgList = tourPkgBO.getTourFreeNEasyList(ProductConstant.TYPE_FNE);
			else tourPkgVO.setIdParent(null);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	/**
	 * @return the tourPkgList
	 */
	public List<TourPackageVO> getTourPkgList() {
		return tourPkgList;
	}

	/**
	 * @param tourPkgList the tourPkgList to set
	 */
	public void setTourPkgList(List<TourPackageVO> tourPkgList) {
		this.tourPkgList = tourPkgList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.web.bean.TourPackageBean#setTourPkgVO(com.bcs.zsg.product.vo.TourPackageVO)
	 */
	public void setTourPkgVO(TourPackageVO tourPkgVO2) {
		try {
			this.tourPkgVO = tourPkgVO2;
			tourDepVO = tourPkgBO.getTourDep(tourPkgVO.getId());

			List<TourPackageRoomPriceVO> roomPriceList = tourPkgBO.getTourPackageRoomPrice(tourPkgVO.getId());
			tourPkgVO.setTourPackageRoomPriceList(roomPriceList); 
			tourPkgVO.setAddEdit(false);
			
			roomPriceVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
			
			tourItineryVO = tourPkgBO.getTourItinery(tourDepVO.getId());
			if (tourItineryVO == null) tourItineryVO = new TourItineryVO();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	

	/**
	 * Handle add tour package selection
	 * @param vo
	 */
	public void handleAddTourPkgFNESelect(TourThemeVO vo) {
		resetFNEForm();
		handleAddTourPkgSelect(vo);
	}


	public void resetFNEForm() {
		tourPkgVO = new TourPackageVO();

	 	roomPriceVOList = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		tourPkgVO.setTourPackageRoomPriceList(new ArrayList<TourPackageRoomPriceVO>()); 
		 
	}


	public void addRoomPrice() {
		try { 
			if(!tourPkgVO.isAddEdit()) {
				roomPriceVO.setPkgId(tourPkgVO.getId()); 
				//customerBO.insertObject(remarksVO);
			} 
 
			tourPkgVO.getTourPackageRoomPriceList().add(roomPriceVO);   
			roomPriceVOList.getAddList().add(roomPriceVO);  
			resetRoomPriceForm();
			successResult();
			
		} catch (Throwable t) {
 
			errorResult(t);
		}
	}
	 

	/**
	 * Edit Remarks
	 */
	public void updateRoomPrice() {
		try {
			/*if(!customerVO.isAddEdit()) {
				customerBO.updateObject(remarksVO);
			}*/
		
			roomPriceVOList.getUpdList().add(roomPriceVO);
			
			resetRoomPriceForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	
	/**
	 * On Remarks selected
	 * @param event
	 */
	public void onRoomPriceSelected(TourPackageRoomPriceVO vo) {
		try {
			setRoomPriceAdd(false);
			setRoomPriceVO(vo);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	

	/**
	 * Delete Remarks
	 */
	public void deleteRoomPrice(TourPackageRoomPriceVO vo) {
		try {  
			onRoomPriceSelected(vo); 
			  
			tourPkgVO.getTourPackageRoomPriceList().remove(roomPriceVO); 
			roomPriceVOList.getDelList().add(roomPriceVO); 
			
			//resetRoomPriceForm();
			//successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	

	/**
	 * @return the remarksVO
	 */
	public TourPackageRoomPriceVO getRoomPriceVO() {
		return roomPriceVO;
	}

	/**
	 * @param remarksVO the remarksVO to set
	 */
	public void setRoomPriceVO(TourPackageRoomPriceVO roomPriceVO) {
		this.roomPriceVO = roomPriceVO;
	}

	/**
	 * @return the remarksAdd
	 */
	public boolean isRoomPriceAdd() {
		return roomPriceAdd;
	}

	/**
	 * Reset remarks form
	 * @throws BusinessException
	 */
	public void resetRoomPriceForm() throws BusinessException {
		setRoomPriceAdd(true);
		roomPriceVO = new TourPackageRoomPriceVO();
	}

	public void setRoomPriceAdd(boolean roomPriceAdd) {
		this.roomPriceAdd = roomPriceAdd; 
		try{
		loadRoomTypeList();  
		}catch (Throwable t) {
			errorResult(t);
		} 
	}

}
