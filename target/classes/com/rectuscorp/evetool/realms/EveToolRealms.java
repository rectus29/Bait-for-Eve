package com.rectuscorp.evetool.realms;

import com.rectuscorp.evetool.entities.Permission;
import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;


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


public class EveToolRealms extends AuthorizingRealm {

    private static final Logger log = Logger.getLogger(EveToolRealms.class);

    protected IserviceUser serviceUser;

    public EveToolRealms() {
        setName("MismaCoreRealms"); //This name must match the name in the User class's getPrincipals() method

        //Sha256CredentialsMatcher matcher = new Sha256CredentialsMatcher();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(false);
        setCredentialsMatcher(matcher);
        //setCredentialsMatcher(new SimpleCredentialsMatcher());
    }

    @Autowired
    public void setServiceUser(IserviceUser serviceUser) {
        this.serviceUser = serviceUser;
    }


    protected SaltedAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = serviceUser.getByProperty("username",token.getUsername(),true);
        if (user != null) {
            SimpleAuthenticationInfo auth = new SimpleAuthenticationInfo(user.getId(), user.getPassword(), new SimpleByteSource(Base64.decode(user.getSalt())), getName());
            return auth;
        } else {
            return null;
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = null;
        try {
            userId = (Long) principals.fromRealm(getName()).iterator().next();

            User user = serviceUser.get(userId);

            if (user != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                //apply permission for core only
                for (Permission rc : user.getRole().getPermissions()) {
                    if (rc.getCodeString().startsWith("core"))
                        info.addStringPermission(rc.getCodeString());
                }
                log.debug("permission loading done");
                return info;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }


    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }


}
