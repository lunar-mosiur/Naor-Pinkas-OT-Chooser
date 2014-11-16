package ot.naorpinkas.utility;

import javax.xml.bind.DatatypeConverter;

public class ChooserDataTypeConvertUtility 
{
	public static byte[] stringToByte(String string)
	{
		return DatatypeConverter.parseHexBinary(string);
	}
	
	public static String byteToString(byte[] bytes)
	{
		return DatatypeConverter.printHexBinary(bytes);
	}
	
	public static byte[] parsePlainBytesFromDecryptedCipher(byte []decryptedCipher)
	{
		int textIndex = 0;
		while(decryptedCipher[textIndex]!= 0x00)
		{
			textIndex++;
		}
		
		
		byte [] plainBytes = new byte[textIndex];
		System.arraycopy(decryptedCipher, 0, plainBytes, 0, textIndex);
		
		return plainBytes;
	}
}
