package co.edu.unicauca.mycompany.projects.infra.state;

import org.mindrot.jbcrypt.BCrypt;

public class Cifrador {

    public static String cifrar(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(12));
    }

    public static boolean verificar(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }
}