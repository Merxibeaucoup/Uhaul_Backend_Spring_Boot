package com.edgar.uhaul.models;

import java.time.LocalDateTime;

import com.edgar.uhaul.models.enums.EmailStatus;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailerModel extends BaseEntity {

	@NotNull
	private String emailFrom;
	
	@NotNull
	private String emailTo;
	
	@Nullable
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String message;
	
	private LocalDateTime SentDateTime;

	private EmailStatus status;
}
