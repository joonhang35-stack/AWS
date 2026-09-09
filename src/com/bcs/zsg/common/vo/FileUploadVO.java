package com.bcs.zsg.common.vo;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class FileUploadVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UploadedFile uploadedFile;
	private String path;
	private String name;
	private String copyFromPath;
	private String copyFromName;
	private boolean isCopyFrom;
	
	public FileUploadVO(UploadedFile uploadedFile, String path, String name) {
		this.uploadedFile = uploadedFile;
		this.path = path;
		this.name = name;
	}
	
	public FileUploadVO(String path, String name, String copyFromPath, String copyFromName) {
		this.uploadedFile = null;
		this.path = path;
		this.name = name;
		this.copyFromPath = copyFromPath;
		this.copyFromName = copyFromName;
		this.isCopyFrom = true;
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getCopyFromPath() {
		return copyFromPath;
	}

	public void setCopyFromPath(String copyFromPath) {
		this.copyFromPath = copyFromPath;
	}

	public String getCopyFromName() {
		return copyFromName;
	}

	public void setCopyFromName(String copyFromName) {
		this.copyFromName = copyFromName;
	}

	public boolean isCopyFrom() {
		return isCopyFrom;
	}

	public void setCopyFrom(boolean isCopyFrom) {
		this.isCopyFrom = isCopyFrom;
	}
	
}
