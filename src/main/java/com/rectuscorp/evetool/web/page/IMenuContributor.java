package com.rectuscorp.evetool.web.page;

import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;

import java.util.List;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 30/06/2016 11:54               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public interface IMenuContributor {

	List<MenuElement> getMenuContribution();
}
