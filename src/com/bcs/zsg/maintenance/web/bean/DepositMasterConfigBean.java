package com.bcs.zsg.maintenance.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.bcs.zsg.bank.bo.BankAcctBO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.DepositMasterConfigBO;
import com.bcs.zsg.maintenance.vo.DepositMasterConfigVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;

public class DepositMasterConfigBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient DepositMasterConfigBO depositMasterConfigBO;
	@Autowired
	private transient BankAcctBO bankAcctBO;
	
	private List<DepositMasterConfigVO> depositMasterConfigList;
	private List<BankAcctViewVO> bankAcctViewList;

	@Override
	public void resetForm() {
		
	}
	
	public void init() {
		try {
			bankAcctViewList = bankAcctBO.getBankAcctList(getSessionInfoBean().getCompanyVO().getId());
			loadDepositMasterList();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void save() {
		try {
			depositMasterConfigBO.saveDepositMasterConfig(depositMasterConfigList);
			loadDepositMasterList();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadDepositMasterList() throws BusinessException {
		List<LookupItemVO> pmntTypeList = LookupItemUtils.getLookupItemList("pymt_type");
		depositMasterConfigList = depositMasterConfigBO.getDepositMasterConfigVOList();
		
		if (CollectionUtils.isEmpty(depositMasterConfigList)) {
			depositMasterConfigList = new ArrayList<DepositMasterConfigVO>();
			
			for (LookupItemVO itemVO : pmntTypeList) {
				DepositMasterConfigVO vo = new DepositMasterConfigVO();
				vo.setPmntTypeCode(itemVO.getCode());
				vo.setPmntTypeDesc(itemVO.getDescription());
				depositMasterConfigList.add(vo);
			}
		} else {
			for (LookupItemVO itemVO : pmntTypeList) {
				boolean notExisted = true;
				
				for (DepositMasterConfigVO vo : depositMasterConfigList) {
					if (vo.getPmntTypeCode().equals(itemVO.getCode())) {
						vo.setPmntTypeDesc(itemVO.getDescription());
						notExisted = false;
						break;
					}
				}
				
				if (notExisted) {
					DepositMasterConfigVO vo = new DepositMasterConfigVO();
					vo.setPmntTypeCode(itemVO.getCode());
					vo.setPmntTypeDesc(itemVO.getDescription());
					depositMasterConfigList.add(vo);
				}
			}
		}
	}

	/**
	 * @return the depositMasterConfigList
	 */
	public List<DepositMasterConfigVO> getDepositMasterConfigList() {
		return depositMasterConfigList;
	}

	/**
	 * @return the bankAcctViewList
	 */
	public List<BankAcctViewVO> getBankAcctViewList() {
		return bankAcctViewList;
	}

	
}
