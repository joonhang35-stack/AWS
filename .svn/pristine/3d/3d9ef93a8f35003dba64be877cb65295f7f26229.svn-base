package com.bcs.zsg.product.web.bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.product.bo.SalesCommConfigBO;
import com.bcs.zsg.product.vo.SalesCommConfigDetailVO;
import com.bcs.zsg.product.vo.SalesCommConfigVO;

public class SalesCommConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SalesCommConfigBO salesCommConfigBO;
	
	private SalesCommConfigVO salesCommConfigVO;
	private SalesCommConfigDetailVO salesCommConfigDetailVO;
	
	private List<SalesCommConfigVO> salesCommConfigList;
	
	private boolean detailAdd;
	
	@Override
	public void resetForm() {
		salesCommConfigVO = new SalesCommConfigVO();
		
		resetSalesCommConfigDetailForm();
	}
	
	public void resetSalesCommConfigDetailForm() {
		detailAdd = true;
		salesCommConfigDetailVO = new SalesCommConfigDetailVO();
	}
	
	/**
	 * Initialization
	 */
	public void init() {
		try {
			resetForm();
			salesCommConfigList = salesCommConfigBO.getSalesCommConfigList();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void addSalesCommConfig() {
		try {
			if (CollectionUtils.isEmpty(salesCommConfigVO.getSalesCommConfigDetailList())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No sales commission detail!", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
			
			salesCommConfigBO.addSalesCommConfig(salesCommConfigVO);
			
			salesCommConfigBO.insertSalesCommConfigHistory(salesCommConfigVO.getId(), CommonConstant.ACTION_CD_ADD, "");
			
			init();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void updSalesCommConfig() {
		try {
			if (CollectionUtils.isEmpty(salesCommConfigVO.getSalesCommConfigDetailList())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No sales commission detail!", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				RequestContext.getCurrentInstance().addCallbackParam("isValidSave", false);
				return;
			}
			
			salesCommConfigBO.updSalesCommConfig(salesCommConfigVO);
			
			salesCommConfigBO.insertSalesCommConfigHistory(salesCommConfigVO.getId(), CommonConstant.ACTION_CD_UPD, "");
			
			RequestContext.getCurrentInstance().addCallbackParam("isValidSave", true);
			
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void delSalesCommConfig() {
		try {
			salesCommConfigBO.delSalesCommConfig(salesCommConfigVO);
			
			init();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	
	public void saveDetail() {
		try {
			if ("LT".equals(salesCommConfigDetailVO.getFareRangetype()) || "GT".equals(salesCommConfigDetailVO.getFareRangetype())) {
				salesCommConfigDetailVO.setToAmt(0.00);
			}
			if (detailAdd) salesCommConfigVO.getSalesCommConfigDetailList().add(salesCommConfigDetailVO);

			resetSalesCommConfigDetailForm();

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void delDetail(SalesCommConfigDetailVO vo) {
		try {
			if (CollectionUtils.isNotEmpty(salesCommConfigVO.getSalesCommConfigDetailList())) {
				salesCommConfigVO.getSalesCommConfigDetailList().remove(vo);
			}

		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	
	public void handleSalesCommConfigSelect(SalesCommConfigVO vo) {
		try {
			salesCommConfigVO = vo;
			salesCommConfigVO.setSalesCommConfigDetailList(salesCommConfigBO.getSalesCommConfigDetailList(vo.getId()));
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void handleDetailSelect(SalesCommConfigDetailVO vo) {
		try {
			detailAdd = false;
			salesCommConfigDetailVO = vo;
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleAddSalesCommConfig() {
		try {
			resetForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	public SalesCommConfigVO getSalesCommConfigVO() {
		return salesCommConfigVO;
	}

	public void setSalesCommConfigVO(SalesCommConfigVO salesCommConfigVO) {
		this.salesCommConfigVO = salesCommConfigVO;
	}

	public SalesCommConfigDetailVO getSalesCommConfigDetailVO() {
		return salesCommConfigDetailVO;
	}

	public void setSalesCommConfigDetailVO(SalesCommConfigDetailVO salesCommConfigDetailVO) {
		this.salesCommConfigDetailVO = salesCommConfigDetailVO;
	}

	public List<SalesCommConfigVO> getSalesCommConfigList() {
		return salesCommConfigList;
	}

	public void setSalesCommConfigList(List<SalesCommConfigVO> salesCommConfigList) {
		this.salesCommConfigList = salesCommConfigList;
	}

	public boolean isDetailAdd() {
		return detailAdd;
	}

	public void setDetailAdd(boolean detailAdd) {
		this.detailAdd = detailAdd;
	}

}
