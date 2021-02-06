package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Buku extends Connector{
    private String id_buku;
    private String judul;
    private String penulis;
    private String penerbit;
    private String thn_terbit;
    private String jml_buku;
    
    public void setId(String id_buku){
        this.id_buku = id_buku;
    }
    public String getId(){
        return id_buku;
    }
    
    public void setJudul(String judul){
        this.judul = judul;
    }
    public String getJudul(){
        return judul;
    }
    
    public void setPenulis(String penulis){
        this.penulis = penulis;
    }
    public String getPenulis(){
        return penulis;
    }
    
    public void setPenerbit(String penerbit){
        this.penerbit = penerbit;
    }
    public String getPenerbit(){
        return penerbit;
    }
    
    public void setThn(String thn_terbit){
        this.thn_terbit = thn_terbit;
    }
    public String getThn(){
        return thn_terbit;
    }
    
    public void setJml(String jml_buku){
        this.jml_buku = jml_buku;
    }
    public String getJml(){
        return jml_buku;
    }
    
    public boolean SimpanBuku(Buku data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO buku (id_buku, judul_buku, penulis, penerbit, thn_terbit, jml_buku)"
                + "VALUES (?,?,?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, data.getJudul());
            pstm.setString(3, data.getPenulis());
            pstm.setString(4, data.getPenerbit());
            pstm.setString(5, data.getThn());
            pstm.setString(6, data.getJml());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean UpdateBuku(Buku data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "UPDATE buku SET id_buku=?, judul_buku=?, penulis=?, penerbit=?, thn_terbit=?, jml_buku=?"
                + "WHERE id_buku=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, data.getJudul());
            pstm.setString(3, data.getPenulis());
            pstm.setString(4, data.getPenerbit());
            pstm.setString(5, data.getThn());
            pstm.setString(6, data.getJml());
            pstm.setString(7, data.getId());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean HapusBuku(Buku data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM buku WHERE id_buku=?";
        
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
