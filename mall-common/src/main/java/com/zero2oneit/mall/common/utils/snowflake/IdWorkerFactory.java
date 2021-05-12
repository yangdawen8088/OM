package com.zero2oneit.mall.common.utils.snowflake;

/**
 * Description: Twitter的分布式自增ID算法snowflake
 *
 * @author Sinper
 * @date 2021/2/23
 */
public class IdWorkerFactory {

    public static IdWorker create(int ... indexes) {
        return new StardardIdWorker(indexes);
    }

}
