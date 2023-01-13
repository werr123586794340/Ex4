package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Polygon2DTest {

	@Test
	void testContains() {

		Point2D p1 = new Point2D(1,8.2);
		Point2D p2 = new Point2D(5.8,8.3);
		Point2D p3 = new Point2D(5.5,6.5);
		Point2D p4 = new Point2D(1.5,6);
		Point2D p5 = new Point2D(3,7);
		Point2D p6 = new Point2D(1.7,6.8);
		Point2D p7 = new Point2D(2.5,7.6);
		Point2D p8 = new Point2D(0.75,7.4);
		Point2D p9 = new Point2D(0.3,9.3);

		Point2D p10 = new Point2D(0.65625,8.6406253);
		Point2D p11 = new Point2D(2.1875,6.609375);

		ArrayList<Point2D> polyPoints = new ArrayList<>();
		polyPoints.add(p1);
		polyPoints.add(p2);
		polyPoints.add(p3);
		polyPoints.add(p4);
		polyPoints.add(p5);
		polyPoints.add(p6);
		polyPoints.add(p7);	
		polyPoints.add(p8);
		polyPoints.add(p9);

		Polygon2D poly1 = new Polygon2D(polyPoints);

		boolean contain1 = poly1.contains(p10);
		boolean contain2 = poly1.contains(p11);

		assertEquals(contain1, true);
		assertEquals(contain2, false);

	}

	@Test
	void testArea() {

		Point2D p1 = new Point2D(1,1.5);
		Point2D p2 = new Point2D(4.5,1.5);
		Point2D p3 = new Point2D(6.5,3);
		Point2D p4 = new Point2D(4.5,4.5);
		Point2D p5 = new Point2D(1,4.5);

		ArrayList<Point2D> polyPoints = new ArrayList<>();
		polyPoints.add(p1);
		polyPoints.add(p2);
		polyPoints.add(p3);
		polyPoints.add(p4);
		polyPoints.add(p5);

		Polygon2D poly1 = new Polygon2D(polyPoints);

		double area1 = poly1.area();
		double eps = Ex4_Const.EPS;

		assertEquals(area1,13.5, eps);
	}

	@Test
	void testPerimeter() {

		Point2D p1 = new Point2D(1,1.5);
		Point2D p2 = new Point2D(4.5,1.5);
		Point2D p3 = new Point2D(6.5,3);
		Point2D p4 = new Point2D(4.5,4.5);
		Point2D p5 = new Point2D(1,4.5);

		ArrayList<Point2D> polyPoints = new ArrayList<>();
		polyPoints.add(p1);
		polyPoints.add(p2);
		polyPoints.add(p3);
		polyPoints.add(p4);
		polyPoints.add(p5);

		Polygon2D poly1 = new Polygon2D(polyPoints);

		double perimeter1 = poly1.perimeter();
		double eps = Ex4_Const.EPS;

		assertEquals(perimeter1,15, eps);
	}

	@Test
	void testMove() {

		Point2D p1 = new Point2D(1,1);
		Point2D pp1 = new Point2D(2,2);
		Point2D p2 = new Point2D(4,1);
		Point2D pp2 = new Point2D(5,2);
		Point2D p3 = new Point2D(6.5,3);
		Point2D pp3 = new Point2D(7.5,4);
		Point2D p4 = new Point2D(4,4.5);
		Point2D pp4 = new Point2D(5,5.5);
		Point2D p5 = new Point2D(1,4.5);
		Point2D pp5 = new Point2D(2,5.5);
		Point2D vec = new Point2D(1,1);

		ArrayList<Point2D> polyPoints = new ArrayList<>();
		polyPoints.add(p1);
		polyPoints.add(p2);
		polyPoints.add(p3);
		polyPoints.add(p4);
		polyPoints.add(p5);

		Polygon2D poly1 = new Polygon2D(polyPoints);

		poly1.move(vec);

		double polyx1 = poly1.getXs()[0];
		double polyx2 = poly1.getXs()[1];
		double polyx3 = poly1.getXs()[2];
		double polyx4 = poly1.getXs()[3];
		double polyx5 = poly1.getXs()[4];

		double polyy1 = poly1.getYs()[0];
		double polyy2 = poly1.getYs()[1];
		double polyy3 = poly1.getYs()[2];
		double polyy4 = poly1.getYs()[3];
		double polyy5 = poly1.getYs()[4];

		assertEquals(pp1.x(),polyx1);
		assertEquals(pp2.x(),polyx2);
		assertEquals(pp3.x(),polyx3);
		assertEquals(pp4.x(),polyx4);
		assertEquals(pp5.x(),polyx5);

		assertEquals(pp1.y(),polyy1);
		assertEquals(pp2.y(),polyy2);
		assertEquals(pp3.y(),polyy3);
		assertEquals(pp4.y(),polyy4);
		assertEquals(pp5.y(),polyy5);

	}

	//	@Test
	//	void testCopy() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testScale() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	void testRotate() {
	//		fail("Not yet implemented");
	//	}

}