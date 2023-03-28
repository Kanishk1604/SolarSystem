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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Assignment3KS extends JPanel implements KeyListener,MouseListener {
  
    
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
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
	

	private static RingObjectsKS[] Object3D = new RingObjectsKS[27];

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

	public static int s1 = -1;

    private static PickTool pickTool;
    private Canvas3D canvas;
	private static TransformGroup R1;

	private static Sphere mtr;
	public static Alpha get_Alpha() { return alpha1; };    // NOTE: keep for future use 

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
        uranus = new export("Uranus", CommonsKS.White, (float) 1.5, (float) 0.0, (float) 0.0f, (float) 8); // create
        neptune = new Neptune(CommonsKS.White, (float) 2.3, (float) 8); // create the external object
        pluto = new Pluto(CommonsKS.White, (float) 0.7, (float) 9); // create the external object

        Object3D[9] = new Meteor("meteor1", s1,CommonsKS.Grey,0f,0.26f,0.9f);
        Object3D[10] = new Meteor("meteor2", -2,CommonsKS.Grey,0f,0.26f,1.1f);

        Object3D[11] = new Meteor("meteor3",-3, CommonsKS.Grey,0f,0.56f,1.7f);
        Object3D[12] = new Meteor("meteor4",-4, CommonsKS.Grey,0f,0.56f,1.9f);
        
		Object3D[13] = new Meteor("meteor5",-5, CommonsKS.Grey,0f,0.86f,2.5f);
        Object3D[14] = new Meteor("meteor6",-6, CommonsKS.Grey,0f,0.86f,2.7f);

        Object3D[15] = new Meteor("meteor7",-7, CommonsKS.Grey,0f,1.16f,3.5f);
        Object3D[16] = new Meteor("meteor8",-8 ,CommonsKS.Grey,0f,1.16f,3.7f);

        Object3D[17] = new Meteor("meteor9",-9, CommonsKS.Grey,0f,1.46f,4.3f);
        Object3D[18] = new Meteor("meteor10",-10 ,CommonsKS.Grey,0f,1.46f,4.5f);
		
        Object3D[19] = new Meteor("meteor11",-11, CommonsKS.Grey,0f,1.76f,5.3f);
        Object3D[20] = new Meteor("meteor12",-12, CommonsKS.Grey,0f,1.76f,5.5f);
		
        Object3D[21] = new Meteor("meteor11",-13, CommonsKS.Grey,0f,2.06f,6.3f);
        Object3D[22] = new Meteor("meteor12",-14, CommonsKS.Grey,0f,2.06f,6.5f);

        Object3D[23] = new Meteor("meteor11",-15, CommonsKS.Grey,0f,2.36f,7.3f);
        Object3D[24] = new Meteor("meteor12",-16, CommonsKS.Grey,0f,2.36f,7.5f);		

        Object3D[25] = new Meteor("meteor11",-17, CommonsKS.Grey,0f,2.66f,8.7f);
        Object3D[26] = new Meteor("meteor12",-18, CommonsKS.Grey,0f,2.66f,8.9f);	
		TransformGroup met = new TransformGroup();
		
		 //meteors
		for(int i=9;i<27;i++) {
			met.addChild(Object3D[i].position_Object());
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


		alpha1 = new Alpha(-1, 3500);
        alpha2 = new Alpha(-1, 6000);
        alpha3 = new Alpha(-1, 9000);
        alpha4 = new Alpha(-1, 11000);
        alpha5 = new Alpha(-1, 20000);
        alpha6 = new Alpha(-1, 25000);
        alpha7 = new Alpha(-1, 31000);
        alpha8 = new Alpha(-1, 42000);
        alpha9 = new Alpha(-1, 51000);

		rotalpha1 = new Alpha(-1, 1500);
        rotalpha2 = new Alpha(-1, 3000);
        rotalpha3 = new Alpha(-1, 4500);
        rotalpha4 = new Alpha(-1, 5000);
        rotalpha5 = new Alpha(-1, 10500);
        rotalpha6 = new Alpha(-1, 11000);
        rotalpha7 = new Alpha(-1, 17000);
        rotalpha8 = new Alpha(-1, 21000);
        rotalpha9 = new Alpha(-1, 25000);


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

		uranus = new export("Uranus", CommonsKS.White, (float) 1.4, (float) 0.0, (float) 0.0f, (float) 8); // create
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
		sceneBG.addChild(CommonsKS.add_Lights(CommonsKS.White, 1));	
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

		SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
		CommonsKS.define_Viewer(su, new Point3d(4.5d, 0.0d, 1.0d));
		// CommonsKS.define_Viewer(su, new Point3d(0.0d, 0.0d, 0.0d));
		
		sceneBG.addChild(CommonsKS.key_Navigation(su));     // allow key navigation
		sceneBG.compile();		                           // optimize the BranchGroup
		su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

		setLayout(new BorderLayout());
		add("Center", canvas);
		frame.setSize(800, 800);                           // set the size of the JFrame
		frame.setVisible(true);
	}

    public static void main(String[] args) {
		frame = new JFrame("KS's Assignment3");                   // NOTE: change XY to student's initials
		frame.getContentPane().add(new Assignment3KS(create_Scene()));  // create an instance of the class
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

				//Interaction using keyboard
	@Override
	public void keyPressed(KeyEvent e) {

		//For revolution of planets
		if ((e.getKeyCode() == KeyEvent.VK_Y)){
			if (y) {
				alpha1.pause();
				y = false;
				s1 = -1;
				mtr.setUserData(s1);                        // reset 'UserData'
				}
			else {
				y = true;
				alpha1.resume();
				s1 = 1;
				mtr.setUserData(s1);   
			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_V)){
			if (v) {
				alpha2.pause();
				v = false;
				// sphere.setUserData(2);

				}
			else {
				v = true;
				alpha2.resume();
				// sphere.setUserData(-2);

			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_E)){
			if (er) {
				alpha3.pause();
				er = false;

				}
			else {
				er = true;
				alpha3.resume();

			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_M)){
			if (m) {
				alpha4.pause();
				m = false;

				}
			else {
				m = true;
				alpha4.resume();

			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_J)){
			if (j) {
				alpha5.pause();
				m = false;

				}
			else {
				j = true;
				alpha5.resume();

			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_S)){
			if (s) {
				alpha6.pause();
				s = false;

				}
			else {
				s = true;
				alpha6.resume();

			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_U)){
			if (u) {
				alpha7.pause();
				u = false;

				}
			else {
				u = true;
				alpha7.resume();

			}
		}


		if ((e.getKeyCode() == KeyEvent.VK_N)){
			if (n) {
				alpha8.pause();
				n = false;

				}
			else {
				n = true;
				alpha8.resume();

			}
		}
		if ((e.getKeyCode() == KeyEvent.VK_P)){
			if (p) {
				alpha9.pause();
				p = false;

				}
			else {
				p = true;
				alpha9.resume();

			}
		}

		//for rotation of each planet

		if(e.getKeyCode() == KeyEvent.VK_1){
			if (a1) {
				rotalpha1.pause();
				a1 = false;
			}	
			else{
				a1 =true;
				rotalpha1.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_2){
			if (a2) {
				rotalpha2.pause();
				a2 = false;
			}	
			else{
				a2 =true;
				rotalpha2.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_3){
			if (a3) {
				rotalpha3.pause();
				a3 = false;
			}	
			else{
				a3 =true;
				rotalpha3.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_4){
			if (a4) {
				rotalpha4.pause();
				a4 = false;
			}	
			else{
				a4 =true;
				rotalpha4.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_5){
			if (a5) {
				rotalpha5.pause();
				a5 = false;
			}	
			else{
				a5 =true;
				rotalpha5.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_6){
			if (a6) {
				rotalpha6.pause();
				a6 = false;
			}	
			else{
				a6 =true;
				rotalpha6.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_7){
			if (a7) {
				rotalpha7.pause();
				a7 = false;
			}	
			else{
				a7 =true;
				rotalpha7.resume();

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_8){
			if (a8) {
				rotalpha8.pause();
				a8 = false;
			}	
			else{
				a8 =true;
				rotalpha8.resume();

			}
		} 
		if(e.getKeyCode() == KeyEvent.VK_9){
			if (a9) {
				rotalpha9.pause();
				a9 = false;
			}	
			else{
				a9 =true;
				rotalpha9.resume();

			}
		}

	}
		
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}



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


			//Mercury
			if ((int) mtr.getUserData() == 1) {            // retrieve 'UserData'
				alpha1.resume();
				//alpha2.resume();
				s1 = -1;
				mtr.setUserData(s1);                        // set 'UserData' to a new value
				y = true;	

			}
			else if((int) mtr.getUserData() == -1){                                         // use 'UserData' as flag to switch color
				alpha1.pause();  
				s1 = 1; 
				mtr.setUserData(s1);                        // reset 'UserData'
				y = false;

			}

			if ((int) mtr.getUserData() == 2) {            // retrieve 'UserData'
				rotalpha1.resume();
				//alpha2.resume();
				mtr.setUserData(-2);                        // set 'UserData' to a new value
				a1 = true;	

			}
			else if((int) mtr.getUserData() == -2){                                         // use 'UserData' as flag to switch color
				rotalpha1.pause();
				//alpha2.pause();
				mtr.setUserData(2);                        // reset 'UserData'
				a1 = false;
				
			}

			//Venus
			if ((int) mtr.getUserData() == 3) {            // retrieve 'UserData'
				alpha2.resume();
				//alpha2.resume();
				mtr.setUserData(-3);                        // set 'UserData' to a new value
				v = true;	

			}
			else if((int) mtr.getUserData() == -3){                                         // use 'UserData' as flag to switch color
				alpha2.pause();
				//alpha2.pause();
				mtr.setUserData(3);                        // reset 'UserData'
				v = false;

			}

			if ((int) mtr.getUserData() == 4) {            // retrieve 'UserData'
				rotalpha2.resume();
				//alpha2.resume();
				mtr.setUserData(-4);                        // set 'UserData' to a new value
				a2 = true;	

			}
			else if((int) mtr.getUserData() == -4){                                         // use 'UserData' as flag to switch color
				rotalpha2.pause();
				//alpha2.pause();
				mtr.setUserData(4);                        // reset 'UserData'
				a2 = false;
				
			}

			//Earth
			if ((int) mtr.getUserData() == 5) {            // retrieve 'UserData'
				alpha3.resume();
				//alpha2.resume();
				mtr.setUserData(-5);                        // set 'UserData' to a new value
				er = true;	

			}
			else if((int) mtr.getUserData() == -5){                                         // use 'UserData' as flag to switch color
				alpha3.pause();
				//alpha2.pause();
				mtr.setUserData(5);                        // reset 'UserData'
				er = false;

			}

			if ((int) mtr.getUserData() == 6) {            // retrieve 'UserData'
				rotalpha3.resume();
				//alpha2.resume();
				mtr.setUserData(-6);                        // set 'UserData' to a new value
				a3 = true;	

			}
			else if((int) mtr.getUserData() == -6){                                         // use 'UserData' as flag to switch color
				rotalpha3.pause();
				//alpha2.pause();
				mtr.setUserData(6);                        // reset 'UserData'
				a3 = false;
				
			}

			//mars
			if ((int) mtr.getUserData() == 7) {            // retrieve 'UserData'
				alpha4.resume();
				//alpha2.resume();
				mtr.setUserData(-7);                        // set 'UserData' to a new value
				m = true;	

			}
			else if((int) mtr.getUserData() == -7){                                         // use 'UserData' as flag to switch color
				alpha4.pause();
				//alpha2.pause();
				mtr.setUserData(7);                        // reset 'UserData'
				m = false;

			}

			if ((int) mtr.getUserData() == 8) {            // retrieve 'UserData'
				rotalpha4.resume();
				//alpha2.resume();
				mtr.setUserData(-8);                        // set 'UserData' to a new value
				a4 = true;	

			}
			else if((int) mtr.getUserData() == -8){                                         // use 'UserData' as flag to switch color
				rotalpha4.pause();
				//alpha2.pause();
				mtr.setUserData(8);                        // reset 'UserData'
				a4 = false;
				
			}

			//Jupiter
			if ((int) mtr.getUserData() == 9) {            // retrieve 'UserData'
				alpha5.resume();
				//alpha2.resume();
				mtr.setUserData(-9);                        // set 'UserData' to a new value
				j = true;	

			}
			else if((int) mtr.getUserData() == -9){                                         // use 'UserData' as flag to switch color
				alpha5.pause();
				//alpha2.pause();
				mtr.setUserData(9);                        // reset 'UserData'
				j = false;

			}

			if ((int) mtr.getUserData() == 10) {            // retrieve 'UserData'
				rotalpha5.resume();
				//alpha2.resume();
				mtr.setUserData(-10);                        // set 'UserData' to a new value
				a5 = true;	

			}
			else if((int) mtr.getUserData() == -10){                                         // use 'UserData' as flag to switch color
				rotalpha5.pause();
				//alpha2.pause();
				mtr.setUserData(10);                        // reset 'UserData'
				a5 = false;
				
			}

			//Saturn
			if ((int) mtr.getUserData() == 11) {            // retrieve 'UserData'
				alpha6.resume();
				//alpha2.resume();
				mtr.setUserData(-11);                        // set 'UserData' to a new value
				s = true;	

			}
			else if((int) mtr.getUserData() == -11){                                         // use 'UserData' as flag to switch color
				alpha6.pause();
				//alpha2.pause();
				mtr.setUserData(11);                        // reset 'UserData'
				s = false;

			}

			if ((int) mtr.getUserData() == 12) {            // retrieve 'UserData'
				rotalpha6.resume();
				//alpha2.resume();
				mtr.setUserData(-12);                        // set 'UserData' to a new value
				a6 = true;	

			}
			else if((int) mtr.getUserData() == -12){                                         // use 'UserData' as flag to switch color
				rotalpha6.pause();
				//alpha2.pause();
				mtr.setUserData(12);                        // reset 'UserData'
				a6 = false;
				
			}

			//Uranus
			if ((int) mtr.getUserData() == 13) {            // retrieve 'UserData'
				alpha7.resume();
				//alpha2.resume();
				mtr.setUserData(-13);                        // set 'UserData' to a new value
				u = true;	

			}
			else if((int) mtr.getUserData() == -13){                                         // use 'UserData' as flag to switch color
				alpha7.pause();
				//alpha2.pause();
				mtr.setUserData(13);                        // reset 'UserData'
				u = false;

			}

			if ((int) mtr.getUserData() == 14) {            // retrieve 'UserData'
				rotalpha7.resume();
				//alpha2.resume();
				mtr.setUserData(-14);                        // set 'UserData' to a new value
				a7 = true;	

			}
			else if((int) mtr.getUserData() == -14){                                         // use 'UserData' as flag to switch color
				rotalpha7.pause();
				//alpha2.pause();
				mtr.setUserData(14);                        // reset 'UserData'
				a7 = false;
				
			}

			//Neptune
			if ((int) mtr.getUserData() == 15) {            // retrieve 'UserData'
				alpha8.resume();
				//alpha2.resume();
				mtr.setUserData(-15);                        // set 'UserData' to a new value
				n = true;	

			}
			else if((int) mtr.getUserData() == -15){                                         // use 'UserData' as flag to switch color
				alpha8.pause();
				//alpha2.pause();
				mtr.setUserData(15);                        // reset 'UserData'
				n = false;

			}

			if ((int) mtr.getUserData() == 16) {            // retrieve 'UserData'
				rotalpha8.resume();
				//alpha2.resume();
				mtr.setUserData(-16);                        // set 'UserData' to a new value
				a8 = true;	

			}
			else if((int) mtr.getUserData() == -16){                                         // use 'UserData' as flag to switch color
				rotalpha8.pause();
				//alpha2.pause();
				mtr.setUserData(16);                        // reset 'UserData'
				a8 = false;
				
			}

			//Pluto
			if ((int) mtr.getUserData() == 17) {            // retrieve 'UserData'
				alpha9.resume();
				//alpha2.resume();
				mtr.setUserData(-17);                        // set 'UserData' to a new value
				p = true;	

			}
			else if((int) mtr.getUserData() == -17){                                         // use 'UserData' as flag to switch color
				alpha9.pause();
				//alpha2.pause();
				mtr.setUserData(17);                        // reset 'UserData'
				p = false;

			}

			if ((int) mtr.getUserData() == 18) {            // retrieve 'UserData'
				rotalpha9.resume();
				//alpha2.resume();
				mtr.setUserData(-18);                        // set 'UserData' to a new value
				a9 = true;	

			}
			else if((int) mtr.getUserData() == -18){                                         // use 'UserData' as flag to switch color
				rotalpha9.pause();
				//alpha2.pause();
				mtr.setUserData(18);                        // reset 'UserData'
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
	
}
