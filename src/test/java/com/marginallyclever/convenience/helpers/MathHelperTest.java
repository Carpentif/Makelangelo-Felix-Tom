package com.marginallyclever.convenience.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.vecmath.Point2d;

public class MathHelperTest {

    /**
     * Test the 'equals' method of MathHelper, it checks both equal and non-equal cases.
     */
    @Test
    public void testEquals() {
        Point2d a0 = new Point2d(1, 1);
        Point2d a1 = new Point2d(2, 2);
        Point2d b0 = new Point2d(1, 1);
        Point2d b1 = new Point2d(2, 2);
        assertTrue(MathHelper.equals(a0, a1, b0, b1, 0.001));

        b1 = new Point2d(2.001, 2.001);
        assertTrue(MathHelper.equals(a0, a1, b0, b1, 0.01));
        assertFalse(MathHelper.equals(a0, a1, b0, b1, 0.0001));
    }
    
    @Test
    public void testBetween() {
        Point2d a = new Point2d();
        Point2d b = new Point2d();
        Point2d c;
        double epsilon = 1e-9;

        for (int i = 0; i < 50; ++i) {
            a.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            b.set(Math.random() * 500 - 250, Math.random() * 500 - 250);
            c = MathHelper.lerp(a, b, Math.random());
            assert (MathHelper.between(a, b, c, epsilon));
        }
    }

    @Test
    public void testNotBetween() {
        Point2d a = new Point2d();
        Point2d b = new Point2d();
        Point2d c;
        double epsilon = 1e-9;

        for(int i=0;i<50;++i) {
            a.set(Math.random()*500-250, Math.random()*500-250);
            b.set(Math.random()*500-250, Math.random()*500-250);
            c = MathHelper.lerp(a,b,1.0+epsilon+Math.random());
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }

        for(int i=0;i<50;++i) {
            a.set(Math.random()*500-250, Math.random()*500-250);
            b.set(Math.random()*500-250, Math.random()*500-250);
            c = MathHelper.lerp(a,b,-epsilon-Math.random());
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }
    }

    @Test
    public void testOffLine() {
        Point2d a = new Point2d();
        Point2d b = new Point2d();
        Point2d ortho = new Point2d();
        Point2d c;
        double epsilon = 1e-9;

        for(int i=0;i<50;++i) {
            a.set(Math.random()*500-250, Math.random()*500-250);
            b.set(Math.random()*500-250, Math.random()*500-250);
            c = MathHelper.lerp(a,b,Math.random());
            ortho.set(b);
            ortho.sub(a);
            c.x+=ortho.y;
            c.y+=ortho.x;
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }

        for(int i=0;i<50;++i) {
            a.set(Math.random()*500-250, Math.random()*500-250);
            b.set(Math.random()*500-250, Math.random()*500-250);
            c = MathHelper.lerp(a,b,1.0+epsilon+Math.random());
            ortho.set(b);
            ortho.sub(a);
            c.x+=ortho.y;
            c.y+=ortho.x;
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }

        for(int i=0;i<50;++i) {
            a.set(Math.random()*500-250, Math.random()*500-250);
            b.set(Math.random()*500-250, Math.random()*500-250);
            c = MathHelper.lerp(a,b,-epsilon-Math.random());
            ortho.set(b);
            ortho.sub(a);
            c.x+=ortho.y;
            c.y+=ortho.x;
            Assertions.assertFalse(MathHelper.between(a, b, c, epsilon));
        }
    }
}
