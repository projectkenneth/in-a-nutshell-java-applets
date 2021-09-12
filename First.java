//First.java  
import java.applet.Applet; 
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import netscape.javascript.JSObject;
import netscape.javascript.JSException;

public class First extends Applet{  
	private final int CENTERX = 150;  
	private final int CENTERY = 150;
	
	private int diameter = 0;
	private double radius = 0;
	private double circumference = 0;
	
	public void paint(Graphics g){ 
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawString("The Circle Applet", 10, 30);
		
		if (this.diameter > 0) {
			int left = CENTERX - (int)radius;
			int top = CENTERY - (int)radius;
			
			g.setColor(java.awt.Color.black);
			g.drawOval(left, top, this.diameter, this.diameter);  
			
			g.setColor(java.awt.Color.green);
			
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                  0, new float[]{9}, 0);			
			g2d.setStroke(dashed);
			g.drawLine(CENTERX, CENTERY, CENTERX + ((int)radius), CENTERY);
		}
	}    
	
	public void setDiameter(int newDiameter) {
		this.diameter = newDiameter;
		
		try {
            JSObject jsObj = JSObject.getWindow(this);
			
			this.radius = this.diameter / 2.0;
			this.circumference = 2.0 * Math.PI * radius;
 
			jsObj.call("renderRadiusAndCircumference", new Double[] {this.radius, this.circumference});
        } catch (JSException ex) {
            ex.printStackTrace();
        }
		
		repaint();
	}
} 