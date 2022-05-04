package jp.co.lastminute.maintenance.hotel.allot;

import java.net.*;
import java.io.*;
import java.util.*;

import com.sun.net.ssl.*;
import java.security.*;

public class HttpFiler implements Serializable {
  private String inFilename = null;
  private String outFilename = null;
  private String methodName = "GET";
  private boolean sslFlag = false;
  private String rcvEncoding = "Shift_JIS";

	public HttpFiler(){}

	public void action(String defaulturl) throws Exception{
		BufferedWriter out = null;
		BufferedReader in = null;
		StringBuffer ret = new StringBuffer();
		try{
      if(sslFlag == true){
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
      }
NtaAllotUpdate.logs("HttpFiler connect to = " + defaulturl);
			HttpURLConnection con = (HttpURLConnection)new URL(defaulturl).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
      con.setRequestMethod(methodName);
			// システムエンコード使用
			if(inFilename != null){
        out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        readDataFile(out);
			}
			con.connect();

			if(outFilename != null){
        in = new BufferedReader(new InputStreamReader(con.getInputStream(), rcvEncoding));
        writeDataFile(in);
			}
		} catch(Exception e){
			e.printStackTrace();
      throw new Exception("NTA HTTP Connection Fail");
		} finally {
			try { out.close(); in.close();} catch(Exception e){}
		}
	}

  private void readDataFile(BufferedWriter out){
    try{
      BufferedReader in = new BufferedReader(new FileReader(inFilename));
      String str = null;
      while((str = in.readLine()) != null){
        out.write(str, 0, str.length());
        out.newLine();
      }
      out.flush();
      in.close();
    } catch(Exception e){ NtaAllotUpdate.logs(e); }
  }
  private void writeDataFile(BufferedReader in){
    try{
      BufferedWriter out = new BufferedWriter(new FileWriter(outFilename));
      String str = null;
      int dbgcnt = 0;
      while((str = in.readLine()) != null){
        out.write(str, 0, str.length());
        out.newLine();
        dbgcnt++;
      }
      out.close();
NtaAllotUpdate.logs("Receive file records = " + dbgcnt);
    } catch(Exception e){ NtaAllotUpdate.logs(e); }
  }
  public void setInFilename(String inFilename) {
    this.inFilename = inFilename;
  }
  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }
  public void setOutFilename(String outFilename) {
    this.outFilename = outFilename;
  }
  public void setSslFlag(boolean sslFlag) {
    this.sslFlag = sslFlag;
  }
  public void setRcvEncoding(String rcvEncoding) {
    this.rcvEncoding = rcvEncoding;
  }
}
