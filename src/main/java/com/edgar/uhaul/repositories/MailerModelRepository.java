package com.edgar.uhaul.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.uhaul.models.MailerModel;

@Repository
public interface MailerModelRepository  extends JpaRepository<MailerModel, Long> {

}
