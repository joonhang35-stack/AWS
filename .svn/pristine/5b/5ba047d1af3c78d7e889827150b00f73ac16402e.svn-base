package com.bcs.zsg.common.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtils {
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 300;

	/**
	 * @param str
	 * @param width - default 300 if value is null or -1
	 * @param height - default 300 if value is null or -1
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static BitMatrix generateQRCode(String str, Integer width, Integer height) throws WriterException, IOException {

		if (width == null || width == -1)	width = DEFAULT_WIDTH;
		if (height == null || height == -1)	height = DEFAULT_HEIGHT;

		// Specify character encoding
		Map<EncodeHintType, String> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		// Generate BitMatrix (QR Code)
		BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, width, height, hints);
		
		return bitMatrix;
	}
	
	public static BitMatrix generateQRCode(String str) throws WriterException, IOException {
		return generateQRCode(str, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public static BufferedImage generateQRCodeBufferedImage(String str, Integer width, Integer height) throws WriterException, IOException {
		// Convert BitMatrix to BufferedImage
		return MatrixToImageWriter.toBufferedImage(generateQRCode(str, width, height));
	}
	
	public static ByteArrayOutputStream generateQRCodeByteArrayOutputStream(String str, Integer width, Integer height) throws WriterException, IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(generateQRCodeBufferedImage(str, width, height), "PNG", byteArrayOutputStream);
		return byteArrayOutputStream;
	}
	
	public static byte[] generateQRCodeByteArray(String str, Integer width, Integer height) throws WriterException, IOException {
		return generateQRCodeByteArrayOutputStream(str, width, height).toByteArray();
	}
	
	public static byte[] generateQRCodeBase64ByteArray(String str, Integer width, Integer height) throws WriterException, IOException {
		return Base64.getEncoder().encode(generateQRCodeByteArray(str, width, height));
	}
	
	public static ByteArrayInputStream generateQRCodeByteArrayInputStream(String str, Integer width, Integer height) throws WriterException, IOException {
		return new ByteArrayInputStream(generateQRCodeByteArray(str, width, height));
	}
	
	/**
	 * @param str
	 * @param filename - without .png
	 * @param width
	 * @param height
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static File generateQRCodeFile(String str, String filename, Integer width, Integer height) throws WriterException, IOException {
		// Save BufferedImage as a File object
		File file = new File(filename + ".png");
		ImageIO.write(generateQRCodeBufferedImage(str, width, height), "PNG", file);
		return file;
	}
}
