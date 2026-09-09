package com.bcs.zsg.product.vo;

import java.util.Date;
import java.util.List;

import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourPackageVO extends BaseVO {

	private boolean addEdit;
	private static final long serialVersionUID = 1L;
	private Long idTourTheme;
	private Long idCountry;
	private Long idTourCat;
	private Long idCruise;
	// private Long idRegion;
	// private Long idCountry;
	private Long idParent;
	private String typeCd;
	private String code;
	private Integer numDays = 0;
	private Integer numNights = 0;
	private String nameEn;
	private String nameZh;
	private String nameOther;
	private String nameOtherZh;
	private String catDesc;
	private String parentThemeDesc;
	private String themeDesc;
	private Boolean isMuslimPkg = false;
	private Boolean isThemeTour = false;
	private Boolean isTopPkg = false;
	private Boolean isOptional = false;
	private Boolean isNew = false;
	private Boolean isPromo = false;
	private Boolean isPushToOnline = true;
	private Boolean isAllowBooking = true;
	private Boolean isVisaRequired = false;
	private String seasonCd;
	private String year;
	private String langCode;
	private Double deposit = 0.0;
	private Double bagDeduction = 0.0;
	private Double tfairDiscount = 0.0;
	private Double priceDiffSgl = 0.0;
	private Double priceDiffCtw = 0.0;
	private Double priceDiffCwb = 0.0;
	private Double priceDiffCnb = 0.0;
	private Double grndSgl = 0.0;
	private Double grndCtw = 0.0;
	private Double grndCwb = 0.0;
	private Double grndCnb = 0.0;
	private Double cnaAdt = 0.0;
	private Double cpaAdt = 0.0;
	private Double csiAdt = 0.0;
	private Double priceFrom = 0.0;
	private Double priceTo = 0.0;
	private Long idSalesCommConf;
	private Integer deadline = 30;
	private String highLight;
	private String highLightZh;
	private Date dtBookStart;
	private Date dtBookEnd;
	private Date dtTravelStart;
	private Date dtTravelEnd;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reason;
	private String regions;
	private String countries;
	private int seq;
	private String fullRemarks;
	private String fullRemarksZh;
	private String grndRemarks;
	private String grndRemarksZh;
	private String opPic;
	private String opPicName;
	private Integer tourPkgDailyItineraryCount = 0;
	private List<String> opPicList;
	private String groupCd;
	private List<String> tourBadgeTagList;
	private List<String> travelStyleList;
	private List<String> tourBadgeTagZhList;
	private List<String> travelStyleZhList;
	private String[] tourBadgeDescList;
	private String[] travelStyleDescList;

	private TourDepartureVO tourDepVO;
	private TourPackageAttributeVO tourPkgAttributeVO;
	private TourPackageCountryVO tourPkgCountryVO;
	private List<TourPackageRemarksVO> tourPackageRemarksList;
	private List<TourPackageRoomPriceVO> tourPackageRoomPriceList;
	private List<RegionVO> regionList;
	private List<CountryVO> countryList;
	private List<TourDepartureVO> tourDepList;
	private List<TourPackageItineryVO> tourPkgItineryVOList;
	private List<TourPackageDailyItineraryVO> tourPkgDailyItineraryVOList;
	private List<TourPackageCommisionVO> tourPkgCommisionVOList;
	private List<TourPackageTagVO> tourPackageTagVOList;
	private List<TourImageVO> tourImageVOList;

	private GenAddUpdDelVO<TourImageVO> tourImageAUDList;
	private GenAddUpdDelVO<TourPackageItineryVO> tourPkgItineryAUDList;
	private GenAddUpdDelVO<TourPackageDailyItineraryVO> tourPkgDailyItineraryAUDList;
	private GenAddUpdDelVO<TourPackageCommisionVO> tourPkgCommisionAUDList;

	private List<TourPackageTagVO> delTourPackageTagVOList;
	private Boolean isDiscountPercent = false;
	private Boolean isDepositPercent = false;
	private Boolean isCommisionPercent = false;

	private Double cnaValue = 0.0;
	private Double cpaValue = 0.0;
	private Double csiValue = 0.0;
	
	// campaign
	private String campaignPkgDesc;

	@Expose
	@SerializedName("tourCarouselList")
	private List<TourPhotoVO> tourCarouselVOList;
	@Expose
	@SerializedName("tourHighlightList")
	private List<TourPhotoVO> tourHighlightVOList;
	@Expose
	@SerializedName("tourPkgItinery")
	private TourPackageItineryVO tourPkgItineryVO;
	@Expose
	@SerializedName("tourPkgDailyItinerary")
	private TourPackageDailyItineraryVO tourPkgDailyItineraryVO;

	public Long getIdCruise() {
		return idCruise;
	}

	public void setIdCruise(Long idCruise) {
		this.idCruise = idCruise;
	}

	public List<TourPhotoVO> getTourCarouselVOList() {
		return tourCarouselVOList;
	}

	public void setTourCarouselVOList(List<TourPhotoVO> tourCarouselVOList) {
		this.tourCarouselVOList = tourCarouselVOList;
	}

	public List<TourPhotoVO> getTourHighlightVOList() {
		return tourHighlightVOList;
	}

	public void setTourHighlightVOList(List<TourPhotoVO> tourHighlightVOList) {
		this.tourHighlightVOList = tourHighlightVOList;
	}

	public String getCampaignPkgDesc() {
		return campaignPkgDesc;
	}

	public void setCampaignPkgDesc(String campaignPkgDesc) {
		this.campaignPkgDesc = campaignPkgDesc;
	}

	public TourPackageItineryVO getTourPkgItineryVO() {
		return tourPkgItineryVO;
	}

	public void setTourPkgItineryVO(TourPackageItineryVO tourPkgItineryVO) {
		this.tourPkgItineryVO = tourPkgItineryVO;
	}

	public TourPackageDailyItineraryVO getTourPkgDailyItineraryVO() {
		return tourPkgDailyItineraryVO;
	}

	public void setTourPkgDailyItineraryVO(TourPackageDailyItineraryVO tourPkgDailyItineraryVO) {
		this.tourPkgDailyItineraryVO = tourPkgDailyItineraryVO;
	}

	public Double getCnaValue() {
		return cnaValue;
	}

	public void setCnaValue(Double cnaValue) {
		this.cnaValue = cnaValue;
	}

	public Double getCpaValue() {
		return cpaValue;
	}

	public void setCpaValue(Double cpaValue) {
		this.cpaValue = cpaValue;
	}

	public Double getCsiValue() {
		return csiValue;
	}

	public void setCsiValue(Double csiValue) {
		this.csiValue = csiValue;
	}

	public Boolean getIsCommisionPercent() {
		return isCommisionPercent;
	}

	public void setIsCommisionPercent(Boolean isCommisionPercent) {
		this.isCommisionPercent = isCommisionPercent;
	}

	public Boolean getIsDepositPercent() {
		return isDepositPercent;
	}

	public void setIsDepositPercent(Boolean isDepositPercent) {
		this.isDepositPercent = isDepositPercent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getIsDiscountPercent() {
		return isDiscountPercent;
	}

	public void setIsDiscountPercent(Boolean isDiscountPercent) {
		this.isDiscountPercent = isDiscountPercent;
	}

	/**
	 * @return the idTourTheme
	 */
	public Long getIdTourTheme() {
		return idTourTheme;
	}

	/**
	 * @param idTourTheme the idTourTheme to set
	 */
	public void setIdTourTheme(Long idTourTheme) {
		this.idTourTheme = idTourTheme;
	}

	/**
	 * @return the idTourCat
	 */
	public Long getIdTourCat() {
		return idTourCat;
	}

	/**
	 * @param idTourCat the idTourCat to set
	 */
	public void setIdTourCat(Long idTourCat) {
		this.idTourCat = idTourCat;
	}

	/**
	 * @return the idParent
	 */
	public Long getIdParent() {
		return idParent;
	}

	/**
	 * @param idParent the idParent to set
	 */
	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

	/**
	 * @return the typeCd
	 */
	public String getTypeCd() {
		return typeCd;
	}

	/**
	 * @param typeCd the typeCd to set
	 */
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	/**
	 * @return the numDays
	 */
	public Integer getNumDays() {
		return numDays;
	}

	/**
	 * @param numDays the numDays to set
	 */
	public void setNumDays(Integer numDays) {
		this.numDays = numDays;
	}

	/**
	 * @return the numNights
	 */
	public Integer getNumNights() {
		return numNights;
	}

	/**
	 * @param numNights the numNights to set
	 */
	public void setNumNights(Integer numNights) {
		this.numNights = numNights;
	}

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}

	/**
	 * @param nameZh the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	/**
	 * @return the nameOther
	 */
	public String getNameOther() {
		return nameOther;
	}

	/**
	 * @param nameOther the nameOther to set
	 */
	public void setNameOther(String nameOther) {
		this.nameOther = nameOther;
	}

	/**
	 * @return the isMuslimPkg
	 */
	public Boolean getIsMuslimPkg() {
		return isMuslimPkg;
	}

	/**
	 * @param isMuslimPkg the isMuslimPkg to set
	 */
	public void setIsMuslimPkg(Boolean isMuslimPkg) {
		this.isMuslimPkg = isMuslimPkg;
	}

	/**
	 * @return the isThemeTour
	 */
	public Boolean getIsThemeTour() {
		return isThemeTour;
	}

	/**
	 * @param isThemeTour the isThemeTour to set
	 */
	public void setIsThemeTour(Boolean isThemeTour) {
		this.isThemeTour = isThemeTour;
	}

	/**
	 * @return the isOptional
	 */
	public Boolean getIsOptional() {
		return isOptional;
	}

	/**
	 * @param isOptional the isOptional to set
	 */
	public void setIsOptional(Boolean isOptional) {
		this.isOptional = isOptional;
	}

	/**
	 * @return the isNew
	 */
	public Boolean getIsNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * @return the isPromo
	 */
	public Boolean getIsPromo() {
		return isPromo;
	}

	/**
	 * @param isPromo the isPromo to set
	 */
	public void setIsPromo(Boolean isPromo) {
		this.isPromo = isPromo;
	}

	/**
	 * @return the seasonCd
	 */
	public String getSeasonCd() {
		return seasonCd;
	}

	/**
	 * @param seasonCd the seasonCd to set
	 */
	public void setSeasonCd(String seasonCd) {
		this.seasonCd = seasonCd;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}

	/**
	 * @param langCode the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	/**
	 * @return the deposit
	 */
	public Double getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	/**
	 * @return the bagDeduction
	 */
	public Double getBagDeduction() {
		return bagDeduction;
	}

	/**
	 * @param bagDeduction the bagDeduction to set
	 */
	public void setBagDeduction(Double bagDeduction) {
		this.bagDeduction = bagDeduction;
	}

	/**
	 * @return the tfairDiscount
	 */
	public Double getTfairDiscount() {
		return tfairDiscount;
	}

	/**
	 * @param tfairDiscount the tfairDiscount to set
	 */
	public void setTfairDiscount(Double tfairDiscount) {
		this.tfairDiscount = tfairDiscount;
	}

	/**
	 * @return the priceDiffSgl
	 */
	public Double getPriceDiffSgl() {
		return priceDiffSgl;
	}

	/**
	 * @param priceDiffSgl the priceDiffSgl to set
	 */
	public void setPriceDiffSgl(Double priceDiffSgl) {
		this.priceDiffSgl = priceDiffSgl;
	}

	/**
	 * @return the priceDiffCtw
	 */
	public Double getPriceDiffCtw() {
		return priceDiffCtw;
	}

	/**
	 * @param priceDiffCtw the priceDiffCtw to set
	 */
	public void setPriceDiffCtw(Double priceDiffCtw) {
		this.priceDiffCtw = priceDiffCtw;
	}

	/**
	 * @return the priceDiffCwb
	 */
	public Double getPriceDiffCwb() {
		return priceDiffCwb;
	}

	/**
	 * @param priceDiffCwb the priceDiffCwb to set
	 */
	public void setPriceDiffCwb(Double priceDiffCwb) {
		this.priceDiffCwb = priceDiffCwb;
	}

	/**
	 * @return the priceDiffCnb
	 */
	public Double getPriceDiffCnb() {
		return priceDiffCnb;
	}

	/**
	 * @param priceDiffCnb the priceDiffCnb to set
	 */
	public void setPriceDiffCnb(Double priceDiffCnb) {
		this.priceDiffCnb = priceDiffCnb;
	}

	/**
	 * @return the grndSgl
	 */
	public Double getGrndSgl() {
		return grndSgl;
	}

	/**
	 * @param grndSgl the grndSgl to set
	 */
	public void setGrndSgl(Double grndSgl) {
		this.grndSgl = grndSgl;
	}

	/**
	 * @return the grndCtw
	 */
	public Double getGrndCtw() {
		return grndCtw;
	}

	/**
	 * @param grndCtw the grndCtw to set
	 */
	public void setGrndCtw(Double grndCtw) {
		this.grndCtw = grndCtw;
	}

	/**
	 * @return the grndCwb
	 */
	public Double getGrndCwb() {
		return grndCwb;
	}

	/**
	 * @param grndCwb the grndCwb to set
	 */
	public void setGrndCwb(Double grndCwb) {
		this.grndCwb = grndCwb;
	}

	/**
	 * @return the grndCnb
	 */
	public Double getGrndCnb() {
		return grndCnb;
	}

	/**
	 * @param grndCnb the grndCnb to set
	 */
	public void setGrndCnb(Double grndCnb) {
		this.grndCnb = grndCnb;
	}

	/**
	 * @return the cnaAdt
	 */
	public Double getCnaAdt() {
		return cnaAdt;
	}

	/**
	 * @param cnaAdt the cnaAdt to set
	 */
	public void setCnaAdt(Double cnaAdt) {
		this.cnaAdt = cnaAdt;
	}

	/**
	 * @return the cpaAdt
	 */
	public Double getCpaAdt() {
		return cpaAdt;
	}

	/**
	 * @param cpaAdt the cpaAdt to set
	 */
	public void setCpaAdt(Double cpaAdt) {
		this.cpaAdt = cpaAdt;
	}

	/**
	 * @return the csiAdt
	 */
	public Double getCsiAdt() {
		return csiAdt;
	}

	/**
	 * @param csiAdt the csiAdt to set
	 */
	public void setCsiAdt(Double csiAdt) {
		this.csiAdt = csiAdt;
	}

	/**
	 * @return the priceFrom
	 */
	public Double getPriceFrom() {
		return priceFrom;
	}

	/**
	 * @param priceFrom the priceFrom to set
	 */
	public void setPriceFrom(Double priceFrom) {
		this.priceFrom = priceFrom;
	}

	/**
	 * @return the priceTo
	 */
	public Double getPriceTo() {
		return priceTo;
	}

	/**
	 * @param priceFrom the priceFrom to set
	 */
	public void setPriceTo(Double priceTo) {
		this.priceTo = priceTo;
	}

	/**
	 * @return the highLight
	 */
	public String getHighLight() {
		return highLight;
	}

	/**
	 * @param highLight the highLight to set
	 */
	public void setHighLight(String highLight) {
		this.highLight = highLight;
	}

	/**
	 * @return the dtBookStart
	 */
	public Date getDtBookStart() {
		return dtBookStart;
	}

	/**
	 * @param dtBookStart the dtBookStart to set
	 */
	public void setDtBookStart(Date dtBookStart) {
		this.dtBookStart = dtBookStart;
	}

	/**
	 * @return the dtBookEnd
	 */
	public Date getDtBookEnd() {
		return dtBookEnd;
	}

	/**
	 * @param dtBookEnd the dtBookEnd to set
	 */
	public void setDtBookEnd(Date dtBookEnd) {
		this.dtBookEnd = dtBookEnd;
	}

	/**
	 * @return the dtTravelStart
	 */
	public Date getDtTravelStart() {
		return dtTravelStart;
	}

	/**
	 * @param dtTravelStart the dtTravelStart to set
	 */
	public void setDtTravelStart(Date dtTravelStart) {
		this.dtTravelStart = dtTravelStart;
	}

	/**
	 * @return the dtTravelEnd
	 */
	public Date getDtTravelEnd() {
		return dtTravelEnd;
	}

	/**
	 * @param dtTravelEnd the dtTravelEnd to set
	 */
	public void setDtTravelEnd(Date dtTravelEnd) {
		this.dtTravelEnd = dtTravelEnd;
	}

	/**
	 * @return the reserved1
	 */
	public String getReserved1() {
		return reserved1;
	}

	/**
	 * @param reserved1 the reserved1 to set
	 */
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	/**
	 * @return the reserved2
	 */
	public String getReserved2() {
		return reserved2;
	}

	/**
	 * @param reserved2 the reserved2 to set
	 */
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	/**
	 * @return the reserved3
	 */
	public String getReserved3() {
		return reserved3;
	}

	/**
	 * @param reserved3 the reserved3 to set
	 */
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the tourDepVO
	 */
	public TourDepartureVO getTourDepVO() {
		return tourDepVO;
	}

	/**
	 * @param tourDepVO the tourDepVO to set
	 */
	public void setTourDepVO(TourDepartureVO tourDepVO) {
		this.tourDepVO = tourDepVO;
	}

	/**
	 * @param tourPackageRemarksList the tourPackageRemarksList to set
	 */
	public void setTourPackageRemarksList(List<TourPackageRemarksVO> tourPackageRemarksList) {
		this.tourPackageRemarksList = tourPackageRemarksList;
	}

	/**
	 * @return the tourPackageRemarksList
	 */
	public List<TourPackageRemarksVO> getTourPackageRemarksList() {

		return tourPackageRemarksList;
	}

	/**
	 * @param tourPackageRemarksList the tourPackageRemarksList to set
	 */
	public void setTourPackageRoomPriceList(List<TourPackageRoomPriceVO> tourPackageRoomPriceList) {
		this.tourPackageRoomPriceList = tourPackageRoomPriceList;
	}

	/**
	 * @return the tourPackageRemarksList
	 */
	public List<TourPackageRoomPriceVO> getTourPackageRoomPriceList() {

		return tourPackageRoomPriceList;
	}

	/**
	 * @return the addEdit
	 */
	public boolean isAddEdit() {
		return addEdit;
	}

	/**
	 * @param addEdit the addEdit to set
	 */
	public void setAddEdit(boolean addEdit) {
		this.addEdit = addEdit;
	}

	/**
	 * @return the regions
	 */
	public String getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(String regions) {
		this.regions = regions;
	}

	/**
	 * @return the countries
	 */
	public String getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(String countries) {
		this.countries = countries;
	}

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @return the regionList
	 */
	public List<RegionVO> getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(List<RegionVO> regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the countryList
	 */
	public List<CountryVO> getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(List<CountryVO> countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the tourDepList
	 */
	public List<TourDepartureVO> getTourDepList() {
		return tourDepList;
	}

	/**
	 * @param tourDepList the tourDepList to set
	 */
	public void setTourDepList(List<TourDepartureVO> tourDepList) {
		this.tourDepList = tourDepList;
	}

	public Boolean getIsTopPkg() {
		return isTopPkg;
	}

	public void setIsTopPkg(Boolean isTopPkg) {
		this.isTopPkg = isTopPkg;
	}

	public Boolean getIsPushToOnline() {
		return isPushToOnline;
	}

	public void setIsPushToOnline(Boolean isPushToOnline) {
		this.isPushToOnline = isPushToOnline;
	}

	public Boolean getIsAllowBooking() {
		return isAllowBooking;
	}

	public void setIsAllowBooking(Boolean isAllowBooking) {
		this.isAllowBooking = isAllowBooking;
	}

	public List<TourPackageItineryVO> getTourPkgItineryVOList() {
		return tourPkgItineryVOList;
	}

	public void setTourPkgItineryVOList(List<TourPackageItineryVO> tourPkgItineryVOList) {
		this.tourPkgItineryVOList = tourPkgItineryVOList;
	}

	public GenAddUpdDelVO<TourPackageItineryVO> getTourPkgItineryAUDList() {
		return tourPkgItineryAUDList;
	}

	public void setTourPkgItineryAUDList(GenAddUpdDelVO<TourPackageItineryVO> tourPkgItineryAUDList) {
		this.tourPkgItineryAUDList = tourPkgItineryAUDList;
	}

	public String getFullRemarks() {
		return fullRemarks;
	}

	public void setFullRemarks(String fullRemarks) {
		this.fullRemarks = fullRemarks;
	}

	public String getGrndRemarks() {
		return grndRemarks;
	}

	public void setGrndRemarks(String grndRemarks) {
		this.grndRemarks = grndRemarks;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TourPackageDailyItineraryVO> getTourPkgDailyItineraryVOList() {
		return tourPkgDailyItineraryVOList;
	}

	public void setTourPkgDailyItineraryVOList(List<TourPackageDailyItineraryVO> tourPkgDailyItineraryVOList) {
		this.tourPkgDailyItineraryVOList = tourPkgDailyItineraryVOList;
	}

	public GenAddUpdDelVO<TourPackageDailyItineraryVO> getTourPkgDailyItineraryAUDList() {
		return tourPkgDailyItineraryAUDList;
	}

	public void setTourPkgDailyItineraryAUDList(
			GenAddUpdDelVO<TourPackageDailyItineraryVO> tourPkgDailyItineraryAUDList) {
		this.tourPkgDailyItineraryAUDList = tourPkgDailyItineraryAUDList;
	}

	public TourPackageAttributeVO getTourPkgAttributeVO() {
		return tourPkgAttributeVO;
	}

	public void setTourPkgAttributeVO(TourPackageAttributeVO tourPkgAttributeVO) {
		this.tourPkgAttributeVO = tourPkgAttributeVO;
	}

	public List<String> getTourBadgeTagList() {
		return tourBadgeTagList;
	}

	public void setTourBadgeTagList(List<String> tourBadgeTagList) {
		this.tourBadgeTagList = tourBadgeTagList;
	}

	public List<String> getTravelStyleList() {
		return travelStyleList;
	}

	public void setTravelStyleList(List<String> travelStyleList) {
		this.travelStyleList = travelStyleList;
	}

	public List<TourPackageCommisionVO> getTourPkgCommisionVOList() {
		return tourPkgCommisionVOList;
	}

	public String[] getTourBadgeDescList() {
		return tourBadgeDescList;
	}

	public void setTourBadgeDescList(String[] tourBadgeDescList) {
		this.tourBadgeDescList = tourBadgeDescList;
	}

	public String[] getTravelStyleDescList() {
		return travelStyleDescList;
	}

	public void setTravelStyleDescList(String[] travelStyleDescList) {
		this.travelStyleDescList = travelStyleDescList;
	}

	public void setTourPkgCommisionVOList(List<TourPackageCommisionVO> tourPkgCommisionVOList) {
		this.tourPkgCommisionVOList = tourPkgCommisionVOList;
	}

	public GenAddUpdDelVO<TourPackageCommisionVO> getTourPkgCommisionAUDList() {
		return tourPkgCommisionAUDList;
	}

	public void setTourPkgCommisionAUDList(GenAddUpdDelVO<TourPackageCommisionVO> tourPkgCommisionAUDList) {
		this.tourPkgCommisionAUDList = tourPkgCommisionAUDList;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public String getNameOtherZh() {
		return nameOtherZh;
	}

	public void setNameOtherZh(String nameOtherZh) {
		this.nameOtherZh = nameOtherZh;
	}

	public List<String> getTourBadgeTagZhList() {
		return tourBadgeTagZhList;
	}

	public void setTourBadgeTagZhList(List<String> tourBadgeTagZhList) {
		this.tourBadgeTagZhList = tourBadgeTagZhList;
	}

	public List<String> getTravelStyleZhList() {
		return travelStyleZhList;
	}

	public void setTravelStyleZhList(List<String> travelStyleZhList) {
		this.travelStyleZhList = travelStyleZhList;
	}

	public String getHighLightZh() {
		return highLightZh;
	}

	public void setHighLightZh(String highLightZh) {
		this.highLightZh = highLightZh;
	}

	public String getFullRemarksZh() {
		return fullRemarksZh;
	}

	public void setFullRemarksZh(String fullRemarksZh) {
		this.fullRemarksZh = fullRemarksZh;
	}

	public String getGrndRemarksZh() {
		return grndRemarksZh;
	}

	public void setGrndRemarksZh(String grndRemarksZh) {
		this.grndRemarksZh = grndRemarksZh;
	}

	public TourPackageCountryVO getTourPkgCountryVO() {
		return tourPkgCountryVO;
	}

	public void setTourPkgCountryVO(TourPackageCountryVO tourPkgCountryVO) {
		this.tourPkgCountryVO = tourPkgCountryVO;
	}

	public String getGroupCd() {
		return groupCd;
	}

	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}

	public List<TourPackageTagVO> getTourPackageTagVOList() {
		return tourPackageTagVOList;
	}

	public void setTourPackageTagVOList(List<TourPackageTagVO> tourPackageTagVOList) {
		this.tourPackageTagVOList = tourPackageTagVOList;
	}

	public List<TourPackageTagVO> getDelTourPackageTagVOList() {
		return delTourPackageTagVOList;
	}

	public void setDelTourPackageTagVOList(List<TourPackageTagVO> delTourPackageTagVOList) {
		this.delTourPackageTagVOList = delTourPackageTagVOList;
	}

	public GenAddUpdDelVO<TourImageVO> getTourImageAUDList() {
		return tourImageAUDList;
	}

	public void setTourImageAUDList(GenAddUpdDelVO<TourImageVO> tourImageAUDList) {
		this.tourImageAUDList = tourImageAUDList;
	}

	public List<TourImageVO> getTourImageVOList() {
		return tourImageVOList;
	}

	public void setTourImageVOList(List<TourImageVO> tourImageVOList) {
		this.tourImageVOList = tourImageVOList;
	}

	public Boolean getIsVisaRequired() {
		return isVisaRequired;
	}

	public void setIsVisaRequired(Boolean isVisaRequired) {
		this.isVisaRequired = isVisaRequired;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public Long getIdSalesCommConf() {
		return idSalesCommConf;
	}

	public void setIdSalesCommConf(Long idSalesCommConf) {
		this.idSalesCommConf = idSalesCommConf;
	}

	public String getOpPic() {
		return opPic;
	}

	public void setOpPic(String opPic) {
		this.opPic = opPic;
	}

	public String getOpPicName() {
		return opPicName;
	}

	public void setOpPicName(String opPicName) {
		this.opPicName = opPicName;
	}

	public List<String> getOpPicList() {
		return opPicList;
	}

	public void setOpPicList(List<String> opPicList) {
		this.opPicList = opPicList;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getParentThemeDesc() {
		return parentThemeDesc;
	}

	public void setParentThemeDesc(String parentThemeDesc) {
		this.parentThemeDesc = parentThemeDesc;
	}

	public String getThemeDesc() {
		return themeDesc;
	}

	public void setThemeDesc(String themeDesc) {
		this.themeDesc = themeDesc;
	}

	public Integer getTourPkgDailyItineraryCount() {
		return tourPkgDailyItineraryCount;
	}

	public void setTourPkgDailyItineraryCount(Integer tourPkgDailyItineraryCount) {
		this.tourPkgDailyItineraryCount = tourPkgDailyItineraryCount;
	}
}
