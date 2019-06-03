package com.itliusir.test.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/5/15
 */

@RestController
public class TestCustomInfoController {

    @Autowired
    private CustomInfo customInfo;

    @GetMapping("/custom")
    public List<SpecialTaskInfo> get() {
        return customInfo.getSpecialTask();
    }
}
