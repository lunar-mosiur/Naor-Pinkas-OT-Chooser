package ot.naorpinkas.utility;

public class ChooserConstant 
{
	public static final int COMMUNICATION_PORT = 2020;
	
	public static final int TOTAL_CHOOSERS = 10;
	
	public static final String ENCRYPTION_ALGORITHM = "AES";
	public static final String ENCRYPTION_PADDING_MECHANISM = "AES/ECB/PKCS7Padding";
	public static final String CRYPTO_PROVIDER = "BC"; // Bouncy Castle
	public static final String HASH_ALGORITHM = "HmacSHA256";
	public static final int RANDOM_BYTE_STRING_SIZE = 32;
	
	public static final int PRIME_NUMBER_BIT_LENGTH = 24;
	
	public static final String PRIME_LIST_FILENAME = "primes.txt";
	public static final int PRIME_CERTAINITY = 100;
}
