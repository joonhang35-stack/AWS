package com.bcs.zsg.maintenance.web.bean;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;



public class SystemNumberGenerationBean extends AppBackingBean 
{
	private static final long serialVersionUID = 1L;
	@Autowired
	private transient SystemNumberGenerationBO systemNumberGenerationBO;
	private SystemNumberGenerationVO systemNumberGenerationVO;

	private List<SystemNumberGenerationVO> systemNumberGenerationList;

	String temp;

	@Override
	public void resetForm() 
	{
		systemNumberGenerationVO = new SystemNumberGenerationVO();	
	}

	public void init() throws BusinessException{
		try {
			resetForm();
			loadSystemNumberGeneration();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void addSystemNumberGeneration() throws BusinessException {
		try
		{
			systemNumberGenerationVO.setIdCompany(getSessionInfoBean().getCompanyVO().getId());
			systemNumberGenerationVO.setIsDefault(0);
			if(StringUtils.isEmpty(systemNumberGenerationVO.getPrefixid()))
			{
				systemNumberGenerationBO.addSystemNumberGeneration(systemNumberGenerationVO);
			}
			else
			{	
				for(SystemNumberGenerationVO vo : systemNumberGenerationList)
				{
					if(vo.getPrefixid().toString().equalsIgnoreCase(systemNumberGenerationVO.getPrefixid().toString()))
					{
						throw new BusinessException(CommonErrConstant.ERR_SYSNUM_PREFIX_DUPLICATE);
					}
				}
				systemNumberGenerationBO.addSystemNumberGeneration(systemNumberGenerationVO);
			}	
			resetForm();
			loadSystemNumberGeneration();	
			successResult();
			
		}catch(Throwable t){
			errorResult(t);
		}
	}

	private void loadSystemNumberGeneration() throws BusinessException {
		try {
			systemNumberGenerationList=systemNumberGenerationBO.getSystemNumberGenerationList(getSessionInfoBean().getCompanyVO().getId());
			resetForm();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void onSystemNumberGenerationSelected(SystemNumberGenerationVO systemNumberGenerationVO) throws BusinessException{
		try {
			this.systemNumberGenerationVO = systemNumberGenerationVO;
			temp=systemNumberGenerationVO.getPrefixid();

		}catch (Throwable t) {
			errorResult(t);
		}
	}

	public void deleteSystemNumberGeneration() throws BusinessException{
		try {
			systemNumberGenerationBO.deleteSystemNumberGeneration(systemNumberGenerationVO);
			loadSystemNumberGeneration();

		} catch (Throwable t){
			errorResult(t);
		}
	}

	public void editSystemNumberGeneration() throws BusinessException{
		try {
			systemNumberGenerationVO.setIdCompany(getSessionInfoBean().getCompanyVO().getId());
			if(temp.equalsIgnoreCase(systemNumberGenerationVO.getPrefixid().toString()))
			{
				systemNumberGenerationBO.updateSystemNumberGeneration(systemNumberGenerationVO);
			}
			else if(StringUtils.isEmpty(systemNumberGenerationVO.getPrefixid()))
			{
				systemNumberGenerationBO.updateSystemNumberGeneration(systemNumberGenerationVO);
			}	
			else
			{
				systemNumberGenerationList=systemNumberGenerationBO.getSystemNumberGenerationList(getSessionInfoBean().getCompanyVO().getId());
				for(SystemNumberGenerationVO vo : systemNumberGenerationList)
				{
					if(vo.getPrefixid().toString().equalsIgnoreCase(systemNumberGenerationVO.getPrefixid()))
					{
						throw new BusinessException(CommonErrConstant.ERR_SYSNUM_PREFIX_DUPLICATE);
					}
				}
				systemNumberGenerationBO.updateSystemNumberGeneration(systemNumberGenerationVO);
			}
			loadSystemNumberGeneration();
			resetForm();
			successResult();

		} catch (Throwable t) {
			errorResult(t);
		}
	}

	public SystemNumberGenerationVO getSystemNumberGenerationVO() 
	{
		return systemNumberGenerationVO;
	}

	public void setSystemNumberGenerationVO(SystemNumberGenerationVO systemNumberGenerationVO) 
	{
		this.systemNumberGenerationVO = systemNumberGenerationVO;
	}
	
	public List<SystemNumberGenerationVO> getSystemNumberGenerationList() {
		return systemNumberGenerationList;
	}

	public void setSystemNumberGenerationList(
			List<SystemNumberGenerationVO> systemNumberGenerationList) {
		this.systemNumberGenerationList = systemNumberGenerationList;
	}

	/**
	 * @return the temp
	 */
	public String getTemp() {
		return temp;
	}

	/**
	 * @param temp the temp to set
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
}
