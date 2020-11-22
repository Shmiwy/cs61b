public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    //** the first constructor of Planet class */
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
                  this.xxPos = xP;
                  this.yyPos = yP;
                  this.xxVel = xV;
                  this.yyVel = yV;
                  this.mass = m;
                  this.imgFileName = img;
              }

    //**  The second constructor */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    //** recive a planet and return the the distance between the two planet */
    public double calcDistance(Planet p) {
        double dis_xx = p.xxPos - this.xxPos;
        double dis_yy = p.yyPos - this.yyPos;
        return Math.sqrt(dis_xx * dis_xx + dis_yy * dis_yy);
    }

    //** takes in a planet and returns the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet p) {
        return G * this.mass * p.mass / this.calcDistance(p) / this.calcDistance(p);
    }

    //** takes in a planet and returns the force exerted on this planet by the given planet in X directions*/
    public double calcForceExertedByX(Planet p) {
        double cos = (p.xxPos - this.xxPos) / this.calcDistance(p);
        return this.calcForceExertedBy(p) * cos;
    }

    //** takes in a planet and returns the force exerted on this planet by the given planet in Y directions*/
    public double calcForceExertedByY(Planet p) {
        double sin = (p.yyPos - this.yyPos) / this.calcDistance(p);
        return this.calcForceExertedBy(p) * sin;
    }

    //** take in an array of Planets, return net X force exerted upon the current Planet */
    public double calcNetForceExertedByX(Planet[] arrayPlanet) {
        double sumForceOnx = 0.0;
        for (Planet p : arrayPlanet) {
            if (this.equals(p) != true) {
                sumForceOnx += this.calcForceExertedByX(p);
            }
        }
        return sumForceOnx;
    }

    //** take in an array of Planets, return net Y force exerted upon the current Planet */
    public double calcNetForceExertedByY(Planet[] arrayPlanet) {
        double sumForceOny = 0.0;
        for (Planet p : arrayPlanet) {
            if (this.equals(p) != true) {
                sumForceOny += this.calcForceExertedByY(p);
            }
        }
        return sumForceOny;
    }

    //** update the planet’s position and velocity instance variables*/
    public void update(double dt, double fx, double fy) {
        double accelerationOnX = fx / this.mass;
        double accelerationOnY = fy / this.mass;
        this.xxVel += dt * accelerationOnX;
        this.yyVel += dt * accelerationOnY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    /** draw this planet at its appropriate position */
     public void draw() {
         String filename = "images/" + this.imgFileName;
         StdDraw.picture(this.xxPos, this.yyPos, filename);
         StdDraw.show();
     }
}