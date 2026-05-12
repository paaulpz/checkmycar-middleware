package com.paula.checkmc.service.impl;

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

public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger =
            LogManager.getLogger(ClienteServiceImpl.class);

    private ClienteDAO clienteDAO;
    private EncryptionService encryptionService;
    private MailService mailService;

    public ClienteServiceImpl() {

        clienteDAO = new ClienteDAO();
        encryptionService = new EncryptionServiceImpl();
        mailService = new MailServiceApacheImpl();
    }

    @Override
    public Cliente findById(Long id) {

        return clienteDAO.findById(id);
    }

    @Override
    public Results<ClienteDTO> findByCriteria(ClienteCriteria criteria,
                                              int from,
                                              int pageSize) {

        return clienteDAO.findByCriteria(criteria, from, pageSize);
    }

    @Override
    public Cliente create(Cliente cliente) {

        return clienteDAO.create(cliente);
    }

    @Override
    public Long register(Cliente usuario) {

        if (usuario == null) {

            return null;
        }

        String passwordEncriptada =
                encryptionService.encrypt(usuario.getPassword());

        usuario.setPassword(passwordEncriptada);

        Cliente registrado = clienteDAO.create(usuario);

        if (registrado != null) {

            mailService.sendEmail(
                    registrado.getEmail(),
                    "Bienvenido a Checkmycar",
                    "Hola " + registrado.getNombre()
                            + ", su cuenta ha sido registrada correctamente.",
                    "Equipo Checkmycar");
        }

        return registrado != null ? registrado.getId() : null;
    }

    @Override
    public ClienteDTO login(String dni, String password) {

        ClienteCriteria criteria = new ClienteCriteria();

        criteria.setDniNie(dni);

        Results<ClienteDTO> resultados =
                clienteDAO.findByCriteria(criteria, 1, 1);

        if (resultados != null
                && resultados.getPage() != null
                && !resultados.getPage().isEmpty()) {

            ClienteDTO usuario = resultados.getPage().get(0);

            if (encryptionService.checkEncryption(
                    password,
                    usuario.getPassword())) {

                return usuario;
            }
        }

        return null;
    }

    @Override
    public boolean update(Cliente cliente) {

        if (cliente == null || cliente.getId() == null) {

            return false;
        }

        return clienteDAO.update(cliente);
    }

    @Override
    public boolean delete(Long id) {

        if (id == null || id <= 0) {

            return false;
        }

        return clienteDAO.delete(id);
    }
}