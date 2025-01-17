package com.aurionpro.batchprocessing.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportJob; // Job bean that exports data to CSV

    @GetMapping("/export")
    public String exportToCSV() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(exportJob, jobParameters);
            return "Export to CSV job has been triggered!";
        } catch (JobExecutionAlreadyRunningException e) {
            return "Job is already running!";
        } catch (JobRestartException e) {
            return "Job restart failed!";
        } catch (JobInstanceAlreadyCompleteException e) {
            return "Job instance already completed!";
        } catch (JobParametersInvalidException e) {
            return "Invalid job parameters!";
        }
    }
}

