package com.gzxn.core.system.api;

import com.gzxn.core.system.dto.DictionarydataDto;

import java.util.List;

/**
 * @Package: com.gzxn.core.system.api
 * @ClassName: IFieldColumnCommonApi
 * @Author: CodeBird
 * @Date: 2022-03-06 12:14
 * @Description: 字典公共业务接口
 */
public interface IFieldColumnCommonApi {

    List<DictionarydataDto> listDictionaryOption(String key);

}
