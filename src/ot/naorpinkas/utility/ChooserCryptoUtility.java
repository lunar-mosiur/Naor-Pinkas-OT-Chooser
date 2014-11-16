package ot.naorpinkas.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ChooserCryptoUtility 
{

 
    public static byte[] encryptMessage(byte[] secretKeyBytes, byte[] plainText)  
    {
        try {
			return processingSymettricEncryption(secretKeyBytes,plainText, true);
		} catch (DataLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
 
    public static byte[] decryptMessage(byte[] secretKeyBytes, byte[] cipherText) 
    {
        try {
			return processingSymettricEncryption(secretKeyBytes,cipherText, false);
		} catch (DataLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCipherTextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
 
    private static byte[] processingSymettricEncryption(byte[] secretKeyBytes, byte[] input, boolean encrypt)
            throws DataLengthException, InvalidCipherTextException 
    {
    	BlockCipher aesCipher = new AESEngine();
    	
    	SecretKey secretKey = new SecretKeySpec(secretKeyBytes, ChooserConstant.ENCRYPTION_ALGORITHM);
    	
    	PaddedBufferedBlockCipher paddedBufferedBlockCipher = new PaddedBufferedBlockCipher(aesCipher, new PKCS7Padding());
    	
    	
    	KeyParameter keyParameter = new KeyParameter(secretKey.getEncoded());
 
        paddedBufferedBlockCipher.init(encrypt, keyParameter);
 
        byte[] output = new byte[paddedBufferedBlockCipher.getOutputSize(input.length)];
        int bytesWrittenOut = paddedBufferedBlockCipher.processBytes(
            input, 0, input.length, output, 0);
 
        paddedBufferedBlockCipher.doFinal(output, bytesWrittenOut);
        
        
 
        return output;
 
    }
    
    
    public static byte[] generateHashData(byte[] macKeyBytes, byte[] plainText )
    {
    	Security.addProvider(new BouncyCastleProvider());  
    	
    	SecretKeySpec macKey = new SecretKeySpec(macKeyBytes,ChooserConstant.HASH_ALGORITHM);
    	
    	try {
			Mac theMAC = Mac.getInstance(ChooserConstant.HASH_ALGORITHM);
			
			theMAC.init(macKey);
			
			theMAC.update(plainText);
			
			byte[] hashData = theMAC.doFinal();
			
			theMAC.reset();
			
			
			return hashData;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    
    
//    public static void main(String [] args) throws Exception
//    {
//    	 byte[] b = new byte[32];
// 	    new Random().nextBytes(b);
// 	    
// 	   byte[] message = new byte[3000];
//	    new Random().nextBytes(message);
//	    
//	    byte[] c = generateHashData(b, message);
//	    
//	    System.out.println(c.length);
//    }
    
    
    
}
