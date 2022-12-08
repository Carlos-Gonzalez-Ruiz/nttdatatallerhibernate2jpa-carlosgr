package com.nttdata.carlosgr.hibernate.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * NTT Data - Hibernate - Taller 2 + JPA
 * 
 * Clase que abstrae el proceso de hacer hash a una cadena de texto.
 * 
 * @author NTT Data - Carlos González Ruiz
 */
public class Hash {

	/** Objeto para realizar el hash con Argon2 */
	private Argon2 argon2;

	public Hash() {
		super();
		argon2 = Argon2Factory.create();
	}

	/**
	 * Método que devuelve una String con el hash que "string" genera.
	 * 
	 * @param string
	 * @return String
	 */
	public String hashString(final String string) {
		/*
		 * La cantidad de memoria que el hash va a alojar y las iteraciones va a ser
		 * baja para evitar que tarde mucho generar el hash. En producción, subiriamos
		 * este valor a uno mucho mayor para hacerlo más seguro.
		 * 
		 */
		return argon2.hash(5, 32, 1, string.toCharArray());
	}

	/**
	 * Método que comprueba si un hash es equivalente a un hash que una cadena de
	 * texto genera.
	 * 
	 * @param hash
	 * @param string
	 * @return boolean
	 */
	public boolean checkHashString(final String hash, final String string) {
		boolean out = false;

		// Convertir string a array de caractéres.
		char[] stringCharArray = string.toCharArray();

		try {
			// Comprobar hash y el array de caractéres.
			if (argon2.verify(hash, stringCharArray)) {
				out = true;
			}
		} finally {
			// Da igual el caso, siempre eliminamos stringHash de la memoria.
			argon2.wipeArray(stringCharArray);
		}

		return out;
	}

}
