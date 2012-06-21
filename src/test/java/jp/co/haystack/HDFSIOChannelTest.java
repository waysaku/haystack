/**
 * 
 */
package jp.co.haystack;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author watanabe_yusaku
 *
 */
public class HDFSIOChannelTest {
	HDFSIOChannel channel;
	IOChannel ioChannel;
	byte[] bytes;
	
	@Before
	public void before() {
		
		try {
			channel = HDFSIOChannel.getInstance(Paths.get("/Users/watanabe_yusaku/Desktop/hadoop-test.db"));
			
			File file = new File("/tmp/shibuya.png");
			bytes = new byte[(int)file.length()];
			FileInputStream is = new FileInputStream(file);
			is.read(bytes);
		}catch(IOException ioe) {
			Assert.fail();
		}
	}

	@Test
	public void test() throws Exception{
		try {
			channel.write(new ByteArrayInputStream(bytes));
		}catch(IOException ioe) {
			Assert.fail();
		}
		
	}

}
