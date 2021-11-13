package com.company.rs.configuration;


import com.company.rs.transformer.FileTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.LoggingHandler;

import java.io.File;
import java.util.function.Consumer;

@Slf4j
@Configuration
public class FileIntegrationConfiguration {

    @Value("${directory.path}")
    private String directoryPath;

    @Value("${output.filename}")
    private String outputFileName;

    @Bean
    IntegrationFlow fileIntegrationFlow(FileReadingMessageSource fileAdapter,
                                        FileTransformer transformer,
                                        FileWritingMessageHandler outputFileHandler) {
        return IntegrationFlows.from(fileAdapter, getPollingConfig())
                .transform(transformer, "transformFile")
                .log(LoggingHandler.Level.INFO)
                .handle(outputFileHandler)
                .get();
    }

    private Consumer<SourcePollingChannelAdapterSpec> getPollingConfig() {
        return config -> config
                .poller(Pollers.fixedDelay(1000));
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        log.info("Directory input path {}", directoryPath);
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(directoryPath));

        return fileReadingMessageSource;
    }

    @Bean
    FileTransformer transformer() {
        return new FileTransformer();
    }

    @Bean
    FileWritingMessageHandler outputFileAdapter() {
        File directory = new File("data/output");
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setFileNameGenerator(message -> outputFileName);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        handler.setExpectReply(false);

        return handler;
    }
}
