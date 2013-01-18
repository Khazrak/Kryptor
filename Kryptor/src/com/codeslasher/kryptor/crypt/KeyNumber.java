package com.codeslasher.kryptor.crypt;

public class KeyNumber {
	private long key;
	public KeyNumber()
	{
		long time = System.currentTimeMillis();
		if(time > 0)
		{
			key = time;
		}
		else
		{
			key = (long)(Math.random()*9999999);
		}
	}
	
	public long toLong()
	{
		return key;
	}
}
