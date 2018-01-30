import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class io {
	private StringBuilder sb;
	private static final String FILEPATH = "/home/jigar/Desktop/ML/dataset.txt";

	@Before
	public void setup() {
		sb = new StringBuilder();
	}

	@Test
	public void testNio() throws Exception {
		Path resource = Paths.get(FILEPATH);
		FileChannel input = FileChannel.open(resource, StandardOpenOption.READ);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (input.read(buffer) > -1) {

			buffer.flip();
			while (buffer.remaining() > 0)
				sb.append((char) buffer.get());
			buffer.flip();
		}
		input.close();
	}

	@Test
	public void testScanner() throws Exception {
		Scanner sc = new Scanner(new File(FILEPATH));
		while (sc.hasNext())
			sb.append(sc.nextLine());
		sc.close();

	}

	@Test
	public void testBufferedReader() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FILEPATH)));
		String temp;
		while ((temp = br.readLine()) != null)
			sb.append(temp);

		br.close();
	}
}
