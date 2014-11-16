package ot.naorpinkas.messageprocessor;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import ot.naorpinkas.data.ChooserData;
import ot.naorpinkas.data.SenderPublicData;
import ot.naorpinkas.networkconnection.IExchangeData;
import ot.naorpinkas.utility.ChooserConstant;
import ot.naorpinkas.utility.ChooserCryptoUtility;
import ot.naorpinkas.utility.ChooserDataTypeConvertUtility;
import ot.naorpinkas.utility.ChooserMathUtility;

public class ChooserMessageProcessor extends UnicastRemoteObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SenderPublicData senderPublicData = new SenderPublicData();
	private IExchangeData publicData;
	private ChooserData chooserData = new ChooserData();
	
	public ChooserMessageProcessor() throws Exception{
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	
	public ChooserData getChooserData() {
		return chooserData;
	}



	public void setChooserData(ChooserData chooserData) {
		this.chooserData = chooserData;
	}



	public IExchangeData getPublicData() {
		return publicData;
	}
	public void setPublicData(IExchangeData publicData) {
		this.publicData = publicData;
	}
	
	public void setPublicDataFromIndividuatObjects(IExchangeData publicData) 
	{
		try {
			senderPublicData.setConstant(publicData.getSenderPublicDataConstant());
			senderPublicData.setGenerator(publicData.getSenderPublicDataGenerator());
			senderPublicData.setGeneratorWithRandom(publicData.getSenderPublicDataGeneratorWithRandom());
			senderPublicData.setPrime(publicData.getSenderPublicDataPrime());
			senderPublicData.setRandomKey(publicData.getSenderPublicDataRandomKey());
			setPublicData(publicData);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public BigInteger generateRequestKey()
	{
		System.out.println("ChooserMessageProcessor: generateRequestKey");	
		byte[] b = new byte[ChooserConstant.PRIME_NUMBER_BIT_LENGTH-1];
		new Random().nextBytes(b);
		BigInteger k = new BigInteger(b);
		chooserData.setK(k);
		BigInteger prime = senderPublicData.getPrime();
		BigInteger pkSigma = ChooserMathUtility.pow(senderPublicData.getGenerator(),k,prime);
		if(chooserData.getSigma() == 0)
		{
			return pkSigma;
		}
		chooserData.setPkSigma(pkSigma);
		
		BigInteger modInverse = ChooserMathUtility.modInversePrime(pkSigma, prime);
		
		BigInteger [] randomConstantRaw = senderPublicData.getConstant();
		
		BigInteger [] randomConstant = new BigInteger[ChooserConstant.TOTAL_CHOOSERS];
		
		randomConstant[0] = BigInteger.ZERO;
	
		System.arraycopy(randomConstantRaw, 0, randomConstant, 1, ChooserConstant.TOTAL_CHOOSERS-1);
		
		BigInteger pk = (randomConstant[chooserData.getSigma()].multiply(modInverse)).mod(prime);
		
		return pk;
		
	}
	
	public byte [] calculateDecryptionKey()
	{
		System.out.println("ChooserMessageProcessor: calculateDecryptionKey");
		BigInteger intForHashing = ChooserMathUtility.pow(senderPublicData.getGeneratorWithRandom(),chooserData.getK(),senderPublicData.getPrime());
		
		byte [] decryptionKey = ChooserCryptoUtility.generateHashData( senderPublicData.getRandomKey() , intForHashing.toByteArray());
		
		return decryptionKey;
	}
	
	public byte[] decryptMesage(byte[] decryptionKey, byte [] encryptedMessage)
	{
		System.out.println("ChooserMessageProcessor: decryptMesage");
		return ChooserCryptoUtility.decryptMessage(decryptionKey, encryptedMessage);
	}
	
	public String receiveMessage()
	{
		//chooserData.setSigma(5);
		byte[][] cryptoMessage;
		cryptoMessage = new byte[ChooserConstant.TOTAL_CHOOSERS][];
		BigInteger pk = generateRequestKey();
		try {
			cryptoMessage = publicData.getEncryptedMessageChain(pk);
			//BigInteger b = publicData.getSenderPublicDataGenerator();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] decryptionKey = calculateDecryptionKey();
		
		
		byte [] encryptedMessage = cryptoMessage[chooserData.getSigma()];
		
		byte [] decryptedCipher = decryptMesage(decryptionKey, encryptedMessage);
		
		byte [] message = ChooserDataTypeConvertUtility.parsePlainBytesFromDecryptedCipher(decryptedCipher);
		
		String messageText = new String(message);
		
		System.out.println(messageText);
		
		return messageText;
	}
	
}
