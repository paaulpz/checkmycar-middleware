package com.paula.checkmc.service.impl;

import java.util.List;

import com.paula.checkmc.service.MailService;

public class MailServiceMockImpl implements MailService {

	public MailServiceMockImpl() {

	}

	@Override
	public void sendEmail(String para, String asunto, String contenido, String firma) {
		System.out.println("Enviado email a"+para+": Asunto "+asunto);
		System.out.println(contenido);
		System.out.println("Enviado");
	}

	@Override
	public void sendEmail(List<String> para, String asunto, String contenido, String firma) {
	    for (String destinatario : para) {
	        sendEmail(destinatario, asunto, contenido, firma);
	    }
	    System.out.println("Enviados");
	}

}

