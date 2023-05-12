package com.aurora.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//用于自动填充实体类的创建时间和更新时间字段
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     在插入数据时自动填充创建时间
     @param metaObject 实体类的元数据对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }
    /**
     在更新数据时自动填充更新时间
     @param metaObject 实体类的元数据对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
