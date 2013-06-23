package com.na128.mmd.data;

public class Bone {
	public static final short childType = 0x0001;
	public static final short rotatable = 0x0002;
	public static final short movable = 0x0004;
	public static final short show = 0x0008;
	public static final short controlable = 0x0010;
	public static final short ik = 0x0020;
	public static final short givenLocal = 0x0080;
	public static final short givenRotation = 0x0100;
	public static final short givenMovement = 0x0100;
	public static final short fixedAxis = 0x0400;
	public static final short localAxis = 0x0800;
	public static final short postDeform = 0x1000;
	public static final short externalDeform = 0x2000;

	public class IKLink {
		private int bone;
		private byte limited;

		public int getBone() {
			return bone;
		}

		public void setBone(int bone) {
			this.bone = bone;
		}

		public byte getLimited() {
			return limited;
		}

		public void setLimited(byte limited) {
			this.limited = limited;
		}

	}

	public class LimitedIKLink extends IKLink {
		private float upX, upY, upZ, downX, downY, downZ;

		public float getUpX() {
			return upX;
		}

		public void setUpX(float upX) {
			this.upX = upX;
		}

		public float getUpY() {
			return upY;
		}

		public void setUpY(float upY) {
			this.upY = upY;
		}

		public float getUpZ() {
			return upZ;
		}

		public void setUpZ(float upZ) {
			this.upZ = upZ;
		}

		public float getDownX() {
			return downX;
		}

		public void setDownX(float downX) {
			this.downX = downX;
		}

		public float getDownY() {
			return downY;
		}

		public void setDownY(float downY) {
			this.downY = downY;
		}

		public float getDownZ() {
			return downZ;
		}

		public void setDownZ(float downZ) {
			this.downZ = downZ;
		}

	}

	private String name, nameEng;
	private float position, boneOffsetX, boneOffsetY, boneOffsetZ, givenRate,
			fixedAxisX, fixedAxisY, fixedAxisZ, localAxisXX, localAxisXY,
			localAxisXZ, localAxisZX, localAxisZY, localAxisZZ, ikMaxAngle;
	private int parentBone, layer, flags, childBone, givenParentBone, key,
			ikBoneIndex, ikLoopCount, ikLinkCount;

	private IKLink links;

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

	public float getPosition() {
		return position;
	}

	public void setPosition(float position) {
		this.position = position;
	}

	public float getBoneOffsetX() {
		return boneOffsetX;
	}

	public void setBoneOffsetX(float boneOffsetX) {
		this.boneOffsetX = boneOffsetX;
	}

	public float getBoneOffsetY() {
		return boneOffsetY;
	}

	public void setBoneOffsetY(float boneOffsetY) {
		this.boneOffsetY = boneOffsetY;
	}

	public float getBoneOffsetZ() {
		return boneOffsetZ;
	}

	public void setBoneOffsetZ(float boneOffsetZ) {
		this.boneOffsetZ = boneOffsetZ;
	}

	public float getGivenRate() {
		return givenRate;
	}

	public void setGivenRate(float givenRate) {
		this.givenRate = givenRate;
	}

	public float getFixedAxisX() {
		return fixedAxisX;
	}

	public void setFixedAxisX(float fixedAxisX) {
		this.fixedAxisX = fixedAxisX;
	}

	public float getFixedAxisY() {
		return fixedAxisY;
	}

	public void setFixedAxisY(float fixedAxisY) {
		this.fixedAxisY = fixedAxisY;
	}

	public float getFixedAxisZ() {
		return fixedAxisZ;
	}

	public void setFixedAxisZ(float fixedAxisZ) {
		this.fixedAxisZ = fixedAxisZ;
	}

	public float getLocalAxisXX() {
		return localAxisXX;
	}

	public void setLocalAxisXX(float localAxisXX) {
		this.localAxisXX = localAxisXX;
	}

	public float getLocalAxisXY() {
		return localAxisXY;
	}

	public void setLocalAxisXY(float localAxisXY) {
		this.localAxisXY = localAxisXY;
	}

	public float getLocalAxisXZ() {
		return localAxisXZ;
	}

	public void setLocalAxisXZ(float localAxisXZ) {
		this.localAxisXZ = localAxisXZ;
	}

	public float getLocalAxisZX() {
		return localAxisZX;
	}

	public void setLocalAxisZX(float localAxisZX) {
		this.localAxisZX = localAxisZX;
	}

	public float getLocalAxisZY() {
		return localAxisZY;
	}

	public void setLocalAxisZY(float localAxisZY) {
		this.localAxisZY = localAxisZY;
	}

	public float getLocalAxisZZ() {
		return localAxisZZ;
	}

	public void setLocalAxisZZ(float localAxisZZ) {
		this.localAxisZZ = localAxisZZ;
	}

	public float getIkMaxAngle() {
		return ikMaxAngle;
	}

	public void setIkMaxAngle(float ikMaxAngle) {
		this.ikMaxAngle = ikMaxAngle;
	}

	public int getParentBone() {
		return parentBone;
	}

	public void setParentBone(int parentBone) {
		this.parentBone = parentBone;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public int getChildBone() {
		return childBone;
	}

	public void setChildBone(int childBone) {
		this.childBone = childBone;
	}

	public int getGivenParentBone() {
		return givenParentBone;
	}

	public void setGivenParentBone(int givenParentBone) {
		this.givenParentBone = givenParentBone;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getIkBoneIndex() {
		return ikBoneIndex;
	}

	public void setIkBoneIndex(int ikBoneIndex) {
		this.ikBoneIndex = ikBoneIndex;
	}

	public int getIkLoopCount() {
		return ikLoopCount;
	}

	public void setIkLoopCount(int ikLoopCount) {
		this.ikLoopCount = ikLoopCount;
	}

	public int getIkLinkCount() {
		return ikLinkCount;
	}

	public void setIkLinkCount(int ikLinkCount) {
		this.ikLinkCount = ikLinkCount;
	}

	public IKLink getLinks() {
		return links;
	}

	public void setLinks(IKLink links) {
		this.links = links;
	}

	public static short getChildtype() {
		return childType;
	}

	public static short getRotatable() {
		return rotatable;
	}

	public static short getMovable() {
		return movable;
	}

	public static short getShow() {
		return show;
	}

	public static short getControlable() {
		return controlable;
	}

	public static short getIk() {
		return ik;
	}

	public static short getGivenlocal() {
		return givenLocal;
	}

	public static short getGivenrotation() {
		return givenRotation;
	}

	public static short getGivenmovement() {
		return givenMovement;
	}

	public static short getFixedaxis() {
		return fixedAxis;
	}

	public static short getLocalaxis() {
		return localAxis;
	}

	public static short getPostdeform() {
		return postDeform;
	}

	public static short getExternaldeform() {
		return externalDeform;
	}

}
