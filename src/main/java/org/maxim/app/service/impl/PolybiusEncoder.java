package org.maxim.app.service.impl;

import org.maxim.app.service.Encoder;

public class PolybiusEncoder implements Encoder {

    private static final int DEFAULT_STEP = 1;
    private static final String[] RU_ALPHABET = {
            "абвгде",
            "ёжзийк",
            "лмнопр",
            "стуфхц",
            "чшщъыь",
            "эюя123"
    };

    private int step;
    private String[] alphabet;
    private String[] key;

    public PolybiusEncoder() {
        this(DEFAULT_STEP, RU_ALPHABET);
    }

    public PolybiusEncoder(int step) {
        this(step, RU_ALPHABET);
    }

    public PolybiusEncoder(int step, String[] alphabet) {
        this.step = step;
        this.alphabet = alphabet;
        init();
    }

    private void init() {
        generateKey(RU_ALPHABET);
    }

    private void generateKey(String[] alphabet) {
        if (step > alphabet.length) {
            throw new IllegalArgumentException("So big step for Polybius encoder.");
        }

        this.alphabet = alphabet;

        key = new String[alphabet.length];
        int j = 0;
        for (int i = step; i < alphabet.length; i++) {
            key[j++] = alphabet[i];
        }
        for (int i = 0; i < step; i++) {
            key[j++] = alphabet[i];
        }
    }

    private char combineChar(String[] origin, String[] mapping, char c) {
        int row = -1;
        int col = -1;

        for (int i = 0; i < origin.length; i++) {
            col = origin[i].indexOf(c);
            if (col != -1) {
                row = i;
                break;
            }
        }

        if (row == -1) {
            return c;
        }
        return mapping[row].charAt(col);
    }

    public String encrypt(String message) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            char encoded = combineChar(alphabet, key, c);

            if (Character.isUpperCase(c)) {
                builder.append(Character.toUpperCase(encoded));
            } else {
                builder.append(encoded);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char encoded = message.charAt(i);
            char decoded = combineChar(key, alphabet, encoded);

            if (Character.isUpperCase(encoded)) {
                builder.append(Character.toUpperCase(decoded));
            } else {
                builder.append(decoded);
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Polybius encoder";
    }

}
