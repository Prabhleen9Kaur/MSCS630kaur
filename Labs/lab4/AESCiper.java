/**
 * file: AESCiper.java
 * author: Prabhleen Kaur
 * course: MSCS 630
 * assignment: Lab 4
 * due date: Wednesday , April 4, 2018.
 * version: 9.0.4
 *
 * This file implements AES(Advanced Encryption Standard) algorithm
 * and generates a set of Round Keys from the 128- bit key
 */

import java.util.Scanner;

public class AESCiper {
    static String[][] Ke = new String[4][4];
    static String[][] W = new String[4][44];
    static String[][] wNew = new String[4][4];
    static String outHex;
    static String inHex;
    static int count = 0;

    static final String sbox[][] = {
            {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
            {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
            {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
            {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
            {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
            {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
            {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
            {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
            {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
            {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
            {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
            {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
            {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
            {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
            {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
    };

    static final String rCon[][] = {
            {"8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A"},
            {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
            {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
            {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
            {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
            {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
            {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
            {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
            {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
            {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
            {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
            {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
            {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
            {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
            {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
    };

    public void roundKeyHex (String KeyHex){
        //To Compute Ke Matrix
       char[] c = KeyHex.toCharArray();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Ke[j][i] = Character.toString(c[count]) + Character.toString(c[count + 1]);
                count += 2;
            }
        }

        //For W Matrix
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                W[j][i] = Ke[j][i];
            }
        }

        //To Initialize elements of W matrix
        for (int i = 4; i < 44; i++) {
            for (int j = 0; j < 4; j++) {
                W[j][i] = "0";
            }
        }

        //Compute S-Box and shift
        for (int wCount = 4; wCount < 44; wCount++) {
            if (wCount % 4 == 0) {
                for (int i = 0; i < 4; i++) {
                    if (i != 3) {
                        wNew[0][i] = aesSBox(W[i + 1][wCount - 1]);
                    } else {
                        wNew[0][i] = aesSBox(W[0][wCount - 1]);
                    }
                }
                //To Find RCon value
                String RconValue = aesRcon(wCount / 4);
                //To Calculate RCon XOR
                String temporary = wNew[0][0];
                wNew[0][0] = XOR(RconValue, temporary);
                for (int i = 0; i < 4; i++) {
                    W[i][wCount] = (XOR(W[i][wCount - 4], wNew[0][i]));
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    W[i][wCount] = (XOR(W[i][wCount - 4], W[i][wCount - 1]));
                }
            }
        }
        printKeys();
    }

    /**
     * This function is used to calculate the XOR value of two Strings
     */
    public static String XOR(String a, String b) {
        int aDecimal = Integer.parseInt(a, 16);
        int bDecimal = Integer.parseInt(b, 16);
        int result = aDecimal ^ bDecimal;
        String HexResult = Integer.toHexString(result);
        return HexResult.length() == 1 ? ("0" + HexResult) : HexResult;
    }
    /**
     * This function returns the Round Constant value for the Corresponding round
     */
    public static String aesRcon(int round) {
        String rConresult= rCon[0][round];
        return rConresult;
    }
    /**
     * This function returns the output byte using S- box function
     */
    public static String aesSBox(String inHex) {
        outHex = sbox[Integer.parseInt(inHex.split("")[0], 16)][Integer.parseInt(inHex.split("")[1], 16)];
        return outHex;
    }
    /**
     * This function returns the roundKeys
     */
    public static void printKeys() {
        String roundKey="";
        for(int i=0; i<44; i++) {
            for(int j=0; j<4; j++) {
                roundKey+=W[j][i];
                if((i+1)%4==0 && j==3) {
                    System.out.println(roundKey);
                    roundKey="";
                }
            }
        }
    }
}
