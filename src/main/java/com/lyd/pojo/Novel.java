package com.lyd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Novel {
    @JsonIgnore
    @ApiModelProperty(value = "小说id")
    private int id;
    @ApiModelProperty(value = "小说名")
    private String name;
    @ApiModelProperty(value = "照片url地址")
    private String photo = "";
    @ApiModelProperty(value = "作者名")
    private String author = "";
    @ApiModelProperty(value = "作品描述")
    private String info = "";
    @JsonIgnore
    @ApiModelProperty(value = "小说原文")
    private String context = "";
    @ApiModelProperty(value = "小说排名")
    @JsonIgnore
    private int rank = 999;
    @ApiModelProperty(value = "是否推荐")
    @JsonIgnore
    private boolean recommend = false;
    @ApiModelProperty(value = "小说类型")
    private String kind = "暂未分类";
    @ApiModelProperty(value = "是否通过审核")
    @JsonIgnore
    private boolean pass = false;
}
