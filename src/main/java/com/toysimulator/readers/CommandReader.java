package com.toysimulator.readers;


import com.toysimulator.Game;
import com.toysimulator.exception.BlockExplorerException;
import com.toysimulator.exception.ToyExplorerException;
import com.toysimulator.simulator.BlockExplorer;
import com.toysimulator.simulator.SquareBoard;
import com.toysimulator.simulator.ToyExplorer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CommandReader {


	public ArrayList<String> readFrom(File file)
	throws IOException, ToyExplorerException {
		if (file == null) {
			throw new ToyExplorerException("File not found");
		}
		if (!file.exists()) {
			throw new ToyExplorerException("File not exist");
		}
		return read(file);
	}

	private ArrayList<String> read(File file)
	throws IOException {
		ArrayList<String> result = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			SquareBoard squareBoard = new SquareBoard(4, 4);
			ToyExplorer toyExplorer = new ToyExplorer();
			BlockExplorer blockExplorer = new BlockExplorer();

			Game game = new Game(squareBoard, toyExplorer,blockExplorer);

			String currentLine;
			String output=null;
			while ((currentLine = br.readLine()) != null) {
				try {
					output = game.eval(currentLine);
				} catch (ToyExplorerException e ) {
					e.printStackTrace();
				} catch (BlockExplorerException e){
					e.printStackTrace();
				}
				if (currentLine.equals("REPORT") || currentLine.contains("EXPLORER")) {
					result.add(output);
				}
			}
		}
		return result;
	}
}
