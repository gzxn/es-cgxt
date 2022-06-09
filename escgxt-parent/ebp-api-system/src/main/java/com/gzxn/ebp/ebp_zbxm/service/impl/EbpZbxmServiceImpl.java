package com.gzxn.ebp.ebp_zbxm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgb.service.IEbpCgbService;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrw;
import com.gzxn.ebp.ebp_cgrw.service.IEbpCgrwService;
import com.gzxn.ebp.ebp_zbxm.entity.EbpZbxm;
import com.gzxn.ebp.ebp_zbxm.mapper.EbpZbxmMapper;
import com.gzxn.ebp.ebp_zbxm.service.IEbpZbxmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_zbxm.service.impl
 * @ClassName: EbpZbxmServiceImpl
 * @Description: 招标项目表-服务实现类
 * @author: CodeBird
 * @date: 2022-03-26 14:01:37
 */
@Service
public class EbpZbxmServiceImpl extends ServiceImpl<EbpZbxmMapper, EbpZbxm> implements IEbpZbxmService {

    @Autowired
    private IEbpCgrwService iEbpCgrwService;
    @Autowired
    private IEbpCgbService iEbpCgbService;

    public String getCgbLsh() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String str = sdf.format(date);
        String cgbCode = "CGB" + str;
        QueryWrapper<EbpCgb> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("cgbbh");
        List<EbpCgb> list = iEbpCgbService.list(queryWrapper);
        int num = 1;
        if (list.size() > 0) {
            String cgbbh = list.get(0).getCgbbh().split("-")[1];
            Integer cgbbhInt = Integer.parseInt(cgbbh);
            num = cgbbhInt + 1;
        }
        if (num < 10) {
            cgbCode = cgbCode + "-00" + num;
        } else if (num >= 10 && num < 100) {
            cgbCode = cgbCode + "-0" + num;
        } else {
            cgbCode = cgbCode + "-" + num;
        }
        return cgbCode;
    }

    public boolean cgrwPack(Map<String, Object> record) {
        boolean flag = false;
        String cgrwid = record.get("cgrwid").toString();
        // 获取采购任务数据
        EbpCgrw ebpCgrw = iEbpCgrwService.getById(cgrwid);
        // 创建采购包对象
        EbpCgb ebpCgb = new EbpCgb();
        // 封装采购包数据
        ebpCgb.setCgbbh(this.getCgbLsh());
        ebpCgb.setCgbmc(ebpCgrw.getCgrwmc());
        ebpCgb.setCgrwid(cgrwid);
        ebpCgb.setZzxs(ebpCgrw.getZzxs());
        ebpCgb.setCgfs(ebpCgrw.getCgfs());
        ebpCgb.setYsje(ebpCgrw.getYsje());
        ebpCgb.setCgzxryhm(ebpCgrw.getCgzxryhm());
        ebpCgb.setCgzxr(ebpCgrw.getCgzxr());
        // 保存采购包数据
        if (iEbpCgbService.save(ebpCgb)) {
            flag = true;
        }
        return flag;
    }

}
