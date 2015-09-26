package org.maxim.app.service;

public interface Encoder {

    String encrypt(String msg);

    String decrypt(String msg);

}
