package com.zero2oneit.mall.common.utils;

/**
 * @author Lee
 * @date 2020/7/31 -11:48
 */
public class EsIndexConstant {

    public static final String  COMMUNITY_GOOD_INDEX = "community_good";

    public static final String  COMMUNITY_SKU_INDEX = "community_sku";

    public static String GOOD_INDEX="good";

    public static String GOOD_SKU_INDEX="good_sku";

    public static final String COUPON_INDEX = "coupon";

    public static final String COUPON_HISTORY_INDEX = "coupon_history";

    public static String BUSINESS_INDEX="business_info";

    public static String[] DESIGNATED_FIELD={"productName","productPrice","productStock","businessName","giveJf","giveVoucher","soldStock","mainPicture","marketPrice","productId","businessId","needJq","ruleId","share","shareTwo","reward"};

    public static String[]  COMMUNITYPRO_FIELD={"productName","productPrice","productStock","subGraphList","businessName","giveJf","giveVoucher","soldStock","mainPicture","marketPrice","productId","businessId","bussTypeId","skuOne","skuOneName","skuTwo","skuTwoName","buyLimit","share","shareTwo","reward"};

    public static String[]  COMMUNITY_FIELD={"productName","productPrice","productStock","businessName","giveJf","giveVoucher","soldStock","mainPicture","marketPrice","productId","businessId","bussTypeId","skuOne","skuOneName","skuTwo","skuTwoName","buyLimit"};

    public static String[] PROSTOCK_FIELD={"productStock","soldStock"};

    public static String[] BUYLIMIT_FIELD={"buyLimit","favourCurrentcy","share","shareTwo","reward","produceTime","lifeTime"};
}
