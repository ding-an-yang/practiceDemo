package com.example.util;

import com.example.model.word.*;

import static com.example.util.ReplaceWord.isBlank;

/**
 * @author ：yangan
 * @date ：2022/6/14 下午10:00
 * @description：
 * @version:
 */
public class SplitEnclosure {

    /**
     * 为减少前端传入的参数，附件中的参数按行传入之后进行分割
     * 前端传入的格式 年_月_尖峰_高峰_低谷
     * @param enclosure01 分割的字符设置对象
     * @param splitEnclosure01 传入未分割前的对象
     * @return 返回分割设置好的 Enclosure01
     */
    public static Enclosure01 splitSetEnclosure01(Enclosure01 enclosure01, SplitEnclosure01 splitEnclosure01){
        if (splitEnclosure01 == null){
            return null;
        }

        if (!isBlank(splitEnclosure01.getFirstRow())){
            String[] firstRow = splitEnclosure01.getFirstRow().split("_");
            enclosure01.setYear1(firstRow[0]);
            enclosure01.setMonth1(firstRow[1]);
            enclosure01.setPeak1(firstRow[2]);
            enclosure01.setHeight1(firstRow[3]);
            enclosure01.setTrough1(firstRow[4]);
        }

        if (!isBlank(splitEnclosure01.getSecondRow())){
            String[] secondRow = splitEnclosure01.getSecondRow().split("_");
            enclosure01.setYear2(secondRow[0]);
            enclosure01.setMonth2(secondRow[1]);
            enclosure01.setPeak2(secondRow[2]);
            enclosure01.setHeight2(secondRow[3]);
            enclosure01.setTrough2(secondRow[4]);
        }

        if(!isBlank(splitEnclosure01.getThirdRow())){
            String[] thirdRow = splitEnclosure01.getThirdRow().split("_");
            enclosure01.setYear3(thirdRow[0]);
            enclosure01.setMonth3(thirdRow[1]);
            enclosure01.setPeak3(thirdRow[2]);
            enclosure01.setHeight3(thirdRow[3]);
            enclosure01.setTrough3(thirdRow[4]);
        }

        if(!isBlank(splitEnclosure01.getFourthRow())){
            String[] fourthRow = splitEnclosure01.getFourthRow().split("_");
            enclosure01.setYear4(fourthRow[0]);
            enclosure01.setMonth4(fourthRow[1]);
            enclosure01.setPeak4(fourthRow[2]);
            enclosure01.setHeight4(fourthRow[3]);
            enclosure01.setTrough4(fourthRow[4]);
        }

        if(!isBlank(splitEnclosure01.getFifthRow())){
            String[] fifthRow = splitEnclosure01.getFifthRow().split("_");
            enclosure01.setYear5(fifthRow[0]);
            enclosure01.setMonth5(fifthRow[1]);
            enclosure01.setPeak5(fifthRow[2]);
            enclosure01.setHeight5(fifthRow[3]);
            enclosure01.setTrough5(fifthRow[4]);
        }

        if(!isBlank(splitEnclosure01.getSixthRow())){
            String[] sixthRow = splitEnclosure01.getSixthRow().split("_");
            enclosure01.setYear6(sixthRow[0]);
            enclosure01.setMonth6(sixthRow[1]);
            enclosure01.setPeak6(sixthRow[2]);
            enclosure01.setHeight6(sixthRow[3]);
            enclosure01.setTrough6(sixthRow[4]);
        }

        if(!isBlank(splitEnclosure01.getSeventhRow())){
            String[] seventhRow = splitEnclosure01.getSeventhRow().split("_");
            enclosure01.setYear7(seventhRow[0]);
            enclosure01.setMonth7(seventhRow[1]);
            enclosure01.setPeak7(seventhRow[2]);
            enclosure01.setHeight7(seventhRow[3]);
            enclosure01.setTrough7(seventhRow[4]);
        }

        if(!isBlank(splitEnclosure01.getEighthRow())){
            String[] eighthRow = splitEnclosure01.getEighthRow().split("_");
            enclosure01.setYear8(eighthRow[0]);
            enclosure01.setMonth8(eighthRow[1]);
            enclosure01.setPeak8(eighthRow[2]);
            enclosure01.setHeight8(eighthRow[3]);
            enclosure01.setTrough8(eighthRow[4]);
        }

        if(!isBlank(splitEnclosure01.getNinthRow())){
            String[] ninthRow = splitEnclosure01.getNinthRow().split("_");
            enclosure01.setYear9(ninthRow[0]);
            enclosure01.setMonth9(ninthRow[1]);
            enclosure01.setPeak9(ninthRow[2]);
            enclosure01.setHeight9(ninthRow[3]);
            enclosure01.setTrough9(ninthRow[4]);
        }

        if(!isBlank(splitEnclosure01.getTenRow())){
            String[] tenRow = splitEnclosure01.getTenRow().split("_");
            enclosure01.setYear10(tenRow[0]);
            enclosure01.setMonth10(tenRow[1]);
            enclosure01.setPeak10(tenRow[2]);
            enclosure01.setHeight10(tenRow[3]);
            enclosure01.setTrough10(tenRow[4]);
        }

        if(!isBlank(splitEnclosure01.getEleventhRow())){
            String[] eleventhRow = splitEnclosure01.getEleventhRow().split("_");
            enclosure01.setYear11(eleventhRow[0]);
            enclosure01.setMonth11(eleventhRow[1]);
            enclosure01.setPeak11(eleventhRow[2]);
            enclosure01.setHeight11(eleventhRow[3]);
            enclosure01.setTrough11(eleventhRow[4]);
        }

        if(!isBlank(splitEnclosure01.getTwelfthRow())){
            String[] twelfthRow = splitEnclosure01.getTwelfthRow().split("_");
            enclosure01.setYear12(twelfthRow[0]);
            enclosure01.setMonth12(twelfthRow[1]);
            enclosure01.setPeak12(twelfthRow[2]);
            enclosure01.setHeight12(twelfthRow[3]);
            enclosure01.setTrough12(twelfthRow[4]);
        }

        enclosure01.setSigningCompany(splitEnclosure01.getSigningCompany());
        enclosure01.setSigningName(splitEnclosure01.getSigningName());
        enclosure01.setSigningDate(splitEnclosure01.getSigningDate());
        return enclosure01;
    }

    /**
     * 为减少前端传入的参数，附件中的参数按行传入之后进行分割
     * 前端传入的格式 年_月_交易合约电量分月计划（万千瓦时）
     * @param enclosure02 分割的字符设置对象
     * @param splitEnclosure02 传入未分割前的对象
     * @return 返回分割设置好的 Enclosure02
     */
    public static Enclosure02 splitSetEnclosure02(Enclosure02 enclosure02, SplitEnclosure02 splitEnclosure02){
        if (splitEnclosure02 == null){
            return null;
        }

        if (!isBlank(splitEnclosure02.getFirstRow())){
            String[] firstRow = splitEnclosure02.getFirstRow().split("_");
            enclosure02.setYear1(firstRow[0]);
            enclosure02.setMonth1(firstRow[1]);
            enclosure02.setElectricityCount1(firstRow[2]);
        }

        if (!isBlank(splitEnclosure02.getSecondRow())){
            String[] secondRow = splitEnclosure02.getSecondRow().split("_");
            enclosure02.setYear2(secondRow[0]);
            enclosure02.setMonth2(secondRow[1]);
            enclosure02.setElectricityCount1(secondRow[2]);
        }

        if(!isBlank(splitEnclosure02.getThirdRow())){
            String[] thirdRow = splitEnclosure02.getThirdRow().split("_");
            enclosure02.setYear3(thirdRow[0]);
            enclosure02.setMonth3(thirdRow[1]);
            enclosure02.setElectricityCount1(thirdRow[2]);
        }

        if(!isBlank(splitEnclosure02.getFourthRow())){
            String[] fourthRow = splitEnclosure02.getFourthRow().split("_");
            enclosure02.setYear4(fourthRow[0]);
            enclosure02.setMonth4(fourthRow[1]);
            enclosure02.setElectricityCount1(fourthRow[2]);
        }

        if(!isBlank(splitEnclosure02.getFifthRow())){
            String[] fifthRow = splitEnclosure02.getFifthRow().split("_");
            enclosure02.setYear5(fifthRow[0]);
            enclosure02.setMonth5(fifthRow[1]);
            enclosure02.setElectricityCount1(fifthRow[2]);
        }

        if(!isBlank(splitEnclosure02.getSixthRow())){
            String[] sixthRow = splitEnclosure02.getSixthRow().split("_");
            enclosure02.setYear6(sixthRow[0]);
            enclosure02.setMonth6(sixthRow[1]);
            enclosure02.setElectricityCount1(sixthRow[2]);
        }

        if(!isBlank(splitEnclosure02.getSeventhRow())){
            String[] seventhRow = splitEnclosure02.getSeventhRow().split("_");
            enclosure02.setYear7(seventhRow[0]);
            enclosure02.setMonth7(seventhRow[1]);
            enclosure02.setElectricityCount1(seventhRow[2]);
        }

        if(!isBlank(splitEnclosure02.getEighthRow())){
            String[] eighthRow = splitEnclosure02.getEighthRow().split("_");
            enclosure02.setYear8(eighthRow[0]);
            enclosure02.setMonth8(eighthRow[1]);
            enclosure02.setElectricityCount1(eighthRow[2]);
        }

        if(!isBlank(splitEnclosure02.getNinthRow())){
            String[] ninthRow = splitEnclosure02.getNinthRow().split("_");
            enclosure02.setYear9(ninthRow[0]);
            enclosure02.setMonth9(ninthRow[1]);
            enclosure02.setElectricityCount1(ninthRow[2]);
        }

        if(!isBlank(splitEnclosure02.getTenRow())){
            String[] tenRow = splitEnclosure02.getTenRow().split("_");
            enclosure02.setYear10(tenRow[0]);
            enclosure02.setMonth10(tenRow[1]);
            enclosure02.setElectricityCount1(tenRow[2]);
        }

        if(!isBlank(splitEnclosure02.getEleventhRow())){
            String[] eleventhRow = splitEnclosure02.getEleventhRow().split("_");
            enclosure02.setYear11(eleventhRow[0]);
            enclosure02.setMonth11(eleventhRow[1]);
            enclosure02.setElectricityCount1(eleventhRow[2]);
        }

        if(!isBlank(splitEnclosure02.getTwelfthRow())){
            String[] twelfthRow = splitEnclosure02.getTwelfthRow().split("_");
            enclosure02.setYear12(twelfthRow[0]);
            enclosure02.setMonth12(twelfthRow[1]);
            enclosure02.setElectricityCount1(twelfthRow[2]);
        }

        enclosure02.setSigningCompany(splitEnclosure02.getSigningCompany());
        enclosure02.setSigningName(splitEnclosure02.getSigningName());
        enclosure02.setSigningDate(splitEnclosure02.getSigningDate());
        return enclosure02;
    }

    /**
     * 为减少前端传入的参数，附件中的参数按行传入之后进行分割
     * 前端传入的格式 年_月_尖峰_高峰_低谷
     * @param enclosure03 分割的字符设置对象
     * @param splitEnclosure03 传入未分割前的对象
     * @return 返回分割设置好的 Enclosure01
     */
    public static Enclosure03 splitSetEnclosure03(Enclosure03 enclosure03, SplitEnclosure03 splitEnclosure03){
        if (splitEnclosure03 == null){
            return null;
        }

        if (!isBlank(splitEnclosure03.getFirstRow())){
            String[] firstRow = splitEnclosure03.getFirstRow().split("_");
            enclosure03.setYear1(firstRow[0]);
            enclosure03.setMonth1(firstRow[1]);
            enclosure03.setPeak1(firstRow[2]);
            enclosure03.setHeight1(firstRow[3]);
            enclosure03.setTrough1(firstRow[4]);
        }

        if (!isBlank(splitEnclosure03.getSecondRow())){
            String[] secondRow = splitEnclosure03.getSecondRow().split("_");
            enclosure03.setYear2(secondRow[0]);
            enclosure03.setMonth2(secondRow[1]);
            enclosure03.setPeak2(secondRow[2]);
            enclosure03.setHeight2(secondRow[3]);
            enclosure03.setTrough2(secondRow[4]);
        }

        if(!isBlank(splitEnclosure03.getThirdRow())){
            String[] thirdRow = splitEnclosure03.getThirdRow().split("_");
            enclosure03.setYear3(thirdRow[0]);
            enclosure03.setMonth3(thirdRow[1]);
            enclosure03.setPeak3(thirdRow[2]);
            enclosure03.setHeight3(thirdRow[3]);
            enclosure03.setTrough3(thirdRow[4]);
        }

        if(!isBlank(splitEnclosure03.getFourthRow())){
            String[] fourthRow = splitEnclosure03.getFourthRow().split("_");
            enclosure03.setYear4(fourthRow[0]);
            enclosure03.setMonth4(fourthRow[1]);
            enclosure03.setPeak4(fourthRow[2]);
            enclosure03.setHeight4(fourthRow[3]);
            enclosure03.setTrough4(fourthRow[4]);
        }

        if(!isBlank(splitEnclosure03.getFifthRow())){
            String[] fifthRow = splitEnclosure03.getFifthRow().split("_");
            enclosure03.setYear5(fifthRow[0]);
            enclosure03.setMonth5(fifthRow[1]);
            enclosure03.setPeak5(fifthRow[2]);
            enclosure03.setHeight5(fifthRow[3]);
            enclosure03.setTrough5(fifthRow[4]);
        }

        if(!isBlank(splitEnclosure03.getSixthRow())){
            String[] sixthRow = splitEnclosure03.getSixthRow().split("_");
            enclosure03.setYear6(sixthRow[0]);
            enclosure03.setMonth6(sixthRow[1]);
            enclosure03.setPeak6(sixthRow[2]);
            enclosure03.setHeight6(sixthRow[3]);
            enclosure03.setTrough6(sixthRow[4]);
        }

        if(!isBlank(splitEnclosure03.getSeventhRow())){
            String[] seventhRow = splitEnclosure03.getSeventhRow().split("_");
            enclosure03.setYear7(seventhRow[0]);
            enclosure03.setMonth7(seventhRow[1]);
            enclosure03.setPeak7(seventhRow[2]);
            enclosure03.setHeight7(seventhRow[3]);
            enclosure03.setTrough7(seventhRow[4]);
        }

        if(!isBlank(splitEnclosure03.getEighthRow())){
            String[] eighthRow = splitEnclosure03.getEighthRow().split("_");
            enclosure03.setYear8(eighthRow[0]);
            enclosure03.setMonth8(eighthRow[1]);
            enclosure03.setPeak8(eighthRow[2]);
            enclosure03.setHeight8(eighthRow[3]);
            enclosure03.setTrough8(eighthRow[4]);
        }

        if(!isBlank(splitEnclosure03.getNinthRow())){
            String[] ninthRow = splitEnclosure03.getNinthRow().split("_");
            enclosure03.setYear9(ninthRow[0]);
            enclosure03.setMonth9(ninthRow[1]);
            enclosure03.setPeak9(ninthRow[2]);
            enclosure03.setHeight9(ninthRow[3]);
            enclosure03.setTrough9(ninthRow[4]);
        }

        if(!isBlank(splitEnclosure03.getTenRow())){
            String[] tenRow = splitEnclosure03.getTenRow().split("_");
            enclosure03.setYear10(tenRow[0]);
            enclosure03.setMonth10(tenRow[1]);
            enclosure03.setPeak10(tenRow[2]);
            enclosure03.setHeight10(tenRow[3]);
            enclosure03.setTrough10(tenRow[4]);
        }

        if(!isBlank(splitEnclosure03.getEleventhRow())){
            String[] eleventhRow = splitEnclosure03.getEleventhRow().split("_");
            enclosure03.setYear11(eleventhRow[0]);
            enclosure03.setMonth11(eleventhRow[1]);
            enclosure03.setPeak11(eleventhRow[2]);
            enclosure03.setHeight11(eleventhRow[3]);
            enclosure03.setTrough11(eleventhRow[4]);
        }

        if(!isBlank(splitEnclosure03.getTwelfthRow())){
            String[] twelfthRow = splitEnclosure03.getTwelfthRow().split("_");
            enclosure03.setYear12(twelfthRow[0]);
            enclosure03.setMonth12(twelfthRow[1]);
            enclosure03.setPeak12(twelfthRow[2]);
            enclosure03.setHeight12(twelfthRow[3]);
            enclosure03.setTrough12(twelfthRow[4]);
        }

        enclosure03.setSigningCompany(splitEnclosure03.getSigningCompany());
        enclosure03.setSigningName(splitEnclosure03.getSigningName());
        enclosure03.setSigningDate(splitEnclosure03.getSigningDate());
        return enclosure03;
    }

    /**
     * 为减少前端传入的参数，附件中的参数按行传入之后进行分割
     * 前端传入的格式 年_月_市场交易价格/交易基准电价价差
     * @param enclosure04 分割的字符设置对象
     * @param splitEnclosure04 传入未分割前的对象
     * @return 返回分割设置好的 Enclosure02
     */
    public static Enclosure04 splitSetEnclosure04(Enclosure04 enclosure04, SplitEnclosure04 splitEnclosure04){
        if (splitEnclosure04 == null){
            return null;
        }

        if (!isBlank(splitEnclosure04.getFirstRow())){
            String[] firstRow = splitEnclosure04.getFirstRow().split("_");
            enclosure04.setYear1(firstRow[0]);
            enclosure04.setMonth1(firstRow[1]);
            enclosure04.setElectricityCount1(firstRow[2]);
        }

        if (!isBlank(splitEnclosure04.getSecondRow())){
            String[] secondRow = splitEnclosure04.getSecondRow().split("_");
            enclosure04.setYear2(secondRow[0]);
            enclosure04.setMonth2(secondRow[1]);
            enclosure04.setElectricityCount1(secondRow[2]);
        }

        if(!isBlank(splitEnclosure04.getThirdRow())){
            String[] thirdRow = splitEnclosure04.getThirdRow().split("_");
            enclosure04.setYear3(thirdRow[0]);
            enclosure04.setMonth3(thirdRow[1]);
            enclosure04.setElectricityCount1(thirdRow[2]);
        }

        if(!isBlank(splitEnclosure04.getFourthRow())){
            String[] fourthRow = splitEnclosure04.getFourthRow().split("_");
            enclosure04.setYear4(fourthRow[0]);
            enclosure04.setMonth4(fourthRow[1]);
            enclosure04.setElectricityCount1(fourthRow[2]);
        }

        if(!isBlank(splitEnclosure04.getFifthRow())){
            String[] fifthRow = splitEnclosure04.getFifthRow().split("_");
            enclosure04.setYear5(fifthRow[0]);
            enclosure04.setMonth5(fifthRow[1]);
            enclosure04.setElectricityCount1(fifthRow[2]);
        }

        if(!isBlank(splitEnclosure04.getSixthRow())){
            String[] sixthRow = splitEnclosure04.getSixthRow().split("_");
            enclosure04.setYear6(sixthRow[0]);
            enclosure04.setMonth6(sixthRow[1]);
            enclosure04.setElectricityCount1(sixthRow[2]);
        }

        if(!isBlank(splitEnclosure04.getSeventhRow())){
            String[] seventhRow = splitEnclosure04.getSeventhRow().split("_");
            enclosure04.setYear7(seventhRow[0]);
            enclosure04.setMonth7(seventhRow[1]);
            enclosure04.setElectricityCount1(seventhRow[2]);
        }

        if(!isBlank(splitEnclosure04.getEighthRow())){
            String[] eighthRow = splitEnclosure04.getEighthRow().split("_");
            enclosure04.setYear8(eighthRow[0]);
            enclosure04.setMonth8(eighthRow[1]);
            enclosure04.setElectricityCount1(eighthRow[2]);
        }

        if(!isBlank(splitEnclosure04.getNinthRow())){
            String[] ninthRow = splitEnclosure04.getNinthRow().split("_");
            enclosure04.setYear9(ninthRow[0]);
            enclosure04.setMonth9(ninthRow[1]);
            enclosure04.setElectricityCount1(ninthRow[2]);
        }

        if(!isBlank(splitEnclosure04.getTenRow())){
            String[] tenRow = splitEnclosure04.getTenRow().split("_");
            enclosure04.setYear10(tenRow[0]);
            enclosure04.setMonth10(tenRow[1]);
            enclosure04.setElectricityCount1(tenRow[2]);
        }

        if(!isBlank(splitEnclosure04.getEleventhRow())){
            String[] eleventhRow = splitEnclosure04.getEleventhRow().split("_");
            enclosure04.setYear11(eleventhRow[0]);
            enclosure04.setMonth11(eleventhRow[1]);
            enclosure04.setElectricityCount1(eleventhRow[2]);
        }

        if(!isBlank(splitEnclosure04.getTwelfthRow())){
            String[] twelfthRow = splitEnclosure04.getTwelfthRow().split("_");
            enclosure04.setYear12(twelfthRow[0]);
            enclosure04.setMonth12(twelfthRow[1]);
            enclosure04.setElectricityCount1(twelfthRow[2]);
        }

        enclosure04.setSigningCompany(splitEnclosure04.getSigningCompany());
        enclosure04.setSigningName(splitEnclosure04.getSigningName());
        enclosure04.setSigningDate(splitEnclosure04.getSigningDate());
        return enclosure04;
    }
}