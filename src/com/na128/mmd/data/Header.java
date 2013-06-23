package com.na128.mmd.data;

public class Header {
	public static final byte encode = 0;
	public static final byte extraUV = 1;
	public static final byte vertexIndexSize = 2;
	public static final byte textureIndexSize = 3;
	public static final byte materialIndexSize = 4;
	public static final byte boneIndexSize = 5;
	public static final byte morphIndexSize = 6;
	public static final byte dynamicsIndexSize = 7;
	
	private float version;
	private byte[] flags;
	private String name, nameEng, comment, commenEng;

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public byte[] getFlags() {
		return flags;
	}

	public void setFrags(byte[] flags) {
		this.flags = flags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommenEng() {
		return commenEng;
	}

	public void setCommenEng(String commenEng) {
		this.commenEng = commenEng;
	}
}