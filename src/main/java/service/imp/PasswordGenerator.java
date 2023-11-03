package service.imp;

import java.security.SecureRandom;

import jakarta.enterprise.context.RequestScoped;
import model.PasswordParametersDTO;

@RequestScoped
public class PasswordGenerator {

    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";

    public static String generatePassword(PasswordParametersDTO passwordDto) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        passwordDto.setMinLength(validateLength(passwordDto.getMinLength()));
        addMandatoryCharacters(password, random, passwordDto);
        addRandomCharacters(passwordDto.getMinLength(), password, random);
        return mixCharacters(password, random);
    }

    private static void addMandatoryCharacters(StringBuilder password, SecureRandom random,
            PasswordParametersDTO parametersDTO) {

        // Restriccion de digitos
        if (parametersDTO.isRestrictMinDigits()) {
            addMandatoryDigits(password, random, parametersDTO.getMinDigits());
        }
        if (parametersDTO.isRestrictMinLowerCaseLetters()) {
            addMandatoryLowerCase(password, random, parametersDTO.getMinLowerCaseLetters());
        }
        if (parametersDTO.isRestrictMinUpperCaseLetters()) {
            addMandatoryUpperCase(password, random, parametersDTO.getMinUpperCaseLetters());
        }
        if (parametersDTO.isRestrictMinNonAlphanumericCharacters()) {
            addMandatoryCharacters(password, random, parametersDTO.getMinNonAlphanumericCharacters());
        }
    }

    private static void addMandatoryDigits(StringBuilder password, SecureRandom random, int length) {
        for (int i = 0; i < length; i++) {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
    }
    private static void addMandatoryLowerCase(StringBuilder password, SecureRandom random, int length) {
        for (int i = 0; i < length; i++) {
            password.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));;
        }
    }
    private static void addMandatoryUpperCase(StringBuilder password, SecureRandom random, int length) {
        for (int i = 0; i < length; i++) {
            password.append(UPPERCASE_LETTERS.charAt(random.nextInt(UPPERCASE_LETTERS.length())));
        }
    }
    private static void addMandatoryCharacters(StringBuilder password, SecureRandom random, int length) {
        for (int i = 0; i < length; i++) {
            password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
        }
    }

    private static void addRandomCharacters(int length, StringBuilder password, SecureRandom random) {
        for (int i = password.length(); i < length; i++) {
            String allCharacters = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS + SPECIAL_CHARACTERS;
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
    }

    private static int validateLength(int length) {
        if (length < 8) {
            length = 8;
        }
        return length;
    }

    private static String mixCharacters(StringBuilder password, SecureRandom random) {
        String generatedPassword = password.toString();
        char[] passwordArray = generatedPassword.toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }
        return new String(passwordArray);
    }
}
