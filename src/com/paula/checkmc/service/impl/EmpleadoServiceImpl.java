package com.paula.checkmc.service.impl;

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

public class EmpleadoServiceImpl implements EmpleadoService {
	
	private static final Logger logger = LogManager.getLogger(EmpleadoServiceImpl.class);
	
	  private EncryptionService encryptionService = new EncryptionServiceImpl();
	  private EmpleadoDAO empleadoDAO = null; 
	  private MailService mailService = new MailServiceApacheImpl();
	  
	  
 



	public EmpleadoServiceImpl() {
		empleadoDAO = new EmpleadoDAO(); 
		encryptionService = new EncryptionServiceImpl();
		mailService = new MailServiceApacheImpl();
	}



	@Override
    public Empleado findById(Long id) {

        if (id == null || id <= 0) return null;

        return empleadoDAO.findById(id);
    }

 

    @Override
    public Results<EmpleadoDTO> findByCriteria(EmpleadoCriteria criteria, int from , int pageSize) {

        return empleadoDAO.findByCriteria(criteria, from, pageSize);
    }

    @Override
    public Empleado create(Empleado empleado) {

        return empleadoDAO.create(empleado);
    }
    
    
    

    @Override
    public boolean update(Empleado empleado) {

        if (empleado.getId() == null || empleado.getId() <= 0) return false;

        return empleadoDAO.update(empleado);
    }

    @Override
    public boolean delete(Long id) {

        if (id == null || id <= 0) return false;

        return empleadoDAO.delete(id);
    }

   
    @Override
    public Long register(Empleado empleado) {

        logger.info("Registrando empleado: {}", empleado);

        if (empleado == null) {
            logger.warn("Empleado null");
            return null;
        }

        if (empleado.getPassword() == null || empleado.getPassword().trim().isEmpty()) {
            logger.warn("Password inválida");
            return null;
        }

       
        if (empleado.getEmail() != null)
            empleado.setEmail(empleado.getEmail().trim());

        if (empleado.getNombre() != null)
            empleado.setNombre(empleado.getNombre().trim());

        if (empleado.getPrimerApellido() != null)
            empleado.setPrimerApellido(empleado.getPrimerApellido().trim());

        
        String passwordEncriptada = encryptionService.encrypt(empleado.getPassword().trim());
        empleado.setPassword(passwordEncriptada);

        Empleado registrado = empleadoDAO.create(empleado);

        if (registrado != null) {

            
            try {
                mailService.sendEmail(
                    registrado.getEmail(),
                    "Bienvenido a CheckMyCar",
                    "Hola " + registrado.getNombre() + ", tu cuenta de empleado ha sido creada correctamente.",
                    "®Desde hoy perteneces al equipo de checkmycar"
                );
            } catch (Exception e) {
                logger.warn("Error enviando email a {}", registrado.getEmail());
            }

            logger.info("Empleado registrado con id: {}", registrado.getId());
            return registrado.getId();
        }

        logger.error("No se pudo registrar el empleado");
        return null;
    }
    
    @Override
    public EmpleadoDTO login(String dni, String password) {
        EmpleadoCriteria criteria = new EmpleadoCriteria();
        criteria.setDniNie(dni);

        Results<EmpleadoDTO> resultados = empleadoDAO.findByCriteria(criteria, 1, 1);

        if (resultados != null && resultados.getPage() != null && !resultados.getPage().isEmpty()) {
            EmpleadoDTO empleado = resultados.getPage().get(0);
            if (encryptionService.checkEncryption(password, empleado.getPassword())) {
                return empleado;
            }
        }
        return null;
    }
	
	}




