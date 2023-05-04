package com.github.vladimirplotnikov;

import com.google.common.hash.*;

public class HelloOtus {
    public static void main(String[] args) {
        int receivedData = 123;
        CharSequence receivedStringDate = "Vladimir";
        HashCode hashCode = Hashing.crc32c().hashInt(receivedData);
        HashCode hashStringCode = Hashing.crc32c().hashUnencodedChars(receivedStringDate);
        System.out.println(hashCode);
        System.out.println(hashStringCode);

    }
}