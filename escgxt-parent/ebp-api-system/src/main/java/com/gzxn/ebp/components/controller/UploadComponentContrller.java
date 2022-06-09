package com.gzxn.ebp.components.controller;

import cn.hutool.core.util.StrUtil;
import com.gzxn.config.mvc.ResourceMappersProperties;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.exception.EbpRunTimeException;
import com.gzxn.core.system.api.IUploadCommonApi;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.system.dto.UploadDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Package: com.gzxn.ebp.components.controller
 * @ClassName: UploadComponentContrller
 * @Author: CodeBird
 * @Date: 2022-03-06 19:33
 * @Description: 上传组件-控制器
 */
@Api(tags = "上传组件-控制器")
@RestController
@RequestMapping("/components/uploadComponent")
public class UploadComponentContrller extends BaseController {
    @Autowired
    private ResourceMappersProperties mappersProperties;

    @Autowired
    private IUploadCommonApi uploadCommonApi;

    @ApiOperation(value = "上传图片")
    @PostMapping("/imageUpload")
    public ResponseBean<String> imageUpload(MultipartFile file, UploadDto uploadDto) throws Exception {
        if (file.isEmpty()) {
            throw new EbpRunTimeException("uploadComponent-imageUpload 文件为空。");
        }
        if (mappersProperties == null && mappersProperties.getMappers() == null
                && mappersProperties.getMappers().size() == 0) {
            throw new EbpRunTimeException("uploadComponent-imageUpload 文件保存路径配置为空。");
        }
        if (StrUtil.isEmpty(uploadDto.getStatisPath())) {
            throw new EbpRunTimeException("uploadComponent-imageUpload 组件statisPath配置为空。");
        }

        String url = uploadCommonApi.upload(file.getInputStream(), file.getOriginalFilename(),
                mappersProperties.getMappers().get(0).getFile(), uploadDto.getStatisPath(),
                mappersProperties.getMappers().get(0).getUri());
        return ResponseBean.Success("上传成功", url);
    }

}
