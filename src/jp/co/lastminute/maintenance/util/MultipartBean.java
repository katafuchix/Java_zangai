package jp.co.lastminute.maintenance.util;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/************************************************************
* Modified by Nemuneko 7/9/2001
* Servlet/JSP FileUpload MultipartBean class
* 2000.05.19
* write by Jeon HongSeong
************************************************************/

public class MultipartBean {
	private DataInputStream dis;
	private HttpServletRequest req;
	private String fileName,dir;
	private String startLine;
	private String fileLine;
	private byte[] buffer;
	private byte[] data;
	
	/******************************************************
	* 1. HttpServletRequest
	* 2. DataInputStream HttpServletRequest ServletInputStream
	* 
	*******************************************************/
	public MultipartBean(HttpServletRequest req) throws IOException {
		this.req = req;
		dis = new DataInputStream(req.getInputStream());
	}
	
	/******************************************************
	* 1. InputStream data DataInputStream
	*    byte read and ByteArrayOuputStream write
	* 2. Member variable byte[] buffer ByteArrayOutputStream
	*     receive data byte[]
	*******************************************************/
	public void recvData() throws IOException {
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		boolean flag=true;
		while(flag) {
			try {
				byte b=dis.readByte();
				bao.write(b);
			} catch(EOFException e){
				flag=false;
			}
		}
		buffer=bao.toByteArray();
		parseData();
	}
	
	/****************************************************
	* 1. Receive Data parsing file
	*    byte[] data arraycopy
	* 2. file
	*****************************************************/
	private void parseData() {
		int cnt=0;
		int i;
		for(i=0;true;i++) {
			if((buffer[i]==13)&(buffer[i+1]==10)) {
				cnt++;
				if(cnt==1) startLine=new String(buffer,0,i-1);
				if(cnt==2) fileLine=new String(buffer,startLine.length()+3,
				i-(startLine.length()+2));
			}
			if(cnt>=4) break;
		}

		//copy the real data of the file.
		data = new byte[buffer.length-i-startLine.length()-9];
		System.arraycopy(buffer,i+2,data,0,data.length);
		
		//parse the file name.
		int idx=fileLine.lastIndexOf("\\");
		fileName=fileLine.substring(idx+1,fileLine.length()-2);		
	}
	public void setFilename( String filename ){
		this.fileName = filename;	
	}
	/****************************************************
	* 1. fileName data write(file)
	****************************************************/
	public void saveData() throws IOException {
		File f=null;
		if(dir==null) {
			f = new File(fileName);
		} else {
			f = new File(dir,fileName);
		}
		FileOutputStream fo = new FileOutputStream(f);
		fo.write(data);
		fo.close();
	}

	public byte[] getFileData() {
		return data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setDir(String dir) {
		this.dir=dir;
	}
	public String getDir() {
		return this.dir;
	}
};
