package com.na128.mmd;

import java.io.IOException;
import java.io.InputStream;

public interface ChunkReader {
	void interpret(InputStream input) throws IOException;
}
