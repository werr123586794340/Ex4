package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 * @author 1 ID - 323831842
 *	@author 2 ID - 323096131 
 */
	public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;
	private  Point2D _p2;

	private ArrayList<Point2D> _polyPoints = new ArrayList<>();
	private ArrayList<Point2D> _triPoints = new ArrayList<>();
	private ArrayList<Point2D> PolygonPoints = new ArrayList<>();


	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	// this method updates the collection of shapes to g
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
		Point2D _p2 = null;
	}

	// this method shows the GUI window with the collection of shapes
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	// this method draws the shapes at the arraylist currently classified by shape.
	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}

	//this function draws the shapes by using the shape class
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();

		//////////Circle///////////
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}

		//////////Rectangle//////////
		if(gs instanceof Rect2D) {
			Rect2D r = (Rect2D)gs;
			Point2D[] arr = r.getPoints();
			Point2D p1 = arr[0];
			Point2D p2 = arr[1];

			if(isFill) {
				StdDraw_Ex4.filledPolygon(r.GetXs(),r.GetYs());
			}
			else { 
				StdDraw_Ex4.polygon(r.GetXs(),r.GetYs());
			}
		}	

		//////////Triangle//////////
		if(gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D)gs;
			if(isFill) {
				StdDraw_Ex4.filledPolygon(t.GetXs(), t.GetYs());
			}
			else { 
				StdDraw_Ex4.polygon(t.GetXs(), t.GetYs());
			}
		}

		//////////Segment//////////
		if(gs instanceof Segment2D) {
			Segment2D s = (Segment2D)gs;
			if(isFill) {
				StdDraw_Ex4.polygon(s.getXs(), s.getYs());
			}
			else { 
				StdDraw_Ex4.polygon(s.getXs(), s.getYs());
			}
		}

		//////////Polygon//////////
		if(gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D)gs;
			if(isFill) {
				StdDraw_Ex4.filledPolygon(poly.getXs(), poly.getYs());
			}
			else { 
				StdDraw_Ex4.polygon(poly.getXs(), poly.getYs());
			}
		}
	}

	// this method change the color of the object to the selected color
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	// this method fills the object at the selected color
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	// this method operator the drawshape by selected mode
	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}
		if(p.equals("Remove")) {remove();}
		if(_mode.equals("All")) {selectAll();}
		if(_mode.equals("Anti")) {selectAnti();}
		if(_mode.equals("None")) {selectNone();}

		/////////////Sort Comparator///////////////
		if(_mode.equals("SortByTag")) {_shapes.sort(ShapeComp.Comp_By_Tag);}
		if(_mode.equals("ByAntiTag")) {_shapes.sort(ShapeComp.Comp_By_Anti_Tag);}
		if(_mode.equals("ByArea")) {_shapes.sort(ShapeComp.Comp_By_Area);}
		if(_mode.equals("ByAntiArea")) {_shapes.sort(ShapeComp.Comp_By_Anti_Area);}
		if(_mode.equals("ByPerimeter")) {_shapes.sort(ShapeComp.Comp_By_Perimeter);}
		if(_mode.equals("ByAntiPerimeter")) {_shapes.sort(ShapeComp.Comp_By_Anti_Perimeter);}
		if(_mode.equals("ByTtoString")) {_shapes.sort(ShapeComp.Comp_By_ToString);}
		if(_mode.equals("ByAntiToString")) {_shapes.sort(ShapeComp.Comp_By_Anti_ToString);}

		///////////////Save & Load///////////////
		if (_mode.equals("Save")) {
			FileDialog Sfile = new FileDialog(new JFrame(),"Use a .png or .jpg extension", FileDialog.SAVE);
			Sfile.setVisible(true);
			String filename = Sfile.getFile();
			if (filename != null) {
				_shapes.save(Sfile.getDirectory()+ File.separator +Sfile.getFile());
			}
		}
		if (_mode.equals("Load")) {
			FileDialog Lfile = new FileDialog(new JFrame(),"Use a .png or .jpg extension", FileDialog.LOAD);
			Lfile.setVisible(true);
			String filename = Lfile.getFile();
			if (filename != null) {
				_shapes.load(Lfile.getDirectory()+ File.separator +Lfile.getFile());
			}

			drawShapes();
		}
	}

	// this method prints the clicked point and performs action on the  selected points 
	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);

		//////////////Circle//////////////

		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		//////////////Rectangle//////////////

		if(_mode.equals("Rect")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		//////////////Triangle//////////////

		if(_mode.equals("Triangle")) {
			if(_gs==null) {
				_triPoints.clear();
				_triPoints.add(p);
				_p1 = new Point2D(p);
			}
			else {
				if(_triPoints.size()<2) {
					_p2 = new Point2D(p);
					_triPoints.add(p);
				}
				else {
					_triPoints.add(p);
					Polygon2D tri = new Polygon2D(_triPoints);
					_gs = new GUIShape(tri,_fill, _color, 0);
					_shapes.add(_gs);
					_gs = null;
					_p1 = null;
					_p2 = null;
					_triPoints.clear();
				}
			}
		}

		//////////////Segment//////////////

		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}


		//////////////Polygon//////////////

		if(_mode.equals("Polygon")) {
			if(_gs==null) {
				_polyPoints.clear();
				_polyPoints.add(p);
				_p1 = new Point2D(p);
			}
			else {
				_polyPoints.add(p);
			}
		}


		//////////////Move//////////////

		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}

		//////////////Copy//////////////

		if(_mode.equals("Copy")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}

		//////////////Scale//////////////

		if(_mode.equals("Scale_90%")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(g!=null && s.isSelected()) {
					g.scale(p,0.9);
				}
			}
		}
		if(_mode.equals("Scale_110%")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(g!=null && s.isSelected()) {
					g.scale(p,1.1);
				}
			}
		}


		//////////////Rotate//////////////

		if(_mode.equals("Rotate")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				rotate(p);
				_p1=null;
			}
		}

		//////////////Select Point//////////////

		if(_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}

	// boolean method that represents if the object has been selected or not.
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	// this method make all the shapes at the arraylist to a selected shapes
	private void selectAll() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null) {
				s.setSelected(true);
			}
		}
	}

	// this method make all the shapes at the arraylist to a not selected shapes
	private void selectNone() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null) {
				s.setSelected(false);
			}
		}
	}

	// this method make all the shapes at the arraylist that is not selected to a selected shapes, and make all the shapes at the arraylis that is selected to a not selected shapes
	private void selectAnti() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && s.isSelected()) {
				s.setSelected(!s.isSelected());
				continue;
			}
			if(g!=null && !s.isSelected()) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	// this method move every selected shape at the arraylist by a given vec
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}

	// this method copy every selected shape at the arraylist
	private void copy() {
		int originalSize = _shapes.size();
		for(int i=0; i<originalSize; i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				GUI_Shapeable ns = s.copy();
				GeoShapeable ng = ns.getShape();
				ns.setTag(_shapes.size());
				_shapes.add(ns);
				ng.move(_p1);
			}
		}
	}

	// this method remove every selected shape at the arraylist
	private void remove() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && s.isSelected()) {
				_shapes.removeElementAt(i);
				i--;
			}
		}
	}

	//this function is rotate the shape by getting an angle and point
	private void rotate (Point2D p) {
		double angl = 0;
		double dx = p.x() - _p1.x() ;
		double dy = p.y() - _p1.y() ;
		double offset=0;
		if (dx < 0)
			offset=Math.PI;
		angl = Math.atan (dy/dx)+offset;
		angl=Math.toDegrees (angl);
		if(angl<0)
			angl += 360;
		System.out.println(angl);
		for(int i=0;i<_shapes.size() ;i++) {
			GUI_Shapeable s = _shapes.get (i) ;
			GeoShapeable g = s. getShape () ;
			if(s. isSelected ()&& g!=null) {
				PolygonPoints.clear();
				s.setSelected (true);
				g.rotate(_p1, angl);
			}
		}
	}


	// this method performs actions when a right click is pressed
	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");

		if (_mode.equals("Polygon")	&&	_p1 != null) {
			Polygon2D poly = new Polygon2D(_polyPoints);
			_gs = new GUIShape(poly,_fill, _color, 0);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			_polyPoints.clear();
			drawShapes();
		}
		else {
			_gs = null;
			_p1 = null;
			_polyPoints.clear();
			drawShapes();
		}

	}

	// this method performs actions while the mouse moves from click to click (/point to point) limited by the chosen shape mode
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);

			/////Circle/////
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}

			/////Rectangle/////
			if(_mode.equals("Rect")) {
				gs = new Rect2D(_p1, p);
			}

			/////Triangle/////
			if(_mode.equals("Triangle")) {

				//gs = new Triangle2D(_p1, _p2, p);
				if(_triPoints.size() <= 2) {
					Polygon2D tri = new Polygon2D(_triPoints);
					tri.addPoint(p);
					gs = tri;
				}
			}

			/////Segment/////
			if(_mode.equals("Segment")) {

				gs = new Segment2D(_p1, p);
			}

			/////Polygon/////
			if(_mode.equals("Polygon")) {
				Polygon2D poly = new Polygon2D(_polyPoints);
				poly.addPoint(p);
				gs = poly;

			}
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}

	// this method returns the collection of shapes
	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }

	// this method returns a String containing all the toString (of each shape in the collection)
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}