package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderProcessorTest {

    @Test
    void testPrintOrderSummary_forMemberCustomer() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Set up test data
        Customer customer = new Customer("Alice", true);
        Item item1 = new Item("Book", 10.0, 2);
        Item item2 = new Item("Pen", 2.0, 5);
        Order order = new Order(customer, Arrays.asList(item1, item2));

        // Call method under test
        OrderProcessor processor = new OrderProcessor();
        processor.printOrderSummary(order);

        // Convert captured output to String
        String output = outputStream.toString();

        // Simple assertions
        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("Book: 2 x $10.0 = $20.0"));
        assertTrue(output.contains("Pen: 5 x $2.0 = $10.0"));
        assertTrue(output.contains("Total Price: $27.00")); // (20+10)*0.9 = 27.00
    }
}
