package com.aurora.handler;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//权限决策管理器实现类，用于判断用户是否有访问资源的权限
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    /**
     判断用户是否有访问资源的权限
     @param authentication 认证信息
     @param o 要访问的资源对象
     @param collection 资源的权限集合
     @throws AccessDeniedException 权限不足异常
     @throws InsufficientAuthenticationException 认证信息不足异常
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 获取用户拥有的权限列表
        List<String> permissionList = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 判断用户是否有访问资源的权限
        for (ConfigAttribute item : collection) {
            if (permissionList.contains(item.getAttribute())) {
                return;
            }
        }
        // 若没有权限，则抛出权限不足异常
        throw new AccessDeniedException("权限不足");
    }

    /**
     支持所有类型的ConfigAttribute对象
     @param configAttribute 要判断的ConfigAttribute对象
     @return true
     */
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }
    /**
     支持所有类型的Class对象
     @param aClass 要判断的Class对象
     @return true
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
