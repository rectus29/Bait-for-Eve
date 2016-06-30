package com.rectuscorp.evetool.realms;

import com.rectuscorp.evetool.entities.core.Permission;
import com.rectuscorp.evetool.entities.core.User;
import com.rectuscorp.evetool.service.IserviceUser;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

/**
 * The type Eve tool realms.
 */
public class EveToolRealms extends AuthorizingRealm {

    private static final Logger log = LogManager.getLogger(EveToolRealms.class);

	/**
	 * The Service user.
	 */
	protected IserviceUser serviceUser;

	/**
	 * Instantiates a new Eve tool realms.
	 */
	public EveToolRealms() {
        setName("EveToolRealms");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(false);
        setCredentialsMatcher(matcher);
    }

	/**
	 * Sets service user.
	 *
	 * @param serviceUser the service user
	 */
	@Autowired
    public void setServiceUser(IserviceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

   	@Override
    protected SaltedAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = serviceUser.getByProperty("userName",token.getUsername(),true);
        if (user != null) {
			return  new SimpleAuthenticationInfo(user.getId(), user.getPassword(), new SimpleByteSource(Base64.decode(user.getSalt())), getName());
        } else {
            return null;
        }
    }

	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId;
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
