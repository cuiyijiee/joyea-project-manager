package me.cuiyijie.joyea.controller;

import lombok.RequiredArgsConstructor;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.EDoc2Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("edoc2")
@RestController
@RequiredArgsConstructor
public class EDoc2Controller {

    private final EDoc2Service eDoc2Service;

    @GetMapping("token")
    public TransBaseResponse getAccessToken() {
        return TransBaseResponse.success(eDoc2Service.getAccessToken());
    }

    @PostMapping("upload")
    public TransBaseResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return TransBaseResponse.success(eDoc2Service.uploadFile(file));
    }
}
