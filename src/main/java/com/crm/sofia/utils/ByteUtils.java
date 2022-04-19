package com.crm.sofia.utils;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

@Service
public class ByteUtils {

    public byte[] objectToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        }
    }

    public Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    public byte[] objectToBase64Bytes(Object object) throws IOException {
        byte[] bytes = this.objectToBytes(object);
        return Base64.getEncoder().encode(bytes);
    }

    public Object base64BytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        byte[] decodedBytes = Base64.getDecoder().decode(bytes);
        return this.bytesToObject(decodedBytes);
    }

}
