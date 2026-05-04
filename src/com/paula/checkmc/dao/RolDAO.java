package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Rol;
import com.paula.checkmc.util.JDBCUtils;

public class RolDAO {



    public List<Rol> findAll() {

        List<Rol> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM rol ORDER BY name";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	Integer i = 1; 
                Rol r = new Rol();
                r.setId(rs.getLong(i++));
                r.setNombre(rs.getString(i++));
                lista.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}