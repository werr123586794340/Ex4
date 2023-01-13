package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;

class Rect2DTest {

	@Test
	void testContains() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(3,4);
		Point2D p4 = new Point2D(2,1);
		Point2D p5 = new Point2D(14,15.6);
		Point2D p6 = new Point2D(14,15.7);

		Rect2D r1 = new Rect2D(p2,p5);

		boolean contain1 = r1.contains(p3);
		boolean contain2 = r1.contains(p6);

		assertEquals(contain1, true);
		assertEquals(contain2, false);

	}

	@Test
	void testArea() {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p4 = new Point2D(17,11);
		Point2D p5 = new Point2D(4,2.2);

		Rect2D r1 = new Rect2D(p2,p5);
		Rect2D r2 = new Rect2D(p1,p4);

		double area1 = r1.area();
		double area2 = r2.area();
		double eps = 0.01;

		assertEquals(area1,0.4, eps);
		assertEquals(area2,160 ,eps);

	}

	//	@Test
	//	void testPerimeter() {
	//
	//		Point2D p1 = new Point2D(1,1);
	//		Point2D p2 = new Point2D(2,2);
	//		Point2D p3 = new Point2D(17,11);
	//		Point2D p4 = new Point2D(4,10);
	//
	//		Rect2D r1 = new Rect2D(p2,p4);
	//		Rect2D r2 = new Rect2D(p1,p3);
	//
	//		double perimeter1 = r1.perimeter();
	//		double perimeter2 = r2.perimeter();
	//		double eps = 0.01;
	//
	//		assertEquals(perimeter1,4.4, eps);
	//		assertEquals(perimeter2,20 ,eps);
	//
	//	}

	@Test
	void testMove() {

		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(3,1);
		Point2D p4 = new Point2D(3,3);
		Point2D p5 = new Point2D(4,2);

		Rect2D r1 = new Rect2D(p1,p2);
		Rect2D r2 = new Rect2D(p2,p4);

		r1.move(p1);


		double rect1x1 = r1.GetXs()[0];
		double rect1x2 = r1.GetXs()[1];
		double rect2x1 = r2.GetXs()[0];
		double rect2x2 = r2.GetXs()[1];

		double rect1y1 = r1.GetYs()[0];
		double rect1y2 = r1.GetYs()[1];
		double rect2y1 = r2.GetYs()[0];
		double rect2y2 = r2.GetYs()[1];


		assertEquals(rect1x1,rect2x1);
		assertEquals(rect1x2,rect2x2);
		assertEquals(rect1y1,rect2y1);
		assertEquals(rect1y2,rect2y2);

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
	@Test
	void testRotate() {
	}


}



