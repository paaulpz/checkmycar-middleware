package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.TipoMotor;
import com.paula.checkmc.util.JDBCUtils;

public class TipoMotorDAO {


    public List<TipoMotor> findAll() {

        List<TipoMotor> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM type_engine ORDER BY name";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	int i = 1 ; 
                TipoMotor tm = new TipoMotor();
                tm.setId(rs.getLong(i++));
                tm.setNombre(rs.getString(i++));
                lista.add(tm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}