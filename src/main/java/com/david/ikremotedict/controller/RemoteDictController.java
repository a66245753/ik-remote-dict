package com.david.ikremotedict.controller;

import com.david.ikremotedict.domain.IkExtDict;
import com.david.ikremotedict.domain.IkStopDict;
import com.david.ikremotedict.service.IkExtDictService;
import com.david.ikremotedict.service.IkStopDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @authar David
 * @Date 2025/4/1
 * @description
 */
@Controller
@RequestMapping("/remoteDict")
public class RemoteDictController {

    //自定义分词词库
    @Autowired
    private IkExtDictService extDictService;
    @Autowired
    private IkStopDictService stopDictService;
    private final String DEFAULT_LAST_MODIFIED = "1743492903";

    /**
     * 扩展分词
     *
     * @return *  @throws IOException
     */
    @GetMapping(value = "extDict")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> ikExtDict() throws IOException {
        System.out.println(LocalDateTime.now());
        // 获取词库列表
        List<IkExtDict> list = extDictService.list();

        // 提前判断 list 是否为空，避免不必要的流式处理
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent()
                    .header("ETag", "eb5b427b4d494525a6595a215df46dab")
                    .header("Last-Modified", DEFAULT_LAST_MODIFIED)
                    .build();
        }
        // 获取最新的创建时间戳
        IkExtDict latestDict = list.get(list.size() - 1);
        long timestamp = latestDict.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // 创建流式响应体
        StreamingResponseBody responseBody = outputStream -> {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
                for (IkExtDict dict : list) {
                    writer.write(dict.getDict());
                    writer.newLine(); // 写入换行符
                }
            } catch (IOException exception) {
                exception.printStackTrace(); // 抛出异常以便上层处理
            }
        };

        // 返回响应实体
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("text/plain;charset=UTF-8"))
                .header("ETag", "eb5b427b4d494525a6595a215df46dab")
                // 这里应该返回操作dict的最后时间，先临时返回最后一个数据的创建时间
                .header("Last-Modified", String.valueOf(timestamp))
                .body(responseBody);
    }


    /**
     * 扩展停止词
     *
     * @return *  @throws IOException
     */
    @GetMapping(value = "stopDict")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> ikStopDict() throws IOException {
        List<IkStopDict> list = stopDictService.list();

        // 提前判断 list 是否为空，避免不必要的流式处理
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent()
                    .header("ETag", "eb5b427b4d494525a6595a215df46dab")
                    .header("Last-Modified", DEFAULT_LAST_MODIFIED)
                    .build();
        }
        // 获取最新的创建时间戳
        IkStopDict latestDict = list.get(list.size() - 1);
        long timestamp = latestDict.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // 创建流式响应体
        StreamingResponseBody responseBody = outputStream -> {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
                for (IkStopDict dict : list) {
                    writer.write(dict.getDict());
                    writer.newLine(); // 写入换行符
                }
            } catch (IOException exception) {
                exception.printStackTrace(); // 抛出异常以便上层处理
            }
        };

        // 返回响应实体
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("text/plain;charset=UTF-8"))
                .header("ETag", "5a6595a2eb5b427b4d4945215df46dab")
                // 这里应该返回操作dict的最后时间，先临时返回最后一个数据的创建时间
                .header("Last-Modified", String.valueOf(timestamp))
                .body(responseBody);
    }
}
