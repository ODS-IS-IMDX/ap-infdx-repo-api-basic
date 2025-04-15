// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.NoArgsConstructor;

/**
 * メッセージプロパティを読み込むクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/02
 */
@Configuration
@NoArgsConstructor
public class MessageConfig {
    
    /**
     * messages.propertiesをメッセージの取得元として設定する．
     * 
     * @return {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        
        messageSource.addBasenames("classpath:messages", "classpath:logging");
        
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        
        return messageSource;
        
    }
        
}
