package com.bcs.zsg.product.vo;

import java.util.List;

import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.core.vo.BaseVO;

public class RoomingListLandOperatorVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	private Long idTourDep;
	private String salutation;
	private String lastName;
	private String givenName;
	private String contactNo;
	private RoomingListLandOperatorContVO roomingListLandOprContVO;
	private List<RoomingListLandOperatorContVO> roomingListLandOprContList;
	private GenAddUpdDelVO<RoomingListLandOperatorContVO> landOperatorContAUDList;
	
	public Long getIdTourDep() {
		return idTourDep;
	}
	public void setIdTourDep(Long idTourDep) {
		this.idTourDep = idTourDep;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public List<RoomingListLandOperatorContVO> getRoomingListLandOprContList() {
		return roomingListLandOprContList;
	}
	public void setRoomingListLandOprContList(List<RoomingListLandOperatorContVO> roomingListLandOprContList) {
		this.roomingListLandOprContList = roomingListLandOprContList;
	}
	public GenAddUpdDelVO<RoomingListLandOperatorContVO> getLandOperatorContAUDList() {
		return landOperatorContAUDList;
	}
	public void setLandOperatorContAUDList(GenAddUpdDelVO<RoomingListLandOperatorContVO> landOperatorContAUDList) {
		this.landOperatorContAUDList = landOperatorContAUDList;
	}
	public RoomingListLandOperatorContVO getRoomingListLandOprContVO() {
		return roomingListLandOprContVO;
	}
	public void setRoomingListLandOprContVO(RoomingListLandOperatorContVO roomingListLandOprContVO) {
		this.roomingListLandOprContVO = roomingListLandOprContVO;
	}
}
