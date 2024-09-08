package com.aurionpro.batchprocessing.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.aurionpro.batchprocessing.entity.Employee;

@Configuration
public class EmployeeBatchConfig {

	@Bean
	public FlatFileItemReader<Employee> readEmployeeCsv() {
		FlatFileItemReader<Employee> employeeCsvReader = new FlatFileItemReader<>();
		employeeCsvReader.setResource(new ClassPathResource("data.csv"));
		employeeCsvReader.setName("employeeCsvReader");
		employeeCsvReader.setLinesToSkip(1);
		employeeCsvReader.setLineMapper(lineMapper());
		return employeeCsvReader;
	}

	public LineMapper<Employee> lineMapper() {
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);

		lineTokenizer.setNames("employeeId", "name", "salary");

		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Employee.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public JdbcBatchItemWriter<Employee> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Employee>()
				.sql("INSERT INTO employees (employee_id, name, salary) values (:employeeId, :name, :salary )")
				.dataSource(dataSource).beanMapped().build();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource data) {
		return new DataSourceTransactionManager(data);
	}

	@Bean
	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			FlatFileItemReader<Employee> reader, EmployeeProcessor processor, JdbcBatchItemWriter<Employee> writer) {
		return new StepBuilder("importcsvstep", jobRepository).<Employee, Employee>chunk(2, transactionManager)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
	}

}