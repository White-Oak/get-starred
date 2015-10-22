package me.oak.getstarred.map;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import java.io.*;
import me.riseremi.mreader.StarredMap;

/**
 *
 * @author White Oak
 */
public class Converter {

    public static void convert() throws FileNotFoundException, IOException {
	File file = new File("map.m");
	FileReader fileReader = new FileReader(file);
	int[][] map = new StarredMap(fileReader).getBackgroundLayer();
	Kryo kryo = new Kryo();
	try (Output output = new Output(new FileOutputStream("../android/assets/map.bin"))) {
	    kryo.writeObject(output, map);
	}
    }

    public static void main(String args[]) throws FileNotFoundException, IOException {
	convert();
    }
}
