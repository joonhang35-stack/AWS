package com.bcs.zsg.product.dao;

import java.util.List;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public interface TourMICEDAO extends BaseDAO {

	/**
	 * 
	 * @param searchParamVO
	 * @param employeeVO 
	 * @return
	 * @throws BusinessException
	 */
	public List<TourDepartureViewVO> getTourMICEList(SearchParamVO searchParamVO, EmployeeVO employeeVO) throws BusinessException;

	/**
	 * 
	 * @param regions
	 * @return
	 * @throws BusinessException
	 */
	public List<RegionVO> getMICERegionList(String regions) throws BusinessException;

	/**
	 * 
	 * @param countries
	 * @return
	 * @throws BusinessException
	 */
	public List<CountryVO> getMICECountryList(String countries) throws BusinessException;
}
