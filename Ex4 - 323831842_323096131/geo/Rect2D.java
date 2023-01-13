package Exe.Ex4.geo;

import java.util.ArrayList;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {


	//////////data//////////

	private Point2D _vertex1;
	private Point2D _vertex2;
	private Point2D _vertex3;
	private Point2D _vertex4;

	private ArrayList<Point2D> _rectArr;


	//////////constructor//////////

	private Rect2D() {
		_rectArr = new ArrayList<>();
	}

	public Rect2D(Point2D p1, Point2D p2) {

		_vertex1 = new Point2D(p1);
		_vertex2 = new Point2D(p2);
		_vertex4 = new Point2D(this._vertex1.x() , this._vertex2.y());
		_vertex3 = new Point2D(this._vertex2.x(), this._vertex1.y());
	}

	public Rect2D(ArrayList<Point2D> rectArr) {
		_rectArr = (ArrayList<Point2D>)rectArr.clone();

	}

	//functions

	// getter of the xs of points that characteristics the rectangle and build an array of them
	public double[] GetXs() {
		double[] arrX = {this._vertex1.x(), this._vertex4.x(), this._vertex2.x(), this._vertex3.x()};

		return arrX;
	}
	// getter of the ys of points that characteristics the rectangle and build an array of them
	public double[] GetYs() {
		double[] arrY = {this._vertex1.y(), this._vertex4.y(), this._vertex2.y(), this._vertex3.y()};

		return arrY;
	}

	// this function checks if the dot ot is inside the rectangle
	@Override
	public boolean contains(Point2D ot) {

		Triangle2D triangle1 = new Triangle2D(ot, _vertex1, _vertex4);
		Triangle2D triangle2 = new Triangle2D(ot, _vertex1, _vertex3);
		Triangle2D triangle3 = new Triangle2D(ot, _vertex2, _vertex3);
		Triangle2D triangle4 = new Triangle2D(ot, _vertex2, _vertex4);
		double area = triangle1.area() + triangle2.area() + triangle3.area() + triangle4.area();

		if (area + Ex4_Const.EPS >= this.area() && this.area() + Ex4_Const.EPS >= area) {return true;}

		return false;
	}

	// this function computes the area of the the rectangle 
	@Override
	public double area() {
		double ans = _vertex1.distance(_vertex4) * _vertex2.distance(_vertex4);
		return ans;
	}

	// this function computes the perimeter of the the rectangle
	@Override
	public double perimeter() {
		double peri = 0;
		for (int i = 0, j = _rectArr.size() - 1; i < _rectArr.size(); j = i++) {
			peri = _rectArr.get(i).distance(_rectArr.get(j));

		}
		return peri;
	}

	// this function moves the rectangle by given vector
	@Override
	public void move(Point2D vec) {
		_vertex1.move(vec);
		_vertex2.move(vec);
		_vertex4.move(vec);
		_vertex3.move(vec);
	}

	// this function copy the rectangle
	@Override
	public GeoShapeable copy() {
		ArrayList<Point2D> polyRect = new ArrayList<>();
		polyRect.add(_vertex1);
		polyRect.add(_vertex3);
		polyRect.add(_vertex2);
		polyRect.add(_vertex4);
		return new Polygon2D(polyRect);
	}

	// this function scales the rectangle with respect to the given center point
	@Override
	public void scale(Point2D center, double ratio) {

		double x1 = this._vertex1.x()- center.x();
		double y1 = this._vertex1.y()- center.y();
		double newX1 = center.x() + (x1*ratio);
		double newY1 = center.y() + (y1*ratio);
		this._vertex1 = new Point2D(newX1,newY1);

		double x2 = this._vertex2.x()- center.x();
		double y2 = this._vertex2.y()- center.y();
		double newX2 = center.x() + (x2*ratio);
		double newY2 = center.y() + (y2*ratio);
		this._vertex2 = new Point2D(newX2,newY2);

		double x3 = this._vertex3.x()- center.x();
		double y3 = this._vertex3.y()- center.y();
		double newX3 = center.x() + (x3*ratio);
		double newY3 = center.y() + (y3*ratio);
		this._vertex3 = new Point2D(newX3,newY3);

		double x4 = this._vertex4.x()- center.x();
		double y4 = this._vertex4.y()- center.y();
		double newX4 = center.x() + (x4*ratio);
		double newY4 = center.y() + (y4*ratio);
		this._vertex4 = new Point2D(newX4,newY4);
	}

	// this function rotates the rectangle with respect to the given center point by an angle
	@Override
	public void rotate(Point2D center, double angleDegrees) {

		_vertex1.rotate(center, angleDegrees);
		_vertex4.rotate(center, angleDegrees);
		_vertex3.rotate(center, angleDegrees);
		_vertex2.rotate(center, angleDegrees);
	}

	// getter of the points that characteristics the rectangle
	@Override
	public Point2D[] getPoints() {

		Point2D[] arr = new Point2D[4];
		arr[0] = new Point2D(_vertex1);
		arr[1] = new Point2D(_vertex2);
		arr[2] = new Point2D(_vertex3);
		arr[3] = new Point2D(_vertex4);

		return arr;

	}

	private Point2D Point2D(double y, double x) {
		Point2D p2d = new Point2D(y,x);
		return p2d;
	}

	@Override
	public String toString() {
		return "Rect2D: ["+_vertex1+","+_vertex2+","+_vertex3+","+_vertex4+"]";
	}
}
