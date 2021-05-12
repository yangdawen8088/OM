package com.zero2oneit.mall.common.utils.snowflake;

/**
 * Description: Twitter的分布式自增ID算法snowflake
 * idworker是一个ID生成工具，可以生成一个全局唯一的长整形ID。也支持分布式环境下的使用。
 * idworker采用了Snowflake算法，并在此基础上增加了奇偶抖动功能，避免在低并发的环境下生成全是偶数的情况。
 *
 * ## 单机环境下的使用
 *
 * IdWorker idworker = IdWorkerFactory.create(0...15);
 * long id = idworker.nextId();
 *
 * ## 分布式环境下使用
 *
 * int index = getDistributiedIndex(); // 获取一个唯一ID
 * IdWorker idworker = IdWorkerFactory.create(index);
 * long id = idworker.nextId();
 *
 * @author Sinper
 * @date 2021/2/23
 */
public interface IdWorker {

    int MIN_HANDLER_ID = IdWorkerHandler.MIN_WORKER_INDEX;

    int MAX_HANDLER_ID = IdWorkerHandler.MAX_WORKER_INDEX;

    long nextId();
}