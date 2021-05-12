package com.zero2oneit.mall.common.utils.snowflake;

/**
 * Description: Twitter的分布式自增ID算法snowflake
 *
 * @author Sinper
 * @date 2021/2/23
 */
public class StardardIdWorker implements IdWorker {

    private IdWorkerHandler handler;

    public StardardIdWorker(int ... indexes) {
        handler = new IdWorkerHandler(indexes);
    }

    public int getMaxIndex() {
        return IdWorkerHandler.MAX_WORKER_INDEX;
    }

    @Override
    public long nextId() {
        return this.handler.nextId();
    }

    public static void main(String[] args) {

        IdWorker idworker = IdWorkerFactory.create(1,1);
        for (int i = 0; i < 10; i++) {
            long id = idworker.nextId();
            System.out.println(id);
        }

    }
}
