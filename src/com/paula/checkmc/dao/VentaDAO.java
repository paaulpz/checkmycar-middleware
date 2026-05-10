package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Results;
import com.paula.checkmc.model.Venta;
import com.paula.checkmc.model.VentaCriteria;
import com.paula.checkmc.model.VentaDTO;
import com.paula.checkmc.util.DAOUtils;
import com.paula.checkmc.util.JDBCUtils;

public class VentaDAO {

    private static Logger logger = LogManager.getLogger(VentaDAO.class);

    private static final String BASE_SELECT;

    static {

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT s.id, s.sale_date, ");
        sb.append(" s.price_client, s.price_final, ");

        sb.append(" s.buyer_client_id, ");
        sb.append(" CONCAT(cb.name,' ',cb.first_surname) AS comprador_nombre, ");

        sb.append(" s.seller_client_id, ");
        sb.append(" CONCAT(cv.name,' ',cv.first_surname) AS vendedor_nombre, ");

        sb.append(" s.employee_id, ");
        sb.append(" CONCAT(e.name,' ',e.first_surname) AS empleado_nombre, ");

        sb.append(" s.car_id, ");
        sb.append(" ca.car_registration ");

        sb.append(" FROM sale s ");

        sb.append(" LEFT JOIN client cb ");
        sb.append(" ON s.buyer_client_id = cb.id ");

        sb.append(" LEFT JOIN client cv ");
        sb.append(" ON s.seller_client_id = cv.id ");

        sb.append(" JOIN employee e ");
        sb.append(" ON s.employee_id = e.id ");

        sb.append(" JOIN car ca ");
        sb.append(" ON s.car_id = ca.id ");

        BASE_SELECT = sb.toString();
    }

    public VentaDTO findById(Long id) {

        logger.debug("Buscando venta por id: {}", id);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            String sql = BASE_SELECT + " WHERE s.id = ? ";

            logger.debug("SQL: {}", sql);

            ps = c.prepareStatement(sql);

            DAOUtils.setParameters(ps, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                VentaDTO dto = loadNext(rs);

                logger.debug("Venta encontrada: {}", dto);

                return dto;
            }

        } catch (Exception e) {

            logger.error("Error buscando venta id: {}", id, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public Results<VentaDTO> findByCriteria(VentaCriteria cr,
                                            int from,
                                            int pageSize) {

        logger.info("criteria: {}", cr);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Results<VentaDTO> results = new Results<>();

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder(BASE_SELECT);

            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            if (cr.getClienteCompradorId() != null) {

                condiciones.add(" s.buyer_client_id = ? ");
                parametros.add(cr.getClienteCompradorId());
            }

            if (cr.getClienteVendedorId() != null) {

                condiciones.add(" s.seller_client_id = ? ");
                parametros.add(cr.getClienteVendedorId());
            }

            if (cr.getEmpleadoId() != null) {

                condiciones.add(" s.employee_id = ? ");
                parametros.add(cr.getEmpleadoId());
            }

            if (cr.getCocheId() != null) {

                condiciones.add(" s.car_id = ? ");
                parametros.add(cr.getCocheId());
            }

            if (cr.getFechaDesde() != null) {

                condiciones.add(" s.sale_date >= ? ");
                parametros.add(cr.getFechaDesde());
            }

            if (cr.getFechaHasta() != null) {

                condiciones.add(" s.sale_date <= ? ");
                parametros.add(cr.getFechaHasta());
            }

            if (cr.getSoloAbiertas() != null) {

                if (cr.getSoloAbiertas()) {

                    condiciones.add(" s.buyer_client_id IS NULL ");

                } else {

                    condiciones.add(" s.buyer_client_id IS NOT NULL ");
                }
            }

            if (!condiciones.isEmpty()) {

                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            sql.append(" ORDER BY s.sale_date DESC ");
            sql.append(" LIMIT ? OFFSET ? ");

            logger.debug("SQL: {}", sql);

            ps = c.prepareStatement(
                    sql.toString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            int i = 1;

            for (Object param : parametros) {

                ps.setObject(i++, param);
            }

            ps.setInt(i++, pageSize);
            ps.setInt(i++, from - 1);

            rs = ps.executeQuery();

            List<VentaDTO> page = new ArrayList<>();

            while (rs.next()) {

                page.add(loadNext(rs));
            }

            results.setPage(page);

            StringBuilder countSql = new StringBuilder();

            countSql.append(" SELECT COUNT(*) ");
            countSql.append(" FROM sale s ");

            if (!condiciones.isEmpty()) {

                countSql.append(" WHERE ");
                countSql.append(String.join(" AND ", condiciones));
            }

            PreparedStatement psCount =
                    c.prepareStatement(countSql.toString());

            int idx = 1;

            for (Object param : parametros) {

                psCount.setObject(idx++, param);
            }

            ResultSet rsCount = psCount.executeQuery();

            if (rsCount.next()) {

                results.setTotal(rsCount.getInt(1));
            }

            return results;

        } catch (Exception e) {

            logger.error("Error en findByCriteria: {}", cr, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return results;
    }

    public Venta create(Venta v) {

        logger.debug("Creando venta: {}", v);

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append(" INSERT INTO sale ");
            sql.append(" (sale_date, price_client, price_final, ");
            sql.append(" buyer_client_id, seller_client_id, ");
            sql.append(" employee_id, car_id) ");

            sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?) ");

            logger.debug("SQL: {}", sql);

            ps = c.prepareStatement(
                    sql.toString(),
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            int i = 1;

            ps.setObject(i++, v.getFechaVenta());
            ps.setObject(i++, v.getPrecioCliente());
            ps.setObject(i++, v.getPrecioFinal());
            ps.setObject(i++, v.getClienteCompradorId());
            ps.setObject(i++, v.getClienteVendedorId());
            ps.setObject(i++, v.getEmpleadoId());
            ps.setObject(i++, v.getCocheId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {

                v.setId(rs.getLong(1));

                logger.info("Venta creada con id: {}", v.getId());

                return v;
            }

        } catch (Exception e) {

            logger.error("Error creando venta: {}", v, e);

        } finally {

            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    public boolean update(Venta v) {

        logger.debug("Actualizando venta: {}", v);

        Connection c = null;
        PreparedStatement ps = null;

        try {

            c = JDBCUtils.getConnection();

            StringBuilder sql = new StringBuilder();

            sql.append(" UPDATE sale SET ");

            sql.append(" sale_date = ?, ");
            sql.append(" price_client = ?, ");
            sql.append(" price_final = ?, ");
            sql.append(" buyer_client_id = ?, ");
            sql.append(" seller_client_id = ?, ");
            sql.append(" employee_id = ?, ");
            sql.append(" car_id = ? ");

            sql.append(" WHERE id = ? ");

            logger.debug("SQL: {}", sql);

            ps = c.prepareStatement(sql.toString());

            int i = 1;

            ps.setObject(i++, v.getFechaVenta());
            ps.setObject(i++, v.getPrecioCliente());
            ps.setObject(i++, v.getPrecioFinal());
            ps.setObject(i++, v.getClienteCompradorId());
            ps.setObject(i++, v.getClienteVendedorId());
            ps.setObject(i++, v.getEmpleadoId());
            ps.setObject(i++, v.getCocheId());
            ps.setObject(i++, v.getId());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {

            logger.error("Error actualizando venta: {}", v, e);

        } finally {

            DAOUtils.close(null, ps, c);
        }

        return false;
    }

    public boolean delete(Long id) {

        logger.warn("Eliminando venta id: {}", id);

        Connection c = null;
        PreparedStatement ps = null;

        try {

            c = JDBCUtils.getConnection();

            String sql = " DELETE FROM sale WHERE id = ? ";

            ps = c.prepareStatement(sql);

            ps.setLong(1, id);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {

            logger.error("Error eliminando venta id: {}", id, e);

        } finally {

            DAOUtils.close(null, ps, c);
        }

        return false;
    }

    private VentaDTO loadNext(ResultSet rs) throws Exception {

    	int i = 1;
    	VentaDTO v = new VentaDTO();

    	v.setId(rs.getLong(i++));
    	v.setFechaVenta(rs.getDate(i++).toLocalDate());
    	v.setPrecioCliente(rs.getDouble(i++));
    	v.setPrecioFinal(rs.getDouble(i++));
    	v.setClienteCompradorId(rs.getLong(i++));
    	v.setClienteCompradorNombre(rs.getString(i++));
    	v.setClienteVendedorId(rs.getLong(i++));
    	v.setClienteVendedorNombre(rs.getString(i++));
    	v.setEmpleadoId(rs.getLong(i++));
    	v.setEmpleadoNombre(rs.getString(i++));
    	v.setCocheId(rs.getLong(i++));
    	v.setMatriculaCoche(rs.getString(i++));

    	return v;
    }
}