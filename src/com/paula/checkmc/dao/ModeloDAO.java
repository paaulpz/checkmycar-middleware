package com.paula.checkmc.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Modelo;
import com.paula.checkmc.util.JDBCUtils;

public class ModeloDAO {
    private static final String BASE_SELECT =
        " SELECT m.id, m.name, m.brand_id " +
        " FROM model m " ;
       

   

    public List<Modelo> findByMarca(Long marcaId) {
        List<Modelo> lista = new ArrayList<>();
        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " WHERE m.brand_id=? ORDER BY m.name")) {
            ps.setLong(1, marcaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int i = 1;
                Modelo m = new Modelo();
                m.setId(rs.getLong(i++));
                m.setNombre(rs.getString(i++));
                m.setMarcaId(rs.getLong(i++));
                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
 
}