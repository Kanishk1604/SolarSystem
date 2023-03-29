/* Copyright material for students taking COMP-2800 to work on assignment/labs/projects. */

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
import java.util.Iterator;

import org.jdesktop.j3d.examples.sound.PointSoundBehavior;
import org.jdesktop.j3d.examples.sound.audio.JOALMixer;
import java.awt.event.KeyEvent;

/* This behavior of collision detection highlights the
    object when it is in a state of collision. */
public class CollisionDetectShapes extends Behavior {
	private boolean inCollision;
	private Shape3D shape;
	private ColoringAttributes shapeColoring;
	private Appearance shapeAppearance;
	private WakeupOnCollisionEntry wEnter;
	private WakeupOnCollisionExit wExit;
	private boolean ON;
	private static TransformGroup t;
	private static Transform3D trf;
    public static Boolean cr = false;

	// private static PointSound ps;
	public Boolean getCr() {
        return cr;
    }

    public void setCr(Boolean cr) {
        this.cr = cr;
    }

	public CollisionDetectShapes(Shape3D s) {
		shape = s; // save the original color of 'shape"
		shapeAppearance = shape.getAppearance();
		// this.ON = ON;
		shapeColoring = shapeAppearance.getColoringAttributes();
		inCollision = false;

	}

	@Override
	public void initialize() { // USE_GEOMETRY USE_BOUNDS
		wEnter = new WakeupOnCollisionEntry(shape, WakeupOnCollisionEntry.USE_BOUNDS);
		wExit = new WakeupOnCollisionExit(shape, WakeupOnCollisionExit.USE_BOUNDS);
		wakeupOn(wEnter); // initialize the behavior
	}

	@Override
	public void processStimulus(Iterator<WakeupCriterion> criteria) {
		Color3f hilightClr = CommonsKS.Black;
		ColoringAttributes highlight = new ColoringAttributes(hilightClr, ColoringAttributes.SHADE_GOURAUD);
		inCollision = !inCollision; // collision has taken place

		if (inCollision) { // change color to highlight 'shape'
			shapeAppearance.setColoringAttributes(highlight);
			cr = true;
			wakeupOn(wExit); // keep the color until no collision
		} else { // change color back to its original
			shapeAppearance.setColoringAttributes(shapeColoring);
			wakeupOn(wEnter); // wait for collision happens
		}
	}
}
