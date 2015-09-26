package org.maxim.app;

import org.maxim.app.service.Encoder;
import org.maxim.app.service.impl.CaesarEncoder;
import org.maxim.app.service.impl.PolybiusEncoder;

public class App {

    private static final String DEFAULT_MESSAGE = "Мама мыла раму, а Вадим ел кашу.";

    public static void main(String[] args) {
        Encoder[] encoders = {
                new CaesarEncoder(),
                new PolybiusEncoder()
        };

        String message = args.length == 0 ? DEFAULT_MESSAGE : args[0];
        for (Encoder encoder : encoders) {
            testEncoder(encoder, message);
        }
    }

    private static void testEncoder(Encoder encoder, String message) {
        System.out.println("\n---------------------------------\n");

        System.out.println("Testing: " + encoder);
        System.out.println("Original message: " + message);

        String encrypted = encoder.encrypt(message);
        System.out.println("Encrypted message: " + encrypted);

        String decrypted = encoder.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);

        System.out.println("\n---------------------------------\n");
    }

}
