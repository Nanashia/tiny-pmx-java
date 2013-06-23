package com.na128.mmd;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.na128.mmd.data.BDEF2Vertex;
import com.na128.mmd.data.Face;
import com.na128.mmd.data.Header;
import com.na128.mmd.data.Material;
import com.na128.mmd.data.Vertex;

public class PmdReader extends Reader {
	private Pmx data;

	private PmdReader(InputStream input) throws IOException {
		data = new Pmx();

		ChunkReader[] crs = { new HeaderReader(), new VertexReader(),
				new FaceReader(), new MaterialReader() };

		for (ChunkReader cr : crs)
			cr.interpret(input);
		input.close();

	}

	static public Pmx read(InputStream input) throws IOException {
		return new PmdReader(input).getData();
	}

	public Pmx getData() {
		return data;
	}

	public static boolean isPmdSignature(byte[] sig) {
		return sig[0] == 'P' && sig[1] == 'm' && sig[2] == 'd';
	}

	public class HeaderReader implements ChunkReader {
		@Override
		public void interpret(InputStream input) throws IOException {
			Header h = new Header();
			byte[] tmp = new byte[3];
			readBytes(tmp, input);
			h.setVersion(readNativeFloat(input));
			if (h.getVersion() < 1f)
				throw new RuntimeException("Version missmatch: "
						+ h.getVersion());

			Charset charset = Charset.forName("Shift_JIS");

			byte[] name = new byte[20];
			readBytes(name, input);
			int length = name.length;
			for (int i = 0; i < name.length; i++) {
				if (name[i] == 0xFD) {
					length = i;
					break;
				}
			}
			h.setName(new String(name, 0, length, charset));

			byte[] comment = new byte[256];
			readBytes(comment, input);
			length = comment.length;
			for (int i = 0; i < comment.length; i++) {
				if (comment[i] == 0xFD) {
					length = i;
					break;
				}
			}
			h.setComment(new String(comment, 0, length, charset));

			data.setHeader(h);
		}
	}

	public class VertexReader implements ChunkReader {

		@Override
		public void interpret(InputStream input) throws IOException {
			int length = readNativeInteger(input);
			Vertex[] vertices = new Vertex[length];

			for (int i = 0; i < vertices.length; i++) {
				BDEF2Vertex v = new BDEF2Vertex();

				v.setX(readNativeFloat(input));
				v.setY(readNativeFloat(input));
				v.setZ(readNativeFloat(input));
				v.setNx(readNativeFloat(input));
				v.setNy(readNativeFloat(input));
				v.setNz(readNativeFloat(input));
				v.setU(readNativeFloat(input));
				v.setV(readNativeFloat(input));
				v.setBone1(readNativeInteger(input, 2));
				v.setBone2(readNativeInteger(input, 2));
				v.setWeight(readByte(input) / 100f); // range: [0, 100]
				v.setEdgeRate(readByte(input) * -1f + 1); // 0: default, 1: no
															// edge

				vertices[i] = v;
			}
			data.setVertices(vertices);
		}
	}

	public class FaceReader implements ChunkReader {

		@Override
		public void interpret(InputStream input) throws IOException {
			int length = readNativeInteger(input);

			Face[] faces = new Face[length / 3];
			data.setFaces(faces);
			for (int i = 0; i < faces.length; i++) {
				Face face = new Face();
				face.setV1(readNativeInteger(input, 2));
				face.setV2(readNativeInteger(input, 2));
				face.setV3(readNativeInteger(input, 2));
				faces[i] = face;
			}
		}
	}

	public class MaterialReader implements ChunkReader {
		@Override
		public void interpret(InputStream input) throws IOException {
			int length = readNativeInteger(input);
			Material[] materials = new Material[length];
			String[] textures = new String[length];

			Charset charset = Charset.forName("Shift_JIS");

			byte[] tex = new byte[20];
			for (int i = 0; i < materials.length; i++) {
				Material m = new Material();
				m.setDiffuseR(readNativeFloat(input));
				m.setDiffuseG(readNativeFloat(input));
				m.setDiffuseB(readNativeFloat(input));
				m.setDiffuseA(readNativeFloat(input));
				m.setSpecularFactor(readNativeFloat(input));
				m.setSpecularR(readNativeFloat(input));
				m.setSpecularG(readNativeFloat(input));
				m.setSpecularB(readNativeFloat(input));
				m.setAmbientR(readNativeFloat(input));
				m.setAmbientG(readNativeFloat(input));
				m.setAmbientB(readNativeFloat(input));
				m.setToonShared(readByte(input));
				m.setEdgeSize(readByte(input));
				m.setBoundFace(readNativeInteger(input));
				m.setTexture(i);
				readBytes(tex, input);
				textures[i] = new String(tex, charset);
				materials[i] = m;
			}
			data.setMaterials(materials);
			data.setTextures(textures);
		}
	}
}
