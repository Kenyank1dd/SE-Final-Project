package com.aurora.handler;

import com.alibaba.fastjson.JSON;
import com.aurora.constant.CommonConstant;
import com.aurora.model.dto.UserDetailsDTO;
import com.aurora.model.dto.UserInfoDTO;
import com.aurora.entity.UserAuth;
import com.aurora.mapper.UserAuthMapper;
import com.aurora.service.TokenService;
import com.aurora.util.BeanCopyUtil;
import com.aurora.util.UserUtil;
import com.aurora.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 处理登录成功后的逻辑
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserAuthMapper userAuthMapper;  // 用户认证信息的mapper

    @Autowired
    private TokenService tokenService;  // token的服务类

    /**
     处理成功后的逻辑
     @param request HttpServletRequest对象
     @param response HttpServletResponse对象
     @param authentication Authentication对象
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoDTO userLoginDTO = BeanCopyUtil.copyObject(UserUtil.getUserDetailsDTO(), UserInfoDTO.class);
        if (Objects.nonNull(authentication)) {
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
            String token = tokenService.createToken(userDetailsDTO);    // 生成新的token
            userLoginDTO.setToken(token);   // 设置token
        }
        response.setContentType(CommonConstant.APPLICATION_JSON);   // 设置响应的内容
        response.getWriter().write(JSON.toJSONString(ResultVO.ok(userLoginDTO)));// 返回用户信息和token的响应
        updateUserInfo();// 异步更新用户登录信息
    }

    /**
     异步更新用户登录信息
     */
    @Async
    public void updateUserInfo() {
        UserAuth userAuth = UserAuth.builder()
                .id(UserUtil.getUserDetailsDTO().getId()) // 获取当前用户id
                .ipAddress(UserUtil.getUserDetailsDTO().getIpAddress())// 获取当前用户的ip地址
                .ipSource(UserUtil.getUserDetailsDTO().getIpSource())// 获取当前用户的ip来源
                .lastLoginTime(UserUtil.getUserDetailsDTO().getLastLoginTime())  // 获取当前用户的最后登录时间
                .build();
        userAuthMapper.updateById(userAuth);     // 更新用户信息
    }
}
