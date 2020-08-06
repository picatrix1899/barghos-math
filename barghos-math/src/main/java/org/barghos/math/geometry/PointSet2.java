/*
MIT License

Copyright (c) 2019 picatrix1899

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.barghos.math.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point2;
import org.barghos.math.point.Point3;
import org.barghos.math.vector.vec2.Vec2;

public class PointSet2 implements Iterable<Point2>
{
	protected final List<Point2> points = new ArrayList<>();
	protected final Vec2 min = new Vec2();
	protected final Vec2 max = new Vec2();
	
	private boolean isDirty;
	
	public PointSet2() {}
	
	public PointSet2(PointSet2 set)
	{
		set(set);
	}
	
	public PointSet2(Point2... points)
	{
		set(points);
	}
	
	public PointSet2(Collection<Point2> c)
	{
		set(c);
	}
	
	public PointSet2 set() { this.points.clear(); this.isDirty = true; return this; }
	
	public PointSet2 set(PointSet2 set)
	{
		this.points.clear();
		for(int i = 0; i < set.points.size(); i++)
			this.points.add(set.points.get(i));
		this.isDirty = true;
		return this;
	}
	
	public PointSet2 set(Point2... points)
	{
		this.points.clear();
		
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}

	public PointSet2 set(Collection<Point2> c)
	{
		this.points.clear();
		this.points.addAll(c);
		
		this.isDirty = true;
		return this;
	}
	
	public PointSet2 add(Point2... points)
	{
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet2 add(Collection<Point2> c)
	{
		this.points.addAll(c);
		this.isDirty = true;
		return this;
	}

	public PointSet2 getPointSet()
	{
		return new PointSet2(this);
	}
	
	public Point3[] getPoints()
	{
		return this.points.toArray(new Point3[this.points.size()]);
	}
	
	private void calculateExtremes()
	{
		this.isDirty = false;
		
		// do calculations first internal for providing atomic values.
		float minX = Float.POSITIVE_INFINITY;
		float minY = Float.POSITIVE_INFINITY;
		
		float maxX = Float.NEGATIVE_INFINITY;
		float maxY = Float.NEGATIVE_INFINITY;
		
		Point2 current;
		
		for(int i = 0; i < this.points.size(); i++)
		{
			current = this.points.get(i);
			
			if(current.getX() < minX)
				minX = current.getX();
			
			if(current.getY() < minY)
				minY = current.getY();
			
			if(current.getX() > maxX)
				maxX = current.getX();
			
			if(current.getY() > maxY)
				maxY = current.getY();
		}
		
		this.min.set(minX, minY);
		this.max.set(maxX, maxY);
	}
	
	public float getMinX()
	{
		if(this.isDirty) calculateExtremes();

		return this.min.getX();
	}
	
	public float getMinY()
	{
		if(this.isDirty) calculateExtremes();

		return this.min.getY();
	}
	
	public float getMaxX()
	{
		if(this.isDirty) calculateExtremes();

		return this.max.getX();
	}
	
	public float getMaxY()
	{
		if(this.isDirty) calculateExtremes();

		return this.max.getY();
	}

	public PointSet2 transform(Mat4 t)
	{
		Point2[] p = new Point2[this.points.size()];

		for(int i = 0; i < this.points.size(); i++)
			p[i] = t.transform(this.points.get(i), new Point2());

		return set(p);
	}

	public PointSet2 transform(Mat4 t, PointSet2 res)
	{
		res = res != null ? res : new PointSet2();

		Point2[] p = new Point2[this.points.size()];

		for(int i = 0; i < this.points.size(); i++)
			p[i] = t.transform(this.points.get(i), new Point2());

		return res.set(p);
	}
	
	public Iterator<Point2> iterator()
	{
		return this.points.iterator();
	}
}
