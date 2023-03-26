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
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.*;

public class Assignment3KS extends JPanel {
  
    
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static final int OBJ_NUM = 1;

	public static BranchGroup create_Scene() {
        Transform3D scaler = new Transform3D(); // 4x4 matrix for scaling
		scaler.setScale(new Vector3d(0,0,.25f));
		BranchGroup sceneBG = new BranchGroup();           // create the scene' BranchGroup
		TransformGroup sceneTG = new TransformGroup(); 
		TransformGroup baseTG = new TransformGroup();   // create the scene's TransformGroup


		TransformGroup R1 = new TransformGroup();
		TransformGroup cir  = new TransformGroup();
		TransformGroup R3 = new TransformGroup();
		TransformGroup R4 = new TransformGroup();

		float x = (float) 0.9;

		
		RingObjectsKS sun = new Ring1(CommonsKS.Blue,(float)1,(float)0.0f);           // create the external object		
		RingObjectsKS mercury = new Ring1(CommonsKS.White,(float)1,(float)x);           // create the external object		
		RingObjectsKS venus = new Ring1(CommonsKS.White,(float)1,(float)2);           // create the external object		
		RingObjectsKS earth = new Ring1(CommonsKS.White,(float)1,(float)3);           // create the external object		
		RingObjectsKS mars = new Ring1(CommonsKS.White,(float)1,(float)4);           // create the external object		
		RingObjectsKS jupiter = new Ring1(CommonsKS.White,(float)0.35,(float)5);           // create the external object		
		RingObjectsKS saturn = new export("Saturn",CommonsKS.White,(float)1,(float)0.0,(float)0.0f,(float)6);           // create the external object		
		RingObjectsKS uranus = new Ring1(CommonsKS.White,(float)1,(float)7);           // create the external object		
		RingObjectsKS neptune = new Ring1(CommonsKS.White,(float)1,(float)8);           // create the external object		
		RingObjectsKS pluto = new Ring1(CommonsKS.White,(float)1,(float)9);           // create the external object		
		
		
		TransformGroup sunTG= new TransformGroup();
		TransformGroup mercuryTG = new TransformGroup();
		TransformGroup venusTG = new TransformGroup();
		TransformGroup earthTG = new TransformGroup();
		TransformGroup marsTG = new TransformGroup();
		TransformGroup jupiterTG = new TransformGroup();
		TransformGroup saturnTG = new TransformGroup();
		TransformGroup uranusTG = new TransformGroup();
		TransformGroup neptuneTG = new TransformGroup();
		TransformGroup plutoTG = new TransformGroup();
		
		

	
		sunTG.addChild(sun.position_Object());     
		           
		mercuryTG.addChild(mercury.position_Object());   

		mercuryTG.addChild(CommonsKS.rotate_Behavior(50000,mercuryTG));
		mercuryTG.addChild(CommonsKS.rotating(40000, mercuryTG));             
		venusTG.addChild(venus.position_Object());                // addding child ring1
		venusTG.addChild(CommonsKS.rotate_Behavior(5000,venusTG));

		earthTG.addChild(earth.position_Object());                // addding child ring1	
		earthTG.addChild(CommonsKS.rotate_Behavior(5000,earthTG));

		marsTG.addChild(mars.position_Object());                // addding child ring1	
		marsTG.addChild(CommonsKS.rotate_Behavior(5000,marsTG));

		jupiterTG.addChild(jupiter.position_Object());                // addding child ring1	
		jupiterTG.addChild(CommonsKS.rotate_Behavior(5000,jupiterTG));

		saturnTG.addChild(saturn.position_Object());                // addding child ring1	
		saturnTG.addChild(CommonsKS.rotate_Behavior(5000,saturnTG));

		uranusTG.addChild(uranus.position_Object());                // addding child ring1	
		uranusTG.addChild(CommonsKS.rotate_Behavior(5000,uranusTG));

		neptuneTG.addChild(neptune.position_Object());                // addding child ring1	
		neptuneTG.addChild(CommonsKS.rotate_Behavior(5000,neptuneTG));

		plutoTG.addChild(pluto.position_Object());                // addding child ring1	
		plutoTG.addChild(CommonsKS.rotate_Behavior(5000,plutoTG));

		
		R1.addChild(sunTG);
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

	
	BoundingSphere b = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
		sceneBG.addChild(cir);
		sceneBG.addChild(R1);
		sceneBG.addChild(sceneTG);
		sceneBG.addChild(CommonsKS.add_Lights(CommonsKS.White, 1));	
		sceneBG.addChild(CommonsKS.rotate_Behavior(6000, sceneTG));
		sceneBG.addChild(CommonsKS.create_BK(CommonsKS.Grey, b));		//creating background
		
		return sceneBG;
    }
    /* NOTE: Keep the constructor for each of the labs and assignments */
	public Assignment3KS(BranchGroup sceneBG) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		
		SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse
		CommonsKS.define_Viewer(su, new Point3d(4.0d, 0.0d, 1.0d));
		
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
}
