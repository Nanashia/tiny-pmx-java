package com.na128.mmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public abstract class Reader {

	public static Pmx read(File inputFile) throws IOException {
		String path = inputFile.getAbsolutePath();
		FileInputStream fis = new FileInputStream(inputFile);
		if(path.endsWith(".pmx")) {
			return PmxReader.read(fis);
		} else if(path.endsWith(".pmd")) {
			return PmdReader.read(fis);
		}
		fis.close();
		throw new RuntimeException("Unsupported fileformat");
	}

	protected static byte[] buffer4 = new byte[4];

	protected static void readBytes(byte[] out, InputStream input)
			throws IOException {
		if (input.read(out) != out.length)
			throw new IOException("Unexpected end of stream");
	}

	protected static int readNativeInteger(InputStream input, int length)
			throws IOException {
		input.read(buffer4, 0, length);
		ByteBuffer bb = ByteBuffer.wrap(buffer4);
		switch (length) {
		case (1):
			return bb.order(ByteOrder.LITTLE_ENDIAN).get(0);
		case (2):
			return bb.order(ByteOrder.LITTLE_ENDIAN).getShort();
		case (4):
			return bb.order(ByteOrder.LITTLE_ENDIAN).getInt();
		}
		throw new IllegalArgumentException();
	}

	protected static String readPString(InputStream input, Charset charset)
			throws IOException {
		readBytes(buffer4, input);
		int length = readNativeInteger(buffer4);
		byte[] buffer = new byte[length];
		readBytes(buffer, input);
		return readNativeString(buffer, charset);
	}

	protected static String readPString(InputStream input) throws IOException {
		readBytes(buffer4, input);
		int length = readNativeInteger(buffer4);
		byte[] buffer = new byte[length];
		readBytes(buffer, input);
		return new String(buffer);
	}

	protected static String readNativeString(byte[] bytes, Charset charset) {
		return new String(bytes, charset);
	}

	protected static byte readByte(InputStream input) throws IOException {
		int v = input.read();
		if (v == -1)
			throw new IOException("Unexpected end of stream");
		return (byte) v;
	}

	protected static int readNativeInteger(InputStream input)
			throws IOException {
		readBytes(buffer4, input);
		return readNativeInteger(buffer4);
	}

	protected static int readNativeInteger(byte[] input) {
		return ByteBuffer.wrap(input).order(ByteOrder.LITTLE_ENDIAN).getInt();
	}

	protected static long readNativeUInt(InputStream input) throws IOException {
		readBytes(buffer4, input);
		return readNativeUInt(buffer4);
	}

	protected static long readNativeUInt(byte[] input) {
		long i = ByteBuffer.wrap(input).order(ByteOrder.LITTLE_ENDIAN).getInt();
		if (i < 0)
			return (Integer.MAX_VALUE + 1) * 2 - i;
		return i;
	}

	protected static float readNativeFloat(InputStream input)
			throws IOException {
		readBytes(buffer4, input);
		return readNativeFloat(buffer4);
	}

	protected static float readNativeFloat(byte[] input) {
		return ByteBuffer.wrap(input).order(ByteOrder.LITTLE_ENDIAN).getFloat();
	}
}
