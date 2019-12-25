package ru.unn.agile.bitarray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    // Constructor tests
    @Test
    public void constructorDefaultSetAllStringsEmpty() {
        assertEquals("", viewModel.inputBitArrayProperty().get());
        assertEquals("", viewModel.inputBitProperty().get());
        assertEquals("", viewModel.fieldBitArrayProperty().get());
    }

    @Test
    public void constructorDefaultSetStatusWaiting() {
        assertEquals(Status.WAITING.toString(), viewModel.fieldInputArrayStatusProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    // Array status field tests - fieldInputArrayStatusProperty
    @Test
    public void statusArrayFieldOnInputFieldIsEmptyIsWaiting() {
        viewModel.inputBitArrayProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.fieldInputArrayStatusProperty().get());
    }

    @Test
    public void statusArrayFieldOnIncorrectBitArrayInputIsBadArrayFormat() {
        viewModel.inputBitArrayProperty().set("0142");

        assertEquals(Status.BAD_FORMAT_ARRAY.toString(),
                viewModel.fieldInputArrayStatusProperty().get());
    }

    @Test
    public void statusArrayFieldOnCorrectInputIsReady() {
        viewModel.inputBitArrayProperty().set("01");

        assertEquals(Status.READY.toString(), viewModel.fieldInputArrayStatusProperty().get());
    }

    @Test
    public void statusArrayFieldAfterCreatingIsSUCCESS() {
        viewModel.inputBitArrayProperty().set("01");
        viewModel.create();

        assertEquals(Status.SUCCESS.toString(), viewModel.fieldInputArrayStatusProperty().get());
    }

    // Bit status field tests - fieldInputBitStatusProperty
    @Test
    public void statusBitFieldOnInputFieldIsEmptyIsWaiting() {
        viewModel.inputBitProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    @Test
    public void statusBitFieldOnIncorrectBitInputIsBadBitFormat() {
        viewModel.inputBitProperty().set("a");

        assertEquals(Status.BAD_FORMAT_BIT.toString(),
                viewModel.fieldInputBitStatusProperty().get());
    }

    @Test
    public void statusBitFieldOnCorrectInputBitArrayCreatedIsReady() {
        viewModel.inputBitArrayProperty().set("0101");
        viewModel.create();

        viewModel.inputBitProperty().set("3");

        assertEquals(Status.READY.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    @Test
    public void statusBitFieldOnNotCreatedBitArrayUnsetCallIsNOTCREATED() {
        viewModel.inputBitProperty().set("1");
        viewModel.unsetBit();

        assertEquals(Status.NOT_CREATED.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    @Test
    public void statusBitFieldOnNotCreatedBitArraySetCallIsNOTCREATED() {
        viewModel.inputBitProperty().set("1");
        viewModel.setBit();

        assertEquals(Status.NOT_CREATED.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    @Test
    public void statusBitFieldOnCorrectInputUnsetBitIsSUCCESS() {
        viewModel.inputBitArrayProperty().set("0101");
        viewModel.create();

        viewModel.inputBitProperty().set("1");
        viewModel.unsetBit();

        assertEquals(Status.SUCCESS.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    @Test
    public void statusBitFieldOnCorrectInputSetBitIsSUCCESS() {
        viewModel.inputBitArrayProperty().set("0101");
        viewModel.create();

        viewModel.inputBitProperty().set("1");
        viewModel.setBit();

        assertEquals(Status.SUCCESS.toString(), viewModel.fieldInputBitStatusProperty().get());
    }

    // BitArray field tests
    @Test
    public void bitArrayFieldAfterCreatingSameAsInput() {
        String input = "0101";

        viewModel.inputBitArrayProperty().set(input);
        viewModel.create();

        assertEquals(input, viewModel.fieldBitArrayProperty().get().toString());
    }

    @Test
    public void bitArrayFieldAfterCreateOnIncorrectInputIsEmpty() {
        final String incorrectInput = "42";
        viewModel.inputBitArrayProperty().set(incorrectInput);
        viewModel.create();

        assertEquals("", viewModel.fieldBitArrayProperty().get().toString());
    }

    @Test
    public void bitArrayFieldOnCreatedBitArrayAfterSetBitIsChanged() {
        final String input = "0101";
        final String inputIndex = "0";
        viewModel.inputBitArrayProperty().set(input);
        viewModel.create();

        viewModel.inputBitProperty().set(inputIndex);
        viewModel.setBit();

        assertNotEquals(input, viewModel.fieldBitArrayProperty().get().toString());
    }

    @Test
    public void bitArrayFieldOnCreatedBitArrayAfterUnsetBitIsChanged() {
        final String input = "0101";
        final String inputIndex = "1";
        viewModel.inputBitArrayProperty().set(input);
        viewModel.create();

        viewModel.inputBitProperty().set(inputIndex);
        viewModel.unsetBit();

        assertNotEquals(input, viewModel.fieldBitArrayProperty().get().toString());
    }
}
