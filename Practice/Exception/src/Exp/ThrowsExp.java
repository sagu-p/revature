package Exp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ThrowsExp {
	
	public void openFile( String fileName) throws FileNotFoundException
	{
		FileInputStream fs = new FileInputStream(fileName);
	}

}
