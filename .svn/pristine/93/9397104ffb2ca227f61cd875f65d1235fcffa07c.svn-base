package com.bcs.zsg.maintenance.web.bean;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.maintenance.bo.POSUploadSalesBO;
import com.bcs.zsg.maintenance.vo.POSUploadSalesTransVO;

public class POSUploadHistoryBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient POSUploadSalesBO posUploadSalesBO;
	
	private List<POSUploadSalesTransVO> posUploadSalesTransVOList;
	
	private Date dateFrom, dateTo;

	@Override
	public void resetForm() {
		dateFrom = new Date();
		dateTo = new Date();
	}
	
	public void init() {
		resetForm();
	}
	
	public void search() {
		try {
			posUploadSalesTransVOList = posUploadSalesBO.getPostedData(dateFrom, dateTo);
		} catch (IOException e) {
			errorResult(e);
		}
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<POSUploadSalesTransVO> getPosUploadSalesTransVOList() {
		return posUploadSalesTransVOList;
	}

	public void setPosUploadSalesTransVOList(List<POSUploadSalesTransVO> posUploadSalesTransVOList) {
		this.posUploadSalesTransVOList = posUploadSalesTransVOList;
	}
}
