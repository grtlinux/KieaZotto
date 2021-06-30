package org.tain.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTools {

	// text.replaceAll("(?<=^.{47}).*$", "...");

	public static String truncate(String line, int maxLength) {
		if (line.length() < maxLength)
			return line;
		int pos = line.lastIndexOf(" ", maxLength - 3);
		if (pos <= 0)
			pos = maxLength - 3; // no spaces, so just cut anyway
		return line.substring(0, pos) + "...";
	}

	public static String smartSubstring(String str, int maxLength) {
		String subStr = str.substring(0);
		if (maxLength == 0) {
			return "";
		} else if (str.length() <= maxLength) {
			return str;
		} else {
			int i = maxLength;
			while (i >= 0) {
				while (str.length() < i) {
					i--;
				}
				if (str.charAt(i) == ' ') {
					subStr = str.substring(0, i);
					break;
				}
				i--;
			}
			return subStr;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private final static String NON_THIN = "[^iIl1\\.,']";

	private static int textWidth(String str) {
		return (int) (str.length() - str.replaceAll(NON_THIN, "").length() / 2);
	}

	public static String ellipsize(String text, int max) {

		if (textWidth(text) <= max)
			return text;

		// Start by chopping off at the word before max
		// This is an over-approximation due to thin-characters...
		int end = text.lastIndexOf(' ', max - 3);

		// Just one long word. Chop it off.
		if (end == -1)
			return text.substring(0, max - 3) + "...";

		// Step forward as long as textWidth allows.
		int newEnd = end;
		do {
			end = newEnd;
			newEnd = text.indexOf(' ', end + 1);

			// No more spaces.
			if (newEnd == -1)
				newEnd = text.length();

		} while (textWidth(text.substring(0, newEnd) + "...") < max);

		return text.substring(0, end) + "...";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	// KANG-20200918
	public static String stringFromFile(String filePath) {
		StringBuffer sb = new StringBuffer();
		if (Boolean.TRUE) {
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(filePath));
				String line = null;
				while ((line = br.readLine()) != null) {
					if ("".equals(line.trim()) || line.trim().charAt(0) == '#')
						continue;
					sb.append(line).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) try { br.close(); } catch (Exception e) {}
			}
		}
		return sb.toString();
	}
	
	// KANG-20200918
	public static void stringToFile(String strSource, String filePath) {
		if (Boolean.TRUE) {
			PrintWriter pw = null;
			
			try {
				pw = new PrintWriter(new FileWriter(filePath));
				pw.print(strSource);
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) try { pw.close(); } catch (Exception e) {}
			}
		}
	}
	
	// KANG-20210515
	public static byte[] bytesFromFile(String filePath) throws Exception {
		return Files.readAllBytes(new File(filePath).toPath());
	}
	
	// KANG-20210515
	public static void bytesToFile(byte[] bData, String filePath) throws Exception {
		Files.write(Paths.get(filePath), bData);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getExtension(String fileName) {
		int pos = fileName.lastIndexOf('.');
		if (pos < 0)
			return "_NO_EXT_";
		
		return fileName.substring(pos + 1);
	}
	
	public static boolean isExtension(String fileName, String ext) {
		return getExtension(fileName).equals(ext);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getDateTime(String strFormat) throws Exception {
		return new SimpleDateFormat(strFormat).format(new Date());
	}
	
	public static String getYYMMDD() throws Exception {
		return getDateTime("yyMMdd");
	}
	
	public static String getYYYYMMDD() throws Exception {
		return getDateTime("yyyyMMdd");
	}
	
	public static String getYYMMDDHHMMSS() throws Exception {
		return getDateTime("yyMMddHHmmss");
	}
	
	public static String getHHMMSS() throws Exception {
		return getDateTime("HHmmss");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getHttpPath(String url) {
		for (int i = 0; i < 3; i++) {
			int pos = url.indexOf('/');
			if (pos < 0)
				break;
			url = url.substring(pos + 1);
		}
		return url;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static final String PRINT_HEX_CHARSET = "euc-kr";
	//private static final String PRINT_HEX_CHARSET = "utf-8";
	
	public static void printHexAllAscii() throws Exception {
		if (Boolean.TRUE) {
			byte[] bData = new byte[256];
			for (int i=0x00; i <= 0xff; i++) {
				bData[i] = (byte) i;
			}
			printHex(bData);
		}
	}
	
	public static void printHex(String strData) throws UnsupportedEncodingException, Exception {
		printHex(strData.getBytes(PRINT_HEX_CHARSET));
	}
	
	public static void printHex(final byte[] bData) throws Exception {
		
		int lenData = bData.length;
		System.out.println("Size: " + lenData);
		System.out.println("Offset--  Hex--------------------- ------------------------  Data-------------");
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		int maxLen = (lenData + 15) / 16 * 16;
		for (int i=0; i < maxLen; i += 16) {
			int begSec = i;
			int midSec = i + 8;
			int endSec = i + 16;
			sb1.setLength(0);
			sb2.setLength(0);
			sb3.setLength(0);
			
			sb1.append(String.format("%08d", begSec));
			for (int j=begSec; j < endSec; j++) {
				if (j == midSec) {
					sb2.append(" ");
					sb3.append(" ");
				}
				if (j < lenData) {
					sb2.append(String.format("%02X ", bData[j] & 0xff));
					//char ch = (char)(bData[j] & 0xFF);
					// if (Character.isLetterOrDigit(ch)) {
					if (0x20 <= bData[j] && bData[j] < 0x7f) {
						sb3.append((char)(bData[j] & 0xFF));
					} else {
						sb3.append('.');
					}
				} else {
					sb2.append("   ");
					sb3.append(" ");
				}
			}
			sb.append(sb1.toString()).append("  ");
			sb.append(sb2.toString()).append("  ");
			sb.append(sb3.toString()).append('\n');
			System.out.println(sb1.toString() + "  " + sb2.toString() + "  " + sb3.toString());
		}
		//System.out.println(sb.toString());
		System.out.println("--------  ------------------------ ------------------------  -------- --------");
	}
}