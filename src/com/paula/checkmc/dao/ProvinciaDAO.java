package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Provincia;
import com.paula.checkmc.util.JDBCUtils;

public class ProvinciaDAO {

    public Provincia findById(Long id) {

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT id, name, country_id FROM province WHERE id = ?")) {

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


    public List<Provincia> findByPais(Long paisId) {

        List<Provincia> lista = new ArrayList<>();

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT id, name, country_id FROM province WHERE country_id = ? ORDER BY name")) {

            ps.setLong(1, paisId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

      

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    private Provincia loadNext(ResultSet rs) throws Exception {

        int i = 1;
        Provincia p = new Provincia();

        p.setId(rs.getLong(i++));
        p.setNombre(rs.getString(i++));
        p.setPaisId(rs.getLong(i++));
        return p;
    }
}