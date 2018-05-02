/**
 * file: AESCipher.java
 * author: Prabhleen Kaur
 * course: MSCS 630
 * assignment: Lab 5
 * due date: Wednesday April 18, 2018
 * version: 1.0
 *
 * This file implements Adavanced Encryption Standard(AES) algorithm
 * and generates cipher text for the given plain text using the entered Key
 */
import java.util.Scanner;
import java.util.ArrayList;

/*
 * This class is sed to produce the encrypted message 
 *
 * It contains Rijndael s-box (taken as an array of string)
 * 
 * It contains Rcon -Rijndael key schedule
 *
 */
 
 
class AESCipher
{
  
   char[][] inputData = new char[4][44];
   static String[][] galoisMatrix = {
     {"02", "03", "01", "01"},
     {"01", "02", "03", "01"},
     {"01", "01", "02", "03"},
     {"03", "01", "01", "02"}
  };

  private static final String sbox[][] = {
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
  
  private static final String rCon[][] = {
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

  /**
  * This method is used to generates 10 round keys (one key ine each round)
  * input 128-bit key
  *
  * Parameters used :
  * keyHex : StrIng type ,input form the user
  * chars  : character type ,hexadecimal value ,split into pair of 2 values from 128-bit key
  * W[][]  : char type , 4X44 matrix ,each key make 4X4 matrix (each key has 16 ASCII values)
  * k[][]  : store 128-bit key as an array
  * count  : used as a counter to traverse through each value while iterating through loop 
  * wNew   : used to store value transformed using sbox
  * Rcon   : Rcon value holds the round constant of each round
  */
  
  
  char[] chars;
  char[][] W= new char[4][44];
  char[][] K= new char[4][4];
  char[][] wNew= new char[4][4];
  int count=0;
  char temporary;
  char Rcon;
  
  public static void aesRoundKeys(String KeyHex, String InputText) {
//Step1:Initializing values for array K using for loop

    chars = KeyHex.toCharArray();
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 4; j++) {
        K[j][i] = Character.toString(chars[count]) + Character.toString(chars[count+1]);
        count += 2;
      }
    }
   //Step 2: To store the values of round keys produced in 40 columns
    for(int i=0; i<4; i++) {
      for(int j=0; j<4; j++) {
        W[j][i] = K[j][i];
      }
    }
    //Step: for loop Initialises other elements of W matrix to 0
    for(int i=4; i<44; i++) {
      for(int j=0; j<4; j++) {
        W[j][i]="0";
      }
    }
 
    
    for(int wCount=4; wCount<44; wCount++) {
      //Step 3(2):  S-Box and shift
      if(wCount%4==0) {
        for(int i=0; i<4; i++) {
          if(i!=3) {
            wNew[0][i] = aesSBox(W[i+1][wCount-1]);
          }
          else {
            wNew[0][i] = aesSBox(W[0][wCount-1]);
          }
        }
        //Finding RCon value
        Rcon = aesRcon(wCount/4);
        //Calculating RCon XOR
        temporary= wNew[0][0];
        wNew[0][0] = XOR(Rcon, temporary);
        //Last Step
        for(int i=0;i<4;i++) {
          W[i][wCount]= (XOR(W[i][wCount-4],wNew[0][i]));
        }
      }
      //Step 3: (1)
      else {
        for(int i = 0; i < 4; i++) {
          W[i][wCount]= (XOR(W[i][wCount-4], W[i][wCount-1]));
        }
      }
    }
    //printKeys();
    generateDataMatrix(InputText);
  }
  /**
  * This method calculate the XOR value of two Strings 
  */
  public static String XOR(String a, String b) {
    int aDecimal = Integer.parseInt(a, 16);
    int bDecimal = Integer.parseInt(b, 16);
    int result = aDecimal ^ bDecimal;
    String HexResult = Integer.toHexString(result);
    return HexResult.length() == 1 ? ("0" + HexResult) : HexResult;
  }
  /**
  * This function takes an input integer round and returns the Round Constant value for the Corresponding round
  *
  * Parameters used : 
  * round      : used to input the round value by the user
  * rConresult : used to store the value rCon value for each round
  */
  public static String aesRcon(int round) {
    char rConresult= rCon[0][round];
    return rConresult;
  }
  /**
   * This function takes an input character inHex and returns the output character using S- box function
   * Parameters used : 
   * inHex      : used to input as an index value for the sbox function
   * outHex      : used to store the return value (output) transfrmed using sbox function
   * 
   */
  
  char outHex;
  char inHex;
  
  public static String aesSBox(char inHex) {
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

  /**
  * This function calculates the XOR for state matrix and round key to generate a new 4*4 matrix
  * Parameters used : 
  * outStateHex     : used to store and return transformed matrix from XOR operation
  *
  */
  public static String[][] aesStateXOR(String[][] sHex, String[][] keyHex) {
    char[][] outStateHex = new char[4][4];
    for(int i=0; i<4; i++) {
      for(int j=0; j<4;j++) {
        outStateHex[i][j] = XOR(sHex[i][j], keyHex[i][j]);
      }
    }
    return outStateHex;
  }

  /**
  * This function substitutes each entries of input matrix using sbox funtion and return a new 4X4 matrix 
  */
  public static String[][] aesNibbleSub(String[][] inStateHex) {
    char[][] outStateHex = new char[4][4];
    for(int i=0; i<4; i++) {
      for(int j=0; j<4; j++) {
        outStateHex[i][j] = aesSBox(inStateHex[i][j]);
      }
    }
    return outStateHex;
  }

  /**
  * This funtion performs shift operation on  every element in cyclic order to left by their row number and return a new matrix
  * parameter used : 
  *row : used to hold the rowvale for shift operations
  */
  public static String[][] aesShiftRow(String[][] inStateHex) {
    String[][] outStateHex = new String[4][4];
    int row=0;
    for(int i=0; i<4; i++, row++) {
      for(int j=0; j<4; j++) {
        outStateHex[i][j] = inStateHex[i][(j+row)%4];
      }
    }
    return outStateHex;
  }

  /**
  * This method uses galois matrix to perform Galois multiplication on state matrix and corresponding values to return a new resultant matrix
  */
  public static String[][] aesMixColumn(String[][] inStateHex) {
    String sum="0";
    String[][] matrixResult = new String[4][4];
    int row = 0;
    int col = 0;
    while(col<=3) {
      for(int r=0; r<4 && col<4; r++, row++ ) {
        sum = "0";
        for(int c=0,rowState=0; c<4;c++,rowState++) {
          if(null != galoisMatrix[r][c]) {
            switch(galoisMatrix[r][c]) {
              case "02":
                sum = XOR(sum, shiftBit(inStateHex[rowState][col]));
                break;
              case "03":
                String temp = XOR(inStateHex[rowState][col],shiftBit(inStateHex[rowState][col]));
                sum= XOR(sum, temp);
                break;
              case "01":
                sum=XOR(sum, inStateHex[rowState][col]);
                break;
              default:
                break;
              }
            }
         }
          matrixResult[r][col]=sum;
          if(row==3) {
            col++;
            row = -1;
          }
      }
    }
    return matrixResult;
  }

  /**
  * This funt=ction is used to shifts the bitvalue by 1
  */
  public static String shiftBit(String pstrHexValue) {
    String binary = Integer.toBinaryString(Integer.parseInt(pstrHexValue, 16));
    while(binary.length() < 8) {
      binary = "0" + binary;
    }

    int value = Integer.parseInt(binary, 2);
    String shiftedBinary = Integer.toBinaryString(value<<1);
    shiftedBinary = (shiftedBinary.length()>8 ? shiftedBinary.substring(1):shiftedBinary);
    if(binary.substring(0,1).equals("1")) {
      String constant = Integer.toString(27, 2);
      while(constant.length()<8) {
        constant = "0"+constant;
      }
      return XOR(Integer.toHexString(Integer.parseInt(shiftedBinary, 2)),
      Integer.toHexString(Integer.parseInt(constant, 2)));
    }
    else {
      return Integer.toHexString(Integer.parseInt(shiftedBinary, 2));
    }
  }

  /**
  * This function performs AES Encryption using 128-bit key and computes data for one complete round of AES encryption
  */
  public static String[][] AES(String[][] roundData, String[][] roundKey, int roundCount) {
    String[][] nextStepInput = null;
    //Step 1
    nextStepInput = aesStateXOR(roundData, roundKey);

    //Step 2
    if(nextStepInput != null) {
      nextStepInput = aesNibbleSub(nextStepInput);
    }

    //Step 3
    if(nextStepInput != null) {
      nextStepInput = aesShiftRow(nextStepInput);
    }

    //Step 4
    if(roundCount != 10) {
      if(nextStepInput != null) {
        nextStepInput = aesMixColumn(nextStepInput);
      }
    }
    return nextStepInput;
  }

  /**
  * This method takes the plain text as the input  and using aesRoundKeys and generate cipher text message
  */
  public static void aes(String pInputText, String pInputKey) {
    if(!(pInputText.trim().equals("")&& pInputKey.trim().equals(""))) {
      aesRoundKeys(pInputText, pInputKey);
      int roundCount = 0;
      String[][] larRoundKey = new String[4][4];
      String[][] larRoundData = new String[4][4];

      //Copy the Input Data matrix into a local Matrix.
      for (int row = 0; row < W.length; row++) {
        System.arraycopy(inputData[row], 0, larRoundData[row], 0, 4);
      }

      for(int increment=0; increment<W[0].length; increment+=4) {
        for(int row=0; row<W.length; row++) {
          System.arraycopy(W[row],increment,larRoundKey[row], 0, 4);
        }
        if(roundCount == 10)
          larRoundData = aesStateXOR(larRoundData, larRoundKey);
        else {
          roundCount++;
          larRoundData = AES(larRoundData, larRoundKey, roundCount);
        }
      }
      if(larRoundData!=null) {
        printRoundData(larRoundData);
      }
    }
  }

  public static void generateDataMatrix(String inputText) {
    try {
      int col=0;
      for(int colCounter =0; colCounter<(inputText.length()-1);colCounter +=8, col++) {
        int row=0;
        for(int rowCounter=colCounter; rowCounter<(colCounter+8);rowCounter+=2, row++) {
          inputData[row][col]=inputText.substring(rowCounter, (rowCounter+2));
        }
      }
    } catch(Exception ex) {
        System.out.println("Exception in generateDataMatrix: "+ex.getMessage());
      }
  }

  /**
  * This method prints the cipher generated by AES algorithm in the aes method*
  * it uses exception to get the message generated 
  * it is used to convert message to upper case
  */
  public static void printRoundData(String[][] larRoundData) {
    try {
      for (int cols = 0; cols < 4; cols++) {
        for (int row = 0; row < 4; row++) {
          System.out.print(larRoundData[row][cols].toUpperCase());
        }
      }
    } catch (Exception ex) {
      System.out.println("Exception in printRoundData is : " + ex.getMessage());
    }
  }
}