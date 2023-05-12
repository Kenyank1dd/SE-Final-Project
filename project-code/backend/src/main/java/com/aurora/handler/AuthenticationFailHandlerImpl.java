package com.aurora.handler;

import com.alibaba.fastjson.JSON;
import com.aurora.constant.CommonConstant;
import com.aurora.model.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {

    /**
     处理登录失败的逻辑
     @param httpServletRequest HttpServletRequest对象
     @param httpServletResponse HttpServletResponse对象
     @param e AuthenticationException对象，表示发生的认证异常
     @throws IOException 抛出IOException异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        // 设置响应的Content-Type为JSON
        httpServletResponse.setContentType(CommonConstant.APPLICATION_JSON);
        // 返回一个JSON格式的错误信息
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVO.fail(e.getMessage())));
    }

}
