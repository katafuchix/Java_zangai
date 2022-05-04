/*
 * InportFile.java
 *
 * Created on 2002/05/01, 11:15
 */

package jp.co.yobrain.util.file;

import java.io.*;
import java.util.*;

/**
 *
 * @author  skondo
 * @version 
 * 使用例
 * InportFile search = null;
 * String search_file = "";
 * try{
 *  search_file = search.getFileAmount("/als/" + dir + "search_h.txt", "JISAutoDetect");
 * }catch(Exception e){}
 */
public class InportFile {
	private static final int BUF_SZ = 2048;

	/**
	 * ファイル情報の取得を行う
	 * @param String ファイル名
	 * @param String パス
	 */

	public static String getFileAmount(String filename, String path)
		throws Exception {
		String result = "";
		StringWriter swriter = null;
		PrintWriter pwriter = null;
		FileReader sr = null;
		BufferedInputStream bis = null;
		//InputStream is = null;
		try {
			bis =
				new BufferedInputStream(
					new FileInputStream(new File(path + "/" + filename)));
			//bais = (ByteArrayInputStream)is;
			//byte[] bt = new byte[BUF_SZ];
			int availableLength = bis.available();
			byte[] bt = new byte[availableLength];
			int len = 0;
			//while((len = bis.read( bt, 0, BUF_SZ)) != -1 )
			while ((len = bis.read(bt, 0, availableLength)) != -1) {
				result += new String(bt, "UTF-8");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
		return (result);
	}
	/**
	 * ファイル情報の取得を行う
	 * @param String ファイル名
	 * @param String パス
	 * @param String エンコード名
	 */
	public static String getFileAmount(
		String filename,
		String path,
		String encoding)
		throws IOException {
		try {
			return new String(
				getFileAmount(filename, path).getBytes("ISO-8859-1"),
				encoding);
		} catch (IOException e) {
			return "";
		} catch (Exception e) {
			return "";
		}
	}
	private static String makeStringFromReader(Reader reader)
		throws IOException {
		BufferedReader breader = null;
		PrintWriter pwriter = null;
		StringWriter swriter = null;
		String result = null;

		if (reader instanceof BufferedReader) {
			breader = (BufferedReader) reader;
		} else {
			breader = new BufferedReader(reader);
		}
		try {
			swriter = new StringWriter();
			pwriter = new PrintWriter(swriter);
			String line;
			while ((line = breader.readLine()) != null) {
				pwriter.println(line);
			}
			pwriter.flush();
			result = swriter.toString();
		} finally {
			if (pwriter != null) {
				pwriter.close();
			} else if (swriter != null) {
				swriter.close();
			}
		}
		return (result);
	}
}
