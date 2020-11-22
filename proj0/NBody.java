public class NBody {
    /** Given a file name, return the radius of the universe in that file */
     public static double readRadius(String fileName) {
         In in = new In(fileName);
         in.readInt();
         return in.readDouble();
    }

    /** Given a file name, return an array of Planets */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int planetNum = in.readInt();
        Planet[] planetArr = new Planet[planetNum];
        in.readDouble();
        for (int i=0; i<planetNum; i++) {
            planetArr[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planetArr;
    }
 
    public static void main(String[] args) {
        /* get data */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double uniRadius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/* Draw */
		StdDraw.setScale(-uniRadius, uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p : planets) {
			p.draw();
        }
        /* Animation */
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i=0; i<planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                
            }
            for (int i=0; i<planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
				p.draw();
			}

            StdDraw.show();
            StdDraw.pause(10);

			time += dt;
        }
        StdDraw.show();
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", uniRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
