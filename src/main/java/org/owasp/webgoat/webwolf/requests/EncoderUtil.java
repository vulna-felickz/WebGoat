package org.owasp.webgoat.webwolf.requests;

import org.owasp.encoder.Encode;

public class EncoderUtil {
    public static String encodeForJava(String input) {
        return Encode.forJava(input);
    }
}