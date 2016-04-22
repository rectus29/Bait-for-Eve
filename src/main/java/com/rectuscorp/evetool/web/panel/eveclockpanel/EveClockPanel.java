package com.rectuscorp.evetool.web.panel.eveclockpanel;

import com.rectuscorp.evetool.web.Config;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import java.text.SimpleDateFormat;
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

	@Override
	protected void onInitialize() {
		super.onInitialize();

		SimpleDateFormat sdfUTC = new SimpleDateFormat("HH:mm:ss");
		sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateUTC = sdfUTC.format(new Date());

		add(new Label("eveTime", dateUTC));
		add(new Label("realTime", Config.get().dateFormat("HH:mm:ss", new Date())).setOutputMarkupId(true));
	}
}
