package com.bcs.zsg.common.helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcs.zsg.common.vo.FileUploadVO;
import com.bcs.zsg.core.exception.BusinessException;

public class FileUploadUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);
	private Map<String, FileUploadVO> map;
	private Map<String, FileUploadVO> tmpMap;
	private List<String> delList;
	private List<String> delTmpList;
	private String mainPath;
	
	// error message
	private static final String ERR_FILE_EXISTED = "ERR_FILE_EXISTED";
	//private static final String ERR_FILE_MAP_EMPTY = "ERR_FILE_MAP_EMPTY";
	
	public FileUploadUtils(String mainPath) {
		this.mainPath = mainPath;
	}
	
	/**
	 * 
	 * @param in
	 * @param path
	 * @param name
	 * @throws BusinessException
	 */
	public void uploadFile() throws Exception {
		OutputStream out = null;
		InputStream in = null;
		
		try {
			logger.error("[uploadFile]" + map);
			if (map != null && !map.isEmpty()) {
				// write files
				Iterator<Entry<String, FileUploadVO>> iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, FileUploadVO> entry = iterator.next();
					
					String path = entry.getValue().getPath();
					// mkdir
					mkdir(path);
					
					String target = mainPath + path + File.separator + entry.getValue().getName();
					File file = new File(target);
					// write inputStream to FileOutputStream
					if (entry.getValue().isCopyFrom()) {
						in = new FileInputStream(new File(mainPath +  entry.getValue().getCopyFromPath() + File.separator + entry.getValue().getCopyFromName()));
					} else {
						in = entry.getValue().getUploadedFile().getInputstream();
					}
					
					// Validate and compress and resize tour image if necessary
	                if ("IMG".equals(entry.getKey())) {
	                    in = validateAndCompressAndResizeImage(in);
	                }
					
					out = new FileOutputStream(file);
					
					int read = 0;
					byte[] bytes = new byte[1024];
					
					while ((read = in.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					
					out.close();
					out.flush();
					in.close();
					
					out = null;
					in = null;
				}
				// clear map
				clearMap();
				
			} //else throw new BusinessException(ERR_FILE_MAP_EMPTY);
			
		} catch (FileNotFoundException e) {
			logger.error("[uploadFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} catch (IOException e) {
			logger.error("[uploadFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} catch (Exception e) {
			logger.error("[uploadFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} finally {
			out = null;
			in = null;
		}
	}
	
	public void uploadSingleFile(UploadedFile uploadedFile, String path, String name, String copyFromPath, String copyFromName, boolean isCopyFile) throws Exception {
		logger.error("--- uploadSingleFile = " + uploadedFile + " / " + path + " / " + name + " | copy = " + copyFromPath + " / " + copyFromName);
		OutputStream out = null;
		InputStream in = null;
		
		try {
			if (uploadedFile != null || isCopyFile) {
				// mkdir
				mkdir(path);
				
				String target = mainPath + path + File.separator + name;
				File file = new File(target);
				// write inputStream to FileOutputStream
				if (isCopyFile)
					in = new FileInputStream(new File(mainPath +  copyFromPath + File.separator + copyFromName));
				else in = uploadedFile.getInputstream();
				out = new FileOutputStream(file);
				
				int read = 0;
				byte[] bytes = new byte[1024];
				
				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				
				out.close();
				out.flush();
				in.close();
				
				out = null;
				in = null;
			}
		} catch (FileNotFoundException e) {
			logger.error("[uploadSingleFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} catch (IOException e) {
			logger.error("[uploadSingleFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} catch (Exception e) {
			logger.error("[uploadSingleFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} finally {
			out = null;
			in = null;
		}
	}
	
	public void uploadTmpFile() throws Exception {
		OutputStream out = null;
		InputStream in = null;
		
		try {
			logger.error("[uploadFile]" + tmpMap);
			if (tmpMap != null && !tmpMap.isEmpty()) {
				// write files
				Iterator<Entry<String, FileUploadVO>> iterator = tmpMap.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, FileUploadVO> entry = iterator.next();
					
					String path = entry.getValue().getPath();
					// mkdir
					mkdir(path);
					
					String target = mainPath + path + File.separator + entry.getValue().getName();
					File file = new File(target);
					// write inputStream to FileOutputStream
					
					if (entry.getValue().isCopyFrom()) {
						in = new FileInputStream(new File(mainPath +  entry.getValue().getCopyFromPath() + File.separator + entry.getValue().getCopyFromName()));
					} else {
						in = entry.getValue().getUploadedFile().getInputstream();
					}
					out = new FileOutputStream(file);
					
					int read = 0;
					byte[] bytes = new byte[1024];
					
					while ((read = in.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					
					out.close();
					out.flush();
					in.close();
					
					out = null;
					in = null;
				}
				// clear tmpMap
				clearTmpMap();
				
			} //else throw new BusinessException(ERR_FILE_tmpMap_EMPTY);
			
		} catch (FileNotFoundException e) {
			logger.error("[uploadTmpFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} catch (IOException e) {
			logger.error("[uploadTmpFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} catch (Exception e) {
			logger.error("[uploadTmpFile]" + e.getMessage(), e);
			e.printStackTrace();
			throw new BusinessException(e);
			
		} finally {
			out = null;
			in = null;
		}
	}
	
	/**
	 * 
	 * @param path
	 * @param name
	 * @throws BusinessException
	 */
	public void uploadFileValidation(String path, String name) throws Exception {
		String target = AppConfigConstant.uploadPath + path + File.separator + name;
		File file = new File(target);
		if (file.exists()) throw new BusinessException(ERR_FILE_EXISTED);
		
		if (map != null) {
			if (map.containsKey(name)) throw new BusinessException(ERR_FILE_EXISTED);
		}
	}
	
	/**
	 * Delete specified file from dir
	 * @param path
	 * @param name
	 * @throws BusinessException
	 */
	public void deleteFile(String path, String name) throws Exception {
		delete(mainPath + path + File.separator + name);
	}
	
	/**
	 * Delete list of file from dir
	 * @throws BusinessException
	 */
	public void deleteFiles() throws Exception {
		if (CollectionUtils.isNotEmpty(delList)) {
			for (String path : delList) {
				delete(mainPath + path);
			}
		}
	}
	
	public void deleteAllFiles() throws Exception {
		if (CollectionUtils.isNotEmpty(delTmpList)) {
			for (String path : delTmpList) {
				delete(mainPath + path);
			}
		}
	}
	
	/**
	 * Delete specified record from map
	 * @param name
	 * @throws BusinessException
	 */
	public void deleteFileFromMap(String name) throws Exception {
		if (map.containsKey(name)) map.remove(name);
	}
	
	/**
	 * Delete specified record from tmp map
	 * @param name
	 * @throws BusinessException
	 */
	public void deleteFileFromTmpMap(String name) throws Exception {
		if (tmpMap.containsKey(name)) tmpMap.remove(name);
	}
	
	/**
	 * Clear map
	 */
	public void clearMap() {
		map.clear();
	}
	
	/**
	 * Clear tmp map
	 */
	public void clearTmpMap() {
		tmpMap.clear();
	}
	
	/*
	 * Check whether dir existed, if not then create dir
	 * @param path
	 * @throws Exception
	 */
	private void mkdir(String path) throws Exception {
		StringTokenizer token = new StringTokenizer(path, "[/]");
		StringBuilder sb = new StringBuilder();
		while (token.hasMoreTokens()) {
			sb.append(File.separator).append(token.nextToken());
			
			File file = new File(mainPath + sb.toString());
			if (!file.exists()) file.mkdir();
		}
	}
	
	/*
	 * Delete file
	 * @param path
	 * @throws Exception
	 */
	private void delete(String path) throws Exception {
		File file = new File(path);
		boolean flag = false;
		if (file.exists()) flag = file.delete();
		
		if (flag) logger.debug(path + "[deleted]");
		else logger.debug(path + "[not deleted]");
	}
	
	public InputStream validateAndCompressAndResizeImage(InputStream in) throws Exception {
	    try {
	        BufferedImage originalImage = ImageIO.read(in);
	        
	        if (originalImage == null) {
	            return in; // Return the original InputStream without processing
	        }

	        // Step 1: Resize the image if necessary
	        int targetWidth = 1200;
	        int targetHeight = 800;
	        BufferedImage finalImage = originalImage;
	        int imageType = originalImage.getType();
	        
	        if (imageType == BufferedImage.TYPE_CUSTOM || imageType == 0) {
	            // If the image type is unknown or custom, choose a default
	            imageType = BufferedImage.TYPE_INT_RGB;
	        }

	        if (originalImage.getWidth() != targetWidth || originalImage.getHeight() != targetHeight) {
	            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, imageType);
	            Graphics2D g = resizedImage.createGraphics();
	            g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	            g.dispose();
	            finalImage = resizedImage;
	        }

	        // Step 2: Compress the image if its size exceeds 1MB
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(finalImage, "jpg", baos);
	        byte[] imageBytes = baos.toByteArray();

	        if (imageBytes.length > 1048576) { // 1MB in bytes
	            baos.reset();
	            float quality = 0.9f; // Start with a high quality

	            // Loop to compress until the image is less than or equal to 1MB
	            while (imageBytes.length > 1048576 && quality > 0.1f) {
	                baos.reset();
	                ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
	                ImageWriteParam param = writer.getDefaultWriteParam();

	                if (param.canWriteCompressed()) {
	                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	                    param.setCompressionQuality(quality);
	                }

	                writer.setOutput(ImageIO.createImageOutputStream(baos));
	                writer.write(null, new IIOImage(finalImage, null, null), param);
	                writer.dispose();

	                imageBytes = baos.toByteArray();
	                quality -= 0.1f; // Reduce quality for further compression if needed
	            }
	        }

	        return new ByteArrayInputStream(imageBytes);

	    } catch (IOException e) {
	        logger.error("[uploadFile]" + e.getMessage(), e);
	        e.printStackTrace();
	        throw new BusinessException(e);

	    } catch (Exception e) {
	        logger.error("[uploadFile]" + e.getMessage(), e);
	        e.printStackTrace();
	        throw new BusinessException(e);
	    }
	}

	/**
	 * @return the map
	 */
	public Map<String, FileUploadVO> getMap() {
		if (map == null) map = new HashMap<String, FileUploadVO>();
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, FileUploadVO> map) {
		this.map = map;
	}

	/**
	 * @return the delList
	 */
	public List<String> getDelList() {
		if (delList == null) delList = new ArrayList<String>();
		return delList;
	}

	/**
	 * @param delList the delList to set
	 */
	public void setDelList(List<String> delList) {
		this.delList = delList;
	}

	public Map<String, FileUploadVO> getTmpMap() {
		if (tmpMap == null) tmpMap = new HashMap<String, FileUploadVO>();
		return tmpMap;
	}

	public void setTmpMap(Map<String, FileUploadVO> tmpMap) {
		this.tmpMap = tmpMap;
	}

	public List<String> getDelTmpList() {
		if (delTmpList == null) delTmpList = new ArrayList<String>();
		return delTmpList;
	}

	public void setDelTmpList(List<String> delTmpList) {
		this.delTmpList = delTmpList;
	}
}
