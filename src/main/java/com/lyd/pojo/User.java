package com.lyd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User {
    @JsonIgnore
    @ApiModelProperty(value = "用户id")
    private int id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "用户密码")
    private String pwd;
    @ApiModelProperty(value = "昵称")
    private String nname;
//    @JsonIgnore
    @ApiModelProperty(value = "用户权限组")
    private String perms = "user";
}
