package com.gzxn.ebp.components.controller;

import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.system.api.IDictionaryCommonApi;
import com.gzxn.core.system.dto.DictionarydataDto;
import com.gzxn.core.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.components.controller
 * @ClassName: OptionDictionaryComponentContrller
 * @Author: CodeBird
 * @Date: 2022-03-06 19:32
 * @Description: TODO
 */
@Api(tags = "OptionDictionaryComponent-控制器")
@RestController
@RequestMapping("/components/optionDictionaryComponentContrller")
public class OptionDictionaryComponentContrller extends BaseController {
    @Autowired
    private IDictionaryCommonApi iDictionaryCommonApi;

    @ApiOperation(value = "选项字典列表")
    @GetMapping("/listOptionDictionary")
    public ResponseBean<List<DictionarydataDto>> listOptionDictionary(@ApiParam(value = "选项字典编号") @RequestParam(name = "dkey", required = true) String dkey) {
        return ResponseBean.Success(iDictionaryCommonApi.listDictionaryOption(dkey));
    }

}
