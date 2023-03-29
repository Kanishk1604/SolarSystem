package solar;
/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;
import org.jogamp.java3d.*;
import org.jogamp.java3d.loaders.IncorrectFormatException;
import org.jogamp.java3d.loaders.ParsingErrorException;

import org.jogamp.java3d.loaders.Scene;
import org.jogamp.java3d.loaders.objectfile.ObjectFile;
import org.jogamp.java3d.utils.geometry.*;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.vecmath.*;
import org.jogamp.java3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import org.jogamp.java3d.utils.geometry.Box;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.java3d.utils.universe.ViewingPlatform;
import org.jogamp.vecmath.AxisAngle4f;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Point3d;
import org.jogamp.vecmath.Point3f;
import org.jogamp.vecmath.Vector3d;

import com.jogamp.opengl.util.texture.Texture;

public class CommonsKS extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
    private static SoundUtilityJOAL soundJOAL;               // needed for sound
	private static CollisionDetectShapes cd;
	public final static Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
	public final static Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
	public final static Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
	public final static Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
	public final static Color3f Cyan = new Color3f(0.0f, 1.0f, 1.0f);
	public final static Color3f Orange = new Color3f(1.0f, 0.5f, 0.0f);
	public final static Color3f Magenta = new Color3f(1.0f, 0.0f, 1.0f);
	public final static Color3f White = new Color3f(1.0f, 1.0f, 1.0f);
	public final static Color3f Grey = new Color3f(0.35f, 0.35f, 0.35f);
	public final static Color3f Black = new Color3f(0.0f, 0.0f, 0.0f);
	public final static Color3f[] clr_list = {Blue, Green, Red, Yellow, Cyan, Orange, Magenta, Grey};
	public final static int clr_num = 8;
	private static Color3f[] mtl_clrs = {White, Grey, Black};

	public final static BoundingSphere hundredBS = new BoundingSphere(new Point3d(), 100.0);
	public final static BoundingSphere twentyBS = new BoundingSphere(new Point3d(), 20.0);


    /* A1: function to define object's material and use it to set object's appearance */
	public static Appearance obj_Appearance(Color3f m_clr) {		
		Material mtl = new Material();                     // define material's attributes
		mtl.setShininess(32);
		mtl.setAmbientColor(mtl_clrs[0]);                   // use them to define different materials
		mtl.setDiffuseColor(m_clr);
		mtl.setSpecularColor(mtl_clrs[1]);
		mtl.setEmissiveColor(mtl_clrs[2]);                  // use it to switch button on/off
		mtl.setLightingEnable(true);

		Appearance app = new Appearance();
		app.setMaterial(mtl);                              // set appearance's material
		//app.setTexture(texturedApp());
		return app;
	}	

	//planets rotate
	/* a function to create a rotation behavior */
	public static RotationInterpolator rotating(int speed, TransformGroup rotTG,Alpha alpha,float a) {
		rotTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D zAxis = new Transform3D();
		AxisAngle4f axis = new AxisAngle4f(0.0f,1.0f,0.0f,(float) Math.PI);	//rotate around z-axis
		zAxis.setRotation(axis);
		zAxis.rotZ(Math.PI/6);
		//zAxis.setTranslation(new Vector3d(0.46f, 0.68f, 0f));
		zAxis.setTranslation(new Vector3d(0f,0.0f, a));
		// alpha = new Alpha(-1, speed);		//speed = 5000 ms
		RotationInterpolator rot_beh = new RotationInterpolator(alpha, rotTG, zAxis, 0.0f, -(float) Math.PI * 2.0f);
		rot_beh.setSchedulingBounds(hundredBS);
		return rot_beh;
	}

		//planets revolve
	public static RotationInterpolator rotate_Behavior(int r_num, TransformGroup rotTG,Alpha alpha) {

		rotTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yAxis = new Transform3D();
		AxisAngle4f axis = new AxisAngle4f(0.0f,0.0f,1.0f,(float) Math.PI/2);	//rotate around z-axis
		 yAxis.setRotation(axis);
		yAxis.rotZ(Math.PI/6);
		// alpha = new Alpha(-1, r_num);		//speed = 5000 ms
		RotationInterpolator rot_beh = new RotationInterpolator(
				alpha, rotTG, yAxis, 0.0f, (float) Math.PI * 2.0f);
		rot_beh.setSchedulingBounds(hundredBS);
		return rot_beh;
	}

	public static Background create_BK(Color3f clr,BoundingSphere b){
		Background bg = new Background();
		bg.setImage(new TextureLoader("C:\\solarsystem\\soalr\\image\\space.jpg",null).getImage());
		bg.setImageScaleMode(Background.SCALE_FIT_MAX);
		bg.setApplicationBounds(b);
		bg.setColor(clr);
		return bg;
	}

	/* a function to place one light or two lights at opposite locations */
	public static BranchGroup add_Lights(Color3f clr, int p_num) {
		BranchGroup lightBG = new BranchGroup();
		Point3f atn = new Point3f(0.5f, 0.0f, 0.0f);		
		PointLight ptLight;
		float adjt = 1f;
		for (int i = 0; (i < p_num) && (i < 2); i++) {
			if (i > 0) 
				adjt = -1f; 
			ptLight = new PointLight(clr, new Point3f(3.0f * adjt, 1.0f, 3.0f  * adjt), atn);
			ptLight.setInfluencingBounds(hundredBS);
			lightBG.addChild(ptLight);
		}
		return lightBG;
	}

	public static BranchGroup add_Ligh(Color3f clr, int p_num) {
		BranchGroup lightBG = new BranchGroup();
		Point3f atn = new Point3f(0.0f, 0.0f, 0.0f);		
		PointLight ptLight;
		float adjt = 1f;
		for (int i = 0; (i < p_num) && (i < 2); i++) {
			if (i > 0) 
				adjt = -1f; 
			ptLight = new PointLight(clr, new Point3f(3.0f * adjt, 1.0f, 3.0f  * adjt), atn);
			ptLight.setInfluencingBounds(hundredBS);
			lightBG.addChild(ptLight);
		}
		return lightBG;
	}

	/* a function to position viewer to 'eye' location */
	public static void define_Viewer(SimpleUniverse simple_U, Point3d eye) {

	    TransformGroup viewTransform = simple_U.getViewingPlatform().getViewPlatformTransform();
		Point3d center = new Point3d(0, 0, 0);             // define the point where the eye looks at
		Vector3d up = new Vector3d(0, 1, 0);               // define camera's up direction
		Transform3D view_TM = new Transform3D();
		view_TM.lookAt(eye, center, up);
		view_TM.invert();
	    viewTransform.setTransform(view_TM);               // set the TransformGroup of ViewingPlatform
	}

	/* a function to allow key navigation with the ViewingPlateform */
	public static KeyNavigatorBehavior key_Navigation(SimpleUniverse simple_U) {
		ViewingPlatform view_platfm = simple_U.getViewingPlatform();
		TransformGroup view_TG = view_platfm.getViewPlatformTransform();
		KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(view_TG);
		keyNavBeh.setSchedulingBounds(twentyBS);
		return keyNavBeh;
	}

	/* a function to build the content branch and attach to 'scene' */
	public static BranchGroup create_Scene() {
		BranchGroup sceneBG = new BranchGroup();
		TransformGroup sceneTG = new TransformGroup();
		sceneTG.addChild(new Box(0.5f, 0.5f, 0.5f, obj_Appearance(Orange) ));
		// sceneBG.addChild(rotate_Behavior(7500, sceneTG,rotationAlpha));
		// sceneBG.addChild(rotating(6000,sceneTG));
		
		sceneBG.addChild(sceneTG);
		return sceneBG;
	}

	/* a constructor to set up for the application */
	public CommonsKS(BranchGroup sceneBG) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		
		SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
		define_Viewer(su, new Point3d(1.0d, 1.0d, 4.0d));  // set the viewer's location
		
		sceneBG.addChild(add_Lights(Yellow, 1));	
		sceneBG.addChild(key_Navigation(su));              // allow key navigation
		sceneBG.compile();		                           // optimize the BranchGroup
		su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

		setLayout(new BorderLayout());
		add("Center", canvas);
		frame.setSize(800, 800);                           // set the size of the JFrame
		frame.setVisible(true);
	}

	public static BranchGroup loadShape(String name, float x, float y, float z, float scale) {
		int flags = ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY;
		ObjectFile f = new ObjectFile(flags, (float) (60 * Math.PI /180.0));
		Scene s = null;
		try {
			s = f.load("C:\\solarsystem\\soalr\\image\\"+name + ".obj");
		} catch (FileNotFoundException e) {			//exception
			System.err.println(e);
			System.exit(1);
		} catch (ParsingErrorException e) {
			System.err.println(e);
			System.exit(1);
		} catch (IncorrectFormatException e) {
			System.err.println(e);
			System.exit(1);
		}
		s.getSceneGroup();
		BranchGroup objBG = s.getSceneGroup();
		Transform3D translation = new Transform3D();           
		translation.setTranslation(new Vector3f(x, y, z));		//vector for translation
		Shape3D sh = (Shape3D) objBG.getChild(0);
		cd = new CollisionDetectShapes(sh);
		cd.setSchedulingBounds(CommonsKS.twentyBS);        // detect column's collision
		Transform3D scaler = new Transform3D();
		scaler.setScale(scale);			//vector for scaling
		// sh.setUserData(1);
		Transform3D trfm = new Transform3D();           
		trfm.mul(translation); 							// apply translation
		trfm.mul(scaler);                              // apply scaler
		                              
		TransformGroup objTG = new TransformGroup(trfm);
		objTG.addChild(objBG);
		objTG.addChild(cd);
		// soundJOAL.play("crash");
		

		BranchGroup objBG1 = new BranchGroup();
		objBG1.addChild(objTG);
		return objBG1;
	}

	public static  TransformGroup gh(){
		Transform3D trfm = new Transform3D();           
		trfm.setTranslation(new Vector3f(10f,10f,10f));
		TransformGroup objTG = null;
		if(cd.getCr() == true){
			soundJOAL.play("crash");
			objTG  = new TransformGroup(trfm);
		}
		return objTG;
	}
	public static void main(String[] args) {
		frame = new JFrame("KS's Common File");            
		frame.getContentPane().add(new CommonsKS(create_Scene()));  // create an instance of the class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
}
