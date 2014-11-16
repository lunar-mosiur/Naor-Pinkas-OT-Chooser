package ot.naorpinkas.networkconnection;
import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IExchangeData extends Remote
{
	public byte[][]  getEncryptedMessageChain(BigInteger pk) throws RemoteException;
	public BigInteger getSenderPublicDataPrime() throws RemoteException;
	public BigInteger getSenderPublicDataGenerator() throws RemoteException;
	public BigInteger[] getSenderPublicDataConstant() throws RemoteException;
	public byte[] getSenderPublicDataRandomKey() throws RemoteException;
	public BigInteger getSenderPublicDataGeneratorWithRandom() throws RemoteException;



}
