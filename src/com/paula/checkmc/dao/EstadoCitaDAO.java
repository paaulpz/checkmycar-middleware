package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoCitaDAO {

    public EstadoCita findById(Long id) {

        String sql = "SELECT id, name FROM appointment_status WHERE id=?";
        
    

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                EstadoCita ec = new EstadoCita();
                ec.setId(rs.getLong("id"));
                ec.setNombre(rs.getString("name"));
                return ec;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<EstadoCita> findAll() {

        List<EstadoCita> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM appointment_status ORDER BY name";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EstadoCita ec = new EstadoCita();
                ec.setId(rs.getLong("id"));
                ec.setNombre(rs.getString("name"));
                lista.add(ec);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}