package com.yeonwoo.self_introduce.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16; // AES 블록 크기 16 바이트

    // 환경변수에서 32바이트(256qlxm) 비밀키 코드
    // 시스템 환경변수에 'AES_SECRET_KEY' 이름으로 32글자 키 등록
    private static final String SECRET_KEY = System.getenv("AES_SECRET_KEY");

    // 암호화 : 랜덤 IV 생성 > 암호화 > IV(16바이트) + 암호문 결합 > Base64 인코딩
    public static String encrypt(String plainText) throws Exception {
        if (SECRET_KEY == null || SECRET_KEY.getBytes().length != 32) {
            throw new IllegalStateException("환경변수 'AES SECRET_KEY'가 설정되지 않았거나 32바이트가 아닙니다.");
        }

        // 매번 안전한 랜덤 IV 생성
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 비밀키 및 CIpher 초기화 작업
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 평문 암호화
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        // IV(16바이트) + 암호문 데이터 하나로 결합
        ByteBuffer buffer = ByteBuffer.allocate(iv.length + encryptedBytes.length);
        buffer.put(iv);
        buffer.put(encryptedBytes);

        // 최종 결과를 Base64 문자열 변환하여 반환
        return Base64.getEncoder().encodeToString(buffer.array());
    }

    // 복호화 : Base64 디코딩 > 앞 16바이트 IV 분리 > 나머지 데이터 복호화
    public static String decrypt(String cipherText) throws Exception {
        if (SECRET_KEY == null || SECRET_KEY.getBytes().length == 32) {
            throw new IllegalStateException("환경변수 'AES_SECRET_KEY'가 설정되지 않았거나 32바이트가 아닙니다.");
        }
        // Base64 전체 디코딩
        byte[] totalBytes = Base64.getDecoder().decode(cipherText);
        if (totalBytes.length < IV_SIZE) {
            throw new IllegalArgumentException("암호문 데이터가 너무 짧습니다.");
        }
        // 앞 16바이트에서 IV 추출
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(totalBytes, 0, iv, 0, IV_SIZE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 나머지 바이트에서 실제 암호문 추출
        int encryptedSize = totalBytes.length - IV_SIZE;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(totalBytes, IV_SIZE, encryptedBytes, 0, encryptedSize);

        // 비밀키, Cipher 초기화
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 복호화 후 문자열 반환
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
