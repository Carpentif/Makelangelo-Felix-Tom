package com.marginallyclever.convenience.helpers;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import javax.vecmath.Point2d;
import static org.junit.jupiter.api.Assertions.*;
import javax.vecmath.Vector2d;
import com.github.javafaker.Faker;

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

    /**
     * Test the 'lerp' method of MathHelper using faker, check interpolation between random values and edge cases (0 and 1)
     */
    @Test
    public void testLerpWithFaker() {
        Faker faker = new Faker();
        double t = faker.number().randomDouble(2, 0, 1);
        double a = faker.number().randomDouble(2, -100, 100);
        double b = faker.number().randomDouble(2, -100, 100);

        double result = MathHelper.lerp(t, a, b);

        double expected = a + t * (b - a);
        assertEquals(expected, result, 0.0001);
    }

    /**
     * Test the 'intersectionOfCircles' method of MathHelper, check correct calculation of circle intersections.
     */
    @Test
    public void testIntersectionOfCircles() {
        // 1: intersection of two equal circles
        Vector2d result1 = MathHelper.intersectionOfCircles(5, 5, 5);
        assertEquals(2.5, result1.x, 0.0001);
        assertEquals(Math.sqrt(18.75), result1.y, 0.0001);

        // 2: circles touching at one point
        Vector2d result2 = MathHelper.intersectionOfCircles(3, 2, 5);
        assertEquals(3.0, result2.x, 0.0001);
        assertEquals(0.0, result2.y, 0.0001);

        // 3: circles not intersecting
        assertThrows(IllegalArgumentException.class, () -> MathHelper.intersectionOfCircles(1, 1, 3));

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
