package com.leucine.storage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()throws Exception
    {
    	StorageService service=StorageService.getStorageService();
    	SPDirectory dir=service.getDirectory("sample");
    	dir.create();
    	SPDirectory dir1=dir.getItemAsDirectory("sample1");
    	dir1.create();
    	SPDirectory dir3=dir1.getItemAsDirectory("sample3");
    	dir3.create();
    	SPFile file=dir3.getItemAsFile("abcd");
    	file.create();
    	file.writeContentsFrom(new ByteArrayInputStream("abcd".getBytes("UTF-8")));
    	SPDirectory dir2=dir.getItemAsDirectory("sample2");
    	dir2.create();
    	SPFile file1=service.getFile("sample/sample1/sample3/abcd");
    	BufferedReader br=new BufferedReader(new InputStreamReader(file1.getInputStream()));
    	assert (br.readLine().equals("abcd"));
    	br.close();
    }
}
