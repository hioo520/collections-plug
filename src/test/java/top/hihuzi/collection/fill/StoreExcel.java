package top.hihuzi.collection.fill;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * The type Store excel.
 */
@Data
@Accessors(chain = true)
public class StoreExcel {

    private String id;

    private String cityName;//地市名称

    private String department;//部门?如何获取

    private String storeShortName;//店面简称

    private String resourceCode;//推荐送修码

    private String captain;//  团队长

    private String clerk;//驻店员

    private BigDecimal newCarMonthPlatform = new BigDecimal(0);

    private BigDecimal newCarDayPlatform = new BigDecimal(0);

    private BigDecimal planContinueSaveMonth = new BigDecimal(0);

    private BigDecimal dayCunAim = new BigDecimal(0);

    private BigDecimal planAllMonth = new BigDecimal(0);

    private BigDecimal dayWholeAim = new BigDecimal(0);

}
