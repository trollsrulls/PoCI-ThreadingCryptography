package org.maxim.app.service.impl;

import org.maxim.app.service.Encoder;

public class CaesarEncoder implements Encoder {

    public static final String RU_LOCALE = "ru";
    public static final String EN_LOCALE = "en";

    private static final int DEFAULT_STEP = 8;
    private static final String RU_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String EN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private int step;
    private String locale;
    private String alphabet;
    private String key;

    public CaesarEncoder() {
        this(DEFAULT_STEP, RU_LOCALE);
    }

    public CaesarEncoder(int step) {
        this(step, RU_LOCALE);
    }

    public CaesarEncoder(int step, String locale) {
        this.step = step;
        this.locale = locale;
        init();
    }

    private void init() {
        if (EN_LOCALE.equals(locale)) {
            generateKey(EN_ALPHABET);
        } else {
            generateKey(RU_ALPHABET);
        }
    }

    private void generateKey(String alphabet) {
        if (step > alphabet.length()) {
            throw new IllegalArgumentException("So big step for Caesar encoder.");
        }

        this.alphabet = alphabet;

        StringBuilder builder = new StringBuilder();
        for (int i = step; i < alphabet.length(); i++) {
            builder.append(alphabet.charAt(i));
        }
        for (int i = 0; i < step; i++) {
            builder.append(alphabet.charAt(i));
        }
        key = builder.toString();
    }

    public String encrypt(String message) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(c));
            if (index == -1) {
                builder.append(c);
            } else {
                char encoded = key.charAt(index);
                if (Character.isUpperCase(c)) {
                    builder.append(Character.toUpperCase(encoded));
                } else {
                    builder.append(encoded);
                }
            }
        }
        return builder.toString();
    }

    public String decrypt(String message) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char encoded = message.charAt(i);
            int index = key.indexOf(Character.toLowerCase(encoded));
            if (index == -1) {
                builder.append(encoded);
            } else {
                char decoded = alphabet.charAt(index);
                if (Character.isUpperCase(encoded)) {
                    builder.append(Character.toUpperCase(decoded));
                } else {
                    builder.append(decoded);
                }
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Caesar encoder";
    }

}
