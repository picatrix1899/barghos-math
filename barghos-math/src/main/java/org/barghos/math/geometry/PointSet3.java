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

import org.barghos.core.util.Nullable;
import org.barghos.math.matrix.Mat4;
import org.barghos.math.point.Point3;
import org.barghos.math.vector.vec3.Vec3;

public class PointSet3 implements FiniteGeometricObject3, Iterable<Point3>
{
	protected final List<Point3> points = new ArrayList<>();
	protected final Vec3 min = new Vec3();
	protected final Vec3 max = new Vec3();
	
	protected boolean isDirty;
	
	public PointSet3() {}
	
	public PointSet3(PointSet3 set)
	{
		set(set);
	}
	
	public PointSet3(Point3... points)
	{
		set(points);
	}
	
	public PointSet3(Collection<Point3> c)
	{
		set(c);
	}
	
	public PointSet3 set() { this.points.clear(); this.isDirty = true; return this; }
	
	public PointSet3 set(PointSet3 set)
	{
		this.points.clear();
		for(int i = 0; i < set.points.size(); i++)
			this.points.add(set.points.get(i));
		this.isDirty = true;
		return this;
	}
	
	public PointSet3 set(Point3... points)
	{
		this.points.clear();
		
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}

	public PointSet3 set(Collection<Point3> c)
	{
		this.points.clear();
		this.points.addAll(c);
		
		this.isDirty = true;
		return this;
	}
	
	public PointSet3 add(Point3... points)
	{
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet3 add(Collection<Point3> c)
	{
		this.points.addAll(c);
		this.isDirty = true;
		return this;
	}
	
	public PointSet3 transform(Mat4 t)
	{
		Point3[] p = new Point3[this.points.size()];
		
		for(int i = 0; i < this.points.size(); i++)
			p[i] = t.transform(this.points.get(i), (Point3)null);
		
		return set(p);
	}
	
	public PointSet3 transform(Mat4 t, @Nullable PointSet3 res)
	{
		if(res == null) res = new PointSet3();
		
		Point3[] p = new Point3[this.points.size()];
		
		for(int i = 0; i < this.points.size(); i++)
			p[i] = t.transform(this.points.get(i), (Point3)null);
		
		return res.set(p);
	}
	
	public PointSet3 getPointSet()
	{
		return new PointSet3(this);
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
		float minZ = Float.POSITIVE_INFINITY;
		
		float maxX = Float.NEGATIVE_INFINITY;
		float maxY = Float.NEGATIVE_INFINITY;
		float maxZ = Float.NEGATIVE_INFINITY;
		
		Point3 current;
		
		for(int i = 0; i < this.points.size(); i++)
		{
			current = this.points.get(i);
			
			if(current.getX() < minX)
				minX = current.getX();
			
			if(current.getY() < minY)
				minY = current.getY();
			
			if(current.getZ() < minZ)
				minZ = current.getZ();
			
			if(current.getX() > maxX)
				maxX = current.getX();
			
			if(current.getY() > maxY)
				maxY = current.getY();
			
			if(current.getZ() > maxZ)
				maxZ = current.getZ();
		}
		
		this.min.set(minX, minY, minZ);
		this.max.set(maxX, maxY, maxZ);
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
	
	public float getMinZ()
	{
		if(this.isDirty) calculateExtremes();
		
		return this.min.getZ();
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
	
	public float getMaxZ()
	{
		if(this.isDirty) calculateExtremes();

		return this.max.getZ();
	}

	public Iterator<Point3> iterator()
	{
		return this.points.iterator();
	}
}
