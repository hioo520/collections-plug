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

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCityName() {

        return cityName;
    }

    /**
     * Sets city name.
     *
     * @param cityName the city name
     */
    public void setCityName(String cityName) {

        this.cityName = cityName;
    }

    /**
     * Gets department.
     *
     * @return the department
     */
    public String getDepartment() {

        return department;
    }

    /**
     * Sets department.
     *
     * @param department the department
     */
    public void setDepartment(String department) {

        this.department = department;
    }

    /**
     * Gets city ascription.
     *
     * @return the city ascription
     */
    public String getCityAscription() {

        return cityAscription;
    }

    /**
     * Sets city ascription.
     *
     * @param cityAscription the city ascription
     */
    public void setCityAscription(String cityAscription) {

        this.cityAscription = cityAscription;
    }

    /**
     * Gets resource code.
     *
     * @return the resource code
     */
    public String getResourceCode() {

        return resourceCode;
    }

    /**
     * Sets resource code.
     *
     * @param resourceCode the resource code
     */
    public void setResourceCode(String resourceCode) {

        this.resourceCode = resourceCode;
    }

    /**
     * Gets store short name.
     *
     * @return the store short name
     */
    public String getStoreShortName() {

        return storeShortName;
    }

    /**
     * Sets store short name.
     *
     * @param storeShortName the store short name
     */
    public void setStoreShortName(String storeShortName) {

        this.storeShortName = storeShortName;
    }

    /**
     * Gets captain.
     *
     * @return the captain
     */
    public String getCaptain() {

        return captain;
    }

    /**
     * Sets captain.
     *
     * @param captain the captain
     */
    public void setCaptain(String captain) {

        this.captain = captain;
    }

    /**
     * Gets clerk.
     *
     * @return the clerk
     */
    public String getClerk() {

        return clerk;
    }

    /**
     * Sets clerk.
     *
     * @param clerk the clerk
     */
    public void setClerk(String clerk) {

        this.clerk = clerk;
    }

    /**
     * Gets all sale.
     *
     * @return the all sale
     */
    public BigDecimal getAllSale() {

        return allSale;
    }

    /**
     * Sets all sale.
     *
     * @param allSale the all sale
     */
    public void setAllSale(BigDecimal allSale) {

        this.allSale = allSale;
    }

    /**
     * Gets display sale.
     *
     * @return the display sale
     */
    public BigDecimal getDisplaySale() {

        return displaySale;
    }

    /**
     * Sets display sale.
     *
     * @param displaySale the display sale
     */
    public void setDisplaySale(BigDecimal displaySale) {

        this.displaySale = displaySale;
    }

    /**
     * Gets two net sale.
     *
     * @return the two net sale
     */
    public BigDecimal getTwoNetSale() {

        return twoNetSale;
    }

    /**
     * Sets two net sale.
     *
     * @param twoNetSale the two net sale
     */
    public void setTwoNetSale(BigDecimal twoNetSale) {

        this.twoNetSale = twoNetSale;
    }

    /**
     * Gets display rate.
     *
     * @return the display rate
     */
    public BigDecimal getDisplayRate() {

        return displayRate;
    }

    /**
     * Sets display rate.
     *
     * @param displayRate the display rate
     */
    public void setDisplayRate(BigDecimal displayRate) {

        this.displayRate = displayRate;
    }

    /**
     * Gets two net rate.
     *
     * @return the two net rate
     */
    public BigDecimal getTwoNetRate() {

        return twoNetRate;
    }

    /**
     * Sets two net rate.
     *
     * @param twoNetRate the two net rate
     */
    public void setTwoNetRate(BigDecimal twoNetRate) {

        this.twoNetRate = twoNetRate;
    }

    /**
     * Gets single average premium.
     *
     * @return the single average premium
     */
    public BigDecimal getSingleAveragePremium() {

        return singleAveragePremium;
    }

    /**
     * Sets single average premium.
     *
     * @param singleAveragePremium the single average premium
     */
    public void setSingleAveragePremium(BigDecimal singleAveragePremium) {

        this.singleAveragePremium = singleAveragePremium;
    }

    /**
     * Gets display premium.
     *
     * @return the display premium
     */
    public BigDecimal getDisplayPremium() {

        return displayPremium;
    }

    /**
     * Sets display premium.
     *
     * @param displayPremium the display premium
     */
    public void setDisplayPremium(BigDecimal displayPremium) {

        this.displayPremium = displayPremium;
    }

    /**
     * Gets two net premium.
     *
     * @return the two net premium
     */
    public BigDecimal getTwoNetPremium() {

        return twoNetPremium;
    }

    /**
     * Sets two net premium.
     *
     * @param twoNetPremium the two net premium
     */
    public void setTwoNetPremium(BigDecimal twoNetPremium) {

        this.twoNetPremium = twoNetPremium;
    }

    /**
     * Gets plans number.
     *
     * @return the plans number
     */
    public BigDecimal getPlansNumber() {

        return plansNumber;
    }

    /**
     * Sets plans number.
     *
     * @param plansNumber the plans number
     */
    public void setPlansNumber(BigDecimal plansNumber) {

        this.plansNumber = plansNumber;
    }

    /**
     * Gets display share.
     *
     * @return the display share
     */
    public BigDecimal getDisplayShare() {

        return displayShare;
    }

    /**
     * Sets display share.
     *
     * @param displayShare the display share
     */
    public void setDisplayShare(BigDecimal displayShare) {

        this.displayShare = displayShare;
    }

    /**
     * Gets two net share.
     *
     * @return the two net share
     */
    public BigDecimal getTwoNetShare() {

        return twoNetShare;
    }

    /**
     * Sets two net share.
     *
     * @param twoNetShare the two net share
     */
    public void setTwoNetShare(BigDecimal twoNetShare) {

        this.twoNetShare = twoNetShare;
    }

    /**
     * Gets new car month platform.
     *
     * @return the new car month platform
     */
    public BigDecimal getNewCarMonthPlatform() {

        return newCarMonthPlatform;
    }

    /**
     * Sets new car month platform.
     *
     * @param newCarMonthPlatform the new car month platform
     */
    public void setNewCarMonthPlatform(BigDecimal newCarMonthPlatform) {

        this.newCarMonthPlatform = newCarMonthPlatform;
    }

    /**
     * Gets new car day platform.
     *
     * @return the new car day platform
     */
    public BigDecimal getNewCarDayPlatform() {

        return newCarDayPlatform;
    }

    /**
     * Sets new car day platform.
     *
     * @param newCarDayPlatform the new car day platform
     */
    public void setNewCarDayPlatform(BigDecimal newCarDayPlatform) {

        this.newCarDayPlatform = newCarDayPlatform;
    }

    /**
     * Gets last month newcar platform.
     *
     * @return the last month newcar platform
     */
    public BigDecimal getLastMonthNewcarPlatform() {

        return lastMonthNewcarPlatform;
    }

    /**
     * Sets last month newcar platform.
     *
     * @param lastMonthNewcarPlatform the last month newcar platform
     */
    public void setLastMonthNewcarPlatform(BigDecimal lastMonthNewcarPlatform) {

        this.lastMonthNewcarPlatform = lastMonthNewcarPlatform;
    }

    /**
     * Gets last months newcar platform.
     *
     * @return the last months newcar platform
     */
    public BigDecimal getLastMonthsNewcarPlatform() {

        return lastMonthsNewcarPlatform;
    }

    /**
     * Sets last months newcar platform.
     *
     * @param lastMonthsNewcarPlatform the last months newcar platform
     */
    public void setLastMonthsNewcarPlatform(BigDecimal lastMonthsNewcarPlatform) {

        this.lastMonthsNewcarPlatform = lastMonthsNewcarPlatform;
    }

    /**
     * Gets last month realsales.
     *
     * @return the last month realsales
     */
    public BigDecimal getLastMonthRealsales() {

        return lastMonthRealsales;
    }

    /**
     * Sets last month realsales.
     *
     * @param lastMonthRealsales the last month realsales
     */
    public void setLastMonthRealsales(BigDecimal lastMonthRealsales) {

        this.lastMonthRealsales = lastMonthRealsales;
    }

    /**
     * Gets expect newcar growth speed.
     *
     * @return the expect newcar growth speed
     */
    public BigDecimal getExpectNewcarGrowthSpeed() {

        return expectNewcarGrowthSpeed;
    }

    /**
     * Sets expect newcar growth speed.
     *
     * @param expectNewcarGrowthSpeed the expect newcar growth speed
     */
    public void setExpectNewcarGrowthSpeed(BigDecimal expectNewcarGrowthSpeed) {

        this.expectNewcarGrowthSpeed = expectNewcarGrowthSpeed;
    }

    /**
     * Gets expect newcar ring speed.
     *
     * @return the expect newcar ring speed
     */
    public BigDecimal getExpectNewcarRingSpeed() {

        return expectNewcarRingSpeed;
    }

    /**
     * Sets expect newcar ring speed.
     *
     * @param expectNewcarRingSpeed the expect newcar ring speed
     */
    public void setExpectNewcarRingSpeed(BigDecimal expectNewcarRingSpeed) {

        this.expectNewcarRingSpeed = expectNewcarRingSpeed;
    }

    /**
     * Gets plan continue save month.
     *
     * @return the plan continue save month
     */
    public BigDecimal getPlanContinueSaveMonth() {

        return planContinueSaveMonth;
    }

    /**
     * Sets plan continue save month.
     *
     * @param planContinueSaveMonth the plan continue save month
     */
    public void setPlanContinueSaveMonth(BigDecimal planContinueSaveMonth) {

        this.planContinueSaveMonth = planContinueSaveMonth;
    }

    /**
     * Gets plan all month.
     *
     * @return the plan all month
     */
    public BigDecimal getPlanAllMonth() {

        return planAllMonth;
    }

    /**
     * Sets plan all month.
     *
     * @param planAllMonth the plan all month
     */
    public void setPlanAllMonth(BigDecimal planAllMonth) {

        this.planAllMonth = planAllMonth;
    }

    /**
     * Gets plan all grow speed.
     *
     * @return the plan all grow speed
     */
    public BigDecimal getPlanAllGrowSpeed() {

        return planAllGrowSpeed;
    }

    /**
     * Sets plan all grow speed.
     *
     * @param planAllGrowSpeed the plan all grow speed
     */
    public void setPlanAllGrowSpeed(BigDecimal planAllGrowSpeed) {

        this.planAllGrowSpeed = planAllGrowSpeed;
    }

    /**
     * Gets plan all ring speed.
     *
     * @return the plan all ring speed
     */
    public BigDecimal getPlanAllRingSpeed() {

        return planAllRingSpeed;
    }

    /**
     * Sets plan all ring speed.
     *
     * @param planAllRingSpeed the plan all ring speed
     */
    public void setPlanAllRingSpeed(BigDecimal planAllRingSpeed) {

        this.planAllRingSpeed = planAllRingSpeed;
    }

    /**
     * Gets this year month.
     *
     * @return the this year month
     */
    public String getThisYearMonth() {

        return thisYearMonth;
    }

    /**
     * Sets this year month.
     *
     * @param thisYearMonth the this year month
     */
    public void setThisYearMonth(String thisYearMonth) {

        this.thisYearMonth = thisYearMonth;
    }

    /**
     * Gets is import.
     *
     * @return the is import
     */
    public Integer getIsImport() {

        return isImport;
    }

    /**
     * Sets is import.
     *
     * @param isImport the is import
     */
    public void setIsImport(Integer isImport) {

        this.isImport = isImport;
    }

    /**
     * Gets last year synchro new.
     *
     * @return the last year synchro new
     */
    public BigDecimal getLastYearSynchroNew() {

        return lastYearSynchroNew;
    }

    /**
     * Sets last year synchro new.
     *
     * @param lastYearSynchroNew the last year synchro new
     */
    public void setLastYearSynchroNew(BigDecimal lastYearSynchroNew) {

        this.lastYearSynchroNew = lastYearSynchroNew;
    }

    /**
     * Gets last month synchro new.
     *
     * @return the last month synchro new
     */
    public BigDecimal getLastMonthSynchroNew() {

        return lastMonthSynchroNew;
    }

    /**
     * Sets last month synchro new.
     *
     * @param lastMonthSynchroNew the last month synchro new
     */
    public void setLastMonthSynchroNew(BigDecimal lastMonthSynchroNew) {

        this.lastMonthSynchroNew = lastMonthSynchroNew;
    }

    /**
     * Gets last year synchro all.
     *
     * @return the last year synchro all
     */
    public BigDecimal getLastYearSynchroAll() {

        return lastYearSynchroAll;
    }

    /**
     * Sets last year synchro all.
     *
     * @param lastYearSynchroAll the last year synchro all
     */
    public void setLastYearSynchroAll(BigDecimal lastYearSynchroAll) {

        this.lastYearSynchroAll = lastYearSynchroAll;
    }

    /**
     * Gets last month synchro all.
     *
     * @return the last month synchro all
     */
    public BigDecimal getLastMonthSynchroAll() {

        return lastMonthSynchroAll;
    }

    /**
     * Sets last month synchro all.
     *
     * @param lastMonthSynchroAll the last month synchro all
     */
    public void setLastMonthSynchroAll(BigDecimal lastMonthSynchroAll) {

        this.lastMonthSynchroAll = lastMonthSynchroAll;
    }

    /**
     * Gets distribution plan.
     *
     * @return the distribution plan
     */
    public Integer getDistributionPlan() {

        return distributionPlan;
    }

    /**
     * Sets distribution plan.
     *
     * @param distributionPlan the distribution plan
     */
    public void setDistributionPlan(Integer distributionPlan) {

        this.distributionPlan = distributionPlan;
    }

    /**
     * Gets contractor num.
     *
     * @return the contractor num
     */
    public BigDecimal getContractorNum() {

        return contractorNum;
    }

    /**
     * Sets contractor num.
     *
     * @param contractorNum the contractor num
     */
    public void setContractorNum(BigDecimal contractorNum) {

        this.contractorNum = contractorNum;
    }

    /**
     * Gets contractor percent.
     *
     * @return the contractor percent
     */
    public BigDecimal getContractorPercent() {

        return contractorPercent;
    }

    /**
     * Sets contractor percent.
     *
     * @param contractorPercent the contractor percent
     */
    public void setContractorPercent(BigDecimal contractorPercent) {

        this.contractorPercent = contractorPercent;
    }

    /**
     * Gets sell premium.
     *
     * @return the sell premium
     */
    public BigDecimal getSellPremium() {

        return sellPremium;
    }

    /**
     * Sets sell premium.
     *
     * @param sellPremium the sell premium
     */
    public void setSellPremium(BigDecimal sellPremium) {

        this.sellPremium = sellPremium;
    }

    /**
     * Gets contractor premium.
     *
     * @return the contractor premium
     */
    public BigDecimal getContractorPremium() {

        return contractorPremium;
    }

    /**
     * Sets contractor premium.
     *
     * @param contractorPremium the contractor premium
     */
    public void setContractorPremium(BigDecimal contractorPremium) {

        this.contractorPremium = contractorPremium;
    }

    /**
     * Gets contractor premium percent.
     *
     * @return the contractor premium percent
     */
    public BigDecimal getContractorPremiumPercent() {

        return contractorPremiumPercent;
    }

    /**
     * Sets contractor premium percent.
     *
     * @param contractorPremiumPercent the contractor premium percent
     */
    public void setContractorPremiumPercent(BigDecimal contractorPremiumPercent) {

        this.contractorPremiumPercent = contractorPremiumPercent;
    }

    /**
     * Gets day cun aim.
     *
     * @return the day cun aim
     */
    public BigDecimal getDayCunAim() {

        return dayCunAim;
    }

    /**
     * Sets day cun aim.
     *
     * @param dayCunAim the day cun aim
     */
    public void setDayCunAim(BigDecimal dayCunAim) {

        this.dayCunAim = dayCunAim;
    }

    /**
     * Gets day whole aim.
     *
     * @return the day whole aim
     */
    public BigDecimal getDayWholeAim() {

        return dayWholeAim;
    }

    /**
     * Sets day whole aim.
     *
     * @param dayWholeAim the day whole aim
     */
    public void setDayWholeAim(BigDecimal dayWholeAim) {

        this.dayWholeAim = dayWholeAim;
    }

}
