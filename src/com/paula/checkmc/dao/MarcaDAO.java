package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Marca;
import com.paula.checkmc.util.JDBCUtils;

public class MarcaDAO {

    public Marca findById(Long id) {

        String sql = "SELECT id, name FROM brand WHERE id=?";

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

    public List<Marca> findAll() {

        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT id, name FROM brand ORDER BY name";

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
    
    private Marca loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Marca m = new Marca();

        m.setId(rs.getLong(i++));
        m.setNombre(rs.getString(i++));
   

        return m;
    }
}