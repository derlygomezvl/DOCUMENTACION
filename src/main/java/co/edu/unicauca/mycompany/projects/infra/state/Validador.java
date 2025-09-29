package co.edu.unicauca.mycompany.projects.infra.state;

public class Validador {

    public static boolean esEmailUnicauca(String email) {
        return email != null && email.endsWith("@unicauca.edu.co");
    }

    public static boolean esContrasenaSegura(String pass) {
        if (pass == null || pass.length() < 6) return false;
        boolean tieneMayus = !pass.equals(pass.toLowerCase());
        boolean tieneDigito = pass.matches(".*\\d.*");
        boolean tieneEspecial = pass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        return tieneMayus && tieneDigito && tieneEspecial;
    }
}