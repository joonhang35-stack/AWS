package com.bcs.zsg.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.dao.TourMICEDAO;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class TourMICEServiceImpl implements TourMICEService {

	@Autowired
	private TourMICEDAO miceDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourPackageService#getTourMICEList(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@Override
	public List<TourDepartureViewVO> getTourMICEList(SearchParamVO searchParamVO, EmployeeVO employeeVO) throws BusinessException {
		return miceDAO.getTourMICEList(searchParamVO, employeeVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourMICEService#getMICERegionList(java.lang.String)
	 */
	@Override
	public List<RegionVO> getMICERegionList(String regions) throws BusinessException {
		return miceDAO.getMICERegionList(regions);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.service.TourMICEService#getMICECountryList(java.lang.String)
	 */
	@Override
	public List<CountryVO> getMICECountryList(String countries) throws BusinessException {
		return miceDAO.getMICECountryList(countries);
	}
}
