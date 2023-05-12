package com.aurora.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter //通过Lombok注解生成get方法
@AllArgsConstructor
public enum CommentTypeEnum {

    ARTICLE(1, "文章", "/articles/"), //定义了枚举类型的实例变量和构造方法，ARTICLE是枚举类型的实例变量名，1是其type属性的值，"文章"是其desc属性的值，"/articles/"是其path属性的值

    MESSAGE(2, "留言", "/message/"),

    ABOUT(3, "关于我", "/about/"),

    LINK(4, "友链", "/friends/"),

    TALK(5, "说说", "/talks/");

    private final Integer type;

    private final String desc;

    private final String path;

    public static String getCommentPath(Integer type) { //静态方法，通过传入type值返回对应CommentTypeEnum实例的path属性值
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.getPath();
            }
        }
        return null;
    }

    public static CommentTypeEnum getCommentEnum(Integer type) {    //静态方法，通过传入type值返回对应的CommentTypeEnum实例
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }

}
