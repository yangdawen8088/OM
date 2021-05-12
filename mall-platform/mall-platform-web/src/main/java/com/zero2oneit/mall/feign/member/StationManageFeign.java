package com.zero2oneit.mall.feign.member;

import com.zero2oneit.mall.common.bean.goods.GoodSaleArea;
import com.zero2oneit.mall.common.bean.goods.GoodSaleAreaTree;
import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.bean.member.StationManage;
import com.zero2oneit.mall.common.query.goods.GoodSaleAreaQueryObject;
import com.zero2oneit.mall.common.query.member.ChiefManageQueryObject;
import com.zero2oneit.mall.common.query.member.StationManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 16:43
 */
@FeignClient("member-service")
public interface StationManageFeign {

    @PostMapping("/admin/stationManage/addOrEdit")
    R addOrEdit(@RequestBody StationManage stationManage);

    @PostMapping("/admin/stationManage/list")
    BoostrapDataGrid list(@RequestBody StationManageQueryObject qo);

    @PostMapping("/admin/stationManage/stationStatusEdit")
    R stationStatusEdit(@RequestParam("value") String value,@RequestParam("id") String id);

    @PostMapping("/admin/stationManage/checkByIds")
    R checkByIds(@RequestParam("ids") String ids);

    @PostMapping("/admin/stationManage/delByIds")
    R delByIds(@RequestParam("ids") String ids);

    @PostMapping("/admin/stationManage/tree")
    List<BaseTreeNode> tree();

    @PostMapping("/admin/chiefApplyManage/chiefList")
    BoostrapDataGrid chiefList(@RequestBody ChiefManageQueryObject qo);

    @PostMapping("/admin/chiefApplyManage/chiefStatusEdit")
    R chiefStatusEdit(@RequestParam("status") String status,@RequestParam("id") String id);

    @PostMapping("/admin/chiefApplyManage/chiefByIds")
    R chiefByIds(@RequestBody Object chiefApplies);

    @PostMapping("/admin/chiefApplyManage/addOrEdit")
    R addOrEditChief(@RequestBody ChiefApply chiefApply);
}
