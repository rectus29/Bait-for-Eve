package com.rectuscorp.evetool.web.panel.eveclockpanel;

import org.apache.wicket.markup.html.panel.Panel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 21/04/16 14:38                 */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class EveClockPanel extends Panel {

	private Date currentDate = new Date();

	public EveClockPanel(String id) {
		super(id);
	}

}
