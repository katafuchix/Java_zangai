package jp.co.lastminute.maintenance.hotel;

import jp.co.lastminute.ContextProperty;

/**
 * @author user
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public final class Property {
	public static final int product_type_cd = 4;
	public static final int[][] pagemetrix = {{ 4,0 }, {4,0}, {4,0}};
	public static final int[] page_types = {400, 401, 402};
	public static final String[] page_type_names = {"index.xml", "purpose.xml", "areas.xml"};
	public static final String page_paths = ContextProperty._hotel_resource_Dir;
	public static final String targetname = ContextProperty._Hotel;
}
