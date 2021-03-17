package models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionModelTest {

    private TransactionModel failedTransaction;
    private TransactionModel successTransaction;

    @BeforeEach
    public void setUpd() throws Exception{
        failedTransaction = new TransactionModel(10001, new Date(), 1026574281, "Repetidor dual", 100.00, 0.0010);
        successTransaction = new TransactionModel(10001, new Date(), 1026574281, "Repetidor dual", 100.00, 0.0010);
    }

    @Test
    @DisplayName("Validacion de 50 caracteres en el nombre")
    public void testProduct(){
        assertEquals(-1, failedTransaction.getId()); //Transacción con producto no valido regresara -1 como id.
    }

    @Test
    @DisplayName("Validacion de calculo de millas")
    public void testMiles(){
        assertEquals(100000, successTransaction.getMiles());
    }

    @Test
    @DisplayName("Validacion para formato de ToString")
    public void testToString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(successTransaction.getCreatedDate());
        assertEquals("10001|"+date+"|1026574281|Repetidor dual|$100.00|Millas 100,000.00|0.0010", successTransaction.toString());
    }

}