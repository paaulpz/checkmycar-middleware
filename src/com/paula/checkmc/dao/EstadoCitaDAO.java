package com.paula.checkmc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.paula.checkmc.model.EstadoCita;
import com.paula.checkmc.util.JDBCUtils;

public class EstadoCitaDAO {

	public EstadoCita findById( Connection c , Long id) {

	    StringBuilder sql = new StringBuilder();

	    sql.append("SELECT id, name ");
	    sql.append("FROM appointment_status ");
	    sql.append("WHERE id=?");

	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {

	        ps = c.prepareStatement(sql.toString());

	        ps.setLong(1, id);

	        rs = ps.executeQuery();

	        if (rs.next()) {

	            EstadoCita estado = new EstadoCita();

	            estado.setId(rs.getLong("id"));
	            estado.setNombre(rs.getString("name"));

	            return estado;
	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    } finally {

	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        try {
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        try {
	            if (c != null) {
	                c.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return null;
	}

	public List<EstadoCita> findAll(Connection c) {

	    List<EstadoCita> lista = new ArrayList<>();

	    StringBuilder sql = new StringBuilder();

	    sql.append("SELECT id, name ");
	    sql.append("FROM appointment_status ");
	    sql.append("ORDER BY name");

	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {


	        ps = c.prepareStatement(sql.toString());

	        rs = ps.executeQuery();

	        while (rs.next()) {

	            EstadoCita estado = new EstadoCita();

	            estado.setId(rs.getLong("id"));
	            estado.setNombre(rs.getString("name"));

	            lista.add(estado);
	        }

	    } catch (Exception e) {

	        e.printStackTrace();

	    } finally {

	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        try {
	            if (ps != null) {
	                ps.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        try {
	            if (c != null) {
	                c.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return lista;
	}
}