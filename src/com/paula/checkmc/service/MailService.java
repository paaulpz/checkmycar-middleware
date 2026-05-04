package com.paula.checkmc.service;

import java.util.List;

public interface MailService {
	
	/**
	 * Envia un email para un destinatario.
	 * @param para
	 * @param asunto
	 * @param contenido
	 */
	public void sendEmail(String para, String asunto, String contenido, String firma);


/**
 * Eenvia un email para varios destinatarios.
 * @param destinatarios Lista de direcciones de los destinatarios.
 * @param asunto Asunto del email.
 */
public void sendEmail(List<String>  para, String asunto, String contenido, String firma);

}
