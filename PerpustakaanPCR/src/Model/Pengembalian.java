package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Peminjaman;

public class Pengembalian extends Connector{
    private String id_pengembalian;
    private String tgl_pengembalian;
    private String telat;
    private String denda;
    
    public void setId(String id_pengembalian){
        this.id_pengembalian = id_pengembalian;
    }
    public String getId(){
        return id_pengembalian;
    }
    
    public void setTgl_K(String tgl_pengembalian){
        this.tgl_pengembalian = tgl_pengembalian;
    }
    public String getTgl_K(){
        return tgl_pengembalian;
    }
    
    public void setTelat(String telat){
        this.telat = telat;
    }
    public String getTelat(){
        return telat;
    }
    
    public void setDenda(String denda){
        this.denda = denda;
    }
    public String getDenda(){
        return denda;
    }
    
    public boolean SimpanPengembalian(Pengembalian data, Peminjaman data2) throws SQLException{
        PreparedStatement pstm = null;
        PreparedStatement pstm2 = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO pengembalian(id_pengembalian, id_peminjaman, tgl_pengembalian, telat, denda)"
                + "VALUES (?,?,?,?,?)";
        String sql2 = "UPDATE peminjaman set status = 'sudah' "
                    + "WHERE id_peminjaman=?";
        
        try{            
            pstm = conn.prepareStatement(sql);
            pstm2 = conn.prepareStatement(sql2);
            pstm.setString(1, data.getId());
            pstm.setString(2, data2.getId_P());
            pstm.setString(3, data.getTgl_K());
            pstm.setString(4, data.getTelat());
            pstm.setString(5, data.getDenda());
            pstm2.setString(1, data2.getId_P());
            pstm.execute();
            pstm2.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean UpdatePengembalian(Pengembalian data, Peminjaman data2) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "UPDATE pengembalian SET id_pengembalian=?, id_Peminjaman=?, tgl_pengembalian=?, telat=?, denda=?"
                + "WHERE id_pengembalian=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, data2.getId_P());
            pstm.setString(3, data.getTgl_K());
            pstm.setString(4, data.getTelat());
            pstm.setString(5, data.getDenda());
            pstm.setString(6, data.getId());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean HapusPengembalian(Pengembalian data, Peminjaman data2) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM pengembalian WHERE id_pengembalian=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
}
