package Exe.Ex4.geo;

import java.util.ArrayList;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	//////////data//////////

	private Point2D _vertex1;
	private Point2D _vertex2;
	private Point2D _vertex3;

	private ArrayList<Point2D> _verArr;

	//////////constructor//////////

	private Triangle2D() {

		_verArr.add(_vertex1);
		_verArr.add(_vertex2);
		_verArr.add(_vertex3);

	}
	public Triangle2D(ArrayList<Point2D> verArr) {

		_verArr = (ArrayList<Point2D>)verArr.clone();

	}

	public Triangle2D(Point2D vertexA, Point2D vertexB, Point2D vertexC) {
		_vertex1 = new Point2D(vertexA);
		_vertex2 = new Point2D(vertexB);
		_vertex3 = new Point2D(vertexC);
	}

	//////////functions//////////

	// getter of the xs of points that characteristics the triangle and build an array of them
	public double[] GetXs() {
		double[] arrX = {this._vertex1.x(), this._vertex2.x(), this._vertex3.x()};
		return arrX;
	}

	// getter of the ys of points that characteristics the triangle and build an array of them
	public double[] GetYs() {
		double[] arrY = {this._vertex1.y(), this._vertex2.y(), this._vertex3.y()};
		return arrY;
	}

	// this function checks if the dot ot is inside the triangle
	@Override
	public boolean contains(Point2D ot) {
		Triangle2D tri1 = new Triangle2D(ot, this._vertex2, this._vertex3);
		Triangle2D tri2 = new Triangle2D(this._vertex1, ot , this._vertex3);
		Triangle2D tri3 = new Triangle2D(this._vertex1, this._vertex2, ot);
		double area = tri1.area() + tri2.area() + tri3.area();

		if (area + Ex4_Const.EPS >= this.area() && this.area() + Ex4_Const.EPS >= area) {return true;}

		return false;
	}

	// this function computes the area of the the triangle   
	@Override
	public double area() {
		double a = this._vertex1.distance(_vertex2);
		double b = this._vertex2.distance(_vertex3);
		double c = this._vertex3.distance(_vertex1);
		// Heron's formula to compute triangle area in terms of the three side lengths a, b, c.
		double s = (Math.sqrt((a+b+c)*(a+b-c)*(b+c-a)*(c+a-b))/4);

		return s;
	}

	// this function computes the perimeter of the the triangle
	@Override
	public double perimeter() {
		double dis1 = this._vertex1.distance(_vertex2);
		double dis2 = this._vertex2.distance(_vertex3);
		double dis3 = this._vertex3.distance(_vertex1);

		return (dis1+dis2+dis3);
	}

	// this function moves the triangle by given vector
	@Override
	public void move(Point2D vec) {
		_vertex1.move(vec);
		_vertex2.move(vec);
		_vertex3.move(vec);

	}

	// this function copy the triangle
	@Override
	public GeoShapeable copy() {

		return new Triangle2D(_vertex1, _vertex2, _vertex3);
	}

	// this function scales the triangle with respect to the given center point
	@Override
	public void scale(Point2D center, double ratio) {

		double xv1 = this._vertex1.x()- center.x();
		double yv1 = this._vertex1.y()- center.y();
		double newX1 = center.x() + (xv1*ratio);
		double newY1 = center.y() + (yv1*ratio);
		this._vertex1 = new Point2D(newX1,newY1);

		double xv2 = this._vertex2.x()- center.x();
		double yv2 = this._vertex2.y()- center.y();
		double newX2 = center.x() + (xv2*ratio);
		double newY2 = center.y() + (yv2*ratio);
		this._vertex2 = new Point2D(newX2,newY2);

		double xv3 = this._vertex3.x()- center.x();
		double yv3 = this._vertex3.y()- center.y();
		double newX3 = center.x() + (xv3*ratio);
		double newY3 = center.y() + (yv3*ratio);
		this._vertex3 = new Point2D(newX3,newY3);
	}

	// this function rotates the triangle with respect to the given center point by an angle
	@Override
	public void rotate(Point2D center, double angleDegrees) {


		for(int i=0; i<_verArr.size(); i++) {
			_verArr.get(i).rotate(center, angleDegrees);
		}
	}

	// getter of the points that characteristics the triangle
	@Override
	public Point2D[] getPoints() {
		Point2D[] arr = new Point2D[3];
		arr[0] = _vertex1;
		arr[1] = _vertex2;
		arr[2] = _vertex3;

		return arr;
	}

	@Override
	public String toString() {
		return "Rect2D: ["+_vertex1+","+_vertex2+","+_vertex3+"]";
	}

}

