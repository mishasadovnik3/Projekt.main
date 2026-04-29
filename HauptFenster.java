package paket_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class HauptFenster extends JFrame {
	private Themen themen1 = new Themen();
	private JPanel contentPane;
	private JTextField txtA;
	private JCheckBox cbTier = new JCheckBox("Tier");
	private JCheckBox cbInfo = new JCheckBox("Informatik");
	private JCheckBox cbAllg = new JCheckBox("Allgemeinwissen");

	public HauptFenster() {
		boolean mOK = themen1.getAktuelleZugirff().oeffeneDB();
		if(mOK)
		{JOptionPane.showMessageDialog(null, "DB ist geöffnet");
		}
		else
		{JOptionPane.showMessageDialog(null, "DB kann nicht geöffnet werden");}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btWortG = new JButton("Wort \nGenerieren");
		btWortG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btWortG.setBounds(571, 172, 94, 23);
		contentPane.add(btWortG);
		

		cbInfo.setBounds(33, 18, 97, 23);
		contentPane.add(cbInfo);
		
		cbTier.setBounds(157, 18, 52, 23);
		contentPane.add(cbTier);
		

		cbAllg.setBounds(241, 18, 122, 23);
		contentPane.add(cbAllg);
		
		JLabel lblNewLabel = new JLabel("________");
		lblNewLabel.setFont(new Font("SAPDings", Font.PLAIN, 15));
		lblNewLabel.setBounds(183, 407, 169, 16);
		contentPane.add(lblNewLabel);
		
		txtA = new JTextField();
		txtA.setText("a");
		txtA.setBounds(508, 83, 26, 28);
		contentPane.add(txtA);
		txtA.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(33, 68, 495, 330);
		contentPane.add(lblNewLabel_1);
		
		JButton btSchliessen = new JButton("Schliessen");
		btSchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean mOK = themen1.getAktuelleZugirff().schliesseDB();
				if(mOK)
				{JOptionPane.showMessageDialog(null, "DB ist geschlossen");
				}
				else
				{JOptionPane.showMessageDialog(null, "DB kann nicht geschlossen werden");}
				System.exit(0);
			}
		});
		btSchliessen.setBounds(510, 413, 90, 28);
		contentPane.add(btSchliessen);
		
		JButton btLeeren = new JButton("Leeren");
		btLeeren.setBounds(562, 340, 90, 28);
		contentPane.add(btLeeren);
		
		JButton btBuchstabe = new JButton("Buchstabe best\u00E4tigen");
		btBuchstabe.setBounds(562, 50, 90, 28);
		contentPane.add(btBuchstabe);
	}
	
	private ResultSet MWort()
	{ResultSet rWort;
	 rWort = themen1.Wortablesen();
		for(int i = 0; )
	};
}
