package jp.co.yobrain.logger;

import java.io.*;
import java.util.*;
import jp.co.yobrain.common.*;
import jp.co.yobrain.servlet.AppContext;
import java.text.SimpleDateFormat;
/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class EnterpriseLog {
	private RandomAccessFile m_logFile = null;
	private String m_logFilePath = null;
	private SimpleDateFormat m_logDateFormat = null;
	private String m_separator = null;
	private long m_maxSize = 0;
	private int m_generations = 0;
	private int m_filterMask = 0;

	public Object m_lock = new Object();
	private static int m_nIncrement = 0;
	public static final int INFO = 0x01;
	public static final int TRACE = 0x02;
	public static final int ERROR = 0x04;
	public static final int FILTER_INFO = INFO;
	public static final int FILTER_ERROR = ERROR;
	public static final int FILTER_TRACE = TRACE;
	public static final int FILTER_ALL =	FILTER_INFO | FILTER_ERROR | FILTER_TRACE;

	public EnterpriseLog(String logFilePath, String maxSizeStr, String generationsStr) throws IllegalArgumentException{
		
		long maxSize = Long.parseLong( maxSizeStr );
		int generations = Integer.parseInt( generationsStr );
		if (maxSize < 0) {
			throw new IllegalArgumentException("maxSize parameter argument is less than 0.");
		}

		if (generations < 0) {
			throw new IllegalArgumentException("generations parameter argument is less than 0.");
		}

		m_logFilePath = logFilePath;
		m_maxSize = maxSize;
		m_generations = generations;
		m_logDateFormat = new SimpleDateFormat("[ yyyy-MM-dd hh:mm:ss zzz ] ");
		m_separator = System.getProperty("line.separator");
		setLogFilter(EnterpriseLog.FILTER_ALL);
	}
	public EnterpriseLog(String logFilePath, long maxSize, int generations)
		throws IllegalArgumentException {

		if (maxSize < 0) {
			throw new IllegalArgumentException("maxSize parameter argument is less than 0.");
		}

		if (generations < 0) {
			throw new IllegalArgumentException("generations parameter argument is less than 0.");
		}

		m_logFilePath = logFilePath;
		m_maxSize = maxSize;
		m_generations = generations;
		m_logDateFormat = new SimpleDateFormat("[ yyyy-MM-dd hh:mm:ss zzz ] ");
		m_separator = System.getProperty("line.separator");
		setLogFilter(EnterpriseLog.FILTER_ALL);
	}

	public EnterpriseLog(String logFilePath) throws IllegalArgumentException {
		this(logFilePath, 0, 0);
	}

	public int setLogFilter(int filterMask) {
		synchronized (m_lock) {
			int oldMask = m_filterMask;

			m_filterMask = filterMask;

			return oldMask;
		}
	}

	public int getLogFilter() {
		synchronized (m_lock) {
			return m_filterMask;
		}
	}

	public void openLog() throws IOException {
		m_logFile = new RandomAccessFile(m_logFilePath, "rw");
		m_logFile.seek(m_logFile.length());
	}

	public void closeLog() throws IOException {
		m_logFile.close();
	}

	private void checkLogSize() throws IOException {

		//If there is no maxsize or generations, ignore creating
		//generation files
		if ((m_maxSize == 0) || (m_generations == 0)) {
			return;
		}

		//If we have reached the max size then generate
		if (m_logFile.length() >= m_maxSize * 1024) {
			closeLog();
			for (int i = m_generations; i > 0; i--) {
				String oldFilePath = m_logFilePath;
				if ((i - 1) > 0)
					oldFilePath += "." + (i - 1);

				File oldFile = new File(oldFilePath);
				if (oldFile.exists()) {
					String newFilePath = m_logFilePath + "." + i;
					File newFile = new File(newFilePath);
					newFile.delete();
					oldFile.renameTo(new File(newFilePath));
				}
			}

			openLog();
		}
	}

	public String logInfo(String str) {
		return logMessage(INFO, str);
	}

	public String logTrace(String str) {
		return logMessage(TRACE, str);
	}

	private String logMessage(int logType, String str) {
		synchronized (m_lock) {
			String tranId = getTranId();
			StringBuffer buffer = new StringBuffer();

			if ((logType & m_filterMask) == logType) {

				buffer.setLength(0);
				buffer.append(getDateTimeString());

				switch (logType) {
					case (ERROR) :
						buffer.append("{E} ");
						break;
					case (INFO) :
						buffer.append("{I} ");
						break;
					case (TRACE) :
						buffer.append("{T} ");
						break;
					default :
						buffer.append("{?} ");
						break;
				}

				buffer.append(str);
				buffer.append(m_separator);

				try {
					byte[] temp = (buffer.toString()).getBytes("EUC_JP");
					m_logFile.write( temp );
					checkLogSize();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}

			return tranId;
		}
	}

	public String logException(String msg, Exception e) {
		synchronized (m_lock) {
			String tranId = getTranId();
			StringBuffer buffer = new StringBuffer();

			if ((ERROR & m_filterMask) == ERROR) {
				String dateTime = getDateTimeString();
				String errors[] = splitTrace(e);

				buffer.setLength(0);

				buffer.append(dateTime);
				buffer.append("{E} TRAN ID - ");
				buffer.append(tranId);
				buffer.append(m_separator);

				if (msg != null) {
					buffer.append(dateTime);
					buffer.append("{E} ");
					buffer.append(msg);
					buffer.append(m_separator);
				}

				for (int i = 0; i < errors.length; i++) {
					buffer.append(dateTime);
					buffer.append("{E} ");
					buffer.append(errors[i]);
					buffer.append(m_separator);
				}

				try {
					m_logFile.writeBytes(buffer.toString());
					checkLogSize();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}

			return tranId;
		}
	}

	public String logException(Exception e) {
		return logException(null, e);
	}

	private String getDateTimeString() {

		return m_logDateFormat.format(new Date());

	}

	private String[] splitTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		Vector v = new Vector();

		e.printStackTrace(pw);

		StringTokenizer st = new StringTokenizer(sw.toString(), m_separator);

		while (st.hasMoreElements()) {
			v.addElement((String) st.nextElement());
		}

		String[] retArray = new String[v.size()];

		v.copyInto(retArray);

		try {
			pw.close();
			sw.close();
		} catch (Exception ex) {
		} finally {
			pw = null;
			sw = null;
			v = null;
		}

		return retArray;
	}

	public static synchronized String getTranId() {
		StringBuffer sb = new StringBuffer();
		Date now = new Date();
		sb.append(now.getTime());
		sb.append("-");
		sb.append(++m_nIncrement);
		if (m_nIncrement > 9999) {
			m_nIncrement = 0;
		}
		sb.toString();
		return sb.toString();
	}

}
