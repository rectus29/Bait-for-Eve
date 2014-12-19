package com.rectuscorp.evetool.web.security.signout;

import com.rectuscorp.evetool.web.page.base.BasePage;
import org.apache.shiro.SecurityUtils;

/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for Andil         Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class SignoutPage extends BasePage {

    @SuppressWarnings("unchecked")
    public SignoutPage(){
        this.setStatelessHint( true );
        SecurityUtils.getSubject().logout();
        setResponsePage(getApplication().getHomePage());

    }


    @Override
    public String getTitleContribution() {
        return "logOut";
    }
}
