package Control;

import Control.BukuControl;
import Control.MahasiswaControl;
import Control.PeminjamanControl;
import Control.PengembalianControl;
import Model.Buku;
import Model.Mahasiswa;
import Model.Peminjaman;
import Model.Pengembalian;
import View.FrmBuku;
import View.FrmMahasiswa;
import View.FrmPeminjaman;
import View.FrmPengembalian;
import View.FrmUtama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UtamaControl implements ActionListener{
    private FrmUtama frm;
//    private FrmMahasiswa frm2;
//    private FrmBuku frm3;
    
    public UtamaControl(FrmUtama frm){
        this.frm = frm;
        this.frm.jButton1.addActionListener(this);
        this.frm.jButton2.addActionListener(this);
        this.frm.jButton3.addActionListener(this);
        this.frm.jButton4.addActionListener(this);
        this.frm.jButton5.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.jButton1){
            Buku data = new Buku();
            FrmBuku frm = new FrmBuku();
            BukuControl ctrl = new BukuControl(data, frm);
        
            ctrl.KosongkanFormBuku();
            ctrl.TampilDataBuku();
            frm.setVisible(true);
        }
        else if(ae.getSource()==frm.jButton2){
            Mahasiswa data = new Mahasiswa();
            FrmMahasiswa frm = new FrmMahasiswa();
            MahasiswaControl ctrl = new MahasiswaControl(data, frm);
        
            ctrl.KosongkanFormMahasiswa();
            ctrl.TampilDataMahasiswa();
            frm.setVisible(true);
            
        }
        else if(ae.getSource()==frm.jButton3){
            Mahasiswa data2 = new Mahasiswa();
            Buku data3 = new Buku();
            Peminjaman data = new Peminjaman();
            FrmPeminjaman frm = new FrmPeminjaman();
            PeminjamanControl ctrl = new PeminjamanControl(data, frm, data2, data3);
        
            ctrl.KosongkanFormPeminjaman();
            ctrl.TampilDataPeminjaman();
            ctrl.TampilDataBuku();
            ctrl.TampilDataMahasiswa();
            frm.setVisible(true);
        }
        else if(ae.getSource()==frm.jButton4){
            Pengembalian data = new Pengembalian();
            Peminjaman data2 = new Peminjaman();
            FrmPengembalian frm = new FrmPengembalian();
            PengembalianControl ctrl = new PengembalianControl(data, frm, data2);
        
            ctrl.KosongkanFormPengembalian();
            ctrl.TampilDataPengembalian();
            ctrl.TampilDataPeminjaman();
            frm.setVisible(true);
        }
        else if (ae.getSource()==frm.jButton5){
            System.exit(0);
        }
    }
    
}
