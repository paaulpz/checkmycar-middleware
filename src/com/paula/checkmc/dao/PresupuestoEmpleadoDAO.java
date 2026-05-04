package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.Empleado;
import com.paula.checkmc.model.PresupuestoEmpleado;
import com.paula.checkmc.util.JDBCUtils;

public class PresupuestoEmpleadoDAO {

    public boolean create(PresupuestoEmpleado pe) {

        String sql = "INSERT INTO employee_has_quotation(employee_id, quotation_id) VALUES(?,?)";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, pe.getEmpleadoId());
            ps.setLong(2, pe.getPresupuestoId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Long empleadoId, Long presupuestoId) {

        String sql = "DELETE FROM employee_has_quotation WHERE employee_id=? AND quotation_id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, empleadoId);
            ps.setLong(2, presupuestoId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Empleado> findPresupuestosByEmpleado(Long empleadoId) {

        List<Empleado> lista = new ArrayList<>();

        String sql = "SELECT quotation_id FROM employee_has_quotation WHERE employee_id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, empleadoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Empleado> findEmpleadosByPresupuesto(Long presupuestoId) {

        List<Empleado> lista = new ArrayList<>();

        String sql = "SELECT employee_id FROM employee_has_quotation WHERE quotation_id=?";

        try (Connection c = JDBCUtils.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, presupuestoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    
    private Empleado loadNext(ResultSet rs) throws Exception {

        int i = 1;
      Empleado e = new Empleado();

        e.setId(rs.getLong(i++));
        e.setNombre(rs.getString(i++));
    
        return e;
    }
}