/*	Phone Witnessing Tool v1.2
 * 	Raymond Hernandez
 * 	December 4, 2020
 * 
 * 	This is a port of the original app created with Python
 * 	https://github.com/raymondbrianhernandez/PhoneWitnessingTools	
*/

import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PhoneWitnessingTool extends JFrame {
	private JTextField searchField;
	//private JTextField statusLabel;
	private String query;
	private String url = "https://www.fastpeoplesearch.com/";
	private JTextField statusLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneWitnessingTool frame = new PhoneWitnessingTool();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PhoneWitnessingTool() {
		//	MAIN WINDOW SETTINGS 
		getContentPane().setBackground(new Color(91, 60, 136));
		setTitle("Phone Witnessing Tool 1.2");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 192);
		getContentPane().setLayout(null);
		
		// 	LABEL SETTINGS
		//		Main Label
		JLabel mainLabel = new JLabel("Phone Witnessing Tool");
		mainLabel.setForeground(Color.WHITE);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		mainLabel.setBounds(10, 11, 346, 20);
		getContentPane().add(mainLabel);
		
		// 		Credit Label
		JLabel creditLabel = new JLabel("Rev 1.2 2020 raymondhernandez@outlook.com");
		creditLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		creditLabel.setForeground(Color.WHITE);
		creditLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditLabel.setBounds(10, 137, 346, 14);
		getContentPane().add(creditLabel);
		
		// 	TEXT FIELD SETTINGS
		//		Search area
		searchField = new JTextField();
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchField.setText("");
				statusLabel.setText("");
			}
		});
		searchField.setText("Search here...");
		searchField.setBounds(20, 44, 227, 20);
		getContentPane().add(searchField);
		searchField.setColumns(10);
		searchField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		//		Status area
		statusLabel = new JTextField();
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setForeground(Color.WHITE);
		statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		statusLabel.setEditable(false);
		statusLabel.setBackground(new Color(91, 60, 136));
		statusLabel.setBounds(20, 103, 331, 20);
		getContentPane().add(statusLabel);
		statusLabel.setColumns(10);
		
		// 	RADIOBUTTON SETTINGS
		//		Check Phone
		JRadioButton checkPhone = new JRadioButton("Check Phone", true);
		checkPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url = "https://www.fastpeoplesearch.com/";
			}
		});
		checkPhone.setSelected(true);
		checkPhone.setHorizontalAlignment(SwingConstants.CENTER);
		checkPhone.setForeground(Color.WHITE);
		checkPhone.setBackground(new Color(91, 60, 136));
		checkPhone.setBounds(10, 73, 122, 23);
		getContentPane().add(checkPhone);
		
		// 		Check Ethnicity
		JRadioButton checkEthnicity = new JRadioButton("Check Ethnicity", false);
		checkEthnicity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url = "https://forebears.io/surnames/";
			}
		});
		checkEthnicity.setHorizontalAlignment(SwingConstants.CENTER);
		checkEthnicity.setForeground(Color.WHITE);
		checkEthnicity.setBackground(new Color(91, 60, 136));
		checkEthnicity.setBounds(134, 73, 122, 23);
		getContentPane().add(checkEthnicity);
		
		// 		Makes sure only one radio button is selected at a time
		ButtonGroup group = new ButtonGroup();
		group.add(checkPhone);
		group.add(checkEthnicity);
		
		// 	BUTTON SETTINGS
		//		Search Entry
		JButton searchButton = new JButton("Search");
		searchButton.setBorder(null);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					searchButtonClicked(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		searchButton.setBounds(262, 42, 89, 23);
		getContentPane().add(searchButton);
		
		//		Clear Entry
		JButton clearButton = new JButton("Clear");
		clearButton.setBorder(null);
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearButtonClicked(e);
			}
		});
		clearButton.setBounds(262, 73, 89, 23);
		getContentPane().add(clearButton);
		
		
	}
	
	private void searchButtonClicked(java.awt.event.MouseEvent e) throws IOException, URISyntaxException {
		query = searchField.getText();
		
		if (url == "https://forebears.io/surnames/") {	
			openURL();
		} else if (url == "https://www.fastpeoplesearch.com/") { 
			query = formatNumber(query);
			openURL();
		}
	}
	
	private void clearButtonClicked(java.awt.event.MouseEvent e) {
		searchField.setText("");
	}
	
	private String formatNumber(String query) {
		String tempStr;
		tempStr = query.replaceAll("[^0-9]", "") + "00000000000"; // avoid out of bounds if user enters empty phone
		
		if (tempStr.substring(0,1).equals("1")) {
			tempStr = tempStr.substring(1);
		}
		
		query = tempStr.substring(0,3) + '-' + tempStr.substring(3,6) + '-' + tempStr.substring(6,10); 
		
		return query;
	}
	
	private void openURL() throws IOException, URISyntaxException {
		if (url == "https://forebears.io/surnames/") {
			statusLabel.setText("Fetching data from Forebears...");
		} else if (url == "https://www.fastpeoplesearch.com/") {
			statusLabel.setText("Fetching data from FastPeopleSearch...");
		}
		
		Runtime rt = Runtime.getRuntime();
		
		// Enable for Windows
		rt.exec("rundll32 url.dll,FileProtocolHandler " + url + query);
		
		// Enable for Macs
//		rt.exec("open " + url + query);
		
		// Enable for Linux
//		String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror",
//                "netscape", "opera", "links", "lynx" };
//
//		StringBuffer cmd = new StringBuffer();
//		
//		for (int i = 0; i < browsers.length; i++)
//			if(i == 0)
//				cmd.append(String.format(    "%s \"%s\"", browsers[i], url + query));
//			else
//				cmd.append(String.format(" || %s \"%s\"", browsers[i], url + query)); 
//		// If the first didn't work, try the next browser and so on
//		
//		rt.exec(new String[] { "sh", "-c", cmd.toString() });
	} 
}
