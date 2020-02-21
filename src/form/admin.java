/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import conf.dbconnection;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author NEVE
 */
public final class admin extends JFrame {

    /**
     * Creates new form admin
     */
    private DefaultTableModel tbllogin_mdl;

    public admin(String para) {
        initComponents();
        TampilDataPemilih();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        lbPass.setVisible(false);
        UIManager.put("OptionPane.background", new ColorUIResource(44, 62, 80));
        UIManager.put("Panel.background", new ColorUIResource(44, 62, 80));
        UIManager.put("TabbedPane.background", Color.PINK);
    }
    String ip = getIPServer.IPaddress;
    public Connection conn = new dbconnection().connect(ip);

    public void TampilDataPemilih() {
        tbllogin_mdl = new DefaultTableModel();
        tbllogin_mdl.addColumn("NIM");
        tbllogin_mdl.addColumn("Nama");
        tbllogin_mdl.addColumn("Password");
        tbllogin_mdl.addColumn("Status");
        tbPemilih.setModel(tbllogin_mdl);
        tbPemilih.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbPemilih.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbPemilih.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbPemilih.getColumnModel().getColumn(3).setPreferredWidth(20);
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from login where nama <> 'admin'");
            while (res.next()) {
                tbllogin_mdl.addRow(new Object[]{
                    res.getString("nim"),
                    res.getString("nama"),
                    res.getString("password"),
                    res.getString("confirm")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void AddDataCalon() throws FileNotFoundException{
        try {
            File image = new File(txtGbCalon.getText());
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select max(id) as xxx from hasilvote");            
            int id = 0;
            while (res.next()) {
                id = res.getInt("xxx");                
            }
            System.out.println(String.valueOf(id));
            FileInputStream inputstream = new FileInputStream(image);
            PreparedStatement stm = conn.prepareStatement("insert into hasilvote values (?,?,?, ?)");
            try {
                stm.setString(1, txtNoCalon.getText());
                stm.setString(2, txtNamaKa.getText()+" - "+txtNamaWaka.getText());
                stm.setInt(3, 0);
                stm.setBinaryStream(4, (InputStream) inputstream, (int) image.length());
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error Add Data Calon");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println("Error Selecting max ID Calon");
            System.out.println(e);
        }
    }
    
    public void AddDataPemilih(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select max(right(password, 3)) as xxx from login where nim <> 'admin'");
            int pass;
            while (res.next()) {
                pass = res.getInt("xxx");
                String no = String.valueOf(pass + 1);
                int noLong = no.length();
                for (int a = 0; a < 3 - noLong; a++) {
                    no = "0" + no;
                }
                lbPass.setText(no);
                System.out.println(no);
            }
            PreparedStatement stm = conn.prepareStatement("insert into login (nim, nama, password, confirm) values (?,?,?,?)");
            try {
                stm.setString(1, txtNIMTambah.getText());
                stm.setString(2, txtNamaTambah.getText());
                stm.setString(3, "A"+lbPass.getText());
                stm.setString(4, "b");
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error Add Data Pemilih");
                System.out.println(e);
            }
            TampilDataPemilih();
        } catch (SQLException e) {
            System.out.println("Error Selecting max ID");
            System.out.println(e);
        }
        
    }
    
    public void DeleteAllData() {
        try {
            PreparedStatement stm = conn.prepareStatement("truncate login");
            stm.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        insertButton = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPemilih = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtNIMTambah = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNamaTambah = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPaslon = new javax.swing.JTable();
        lbFotoPasangan1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNoUrut = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNamaPaslon = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNamaWaka = new javax.swing.JTextField();
        txtNamaKa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGbCalon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNoCalon = new javax.swing.JTextField();
        lbFotoPasangan = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Halaman Administrator");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(44, 62, 80));
        jTabbedPane1.setForeground(new java.awt.Color(247, 202, 24));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));

        insertButton.setBackground(new java.awt.Color(123, 239, 178));
        insertButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        insertButton.setText("Insert Data");
        insertButton.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        insertButton.setBorderPainted(false);
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });
        jPanel1.add(insertButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 125, 30));

        jButton6.setBackground(new java.awt.Color(150, 40, 27));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Delete All Data");
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 125, 30));

        jButton1.setBackground(new java.awt.Color(123, 239, 178));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Cari Nim");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(71, 20));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 80, 30));

        tbPemilih.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbPemilih.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbPemilih);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 675, 420));

        jButton2.setBackground(new java.awt.Color(0, 181, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Lihat Persentase");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 165, 30));

        jButton3.setBackground(new java.awt.Color(150, 40, 27));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Log Out");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 490, 165, 30));

        jButton5.setBackground(new java.awt.Color(123, 239, 178));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Refresh Tabel");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, 165, 30));

        jTabbedPane1.addTab("  Data Pemilih  ", jPanel1);

        jPanel5.setBackground(new java.awt.Color(44, 62, 80));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNIMTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtNIMTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 180, 30));

        lbPass.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbPass.setForeground(new java.awt.Color(255, 255, 255));
        lbPass.setText("NIM");
        jPanel5.add(lbPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 60, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 60, 30));

        txtNamaTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtNamaTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 230, 30));

        jButton9.setBackground(new java.awt.Color(123, 239, 178));
        jButton9.setText("Input");
        jButton9.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 270, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("NIM");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, 30));

        jTabbedPane1.addTab("Tambah Data Pemilih", jPanel5);

        jPanel4.setBackground(new java.awt.Color(44, 62, 80));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbPaslon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbPaslon.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbPaslon);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 675, 240));

        lbFotoPasangan1.setForeground(new java.awt.Color(255, 255, 255));
        lbFotoPasangan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFotoPasangan1.setText("FOTO PASANGAN CALON");
        lbFotoPasangan1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));
        jPanel4.add(lbFotoPasangan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(475, 30, 150, 160));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("No. Urut Pasangan Calon");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 150, 30));

        txtNoUrut.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.add(txtNoUrut, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 210, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama Pasangan Calon");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 150, 30));

        txtNamaPaslon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.add(txtNamaPaslon, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 210, 30));

        jButton10.setBackground(new java.awt.Color(123, 239, 178));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton10.setText("Perbarui Data Paslon");
        jButton10.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 150, 30));

        jButton12.setBackground(new java.awt.Color(150, 40, 27));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Hapus Data Paslon");
        jButton12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(0, 0, 0)));
        jButton12.setBorderPainted(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 150, 30));

        jTabbedPane1.addTab("Data Pasangan Calon", jPanel4);

        jPanel3.setBackground(new java.awt.Color(44, 62, 80));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 202, 24));
        jLabel1.setText("Nama Calon Wakil Ketua");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 180, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 202, 24));
        jLabel2.setText("Nama Calon Ketua");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, 20));

        txtNamaWaka.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtNamaWaka, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 180, 30));

        txtNamaKa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtNamaKa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 180, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 202, 24));
        jLabel3.setText("Gambar Calon");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 140, 20));

        txtGbCalon.setEditable(false);
        txtGbCalon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtGbCalon.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel3.add(txtGbCalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 180, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(247, 202, 24));
        jLabel4.setText("Nomor Calon");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 140, 20));

        txtNoCalon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.add(txtNoCalon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 180, 30));

        lbFotoPasangan.setForeground(new java.awt.Color(255, 255, 255));
        lbFotoPasangan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFotoPasangan.setText("FOTO PASANGAN CALON");
        lbFotoPasangan.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));
        jPanel3.add(lbFotoPasangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 270, 288));

        jButton7.setBackground(new java.awt.Color(123, 239, 178));
        jButton7.setText("Browse");
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 80, 30));

        jButton8.setBackground(new java.awt.Color(123, 239, 178));
        jButton8.setText("Input");
        jButton8.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 270, 30));

        jButton11.setBackground(new java.awt.Color(150, 40, 27));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Hapus Data Calon");
        jButton11.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(123, 239, 178)));
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 270, 30));

        jTabbedPane1.addTab("  Input Data Calon  ", jPanel3);

        jPanel6.setBackground(new java.awt.Color(44, 62, 80));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("Report", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 700, 560));

        jPanel2.setBackground(new java.awt.Color(247, 202, 24));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(44, 62, 80));
        jLabel5.setText("ADMIN DASHBOARD");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 220, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tbllogin_mdl = new DefaultTableModel();
        tbllogin_mdl.addColumn("NIM");
        tbllogin_mdl.addColumn("Nama");
        tbllogin_mdl.addColumn("Password");
        tbllogin_mdl.addColumn("Status");
        tbPemilih.setModel(tbllogin_mdl);
        tbPemilih.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbPemilih.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbPemilih.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbPemilih.getColumnModel().getColumn(3).setPreferredWidth(20);
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from login where nim like  '" + jTextField1.getText() + "%'");
            while (res.next()) {
                tbllogin_mdl.addRow(new Object[]{
                    res.getString("nim"),
                    res.getString("nama"),
                    res.getString("password"),
                    res.getString("confirm")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "A", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        this.dispose();
        new Persentase().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (new Persentase().isEnabled()) {
            new Persentase().dispose();
            new login().setVisible(true);
            this.dispose();
        } else {
            new Persentase().setVisible(false);
            new login().dispose();
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        TampilDataPemilih();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        new adminlogin().setVisible(true);
        adminlogin.jButton1.setText("Konfirmasi");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        new loadData().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_insertButtonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        FileNameExtensionFilter fileext = new FileNameExtensionFilter("File .jpg / .png", "jpg", "png");
        JFileChooser jfc = new JFileChooser("D:");
        jfc.addChoosableFileFilter(fileext);
        int r = jfc.showOpenDialog(jPanel1);
        if (r == JFileChooser.APPROVE_OPTION) {
            txtGbCalon.setText(jfc.getSelectedFile().getAbsolutePath());
            ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
            Image img1 = icon.getImage();
            Image img2 = img1.getScaledInstance(lbFotoPasangan.getWidth(), lbFotoPasangan.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(img2);
            lbFotoPasangan.setIcon(img);
            lbFotoPasangan.setText("");
            lbFotoPasangan.setBorder(null);
        } else {
            txtGbCalon.setText("");
            lbFotoPasangan.setIcon(null);
            lbFotoPasangan.setText("FOTO PASANGAN CALON");
            lbFotoPasangan.setBorder(new MatteBorder(3, 3, 3, 3, Color.white));
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if ("".equals(txtNamaKa.getText()) || "".equals(txtNamaWaka.getText()) || "".equals(txtNoCalon.getText()) || "".equals(txtGbCalon.getText())) {
            JOptionPane.showMessageDialog(this, "Data Calon Tidak Lengkap! Mohon Periksa Kembali", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                AddDataCalon();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Data Calon Berhasil Diinput", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            txtNamaKa.setText("");
            txtNamaWaka.setText("");
            txtNoCalon.setText("");
            txtGbCalon.setText("");
            lbFotoPasangan.setIcon(null);
            lbFotoPasangan.setText("FOTO PASANGAN CALON");
        }        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if ("".equals(txtNIMTambah.getText()) || "".equals(txtNamaTambah.getText())) {
            JOptionPane.showMessageDialog(this, "Data Peserta Tidak Lengkap! Mohon Periksa Kembali", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            AddDataPemilih();
            JOptionPane.showMessageDialog(this, "Data Pemilih Berhasil Diinput", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            txtNIMTambah.setText("");
            txtNamaTambah.setText("");
        }        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbFotoPasangan;
    private javax.swing.JLabel lbFotoPasangan1;
    private javax.swing.JLabel lbPass;
    private javax.swing.JTable tbPaslon;
    private javax.swing.JTable tbPemilih;
    private javax.swing.JTextField txtGbCalon;
    private javax.swing.JTextField txtNIMTambah;
    private javax.swing.JTextField txtNamaKa;
    private javax.swing.JTextField txtNamaPaslon;
    private javax.swing.JTextField txtNamaTambah;
    private javax.swing.JTextField txtNamaWaka;
    private javax.swing.JTextField txtNoCalon;
    private javax.swing.JTextField txtNoUrut;
    // End of variables declaration//GEN-END:variables
}
