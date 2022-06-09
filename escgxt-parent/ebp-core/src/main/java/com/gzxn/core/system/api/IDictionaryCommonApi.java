package com.gzxn.core.system.api;

import com.gzxn.core.system.dto.DictionarydataDto;

import java.util.List;

/**
 * @Package: com.gzxn.core.system.api
 * @ClassName: IDictionaryCommomApi
 * @Author: CodeBird
 * @Date: 2022-03-06 12:11
 * @Description: 字典公共业务接口
 */
public interface IDictionaryCommonApi {

    List<DictionarydataDto> listDictionaryOption(String key);

}

