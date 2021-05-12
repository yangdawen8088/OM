package com.zero2oneit.mall.system.quartz.entity;

import com.zero2oneit.mall.system.quartz.constant.ScheduleConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 定时任务调度表 sys_job
 */
@Data
public class SysJob implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    private Long jobId;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    private String jobName;

    /**
     * 任务组名
     */
    @NotBlank(message = "任务组名不能为空")
    private String jobGroup;

    /**
     * 调用目标字符串
     */
    @NotBlank(message = "调用目标字符串不能为空")
    private String invokeTarget;

    /**
     * cron执行表达式
     */
    @NotBlank(message = "cron执行表达式")
    private String cronExpression;

    /**
     * cron计划策略
     */
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /**
     * 是否并发执行（0允许 1禁止）
     */
    private String concurrent;

    /**
     * 任务状态（0正常 1暂停）
     */
    private String status;

    private String remark;

}