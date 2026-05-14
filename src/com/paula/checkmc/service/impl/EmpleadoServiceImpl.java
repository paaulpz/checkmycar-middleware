package com.paula.checkmc.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.EmpleadoDAO;
import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.EmpleadoCriteria;
import com.paula.checkmc.model.EmpleadoDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.EmpleadoService;
import com.paula.checkmc.service.EncryptionService;
import com.paula.checkmc.service.MailService;
import com.paula.checkmc.util.JDBCUtils;

public class EmpleadoServiceImpl implements EmpleadoService {

    private static final Logger logger = LogManager.getLogger(EmpleadoServiceImpl.class);

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private EncryptionService encryptionService = new EncryptionServiceImpl();
    private MailService mailService = new MailServiceApacheImpl();

    @Override
    public Empleado findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return empleadoDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando empleado {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from, int pageSize)
            throws Exception {

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Results<EmpleadoDTO> results =
                    empleadoDAO.findByCriteria(c, criteria, from, pageSize);

            commit = true;

            return results;

        } catch (Exception e) {

            logger.error("Buscando {}: {}", criteria, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long create(Empleado empleado) throws Exception {

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Long id = empleadoDAO.create(c, empleado);

            commit = true;

            return id;

        } catch (Exception e) {

            logger.error("Creando {}: {}", empleado, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean update(Empleado empleado) throws Exception {

        if (empleado.getId() == null || empleado.getId() <= 0) {
            return false;
        }

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            boolean updated = empleadoDAO.update(c, empleado);

            commit = true;

            return updated;

        } catch (Exception e) {

            logger.error("Actualizando {}: {}", empleado, e.getMessage(), e);

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

            boolean deleted = empleadoDAO.delete(c, id);

            commit = true;

            return deleted;

        } catch (Exception e) {

            logger.error("Error eliminando empleado {}: {}", id, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long register(Empleado empleado) throws Exception {

        logger.info("Registrando empleado: {}", empleado);

        if (empleado == null) {

            logger.warn("Empleado null");

            return null;
        }

        if (empleado.getPassword() == null
                || empleado.getPassword().trim().isEmpty()) {

            logger.warn("Password invalida");

            return null;
        }

        if (empleado.getEmail() != null) {
            empleado.setEmail(empleado.getEmail().trim());
        }

        if (empleado.getNombre() != null) {
            empleado.setNombre(empleado.getNombre().trim());
        }

        if (empleado.getPrimerApellido() != null) {
            empleado.setPrimerApellido(empleado.getPrimerApellido().trim());
        }

        String passwordEncriptada =
                encryptionService.encrypt(empleado.getPassword().trim());

        empleado.setPassword(passwordEncriptada);

        Connection c = null;
        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);

            Long id = empleadoDAO.create(c, empleado);

            if (id != null) {

                try {

                    mailService.sendEmail(
                            empleado.getEmail(),
                            "Bienvenido a CheckMyCar",
                            "Hola " + empleado.getNombre()
                                    + ", tu cuenta de empleado ha sido creada correctamente.",
                            "®Desde hoy perteneces al equipo de checkmycar");

                } catch (Exception e) {

                    logger.warn("Error enviando email a {}", empleado.getEmail());
                }

                commit = true;

                logger.info("Empleado registrado con id: {}", id);

                return id;
            }

        } catch (Exception e) {

            logger.error("Error registrando empleado {}: {}", empleado, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }

        logger.error("No se pudo registrar el empleado");

        return null;
    }

    @Override
    public EmpleadoDTO login(String dni, String password, Long rolId) throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            Empleado empleado = empleadoDAO.findByEmail(c, dni);

            if (empleado == null) {
                return null;
            }

            boolean loginCorrecto =
                    empleadoDAO.login(c, dni, password, rolId);

            if (!loginCorrecto) {
                return null;
            }

            EmpleadoCriteria criteria = new EmpleadoCriteria();

            criteria.setDniNie(dni);
            criteria.setRolId(rolId);

            Results<EmpleadoDTO> resultados =
                    empleadoDAO.findByCriteria(c, criteria, 1, 1);

            if (resultados != null
                    && resultados.getPage() != null
                    && !resultados.getPage().isEmpty()) {

                return resultados.getPage().get(0);
            }

        } catch (Exception e) {

            logger.error("Error login empleado {}: {}", dni, e.getMessage(), e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }

        return null;
    }
}