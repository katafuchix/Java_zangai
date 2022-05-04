package jp.co.lastminute.Hotel.detail;

import java.io.*;
import java.util.*;
import jp.co.lastminute.Hotel.detail.Form;


/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface CheckAllote{
	public String getConfirmUrl();
	public Vector setParameter( Form condition );
	public String resultresultFromWebsite( Vector paramsVector );
	public Form checkValue( Form condition );
}
