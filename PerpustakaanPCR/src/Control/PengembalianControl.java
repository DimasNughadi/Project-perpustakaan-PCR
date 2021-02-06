package Control;

import Model.Pengembalian;
import Model.Peminjaman;
import Model.Connector;
import View.FrmPengembalian;
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

public class PengembalianControl implements ActionListener, MouseListener{;
    private Peminjaman data2;
    private Pengembalian data;
    private FrmPengembalian frm;
    
    public PengembalianControl(Pengembalian data, FrmPengembalian frm, Peminjaman data2){
        this.data = data;
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
    }
    
    public void KosongkanFormPengembalian(){
        frm.jTextField1.setEditable(true);
        frm.jTextField1.setText(null);
        frm.jTextField2.setText(null);
        frm.jDateChooser1.setDate(null);
        frm.jTextField3.setText(null);
        frm.jTextField4.setText(null);
    }
    
    public void TampilDataPengembalian(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID PENGEMBALIAN");
        model.addColumn("ID PEMINJAMAN");
        model.addColumn("TANGGAL PENGEBALIAN");
        model.addColumn("TERLAMBAT");
        model.addColumn("DENDA");
        
        try{
            String sql = "SELECT * FROM pengembalian";
            java.sql.Connection conn = Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while (res.next()){
                model.addRow(new Object[]{
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5)});
            }
            frm.jTable2.setModel(model);
            
            
        }
        catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
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
            String sql = "SELECT * FROM peminjaman where status='belum'";
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
            frm.jTable1.setModel(model);
            
            
        }
        catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.jButton1){
            KosongkanFormPengembalian();
            
        }
        else if(ae.getSource()==frm.jButton2){
            KosongkanFormPengembalian();
            
        }
        else if(ae.getSource()==frm.jButton3){
            data.setId(frm.jTextField1.getText());
            data2.setId_P(frm.jTextField1.getText());
            data.setTgl_K(frm.tglpengembalian);
            data.setTelat(frm.jTextField3.getText());
            data.setDenda(frm.jTextField4.getText());
            try{
                if(data.SimpanPengembalian(data, data2)){
                    JOptionPane.showMessageDialog(null, "Simpan data Berhasil");
                    KosongkanFormPengembalian();
                    TampilDataPengembalian();
                    TampilDataPeminjaman();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if(ae.getSource()==frm.jButton4){
            data.setId(frm.jTextField1.getText());
            data2.setId_P(frm.jTextField1.getText());
            data2.setTgl_K(frm.tglpengembalian);
            data.setTelat(frm.jTextField3.getText());
            data.setDenda(frm.jTextField4.getText());
            try{
                if(data.UpdatePengembalian(data, data2)){
                    JOptionPane.showMessageDialog(null, "Update data Berhasil");
                    KosongkanFormPengembalian();
                    TampilDataPengembalian();
                    TampilDataPeminjaman();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if (ae.getSource()==frm.jButton5){
            data.setId(frm.jTextField1.getText());
            try{
                if(data.HapusPengembalian(data, data2)){
                    JOptionPane.showMessageDialog(null, "Hapus data Berhasil");
                    KosongkanFormPengembalian();
                    TampilDataPengembalian();
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
        if(me.getSource()==frm.jTable2){
            frm.jTextField1.setEditable(false);
            
            int baris = frm.jTable2.rowAtPoint(me.getPoint());
            String id = frm.jTable2.getValueAt(baris, 0).toString();
            frm.jTextField1.setText(id);
            String id_p = frm.jTable2.getValueAt(baris, 1).toString();
            frm.jTextField2.setText(id_p);
            try {
                Date tgl_k = new SimpleDateFormat("yyyy-MM-dd").parse((String)frm.jTable2.getValueAt(baris, 2));
                frm.jDateChooser1.setDate(tgl_k);
            }
            catch (ParseException ex) {
                Logger.getLogger(PeminjamanControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            String telat = frm.jTable2.getValueAt(baris, 3).toString();
            frm.jTextField3.setText(telat);
            String denda = frm.jTable2.getValueAt(baris, 4).toString();
            frm.jTextField4.setText(denda);
            
        }
        else if(me.getSource()==frm.jTable1){
            
            int baris = frm.jTable1.rowAtPoint(me.getPoint());
            
            String id_p = frm.jTable1.getValueAt(baris, 0).toString();
            frm.jTextField2.setText(id_p);
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
