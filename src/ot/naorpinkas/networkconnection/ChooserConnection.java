package ot.naorpinkas.networkconnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ot.naorpinkas.messageprocessor.ChooserMessageProcessor;
import ot.naorpinkas.utility.ChooserConstant;


public class ChooserConnection extends UnicastRemoteObject
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IExchangeData exchangeData;
    private static Registry registry;
    private String senderAddress= "localhost";
    private int senderPort= ChooserConstant.COMMUNICATION_PORT;
    private ChooserMessageProcessor chooserMessageProcessor = new ChooserMessageProcessor();
	
	public ChooserConnection() throws Exception
	{
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	
	public String getSenderAddress() {
		return senderAddress;
	}



	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}



	public int getSenderPort() {
		return senderPort;
	}



	public void setSenderPort(int senderPort) {
		this.senderPort = senderPort;
	}

	

	public ChooserMessageProcessor getChooserMessageProcessor() {
		return chooserMessageProcessor;
	}



	public void setChooserMessageProcessor(
			ChooserMessageProcessor chooserMessageProcessor) {
		this.chooserMessageProcessor = chooserMessageProcessor;
	}



	public String connectToSender()
	{
		try 
		{
			registry=LocateRegistry.getRegistry(senderAddress, senderPort);
			exchangeData = (IExchangeData)(registry.lookup("ExchangePublicData"));
			chooserMessageProcessor.setPublicDataFromIndividuatObjects(exchangeData);
			
			return chooserMessageProcessor.receiveMessage();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
		
	}
	
//	public static void main(String [] args)
//	{
//		
//		try {
//			ChooserConnection chooserConnection = new ChooserConnection();
//			chooserConnection.connectToSender();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
