package com.bcs.zsg.purchase.vo;

import java.util.Date;

import com.bcs.zsg.core.vo.BaseVO;

public class HotelTourVO  extends BaseVO{
	private static final long serialVersionUID = 1L;
	
	private String arrFlight;
	private String depFlight;
	private String mealProvide;
	private String typeOfRoom;
	private String hotelUsage;
	private String strChkInDt;
	private String strChkOutDt;
	private String noOfPax;
	private Date checkInDt;
	private Date checkOutDt;
	private Integer totalNight;
	private Long exOrderId;
	private Long idHotel;
	private String name;
	private String location;
	private String contact;
	private Integer seq;
	
	public String getArrFlight() {
		return arrFlight;
	}
	public void setArrFlight(String arrFlight) {
		this.arrFlight = arrFlight;
	}
	public String getDepFlight() {
		return depFlight;
	}
	public void setDepFlight(String depFlight) {
		this.depFlight = depFlight;
	}
	public String getMealProvide() {
		return mealProvide;
	}
	public void setMealProvide(String mealProvide) {
		this.mealProvide = mealProvide;
	}
	public String getTypeOfRoom() {
		return typeOfRoom;
	}
	public void setTypeOfRoom(String typeOfRoom) {
		this.typeOfRoom = typeOfRoom;
	}
	public Date getCheckInDt() {
		return checkInDt;
	}
	public void setCheckInDt(Date checkInDt) {
		this.checkInDt = checkInDt;
	}
	public Date getCheckOutDt() {
		return checkOutDt;
	}
	public void setCheckOutDt(Date checkOutDt) {
		this.checkOutDt = checkOutDt;
	}
	public Integer getTotalNight() {
		return totalNight;
	}
	public void setTotalNight(Integer totalNight) {
		this.totalNight = totalNight;
	}
	public String getNoOfPax() {
		return noOfPax;
	}
	public void setNoOfPax(String noOfPax) {
		this.noOfPax = noOfPax;
	}
	public String getHotelUsage() {
		return hotelUsage;
	}
	public void setHotelUsage(String hotelUsage) {
		this.hotelUsage = hotelUsage;
	}
	public String getStrChkInDt() {
		return strChkInDt;
	}
	public void setStrChkInDt(String strChkInDt) {
		this.strChkInDt = strChkInDt;
	}
	public String getStrChkOutDt() {
		return strChkOutDt;
	}
	public void setStrChkOutDt(String strChkOutDt) {
		this.strChkOutDt = strChkOutDt;
	}
	public Long getExOrderId() {
		return exOrderId;
	}
	public void setExOrderId(Long exOrderId) {
		this.exOrderId = exOrderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Long getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}
