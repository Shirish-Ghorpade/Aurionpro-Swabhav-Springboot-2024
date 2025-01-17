package com.aurionpro.batchprocessing.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.aurionpro.batchprocessing.entity.Employee;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class EmployeeBatchConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JpaPagingItemReader<Employee> databaseReader() {
        return new JpaPagingItemReaderBuilder<Employee>()
                .name("employeeReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT e FROM Employee e")
                .pageSize(10)
                .build();
    }

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        return new FlatFileItemWriterBuilder<Employee>()
                .name("employeeWriter")
                .resource(new FileSystemResource("output/employees.csv"))
                .lineAggregator(new DelimitedLineAggregator<Employee>() {{
                    setDelimiter(",");
                    setFieldExtractor(new PassThroughFieldExtractor<>());
                }})
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("exportDbToCsvStep", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(databaseReader())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("exportJob", jobRepository)
                .start(step1)
                .build();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
