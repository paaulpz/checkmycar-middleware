package com.paula.checkmc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.impl.VentaServiceImpl;

public class VentaServiceTest {

	private static final Logger logger = LogManager.getLogger(VentaServiceTest.class);

	private VentaService service;

	public VentaServiceTest() {

		this.service = new VentaServiceImpl();
	}

	public void testFindById(Long id) {

		try {

			VentaDTO venta = service.findById(id);

			if (venta != null) {
				logger.info("Venta encontrada: {}", venta);

			} else {
				logger.warn("No existe venta con id {}", id);
			}
		} catch (Exception e) {
			logger.error("Error en testFindById {}: {}", id, e.getMessage(), e);
		}
	}

	public void testFindByCriteria() {

		try {

			VentaCriteria criteria = new VentaCriteria();
			Results<VentaDTO> results = service.findByCriteria(criteria, 1, 10);
			logger.info("Ventas encontradas: {}", results.getTotal());
			for (VentaDTO venta : results.getPage()) {
				logger.info(venta);
			}
		} catch (Exception e) {

			logger.error("Error en testFindByCriteria: {}", e.getMessage(), e);
		}
	}

	public void testCreate() {

		try {

			VentaDTO venta = new VentaDTO();

			venta.setClienteCompradorId(1L);
			venta.setClienteVendedorId(2L);
			venta.setEmpleadoId(1L);
			venta.setCocheId(1L);

			Long id = service.create(venta);
			logger.info("Venta creada con id {}", id);

		} catch (Exception e) {
			logger.error("Error en testCreate: {}", e.getMessage(), e);
		}
	}

	public void testUpdate() {

		try {

			VentaDTO venta = service.findById(1L);

			if (venta == null) {
				logger.warn("No existe venta para actualizar");
				return;
			}

			boolean updated = service.update(venta);
			logger.info("Venta actualizada: {}", updated);
		} catch (Exception e) {
			logger.error("Error en testUpdate: {}", e.getMessage(), e);
		}
	}

	public void testDelete(Long id) {

		try {

			boolean deleted = service.delete(id);
			logger.info("Venta eliminada: {}", deleted);
		} catch (Exception e) {
			logger.error("Error en testDelete {}: {}", id, e.getMessage(), e);
		}
	}

	public static void main(String[] args) {

		VentaServiceTest test = new VentaServiceTest();

		test.testFindById(1L);
		// test.testFindByCriteria();
		// test.testCreate();
		// test.testUpdate();
		// test.testDelete(1L);
	}
}