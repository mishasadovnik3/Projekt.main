package paket_Hang;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.Font;

public class HauptKlasse extends JFrame
{
  Wort wort1 = new Wort();

  private String wort = "";
  private String hinweis = "";
  private char[] geratenesWort;
  private int versuche = 6;
  private String[] Buchstaben = {"A" , "B", "C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","Ä","Ö","Ü","ß"};
  private JLabel[] lbA= new JLabel[30];
  
  private JPanel contentPane;
  private JLabel lbWort;
  private JLabel lbVersuche;
  private JLabel lbHinweis;
  private JTextField tfBuchstabe;
  private JButton btOK;
  private JButton btStart;
  private JComboBox cbKategorie;
  private HangmanZeichnung zeichnung;

  public HauptKlasse()
  {
    boolean mOk;

    mOk = wort1.getAktuellerZugriff().oeffneDB();

    if (mOk==true)
    { JOptionPane.showMessageDialog(null, "Datenbank geöffnet");
    }
    else
    { JOptionPane.showMessageDialog(null, "Datenbank kann nicht geöffnet werden");
    }

    setTitle("Hangman");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setBounds(300, 150, 650, 430);

    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel label1 = new JLabel("Kategorie:");
    label1.setBounds(390, 40, 100, 25);
    contentPane.add(label1);

    cbKategorie = new JComboBox();
    cbKategorie.addItem("Tiere");
    cbKategorie.addItem("Allgemein");
    cbKategorie.addItem("Informatik");
    cbKategorie.setBounds(470, 40, 120, 25);
    contentPane.add(cbKategorie);

    btStart = new JButton("Start");
    btStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      { 
    	  neuesSpiel();
      }
    });
    btStart.setBounds(390, 75, 200, 25);
    contentPane.add(btStart);

    lbWort = new JLabel("");
    lbWort.setBounds(250, 20, 200, 30);
    contentPane.add(lbWort);

    zeichnung = new HangmanZeichnung();
    zeichnung.setBounds(20, 60, 350, 250);
    contentPane.add(zeichnung);

    lbVersuche = new JLabel("Versuche: " + versuche);
    lbVersuche.setFont(new Font("Tahoma", Font.PLAIN, 12));
    lbVersuche.setBounds(390, 110, 180, 25);
    contentPane.add(lbVersuche);

    lbHinweis = new JLabel("Hinweis:");
    lbHinweis.setBounds(390, 150, 230, 25);
    contentPane.add(lbHinweis);

    JLabel label2 = new JLabel("Buchstabe:");
    label2.setBounds(390, 190, 100, 25);
    contentPane.add(label2);

    tfBuchstabe = new JTextField();
    tfBuchstabe.setBounds(470, 190, 50, 25);
    contentPane.add(tfBuchstabe);

    btOK = new JButton("OK");
    btOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      { pruefen();
      }
    });
    btOK.setBounds(390, 230, 130, 25);
    contentPane.add(btOK);
    

    int xK = 390;
    int yK = 280;
    for(int i = 0; i < 30 ; i++)
    { 
    	lbA[i] = new JLabel();

    lbA[i].setBounds(xK, yK, 31, 14); xK= xK+ 15; 
    if(xK >= 600)
    {xK = 390;
    yK = yK + 20;}
    lbA[i].setText(Buchstaben[i]);
    contentPane.add(lbA[i]);
    }
    
    JButton btSchliessen = new JButton("Schliessen");
    btSchliessen.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    	    boolean mOk = wort1.getAktuellerZugriff().schliessenDB();

    	    if (mOk==true)
    	    { JOptionPane.showMessageDialog(null, "Datenbank geschlossen");
    	    }
    	    else
    	    { JOptionPane.showMessageDialog(null, "Datenbank kann nicht geschlossen werden");
    	    }
    	    System.exit(0);
    	}
    });
    btSchliessen.setBounds(494, 355, 130, 25);
    contentPane.add(btSchliessen);

    setVisible(true);
  }

  private void neuesSpiel()
  {ResultSet rsW;
  	contentPane.add(lbA[0]);
    wort1.setKategorie(String.valueOf(cbKategorie.getSelectedItem()));
    rsW = wort1.sucheZufallsWort();

    try
    { rsW.next();
      wort = rsW.getString("wort");
      hinweis = rsW.getString("hinweis");

      wort = wort.toUpperCase();
      geratenesWort = new char[wort.length()];

      for (int i = 0; i < geratenesWort.length; i++)
      { geratenesWort[i] = '_';
      }

      versuche = 6;
      zeichnung.setFehler(0);
      anzeigen();
    }
    catch(Exception e)
    { JOptionPane.showMessageDialog(null, "Kein Wort gefunden");
    }
  }

  private void pruefen()
  { String eingabe;
    char buchstabe;
    boolean gefunden = true;

    eingabe = tfBuchstabe.getText().toUpperCase();
    tfBuchstabe.setText("");

    if (!eingabe.equals(""))
    { buchstabe = eingabe.charAt(0);
      gefunden = false;

      for (int i = 0; i < wort.length(); i++)
      { if (wort.charAt(i) == buchstabe)
        { geratenesWort[i] = buchstabe;
          gefunden = true;
        }
 		}  
      }

      if (gefunden == false)
      { versuche = versuche - 1;
        zeichnung.setFehler(6 - versuche);

        for(int i = 0; i < 30; i++)
            {if(eingabe.equals(Buchstaben[i]))
        	{ System.out.println(i);
        	Buchstaben[i] = "<html><s><p style=\"color: red;\">"+ Buchstaben[i] + "</p></s></hmtl>";
        	System.out.println(Buchstaben[i]);
            lbA[i].setText(Buchstaben[i]);
        	}
    	}
      }else {
            for(int i = 0; i < 30; i++)
            {if(eingabe.equals(Buchstaben[i]))
        	{ System.out.println(i);
        	Buchstaben[i] = "<html><s><p style=\"color: green;\">"+ Buchstaben[i] + "</p></s></hmtl>";
        	System.out.println(Buchstaben[i]);
            lbA[i].setText(Buchstaben[i]);
        	}
    	}
       }
    	

        
        
      anzeigen();

    }


  private void anzeigen()
  { lbWort.setText(String.valueOf(geratenesWort));
    lbVersuche.setText("Versuche: " + versuche);
    lbHinweis.setText("Hinweis: " + hinweis);

    if (String.valueOf(geratenesWort).equals(wort))
    { JOptionPane.showMessageDialog(null, "Gewonnen!");
    }

    if (versuche == 0)
    { JOptionPane.showMessageDialog(null, "Verloren! Wort war: " + wort);
    }
  }

  class HangmanZeichnung extends JPanel
  {
    private int fehler = 0;

    public void setFehler(int pFehler)
    { fehler = pFehler;
      repaint();
    }

    protected void paintComponent(Graphics g)
    { super.paintComponent(g);

      g.drawLine(50, 220, 150, 220);
      g.drawLine(100, 220, 100, 20);
      g.drawLine(100, 20, 200, 20);
      g.drawLine(200, 20, 200, 50);

      if (fehler > 0)
      { g.drawOval(175, 50, 50, 50);
      }
      if (fehler > 1)
      { g.drawLine(200, 100, 200, 160);
      }
      if (fehler > 2)
      { g.drawLine(200, 120, 170, 150);
      }
      if (fehler > 3)
      { g.drawLine(200, 120, 230, 150);
      }
      if (fehler > 4)
      { g.drawLine(200, 160, 170, 210);
      }
      if (fehler > 5)
      { g.drawLine(200, 160, 230, 210);
      }
    }
  }
}
