package com.bcs.zsg.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.vo.EmployeeVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.RegionVO;
import com.bcs.zsg.product.service.TourMICEService;
import com.bcs.zsg.product.vo.TourDepartureViewVO;
import com.bcs.zsg.purchase.vo.CountryVO;

public class TourMICEBOImpl implements TourMICEBO {

	@Autowired
	private TourMICEService miceService;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourPackageBO#getTourMICEList(com.bcs.zsg.common.vo.SearchParamVO, com.bcs.zsg.cfg.sec.vo.EmployeeVO)
	 */
	@Override
	public List<TourDepartureViewVO> getTourMICEList(SearchParamVO searchParamVO, EmployeeVO employeeVO) throws BusinessException {
		return miceService.getTourMICEList(searchParamVO, employeeVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourMICEBO#getMICERegionList(java.lang.String)
	 */
	@Override
	public List<RegionVO> getMICERegionList(String regions) throws BusinessException {
		return miceService.getMICERegionList(regions);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.product.bo.TourMICEBO#getMICECountryList(java.lang.String)
	 */
	@Override
	public List<CountryVO> getMICECountryList(String countries) throws BusinessException {
		return miceService.getMICECountryList(countries);
	}
}
