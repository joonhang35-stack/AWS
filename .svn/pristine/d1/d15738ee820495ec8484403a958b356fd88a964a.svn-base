package com.bcs.zsg.product.web.bean;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.component.security.vo.UserRoleViewVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.product.bo.TicketingBO;
import com.bcs.zsg.product.helper.ProductConstant;
import com.bcs.zsg.product.vo.AirlineVO;
import com.bcs.zsg.product.vo.TicketingVO;
import com.bcs.zsg.product.vo.TourDepartureVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.InvPmntAuthVO;

public class TicketingBean extends TourPackageBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient TicketingBO ticketingBO;
	
	private List<AirlineVO> ticketingList;
	private List<TourDepartureViewVO> tourDepViewList;
	private TourDepartureViewVO tourDepViewHistVO;
	private TicketingVO ticketingVO;
	private AirlineVO airlineVO;
	private InvPmntAuthVO rolesAuthVO;

	/**
	 * 
	 */
	public void resetTicketingForm() {
		ticketingVO = new TicketingVO();
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			// initial search param
			initSearchParam();
			searchParamVO.setObj1(ProductConstant.TYPE_TICKETING);
			resetForm();
			resetTicketingForm();
			loadAirlineList();
			loadTourCodeList();
			loadTicketingList(null);
			loadAuthority();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Add tour code
	 */
	public void addTourCode() {
		try {
			if (CollectionUtils.isNotEmpty(tourDepViewList)) {
				for (TourDepartureViewVO vo : tourDepViewList) {
					if (vo.getCode().equalsIgnoreCase(tourDepVO.getCode())) throw new BusinessException(CommonErrConstant.ERR_TOUR_DEP_EXISTED);
				}
			}
			
			tourPkgVO.setIdTourTheme(1L);
			tourPkgVO.setTypeCd(ProductConstant.TYPE_TICKETING);
			tourPkgVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourPkgVO.setReserved3(getSessionInfoBean().getEmployeeVO().getDepartment());
			tourPkgBO.insertVO(tourPkgVO);
			
			tourDepVO.setIdTourPkg(tourPkgVO.getId());
			tourDepVO.setDtDep(new Date());
			tourDepVO.setTourStatusCd("A");
			tourDepVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourPkgBO.insertVO(tourDepVO);
			resetForm();
			loadTourCodeList();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update tour code
	 */
	public void updTourCode() {
		try {
			if (!tourDepVO.getCode().equals(tourDepViewHistVO.getCode())) {
				if (CollectionUtils.isNotEmpty(tourDepViewList)) {
					for (TourDepartureViewVO vo : tourDepViewList) {
						if (tourDepVO.getCode().equalsIgnoreCase(vo.getCode())) throw new BusinessException(CommonErrConstant.ERR_TOUR_DEP_EXISTED);
					}
				}
			}
			
			tourPkgBO.updateVO(tourPkgVO);
			tourPkgBO.updateVO(tourDepVO);
			resetForm();
			loadTourCodeList();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete tour code
	 */
	public void delTourCode() {
		try {
			if (tourPkgBO.isInvEOIssued(tourDepVO, null)) throw new BusinessException(CommonErrConstant.ERR_MICE_ISSUED_INV_EO);
			tourPkgVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
			tourPkgBO.updateVO(tourPkgVO);
			tourDepVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
			tourPkgBO.updateVO(tourDepVO);
			resetForm();
			loadTourCodeList();
			successResult();
			
		} catch (Throwable t) {
			resetForm();
			errorResult(t);
		}
	}
	
	/**
	 * Add Ticket
	 */
	public void addTicket() {
		try {
			ticketingVO.setStatusCode(CommonConstant.STATUS_CD_ACTIVE);
			tourPkgBO.insertVO(ticketingVO);
			// load ticketing list
			loadTicketingList(airlineVO);
			// reset form
			resetTicketingForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update ticket
	 */
	public void updTicket() {
		try {
			tourPkgBO.updateVO(ticketingVO);
			resetTicketingForm();
			loadTicketingList(airlineVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Delete ticket
	 */
	public void delTicket() {
		try {
			ticketingVO.setStatusCode(CommonConstant.STATUS_CD_INACTIVE);
			tourPkgBO.deleteVO(ticketingVO);
			resetTicketingForm();
			loadTicketingList(airlineVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void handleTourDepSelect(TourDepartureViewVO vo) {
		try {
			tourPkgVO = vo.getTourPkgVO();
			tourDepVO = (TourDepartureVO) vo.clone();
			tourDepVO.setIdTourPkg(tourPkgVO.getId());
			tourDepViewHistVO = (TourDepartureViewVO) vo.clone();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * 
	 * @param event
	 */
	public void onTabChanged(TabChangeEvent event) {
		try {
			loadTicketingList((AirlineVO) event.getData());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**********
	 * HELPER *
	 **********/
	
	/*
	 * 
	 * @throws BusinessException
	 */
	private void loadTicketingList(AirlineVO vo) throws BusinessException {
		resetFilteredObjList();
		airlineVO = vo;
		ticketingList = ticketingBO.getTicketingList(vo);
	}
	
	/*
	 * 
	 * @throws BusinessException
	 */
	private void loadTourCodeList() throws BusinessException {
		tourDepViewList = ticketingBO.getTourCodeList(getSessionInfoBean().getEmployeeVO());
	}
	
	private void loadAuthority() throws BusinessException {
		List<UserRoleViewVO> userRoleList = this.getUserInfo().getRoleList();
		
		rolesAuthVO = new InvPmntAuthVO();
		if (CollectionUtils.isNotEmpty(userRoleList)) {
			for (UserRoleViewVO userRoleVO : userRoleList) {
				if (userRoleVO.getRoleCode().equals(SalesConstant.SALES_MANAGER_ROLE)) {
					rolesAuthVO.setSalesManager(true);
					break;
				}
			}
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the ticketingList
	 */
	public List<AirlineVO> getTicketingList() {
		return ticketingList;
	}

	/**
	 * @param ticketingList the ticketingList to set
	 */
	public void setTicketingList(List<AirlineVO> ticketingList) {
		this.ticketingList = ticketingList;
	}

	/**
	 * @return the tourDepViewList
	 */
	public List<TourDepartureViewVO> getTourDepViewList() {
		return tourDepViewList;
	}

	/**
	 * @param tourDepViewList the tourDepViewList to set
	 */
	public void setTourDepViewList(List<TourDepartureViewVO> tourDepViewList) {
		this.tourDepViewList = tourDepViewList;
	}

	/**
	 * @return the ticketingVO
	 */
	public TicketingVO getTicketingVO() {
		return ticketingVO;
	}

	/**
	 * @param ticketingVO the ticketingVO to set
	 */
	public void setTicketingVO(TicketingVO ticketingVO) {
		this.ticketingVO = ticketingVO;
	}

	public InvPmntAuthVO getRolesAuthVO() {
		return rolesAuthVO;
	}

	public void setRolesAuthVO(InvPmntAuthVO rolesAuthVO) {
		this.rolesAuthVO = rolesAuthVO;
	}
	
}
