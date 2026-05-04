package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.EstadoPieza;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoPiezaDAO {

    public EstadoPieza findById(Long id) {

        String sql = "SELECT id, name FROM part_status WHERE id=?";

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

    public List<EstadoPieza> findAll() {

        List<EstadoPieza> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM part_status ORDER BY name";

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
    
    
    private EstadoPieza loadNext(ResultSet rs) throws Exception {

        int i = 1;

        EstadoPieza e = new EstadoPieza();

        e.setId(rs.getLong(i++));
        e.setNombre(rs.getString(i++));
        

        return e;
    }
}