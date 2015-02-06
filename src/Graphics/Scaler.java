package Graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Scaler {

	static double scaleFactor = 1.0;
	static Boolean enableScaleMethod = false;
	static int dimension = 1;
	static Dimension normalDimension = new Dimension(1600, 900);
	static Dimension[] dimensions = new Dimension[]{new Dimension(1920, 1080), new Dimension(1600, 900), new Dimension(1280, 720)};

	public static void initialize(Dimension windowDimension){
		for(int i = 0; i<dimensions.length; i++)if(windowDimension.equals(dimensions[i]))dimension = i;
		
		scaleFactor = (dimensions[dimension].getHeight() / normalDimension.getHeight());
		if(scaleFactor != 1)enableScaleMethod = true;
	}

	public static BufferedImage scale(BufferedImage img){	
		if(!enableScaleMethod)return img;
		else {
			Image scaled = img.getScaledInstance(((int) (img.getWidth()*scaleFactor)), ((int) (img.getHeight()*scaleFactor)), Image.SCALE_SMOOTH);
			BufferedImage buffered = new BufferedImage(((int) (img.getWidth()*scaleFactor)), ((int) (img.getHeight()*scaleFactor)), BufferedImage.TYPE_INT_ARGB);
			buffered.getGraphics().drawImage(scaled, 0, 0 , null);
			return buffered;
		}
	}
	
	public static int scale(int i){
		return (int) (i*scaleFactor);
	}
}