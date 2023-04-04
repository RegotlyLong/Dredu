package dredu.regotly;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Regotly
 * @title
 * @date 2023/4/3 22:42
 * @since 1.0.0
 */
public class Main {
    public final String letter = "ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwsyz";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean repeat = true;

        while (repeat) {
            System.out.println("请输入你需要加密或者解密的文字:");
            String message = scanner.nextLine();

            System.out.println("输入E进行加密，D进行解密: ");
            String function = scanner.nextLine().trim();

            if (function.equalsIgnoreCase("E")) {
                System.out.println("密钥:");

                int key = scanner.nextInt();
                scanner.nextLine();
                String encrypted = encrypt(message, key);
                System.out.println("加密后的文字: " + encrypted);
                System.out.println("展示给用户看的文字(不能进行解密的文字，需要转化成unicode): " + encrypted(encrypted));
            } else if (function.equalsIgnoreCase("D")) {
                System.out.println("密钥:");
                int key = scanner.nextInt();
                scanner.nextLine();
                String decrypted = decrypt(message, key);
                System.out.println("解密的信息: " + decrypted);
            } else {
                System.out.println("输入错误，请重新输入!输入E进行加密，D进行解密:");
            }

            System.out.println("你想退出嘛？输入1不退出，其它任意字符退出.");
            String userInput = scanner.nextLine().trim();

            repeat = userInput.equals("1");
        }

        scanner.close();
    }

    public static String encrypt(String message, int key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int code = (int) c;
            code += key;
            ciphertext.append(String.format("\\u%04x", code));
        }

        return ciphertext.toString();
    }

    public static String decrypt(String message, int key) {
        StringBuilder plaintext = new StringBuilder();

        String[] codes = message.split("\\\\u");
        for (int i = 1; i < codes.length; i++) {
            int code = Integer.parseInt(codes[i], 16);
            code -= key;
            plaintext.append((char) code);
        }

        return plaintext.toString();
    }

    public static String encrypted(String string){
        String s = "";
        String[] strArr = string.split("\\\\u");
        for (int i = 1; i < strArr.length; i++) {
            int hexVal = Integer.parseInt(strArr[i], 16);
            s += (char)hexVal;
        }
        return s;
    }


}