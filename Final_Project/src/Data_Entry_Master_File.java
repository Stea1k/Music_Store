

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class Data_Entry_Master_File extends JFrame{
	
	final static String loginPanel = "Login";
	final static String userPanel = " ";
	
	public static JPanel contentPane;
	
	private JTextField txtYourUsername;
	private JTextField txtYourPassword;
	
	private boolean loggedIn = false;
	
	private JTextField UserName;
	private JTextField UserPass;
	private JTextField UserPhone;
	private JTextField Email;
	private JTextField searchEntry;
	private JTextField importFile;
	private JTextField cosignorName;
	private JTextField coPhoneNum;
	
	private JTable table;
	
	protected void resetUsername(){
		txtYourUsername.setText(" ");
	}
	protected void resetPassword(){
		txtYourPassword.setText(" ");
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Statement statement = null;
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<Music> timeList = new ArrayList<Music>();
		

		DataBaseConstructor.assignTables();
		DataBaseConstructor.createDataTables();
//		Music hellsbells = new Music("Hell's Bells", "ACDC");
		
		//checks database connection and table access.
//		try{
//			Class.forName(driver);
//			conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
//			statement = conn.createStatement();
//			System.out.println("connection established");
//			String createTable = "create Table MusicRecords (RecordName varchar(20), RecordArtist varchar(20))";
//			String getFromMusicRecords = "Select * from MusicRecords";
//			String addToRecords = "insert into MusicRecords Values('Hells Bells','ACDC')";
//			String del = "drop table MusicRecords";
//			try{
//				statement.executeUpdate(del);
//			}catch(SQLException e){
//				e.printStackTrace();
//				System.out.println("Table deletion failed");
//			}
//			try{
//				statement.executeUpdate(createTable);
//				System.out.println("Table Created Successfully");
//				try{
//					statement.executeUpdate(addToRecords);
//					System.out.println("Records added successfully");
//				}catch(SQLException e){
//					e.printStackTrace();
//					System.out.println("Table addition failed");
//				}
//				try{
//					statement.executeQuery(getFromMusicRecords);
//				}catch(SQLException e){
//					e.printStackTrace();
//					System.out.println("Table request failed");
//				}
//			}catch(SQLException e){
//				e.printStackTrace();
//				System.out.println("Table creation failed");
//			}
//		}catch(SQLException e){
//			e.printStackTrace(System.err);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Data_Entry_Master_File frame = new Data_Entry_Master_File();
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Data_Entry_Master_File() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//ExitListener thanks to Baarn from StackOverflow
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ExitListener());
		mnFile.add(mntmQuit);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				//----------------------------------------------------------------//
			}
		});
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnFile.add(mntmLogout);
		
		mnFile.add(mntmRefresh);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
//		if(!loggedIn){
//			mnTools.setVisible("false");
//		}
		
		JMenuItem mntmAddMusic = new JMenuItem("Add Music");
		mnTools.add(mntmAddMusic);
		
		JMenuItem mntmAddCosigner = new JMenuItem("Add Cosigner");
		mnTools.add(mntmAddCosigner);
		
		JMenuItem mntmSearchMusic = new JMenuItem("Search Music");
		mnTools.add(mntmSearchMusic);
		
		JMenuItem mntmClearDatabase = new JMenuItem("Clear Database");
		mnTools.add(mntmClearDatabase);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		JPanel loginCard = new JPanel();
		contentPane.add(loginCard, loginPanel);
		GridBagLayout gbl_loginCard = new GridBagLayout();
		gbl_loginCard.columnWidths = new int[]{204, 0};
		gbl_loginCard.rowHeights = new int[]{198, 0};
		gbl_loginCard.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_loginCard.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		loginCard.setLayout(gbl_loginCard);

		JPanel userCard = new JPanel();
		contentPane.add(userCard,userPanel);
		GridBagLayout gbl_userCard = new GridBagLayout();
		gbl_userCard.columnWidths = new int[]{413, 0};
		gbl_userCard.rowHeights = new int[]{34, 0, 0};
		gbl_userCard.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_userCard.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		userCard.setLayout(gbl_userCard);

		JPanel newUserCard = new JPanel();
		contentPane.add(newUserCard,"new User");
		GridBagLayout gbl_newUserCard = new GridBagLayout();
		gbl_newUserCard.columnWidths = new int[]{0, 0};
		gbl_newUserCard.rowHeights = new int[]{0, 0};
		gbl_newUserCard.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_newUserCard.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		newUserCard.setLayout(gbl_newUserCard);
		
		JPanel addUserFinish = new JPanel();
		contentPane.add(addUserFinish,"addUserFinish");
		GridBagLayout gbl_addUserFinish = new GridBagLayout();
		gbl_addUserFinish.columnWidths = new int[]{0, 0};
		gbl_addUserFinish.rowHeights = new int[]{0, 0, 0};
		gbl_addUserFinish.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_addUserFinish.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		addUserFinish.setLayout(gbl_addUserFinish);
		
		JPanel addMusic = new JPanel();
		contentPane.add(addMusic,"addMusic");
		GridBagLayout gbl_addMusic = new GridBagLayout();
		gbl_addMusic.columnWidths = new int[]{0, 0, 0, 0};
		gbl_addMusic.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_addMusic.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_addMusic.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addMusic.setLayout(gbl_addMusic);
		
		JPanel addCosignor = new JPanel();
		contentPane.add(addCosignor,"addcosignor");
		GridBagLayout gbl_addCosignor = new GridBagLayout();
		gbl_addCosignor.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_addCosignor.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_addCosignor.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_addCosignor.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addCosignor.setLayout(gbl_addCosignor);
		
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//Add Cosigner Start<------------------------------------------------------------------>
		////////////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblAddCosigner = new JLabel("Add Cosigner");
		GridBagConstraints gbc_lblAddCosigner = new GridBagConstraints();
		gbc_lblAddCosigner.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddCosigner.gridx = 1;
		gbc_lblAddCosigner.gridy = 0;
		addCosignor.add(lblAddCosigner, gbc_lblAddCosigner);
		
		JLabel lblCosignerName = new JLabel("Cosigner Name");
		GridBagConstraints gbc_lblCosignerName = new GridBagConstraints();
		gbc_lblCosignerName.anchor = GridBagConstraints.WEST;
		gbc_lblCosignerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosignerName.gridx = 1;
		gbc_lblCosignerName.gridy = 2;
		addCosignor.add(lblCosignerName, gbc_lblCosignerName);
		
		cosignorName = new JTextField();
		cosignorName.setText(" ");
		GridBagConstraints gbc_cosignorName = new GridBagConstraints();
		gbc_cosignorName.insets = new Insets(0, 0, 5, 5);
		gbc_cosignorName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cosignorName.gridx = 2;
		gbc_cosignorName.gridy = 2;
		addCosignor.add(cosignorName, gbc_cosignorName);
		cosignorName.setColumns(10);
		
		JLabel lblCosignerPhoneNumber = new JLabel("Cosigner Phone Number");
		GridBagConstraints gbc_lblCosignerPhoneNumber = new GridBagConstraints();
		gbc_lblCosignerPhoneNumber.anchor = GridBagConstraints.EAST;
		gbc_lblCosignerPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosignerPhoneNumber.gridx = 1;
		gbc_lblCosignerPhoneNumber.gridy = 3;
		addCosignor.add(lblCosignerPhoneNumber, gbc_lblCosignerPhoneNumber);
		
		coPhoneNum = new JTextField();
		coPhoneNum.setText(" ");
		GridBagConstraints gbc_coPhoneNum = new GridBagConstraints();
		gbc_coPhoneNum.insets = new Insets(0, 0, 5, 5);
		gbc_coPhoneNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_coPhoneNum.gridx = 2;
		gbc_coPhoneNum.gridy = 3;
		addCosignor.add(coPhoneNum, gbc_coPhoneNum);
		coPhoneNum.setColumns(10);
		
		JButton btnAddCosignor = new JButton("Add Cosignor");
		GridBagConstraints gbc_btnAddCosignor = new GridBagConstraints();
		gbc_btnAddCosignor.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddCosignor.gridx = 1;
		gbc_btnAddCosignor.gridy = 4;
		addCosignor.add(btnAddCosignor, gbc_btnAddCosignor);
		
		JButton btnReturnToSearch = new JButton("Return to Search");
		GridBagConstraints gbc_btnReturnToSearch = new GridBagConstraints();
		gbc_btnReturnToSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnReturnToSearch.gridx = 2;
		gbc_btnReturnToSearch.gridy = 4;
		addCosignor.add(btnReturnToSearch, gbc_btnReturnToSearch);
		
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//Add Cosigner End<-------------------------------------------------------------------->
		////////////////////////////////////////////////////////////////////////////////////////
		
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//Add Music Start<--------------------------------------------------------------------->
		////////////////////////////////////////////////////////////////////////////////////////
			
		JLabel lblCosigner = new JLabel("Cosigner");
		GridBagConstraints gbc_lblCosigner = new GridBagConstraints();
		gbc_lblCosigner.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosigner.gridx = 0;
		gbc_lblCosigner.gridy = 0;
		addMusic.add(lblCosigner, gbc_lblCosigner);
		
		JLabel lblAddMusicFrom = new JLabel("Add Music From File");
		GridBagConstraints gbc_lblAddMusicFrom = new GridBagConstraints();
		gbc_lblAddMusicFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddMusicFrom.gridx = 1;
		gbc_lblAddMusicFrom.gridy = 0;
		addMusic.add(lblAddMusicFrom, gbc_lblAddMusicFrom);
		
		JButton btnBackToLogin = new JButton("Return to search");
		GridBagConstraints gbc_btnBackToLogin = new GridBagConstraints();
		gbc_btnBackToLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnBackToLogin.gridx = 2;
		gbc_btnBackToLogin.gridy = 0;
		addMusic.add(btnBackToLogin, gbc_btnBackToLogin);
		
		JLabel successfailure = new JLabel(" ");
		GridBagConstraints gbc_successfailure = new GridBagConstraints();
		gbc_successfailure.insets = new Insets(0, 0, 5, 5);
		gbc_successfailure.gridx = 1;
		gbc_successfailure.gridy = 1;
		addMusic.add(successfailure, gbc_successfailure);
		
		JLabel lblFileSpecified = new JLabel("File Specified:");
		GridBagConstraints gbc_lblFileSpecified = new GridBagConstraints();
		gbc_lblFileSpecified.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileSpecified.anchor = GridBagConstraints.EAST;
		gbc_lblFileSpecified.gridx = 0;
		gbc_lblFileSpecified.gridy = 2;
		addMusic.add(lblFileSpecified, gbc_lblFileSpecified);
		
		importFile = new JTextField();
		importFile.setText(" ");
		GridBagConstraints gbc_importFile = new GridBagConstraints();
		gbc_importFile.insets = new Insets(0, 0, 5, 5);
		gbc_importFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_importFile.gridx = 1;
		gbc_importFile.gridy = 2;
		addMusic.add(importFile, gbc_importFile);
		importFile.setColumns(10);
		
		JButton btnImportFileData = new JButton("Import File Data");
		GridBagConstraints gbc_btnImportFileData = new GridBagConstraints();
		gbc_btnImportFileData.insets = new Insets(0, 0, 5, 0);
		gbc_btnImportFileData.gridx = 2;
		gbc_btnImportFileData.gridy = 2;
		addMusic.add(btnImportFileData, gbc_btnImportFileData);
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//Add Music End<---------------------------------------------------------------------->
		////////////////////////////////////////////////////////////////////////////////////////
		
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//NEW USER START<---------------------------------------------------------------------->
		////////////////////////////////////////////////////////////////////////////////////////
		JPanel newUserPanel = new JPanel();
		newUserPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_newUserPanel = new GridBagConstraints();
		gbc_newUserPanel.fill = GridBagConstraints.BOTH;
		gbc_newUserPanel.gridx = 0;
		gbc_newUserPanel.gridy = 0;
		newUserCard.add(newUserPanel, gbc_newUserPanel);
		GridBagLayout gbl_newUserPanel = new GridBagLayout();
		gbl_newUserPanel.columnWidths = new int[]{0, 133, 0};
		gbl_newUserPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_newUserPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_newUserPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		newUserPanel.setLayout(gbl_newUserPanel);
		
		JLabel lblEnterYourInformation = new JLabel("Enter Your Information Below");
		GridBagConstraints gbc_lblEnterYourInformation = new GridBagConstraints();
		gbc_lblEnterYourInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterYourInformation.gridx = 1;
		gbc_lblEnterYourInformation.gridy = 0;
		newUserPanel.add(lblEnterYourInformation, gbc_lblEnterYourInformation);
		
		JLabel newUserName = new JLabel("User Name:");
		GridBagConstraints gbc_newUserName = new GridBagConstraints();
		gbc_newUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newUserName.insets = new Insets(0, 0, 5, 5);
		gbc_newUserName.gridx = 0;
		gbc_newUserName.gridy = 1;
		newUserPanel.add(newUserName, gbc_newUserName);
		
		UserName = new JTextField(" ");
		UserName.setColumns(10);
		GridBagConstraints gbc_UserName = new GridBagConstraints();
		gbc_UserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserName.insets = new Insets(0, 0, 5, 0);
		gbc_UserName.gridx = 1;
		gbc_UserName.gridy = 1;
		newUserPanel.add(UserName, gbc_UserName);
		
		JLabel newPassword = new JLabel("Password:");
		GridBagConstraints gbc_newPassword = new GridBagConstraints();
		gbc_newPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPassword.insets = new Insets(0, 0, 5, 5);
		gbc_newPassword.gridx = 0;
		gbc_newPassword.gridy = 2;
		newUserPanel.add(newPassword, gbc_newPassword);
		
		UserPass = new JTextField(" ");
		UserPass.setColumns(10);
		GridBagConstraints gbc_UserPass = new GridBagConstraints();
		gbc_UserPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserPass.insets = new Insets(0, 0, 5, 0);
		gbc_UserPass.gridx = 1;
		gbc_UserPass.gridy = 2;
		newUserPanel.add(UserPass, gbc_UserPass);
		
		JLabel newPhoneNumber = new JLabel("Phone Number:");
		GridBagConstraints gbc_newPhoneNumber = new GridBagConstraints();
		gbc_newPhoneNumber.anchor = GridBagConstraints.EAST;
		gbc_newPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_newPhoneNumber.gridx = 0;
		gbc_newPhoneNumber.gridy = 3;
		newUserPanel.add(newPhoneNumber, gbc_newPhoneNumber);
		
		UserPhone = new JTextField();
		UserPhone.setColumns(10);
		GridBagConstraints gbc_UserPhone = new GridBagConstraints();
		gbc_UserPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserPhone.insets = new Insets(0, 0, 5, 0);
		gbc_UserPhone.gridx = 1;
		gbc_UserPhone.gridy = 3;
		newUserPanel.add(UserPhone, gbc_UserPhone);
		
		JLabel newEmail = new JLabel("Email:");
		GridBagConstraints gbc_newEmail = new GridBagConstraints();
		gbc_newEmail.anchor = GridBagConstraints.WEST;
		gbc_newEmail.insets = new Insets(0, 0, 5, 5);
		gbc_newEmail.gridx = 0;
		gbc_newEmail.gridy = 4;
		newUserPanel.add(newEmail, gbc_newEmail);
		
		Email = new JTextField();
		Email.setColumns(10);
		GridBagConstraints gbc_Email = new GridBagConstraints();
		gbc_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_Email.insets = new Insets(0, 0, 5, 0);
		gbc_Email.gridx = 1;
		gbc_Email.gridy = 4;
		newUserPanel.add(Email, gbc_Email);
		
		JButton newUserCancel = new JButton("Cancel");
		GridBagConstraints gbc_newUserCancel = new GridBagConstraints();
		gbc_newUserCancel.insets = new Insets(0, 0, 0, 5);
		gbc_newUserCancel.gridx = 0;
		gbc_newUserCancel.gridy = 5;
		newUserPanel.add(newUserCancel, gbc_newUserCancel);
		
		JButton newUserAdd = new JButton("Add");
		GridBagConstraints gbc_newUserAdd = new GridBagConstraints();
		gbc_newUserAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_newUserAdd.gridx = 1;
		gbc_newUserAdd.gridy = 5;
		newUserPanel.add(newUserAdd, gbc_newUserAdd);
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//NEW USER ENDT<---------------------------------------------------------------------->


	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//TOP PANEL<-------------------------------------------------------------------------->
		
		JPanel top_panel = new JPanel();
		GridBagConstraints gbc_top_panel = new GridBagConstraints();
		gbc_top_panel.anchor = GridBagConstraints.WEST;
		gbc_top_panel.gridx = 0;
		gbc_top_panel.gridy = 0;
		loginCard.add(top_panel, gbc_top_panel);
		top_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel figure_panel = new JPanel();
		top_panel.add(figure_panel);
		
		JPanel login_panel = new JPanel();
		top_panel.add(login_panel);
		GridBagLayout gbl_login_panel = new GridBagLayout();
		gbl_login_panel.columnWidths = new int[]{0, 0, 0};
		gbl_login_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_login_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_login_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		login_panel.setLayout(gbl_login_panel);
		
		
		JLabel lblUserName = new JLabel("User Name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		login_panel.add(lblUserName, gbc_lblUserName);
		
		txtYourUsername = new JTextField();
		txtYourUsername.setText("Your UserName");
		GridBagConstraints gbc_txtYourUsername = new GridBagConstraints();
		gbc_txtYourUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourUsername.gridx = 1;
		gbc_txtYourUsername.gridy = 0;
		login_panel.add(txtYourUsername, gbc_txtYourUsername);
		txtYourUsername.setColumns(10);
		
		JLabel lblPassWord = new JLabel("Password:");
		GridBagConstraints gbc_lblPassWord = new GridBagConstraints();
		gbc_lblPassWord.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassWord.gridx = 0;
		gbc_lblPassWord.gridy = 1;
		login_panel.add(lblPassWord, gbc_lblPassWord);
		
		txtYourPassword = new JTextField();
		txtYourPassword.setText("Your Password");
		GridBagConstraints gbc_txtYourPassword = new GridBagConstraints();
		gbc_txtYourPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourPassword.gridx = 1;
		gbc_txtYourPassword.gridy = 1;
		login_panel.add(txtYourPassword, gbc_txtYourPassword);
		txtYourPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		login_panel.add(btnLogin, gbc_btnLogin);
		
		JButton btnNewUser = new JButton("New User");
		GridBagConstraints gbc_btnNewUser = new GridBagConstraints();
		gbc_btnNewUser.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewUser.gridx = 1;
		gbc_btnNewUser.gridy = 2;
		login_panel.add(btnNewUser, gbc_btnNewUser);
		
		JLabel loginFail = new JLabel(" ");
		GridBagConstraints gbc_loginFail = new GridBagConstraints();
		gbc_loginFail.gridx = 1;
		gbc_loginFail.gridy = 3;
		login_panel.add(loginFail, gbc_loginFail);
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//TOP PANEL END <------------------------------------------------------------------>
		////////////////////////////////////////////////////////////////////////////////////////
		
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//LOGGED IN PANEL <---------------------------------------------------------------->
		////////////////////////////////////////////////////////////////////////////////////////

		JPanel logged_in = new JPanel();
		GridBagConstraints gbc_logged_in = new GridBagConstraints();
		gbc_logged_in.fill = GridBagConstraints.BOTH;
		gbc_logged_in.insets = new Insets(0, 0, 5, 0);
		gbc_logged_in.gridx = 0;
		gbc_logged_in.gridy = 0;
		userCard.add(logged_in, gbc_logged_in);
		GridBagLayout gbl_logged_in = new GridBagLayout();
		gbl_logged_in.columnWidths = new int[]{0, 351, 0};
		gbl_logged_in.rowHeights = new int[]{0, 0};
		gbl_logged_in.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_logged_in.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		logged_in.setLayout(gbl_logged_in);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		logged_in.add(lblUsername, gbc_lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.anchor = GridBagConstraints.EAST;
		gbc_btnLogout.gridx = 1;
		gbc_btnLogout.gridy = 0;
		logged_in.add(btnLogout, gbc_btnLogout);
		
		JPanel userCards = new JPanel();
		GridBagConstraints gbc_userCards = new GridBagConstraints();
		gbc_userCards.fill = GridBagConstraints.BOTH;
		gbc_userCards.gridx = 0;
		gbc_userCards.gridy = 1;
		userCard.add(userCards, gbc_userCards);
		GridBagLayout gbl_userCards = new GridBagLayout();
		gbl_userCards.columnWidths = new int[]{413, 0};
		gbl_userCards.rowHeights = new int[]{0, 0};
		gbl_userCards.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_userCards.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		userCards.setLayout(gbl_userCards);
		
		//LOGGED IN END <------------------------------------------------------------>
		
		//OUTPUT START <------------------------------------------------------------->
		
		JPanel output_Panel = new JPanel();
		GridBagConstraints gbc_output_Panel = new GridBagConstraints();
		gbc_output_Panel.anchor = GridBagConstraints.WEST;
		gbc_output_Panel.fill = GridBagConstraints.VERTICAL;
		gbc_output_Panel.gridx = 0;
		gbc_output_Panel.gridy = 0;
		userCards.add(output_Panel, gbc_output_Panel);
		GridBagLayout gbl_output_Panel = new GridBagLayout();
		gbl_output_Panel.columnWidths = new int[]{0, 312, 0};
		gbl_output_Panel.rowHeights = new int[]{0, 0};
		gbl_output_Panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_output_Panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		output_Panel.setLayout(gbl_output_Panel);
		
		JToolBar toolBar = new JToolBar(null, JToolBar.VERTICAL);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.fill = GridBagConstraints.VERTICAL;
		gbc_toolBar.insets = new Insets(0, 0, 0, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		output_Panel.add(toolBar, gbc_toolBar);
		
		JPanel panel = new JPanel();
		toolBar.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{119, 0};
		gbl_panel.rowHeights = new int[]{31, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSearchMusic = new JLabel("Search Music");
		GridBagConstraints gbc_lblSearchMusic = new GridBagConstraints();
		gbc_lblSearchMusic.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchMusic.gridx = 0;
		gbc_lblSearchMusic.gridy = 0;
		panel.add(lblSearchMusic, gbc_lblSearchMusic);
		
		JCheckBox chckbxSongTitle = new JCheckBox("Song Title");
		GridBagConstraints gbc_chckbxSongTitle = new GridBagConstraints();
		gbc_chckbxSongTitle.anchor = GridBagConstraints.WEST;
		gbc_chckbxSongTitle.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSongTitle.gridx = 0;
		gbc_chckbxSongTitle.gridy = 1;
		panel.add(chckbxSongTitle, gbc_chckbxSongTitle);
		
		JCheckBox chckbxArtistTitle = new JCheckBox("Artist");
		GridBagConstraints gbc_chckbxArtistTitle = new GridBagConstraints();
		gbc_chckbxArtistTitle.anchor = GridBagConstraints.WEST;
		gbc_chckbxArtistTitle.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxArtistTitle.gridx = 0;
		gbc_chckbxArtistTitle.gridy = 2;
		panel.add(chckbxArtistTitle, gbc_chckbxArtistTitle);
		
		searchEntry = new JTextField();
		searchEntry.setText(" ");
		GridBagConstraints gbc_searchEntry = new GridBagConstraints();
		gbc_searchEntry.insets = new Insets(0, 0, 5, 0);
		gbc_searchEntry.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchEntry.gridx = 0;
		gbc_searchEntry.gridy = 3;
		panel.add(searchEntry, gbc_searchEntry);
		searchEntry.setColumns(10);
		
		JButton searchMusicRecords = new JButton("Search");
		GridBagConstraints gbc_searchMusicRecords = new GridBagConstraints();
		gbc_searchMusicRecords.insets = new Insets(0, 0, 5, 0);
		gbc_searchMusicRecords.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchMusicRecords.gridx = 0;
		gbc_searchMusicRecords.gridy = 4;
		panel.add(searchMusicRecords, gbc_searchMusicRecords);
		
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//Search tools
		////////////////////////////////////////////////////////////////////////////////////////////
		JTabbedPane Music = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_Music = new GridBagConstraints();
		gbc_Music.fill = GridBagConstraints.BOTH;
		gbc_Music.gridx = 1;
		gbc_Music.gridy = 0;
		output_Panel.add(Music, gbc_Music);
		
		JPanel Records = new JPanel();
		Music.addTab("Records", null, Records, null);
		GridBagLayout gbl_Records = new GridBagLayout();
		gbl_Records.columnWidths = new int[]{0, 0};
		gbl_Records.rowHeights = new int[]{0, 0};
		gbl_Records.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_Records.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		Records.setLayout(gbl_Records);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		Records.add(table, gbc_table);
		
		//OUTPUT END<--------------------------------------------------------------------->
		
		//addUserResult Start
		

		JLabel lblResultOfAdding = new JLabel("Add new user result");
		GridBagConstraints gbc_lblResultOfAdding = new GridBagConstraints();
		gbc_lblResultOfAdding.insets = new Insets(0, 0, 5, 0);
		gbc_lblResultOfAdding.gridx = 0;
		gbc_lblResultOfAdding.gridy = 0;
		addUserFinish.add(lblResultOfAdding, gbc_lblResultOfAdding);
		
		JButton returnToLogin = new JButton("Return to login screen");
		GridBagConstraints gbc_returnToLogin = new GridBagConstraints();
		gbc_returnToLogin.gridx = 0;
		gbc_returnToLogin.gridy = 1;
		addUserFinish.add(returnToLogin, gbc_returnToLogin);
		
		//action handlers<---------------------------------------------------------------->
		
		//Login button actions for visibility settings.

		ActionListener toUserPanel = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try{
					DataCommands.DataConnect(txtYourUsername.getText(),txtYourPassword.getText());
				}catch (ClassNotFoundException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				if(DataCommands.getLoggedIn()){
					CardLayout cl = (CardLayout)(contentPane.getLayout());
					cl.show(contentPane,userPanel);
				}else {
					loginFail.setText("Your credentials were not recognized.");
				}
			}
		};
		ActionListener toLoginPanel = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,loginPanel);
				resetUsername();
				resetPassword();
				DataCommands.toggleLoggedIn();
				pack();
			}
		};
		ActionListener toNewUser = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,"new User");
				pack();
			}
		};
		ActionListener runCheck = new ActionListener(){
			Users newUser = new Users(UserName.getText(),
					  			  	  UserPass.getText(),
					  			  	  UserPhone.getText(),
					  			  	  Email.getText());
			public void actionPerformed(ActionEvent e){
				if(DataCommands.newLogin(newUser)){
					lblResultOfAdding.setText("You have been added successfully!");
				}else lblResultOfAdding.setText("You were not successfully added.");
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,"addUserFinish");
				pack();
			}
		};
		ActionListener addMusicPanel = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,"addMusic");
			}
		};
		ActionListener addCosignorPanel = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,"addCosignor");
			}
		};
		searchMusicRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table = new JTable(DataCommands.searchRecords(searchEntry.getText()));
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		mntmClearDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataCommands.dropAll();
			}
		});
		btnAddCosignor.addActionListener(new ActionListener() {
			Cosigner newCosigner = new Cosigner(cosignorName.getText(),coPhoneNum.getText());
			public void actionPerformed(ActionEvent e) {
				DataCommands.newCosigner(newCosigner);
			}
		});
		btnLogin.addActionListener(toUserPanel);
		mntmSearchMusic.addActionListener(toUserPanel);
		btnLogout.addActionListener(toLoginPanel);
		newUserCancel.addActionListener(toLoginPanel);
		returnToLogin.addActionListener(toLoginPanel);
		mntmLogout.addActionListener(toLoginPanel);
		btnNewUser.addActionListener(toNewUser);
		newUserAdd.addActionListener(runCheck);
		mntmAddMusic.addActionListener(addMusicPanel);
		mntmAddCosigner.addActionListener(addCosignorPanel);
	}
}
