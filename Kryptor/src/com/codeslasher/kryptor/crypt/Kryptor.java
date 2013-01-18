package com.codeslasher.kryptor.crypt;

import java.util.Arrays;
import java.util.Random;

public class Kryptor {
	private final char [] originalCharacterArray = {' ','!','#','$','%','&','(',')','*','+',',','-','.','/','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','^','_','`','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','{','|','}','~','¡','¢','£','¤','¥','¦','§','¨','©','ª','«','¬','­','®','¯','°','±','²','³','´','µ','¶','·','¸','¹','º','»','¼','½','¾','¿','À','Á','Â','Ã','Ä','Å','Æ','Ç','È','É','Ê','Ë','Ì','Í','Î','Ï','Ð','Ñ','Ò','Ó','Ô','Õ','Ö','×','Ø','Ù','Ú','Û','Ü','Ý','Þ','ß','à','á','â','ã','ä','å','æ','ç','è','é','ê','ë','ì','í','î','ï','ð','ñ','ò','ó','ô','õ','ö','÷','ø','ù','ú','û','ü','ý','þ','ÿ'};
	
	private char [] characterArray = {' ','!','#','$','%','&','(',')','*','+',',','-','.','/','0','1','2','3','4','5','6','7','8','9',':',';','<','=','>','?','@','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','^','_','`','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','{','|','}','~','¡','¢','£','¤','¥','¦','§','¨','©','ª','«','¬','­','®','¯','°','±','²','³','´','µ','¶','·','¸','¹','º','»','¼','½','¾','¿','À','Á','Â','Ã','Ä','Å','Æ','Ç','È','É','Ê','Ë','Ì','Í','Î','Ï','Ð','Ñ','Ò','Ó','Ô','Õ','Ö','×','Ø','Ù','Ú','Û','Ü','Ý','Þ','ß','à','á','â','ã','ä','å','æ','ç','è','é','ê','ë','ì','í','î','ï','ð','ñ','ò','ó','ô','õ','ö','÷','ø','ù','ú','û','ü','ý','þ','ÿ'};
	
	private char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','å','ä','ö',' ','0','1','2','3','4','5','6','7','8','9',',','.','?','!'};
	
	private CryptLetter [] cryptLetters;
	
	private StringBuilder newMessage;
	
	private String prepareMessage(String msg)
	{
		return msg.toLowerCase();
	}
	
	
	private void prepateLetters()
	{
		characterArray = Arrays.copyOf(originalCharacterArray, originalCharacterArray.length);
		int len = characterArray.length / alphabet.length;
		int index = 0;
		cryptLetters = new CryptLetter[alphabet.length];
		for(int i = 0;i<cryptLetters.length;i++)
		{
			cryptLetters[i] = new CryptLetter(alphabet[i]);
			for(int j = 0;j<len;j++)
			{
				cryptLetters[i].addLetter(characterArray[index]);
				index++;
			}
		}
		
	}
	
	/**
	 * Mixes the alphabet for crypting
	 * @param rand
	 */
	private void scramble(Random rand)
	{
		for(int i = 0;i<600;i++)
		{
			int first = rand.nextInt(characterArray.length);
			int second = rand.nextInt(characterArray.length);
			char temp = characterArray[first];
			characterArray[first] = characterArray[second];
			characterArray[second] = temp;
		}
	}
	
	public String crypt(String msg)
	{
		msg = prepareMessage(msg);
		long key = getKeyNumber();
		Random rand = new Random(key);
		scramble(rand);
		prepateLetters();
		newMessage = new StringBuilder();
		newMessage.append(cryptKey(key));
		for(int i = 0;i<msg.length();i++)
		{
			int index = charIndex(msg.charAt(i));
			newMessage.append(cryptLetters[index].getRandomChar());
		}
		return newMessage.toString();
	}
	
	/**
	 * Finds the index of the character in the original alphabet
	 * @param c - character to search for
	 * @return index
	 */
	private int charIndex(char c)
	{
		for(int i = 0;i<alphabet.length;i++)
		{
			if(alphabet[i] == c)
			{
				return i;
			}
		}
		return -1;
	}
	
	private long getKeyNumber()
	{
		return new KeyNumber().toLong();
	}
	
	public String deCrypt(String msg)
	{
		long key = deCryptKey(msg);
		msg = cutKey(msg);
		Random rand = new Random(key);
		scramble(rand);
		prepateLetters();
		
		newMessage = new StringBuilder();
		for(int i = 0;i<msg.length();i++)
		{
			newMessage.append(deCryptChar(msg.charAt(i)));
		}
		return newMessage.toString();
	}
	
	
	//FIX
	private String cutKey(String msg) {
		String m = msg.substring(1);
		return m;
	}


	public char deCryptChar(char c)
	{
		for(int i = 0;i<cryptLetters.length;i++)
		{
			if(cryptLetters[i].containsChar(c))
			{
				return cryptLetters[i].getOriginalChar();
			}
		}
		return ' '; //should not happen
	}
	
	private String cryptKey(long key)
	{
		return ""+key;
	}

	private long deCryptKey(String msg) {

		return 0;
	}
	
	
	
}
