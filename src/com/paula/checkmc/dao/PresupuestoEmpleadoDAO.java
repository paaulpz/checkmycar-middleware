package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.PresupuestoEmpleado;
import com.paula.checkmc.util.JDBCUtils;

public class PresupuestoEmpleadoDAO {

    private static final Logger logger = LogManager.getLogger(PresupuestoEmpleadoDAO.class);

    public boolean create(Connection c ,PresupuestoEmpleado pe) {

        PreparedStatement ps = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO employee_has_quotation ");
            sql.append("(employee_id, quotation_id) ");
            sql.append("VALUES (?, ?)");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, pe.getEmpleadoId());
            ps.setLong(2, pe.getPresupuestoId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error creando relacion empleado presupuesto", e);

        } finally {

            JDBCUtils.close(null, ps);
        }

        return false;
    }

    public boolean delete(Connection c ,Long empleadoId, Long presupuestoId) {

        PreparedStatement ps = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM employee_has_quotation ");
            sql.append("WHERE employee_id=? ");
            sql.append("AND quotation_id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, empleadoId);
            ps.setLong(2, presupuestoId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            logger.error("Error eliminando relacion empleado presupuesto", e);

        } finally {

            JDBCUtils.close(null, ps);
        }

        return false;
    }

    public List<Empleado> findPresupuestosByEmpleado(Connection c ,Long empleadoId) {

        List<Empleado> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT employee_id ");
            sql.append("FROM employee_has_quotation ");
            sql.append("WHERE employee_id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, empleadoId);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error buscando presupuestos por empleado: {}", empleadoId, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    public List<Empleado> findEmpleadosByPresupuesto(Connection c ,Long presupuestoId) {

        List<Empleado> lista = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {


            StringBuilder sql = new StringBuilder();

            sql.append("SELECT employee_id ");
            sql.append("FROM employee_has_quotation ");
            sql.append("WHERE quotation_id=?");

            ps = c.prepareStatement(sql.toString());

            ps.setLong(1, presupuestoId);

            rs = ps.executeQuery();

            while (rs.next()) {

                lista.add(loadNext(rs));
            }

        } catch (Exception e) {

            logger.error("Error buscando empleados por presupuesto: {}", presupuestoId, e);

        } finally {

            JDBCUtils.close(rs, ps);
        }

        return lista;
    }

    private Empleado loadNext(ResultSet rs) throws Exception {

        Empleado e = new Empleado();

        e.setId(rs.getLong(1));

        return e;
    }
}