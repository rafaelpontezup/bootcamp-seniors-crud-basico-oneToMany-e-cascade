package br.com.zup.edu.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CpfUtils {

    /**
     * Gera hash SHA-256 em hexadecimal (64 bytes) do CPF informado
     */
    public static String hash(String cpf) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hash = digest.digest(cpf.getBytes(StandardCharsets.UTF_8));
            return toHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um CPF: " + cpf);
        }
    }

    /**
     * Aplica data masking/anonymization em um CPF formatado: 999.***.***-99
     */
    public static String anonymize(String cpf) {
        String masked = cpf
                .replaceAll("([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})", "$1.***.***-$4");
        return masked;
    }

    private static String toHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(CpfUtils.hash("555.933.825-73"));
        System.out.println(CpfUtils.anonymize("555.933.825-73"));
    }
}
