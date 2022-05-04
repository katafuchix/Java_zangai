package jp.co.lastminute.maintenance;

import java.util.Properties;
import java.io.*;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Authenticater {
	public UserInfo getAuthentication(String user_id) {
		//todo add DB check

		/////////////////// TODO //////////////////////////
		//temp
		Properties tempUserTable = new Properties();
		UserInfo user = new UserInfo();
		try {
			InputStream IS =
				this.getClass().getResourceAsStream("tempUserTable.properties");
			tempUserTable.load(IS);
			String _user_id = tempUserTable.getProperty("user_id");
			if (!user_id.equals(_user_id)) {
				user = null;
			} else {
				user.setUser_id(_user_id);
				user.setPassword(tempUserTable.getProperty("password"));

			}
		} catch (IOException e) {
			e.printStackTrace();			
		}
		return user;
	}
}
