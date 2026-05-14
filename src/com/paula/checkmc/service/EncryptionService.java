package com.paula.checkmc.service;

public interface EncryptionService {
	
	
	/**
	 * Encripta una cadena de texto.
	 * @param data La cadena de texto a encriptar.
	 * @return La cadena de texto encriptada.
	 */
	public String encrypt(String data);
	
	
	/**
	 * Verifica si una cadena de texto coincide con su versión encriptada.
	 * @param data
	 * @param encryptedData
	 * @return
	 */
	public boolean checkEncryption(String data, String encryptedData);
}
