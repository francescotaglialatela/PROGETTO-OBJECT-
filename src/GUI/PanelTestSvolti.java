package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.StudenteDAO;
import DAO.TestDAO;
import ImplementazionePostgresDAO.StudenteImplementazionePostgresDAO;
import ImplementazionePostgresDAO.TestImplementazionePostgresDAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class PanelTestSvolti extends JPanel {
	
	String username;
	private JTable nuova_tabella;
	private DefaultTableModel modeltabella;
	
	
	
	/**
	 * Create the panel.
	 */
	public PanelTestSvolti(String user, Account_Studente frame) {
		username=user;
		setVisible(true);
		setBackground(new Color(153, 102, 255));
		setSize(838,475);
		setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 102, 255));
		panel.setBounds(8, 8, 838, 468);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 10, 612, 421);
		panel.add(scrollPane);
		
		nuova_tabella = new JTable();
		nuova_tabella.setCellSelectionEnabled(true);
		nuova_tabella.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(nuova_tabella);
		nuova_tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nuova_tabella.setModel(modeltabella =new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Test", "Cognome","Punteggio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kekko\\Downloads\\wallpaperbetter.jpg"));
		lblNewLabel_1.setBounds(0, 0, 830, 475);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\kekko\\Downloads\\wallpaperbetter.jpg"));
		lblNewLabel.setBounds(0, 0, 838, 475);
		add(lblNewLabel);
		nuova_tabella.getColumnModel().getColumn(0).setResizable(false);
		
		
		StudenteDAO prova = new StudenteImplementazionePostgresDAO();
		TestDAO t = new TestImplementazionePostgresDAO();
		ArrayList testSvolti = new ArrayList();
		testSvolti = prova.VisualizzaTestSvoltiDB(username);
		
		
		if(testSvolti.size()==0) {
			//NIENTE
		}
		
		if(testSvolti.size()>=2) {
		Object[] tmp;
		int j=0;
		
		for(int i=0; i<testSvolti.size()/2;i++) {
			
			if(prova.valutazioneDB((int ) prova.VisualizzaTestSvoltiDB(username).get(j), prova.TrovaIdStudenteDB(username))==true) {
			 
			  tmp= new Object[] {t.getNomeTestDB((int ) testSvolti.get(j)),prova.TrovaCognomeStudenteDB(prova.TrovaIdStudenteDB(username)),prova.visionaPunteggioDB((int) prova.VisualizzaTestSvoltiDB(username).get(j),(int) prova.VisualizzaTestSvoltiDB(username).get(j+1))};
			modeltabella.addRow(tmp);
			}
			
			else
			{
				tmp= new Object[] {t.getNomeTestDB((int ) testSvolti.get(j)),prova.TrovaCognomeStudenteDB(prova.TrovaIdStudenteDB(username)),"In fase di valutazione"};
				
				modeltabella.addRow(tmp);
			}
			
			if(testSvolti.size()>2)
				j=j+2;
			
		}
		
		
	}
	
	}	
}

