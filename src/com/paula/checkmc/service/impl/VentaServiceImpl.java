package com.paula.checkmc.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.VentaDAO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.Venta;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.service.VentaService;
import com.paula.checkmc.util.JDBCUtils;

public class VentaServiceImpl implements VentaService {

	private static final Logger logger = LogManager.getLogger(VentaServiceImpl.class);

	private VentaDAO ventaDAO = new VentaDAO();

	@Override
	public VentaDTO findById(Long id) throws Exception {

		if (id == null || id <= 0) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			VentaDTO venta = ventaDAO.findById(c, id);

			commit = true;

			return venta;

		} catch (Exception e) {

			logger.error("Error buscando venta {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Results<VentaDTO> findByCriteria(VentaCriteria criteria, int from, int pageSize) throws Exception {

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Results<VentaDTO> results = ventaDAO.findByCriteria(c, criteria, from, pageSize);

			commit = true;

			return results;

		} catch (Exception e) {

			logger.error("Buscando ventas {}: {}", criteria, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {

		if (id == null || id <= 0) {
			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			boolean deleted = ventaDAO.delete(c, id);

			commit = true;

			return deleted;

		} catch (Exception e) {

			logger.error("Error eliminando venta {}: {}", id, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public Long create(VentaDTO venta) throws Exception {

		if (venta == null) {
			return null;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Venta v = new Venta();

			v.setFechaVenta(venta.getFechaVenta());

			v.setPrecioCliente(venta.getPrecioCliente());

			v.setPrecioFinal(venta.getPrecioFinal());

			v.setClienteCompradorId(venta.getClienteCompradorId());

			v.setClienteVendedorId(venta.getClienteVendedorId());

			v.setEmpleadoId(venta.getEmpleadoId());

			v.setCocheId(venta.getCocheId());

			Long creada = ventaDAO.create(c, v);

			commit = true;

			return creada;

		} catch (Exception e) {

			logger.error("Error creando venta {}: {}", venta, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public boolean update(VentaDTO venta) throws Exception {

		if (venta == null || venta.getId() == null || venta.getId() <= 0) {

			return false;
		}

		Connection c = null;

		boolean commit = false;

		try {

			c = JDBCUtils.getConnection();

			c.setAutoCommit(false);

			Venta v = new Venta();

			v.setId(venta.getId());

			v.setFechaVenta(venta.getFechaVenta());

			v.setPrecioCliente(venta.getPrecioCliente());

			v.setPrecioFinal(venta.getPrecioFinal());

			v.setClienteCompradorId(venta.getClienteCompradorId());

			v.setClienteVendedorId(venta.getClienteVendedorId());

			v.setEmpleadoId(venta.getEmpleadoId());

			v.setCocheId(venta.getCocheId());

			boolean updated = ventaDAO.update(c, v);

			commit = true;

			return updated;

		} catch (Exception e) {

			logger.error("Error actualizando venta {}: {}", venta, e.getMessage(), e);

			throw e;

		} finally {

			JDBCUtils.close(c, commit);
		}
	}
}