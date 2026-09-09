package com.bcs.zsg.maintenance.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.AccountCodeBO;
import com.bcs.zsg.maintenance.vo.AccountCodeConfigVO;
import com.bcs.zsg.product.bo.InvoiceAndExchangeOrderBO;
import com.bcs.zsg.product.vo.InvoiceAndExchangeOrderVO;
import com.bcs.zsg.purchase.bo.BillPymtBO;

public class AccountCodeConfigBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient AccountCodeBO accountCodeBO;
	@Autowired
	protected transient BillPymtBO billPymtBO;
	@Autowired
	protected transient InvoiceAndExchangeOrderBO invEoBO;
	
	private List<AccountCodeConfigVO> accountCodeConfigVOList;
	
	private List<AcctVO> acctList;
	private List<InvoiceAndExchangeOrderVO> invEoItemList;
	
	private AccountCodeConfigVO selectedAccountCodeConfigVO;

	public String typeCode;
	

	@Override
	public void resetForm() {
	}
	
	public void loadAccountCodeList(String typeCode) {
		try {
			accountCodeConfigVOList = accountCodeBO.getAccountCodeConfigList(typeCode);
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void init(String typeCode) {
//		resetForm();
//		loadAccountList();
		this.typeCode = typeCode;
		loadAccountCodeList(typeCode);
		loadAccountList();
		loadInvoiceAndExchangeOrderItemList();
	}
	
	public void loadAccountList() {
		try {
			AcctCatVO liabilityVO = billPymtBO.getLiability();
			AcctCatVO expenditureVO = billPymtBO.getIncome();
			AcctCatVO assetVO = billPymtBO.getIncome(); 
			acctList = billPymtBO.getAcctListByComp(liabilityVO.getId(), assetVO.getId(), getSessionInfoBean().getCompanyVO().getId());
			
			for (AccountCodeConfigVO vo : accountCodeConfigVOList) {
				for (AcctVO acctVO : acctList) {
					if (vo.getIdAcct() != null && vo.getIdAcct().equals(acctVO.getId())) {
						vo.setAccountCode(acctVO.getCode());
						vo.setAccountDesc(acctVO.getDesc()); 
						
						if (StringUtils.isNotEmpty(acctVO.getSubCode())) {
							vo.setAccountCode(acctVO.getCode() + "-" + acctVO.getSubCode());
							vo.setAccountDesc(acctVO.getDesc() + "-" + acctVO.getSubDesc());
						}
						break;
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	public void loadInvoiceAndExchangeOrderItemList() {
		try {
			invEoItemList = invEoBO.getInvoiceAndExchangeOrderList(getSessionInfoBean().getCompanyVO().getId(), null, true, false);
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void handleAccountCodeKeyUp(AccountCodeConfigVO vo) {
		AcctVO tempAcctVO = new AcctVO();
		String strTemp;
		try {
			if(vo.getAccountCode() != null) {
				tempAcctVO = accountCodeBO.getAccountVObyAccCodeSubCode(getSessionInfoBean().getCompanyVO().getId(), vo.getAccountCode());
				
				if(tempAcctVO != null) {
					strTemp= tempAcctVO.getDesc();
					if(tempAcctVO.getSubDesc() != null && !tempAcctVO.getSubDesc().equals("")) strTemp += ", " + tempAcctVO.getSubDesc();
					vo.setIdAcct(tempAcctVO.getId());
					vo.setAccountCode(tempAcctVO.getCode());
					vo.setAccountDesc(strTemp); 
					
					if (StringUtils.isNotEmpty(tempAcctVO.getSubCode())) {
						vo.setAccountCode(tempAcctVO.getCode() + "-" + tempAcctVO.getSubCode());
						vo.setAccountDesc(tempAcctVO.getDesc() + "-" + tempAcctVO.getSubDesc());
					}
				} else {
					vo.setAccountDesc("<font color='red'>No data found !</font>"); 
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectAccountCodeConfig(AccountCodeConfigVO vo) {
		selectedAccountCodeConfigVO = vo;
	}
	
	public void removeAccountCodeConfig(AccountCodeConfigVO vo) {
		vo.setIdAcct(null);
		vo.setAccountCode(null);
		vo.setAccountDesc(null);
	}
	
	public void handleSubAcctSelect(SelectEvent event) {
		try {
			AcctVO acctVO = (AcctVO) event.getObject();
			selectedAccountCodeConfigVO.setIdAcct(acctVO.getId());
			selectedAccountCodeConfigVO.setAccountCode(acctVO.getCode());
			selectedAccountCodeConfigVO.setAccountDesc(acctVO.getDesc()); 
			
			if (StringUtils.isNotEmpty(acctVO.getSubCode())) {
				selectedAccountCodeConfigVO.setAccountCode(acctVO.getCode() + "-" + acctVO.getSubCode());
				selectedAccountCodeConfigVO.setAccountDesc(acctVO.getDesc() + "-" + acctVO.getSubDesc());
			}
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void addAccountCode() {
		AccountCodeConfigVO acctCodeConfigVO = new AccountCodeConfigVO();
		acctCodeConfigVO.setIsEditable(Boolean.TRUE);
		acctCodeConfigVO.setTypeCode(typeCode);
		accountCodeConfigVOList.add(acctCodeConfigVO);
	}
	
	public void deleteAccountCode() {
		try {
			if (selectedAccountCodeConfigVO.getId() != null) {
				accountCodeBO.deleteAccountCodeConfig(selectedAccountCodeConfigVO);
			}
			accountCodeConfigVOList.remove(selectedAccountCodeConfigVO);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void saveAccountCode() {
		try {
			accountCodeBO.updateAccountCodeConfig(accountCodeConfigVOList);
			successResult();
			init(typeCode);
		} catch (BusinessException e) {
			errorResult(e);
		}
	}
	
	public List<String> complete(String query) {  
        List<String> results = new ArrayList<String>();   
        String tempStr;
        try {
        	for (int i = 1 ; i < acctList.size() ; i++) {
        		AcctVO vo = acctList.get(i);
        		tempStr = vo.getCode() + (StringUtils.isNotEmpty(vo.getSubCode()) ? "-" + vo.getSubCode() : "");
        		if (tempStr.startsWith(query)) results.add(tempStr);
        	}
 
        } catch(Exception e) {
        	errorResult(e); 
        }
        return results;  
    }  
	
	public void handleInvEoItemSelect(AccountCodeConfigVO vo) {
		try {
			String codes = vo.getCode();
			int flag = 0;
			for (InvoiceAndExchangeOrderVO invEoVO : invEoItemList) {
				if (codes.trim().equals(invEoVO.getCode())) {
					vo.setDescription(invEoVO.getDescription());
					flag = 1;
					break;
				}
			}
			
			if (flag == 0) {
				vo.setDescription("");
			}
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public List<AccountCodeConfigVO> getAccountCodeConfigVOList() {
		return accountCodeConfigVOList;
	}

	public void setAccountCodeConfigVOList(List<AccountCodeConfigVO> accountCodeConfigVOList) {
		this.accountCodeConfigVOList = accountCodeConfigVOList;
	}

	public AccountCodeConfigVO getSelectedAccountCodeConfigVO() {
		return selectedAccountCodeConfigVO;
	}

	public void setSelectedAccountCodeConfigVO(AccountCodeConfigVO selectedAccountCodeConfigVO) {
		this.selectedAccountCodeConfigVO = selectedAccountCodeConfigVO;
	}

	public List<AcctVO> getAcctList() {
		return acctList;
	}

	public void setAcctList(List<AcctVO> acctList) {
		this.acctList = acctList;
	}
	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public List<InvoiceAndExchangeOrderVO> getInvEoItemList() {
		return invEoItemList;
	}

	public void setInvEoItemList(List<InvoiceAndExchangeOrderVO> invEoItemList) {
		this.invEoItemList = invEoItemList;
	}
}
