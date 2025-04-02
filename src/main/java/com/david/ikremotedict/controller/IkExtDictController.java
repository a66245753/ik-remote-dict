package com.david.ikremotedict.controller;

import com.david.ikremotedict.domain.IkExtDict;
import com.david.ikremotedict.service.IkExtDictService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @authar David
 * @Date 2025/4/1
 * @description
 */
@Controller
@RequestMapping("/extDict")
public class IkExtDictController {

    @Autowired
    private IkExtDictService service;

    /**
     * 新增扩展分词
     *
     * @return *  @throws IOException
     */
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<IkExtDict> add(@RequestBody IkExtDict dict) {
        service.save(dict);
        return ResponseEntity.ok().body(dict);
    }

    /**
     * 新增扩展分词
     *
     * @return *  @throws IOException
     */
    @Delete("delete")
    @ResponseBody
    public ResponseEntity<IkExtDict> delete(@RequestBody IkExtDict dict) {
        service.removeById(dict.getId());
        return ResponseEntity.ok().body(dict);
    }


    /**
     * 全量扩展分词
     *
     * @return *  @throws IOException
     */
    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<List<IkExtDict>> list() {
        return ResponseEntity.ok().body(service.list());
    }

}
