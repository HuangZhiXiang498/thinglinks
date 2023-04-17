package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.domain.SysFile;
import com.mqttsnet.thinglinks.boot.file.controller.SysFileController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 * 
 * @author thinglinks
 */
@Component
public class RemoteFileService
{
    @Autowired
    private SysFileController fileController;

    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    public R<SysFile> upload(MultipartFile file){
      return   fileController.upload(file);
    };
}
