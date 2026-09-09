package com.bcs.zsg.common.dao;

import java.util.List;

import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.maintenance.vo.GlobalConfigVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;

public interface LookupItemDAO extends BaseDAO {

	/**
	 * 
	 * @param category
	 * @return
	 */
	public List<LookupItemVO> getLookupItemList(String category);

	/**
	 * 
	 * @return
	 */
	public List<GlobalConfigVO> getGlobalConfigList();

}
