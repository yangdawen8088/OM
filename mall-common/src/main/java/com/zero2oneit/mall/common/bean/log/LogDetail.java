package com.zero2oneit.mall.common.bean.log;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("f_log_detail")
public class LogDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID自增
     */
    private Long id;
    /**
     * 业务类型
     */
    private String logType;
    /**
     * 业务名称
     */
    private String logName;
    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 业务操作状态：成功 失败
     */
    private String logStaus;
    /**
     * 记录日志信息（错误或内容）
     */
    private String errorMsg;

}
