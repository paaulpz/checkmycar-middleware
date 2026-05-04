package com.paula.checkmc.service.impl;

import com.paula.checkmc.dao.CitaDAO;
import com.paula.checkmc.model.Cita;
import com.paula.checkmc.model.CitaCriteria;
import com.paula.checkmc.model.CitaDTO;
import com.paula.checkmc.model.Cliente;
import com.paula.checkmc.model.Results;
import com.paula.checkmc.service.CitaService;

public class CitaServiceImpl implements CitaService {

    private CitaDAO dao = new CitaDAO();

    @Override
    public Results<CitaDTO> findByCriteria(CitaCriteria criteria, int from , int pageSize) {

      return dao.findByCriteria(criteria, from, pageSize); 
    }

    @Override
    public Long create(Cita  cita)  {

        Cita c = new Cita();

        c.setDescripcion(cita.getDescripcion());
        c.setFecha(cita.getFecha());
        c.setClienteId(cita.getClienteId());
        c.setCocheId(cita.getCocheId());
        c.setEstadoCitaId(cita.getEstadoCitaId());

        return create(cita); 
    }

    @Override
    public boolean delete(Long id) throws Exception {
        return dao.delete(id);
    }

	@Override
	public boolean update(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

 /*   private boolean validar(Cita cita) {

        if (cita == null) return false;

        if (cita.getDescripcion() == null || cita.getDescripcion().trim().isEmpty()) return false;

        if (cita.getFecha() == null) return false;

        if (cita.getClienteId() == null) return false;

        if (cita.getCocheId() == null) return false;

        if (cita.getEstadoCitaId() == null) return false;

        return true;
    }
    */
}