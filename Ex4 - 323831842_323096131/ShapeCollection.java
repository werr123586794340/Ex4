package Exe.Ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private static final String String = null;
	private ArrayList<GUI_Shapeable> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}

	//this method returns a reference to the i'th element at the shapes collection
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	// this method returns the size of the shapes collection
	@Override
	public int size() {
		return _shapes.size();
	}

	// this method remove (and return) the shape at the i'th location
	@Override
	public GUI_Shapeable removeElementAt(int i) {

		return _shapes.remove(i);
	}

	// this method adds the element to the collection at the last position
	@Override
	public void addAt(GUI_Shapeable s, int i) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(i, s);
		}
	}

	// this method adds the element to the collection in the i'th position
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}

	// this method constructs a copy of this collection
	@Override
	public ShapeCollectionable copy() {

		ShapeCollection copy = new ShapeCollection();
		ArrayList<GUI_Shapeable> copyArray = new ArrayList();
		for(int i = 0; i < _shapes.size(); i++) {
			copyArray.add(_shapes.get(i).copy());
		}
		copy._shapes = copyArray;
		return copy;

	}

	// this method sorts this shapes collection according to the comparator - in increasing order
	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);
	}

	// this method removes all the elements from the collection
	@Override
	public void removeAll() {
		_shapes.clear();
	}

	// this method saves this shapes collection to a text file.
	@Override
	public void save(String file) {
		try {
			FileWriter NewFile = new FileWriter(file);
			for (int i=0; i<_shapes.size(); i++) {
				if(_shapes.get(i) != null) {
					String s = _shapes.get(i).toString();
					NewFile.append(s+'\n');
				}
			}
			NewFile.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	//  this method get a file and
	@Override
	public void load(String file) {
		_shapes.clear();

		try { 
			FileReader Freader = new FileReader(file);
			BufferedReader BReader = new BufferedReader(Freader);
			String line = BReader.readLine();
			while (line != null) {
				_shapes.add(new GUIShape(line));

			}
			BReader.close();
		}

		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}


	// this method returns the minimal axis parallel containing all the shapes in this collection.
	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;

		double minimum_x = Double.MAX_VALUE;
		double maximum_x = -Double.MAX_VALUE;
		double minimum_y = Double.MAX_VALUE;
		double maximum_y = -Double.MAX_VALUE;
		Circle2D copy ;
		Point2D arrayP[] ;
		int i = 0 ;
		while (i < this._shapes.size()) {
			if(_shapes.get(i).getShape() instanceof Circle2D) {
				copy = (Circle2D) _shapes.get(i).getShape();
				if (copy.getCenter().x() + copy.getRadius() > maximum_x ) {
					maximum_x = copy.getCenter().x() + copy.getRadius();
				}

				else if(copy.getCenter().x() - copy.getRadius() < minimum_x ) {
					minimum_x = copy.getCenter().x() - copy.getRadius();
				}

				else if (copy.getCenter().y() + copy.getRadius() > maximum_y) {
					maximum_y = copy.getCenter().y() + copy.getRadius();
				}

				else if ((copy.getCenter().y() - copy.getRadius() < minimum_y)) {
					minimum_y = copy.getCenter().y() - copy.getRadius();
				}

			}
			else {
				arrayP = _shapes.get(i).getShape().getPoints() ;
				for(int j = 0 ; j < arrayP.length&& arrayP[j] !=null ; j ++ ) {
					if(arrayP[j].x() > maximum_x) {
						maximum_x=arrayP[j].x();
					}
					if((arrayP[j].x() < minimum_x)) {
						minimum_x = arrayP[j] .x();
					}
					if(arrayP[j].y() > maximum_y) {
						maximum_y = arrayP[j].y();
					}
					if((arrayP[j].y() < minimum_y)) {
						minimum_y = arrayP[j].y();
					}
				}
			}
			i++;
		}

		Point2D sidemax = new Point2D(maximum_x , maximum_y) ;
		Point2D sidemin = new Point2D(minimum_x , minimum_y) ;
		return(new Rect2D(sidemin, sidemax));

	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0; i<size(); i=i+1) {
			ans += this.get(i).toString();
		}
		return ans;
	}
}
