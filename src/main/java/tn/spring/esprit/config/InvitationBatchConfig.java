package tn.spring.esprit.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;

import tn.spring.esprit.Service.Email_Sender_Service;
import tn.spring.esprit.batch.InvitationProcessor;
import tn.spring.esprit.batch.InvitationWriter;
import tn.spring.esprit.entities.Invitation;
import tn.spring.esprit.entities.Invitationstatus;
import tn.spring.esprit.entities.Invitationtype;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class InvitationBatchConfig {


	private static final String FILE_NAME = "invitation.csv";
	private static final String JOB_NAME = "listinvitationsJob";
	private static final String STEP_NAME = "invitationsprocessingStep";
	private static final String READER_NAME = "invitationItemReader";


	private String names = "InvitationStatus,InvitationType,SendingDate,invitationBody,invitationTitle,email";

	private String delimiter = ",";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private Email_Sender_Service emailSenderService;
//	@Autowired
//	private UserRepository userrep;

	
	@Bean
	public Job listInvitationsJob(Step step1) {
		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
	}

	
	@Bean
	public Step InvitationStep() {
		return stepBuilderFactory.get(STEP_NAME).<Invitation, Invitation>chunk(5).reader(InvitationItemReader())
				.processor(InvitationItemProcessor()).writer(InvitationItemWriter()).build();
	}
	
	
	@Bean
	public ItemReader<Invitation> InvitationItemReader() {
		FlatFileItemReader<Invitation> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource(FILE_NAME));
		reader.setName(READER_NAME);
		reader.setLinesToSkip(1);
	
		reader.setLineMapper(InvitationLineMapper());
		return reader;

	}


	
	

	@Bean
	public LineMapper<Invitation> InvitationLineMapper() {

		final DefaultLineMapper<Invitation> defaultLineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(delimiter);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(names.split(delimiter));
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSet -> {
			
			
	
			
			Invitation invitation = new Invitation();
			
			
		
			
			invitation.setInvitationStatus(Invitationstatus.valueOf(fieldSet.readString(0)));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime date = LocalDateTime.parse(fieldSet.readString(2), formatter);
			invitation.setSendingDate(date);
			
			invitation.setInvitationType(Invitationtype.valueOf(fieldSet.readString(1)));
	
			
			invitation.setInvitationTitle(fieldSet.readString(4));
			invitation.setInvitationBody(fieldSet.readString(3));
		
           

	
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(fieldSet.readString(5));
			mailMessage.setSubject(invitation.getInvitationTitle());
			mailMessage.setFrom("spring.boot.from1@gmail.com");
			mailMessage.setText(invitation.getInvitationBody());

			emailSenderService.sendEmail(mailMessage);
			return invitation;
			
			
			
			
		});
		return defaultLineMapper;
	}

	/* 11. étape 2 (ItemProcessor) fait appel à la classe Processor
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */
	@Bean
	public ItemProcessor<Invitation, Invitation> InvitationItemProcessor() {
		return new InvitationProcessor();
	}

	
	/* 12. étape 3 (ItemWriter) fait appel à la classe Writer
	 * qui se charge de lancer le service de sauvegarde des 
	 * données liées à la partie  dans la BD*/
	@Bean
	public ItemWriter<Invitation> InvitationItemWriter() {
		return new InvitationWriter();
	}
}
