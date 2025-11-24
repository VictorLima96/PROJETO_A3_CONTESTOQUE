package utils;

public class RastreioGen {
    public static String gerarCodigoRastreio() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder cod = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int idx = (int) (Math.random() * chars.length());
            cod.append(Character.toLowerCase(chars.charAt(idx)));
        }
        return cod.toString();
    }
} 
