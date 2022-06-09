package com.gzxn.ebp.system.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Package: com.gzxn.ebp.system.dto
 * @ClassName: UploadDto
 * @Author: CodeBird
 * @Date: 2022-03-06 17:58
 * @Description: 上传-实体类
 */
@ApiModel(value = "上传")
@Data
public class UploadDto implements Serializable {
    /**
     * 是否base64格式
     */
    private boolean isBase64;
    /**
     * 保存路径
     */
    private String statisPath;
    /**
     * base64文件
     */
    private String base64File;
}
