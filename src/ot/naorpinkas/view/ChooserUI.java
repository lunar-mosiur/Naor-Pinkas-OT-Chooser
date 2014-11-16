package ot.naorpinkas.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ot.naorpinkas.networkconnection.ChooserConnection;

public class ChooserUI 
{
	private JFrame chooserFrame = new JFrame("Naor-Pinkas OT");
	private JPanel chooserPanel = new JPanel();

	private JButton buttonReceiveMessage = new JButton("Request");
	private JButton buttonCancel = new JButton("Cancel");

	private JLabel labelSenderServerIP = new JLabel("Sender Server IP: ");
	private JLabel labelServerPort = new JLabel("Server Port: ");
	private JLabel labelSigma = new JLabel("<html>Value of &#963: </html>");
	
	private JTextField textSenderServerIP = new JTextField();
	private JTextField textServerPort = new JTextField();
	private JTextField textSigma = new JTextField();


	private int gridBagVerticalPosition = 0;
	
	private boolean useSystemLookAndFeel = true;

	public ChooserUI() {
		// TODO Auto-generated constructor stub
	}

	public void buildFrame()
	{
		setSystemLookAndFeel();
		chooserFrame.setSize(400, 300);
		setPanel();
		setActionListeners();
		chooserFrame.add(chooserPanel);
		chooserFrame.setVisible(true);
		chooserFrame.setLocationRelativeTo(null);
		chooserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public void setSystemLookAndFeel()
	{
		if(useSystemLookAndFeel)
		{
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setPanel()
	{
		chooserPanel.setLayout(new GridBagLayout());
		chooserPanel.setBackground(Color.WHITE);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		setGridBagConstrains(gridBagConstraints, 0, gridBagVerticalPosition,new Insets(0,0,5,10));
		chooserPanel.add(labelSenderServerIP,gridBagConstraints);
		
		textSenderServerIP.setText("127.0.0.1");
		setGridBagConstrains(gridBagConstraints, 1, gridBagVerticalPosition);
		chooserPanel.add(textSenderServerIP,gridBagConstraints);
		
		gridBagVerticalPosition++;
		
		setGridBagConstrains(gridBagConstraints, 0, gridBagVerticalPosition);
		chooserPanel.add(labelServerPort,gridBagConstraints);
		
		textServerPort.setText("2020");
		setGridBagConstrains(gridBagConstraints, 1, gridBagVerticalPosition);
		chooserPanel.add(textServerPort,gridBagConstraints);
		
		gridBagVerticalPosition++;
		
		setGridBagConstrains(gridBagConstraints, 0, gridBagVerticalPosition);
		chooserPanel.add(labelSigma,gridBagConstraints);
		
		setGridBagConstrains(gridBagConstraints, 1, gridBagVerticalPosition);
		chooserPanel.add(textSigma,gridBagConstraints);
		
		gridBagVerticalPosition++;
		
		setGridBagConstrains(gridBagConstraints, 0, gridBagVerticalPosition, new Insets(20,0,40,80));
		chooserPanel.add(buttonCancel,gridBagConstraints);
		
		setGridBagConstrains(gridBagConstraints, 1, gridBagVerticalPosition);
		chooserPanel.add(buttonReceiveMessage,gridBagConstraints);

	
		
	}
	
	public void setGridBagConstrains(GridBagConstraints gridBagConstraints, int x, int y, Insets insets)
	{
		gridBagConstraints.insets = insets;
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	}
	
	public void setGridBagConstrains(GridBagConstraints gridBagConstraints, int x, int y)
	{
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
	}
	
	public void setActionListeners()
	{
		buttonCancel.addActionListener(new ButtonCancelActionListener());
		buttonReceiveMessage.addActionListener(new ButtonReceiveMessageActionListener());
	}
	
	private class ButtonReceiveMessageActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String serverIP = textSenderServerIP.getText();
			String serverPort = textServerPort.getText();
			String sigmaString = textSigma.getText();
			try {
				ChooserConnection chooserConnection = new ChooserConnection();
				chooserConnection.setSenderAddress(serverIP);
				chooserConnection.setSenderPort(Integer.parseInt(serverPort));
				chooserConnection.getChooserMessageProcessor().getChooserData().setSigma(Integer.parseInt(sigmaString));
				String message = chooserConnection.connectToSender();
				if(message == null || message.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Message is not Received","Failure",JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(null, message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class ButtonCancelActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			chooserFrame.dispose();
		}
	}
	
	public static void main(String []args)
	{
		ChooserUI chooserUI = new ChooserUI();
		chooserUI.buildFrame();
	}
	
}
