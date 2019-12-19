package com.dsc.springboot.shiro;

import com.dsc.springboot.service.ManagerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

  @Autowired
  private ManagerService managerService;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    //获取当前登录用户名
    String cur_loginName = (String) principalCollection.getPrimaryPrincipal();
    return null;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    return null;
  }
}
