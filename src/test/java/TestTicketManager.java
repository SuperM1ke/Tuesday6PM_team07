package fit5171.monash.edu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
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
    public void testChooseAndBuyTicketWithEmptyCity() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        inputStr = "";
        ticketManager.chooseAndBuyTicket(inputStr, inputStr);
        String expectedOutput = "No direct flights found. Searching for connecting flights...\nNo flights to  found.";
        assertEquals(expectedOutput, outContent.toString().trim());
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    @DisplayName("")
    public void testChooseAndBuyTicket(){

    }

}
