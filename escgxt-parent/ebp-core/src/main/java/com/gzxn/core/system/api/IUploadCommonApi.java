package com.gzxn.core.system.api;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Package: com.gzxn.core.system.api
 * @ClassName: IUploadCommonApi
 * @Author: CodeBird
 * @Date: 2022-03-06 12:16
 * @Description: 上传公共业务接口
 */
public interface IUploadCommonApi {

    String upload(InputStream inputStream, String fileName, String rootPath, String path, String uri)
            throws IOException;

}
