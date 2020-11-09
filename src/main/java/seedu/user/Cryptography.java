package seedu.user;


public class Cryptography {
    
    public static String encipherPassword(String plainText) {
        
        int plainTextLength = plainText.length();

        int[] numbers = new int[plainTextLength];

        // Creating array of string length 
        char[] ch = new char[plainTextLength];

        // Copy character by character into array 
        for (int i = 0; i < plainTextLength; i++) {
            ch[i] = plainText.charAt(i);
        }

        for (int i = 0; i < plainTextLength; i++) {
            numbers[i] = ((int)(plainText.charAt(i) - '0'));
        }
        
        int[] cipherNum = new int[plainTextLength];
        
        for (int i = 0; i < plainTextLength; i++) {
            if (i == 0 | i == 2 | i == 4) {
                cipherNum[i] = 1 * numbers[i] + 2 * numbers[i + 1];
                if (cipherNum[i] >= 10) {
                    cipherNum[i] = cipherNum[i] % 10; 
                }
                
                cipherNum[i + 1] = 0 * numbers[i] + 3 * numbers[i + 1];
                if (cipherNum[i + 1] >= 10) {
                    cipherNum[i + 1] = cipherNum[i + 1] % 10;
                }
            }
            
        }
        
        StringBuilder strNum = new StringBuilder();

        for (int num : cipherNum) {
            strNum.append(num);
        }
        
        String cipherText;

        cipherText = strNum.toString(); 
        
        return cipherText;
    }

    public static String decipherPassword(String cipherText) {
        
        int cipherTextLength = cipherText.length();

        int[] numbers = new int[cipherTextLength];

        char[] ch = new char[cipherTextLength];

        for (int i = 0; i < cipherTextLength; i++) {
            ch[i] = cipherText.charAt(i);
        }

        for (int i = 0; i < cipherTextLength; i++) {
            numbers[i] = ((int)(cipherText.charAt(i) - '0'));
        }

        int[] plainNum = new int[cipherTextLength];

        for (int i = 0; i < cipherTextLength; i++) {
            if (i == 0 | i == 2 | i == 4) {
                plainNum[i] = 21 * numbers[i] - 14 * numbers[i + 1];
                if (plainNum[i] < 0) {
                    plainNum[i] = 10 + plainNum[i];
                    plainNum[i] = plainNum[i] % 10;
                } else if (plainNum[i] >= 10) {
                    plainNum[i] = plainNum[i] % 10;
                }
                
                plainNum[i + 1] = 0 * numbers[i] + 7 * numbers[i + 1];
                if (plainNum[i] < 0) {
                    plainNum[i] = 10 - plainNum[i];
                    plainNum[i] = plainNum[i] % 10;
                } else if (plainNum[i + 1] >= 10) {
                    plainNum[i + 1] = plainNum[i + 1] % 10;
                }
            }
        }

        StringBuilder strNum = new StringBuilder();

        for (int num : plainNum) {
            strNum.append(num);
        }

        String plainText;

        plainText = strNum.toString();

        return plainText;
    }
}
