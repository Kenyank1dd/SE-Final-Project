package com.aurora.handler;

import com.alibaba.fastjson.JSON;
import com.aurora.constant.CommonConstant;
import com.aurora.model.vo.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//为实现 AccessDeniedHandler 接口的自定义访问拒绝处理器
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     处理访问拒绝的方法。
     @param request HttpServletRequest 请求
     @param response HttpServletResponse 响应
     @param accessDeniedException AccessDeniedException 异常对象
     @throws IOException 输入输出异常
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // 设置响应类型
        response.setContentType(CommonConstant.APPLICATION_JSON);
        // 写入响应内容
        response.getWriter().write(JSON.toJSONString(ResultVO.fail("权限不足")));
    }
}
