package com.na128.mmd;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.na128.mmd.data.BDEF1Vertex;
import com.na128.mmd.data.BDEF2Vertex;
import com.na128.mmd.data.BDEF4Vertex;
import com.na128.mmd.data.Face;
import com.na128.mmd.data.Header;
import com.na128.mmd.data.Material;
import com.na128.mmd.data.SDEFVertex;
import com.na128.mmd.data.Vertex;

public class PmxReader extends Reader {
	private Pmx data;

	private PmxReader(InputStream input) throws IOException {
		data = new Pmx();	
		
		ChunkReader[] crs = {
			new HeaderReader(),
			new VertexReader(),
			new FaceReader(),
			new TextureReader(),
			new MaterialReader()
		};
		
		for(ChunkReader cr : crs)
			cr.interpret(input);
		input.close();
	}

	static public Pmx read(InputStream input) throws IOException {
		return new PmxReader(input).getData();
	}
	
	public Pmx getData() {
		return data;
	}

	public static boolean isPmxSignature(byte[] sig) {
		return sig[0] == 'P' && sig[1] == 'M' && sig[2] == 'X' && sig[3] == ' '; 
	}
	
	public class HeaderReader implements ChunkReader {

		@Override
		public void interpret(InputStream input) throws IOException {
			Header h = new Header();
			byte[] tmp = new byte[4];
			readBytes(tmp, input);
			h.setVersion(readNativeFloat(input));
			if (h.getVersion() < 2f)
				throw new RuntimeException();

			h.setFrags(new byte[readByte(input)]);
			readBytes(h.getFlags(), input);

			Charset charset = h.getFlags()[0] == 0 ? Charset
					.forName("UTF-16LE") : Charset.forName("UTF-8");

			h.setName(readPString(input, charset));
			h.setNameEng(readPString(input, charset));
			h.setComment(readPString(input, charset));
			h.setCommenEng(readPString(input, charset));

			data.setHeader(h);
		}
	}

	public class VertexReader implements ChunkReader {

		@Override
		public void interpret(InputStream input) throws IOException {
			final byte[] flags = data.getHeader().getFlags();
			int length = readNativeInteger(input);
			Vertex[] vertices = new Vertex[length];

			int auvLength = flags[1];
			for (int i = 0; i < vertices.length; i++) {
				Vertex a = null;
				float x = readNativeFloat(input), //
				y = readNativeFloat(input), //
				z = readNativeFloat(input), //
				nx = readNativeFloat(input), //
				ny = readNativeFloat(input), //
				nz = readNativeFloat(input), //
				u = readNativeFloat(input), //
				v = readNativeFloat(input);

				float[] auv = null;
				if (auvLength > 0) {
					auv = new float[auvLength * 4];
					for (int j = 0; j < auvLength; j++) {
						auv[j * 4 + 0] = readNativeFloat(input);
						auv[j * 4 + 1] = readNativeFloat(input);
						auv[j * 4 + 2] = readNativeFloat(input);
						auv[j * 4 + 3] = readNativeFloat(input);
					}
				}
				int type = readByte(input);
				switch (type) {
				case (0):
					BDEF1Vertex b1 = new BDEF1Vertex();
					b1.setBone(readNativeInteger(input, flags[5]));
					a = b1;
					break;
				case (1):
					BDEF2Vertex b2 = new BDEF2Vertex();
					b2.setBone1(readNativeInteger(input, flags[5]));
					b2.setBone2(readNativeInteger(input, flags[5]));
					b2.setWeight(readNativeFloat(input));
					a = b2;
					break;
				case (2):
					BDEF4Vertex b4 = new BDEF4Vertex();
					b4.setBone1(readNativeInteger(input, flags[5]));
					b4.setBone2(readNativeInteger(input, flags[5]));
					b4.setBone3(readNativeInteger(input, flags[5]));
					b4.setBone4(readNativeInteger(input, flags[5]));
					b4.setWeight1(readNativeFloat(input));
					b4.setWeight2(readNativeFloat(input));
					b4.setWeight3(readNativeFloat(input));
					b4.setWeight4(readNativeFloat(input));
					a = b4;
					break;
				case (3):
					SDEFVertex bs = new SDEFVertex();
					bs.setBone1(readNativeInteger(input, flags[5]));
					bs.setBone2(readNativeInteger(input, flags[5]));
					bs.setWeight(readNativeFloat(input));
					float[] f = new float[9];
					for (int j = 0; j < 9; j++) {
						f[j] = readNativeFloat(input);
					}
					bs.setData(f);
					a = bs;
					break;
				default:
					throw new RuntimeException("Code: " + type);
				}
				float edgeRate = readNativeFloat(input);

				a.setX(x);
				a.setY(y);
				a.setZ(z);
				a.setNx(nx);
				a.setNy(ny);
				a.setNz(nz);
				a.setU(u);
				a.setV(v);
				a.setAuv(auv);
				a.setType((byte) type);
				a.setEdgeRate(edgeRate);

				vertices[i] = a;
			}
			data.setVertices(vertices);
		}
	}

	public class FaceReader implements ChunkReader {

		@Override
		public void interpret(InputStream input) throws IOException {
			final byte[] flags = data.getHeader().getFlags();
			int length = readNativeInteger(input);

			Face[] faces = new Face[length / 3];
			data.setFaces(faces);
			for (int i = 0; i < faces.length; i++) {
				Face face = new Face();
				face.setV1(readNativeUInt(input, flags[2]));
				face.setV2(readNativeUInt(input, flags[2]));
				face.setV3(readNativeUInt(input, flags[2]));
				faces[i] = face;
			}
		}
	}

	public class TextureReader implements ChunkReader {

		@Override
		public void interpret(InputStream input) throws IOException {
			int length = readNativeInteger(input);
			Charset charset = data.getHeader().getFlags()[0] == 0 ? Charset
					.forName("UTF-16LE") : Charset.forName("UTF-8");

			String[] textures = new String[length];
			data.setTextures(textures);
			for (int i = 0; i < length; i++) {
				textures[i] = readPString(input, charset);
			}
		}
	}

	public class MaterialReader implements ChunkReader {
		@Override
		public void interpret(InputStream input) throws IOException {
			final byte[] flags = data.getHeader().getFlags();
			int length = readNativeInteger(input);
			Material[] materials = new Material[length];
			Charset charset = flags[0] == 0 ? Charset
					.forName("UTF-16LE") : Charset.forName("UTF-8");

			for (int i = 0; i < materials.length; i++) {
				Material m = new Material();
				m.setName(readPString(input, charset));
				m.setNameEng(readPString(input, charset));
				m.setDiffuseR(readNativeFloat(input));
				m.setDiffuseG(readNativeFloat(input));
				m.setDiffuseB(readNativeFloat(input));
				m.setDiffuseA(readNativeFloat(input));
				m.setSpecularR(readNativeFloat(input));
				m.setSpecularG(readNativeFloat(input));
				m.setSpecularB(readNativeFloat(input));
				m.setSpecularFactor(readNativeFloat(input));
				m.setAmbientR(readNativeFloat(input));
				m.setAmbientG(readNativeFloat(input));
				m.setAmbientB(readNativeFloat(input));
				m.setFlags(readByte(input));
				m.setEdgeR(readNativeFloat(input));
				m.setEdgeG(readNativeFloat(input));
				m.setEdgeB(readNativeFloat(input));
				m.setEdgeA(readNativeFloat(input));
				m.setEdgeSize(readNativeFloat(input));
				m.setTexture(readNativeInteger(input, flags[3]));
				m.setSphereTexture(readNativeInteger(input, flags[3]));
				m.setSphereMode(readByte(input));
				m.setToonShared(readByte(input));
				if(m.getToonShared() == 1)
					m.setToonTexture(readNativeInteger(input, flags[3]));
				else
					m.setToonTexture(readByte(input));
				m.setMemo(readPString(input, charset));
				m.setBoundFace(readNativeInteger(input));
				materials[i] = m;
			}
			data.setMaterials(materials);
		}
	}

}
