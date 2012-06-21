/**
 * 
 */
package jp.co.haystack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author watanabe_yusaku
 *
 */
public class HDFSIOChannel {
	private ByteBuffer bf;
	private FSDataOutputStream fsds;
	
	private HDFSIOChannel(java.nio.file.Path path) throws IOException{
		bf = ByteBuffer.allocate(128);
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		fsds = fs.create(new Path(path.toUri()));
	}
	
	public static HDFSIOChannel getInstance(java.nio.file.Path path) throws IOException{
		return new HDFSIOChannel(path);
	}
	
	synchronized public void write(InputStream is) throws IOException {
		ReadableByteChannel rch = Channels.newChannel(is);
		while(rch.read(bf) > 0){
			bf.flip();
			byte[] b = bf.array();
			fsds.write(bf.array());
			bf.clear();
		};
	
	}


}
