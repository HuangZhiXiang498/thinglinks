package com.mqttsnet.thinglinks.boot.link.controller.protocol;

import com.mqttsnet.thinglinks.common.core.dynamicCompilation.ClassInjector;
import com.mqttsnet.thinglinks.common.core.dynamicCompilation.DynamicClassLoader;
import com.mqttsnet.thinglinks.common.core.dynamicCompilation.DynamicLoaderEngine;
import com.mqttsnet.thinglinks.common.core.dynamicCompilation.bytecode.InjectionSystem;
import com.mqttsnet.thinglinks.common.core.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @program: thinglinks
 * @description: 动态编译代码Controller
 * @packagename: com.mqttsnet.thinglinks.link.controller.protocol
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2022-07-01 18:31
 **/
@RestController
@RequestMapping("/protocolCompileXcode")
@Slf4j
public class CompileXcodeController {

    /**
     * 动态编译代码
     * @param code
     * @param inparam
     * @return
     */
    @PostMapping("/dynamicallyXcode")
    public AjaxResult importProductJson(@NotBlank(message = "脚本方法块不能为空") String code,@NotBlank(message = "脚本方法块入参不能为空") String inparam) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PrintWriter out = new PrintWriter(buffer, true);
            byte[] classBytes = DynamicLoaderEngine.compile(code, out, null);
            byte[] injectedClass = ClassInjector.injectSystem(classBytes);
            InjectionSystem.inject(null, new PrintStream(buffer, true), null);
            DynamicClassLoader classLoader = new DynamicClassLoader(this.getClass().getClassLoader());
            DynamicLoaderEngine.executeMain(classLoader, injectedClass, out,inparam);
            return AjaxResult.success(buffer.toString().trim());
        } catch (Throwable e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
