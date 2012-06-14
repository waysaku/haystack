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
public class IOChannelTest {

	IOChannel ioChannel;
	byte[] bytes;

	@Before
	public void before() {
		File db = Paths.get("/Users/watanabe_yusaku/development/test.db").toFile();
		if(db.exists()) {
			db.delete();
		}

		try {
			ioChannel = IOChannel.getInstance(db.toPath());

			File file = new File("/tmp/shibuya.png");
			bytes = new byte[(int)file.length()];
			FileInputStream is = new FileInputStream(file);
			is.read(bytes);
		}catch(IOException ioe) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void test() {
		try {
			long start = System.currentTimeMillis();
			for(int i = 0; i < 10000; i++) {
				ioChannel.write(new ByteArrayInputStream(bytes));
			}
			System.out.println("time = " + (System.currentTimeMillis() - start));
		}catch(IOException ioe) {
			Assert.fail();
		}
	}

}
