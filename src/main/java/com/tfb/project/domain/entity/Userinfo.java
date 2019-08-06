package com.tfb.project.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("")
public class Userinfo implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    @ApiModelProperty("创建时间")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    @ApiModelProperty("更新时间")
    private Date updateAt;

    private static final long serialVersionUID = 1L;
}