package solar;
import java.awt.Font;
        
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.geometry.*;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.vecmath.*;
import java.io.FileNotFoundException;

import org.jogamp.java3d.loaders.IncorrectFormatException;
import org.jogamp.java3d.loaders.ParsingErrorException;
import org.jogamp.java3d.loaders.Scene;
import org.jogamp.java3d.loaders.objectfile.ObjectFile;


public abstract class RingObjectsKS {
	protected Alpha rotationAlpha;                           // NOTE: keep for future use
    protected abstract Node create_Object();	           // use 'Node' for both Group and Shape3D
    public abstract Node position_Object();
    public Alpha get_Alpha() { return alpha; };    // NOTE: keep for future use 
	protected static Alpha alpha;

}

class StringA2 extends RingObjectsKS {
    private TransformGroup objTG;                              // use 'objTG' to position an object
    private String str;
    public StringA2(String str_ltrs) {
        str = str_ltrs;		
        Transform3D scaler = new Transform3D();
        scaler.setScale(0.2);                              // scaling 4x4 matrix 
        Transform3D rot = new Transform3D();
        rot.rotY(Math.PI );
        Transform3D tr = new Transform3D();
        tr.mul(scaler);
        tr.mul(rot);
        objTG = new TransformGroup(tr);
        objTG.addChild(create_Object());		   // apply scaling to change the string's size
    }
    protected Node create_Object() {
        Font my2DFont = new Font("Arial", Font.PLAIN, 1);  // font's name, style, size
        FontExtrusion myExtrude = new FontExtrusion();
        Font3D font3D = new Font3D(my2DFont, myExtrude);		

        Point3f pos = new Point3f(-str.length()/4f, -.3f, 4.9f);// position for the string 
        Text3D text3D = new Text3D(font3D, str, pos);      // create a text3D object
        Appearance app = CommonsKS.obj_Appearance(CommonsKS.Red);
        return new Shape3D(text3D,app);
    }
    public Node position_Object() {
        return objTG;
    }
}

class circle extends RingObjectsKS{
	private float r;
    private TransformGroup orbit;
    public circle(float rad){
        r = rad;
        Transform3D rotator = new Transform3D();           // 4x4 matrix for rotation
		rotator.rotZ(Math.PI/6);  
		Transform3D trfm = new Transform3D();              // 4x4 matrix for composition

		trfm.mul(rotator);                                 // apply rotation first
		 orbit = new TransformGroup(trfm);
		orbit.addChild(create_Object());
		
    }
	protected Node create_Object() {
		float  z, x;                              // vertices at 0.6 away from origin
		Point3f coor[] = new Point3f[360];                   // declare 15 points for star shape
		LineArray lineArr = new LineArray(720, LineArray.COLOR_3 | LineArray.COORDINATES);
		for (int i = 0; i < 360; i++) {                     // define coordinates for circle shape
			z = (float) Math.cos(Math.PI / 180 * (90 + 1 * i)) * r;
			x = (float) Math.sin(Math.PI / 180 * (90 + 1 * i)) * r;
			coor[i] = new Point3f(x, 0.0f, z);            // use z-value to position star shape
		}
		for (int i = 0; i < 360; i++) {
			lineArr.setCoordinate(i*2 , coor[i]);         // define point pairs for each line
			lineArr.setCoordinate(i*2+1 ,coor[(i+1)%360] );
			lineArr.setColor(i*2 , CommonsKS.White);        // specify color for each pair of points
			lineArr.setColor(i*2+1, CommonsKS.White);
		}
		return new Shape3D(lineArr);                        // create and return a Shape3D
	}
	public Node position_Object() {
		return orbit;
	}
}


//class for smallest ring shape
class export extends RingObjectsKS {
	protected BranchGroup objBG ;                           // load external object to 'objBG'
    private String obj_name;
    Color3f clr;
    private float scaling;
    private float aa;
    private float b;
    private float d;
    public export(String obj,Color3f c,float scale,float aa,float b,float d) {                                  // identify object as "Ring1.obj"
        obj_name = obj;
        clr = c;       
        scaling = scale;
        this.aa = aa;
        this.b = b;
        this.d = d;

	}

    protected Node create_Object() {
		ObjectFile f = new ObjectFile(ObjectFile.RESIZE, (float) (60 * Math.PI / 180.0));
		Scene s = null;
		try {                                       // load object's definition file to 's'
			s = f.load("C:\\solarsystem\\soalr\\src\\solar\\"+obj_name+ ".obj");
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		} catch (ParsingErrorException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IncorrectFormatException e) {
			System.err.println(e);
			System.exit(1);
		}	
        objBG = s.getSceneGroup();
        Appearance a = CommonsKS.obj_Appearance(clr);       //adding the appearance
        Shape3D sh = (Shape3D)  objBG.getChild(0);
        sh.setAppearance(a);
        Transform3D r1 = new Transform3D();
        r1.setTranslation(new Vector3f((float) aa, (float) b,(float) d));     //0,0.06f,0
		r1.setScale(scaling);      //0.35
		TransformGroup R1 =new TransformGroup(r1);
        R1.addChild(objBG);
        return R1;
	}
	/* a function to attach the current object to 'objTG' and return 'objTG' */
	public Node position_Object() {
		return create_Object();                                // return 'objTG' as object's position
	}
}

//class for smallest ring shape
class Ring1 extends RingObjectsKS {
	protected BranchGroup objBG ;                           // load external object to 'objBG'
    Color3f clr;
    private float scaling;
    private int num;
    private float d;
    public Ring1(Color3f c,float scale,float d) {                                  // identify object as "Ring1.obj"
        clr = c;       
        scaling = scale;
        this.d = d;
       // alpha = new Alpha(-1,5000);

}
    
    protected Node create_Object() {
        Transform3D r1 = new Transform3D();
        r1.setTranslation(new Vector3f(0.0f, 0.0f,(float) d));     //0,0.06f,0
		r1.setScale(scaling);      //0.35
		TransformGroup R1 =new TransformGroup(r1);
        R1.addChild(new Sphere(0.12f,Primitive.GENERATE_NORMALS, 30,CommonsKS.obj_Appearance(CommonsKS.Blue)));
        //R1.addChild(CommonsKS.rotate_Behavior(50, R1,alpha));
        return R1;
	}

    public Node position_Object() {
        return create_Object();
    }
}
