package ot.naorpinkas.data;

import java.math.BigInteger;

public class SenderPublicData 
{
	private BigInteger generator;
	private BigInteger[] constant;
	private byte[] randomKey;
	private BigInteger generatorWithRandom; //g^r
	private BigInteger prime;
	
	public SenderPublicData() {
		// TODO Auto-generated constructor stub
	}

	public BigInteger getGenerator() {
		return generator;
	}

	public void setGenerator(BigInteger generator) {
		this.generator = generator;
	}

	public BigInteger[] getConstant() {
		return constant;
	}

	public void setConstant(BigInteger[] constant) {
		this.constant = constant;
	}

	public byte[] getRandomKey() {
		return randomKey;
	}

	public void setRandomKey(byte[] randomKey) {
		this.randomKey = randomKey;
	}

	public BigInteger getGeneratorWithRandom() {
		return generatorWithRandom;
	}

	public void setGeneratorWithRandom(BigInteger generatorWithRandom) {
		this.generatorWithRandom = generatorWithRandom;
	}

	public BigInteger getPrime() {
		return prime;
	}

	public void setPrime(BigInteger prime) {
		this.prime = prime;
	}



	
}
