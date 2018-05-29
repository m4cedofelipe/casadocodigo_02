package br.com.casadocodigo.loja.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeraSenhaBCrypt {

	public static void main(String[] args) {
		String senhaCriptografado = new BCryptPasswordEncoder().encode("123456");
		System.out.println("Senha Cryptografada: " + senhaCriptografado);
	}

}
