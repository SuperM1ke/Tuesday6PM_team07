package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTicketManager {
    private TicketManager ticketManager;
    private String inputStr;

    @BeforeEach
    public void setup(){
        ticketManager = new TicketManager();
    }

    @Test
    @DisplayName("validate if input city is empty")
    public void testChooseAndBuyTicketWithEmptyCity(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            ticketManager.chooseAndBuyTicket(inputStr, inputStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        inputStr = "";
        assertEquals("No flights to  found.", outContent.toString());
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
