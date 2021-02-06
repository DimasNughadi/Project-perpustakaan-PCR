package Control;

import Model.Buku;
import Model.Connector;
import View.FrmBuku;
import View.FrmUtama;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BukuControl implements ActionListener, MouseListener{
    private Buku data;
    private FrmBuku frm;

    public BukuControl(Buku data, FrmBuku frm){
        this.data = data;
        this.frm = frm;
        this.frm.jButton1.addActionListener(this);
        this.frm.jButton2.addActionListener(this);
        this.frm.jButton3.addActionListener(this);
        this.frm.jButton4.addActionListener(this);
        this.frm.jButton5.addActionListener(this);
        this.frm.jButton6.addActionListener(this);
        this.frm.jTable1.addMouseListener(this);
    }
    
    public void KosongkanFormBuku(){
        frm.jTextField1.setEditable(true);
        frm.jTextField1.setText(null);
        frm.jTextField2.setText(null);
        frm.jTextField3.setText(null);
        frm.jTextField4.setText(null);
        frm.jTextField5.setText(null);
        frm.jTextField6.setText(null);
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
            frm.jTable1.setModel(model);
            
            
        }
        catch(SQLException e){
            System.out.println("Error "+e.getMessage());
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.jButton1){
            KosongkanFormBuku();
            
        }
        else if(ae.getSource()==frm.jButton2){
            KosongkanFormBuku();
            
        }
        else if(ae.getSource()==frm.jButton3){
            data.setId(frm.jTextField1.getText());
            data.setJudul(frm.jTextField2.getText());
            data.setPenulis(frm.jTextField3.getText());
            data.setPenerbit(frm.jTextField4.getText());
            data.setThn(frm.jTextField5.getText());
            data.setJml(frm.jTextField6.getText());
            try{
                if(data.SimpanBuku(data)){
                    JOptionPane.showMessageDialog(null, "Simpan data Berhasil");
                    KosongkanFormBuku();
                    TampilDataBuku();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if(ae.getSource()==frm.jButton4){
            data.setId(frm.jTextField1.getText());
            data.setJudul(frm.jTextField2.getText());
            data.setPenulis(frm.jTextField3.getText());
            data.setPenerbit(frm.jTextField4.getText());
            data.setThn(frm.jTextField5.getText());
            data.setJml(frm.jTextField6.getText());
            try{
                if(data.UpdateBuku(data)){
                    JOptionPane.showMessageDialog(null, "Update data Berhasil");
                    KosongkanFormBuku();
                    TampilDataBuku();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if (ae.getSource()==frm.jButton5){
            data.setId(frm.jTextField1.getText());
            try{
                if(data.HapusBuku(data)){
                    JOptionPane.showMessageDialog(null, "Hapus data Berhasil");
                    KosongkanFormBuku();
                    TampilDataBuku();
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
        if(me.getSource()==frm.jTable1){
            frm.jTextField1.setEditable(false);
            
            int baris = frm.jTable1.rowAtPoint(me.getPoint());
            String id = frm.jTable1.getValueAt(baris, 0).toString();
            frm.jTextField1.setText(id);
            String judul = frm.jTable1.getValueAt(baris, 1).toString();
            frm.jTextField2.setText(judul);
            String penulis = frm.jTable1.getValueAt(baris, 2).toString();
            frm.jTextField3.setText(penulis);
            String penerbit = frm.jTable1.getValueAt(baris, 3).toString();
            frm.jTextField4.setText(penerbit);
            String tahun = frm.jTable1.getValueAt(baris, 4).toString();
            frm.jTextField5.setText(tahun);
            String jumlah = frm.jTable1.getValueAt(baris, 5).toString();
            frm.jTextField6.setText(jumlah);
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
