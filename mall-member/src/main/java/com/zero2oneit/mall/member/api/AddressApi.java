package com.zero2oneit.mall.member.api;

import com.zero2oneit.mall.common.bean.member.MemberAddress;
import com.zero2oneit.mall.common.query.member.MemberAddressQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.member.service.MemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Description: 地址管理
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/4/18
 */
@RestController
@RequestMapping("/api/auth/member/address")
public class AddressApi {

    @Autowired
    private MemberAddressService addressService;

    /**
     * 编辑或添加地址信息
     * @param address 参数
     * @return
     */
    @PostMapping("/addOrEdit")
    public R addOrEdit(@RequestBody MemberAddress address){
        addressService.saveOrUpdate(address);
        return R.ok("操作成功");
    }

    /**
     * 加载地址信息
     * @param id 参数
     * @return
     */
    @PostMapping("/load")
    public R load(@RequestBody Long id){
        MemberAddress address = addressService.getById(id);
        return R.ok("加载成功", address);
    }

    /**
     * 删除地址信息
     * @param qo 参数
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody MemberAddressQueryObject qo){
        addressService.removeById(String.valueOf(qo.getId()));
        return R.ok("删除成功");
    }

    /**
     * 设置默认地址
     * @param qo 参数
     * @return
     */
    @PostMapping("/setDefault")
    public R setDefault(@RequestBody MemberAddressQueryObject qo){
        addressService.setDefault(qo.getId(), Long.parseLong(qo.getMemberId()));
        return R.ok("设置默认地址成功");
    }

    /**
     * 查询地址信息
     * @param qo 参数
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody MemberAddressQueryObject qo){
        BoostrapDataGrid page = addressService.pageList(qo);
        return R.ok("加载成功", page);
    }

}
