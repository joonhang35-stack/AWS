package com.bcs.zsg.product.web.bean;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.cfg.sec.vo.EmployeeViewVO;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.component.GenUpdateVOList;
import com.bcs.zsg.component.helper.ConstantScreenAction;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.maintenance.bo.AccountCodeBO;
import com.bcs.zsg.maintenance.bo.CorporateProfileBO;
import com.bcs.zsg.maintenance.bo.LookUpBO;
import com.bcs.zsg.maintenance.bo.RegionBO;
import com.bcs.zsg.maintenance.vo.CompanyContactVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.product.bo.AirlineBO;
import com.bcs.zsg.product.bo.HotelBO;
import com.bcs.zsg.product.bo.RoomingListBO;
import com.bcs.zsg.product.bo.RoomingListLandOperatorBO;
import com.bcs.zsg.product.bo.TourPackageBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.AirlineScheduleItemVO;
import com.bcs.zsg.product.vo.AirlineScheduleVO;
import com.bcs.zsg.product.vo.HotelAddressVO;
import com.bcs.zsg.product.vo.HotelContactVO;
import com.bcs.zsg.product.vo.HotelVO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorContVO;
import com.bcs.zsg.product.vo.RoomingListLandOperatorVO;
import com.bcs.zsg.product.vo.RoomingListVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourHotelVO;
import com.bcs.zsg.product.vo.TourPackageVO;
import com.bcs.zsg.purchase.bo.BillPymtBO;
import com.bcs.zsg.purchase.bo.PurchaseBO;
import com.bcs.zsg.purchase.bo.PurchaseEOBO;
import com.bcs.zsg.purchase.vo.AddressVO;
import com.bcs.zsg.purchase.vo.AirLineDetailsVO;
import com.bcs.zsg.purchase.vo.CorAddressVO;
import com.bcs.zsg.purchase.vo.CorContactVO;
import com.bcs.zsg.purchase.vo.CorporateVO;
import com.bcs.zsg.purchase.vo.CountryVO;
import com.bcs.zsg.purchase.vo.ExOrderVO;
import com.bcs.zsg.purchase.vo.HotelTourVO;
import com.bcs.zsg.purchase.vo.IdentityVO;
import com.bcs.zsg.purchase.vo.PersonVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.sales.bo.CustomerBO;
import com.bcs.zsg.sales.bo.InvoiceBO;
import com.bcs.zsg.sales.vo.CustomerVO;
import com.bcs.zsg.sales.vo.IdentityDetailVO;
import com.bcs.zsg.sales.vo.InvoicePaxVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.PersonContactVO;
import com.bcs.zsg.sales.vo.PersonLangVO;
import com.bcs.zsg.sales.vo.PersonMealVO;
import com.mchange.v2.ser.SerializableUtils;

public class RoomingListBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	@Autowired
	protected transient AccountCodeBO accountCodeBO;
	@Autowired
	protected transient TourPackageBO tourPkgBO;
	@Autowired
	protected transient RegionBO regionBO;
	@Autowired
	protected transient InvoiceBO invoiceBO;
	@Autowired
	protected transient PurchaseBO purchaseBO;
	@Autowired
	protected transient RoomingListBO roomingListBO;
	@Autowired
	protected transient BillPymtBO billPymtBO;
	@Autowired
	protected transient CorporateProfileBO corporateProfileBO;
	@Autowired
	protected transient AirlineBO airlineBO;
	@Autowired
	protected transient HotelBO hotelBO;
	@Autowired
	private transient CustomerBO customerBO;
	@Autowired
	private transient LookUpBO lookUpBO;
	@Autowired
	private transient UserBO userBO;
	@Autowired
	private transient PurchaseEOBO purchaseEOBO;
	@Autowired
	private transient RoomingListLandOperatorBO roomingListLandOperatorBO;

	private LazyDataModel<TourDepartureVO> lazyDMTourDepWithInvoicePaxVO;

	private List<SupplierVO> supplierList;
	private List<EmployeeViewVO> employeeList;
	private List<InvoicePaxVO> invoicePaxInvIdList;
	private List<RoomingListVO> PICList;
	private List<RoomingListVO> tourLeaderList;
	private List<RoomingListVO> tourManagerList;
	private List<RoomingListVO> idHotelList;
	private List<TourDepartureVO> tourDepVOList;
	private List<CompanyContactVO> companyContList;
	private List<TourHotelVO> tourHotelList;
	private List<HotelAddressVO> hotelAddrList;
	private List<HotelContactVO> hotelContList;
	private List<PersonContactVO> personContList;
	private List<PersonContactVO> personContPList;
	private List<InvoiceVO> invoiceList;
	private List<PersonLangVO> personLangList;
	private List<PersonMealVO> personMealList;
	private List<LookupItemVO> lookupItemrmList;
	private List<ExOrderVO> exOrderVOList;
	private List<AirLineDetailsVO> airLineDetailsVOList;
	private List<HotelTourVO> hotelTourVOList;
	private List<HotelVO> hotelList;
	private List<RoomingListLandOperatorVO> roomingListLandOprVOList;
	private List<CorContactVO> corContactVOList;

	private TourDepartureVO tourDepVO;
	private TourDepartureVO selectedTourDepVO;
	private TourPackageVO tourPkgVO;
	private CountryVO countryVO;
	private RoomingListVO RLtourGuideVO;
	private RoomingListVO RLsupplierVO;
	private RoomingListVO RLVO;
	private RoomingListVO RLAirlineVO;
	private RoomingListVO RLHotelVO;
	private RoomingListVO RLLandOperatorVO;
	private SupplierVO supplierVO;
	private EmployeeViewVO employeeViewVO;
	private AddressVO addressVO;
	private CorporateVO corporateVO;
	private CorAddressVO corpAddrVO;
	private CustomerVO custVO;
	private PersonVO personVO;
	private TourHotelVO tourHotelVO;
	private HotelVO hotelVO;
	private HotelTourVO hotelTourVO;
	private RoomingListLandOperatorVO roomingListLandOperatorVO;
	private RoomingListLandOperatorContVO roomingListLandOperatorContVO;

	protected AcctCatVO expenditureVO;
	protected AcctCatVO assetVO;
	private List<InvoicePaxVO> invoicePaxList;
	private List<LookupItemVO> roomTypeList;
	private List<LookupItemVO> uniqueRemarksList;

	private String tourCode;
	private String typeCd;
	private String exorderType;
	private boolean isAddLandOperator;
	private boolean isAddLandOperatorCont;
	private final int excelPrintColumnCount = 25;
	
	protected AcctTransVO acctTransViewVO;

	protected AddUpdDelVO invoicePaxAUDVO;
	protected AddUpdDelVO roomingListAUDVO;
	private GenAddUpdDelVO<RoomingListLandOperatorVO> landOprAUDList;

	private TrackingLogUtils trackingLogUtils;

	@Override
	public void resetForm() {
		invoicePaxAUDVO = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(), new ArrayList<Object>());
		invoicePaxList = new ArrayList<InvoicePaxVO>();
		tourHotelVO = new TourHotelVO();
	}

	public void refreshList() {
		try {
			RLtourGuideVO = new RoomingListVO();
			RLsupplierVO = new RoomingListVO();

			supplierList = purchaseBO.getSupplierList(getSessionInfoBean().getCompanyVO().getId(), true);
			employeeList = userBO.getEmployeeViewList(this.getSessionInfoBean().getCompanyVO().getId());

			lazyDMTourDepWithInvoicePaxVO = new LazyRoomingListDataModel();
			// tourDepVOList = tourPkgBO.getTourDepListWithInvoicePax(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws BusinessException {
		try {
			resetForm();
			/* hotelList = hotelBO.getHotelList(); */
			trackingLogUtils = new TrackingLogUtils(this.getClass());
			refreshList();

			tourCode = "";
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void loadExOrderList(String exorderType) {
		try {
			this.exorderType = exorderType;
			exOrderVOList = purchaseEOBO.getEOExOrderList(getSessionInfoBean().getCompanyVO().getId());
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void loadHotelList(HotelTourVO vo) {
		try {
			hotelTourVO = vo;
			hotelList = hotelBO.getHotelList();
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void handleHotelSelect(SelectEvent event) {
		try {
			hotelVO = (HotelVO) event.getObject();
			getHotelDetailsList(hotelVO, hotelTourVO);
			hotelTourVO.setIdHotel(hotelVO.getId());
			hotelTourVO.setName(hotelVO.getName());
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void handleEOSelect(SelectEvent event) {
		try {
			ExOrderVO vo = (ExOrderVO) event.getObject();

			if (StringUtils.equals(exorderType, ProductConstant.ROOMING_LIST_CAT_AIRLINE)) {
				RLAirlineVO.setCategory(ProductConstant.ROOMING_LIST_CAT_AIRLINE);
				RLAirlineVO.setFkId(vo.getId());
				RLAirlineVO.setDescription(vo.getCode());

				airLineDetailsVOList = purchaseEOBO.getAirLineList(vo.getId());
			} else if (StringUtils.equals(exorderType, ProductConstant.ROOMING_LIST_CAT_HOTEL)) {
				RLHotelVO.setCategory(ProductConstant.ROOMING_LIST_CAT_HOTEL);
				RLHotelVO.setFkId(vo.getId());
				RLHotelVO.setDescription(vo.getCode());

				hotelTourVOList = purchaseEOBO.getHotelTourList(vo.getId());
			}
		} catch (BusinessException e) {
			errorResult(e);
		}
	}

	public void getHotelDetailsList(HotelVO vo, HotelTourVO hotelTourVO) {
		try {
			hotelVO = vo;
			hotelAddrList = hotelBO.getHotelAddrList(hotelVO.getId());
			hotelContList = hotelBO.getHotelContList(hotelVO.getId());
			for (HotelAddressVO addressVO : hotelAddrList) {
				hotelVO.setLocation(addressVO.getAddr1());
				if (StringUtils.isNotEmpty(addressVO.getAddr2()))
					hotelVO.setLocation(hotelVO.getLocation() + "," + addressVO.getAddr2());
				if (StringUtils.isNotEmpty(addressVO.getAddr3()))
					hotelVO.setLocation(hotelVO.getLocation() + "," + addressVO.getAddr3());
				if (StringUtils.isNotEmpty(addressVO.getCity()))
					hotelVO.setLocation(hotelVO.getLocation() + "," + addressVO.getCity());
				if (StringUtils.isNotEmpty(addressVO.getState()))
					hotelVO.setLocation(hotelVO.getLocation() + "," + addressVO.getState());
				if (StringUtils.isNotEmpty(addressVO.getPostcode()))
					hotelVO.setLocation(hotelVO.getLocation() + "," + addressVO.getPostcode());
			}
			hotelTourVO.setLocation(hotelVO.getLocation());
			for (HotelContactVO contactVO : hotelContList) {
				hotelVO.setContact(StringUtils.defaultIfBlank(hotelVO.getContact(), "") + contactVO.getTypeCd() + ":"
						+ contactVO.getNumber() + "\n");
			}
			hotelTourVO.setContact(hotelVO.getContact());
		} catch (Throwable t) {
			errorResult(t);
		}

	}

	/**********************
	 * Lazy loading model *
	 **********************/
	class LazyRoomingListDataModel extends LazyDataModel<TourDepartureVO> implements Serializable {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
		 * org.primefaces.model.SortOrder, java.util.Map)
		 */
		@Override
		public List<TourDepartureVO> load(int first, int pageSize, String sortField, SortOrder sortOrder,
				Map<String, String> filters) {
			try {
				trackingLogUtils.startLogs();

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("companyId", getSessionInfoBean().getCompanyVO().getId());
				params.put("first", first);
				params.put("pageSize", pageSize);
				params.put("sortField", sortField);
				params.put("sortOrder", sortOrder);
				params.put("filters", filters);

				setRowCount(tourPkgBO.getTourDepListCountWithInvoicePax(params));
				if (0 < getRowCount())
					return tourPkgBO.getTourDepListWithInvoicePax(params);

			} catch (Throwable t) {
				errorResult(t);
			} finally {
				trackingLogUtils.endLogs("LazyRoomingListDataModel");
			}
			return null;
		}

	}

	public void saveAddEmployee() {
		if (StringUtils.equals(typeCd, ProductConstant.ROOMING_LIST_CAT_PIC))
			PICList.add(RLVO);
		else if (StringUtils.equals(typeCd, ProductConstant.ROOMING_LIST_CAT_TOUR_LEADER))
			tourLeaderList.add(RLVO);
		else if (StringUtils.equals(typeCd, ProductConstant.ROOMING_LIST_CAT_TOUR_MANAGER))
			tourManagerList.add(RLVO);

		RLVO.setCategory(typeCd);
		roomingListAUDVO.getAddList().add(RLVO);

		if (StringUtils.equals(typeCd, ProductConstant.ROOMING_LIST_CAT_PIC))
			resetPIC();
		else if (StringUtils.equals(typeCd, ProductConstant.ROOMING_LIST_CAT_TOUR_LEADER))
			resetTourLeader();
		else if (StringUtils.equals(typeCd, ProductConstant.ROOMING_LIST_CAT_TOUR_MANAGER))
			resetTourManager();

	}

	public void saveAccountCode() {
		try {
			int i;
			invoicePaxAUDVO = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(),
					new ArrayList<Object>());
			for (i = 0; i < invoicePaxList.size(); i++) {
				invoicePaxAUDVO.getUpdList().add(invoicePaxList.get(i));
			}

			invoiceBO.updInvoicePax(invoicePaxAUDVO);

			roomingListAUDVO.getUpdList().add(RLtourGuideVO);
			roomingListAUDVO.getUpdList().add(RLsupplierVO);
			roomingListAUDVO.getUpdList().add(RLAirlineVO);
			StringBuilder sb = new StringBuilder();
			for (HotelTourVO vo : hotelTourVOList) {
				sb.append(vo.getIdHotel() == null ? "" : vo.getIdHotel());
				sb.append(",");
			}
			RLHotelVO.setDescription2(sb.toString());
			roomingListAUDVO.getUpdList().add(RLHotelVO);
			roomingListBO.updRoomingList(roomingListAUDVO);
			roomingListLandOperatorBO.addLandOperatorSupplier(roomingListLandOprVOList, landOprAUDList);
			handleAccountDepSelect(selectedTourDepVO);
			successResult();
		} catch (Exception e) {
			errorResult(e);
		}
	}

	public void handleAccountDepSelect(TourDepartureVO vo) {
		try {
			selectedTourDepVO = vo;
			supplierVO = new SupplierVO();
			invoicePaxList = invoiceBO.getInvoicePaxListWithPkgId(vo.getId());
			roomingListAUDVO = new AddUpdDelVO(new ArrayList<Object>(), new ArrayList<Object>(),
					new ArrayList<Object>());
			PICList = roomingListBO.getPICList(vo.getId());
			tourLeaderList = roomingListBO.getTourLeaderList(vo.getId());
			tourManagerList = roomingListBO.getTourManagerList(vo.getId());
			idHotelList = roomingListBO.getIdHotelList(vo.getId());
			hotelList = hotelBO.getHotelList();
			
			resetLandOperatorPICForm();
			landOprAUDList = new GenAddUpdDelVO<RoomingListLandOperatorVO>();
			resetLandOperator();
			resetPIC();
			resetTourLeader();
			resetTourManager();
			

			RLtourGuideVO = roomingListBO.getRoomingListByCategory(vo.getId(),
					ProductConstant.ROOMING_LIST_CAT_TOUR_GUIDE);
			if (RLtourGuideVO == null) {
				RLtourGuideVO = new RoomingListVO();
				RLtourGuideVO.setCategory(ProductConstant.ROOMING_LIST_CAT_TOUR_GUIDE);
				RLtourGuideVO.setTourDepId(vo.getId());

				roomingListBO.insertVO(RLtourGuideVO);
			}

			RLsupplierVO = roomingListBO.getRoomingListByCategory(vo.getId(),
					ProductConstant.ROOMING_LIST_CAT_SUPPLIER);
			if (RLsupplierVO == null) {
				RLsupplierVO = new RoomingListVO();
				RLsupplierVO.setCategory(ProductConstant.ROOMING_LIST_CAT_SUPPLIER);
				RLsupplierVO.setTourDepId(vo.getId());

				roomingListBO.insertVO(RLsupplierVO);
			}

			RLAirlineVO = roomingListBO.getRoomingListByCategory(vo.getId(), ProductConstant.ROOMING_LIST_CAT_AIRLINE);
			if (RLAirlineVO == null) {
				RLAirlineVO = new RoomingListVO();
				RLAirlineVO.setCategory(ProductConstant.ROOMING_LIST_CAT_AIRLINE);
				RLAirlineVO.setTourDepId(vo.getId());

				roomingListBO.insertVO(RLAirlineVO);
			} else {
				airLineDetailsVOList = purchaseEOBO.getAirLineList(RLAirlineVO.getFkId());
			}

			RLHotelVO = roomingListBO.getRoomingListByCategory(vo.getId(), ProductConstant.ROOMING_LIST_CAT_HOTEL);
			if (RLHotelVO == null) {
				RLHotelVO = new RoomingListVO();
				RLHotelVO.setCategory(ProductConstant.ROOMING_LIST_CAT_HOTEL);
				RLHotelVO.setTourDepId(vo.getId());

				roomingListBO.insertVO(RLHotelVO);
			} else {
				hotelTourVOList = purchaseEOBO.getHotelTourList(RLHotelVO.getFkId());
				String[] idHotel = (StringUtils.isEmpty(RLHotelVO.getDescription2()) ? new String [] {} : RLHotelVO.getDescription2().replaceAll(",$", "").split(","));
				Map<Integer, String> unsortMap = new HashMap<Integer, String>();

				for (int i = 0; i < idHotel.length; i++) {
					unsortMap.put(i, StringUtils.isBlank(idHotel[i]) ? "" : String.valueOf(idHotel[i]));
				}

				Map<Integer, String> hotelListMap = new TreeMap<Integer, String>(unsortMap);

				for (int i = 0; i < hotelTourVOList.size(); i++) {
					hotelTourVOList.get(i).setIdHotel(
							StringUtils.isEmpty(hotelListMap.get(i)) ? null : Long.valueOf(hotelListMap.get(i)));
					hotelVO = hotelBO.getHotelById(hotelTourVOList.get(i).getIdHotel());
					if (hotelVO != null) {
						hotelTourVOList.get(i).setName(hotelVO.getName());
						getHotelDetailsList(hotelVO, hotelTourVOList.get(i));
					}
				}
			}
			
			roomingListLandOprVOList = roomingListLandOperatorBO.getSupplierList(vo.getId(), false);
			
			
			if (RLsupplierVO.getFkId() != null) {
				supplierVO = billPymtBO.getSupplier(RLsupplierVO.getFkId(),
						getSessionInfoBean().getCompanyVO().getId());
//				if (CollectionUtils.isEmpty(roomingListLandOprVOList)) {
//					isAddLandOperator = true;
//					addExistedLandOprSupplier(supplierVO);
//				}
			}
			
			for (InvoicePaxVO paxVO : invoicePaxList) {
				if (paxVO.getActRoomPairingNo() != null && paxVO.getActRoomPairingNo() == 0)
					paxVO.setActRoomPairingNo(null);

				if (!paxVO.getActRoomRemarksFlg()) {
					paxVO.setActRoomRemarks(paxVO.getSpclReq());
					paxVO.setActRoomRemarksFlg(true);
				}
			}

			tourDepVO = new TourDepartureVO();
			tourPkgVO = new TourPackageVO();
			countryVO = new CountryVO();
			countryVO.setCountry("");

			tourDepVO = vo;

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void deletePIC(RoomingListVO vo) {
		PICList.remove(vo);
		roomingListAUDVO.getDelList().add(vo);
	}

	public void deleteTourLeader(RoomingListVO vo) {
		tourLeaderList.remove(vo);
		roomingListAUDVO.getDelList().add(vo);
	}

	public void deleteTourManager(RoomingListVO vo) {
		tourManagerList.remove(vo);
		roomingListAUDVO.getDelList().add(vo);
	}

	public void deleteEO(String exorderType) {
		if (StringUtils.equals(exorderType, ProductConstant.ROOMING_LIST_CAT_AIRLINE)) {
			RLAirlineVO.setFkId(null);
			RLAirlineVO.setDescription(null);

			airLineDetailsVOList = new ArrayList<AirLineDetailsVO>();
		} else if (StringUtils.equals(exorderType, ProductConstant.ROOMING_LIST_CAT_HOTEL)) {
			RLHotelVO.setFkId(null);
			RLHotelVO.setDescription(null);
			RLHotelVO.setDescription2(null);

			hotelTourVOList = new ArrayList<HotelTourVO>();
		}
	}

	/**
	 * Refresh Invoice Pax List
	 */
	public void refreshInvoicePaxList() {
		try {
			invoicePaxList = invoiceBO.getInvoicePaxListWithPkgId(selectedTourDepVO.getId());

			for (int i = 0; i < invoicePaxList.size(); i++) {
				if (invoicePaxList.get(i).getActRoomPairingNo() != null)
					if (invoicePaxList.get(i).getActRoomPairingNo() == 0)
						invoicePaxList.get(i).setActRoomPairingNo(null);

				if (invoicePaxList.get(i).getActRoomRemarksFlg() == false) {
					invoicePaxList.get(i).setActRoomRemarks(invoicePaxList.get(i).getSpclReq());
					invoicePaxList.get(i).setActRoomRemarksFlg(true);
				}
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void resetPIC() {
		RLVO = new RoomingListVO();
		RLVO.setTourDepId(selectedTourDepVO.getId());
		RLVO.setCategory(ProductConstant.ROOMING_LIST_CAT_PIC);
	}

	public void resetTourLeader() {
		RLVO = new RoomingListVO();
		RLVO.setTourDepId(selectedTourDepVO.getId());
		RLVO.setCategory(ProductConstant.ROOMING_LIST_CAT_TOUR_LEADER);
	}

	public void resetTourManager() {
		RLVO = new RoomingListVO();
		RLVO.setTourDepId(selectedTourDepVO.getId());
		RLVO.setCategory(ProductConstant.ROOMING_LIST_CAT_TOUR_MANAGER);
	}
	
	public void resetLandOperator() {
		roomingListLandOprVOList = new ArrayList<RoomingListLandOperatorVO>();
	}
	
	public void resetLandOperatorPICForm() {
		roomingListLandOperatorVO = new RoomingListLandOperatorVO();
		roomingListLandOperatorContVO = new RoomingListLandOperatorContVO();
		roomingListLandOperatorVO.setLandOperatorContAUDList(new GenAddUpdDelVO<RoomingListLandOperatorContVO>());
		roomingListLandOperatorVO.setRoomingListLandOprContList(new ArrayList<RoomingListLandOperatorContVO>());
	}

	public void handleSupplierSelect(SelectEvent event) {
		try {
			
			supplierVO = (SupplierVO) event.getObject();
			purchaseBO.getSupplierDetails(supplierVO);

			RLsupplierVO.setFkId(supplierVO.getId());
			if(roomingListLandOprVOList!=null) {
				for (RoomingListLandOperatorVO vo : roomingListLandOprVOList) {
					landOprAUDList.getDelList().add(vo);	
				}
			}
			resetLandOperatorPICForm();
			//resetLandOperator();
			isAddLandOperator = true;
			addExistedLandOprSupplier(supplierVO);
			
			// Add codes here to set th tourDepVO.AcctVO = selection
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public void handleEmployeeSelect(SelectEvent event) {
		try {
			employeeViewVO = (EmployeeViewVO) event.getObject();
			RLVO.setDescription(employeeViewVO.getUserVO().getName());
			RLVO.setDescription2(employeeViewVO.getUserVO().getMobileNumber());
			RLVO.setDepartment(employeeViewVO.getDepartment());
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void addLandOperatorPIC(){
		resetLandOperatorPICForm();
		roomingListLandOperatorVO.setRoomingListLandOprContList(new ArrayList<RoomingListLandOperatorContVO>());
		isAddLandOperator = true;
	}
	
	public void addContact() {
		try {
			roomingListLandOperatorContVO.setContactType(roomingListLandOperatorContVO.getContactType());
			roomingListLandOperatorContVO.setContactNo(roomingListLandOperatorContVO.getContactNo());
			if(roomingListLandOperatorVO.getLandOperatorContAUDList()==null)
				roomingListLandOperatorVO.setLandOperatorContAUDList(new GenAddUpdDelVO<RoomingListLandOperatorContVO>());
			if(!isAddLandOperatorCont) {
				GenUpdateVOList.processAddUpdDelVO(roomingListLandOperatorVO.getLandOperatorContAUDList(), 
						roomingListLandOperatorVO.getRoomingListLandOprContList(), 
						roomingListLandOperatorContVO, 
						ConstantScreenAction.UPD);
				Thread.sleep(10);
			} else { 
				GenUpdateVOList.processAddUpdDelVO(roomingListLandOperatorVO.getLandOperatorContAUDList(), 
						roomingListLandOperatorVO.getRoomingListLandOprContList(), 
						roomingListLandOperatorContVO, 
						ConstantScreenAction.ADD);
				Thread.sleep(10);
				
			}
			/*RLLandOperatorVO.getLandOperatorContList().add(roomingListLandOperatorContVO);*/

			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void addExistedLandOprSupplier(SupplierVO vo) {
		try {
			corContactVOList = purchaseBO.getCorContactList(vo.getCorporateVO().getId());
			/*roomingListLandOprContVOList = roomingListLandOperatorBO.getContactList(roomingListLandOperatorVO.getId());*/
			roomingListLandOperatorVO.setIdTourDep(selectedTourDepVO.getId());
			roomingListLandOperatorVO.setSalutation(vo.getPersonVO().getSalutation());
			roomingListLandOperatorVO.setGivenName(StringUtils.isEmpty(vo.getPersonVO().getGivenName()) ? "" : vo.getPersonVO().getGivenName());
			roomingListLandOperatorVO.setLastName(StringUtils.isEmpty(vo.getPersonVO().getLastName()) ? "" : vo.getPersonVO().getLastName());

			for (CorContactVO corContactVO : corContactVOList) {
				roomingListLandOperatorContVO = new RoomingListLandOperatorContVO();
				roomingListLandOperatorContVO.setContactType(corContactVO.getContactType());
				roomingListLandOperatorContVO.setContactNo(corContactVO.getContactNo());	
				GenUpdateVOList.processAddUpdDelVO(roomingListLandOperatorVO.getLandOperatorContAUDList(), 
						roomingListLandOperatorVO.getRoomingListLandOprContList(), 
						roomingListLandOperatorContVO, 
						ConstantScreenAction.ADD);
				Thread.sleep(10);
			}
			
			for (RoomingListLandOperatorContVO roomingListLandOprContVO : roomingListLandOperatorVO.getRoomingListLandOprContList()) {
				roomingListLandOperatorVO.setContactNo(StringUtils.defaultIfBlank(roomingListLandOperatorVO.getContactNo(), "")
						+ roomingListLandOprContVO.getContactType() + ":" + roomingListLandOprContVO.getContactNo() + "<br/>");
			}
			
			GenUpdateVOList.processAddUpdDelVO(landOprAUDList, 
					roomingListLandOprVOList, 
					roomingListLandOperatorVO, 
					ConstantScreenAction.ADD);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onLandOperatorPICContactSelected(RoomingListLandOperatorContVO vo) {
		try {
			
			if (vo != null) {
				isAddLandOperatorCont = false;
				roomingListLandOperatorContVO = (RoomingListLandOperatorContVO) SerializableUtils.deepCopy(vo);
			} else {
				isAddLandOperatorCont = true;
				roomingListLandOperatorContVO = new RoomingListLandOperatorContVO();
			}
			
			successResult();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void onLandOperatorPICSelected(RoomingListLandOperatorVO vo) {
		try {
			
			if (vo != null) {
				isAddLandOperator = false;
				roomingListLandOperatorVO = (RoomingListLandOperatorVO) SerializableUtils.deepCopy(vo);
			} else {
				isAddLandOperator = true;
				roomingListLandOperatorVO = new RoomingListLandOperatorVO();
			}
			
			successResult();
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void delLandOperatorPICContactList(RoomingListLandOperatorContVO vo) {
		try {
			/*if(supplierVO.getAddEdit()){
					corContactVO.setCorporateId(supplierVO.getCorporateVO().getId());
					corContactAUDVO.getDelList().add(corContactVO);
				}
			supplierVO.getCorContactList().remove(corContactVO);
			successResult();
			resetContactForm();*/
			GenUpdateVOList.processAddUpdDelVO(roomingListLandOperatorVO.getLandOperatorContAUDList(), 
					roomingListLandOperatorVO.getRoomingListLandOprContList(), 
					vo, 
					ConstantScreenAction.DEL);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void delLandOperatorPICList(RoomingListLandOperatorVO vo) {
		try {
			GenUpdateVOList.processAddUpdDelVO(landOprAUDList, 
					roomingListLandOprVOList, 
					vo, 
					ConstantScreenAction.DEL);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void saveAddLandOperatorDetails() {
		try {
			roomingListLandOperatorVO.setIdTourDep(selectedTourDepVO.getId());
			roomingListLandOperatorVO.setContactNo("");
			for (RoomingListLandOperatorContVO vo : roomingListLandOperatorVO.getRoomingListLandOprContList()) {
				String tempCode = "";
				if (StringUtils.equals(vo.getContactType(), "mobile")) {
					tempCode = "(M):";
				} else if (StringUtils.equals(vo.getContactType(), "fax")) {
					tempCode = "(F):";
				} else if (StringUtils.equals(vo.getContactType(), "home")) {
					tempCode = "(H):";
				} else if (StringUtils.equals(vo.getContactType(), "office")) {
					tempCode = "(O):";
				}
				
				roomingListLandOperatorVO .setContactNo(StringUtils.defaultIfBlank(roomingListLandOperatorVO.getContactNo(), "")
						+ tempCode + vo.getContactNo() + "\n");		
			}
			if(!isAddLandOperator) {
				GenUpdateVOList.processAddUpdDelVO(landOprAUDList, 
						roomingListLandOprVOList, 
						roomingListLandOperatorVO, 
						ConstantScreenAction.UPD);
			}else {
				GenUpdateVOList.processAddUpdDelVO(landOprAUDList, 
						roomingListLandOprVOList, 
						roomingListLandOperatorVO, 
						ConstantScreenAction.ADD);
			}

			resetLandOperatorPICForm();
		} catch(Throwable t) {
			errorResult(t);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void exportToExcel() throws BusinessException {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Users");

			// turn off gridlines
			sheet.setDisplayGridlines(true);
			sheet.setPrintGridlines(true);
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);
			PrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setLandscape(true);

			// the following three statements are required only for HSSF
			sheet.setAutobreaks(true);
			printSetup.setFitHeight((short) 1);
			printSetup.setFitWidth((short) 1);

			Row row0 = sheet.createRow(0);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_LEFT);
			CellStyle styleCenter = wb.createCellStyle();
			styleCenter.setAlignment(CellStyle.ALIGN_CENTER);
			row0.setHeightInPoints(25.75f);
			
			CellStyle borderStyle = wb.createCellStyle();
			borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
			borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
			borderStyle.setBorderRight(CellStyle.BORDER_THIN);
			borderStyle.setBorderTop(CellStyle.BORDER_THIN);
			
			CellStyle combined = wb.createCellStyle();
			combined.cloneStyleFrom(styleCenter);
			combined.setBorderBottom(borderStyle.getBorderBottom());
			combined.setBorderLeft(borderStyle.getBorderLeft());
			combined.setBorderRight(borderStyle.getBorderRight());
			combined.setBorderTop(borderStyle.getBorderTop());
			
			CellStyle combined2 = wb.createCellStyle();
			combined2.cloneStyleFrom(style);
			combined2.setBorderBottom(borderStyle.getBorderBottom());
			combined2.setBorderLeft(borderStyle.getBorderLeft());
			combined2.setBorderRight(borderStyle.getBorderRight());
			combined2.setBorderTop(borderStyle.getBorderTop());
			

			Cell cell02 = row0.createCell(1);
			cell02.setCellValue(getSessionInfoBean().getCompanyVO().getName());
			cell02.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 5));

			Row row1 = sheet.createRow(1);
			Cell cell12 = row1.createCell(1);
			companyContList = corporateProfileBO.getCompanyContactList(getSessionInfoBean().getCompanyVO().getId());
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
			String temp = null, prevTemp = null;
			for (int i = 0; i < companyContList.size(); i++) {
				temp = companyContList.get(i).getTypecodecontact() + ":" + companyContList.get(i).getNumber();
				if (prevTemp != null) {
					temp = prevTemp + " " + temp;
				}
				prevTemp = temp;
			}
			cell12.setCellValue(temp);

			Row row2 = sheet.createRow(2);
			Cell cell29 = row2.createCell(9);
			cell29.setCellValue("UPDATED:");
			Cell cell210 = row2.createCell(10);
			cell210.setCellValue(new SimpleDateFormat("dd-MMM-yy").format(RLsupplierVO.getUpdatedDate()));

			Row row3 = sheet.createRow(3);
			Cell cell32 = row3.createCell(1);
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 4));

			cell32.setCellValue("TOUR CODE:" + tourDepVO.getCode());

			Cell cell39 = row3.createCell(9);
			cell39.setCellValue("C/INDATE");
			cell39.setCellStyle(borderStyle);
			Cell cell310 = row3.createCell(10);
			cell310.setCellValue("C/OUTDATE");
			cell310.setCellStyle(borderStyle);
			Cell cell311 = row3.createCell(11);
			cell311.setCellValue("Location");
			cell311.setCellStyle(styleCenter);
			setRegionBorder(sheet,3,11,12);
			Cell cell313 = row3.createCell(13);
			cell313.setCellValue("HOTEL NAME");
			cell313.setCellStyle(styleCenter);
			setRegionBorder(sheet,3,13,15);
			Cell cell316 = row3.createCell(16);
			cell316.setCellValue("CONTACT NO");
			cell316.setCellStyle(styleCenter);
			setRegionBorder(sheet,3,16,20);

			int rowNum = 3;
			tourHotelList = tourPkgBO.getTourHotelList(tourDepVO.getId());

			if (CollectionUtils.isNotEmpty(hotelTourVOList)) {
				for (HotelTourVO hotelTourVO : hotelTourVOList) {
					rowNum += 1;
					Row rowHotel = sheet.createRow(rowNum);
					Cell cellChkInDate = rowHotel.createCell(9);
					if (hotelTourVO.getCheckInDt() != null) {
						cellChkInDate.setCellValue(new SimpleDateFormat("dd-MMM-yy").format(hotelTourVO.getCheckInDt()));
						cellChkInDate.setCellStyle(borderStyle);
					}
					else {
						cellChkInDate.setCellValue("");
						cellChkInDate.setCellStyle(borderStyle);
					}
					Cell cellChkOutDate = rowHotel.createCell(10);
					if (hotelTourVO.getCheckOutDt() != null) {
						cellChkOutDate.setCellValue(new SimpleDateFormat("dd-MMM-yy").format(hotelTourVO.getCheckOutDt()));
						cellChkOutDate.setCellStyle(borderStyle);
					}
					else {
						cellChkOutDate.setCellValue("");
						cellChkOutDate.setCellStyle(borderStyle);
					}
					Cell cellLocation = rowHotel.createCell(11);
					cellLocation.setCellValue(hotelTourVO.getLocation());
					setRegionBorder(sheet,rowNum,11,12);
					Cell cellName = rowHotel.createCell(13);
					cellName.setCellValue(hotelTourVO.getName());
					setRegionBorder(sheet,rowNum,13,15);
					Cell cellCont = rowHotel.createCell(16);
					cellCont.setCellValue(hotelTourVO.getContact().replaceAll("\\r|\\n", " "));
					setRegionBorder(sheet,rowNum,16,20);
				}
			}

			rowNum += 1;
			Row row5 = sheet.createRow(rowNum);
			Cell cell51 = row5.createCell(1);
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));
			cell51.setCellValue("TOUR GUIDE :");
			Cell cell54 = row5.createCell(4);
			sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 4, 6));
			cell54.setCellValue(StringUtils.isNotBlank(RLtourGuideVO.getDescription())
					? RLtourGuideVO.getDescription().replaceAll("\\r\\n", " ")
					: "");
			
			rowNum += 2;
			Row row = null;
			row = sheet.createRow(rowNum);
			Cell cell42 = row.createCell(1);
			cell42.setCellValue("FLIGHT SCHEDULE [PNR:" + StringUtils.defaultIfBlank(tourDepVO.getPrn(), " ") + "]");
			
			rowNum += 1;
			Row rowAirlineSchedule = sheet.createRow(rowNum);
			Cell airlineType = rowAirlineSchedule.createCell(1);
			airlineType.setCellValue("Type");
			airlineType.setCellStyle(borderStyle);
			Cell airlineDepDate = rowAirlineSchedule.createCell(2);
			airlineDepDate.setCellValue("Dep Date");
			airlineDepDate.setCellStyle(borderStyle);
			Cell airlineArrDate = rowAirlineSchedule.createCell(3);
			airlineArrDate.setCellValue("Arr Date");
			airlineArrDate.setCellStyle(borderStyle);
			Cell airlineFlightCode = rowAirlineSchedule.createCell(4);
			airlineFlightCode.setCellValue("Flight Code");
			airlineFlightCode.setCellStyle(borderStyle);
			Cell airlineFrom = rowAirlineSchedule.createCell(5);
			airlineFrom.setCellValue("From");
			airlineFrom.setCellStyle(borderStyle);
			Cell airlineTo = rowAirlineSchedule.createCell(6);
			airlineTo.setCellValue("To");
			airlineTo.setCellStyle(borderStyle);
			Cell airlineETD = rowAirlineSchedule.createCell(7);
			airlineETD.setCellValue("ETD");
			airlineETD.setCellStyle(borderStyle);
			Cell airlineETA = rowAirlineSchedule.createCell(8);
			airlineETA.setCellValue("ETA");
			airlineETA.setCellStyle(borderStyle);
			
			AirlineScheduleVO airlineDetailsVO = airlineBO.getAirlineSchedule(tourDepVO);
			for (AirlineScheduleItemVO vo : airlineDetailsVO.getAirlineScheduleItemList()) {
				rowNum += 1;
				rowAirlineSchedule = sheet.createRow(rowNum);
				Cell cellArlType = rowAirlineSchedule.createCell(1);
				cellArlType.setCellValue(vo.getTypeCd());
				cellArlType.setCellStyle(borderStyle);
				Cell cellArlDepDate = rowAirlineSchedule.createCell(2);
				if (vo.getDepartureDate() != null) {
					cellArlDepDate.setCellValue(new SimpleDateFormat("dd-MMM-yy").format(vo.getDepartureDate()));
					cellArlDepDate.setCellStyle(borderStyle);
				} else {
					cellArlDepDate.setCellValue("");
					cellArlDepDate.setCellStyle(borderStyle);
				}
				Cell cellArlArrDate = rowAirlineSchedule.createCell(3);
				if (vo.getArrivalDate() != null) {
					cellArlArrDate.setCellValue(new SimpleDateFormat("dd-MMM-yy").format(vo.getArrivalDate()));
					cellArlArrDate.setCellStyle(borderStyle);
				} else {
					cellArlArrDate.setCellValue("");
					cellArlArrDate.setCellStyle(borderStyle);
				}
				Cell cellFlightCode = rowAirlineSchedule.createCell(4);
				cellFlightCode.setCellValue(vo.getFlightCd());
				cellFlightCode.setCellStyle(borderStyle);
				Cell cellFrom = rowAirlineSchedule.createCell(5);
				cellFrom.setCellValue(vo.getFromAirportCd());
				cellFrom.setCellStyle(borderStyle);
				Cell cellTo = rowAirlineSchedule.createCell(6);
				cellTo.setCellValue(vo.getToAirportCd());
				cellTo.setCellStyle(borderStyle);
				Cell cellEtd = rowAirlineSchedule.createCell(7);
				if (vo.getEtd() != null) {
					cellEtd.setCellValue(vo.getEtd());
					cellEtd.setCellStyle(borderStyle);
				} else {
					cellEtd.setCellValue("");
					cellEtd.setCellStyle(borderStyle);
				}
				Cell cellEta = rowAirlineSchedule.createCell(8);
				if (vo.getEta() != null) {
					cellEta.setCellValue(vo.getEta());
					cellEta.setCellStyle(borderStyle);
				} else {
					cellEta.setCellValue("");
					cellEta.setCellStyle(borderStyle);
				}
			}

			rowNum += 2;
			int idx1 = 1;
			Row row16 = sheet.createRow(rowNum);
			for (int m = 0; m < excelPrintColumnCount; m++) {
				Cell cell161 = row16.createCell(idx1);
				switch (m) {
				case 0:
					cell161.setCellValue("NO");
					cell161.setCellStyle(combined);
					break;
				case 1:
					cell161.setCellValue("Title");
					cell161.setCellStyle(combined);
					break;
				case 2:
					cell161.setCellValue("Surname");
					cell161.setCellStyle(combined);
					break;
				case 3:
					cell161.setCellValue("Given Name");
					cell161.setCellStyle(combined);
					break;
				case 4:
					cell161.setCellValue("Nick Name");
					cell161.setCellStyle(combined);
					break;
				case 5:
					cell161.setCellValue("Room Type");
					cell161.setCellStyle(combined);
					break;
				case 6:
					cell161.setCellValue("Room No");
					cell161.setCellStyle(combined);
					break;
				case 7:
					cell161.setCellValue("PST NO");
					cell161.setCellStyle(combined);
					break;
				case 8:
					cell161.setCellValue("PST DOE");
					cell161.setCellStyle(combined);
					break;
				case 9:
					cell161.setCellValue("Visa No");
					cell161.setCellStyle(combined);
					break;
				case 10:
					cell161.setCellValue("Visa DOE");
					cell161.setCellStyle(combined);
					break;
				case 11:
					cell161.setCellValue("DOB");
					cell161.setCellStyle(combined);
					break;
				case 12:
					cell161.setCellValue("Age");
					cell161.setCellStyle(combined);
					break;
				case 13:
					cell161.setCellValue("Nationality");
					cell161.setCellStyle(combined);
					break;
				case 14:
					cell161.setCellValue("Lang");
					cell161.setCellStyle(combined);
					break;
				case 15:
					cell161.setCellValue("INS");
					cell161.setCellStyle(combined);
					break;
				case 16:
					cell161.setCellValue("Meal Pref");
					cell161.setCellStyle(combined);
					break;
				case 17:
					cell161.setCellValue("Seat-D");
					cell161.setCellStyle(combined);
					break;
				case 18:
					cell161.setCellValue("Seat-R");
					cell161.setCellStyle(combined);
					break;
				case 19:
					cell161.setCellValue("Contact No.");
					cell161.setCellStyle(combined);
					break;
				case 20:
					cell161.setCellValue("Email");
					cell161.setCellStyle(combined);
					break;
				case 21:
					cell161.setCellValue("External Remarks");
					cell161.setCellStyle(combined);
					break;
				case 22:
					cell161.setCellValue("Special Request");
					cell161.setCellStyle(combined);
					break;
				case 23:
					cell161.setCellValue("Invoice No");
					cell161.setCellStyle(combined);
					break;
				case 24:
					cell161.setCellValue("Salesman");
					cell161.setCellStyle(combined);
					break;
				}
				idx1 += 1;
			}

			invoiceList = roomingListBO.getInvoiceList(tourDepVO.getId());
			int sno = 0;
			for (InvoiceVO vo : invoiceList) {
				EmployeeViewVO employeeVO = userBO.getEmployeeViewWithUser(vo.getSalerId());
				invoicePaxInvIdList = roomingListBO.getInvoicePax(vo.getId());

				for (InvoicePaxVO paxVO : invoicePaxInvIdList) {
					IdentityVO personIdentityPassVO = new IdentityVO();
					IdentityVO personIdentityVisaVO = new IdentityVO();
					IdentityDetailVO personIdentityDetailVO = new IdentityDetailVO();
					IdentityDetailVO personIdentityVisaDetailVO = new IdentityDetailVO();

					custVO = customerBO.getCustomer(paxVO.getCustId());
					if (custVO != null)
						personVO = roomingListBO.getPerson(custVO.getPersCorpId());
					if (personVO != null)
						personIdentityPassVO = roomingListBO.getPersonIdentity(personVO.getId(), "pss_prt");
					if (personVO != null)
						personIdentityVisaVO = roomingListBO.getPersonIdentity(personVO.getId(), "visa");
					if (personIdentityPassVO != null)
						personIdentityDetailVO = roomingListBO.getPersonIdentityDetails(personIdentityPassVO.getId());
					if (personIdentityVisaVO != null)
						personIdentityVisaDetailVO = roomingListBO
								.getPersonIdentityDetails(personIdentityVisaVO.getId());

					personLangList = roomingListBO.getpersonLang(personVO.getId());
					personMealList = roomingListBO.getpersonMeal(personVO.getId());
					personContPList = roomingListBO.getPersonContList(personVO.getId());

					String dob = "", passDtExp = "", visaDtExp = "", nationality = "";

					if (personVO.getDob() != null)
						dob = new SimpleDateFormat("dd-MMM-yy").format(personVO.getDob());

					if (personIdentityDetailVO != null && personIdentityDetailVO.getDtExpired() != null)
						passDtExp = new SimpleDateFormat("dd-MMM-yy").format(personIdentityDetailVO.getDtExpired());

					if (personIdentityVisaDetailVO != null && personIdentityVisaDetailVO.getDtExpired() != null)
						visaDtExp = new SimpleDateFormat("dd-MMM-yy").format(personIdentityVisaDetailVO.getDtExpired());

					if (personVO != null && personVO.getCountryId() != null) {
						CountryVO countryVO = regionBO.getCountryById(personVO.getCountryId());
						if (countryVO != null)
							nationality = countryVO.getCountry();
					}

					String tit = null;

					List<LookupItemVO> travelInsTypeList = lookUpBO.getLookUpCodeList("travel_Ins_type");

					for (LookupItemVO travelInsType : travelInsTypeList) {
						if (StringUtils.equals(travelInsType.getCode(), paxVO.getTravelInsType())) {
							tit = travelInsType.getDescription();
						}
					}

					rowNum += 1;
					Row row17 = sheet.createRow(rowNum);
					sno += 1;
					idx1 = 1;

					for (int i = 0; i < invoicePaxInvIdList.size(); i++) {
						if (invoicePaxInvIdList.get(i).getActRoomPairingNo() != null)
							if (invoicePaxInvIdList.get(i).getActRoomPairingNo() == 0)
								invoicePaxInvIdList.get(i).setActRoomPairingNo(null);

						if (invoicePaxInvIdList.get(i).getActRoomRemarksFlg() == false) {
							invoicePaxInvIdList.get(i).setActRoomRemarks(invoicePaxInvIdList.get(i).getSpclReq());
							invoicePaxInvIdList.get(i).setActRoomRemarksFlg(true);
						}
					}

					for (int m = 0; m < excelPrintColumnCount; m++) {
						Cell cell171 = row17.createCell(idx1);
						switch (m) {
						case 0:
							cell171.setCellValue(sno);
							cell171.setCellStyle(combined);
							break;
						case 1:
							if (personVO == null)
								break;
							else {
								cell171.setCellValue(personVO.getSalutation());
								cell171.setCellStyle(combined);
								break;
							}
						case 2:
							if (personVO == null)
								break;
							else {
								cell171.setCellValue(personVO.getLastName());
								cell171.setCellStyle(combined);
								break;
							}
						case 3:
							if (personVO == null)
								break;
							else {
								cell171.setCellValue(personVO.getGivenName());
								cell171.setCellStyle(combined);
								break;
							}
						case 4:
							cell171.setCellValue("");
							cell171.setCellStyle(combined);
							break;
						case 5:
							if (paxVO.getActRoomPairingNo() == null) {
								cell171.setCellValue(paxVO.getActRoomTypeCd());
								cell171.setCellStyle(combined);
								break;
							} else if (paxVO.getActRoomPairingNo() == 0) {
								cell171.setCellValue(paxVO.getActRoomTypeCd());
								cell171.setCellStyle(combined);
								break;
							} else {
								cell171.setCellValue(paxVO.getActRoomTypeCd() + " " + paxVO.getActRoomPairingNo());
								cell171.setCellStyle(combined);
								break;
							}
						case 6:
							cell171.setCellValue("");
							cell171.setCellStyle(combined);
							break;
						case 7:
							if (personIdentityPassVO == null) {
								cell171.setCellStyle(borderStyle);
								break;
							}
							else {
								cell171.setCellValue(personIdentityPassVO.getIdNo());
								cell171.setCellStyle(combined);
								break;
							}
						case 8:
							cell171.setCellValue(passDtExp);
							cell171.setCellStyle(borderStyle);
							break;
						case 9:
							if (personIdentityVisaVO == null) {
								cell171.setCellValue("");
								cell171.setCellStyle(borderStyle);
								break;
							} else {
								cell171.setCellValue(personIdentityVisaVO.getIdNo());
								cell171.setCellStyle(combined);
								break;
							}
						case 10:
							cell171.setCellValue(visaDtExp);
							cell171.setCellStyle(combined);
							break;
						case 11:
							cell171.setCellValue(dob);
							cell171.setCellStyle(combined);
							break;
						case 12:
							if (personVO.getAge() == null) {
								cell171.setCellStyle(combined);
								break;
							}
							else {
								cell171.setCellValue(personVO.getAge());
								cell171.setCellStyle(combined);
								break;
							}
						case 13:
							cell171.setCellValue(nationality);
							cell171.setCellStyle(borderStyle);
							break;
						case 14:
							prevTemp = null;
							temp = null;
							for (PersonLangVO personLangVO : personLangList) {
								if (StringUtils.equals(personLangVO.getLangCd(), "EN")) {
									temp = "E";
								} else if (StringUtils.equals(personLangVO.getLangCd(), "mdn")) {
									temp = "Z";
								} else if (StringUtils.equals(personLangVO.getLangCd(), "MY")) {
									temp = "M";
								} else if (StringUtils.equals(personLangVO.getLangCd(), "canto")) {
									temp = "C";
								}

								if (prevTemp != null) {
									temp = prevTemp + "/" + temp;
								}
								prevTemp = temp;
							}
							if(personLangList.size()!=0) {
								cell171.setCellValue(temp);
								cell171.setCellStyle(combined2);
							}else
								cell171.setCellStyle(combined2);
							break;
						case 15:
							cell171.setCellValue(tit);
							cell171.setCellStyle(borderStyle);
							break;
						case 16:
							prevTemp = null;
							temp = null;
							for (int n = 0; n < personMealList.size(); n++) {
								temp = personMealList.get(n).getMealCd();
								if (prevTemp != null) {
									temp = prevTemp + "/" + temp;
								}
								prevTemp = temp;
							}
							if(personMealList.size()!=0) {
								cell171.setCellValue(temp);
								cell171.setCellStyle(combined2);
							}else
								cell171.setCellStyle(combined2);
							break;
						case 17:
							cell171.setCellValue("");
							cell171.setCellStyle(borderStyle);
							break;
						case 18:
							cell171.setCellValue("");
							cell171.setCellStyle(borderStyle);
							break;
						case 19:
							prevTemp = null;
							temp = null;

							for (PersonContactVO personContactVO : personContPList) {
								String tempCode = "";
								if (StringUtils.equals(personContactVO.getTypeCd(), "mobile")) {
									tempCode = "(M):";
								} else if (StringUtils.equals(personContactVO.getTypeCd(), "fax")) {
									tempCode = "(F):";
								} else if (StringUtils.equals(personContactVO.getTypeCd(), "home")) {
									tempCode = "(H):";
								} else if (StringUtils.equals(personContactVO.getTypeCd(), "office")) {
									tempCode = "(O):";
								}

								temp = tempCode + personContactVO.getNumber();

								if (prevTemp != null) {
									temp = prevTemp + " " + temp;
								}
								prevTemp = temp;
							}

							if (personContPList.size() != 0) {
								cell171.setCellValue(temp);
								cell171.setCellStyle(combined2);
							}else
								cell171.setCellStyle(combined2);
							break;
						case 20:
							if (personVO != null && personContPList.size() != 0) {
								cell171.setCellValue(personVO.getEmail());
								cell171.setCellStyle(combined2);
							}else
								cell171.setCellStyle(borderStyle);
							break;
						case 21:
							cell171.setCellValue(paxVO.getActRoomRemarks());
							cell171.setCellStyle(borderStyle);
							break;
						case 22:
							cell171.setCellValue(paxVO.getSpclReq());
							cell171.setCellStyle(borderStyle);
							break;
						case 23:
							cell171.setCellValue(vo.getCode());
							cell171.setCellStyle(borderStyle);
							break;
						case 24:
							if (employeeVO != null && employeeVO.getUserVO() != null) {
								cell171.setCellValue(employeeVO.getUserVO().getName());
								cell171.setCellStyle(borderStyle);
							}
							else {
								cell171.setCellValue("");
								cell171.setCellStyle(borderStyle);
							}
							break;
						}
						idx1 += 1;

					}
					
				}
			}

			List<RoomingListVO> tempTourLeaderList = roomingListBO.getTourLeaderList(tourDepVO.getId());
			List<RoomingListVO> tempTourManagerList = roomingListBO.getTourManagerList(tourDepVO.getId());

			for (RoomingListVO tourManagerVO : tempTourLeaderList) {
				rowNum += 1;
				row = sheet.createRow(rowNum);
				sno += 1;
				populateTourLeaderAndManager(row, combined, borderStyle, tourManagerVO, "TOUR MANAGER", sno);
			}

			for (RoomingListVO tourManagerVO : tempTourManagerList) {
				rowNum += 1;
				row = sheet.createRow(rowNum);
				sno += 1;
				populateTourLeaderAndManager(row, combined, borderStyle, tourManagerVO, "TOUR LEADER", sno);
			}

			rowNum += 2;

			corporateVO = roomingListBO.getCorporateVO(supplierVO.getPersonId());
			corpAddrVO = null;
			if (corporateVO != null) {
				corpAddrVO = roomingListBO.getCorpAddrVO(corporateVO.getId());
			}

			personContList = roomingListBO.getPersonContList(supplierVO.getPersonId());

			String text = "";

			if (corpAddrVO != null) {
				if (corpAddrVO.getAddr1() != null)
					text = text.concat(corpAddrVO.getAddr1());
				if (corpAddrVO.getAddr2() != null)
					text = text.concat(corpAddrVO.getAddr2());
				if (corpAddrVO.getAddr3() != null)
					text = text.concat(corpAddrVO.getAddr3());
				if (corpAddrVO.getCity() != null)
					text = text.concat(StringUtils.isNotBlank(corpAddrVO.getCity()) ? ", " + corpAddrVO.getCity() : "");
				if (corpAddrVO.getPostcode() != null)
					text = text.concat(
							StringUtils.isNotBlank(corpAddrVO.getPostcode()) ? ", " + corpAddrVO.getPostcode() : "");
				if (corpAddrVO.getState() != null)
					text = text
							.concat(StringUtils.isNotBlank(corpAddrVO.getState()) ? ", " + corpAddrVO.getState() : "");
			}
			temp = null;
			prevTemp = null;
			for (int i = 0; i < personContList.size(); i++) {
				temp = personContList.get(i).getTypeCd() + ":" + personContList.get(i).getNumber();
				text = text.concat(temp);
				if (prevTemp != null) {
					temp = prevTemp + " " + temp;
				}
				prevTemp = temp;
			}

			List<String> list = new ArrayList<String>();
			int index = 0;
			while (index < text.length()) {
				list.add(text.substring(index, Math.min(index + 34, text.length())));
				index = index + 34;
			}

			List<RoomingListVO> PICList = roomingListBO.getPICList(tourDepVO.getId());
			
			invoiceList = roomingListBO.getInvoiceList(tourDepVO.getId());

			int total = 0;
			String text1 = null, text2 = null, text3 = null, text4 = null;
			roomTypeList = lookUpBO.getLookUpCodeList("room_type");

			ArrayList<String> roomRemarksList = new ArrayList<String>();
			for (int m = 0; m < roomTypeList.size(); m++) {
				int cnt = 0;
				// Room Usage Calculation should group by Invoice No. & Pairing No.
				for (int i = 0; i < invoiceList.size(); i++) {
					invoicePaxInvIdList = roomingListBO.getInvoicePax(invoiceList.get(i).getId());

					int pairingNo = 0;
					for (InvoicePaxVO paxVO : invoicePaxInvIdList) {
						if (pairingNo != paxVO.getRoomPairingNo()) {
							if (roomTypeList.get(m).getCode().equalsIgnoreCase(paxVO.getRoomTypeCd())) {
								cnt += 1;
								total += 1;
								roomRemarksList.add(roomTypeList.get(m).getRemarks());
							}
						}

						pairingNo = paxVO.getRoomPairingNo();
					}
				}
				if (cnt != 0) {
					if (text2 == null) {
						text1 = cnt + roomTypeList.get(m).getCode();
					} else {
						text1 = text2 + " + " + cnt + roomTypeList.get(m).getCode();
					}

					text2 = text1;
				}
			}

			// uniqueRemarksList=roomingListBO.getuniqueRemarksList();
			List<Object> uniqueList = new ArrayList<Object>();
			uniqueList = roomingListBO.getuniqueList();

			for (Iterator iterator = uniqueList.iterator(); iterator.hasNext();) {
				Object obj = iterator.next();

				int cnt = 0;
				for (int y = 0; y < roomRemarksList.size(); y++) {
					if (obj.toString().equalsIgnoreCase(roomRemarksList.get(y).toString())) {
						cnt += 1;
					}
				}

				if (cnt != 0) {
					if (text4 == null)
						text3 = cnt + obj.toString();
					else
						text3 = text4 + " + " + cnt + obj.toString();

					text4 = text3;
				}
			}
			List<RoomingListLandOperatorVO> landOprList = roomingListLandOperatorBO.getSupplierList(tourDepVO.getId(), true);
			String landOperatorLabel = "LAND OPERATOR";
			String landOperatorPICLabel = "PIC";
			Map<Integer, String> addressListMap = new HashMap<Integer, String>();
			Map<Integer, String> roomUsageListMap = new HashMap<Integer, String>();
			Map<Integer, RoomingListVO> PICListMap = new HashMap<Integer, RoomingListVO>();

			addressListMap.put(0, landOperatorLabel);
			addressListMap.put(1, supplierVO.getFullName());
			int counter = 2;
			int PICno = 1;
			if (landOprList.size() != 0) {
				for (int i = 0; i < landOprList.size(); i++) {
					String contactNo = StringUtils.defaultIfBlank(landOprList.get(i).getContactNo(), "").replaceAll("\\r|\\n", " ");
					addressListMap.put(i + 2, landOperatorPICLabel + PICno + ": " + landOprList.get(i).getSalutation() + " " + StringUtils.defaultIfBlank(landOprList.get(i).getGivenName(), "") + " " + StringUtils.defaultIfBlank(landOprList.get(i).getLastName(), "") + " " + contactNo);
					counter++;
					PICno++;
				}
			}
			for (int i = 0; i < list.size(); i++) {
				addressListMap.put(i+counter, list.get(i).isEmpty() ? null : list.get(i));
			}

			roomUsageListMap.put(0, tourDepVO.getTravelInsPolicyS());
			roomUsageListMap.put(1, tourDepVO.getTravelInsPolicyF());
			roomUsageListMap.put(2, text2);
			roomUsageListMap.put(3, String.valueOf(total));
			roomUsageListMap.put(4, text3);

			for (int i = 0; i < PICList.size(); i++) {
				PICListMap.put(i+1, PICList.isEmpty() ? null : PICList.get(i));
			}
			if (addressListMap.size() > PICListMap.size() && addressListMap.size() > roomUsageListMap.size()) {
				rowNum += 1;
				Row rows = sheet.createRow(rowNum);
				Cell landOprLabel = rows.createCell(1);
				landOprLabel.setCellValue(addressListMap.get(0));
				Cell travelInsPolicySLabel = rows.createCell(7);
				travelInsPolicySLabel.setCellValue("TRAVEL INS POLICY (S):");
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
				Cell travelInsPolicyS = rows.createCell(11);
				travelInsPolicyS.setCellValue(roomUsageListMap.get(0));
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
				Cell picLabel = rows.createCell(18);
				picLabel.setCellValue("APPLE PIC:");
				for (int i = 1; i < addressListMap.size(); i++) {
					rowNum += 1;
					rows = sheet.createRow(rowNum);
					Cell addressLandOperator = rows.createCell(1);
					addressLandOperator.setCellValue(addressListMap.get(i));
					if (i == 1) {
						Cell travelInsPolicyFLabel = rows.createCell(7);
						travelInsPolicyFLabel.setCellValue("TRAVEL INS POLICY (F):");
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
						if (roomUsageListMap.get(1) != null) {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue(roomUsageListMap.get(1));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
						} else {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue("");
						}
					}
					if (i == 2) {
						Cell emptyCell = rows.createCell(7);
						emptyCell.setCellValue("");
					}
					if (i == 3) {
						if (roomUsageListMap.get(i) != null) {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("ROOM USAGE: " + roomUsageListMap.get(2) + " = " + " "
									+ roomUsageListMap.get(3) + "  R/M");
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("");
						}
					}
					if (i == 4) {
						if (roomUsageListMap.get(i) != null) {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("TOTAL PAX: " + roomUsageListMap.get(4));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("");
						}
					}
					if (i > 4) {
						Cell emptyRoomUsage = rows.createCell(7);
						emptyRoomUsage.setCellValue("");
					}
					if (PICListMap.get(i) != null) {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue(PICListMap.get(i).getDescription());
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue(PICListMap.get(i).getDescription2());
					} else {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue("");
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue("");
					}
				}
			} else if (PICListMap.size() > addressListMap.size() && PICListMap.size() > roomUsageListMap.size()) {
				rowNum += 1;
				Row rows = sheet.createRow(rowNum);
				Cell landOprLabel = rows.createCell(1);
				landOprLabel.setCellValue(addressListMap.get(0));
				Cell travelInsPolicySLabel = rows.createCell(7);
				travelInsPolicySLabel.setCellValue("TRAVEL INS POLICY (S):");
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
				Cell travelInsPolicyS = rows.createCell(11);
				travelInsPolicyS.setCellValue(roomUsageListMap.get(0));
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
				Cell picLabel = rows.createCell(18);
				picLabel.setCellValue("APPLE PIC:");
				for (int i = 1; i < PICListMap.size()+1; i++) {
					rowNum += 1;
					rows = sheet.createRow(rowNum);
					if (addressListMap.get(i) != null) {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue(addressListMap.get(i));
					} else {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue("");
					}
					if (i == 1) {
						Cell travelInsPolicyFLabel = rows.createCell(7);
						travelInsPolicyFLabel.setCellValue("TRAVEL INS POLICY (F):");
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
						if (roomUsageListMap.get(1) != null) {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue(roomUsageListMap.get(1));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
						} else {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue("");
						}
					}
					if (i == 2) {
						Cell emptyCell = rows.createCell(7);
						emptyCell.setCellValue("");
					}
					if (i == 3) {
						if (roomUsageListMap.get(i) != null) {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("ROOM USAGE:" + roomUsageListMap.get(2) + "=" + " "
									+ roomUsageListMap.get(3) + "  R/M");
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("");
						}
					}
					if (i == 4) {
						if (roomUsageListMap.get(i) != null) {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("TOTAL PAX:" + roomUsageListMap.get(4));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("");
						}
					}
					if (i > 4) {
						Cell emptyRoomUsage = rows.createCell(7);
						emptyRoomUsage.setCellValue("");
					}
					Cell PICName = rows.createCell(18);
					PICName.setCellValue(PICListMap.get(i).getDescription());
					Cell PICCont = rows.createCell(19);
					PICCont.setCellValue(PICListMap.get(i).getDescription2());
				}
			} else if (addressListMap.size() == roomUsageListMap.size() || roomUsageListMap.size() == PICListMap.size()) {
				rowNum += 1;
				Row rows = sheet.createRow(rowNum);
				Cell landOprLabel = rows.createCell(1);
				landOprLabel.setCellValue(addressListMap.get(0));
				Cell travelInsPolicySLabel = rows.createCell(7);
				travelInsPolicySLabel.setCellValue("TRAVEL INS POLICY (S):");
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
				Cell travelInsPolicyS = rows.createCell(11);
				travelInsPolicyS.setCellValue(roomUsageListMap.get(0));
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
				Cell picLabel = rows.createCell(18);
				picLabel.setCellValue("APPLE PIC:");
				for (int i = 1; i <= roomUsageListMap.size(); i++) {
					rowNum += 1;
					rows = sheet.createRow(rowNum);
					if (addressListMap.get(i) != null) {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue(addressListMap.get(i));
					} else {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue("");
					}
					if (i == 1) {
						Cell travelInsPolicyFLabel = rows.createCell(7);
						travelInsPolicyFLabel.setCellValue("TRAVEL INS POLICY (F):");
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
						if (roomUsageListMap.get(1) != null) {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue(roomUsageListMap.get(1));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
						} else {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue("");
						}
					}
					if (i == 2) {
						Cell emptyCell = rows.createCell(7);
						emptyCell.setCellValue("");
					}
					if (i == 3) {
						if (roomUsageListMap.get(i) != null) {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("ROOM USAGE:" + roomUsageListMap.get(2) + "=" + " "
									+ roomUsageListMap.get(3) + "  R/M");
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("");
						}
					}
					if (i == 4) {
						if (roomUsageListMap.get(i) != null) {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("TOTAL PAX:" + roomUsageListMap.get(4));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("");
						}
					}
					if (i > 4) {
						Cell emptyRoomUsage = rows.createCell(7);
						emptyRoomUsage.setCellValue("");
					}
					if (PICListMap.get(i) != null) {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue(PICListMap.get(i).getDescription());
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue(PICListMap.get(i).getDescription2());
					} else {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue("");
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue("");
					}
				}
			} else if (roomUsageListMap.size() > addressListMap.size() && roomUsageListMap.size() > PICListMap.size()) {
				rowNum += 1;
				Row rows = sheet.createRow(rowNum);
				Cell landOprLabel = rows.createCell(1);
				landOprLabel.setCellValue(addressListMap.get(0));
				Cell travelInsPolicySLabel = rows.createCell(7);
				travelInsPolicySLabel.setCellValue("TRAVEL INS POLICY (S):");
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
				Cell travelInsPolicyS = rows.createCell(11);
				travelInsPolicyS.setCellValue(roomUsageListMap.get(0));
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
				Cell picLabel = rows.createCell(18);
				picLabel.setCellValue("APPLE PIC:");
				for (int i = 1; i < roomUsageListMap.size(); i++) {
					rowNum += 1;
					rows = sheet.createRow(rowNum);
					if (addressListMap.get(i) != null) {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue(addressListMap.get(i));
					} else {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue("");
					}
					if (i == 1) {
						Cell travelInsPolicyFLabel = rows.createCell(7);
						travelInsPolicyFLabel.setCellValue("TRAVEL INS POLICY (F):");
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
						if (roomUsageListMap.get(1) != null) {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue(roomUsageListMap.get(1));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
						} else {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue("");
						}
					}
					if (i == 2) {
						Cell emptyCell = rows.createCell(7);
						emptyCell.setCellValue("");
					}
					if (i == 3) {
						if (roomUsageListMap.get(i) != null) {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("ROOM USAGE:" + roomUsageListMap.get(2) + "=" + " "
									+ roomUsageListMap.get(3) + "  R/M");
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("");
						}
					}
					if (i == 4) {
						if (roomUsageListMap.get(i) != null) {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("TOTAL PAX:" + roomUsageListMap.get(4));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("");
						}
					}
					if (i > 4) {
						Cell emptyRoomUsage = rows.createCell(7);
						emptyRoomUsage.setCellValue("");
					}
					if (PICListMap.get(i) != null) {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue(PICListMap.get(i).getDescription());
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue(PICListMap.get(i).getDescription2());
					} else {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue("");
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue("");
					}
				}
			} else if(addressListMap.size()==PICListMap.size()) {
				rowNum += 1;
				Row rows = sheet.createRow(rowNum);
				Cell landOprLabel = rows.createCell(1);
				landOprLabel.setCellValue(addressListMap.get(0));
				Cell travelInsPolicySLabel = rows.createCell(7);
				travelInsPolicySLabel.setCellValue("TRAVEL INS POLICY (S):");
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
				Cell travelInsPolicyS = rows.createCell(11);
				travelInsPolicyS.setCellValue(roomUsageListMap.get(0));
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
				Cell picLabel = rows.createCell(18);
				picLabel.setCellValue("APPLE PIC:");
				for (int i = 1; i <= addressListMap.size(); i++) {
					rowNum += 1;
					rows = sheet.createRow(rowNum);
					if (addressListMap.get(i) != null) {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue(addressListMap.get(i));
					} else {
						Cell addressLandOperator = rows.createCell(1);
						addressLandOperator.setCellValue("");
					}
					if (i == 1) {
						Cell travelInsPolicyFLabel = rows.createCell(7);
						travelInsPolicyFLabel.setCellValue("TRAVEL INS POLICY (F):");
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 10));
						if (roomUsageListMap.get(1) != null) {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue(roomUsageListMap.get(1));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 11, 15));
						} else {
							Cell travelInsPolicyF = rows.createCell(11);
							travelInsPolicyF.setCellValue("");
						}
					}
					if (i == 2) {
						Cell emptyCell = rows.createCell(7);
						emptyCell.setCellValue("");
					}
					if (i == 3) {
						if (roomUsageListMap.get(i) != null) {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("ROOM USAGE:" + roomUsageListMap.get(2) + "=" + " "
									+ roomUsageListMap.get(3) + "  R/M");
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell roomUsage = rows.createCell(7);
							roomUsage.setCellValue("");
						}
					}
					if (i == 4) {
						if (roomUsageListMap.get(i) != null) {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("TOTAL PAX:" + roomUsageListMap.get(4));
							sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 12));
						} else {
							Cell totalPax = rows.createCell(7);
							totalPax.setCellValue("");
						}
					}
					if (i > 4) {
						Cell emptyRoomUsage = rows.createCell(7);
						emptyRoomUsage.setCellValue("");
					}
					if (PICListMap.get(i) != null) {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue(PICListMap.get(i).getDescription());
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue(PICListMap.get(i).getDescription2());
					} else {
						Cell PICName = rows.createCell(18);
						PICName.setCellValue("");
						Cell PICCont = rows.createCell(19);
						PICCont.setCellValue("");
					}
				}
			}
			
			for (int j = 0; j < invoiceList.size(); j++) {
				invoicePaxInvIdList = roomingListBO.getInvoicePax(invoiceList.get(j).getId());
				for (int l = 0; l < invoicePaxInvIdList.size(); l++) {
					lookupItemrmList = lookUpBO.getLookUpCodeList("room_type");
					for (LookupItemVO vo : lookupItemrmList) {
						if (vo.getCode() == invoicePaxInvIdList.get(l).getRoomTypeCd()) {
						}
					}

				}
			}
			
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.setContentType("application/vnd.ms-excel");
			httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + tourDepVO.getCode() + "_"
					+ new SimpleDateFormat("dd-MMM-yyyy").format(tourDepVO.getDtDep()) + ".xls");

			ServletOutputStream out = httpServletResponse.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
			out = null;

			FacesContext.getCurrentInstance().responseComplete();

		} catch (Throwable t) {
			errorResult(t);
		}

	}
	
	private static void setRegionBorder(HSSFSheet sheet, int row, int columnFrom, int columnTo) {
        CellRangeAddress region = new CellRangeAddress(row, row, columnFrom, columnTo);
        sheet.addMergedRegion(region);
        final short border = CellStyle.BORDER_THIN;
        HSSFWorkbook wb = sheet.getWorkbook();
        RegionUtil.setBorderBottom(border, region, sheet, wb);
        RegionUtil.setBorderTop(border, region, sheet, wb);
        RegionUtil.setBorderLeft(border, region, sheet, wb);
        RegionUtil.setBorderRight(border, region, sheet, wb);
        RegionUtil.setBottomBorderColor(HSSFColor.BLACK.index, region, sheet, wb);
        RegionUtil.setTopBorderColor(HSSFColor.BLACK.index, region, sheet, wb);
        RegionUtil.setLeftBorderColor(HSSFColor.BLACK.index, region, sheet, wb);
        RegionUtil.setRightBorderColor(HSSFColor.BLACK.index, region, sheet, wb);
    }

	private void populateTourLeaderAndManager(Row row, CellStyle combined, CellStyle borderStyle, RoomingListVO vo, String personTitle,
			int rowIndex) {
		int idx = 1;
		for (int m = 0; m < excelPrintColumnCount; m++) {
			Cell cell171 = row.createCell(idx);
			switch (m) {
			case 0:
				cell171.setCellValue(rowIndex);
				cell171.setCellStyle(combined);
				break;
			case 1:
				cell171.setCellValue("");
				cell171.setCellStyle(combined);
				break;
			case 2:
				cell171.setCellValue("");
				cell171.setCellStyle(combined);
				break;
			case 3:
				cell171.setCellValue(vo.getDescription());
				cell171.setCellStyle(borderStyle);
				break;
			case 4:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 5:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 6:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 7:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 8:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 9:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 10:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 11:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 12:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 13:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 14:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 15:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 16:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 17:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 18:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 19:
				cell171.setCellValue(vo.getDescription2());
				cell171.setCellStyle(borderStyle);
				break;
			case 20:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 21:
				cell171.setCellValue(personTitle);
				cell171.setCellStyle(borderStyle);
				break;
			case 22:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 23:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			case 24:
				cell171.setCellValue("");
				cell171.setCellStyle(borderStyle);
				break;
			}
			idx++;
		}
	}

	/**
	 * @return the corporateVO
	 */
	public CorporateVO getCorporateVO() {
		return corporateVO;
	}

	/**
	 * @param corporateVO
	 *            the corporateVO to set
	 */
	public void setCorporateVO(CorporateVO corporateVO) {
		this.corporateVO = corporateVO;
	}

	/**
	 * @return the corpAddrVO
	 */
	public CorAddressVO getCorpAddrVO() {
		return corpAddrVO;
	}

	/**
	 * @param corpAddrVO
	 *            the corpAddrVO to set
	 */
	public void setCorpAddrVO(CorAddressVO corpAddrVO) {
		this.corpAddrVO = corpAddrVO;
	}

	/**
	 * @return the roomTypeList
	 */
	public List<LookupItemVO> getRoomTypeList() {
		return roomTypeList;
	}

	/**
	 * @param roomTypeList
	 *            the roomTypeList to set
	 */
	public void setRoomTypeList(List<LookupItemVO> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	/**
	 * @return the uniqueRemarksList
	 */
	public List<LookupItemVO> getUniqueRemarksList() {
		return uniqueRemarksList;
	}

	/**
	 * @param uniqueRemarksList
	 *            the uniqueRemarksList to set
	 */
	public void setUniqueRemarksList(List<LookupItemVO> uniqueRemarksList) {
		this.uniqueRemarksList = uniqueRemarksList;
	}

	public LazyDataModel<TourDepartureVO> getLazyDMTourDepWithInvoicePaxVO() {
		return lazyDMTourDepWithInvoicePaxVO;
	}

	public void setLazyDMTourDepWithInvoicePaxVO(LazyDataModel<TourDepartureVO> lazyDMTourDepWithInvoicePaxVO) {
		this.lazyDMTourDepWithInvoicePaxVO = lazyDMTourDepWithInvoicePaxVO;
	}

	public List<InvoicePaxVO> getInvoicePaxList() {
		return invoicePaxList;
	}

	public void setInvoicePaxList(List<InvoicePaxVO> invoicePaxList) {
		this.invoicePaxList = invoicePaxList;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void settourDepVOList(List<TourDepartureVO> tourDepVOList) {
		this.tourDepVOList = tourDepVOList;
	}

	public List<TourDepartureVO> gettourDepVOList() {
		return tourDepVOList;
	}

	public void setSupplierList(List<SupplierVO> supplierList) {
		this.supplierList = supplierList;
	}

	public List<SupplierVO> getSupplierList() {
		return supplierList;
	}

	public void setTourDepVO(TourDepartureVO tourDepVO) {
		this.tourDepVO = tourDepVO;
	}

	public TourDepartureVO getTourDepVO() {
		return tourDepVO;
	}

	public void setTourPkgVO(TourPackageVO tourPkgVO) {
		this.tourPkgVO = tourPkgVO;
	}

	public TourPackageVO getTourPkgVO() {
		return tourPkgVO;
	}

	public void setRLtourGuideVO(RoomingListVO RLtourGuideVO) {
		this.RLtourGuideVO = RLtourGuideVO;
	}

	public RoomingListVO getRLtourGuideVO() {
		return RLtourGuideVO;
	}

	public void setRLsupplierVO(RoomingListVO RLsupplierVO) {
		this.RLsupplierVO = RLsupplierVO;
	}

	public RoomingListVO getRLsupplierVO() {
		return RLsupplierVO;
	}

	public void setSupplierVO(SupplierVO supplierVO) {
		this.supplierVO = supplierVO;
	}

	public SupplierVO getSupplierVO() {
		return supplierVO;
	}

	public void setPICList(List<RoomingListVO> PICList) {
		this.PICList = PICList;
	}

	public List<RoomingListVO> getPICList() {
		return PICList;
	}

	public List<EmployeeViewVO> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @return the companyContList
	 */
	public List<CompanyContactVO> getCompanyContList() {
		return companyContList;
	}

	/**
	 * @param companyContList
	 *            the companyContList to set
	 */
	public void setCompanyContList(List<CompanyContactVO> companyContList) {
		this.companyContList = companyContList;
	}

	/**
	 * @param employeeList
	 *            the employeeList to set
	 */
	public void setEmployeeList(List<EmployeeViewVO> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * @return the tourHotelList
	 */
	public List<TourHotelVO> getTourHotelList() {
		return tourHotelList;
	}

	/**
	 * @param tourHotelList
	 *            the tourHotelList to set
	 */
	public void setTourHotelList(List<TourHotelVO> tourHotelList) {
		this.tourHotelList = tourHotelList;
	}

	/**
	 * @return the hotelAddrList
	 */
	public List<HotelAddressVO> getHotelAddrList() {
		return hotelAddrList;
	}

	/**
	 * @param hotelAddrList
	 *            the hotelAddrList to set
	 */
	public void setHotelAddrList(List<HotelAddressVO> hotelAddrList) {
		this.hotelAddrList = hotelAddrList;
	}

	/**
	 * @return the hotelContList
	 */
	public List<HotelContactVO> getHotelContList() {
		return hotelContList;
	}

	/**
	 * @param hotelContList
	 *            the hotelContList to set
	 */
	public void setHotelContList(List<HotelContactVO> hotelContList) {
		this.hotelContList = hotelContList;
	}

	/**
	 * @return the addressVO
	 */
	public AddressVO getAddressVO() {
		return addressVO;
	}

	/**
	 * @param addressVO
	 *            the addressVO to set
	 */
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}

	/**
	 * @return the personContList
	 */
	public List<PersonContactVO> getPersonContList() {
		return personContList;
	}

	/**
	 * @param personContList
	 *            the personContList to set
	 */
	public void setPersonContList(List<PersonContactVO> personContList) {
		this.personContList = personContList;
	}

	/**
	 * @return the invoiceList
	 */
	public List<InvoiceVO> getInvoiceList() {
		return invoiceList;
	}

	/**
	 * @param invoiceList
	 *            the invoiceList to set
	 */
	public void setInvoiceList(List<InvoiceVO> invoiceList) {
		this.invoiceList = invoiceList;
	}

	/**
	 * @return the custVO
	 */
	public CustomerVO getCustVO() {
		return custVO;
	}

	/**
	 * @param custVO
	 *            the custVO to set
	 */
	public void setCustVO(CustomerVO custVO) {
		this.custVO = custVO;
	}

	/**
	 * @return the personVO
	 */
	public PersonVO getPersonVO() {
		return personVO;
	}

	/**
	 * @param personVO
	 *            the personVO to set
	 */
	public void setPersonVO(PersonVO personVO) {
		this.personVO = personVO;
	}

	/**
	 * @return the invoicePaxInvIdList
	 */
	public List<InvoicePaxVO> getInvoicePaxInvIdList() {
		return invoicePaxInvIdList;
	}

	/**
	 * @param invoicePaxInvIdList
	 *            the invoicePaxInvIdList to set
	 */
	public void setInvoicePaxInvIdList(List<InvoicePaxVO> invoicePaxInvIdList) {
		this.invoicePaxInvIdList = invoicePaxInvIdList;
	}

	/**
	 * @return the personLangList
	 */
	public List<PersonLangVO> getPersonLangList() {
		return personLangList;
	}

	/**
	 * @param personLangList
	 *            the personLangList to set
	 */
	public void setPersonLangList(List<PersonLangVO> personLangList) {
		this.personLangList = personLangList;
	}

	/**
	 * @return the personMealList
	 */
	public List<PersonMealVO> getPersonMealList() {
		return personMealList;
	}

	/**
	 * @param personMealList
	 *            the personMealList to set
	 */
	public void setPersonMealList(List<PersonMealVO> personMealList) {
		this.personMealList = personMealList;
	}

	/**
	 * @return the personContPList
	 */
	public List<PersonContactVO> getPersonContPList() {
		return personContPList;
	}

	/**
	 * @param personContPList
	 *            the personContPList to set
	 */
	public void setPersonContPList(List<PersonContactVO> personContPList) {
		this.personContPList = personContPList;
	}

	/**
	 * @return the lookupItemrmList
	 */
	public List<LookupItemVO> getLookupItemrmList() {
		return lookupItemrmList;
	}

	/**
	 * @param lookupItemrmList
	 *            the lookupItemrmList to set
	 */
	public void setLookupItemrmList(List<LookupItemVO> lookupItemrmList) {
		this.lookupItemrmList = lookupItemrmList;
	}

	public EmployeeViewVO getEmployeeViewVO() {
		return employeeViewVO;
	}

	public void setEmployeeViewVO(EmployeeViewVO employeeViewVO) {
		this.employeeViewVO = employeeViewVO;
	}

	public List<RoomingListVO> getTourLeaderList() {
		return tourLeaderList;
	}

	public void setTourLeaderList(List<RoomingListVO> tourLeaderList) {
		this.tourLeaderList = tourLeaderList;
	}

	public List<RoomingListVO> getTourManagerList() {
		return tourManagerList;
	}

	public void setTourManagerList(List<RoomingListVO> tourManagerList) {
		this.tourManagerList = tourManagerList;
	}

	public RoomingListVO getRLVO() {
		return RLVO;
	}

	public void setRLVO(RoomingListVO rLVO) {
		RLVO = rLVO;
	}

	public String getTypeCd() {
		return typeCd;
	}

	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}

	public List<ExOrderVO> getExOrderVOList() {
		return exOrderVOList;
	}

	public void setExOrderVOList(List<ExOrderVO> exOrderVOList) {
		this.exOrderVOList = exOrderVOList;
	}

	public List<AirLineDetailsVO> getAirLineDetailsVOList() {
		return airLineDetailsVOList;
	}

	public void setAirLineDetailsVOList(List<AirLineDetailsVO> airLineDetailsVOList) {
		this.airLineDetailsVOList = airLineDetailsVOList;
	}

	public List<HotelTourVO> getHotelTourVOList() {
		return hotelTourVOList;
	}

	public void setHotelTourVOList(List<HotelTourVO> hotelTourVOList) {
		this.hotelTourVOList = hotelTourVOList;
	}

	public String getExorderType() {
		return exorderType;
	}

	public void setExorderType(String exorderType) {
		this.exorderType = exorderType;
	}

	public RoomingListVO getRLAirlineVO() {
		return RLAirlineVO;
	}

	public void setRLAirlineVO(RoomingListVO rLAirlineVO) {
		RLAirlineVO = rLAirlineVO;
	}

	public RoomingListVO getRLHotelVO() {
		return RLHotelVO;
	}

	public void setRLHotelVO(RoomingListVO rLHotelVO) {
		RLHotelVO = rLHotelVO;
	}

	public List<HotelVO> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HotelVO> hotelList) {
		this.hotelList = hotelList;
	}

	public TourHotelVO getTourHotelVO() {
		return tourHotelVO;
	}

	public void setTourHotelVO(TourHotelVO tourHotelVO) {
		this.tourHotelVO = tourHotelVO;
	}

	public HotelVO getHotelVO() {
		return hotelVO;
	}

	public void setHotelVO(HotelVO hotelVO) {
		this.hotelVO = hotelVO;
	}

	public HotelTourVO getHotelTourVO() {
		return hotelTourVO;
	}

	public void setHotelTourVO(HotelTourVO hotelTourVO) {
		this.hotelTourVO = hotelTourVO;
	}

	public List<RoomingListVO> getIdHotelList() {
		return idHotelList;
	}

	public void setIdHotelList(List<RoomingListVO> idHotelList) {
		this.idHotelList = idHotelList;
	}

	public RoomingListLandOperatorVO getRoomingListLandOperatorVO() {
		return roomingListLandOperatorVO;
	}

	public void setRoomingListLandOperatorVO(RoomingListLandOperatorVO roomingListLandOperatorVO) {
		this.roomingListLandOperatorVO = roomingListLandOperatorVO;
	}

	public RoomingListLandOperatorContVO getRoomingListLandOperatorContVO() {
		return roomingListLandOperatorContVO;
	}

	public void setRoomingListLandOperatorContVO(RoomingListLandOperatorContVO roomingListLandOperatorContVO) {
		this.roomingListLandOperatorContVO = roomingListLandOperatorContVO;
	}

	public RoomingListVO getRLLandOperatorVO() {
		return RLLandOperatorVO;
	}

	public void setRLLandOperatorVO(RoomingListVO rLLandOperatorVO) {
		RLLandOperatorVO = rLLandOperatorVO;
	}

	public boolean isAddLandOperator() {
		return isAddLandOperator;
	}

	public void setAddLandOperator(boolean isAddLandOperator) {
		this.isAddLandOperator = isAddLandOperator;
	}

	public List<RoomingListLandOperatorVO> getRoomingListLandOprVOList() {
		return roomingListLandOprVOList;
	}

	public void setRoomingListLandOprVOList(List<RoomingListLandOperatorVO> roomingListLandOprVOList) {
		this.roomingListLandOprVOList = roomingListLandOprVOList;
	}

	public List<CorContactVO> getCorContactVOList() {
		return corContactVOList;
	}

	public void setCorContactVOList(List<CorContactVO> corContactVOList) {
		this.corContactVOList = corContactVOList;
	}
	
	public GenAddUpdDelVO<RoomingListLandOperatorVO> getLandOprAUDList() {
		return landOprAUDList;
	}

	public void setLandOprAUDList(GenAddUpdDelVO<RoomingListLandOperatorVO> landOprAUDList) {
		this.landOprAUDList = landOprAUDList;
	}

	public boolean isAddLandOperatorCont() {
		return isAddLandOperatorCont;
	}

	public void setAddLandOperatorCont(boolean isAddLandOperatorCont) {
		this.isAddLandOperatorCont = isAddLandOperatorCont;
	}

}
