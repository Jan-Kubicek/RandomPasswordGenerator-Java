package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class MainFrame extends JFrame {
    //Komponenty
    JTextField tfPassword, tfPocetZnaku;
    JButton btnGenerovat;
    JCheckBox cbCisla, cbSpecka, cbVelkaPismena, cbDiakritika;
    JPanel pCenter;

    //Konstruktor
    public MainFrame() {
        InitGui();
        setTitle("Generování hesel");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //GUI
    public void InitGui() {
        tfPassword = new JTextField("Vaše nové heslo");
        add(tfPassword, BorderLayout.NORTH);

        pCenter = new JPanel(new GridLayout(3, 2));
        tfPocetZnaku = new JTextField("Zadejte počet znaků hesla");
        pCenter.add(tfPocetZnaku);
        btnGenerovat = new JButton("Generovat");
        btnGenerovat.addActionListener(e -> {
            int pocetZnaku = 0;
            int poziceCisla = 0;
            int poziceSpecky = 0;
            int poziceVelkehoP = 0;
            int poziceDiakritiky = 0;
            String heslo = "";
            pocetZnaku = Integer.parseInt(tfPocetZnaku.getText());
            String[] pismena = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "y", "z"};
            int[] cisla = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
            String[] specka = new String[]{"@", "/", "-", "#", "&", "!", "_", "."};
            String[] Vpismena = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z"};
            String[] Diakritika = new String[]{"á", "č", "ě", "é", "í", "ř", "š", "ů", "ú", "ž"};
            for (int i = 0; i < pocetZnaku; i++) {
                Random random = new Random();
                int random_int1 = random.nextInt(pismena.length);
                String pismeno = pismena[random_int1];
                heslo += pismeno;
            }
            if (cbCisla.isSelected()) { //Přidání čísla
                Random random = new Random();
                int random_int1 = random.nextInt(cisla.length);
                int random_int2 = random.nextInt(pocetZnaku);
                char[] hesloChars = heslo.toCharArray();
                char[] cisloChars = String.valueOf(random_int1).toCharArray();
                hesloChars[random_int2] = cisloChars[0];
                poziceCisla = random_int2;
                heslo = String.valueOf(hesloChars);
            }
            if (cbSpecka.isSelected()) {//Přidání speciálního znaku
                Random random = new Random();
                int random_int1 = random.nextInt(pocetZnaku);
                int random_int2 = random.nextInt(specka.length);
                if (random_int1 == poziceCisla) {
                        random_int1 = random.nextInt(pocetZnaku);
                }
                char[] hesloChars = heslo.toCharArray();
                char[] chars = specka[random_int2].toCharArray();
                hesloChars[random_int1] = chars[0];
                poziceSpecky = random_int1;
                heslo = String.valueOf(hesloChars);
            }
            if(cbVelkaPismena.isSelected()) { //Pridani velkoho pismena
                Random ranom = new Random();
                int random_int1 = ranom.nextInt(pocetZnaku); //MÍSTO velkého písmena
                int random_int2 = ranom.nextInt(Vpismena.length);
                if(random_int1 == poziceCisla || random_int1 == poziceSpecky){
                    random_int1 = ranom.nextInt(pocetZnaku);
                }
                char[] hesloChars = heslo.toCharArray();
                char[] chars = Vpismena[random_int2].toCharArray();
                hesloChars[random_int1] = chars[0];
                poziceVelkehoP = random_int1;
                heslo = String.valueOf(hesloChars);
            }
            if(cbDiakritika.isSelected()){
                Random random = new Random();
                int random_int1 = random.nextInt(pocetZnaku); //pozicee
                int random_int2 = random.nextInt(Diakritika.length);
                if(random_int1 == poziceCisla || random_int1 == poziceSpecky || random_int1 == poziceVelkehoP ){
                    random_int1 = random.nextInt(pocetZnaku);
                }
                char[] hesloChars = heslo.toCharArray();
                char[] chars = Diakritika[random_int2].toCharArray();
                hesloChars[random_int1] = chars[0];
                poziceDiakritiky = random_int1;
                heslo = String.valueOf(hesloChars);
            }
            tfPassword.setText(heslo);
        });
        pCenter.add(btnGenerovat);
        cbCisla = new JCheckBox("Čísla");
        pCenter.add(cbCisla);
        cbSpecka = new JCheckBox("Speciální znaky");
        pCenter.add(cbSpecka);
        cbVelkaPismena = new JCheckBox("Velká písmena");
        pCenter.add(cbVelkaPismena);
        cbDiakritika = new JCheckBox("Diakritika");
        pCenter.add(cbDiakritika);

        add(pCenter, BorderLayout.CENTER);

        pack();
    }

    //Main
    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
