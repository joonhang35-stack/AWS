package com.bcs.zsg.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenAddUpdDelVO<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<T> addList;
	private List<T> updList;
	private List<T> delList;

	public GenAddUpdDelVO() {
		this.addList = new ArrayList<T>();
		this.updList = new ArrayList<T>();
		this.delList = new ArrayList<T>();
	}
	public GenAddUpdDelVO(List<T> addList, List<T> updList, List<T> delList) {
		this.addList = addList;
		this.updList = updList;
		this.delList = delList;
	}
	
	/**
	 * @return the addList
	 */
	public List<T> getAddList() {
		return addList;
	}
	
	/**
	 * @param addList the addList to set
	 */
	public void setAddList(List<T> addList) {
		this.addList = addList;
	}

	/**
	 * @return the updList
	 */
	public List<T> getUpdList() {
		return updList;
	}

	/**
	 * @param updList the updList to set
	 */
	public void setUpdList(List<T> updList) {
		this.updList = updList;
	}

	/**
	 * @return the delList
	 */
	public List<T> getDelList() {
		return delList;
	}

	/**
	 * @param delList the delList to set
	 */
	public void setDelList(List<T> delList) {
		this.delList = delList;
	}
}
