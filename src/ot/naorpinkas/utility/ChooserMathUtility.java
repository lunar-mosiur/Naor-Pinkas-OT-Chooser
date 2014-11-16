package ot.naorpinkas.utility;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChooserMathUtility
{
	private static BigInteger bigIntegerTwo = BigInteger.valueOf(2L);
	//private static BigInteger bigIntegerThree = BigInteger.valueOf(3L);

	public static BigInteger probablePrimeGenerator(int certainity, int bitLength)
	{
		BigInteger p = BigInteger.probablePrime(bitLength, new Random());

		Boolean loopInside = true;

		while(loopInside)
		{


			if(p.isProbablePrime(certainity))
				loopInside = false;

			p = BigInteger.probablePrime(bitLength, new Random());
		}

		return p;
	}
	
	public static BigInteger probablePrimeGeneratorForPrimeOrderGroup(int certainity, int bitLength)
	{
		BigInteger p = BigInteger.probablePrime(bitLength, new Random());

		Boolean loopInside = true;

		while(loopInside)
		{
			BigInteger q = (bigIntegerTwo.multiply(p)).add(BigInteger.ONE);

			if(p.isProbablePrime(certainity) && q.isProbablePrime(certainity))
				loopInside = false;

			p = BigInteger.probablePrime(bitLength, new Random());
		}

		return p;
	}

	public static boolean isDivisible(BigInteger isThisNumberDivisible, BigInteger byThisNumber)
	{
		return isThisNumberDivisible.mod(byThisNumber).equals(BigInteger.ZERO);
	}


	public static List<BigInteger>  primeFactorOfBigInteger(BigInteger bigInteger)
	{
		List<BigInteger> listPrimeFactors = new ArrayList<BigInteger>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(ChooserConstant.PRIME_LIST_FILENAME)));
			String primeString;

			while(!bigInteger.equals(BigInteger.ONE))
			{
				primeString = bufferedReader.readLine();
				BigInteger prime = new BigInteger(primeString);
				if(isDivisible(bigInteger, prime))
				{
					listPrimeFactors.add(prime);
				}
				while(isDivisible(bigInteger, prime))
				{
					bigInteger = bigInteger.divide(prime);
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPrimeFactors;
	}

	public static BigInteger pow(BigInteger base, BigInteger exponent) 
	{
		BigInteger result = BigInteger.ONE;
		while (exponent.signum() > 0) {
			if (exponent.testBit(0)) result = result.multiply(base);
			base = base.multiply(base);
			exponent = exponent.shiftRight(1);
		}
		return result;
	}

	public static BigInteger pow(BigInteger base, BigInteger exponent,BigInteger prime) 
	{
		BigInteger result = BigInteger.ONE;
		while (exponent.signum() > 0) {
			if (exponent.testBit(0)) result = result.multiply(base);
			base = base.multiply(base).mod(prime);
			exponent = exponent.shiftRight(1);
		}
		return result.mod(prime);
	}

	public static boolean isModuloOneForPrimitiveRoot(BigInteger a, BigInteger s, BigInteger p)
	{
		return (pow(a,s,p)).equals(BigInteger.ONE);
	}

	// Algorithm: http://modular.math.washington.edu/edu/2007/spring/ent/ent-html/node31.html
	public static BigInteger computePrimitiveRoot(BigInteger prime)
	{
		List<BigInteger> listPrimeFactors = new ArrayList<BigInteger>();

		BigInteger q = prime.subtract(BigInteger.ONE);

		listPrimeFactors = primeFactorOfBigInteger(q);

		BigInteger a = bigIntegerTwo;


		while(true)
		{
			boolean flag = true;
			for(int i =0; i< listPrimeFactors.size(); i++)
			{
				BigInteger s = q.divide(listPrimeFactors.get(i));
				if(isModuloOneForPrimitiveRoot(a, s, prime))
				{
					flag = false;
					break;
				}
			}
			if(flag)
			{
				return a;
			}
			a = a.add(BigInteger.ONE);
			if(a.equals(prime))
			{
				return BigInteger.ZERO;
			}
		}
	}
	
	public static BigInteger modInversePrime(BigInteger b,BigInteger p)
    {
    	BigInteger exponent = p.subtract(bigIntegerTwo);
    	
    	return pow(b, exponent, p);
    }
	
}
