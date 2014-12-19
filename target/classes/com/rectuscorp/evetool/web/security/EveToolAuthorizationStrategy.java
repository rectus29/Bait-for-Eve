package com.rectuscorp.evetool.web.security;

import com.rectuscorp.evetool.session.EveToolSession;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.security.signin.SigninPage;
import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;

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

public final class EveToolAuthorizationStrategy implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

    private static final Logger log = Logger.getLogger(EveToolAuthorizationStrategy.class);


    public boolean isActionAuthorized(Component component, Action action) {
        return true;
    }

    public boolean isInstantiationAuthorized(Class componentClass) {
        if (ProtectedPage.class.isAssignableFrom(componentClass)) {
            return EveToolSession.get().isAuthenticated();
        }

        return true;
    }

    public void onUnauthorizedInstantiation(Component component) {
        throw new RestartResponseAtInterceptPageException(
                SigninPage.class);
    }
}
