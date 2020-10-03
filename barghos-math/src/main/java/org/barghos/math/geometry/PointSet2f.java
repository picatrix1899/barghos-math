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

import org.barghos.core.exception.ArgumentNullException;

import org.barghos.math.BarghosMath;
import org.barghos.math.ITransform2f;
import org.barghos.math.matrix.Mat3f;
import org.barghos.math.point.Point2f;

public class PointSet2f implements FiniteGeometricObject2f, Iterable<Point2f>
{
	protected final List<Point2f> points = new ArrayList<>();
	
	protected final Point2f min = new Point2f();
	protected final Point2f max = new Point2f();
	
	private boolean isDirty;
	
	public PointSet2f() { }
	
	public PointSet2f(PointSet2f set)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(set == null) throw new ArgumentNullException("set");
		}
		
		set(set);
	}
	
	public PointSet2f(Point2f... points)
	{
		set(points);
	}
	
	public PointSet2f(Collection<Point2f> c)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(c == null) throw new ArgumentNullException("c");
		}
		
		set(c);
	}
	
	public PointSet2f set()
	{
		this.points.clear();
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet2f set(PointSet2f set)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(set == null) throw new ArgumentNullException("set");
		}
		
		this.points.clear();
		
		for(int i = 0; i < set.points.size(); i++)
			this.points.add(set.points.get(i));
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet2f set(Point2f... points)
	{
		this.points.clear();
		
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}

	public PointSet2f set(Collection<Point2f> c)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(c == null) throw new ArgumentNullException("c");
		}
		
		this.points.clear();
		
		this.points.addAll(c);
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet2f add(Point2f... points)
	{
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet2f add(Collection<Point2f> c)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(c == null) throw new ArgumentNullException("c");
		}
		
		this.points.addAll(c);
		
		this.isDirty = true;
		
		return this;
	}

	public PointSet2f getPointSet()
	{
		return new PointSet2f(this);
	}
	
	public Point2f[] getPoints()
	{
		return this.points.toArray(new Point2f[this.points.size()]);
	}
	
	private void calculateExtremes()
	{
		this.isDirty = false;
		
		// do calculations first internal for providing atomic values.
		float minX = Float.POSITIVE_INFINITY;
		float minY = Float.POSITIVE_INFINITY;
		
		float maxX = Float.NEGATIVE_INFINITY;
		float maxY = Float.NEGATIVE_INFINITY;
		
		for(int i = 0; i < this.points.size(); i++)
		{
			Point2f current = this.points.get(i);
			
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
	
	public Point2f getMin(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.min);
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

	public Point2f getMax(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.max);
	}
	
	public PointSet2f transform(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return transform(t, this);
	}
	
	public PointSet2f transform(ITransform2f t, PointSet2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		Mat3f m = Mat3f.transform2D(t);
		
		Point2f[] p = new Point2f[this.points.size()];

		for(int i = 0; i < this.points.size(); i++)
			p[i] = m.transform(this.points.get(i), new Point2f());

		return res.set(p);
	}
	
	public Iterator<Point2f> iterator()
	{
		return this.points.iterator();
	}
}
