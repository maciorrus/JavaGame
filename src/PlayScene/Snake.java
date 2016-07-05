package PlayScene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;

import engine.Engine;

public class Snake {

	Czlonek[] czlonki;
	int n;
	int pause;

	public Snake() {
		n = 5;
		pause = 0;
		czlonki = new Czlonek[5];
		for (int i = 0; i < 5; i++) {
			czlonki[i] = new Czlonek(i, 1, 1);
		}

	}

	public void move(int k) {
		System.out.println("GO: " + k);
		czlonki[n - 1].kier = k;
	}

	public boolean update() {
		pause = (pause+1) % 5;
		System.out.println(pause);
		if (pause == 0) {
			for (int i = 0; i < n; i++) {
				switch (czlonki[i].kier) {
				case 1:
					czlonki[i].x++;
					break;
				case 2:
					czlonki[i].y++;
					break;
				case 3:
					czlonki[i].x--;
					break;
				case 4:
					czlonki[i].y--;
					break;
				}
				if (czlonki[i].x > 20 || czlonki[i].y > 15 || czlonki[i].x < 0 || czlonki[i].y < 0) {
					System.out.println("resetujemy");
					for (i = 0; i < 5; i++) {
						czlonki[i].x = i;
						czlonki[i].y = 1;
						czlonki[i].kier = 1;
					}
					return true;
				}
				if (i < (n - 1))
					czlonki[i].kier = czlonki[i + 1].kier;
			}
		}
		return false;
	}

	public void draw(final GL2 gl) {
		gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(0f, 1f, 1f);
		gl.glVertex2f(Engine.relOX(10), Engine.relOY(10));
		gl.glVertex2f(Engine.relOX(10), Engine.relOY(490));
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(Engine.relOX(10), Engine.relOY(490));
		gl.glVertex2f(Engine.relOX(640), Engine.relOY(490));
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(Engine.relOX(640), Engine.relOY(490));
		gl.glVertex2f(Engine.relOX(640), Engine.relOY(10));
		gl.glEnd();
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(Engine.relOX(640), Engine.relOY(10));
		gl.glVertex2f(Engine.relOX(10), Engine.relOY(10));
		gl.glEnd();
		for (int i = 0; i < 5; i++) {
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3f(0f, 1f, 1f);
			gl.glVertex2f(Engine.relOX(czlonki[i].x * 30+10), Engine.relOY(czlonki[i].y * 30+10));
			gl.glVertex2f(Engine.relOX(czlonki[i].x * 30+10), Engine.relOY(czlonki[i].y * 30 + 38));
			gl.glVertex2f(Engine.relOX(czlonki[i].x * 30 + 38), Engine.relOY(czlonki[i].y * 30 + 38));
			gl.glVertex2f(Engine.relOX(czlonki[i].x * 30 + 38), Engine.relOY(czlonki[i].y * 30+10));
			gl.glEnd();

		}

	}
}
