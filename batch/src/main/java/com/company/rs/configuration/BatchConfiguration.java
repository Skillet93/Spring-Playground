package com.company.rs.configuration;

import com.company.rs.converter.UserProcessor;
import com.company.rs.dto.UserConverted;
import com.company.rs.dto.UserRaw;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    FlatFileItemReader<UserRaw> reader() {
        FlatFileItemReader<UserRaw> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("input_set.csv"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("firstName", "lastName", "birthDate");

        BeanWrapperFieldSetMapper<UserRaw> fieldExtractor = new BeanWrapperFieldSetMapper<>();
        fieldExtractor.setTargetType(UserRaw.class);

        DefaultLineMapper<UserRaw> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldExtractor);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    UserProcessor processor(){
        return new UserProcessor();
    }


    @Bean
    FlatFileItemWriter<UserConverted> writer() {
        BeanWrapperFieldExtractor<UserConverted> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"firstName", "lastName", "age"});

        DelimitedLineAggregator<UserConverted> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        FlatFileItemWriter<UserConverted> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output.csv"));
        writer.setShouldDeleteIfExists(true);
        writer.setLineAggregator(aggregator);

        return writer;
    }

    @Bean
    Step userConvert(
            ItemReader<UserRaw> reader,
            ItemProcessor<UserRaw, UserConverted> processor,
            ItemWriter<UserConverted> writer) {

        return stepBuilderFactory.get("userConvert")
                .<UserRaw, UserConverted>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

    @Bean
    Job userConvertJob(Step priceChange) {
        return jobBuilderFactory.get("userConvertJob")
                .incrementer(new RunIdIncrementer())
                .flow(priceChange)
                .end()
                .build();
    }

}
