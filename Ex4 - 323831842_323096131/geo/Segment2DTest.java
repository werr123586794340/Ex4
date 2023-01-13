package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;

class Segment2DTest {

	@Test
	void testContains() {
		Point2D p1 = new Point2D(2,6);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(2,3);
		Point2D p4 = new Point2D(5,9);

		Segment2D s1 = new Segment2D(p1,p2);

		boolean contain1 = s1.contains(p3);
		boolean contain2 = s1.contains(p4);

		assertEquals(contain1, true);
		assertEquals(contain2, false);
	}

	@Test
	void testArea() {
		Point2D p1 =  new Point2D(4,9);
		Point2D p2 =  new Point2D(3,55);

		Segment2D s1 = new Segment2D(p1,p2);

		double area = s1.area();
		double eps = 0.001;

		assertEquals(area,0, eps);
	}


	@Test
	void testPerimeter() {
		Point2D p1 = new Point2D(5,8);
		Point2D p2 = new Point2D(2,4);
		Point2D p3 = new Point2D(6,4);
		Point2D p4 = new Point2D(3,10);

		Segment2D s1 = new Segment2D(p1,p2);
		Segment2D s2 = new Segment2D(p3,p4);

		double perimeter1 = s1.perimeter();
		double perimeter2 = s2.perimeter();
		double eps = 0.01;


		assertEquals(perimeter1,10,eps);
		assertEquals(perimeter2,2*Math.sqrt(45),eps);

	} 
}
