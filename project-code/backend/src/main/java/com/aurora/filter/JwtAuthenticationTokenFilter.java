package com.aurora.filter;


import com.aurora.model.dto.UserDetailsDTO;
import com.aurora.service.TokenService;
import com.aurora.util.UserUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@SuppressWarnings("all")    //关闭所有警告信息
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {//继承 OncePerRequestFilter，确保在一次请求只通过一次filter，而不需要重复执行

    @Autowired
    public TokenService tokenService;

    @Autowired
    public AuthenticationEntryPoint authenticationEntryPoint;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        UserDetailsDTO userDetailsDTO = tokenService.getUserDetailDTO(request); //获取请求中携带的用户信息，并存储在 userDetailsDTO 中
        if (Objects.nonNull(userDetailsDTO) && Objects.isNull(UserUtil.getAuthentication())) {  //判断用户信息不为空并且还未进行身份验证
            tokenService.renewToken(userDetailsDTO);    //刷新用户 Token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsDTO, null, userDetailsDTO.getAuthorities());//创建身份验证 Token
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);  //设置到当前安全上下文
        }
        filterChain.doFilter(request, response);    //执行过滤器链的下一个过滤器，如果不存在下一个过滤器，则将请求发送给目标资源
    }
}
