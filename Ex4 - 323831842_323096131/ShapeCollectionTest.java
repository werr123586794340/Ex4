package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;

class ShapeCollectionTest {


	@Test
	void testGetBoundingBox() {

		ShapeCollectionable _shapesT = new ShapeCollection();

		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(2,2);
		Point2D p3 = new Point2D(3,1);
		Point2D p4 = new Point2D(3,3);
		Point2D p5 = new Point2D(4,2);
		Point2D p6 = new Point2D(4,4);
		Point2D p7 = new Point2D(2,1);
		ArrayList<Point2D> polyPoints = new ArrayList<>();
		polyPoints.add(p1);
		polyPoints.add(p7);
		polyPoints.add(p2);
		polyPoints.add(p5);

		//				Triangle2D t1 = new Triangle2D(p1,p2,p3);
		//				Circle2D c1 = new Circle2D(p2,2);
		//				Triangle2D t2 = new Triangle2D(p2,p4,p5);
		//				Rect2D r1 = new Rect2D(p2,p6);
		//				Polygon2D poly1 = new Polygon2D(polyPoints);

		//				GUI_Shapeable gs1 = new GUIShape(t1, true, Color.black, 0);
		//				GUI_Shapeable gs2 = new GUIShape(c1, false, Color.blue, 0);
		//				GUI_Shapeable gs3 = new GUIShape(t2, true, Color.black, 0);
		//				GUI_Shapeable gs4 = new GUIShape(r1, false, Color.red, 0);
		//				GUI_Shapeable gs5 = new GUIShape(poly1, true, Color.black, 0);
		//				GUI_Shapeable gs6 = new GUIShape(poly1, false, Color.green, 0);
		//				
		//				_shapesT.add(gs1);
		//				_shapesT.add(gs2);
		//				_shapesT.add(gs3);
		//				_shapesT.add(gs4);
		//				_shapesT.add(gs5);
		//				_shapesT.add(gs6);
		//				
		//				Rect2D testRect = _shapesT.getBoundingBox();		
		//				double eps = Point2D.EPS;
		//				
		//				assertEquals(testRect.area(), 9, eps);

	}

}
