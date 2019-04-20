package com.leucine.storage;

public class SPException extends Exception{
public SPException(String message)
{
	super(message);
}
public SPException(String message,Throwable t)
{
	super(message,t);
}
public SPException(Throwable t)
{
	super(t);
}
}

