package solar;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.crypto.dsig.Transform;

import org.jogamp.java3d.*;
import org.jogamp.java3d.loaders.IncorrectFormatException;
import org.jogamp.java3d.loaders.ParsingErrorException;
import org.jogamp.java3d.loaders.Scene;
import org.jogamp.java3d.loaders.objectfile.ObjectFile;
import org.jogamp.java3d.utils.geometry.Sphere;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.java3d.utils.picking.PickResult;
import org.jogamp.java3d.utils.picking.PickTool;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;
import org.jogamp.java3d.utils.universe.Viewer;
import java.net.URL;

import solar.PointSoundBehavior;
import org.jdesktop.j3d.examples.sound.audio.JOALMixer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Assignment3KS extends JPanel implements KeyListener,MouseListener {
  
    
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static SimpleUniverse su;
	private static final int OBJ_NUM = 1; 
	protected static Alpha alpha1;                           //this is for revolving
	protected static Alpha alpha2;                           //this is for revolving
	protected static Alpha alpha3;                           //this is for revolving
	protected static Alpha alpha4;                           //this is for revolving
	protected static Alpha alpha5;                           //this is for revolving
	protected static Alpha alpha6;                           //this is for revolving
	protected static Alpha alpha7;                           //this is for revolving
	protected static Alpha alpha8;                           //this is for revolving
	protected static Alpha alpha9;                           //this is for revolving
	protected static Alpha alpha10;                           //this is for revolving
	
	protected static Alpha rotalpha1;                           //this is for rotating
	protected static Alpha rotalpha2;                           //this is for rotating
	protected static Alpha rotalpha3;                           //this is for rotating
	protected static Alpha rotalpha4;                           //this is for rotating
	protected static Alpha rotalpha5;                           //this is for rotating
	protected static Alpha rotalpha6;                           //this is for rotating
	protected static Alpha rotalpha7;                           //this is for rotating
	protected static Alpha rotalpha8;                           //this is for rotating
	protected static Alpha rotalpha9;                           //this is for rotating

	// public Alpha get_Alpha() { return alpha; };    // NOTE: keep for future use 
	private static RingObjectsKS sun;
	private static RingObjectsKS mercury;
	private static RingObjectsKS venus;
	private static RingObjectsKS earth;
	private static RingObjectsKS mars;
	private static RingObjectsKS jupiter;
	private static RingObjectsKS saturn;
	private static RingObjectsKS uranus;
	private static RingObjectsKS neptune;
	private static RingObjectsKS pluto;
	

	private static RingObjectsKS[] Object3D = new RingObjectsKS[404];
	private static boolean space = true;

	private static boolean y = true;
	private static boolean v = true;
	private static boolean er = true;
	private static boolean m = true;
	private static boolean j = true;
	private static boolean s = true;
	private static boolean u = true;
	private static boolean n = true;
	private static boolean p = true;
	
	private static boolean a1 = true;
	private static boolean a2 = true;
	private static boolean a3 = true;
	private static boolean a4 = true;
	private static boolean a5 = true;
	private static boolean a6 = true;
	private static boolean a7 = true;
	private static boolean a8 = true;
	private static boolean a9 = true;


	public static boolean ON1 = false;
	public static boolean ON2 = false;
	public static boolean ON3 = false;
	public static boolean ON4 = false;
	public static boolean ON5 = false;
	public static boolean ON6 = false;
	public static boolean ON7 = false;
	public static boolean ON8 = false;
	public static boolean ON9 = false;

	public static int s1 = -1;
	public static int s2 = -2;
	public static int s3 = -3;
	public static int s4 = -4;
	public static int s5 = -5;
	public static int s6 = -6;
	public static int s7 = -7;
	public static int s8 = -8;
	public static int s9 = -9;
	public static int s10 = -10;
	public static int s11= -11;
	public static int s12= -12;
	public static int s13= -13;
	public static int s14= -14;
	public static int s15= -15;
	public static int s16 = -16;
	public static int s17 = -17;
	public static int s18 = -18;

    private static PickTool pickTool;
    private Canvas3D canvas;
	private static TransformGroup R1;
	public static Transform3D trfm = new Transform3D();
	// public static TransformGroup rocket  = new TransformGroup();
	private static Sphere mtr;
	public static Alpha get_Alpha() { return alpha1; };    // NOTE: keep for future use 
	
	 private static SoundUtilityJOAL soundJOAL;               // for A5: needed for sound
    
    private static PointSound ps;
    private static PointSound ps2;
    private static PointSound ps3;
    private static PointSound ps4;
    private static PointSound ps5;
    private static PointSound ps6;
    private static PointSound ps7;
    private static PointSound ps8;
    private static PointSound ps9;

	public static BranchGroup create_Scene() {
        Transform3D scaler = new Transform3D(); // 4x4 matrix for scaling
		scaler.setScale(new Vector3d(0,0,1f));
		BranchGroup sceneBG = new BranchGroup();           // create the scene' BranchGroup
		TransformGroup sceneTG = new TransformGroup(); 
		TransformGroup baseTG = new TransformGroup();   // create the scene's TransformGroup


		R1 = new TransformGroup();
		TransformGroup cir  = new TransformGroup();

		float x = (float) 0.9;

		Object3D[0] = new StringA2("Mercury",(float)0.3,(float)1,CommonsKS.Red);
		Object3D[1] = new StringA2("Venus",(float) 0.6,(float)1.8,CommonsKS.Blue);
		Object3D[2] = new StringA2("Earth",(float)0.9,(float)2.6,CommonsKS.Green);
		Object3D[3] = new StringA2("Mars",(float)1.2,(float)3.4,CommonsKS.Yellow);
		Object3D[4] = new StringA2("Jupiter",(float)1.5,(float)4.2,CommonsKS.Magenta);
		Object3D[5] = new StringA2("Saturn",(float)1.8,(float)5.2,CommonsKS.Cyan);
		Object3D[6] = new StringA2("Uranus",(float)2.1,(float)6.2,CommonsKS.Grey);
		Object3D[7] = new StringA2("Neptune",(float)2.4,(float)7.2,CommonsKS.Orange);
		Object3D[8] = new StringA2("Pluto",(float)2.7,(float)8.6,CommonsKS.Red);

		sun = new Sun(CommonsKS.Blue, (float) 3, (float) 0.0f); // create the external object
        mercury = new Mercury(CommonsKS.White, (float) 1, (float) x); // create the external object
        venus = new Venus(CommonsKS.White, (float) 1.8, (float) 2); // create the external object
        earth = new Earth(CommonsKS.White, (float) 1.9, (float) 3); // create the external object
        mars = new Mars(CommonsKS.White, (float) 1.6, (float) 4); // create the external object
        jupiter = new Jupiter(CommonsKS.White, (float) 3.4, (float) 5); // create the external object
        saturn = new export("Saturn", CommonsKS.White, (float) 0.7, (float) 0.0, (float) 0.0f, (float) 6); // create
        uranus = new Uranus(CommonsKS.White, (float) 2.7, (float) 7); // create the external object 
        neptune = new Neptune(CommonsKS.White, (float) 2.3, (float) 8); // create the external object
        pluto = new Pluto(CommonsKS.White, (float) 0.7, (float) 9); // create the external object

        Object3D[9] = new Meteor("meteor1", s1,CommonsKS.Grey,0f,0.26f,0.9f);
        Object3D[10] = new Meteor("meteor2", s2,CommonsKS.Grey,0f,0.26f,1.1f);

        Object3D[11] = new Meteor("meteor3",s3, CommonsKS.Grey,0f,0.56f,1.7f);
        Object3D[12] = new Meteor("meteor4",s4, CommonsKS.Grey,0f,0.56f,1.9f);
        
		Object3D[13] = new Meteor("meteor5",s5, CommonsKS.Grey,0f,0.86f,2.5f);
        Object3D[14] = new Meteor("meteor6",s6, CommonsKS.Grey,0f,0.86f,2.7f);

        Object3D[15] = new Meteor("meteor7",s7, CommonsKS.Grey,0f,1.16f,3.5f);
        Object3D[16] = new Meteor("meteor8",s8 ,CommonsKS.Grey,0f,1.16f,3.7f);

        Object3D[17] = new Meteor("meteor9",s9, CommonsKS.Grey,0f,1.46f,4.3f);
        Object3D[18] = new Meteor("meteor10",s10 ,CommonsKS.Grey,0f,1.46f,4.5f);
		
        Object3D[19] = new Meteor("meteor11",s11, CommonsKS.Grey,0f,1.76f,5.3f);
        Object3D[20] = new Meteor("meteor12",s12, CommonsKS.Grey,0f,1.76f,5.5f);
		
        Object3D[21] = new Meteor("meteor11",s13, CommonsKS.Grey,0f,2.06f,6.3f);
        Object3D[22] = new Meteor("meteor12",s14, CommonsKS.Grey,0f,2.06f,6.5f);

        Object3D[23] = new Meteor("meteor11",s15, CommonsKS.Grey,0f,2.36f,7.3f);
        Object3D[24] = new Meteor("meteor12",s16, CommonsKS.Grey,0f,2.36f,7.5f);		

        Object3D[25] = new Meteor("meteor11",s17, CommonsKS.Grey,0f,2.66f,8.7f);
        Object3D[26] = new Meteor("meteor12",s18, CommonsKS.Grey,0f,2.66f,8.9f);	
		
		// Object3D[27] = new rocket(alpha10);
		TransformGroup met = new TransformGroup();

        float aa = 0;
        float cc = 3.5f;

        TransformGroup metbelt = new TransformGroup();

        for (int i = 28; i < 118; i++) {

            Transform3D rotator = new Transform3D(); // 4x4 matrix for rotation
            rotator.rotZ(Math.PI / 6); // 2.8
            Transform3D trfm = new Transform3D(); // 4x4 matrix for composition

            trfm.mul(rotator); // apply rotation first

            metbelt = new TransformGroup(trfm);

            cc = (float) Math.cos(Math.PI / 180 * (90 + 4 * i)) * 4.5f;
            aa = (float) Math.sin(Math.PI / 180 * (90 + 4 * i)) * 4.5f;

            Object3D[i] = new Meteor("meteor10", s7, CommonsKS.Grey, aa, 0f, cc);
        }

        for (int i = 118; i < 208; i++) {

            Transform3D rotator = new Transform3D(); // 4x4 matrix for rotation
            rotator.rotZ(Math.PI / 6); // 2.8
            Transform3D trfm = new Transform3D(); // 4x4 matrix for composition

            trfm.mul(rotator); // apply rotation first

            metbelt = new TransformGroup(trfm);

            cc = (float) Math.cos(Math.PI / 180 * (90 + 4 * i)) * 4.5f;
            aa = (float) Math.sin(Math.PI / 180 * (90 + 4 * i)) * 4.5f;

            Object3D[i] = new Meteor("meteor3", s7, CommonsKS.Grey, (float) (aa - 0.1f), 0.1f, cc);
        }
        for (int i = 208; i < 298; i++) {

            Transform3D rotator = new Transform3D(); // 4x4 matrix for rotation
            rotator.rotZ(Math.PI / 6); // 2.8
            Transform3D trfm = new Transform3D(); // 4x4 matrix for composition

            trfm.mul(rotator); // apply rotation first

            metbelt = new TransformGroup(trfm);

            cc = (float) Math.cos(Math.PI / 180 * (90 + 4 * i)) * 4.5f;
            aa = (float) Math.sin(Math.PI / 180 * (90 + 4 * i)) * 4.5f;

            Object3D[i] = new Meteor("meteor5", s7, CommonsKS.Grey, (float) (aa - 0.2f), 0.15f, (float) (cc + 0.1f));
        }

        for (int i = 298; i < 388; i++) {

            Transform3D rotator = new Transform3D(); // 4x4 matrix for rotation
            rotator.rotZ(Math.PI / 6); // 2.8
            Transform3D trfm = new Transform3D(); // 4x4 matrix for composition

            trfm.mul(rotator); // apply rotation first

            metbelt = new TransformGroup(trfm);

            cc = (float) Math.cos(Math.PI / 180 * (90 + 4 * i)) * 4.5f;
            aa = (float) Math.sin(Math.PI / 180 * (90 + 4 * i)) * 4.5f;

            Object3D[i] = new Meteor("meteor7", s7, CommonsKS.Grey, (float) (aa - 0.3f), 0.2f, (float) (cc + 0.2f));
        }
        Object3D[27] = new rocket(alpha10);
        // TransformGroup met = new TransformGroup();

        // meteors
        for (int i = 9; i < 28; i++) {
            met.addChild(Object3D[i].position_Object());
        }

		
		Object3D[388] = new Meteor("meteor7", s7, CommonsKS.Grey, 0f, 1.16f, 4.1f);
        Object3D[389] = new Meteor("meteor8", s8, CommonsKS.Grey, 0f, 1.16f, 4.1f);
        Object3D[390] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.1f, 1.17f, 4.3f);
        Object3D[391] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.1f, 1.17f, 4.2f);
        Object3D[392] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.2f, 1.18f, 4.3f);
        Object3D[393] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.2f, 1.19f, 4.1f);
        Object3D[394] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.3f, 1.19f, 4.3f);
        Object3D[395] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.3f, 1.20f, 4.3f);
        Object3D[396] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.4f, 1.20f, 4.5f);
        Object3D[397] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.5f, 1.21f, 4.2f);
        Object3D[398] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.5f, 1.21f, 4.1f);
        Object3D[399] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.6f, 1.22f, 4.3f);
        Object3D[400] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.6f, 1.23f, 4.3f);
        Object3D[401] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.7f, 1.24f, 4.5f);
        Object3D[402] = new Meteor("meteor7", s7, CommonsKS.Grey, 0.7f, 1.23f, 4.3f);
        Object3D[403] = new Meteor("meteor8", s8, CommonsKS.Grey, 0.8f, 1.25f, 4.2f);
		
        for (int i = 28; i < 404; i++) {
            metbelt.addChild(Object3D[i].position_Object());
        }
		TransformGroup sunTG= new TransformGroup();

		TransformGroup mercuryTG = new TransformGroup();
		TransformGroup mercuryTG2 = new TransformGroup();
		// TransformGroup r1T = new TransformGroup();
		//  r1T.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		TransformGroup venusTG = new TransformGroup();
		TransformGroup venusTG2 = new TransformGroup();
		
		TransformGroup earthTG = new TransformGroup();
		TransformGroup earthTG2 = new TransformGroup();
		
		TransformGroup marsTG = new TransformGroup();
		TransformGroup marsTG2 = new TransformGroup();
		
		TransformGroup jupiterTG = new TransformGroup();
		TransformGroup jupiterTG2 = new TransformGroup();
		
		TransformGroup saturnTG = new TransformGroup();
		TransformGroup saturnTG2 = new TransformGroup();
		
		TransformGroup uranusTG = new TransformGroup();
		TransformGroup uranusTG2 = new TransformGroup();
		
		TransformGroup neptuneTG = new TransformGroup();
		TransformGroup neptuneTG2 = new TransformGroup();
		
		TransformGroup plutoTG = new TransformGroup();
		TransformGroup plutoTG2 = new TransformGroup();
		
		TransformGroup str = new TransformGroup();


        alpha1 = new Alpha(-1, 17000);
        alpha2 = new Alpha(-1, 30000);
        alpha3 = new Alpha(-1, 45000);
        alpha4 = new Alpha(-1, 70000);
        alpha5 = new Alpha(-1, 100000);
        alpha6 = new Alpha(-1, 250000);
        alpha7 = new Alpha(-1, 370000);
        alpha8 = new Alpha(-1, 420000);
        alpha9 = new Alpha(-1, 510000);
        alpha10 = new Alpha(-1, 5000);

        rotalpha1 = new Alpha(-1, 7000);
        rotalpha2 = new Alpha(-1, 15000);
        rotalpha3 = new Alpha(-1, 24000);
        rotalpha4 = new Alpha(-1, 29000);
        rotalpha5 = new Alpha(-1, 55000);
        rotalpha6 = new Alpha(-1, 70000);
        rotalpha7 = new Alpha(-1, 85000);
        rotalpha8 = new Alpha(-1, 105000);
        rotalpha9 = new Alpha(-1, 125000);


		sunTG.addChild(sun.position_Object());     
		           
		mercuryTG.addChild(mercury.position_Object());   
		mercuryTG.addChild(CommonsKS.rotate_Behavior(50000,mercuryTG,alpha1));
		mercuryTG2.addChild(mercury.position_Object());
		mercuryTG2.addChild(CommonsKS.rotating(400, mercuryTG2,rotalpha1,(float)0.9));             

		venusTG.addChild(venus.position_Object());                // addding child ring1
		venusTG2.addChild(venus.position_Object());                // addding child ring1
		venusTG.addChild(CommonsKS.rotate_Behavior(500,venusTG,alpha2));
		venusTG2.addChild(CommonsKS.rotating(400, venusTG2,rotalpha2,(float)2));             

		earthTG.addChild(earth.position_Object());                // addding child ring1	
		earthTG2.addChild(earth.position_Object());                // addding child ring1	
		earthTG.addChild(CommonsKS.rotate_Behavior(4000,earthTG,alpha3));
		earthTG2.addChild(CommonsKS.rotating(400, earthTG2,rotalpha3,(float)3));             

		marsTG.addChild(mars.position_Object());                // addding child ring1	
		marsTG2.addChild(mars.position_Object());                // addding child ring1	
		marsTG.addChild(CommonsKS.rotate_Behavior(5000,marsTG,alpha4));
		marsTG2.addChild(CommonsKS.rotating(400, marsTG2,rotalpha4,(float)4));             

		jupiterTG.addChild(jupiter.position_Object());                // addding child ring1	
		jupiterTG2.addChild(jupiter.position_Object());                // addding child ring1	
		jupiterTG.addChild(CommonsKS.rotate_Behavior(5000,jupiterTG,alpha5));
		jupiterTG2.addChild(CommonsKS.rotating(400, jupiterTG2,rotalpha5,(float)5));             

		saturnTG.addChild(saturn.position_Object());                // addding child ring1	
		//saturnTG2.addChild(saturn.position_Object());                // addding child ring1	
		saturnTG.addChild(CommonsKS.rotate_Behavior(5000,saturnTG,alpha6));
		//saturnTG2.addChild(CommonsKS.rotating(400, saturnTG2,rotalpha6,(float)6));             

		// uranus = new export("Uranus", CommonsKS.White, (float) 1.4, (float) 0.0, (float) 0.0f, (float) 8,su); // create
		uranusTG.addChild(uranus.position_Object()); // addding child ring1
        // uranusTG2.addChild(uranus.position_Object()); // addding child ring1
        uranusTG.addChild(CommonsKS.rotate_Behavior(30000, uranusTG, alpha7));
        // uranusTG2.addChild(CommonsKS.rotating(400, uranusTG2, rotalpha7, (float) 7));
		
		neptuneTG.addChild(neptune.position_Object());                // addding child ring1	
		neptuneTG2.addChild(neptune.position_Object());                // addding child ring1	
		neptuneTG.addChild(CommonsKS.rotate_Behavior(40000,neptuneTG,alpha8));
		neptuneTG2.addChild(CommonsKS.rotating(400, neptuneTG2,rotalpha8,(float)8));             

		plutoTG.addChild(pluto.position_Object());                // addding child ring1	
		plutoTG2.addChild(pluto.position_Object());                // addding child ring1	
		plutoTG.addChild(CommonsKS.rotate_Behavior(50000,plutoTG,alpha9));
		plutoTG2.addChild(CommonsKS.rotating(400, plutoTG2,rotalpha9,(float)9));            

		
		R1.addChild(sunTG);

		mercuryTG.addChild(mercuryTG2);
		venusTG.addChild(venusTG2);
		earthTG.addChild(earthTG2);
		marsTG.addChild(marsTG2);
		jupiterTG.addChild(jupiterTG2);
		saturnTG.addChild(saturnTG2);
		uranusTG.addChild(uranusTG2);
		neptuneTG.addChild(neptuneTG2);
		plutoTG.addChild(plutoTG2);
		
		// mercuryTG.addChild(r1T);
		
        mercuryTG.addChild(pointSound());
        venusTG.addChild(pointSound2());
        earthTG.addChild(pointSound3());
        marsTG.addChild(pointSound4());
        jupiterTG.addChild(pointSound5());
        saturnTG.addChild(pointSound6());
        uranusTG.addChild(pointSound7());
        neptuneTG.addChild(pointSound8());
        plutoTG.addChild(pointSound9());

		R1.addChild(mercuryTG);
		R1.addChild(venusTG);
		R1.addChild(earthTG);
		R1.addChild(marsTG);
		R1.addChild(jupiterTG);
		R1.addChild(saturnTG);
		R1.addChild(uranusTG);
		R1.addChild(neptuneTG);
		R1.addChild(plutoTG);

		
		//R1.addChild(CommonsKS.rotate_Behavior(5000,R1));

		//orbits
		RingObjectsKS merOrb = new circle((float)x);
		RingObjectsKS venOrb = new circle((float)2);
		RingObjectsKS EarthOrb = new circle((float)3);
		RingObjectsKS MarsOrb = new circle((float)4);
		RingObjectsKS JupiterOrb = new circle((float)5);
		RingObjectsKS SatOrb = new circle((float)6);
		RingObjectsKS UrAnusOrb = new circle((float)7);
		RingObjectsKS NepOrb = new circle((float)8);
		RingObjectsKS PlutoOrb = new circle((float)9);
		

		cir.addChild(merOrb.position_Object());
		cir.addChild(venOrb.position_Object());
		cir.addChild(EarthOrb.position_Object());
		cir.addChild(MarsOrb.position_Object()); 
		cir.addChild(JupiterOrb.position_Object());
		cir.addChild(SatOrb.position_Object());
		cir.addChild(UrAnusOrb.position_Object());
		cir.addChild(NepOrb.position_Object());
		cir.addChild(PlutoOrb.position_Object());

		//addiong strings
		str.addChild(Object3D[0].position_Object());
		str.addChild(Object3D[1].position_Object());
		str.addChild(Object3D[2].position_Object());
		str.addChild(Object3D[3].position_Object());
		str.addChild(Object3D[4].position_Object());
		str.addChild(Object3D[5].position_Object());
		str.addChild(Object3D[6].position_Object());
		str.addChild(Object3D[7].position_Object());
		str.addChild(Object3D[8].position_Object());

		
	BoundingSphere b = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
		sceneBG.addChild(cir);
		
		sceneBG.addChild(R1);
		sceneBG.addChild(sceneTG);
		sceneBG.addChild(str);
		sceneBG.addChild(met);
        sceneBG.addChild(metbelt);
		sceneBG.addChild(CommonsKS.add_Lights(CommonsKS.White, 1));	
		// sceneBG.addChild(CommonsKS.add_Ligh(CommonsKS.White, 1));	

		// sceneBG.addChild(CommonsKS.rotate_Behavior(6000, sceneTG,alpha));
		sceneBG.addChild(CommonsKS.create_BK(CommonsKS.Grey, b));		//creating background
		
		return sceneBG;
    }
    /* NOTE: Keep the constructor for each of the labs and assignments */
	public Assignment3KS(BranchGroup sceneBG) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(config);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);

        pickTool = new PickTool(sceneBG);
        pickTool.setMode(PickTool.GEOMETRY);

		 su = new SimpleUniverse(canvas);    // create a SimpleUniverse
		CommonsKS.define_Viewer(su, new Point3d(4.5d, 0.0d, 1.0d));
		// CommonsKS.define_Viewer(su, new Point3d(0.0d, 0.0d, 0.0d));
		enableAudio(su);                                   // enable audio
		sceneBG.addChild(CommonsKS.key_Navigation(su));     // allow key navigation
		sceneBG.compile();		                           // optimize the BranchGroup
		su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

		setLayout(new BorderLayout());
		add("Center", canvas);
		frame.setSize(800, 800);                           // set the size of the JFrame
		frame.setVisible(true);
	}
    public Assignment3KS(Canvas3D canvas3D) {
        canvas = canvas3D; 
    }
    public static void main(String[] args) {
		frame = new JFrame("KS's Assignment3");                   // NOTE: change XY to student's initials
		frame.getContentPane().add(new Assignment3KS(create_Scene()));  // create an instance of the class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void enableAudio(SimpleUniverse simple_U) {

        JOALMixer mixer = null;                                 // create a null mixer as a joalmixer
        Viewer viewer = simple_U.getViewer();
        viewer.getView().setBackClipDistance(20.0f);         // make object(s) disappear beyond 20f 

        if (mixer == null && viewer.getView().getUserHeadToVworldEnable()) {
            mixer = new JOALMixer(viewer.getPhysicalEnvironment());
            if (!mixer.initialize()) {                       // add mixer as audio device if successful
                System.out.println("Open AL failed to init");
                viewer.getPhysicalEnvironment().setAudioDevice(null);
            }
        }
    }

    private static PointSound pointSound() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\magic_bells.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps = new PointSound();                    // create and position a point sound
        ps.setEnable(false);
        ps.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.09));
		ps.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps;
    }

	private static PointSound pointSound2() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\VenusSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps2 = new PointSound();                    // create and position a point sound
        ps2.setEnable(false);
        ps2.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps2, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 2));
		ps2.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps2;
    }

	private static PointSound pointSound3() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\EarthSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps3 = new PointSound();                    // create and position a point sound
        ps3.setEnable(false);
        ps3.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps3, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps3.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps3;
    }

	private static PointSound pointSound4() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\MarsSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps4 = new PointSound();                    // create and position a point sound
        ps4.setEnable(false);
        ps4.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps4, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps4.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps4;
    }

	private static PointSound pointSound5() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\JupiterSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps5 = new PointSound();                    // create and position a point sound
        ps5.setEnable(false);
        ps5.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps5, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps5.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps5;
    }

	private static PointSound pointSound6() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\SaturnSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps6 = new PointSound();                    // create and position a point sound
        ps6.setEnable(false);
        ps6.setInitialGain(0.1f);
        
        PointSoundBehavior player = new PointSoundBehavior(ps6, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps6.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps6;
    }

	private static PointSound pointSound7() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\UranusSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps7 = new PointSound();                    // create and position a point sound
        ps7.setEnable(false);
        ps7.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps7, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps7.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps7;
    }

	private static PointSound pointSound8() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\NeptuneSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps8 = new PointSound();                    // create and position a point sound
        ps8.setEnable(false);
        ps8.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps8, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps8.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps8;
    }
	
	private static PointSound pointSound9() {
        URL url = null;
        String filename = "C:\\solarsystem\\soalr\\image\\PlutoSound.wav";
        try {
            url = new URL("file", "localhost", filename);
        } catch (Exception e) {
            System.out.println("Can't open " + filename);
        }
        ps9 = new PointSound();                    // create and position a point sound
        ps9.setEnable(false);
        ps9.setInitialGain(0.1f);
        PointSoundBehavior player = new PointSoundBehavior(ps9, url, new Point3f(0.0f, 0.0f, 0.0f));
        player.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 0.1));
		ps9.setCapability(PointSound.ALLOW_ENABLE_WRITE);
        return ps9;
    }
	//Interaction using keyboard
	@Override
    public void keyPressed(KeyEvent e) {

        // if((e.getKeyCode() == KeyEvent.VK_R)){
        // Transform3D uu = new Transform3D();
        // uu.rotZ(Math.PI/2);
        // trfm.mul(uu);

        // }
        // For revolution of planets
        if ((e.getKeyCode() == KeyEvent.VK_Y)) {
            if (y){ 
                alpha1.pause();
                y = false;
                s1 = -1;
				ON1  = true;
				ps.setEnable(ON1);
                mtr.setUserData(s1); // reset 'UserData'
			}
			else {
                y = true;
                alpha1.resume();
                s1 = 1;
                ON1  = false;
				ps.setEnable(ON1);
			
                mtr.setUserData(s1);
			}
        }
        if ((e.getKeyCode() == KeyEvent.VK_V)) {
            if (v) {
                alpha2.pause();
                v = false;
                // sphere.setUserData(2);
                s3 = -3;
                ON2  = true;
				ps2.setEnable(ON2);
                mtr.setUserData(s3);
            } else {
                v = true;
                alpha2.resume();
                // sphere.setUserData(-2);
                s3 = 3;
                ON2  = false;
				ps2.setEnable(ON2);
                mtr.setUserData(s3);
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_E)) {
            if (er) {
                alpha3.pause();
                er = false;
                s5 = -5;
                ON3  = true;
				ps3.setEnable(ON3);
                mtr.setUserData(s5);
            } else {
                er = true;
                alpha3.resume();
                s5 = 5;
                ON3  = false;
				ps3.setEnable(ON3);
                mtr.setUserData(s5);
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_M)) {
            if (m) {
                alpha4.pause();
                m = false;
                s7 = -7;
                ON4  = true;
				ps4.setEnable(ON4);
                mtr.setUserData(s7);
            } else {
                m = true;
                alpha4.resume();
                s7 = 7;
                ON4  = false;
				ps4.setEnable(ON4);
                mtr.setUserData(s7);
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_J)) {
            if (j) {
                alpha5.pause();
                j = false;
                s9 = -9;
                ON5  = true;
				ps5.setEnable(ON5);
                mtr.setUserData(s9);
            } else {
                j = true;
                alpha5.resume();
                s9 = 9;
                ON5  = false;
				ps5.setEnable(ON5);
                mtr.setUserData(s9);
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_S)) {
            if (s) {
                alpha6.pause();
                s = false;
                s11 = -11;
                ON6  = true;
				ps6.setEnable(ON6);
                mtr.setUserData(s11);
            } else {
                s = true;
                alpha6.resume();
                s11 = 11;
                ON6  = false;
				ps6.setEnable(ON6);
                mtr.setUserData(s11);
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_U)) {
            if (u) {
                alpha7.pause();
                u = false;
                s13 = -13;
                ON7  = true;
				ps7.setEnable(ON7);
                mtr.setUserData(s13);
            } else {
                u = true;
                alpha7.resume();
                s13 = 13;
                ON7  = false;
				ps7.setEnable(ON7);
                mtr.setUserData(s13);
            }
        }

        if ((e.getKeyCode() == KeyEvent.VK_N)) {
            if (n) {
                alpha8.pause();
                n = false;
                s15 = -15;
                ON8  = true;
				ps8.setEnable(ON8);
                mtr.setUserData(s15);
            } else {
                n = true;
                alpha8.resume();
                s15 = 15;
                ON8  = false;
				ps8.setEnable(ON8);
                mtr.setUserData(s15);
            }
        }
        if ((e.getKeyCode() == KeyEvent.VK_P)) {
            if (p) {
                alpha9.pause();
                p = false;
                s17 = -17;
                ON9  = true;
				ps9.setEnable(ON9);
                mtr.setUserData(s17);
            } else {
                p = true;
                alpha9.resume();
                s17 = 17;
                ON9  = false;
				ps9.setEnable(ON9);
                mtr.setUserData(s17);
            }
        }

        // for rotation of each planet

        if (e.getKeyCode() == KeyEvent.VK_1) {
            if (a1) {
                rotalpha1.pause();
                a1 = false;
                s2 = 2;
                mtr.setUserData(s2);
            } else {
                a1 = true;
                rotalpha1.resume();
                s2 = -2;
                mtr.setUserData(s2);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            if (a2) {
                rotalpha2.pause();
                a2 = false;
            } else {
                a2 = true;
                rotalpha2.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            if (a3) {
                rotalpha3.pause();
                a3 = false;
            } else {
                a3 = true;
                rotalpha3.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            if (a4) {
                rotalpha4.pause();
                a4 = false;
            } else {
                a4 = true;
                rotalpha4.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            if (a5) {
                rotalpha5.pause();
                a5 = false;
            } else {
                a5 = true;
                rotalpha5.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_6) {
            if (a6) {
                rotalpha6.pause();
                a6 = false;
            } else {
                a6 = true;
                rotalpha6.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_7) {
            if (a7) {
                rotalpha7.pause();
                a7 = false;
            } else {
                a7 = true;
                rotalpha7.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_8) {
            if (a8) {
                rotalpha8.pause();
                a8 = false;
            } else {
                a8 = true;
                rotalpha8.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_9) {
            if (a9) {
                rotalpha9.pause();
                a9 = false;
            } else {
                a9 = true;
                rotalpha9.resume();
                s1 = 1;
                mtr.setUserData(s1);
            }
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        int x = event.getX();
        int i = event.getY(); // mouse coordinates
        Point3d point3d = new Point3d(), center = new Point3d();
        canvas.getPixelLocationInImagePlate(x, i, point3d);// obtain AWT pixel in ImagePlate coordinates
        canvas.getCenterEyeInImagePlate(center); // obtain eye's position in IP coordinates

        Transform3D transform3D = new Transform3D(); // matrix to relate ImagePlate coordinates~
        canvas.getImagePlateToVworld(transform3D); // to Virtual World coordinates
        transform3D.transform(point3d); // transform 'point3d' with 'transform3D'
        transform3D.transform(center); // transform 'center' with 'transform3D'

        Vector3d mouseVec = new Vector3d();
        mouseVec.sub(point3d, center);
        mouseVec.normalize();
        pickTool.setShapeRay(point3d, mouseVec); // send a PickRay for intersection

        if (pickTool.pickClosest() != null) {
            PickResult pickResult = pickTool.pickClosest();// obtain the closest hit
            Node mtr = pickResult.getNode(PickResult.SHAPE3D); // originally a PRIMITIVE as a box
            Shape3D pick = (Shape3D) mtr; // cast to Shape3D

            // Mercury
            if ((int) mtr.getUserData() == 1) { // retrieve 'UserData'
                alpha1.resume();
                // alpha2.resume();
                s1 = -1;
                mtr.setUserData(s1); // set 'UserData' to a new value
                y = true;

            } else if ((int) mtr.getUserData() == -1) { // use 'UserData' as flag to switch color
                alpha1.pause();
                s1 = 1;
                mtr.setUserData(s1); // reset 'UserData'
                y = false;

            }

            if ((int) mtr.getUserData() == 2) { // retrieve 'UserData'
                rotalpha1.resume();
                // alpha2.resume();
                s2 = -2;
                mtr.setUserData(s2); // set 'UserData' to a new value
                a1 = true;

            } else if ((int) mtr.getUserData() == -2) { // use 'UserData' as flag to switch color
                rotalpha1.pause();
                // alpha2.pause();
                s2 = 2;
                mtr.setUserData(s2); // reset 'UserData'
                a1 = false;

            }

            // Venus
            if ((int) mtr.getUserData() == 3) { // retrieve 'UserData'
                alpha2.resume();
                // alpha2.resume();
                v = true;
                s3 = -3;
                mtr.setUserData(s3);
            } else if ((int) mtr.getUserData() == -3) { // use 'UserData' as flag to switch color
                alpha2.pause();
                // alpha2.pause();
                s3 = 3;
                mtr.setUserData(s3); // reset 'UserData'
                v = false;

            }

            if ((int) mtr.getUserData() == 4) { // retrieve 'UserData'
                rotalpha2.resume();
                // alpha2.resume();
                s4 = -4;
                mtr.setUserData(s4); // set 'UserData' to a new value
                a2 = true;

            } else if ((int) mtr.getUserData() == -4) { // use 'UserData' as flag to switch color
                rotalpha2.pause();
                // alpha2.pause();
                s4 = 4;
                mtr.setUserData(s4); // reset 'UserData'
                a2 = false;

            }

            // Earth
            if ((int) mtr.getUserData() == 5) { // retrieve 'UserData'
                alpha3.resume();
                s5 = -5;
                // alpha2.resume();
                mtr.setUserData(s5); // set 'UserData' to a new value
                er = true;

            } else if ((int) mtr.getUserData() == -5) { // use 'UserData' as flag to switch color
                alpha3.pause();
                // alpha2.pause();
                s5 = 5;
                mtr.setUserData(s5); // reset 'UserData'
                er = false;

            }

            if ((int) mtr.getUserData() == 6) { // retrieve 'UserData'
                rotalpha3.resume();
                // alpha2.resume();
                s6 = -6;
                mtr.setUserData(s6); // set 'UserData' to a new value
                a3 = true;

            } else if ((int) mtr.getUserData() == -6) { // use 'UserData' as flag to switch color
                rotalpha3.pause();
                // alpha2.pause();
                s6 = 6;
                mtr.setUserData(s6); // reset 'UserData'
                a3 = false;

            }

            // mars
            if ((int) mtr.getUserData() == 7) { // retrieve 'UserData'
                alpha4.resume();
                // alpha2.resume();
                s7 = -7;
                mtr.setUserData(s7); // set 'UserData' to a new value
                m = true;

            } else if ((int) mtr.getUserData() == -7) { // use 'UserData' as flag to switch color
                alpha4.pause();
                // alpha2.pause();
                s7 = 7;
                mtr.setUserData(s7); // reset 'UserData'
                m = false;

            }

            if ((int) mtr.getUserData() == 8) { // retrieve 'UserData'
                rotalpha4.resume();
                // alpha2.resume();
                s8 = -8;
                mtr.setUserData(s8); // set 'UserData' to a new value
                a4 = true;

            } else if ((int) mtr.getUserData() == -8) { // use 'UserData' as flag to switch color
                rotalpha4.pause();
                // alpha2.pause();
                s8 = 8;
                mtr.setUserData(s8); // reset 'UserData'
                a4 = false;

            }

            // Jupiter
            if ((int) mtr.getUserData() == 9) { // retrieve 'UserData'
                alpha5.resume();
                // alpha2.resume();
                s9 = -9;
                mtr.setUserData(s9); // set 'UserData' to a new value
                j = true;

            } else if ((int) mtr.getUserData() == -9) { // use 'UserData' as flag to switch color
                alpha5.pause();
                // alpha2.pause();
                s9 = 9;
                mtr.setUserData(s9); // reset 'UserData'
                j = false;

            }

            if ((int) mtr.getUserData() == 10) { // retrieve 'UserData'
                rotalpha5.resume();
                // alpha2.resume();
                s10 = -10;
                mtr.setUserData(s10); // set 'UserData' to a new value
                a5 = true;

            } else if ((int) mtr.getUserData() == -10) { // use 'UserData' as flag to switch color
                rotalpha5.pause();
                // alpha2.pause();
                s10 = 10;
                mtr.setUserData(s10); // reset 'UserData'
                a5 = false;

            }

            // Saturn
            if ((int) mtr.getUserData() == 11) { // retrieve 'UserData'
                alpha6.resume();
                // alpha2.resume();
                s11 = -11;
                mtr.setUserData(s11); // set 'UserData' to a new value
                s = true;

            } else if ((int) mtr.getUserData() == -11) { // use 'UserData' as flag to switch color
                alpha6.pause();
                // alpha2.pause();
                s11 = 11;
                mtr.setUserData(s11); // reset 'UserData'
                s = false;

            }

            if ((int) mtr.getUserData() == 12) { // retrieve 'UserData'
                rotalpha6.resume();
                // alpha2.resume();
                s12 = -12;
                mtr.setUserData(s12); // set 'UserData' to a new value
                a6 = true;

            } else if ((int) mtr.getUserData() == -12) { // use 'UserData' as flag to switch color
                rotalpha6.pause();
                // alpha2.pause();
                mtr.setUserData(12); // reset 'UserData'
                a6 = false;

            }

            // Uranus
            if ((int) mtr.getUserData() == 13) { // retrieve 'UserData'
                alpha7.resume();
                // alpha2.resume();
                mtr.setUserData(-13); // set 'UserData' to a new value
                u = true;

            } else if ((int) mtr.getUserData() == -13) { // use 'UserData' as flag to switch color
                alpha7.pause();
                // alpha2.pause();
                mtr.setUserData(13); // reset 'UserData'
                u = false;

            }

            if ((int) mtr.getUserData() == 14) { // retrieve 'UserData'
                rotalpha7.resume();
                // alpha2.resume();
                mtr.setUserData(-14); // set 'UserData' to a new value
                a7 = true;

            } else if ((int) mtr.getUserData() == -14) { // use 'UserData' as flag to switch color
                rotalpha7.pause();
                // alpha2.pause();
                mtr.setUserData(14); // reset 'UserData'
                a7 = false;

            }

            // Neptune
            if ((int) mtr.getUserData() == 15) { // retrieve 'UserData'
                alpha8.resume();
                // alpha2.resume();
                mtr.setUserData(-15); // set 'UserData' to a new value
                n = true;

            } else if ((int) mtr.getUserData() == -15) { // use 'UserData' as flag to switch color
                alpha8.pause();
                // alpha2.pause();
                mtr.setUserData(15); // reset 'UserData'
                n = false;

            }

            if ((int) mtr.getUserData() == 16) { // retrieve 'UserData'
                rotalpha8.resume();
                // alpha2.resume();
                mtr.setUserData(-16); // set 'UserData' to a new value
                a8 = true;

            } else if ((int) mtr.getUserData() == -16) { // use 'UserData' as flag to switch color
                rotalpha8.pause();
                // alpha2.pause();
                mtr.setUserData(16); // reset 'UserData'
                a8 = false;

            }

            // Pluto
            if ((int) mtr.getUserData() == 17) { // retrieve 'UserData'
                alpha9.resume();
                // alpha2.resume();
                mtr.setUserData(-17); // set 'UserData' to a new value
                p = true;

            } else if ((int) mtr.getUserData() == -17) { // use 'UserData' as flag to switch color
                alpha9.pause();
                // alpha2.pause();
                mtr.setUserData(17); // reset 'UserData'
                p = false;

            }

            if ((int) mtr.getUserData() == 18) { // retrieve 'UserData'
                rotalpha9.resume();
                // alpha2.resume();
                mtr.setUserData(-18); // set 'UserData' to a new value
                a9 = true;

            } else if ((int) mtr.getUserData() == -18) { // use 'UserData' as flag to switch color
                rotalpha9.pause();
                // alpha2.pause();
                mtr.setUserData(18); // reset 'UserData'
                a9 = false;

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /* for A5: a function to initialize for playing sound */
	 public static void initialSound() {
        soundJOAL = new SoundUtilityJOAL();
        if (!soundJOAL.load("laser2", 0f, 0f, 10f, true))
            System.out.println("Could not load " + "laser2");	
        if (!soundJOAL.load("magic_bells", 0f, 0f, 10f, true))
            System.out.println("Could not load " + "magic_bells");	
        }

    public static void playSound(int key) {
        String snd_pt = "laser2";
        if (key > 1)
            snd_pt = "magic_bells";
        soundJOAL.play(snd_pt);
        try {
            Thread.sleep(500); // sleep for 0.5 secs
        } catch (InterruptedException ex) {}
        soundJOAL.stop(snd_pt);
    }

    public static void addLights(BranchGroup sceneBG, Color3f clr, Point3f point) {
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100000.0);
        AmbientLight amLgt = new AmbientLight(new Color3f(0.2f, 0.2f, 0.2f));
        amLgt.setInfluencingBounds(bounds);
        sceneBG.addChild(amLgt);
        Point3f pt  = new Point3f(point);
        Point3f atn = new Point3f(1.0f, 0.0f, 0.0f);
        PointLight ptLight = new PointLight(clr, pt, atn);
        ptLight.setInfluencingBounds(bounds);
        sceneBG.addChild(ptLight);
    }

        
    
}

