package com.tfb.project.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author leish
 * @date 2017/11/8 18:59
 */
@Data
public class UserReq implements Serializable {


    @ApiModelProperty(value = "用户名", example = "name")
    @Pattern(regexp = "\\w{2,12}", message = "请输入合法的用户名")
    private String name;


    @ApiModelProperty(value = "手机号码", example = "15566668888")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}", message = "请输入合法的手机号码")
    private String phone;
}
