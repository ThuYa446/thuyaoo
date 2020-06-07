package com.nirvasoft.fi.util;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TimeBasedOneTimePasswordUtil
{
  public static final int DEFAULT_TIME_STEP_SECONDS = Integer.parseInt(SMSUtility.StepSeconds);

  private static int NUM_DIGITS_OUTPUT = Integer.parseInt(SMSUtility.OutputDigit);
  private static final String blockOfZeros;

  static
  {
    char[] chars = new char[NUM_DIGITS_OUTPUT];
    for (int i = 0; i < chars.length; i++) {
      chars[i] = '0';
    }
    blockOfZeros = new String(chars);
  }

  public static String generateBase32Secret()
  {
    StringBuilder sb = new StringBuilder();
    Random random = new SecureRandom();
    for (int i = 0; i < 16; i++) {
      int val = random.nextInt(32);
      if (val < 26)
        sb.append((char)(65 + val));
      else {
        sb.append((char)(48 + (val - 26)));
      }
    }
    return sb.toString();
  }

  public static String generateCurrentNumber(String secret)
    throws GeneralSecurityException
  {
    return generateCurrentNumber(secret, System.currentTimeMillis() / 1000L, DEFAULT_TIME_STEP_SECONDS);
  }

  public static String generateOTPNumber(String secret, long generateTimeSecond)
    throws GeneralSecurityException
  {
    return generateCurrentNumber(secret, generateTimeSecond, DEFAULT_TIME_STEP_SECONDS);
  }

  public static String generateCurrentNumber(String secret, long currentTimeSecond, int timeStepSeconds)
    throws GeneralSecurityException
  {
    byte[] key = decodeBase32(secret);

    byte[] data = new byte[8];
    long value = currentTimeSecond / timeStepSeconds;

    for (int i = 7; value > 0L; i--) {
      data[i] = (byte)(int)(value & 0xFF);
      value >>= 8;
    }

    SecretKeySpec signKey = new SecretKeySpec(key, SMSUtility.EncryptType);

    Mac mac = Mac.getInstance(SMSUtility.EncryptType);
    mac.init(signKey);
    byte[] hash = mac.doFinal(data);

    int offset = hash[(hash.length - 1)] & 0xF;

    long truncatedHash = 0L;
    for (int i = offset; i < offset + 4; i++) {
      truncatedHash <<= 8;

      truncatedHash |= hash[i] & 0xFF;
    }

    truncatedHash &= 2147483647L;

    truncatedHash %= 1000000L;

    return zeroPrepend(truncatedHash, NUM_DIGITS_OUTPUT);
  }

  public static String qrImageUrl(String keyId, String secret)
  {
    StringBuilder sb = new StringBuilder(128);
    sb.append("https://chart.googleapis.com/chart");
    sb.append("?chs=200x200&cht=qr&chl=200x200&chld=M|0&cht=qr&chl=");
    sb.append("otpauth://totp/").append(keyId).append("%3Fsecret%3D").append(secret);
    return sb.toString();
  }

  static String zeroPrepend(long num, int digits)
  {
    String numStr = Long.toString(num);
    if (numStr.length() >= digits) {
      return numStr;
    }
    StringBuilder sb = new StringBuilder(digits);
    int zeroCount = digits - numStr.length();
    sb.append(blockOfZeros, 0, zeroCount);
    sb.append(numStr);
    return sb.toString();
  }

  static byte[] decodeBase32(String str)
  {
    int numBytes = (str.length() * 5 + 4) / 8;

    numBytes++;
    byte[] result = new byte[numBytes];
    int resultIndex = 0;
    int which = 0;
    int working = 0;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if ((ch >= 'a') && (ch <= 'z')) {
      }
      else
      {
        if ((ch >= 'A') && (ch <= 'Z')) {
        }
        else
        {
          if ((ch >= '0') && (ch <= '9')) { } else {
            if (ch == '=')
            {
              which = 0;
              break;
            }
            throw new IllegalArgumentException("Invalid base-32 character: " + ch);
          }
        }
      }
      int val3 = 0;
      switch (which)
      {
      case 0:
        working = (val3 & 0x1F) << 3;
        which = 1;
        break;
      case 1:
        working |= (val3 & 0x1C) >> 2;
        result[(resultIndex++)] = (byte)working;

        working = (val3 & 0x3) << 6;
        which = 2;
        break;
      case 2:
        working |= (val3 & 0x1F) << 1;
        which = 3;
        break;
      case 3:
        working |= (val3 & 0x10) >> 4;
        result[(resultIndex++)] = (byte)working;

        working = (val3 & 0xF) << 4;
        which = 4;
        break;
      case 4:
        working |= (val3 & 0x1E) >> 1;
        result[(resultIndex++)] = (byte)working;

        working = (val3 & 0x1) << 7;
        which = 5;
        break;
      case 5:
        working |= (val3 & 0x1F) << 2;
        which = 6;
        break;
      case 6:
        working |= (val3 & 0x18) >> 3;
        result[(resultIndex++)] = (byte)working;

        working = (val3 & 0x7) << 5;
        which = 7;
        break;
      case 7:
        working |= val3 & 0x1F;
        result[(resultIndex++)] = (byte)working;
        which = 0;
      }
    }

    if (which != 0) {
      result[(resultIndex++)] = (byte)working;
    }
    if (resultIndex != result.length) {
      result = Arrays.copyOf(result, resultIndex);
    }
    return result;
  }
}