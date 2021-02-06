package Control;

import Model.Connector;
import Model.Mahasiswa;
import View.FrmMahasiswa;
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

public class MahasiswaControl implements ActionListener, MouseListener{
    private Mahasiswa data;
    private FrmMahasiswa frm;
    
    public MahasiswaControl(Mahasiswa data, FrmMahasiswa frm){
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
    
    public void KosongkanFormMahasiswa(){
        frm.jTextField1.setEditable(true);
        frm.jTextField1.setText(null);
        frm.jTextField2.setText(null);
        frm.jTextField3.setText(null);
        frm.jTextField4.setText(null);
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
            KosongkanFormMahasiswa();
            
        }
        else if(ae.getSource()==frm.jButton2){
            KosongkanFormMahasiswa();
            
        }
        else if(ae.getSource()==frm.jButton3){
            data.setNim(frm.jTextField1.getText());
            data.setNama(frm.jTextField2.getText());
            data.setProdi(frm.jTextField3.getText());
            data.setGen(frm.jTextField4.getText());
            try{
                if(data.SimpanMahasiswa(data)){
                    JOptionPane.showMessageDialog(null, "Simpan data Berhasil");
                    KosongkanFormMahasiswa();
                    TampilDataMahasiswa();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if(ae.getSource()==frm.jButton4){
            data.setNim(frm.jTextField1.getText());
            data.setNama(frm.jTextField2.getText());
            data.setProdi(frm.jTextField3.getText());
            data.setGen(frm.jTextField4.getText());
            try{
                if(data.UpdateMahasiswa(data)){
                    JOptionPane.showMessageDialog(null, "Update data Berhasil");
                    KosongkanFormMahasiswa();
                    TampilDataMahasiswa();
                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
        else if (ae.getSource()==frm.jButton5){
            data.setNim(frm.jTextField1.getText());
            try{
                if(data.HapusMahasiswa(data)){
                    JOptionPane.showMessageDialog(null, "Hapus data Berhasil");
                    KosongkanFormMahasiswa();
                    TampilDataMahasiswa();
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
            String nim = frm.jTable1.getValueAt(baris, 0).toString();
            frm.jTextField1.setText(nim);
            String nama = frm.jTable1.getValueAt(baris, 1).toString();
            frm.jTextField2.setText(nama);
            String prodi = frm.jTable1.getValueAt(baris, 2).toString();
            frm.jTextField3.setText(prodi);
            String generasi = frm.jTable1.getValueAt(baris, 3).toString();
            frm.jTextField4.setText(generasi);
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
