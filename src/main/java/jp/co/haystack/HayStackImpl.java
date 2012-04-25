package jp.co.haystack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class HayStackImpl implements HayStack{
	private FileChannel inputChannel;
	private FileChannel outputChannel;
			
	public HayStackImpl(String dataFilePath) {
		File dataFile = new File(dataFilePath);
		try {
			inputChannel = new FileInputStream(dataFile).getChannel();
			outputChannel = new FileOutputStream(dataFile).getChannel();
		}catch(FileNotFoundException fnfe) {
			//:TODO
		}
	}

	@Override
	public void save(String path, byte[] bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File load(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(String path) {
		// TODO Auto-generated method stub
		return false;
	}

}
