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
    protected abstract Node create_Object();	           // use 'Node' for both Group and Shape3D
    public abstract Node position_Object();
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

// class the3Lines extends RingObjectsKS{

//     @Override
//     protected Node create_Object() {
//         // TODO Auto-generated method stub

//         Point3d point1 = new Point3d(0,0,0);
//         Point3d point2 = new Point3d(0,-0.3,0.4);
//         Point3d point3 = new Point3d(2,0,0);
//         Point3d point4 = new Point3d(0,1,0);


//         LineArray l = new LineArray(6,LineArray.COLOR_3 | LineArray.COORDINATES);

//         l.setCoordinate(0,point1);
//         l.setColor(0, CommonsKS.Blue);
//         l.setCoordinate(1, point2);
//         l.setColor(1, CommonsKS.Blue);

//         l.setCoordinate(2,point1);
//         l.setColor(2, CommonsKS.Green);
//         l.setCoordinate(3, point4);
//         l.setColor(3, CommonsKS.Green);

//         l.setCoordinate(4,point1);
//         l.setColor(4, CommonsKS.Red);
//         l.setCoordinate(5, point3);
//         l.setColor(5, CommonsKS.Red);



//         return new Shape3D(l);
//     }
//         @Override
//         public Node position_Object() {
//             // TODO Auto-generated method stub
//             return create_Object();
//         }
// }


    
// class MyCylinderA2 extends RingObjectsKS{
        
//     BranchGroup sgGroup;
//     @Override
//     protected Node create_Object() {
//         // TODO Auto-generated method stub
//         return new Cylinder(.5f,.2f,Primitive.GENERATE_NORMALS,30,30,CommonsKS.obj_Appearance(CommonsKS.Orange));
//     }

//     @Override
//     public Node position_Object() {
//         // TODO Auto-generated method stub
//         return create_Object();
//     }
    
// }

// class BoxA2 extends RingObjectsKS {                        // use 'objTG' to position an object
//     protected Node create_Object() {
//         return new Box (.5f, .1f, 1, Primitive.GENERATE_NORMALS, CommonsKS.obj_Appearance(CommonsKS.Orange));
//     }
//     protected Node create_Object(float x, float y, float z) {
//         return new Box(x, y, z, Primitive.GENERATE_NORMALS, CommonsKS.obj_Appearance(CommonsKS.Orange));
//     }
//     public Node position_Object() {
//         return create_Object();
//     }
// }


//class for smallest ring shape
class Ring1 extends RingObjectsKS {
	protected BranchGroup objBG ;                           // load external object to 'objBG'
    private String obj_name;
    Color3f clr;
    private float scaling;
    private float aa;
    private float b;
    private float d;
    public Ring1(String obj,Color3f c,float scale,float aa,float b,float d) {                                  // identify object as "Ring1.obj"
        obj_name = obj;
        clr = c;       
        scaling = scale;
        this.aa = aa;
        this.b = b;
        this.d = d;
        // String file = "C:\\solarsystem\\soalr\\src\\solar\\earth.jpg";
        // TextureLoader loader = new TextureLoader(file,null);
        // ImageComponent2D image = loader.getImage();
        // if(image == null) System.out.println("Cannot load file: " + file);
        // t = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,image.getWidth(),image.getHeight());
        // t.setImage(0,image);
		// ta  = new TransparencyAttributes(TransparencyAttributes.SCREEN_DOOR,0.5f);

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


// //class for innner ring shape
// class Ring2 extends RingObjectsKS {
// 	protected BranchGroup objBG ;                           // load external object to 'objBG'

//     private String obj_name;
//     Color3f clr;
//     public Ring2 (String obj,Color3f c) {                                  // identify object as "Ring1.obj"
//         obj_name = obj;
//         clr = c;                                                                                                      
// 	}

//     protected Node create_Object() {
// 		ObjectFile f = new ObjectFile(ObjectFile.RESIZE, (float) (60 * Math.PI / 180.0));
// 		Scene s = null;
// 		try {                                       // load object's definition file to 's'
// 			s = f.load(ClassLoader.getSystemResource(obj_name+ ".obj"));
// 		} catch (FileNotFoundException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		} catch (ParsingErrorException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		} catch (IncorrectFormatException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		}	
//         objBG = s.getSceneGroup();
//         Appearance a = CommonsKS.obj_Appearance(clr);       //adding the appearance
//         Shape3D sh = (Shape3D)  objBG.getChild(0);
//         sh.setAppearance(a);
//         Transform3D r1 = new Transform3D();
//         r1.setTranslation(new Vector3f(0,0.06f,0));
// 		r1.setScale(0.55);
// 		TransformGroup R1 =new TransformGroup(r1);
//         R1.addChild(objBG);
//         return R1;
// 	}
// 	/* a function to attach the current object to 'objTG' and return 'objTG' */
// 	public Node position_Object() {
// 		return create_Object();                                // return 'objTG' as object's position
// 	}
// }

// //class for middle ring shape
// class Ring3 extends RingObjectsKS {
// 	protected BranchGroup objBG ;                           // load external object to 'objBG'
//     private String obj_name;
//     Color3f clr;
//     public Ring3(String obj,Color3f c) {                                  // identify object as "Ring1.obj"
//         obj_name = obj;
//         clr = c;                                                                                                      
// 	}

//     protected Node create_Object() {
// 		ObjectFile f = new ObjectFile(ObjectFile.RESIZE, (float) (60 * Math.PI / 180.0));
// 		Scene s = null;
// 		try {                                       // load object's definition file to 's'
// 			s = f.load(ClassLoader.getSystemResource(obj_name+ ".obj"));
// 		} catch (FileNotFoundException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		} catch (ParsingErrorException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		} catch (IncorrectFormatException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		}	
//         objBG = s.getSceneGroup();
//         Appearance a = CommonsKS.obj_Appearance(clr);       //adding the appearance
//         Shape3D sh = (Shape3D)  objBG.getChild(0);
//         sh.setAppearance(a);
//         Transform3D r1 = new Transform3D();
// 		r1.setScale(0.8);
//         r1.setTranslation(new Vector3f(0,0.06f,0));
// 		TransformGroup R1 =new TransformGroup(r1);
//         R1.addChild(objBG);
//         return R1;
//     }
// 	/* a function to attach the current object to 'objTG' and return 'objTG' */
// 	public Node position_Object() {
// 		return create_Object();                                // return 'objTG' as object's position
// 	}
// }


// //class for outer ring shape
// class Ring4 extends RingObjectsKS {
// 	protected BranchGroup objBG ;                           // load external object to 'objBG'
//     private String obj_name;
//     Color3f clr;
// 	TransformGroup R1 ;
//     public Ring4(String obj,Color3f c) {                                  // identify object as "Ring1.obj"
//         obj_name = obj;
//         clr = c;                                                                                                      
// 	}

//     protected Node create_Object() {
// 		ObjectFile f = new ObjectFile(ObjectFile.RESIZE, (float) (60 * Math.PI / 180.0));
// 		Scene s = null;
// 		try {                                       // load object's definition file to 's'
// 			s = f.load(ClassLoader.getSystemResource(obj_name+ ".obj"));
// 		} catch (FileNotFoundException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		} catch (ParsingErrorException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		} catch (IncorrectFormatException e) {
// 			System.err.println(e);
// 			System.exit(1);
// 		}	
//         objBG = s.getSceneGroup();
//         Appearance a = CommonsKS.obj_Appearance(clr);   //adding the appearance
//         Shape3D sh = (Shape3D)  objBG.getChild(0);
//         sh.setAppearance(a);
//         Transform3D r1 = new Transform3D();
// 		r1.setScale(1.0);
// 		TransformGroup R1 =new TransformGroup(r1);
//         R1.addChild(objBG);
//         return R1;
// 	}
// 	/* a function to attach the current object to 'objTG' and return 'objTG' */
// 	public Node position_Object() {
// 		return create_Object();                                // return 'objTG' as object's position
// 	}
// }
