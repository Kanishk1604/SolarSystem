package codesKS280;
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
		Vector3f[] post = new Vector3f[4];
		post[0] = new Vector3f(.5f,0,0.5f);
		post[1] = new Vector3f(-.5f,0,0.5f);
		post[2] = new Vector3f(-.5f,0,-0.5f);
		post[3] = new Vector3f(.5f,0,-0.5f); 

		RingObjectsKS[] Assign2Shapes = new RingObjectsKS[OBJ_NUM];
		Assign2Shapes[0] = new MyCylinderA2();
		RingObjectsKS[] box = new RingObjectsKS[2];
		box[0] = new BoxA2();
		box[1] = new BoxA2();

		Transform3D boxT3D = new Transform3D();
		boxT3D.rotY(Math.PI/2);
		TransformGroup boxTG = new TransformGroup(boxT3D);
		boxTG.addChild(box[1].position_Object());

		TransformGroup R1 = new TransformGroup();
		TransformGroup R2  = new TransformGroup();
		TransformGroup R3 = new TransformGroup();
		TransformGroup R4 = new TransformGroup();

	
		RingObjectsKS ring1 = new Ring1("small",CommonsKS.Yellow);           // create the external object		
		RingObjectsKS ring2 = new Ring2("med",CommonsKS.Red);           // create the external object		
		RingObjectsKS ring3 = new Ring3("big",CommonsKS.Green);           // create the external object		
		RingObjectsKS ring4 = new Ring4("biggest",CommonsKS.Blue);           // create the external object		
		
		R1.addChild(ring1.position_Object());                // addding child ring1	
		R2.addChild(ring2.position_Object());                // addding child ring2	
		R3.addChild(ring3.position_Object());                // addding child ring3	
		R4.addChild(ring4.position_Object());                // addding child ring4	



		//adding cylinders
		SharedGroup sharedGroup = new SharedGroup();
		sharedGroup.addChild(Assign2Shapes[0].position_Object());
		Link[] link = new Link[4];
		for (int i = 0; i < 4; i++) {
			link[i] = new Link(sharedGroup);
			Transform3D transform = new Transform3D();
			transform.setTranslation(post[i]);
			TransformGroup transformGroup = new TransformGroup(transform);
			transformGroup.addChild(link[i]);
			baseTG.addChild(transformGroup);
		}

		//adding base boxes
		baseTG.addChild(boxTG);
		baseTG.addChild(box[0].position_Object());
		baseTG.addChild(new StringA2("KS's Assignment 3").position_Object());
 

	 	float t3dMat[] = {1,0,0,0, 
						  0,1,0,0, 
						  0,0,0.25f,0, 
						  0,0,0,1};  // Matrix to scale objects by 1/4 on Z axis and translate 2.2 down
		Transform3D t3d = new Transform3D(t3dMat);
		Transform3D translator = new Transform3D();
		translator.setTranslation(new Vector3f(0,-1f,0));
		Transform3D trfm2 = new Transform3D();
		trfm2.mul(t3d, translator);
		baseTG.setTransform(trfm2);
		sceneTG.addChild(baseTG);

		//adding background
		BoundingSphere b = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);

        TransformGroup rr1 = new TransformGroup();
        TransformGroup rr2 = new TransformGroup();
        TransformGroup rr3 = new TransformGroup();

		//adding transform group as a chain
        rr1.addChild(R3);
        rr1.addChild(CommonsKS.rotating(4500,R3,2));
        rr2.addChild(R2);
        rr2.addChild(CommonsKS.rotating(3000,R2,1));
        rr3.addChild(R1);
        rr2.addChild(CommonsKS.rotating(1500,R1,2));
        
       // R1.addChild(rr1);
		R2.addChild(rr3);
		R3.addChild(rr2);
		R4.addChild(rr1);

		sceneTG.addChild(R4);
		sceneBG.addChild(sceneTG);
		sceneBG.addChild(CommonsKS.add_Lights(CommonsKS.White, 1));	
		sceneBG.addChild(CommonsKS.rotate_Behavior(6000, sceneTG));
		sceneBG.addChild(CommonsKS.create_BK(CommonsKS.Grey, b));		//creating background
		sceneBG.addChild(new the3Lines().position_Object()); // adding origin line                        // make 'sceneTG' continuous rotating
		
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
