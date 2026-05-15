package com.paula.checkmc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.service.impl.TipoTransmisionServiceImpl;

public class TipoTransmisionServiceTest {

	private static final Logger logger = LogManager.getLogger(TipoTransmisionServiceTest.class);

	private TipoTransmisionService service;

	public TipoTransmisionServiceTest() {

		this.service = new TipoTransmisionServiceImpl();
	}

	public void testFindById(Long id) {

		try {
			TipoTransmision tipo = service.findById(id);

			if (tipo != null) {
				logger.info("Tipo transmision encontrado: {}", tipo);
			} else {
				logger.warn("No existe tipo transmision con id {}", id);
			}

		} catch (Exception e) {
			logger.error("Error en testFindById {}: {}", id, e.getMessage(), e);
		}
	}

	public void testFindAll() {

		try {

			List<TipoTransmision> tipos = service.findAll();
			logger.info("Tipos transmision encontrados: {}", tipos.size());
			for (TipoTransmision tipo : tipos) {
				logger.info(tipo);
			}
		} catch (Exception e) {
			logger.error("Error en testFindAll: {}", e.getMessage(), e);
		}
	}

	public static void main(String[] args) {

		TipoTransmisionServiceTest test = new TipoTransmisionServiceTest();

		test.testFindById(1L);
		// test.testFindAll();
	}
}