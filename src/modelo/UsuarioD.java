/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CristianCamiloAlvare
 */
public class UsuarioD {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int delete(int id) {
        String sql = "delete from usuarios where id =" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public int add(Usuario u) {
        String sql = "insert into usuarios (Nombre,Correo,Telefono,Cargo) values(?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getMail());
            ps.setString(3, u.getTel());
            ps.setString(4, u.getRol());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public List list() {
        List<Usuario> datos = new ArrayList<>();
        String sql = "select * from usuarios";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setMail(rs.getString(3));
                u.setTel(rs.getString(4));
                u.setRol(rs.getString(5));
                datos.add(u);
            }
        } catch (Exception e) {
        }
        return datos;
    }

    public int update(Usuario user) {
        int r = 0;
        String sql = "update usuarios set Nombre=?,Correo=?,Telefono=?,Cargo=? where Id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getMail());
            ps.setString(3, user.getTel());
            ps.setString(4, user.getRol());
            ps.setInt(5, user.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
        }
        return r;
    }
}
