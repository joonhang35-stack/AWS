package com.bcs.zsg.product.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.CollectionUtils;
import com.bcs.zsg.product.bo.CruiseBO;
import com.bcs.zsg.product.bo.CruiseCabinBO;
import com.bcs.zsg.product.vo.CruiseCabinVO;
import com.bcs.zsg.product.vo.CruiseVO;

public class CruiseCabinSetupBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient CruiseCabinBO cruiseCabinBO;
	@Autowired
	private transient CruiseBO cruiseBO;

	private List<CruiseCabinVO> cabinList;
	private List<CruiseVO> cruiseList;

	private CruiseCabinVO cabinVO;

	protected String company;

	@Override
	public void resetForm() {
		cabinVO = new CruiseCabinVO();
	}

	public void init() {
		try {
			loadCruiseList();
			loadCabinList();

			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			company = servletContext.getContextPath().substring(1);

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void save() {
		try {
			cruiseCabinBO.save(cabinVO);
			loadCabinList();

			String action = "";
			if (cabinVO.getId() == null) {
				action = "add";
			} else {
				action = "upd";
			}

			successResult();
			resetForm();

		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	public void delete() {
		try {
			cruiseCabinBO.delete(cabinVO);
			loadCabinList();

			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	private void loadCabinList() throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
//		if (cabinVO.getIdCruise()==null) {
//			params.put("idCruise", 1L);
//		} else {
//			params.put("idCruise", cabinVO.getIdCruise());
//		}

		  cabinList = cruiseCabinBO.getCabinList(params);
		    if (CollectionUtils.isNotEmpty(cabinList)) {
		        for (CruiseCabinVO cabin : cabinList) {
		        	if (CollectionUtils.isNotEmpty(cruiseList)) {
			            for (CruiseVO cruise : cruiseList) {
			                if (cruise.getId().equals(cabin.getIdCruise())) {
			                	cabin.setCruiseDesc(cruise.getDesc());
			                    break;
			                }
			            }
		        	} 
		        }
		    } 
	}

	private void loadCruiseList() throws BusinessException {
		cruiseList = cruiseBO.getCruiseList();
	}

	public List<CruiseCabinVO> getCabinList() {
		return cabinList;
	}

	public CruiseCabinVO getCabinVO() {
		return cabinVO;
	}

	public void setCabinVO(CruiseCabinVO cabinVO) {
		this.cabinVO = cabinVO;
	}

	public List<CruiseVO> getCruiseList() {
		return cruiseList;
	}

	public void setCruiseList(List<CruiseVO> cruiseList) {
		this.cruiseList = cruiseList;
	}

}
