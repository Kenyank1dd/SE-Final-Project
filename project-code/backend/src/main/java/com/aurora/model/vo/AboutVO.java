package com.aurora.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//VO（View Object）是在 MVC 设计模式中，用于展示数据的对象，一般由前端页面展示使用。它的作用是将
// Controller 或 Service 层返回的结果封装成一个与前端交互的对象，让前端能够方便地获取需要的数据
// 和信息。VO 对象一般不包含业务逻辑，只有简单的 get/set 方法和属性，通常与 DTO（Data Transfer Object）
// 不同的是，DTO 更关注数据传输，而 VO 更关注视图展示

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AboutVO {

    //@ApiModelProperty: Swagger提供的注解，用于描述实体类的属性。其中，name表示属性名
    // ，value表示属性值，required表示属性是否必填，dataType表示属性类型。
    @ApiModelProperty(name = "About内容", value = "content", required = true, dataType = "String")
    private String content;
}
