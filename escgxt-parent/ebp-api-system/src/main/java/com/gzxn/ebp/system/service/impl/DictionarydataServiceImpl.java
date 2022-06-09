package com.gzxn.ebp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.core.constant.CacheConstant;
import com.gzxn.ebp.system.dto.OptionDictionaryDto;
import com.gzxn.ebp.system.entity.Dictionarydata;
import com.gzxn.ebp.system.mapper.DictionarydataMapper;
import com.gzxn.ebp.system.service.IDictionarydataService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: DictionarydataServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:19
 * @Description: 数据字典数据-服务实现类
 */
@Service
public class DictionarydataServiceImpl extends ServiceImpl<DictionarydataMapper, Dictionarydata>
        implements IDictionarydataService {

    @CacheEvict(value = { CacheConstant.CACHE_DICTIONARY_OPTIONDICTIONARY_PREFIX }, allEntries = true)
    @Override
    public boolean updateOptionDictionaryDto(OptionDictionaryDto optionDictionaryDto) {
        // 把已有的字典选项删除
        this.remove(new QueryWrapper<Dictionarydata>().eq("did", optionDictionaryDto.getDid()));
        this.saveBatch(optionDictionaryDto.getDictionarydatas());
        return true;
    }

}