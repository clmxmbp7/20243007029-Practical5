package com.example.practicalfive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class TextProcessorTest {

    private TextProcessor textProcessor;

    @Before
    public void setUp() {
        textProcessor = new TextProcessor();
    }

    @Test
    public void processMunicipality_acceptsAllSupportedMunicipalities() {
        assertEquals("Beijing", textProcessor.processMunicipality("Beijing"));
        assertEquals("Shanghai", textProcessor.processMunicipality("Shanghai"));
        assertEquals("Chongqing", textProcessor.processMunicipality("Chongqing"));
        assertEquals("Tianjin", textProcessor.processMunicipality("Tianjin"));
    }

    @Test
    public void processMunicipality_isCaseInsensitive() {
        assertEquals("Beijing", textProcessor.processMunicipality("BEIJING"));
        assertEquals("Shanghai", textProcessor.processMunicipality("shanghai"));
        assertEquals("Chongqing", textProcessor.processMunicipality("ChOnGqInG"));
        assertEquals("Tianjin", textProcessor.processMunicipality("tIaNjIn"));
    }

    @Test
    public void processMunicipality_trimsSurroundingWhitespace() {
        assertEquals("Shanghai", textProcessor.processMunicipality("  Shanghai  "));
    }

    @Test
    public void processMunicipality_rejectsNullEmptyWhitespaceAndUnsupportedInput() {
        assertNull(textProcessor.processMunicipality(null));
        assertNull(textProcessor.processMunicipality(""));
        assertNull(textProcessor.processMunicipality("   "));
        assertNull(textProcessor.processMunicipality("Guangzhou"));
    }
}

