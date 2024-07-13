package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Springbatch5courseConfig {

	@Bean
	public Job imprimeOlaMundoJob(JobRepository jobRepository, Step step) {
		return new JobBuilder("imprimeOlaMundoJob", jobRepository)
				.start(step)
				.build();
	}

	@Bean
	public Step imprimeOlaMundoStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("imprimeOlaMundoStep", jobRepository)
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Olá Mundo!");
					return RepeatStatus.FINISHED;
				}, transactionManager)
				.build();
	}
}
