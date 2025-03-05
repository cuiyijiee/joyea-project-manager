package me.cuiyijie.joyea.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.joyea.model.CheckItemResultAttachment;
import me.cuiyijie.joyea.model.v2.FileMetadataResponse;
import me.cuiyijie.joyea.pojo.Edoc2FileInfoResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncFileInfoService {

    private final CheckItemResultAttachmentService checkItemResultAttachmentService;
    private final OpenApiV2Service openApiV2Service;
    private final EDoc2Service edoc2Service;

    private final static int pageSize = 500;


    public void syncFileInfoFromLenovoToEdoc2() {
        Page<CheckItemResultAttachment> page = new Page<>(1, pageSize);
        Page<CheckItemResultAttachment> pageInfo = checkItemResultAttachmentService.findLenovoCheckItemResultAttachment(page);
        long total = pageInfo.getTotal();
        long pageNum = (total + pageSize - 1) / pageSize;
        log.info("total size: {}, pageSize: {}, pageNum: {}", total, pageSize, pageNum);
        for (int index = 1; index <= pageNum; index++) {
            log.info("current index: {}", index);
            page.setCurrent(index);
            Page<CheckItemResultAttachment> resultPage = checkItemResultAttachmentService.findLenovoCheckItemResultAttachment(page);
            for (CheckItemResultAttachment attachment : resultPage.getRecords()) {
                FileMetadataResponse fileMetadataResponse = openApiV2Service.getFileInfoByNeid(attachment.getLenovoId());
                log.info("fileMetadataResponse: {}", fileMetadataResponse);
            }
        }
    }

}
