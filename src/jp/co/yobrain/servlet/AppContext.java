package jp.co.yobrain.servlet;

import jp.co.yobrain.io.*;
import jp.co.yobrain.html.*;
import jp.co.yobrain.common.*;
import jp.co.yobrain.logger.*;

import javax.servlet.*;
import java.io.*;
import java.util.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AppContext {
  public static final String LOG_FILE_PATH           = "Log.File.Path";
  public static final String LOG_GENERATION          = "Log.Generation.Count";
  public static final String LOG_MAX_SIZE            = "Log.Max.Size";
  public static final String LOG_FILTER              = "Log.Filter";

  public static final String METHOD_PREFIX           = "Method.";
  public static final String TEMPLATE_PREFIX         = "Template.";

  public static final String DEFAULT_METHOD          = "Default.Method";
  public static final String APP_BASE_URL            = "Application.Base.URL";
  public static final String DEFAULT_TEMPLATE_PATH   = "Default.Template.Path";

  private String             sAppBaseURL     = null;
  private ConfFile           oConf           = null;
  private String             sAppName        = null;
  private MethodList         oMethods        = null;
  private String             sDefaultMethod  = null;
  private String             sTemplatePath   = null;
  private TemplateCacheList  oTemplateCaches = null;
  private EnterpriseLog      oAppLog         = null;


  public String getAppBaseURL()
  {
    return sAppBaseURL;
  }

  protected boolean setAppBaseURL(String baseURL)
  {
    sAppBaseURL = baseURL;

    return true;
  }

  public String getAppName()
  {
    return sAppName;
  }

  protected boolean setAppName(String appName)
  {
    sAppName = new String(appName);

    return true;
  }

  protected boolean setConfigFile(ConfFile confFile)
  {
    oConf = confFile;
    return true;
  }

  public ConfFile getConfigFile()
  {
    return oConf;
  }

  protected boolean setMethodList(MethodList methods)
  {
    oMethods = methods;

    return true;
  }

  protected MethodList getMethodList()
      throws ResourceNotConfiguredException
  {

    if (oMethods == null)
    {
      throw new ResourceNotConfiguredException(
        "No methods are  configured.");
    }

    return oMethods;
  }

  protected boolean setDefaultMethod(String methodName)
  {
    sDefaultMethod = new String(methodName);

    return true;
  }

  public String getDefaultMethod()
    throws ResourceNotConfiguredException
  {

    if (sDefaultMethod == null)
    {
      throw new ResourceNotConfiguredException(
           "The default method is not configured.");
    }

    return sDefaultMethod;
  }

  protected boolean setTemplateCacheList(TemplateCacheList caches)
  {
        oTemplateCaches= caches;

        return true;
  }

  public TemplateCacheList getTemplateCacheList()
      throws ResourceNotConfiguredException
  {

        if (oTemplateCaches == null)
        {
            throw new ResourceNotConfiguredException("No HTML template caches are configured.");
        }

        return oTemplateCaches;
  }

  protected boolean setDefaultTemplatePath(String templatePath)
  {
        this.sTemplatePath = new String(templatePath);

        return true;
  }

  public String getDefaultTemplatePath()
  {
    return sTemplatePath;
  }

  public String getValue(String key)
    throws ResourceNotConfiguredException
  {

    if (oConf == null)
    {
      throw new ResourceNotConfiguredException(
          "The application name is not configured.");
    }

    return oConf.getValue(key);
  }

  public synchronized String logTrace(String msg)
  {
    String transId = "";

    if (isLog())
    {
      try
      {
        transId = oAppLog.logTrace(msg);
      }
      catch (Exception e)
      {
        System.err.print(sAppName + " can't generate log : " + e + " IOException");
      }
    }

    return transId;
  }

  public boolean isLog()
  {
    return (oAppLog != null);
  }

  public synchronized int setLogFilter(int filterMask)
        throws ResourceNotConfiguredException
  {

    if (isLog())
    {
      throw new ResourceNotConfiguredException("The log file is not configured.");
    }

    return oAppLog.setLogFilter(filterMask);
  }

  public synchronized int getLogFilter()
        throws ResourceNotConfiguredException
  {

    if (isLog())
    {
      throw new ResourceNotConfiguredException("The log file is not configured.");
    }

    return oAppLog.getLogFilter();
  }

  public synchronized Object getLogLockSemaphore()
        throws ResourceNotConfiguredException
  {
    if (oAppLog == null)
    {
      throw new ResourceNotConfiguredException("The log file is not configured.");
    }

    return oAppLog.m_lock;
  }

  public synchronized String logInfo(String msg)
  {
    String transId = "";

    if (isLog())
    {
      try
      {
        transId = oAppLog.logInfo(msg);
      }
      catch (Exception e)
      {
        System.err.print(sAppName + " can't generate log : " + e + " IOException");
      }
    }

    return transId;
  }

  public synchronized String logError(String msg, Exception exp)
  {
    String transId = "";

    if (isLog())
    {
      try
      {
        transId = oAppLog.logException(msg, exp);
      }
      catch (Exception e)
      {
        System.err.print(sAppName + " can't generate log : " + e + " IOException");
      }
    }

    return transId;
  }

  public synchronized String logError(Exception exp)
  {
    String transId = "";

    if (isLog())
    {
      try
      {
        transId = oAppLog.logException(exp);
      }
      catch (Exception e)
      {
        System.err.print(sAppName + " can't generate log : " + e + " IOException");
      }
    }

    return transId;
  }

  public void parseConfigFile(String appName,String fileName)
        throws ServletException
  {
    ConfFile cf = new ConfFile(fileName);

    try
    {
      cf.load();
    }
    catch (IOException ioe)
    {
     ioe.printStackTrace();
     throw new ServletException(ioe.getMessage());
    }

    setConfigFile(cf);

    String baseURL = cf.getValue(APP_BASE_URL);
    if (baseURL == null || baseURL.length() == 0)
    {
       throw new ServletException(APP_BASE_URL + " not Supplied.");
    }

    setAppBaseURL(baseURL);

    if (appName == null || appName.length() == 0)
    {
      throw new ServletException("Application Name Not Supplied.");
    }

    setAppName(appName);

    parseLogInfo(cf);

    buildMethodList();

    buildTemplateCache();

  }

  private void parseLogInfo(ConfFile cf)
    throws ServletException
  {

    int                nLogGenerations = 0;
    long               nLogMaxSize     = 0;
    int                nFilter         = 0;

    // Set the max number of log generations
    String sLogGen = cf.getValue(LOG_GENERATION);
    if ((sLogGen != null) && (sLogGen.trim().length() > 0))
    {
      try
      {
        nLogGenerations = Integer.parseInt(sLogGen);
      }
      catch(NumberFormatException nef)
      {
        throw new ServletException(LOG_GENERATION + " contains a non-numeric value.");
      }
    }

    // Set the max log file size
    String sLogSize = cf.getValue(LOG_MAX_SIZE);
    if ((sLogSize != null) && (sLogSize.trim().length() > 0))
    {
      try
      {
        nLogMaxSize = Long.parseLong(sLogSize);
      }
      catch(NumberFormatException nef)
      {
        throw new ServletException(LOG_MAX_SIZE + " contains a non-numeric value.");
      }
    }

    // Get the logging filter
    String sLogFilter = cf.getValue(LOG_FILTER);
    if ((sLogFilter != null) && (sLogFilter.trim().length() > 0))
    {
      StringTokenizer st = new StringTokenizer(sLogFilter, ",");
      while (st.hasMoreTokens())
      {
        String sFilter = st.nextToken();
        if (sFilter.trim().toUpperCase().equals("ALL"))
        {
          nFilter = nFilter | EnterpriseLog.FILTER_ALL;
        } else if (sFilter.trim().toUpperCase().equals("INFO"))
        {
          nFilter = nFilter | EnterpriseLog.FILTER_INFO;
        } else if (sFilter.trim().toUpperCase().equals("TRACE"))
        {
          nFilter = nFilter | EnterpriseLog.FILTER_TRACE;
        } else if (sFilter.trim().toUpperCase().equals("ERROR"))
        {
          nFilter = nFilter | EnterpriseLog.FILTER_ERROR;
        } else
        {
          throw new ServletException(LOG_FILTER + " contains an invalid value.");
        }
      }
    } else
    {
      nFilter = EnterpriseLog.FILTER_ALL;
    }

    // Get the log file to activate logging.

    String logFilePath = cf.getValue(LOG_FILE_PATH);

    if (logFilePath != null)
    {
      try
      {
        oAppLog = new EnterpriseLog(logFilePath, nLogMaxSize, nLogGenerations);
        oAppLog.setLogFilter(nFilter);

        oAppLog.openLog();
      }
      catch(Exception e)
      {
        e.printStackTrace();
        throw new ServletException(e.getMessage());
      }
    }
  }

  public void buildMethodList()
    throws ServletException
  {
    // Get the list of methods.

    ConfFile cf = getConfigFile();

    MethodList ml = new MethodList();

    for (Enumeration m = cf.getKeys() ; m.hasMoreElements() ;)
    {
      String key = (String) m.nextElement();

      if (key.startsWith(METHOD_PREFIX))
      {
         String method = key.substring(METHOD_PREFIX.length());

         if (method.length() > 0)
         {
            String classpath = cf.getValue(key);
            ml.add(method.trim(),classpath.trim());
         }
      }
    }

    setMethodList(ml);

    String defaultMethod = cf.getValue(DEFAULT_METHOD);

    if (defaultMethod != null)
    {
      try
      {
        ml.get(defaultMethod);
      } catch (IllegalArgumentException iae)
      {
        throw new ServletException("Default method " + defaultMethod
            + " is not a valid method.");
      }

      setDefaultMethod(defaultMethod.trim());
    }
  }

  public void buildTemplateCache()
    throws ServletException
  {
    ConfFile cf = getConfigFile();

    String defaultTemplatePath = cf.getValue(DEFAULT_TEMPLATE_PATH);

    if (defaultTemplatePath != null)
    {
      String dirSep = System.getProperty("file.separator");
      if (!defaultTemplatePath.endsWith(dirSep))
      {
        defaultTemplatePath += dirSep;
      }

      setDefaultTemplatePath(defaultTemplatePath.trim());
    }

    TemplateCacheList tcl = new TemplateCacheList();

    for (Enumeration t = cf.getKeys() ; t.hasMoreElements() ;)
    {
      String key = (String) t.nextElement();

      if (key.startsWith(TEMPLATE_PREFIX))
      {
        String template = key.substring(TEMPLATE_PREFIX.length());

        if (template.length() > 0)
        {
          String templateFile = cf.getValue(key);

          try
          {
            HTMLCompiledTemplate hct = new HTMLCompiledTemplate(templateFile.trim());
            tcl.add(template.trim(), hct);
          }
          catch (IOException ioe)
          {
            if (defaultTemplatePath != null)
            {
              try
              {
                templateFile = defaultTemplatePath + cf.getValue(key);
                HTMLCompiledTemplate hct = new HTMLCompiledTemplate(templateFile.trim());
                tcl.add(template.trim(), hct);
              }
              catch (IOException ie)
              {
                ioe.printStackTrace();
                throw new ServletException(ioe.getMessage());
              }
            }
            else
            {
              ioe.printStackTrace();
              throw new ServletException(ioe.getMessage());
            }
          }
        }
      }
    }

    setTemplateCacheList(tcl);
  }

}
