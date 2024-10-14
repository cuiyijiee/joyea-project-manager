package me.cuiyijie.joyea.config;

import com.edoc2.sdkconfig.SdkBaseInfo;
import com.modules.Edoc2Version;
import com.modules.TransferConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EDoc2Config {

    @PostConstruct
    public void init() {
        SdkBaseInfo.baseUrl = Constants.EDOC2_HOST;
    }

    @Bean
    public TransferConfig getTransferConfig() {
        TransferConfig config = new TransferConfig(Constants.EDOC2_HOST, Edoc2Version.V6_60);
        config.setCacheHttpClient(false);
        config.setMaxErrorRetry(2);  // 重试次数
        config.setOpenSpeedLog(true);  // 是否开启上传速率日志,打印至控制台
        config.setTimeout(60 * 1000);  // 超时时间(毫秒ms)
        return config;
    }

}
