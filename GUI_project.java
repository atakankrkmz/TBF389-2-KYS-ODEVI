/**
 *Text genereted by Simple GUI Extension for BlueJ
 */
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;
import java.util.*;
import Utils.*;

public class GUI_project extends JFrame {
    private JMenuBar menuBar;
    private JLabel label1;
    private JButton btnOduncAl;
    private JButton btnRezerveEt;
    private JButton btnGuncelle;
    private JComboBox cbKaynakDurum;
    private JComboBox cbKitap;
    private JComboBox cbUye;
    private JComboBox cbUyeTuru;
    private JComboBox cbYazar;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    private OduncKontrolor oduncKontrolor;
    private KaynakKontrolor kaynakKontrolor;
    private KisiKontrolor kisiKontrolor;
    private OduncAlListener oduncAlListener;
    private RezerveEtListener rezerveEtListener;
    private GuncelleListener guncelleListener;
    
    private KutuphaneUyesi selectedUye;
    private YaziliKaynak selectedKaynak;
    //Constructor 
    public GUI_project(){

        oduncKontrolor = new OduncKontrolor();
        kaynakKontrolor = new KaynakKontrolor();
        kisiKontrolor = new KisiKontrolor();
        oduncAlListener = new OduncAlListener();
        rezerveEtListener = new RezerveEtListener();
        guncelleListener = new GuncelleListener();

        this.setTitle("Nesne Yonelimli Programlama TBF389-2 | Kutuphane Yonetim Sistemi");
        
        this.pack();
        this.setSize(200,350);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(604,600));
        contentPane.setBackground(new Color(255,255,255));

        label1 = new JLabel();
        label1.setBounds(24,5,90,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Kitap Adi");
        label1.setVisible(true);

        btnOduncAl = new JButton();
        btnOduncAl.addActionListener(oduncAlListener);
        btnOduncAl.setBounds(17,20,150,35);
        btnOduncAl.setBackground(new Color(214,217,223));
        btnOduncAl.setForeground(new Color(0,0,0));
        btnOduncAl.setEnabled(true);
        btnOduncAl.setFont(new Font("sansserif",0,12));
        btnOduncAl.setText("Odunc Al");
        btnOduncAl.setVisible(true);

        btnRezerveEt = new JButton();
        btnRezerveEt.addActionListener(rezerveEtListener);
        btnRezerveEt.setBounds(17,65,150,35);
        btnRezerveEt.setBackground(new Color(214,217,223));
        btnRezerveEt.setForeground(new Color(0,0,0));
        btnRezerveEt.setEnabled(true);
        btnRezerveEt.setFont(new Font("sansserif",0,12));
        btnRezerveEt.setText("Rezerve Et");
        btnRezerveEt.setVisible(true);
        
        btnGuncelle = new JButton();
        btnGuncelle.addActionListener(guncelleListener);
        btnGuncelle.setBounds(17,110,150,35);
        btnGuncelle.setBackground(new Color(214,217,223));
        btnGuncelle.setForeground(new Color(0,0,0));
        btnGuncelle.setEnabled(true);
        btnGuncelle.setFont(new Font("sansserif",0,12));
        btnGuncelle.setText("Guncelle");
        btnGuncelle.setVisible(true);

        cbKaynakDurum = new JComboBox();
        cbKaynakDurum.setBounds(23,191,150,35);
        cbKaynakDurum.setBackground(new Color(214,217,223));
        cbKaynakDurum.setForeground(new Color(0,0,0));
        cbKaynakDurum.setEnabled(true);
        cbKaynakDurum.setFont(new Font("sansserif",0,12));
        cbKaynakDurum.setVisible(true);
        cbKaynakDurum.addItem(YaziliKaynakDurum.OGRENCIDE);
        cbKaynakDurum.addItem(YaziliKaynakDurum.REZERVE);
        cbKaynakDurum.addItem(YaziliKaynakDurum.RAFTA);
        cbKaynakDurum.addItem(YaziliKaynakDurum.ODUNCALINAMAZ);

        cbKitap = new JComboBox();
        cbKitap.setBounds(20,42,150,35);
        cbKitap.setBackground(new Color(214,217,223));
        cbKitap.setForeground(new Color(0,0,0));
        cbKitap.setEnabled(true);
        cbKitap.setFont(new Font("sansserif",0,12));
        cbKitap.setVisible(true);

        // Butun kitaplari comboBox'a ekle
        DataResult<java.util.ArrayList<YaziliKaynak>> dataResult = kaynakKontrolor.getYaziliKaynaklar();
        ArrayList<YaziliKaynak> yaziliKaynaklar = dataResult.getData();
        int size = yaziliKaynaklar.size();

        for(int i = 0; i < size; i++){
            YaziliKaynak yk = yaziliKaynaklar.get(i);
            cbKitap.addItem(yk.getAdi());
        }
        
        cbKitap.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String ss = cbKitap.getSelectedItem().toString();
                    DataResult<YaziliKaynak> result = kaynakKontrolor.getYaziliKaynakByName(ss);
                    if(!result.isSuccess()){
                        JOptionPane.showMessageDialog(null, result.getMessage(),"Hata",JOptionPane.ERROR_MESSAGE);
                    }
                    YaziliKaynak selectedYaziliKaynak = result.getData();
                    
                    Yazar currentYazar = selectedYaziliKaynak.getYazar();
                    
                    cbYazar.setSelectedItem(currentYazar.getAdi() + " " + currentYazar.getSoyadi());
                    cbKaynakDurum.setSelectedItem(selectedYaziliKaynak.getDurum());
                    selectedKaynak = result.getData();
                }
            });

        cbUye = new JComboBox();
        cbUye.setBounds(13,60,150,35);
        cbUye.setBackground(new Color(214,217,223));
        cbUye.setForeground(new Color(0,0,0));
        cbUye.setEnabled(true);
        cbUye.setFont(new Font("sansserif",0,12));
        cbUye.setVisible(true);

        // Uyeleri comboBox'a ekle
        DataResult<java.util.ArrayList<Ogrenci>> dataResult2 = kisiKontrolor.getOgrenciler();
        ArrayList<Ogrenci> ogrenciler = new ArrayList<Ogrenci>(dataResult2.getData());
        for(Ogrenci ogrenci: ogrenciler){
            cbUye.addItem(ogrenci.getAdi() + " " + ogrenci.getSoyadi());
        }

        cbUye.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String[] ss = cbUye.getSelectedItem().toString().split(" ");
                    DataResult<Ogrenci> result = kisiKontrolor.getOgrenciByName(ss[0]);
                    if(!result.isSuccess()){
                        JOptionPane.showMessageDialog(null, result.getMessage(),"Hata",JOptionPane.ERROR_MESSAGE);
                    }
                    cbUyeTuru.setSelectedItem("Ogrenci");
                    
                    selectedUye = result.getData();
                }
            });

        cbUyeTuru = new JComboBox();
        cbUyeTuru.setBounds(16,146,150,35);
        cbUyeTuru.setBackground(new Color(214,217,223));
        cbUyeTuru.setForeground(new Color(0,0,0));
        cbUyeTuru.setEnabled(false);
        cbUyeTuru.setFont(new Font("sansserif",0,12));
        cbUyeTuru.setVisible(true);
        cbUyeTuru.addItem("Ogretim Gorevlisi");
        cbUyeTuru.addItem("Ogrenci");

        cbYazar = new JComboBox();
        cbYazar.setBounds(22,117,150,35);
        cbYazar.setBackground(new Color(214,217,223));
        cbYazar.setForeground(new Color(0,0,0));
        cbYazar.setEnabled(false);
        cbYazar.setFont(new Font("sansserif",0,12));
        cbYazar.setVisible(true);
        
        // Yazarlari comboBox'a ekle
        DataResult<java.util.ArrayList<Yazar>> dataResult3 = kisiKontrolor.getYazarlar();
        ArrayList<Yazar> yazarlar = new ArrayList<Yazar>(dataResult3.getData());
        for(Yazar yazar: yazarlar){
            cbYazar.addItem(yazar.getAdi() + " " + yazar.getSoyadi());
        }
        

        label2 = new JLabel();
        label2.setBounds(24,81,90,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Yazar");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(25,154,90,35);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("sansserif",0,12));
        label3.setText("Kaynak Durum");
        label3.setVisible(true);

        label4 = new JLabel();
        label4.setBounds(17,20,90,35);
        label4.setBackground(new Color(214,217,223));
        label4.setForeground(new Color(0,0,0));
        label4.setEnabled(true);
        label4.setFont(new Font("sansserif",0,12));
        label4.setText("Uye");
        label4.setVisible(true);

        label5 = new JLabel();
        label5.setBounds(19,109,90,35);
        label5.setBackground(new Color(214,217,223));
        label5.setForeground(new Color(0,0,0));
        label5.setEnabled(true);
        label5.setFont(new Font("sansserif",0,12));
        label5.setText("Uye Turu");
        label5.setVisible(true);

        panel1 = new JPanel(null);
        panel1.setBorder(BorderFactory.createEtchedBorder(1));
        panel1.setBounds(5,5,200,255);
        panel1.setBackground(new Color(204,204,255));
        panel1.setForeground(new Color(0,0,0));
        panel1.setEnabled(true);
        panel1.setFont(new Font("sansserif",0,12));
        panel1.setVisible(true);

        panel2 = new JPanel(null);
        panel2.setBorder(BorderFactory.createEtchedBorder(1));
        panel2.setBounds(210,5,200,255);
        panel2.setBackground(new Color(255,204,204));
        panel2.setForeground(new Color(0,0,0));
        panel2.setEnabled(true);
        panel2.setFont(new Font("sansserif",0,12));
        panel2.setVisible(true);

        panel3 = new JPanel(null);
        panel3.setBorder(BorderFactory.createEtchedBorder(1));
        panel3.setBounds(414,45,185,170);
        panel3.setBackground(new Color(204,255,153));
        panel3.setForeground(new Color(0,0,0));
        panel3.setEnabled(true);
        panel3.setFont(new Font("sansserif",0,12));
        panel3.setVisible(true);

        //adding components to contentPane panel
        panel1.add(label1);
        panel3.add(btnOduncAl);
        panel3.add(btnRezerveEt);
        panel3.add(btnGuncelle);
        panel1.add(cbKaynakDurum);
        panel1.add(cbKitap);
        panel2.add(cbUye);
        panel2.add(cbUyeTuru);
        panel1.add(cbYazar);
        panel1.add(label2);
        panel1.add(label3);
        panel2.add(label4);
        panel2.add(label5);
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //method for generate menu
    public void generateMenu(){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open   ");
        JMenuItem save = new JMenuItem("Save   ");
        JMenuItem exit = new JMenuItem("Exit   ");
        JMenuItem preferences = new JMenuItem("Preferences   ");
        JMenuItem about = new JMenuItem("About   ");

        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        tools.add(preferences);
        help.add(about);

        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(help);
    }

    public static void main(String[] args){
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI_project();
                }
            });
    }

    private class OduncAlListener implements ActionListener
    {
        public void actionPerformed  (ActionEvent event)
        {
            if(event.getSource() == btnOduncAl)// Eğer butona tıklanmışsa
            {
                YaziliKaynak yaziliKaynak;
                if (selectedKaynak != null) {
                    yaziliKaynak = selectedKaynak;
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen işlem yapacağınız kaynağı seçiniz", "Kaynak Seçilmedi " , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                KutuphaneUyesi alacak;
                if (selectedUye != null) {
                    alacak = selectedUye;
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen işlem yapacağınız uyeyi seçiniz", "Uye Seçilmedi " , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Result result2 = oduncKontrolor.yaziliKaynakOduncVer(yaziliKaynak, alacak);
                if(result2.isSuccess()) {
                    cbKaynakDurum.setSelectedItem(YaziliKaynakDurum.OGRENCIDE);
                    JOptionPane.showMessageDialog(null, result2.getMessage(),"Başarili",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    JOptionPane.showMessageDialog(null, result2.getMessage(),"HATA",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private class RezerveEtListener implements ActionListener
    {
        public void actionPerformed  (ActionEvent event)
        {
            if(event.getSource() == btnRezerveEt)// Eğer butona tıklanmışsa
            {
                YaziliKaynak yaziliKaynak;
                if (selectedKaynak != null) {
                    yaziliKaynak = selectedKaynak;
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen işlem yapacağınız kaynağı seçiniz", "Kaynak Seçilmedi " , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                KutuphaneUyesi alacak;
                if (selectedUye != null) {
                    alacak = selectedUye;
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen işlem yapacağınız kaynağı seçiniz", "Kaynak Seçilmedi " , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Result result2 = oduncKontrolor.yaziliKaynakRezerveEt(yaziliKaynak, alacak);
                if (result2.isSuccess()) {
                    cbKaynakDurum.setSelectedItem(YaziliKaynakDurum.REZERVE);
                    JOptionPane.showMessageDialog(null, result2.getMessage(),"Başarili",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, result2.getMessage(),"HATA",JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }
    }
    
    private class GuncelleListener implements ActionListener
    {
        public void actionPerformed  (ActionEvent event)
        {
            if(event.getSource() == btnGuncelle)// Eğer butona tıklanmışsa
            {
                YaziliKaynak yaziliKaynak;
                if (selectedKaynak != null) {
                    yaziliKaynak = selectedKaynak;
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen işlem yapacağınız kaynağı seçiniz", "Kaynak Seçilmedi " , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                KutuphaneUyesi alacak;
                if (selectedUye != null) {
                    alacak = selectedUye;
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen işlem yapacağınız kaynağı seçiniz", "Kaynak Seçilmedi " , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String[] name = cbUye.getSelectedItem().toString().split(" ");
                KutuphaneUyesi uye = kisiKontrolor.getOgrenciByName(name[0]).getData();
                YaziliKaynak newKaynak = new YaziliKaynak(selectedKaynak.getId(), selectedKaynak.getAdi(), selectedKaynak.getYazar(), selectedKaynak.getOduncTarihi(), uye, YaziliKaynakDurum.valueOf(cbKaynakDurum.getSelectedItem().toString()));
                
                
                int n = JOptionPane.showConfirmDialog(null,
                "Degistirdiginiz ozellikler kaydedilsin mi?",null, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
                switch(n){
                    case 0:
                        //evet
                        Result res = kaynakKontrolor.yaziliKaynakGuncelle(selectedKaynak, newKaynak);
                        if (!res.isSuccess()) {
                            JOptionPane.showMessageDialog(null,res.getMessage(),"HATA",JOptionPane.ERROR_MESSAGE);
                            return;
                        }else {
                            JOptionPane.showMessageDialog(null, res.getMessage(),"Basarili!",JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case 1:
                        //hayir
                        return;
                    default:
                        JOptionPane.showMessageDialog(null,"Hatali seçim yapildi!","HATA",JOptionPane.ERROR_MESSAGE);
                        break;
                }
                
            }
        }
    }

}