package com.marginallyclever.convenience;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuadGraphTest {

    /**
     * This test is used to test inserting a valid 2d point in a QuadGraph (happy path)
     */
    @Test
    public void testInsert2DPointInSite() {
        // Arrange
        Point2D point = new Point2D(34,52);
        QuadGraph qg = new QuadGraph(0, 0, 100, 100);

        // Act
        boolean inserted = qg.insert(point);

        // Assert
        assertTrue(inserted);
    }

    /**
     * This test is used to verify that inserting a new point in a quadGraph that has too many points (number
     * of points >= MAX_POINTS) will get inserted in of the child from the split quadGraph
     */
    @Test
    public void testInsert2DPointInFullQuadGraph() {
        // Arrange
        QuadGraph qg = new QuadGraph(0, 0, 20, 20);
        Point2D point1 = new Point2D(0,10);
        Point2D point2 = new Point2D(10,0);
        Point2D point3 = new Point2D(10,10);
        Point2D point4 = new Point2D(0,0);
        Point2D point5 = new Point2D(18,18);
        qg.insert(point1);
        qg.insert(point2);
        qg.insert(point3);
        qg.insert(point4);
        qg.insert(point5);
        Point2D pointToAdd = new Point2D(15,15);

        // Act
        boolean inserted = qg.insert(pointToAdd);

        // Assert
        assertTrue(inserted);
    }

    /**
     * This test checks that we properly find the point we're searching for in our quadGraph (happy path)
     */
    @Test
    public void testSearchingPointWithSite() {
        // Arrange
        QuadGraph qg = new QuadGraph(0, 0, 10, 10);
        Point2D point = new Point2D(3,3);
        qg.insert(point);

        // Act
        Point2D pointFound = qg.search(point);

        // Assert
        assertEquals(point, pointFound);
    }

    /**
     * This test verifies that no point will be returned if we search for a point not in our QuadGraph
     * that has children's
     */
    @Test
    public void testSearchingPointWithSiteAndChildrenEmpty() {
        // Arrange
        Point2D pointToSearch = new Point2D(34,52);
        QuadGraph qg = new QuadGraph(0, 0, 100, 100);
        qg.children = new QuadGraph[4];
        qg.children[0] = new QuadGraph(0, 0, 50, 50);
        qg.children[1] = new QuadGraph(0, 50, 50, 0);
        qg.children[2] = new QuadGraph(50, 50, 0, 0);
        qg.children[3] = new QuadGraph(50, 0, 0, 50);

        // Act
        Point2D pointFound = qg.search(pointToSearch);

        // Assert
        assertNull(pointFound);
    }
}
