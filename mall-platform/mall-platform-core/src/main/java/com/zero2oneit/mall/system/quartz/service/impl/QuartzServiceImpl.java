package com.zero2oneit.mall.system.quartz.service.impl;

import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.system.quartz.constant.ScheduleConstants;
import com.zero2oneit.mall.system.quartz.entity.SysJob;
import com.zero2oneit.mall.system.quartz.mapper.QuartzMapper;
import com.zero2oneit.mall.system.quartz.query.QuartzQueryObject;
import com.zero2oneit.mall.system.quartz.service.QuartzService;
import com.zero2oneit.mall.system.quartz.util.ScheduleUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzMapper quartzMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() {
        List<SysJob> jobList = quartzMapper.selectJobAll();
        for (SysJob job : jobList){
            updateSchedulerJob(job, job.getJobGroup());
        }
    }


    @Override
    public BoostrapDataGrid queryList(QuartzQueryObject qo) {
        //查询总记录数
        int total = quartzMapper.selectTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : quartzMapper.selectRows(qo));
    }

    @Override
    public BoostrapDataGrid quartzLogPage(QuartzQueryObject qo) {
        //查询总记录数
        int total = quartzMapper.selectLogTotal(qo);
        return new BoostrapDataGrid(total, total == 0 ? Collections.EMPTY_LIST : quartzMapper.selectLogRows(qo));
    }

    @Transactional
    public void addOrEditQuartz(SysJob job) {
        if (job.getJobId() == null){//添加
            int rows = quartzMapper.add(job);
            if (rows > 0){
                try {
                    ScheduleUtils.createScheduleJob(scheduler, job);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("添加定时任务异常");
                }
            }
        }else{//编辑

            SysJob properties = quartzMapper.selectJobById(job.getJobId());
            int rows = quartzMapper.edit(job);
            if (rows > 0){//不做任何处理
                updateSchedulerJob(job, properties.getJobGroup());
            }

        }
    }

    /**
     * 更新任务
     *
     * @param job 任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(SysJob job, String jobGroup) {
        Long jobId = job.getJobId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        try {
            if (scheduler.checkExists(jobKey)){
                // 防止创建时存在数据问题 先移除，然后在执行创建操作
                scheduler.deleteJob(jobKey);
            }
            ScheduleUtils.createScheduleJob(scheduler, job);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("编辑定时任务异常");
        }
    }

    @Transactional
    public void changeQuartzStatus(SysJob job){
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)){
            resumeJob(job);
        }else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)){
            pauseJob(job);
        }
    }

    @Override
    public void delQuartz(SysJob job) {
        deleteJob(job);
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    @Transactional
    public int deleteJob(SysJob job){
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        int rows = quartzMapper.deleteJobById(jobId);
        if (rows > 0){
            try {
                scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("删除任务异常");
            }
        }
        return rows;
    }

    /**
     * 恢复任务
     * @param job 调度信息
     */
    @Transactional
    public int resumeJob(SysJob job){
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = quartzMapper.edit(job);
        if (rows > 0){
            try {
                scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("恢复任务异常");
            }
        }
        return rows;
    }

    /**
     * 暂停任务
     * @param job 调度信息
     */
    @Transactional
    public int pauseJob(SysJob job){
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = quartzMapper.edit(job);
        if (rows > 0){
            try {
                 scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("暂停任务异常");
            }
        }
        return rows;
    }


}
