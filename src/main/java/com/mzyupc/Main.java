package com.mzyupc;

import com.alibaba.fastjson.JSON;
import org.web3j.crypto.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author mzyupc@163.com
 * @date 2022/1/19 4:40 下午
 */
public class Main {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException {
        String path = System.getProperty("path", "keystore/");
        String separator = File.separator;
        if (!path.endsWith(separator)) {
            path = path + separator;
        }

        String quantityString = System.getProperty("quantity", "1");
        int quantity = Integer.parseInt(quantityString);
        if (quantity <= 0 || quantity > 100) {
            throw new RuntimeException("quantity out of range");
        }

        String password = System.getProperty("password");
        if (password == null || "".equals(password)) {
            throw new RuntimeException("password can not be empty");
        }
        for (int i = 0; i < quantity; i++) {
            generate(path, password);
        }
    }

    public static void generate(String walletFilePath, String password) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        System.out.println("privateKey: " + ecKeyPair.getPrivateKey().toString(16));
        System.out.println("publicKey: " + ecKeyPair.getPublicKey());
        System.out.println("address: 0x" + Keys.getAddress(ecKeyPair.getPublicKey()));
        WalletFile walletFile = Wallet.createStandard(password, ecKeyPair);
        saveWalletFile(walletFile, walletFilePath);
    }

    private static void saveWalletFile(WalletFile walletFile, String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(path + "0x" + walletFile.getAddress());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String jsonString = JSON.toJSONString(walletFile);
            System.out.println("keystore: " + file.getAbsolutePath());
            System.out.println();
            writer.write(jsonString);
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException("failed to save keystore", e);
        }
    }
}
