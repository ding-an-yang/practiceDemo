package com.example.model.word;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("附件1-1 分割前传入字符")
public class SplitEnclosure01 {
  /* 附件1-1：《交易电量分月计划预排表》-峰谷分时电价用户 */
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String firstRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String secondRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String thirdRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String fourthRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String fifthRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String sixthRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String seventhRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String eighthRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String ninthRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String tenRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String eleventhRow;
  @ApiModelProperty("附件1-1 年-月-尖峰-高峰-低谷")
  private String twelfthRow;
  @ApiModelProperty("公司名称（盖章）：")
  private String signingCompany;
  @ApiModelProperty("签署人：")
  private String signingName;
  @ApiModelProperty("日期：")
  private String signingDate;

}
