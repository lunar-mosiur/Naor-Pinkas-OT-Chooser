package ot.naorpinkas.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class ChooserFileUtility
{
	public static byte[] readByteFromFile(String fileName)
	{
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream( new FileInputStream(fileName));
			byte[] keyBytes = new byte [bufferedInputStream.available()];
			bufferedInputStream.read(keyBytes);
			bufferedInputStream.close();
			
			return keyBytes;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void writeByteToFile(byte[] keyBytes, String fileName)
	{
		try {
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
			bufferedOutputStream.write(keyBytes);
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BigInteger parsePrimeFromFile(String fileName, int lineIndex)
	{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			int i = 0;
			while(i != lineIndex)
			{
				i++;
				br.readLine();
			}
			BigInteger primeNumber =  new BigInteger(br.readLine());
			br.close();
			return primeNumber;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
