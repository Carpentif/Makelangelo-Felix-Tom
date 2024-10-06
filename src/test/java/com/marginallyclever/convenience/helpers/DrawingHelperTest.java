package com.marginallyclever.convenience.helpers;

import com.jogamp.opengl.util.texture.Texture;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DrawingHelperTest {

    /**
     * This is used to make sure an exception is thrown with different invalid files
     */
    @ParameterizedTest
    // Arrange
    @ValueSource(strings = { "Non-existing file", "TextFile.txt", "AnotherFailure.png"})
    public void testLoadTexture(String file) {
        // Act
        Texture tex = DrawingHelper.loadTexture(file);

        // Assert
        assertNull(tex);
    }
}
