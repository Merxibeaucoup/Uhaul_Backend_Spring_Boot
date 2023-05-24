package com.edgar.uhaul.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.edgar.uhaul.models.MailerModel;
import com.edgar.uhaul.models.enums.EmailStatus;
import com.edgar.uhaul.repositories.MailerModelRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailerModelService {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MailerModelRepository modelRepository;
	
	
	public MailerModel sendMessage(MailerModel mailerModel) {
		
		mailerModel.setSentDateTime(LocalDateTime.now());
			
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setFrom(mailerModel.getEmailFrom());
			message.setTo(mailerModel.getEmailTo());
			message.setSubject(mailerModel.getSubject());
			message.setText(mailerModel.getMessage());
			this.mailSender.send(message);
			
			mailerModel.setStatus(EmailStatus.SENT);
			
			log.info("---Mesage Sent---");
			
		}
		catch(RuntimeException e) {
			
			mailerModel.setStatus(EmailStatus.FAILED); // else
			log.info("---Mesage unable to send---");
			
		}
		
		return  modelRepository.save(mailerModel);
		
		
	}

}
