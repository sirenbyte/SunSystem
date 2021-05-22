	import java.util.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import java.awt.image.*;
	
public class SolarSystemMain extends JPanel
{
    Model model;
    CelestialBody[] celestialBodies = new CelestialBody[9];
    boolean[] descriptionSeen = new boolean[9];
    
    final static int DELAY = 50; 
    double size = 1;
    BufferedImage[] bimgs = new BufferedImage[9];
    String[][] description;
    boolean stop = false;
    int clicked = -1;
    ArrayList<Color>a=new ArrayList<Color>();
    public SolarSystemMain()
    {	a.add(Color.GRAY);
    	a.add(new Color(207,153,52));
    	a.add(Color.BLUE);
    	a.add(Color.RED);
    	a.add(new Color(255,140,0));
    	a.add(new Color(112,128,144));
    	a.add(new Color(196,233,238));
    	a.add(new Color(66, 98, 243));
    	a.add(Color.ORANGE);
          model = new Model();
          model.setPreferredSize(new Dimension(1200,1200));
          add(model);
          
            celestialBodies[0] = new CelestialBody(600, 450, -4.7, 0, 9, 8, a.get(0), 1000); //Mercury
            celestialBodies[1] = new CelestialBody(752, 400, 0, 2.5, 900, 12, a.get(1), 1000); //Venus
            celestialBodies[2] = new CelestialBody(600, 150, 1.8, 0, 900, 11,a.get(2) , 2000); //Earth
            celestialBodies[3] = new CelestialBody(650, -50, 1.2, 0, 900, 7,a.get(3) , 2000); //Mars
            celestialBodies[4] = new CelestialBody(600, -100, 1.2, 0, 900, 20,a.get(4) , 2000); //Jupiter
            celestialBodies[5] = new CelestialBody(600, -150, 1.2, 0, 900, 15,a.get(5) , 2000); //Saturn
            celestialBodies[6] = new CelestialBody(600, -175, 1.2, 0, 900, 15, a.get(6), 2000); //Uranus
            celestialBodies[7] = new CelestialBody(0, 400, 0, -1.2, 900, 13, a.get(7), 2000);//Neptune
            
            celestialBodies[8] = new CelestialBody(600, 400, .1, 0, 1000, 30, a.get(8), 0);//Sun
            
                      
                    
          setBackground(Color.BLACK);
         
          description = new String[][]{ 
              {"Mercury","Diameter: " + celestialBodies[0].getDiameter()*1058+ " kilometers",
                  "Mass: 0.330 x 10^(24) kg",
                  "Atmosphere Type: Thin",
                  "Average Temperature: 167 degrees C",
                  "Average Day Length: 3.1 Earth Days",
                  "Inner Planet - Closest to the Sun"},
              {"Venus","Diameter: " + celestialBodies[1].getDiameter()*1058+ " kilometers",
                  	 "Mass: 4.87 x 10^(24) kg",
                       "Atmosphere Type: Medium Thin",
                       "Average Temperature: 464 degrees C",                             
                       "Average Day Length: .9 Earth Days",
                       "Inner Planet - Known as Earth's Twin"
                  },
              { "Earth","Diameter: " + celestialBodies[2].getDiameter()*1058+ " kilometers",
                      "Mass: 5.97 x 10^(24) kg",
                      "Atmosphere Type: Thin",
                      "Average Temperature: 15 degrees C",
                      "Average Day Length: 1 Earth Day",
                      "Inner Planet - Our Home, the Blue Marble"	
                 },
              { "Mars","Diameter: " + celestialBodies[3].getDiameter()*1058+ " kilometers",
                 	"Mass: 0.642 x 10^(24) kg",
                  "Atmosphere Type: Medium Thick",
                  "Average Temperature: -65 degrees C",
                  "Average Day Length: .8 Earth Days",
                  " Inner Planet - Known as the Red Planet"
                  },
              {"Jupiter","Diameter: " + celestialBodies[4].getDiameter()*1058+ " kilometers",
                      "Mass: 1898 x 10^(24) kg",
                      "Atmosphere Type: Thick",
                      "Average Temperature: -110 degrees C",
                      "Average Day Length: .6 Earth Days",
                      "Outer Planet - Largest planet in the Solar System"},
                  {"Saturn","Diameter: " + celestialBodies[5].getDiameter()*3058+ " kilometers",
                      "Mass: 568 x 10^(24) kg",
                      "Average Temperature: -140 degrees C",
                      "Atmosphere Type: Thick",
                      "Outer Planet - Known for its Rings"}, 
                  {"Uranus","Diameter: " + celestialBodies[6].getDiameter()*3058+ " kilometers",
                          "Mass: 86.8 x 10^(24) kg",
                          "Atmosphere Type: Thick",
                          "Average Temperature: -195 degrees C",
                          "Outer Planet - Interior Composed of Ices and Rock"},
              {"Neptune","Diameter: " + celestialBodies[7].getDiameter()*1058+ " kilometers",
                  "Mass: 102 x 10^(24) kg",
                  "Atmosphere Type: Thin",
                  "Atmosphere Type: Thick",
                  "Average Temperature: -200 degrees C",
                  "Average Day Length: .6 Earth Days",
                  "Outer Planet - Only planet found by mathematical prediction, not empirical observation"},
              {"Sun","Diameter: " + celestialBodies[8].getDiameter()*3058+ " kilometers",
                  "Mass: 1.989 Г— 10^30 kg",
                  "Atmosphere Type: Thick",
                  "Average Temperature: 5505 degrees C",
                  "Largest Body in the Solar System"},
           };

        
          Thread thread =  new Thread() {
     
            @Override
             public void run() {
                gameLoop();
             }
          }; 
          
          thread.start();
    }
   
    
    
    private void gameLoop() {
    	
      while (true) {
            if (!stop)
            {
                for(int i = 0; i < celestialBodies.length-1; i++)
                {
                	celestialBodies[i].update(celestialBodies[8].getXPosition(),celestialBodies[8].getYPosition(),celestialBodies[8].getMass());
                }
            }
         repaint();

         try {
            Thread.sleep(DELAY);
         } catch (InterruptedException ex) { }
      }
   }
    

class Model extends JPanel implements KeyListener, MouseListener {
      public Model() {

         setFocusable(true);
         requestFocus();
         addKeyListener(this);
         addMouseListener(this);
      }

      
      public void paintComponent(Graphics g) {


         for(CelestialBody body : celestialBodies)
            body.draw(g,size);
         
     
         for(int count=0;count<=1000;count++) {
        	 g.setColor(Color.WHITE); 
        	 
        	 g.drawOval(50*count,100*count,1,1);
        	 g.drawOval(75*count,100*count,1,1);	
        	 g.drawOval(100*count,200*count,1,1);
        	 g.drawOval(150*count,200*count,1,1);
        	 g.drawOval(200*count,200*count,1,1);
        	 g.drawOval(250*count,200*count,1,1);
        	 g.drawOval(300*count,200*count,1,1);
        	 g.drawOval(350*count,200*count,1,1);
        	 g.drawOval(400*count,100*count,1,1);
        	 g.drawOval(450*count,100*count,1,1);
        	 g.drawOval(500*count,100*count,1,1);
        	 g.drawOval(550*count,300*count,1,1);
        	 g.drawOval(600*count,300*count,1,1);
        	 g.drawOval(700*count,300*count,1,1);
        	 g.drawOval(800*count,300*count,1,1);
        	 g.drawOval(900*count,300-count,1,1);
        	 g.drawOval(1000*count,300-count,1,1);



        	 }
         
         
         if (clicked > -1)
         {
             g.drawImage(bimgs[clicked],0,0,200,200,Color.WHITE,null);
             g.setFont(new Font("Arial", Font.PLAIN, 20));
             g.setColor(Color.WHITE);
             for(int i = 0; i < description[clicked].length; i++)
             {
                 g.drawString(description[clicked][i], 0, 210+i*30);
             }
         }
         
          
          
        
         
      
		
         
         celestialBodies[0].dispDesc(g,size,"Mercury");
         celestialBodies[1].dispDesc(g,size,"Venus");
         celestialBodies[2].dispDesc(g,size,"Earth");
         celestialBodies[3].dispDesc(g,size,"Mars");
         celestialBodies[4].dispDesc(g,size,"Jupiter");
         celestialBodies[5].dispDesc(g,size,"Saturn");
         celestialBodies[6].dispDesc(g,size,"Uranus");
         celestialBodies[7].dispDesc(g,size,"Neptune");
         celestialBodies[8].dispDesc(g,size,"Sun");

      }
      
     
      public void mousePressed(MouseEvent e) {
    	  
      }
      public void mouseReleased(MouseEvent e) {
          for(int i = 0; i < celestialBodies.length; i++)
              if (celestialBodies[i].hitPlanet(e.getX(), e.getY(), size))
              {
                  
            	  celestialBodies[i].setDescVisible(!celestialBodies[i].getDescVisible());
                  if(celestialBodies[i].getDescVisible()) {
                	  clicked = i;
                  }
                  else  {
                	  clicked = -1;
                  }
              }
      }
      public void keyTyped(KeyEvent e) {
    	  
      }
      public void mouseEntered(MouseEvent e) { 
    	  
      }
      public void mouseExited(MouseEvent e) { 
    	  
      }
      public void mouseClicked(MouseEvent e) { 
    	  
      }
      
      public void keyPressed(KeyEvent e) {
    	  
      }
      
      @Override
      public void keyReleased(KeyEvent e) { 

          if(e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == KeyEvent.VK_EQUALS)
        	  size += .1;
          
    	  if(e.getKeyCode() == KeyEvent.VK_MINUS && size > 0)
        	  size -= .1;
    	  
          if(e.getKeyCode() == KeyEvent.VK_SPACE)
          {
              stop = !stop;
          }
          if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
          {
              System.exit(0);
          }
         
      }
   

   }
     
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            JFrame frame = new JFrame("Solar System Model");
            frame.setContentPane(new SolarSystemMain());  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);            
         }
      });
    }
}




