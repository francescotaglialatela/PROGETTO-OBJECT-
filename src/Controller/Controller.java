package Controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.*;
import ImplementazionePostgresDAO.*;
import Model.*;

import Database.ConnessioneDatabase;


public class Controller {
	
	Insegnante i;
	Studente s;
	Test t;
	
	
	public Controller() {};
	
	/**
	 * Nuovo test.
	 * 
	 * @param nometest
	 * @param i
	 */
	public void nuovoTest(String nometest, Insegnante i) {
		/*
		t = new Test(nometest,i);
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		ins.CreazioneTestDB(t);
		*/
	}
	
	/**
	 * Elenco domande aperte di un determinato test.
	 * 
	 * @param nometest
	 * @return
	 */
	public ArrayList<String> getDomandaApertaTest(String nometest) {
		ArrayList<String> elencoDomandeAperte = new ArrayList();
		TestDAO t = new TestImplementazionePostgresDAO();
		elencoDomandeAperte=t.getElencoQuizApertoDB(nometest);
		
		return elencoDomandeAperte;
	}
	
	/**
	 * Elenco Domande e opzioni di un quiz multiplo di un determinato test.
	 * 
	 * @param nometest
	 * @return
	 */
	public ArrayList<String> getDomandeMultipleTest(String nometest) {
		ArrayList<String> elencoDomandeMultiple = new ArrayList();
		TestDAO t = new TestImplementazionePostgresDAO();
		elencoDomandeMultiple = t.getElencoQuizMultiploDB(nometest);
		
		return elencoDomandeMultiple;
	}
	
	/**
	 * Registrazione Studente
	 * 
	 * @param u
	 * @param p
	 * @param e
	 * @param n
	 * @param c
	 * @param se
	 * @param t
	 */
	
	public void registrazioneStudente(String u, String p, String e, String n, String c, char se, String t) {
		s= new Studente(u,p,e,n,c,se,t);
		StudenteDAO nuovoStu = new StudenteImplementazionePostgresDAO();
		nuovoStu.RegistrazioneStudenteDB(s);
		
	}
	
	/**
	 * Login di uno studente.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginStudente(String username, String password) {
		boolean res;
		StudenteDAO stu = new StudenteImplementazionePostgresDAO();
		res = stu.LoginStudenteDB(username, password);
		
		return res;
	}
	
	/**
	 * Registrazione di un insegnante.
	 * 
	 * @param u
	 * @param p
	 * @param e
	 * @param n
	 * @param c
	 * @param s
	 * @param t
	 * @param in
	 */
	
	public void registrazioneInsegnante(String u, String p, String e, String n, String c, char s, String t, String in) {
		i = new Insegnante(u,p,e,n,c,s,t,in);
		InsegnanteDAO nuovoIns = new InsegnanteImplementazionePostgresDAO();
		nuovoIns.RegistrazioneInsegnanteDB(i);
	}
	
	/**
	 * Login di un insegnante.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	
	public boolean loginInsegnante(String username, String password) {
		boolean res;
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		res = ins.LoginInsegnanteDB(username, password);
		
		return res;
	}
	
	/**
	 * Creare un quiz multiplo e aggiungerlo al test creato
	 * 
	 * @param d
	 * @param t
	 * @param pc
	 * @param ps
	 * @param txtop
	 * @param nomet
	 * @param n
	 * @return
	 */
	public int  aggiungiQuizMultiplo(String d, double pc, double ps, String nomet, char rispostaC) {
		Multiplo m = new Multiplo( d,  pc,  ps,  "",rispostaC);
		InsegnanteDAO  ins = new InsegnanteImplementazionePostgresDAO();
		int idquizm = ins.addQuizMultiploDB(m, nomet);
		return idquizm;
	}
	
	/**
	 * Creare le opzioni di un quiz multiplo
	 * 
	 * @param text
	 * @param idquizm
	 * @param nm
	 * @param op
	 */
	public void aggiungiOpzione(String text, int idquizm, char op) {
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		ins.addOpzione(text, idquizm, op);
	}
	
	/**
	 * Creazione di un quiz aperto 
	 * 
	 * @param d
	 * @param t
	 * @param pmin
	 * @param pmax
	 * @param mleng
	 * @param rispostaC
	 * @param nometest
	 * @return
	 */
	/*
	public int aggiungiQuizAperto(String d, String t, float pmin, float pmax, int mleng, char rispostaC, String nometest) {
		Aperto a = new Aperto(d,t,pmin,pmax,mleng,rispostaC);
		InsegnanteDAO ins = new InsegnanteImplementazionePostgresDAO();
		int idquiza = ins.addQuizApertoDB(a, nometest);
		
		return idquiza;
	}
	
	
	*/
}
