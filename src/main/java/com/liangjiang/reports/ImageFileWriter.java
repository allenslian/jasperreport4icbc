package com.liangjiang.reports;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageFileWriter {
    void WriteTo(int index, BufferedImage image) throws IOException;
}
