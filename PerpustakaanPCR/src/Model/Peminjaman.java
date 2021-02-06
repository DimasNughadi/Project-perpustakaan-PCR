package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Mahasiswa;
import Model.Buku;

public class Peminjaman extends Connector{
    private String id_peminjaman;
    private String tgl_pinjam;
    private String tgl_kembali;
    private String StatusP;
//    private String nim;
//    private String nama;
//    private String prodi;
//    private String id_buku;
//    private String judul;
    
    public void setId_P(String id_peminjaman){
        this.id_peminjaman = id_peminjaman;
    }
    public String getId_P(){
        return id_peminjaman;
    }
    
    public void setTgl_P(String tgl_pinjam){
        this.tgl_pinjam = tgl_pinjam;
    }
    public String getTgl_P(){
        return tgl_pinjam;
    }
    
    public void setTgl_K(String tgl_kembali){
        this.tgl_kembali = tgl_kembali;
    }
    public String getTgl_K(){
        return tgl_kembali;
    }
    
    public void setStatusP(String StatusP){
        this.StatusP = StatusP;
    }
    public String getStatusP(){
        return StatusP;
    }
//    public void setNim(String nim){
//        this.nim = nim;
//    }
//    public String getNim(){
//        return nim;
//    }
//    
//    public void setNama(String nama){
//        this.nama = nama;
//    }
//    public String getNama(){
//        return nama;
//    }
//    
//    public void setProdi(String prodi){
//        this.prodi = prodi;
//    }
//    public String getProdi(){
//        return prodi;
//    }
//    
//    public void setId(String id_buku){
//        this.id_buku = id_buku;
//    }
//    public String getId(){
//        return id_buku;
//    }
//    
//    public void setJudul(String judul){
//        this.judul = judul;
//    }
//    public String getJudul(){
//        return judul;
//    }
    
    public boolean SimpanPeminjaman(Peminjaman data, Mahasiswa data2, Buku data3) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO peminjaman(id_peminjaman, nim, nama, prodi, id_buku, judul, tgl_pinjam, tgl_kembali, status)"
                + "VALUES (?,?,?,?,?,?,?,?,'belum')";
        
        try{            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId_P());
            pstm.setString(2, data2.getNim());
            pstm.setString(3, data2.getNama());
            pstm.setString(4, data2.getProdi());
            pstm.setString(5, data3.getId());
            pstm.setString(6, data3.getJudul());
            pstm.setString(7, data.getTgl_P());
            pstm.setString(8, data.getTgl_K());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean UpdatePeminjaman(Peminjaman data, Mahasiswa data2, Buku data3) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "UPDATE peminjaman SET id_Peminjaman=?, nim=?, nama=?, prodi=?, id_buku=?, judul=?, tgl_pinjam=?, "
                + "tgl_kembali=?, status='belum'"
                + "WHERE id_peminjaman=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId_P());
            pstm.setString(2, data2.getNim());
            pstm.setString(3, data2.getNama());
            pstm.setString(4, data2.getProdi());
            pstm.setString(5, data3.getId());
            pstm.setString(6, data3.getJudul());
            pstm.setString(7, data.getTgl_P());
            pstm.setString(8, data.getTgl_K());
            pstm.setString(9, data.getId_P());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean HapusPeminjaman(Peminjaman data, Mahasiswa data2, Buku data3) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM peminjaman WHERE id_peminjaman=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId_P());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            return false;
        }
    }
    
}
