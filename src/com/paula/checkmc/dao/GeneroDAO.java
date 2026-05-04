package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Genero;
import com.paula.checkmc.util.JDBCUtils;

public class GeneroDAO {

    public Genero findById(Long id) {

        String sql = "SELECT id, name FROM gender WHERE id=?";

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

    public List<Genero> findAll() {

        List<Genero> lista = new ArrayList<>();

        String sql = "SELECT id, name FROM gender ORDER BY name";

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
    
    private Genero loadNext(ResultSet rs) throws Exception {

        int i = 1;
        Genero g = new Genero();
        
        g.setId(rs.getLong(i++));
        g.setNombre(rs.getString(i++));
		return g;

      
    }
}