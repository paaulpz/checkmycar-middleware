package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Venta;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.util.DAOUtils;

public class VentaDAO {

    private static final String BASE_SELECT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT s.id, s.sale_date, ");
        sb.append(" s.price_client, s.price_final, ");
        sb.append(" s.buyer_client_id, ");
        sb.append(" CONCAT(cb.name,' ',cb.first_surname) AS comprador_nombre, ");
        sb.append(" s.seler_client_id, ");
        sb.append(" CONCAT(cv.name,' ',cv.first_surname) AS vendedor_nombre, ");
        sb.append(" s.employee_id, ");
        sb.append(" CONCAT(e.name,' ',e.first_surname) AS empleado_nombre, ");
        sb.append(" s.car_id, ca.car_registration ");
        sb.append(" FROM sale s ");
        sb.append(" LEFT JOIN client cb ON s.buyer_client_id = cb.id ");
        sb.append(" LEFT JOIN client cv ON s.seler_client_id = cv.id ");
        sb.append(" JOIN employee e ON s.employee_id = e.id ");
        sb.append(" JOIN car ca ON s.car_id = ca.id ");
        BASE_SELECT = sb.toString();
    }

    

    public VentaDTO findById(Long id) throws Exception {

        try (Connection c = DAOUtils.getConnection()) {

            StringBuilder sql = new StringBuilder(BASE_SELECT);
            sql.append(" WHERE s.id = ? ");

            PreparedStatement ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, id);

            ResultSet rs = ps.executeQuery();
            return rs.next() ? loadNext(rs) : null;
        }
    }

    public List<VentaDTO> findByCriteria(VentaCriteria cr, int from, int pageSize) throws Exception {

        List<VentaDTO> res = new ArrayList<>();

        try (Connection c = DAOUtils.getConnection()) {

            StringBuilder sql = new StringBuilder(BASE_SELECT);
            List<String> cond = new ArrayList<>();
            List<Object> params = new ArrayList<>();

            if (cr.getClienteCompradorId() != null) {
                cond.add(" s.buyer_client_id = ? ");
                params.add(cr.getClienteCompradorId());
            }

            if (cr.getClienteVendedorId() != null) {
                cond.add(" s.seler_client_id = ? ");
                params.add(cr.getClienteVendedorId());
            }

            if (cr.getEmpleadoId() != null) {
                cond.add(" s.employee_id = ? ");
                params.add(cr.getEmpleadoId());
            }

            if (cr.getCocheId() != null) {
                cond.add(" s.car_id = ? ");
                params.add(cr.getCocheId());
            }

            if (cr.getFechaDesde() != null) {
                cond.add(" s.sale_date >= ? ");
                params.add(cr.getFechaDesde());
            }

            if (cr.getFechaHasta() != null) {
                cond.add(" s.sale_date <= ? ");
                params.add(cr.getFechaHasta());
            }

            if (cr.getSoloAbiertas() != null) {
                if (cr.getSoloAbiertas()) {
                    cond.add(" s.buyer_client_id IS NULL ");
                } else {
                    cond.add(" s.buyer_client_id IS NOT NULL ");
                }
            }

            if (!cond.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", cond));
            }

            PreparedStatement ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps, params);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(loadNext(rs));
            }
        }

        return res;
    }

    public Long create(Venta v) throws Exception {

        try (Connection c = DAOUtils.getConnection()) {

            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO sale ");
            sql.append(" (sale_date, price_client, price_final, buyer_client_id, seler_client_id, employee_id, car_id) ");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?) ");

            PreparedStatement ps = c.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

            DAOUtils.setParameters(ps,
                    v.getFechaVenta(),
                    v.getPrecioCliente(),
                    v.getPrecioFinal(),
                    v.getClienteCompradorId(),
                    v.getClienteVendedorId(),
                    v.getEmpleadoId(),
                    v.getCocheId()
            );

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            return rs.next() ? rs.getLong(1) : null;
        }
    }

    public boolean update(Venta v) throws Exception {

        try (Connection c = DAOUtils.getConnection()) {

            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE sale SET ");
            sql.append(" sale_date = ?, ");
            sql.append(" price_client = ?, ");
            sql.append(" price_final = ?, ");
            sql.append(" buyer_client_id = ?, ");
            sql.append(" seler_client_id = ?, ");
            sql.append(" employee_id = ?, ");
            sql.append(" car_id = ? ");
            sql.append(" WHERE id = ? ");

            PreparedStatement ps = c.prepareStatement(sql.toString());

            DAOUtils.setParameters(ps,
                    v.getFechaVenta(),
                    v.getPrecioCliente(),
                    v.getPrecioFinal(),
                    v.getClienteCompradorId(),
                    v.getClienteVendedorId(),
                    v.getEmpleadoId(),
                    v.getCocheId(),
                    v.getId()
            );

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(Long id) throws Exception {

        try (Connection c = DAOUtils.getConnection()) {

            PreparedStatement ps = c.prepareStatement("DELETE FROM sale WHERE id = ?");
            DAOUtils.setParameters(ps, id);

            return ps.executeUpdate() > 0;
        }
    }
    
    private VentaDTO loadNext(ResultSet rs) throws Exception {

        int i = 1;
        VentaDTO v = new VentaDTO();

        v.setId(rs.getLong(i++));
        v.setFechaVenta(rs.getDate(i++).toLocalDate());

        v.setPrecioCliente(rs.getObject(i++, Double.class));
        v.setPrecioFinal(rs.getObject(i++, Double.class));

        v.setClienteCompradorId(rs.getObject(i++, Long.class));
        v.setClienteCompradorNombre(rs.getString(i++));
        v.setClienteVendedorId(rs.getObject(i++, Long.class));
        v.setClienteVendedorNombre(rs.getString(i++));
        v.setEmpleadoId(rs.getLong(i++));
        v.setEmpleadoNombre(rs.getString(i++));
        v.setCocheId(rs.getLong(i++));
        v.setMatriculaCoche(rs.getString(i++));

        return v;
    }
}