package com.codeslasher.kryptor.crypt;

import java.util.LinkedList;

public class CryptLetter {
	private char originalLetter;
	private LinkedList <Character> letterList;
	
	public CryptLetter(char c)
	{
		originalLetter = c;
		letterList = new LinkedList<Character>();
	}
	
	public void addLetter(char c)
	{
		letterList.add(c);
	}
	
	public char getRandomChar()
	{
		int r = (int)(Math.random()*letterList.size());
		return letterList.get(r);
	}
	
	public char getChar(int index)
	{
		return letterList.get(index);
	}

	public char[] getAllChar()
	{
		char [] letters = new char[letterList.size()];
		for(int i = 0;i<letterList.size();i++)
		{
			letters[i] = letterList.get(i);
		}
		return letters;
	}
	
	public char getOriginalChar()
	{
		return originalLetter;
	}
	
	public boolean containsChar(char c)
	{
		for(int i = 0;i<letterList.size();i++)
		{
			if(c == letterList.get(i))
			{
				return true;
			}
		}
		return false;
	}
}
