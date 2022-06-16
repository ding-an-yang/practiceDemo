package com.example.model.word;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("附件3-2 分割前传入字符")
public class SplitEnclosure04 {
  /* 附件3-2：交易合约电量表-一口价用户 */
  @ApiModelProperty("附件3-1 年-月-尖市场交易价格/交易基准电价价差")
  private String firstRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String secondRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String thirdRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String fourthRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String fifthRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String sixthRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String seventhRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String eighthRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String ninthRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String tenRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String eleventhRow;
  @ApiModelProperty("附件3-1 年-月-市场交易价格/交易基准电价价差")
  private String twelfthRow;
  @ApiModelProperty("市场交易价格/交易基准电价价差")
  private String electricityCount12;
  @ApiModelProperty("公司名称（盖章）：")
  private String signingCompany;
  @ApiModelProperty("签署人：")
  private String signingName;
  @ApiModelProperty("日期：")
  private String signingDate;
  @ApiModelProperty("公司名称（盖章）：")
  private String signingCompany2;
  @ApiModelProperty("签署人：")
  private String signingName2;
  @ApiModelProperty("日期：")
  private String signingDate2;
}
