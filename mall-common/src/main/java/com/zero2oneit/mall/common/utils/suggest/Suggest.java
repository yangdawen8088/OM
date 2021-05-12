package com.zero2oneit.mall.common.utils.suggest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Description: Suggest封装工具类
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suggest implements Serializable {

  private List<?> value;//数据格式

}
