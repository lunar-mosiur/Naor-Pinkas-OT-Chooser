package ot.naorpinkas.data;

import java.math.BigInteger;

public class ChooserData {

	private int sigma;

	private BigInteger k;  //random k

	private BigInteger pkSigma;

	public int getSigma() {
		return sigma;
	}

	public void setSigma(int sigma) {
		this.sigma = sigma;
	}

	public BigInteger getK() {
		return k;
	}

	public void setK(BigInteger k) {
		this.k = k;
	}

	public BigInteger getPkSigma() {
		return pkSigma;
	}

	public void setPkSigma(BigInteger pkSigma) {
		this.pkSigma = pkSigma;
	}

}
