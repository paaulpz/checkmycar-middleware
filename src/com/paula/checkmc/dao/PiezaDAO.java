package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Pieza;
import com.paula.checkmc.model.PiezaCriteria;
import com.paula.checkmc.util.JDBCUtils;

public class PiezaDAO {

    private static final String BASE_SELECT =
        " SELECT p.id, p.name, p.stock, p.part_status_id, p.price " +
        " FROM part p ";

   

    public Pieza findById(Long id) {

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " WHERE p.id=?")) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            return rs.next() ? loadNext(rs) : null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pieza> findAll() {

        List<Pieza> lista = new ArrayList<>();

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(BASE_SELECT + " ORDER BY p.name");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Pieza> findByCriteria(PiezaCriteria cr) {

        List<Pieza> lista = new ArrayList<>();

        try (Connection c = JDBCUtils.getConnection()) {

            StringBuilder sql = new StringBuilder(BASE_SELECT);
            List<String> cond = new ArrayList<>();
            List<Object> params = new ArrayList<>();

            if (cr.getNombre() != null && !cr.getNombre().trim().isEmpty()) {
                cond.add(" UPPER(p.name) LIKE UPPER(?) ");
                params.add("%" + cr.getNombre() + "%");
            }

            if (cr.getEstadoId() != null) {
                cond.add(" p.part_status_id = ? ");
                params.add(cr.getEstadoId());
            }

            if (!cond.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", cond));
            }

            PreparedStatement ps = c.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Long create(Pieza p) {

        String sql = "INSERT INTO part(name,stock,part_status_id,price) VALUES(?,?,?,?)";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getStock());
            ps.setLong(3, p.getEstadoId());
            ps.setBigDecimal(4, p.getPrecio());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getLong(1) : null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Pieza p) {

        String sql = "UPDATE part SET name=?, stock=?, part_status_id=?, price=? WHERE id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getStock());
            ps.setLong(3, p.getEstadoId());
            ps.setBigDecimal(4, p.getPrecio());
            ps.setLong(5, p.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Long id) {

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM part WHERE id=?")) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Pieza loadNext(ResultSet rs) throws Exception {

        Pieza p = new Pieza();
        int i = 1;

        p.setId(rs.getLong(i++));
        p.setNombre(rs.getString(i++));
        p.setStock(rs.getInt(i++));
        p.setEstadoId(rs.getLong(i++));
        p.setPrecio(rs.getBigDecimal(i++));

        return p;
    }
}