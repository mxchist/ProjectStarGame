package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 0.8f;

    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 touch;
    private Vector2 common;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        v = new Vector2();
        touch = new Vector2();
        common = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        common.set(touch);
        if (common.sub(pos).len() > V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
            v.setZero();
        }
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(pos));
        v.setLength(V_LEN);
        return false;
    }
}
