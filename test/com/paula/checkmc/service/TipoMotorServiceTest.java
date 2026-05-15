package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.service.impl.TipoMotorServiceImpl;

public class TipoMotorServiceTest {

	private static final Logger logger = LogManager.getLogger(TipoMotorServiceTest.class);

	private TipoMotorService service;

	public TipoMotorServiceTest() {

		this.service = new TipoMotorServiceImpl();
	}

	public void testFindById(Long id) {

		try {

			TipoMotor tipo = service.findById(id);
			if (tipo != null) {
				logger.info("Tipo motor encontrado: {}", tipo);
			} else {
				logger.warn("No existe tipo motor con id {}", id);
			}
		} catch (Exception e) {
			logger.error("Error en testFindById {}: {}", id, e.getMessage(), e);
		}
	}

	public void testFindAll() {

		try {
			List<TipoMotor> tipos = service.findAll();
			logger.info("Tipos motor encontrados: {}", tipos.size());
			for (TipoMotor tipo : tipos) {

				logger.info(tipo);
			}
		} catch (Exception e) {
			logger.error("Error en testFindAll: {}", e.getMessage(), e);
		}
	}

	public static void main(String[] args) {

		TipoMotorServiceTest test = new TipoMotorServiceTest();

		test.testFindById(1L);
		// test.testFindAll();
	}
}