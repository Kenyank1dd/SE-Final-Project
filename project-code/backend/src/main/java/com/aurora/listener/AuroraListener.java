package com.aurora.listener;

import com.aurora.entity.ExceptionLog;
import com.aurora.entity.OperationLog;
import com.aurora.event.ExceptionLogEvent;
import com.aurora.event.OperationLogEvent;
import com.aurora.mapper.ExceptionLogMapper;
import com.aurora.mapper.OperationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//该类是一个Spring事件监听器，用于监听操作日志和异常日志事件。
//saveOperationLog()方法使用@EventListener注解来监听操作日志事件(OperationLogEvent)，并在接收到事件时将事件源
// (OperationLog)插入到数据库中。这里使用@Async注解将该方法标记为异步执行，以避免阻塞主线程。
//saveExceptionLog()方法也是类似的，用于监听异常日志事件(ExceptionLogEvent)并将事件源(ExceptionLog)插入到数据库中。
@Component
public class AuroraListener {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    // 异步监听操作日志事件并保存到数据库
    @Async
    @EventListener(OperationLogEvent.class)
    public void saveOperationLog(OperationLogEvent operationLogEvent) {
        operationLogMapper.insert((OperationLog) operationLogEvent.getSource());
    }

    // 异步监听异常日志事件并保存到数据库
    @Async
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent exceptionLogEvent) {
        exceptionLogMapper.insert((ExceptionLog) exceptionLogEvent.getSource());
    }


}
