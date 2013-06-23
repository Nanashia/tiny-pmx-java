package com.na128.mmd.data;

public class Material {
	private String name, nameEng, memo;
	private float diffuseR, diffuseG, diffuseB, diffuseA, //
			specularR, specularG, specularB, specularFactor, //
			ambientR, ambientG, ambientB, //
			edgeR, edgeG, edgeB, edgeA, edgeSize;

	private byte flags, sphereMode, toonShared;
	private int texture, sphereTexture, toonTexture, boundFace;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public float getDiffuseR() {
		return diffuseR;
	}

	public void setDiffuseR(float diffuseR) {
		this.diffuseR = diffuseR;
	}

	public float getDiffuseG() {
		return diffuseG;
	}

	public void setDiffuseG(float diffuseG) {
		this.diffuseG = diffuseG;
	}

	public float getDiffuseB() {
		return diffuseB;
	}

	public void setDiffuseB(float diffuseB) {
		this.diffuseB = diffuseB;
	}

	public float getDiffuseA() {
		return diffuseA;
	}

	public void setDiffuseA(float diffuseA) {
		this.diffuseA = diffuseA;
	}

	public float getSpecularR() {
		return specularR;
	}

	public void setSpecularR(float specularR) {
		this.specularR = specularR;
	}

	public float getSpecularG() {
		return specularG;
	}

	public void setSpecularG(float specularG) {
		this.specularG = specularG;
	}

	public float getSpecularB() {
		return specularB;
	}

	public void setSpecularB(float specularB) {
		this.specularB = specularB;
	}

	public float getSpecularFactor() {
		return specularFactor;
	}

	public void setSpecularFactor(float specularFactor) {
		this.specularFactor = specularFactor;
	}

	public float getAmbientR() {
		return ambientR;
	}

	public void setAmbientR(float ambientR) {
		this.ambientR = ambientR;
	}

	public float getAmbientG() {
		return ambientG;
	}

	public void setAmbientG(float ambientG) {
		this.ambientG = ambientG;
	}

	public float getAmbientB() {
		return ambientB;
	}

	public void setAmbientB(float ambientB) {
		this.ambientB = ambientB;
	}

	public float getEdgeR() {
		return edgeR;
	}

	public void setEdgeR(float edgeR) {
		this.edgeR = edgeR;
	}

	public float getEdgeG() {
		return edgeG;
	}

	public void setEdgeG(float edgeG) {
		this.edgeG = edgeG;
	}

	public float getEdgeB() {
		return edgeB;
	}

	public void setEdgeB(float edgeB) {
		this.edgeB = edgeB;
	}
	
	public float getEdgeA() {
		return edgeA;
	}

	public void setEdgeA(float edgeA) {
		this.edgeA = edgeA;
	}

	public float getEdgeSize() {
		return edgeSize;
	}

	public void setEdgeSize(float edgeSize) {
		this.edgeSize = edgeSize;
	}

	public byte getFlags() {
		return flags;
	}

	public void setFlags(byte flags) {
		this.flags = flags;
	}

	public byte getSphereMode() {
		return sphereMode;
	}

	public void setSphereMode(byte sphereMode) {
		this.sphereMode = sphereMode;
	}

	public byte getToonShared() {
		return toonShared;
	}

	public void setToonShared(byte toonShared) {
		this.toonShared = toonShared;
	}

	public int getSphereTexture() {
		return sphereTexture;
	}

	public void setSphereTexture(int sphereTexture) {
		this.sphereTexture = sphereTexture;
	}

	public int getToonTexture() {
		return toonTexture;
	}

	public void setToonTexture(int toonTexture) {
		this.toonTexture = toonTexture;
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public int getBoundFace() {
		return boundFace;
	}

	public void setBoundFace(int boundFace) {
		this.boundFace = boundFace;
	}

}
