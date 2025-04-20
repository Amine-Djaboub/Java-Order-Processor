package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderProcessorTest {

    @Test
    void testPrintOrderSummary_forMemberCustomer() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Set up test data
        Item book = new Item("Book", 10.0, 2); // $20.00
        Item pen = new Item("Pen", 2.5, 4);    // $10.00
        List<Item> items = List.of(book, pen);
        Customer customer = new Customer("Alice", false);

        // Call method under test
        Order order = new Order(customer, items);

        // capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Process order
        OrderProcessor processor = new OrderProcessor();
        processor.printOrderSummary(order);

        // Get the printed string
        String output = outContent.toString();

        // Simple assertions
        assertTrue(output.contains("Customer: Alice"));
        assertTrue(output.contains("Book: 2 x $10.00 = $20.00"));
        assertTrue(output.contains("Pen: 4 x $2.50 = $10.00"));
        assertTrue(output.contains("Total Price: $30.00"));
    }
}
