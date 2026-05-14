package com.paula.checkmc.service.impl;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.dao.ClienteDAO;
import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.ClienteCriteria;
import com.paula.checkmc.model.ClienteDTO;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.ClienteService;
import com.paula.checkmc.service.EncryptionService;
import com.paula.checkmc.service.MailService;
import com.paula.checkmc.util.JDBCUtils;

public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger =
            LogManager.getLogger(ClienteServiceImpl.class);

    private ClienteDAO clienteDAO = new ClienteDAO();

    private EncryptionService encryptionService =  new EncryptionServiceImpl();

    private MailService mailService = new MailServiceApacheImpl();
    
    @Override
    public Cliente findById(Long id) throws Exception {

        if (id == null || id <= 0) {
            return null;
        }

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            return clienteDAO.findById(c, id);

        } catch (Exception e) {

            logger.error("Error buscando cliente {}: {}",
                    id,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public Results<ClienteDTO> findByCriteria(
            ClienteCriteria criteria,
            int from,
            int pageSize) throws Exception {

        Connection c = null;

        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();

            c.setAutoCommit(false);

            Results<ClienteDTO> results =
                    clienteDAO.findByCriteria(
                            c,
                            criteria,
                            from,
                            pageSize);

            commit = true;

            return results;

        } catch (Exception e) {

            logger.error("Buscando {}: {}",
                    criteria,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long  create(Cliente cliente) throws Exception {

        Connection c = null;

        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();
            c.setAutoCommit(false);
            Long id = clienteDAO.create(c, cliente);
            commit = true;
            return id;
        } catch (Exception e) {

            logger.error("Creando {}: {}",
                    cliente,
                    e.getMessage(),
                    e);
            throw e;
        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public Long register(Cliente usuario) throws Exception {

        if (usuario == null) {

            return null;
        }

        Connection c = null;

        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();

            c.setAutoCommit(false);

            String passwordEncriptada =
                    encryptionService.encrypt(usuario.getPassword());

            usuario.setPassword(passwordEncriptada);

            Long id = clienteDAO.create(c, usuario);

            if (id != null) {

                mailService.sendEmail(
                        usuario.getEmail(),
                        "Bienvenido a Checkmycar",
                        "Hola " + usuario.getNombre()
                                + ", su cuenta ha sido registrada correctamente.",
                        "Equipo Checkmycar");
            }

            commit = true;

            return id;

        } catch (Exception e) {

            logger.error("Error registrando usuario {}: {}",
                    usuario,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public ClienteDTO login(String dni, String password)
            throws Exception {

        Connection c = null;

        try {

            c = JDBCUtils.getConnection();

            ClienteCriteria criteria = new ClienteCriteria();

            criteria.setDniNie(dni);

            Results<ClienteDTO> resultados =
                    clienteDAO.findByCriteria(c, criteria, 1, 1);

            if (resultados != null
                    && resultados.getPage() != null
                    && !resultados.getPage().isEmpty()) {

                ClienteDTO usuario =
                        resultados.getPage().get(0);

                if (encryptionService.checkEncryption(
                        password,
                        usuario.getPassword())) {

                    return usuario;
                }
            }

            return null;

        } catch (Exception e) {

            logger.error("Error login {}: {}",
                    dni,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, true);
        }
    }

    @Override
    public boolean update(Cliente cliente)
            throws Exception {

        if (cliente == null
                || cliente.getId() == null
                || cliente.getId() <= 0) {

            return false;
        }

        Connection c = null;

        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();

            c.setAutoCommit(false);

            boolean updated =
                    clienteDAO.update(c, cliente);

            commit = true;

            return updated;

        } catch (Exception e) {

            logger.error("Actualizando {}: {}",
                    cliente,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }

    @Override
    public boolean delete(Long id)
            throws Exception {

        if (id == null || id <= 0) {

            return false;
        }

        Connection c = null;

        boolean commit = false;

        try {

            c = JDBCUtils.getConnection();

            c.setAutoCommit(false);

            boolean deleted =
                    clienteDAO.delete(c, id);

            commit = true;

            return deleted;

        } catch (Exception e) {

            logger.error("Error eliminando cliente {}: {}",
                    id,
                    e.getMessage(),
                    e);

            throw e;

        } finally {

            JDBCUtils.close(c, commit);
        }
    }
}