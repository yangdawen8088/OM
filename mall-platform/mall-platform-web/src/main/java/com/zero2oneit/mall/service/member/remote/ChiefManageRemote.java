package com.zero2oneit.mall.service.member.remote;
import com.zero2oneit.mall.common.bean.member.ChiefApply;
import com.zero2oneit.mall.common.query.member.ChiefManageQueryObject;
import com.zero2oneit.mall.common.utils.R;
import com.zero2oneit.mall.common.utils.bootstrap.BoostrapDataGrid;
import com.zero2oneit.mall.feign.member.StationManageFeign;
import com.zero2oneit.mall.feign.oss.OssFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 *
 * @author lf
 * @date 2021/4/27 18:31
 */
@RestController
@RequestMapping("/remote/chiefManage")
public class ChiefManageRemote {

    @Autowired
    private StationManageFeign stationManageFeign;

    @Autowired
    private OssFeign ossFeign;

    /**
     * 获取团长站点
     * @param qo
     * @return
     */
    @PostMapping("/list")
    public BoostrapDataGrid chiefList(@RequestBody ChiefManageQueryObject qo) {
        return stationManageFeign.chiefList(qo);
    }

    /**
     * 站点开关状态
     */
    @PostMapping("/chiefStatusEdit")
    public R chiefStatusEdit(String status, String id){
        return stationManageFeign.chiefStatusEdit(status,id);
    }

    /**
     * 驿站审核申请
     * @param
     * @return
     */
    @PostMapping("/chiefByIds")
    public R chiefByIds(@RequestBody Object chiefApplies){
        return stationManageFeign.chiefByIds(chiefApplies);
    }

    /**
     * 添加或编辑
     */
    @PostMapping("/addOrEdit")
    public R addOrEditChief(ChiefApply chiefApply, @RequestParam("file") MultipartFile[] file,
                            @RequestParam("filePlusOne") MultipartFile[] filePlusOne,
                            @RequestParam("filePlusTwo") MultipartFile[] filePlusTwo){
        String imgUrl = null;
        String imgUrlOne = null;
        String imgUrlTwo = null;
        if (file != null && file.length >= 1) {
            imgUrl = ossFeign.uploadImage(file, "stationManage");
            chiefApply.setDoorPhoto(imgUrl);
        }
        if (filePlusOne != null && filePlusOne.length >= 1) {
            imgUrlOne = ossFeign.uploadImage(filePlusOne, "stationManage");
            chiefApply.setCardFront(imgUrlOne);
        }
        if (filePlusTwo != null && filePlusTwo.length >= 1) {
            imgUrlTwo = ossFeign.uploadImage(filePlusTwo, "stationManage");
            chiefApply.setCardBack(imgUrlTwo);
        }
        return stationManageFeign.addOrEditChief(chiefApply);
    }



}
