package test;

import java.util.Queue;

import main.Buffer;
import main.Item;

public class MockBuffer extends Buffer {

	public Queue<Item> getBufferQueue() {
		return super.buffer;
	}

}