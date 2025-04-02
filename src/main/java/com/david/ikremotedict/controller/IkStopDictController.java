package com.david.ikremotedict.controller;

import com.david.ikremotedict.domain.IkStopDict;
import com.david.ikremotedict.service.IkStopDictService;
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
@RequestMapping("/stopDict")
public class IkStopDictController {

    @Autowired
    private IkStopDictService service;

    /**
     * 新增停止分词
     *
     * @return *  @throws IOException
     */
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<IkStopDict> add(@RequestBody IkStopDict dict) {
        service.save(dict);
        return ResponseEntity.ok().body(dict);
    }

    /**
     * 新增停止分词
     *
     * @return *  @throws IOException
     */
    @Delete("delete")
    @ResponseBody
    public ResponseEntity<IkStopDict> delete(@RequestBody IkStopDict dict) {
        service.removeById(dict.getId());
        return ResponseEntity.ok().body(dict);
    }


    /**
     * 全量停止分词
     *
     * @return *  @throws IOException
     */
    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<List<IkStopDict>> list() {
        return ResponseEntity.ok().body(service.list());
    }
    
}
