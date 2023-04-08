/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

package solar;

import java.util.Iterator;

import org.jogamp.java3d.Appearance;
import org.jogamp.java3d.Behavior;
import org.jogamp.java3d.ColoringAttributes;
import org.jogamp.java3d.Shape3D;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.java3d.WakeupCriterion;
import org.jogamp.java3d.WakeupOnCollisionEntry;
import org.jogamp.java3d.WakeupOnCollisionExit;
import org.jogamp.vecmath.Color3f;
import org.jogamp.vecmath.Vector3d;

/* This behavior of collision detection highlights the
    object when it is in a state of collision. */
public class CollisionDetectShapes extends Behavior {
	private boolean inCollision;
	private Shape3D shape;
	private ColoringAttributes shapeColoring;
	private Appearance shapeAppearance;
	private WakeupOnCollisionEntry wEnter;
	private WakeupOnCollisionExit wExit;
	 private static SoundUtilityJOAL sound1;               
	 private static TransformGroup t =null;
	public CollisionDetectShapes(Shape3D s,SoundUtilityJOAL ss) {
		shape = s; // save the original color of 'shape"
		shapeAppearance = shape.getAppearance();
		shapeColoring = shapeAppearance.getColoringAttributes();
		inCollision = false;
		sound1 = ss;
	}
	public static void playSound() {
        String snd_pt = "crash";
         
        sound1.play(snd_pt);
        try {
            Thread.sleep(2500); // sleep for 0.5 secs
        } catch (InterruptedException ex) {}
        sound1.stop(snd_pt);
    }
	public Boolean getCr() {
        return cr;
    }

    public void setCr(Boolean cr) {
        this.cr = cr;
    }
    public Boolean cr = false;

	@Override
	public void initialize() { // USE_GEOMETRY USE_BOUNDS
		wEnter = new WakeupOnCollisionEntry(shape, WakeupOnCollisionEntry.USE_GEOMETRY);
		wExit = new WakeupOnCollisionExit(shape, WakeupOnCollisionExit.USE_GEOMETRY);
		wakeupOn(wEnter); // initialize the behavior
	}

	@Override
	public void processStimulus(Iterator<WakeupCriterion> criteria) {
		Color3f hilightClr = CommonsKS.Green;
		Color3f hilight = CommonsKS.Black;
		ColoringAttributes highlight = new ColoringAttributes(hilightClr, ColoringAttributes.SHADE_GOURAUD);
		ColoringAttributes highlight2 = new ColoringAttributes(hilight, ColoringAttributes.SHADE_GOURAUD);

        // sound1 = new SoundUtilityJOAL();
		inCollision = !inCollision; // collision has taken place

		if (inCollision) { // change color to highlight 'shape'
			shapeAppearance.setColoringAttributes(highlight);
			// playSound();
			cr = true;
			wakeupOn(wExit); // keep the color until no collision
		 } 
		 else { // change color back to its original
			// shapeAppearance.setColoringAttributes(shapeColoring);
			// wakeupOn(wEnter); // wait for collision happens
			playSound();

			shapeAppearance.setColoringAttributes(highlight2);

		}
	}
}
