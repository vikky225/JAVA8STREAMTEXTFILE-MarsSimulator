package com.explorer.readers;

import com.toysimulator.readers.CommandReader;
import junit.framework.TestCase;
import org.junit.Before;

import java.io.File;
import java.util.ArrayList;

public class CommandReaderTest extends TestCase {
	private CommandReader reader;

	@Before
	public void setUp() {
		reader = new CommandReader();
	}

	public void testReadFromFileNotExists() {
		try {
			reader.readFrom(new File("xyz.txt"));
			fail("Throws an exception since xyz.txt not exist");
		} catch (Exception e) {
			assertEquals("File not exist", e.getMessage());
		}
	}

	public void testReadFromFileNotFound() {
		try {
			File file = null;
			reader.readFrom(file);
			fail("Throws an exception because file not found");
		} catch (Exception e) {
			assertEquals("File not found", e.getMessage());
		}
	}

	public void testReadFromValidFile() {
		try {
			File file = new File("command-data.txt");
			ArrayList<String> result = reader.readFrom(file);
			assertEquals(3, result.size());

		} catch (Exception e) {
			//do nothing
		}
	}
}
