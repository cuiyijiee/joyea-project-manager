package me.cuiyijie.joyea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.service.EDoc2Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/external")
public class ExternalController {

    private final EDoc2Service eDoc2Service;

    @GetMapping("preview")
    public String preview(@RequestParam String fileId) {
        return "redirect:" + eDoc2Service.getFilePreviewInfo(fileId).getFileUrl();
    }

}
