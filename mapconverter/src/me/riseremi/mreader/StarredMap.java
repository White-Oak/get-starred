package me.riseremi.mreader;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author riseremi <riseremi at icloud.com>
 */
public final class StarredMap {

    private String author;
    private int width, height;
    private int[][] obstaclesLayer, backgroundLayer, decorationsLayer;
    private String fileName;
    private boolean debug;

    private StarredMap(String fileName, boolean debug) {
	this.fileName = fileName;
	this.debug = debug;
    }

    public StarredMap(Reader reader) throws IOException {
	this("not specified", false);
	BufferedReader br = new BufferedReader(reader);

	StringBuilder sb = new StringBuilder();
	String line;
	while ((line = br.readLine()) != null) {
	    sb.append(line);
	}

	String fileContent = sb.toString();
	parse(fileContent);
    }

    public StarredMap(String fileName) throws IOException {
	this(fileName, false);
	parse();
    }

    private void parse(String fileContent) {
	author = XMLUtils.getProperty("author", fileContent);
	width = Integer.parseInt(XMLUtils.getProperty("width", fileContent));
	height = Integer.parseInt(XMLUtils.getProperty("height", fileContent));

	ArrayList<int[][]> layers;
	layers = XMLUtils.parse(fileContent, width, height, debug);

	obstaclesLayer = layers.get(0);
	backgroundLayer = layers.get(1);
	decorationsLayer = layers.get(2);
    }

    private void parse() throws IOException {
	String fileContent = FileUtils.getFileContent(fileName, debug);
	parse(fileContent);
    }

    public String getAuthor() {
	return author;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public int[][] getObstaclesLayer() {
	return obstaclesLayer;
    }

    public int[][] getBackgroundLayer() {
	return backgroundLayer;
    }

    public int[][] getDecorationsLayer() {
	return decorationsLayer;
    }

}
