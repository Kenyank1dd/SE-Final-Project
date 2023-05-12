package com.aurora.interceptor;

import com.alibaba.fastjson.JSON;

import com.aurora.annotation.AccessLimit;
import com.aurora.model.vo.ResultVO;
import com.aurora.service.RedisService;
import com.aurora.util.IpUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static com.aurora.constant.CommonConstant.APPLICATION_JSON;

/**
 访问限制拦截器，实现对请求频率的限制
 */
@Log4j2
@Component
@SuppressWarnings("all")
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    /**
     在请求被处理前执行，实现访问次数的限制
     @param httpServletRequest HTTP请求
     @param httpServletResponse HTTP响应
     @param handler 处理器
     @return 是否通过拦截器
     @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (accessLimit != null) {
                // 获取注解中的参数
                long seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                // 构建key
                String key = IpUtil.getIpAddress(httpServletRequest) + "-" + handlerMethod.getMethod().getName();
                try {
                    // 增加计数器并设置过期时间
                    long q = redisService.incrExpire(key, seconds);
                    if (q > maxCount) {
                        // 如果超过访问次数限制，则返回失败信息
                        render(httpServletResponse, ResultVO.fail("请求过于频繁，" + seconds + "秒后再试"));
                        log.warn(key + "请求次数超过每" + seconds + "秒" + maxCount + "次");
                        return false;
                    }
                    // 如果超过访问次数限制，则返回失败信息
                    return true;
                } catch (RedisConnectionFailureException e) {
                    log.warn("redis错误: " + e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
    /**
     输出JSON格式的响应信息
     @param response HTTP响应
     @param resultVO 响应数据
     @throws Exception 异常
     */
    private void render(HttpServletResponse response, ResultVO<?> resultVO) throws Exception {
        response.setContentType(APPLICATION_JSON);
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(resultVO);
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

}
