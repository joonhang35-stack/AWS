package com.bcs.zsg.component;

import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

import com.bcs.zsg.common.vo.GenAddUpdDelVO;
import com.bcs.zsg.component.helper.ConstantScreenAction;
import com.bcs.zsg.core.exception.ApplicationException;
import com.bcs.zsg.core.vo.BaseVO;

public class GenUpdateVOList {

	@SuppressWarnings({ "unchecked", "hiding" })
	public static <T extends BaseVO> boolean updateVOInList (List<T> voList, T vo, 
					boolean addIfNotExist, boolean deleteIfExist) throws ApplicationException, Exception {
		boolean isVOFoundInList = false;

		if (voList == null)
			return true;
		
		for(int i = voList.size() - 1; i >= 0; i--) {
			
			boolean sameVersion = false;
			if (vo.getId() != null && voList.get(i).getId() != null) {
				if (vo.getId().longValue() == voList.get(i).getId().longValue())
					sameVersion =  true;
		    } else {
				if (vo.getVersion() != null && voList.get(i).getVersion() != null) {
			    	if(vo.getVersion().longValue() == voList.get(i).getVersion().longValue()) 
			    		sameVersion =  true;
				}
		    }
			
			if (sameVersion) {
				if (deleteIfExist)
					voList.remove(i);
				else
					voList.set(i, (T) vo.clone());
				isVOFoundInList = true;
					
				break;
			}
		}
		
		if (!isVOFoundInList && addIfNotExist && !deleteIfExist)
			voList.add((T) vo.clone());
		
		return isVOFoundInList;
	}
	
	@SuppressWarnings("hiding")
	public static <T extends BaseVO> void processAddUpdDelVO(GenAddUpdDelVO<T> addUpdDelVO, List<T> voList
			, T vo, String action) throws ApplicationException, Exception {
		processAddUpdDelVO(addUpdDelVO, voList, vo, action, false, 0);
	}
	
	@SuppressWarnings("hiding")
	public static <T extends BaseVO> void processAddUpdDelVO(GenAddUpdDelVO<T> addUpdDelVO, List<T> voList
			, T vo, String action, int index) throws ApplicationException, Exception {
		processAddUpdDelVO(addUpdDelVO, voList, vo, action, true, index);
	}
	
	@SuppressWarnings("hiding")
	private static <T extends BaseVO> void processAddUpdDelVO(GenAddUpdDelVO<T> addUpdDelVO, List<T> voList
			, T vo, String action, boolean isWithIndex, int index) throws ApplicationException, Exception {
		if (vo.getId() == null && vo.getVersion() != null && action.equals(ConstantScreenAction.UPD)) {
			action = ConstantScreenAction.ADD;
		}
		
		if (action.equals(ConstantScreenAction.ADD)) {
	
			if (vo.getId() == null && vo.getVersion() == null) {
				vo.setVersion(System.currentTimeMillis());
			}
			//Add to list
			boolean addListExist = updateVOInList(addUpdDelVO.getAddList(), vo, true, false);
			//Remove from update list if exist
			updateVOInList(addUpdDelVO.getUpdList(), vo, false, true);
	
			if (voList != null && !addListExist)	{//Sometime may require adding at specific location, thus the voList maybe null
				if (isWithIndex)
					voList.add(index, vo);
				else
					voList.add(vo);
			}
				
			if (voList != null)
				updateVOInList(voList, vo, false, false);
			
		} else if (action.equals(ConstantScreenAction.UPD)) {
			//Update the new list first
			if (!updateVOInList(addUpdDelVO.getAddList(), vo, false, false)) {
				//Add to the update list if not found or else it will udpate it
				updateVOInList(addUpdDelVO.getUpdList(), vo, true, false);
				
			}
			
			updateVOInList(voList, vo, false, false);
			
		} else if (action.equals(ConstantScreenAction.DEL)) {
			//delete from add list first
			if (!updateVOInList(addUpdDelVO.getAddList(), vo, false, true)) {
				//Delete from updateVO List 
				updateVOInList(addUpdDelVO.getUpdList(), vo, false, true);
			}
			
			//Add to delete list
			if (vo.getId() != null)
				updateVOInList(addUpdDelVO.getDelList(), vo, true, false);
			
			updateVOInList(voList, vo, false, true);
		}
	}
}
