package com.gzxn.ebp.system.service.impl;

import com.gzxn.core.system.api.IUploadCommonApi;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: UploadCommonApiImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:25
 * @Description: TODO
 */
@Service
public class UploadCommonApiImpl implements IUploadCommonApi {

    @Override
    public String upload(InputStream inputStream, String fileName, String rootPath, String path, String uri)
            throws IOException {
        String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
        String newFileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(rootPath + File.separator + path + File.separator + newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        Files.copy(inputStream, Paths.get(rootPath + File.separator + path + File.separator + newFileName));
        String url = File.separator + uri + File.separator + path + File.separator + newFileName;
        return url;
    }

}
