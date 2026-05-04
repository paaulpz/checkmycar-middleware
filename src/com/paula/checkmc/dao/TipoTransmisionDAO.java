package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.TipoTransmision;
import com.paula.checkmc.util.JDBCUtils;

public class TipoTransmisionDAO {



    public List<TipoTransmision> findAll() {

        List<TipoTransmision> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM transmission_type ORDER BY name";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	int i = 1; 
                TipoTransmision tt = new TipoTransmision();
                tt.setId(rs.getLong(i++));
                tt.setNombre(rs.getString(i++));
                lista.add(tt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}