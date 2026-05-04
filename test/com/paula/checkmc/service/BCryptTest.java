package com.paula.checkmc.service;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {
    
    public static void main(String[] args) {
        System.out.println("=== ENCRIPTACIÓN NO REVERSIBLE CON jBCrypt ===\n");
        
        // 1. Password original del usuario (esto NO se almacena)
        String passwordUsuario = "MiSuperPassword123!";
        
        // 2. Generar hash (esto SÍ se almacena en la BD)
        // BCrypt genera automáticamente un SALT único y lo incluye en el hash
        String hashSeguro = BCrypt.hashpw(passwordUsuario, BCrypt.gensalt());
        
        System.out.println("✅ Password original: " + passwordUsuario);
        System.out.println("🔒 Hash BCrypt generado: " + hashSeguro);
        System.out.println("📏 Longitud del hash: " + hashSeguro.length() + " caracteres");
        
        // 3. Verificar password (cuando el usuario intenta login)
        boolean passwordCorrecto = BCrypt.checkpw(passwordUsuario, hashSeguro);
        System.out.println("\n🔍 Verificación con password CORRECTO:");
        System.out.println("   ¿Es válido? " + passwordCorrecto);
        
        // 4. Intentar con password INCORRECTO
        boolean passwordIncorrecto = BCrypt.checkpw("passwordErroneo", hashSeguro);
        System.out.println("\n❌ Verificación con password INCORRECTO:");
        System.out.println("   ¿Es válido? " + passwordIncorrecto);
        
        // 5. Demostrar que es irreversible
        // demostrarIrreversibilidad(hashSeguro);
        
        // 6. Ejemplo con diferentes costos computacionales
        // probarDiferentesCostos(passwordUsuario);
    }
    
    private static void demostrarIrreversibilidad(String hash) {
        System.out.println("\n=== DEMOSTRACIÓN DE IRREVERSIBILIDAD ===");
        System.out.println("❌ NO puedes obtener el password original desde el hash");
        System.out.println("Hash almacenado: " + hash);
        System.out.println("El hash contiene: algoritmo + costo + salt + hash real");
        System.out.println("Formato: $2a$[costo]$[salt 22 chars][hash 31 chars]");
        System.out.println("Ejemplo: $2a$12$R9h/cIPz0gi.URNNX3kh2OPST9/PgBkqquzi.Ss7KIUgO2t0jWMUW");
    }
    
    private static void probarDiferentesCostos(String password) {
        System.out.println("\n=== DIFERENTES NIVELES DE SEGURIDAD (costo) ===");
        
        // El costo determina cuántas iteraciones se realizan (2^costo)
        int[] costos = {4, 8, 10, 12, 14};
        
        for (int costo : costos) {
            long inicio = System.currentTimeMillis();
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(costo));
            long fin = System.currentTimeMillis();
            
            System.out.println("Costo " + costo + ": " + hash);
            System.out.println("   Tiempo generación: " + (fin - inicio) + " ms");
        }
        
        System.out.println("\n💡 Recomendación: Usa costo 10-14 para producción");
    }
}