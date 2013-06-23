package com.na128.mmd.data;

public class Vertex {
	private float x, y, z, nx, ny, nz, u, v;
	private float[] auv;
	private byte type;
	private float edgeRate;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getNx() {
		return nx;
	}

	public void setNx(float nx) {
		this.nx = nx;
	}

	public float getNy() {
		return ny;
	}

	public void setNy(float ny) {
		this.ny = ny;
	}

	public float getNz() {
		return nz;
	}

	public void setNz(float nz) {
		this.nz = nz;
	}

	public float getU() {
		return u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public float[] getAuv() {
		return auv;
	}

	public void setAuv(float[] auv) {
		this.auv = auv;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public float getEdgeRate() {
		return edgeRate;
	}

	public void setEdgeRate(float edgeRate) {
		this.edgeRate = edgeRate;
	}
}