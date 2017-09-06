package util;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import exception.ApplicationException;
import constant.ErrorMessages;

public class EncryptUtil {

    private static final Logger LOGGER = Logger.getLogger(EncryptUtil.class);

    public static String encryptPassword(String password) throws ApplicationException {
        MessageDigest messageDigest;
        byte[] digest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(ErrorMessages.ERROR_CAN_NOT_ENCRYPT_PASSWORD, e);
            throw new ApplicationException();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String encryptPassword = bigInt.toString(16);
        while( encryptPassword.length() < 32 ){
            encryptPassword = "0" + encryptPassword;
        }
        return encryptPassword;
    }

    public static void main(String[] args) throws ApplicationException {
        System.out.println(encryptPassword("client"));
    }

}
