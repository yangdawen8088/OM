package com.zero2oneit.mall.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero2oneit.mall.common.query.member.MemberShareObject;
import com.zero2oneit.mall.common.query.member.MemberShareQueryObject;
import com.zero2oneit.mall.common.bean.member.MemberShareRecord;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;


/**
 * @Title: MemberShareRecordService
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2021/1/20 11:41
 */
public interface MemberShareRecordService extends IService<MemberShareRecord> {


     BoostrapDataGrid queryShareOrderList(MemberShareQueryObject memberShareQueryObject);

     BoostrapDataGrid lists(MemberShareObject qo);
}
