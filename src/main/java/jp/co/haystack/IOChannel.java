package jp.co.haystack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

/**
 * @author watanabe_yusaku
 *
 */
public class IOChannel {
	private ByteBuffer bf;
	private FileChannel inputChannel;
	private FileChannel outputChannel;
	
	File dataFile;
	private IOChannel(Path path) throws IOException {
		bf = ByteBuffer.allocate(128);
//		bf = ByteBuffer.allocateDirect(128);
//		File dataFile = path.toFile();
		dataFile = path.toFile();
		if(!dataFile.exists()) {
			dataFile.createNewFile();
		}
		
		inputChannel = new FileInputStream(dataFile).getChannel();
		outputChannel = new FileOutputStream(dataFile).getChannel();
	};
	
	public static IOChannel getInstance(Path path) throws IOException{
		return new IOChannel(path);
	}
	
//	bytebuffer			3822	3719	3593
//	direct bytebuffer	3416	3222	3266
//	ouputstream			333405
	
	synchronized public void write(InputStream is) throws IOException {
		
//		FileOutputStream os = new FileOutputStream(dataFile, true);
//		InputStreamReader r = new InputStreamReader(is);
//		int c;
//		while((c = r.read()) != -1) {
//			os.write(c);
//		}
//		os.close();
//		r.close();
		
		ReadableByteChannel rch = Channels.newChannel(is);
		while(rch.read(bf) > 0){
			bf.flip();
			outputChannel.write(bf);
			bf.clear();
		};
		
	}
	

}
