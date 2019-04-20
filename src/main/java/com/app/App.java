package com.app;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.leucine.storage.SPDirectory;
import com.leucine.storage.SPException;
import com.leucine.storage.SPFile;
import com.leucine.storage.StorageProvider;
import com.leucine.storage.StorageService;

public class App {
public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, SPException
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
System.out.println(br.readLine());
br.close();
}
}
