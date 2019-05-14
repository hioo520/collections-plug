package top.hihuzi.collection.fill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

//店面预算

/**
 * The type 店面预算
 */
//@Data
//@Accessors(chain = true)
//@EqualsAndHashCode(callSuper = false)
public class Store extends RootBean {

    private String cityName;//地市名称 对应crop 中的 comCode

    private String department;//部门?如何获取

    private String cityAscription;//归属机构

    private String resourceCode;//推荐送修码

    private String storeShortName;//店面简称

    /**
     * notice 团队长id
     **/

    private String captain;

    /**
     * notice 营业员
     **/
    private String clerk;

    private BigDecimal allSale = new BigDecimal(0);//总销售台次

    private BigDecimal displaySale = new BigDecimal(0);//展厅销售台次

    private BigDecimal twoNetSale = new BigDecimal(0);//二网销售台次

    private BigDecimal displayRate = new BigDecimal(0);//展厅承包率

    private BigDecimal twoNetRate = new BigDecimal(0);//二网承保率

    private BigDecimal singleAveragePremium = new BigDecimal(0);//单均保费(万元)  系统提取、剔除单交强（每个店过去三个月的平均）

    private BigDecimal displayPremium = new BigDecimal(0);//展厅保费预计（万元） 展厅销售台次*展厅承保率*单均保费+950*展厅销售台次

    private BigDecimal twoNetPremium = new BigDecimal(0);//二网保费预计（万元） 二网销售台次*二网承保率*单均保费+950*二网销售台次

    private BigDecimal plansNumber = new BigDecimal(0); //计划总数（万元）  展厅保费预计+二网保费预计

    private BigDecimal displayShare = new BigDecimal(0);//展厅份额预计

    private BigDecimal twoNetShare = new BigDecimal(0);//二网份额预计

    private BigDecimal newCarMonthPlatform = new BigDecimal(0);//当月新车月平台  展厅保费预计*展厅份额预计+二网保费预计*二网份额预计

    private BigDecimal newCarDayPlatform = new BigDecimal(0);//当月新车日平台  当月新车月平台/当月工作日

    private BigDecimal lastMonthNewcarPlatform = new BigDecimal(0);//上月新车平台  从“上月销售计划预算”表中导入

    private BigDecimal lastMonthsNewcarPlatform = new BigDecimal(0);//上上月新车平台  从“上上月销售计划预算”表中导入

    private BigDecimal lastMonthRealsales = new BigDecimal(0);//从APP驻店员/团队长录入数据中搜集

    private BigDecimal expectNewcarGrowthSpeed = new BigDecimal(0);//预计新车同比增速

    private BigDecimal expectNewcarRingSpeed = new BigDecimal(0);//预计新车环比增速

    private BigDecimal planContinueSaveMonth = new BigDecimal(0);//预计存量月平台

    private BigDecimal planAllMonth = new BigDecimal(0);//预计整体月平台

    private BigDecimal planAllGrowSpeed = new BigDecimal(0);//预计整体同比增速

    private BigDecimal planAllRingSpeed = new BigDecimal(0);//预计整体环比增速

    private String thisYearMonth;//格式 2018-06

    private Integer isImport;//是否导入


    private BigDecimal lastYearSynchroNew = new BigDecimal(0);//去年同期新车签单保费(不做展示,只做计算)

    private BigDecimal lastMonthSynchroNew = new BigDecimal(0);//上月新车签单保费(不做展示,只做计算)

    private BigDecimal lastYearSynchroAll = new BigDecimal(0);//去年同期整体签单保费(不做展示,只做计算)

    private BigDecimal lastMonthSynchroAll = new BigDecimal(0);//上月整体签单保费(不做展示,只做计算)

    private Integer distributionPlan;//导入数据是否符合省预算派发计划    新导入的默认为1    不符合    符合改为1


    private BigDecimal contractorNum = new BigDecimal(0);//预计承包台次

    private BigDecimal contractorPercent = new BigDecimal(0);//预计承包台次占比

    private BigDecimal sellPremium = new BigDecimal(0);//预计总销售保费

    private BigDecimal contractorPremium = new BigDecimal(0);//预计承包保费

    private BigDecimal contractorPremiumPercent = new BigDecimal(0);//预计保费占比

    private BigDecimal dayCunAim = new BigDecimal(0);//当日存量保费目标

    private BigDecimal dayWholeAim = new BigDecimal(0);//当日整体保费目标

    public String getCityName() {

        return cityName;
    }

    public void setCityName(String cityName) {

        this.cityName = cityName;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getCityAscription() {

        return cityAscription;
    }

    public void setCityAscription(String cityAscription) {

        this.cityAscription = cityAscription;
    }

    public String getResourceCode() {

        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {

        this.resourceCode = resourceCode;
    }

    public String getStoreShortName() {

        return storeShortName;
    }

    public void setStoreShortName(String storeShortName) {

        this.storeShortName = storeShortName;
    }

    public String getCaptain() {

        return captain;
    }

    public void setCaptain(String captain) {

        this.captain = captain;
    }

    public String getClerk() {

        return clerk;
    }

    public void setClerk(String clerk) {

        this.clerk = clerk;
    }

    public BigDecimal getAllSale() {

        return allSale;
    }

    public void setAllSale(BigDecimal allSale) {

        this.allSale = allSale;
    }

    public BigDecimal getDisplaySale() {

        return displaySale;
    }

    public void setDisplaySale(BigDecimal displaySale) {

        this.displaySale = displaySale;
    }

    public BigDecimal getTwoNetSale() {

        return twoNetSale;
    }

    public void setTwoNetSale(BigDecimal twoNetSale) {

        this.twoNetSale = twoNetSale;
    }

    public BigDecimal getDisplayRate() {

        return displayRate;
    }

    public void setDisplayRate(BigDecimal displayRate) {

        this.displayRate = displayRate;
    }

    public BigDecimal getTwoNetRate() {

        return twoNetRate;
    }

    public void setTwoNetRate(BigDecimal twoNetRate) {

        this.twoNetRate = twoNetRate;
    }

    public BigDecimal getSingleAveragePremium() {

        return singleAveragePremium;
    }

    public void setSingleAveragePremium(BigDecimal singleAveragePremium) {

        this.singleAveragePremium = singleAveragePremium;
    }

    public BigDecimal getDisplayPremium() {

        return displayPremium;
    }

    public void setDisplayPremium(BigDecimal displayPremium) {

        this.displayPremium = displayPremium;
    }

    public BigDecimal getTwoNetPremium() {

        return twoNetPremium;
    }

    public void setTwoNetPremium(BigDecimal twoNetPremium) {

        this.twoNetPremium = twoNetPremium;
    }

    public BigDecimal getPlansNumber() {

        return plansNumber;
    }

    public void setPlansNumber(BigDecimal plansNumber) {

        this.plansNumber = plansNumber;
    }

    public BigDecimal getDisplayShare() {

        return displayShare;
    }

    public void setDisplayShare(BigDecimal displayShare) {

        this.displayShare = displayShare;
    }

    public BigDecimal getTwoNetShare() {

        return twoNetShare;
    }

    public void setTwoNetShare(BigDecimal twoNetShare) {

        this.twoNetShare = twoNetShare;
    }

    public BigDecimal getNewCarMonthPlatform() {

        return newCarMonthPlatform;
    }

    public void setNewCarMonthPlatform(BigDecimal newCarMonthPlatform) {

        this.newCarMonthPlatform = newCarMonthPlatform;
    }

    public BigDecimal getNewCarDayPlatform() {

        return newCarDayPlatform;
    }

    public void setNewCarDayPlatform(BigDecimal newCarDayPlatform) {

        this.newCarDayPlatform = newCarDayPlatform;
    }

    public BigDecimal getLastMonthNewcarPlatform() {

        return lastMonthNewcarPlatform;
    }

    public void setLastMonthNewcarPlatform(BigDecimal lastMonthNewcarPlatform) {

        this.lastMonthNewcarPlatform = lastMonthNewcarPlatform;
    }

    public BigDecimal getLastMonthsNewcarPlatform() {

        return lastMonthsNewcarPlatform;
    }

    public void setLastMonthsNewcarPlatform(BigDecimal lastMonthsNewcarPlatform) {

        this.lastMonthsNewcarPlatform = lastMonthsNewcarPlatform;
    }

    public BigDecimal getLastMonthRealsales() {

        return lastMonthRealsales;
    }

    public void setLastMonthRealsales(BigDecimal lastMonthRealsales) {

        this.lastMonthRealsales = lastMonthRealsales;
    }

    public BigDecimal getExpectNewcarGrowthSpeed() {

        return expectNewcarGrowthSpeed;
    }

    public void setExpectNewcarGrowthSpeed(BigDecimal expectNewcarGrowthSpeed) {

        this.expectNewcarGrowthSpeed = expectNewcarGrowthSpeed;
    }

    public BigDecimal getExpectNewcarRingSpeed() {

        return expectNewcarRingSpeed;
    }

    public void setExpectNewcarRingSpeed(BigDecimal expectNewcarRingSpeed) {

        this.expectNewcarRingSpeed = expectNewcarRingSpeed;
    }

    public BigDecimal getPlanContinueSaveMonth() {

        return planContinueSaveMonth;
    }

    public void setPlanContinueSaveMonth(BigDecimal planContinueSaveMonth) {

        this.planContinueSaveMonth = planContinueSaveMonth;
    }

    public BigDecimal getPlanAllMonth() {

        return planAllMonth;
    }

    public void setPlanAllMonth(BigDecimal planAllMonth) {

        this.planAllMonth = planAllMonth;
    }

    public BigDecimal getPlanAllGrowSpeed() {

        return planAllGrowSpeed;
    }

    public void setPlanAllGrowSpeed(BigDecimal planAllGrowSpeed) {

        this.planAllGrowSpeed = planAllGrowSpeed;
    }

    public BigDecimal getPlanAllRingSpeed() {

        return planAllRingSpeed;
    }

    public void setPlanAllRingSpeed(BigDecimal planAllRingSpeed) {

        this.planAllRingSpeed = planAllRingSpeed;
    }

    public String getThisYearMonth() {

        return thisYearMonth;
    }

    public void setThisYearMonth(String thisYearMonth) {

        this.thisYearMonth = thisYearMonth;
    }

    public Integer getIsImport() {

        return isImport;
    }

    public void setIsImport(Integer isImport) {

        this.isImport = isImport;
    }

    public BigDecimal getLastYearSynchroNew() {

        return lastYearSynchroNew;
    }

    public void setLastYearSynchroNew(BigDecimal lastYearSynchroNew) {

        this.lastYearSynchroNew = lastYearSynchroNew;
    }

    public BigDecimal getLastMonthSynchroNew() {

        return lastMonthSynchroNew;
    }

    public void setLastMonthSynchroNew(BigDecimal lastMonthSynchroNew) {

        this.lastMonthSynchroNew = lastMonthSynchroNew;
    }

    public BigDecimal getLastYearSynchroAll() {

        return lastYearSynchroAll;
    }

    public void setLastYearSynchroAll(BigDecimal lastYearSynchroAll) {

        this.lastYearSynchroAll = lastYearSynchroAll;
    }

    public BigDecimal getLastMonthSynchroAll() {

        return lastMonthSynchroAll;
    }

    public void setLastMonthSynchroAll(BigDecimal lastMonthSynchroAll) {

        this.lastMonthSynchroAll = lastMonthSynchroAll;
    }

    public Integer getDistributionPlan() {

        return distributionPlan;
    }

    public void setDistributionPlan(Integer distributionPlan) {

        this.distributionPlan = distributionPlan;
    }

    public BigDecimal getContractorNum() {

        return contractorNum;
    }

    public void setContractorNum(BigDecimal contractorNum) {

        this.contractorNum = contractorNum;
    }

    public BigDecimal getContractorPercent() {

        return contractorPercent;
    }

    public void setContractorPercent(BigDecimal contractorPercent) {

        this.contractorPercent = contractorPercent;
    }

    public BigDecimal getSellPremium() {

        return sellPremium;
    }

    public void setSellPremium(BigDecimal sellPremium) {

        this.sellPremium = sellPremium;
    }

    public BigDecimal getContractorPremium() {

        return contractorPremium;
    }

    public void setContractorPremium(BigDecimal contractorPremium) {

        this.contractorPremium = contractorPremium;
    }

    public BigDecimal getContractorPremiumPercent() {

        return contractorPremiumPercent;
    }

    public void setContractorPremiumPercent(BigDecimal contractorPremiumPercent) {

        this.contractorPremiumPercent = contractorPremiumPercent;
    }

    public BigDecimal getDayCunAim() {

        return dayCunAim;
    }

    public void setDayCunAim(BigDecimal dayCunAim) {

        this.dayCunAim = dayCunAim;
    }

    public BigDecimal getDayWholeAim() {

        return dayWholeAim;
    }

    public void setDayWholeAim(BigDecimal dayWholeAim) {

        this.dayWholeAim = dayWholeAim;
    }

}
