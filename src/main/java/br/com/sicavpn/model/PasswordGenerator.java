package br.com.sicavpn.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordGenerator {

	public PasswordGenerator() {

	}

	String senha = "";

	public String Password() {
		Random ran = new Random();
		String[] letrasMi = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		String[] letrasMa = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		String[] numeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] caracterEsp = { "@", "#", "$", "%", "&", "*", "!", "?", "<", ">" };

		for (int i = 0; i < 3; i++) {
			int a = ran.nextInt(letrasMa.length);
			senha += letrasMa[a];
		}

		for (int i = 0; i < 2; i++) {
			int b = ran.nextInt(caracterEsp.length);
			senha += caracterEsp[b];
		}
		
		for (int i = 0; i < 3; i++) {
			int c = ran.nextInt(letrasMi.length);
			senha += letrasMi[c];
		}

		for (int i = 0; i < 2; i++) {
			int d = ran.nextInt(numeros.length);
			senha += numeros[d];
		}

		return senha;

	}
	
	public static String md5(String senha) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		sen = hash.toString(16);
		return sen;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
