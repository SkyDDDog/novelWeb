package com.lyd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户收藏夹")
public class UserCollection {
    @JsonIgnore
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "小说名")
    private String novelname;
}
