package me.cuiyijie.joyea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.pojo.request.TransBasePageResponse;
import me.cuiyijie.joyea.pojo.request.TransBaseResponse;
import me.cuiyijie.joyea.service.SyncFileInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/sync")
public class SyncController {

    private final SyncFileInfoService syncFileInfoService;

    @GetMapping("/attachment")
    public TransBaseResponse syncCheckResultItemAttachment() {
        syncFileInfoService.syncFileInfoFromLenovoToEdoc2();
        return TransBasePageResponse.success("");
    }

}
