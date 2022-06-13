package com.example.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：yangan
 * @date ：2022/6/2 上午9:15
 * @description：占位符替换内容
 * @version: 1.0
 */
@Data
public class Placeholder2 {
    @ApiModelProperty("甲方（购电方，电力用户）")
    private String firstPartyCompany;
    @ApiModelProperty("乙方（售电方，售电公司）")
    private String secondPartyCompany;
    @ApiModelProperty("首页 年")
    private String firstPageYear;
    @ApiModelProperty("首页 月")
    private String firstPageMonth;
    @ApiModelProperty("首页 日")
    private String firstPageDay;
    /* 甲方提供联络通讯信息如下：*/
    @ApiModelProperty("购电方（电力用户，以下简称甲方）")
    private String firstPartyCompany2;
    @ApiModelProperty("...的用电企业")
    private String powerConsumptionEnterprise;
    @ApiModelProperty("甲方 企业所在地")
    private String firstEnterpriseLocation;
    @ApiModelProperty("甲方 市场监督管理局")
    private String firstMarketSupervisionAdministration;
    @ApiModelProperty("甲方 统一社会信用代码或税务登记号")
    private String firstTaxRegistrationNo;
    @ApiModelProperty("甲方 住所")
    private String firstResidence;
    @ApiModelProperty("甲方 法定代表人")
    private String firstLegalRepresentativeName;
    @ApiModelProperty("甲方 电压等级")
    private String voltageGrade;
    @ApiModelProperty("甲方 变压器容量")
    private String transformerCapacity;
    @ApiModelProperty("甲方 用电户号")
    private String firstElectricityAccount;
    @ApiModelProperty("甲方 联系人")
    private String firstContacts;
    @ApiModelProperty("甲方 电子邮箱")
    private String firstEmail;
    @ApiModelProperty("甲方 电  话")
    private String firstTelephone;
    @ApiModelProperty("甲方 手  机")
    private String firstMobilePhone;
    @ApiModelProperty("甲方 传  真")
    private String firstFax;
    @ApiModelProperty("甲方 邮  编")
    private String firstPostalCode;
    @ApiModelProperty("甲方 通讯地址")
    private String firstPostalAddress;
    @ApiModelProperty("甲方 开户银行")
    private String firstDepositBank;
    @ApiModelProperty("甲方 银行账号")
    private String firstBankAccount;

    /* 乙方提供联络通讯信息如下：*/
    @ApiModelProperty("售电方（售电公司，以下简称乙方）")
    private String secondPartyCompany2;
    @ApiModelProperty("乙方 企业所在地")
    private String secondEnterpriseLocation;
    @ApiModelProperty("乙方 市场监督管理局")
    private String secondMarketSupervisionAdministration;
    @ApiModelProperty("乙方 统一社会信用代码或税务登记号")
    private String secondTaxRegistrationNo;
    @ApiModelProperty("乙方 住所")
    private String secondResidence;
    @ApiModelProperty("乙方 法定代表人")
    private String secondLegalRepresentativeName;
    @ApiModelProperty("乙方 联系人")
    private String secondContacts;
    @ApiModelProperty("乙方 电子邮箱")
    private String secondEmail;
    @ApiModelProperty("乙方 电  话")
    private String secondTelephone;
    @ApiModelProperty("乙方 手  机")
    private String secondMobilePhone;
    @ApiModelProperty("乙方 传  真")
    private String secondFax;
    @ApiModelProperty("乙方 邮  编")
    private String secondPostalCode;
    @ApiModelProperty("乙方 通讯地址")
    private String secondPostalAddress;
    @ApiModelProperty("乙方 开户银行")
    private String secondDepositBank;
    @ApiModelProperty("乙方 银行账号")
    private String secondBankAccount;

    /* 第4章 交易电量、电价 */
    @ApiModelProperty("起始日期 年")
    private String startYear;
    @ApiModelProperty("起始日期 月")
    private String startMonth;
    @ApiModelProperty("起始日期 日")
    private String startDay;
    @ApiModelProperty("结束日期 年")
    private String endYear;
    @ApiModelProperty("结束日期 月")
    private String endMonth;
    @ApiModelProperty("结束日期 日")
    private String endDay;
    @ApiModelProperty("交易电量")
    private Double tradingCapacity;
    @ApiModelProperty("用电上浮")
    private Double floatPrice;
    /* 4.4.1 选择方式*/
    @ApiModelProperty("处理方式 1 2 3")
    private Integer chooseModel;
    /* 方式1*/
    @ApiModelProperty("处理方式1 尖峰时段价格")
    private Double peakPeriodPrice;
    @ApiModelProperty("处理方式1 高峰时段价格")
    private Double peakHourPrice;
    @ApiModelProperty("处理方式1 低谷时段价格")
    private Double lowPeriodPrice;
    /* 方式2*/
    @ApiModelProperty("处理方式2 比例分担 甲方")
    private Double firstProportionalShare;
    @ApiModelProperty("处理方式2 比例分担 乙方")
    private Double secondProportionalShare;
    @ApiModelProperty("处理方式2 比例分成 甲方")
    private Double firstProportionalShare2;
    @ApiModelProperty("处理方式2 比例分成 乙方")
    private Double secondProportionalShare2;
    /* 方式3*/
    @ApiModelProperty("处理方式3 尖峰时段价格")
    private Double peakPeriodPrice2;
    @ApiModelProperty("处理方式3 高峰时段价格")
    private Double peakHourPrice2;
    @ApiModelProperty("处理方式3 低谷时段价格")
    private Double lowPeriodPrice2;

    /* 4.4.2 处理方式*/
    @ApiModelProperty("处理方式 1 2 3")
    private Integer chooseModel2;
    /* 方式1*/
    @ApiModelProperty("处理方式1 交易价格固定")
    private Double fixedTransactionPrice;
    /* 方式2*/
    @ApiModelProperty("处理方式2 比例分担 甲方")
    private Double firstProportionalShare3;
    @ApiModelProperty("处理方式2 比例分担 乙方")
    private Double secondProportionalShare3;
    @ApiModelProperty("处理方式2 比例分成 甲方")
    private Double firstProportionalShare4;
    @ApiModelProperty("处理方式2 比例分成 乙方")
    private Double secondProportionalShare4;
    /* 方式3*/
    @ApiModelProperty("处理方式3 尖峰时段价格")
    private Double peakPeriodPrice3;

    /* 第12章 合同生效和期限 */
    @ApiModelProperty("起始日期 年")
    private String startContractYear;
    @ApiModelProperty("起始日期 月")
    private String startContractMonth;
    @ApiModelProperty("起始日期 日")
    private String startContractDay;
    @ApiModelProperty("结束日期 年")
    private String endContractYear;
    @ApiModelProperty("结束日期 月")
    private String endContractMonth;
    @ApiModelProperty("结束日期 日")
    private String endContractDay;

    /* 第13章 其他 */
    @ApiModelProperty("甲方（盖章）：")
    private String firstCompanyName;
    @ApiModelProperty("甲方 法定代表人/授权代理人")
    private String firstLegalRepresentativeName2;
    @ApiModelProperty("签订日期 年")
    private String firstSigningDateYear;
    @ApiModelProperty("签订日期 月")
    private String firstSigningDateMonth;
    @ApiModelProperty("签订日期 日")
    private String firstSigningDateDay;
    @ApiModelProperty("乙方（盖章）：")
    private String secondCompanyName;
    @ApiModelProperty("乙方 法定代表人/授权代理人")
    private String secondLegalRepresentativeName2;
    @ApiModelProperty("签订日期 年")
    private String secondSigningDateYear;
    @ApiModelProperty("签订日期 月")
    private String secondSigningDateMonth;
    @ApiModelProperty("签订日期 日")
    private String secondSigningDateDay;

    /* 附件2：《交易电量确认函》 */
    @ApiModelProperty("附件2 用户")
    private String userName;
    @ApiModelProperty("附件2 年")
    private String year1;
    @ApiModelProperty("附件2 月")
    private String month1;
    @ApiModelProperty("附件2 尖峰")
    private Double peak1;
    @ApiModelProperty("附件2 高峰")
    private Double height1;
    @ApiModelProperty("附件2 低谷")
    private Double trough1;
    @ApiModelProperty("附件2 年")
    private String year2;
    @ApiModelProperty("附件2 月")
    private String month2;
    @ApiModelProperty("交易合约电量分月计划（万千瓦时）")
    private Double electricityCount;
    @ApiModelProperty("公司名称（盖章）：")
    private String signingCompany;
    @ApiModelProperty("签署人：")
    private String signingName;
    @ApiModelProperty("日期：")
    private String signingDate;
}