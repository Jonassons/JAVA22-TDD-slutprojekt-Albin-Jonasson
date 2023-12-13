package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Consumer;
import main.Producer;

import static org.junit.jupiter.api.Assertions.*;

public class BufferTest {
	private MockBuffer buffer;
	private MockProducer producer;
	private MockConsumer consumer;

	@BeforeEach
	public void setUp() {
		buffer = new MockBuffer();
		producer = new MockProducer(buffer);
		consumer = new MockConsumer(buffer);
	}

	@Test
	@DisplayName("Test that addItem method in producer works for correct item")
	public void testAddItemProducer() {
		MockItem mockItem = new MockItem("test");
		assertEquals(true, producer.addItem(mockItem));
	}

	@Test
	@DisplayName("Test that item is added correctly to Buffer list")
	public void testAddItemBufferList() {
		MockItem mockItem = new MockItem("test");
		producer.addItem(mockItem);
		assertFalse(buffer.getBufferQueue().isEmpty());
	}

	@Test
	@DisplayName("Test adding multiple items to Buffer list")
	public void testAddMultipleItems() {
		MockItem mockItem1 = new MockItem("item1");
		MockItem mockItem2 = new MockItem("item2");
		producer.addItem(mockItem1);
		producer.addItem(mockItem2);
		assertEquals(2, buffer.getBufferQueue().size());
	}

	@Test
	@DisplayName("Test specific items are added to Buffer list")
	public void testSpecificItemsAdded() {
		MockItem item1 = new MockItem("item1");
		MockItem item2 = new MockItem("item2");
		producer.addItem(item1);
		producer.addItem(item2);
		assertEquals(2, buffer.getBufferQueue().size());
		assertTrue(buffer.getBufferQueue().contains(item1));
		assertTrue(buffer.getBufferQueue().contains(item2));
	}

	@Test
	@DisplayName("Test removing an item from Buffer list")
	public void testRemoveItemFromBuffer() {
		MockItem mockItem = new MockItem("test");
		producer.addItem(mockItem);
		assertEquals(mockItem, consumer.removeItem());
		assertTrue(buffer.getBufferQueue().isEmpty());
	}

	@Test
	@DisplayName("Test interrupted exception during wait in remove method")
	public void testInterruptedExceptionDuringWait() {
		Thread thread = new Thread(() -> {
			try {
				consumer.removeItem();
			} catch (Exception ignored) {

			}
		});

		thread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();

	}

	@Test
	@DisplayName("Test buffer with different data types")
	public void testBufferWithVariousDataTypes() {
		producer.addItem(new MockItem("stringItem"));
		producer.addItem(new MockItem("123"));
		producer.addItem(new MockItem("!@#"));
		assertEquals(3, buffer.getBufferQueue().size());
	}

	@Test
	@DisplayName("Test MockConsumer's run() and stopRunning()")
	public void testMockConsumerRunAndStopRunning() {
		MockBuffer buffer = new MockBuffer();
		Consumer consumer = new MockConsumer(buffer);

		consumer.run();

		consumer.stopRunning();

	}

	@Test
	@DisplayName("Test MockProducer's run() and stopRunning()")
	public void testMockProducerRunAndStopRunning() {
		MockBuffer buffer = new MockBuffer();
		Producer producer = new MockProducer(buffer);

		producer.run();

		producer.stopRunning();

	}

}
