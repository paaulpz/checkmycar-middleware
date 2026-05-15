package com.paula.checkmc.service.impl;

import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.service.MailService;

/**
 * Implementacion de MailService sobre Apache Commons Email.
 */
public class MailServiceApacheImpl implements MailService {

	private static final Logger logger = LogManager.getLogger(MailServiceApacheImpl.class);

	public MailServiceApacheImpl() {

	}

	@Override
	public void sendEmail(String para, String asunto, String contenido, String firma) {

		try {

			logger.debug("Enviando email a {}", para);

			Email email = new SimpleEmail();

			email.setHostName("smtp.gmail.com");

			email.setSmtpPort(587);

			email.setStartTLSEnabled(true);

			email.setSSLOnConnect(false);

			email.setFrom("checkmycar6@gmail.com", "Checkmycar");

			email.setAuthentication("checkmycar6@gmail.com", "dvrc lifz uzjr qccj");

			email.setSubject(asunto);

			email.setMsg(contenido + "\n\n" + firma);

			email.addTo(para);

			email.send();

			logger.info("Email enviado correctamente a {}", para);

		} catch (Exception e) {

			logger.error("Error enviando email a {}: {}", para, e.getMessage(), e);
		}
	}

	@Override
	public void sendEmail(List<String> destinatarios, String asunto, String contenido, String firma) {

		for (String destinatario : destinatarios) {

			sendEmail(destinatario, asunto, contenido, firma);
		}
	}
}