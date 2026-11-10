package cesur.dam.inventarioappg1.dao;

import cesur.dam.inventarioappg1.db.Conexion;
import cesur.dam.inventarioappg1.models.Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/** Acceso a la tabla "inventario". Los controladores solo hablan con esta clase, nunca con SQL. */
public class InventarioDAO {

    /** Devuelve todas las filas de la tabla. */
    public List<Inventario> findAll() throws SQLException {
        String sql = "SELECT id, nombre, cantidad, comentario, fecha FROM inventario ORDER BY id";
        List<Inventario> lista = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventario i = new Inventario();
                i.setId(rs.getInt("id"));
                i.setNombre(rs.getString("nombre"));
                i.setCantidad(rs.getInt("cantidad"));
                i.setComentario(rs.getString("comentario"));
                Timestamp ts = rs.getTimestamp("fecha");
                i.setFecha(ts != null ? ts.toLocalDateTime() : null);
                lista.add(i);
            }
        }
        return lista;
    }

    /** Inserta una fila nueva. El id y la fecha los pone MySQL solo. */
    public void insert(Inventario i) throws SQLException {
        String sql = "INSERT INTO inventario (nombre, cantidad, comentario) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, i.getNombre());
            ps.setInt(2, i.getCantidad());
            ps.setString(3, i.getComentario());
            ps.executeUpdate();
        }
    }
}
