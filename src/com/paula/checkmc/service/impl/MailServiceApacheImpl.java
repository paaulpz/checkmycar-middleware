package com.paula.checkmc.service.impl;

import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import com.paula.checkmc.service.MailService;


/**
 * Implementacion de MailService sobre Apache Commons Email.
 */
public class MailServiceApacheImpl implements MailService {

	public MailServiceApacheImpl() {
		// Constructor
	}

	public void sendEmail(String para, String asunto, String contenido, String firma) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmail(List<String> destinatarios, String asunto, String contenido, String firma) {
		for (String destinatario : destinatarios) {
			sendEmail(destinatario, asunto, contenido, firma);
		}
	}

}