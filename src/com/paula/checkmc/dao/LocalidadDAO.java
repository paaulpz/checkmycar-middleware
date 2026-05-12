package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Localidad;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class LocalidadDAO {

    private static final Logger logger = LogManager.getLogger(LocalidadDAO.class);

    public LocalidadDAO() {
    }

    public Localidad findById(Long id) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name, province_id ");
            sql.append("FROM locality ");
            sql.append("WHERE id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                return loadNext(rs);
            }

        } catch (Exception e) {

            logger.error("Error buscando localidad: {}", id, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public List<Localidad> findAll() {

        List<Localidad> resultados = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name, province_id ");
            sql.append("FROM locality ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            rs = ps.executeQuery();

            while (rs.next()) {

                resultados.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error listando localidades", e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return resultados;
    }

    public List<Localidad> findByProvince(Long provinceId) {

        List<Localidad> resultados = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name, province_id ");
            sql.append("FROM locality ");
            sql.append("WHERE province_id=? ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, provinceId);

            rs = ps.executeQuery();

            while (rs.next()) {

                resultados.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error buscando localidades por provincia: {}", provinceId, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return resultados;
    }

    public List<Localidad> findByNombre(String nombre) {

        List<Localidad> resultados = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append("SELECT id, name, province_id ");
            sql.append("FROM locality ");
            sql.append("WHERE UPPER(name) LIKE UPPER(?) ");
            sql.append("ORDER BY name");

            ps = c.prepareStatement(sql.toString());

            ps.setString(1, "%" + nombre + "%");

            rs = ps.executeQuery();

            while (rs.next()) {

                resultados.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error buscando localidades por nombre: {}", nombre, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return resultados;
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