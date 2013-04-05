import org.newdawn.slick.*;

public class PlaneGame extends BasicGame
{
    Image land;
    Image plane;
    float x;
    float y;
    float scale;

    public PlaneGame() {
        super("Hello World");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        land = new Image("/data/land.jpg");
        plane = new Image("/data/plane.png");
        x = 400;
        y = 300;
        scale = 1.0f;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            plane.rotate(-0.2f * delta);
        }

        if (input.isKeyDown(Input.KEY_D)) {
            plane.rotate(0.2f * delta);
        }

        if (input.isKeyDown(Input.KEY_W)) {
            float hip = 0.4f * delta;

            float rotation = plane.getRotation();

            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }

        if (input.isKeyDown(Input.KEY_2)) {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        if (input.isKeyDown(Input.KEY_1)) {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        land.draw(0, 0);
        plane.draw(x, y, scale);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new PlaneGame());

        app.setDisplayMode(800, 600, false);
        app.start();
    }

}