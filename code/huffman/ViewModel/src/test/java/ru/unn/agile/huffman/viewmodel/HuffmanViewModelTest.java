package ru.unn.agile.huffman.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HuffmanViewModelTest {
    private HuffmanViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new HuffmanViewModel(new LoggerStub());
    }

    public void setViewModel(final HuffmanViewModel vM) {
        this.viewModel = vM;
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void checkRefLabelTextByDefaultText() {
        assertEquals("Reference string", viewModel.getInput().get());
    }

    @Test
    public void checkEncodeLabelTextByDefaultText() {
        assertEquals("Encode", viewModel.getOutputEncode().get());
    }

    @Test
    public void checkDecodeLabelTextByDefaultText() {
        assertEquals("Decode", viewModel.getOutputDecode().get());
    }

    @Test
    public void checkEncodeLabelAfterPushStartWithZeroString() {
        viewModel.setInput("");
        viewModel.startEncodeAndDecode();
        assertEquals("", viewModel.getOutputEncode().get());
    }

    @Test
    public void checkEncodeLabelAfterPushStartWithString() {
        viewModel.setInput("ab");
        viewModel.startEncodeAndDecode();
        assertEquals("01", viewModel.getOutputEncode().get());
    }

    @Test
    public void checkDecodeLabelAfterPushStartWithZeroString() {
        viewModel.setInput("");
        viewModel.startEncodeAndDecode();
        assertEquals("", viewModel.getOutputDecode().get());
    }
    @Test
    public void logIsEmptyAtTheBeginningOfWork() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }
}
