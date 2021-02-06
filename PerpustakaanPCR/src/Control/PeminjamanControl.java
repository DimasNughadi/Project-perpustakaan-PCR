package Control;

import Model.Peminjaman;
import Model.Buku;
import Model.Mahasiswa;
import Model.Connector;
import View.FrmPeminjaman;
import View.FrmUtama;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PeminjamanControl implements ActionListener, MouseListener{
    private Mahasiswa data2;
    private Buku data3;
    private Peminjaman data;
    private FrmPeminjaman frm;
//    private FrmMahasiswa frm2;
//    private FrmBuku frm3;
    
    public PeminjamanControl(Peminjaman data, FrmPeminjaman frm, Mahasiswa data2, Buku data3){
        this.data = data;
        this.data3 = data3;
        this.data2 = data2;
        this.frm = frm;
        this.frm.jButton1.addActionListener(this);
        this.frm.jButton2.addActionListener(this);
        this.frm.jButton3.addActionListener(this);
        this.frm.jButton4.addActionListener(this);
        this.frm.jButton5.addActionListener(this);
        this.frm.jButton6.addActionListener(this);
        this.frm.jTable1.addMouseListener(this);
        this.frm.jTable2.addMouseListener(this);
        this.frm.jTable3.addMouseListener(this);
    }
    
    public void KosongkanFormPeminjaman(){
        frm.jTextField1.setEditable(true);
        frm.jTextField1.setText(null);
        frm.jTextField2.setText(null);
        frm.jTextField3.setText(null);
        frm.jTextField4.setText(null);
        frm.jTextField5.setText(null);
        frm.jTextField6.setText(null);
        frm.jDateChooser1.setDate(null);
        frm.jDateChooser2.setDate(null);
    }
    
    public void TampilDataPeminjaman(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NIM");
        model.addColumn("NAMA");
        model.addColumn("PRODI");
        model.addColumn("ID BUKU");
        model.addColumn("JUDUL");
        model.addColumn("TANGGAL PINJAM");
        model.addColumn("TANGGAL KEMBALI");
        model.addColumn("STATUS");
        
        try{
            String sql = "SELECT * FROM peminjaman";
            java.sql.Connection conn = Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6),
                res.getString(7),
                res.getString(8),
                res.getString(9)});
            }
            frm.jTable3.setModel(model);
            
            
        }
        catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    public void TampilDataBuku(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("JUDUL");
        model.addColumn("PENULIS");
        model.addColumn("PENERBIT");
        model.addColumn("TAHUN TERBIT");
        model.addColumn("STOK");
        
        try{
            String sql = "SELECT * FROM buku";
            java.sql.Connection conn = Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6)});
            }
            frm.jTable2.setModel(model);
            
            
        }
        catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    public void TampilDataMahasiswa(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIM");
        model.addColumn("NAMA");
        model.addColumn("PRODI");
        model.addColumn("ANGKATAN");
        
        try{
            String sql = "SELECT * FROM mahasiswa";
            java.sql.Connection conn = Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4)});
            }
            frm.jTable1.setModel(model);
            
            
        }
        catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.jButton1){
            KosongkanFormPeminjaman();
            
        }
        else if(ae.getSource()==frm.jButton2){
            KosongkanFormPeminjaman();
            
        }
        else if(ae.getSource()==frm.jButton3){
            data.setId_P(frm.jTextField1.getText());
            data2.setNim(frm.jTextField2.getText());
            data2.setNama(frm.jTextField3.getText());
            data2.setProdi(frm.jTextField4.getText());
            data3.setId(frm.jTextField5.getText());
            data3.setJudul(frm.jTextField6.getText());
            data.setTgl_P(frm.tglpinjam);
            data.setTgl_K(frm.tglkembali);
            try{
                if(data.SimpanPeminjaman(data, data2, data3)){
                    JOptionPane.showMessageDialog(null, "Simpan data Berhasil");
                    KosongkanFormPeminjaman();
                    TampilDataBuku();
                    TampilDataMahasiswa();
                    TampilDataPeminjaman();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if(ae.getSource()==frm.jButton4){
            data.setId_P(frm.jTextField1.getText());
            data2.setNim(frm.jTextField2.getText());
            data2.setNama(frm.jTextField3.getText());
            data2.setProdi(frm.jTextField4.getText());
            data3.setId(frm.jTextField5.getText());
            data3.setJudul(frm.jTextField6.getText());
            data.setTgl_P(frm.tglpinjam);
            data.setTgl_K(frm.tglkembali);
            try{
                if(data.UpdatePeminjaman(data, data2, data3)){
                    JOptionPane.showMessageDialog(null, "Update data Berhasil");
                    KosongkanFormPeminjaman();
                    TampilDataBuku();
                    TampilDataMahasiswa();
                    TampilDataPeminjaman();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if (ae.getSource()==frm.jButton5){
            data.setId_P(frm.jTextField1.getText());
            try{
                if(data.HapusPeminjaman(data, data2, data3)){
                    JOptionPane.showMessageDialog(null, "Hapus data Berhasil");
                    KosongkanFormPeminjaman();
                    TampilDataBuku();
                    TampilDataMahasiswa();
                    TampilDataPeminjaman();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if (ae.getSource()==frm.jButton6){
            FrmUtama frm = new FrmUtama();
            UtamaControl ctrl = new UtamaControl(frm);
            frm.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getSource()==frm.jTable3){
            frm.jTextField1.setEditable(false);
            
            int baris = frm.jTable3.rowAtPoint(me.getPoint());
            String id = frm.jTable3.getValueAt(baris, 0).toString();
            frm.jTextField1.setText(id);
            String nim = frm.jTable3.getValueAt(baris, 1).toString();
            frm.jTextField2.setText(nim);
            String nama = frm.jTable3.getValueAt(baris, 2).toString();
            frm.jTextField3.setText(nama);
            String prodi = frm.jTable3.getValueAt(baris, 3).toString();
            frm.jTextField4.setText(prodi);
            String id_buku = frm.jTable3.getValueAt(baris, 4).toString();
            frm.jTextField5.setText(id_buku);
            String judul = frm.jTable3.getValueAt(baris, 5).toString();
            frm.jTextField6.setText(judul);
            try {
                Date tgl_p = new SimpleDateFormat("yyyy-MM-dd").parse((String)frm.jTable3.getValueAt(baris, 6));
                frm.jDateChooser1.setDate(tgl_p);
            }
            catch (ParseException ex) {
                Logger.getLogger(PeminjamanControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Date tgl_k = new SimpleDateFormat("yyyy-MM-dd").parse((String)frm.jTable3.getValueAt(baris, 7));
                frm.jDateChooser2.setDate(tgl_k);
            }
            catch (ParseException ex) {
                Logger.getLogger(PeminjamanControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(me.getSource()==frm.jTable1){
            
            int baris = frm.jTable1.rowAtPoint(me.getPoint());
            
            String nim = frm.jTable1.getValueAt(baris, 0).toString();
            frm.jTextField2.setText(nim);
            String nama = frm.jTable1.getValueAt(baris, 1).toString();
            frm.jTextField3.setText(nama);
            String prodi = frm.jTable1.getValueAt(baris, 2).toString();
            frm.jTextField4.setText(prodi);
        }
        else if(me.getSource()==frm.jTable2){
            
            int baris = frm.jTable2.rowAtPoint(me.getPoint());
            
            String id_buku = frm.jTable2.getValueAt(baris, 0).toString();
            frm.jTextField5.setText(id_buku);
            String judul = frm.jTable2.getValueAt(baris, 1).toString();
            frm.jTextField6.setText(judul);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
