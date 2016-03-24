package com.rectuscorp.evetool.tools.feedreader;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 25/02/2016 16:59               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import java.io.Serializable;

public interface IFeedNode extends Serializable{

	public String getSubject();

	public String getCreated();

	public String getContent();

	public String getLink();

	public String getAuthor();

	public String getEnclosure();

}
