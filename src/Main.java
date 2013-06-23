import java.io.File;
import com.na128.mmd.Pmx;
import com.na128.mmd.Reader;
import com.na128.mmd.data.Face;
import com.na128.mmd.data.Material;
import com.na128.mmd.data.Vertex;

import processing.core.*;

public class Main extends PApplet {

	Pmx data;

	static String fn = null; // ファイルパス

	static public void main(String[] args) {
			new Main().runSketch();
	}

	@Override
	public void setup() {
		size(640, 480, P3D);

		float fov = PI / 3.0f;
		float cameraZ = (height / 2.0f) / tan(fov / 2.0f);
		perspective(fov, (float) width / height, cameraZ / 10.0f,
				cameraZ * 10.0f);

		try {
			if(fn == null)
				throw new Exception("'fn' has not been assigned. Please put path to it.");
			
			System.out.println("Loading " + fn);
			data = Reader.read(new File(fn));
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		
		System.out.println(data.getHeader().getName());
		System.out.println(data.getHeader().getComment());
		System.out.println(data.getVertices().length + " verts");
		System.out.println(data.getFaces().length + " faces");
		System.out.println(data.getMaterials().length + " materials");
	}

	int i = 0;

	@Override
	public void draw() {
		background(0);

		if (i++ >= 360)
			i = 0;
		camera(150 * (cos(radians(i)) - sin(radians(i))), 30, -150
				* (sin(radians(i)) + cos(radians(i))), 0, 0, 0, 0, -1, 0);
		
		scale(10f);
		stroke(0xFF, 0x20);
		noFill();
		box(13, 20, 5);
		translate(0, -10, 0);

		Vertex[] vs = data.getVertices();
		Face[] fs = data.getFaces();
		Material[] ms = data.getMaterials();
		
		ambientLight(128, 128, 128);
		directionalLight(200, 200, 200, 0, -1, 1);
		
		noStroke();
		for (int i = 0, v = 0; i < ms.length; i++) {
			Material m = ms[i];
			beginShape(TRIANGLES);
			for (int j = 0; j < m.getBoundFace() / 3; j++, v++) {
				fill(255 * m.getDiffuseR(), 255 * m.getDiffuseG(), 255 * m.getDiffuseB());
				ambient(255 * m.getAmbientR(), 255 * m.getAmbientG(), 255 * m.getAmbientB());
				specular(255 * m.getSpecularR(), 255 * m.getSpecularG(), 255 * m.getSpecularB());
				
				Face f = fs[v];
				vertex(vs[f.getV1()].getX(), vs[f.getV1()].getY(),
						vs[f.getV1()].getZ());
				vertex(vs[f.getV2()].getX(), vs[f.getV2()].getY(),
						vs[f.getV2()].getZ());
				vertex(vs[f.getV3()].getX(), vs[f.getV3()].getY(),
						vs[f.getV3()].getZ());
				endPGL();
			}
			endShape();
		}

		// stroke(0xFF);
		// for (int i = 0; i < vs.length; i++) {
		// point(vs[i].getX(), vs[i].getY(), vs[i].getZ());
		// }
	};
}
