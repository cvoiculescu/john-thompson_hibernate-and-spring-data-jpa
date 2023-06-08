package org.voiculescu.sdjpa.creditcard.services;

public interface EncryptionService {
    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
