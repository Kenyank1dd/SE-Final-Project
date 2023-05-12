package com.aurora.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//DTO，全称为 Data Transfer Object，即数据传输对象，是一种设计模式，它的
// 作用是在不同的层（如前端、后端、数据库层）之间传输数据。
//DTO通常用于在不同层间进行数据传输，使不同层间解耦，从而提高系统的可维护性和可扩展性。
// 在实际开发中，我们通常使用DTO来封装需要进行传输的数据，以减少不必要的数据交互，减少
// 数据传输量，提高传输效率，同时可以对传输的数据进行封装和校验，提高数据的安全性和稳定性。
//后面的dto文件中除了一些新的注解，其他不做过多注释
@Data
@Builder     // 使用Builder模式构建对象
@NoArgsConstructor
@AllArgsConstructor
public class AboutDTO {

    private String Content;

}
