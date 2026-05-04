package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.EstadoPresupuesto;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoPresupuestoDAO {

    public EstadoPresupuesto findById(Long id) {

        String sql = "SELECT id, name FROM quotation_status WHERE id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
             
                return loadNext(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<EstadoPresupuesto> findAll() {

        List<EstadoPresupuesto> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM quotation_status ORDER BY name";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
               
                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private EstadoPresupuesto loadNext(ResultSet rs) throws Exception {

        int i = 1;

        EstadoPresupuesto e = new EstadoPresupuesto();

        e.setId(rs.getLong(i++));
        e.setNombre(rs.getString(i++));


        return e;
    }
 
}