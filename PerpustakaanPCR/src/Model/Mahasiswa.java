package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Mahasiswa extends Connector{
    private String nim;
    private String nama;
    private String prodi;
    private String generasi;
    
    public void setNim(String nim){
        this.nim = nim;
    }
    public String getNim(){
        return nim;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getNama(){
        return nama;
    }
    
    public void setProdi(String prodi){
        this.prodi = prodi;
    }
    public String getProdi(){
        return prodi;
    }
    
    public void setGen(String generasi){
        this.generasi = generasi;
    }
    public String getGen(){
        return generasi;
    }
    
    public boolean SimpanMahasiswa(Mahasiswa data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO mahasiswa (nim, nama, prodi, generasi)"
                + "VALUES (?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getNim());
            pstm.setString(2, data.getNama());
            pstm.setString(3, data.getProdi());
            pstm.setString(4, data.getGen());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean UpdateMahasiswa(Mahasiswa data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "UPDATE mahasiswa SET nim=?, nama=?, prodi=?, generasi=?"
                + "WHERE nim=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getNim());
            pstm.setString(2, data.getNama());
            pstm.setString(3, data.getProdi());
            pstm.setString(4, data.getGen());
            pstm.setString(5, data.getNim());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean HapusMahasiswa(Mahasiswa data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM mahasiswa WHERE nim=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getNim());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
}
