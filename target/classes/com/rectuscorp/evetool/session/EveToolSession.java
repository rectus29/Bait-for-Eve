package com.rectuscorp.evetool.session;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


public class EveToolSession extends WebSession {

    private static final Logger log = Logger.getLogger(EveToolSession.class);


    public static EveToolSession get() {
        return (EveToolSession) Session.get();
    }

    public EveToolSession(Request request) {
        super(request);
        Injector.get().inject(this);
    }

    public boolean logout() {
        SecurityUtils.getSubject().logout();
        return true;
    }

    public boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

}
