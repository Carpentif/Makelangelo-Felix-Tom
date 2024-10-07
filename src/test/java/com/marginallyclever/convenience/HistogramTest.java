package com.marginallyclever.convenience;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;

public class HistogramTest {

    /**
     * test the 'getGreyHistogramOf' method of Histogram
     * Create a black & white test image and verify correct grayscale calculation
     */
    @Test
    public void testGetGreyHistogramOfBlackAndWhiteImage() {
        BufferedImage image = new BufferedImage(2, 1, BufferedImage.TYPE_BYTE_GRAY);
        image.setRGB(0, 0, 0x000000);
        image.setRGB(1, 0, 0xFFFFFF);

        Histogram histogram = new Histogram();
        histogram.getGreyHistogramOf(image);

        assertEquals(1, histogram.red[0], "should have one black pixel");
        assertEquals(1, histogram.red[255], "should have one white pixel");
        for (int i = 1; i < 255; i++) {
            assertEquals(0, histogram.red[i], "Other should be empty");
        }
    }


    /**
     * test the 'getLevelsMapped' method of Histogram.
     * create a uniform distribution histogram and verify correct level mapping for different percentages
     */
    @Test
    public void testGetLevelsMapped() {
        Histogram histogram = new Histogram();
        for (int i = 0; i < 256; i++) {
            histogram.red[i] = 1; 
        }

        double[] input = {0.25, 0.5, 0.75};
        double[] levels = histogram.getLevelsMapped(input);

        assertEquals(64, levels[0], 1);
        assertEquals(128, levels[1], 1);
        assertEquals(192, levels[2], 1);
    }

}
