package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Solution {

    private static final String KEY = "qwertyqwertyqwer";
    private static final String ALGORITM = "AES/CBC/PKCS5Padding";
    private static SecretKeySpec key;
    private static Cipher cipher;
    private static byte[] iv = { 2, 1, 3, 2, 6, 3, 2, 4, 4, 5, 3, 6, 5, 7, 7, 8 };
    private static IvParameterSpec ivspec;

    static {
        key =  new SecretKeySpec(KEY.getBytes(), "AES");
        ivspec = new IvParameterSpec(iv);
        try {
            cipher = Cipher.getInstance(ALGORITM);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  }
        catch (NoSuchPaddingException e)   {
            e.printStackTrace(); }
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException
    {

        if (args.length == 3)
        {
            if (args[0] == "-d") decrypt(args[1], args[2]);
            else if (args[0] == "-e") encrypt(args[1], args[2]);
        }
    }

    public static void encrypt(String inputFile, String outputFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException
    {

        try (OutputStream os1 = new FileOutputStream(outputFile);
             InputStream is2 = new FileInputStream(inputFile);)
        {
            if (is2.available() > 0)
            {
                //читаем  файл
                byte[] buffer1 = new byte[is2.available()];

                int count1 = is2.read(buffer1);

                cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
                //запись
                os1.write(cipher.doFinal(buffer1));
            }
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }

    }

    public static void decrypt(String inputFile, String outputFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException
    {

        try (OutputStream os1 = new FileOutputStream(outputFile);
             InputStream is2 = new FileInputStream(inputFile);)
        {
            if (is2.available() > 0)
            {
                //читаем  файл
                byte[] buffer1 = new byte[is2.available()];

                int count1 = is2.read(buffer1);

                cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
                //запись
                os1.write(cipher.doFinal(buffer1));
            }
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }

    }

}
