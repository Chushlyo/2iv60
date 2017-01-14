package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import static javax.media.opengl.GL.GL_CW;
import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import static javax.media.opengl.GL2.*;

/**
 * Implementation of a race track that is made from Bezier segments.
 */
abstract class RaceTrack {
    
    /** The width of one lane. The total width of the track is 4 * laneWidth. */
    private final static float laneWidth = 1.22f;
    
    
    
    /**
     * Constructor for the default track.
     */
    public RaceTrack() {
    }


    
    /**
     * Draws this track, based on the control points.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        double du = 1.0/100.0;
        double u=0;
        float scaler = laneWidth;
        
        //drawLine1-------------------------------------------------------------
        gl.glFrontFace(GL_CW);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(1f, 1f, 0f);
        
        //first 2 points---------------
        Vector firstPoint = this.getPoint(u);
        Vector firstTangent = this.getTangent(u);
        Vector firstNormal = new Vector(firstTangent.y(), firstTangent.x(), 0);
        firstNormal = firstNormal.normalized();
        Vector w = new Vector(firstPoint.x, firstPoint.y, firstPoint.z);
        double scalar = (w.length()+scaler)/w.length();
        w = w.scale(scalar);
        
        gl.glVertex3f( (float) ( firstPoint.x() ), (float) ( firstPoint.y()), 1.0f);
        gl.glVertex3f( (float) ( w.x() ), (float) ( w.y() ), 1.0f);
        
        for(int i=1;i<101;i++) {
            u = i*du;
            Vector pointTemp = this.getPoint(u);
            Vector tangentTemp = this.getTangent(u);
            Vector normalTemp = new Vector(firstTangent.y(), firstTangent.x(), 0);
            normalTemp = normalTemp.normalized();
            Vector ww = new Vector(pointTemp.x, pointTemp.y, pointTemp.z);
            double scalarw = (ww.length()+scaler)/ww.length();
            ww = ww.scale(scalarw);
            
            gl.glVertex3f( (float) ( pointTemp.x()), (float) ( pointTemp.y()), 1.0f);
            gl.glVertex3f( (float) ( ww.x()), (float) ( ww.y() ), 1.0f);
        }
        gl.glEnd();
        
        //draw Line 1 fundamentals
        gl.glFrontFace(GL_CW);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(0f, 0f, 0f);
        firstPoint = this.getPoint(u);
         gl.glVertex3f( (float) ( firstPoint.x() ), (float) ( firstPoint.y()), 1.0f);
          gl.glVertex3f( (float) ( firstPoint.x() ), (float) ( firstPoint.y()), 0f);
          for(int i=1;i<101;i++) {
            u = i*du;
            Vector pointTemp = this.getPoint(u);
            gl.glVertex3f( (float) ( pointTemp.x()), (float) ( pointTemp.y()), 1.0f);
            gl.glVertex3f( (float) ( pointTemp.x()), (float) ( pointTemp.y()), 0f);
          }
          gl.glEnd();
            
            
        //draw Line 2
        gl.glFrontFace(GL_CW);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(1f, 0f, 1f);
        
        firstPoint = this.getPoint(u);
        firstTangent = this.getTangent(u);
        firstNormal = new Vector(firstTangent.y(), firstTangent.x(), 0);
        firstNormal = firstNormal.normalized();
        firstPoint = firstPoint.scale(scalar);
        w = new Vector(firstPoint.x, firstPoint.y, firstPoint.z);
        scalar = (w.length()+laneWidth)/w.length();
        w = w.scale(scalar);

        gl.glVertex3f( (float) ( firstPoint.x() ), (float) ( firstPoint.y()), 1.0f);
        gl.glVertex3f( (float) ( w.x() ), (float) ( w.y() ), 1.0f);
        
        for(int i=1;i<101;i++) {
            u = i*du;
            Vector pointTemp = this.getPoint(u);
            Vector tangentTemp = this.getTangent(u);
            Vector normalTemp = new Vector(firstTangent.y(), firstTangent.x(), 0);
            normalTemp = normalTemp.normalized();
            double scalarw2 = (pointTemp.length()+laneWidth)/pointTemp.length();
            pointTemp = pointTemp.scale(scalarw2);
            Vector ww = new Vector(pointTemp.x, pointTemp.y, pointTemp.z);
            double scalarw = (ww.length()+laneWidth)/ww.length();
            ww = ww.scale(scalarw);
            
            gl.glVertex3f( (float) ( pointTemp.x()), (float) ( pointTemp.y()), 1.0f);
            gl.glVertex3f( (float) ( ww.x()), (float) ( ww.y() ), 1.0f);
        }

        gl.glEnd();
        
        //draw Line 3
        gl.glFrontFace(GL_CW);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(1f, 0.7f, 0.3f);
        
        firstPoint = this.getPoint(u);
        firstTangent = this.getTangent(u);
        firstNormal = new Vector(firstTangent.y(), firstTangent.x(), 0);
        firstNormal = firstNormal.normalized();
        scalar = (firstPoint.length() + 2* laneWidth)/firstPoint.length();
        firstPoint = firstPoint.scale(scalar);
        w = new Vector(firstPoint.x, firstPoint.y, firstPoint.z);
        scalar = (w.length()+laneWidth)/w.length();
        w = w.scale(scalar);

        gl.glVertex3f( (float) ( firstPoint.x() ), (float) ( firstPoint.y()), 1.0f);
        gl.glVertex3f( (float) ( w.x()), (float) ( w.y() ), 1.0f);
        
        for(int i=1;i<101;i++) {
            u = i*du;
            Vector pointTemp = this.getPoint(u);
            Vector tangentTemp = this.getTangent(u);
            Vector normalTemp = new Vector(firstTangent.y(), firstTangent.x(), 0);
            normalTemp = normalTemp.normalized();
            double scalarw2 = (pointTemp.length()+2*laneWidth)/pointTemp.length();
            pointTemp = pointTemp.scale(scalarw2);
            Vector ww = new Vector(pointTemp.x, pointTemp.y, pointTemp.z);
            double scalarw = (ww.length()+laneWidth)/ww.length();
            ww = ww.scale(scalarw);
            
            gl.glVertex3f( (float) ( pointTemp.x()), (float) ( pointTemp.y()), 1.0f);
            gl.glVertex3f( (float) ( ww.x()), (float) ( ww.y() ), 1.0f);
        }

        gl.glEnd();
        
        //draw Line 4
        gl.glFrontFace(GL_CW);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(0f, 1f, 1f);
        
        firstPoint = this.getPoint(u);
        firstTangent = this.getTangent(u);
        firstNormal = new Vector(firstTangent.y(), firstTangent.x(), 0);
        firstNormal = firstNormal.normalized();
        scalar = (firstPoint.length() + 3* laneWidth)/firstPoint.length();
        firstPoint = firstPoint.scale(scalar);
        w = new Vector(firstPoint.x, firstPoint.y, firstPoint.z);
        scalar = (w.length()+laneWidth)/w.length();
        w = w.scale(scalar);

        gl.glVertex3f( (float) ( firstPoint.x() ), (float) ( firstPoint.y()), 1.0f);
        gl.glVertex3f( (float) ( w.x()), (float) ( w.y() ), 1.0f);
        
        for(int i=1;i<101;i++) {
            u = i*du;
            Vector pointTemp = this.getPoint(u);
            Vector tangentTemp = this.getTangent(u);
            Vector normalTemp = new Vector(firstTangent.y(), firstTangent.x(), 0);
            normalTemp = normalTemp.normalized();
            double scalarw2 = (pointTemp.length()+3*laneWidth)/pointTemp.length();
            pointTemp = pointTemp.scale(scalarw2);
            Vector ww = new Vector(pointTemp.x, pointTemp.y, pointTemp.z);
            double scalarw = (ww.length()+laneWidth)/ww.length();
            ww = ww.scale(scalarw);
            
            gl.glVertex3f( (float) ( pointTemp.x()), (float) ( pointTemp.y()), 1.0f);
            gl.glVertex3f( (float) ( ww.x()), (float) ( ww.y() ), 1.0f);
        }

        gl.glEnd();
        
        
        //draw Fundamentals
        gl.glFrontFace(GL_CW);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(0f, 0f, 0f);
        
        firstPoint = this.getPoint(u);
        firstTangent = this.getTangent(u);
        firstNormal = new Vector(firstTangent.y(), firstTangent.x(), 0);
        firstNormal = firstNormal.normalized();
        scalar = (firstPoint.length() + 3* laneWidth)/firstPoint.length();
        firstPoint = firstPoint.scale(scalar);
        w = new Vector(firstPoint.x, firstPoint.y, firstPoint.z);
        scalar = (w.length()+laneWidth)/w.length();
        w = w.scale(scalar);

        
        gl.glVertex3f( (float) ( w.x()), (float) ( w.y() ), 1.0f);
        gl.glVertex3f( (float) ( w.x()), (float) ( w.y() ), 0f);
        for(int i=1;i<101;i++) {
            u = i*du;
            Vector pointTemp = this.getPoint(u);
            Vector tangentTemp = this.getTangent(u);
            Vector normalTemp = new Vector(firstTangent.y(), firstTangent.x(), 0);
            normalTemp = normalTemp.normalized();
            double scalarw2 = (pointTemp.length()+3*laneWidth)/pointTemp.length();
            pointTemp = pointTemp.scale(scalarw2);
            Vector ww = new Vector(pointTemp.x, pointTemp.y, pointTemp.z);
            double scalarw = (ww.length()+laneWidth)/ww.length();
            ww = ww.scale(scalarw);
            
            gl.glVertex3f( (float) ( ww.x()), (float) ( ww.y() ), 1.0f);
            gl.glVertex3f( (float) ( ww.x()), (float) ( ww.y() ), 0f);
            
        }

        gl.glEnd();
        
    }
    
    /**
     * Returns the center of a lane at 0 <= t < 1.
     * Use this method to find the position of a robot on the track.
     */
    public Vector getLanePoint(int lane, double t){

        return Vector.O;

    }
    
    /**
     * Returns the tangent of a lane at 0 <= t < 1.
     * Use this method to find the orientation of a robot on the track.
     */
    public Vector getLaneTangent(int lane, double t){
        
        return Vector.O;

    }
    
    
    
    // Returns a point on the test track at 0 <= t < 1.
    protected abstract Vector getPoint(double t);

    // Returns a tangent on the test track at 0 <= t < 1.
    protected abstract Vector getTangent(double t);

}
