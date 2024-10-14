package me.cuiyijie.joyea.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.service.CheckItemAttachmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: yjcui3
 * @Date: 2022/10/10 10:11
 */
@Slf4j
@RestController
@RequestMapping("checkItemAttachment")
@Api(tags = "点检项附件模块")
@RequiredArgsConstructor
public class CheckItemAttachmentController {

    private final CheckItemAttachmentService checkItemAttachmentService;

    @RequestMapping("/download")
    public void download(@RequestParam("attachId") String attachId, HttpServletResponse httpServletResponse) {
        File file = checkItemAttachmentService.getPreviewFilePath(attachId);

        try {
            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            httpServletResponse.reset();
            // 设置response的Header
            httpServletResponse.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            httpServletResponse.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            // 告知浏览器文件的大小
            httpServletResponse.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
            httpServletResponse.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (Exception exception) {
            log.error("download file exist error: ", exception);
        }
    }

}
