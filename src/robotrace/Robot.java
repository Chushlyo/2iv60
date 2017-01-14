package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2GL3.GL_QUADS;
import robotrace.Base;
import robotrace.Vector;
import static robotrace.Textures.*;

/**
 * Represents a Robot, to be implemented according to the Assignments.
 */
class Robot  {

    /**
     * The position of the robot.
     */
    public Vector position = new Vector(0, 0, 0);

    /**
     * The direction in which the robot is running.
     */
    public Vector direction = new Vector(1, 0, 0);

    /**
     * The material from which this robot is built.
     */
    private final Material material;

    /**
     * How far we've traversed the track so far
     */
    public double distanceTravelled = 0.0;

    /**
     * How much we deviate from the standard distance traveled.
     */
    public double deviation = 0.0;

    double armXOffset = 0.44;
    double legXOffset = 0.15;

    double upperLegLength = 0.65;
    double lowerLegLength = 0.7;

    double legThickness = 0.27;

    /**
     * Converts a position into an angle and a distance
     *
     * @param pos target position
     * @return angle (x) and distance(y)
     */
    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material) {
        this.material = material;
    }

      public void setPosAndDir(Vector pos, Vector dir){
        this.position = pos;
        this.direction = dir;
    }
      double alpha = 0.0f;
    
       public void setOrientation(double angle){
        this.alpha = angle;
    }
    
    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void drawHead(GL2 gl, GLU glu, GLUT glut) {
        // Neck
        glut.glutSolidCylinder(0.10f, 0.1, 30, 30);
        gl.glColor3f(1f, 1f, 1f);

        // Head
         Textures.head.bind(gl);
        gl.glColor4d(1, 0, 0, 0);
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL_QUAD_STRIP);

        //Face
        //Front
        //Specify the x,y and z coordinates of the new current normal.
        //The initial value of the current normal is the unit vector, (0, 0, 1).
        gl.glNormal3d(1, 0, 0);
        //set the current texture coordinates
        gl.glTexCoord2d(0, 0);
        //first vertex (right down)
        gl.glVertex3d(-0.20, 0.10, 0.1);
        gl.glTexCoord2d(1, 0);
        // left down 
        gl.glVertex3d(0.20, 0.10, 0.1);
        gl.glTexCoord2d(0, 1);
        // top right
        gl.glVertex3d(-0.20, 0.10, 0.5);
        gl.glTexCoord2d(1, 1);
        //top left
        gl.glVertex3d(0.20, 0.10, 0.5);
        //glut.glutSolidSphere(0.10f, 0.1, 30, 30);
        gl.glColor4f(0, 0, 0, 1);

        // Top
        gl.glNormal3d(0, 0, 1);
        gl.glVertex3d(-0.20, -0.10, 0.5);
        gl.glVertex3d(0.20, -0.10, 0.5);

        // Back
        gl.glNormal3d(0, -1, 0);
        gl.glVertex3d(-0.20, -0.10, 0.1);
        gl.glVertex3d(0.20, -0.10, 0.1);
        gl.glEnd();

        gl.glBegin(GL_QUAD_STRIP);

        // Left
        gl.glNormal3d(-1, 0, 0);
        gl.glVertex3d(-0.20, -0.10, 0.5);
        gl.glVertex3d(-0.20, 0.10, 0.5);
        gl.glVertex3d(-0.20, -0.10, 0.1);
        gl.glVertex3d(-0.20, 0.10, 0.1);

        // Bottom
        gl.glNormal3d(0, 0, -1);
        gl.glVertex3d(0.20, -0.10, 0.1);
        gl.glVertex3d(0.20, 0.10, 0.1);

        // Right
        gl.glNormal3d(1, 0, 0);
        gl.glVertex3d(0.20, -0.10, 0.5);
        gl.glVertex3d(0.20, 0.10, 0.5);
        gl.glEnd();

        // And reset the color
        gl.glColor4f(0, 0, 0, 1);

    }

    public void drawArm(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();

        //Arm
        gl.glTranslated(0.44, 0.0, 0.0);
        gl.glRotatef(-15, 0.0f, 1.0f, 0.0f); //Points slightly to the right and upwards
        glut.glutSolidSphere(0.16, 30, 30);
        gl.glRotated(50 * Math.sin(tAnim * 5), 1.0, 0.0, 0.0);
        gl.glTranslatef(0.0f, 0.0f, -0.3f); // Go down a bit
        gl.glScalef(0.2f, 0.2f, 0.7f); // Scale the cubes to become long
        glut.glutSolidCube(1.0f);
        glut.glutSolidCylinder(0.5f, 0.1, 30, 30);

        //Palm
        gl.glTranslatef(0.0f, 0.0f, -0.5f);
        glut.glutSolidCylinder(0.7f, 0.1, 30, 30);
        gl.glTranslatef(0.3f, 0.0f, 0.0f);
        glut.glutSolidSphere(0.25, 30, 30);
        gl.glTranslatef(-0.6f, 0.0f, 0.0f);
        glut.glutSolidSphere(0.25, 30, 30);
        gl.glPopMatrix();
    }

    public void drawLeg(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();

        //Leg
        gl.glTranslated(0.2, 0.0, -1.0);
       // gl.glRotatef(-15, 0.0f, 1.0f, 0.0f); //Points slightly to the right and upwards
       // glut.glutSolidSphere(0.16, 30, 30);
        gl.glRotated(50 * Math.sin(tAnim * 5), 1.0, 0.0, 0.0);
        //gl.glTranslatef(0.0f, 0.0f, -0.3f); // Go down a bit
        gl.glScalef(0.2f, 0.2f, 0.7f); // Scale the cubes to become long
        glut.glutSolidCube(1.0f);
       

        //Feet
        gl.glTranslatef(0.0f, 0.0f, -0.5f);
        glut.glutSolidCylinder(0.9f, 0.3, 30, 30);
      
        gl.glPopMatrix();
    }

    public void drawTorso(GL2 gl, GLU glu, GLUT glut) {
        //Torso
        gl.glPushMatrix();

        //Body
        gl.glTranslatef(0.0f, 0.0f, -0.35f);
        gl.glScalef(0.620f, 0.425f, 0.8f);
        glut.glutSolidCube(1.0f);
        gl.glEnable(GL_TEXTURE_2D);
        Textures.brick.bind(gl);
        //torso.setTexParameteri(gl, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        //torso.setTexParameteri(gl, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        //Cap
        gl.glColor3f(1, 0, 0);
        gl.glTranslatef(0.0f, 0.5f, 0.0f);
        gl.glScalef(1.2f, 0.2f, 1.0f);
        glut.glutSolidCube(1.0f);
        gl.glEnable(GL_TEXTURE_2D);

        // Cape
        gl.glColor3f(1, 0, 1);
        gl.glBegin(GL_QUADS);
        gl.glNormal3f(0, 0.27f, 0.57f);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(-0.3f, -4.80f, 0.45f);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(-1f, -5.80f, -1f);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(1f, -5.80f, -1f);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(0.3f, -4.80f, 0.45f);
        gl.glEnd();
        gl.glDisable(GL_TEXTURE_2D);
        gl.glPopMatrix();

    }
    //  tAnim;         // Time since start of animation in seconds.
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
         GlobalState gs;
         gl.glEnable(GL_TEXTURE_2D);
        drawHead(gl, glu, glut);

        gl.glPushMatrix();
        drawArm(gl, glu, glut, tAnim);
        gl.glScalef(-1.0f, 1.0f, 1.0f);
        drawArm(gl, glu, glut, tAnim - (float) Math.PI);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        drawLeg(gl, glu, glut, tAnim);
        gl.glScalef(-1.0f, 1.0f, 1.0f);
        drawLeg(gl, glu, glut, tAnim - (float) Math.PI);
        
        gl.glPopMatrix();
         
        drawTorso(gl, glu, glut);

    }

}
