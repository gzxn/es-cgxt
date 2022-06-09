package com.gzxn.ebp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.core.constant.CacheConstant;
import com.gzxn.ebp.system.entity.Dictionary;
import com.gzxn.ebp.system.mapper.DictionaryMapper;
import com.gzxn.ebp.system.service.IDictionaryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: DictionaryServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:20
 * @Description: 数据字典-服务实现类
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {
    @Cacheable(cacheNames = CacheConstant.CACHE_DICTIONARY_DICTIONARY_PREFIX, key = "#code", unless = "#result == null")
    @Override
    public Dictionary getOneByCode(String code) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", code);
        return super.getOne(queryWrapper);
    }

    @CacheEvict(value = { CacheConstant.CACHE_DICTIONARY_DICTIONARY_PREFIX }, allEntries = true)
    @Override
    public boolean save(Dictionary entity) {
        return super.save(entity);
    }

    @CacheEvict(value = { CacheConstant.CACHE_DICTIONARY_DICTIONARY_PREFIX }, allEntries = true)
    @Override
    public boolean updateById(Dictionary entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = { CacheConstant.CACHE_DICTIONARY_DICTIONARY_PREFIX }, allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}