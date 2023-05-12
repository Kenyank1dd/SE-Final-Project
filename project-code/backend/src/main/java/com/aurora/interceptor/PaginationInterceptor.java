package com.aurora.interceptor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aurora.util.PageUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

import static com.aurora.constant.CommonConstant.*;

//用于处理分页相关的参数
@Component
@SuppressWarnings("all")
public class PaginationInterceptor implements HandlerInterceptor {
    /**
     在请求处理前处理分页相关的参数。
     @param request HttpServletRequest 对象。
     @param response HttpServletResponse 对象。
     @param handler 处理器。
     @return 返回 true，表示继续执行后续的处理器或请求。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String currentPage = request.getParameter(CURRENT);  // 获取当前页码
        String pageSize = Optional.ofNullable(request.getParameter(SIZE)).orElse(DEFAULT_SIZE); // 获取每页的记录数，如果为空则使用默认值
        if (!Objects.isNull(currentPage) && !StringUtils.isEmpty(currentPage)) {// 如果当前页码不为空，则设置到 ThreadLocal 中
            PageUtil.setCurrentPage(new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize)));
        }
        return true;
    }
    /**
     在请求完成后清除分页相关的参数。
     @param request HttpServletRequest 对象。
     @param response HttpServletResponse 对象。
     @param handler 处理器。
     @param ex 异常。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        PageUtil.remove();// 清除 ThreadLocal 中的分页参数
    }

}