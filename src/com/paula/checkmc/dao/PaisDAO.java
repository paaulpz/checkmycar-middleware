package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Pais;
import com.paula.checkmc.util.JDBCUtils;

public class PaisDAO {

    public Pais findById(Long id) {

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT id, name FROM country WHERE id = ?")) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return loadNext(rs);
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pais> findAll() {

        List<Pais> lista = new ArrayList<>();

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT id, name FROM country ORDER BY name");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    private Pais loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Pais p = new Pais();

        p.setId(rs.getLong(i++));
        p.setNombre(rs.getString(i++));
       
        return p;
    }
}