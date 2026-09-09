package com.bcs.zsg.acct.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.bcs.zsg.acct.dao.FinancialPeriodDAO;
import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.service.HomeService;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.vo.BaseVO;
import com.bcs.zsg.gst.service.GSTService;
import com.bcs.zsg.maintenance.vo.CompanyVO;

public class FinancialPeriodServiceImpl implements FinancialPeriodService {

	@Autowired
	private FinancialPeriodDAO finPeriodDAO;
	@Autowired
	private GSTService gstService;
	@Autowired
	private HomeService homeService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		finPeriodDAO.insert(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		finPeriodDAO.update(vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#getfinPeriodList(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<FinancialPeriodVO> getFinPeriodList(Long idCompany, String year) throws BusinessException {
		return finPeriodDAO.getFinPeriodList(idCompany, year);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#addFinPeriod(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public void addFinPeriod(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		String year = (String) searchParamVO.getObj1();
		int option = Integer.parseInt((String) searchParamVO.getObj2());
		int month = Integer.parseInt((String) searchParamVO.getObj3());
		
		if (finPeriodDAO.isClosedPeriodExisted(idCompany, year)) throw new BusinessException(CommonErrConstant.ERR_ACCT_CLOSE_PERIOD_EXISTED);
		// remove existing record
		finPeriodDAO.delFinPeriod(idCompany, year);
		
		// copy previous period
		if (option == 1) {
			List<FinancialPeriodVO> finPeriodList = getFinPeriodList(idCompany, String.valueOf(Integer.parseInt(year) - 1));
			if (CollectionUtils.isEmpty(finPeriodList)) throw new BusinessException(CommonErrConstant.ERR_ACCT_FINANCAIL_PERIOD_NOT_EXISTED);
			
			for (FinancialPeriodVO vo : finPeriodList) {
				FinancialPeriodVO finPeriodVO = new FinancialPeriodVO();
				finPeriodVO.setIdCompany(idCompany);
				finPeriodVO.setYear(year);
				finPeriodVO.setSeqNo(vo.getSeqNo());
				finPeriodVO.setDesc(vo.getDesc());
				
				Calendar cal = Calendar.getInstance();
				// set start date
				cal.setTime(vo.getDtStart());
				cal.add(Calendar.YEAR, 1);
				finPeriodVO.setDtStart(cal.getTime());
				// set end date
				//cal.setTime(vo.getDtEnd());
				//cal.add(Calendar.YEAR, 1);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				finPeriodVO.setDtEnd(cal.getTime());
				
				finPeriodVO.setStatus(1);
				finPeriodVO.setPeriodType(vo.getPeriodType());
				// insert vo
				insertVO(finPeriodVO);
			}
			
		// generate monthly period
		} else if (option == 2) {
			// check month included previous financial period or not
			if (finPeriodDAO.isMonthIncludedPrevPeriod(idCompany, year, month, option)) throw new BusinessException(CommonErrConstant.ERR_ACCT_FIN_PERIOD_MONTH_EXISTED);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.YEAR, Integer.parseInt(year));
			
			for (int i = 0 ; i < 12 ; i++) {
				FinancialPeriodVO finPeriodVO = new FinancialPeriodVO();
				finPeriodVO.setIdCompany(idCompany);
				finPeriodVO.setYear(year);
				finPeriodVO.setSeqNo(i + 1);
				
				if (i == 0) cal.set(Calendar.MONTH, month);
				else cal.add(Calendar.MONTH, 1);
				finPeriodVO.setDesc(getMonthString(cal.get(Calendar.MONTH)));
				
				// set start date
				cal.set(Calendar.DATE, 1);
				finPeriodVO.setDtStart(cal.getTime());
				// set end date
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				finPeriodVO.setDtEnd(cal.getTime());
				
				finPeriodVO.setStatus(1);
				finPeriodVO.setPeriodType(0);
				// insert vo
				insertVO(finPeriodVO);
			}
			
		// generate quaterly period
		} else {
			// check month included previous financial period or not
			if (finPeriodDAO.isMonthIncludedPrevPeriod(idCompany, year, month, option)) throw new BusinessException(CommonErrConstant.ERR_ACCT_FIN_PERIOD_MONTH_EXISTED);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.set(Calendar.YEAR, Integer.parseInt(year));
			
			for (int i = 0 ; i < 4 ; i++) {
				FinancialPeriodVO finPeriodVO = new FinancialPeriodVO();
				finPeriodVO.setIdCompany(idCompany);
				finPeriodVO.setYear(year);
				finPeriodVO.setSeqNo(i + 1);
				finPeriodVO.setDesc(getQuarterString(i));
				
				if (i == 0) cal.set(Calendar.MONTH, month);
				else cal.add(Calendar.MONTH, 1);
				// set start date
				cal.set(Calendar.DATE, 1);
				finPeriodVO.setDtStart(cal.getTime());
				// set end date
				cal.add(Calendar.MONTH, 2);
				cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				finPeriodVO.setDtEnd(cal.getTime());
				
				finPeriodVO.setStatus(1);
				finPeriodVO.setPeriodType(1);
				// insert vo
				insertVO(finPeriodVO);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#getfinPeriod(java.lang.Long)
	 */
	@Override
	public FinancialPeriodVO getFinPeriod(Long idCompany) throws BusinessException {
		return finPeriodDAO.getFinPeriod(idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#getFinPeriodLock(java.lang.Long)
	 */
	@Override
	public FinancialPeriodLockVO getFinPeriodLock(Long idCompany) throws BusinessException {
		return finPeriodDAO.getFinPeriodLock(idCompany);
	}

	/**********
	 * HELPER *
	 **********/
	
	/*
	 * Return month string
	 * @param month
	 * @return
	 */
	private String getMonthString(int month) {
		switch (month) {
		case 0: return "January";
		case 1: return "February";
		case 2: return "March";
		case 3: return "April";
		case 4: return "May";
		case 5: return "June";
		case 6: return "July";
		case 7: return "August";
		case 8: return "September";
		case 9: return "October";
		case 10: return "November";
		case 11: return "December";
		}
		return null;
	}

	/*
	 * 
	 * @param quarter
	 * @return
	 */
	private String getQuarterString(int quarter) {
		switch (quarter) {
		case 0: return "1st Quarter";
		case 1: return "2nd Quarter";
		case 2: return "3rd Quarter";
		case 3: return "4th Quarter";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.service.FinancialPeriodService#updFinPeriod(java.lang.Long, java.lang.String, com.bcs.zsg.acct.vo.FinancialPeriodVO)
	 */
	@Override
	public void updFinPeriod(Long idCompany, String userName, FinancialPeriodVO finPeriodVO) throws BusinessException {
		//Check date of GST filling for company
		//if (!gstService.isGSTPeriod(idCompany, finPeriodVO.getDtStart())) {
			finPeriodDAO.calculateFinPeriodBalance(homeService.getCompanyInfo(idCompany), userName, finPeriodVO);
			finPeriodVO.setStatus(-1);
		//} else finPeriodVO.setStatus(0);
		
		// close financial period
		finPeriodDAO.update(finPeriodVO);
		
		//Handle to run the following serviceTask only when closed period
		//gstService.setClosedMonthParams(idCompany, userName, finPeriodVO.getDtStart());
		//new Thread((Runnable) gstService).start();
	}
	
	@Override
	public void updateFinPeriod(Long idCompany, String userName, Date dateFrom, Date dateTo, boolean finalSubmit) throws BusinessException {
		FinancialPeriodVO finPeriodVO = finPeriodDAO.getFinPeriodLock(idCompany, dateFrom, dateTo);
		CompanyVO companyVO = homeService.getCompanyInfo(idCompany);
		
		if(finalSubmit) {
			//Final closing of financial period which account manager cant change as well
			finPeriodDAO.calculateFinPeriodBalance(companyVO, userName, finPeriodVO);
		} else {
			finPeriodVO.setUpdatedBy(userName);
			
			//Enable/Disable account manager privileage to edit data
			if (finPeriodVO.getStatus().intValue() == 0) {
				finPeriodDAO.calculateFinPeriodBalance(companyVO, userName, finPeriodVO);
				finPeriodVO.setStatus(-1);
				
			} else {
				finPeriodVO.setStatus(0);
			}
			
			finPeriodDAO.update(finPeriodVO);
		}
	}
	
	@Override
	public boolean isDateInFinPeriodClosed(Long idCompany, Date date) throws BusinessException {
		return finPeriodDAO.isDateInFinPeriodClosed(idCompany, date);
	}
}
