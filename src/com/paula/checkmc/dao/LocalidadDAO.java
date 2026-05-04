package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.util.JDBCUtils;

public class LocalidadDAO {

    public LocalidadDAO() {
    }

    public Localidad findById(Long id) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = JDBCUtils.getConnection();

            String sql = "SELECT id, name, province_id FROM locality WHERE id = ?";
            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
           

                return loadNext(rs);
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public List<Localidad> findAll() {

        List<Localidad> resultados = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = JDBCUtils.getConnection();

            String sql = "SELECT id, name, province_id FROM locality ORDER BY name";
            ps = c.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                resultados.add(loadNext(rs));
            }

            return resultados;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Localidad> findByProvince(Long provinceId) {

        List<Localidad> resultados = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = JDBCUtils.getConnection();

            String sql = "SELECT id, name, province_id FROM locality WHERE province_id = ? ORDER BY name";
            ps = c.prepareStatement(sql);
            ps.setLong(1, provinceId);

            rs = ps.executeQuery();

            while (rs.next()) {

                int i = 1;
       
                resultados.add(loadNext(rs));
            }

            return resultados;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   
    public List<Localidad> findByNombre(String nombre) {

        List<Localidad> resultados = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = JDBCUtils.getConnection();

            String sql = "SELECT id, name, province_id FROM locality WHERE UPPER(name) LIKE UPPER(?) ORDER BY name";
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");

            rs = ps.executeQuery();

            while (rs.next()) {

                resultados.add(loadNext(rs));
            }

            return resultados;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (c != null) c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private Localidad loadNext(ResultSet rs) throws Exception {

        int i = 1;

        Localidad l = new Localidad();

        l.setId(rs.getLong(i++));
        l.setNombre(rs.getString(i++));
        l.setProvinceId(rs.getLong(i++));


        return l;
    }
}