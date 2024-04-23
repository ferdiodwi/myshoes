/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package myshoes;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import com.mysql.cj.jdbc.Driver;
import java.sql.Statement;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.File;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRDesignViewer;
import net.sf.jasperreports.view.JasperViewer;




/**
 *
 * @author Lenovo
 */
public class Home1 extends javax.swing.JFrame {
    private PreparedStatement stat;
    private ResultSet rs;
    private Connection con;
    koneksi k = new koneksi();
    public String tanggal_pengeluaran;
    
    private Statement st;
    private ResultSet RSsuplier;
    private String sql="";
    
    HashMap param = new HashMap();

    
    
    /**
     * Creates new form Home1
     */
    public Home1() {
        initComponents();
        k.connect();
        //auto_id();
        
    }
    
   
    
    
    
    /*private void auto_id(){
        try {
            String sql = "SELECT id_customer as a FROM customer ORDER BY id_customer DESC";
            Connection con = (Connection) k.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                String no_urut = rs.getString("a");
                int a = Integer.parseInt(no_urut);
                int panjang = no_urut.length();
                for (int i = 0; i < 2 - panjang; i++) {
                    a = a;
                    
                    
                }
                txt_id_customer.setText("CU" + Integer.toString(a + 1));
                txt_id_bantu.setText(Integer.toString(a + 1));
            }else {
                txt_id_customer.setText("CU001");
                txt_id_bantu.setText("001");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah");
        }
    }*/
    
    
    private void auto_id(){
        String idCus = "CU000";
        int i = 0;
        
        try {
            String sql = "SELECT id_customer FROM customer";
            Connection con = (Connection) k.getCon();
             Statement stat = con.createStatement();
             ResultSet res = stat.executeQuery(sql);
             while (rs.next()) {
                idCus = rs.getString("id_customer");   
            }
             idCus = idCus.substring(2);
             i = Integer.parseInt(idCus)+1;
             idCus = "00" +i;
             idCus = "CU" +idCus.substring(idCus.length()-3);
             txt_id_customer.setText(idCus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    void cari_customer(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Customer");
        tbl.addColumn("Nama");
        tbl.addColumn("No Telp");
        table_DataCustomer.setModel(tbl);
        
        try {
            String sql = " SELECT * FROM customer WHERE id_customer like '%" + txt_cari.getText() + "%'";
             Connection con = (Connection) k.getCon();
             Statement stat = con.createStatement();
             ResultSet res = stat.executeQuery(sql);
             
             while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_customer"),
                res.getString("nama_customer"),
                res.getString("notelp")
            });
            table_DataCustomer.setModel(tbl);
                
            }
        } catch (Exception e) {
        }
    }
    
    void cari_karyawan(){
    DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id_Karyawan");
        tbl.addColumn("Nama Karyawan");
        tbl.addColumn("Jabatan");
        tbl.addColumn("Gaji");
        tbl.addColumn("NO telp");
        table_Karyawan.setModel(tbl);
        
        try {
            String sql = " SELECT * FROM karyawan WHERE id_karyawan like '%" + txt_cari_karyawan.getText() + "%'";
             Connection con = (Connection) k.getCon();
             Statement stat = con.createStatement();
             ResultSet res = stat.executeQuery(sql);
             
             while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_karyawan"),
                res.getString("nama_karyawan"),
                res.getString("jabatan"),
                res.getInt("gaji"),
                res.getString("notelp")
            });
            table_Karyawan.setModel(tbl);
                
            }
        } catch (Exception e) {
        }
    }
    
    
    
    
    void cari_pengeluaran(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Pengeluaran");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Harga");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Tanggal");
        table_pengeluaran.setModel(tbl);
        
        try {
            String sql = " SELECT * FROM pengeluaran WHERE id_pengeluaran like '%" + txt_cari_pengeluaran.getText() + "%'";
             Connection con = (Connection) k.getCon();
             Statement stat = con.createStatement();
             ResultSet res = stat.executeQuery(sql);
             
             while(res.next())
            {
               tbl.addRow(new Object[]{
                res.getString("id_pengeluaran"),
                res.getString("nama_brng"),
                res.getString("harga_brng"),
                res.getString("jumlah_brng"),
                res.getString("tanggal"),
            });
            table_Karyawan.setModel(tbl);
                
            }
        } catch (Exception e) {
  
        }
    
    }
    
    
    
    /*public void setUpTableData() {
    DefaultTableModel tableModel = (DefaultTableModel) table_struk.getModel();
    ArrayList<Contact> list = new ArrayList<Contact>();
    if (!con.equals(""))
        list = sql.getContactListsByGroup(con);
    else
        list = sql.getContactLists();
    for (int i = 0; i < list.size(); i++) {
        String[] data = new String[7];

            data[0] = list.get(i).getName();
            data[1] = list.get(i).getEmail();
            data[2] = list.get(i).getPhone1();
            data[3] = list.get(i).getPhone2();
            data[4] = list.get(i).getGroup();
            data[5] = list.get(i).getId();

        tableModel.addRow(data);
    }
    table_struk.setModel(tableModel);
}*/
    
    
    
    
    public void datatable_DataCustomer(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Customer");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("No Telp");
        table_DataCustomer.setModel(tbl);
        try {
            Statement statement=(Statement)k.getCon().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM customer");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_customer"),
                res.getString("nama_customer"),
                res.getString("notelp")
            });
            table_DataCustomer.setModel(tbl);
                
            }
            
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
        
    }
    
    
    public void datatable_Karyawan(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Id_Karyawan");
        tbl.addColumn("Nama Karyawan");
        tbl.addColumn("Jabatan");
        tbl.addColumn("Gaji");
        tbl.addColumn("No telp");
        table_Karyawan.setModel(tbl);
        try {
            Statement statement=(Statement)k.getCon().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM karyawan");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_karyawan"),
                res.getString("nama_karyawan"),
                res.getString("jabatan"),
                res.getInt("gaji"),
                res.getString("notelp")
            });
            table_Karyawan.setModel(tbl);
                
            }
            
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
        
    }
    
    
    public void datatable_pengeluaran(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Pengeluaran");
        tbl.addColumn("Nama Barang");
        tbl.addColumn("Harga");
        tbl.addColumn("Jumlah");
        tbl.addColumn("Tanggal");
        table_pengeluaran.setModel(tbl);
        try {
            Statement statement=(Statement)k.getCon().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM pengeluaran");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_pengeluaran"),
                res.getString("nama_brng"),
                res.getString("harga_brng"),
                res.getString("jumlah_brng"),
                res.getString("tanggal"),
            });
            table_pengeluaran.setModel(tbl);
                
            }
            
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
        
    }
    
    
    
    public void datatable_transaksi(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID transaksi");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("sepatu");
        tbl.addColumn("total harga");
        tbl.addColumn("jenis paket");
        tbl.addColumn("harga");
        table_transaksi.setModel(tbl);
        try {
            Statement statement=(Statement)k.getCon().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM detail_transaksi");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_transaksi"),    
                res.getString("nama_customer"),
                res.getString("jenis_paket"),
                res.getString("sepatu"),
                res.getInt("harga"),
                res.getString("total_harga"),
                res.getInt("jumlah_bayar"),
                res.getInt("jumlah_kembalian")
            });
            table_transaksi.setModel(tbl);
                
            }
            
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
        
    }
    
    
    
    
    public void datatable_laporan(){
        
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID transaksi");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("jenis paket");
        tbl.addColumn("sepatu");
        tbl.addColumn("harga");
        table_laporan.setModel(tbl);
        try {
            Statement statement=(Statement)k.getCon().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM detail_transaksi");
            while(res.next())
            {
                tbl.addRow(new Object[]{
                res.getString("id_transaksi"),    
                res.getString("nama_customer"),
                res.getString("jenis_paket"),
                res.getString("sepatu"),
                res.getInt("harga"),
                res.getInt("jumlah_bayar"),
                res.getInt("jumlah_kembalian")
            });
            table_laporan.setModel(tbl);
                
            }
            
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(rootPane, "salah");
        }
        
    }
    
    
    
    
    public void tanggal_pengeluaran(){
        DateFormat tgl = new SimpleDateFormat("dd/MM/yyyy");
        String htgl = tgl.format(Calendar.getInstance().getTime());
        txt_tanggal_pengeluaran.setText(htgl);
    }
    
    
    /*public void tanggal_transaksi(){
        DateFormat tgl = new SimpleDateFormat("dd/MM/yyyy");
        String htgl = tgl.format(Calendar.getInstance().getTime());
        txt_tanggal_transaksi.setText(htgl);
    }*/
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bg = new javax.swing.JPanel();
        sidipane = new javax.swing.JPanel();
        btn_DataCustomer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_transaksi = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_tentang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_pengeluaran = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        btn_Karyawan = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        dashboardpanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        laporanpanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_laporan = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        tentangpanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        Karyawan_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Karyawan = new javax.swing.JTable();
        txt_cari_karyawan = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txt_nama_karyawan = new javax.swing.JTextField();
        txt_jabatan_karyawan = new javax.swing.JTextField();
        txt_gaji_karyawan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_notelp_karyawan = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_id_karyawan = new javax.swing.JTextField();
        btn_simpan_karyawan = new rojerusan.RSMaterialButtonRectangle();
        btn_hapus_karyawan = new rojerusan.RSMaterialButtonRectangle();
        btn_edit_karyawan = new rojerusan.RSMaterialButtonRectangle();
        btn_cari_karyawan = new rojerusan.RSMaterialButtonRectangle();
        pengeluaran_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_pengeluaran = new javax.swing.JTable();
        txt_nama_brng_pengeluaran = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_jumlah_brng_pengeluaran = new javax.swing.JTextField();
        txt_harga_brng_pengeluaran = new javax.swing.JTextField();
        txt_cari_pengeluaran = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_id_pengeluaran = new javax.swing.JTextField();
        txt_tanggal_pengeluaran = new javax.swing.JTextField();
        btn_simpan_pengeluaran1 = new rojerusan.RSMaterialButtonRectangle();
        btn_hapus_pengeluaran1 = new rojerusan.RSMaterialButtonRectangle();
        btn_edit_pengeluaran1 = new rojerusan.RSMaterialButtonRectangle();
        btn_cari_pengeluaran1 = new rojerusan.RSMaterialButtonRectangle();
        DataCustomer_panel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txt_id_bantu = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_nama_customer = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txt_notelp_customer = new javax.swing.JTextField();
        txt_cari = new javax.swing.JTextField();
        txt_id_customer = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_DataCustomer = new javax.swing.JTable();
        btn_simpan_DataCustomer = new rojerusan.RSMaterialButtonRectangle();
        btn_hapus_DataCustomer = new rojerusan.RSMaterialButtonRectangle();
        btn_edit_DataCustomer = new rojerusan.RSMaterialButtonRectangle();
        btn_cari_DataCustomer = new rojerusan.RSMaterialButtonRectangle();
        transaksipanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txt_transaksi = new javax.swing.JTextField();
        txt_nama_customer_transaksi = new javax.swing.JTextField();
        txt_sepatu_transaksi = new javax.swing.JTextField();
        combo_jenispaket = new javax.swing.JComboBox<>();
        txt_harga = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_jumlah_bayar_transaksi = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_jumlah_kembalian_transaksi = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_struk = new javax.swing.JTable();
        txt_totalHarga_transaksi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_transaksi = new javax.swing.JTable();
        save1 = new javax.swing.JButton();
        btn_simpan_transaksi = new rojerusan.RSMaterialButtonRectangle();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sidipane.setBackground(new java.awt.Color(54, 33, 89));

        btn_DataCustomer.setBackground(new java.awt.Color(64, 43, 100));
        btn_DataCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_DataCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DataCustomerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DataCustomerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DataCustomerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_DataCustomerMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_DataCustomerMouseReleased(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_diff_files_15px_1.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Data Customer");

        javax.swing.GroupLayout btn_DataCustomerLayout = new javax.swing.GroupLayout(btn_DataCustomer);
        btn_DataCustomer.setLayout(btn_DataCustomerLayout);
        btn_DataCustomerLayout.setHorizontalGroup(
            btn_DataCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DataCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_DataCustomerLayout.setVerticalGroup(
            btn_DataCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DataCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DataCustomerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(15, 15, 15))
        );

        btn_transaksi.setBackground(new java.awt.Color(64, 43, 100));
        btn_transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_transaksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_transaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_transaksiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_transaksiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_transaksiMouseReleased(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_transaction_15px.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Transaksi");

        javax.swing.GroupLayout btn_transaksiLayout = new javax.swing.GroupLayout(btn_transaksi);
        btn_transaksi.setLayout(btn_transaksiLayout);
        btn_transaksiLayout.setHorizontalGroup(
            btn_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_transaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_transaksiLayout.setVerticalGroup(
            btn_transaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_transaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_transaksiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(15, 15, 15))
        );

        btn_laporan.setBackground(new java.awt.Color(64, 43, 100));
        btn_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_laporanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_laporanMouseReleased(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_graph_report_15px.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Laporan");

        javax.swing.GroupLayout btn_laporanLayout = new javax.swing.GroupLayout(btn_laporan);
        btn_laporan.setLayout(btn_laporanLayout);
        btn_laporanLayout.setHorizontalGroup(
            btn_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_laporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_laporanLayout.setVerticalGroup(
            btn_laporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_laporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_laporanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(15, 15, 15))
        );

        btn_tentang.setBackground(new java.awt.Color(64, 43, 100));
        btn_tentang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tentang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_tentangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tentangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_tentangMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tentangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_tentangMouseReleased(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_about_15px_1.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tentang Kami");

        javax.swing.GroupLayout btn_tentangLayout = new javax.swing.GroupLayout(btn_tentang);
        btn_tentang.setLayout(btn_tentangLayout);
        btn_tentangLayout.setHorizontalGroup(
            btn_tentangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tentangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_tentangLayout.setVerticalGroup(
            btn_tentangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_tentangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_tentangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(15, 15, 15))
        );

        btn_keluar.setBackground(new java.awt.Color(85, 65, 118));
        btn_keluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_keluarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_keluarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_keluarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_keluarMouseReleased(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_export_15px.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Logout");

        javax.swing.GroupLayout btn_keluarLayout = new javax.swing.GroupLayout(btn_keluar);
        btn_keluar.setLayout(btn_keluarLayout);
        btn_keluarLayout.setHorizontalGroup(
            btn_keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_keluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        btn_keluarLayout.setVerticalGroup(
            btn_keluarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_keluarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_keluarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(15, 15, 15))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("MyShoes");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("______________________");

        btn_pengeluaran.setBackground(new java.awt.Color(64, 43, 100));
        btn_pengeluaran.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pengeluaranMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pengeluaranMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pengeluaranMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_pengeluaranMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_pengeluaranMouseReleased(evt);
            }
        });

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_general_ledger_15px.png"))); // NOI18N

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Pengeluaran");

        javax.swing.GroupLayout btn_pengeluaranLayout = new javax.swing.GroupLayout(btn_pengeluaran);
        btn_pengeluaran.setLayout(btn_pengeluaranLayout);
        btn_pengeluaranLayout.setHorizontalGroup(
            btn_pengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_pengeluaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel63)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_pengeluaranLayout.setVerticalGroup(
            btn_pengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_pengeluaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_pengeluaranLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel63)
                .addGap(15, 15, 15))
        );

        btn_Karyawan.setBackground(new java.awt.Color(64, 43, 100));
        btn_Karyawan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_KaryawanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_KaryawanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_KaryawanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_KaryawanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_KaryawanMouseReleased(evt);
            }
        });

        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/icons8_management_15px.png"))); // NOI18N

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Karyawan");

        javax.swing.GroupLayout btn_KaryawanLayout = new javax.swing.GroupLayout(btn_Karyawan);
        btn_Karyawan.setLayout(btn_KaryawanLayout);
        btn_KaryawanLayout.setHorizontalGroup(
            btn_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_KaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel65)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_KaryawanLayout.setVerticalGroup(
            btn_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_KaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_KaryawanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel65)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout sidipaneLayout = new javax.swing.GroupLayout(sidipane);
        sidipane.setLayout(sidipaneLayout);
        sidipaneLayout.setHorizontalGroup(
            sidipaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidipaneLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sidipaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(sidipaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel11))))
            .addComponent(btn_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_DataCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_laporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_pengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_tentang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        sidipaneLayout.setVerticalGroup(
            sidipaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidipaneLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(sidipaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidipaneLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel12))
                    .addComponent(jLabel11))
                .addGap(32, 32, 32)
                .addGroup(sidipaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidipaneLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(btn_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidipaneLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidipaneLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btn_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sidipaneLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btn_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(btn_tentang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainpanel.setBackground(new java.awt.Color(122, 72, 221));
        mainpanel.setLayout(new java.awt.CardLayout());

        dashboardpanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/sepatu2.jpg"))); // NOI18N

        javax.swing.GroupLayout dashboardpanelLayout = new javax.swing.GroupLayout(dashboardpanel);
        dashboardpanel.setLayout(dashboardpanelLayout);
        dashboardpanelLayout.setHorizontalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardpanelLayout.createSequentialGroup()
                .addGap(0, 101, Short.MAX_VALUE)
                .addComponent(jLabel27))
        );
        dashboardpanelLayout.setVerticalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 523, Short.MAX_VALUE)
        );

        mainpanel.add(dashboardpanel, "card2");

        laporanpanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(122, 72, 221));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Laporan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        table_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_transaksi", "nama", "jenis paket", "sepatu", "harga"
            }
        ));
        table_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_laporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_laporan);

        jButton4.setText("Print Laporan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout laporanpanelLayout = new javax.swing.GroupLayout(laporanpanel);
        laporanpanel.setLayout(laporanpanelLayout);
        laporanpanelLayout.setHorizontalGroup(
            laporanpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(laporanpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(laporanpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, laporanpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        laporanpanelLayout.setVerticalGroup(
            laporanpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laporanpanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        mainpanel.add(laporanpanel, "card4");

        tentangpanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(122, 72, 221));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Tentang Kami");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/1.jpg"))); // NOI18N

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myshoes/images/2.jpg"))); // NOI18N

        javax.swing.GroupLayout tentangpanelLayout = new javax.swing.GroupLayout(tentangpanel);
        tentangpanel.setLayout(tentangpanelLayout);
        tentangpanelLayout.setHorizontalGroup(
            tentangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel28)
            .addGroup(tentangpanelLayout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        tentangpanelLayout.setVerticalGroup(
            tentangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tentangpanelLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(tentangpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tentangpanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        mainpanel.add(tentangpanel, "card5");

        jPanel5.setBackground(new java.awt.Color(122, 72, 221));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Karyawan");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        table_Karyawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_Karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_KaryawanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_Karyawan);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Nama");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("Jabatan");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Gaji");

        txt_nama_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nama_karyawanActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("No Telp");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("ID Karyawan");

        txt_id_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_karyawanActionPerformed(evt);
            }
        });

        btn_simpan_karyawan.setText("simpan");
        btn_simpan_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_karyawanActionPerformed(evt);
            }
        });

        btn_hapus_karyawan.setText("hapus");
        btn_hapus_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_karyawanActionPerformed(evt);
            }
        });

        btn_edit_karyawan.setText("edit");
        btn_edit_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_karyawanActionPerformed(evt);
            }
        });

        btn_cari_karyawan.setText("cari");
        btn_cari_karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cari_karyawanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Karyawan_panelLayout = new javax.swing.GroupLayout(Karyawan_panel);
        Karyawan_panel.setLayout(Karyawan_panelLayout);
        Karyawan_panelLayout.setHorizontalGroup(
            Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Karyawan_panelLayout.createSequentialGroup()
                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Karyawan_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(Karyawan_panelLayout.createSequentialGroup()
                        .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Karyawan_panelLayout.createSequentialGroup()
                                    .addGap(299, 299, 299)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_jabatan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                    .addGap(201, 201, 201)
                                    .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                    .addGap(196, 196, 196)
                                    .addComponent(btn_simpan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btn_hapus_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_edit_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                        .addGap(299, 299, 299)
                                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_nama_karyawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_notelp_karyawan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(Karyawan_panelLayout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addComponent(txt_gaji_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 281, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Karyawan_panelLayout.setVerticalGroup(
            Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Karyawan_panelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_jabatan_karyawan))
                    .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nama_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gaji_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_notelp_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Karyawan_panelLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Karyawan_panelLayout.createSequentialGroup()
                        .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_hapus_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_edit_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Karyawan_panelLayout.createSequentialGroup()
                        .addComponent(btn_simpan_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(Karyawan_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cari_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        mainpanel.add(Karyawan_panel, "card7");

        table_pengeluaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pengeluaranMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_pengeluaran);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("tanggal");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("nama barang");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("jumlah");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("harga");

        jPanel11.setBackground(new java.awt.Color(122, 72, 221));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Pengeluaran");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("id_pengeluaran");

        txt_tanggal_pengeluaran.setEnabled(false);
        txt_tanggal_pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tanggal_pengeluaranActionPerformed(evt);
            }
        });

        btn_simpan_pengeluaran1.setText("simpan");
        btn_simpan_pengeluaran1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_pengeluaran1ActionPerformed(evt);
            }
        });

        btn_hapus_pengeluaran1.setText("hapus");
        btn_hapus_pengeluaran1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_pengeluaran1ActionPerformed(evt);
            }
        });

        btn_edit_pengeluaran1.setText("edit");
        btn_edit_pengeluaran1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_pengeluaran1ActionPerformed(evt);
            }
        });

        btn_cari_pengeluaran1.setText("cari");
        btn_cari_pengeluaran1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cari_pengeluaran1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pengeluaran_panelLayout = new javax.swing.GroupLayout(pengeluaran_panel);
        pengeluaran_panel.setLayout(pengeluaran_panelLayout);
        pengeluaran_panelLayout.setHorizontalGroup(
            pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(43, 43, 43)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_id_pengeluaran)
                            .addComponent(txt_harga_brng_pengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txt_nama_brng_pengeluaran))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pengeluaran_panelLayout.createSequentialGroup()
                        .addContainerGap(241, Short.MAX_VALUE)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                                .addComponent(btn_simpan_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hapus_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_edit_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                                .addComponent(txt_cari_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tanggal_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_jumlah_brng_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pengeluaran_panelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_cari_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128)))))
                        .addGap(132, 132, 132)))
                .addContainerGap())
        );
        pengeluaran_panelLayout.setVerticalGroup(
            pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jumlah_brng_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tanggal_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pengeluaran_panelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nama_brng_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))))
                .addGap(18, 18, 18)
                .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_harga_brng_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(24, 24, 24)
                .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pengeluaran_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari_pengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari_pengeluaran1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        mainpanel.add(pengeluaran_panel, "card8");

        jPanel8.setBackground(new java.awt.Color(122, 72, 221));

        txt_id_bantu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_id_bantu.setForeground(new java.awt.Color(255, 255, 255));
        txt_id_bantu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_id_bantu.setText("Data Customer");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_id_bantu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_id_bantu, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Nama");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("No Telp");

        txt_id_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_customerActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("ID Customer");

        table_DataCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Customer", "Nama", "Warna", "Bahan", "Merk", "No Telp"
            }
        ));
        table_DataCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_DataCustomerMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_DataCustomer);

        btn_simpan_DataCustomer.setText("simpan");
        btn_simpan_DataCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_DataCustomerActionPerformed(evt);
            }
        });

        btn_hapus_DataCustomer.setText("hapus");
        btn_hapus_DataCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_DataCustomerActionPerformed(evt);
            }
        });

        btn_edit_DataCustomer.setText("edit");
        btn_edit_DataCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_DataCustomerActionPerformed(evt);
            }
        });

        btn_cari_DataCustomer.setText("cari");
        btn_cari_DataCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cari_DataCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataCustomer_panelLayout = new javax.swing.GroupLayout(DataCustomer_panel);
        DataCustomer_panel.setLayout(DataCustomer_panelLayout);
        DataCustomer_panelLayout.setHorizontalGroup(
            DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                    .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                        .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nama_customer, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(txt_notelp_customer, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(txt_id_customer)))
                            .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_cari_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                                        .addComponent(btn_simpan_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_hapus_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_edit_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DataCustomer_panelLayout.setVerticalGroup(
            DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataCustomer_panelLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_id_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nama_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_notelp_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataCustomer_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari_DataCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        mainpanel.add(DataCustomer_panel, "card6");

        transaksipanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("id transaksi");

        txt_sepatu_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sepatu_transaksiActionPerformed(evt);
            }
        });

        combo_jenispaket.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fast cleaning", "whitening", "deep cleaning", " " }));
        combo_jenispaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_jenispaketActionPerformed(evt);
            }
        });

        jButton1.setText("cari data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Nama Customer");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Sepatu");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Jenis Paket");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Harga");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txt_nama_customer_transaksi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_sepatu_transaksi)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combo_jenispaket, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_harga)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addContainerGap(37, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nama_customer_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sepatu_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_jenispaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Tunai");

        txt_jumlah_bayar_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlah_bayar_transaksiActionPerformed(evt);
            }
        });

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("jumlah kembalian");

        txt_jumlah_kembalian_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlah_kembalian_transaksiActionPerformed(evt);
            }
        });

        jButton2.setText("bayar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        table_struk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Transaksi", "Nama Customer", "Sepatu", "Jenis Paket", "Harga"
            }
        ));
        jScrollPane6.setViewportView(table_struk);

        txt_totalHarga_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalHarga_transaksiActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Total Harga");

        save.setText("save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        table_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Transaksi", "Nama Customer", "Sepatu", "Jenis Paket", "Harga"
            }
        ));
        table_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_transaksiMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table_transaksi);

        save1.setText("delete");
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });

        btn_simpan_transaksi.setText("simpan");
        btn_simpan_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_transaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout transaksipanelLayout = new javax.swing.GroupLayout(transaksipanel);
        transaksipanel.setLayout(transaksipanelLayout);
        transaksipanelLayout.setHorizontalGroup(
            transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksipanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksipanelLayout.createSequentialGroup()
                        .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksipanelLayout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(save)
                                .addGap(18, 18, 18)
                                .addComponent(save1)
                                .addGap(0, 203, Short.MAX_VALUE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_totalHarga_transaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(transaksipanelLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_jumlah_kembalian_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(transaksipanelLayout.createSequentialGroup()
                                .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txt_jumlah_bayar_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transaksipanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        transaksipanelLayout.setVerticalGroup(
            transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transaksipanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transaksipanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_simpan_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(transaksipanelLayout.createSequentialGroup()
                                .addComponent(txt_totalHarga_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_jumlah_bayar_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)))
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_jumlah_kembalian_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(102, 102, 102))
                    .addGroup(transaksipanelLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(transaksipanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(save)
                            .addComponent(save1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))))
        );

        mainpanel.add(transaksipanel, "card3");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(sidipane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sidipane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(mainpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DataCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataCustomerMouseEntered
        // TODO add your handling code here:
        btn_DataCustomer.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_DataCustomerMouseEntered

    private void btn_DataCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataCustomerMouseExited
        // TODO add your handling code here:
        btn_DataCustomer.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_DataCustomerMouseExited

    private void btn_DataCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataCustomerMousePressed
        // TODO add your handling code here:
        btn_DataCustomer.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_DataCustomerMousePressed

    private void btn_DataCustomerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataCustomerMouseReleased
        // TODO add your handling code here:
        btn_DataCustomer.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_DataCustomerMouseReleased

    private void btn_DataCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DataCustomerMouseClicked
        // TODO add your handling code here:
        //remove panel
        datatable_DataCustomer();
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(DataCustomer_panel);
        mainpanel.repaint();
        mainpanel.revalidate();
        //auto_id();
    }//GEN-LAST:event_btn_DataCustomerMouseClicked

    private void btn_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMouseClicked
        // TODO add your handling code here:
        //tanggal_transaksi();
        datatable_transaksi();
        //remove panel
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(transaksipanel);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_btn_transaksiMouseClicked

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        // TODO add your handling code here:
        //remove panel
        datatable_laporan();
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(laporanpanel);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_btn_laporanMouseClicked

    private void btn_tentangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tentangMouseClicked
        // TODO add your handling code here:
        //remove panel
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(tentangpanel);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_btn_tentangMouseClicked

    private void btn_transaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMouseEntered
        // TODO add your handling code here:
        btn_transaksi.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_transaksiMouseEntered

    private void btn_transaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMouseExited
        // TODO add your handling code here:
        btn_transaksi.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_transaksiMouseExited

    private void btn_transaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMousePressed
        // TODO add your handling code here:
        btn_transaksi.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_transaksiMousePressed

    private void btn_transaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMouseReleased
        // TODO add your handling code here:
        btn_transaksi.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_transaksiMouseReleased

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        // TODO add your handling code here:
        btn_laporan.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        // TODO add your handling code here:
        btn_laporan.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_laporanMouseExited

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
        // TODO add your handling code here:
        btn_laporan.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_laporanMousePressed

    private void btn_laporanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseReleased
        // TODO add your handling code here:
        btn_laporan.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_laporanMouseReleased

    private void btn_tentangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tentangMouseEntered
        // TODO add your handling code here:
        btn_tentang.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_tentangMouseEntered

    private void btn_tentangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tentangMouseExited
        // TODO add your handling code here:
        btn_tentang.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_tentangMouseExited

    private void btn_tentangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tentangMousePressed
        // TODO add your handling code here:
        btn_tentang.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_tentangMousePressed

    private void btn_tentangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tentangMouseReleased
        // TODO add your handling code here:
        btn_tentang.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_tentangMouseReleased

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        // TODO add your handling code here:
        Login log = new Login();
        log.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_keluarMouseClicked

    private void btn_keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseEntered
        // TODO add your handling code here:
         btn_keluar.setBackground(new Color(85,65,118));
        
    }//GEN-LAST:event_btn_keluarMouseEntered

    private void btn_keluarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseExited
        // TODO add your handling code here:
        btn_keluar.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_keluarMouseExited

    private void btn_keluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMousePressed
        // TODO add your handling code here:
         btn_keluar.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_keluarMousePressed

    private void btn_keluarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseReleased
        // TODO add your handling code here:
         btn_keluar.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_keluarMouseReleased

    private void btn_simpan_tabeldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_tabeldataMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan_tabeldataMouseClicked

    private void btn_hapus_tabeldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus_tabeldataMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_tabeldataMouseClicked

    private void btn_edit_tabeldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_tabeldataMouseClicked
        // TODO add your handling code here:     
    }//GEN-LAST:event_btn_edit_tabeldataMouseClicked

    private void txt_id_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_customerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_customerActionPerformed

    private void table_DataCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DataCustomerMouseClicked
         // TODO add your handling code here:
        int baris = table_DataCustomer.rowAtPoint(evt.getPoint());
        
        String id = table_DataCustomer.getValueAt(baris, 0).toString();
        txt_id_customer.setText(id);
        
        String nama = table_DataCustomer.getValueAt(baris, 1).toString();
        txt_nama_customer.setText(nama);
        
        String notelp = table_DataCustomer.getValueAt(baris, 2).toString();
        txt_notelp_customer.setText(notelp); 
    }//GEN-LAST:event_table_DataCustomerMouseClicked

    private void btn_edit_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_karyawanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_karyawanMouseClicked

    private void btn_hapus_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus_karyawanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_karyawanMouseClicked

    private void btn_cari_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari_karyawanActionPerformed
        // TODO add your handling code here:
        cari_karyawan();
    }//GEN-LAST:event_btn_cari_karyawanActionPerformed
    // TODO add your handling code here:
    // TODO add your handling code here:


    private void btn_simpan_tabeldata2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_tabeldata2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan_tabeldata2MouseClicked

    private void btn_hapus_tabeldata2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus_tabeldata2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_tabeldata2MouseClicked

    private void btn_edit_tabeldata2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_tabeldata2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_tabeldata2MouseClicked

    private void btn_tambah_tabeldata2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah_tabeldata2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambah_tabeldata2MouseClicked

    private void btn_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengeluaranMouseClicked
        // TODO add your handling code here:
        tanggal_pengeluaran();
        datatable_pengeluaran();
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(pengeluaran_panel);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_btn_pengeluaranMouseClicked

    private void btn_pengeluaranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengeluaranMouseEntered
        // TODO add your handling code here:
        btn_pengeluaran.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_pengeluaranMouseEntered

    private void btn_pengeluaranMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengeluaranMouseExited
        // TODO add your handling code here:
        btn_pengeluaran.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_pengeluaranMouseExited

    private void btn_pengeluaranMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengeluaranMousePressed
        // TODO add your handling code here:
        btn_pengeluaran.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_pengeluaranMousePressed

    private void btn_pengeluaranMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pengeluaranMouseReleased
        // TODO add your handling code here:
        btn_pengeluaran.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_pengeluaranMouseReleased

    private void btn_KaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KaryawanMouseClicked
        // TODO add your handling code here:
        datatable_Karyawan();
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(Karyawan_panel);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_btn_KaryawanMouseClicked

    private void btn_KaryawanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KaryawanMouseEntered
        // TODO add your handling code here:
        btn_Karyawan.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_KaryawanMouseEntered

    private void btn_KaryawanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KaryawanMouseExited
        // TODO add your handling code here:
        btn_Karyawan.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_KaryawanMouseExited

    private void btn_KaryawanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KaryawanMousePressed
        // TODO add your handling code here:
        btn_Karyawan.setBackground(new Color(85,65,118));
    }//GEN-LAST:event_btn_KaryawanMousePressed

    private void btn_KaryawanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KaryawanMouseReleased
        // TODO add your handling code here:
        btn_Karyawan.setBackground(new Color(64,43,100));
    }//GEN-LAST:event_btn_KaryawanMouseReleased

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        mainpanel.removeAll();
        mainpanel.repaint();
        mainpanel.revalidate();
        //add panel
        mainpanel.add(dashboardpanel);
        mainpanel.repaint();
        mainpanel.revalidate();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void btn_cari_tabeldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_tabeldataMouseClicked
        // TODO add your handling code here:
        cari_customer();
    }//GEN-LAST:event_btn_cari_tabeldataMouseClicked

    private void txt_id_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_karyawanActionPerformed

    private void txt_nama_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nama_karyawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nama_karyawanActionPerformed

    private void table_KaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_KaryawanMouseClicked
        // TODO add your handling code here:
        int baris = table_Karyawan.rowAtPoint(evt.getPoint());
        
        String id = table_Karyawan.getValueAt(baris, 0).toString();
        txt_id_karyawan.setText(id);
        
        String nama = table_Karyawan.getValueAt(baris, 1).toString();
        txt_nama_karyawan.setText(nama);
        
        String jabatan = table_Karyawan.getValueAt(baris, 2).toString();
        txt_jabatan_karyawan.setText(jabatan);
        
        String gaji = table_Karyawan.getValueAt(baris, 3).toString();
        txt_gaji_karyawan.setText(gaji);
        
        String notelp = table_Karyawan.getValueAt(baris, 4).toString();
        txt_notelp_karyawan.setText(notelp);
    }//GEN-LAST:event_table_KaryawanMouseClicked

    private void btn_simpan_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_customerActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_customer.getText();
        String nama_customer = txt_nama_customer.getText();
        String notelp = txt_notelp_customer.getText();


        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("insert into customer VALUES ('"+id_customer+"','"+nama_customer+"','"+notelp+"');");
            txt_id_customer.setText("");
            txt_nama_customer.setText("");
            txt_notelp_customer.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_DataCustomer();
    }//GEN-LAST:event_btn_simpan_customerActionPerformed

    private void btn_hapus_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_customerActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_customer.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate(" DELETE from customer where id_customer = ('"+id_customer+"');");
            JOptionPane.showMessageDialog(null, "data berhasil dihapus");
            txt_id_customer.setText("");
            txt_nama_customer.setText("");
            txt_notelp_customer.setText("");
            txt_id_customer.requestFocus();
        } catch (HeadlessException | SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal dihapus");
        }
        datatable_DataCustomer();
    }//GEN-LAST:event_btn_hapus_customerActionPerformed

    private void btn_hapus_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_karyawanActionPerformed
        // TODO add your handling code here:
        String id_karyawan = txt_id_karyawan.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate(" DELETE from karyawan where id_karyawan=('"+id_karyawan+"');");
            JOptionPane.showMessageDialog(null, "data berhasil dihapus");
            txt_id_karyawan.setText("");
            txt_nama_karyawan.setText("");
            txt_jabatan_karyawan.setText("");
            txt_gaji_karyawan.setText("");
            txt_notelp_karyawan.setText("");
            txt_nama_karyawan.requestFocus();
        } catch (HeadlessException | SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal dihapus");
        }
        datatable_Karyawan();
    }//GEN-LAST:event_btn_hapus_karyawanActionPerformed

    private void btn_simpan_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_customerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan_customerMouseClicked

    private void btn_hapus_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus_customerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_customerMouseClicked

    private void btn_edit_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_customerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_customerMouseClicked

    private void btn_cari_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_customerMouseClicked
        // TODO add your handling code here:
        cari_customer();
    }//GEN-LAST:event_btn_cari_customerMouseClicked

    private void btn_simpan_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fButton5MouseClicked
        // TODO add your handling code here:
        String nama = txt_nama_karyawan.getText();
        String jabatan = txt_jabatan_karyawan.getText();
        int gaji = Integer.parseInt(txt_gaji_karyawan.getText());
        int notelp = Integer.parseInt(txt_notelp_karyawan.getText());
        String id_karyawan = txt_id_karyawan.getText();
        
        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("insert into karyawan VALUES ('"+nama+"','"+jabatan+"','"+gaji+"','"+notelp+"','"+id_karyawan+"');");
            statement.close();
            txt_nama_karyawan.setText("");
            txt_jabatan_karyawan.setText("");
            txt_gaji_karyawan.setText("");
            txt_notelp_karyawan.setText("");
            txt_id_karyawan.setText("");
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_Karyawan();
    }//GEN-LAST:event_fButton5MouseClicked

    private void btn_cari_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_karyawanMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_cari_karyawanMouseClicked

    private void btn_simpan_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_pengeluaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan_pengeluaranMouseClicked

    private void btn_hapus_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapus_pengeluaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus_pengeluaranMouseClicked

    private void btn_edit_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_pengeluaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_pengeluaranMouseClicked

    private void btn_cari_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_pengeluaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cari_pengeluaranMouseClicked

    private void table_pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pengeluaranMouseClicked
        // TODO add your handling code here:
        int baris = table_pengeluaran.rowAtPoint(evt.getPoint());
        
        String id = table_pengeluaran.getValueAt(baris, 0).toString();
        txt_id_pengeluaran.setText(id);
        
        String nama_brng = table_pengeluaran.getValueAt(baris, 1).toString();
        txt_nama_brng_pengeluaran.setText(nama_brng);
        
        String harga = table_pengeluaran.getValueAt(baris, 2).toString();
        txt_harga_brng_pengeluaran.setText(harga);
        
        String jumlah = table_pengeluaran.getValueAt(baris, 3).toString();
        txt_jumlah_brng_pengeluaran.setText(jumlah);
        
    }//GEN-LAST:event_table_pengeluaranMouseClicked

    private void btn_edit_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_customerActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_customer.getText();
        String nama_customer = txt_nama_customer.getText();
        String notelp = txt_notelp_customer.getText();
        
        

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("update customer set nama_customer='"+ txt_nama_customer.getText() +"',notelp='" + txt_notelp_customer.getText() +"'where id_customer='" + txt_id_customer.getText() + "'");
            txt_id_customer.setText("");
            txt_nama_customer.setText("");
            txt_notelp_customer.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_DataCustomer();
    }//GEN-LAST:event_btn_edit_customerActionPerformed

    private void btn_edit_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_karyawanActionPerformed
        // TODO add your handling code here:
        String id_karyawan = txt_id_karyawan.getText();
        String nama_karyawan = txt_nama_karyawan.getText();
        String jabatan = txt_jabatan_karyawan.getText();
        int gaji = Integer.parseInt(txt_gaji_karyawan.getText());
        String notelp = txt_notelp_karyawan.getText();
        
        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("update karyawan set nama_karyawan='"+ txt_nama_karyawan.getText() +"',jabatan='" + txt_jabatan_karyawan.getText() +"',gaji='" + txt_gaji_karyawan.getText()+"',notelp='" + txt_notelp_karyawan.getText() +"' where id_karyawan='"+ txt_id_karyawan.getText() +"'");
            JOptionPane.showMessageDialog(this, "data berhasil di edit");
            txt_nama_karyawan.setText("");
            txt_jabatan_karyawan.setText("");
            txt_gaji_karyawan.setText("");
            txt_notelp_karyawan.setText("");
            txt_id_karyawan.setText("");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(this, "data gagal di edit");
        }
        datatable_Karyawan();
    }//GEN-LAST:event_btn_edit_karyawanActionPerformed

    private void btn_simpan_karyawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_karyawanActionPerformed
        // TODO add your handling code here:
        String id_karyawan = txt_id_karyawan.getText();
        String nama_karyawan = txt_nama_karyawan.getText();
        String jabatan = txt_jabatan_karyawan.getText();
        int gaji = Integer.parseInt(txt_gaji_karyawan.getText());
        String notelp = txt_notelp_karyawan.getText();
                

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("insert into karyawan (id_karyawan, nama_karyawan, jabatan, gaji, notelp) VALUES ('"+id_karyawan+"','"+nama_karyawan+"','"+jabatan+"','"+gaji+"','"+notelp+"');");
            txt_id_karyawan.setText("");
            txt_nama_karyawan.setText("");
            txt_jabatan_karyawan.setText("");
            txt_gaji_karyawan.setText("");
            txt_notelp_karyawan.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_Karyawan();
    }//GEN-LAST:event_btn_simpan_karyawanActionPerformed

    private void date_pengeluaranPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_pengeluaranPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_date_pengeluaranPropertyChange

    private void txt_tanggal_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tanggal_pengeluaranActionPerformed
        // TODO add your handling code here:
            tanggal_pengeluaran();
    }//GEN-LAST:event_txt_tanggal_pengeluaranActionPerformed

    private void combo_jenispaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_jenispaketActionPerformed
        // TODO add your handling code here:
        String jenis_paket=(String)this.combo_jenispaket.getSelectedItem();
        switch (jenis_paket) {
            case "fast cleaning":
                txt_harga.setText("25000");
                break;
            case "whitening":
                txt_harga.setText("70000");
                break;
            case "deep cleaning":
                txt_harga.setText("45000");
                break;
        }
    }//GEN-LAST:event_combo_jenispaketActionPerformed

    private void btn_cari_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari_customerActionPerformed
        // TODO add your handling code here:
        cari_customer();
    }//GEN-LAST:event_btn_cari_customerActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int totalharga = Integer.parseInt(txt_totalHarga_transaksi.getText());
        int JumlahBayar = Integer.parseInt(txt_jumlah_bayar_transaksi.getText());
        int kembalian = JumlahBayar - totalharga;
        txt_jumlah_kembalian_transaksi.setText(""+kembalian);        
    
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fButton1ActionPerformed
            // TODO add your handling code here:
        String id_tran = txt_transaksi.getText();
        String nama = txt_nama_customer_transaksi.getText();
        String sepatu = txt_sepatu_transaksi.getText();
        String jenis = (String) combo_jenispaket.getSelectedItem();
        int harga = Integer.parseInt(txt_harga.getText());
        
        DefaultTableModel tbl = (DefaultTableModel) table_struk.getModel();
        
        tbl.addRow(new Object[]{
            id_tran,
            nama,
            sepatu,
            jenis,
            harga
        });
        
        int TotalHarga =0;
        for(int i=0; i<table_struk.getRowCount();i++){
            TotalHarga += Integer.parseInt(table_struk.getValueAt(i, 4).toString());
            
        }
        txt_totalHarga_transaksi.setText(""+TotalHarga);
        txt_transaksi.setText("");
        txt_nama_customer_transaksi.setText("");
        combo_jenispaket.setSelectedItem("");
        txt_sepatu_transaksi.setText("");
        txt_harga.setText("");
    }//GEN-LAST:event_fButton1ActionPerformed

    private void table_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_laporanMouseClicked
        // TODO add your handling code here:
        int baris = table_transaksi.rowAtPoint(evt.getPoint());
        
        String id = table_transaksi.getValueAt(baris, 0).toString();
        txt_transaksi.setText(id);
        
        String nama = table_transaksi.getValueAt(baris, 1).toString();
        txt_nama_customer_transaksi.setText(nama);
        
        String jenis = table_transaksi.getValueAt(baris, 2).toString();
        combo_jenispaket.setSelectedItem(jenis);
        
        String sepatu = table_transaksi.getValueAt(baris, 3).toString();
        txt_sepatu_transaksi.setText(sepatu);
        
        String harga = table_transaksi.getValueAt(baris, 4).toString();
        txt_harga.setText(harga);
    }//GEN-LAST:event_table_laporanMouseClicked

    private void txt_jumlah_kembalian_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlah_kembalian_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlah_kembalian_transaksiActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
        File namafile = new File("src/laporan/struk_penjualan.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, k.getCon());
            JasperViewer.viewReport(jp,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_sepatu_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sepatu_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sepatu_transaksiActionPerformed

    private void txt_totalHarga_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalHarga_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalHarga_transaksiActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        /*String id_tran = txt_transaksi.getText();
        String nama_cus = txt_nama_customer_transaksi.getText();
        String jenis = (String)combo_jenispaket.getSelectedItem();
        String sepatu = txt_sepatu_transaksi.getText();
        int harga = Integer.parseInt(txt_harga.getText());
        int jumlah_bayar = Integer.parseInt(txt_jumlah_bayar_transaksi.getText());
        int jumlah_kembalian = Integer.parseInt(txt_jumlah_kembalian_transaksi.getText());

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("insert into detail_transaksi (id_transaksi, nama_customer, jenis_paket, sepatu, harga, jumlah_bayar, jumlah_kembalian) VALUES ('"+id_tran+"','"+nama_cus+"','"+jenis+"','"+sepatu+"','"+harga+"','"+jumlah_bayar+"','"+jumlah_kembalian+"');");
            txt_transaksi.setText("");
            txt_nama_customer_transaksi.setText("");
            combo_jenispaket.setSelectedItem("");
            txt_sepatu_transaksi.setText("");
            txt_harga.setText("");
            txt_jumlah_bayar_transaksi.setText("");
            txt_jumlah_kembalian_transaksi.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_transaksi();*/

        Connection con = (Connection) k.getCon();
        Statement stat;

        DefaultTableModel model = (DefaultTableModel) table_struk.getModel();
        
        int total_harga = Integer.parseInt(txt_totalHarga_transaksi.getText());
        int jumlah_bayar = Integer.parseInt(txt_jumlah_bayar_transaksi.getText());
        int jumlah_kembalian = Integer.parseInt(txt_jumlah_kembalian_transaksi.getText());

        try {
            stat = con.createStatement();

            for(int i = 0; i < model.getRowCount(); i++){

                String id = model.getValueAt(i, 0).toString();
                String nama = model.getValueAt(i, 1).toString();
                String sepatu = model.getValueAt(i, 2).toString();
                String jenis = model.getValueAt(i, 3).toString();
                int harga = Integer.valueOf(model.getValueAt(i, 4).toString());
                int total = Integer.valueOf(model.getValueAt(i, 5).toString());

                String sqlQuery = "INSERT INTO detail_transaksi (id_transaksi, nama_customer, sepatu, jenis_paket, harga,total_harga, jumlah_bayar, jumlah_kembalian) VALUES ('"+id+"','"+nama+"','"+jenis+"','"+sepatu+"','"+harga+"','"+total+"','"+jumlah_bayar+"','"+jumlah_kembalian+"')";

                stat.addBatch(sqlQuery);
            }

            int[] rowInserted = stat.executeBatch();
            System.out.println("Data Inserted");
            System.out.println("rowInserted Count = " + rowInserted.length);
            
            JOptionPane.showMessageDialog(null, "success");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "kontol");
        }
        datatable_transaksi();
        
        
        
        DefaultTableModel tableModel = (DefaultTableModel) table_struk.getModel();
        tableModel.setRowCount(0);
    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        data dat = new data();
        dat.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        // TODO add your handling code here:
        String id_transaksi = txt_transaksi.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate(" DELETE from detail_transaksi where id_transaksi = ('"+id_transaksi+"');");
            JOptionPane.showMessageDialog(null, "data berhasil dihapus");
            txt_transaksi.setText("");
            txt_nama_customer_transaksi.setText("");
            combo_jenispaket.setSelectedItem("");
            txt_sepatu_transaksi.setText("");
            txt_harga.setText("");
            txt_transaksi.requestFocus();
        } catch (HeadlessException | SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal dihapus");
        }
        datatable_transaksi();
    }//GEN-LAST:event_save1ActionPerformed

    private void table_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_transaksiMouseClicked
        // TODO add your handling code here:
        int baris = table_transaksi.rowAtPoint(evt.getPoint());
        
        String id = table_transaksi.getValueAt(baris, 0).toString();
        txt_transaksi.setText(id);
        
        String nama = table_transaksi.getValueAt(baris, 1).toString();
        txt_nama_customer_transaksi.setText(nama);
        
        String jenis = table_transaksi.getValueAt(baris, 3).toString();
        combo_jenispaket.setSelectedItem(jenis);
        
        String sepatu = table_transaksi.getValueAt(baris, 2).toString();
        txt_sepatu_transaksi.setText(sepatu);
        
        String harga = table_transaksi.getValueAt(baris, 4).toString();
        txt_harga.setText(harga);
    }//GEN-LAST:event_table_transaksiMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
        File namafile = new File("src/laporan/laporan.jasper");
            JasperPrint jp = JasperFillManager.fillReport(namafile.getPath(), null, k.getCon());
            JasperViewer.viewReport(jp,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_simpan_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapus_pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_pengeluaran1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_simpan_pengeluaran1ActionPerformed

    private void btn_cari_pengeluaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari_pengeluaran1ActionPerformed
        // TODO add your handling code here:
        cari_pengeluaran();
    }//GEN-LAST:event_btn_cari_pengeluaran1ActionPerformed

    private void btn_edit_pengeluaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_pengeluaran1ActionPerformed
        // TODO add your handling code here:
        String id_pengeluaran = txt_id_pengeluaran.getText();
        String nama_brng = txt_nama_brng_pengeluaran.getText();
        int harga_brng = Integer.parseInt(txt_harga_brng_pengeluaran.getText());
        int jumlah_brng = Integer.parseInt(txt_jumlah_brng_pengeluaran.getText());
        String tanggal = txt_tanggal_pengeluaran.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("update pengeluaran set nama_brng='"+ txt_nama_brng_pengeluaran.getText() +"',harga_brng='" + txt_harga_brng_pengeluaran.getText() +"',jumlah_brng='" + txt_jumlah_brng_pengeluaran.getText() +"',tanggal='" + txt_tanggal_pengeluaran.getText() +"'where id_pengeluaran='" + txt_id_pengeluaran.getText() + "'");
            txt_id_pengeluaran.setText("");
            txt_nama_brng_pengeluaran.setText("");
            txt_harga_brng_pengeluaran.setText("");
            txt_jumlah_brng_pengeluaran.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_pengeluaran();
    }//GEN-LAST:event_btn_edit_pengeluaran1ActionPerformed

    private void btn_hapus_pengeluaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_pengeluaran1ActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_pengeluaran.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate(" DELETE from pengeluaran where id_pengeluaran = ('"+id_customer+"');");
            JOptionPane.showMessageDialog(null, "data berhasil dihapus");
            txt_id_pengeluaran.setText("");
            txt_nama_brng_pengeluaran.setText("");
            txt_harga_brng_pengeluaran.setText("");
            txt_jumlah_brng_pengeluaran.setText("");
            txt_id_pengeluaran.requestFocus();
        } catch (HeadlessException | SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal dihapus");
        }
        datatable_pengeluaran();
    }//GEN-LAST:event_btn_hapus_pengeluaran1ActionPerformed

    private void btn_simpan_pengeluaran1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_pengeluaranActionPerformed
        // TODO add your handling code here:
        String id_pengeluaran = txt_id_pengeluaran.getText();
        String nama_brng = txt_nama_brng_pengeluaran.getText();
        int harga_brng = Integer.parseInt(txt_harga_brng_pengeluaran.getText());
        int jumlah_brng = Integer.parseInt(txt_jumlah_brng_pengeluaran.getText());
        String tanggal = txt_tanggal_pengeluaran.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("insert into pengeluaran (id_pengeluaran, nama_brng, harga_brng, jumlah_brng, tanggal) VALUES ('"+id_pengeluaran+"','"+nama_brng+"','"+harga_brng+"','"+jumlah_brng+"','"+tanggal+"');");
            txt_id_pengeluaran.setText("");
            txt_nama_brng_pengeluaran.setText("");
            txt_harga_brng_pengeluaran.setText("");
            txt_jumlah_brng_pengeluaran.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_pengeluaran();
    }//GEN-LAST:event_btn_simpan_pengeluaranActionPerformed

    private void btn_simpan_DataCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_DataCustomerActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_customer.getText();
        String nama = txt_nama_customer.getText();
        String notelp = txt_notelp_customer.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("insert into customer (id_customer, nama_customer, notelp) VALUES ('"+id_customer+"','"+nama+"','"+notelp+"');");
            txt_id_customer.setText("");
            txt_nama_customer.setText("");
            txt_notelp_customer.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_DataCustomer();
        //auto_id();
    }//GEN-LAST:event_btn_simpan_DataCustomerActionPerformed

    private void btn_hapus_DataCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_DataCustomerActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_customer.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate(" DELETE from customer where id_customer = ('"+id_customer+"');");
            JOptionPane.showMessageDialog(null, "data berhasil dihapus");
            txt_id_customer.setText("");
            txt_nama_customer.setText("");
            txt_notelp_customer.setText("");
            txt_id_customer.requestFocus();
        } catch (HeadlessException | SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal dihapus");
        }
        datatable_DataCustomer();
    }//GEN-LAST:event_btn_hapus_DataCustomerActionPerformed

    private void btn_edit_DataCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_DataCustomerActionPerformed
        // TODO add your handling code here:
        String id_customer = txt_id_customer.getText();
        String nama = txt_nama_customer.getText();
        String notelp = txt_notelp_customer.getText();

        try {
            Statement statement = (Statement) k.getCon().createStatement();
            statement.executeUpdate("update customer set nama_customer='"+ txt_nama_customer.getText() +"',notelp='" + txt_notelp_customer.getText() +"'where id_customer='" + txt_id_customer.getText() + "'");
            txt_id_customer.setText("");
            txt_nama_customer.setText("");
            txt_notelp_customer.setText("");
            statement.close();
            JOptionPane.showMessageDialog(null, "data berhasil dimpan");
        } catch (SQLException t) {
            JOptionPane.showMessageDialog(null, "data gagal disimpan");
        }
        datatable_DataCustomer();
    }//GEN-LAST:event_btn_edit_DataCustomerActionPerformed

    private void btn_cari_DataCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari_DataCustomerActionPerformed
        // TODO add your handling code here:
        cari_customer();
    }//GEN-LAST:event_btn_cari_DataCustomerActionPerformed

    private void btn_simpan_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_transaksiActionPerformed
        // TODO add your handling code here:
        String id_tran = txt_transaksi.getText();
        String nama = txt_nama_customer_transaksi.getText();
        String sepatu = txt_sepatu_transaksi.getText();
        String jenis = (String) combo_jenispaket.getSelectedItem();
        int harga = Integer.parseInt(txt_harga.getText());
        
        DefaultTableModel tbl = (DefaultTableModel) table_struk.getModel();
        
        tbl.addRow(new Object[]{
            id_tran,
            nama,
            sepatu,
            jenis,
            harga
        });
        
        int TotalHarga =0;
        for(int i=0; i<table_struk.getRowCount();i++){
            TotalHarga += Integer.parseInt(table_struk.getValueAt(i, 4).toString());
            
        }
        txt_totalHarga_transaksi.setText(""+TotalHarga);
        txt_transaksi.setText("");
        txt_nama_customer_transaksi.setText("");
        combo_jenispaket.setSelectedItem("");
        txt_sepatu_transaksi.setText("");
        txt_harga.setText("");
    }//GEN-LAST:event_btn_simpan_transaksiActionPerformed

    private void txt_jumlah_bayar_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlah_bayar_transaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlah_bayar_transaksiActionPerformed

    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home1().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DataCustomer_panel;
    private javax.swing.JPanel Karyawan_panel;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel btn_DataCustomer;
    private javax.swing.JPanel btn_Karyawan;
    private rojerusan.RSMaterialButtonRectangle btn_cari_DataCustomer;
    private rojerusan.RSMaterialButtonRectangle btn_cari_karyawan;
    private rojerusan.RSMaterialButtonRectangle btn_cari_pengeluaran1;
    private rojerusan.RSMaterialButtonRectangle btn_edit_DataCustomer;
    private rojerusan.RSMaterialButtonRectangle btn_edit_karyawan;
    private rojerusan.RSMaterialButtonRectangle btn_edit_pengeluaran1;
    private rojerusan.RSMaterialButtonRectangle btn_hapus_DataCustomer;
    private rojerusan.RSMaterialButtonRectangle btn_hapus_karyawan;
    private rojerusan.RSMaterialButtonRectangle btn_hapus_pengeluaran1;
    private javax.swing.JPanel btn_keluar;
    private javax.swing.JPanel btn_laporan;
    private javax.swing.JPanel btn_pengeluaran;
    private rojerusan.RSMaterialButtonRectangle btn_simpan_DataCustomer;
    private rojerusan.RSMaterialButtonRectangle btn_simpan_karyawan;
    private rojerusan.RSMaterialButtonRectangle btn_simpan_pengeluaran1;
    private rojerusan.RSMaterialButtonRectangle btn_simpan_transaksi;
    private javax.swing.JPanel btn_tentang;
    private javax.swing.JPanel btn_transaksi;
    private javax.swing.JComboBox<String> combo_jenispaket;
    private javax.swing.JPanel dashboardpanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel laporanpanel;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JPanel pengeluaran_panel;
    private javax.swing.JButton save;
    private javax.swing.JButton save1;
    private javax.swing.JPanel sidipane;
    private javax.swing.JTable table_DataCustomer;
    private javax.swing.JTable table_Karyawan;
    private javax.swing.JTable table_laporan;
    private javax.swing.JTable table_pengeluaran;
    private javax.swing.JTable table_struk;
    private javax.swing.JTable table_transaksi;
    private javax.swing.JPanel tentangpanel;
    private javax.swing.JPanel transaksipanel;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_cari_karyawan;
    private javax.swing.JTextField txt_cari_pengeluaran;
    private javax.swing.JTextField txt_gaji_karyawan;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_harga_brng_pengeluaran;
    private javax.swing.JLabel txt_id_bantu;
    private javax.swing.JTextField txt_id_customer;
    private javax.swing.JTextField txt_id_karyawan;
    private javax.swing.JTextField txt_id_pengeluaran;
    private javax.swing.JTextField txt_jabatan_karyawan;
    private javax.swing.JTextField txt_jumlah_bayar_transaksi;
    private javax.swing.JTextField txt_jumlah_brng_pengeluaran;
    private javax.swing.JTextField txt_jumlah_kembalian_transaksi;
    private javax.swing.JTextField txt_nama_brng_pengeluaran;
    private javax.swing.JTextField txt_nama_customer;
    public javax.swing.JTextField txt_nama_customer_transaksi;
    private javax.swing.JTextField txt_nama_karyawan;
    private javax.swing.JTextField txt_notelp_customer;
    private javax.swing.JTextField txt_notelp_karyawan;
    private javax.swing.JTextField txt_sepatu_transaksi;
    private javax.swing.JTextField txt_tanggal_pengeluaran;
    private javax.swing.JTextField txt_totalHarga_transaksi;
    private javax.swing.JTextField txt_transaksi;
    // End of variables declaration//GEN-END:variables
}