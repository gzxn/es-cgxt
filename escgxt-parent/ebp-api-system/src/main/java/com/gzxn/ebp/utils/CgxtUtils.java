package com.gzxn.ebp.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.gzxn.config.utils
 * @ClassName: CgxtUtils
 * @Author: CodeBird
 * @Date: 2022-05-05 16:49
 * @Description: 采购系统工具类
 */
public class CgxtUtils extends ServiceImpl {


    /**
     * 获取项目编号
     *
     * @param code 业务表code，如ebp_cgsq表，则code为ebp_cgsq
     * @return String
     */
    public String getLsh(String code) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        // 年份
        String year = sdf.format(date);
        // 头部编号
        String[] str = code.split("_");
        String headCode = str[1].toUpperCase() + year;
        // 将首字母转换为大写
        String entityName = StringUtils.capitalize(str[0])+StringUtils.capitalize(str[1]);
        QueryWrapper queryWrapper;
        List list;
        int num = 1;

        if ("EbpCgsq".equals(entityName)) {
             queryWrapper = new QueryWrapper<EbpCgsq>();
            queryWrapper.orderByDesc("cgbh");
            list = baseMapper.selectList(queryWrapper);
            if (list.size() > 0) {
                String cgrwbh = list.get(0).toString().split("-")[1];
                Integer cgrwbhInt = Integer.parseInt(cgrwbh);
                num = cgrwbhInt + 1;
            }
        }

        if (num < 10) {
            headCode = headCode + "-00" + num;
        } else if (num >= 10 && num < 100) {
            headCode = headCode + "-0" + num;
        } else {
            headCode = headCode + "-" + num;
        }
        return headCode;
    }


}
