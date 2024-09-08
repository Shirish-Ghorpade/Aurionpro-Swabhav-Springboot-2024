//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.aurionpro.model.service.PdfService;
//import com.cloudinary.provisioning.Account;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    @Autowired
//    private PdfService pdfService;
//
//    @Bean
//    public Job generatePdfJob(JobConfigurer jobConfigurer) {
//        return jobConfigurer.getJobBuilder("generatePdfJob")
//            .start(generatePdfStep(jobConfigurer))
//            .build();
//    }
//
//    @Bean
//    public Step generatePdfStep(JobConfigurer jobConfigurer) {
//        return jobConfigurer.getStepBuilder("generatePdfStep")
//            .tasklet(pdfTasklet())
//            .build();
//    }
//
//    @Bean
//    public Tasklet pdfTasklet() {
//        return (contribution, chunkContext) -> {
//            Long accountId = (Long) chunkContext.getStepContext().getJobParameters().get("accountId");
//            // Fetch account based on accountId
//            Account account = // fetch account by ID
//            byte[] pdfBytes = pdfService.generateTransactionPdf(account);
//            // Handle or save the PDF bytes as needed
//            return RepeatStatus.FINISHED;
//        };
//    }
//}
