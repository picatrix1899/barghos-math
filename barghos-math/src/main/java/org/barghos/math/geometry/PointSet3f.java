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
import org.barghos.math.point.Point3f;
import org.barghos.math.vec3.Vec3f;

public class PointSet3f implements FiniteGeometricObject3f, Iterable<Point3f>
{
	protected final List<Point3f> points = new ArrayList<>();
	protected final Vec3f min = new Vec3f();
	protected final Vec3f max = new Vec3f();
	
	protected boolean isDirty;
	
	public PointSet3f() {}
	
	public PointSet3f(PointSet3f set)
	{
		set(set);
	}
	
	public PointSet3f(Point3f... points)
	{
		set(points);
	}
	
	public PointSet3f(Collection<Point3f> c)
	{
		set(c);
	}
	
	public PointSet3f set() { this.points.clear(); this.isDirty = true; return this; }
	
	public PointSet3f set(PointSet3f set)
	{
		this.points.clear();
		for(int i = 0; i < set.points.size(); i++)
			this.points.add(set.points.get(i));
		this.isDirty = true;
		return this;
	}
	
	public PointSet3f set(Point3f... points)
	{
		this.points.clear();
		
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}

	public PointSet3f set(Collection<Point3f> c)
	{
		this.points.clear();
		this.points.addAll(c);
		
		this.isDirty = true;
		return this;
	}
	
	public PointSet3f add(Point3f... points)
	{
		for(int i = 0; i < points.length; i++)
			this.points.add(points[i]);
		
		this.isDirty = true;
		
		return this;
	}
	
	public PointSet3f add(Collection<Point3f> c)
	{
		this.points.addAll(c);
		this.isDirty = true;
		return this;
	}
	
	public PointSet3f transform(Mat4 t)
	{
		Point3f[] p = new Point3f[this.points.size()];
		
		for(int i = 0; i < this.points.size(); i++)
			p[i] = t.transform(this.points.get(i), (Point3f)null);
		
		return set(p);
	}
	
	public PointSet3f transform(Mat4 t, @Nullable PointSet3f res)
	{
		if(res == null) res = new PointSet3f();
		
		Point3f[] p = new Point3f[this.points.size()];
		
		for(int i = 0; i < this.points.size(); i++)
			p[i] = t.transform(this.points.get(i), (Point3f)null);
		
		return res.set(p);
	}
	
	public PointSet3f getPointSet()
	{
		return new PointSet3f(this);
	}
	
	public Point3f[] getPoints()
	{
		return this.points.toArray(new Point3f[this.points.size()]);
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
		
		Point3f current;
		
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

	public Iterator<Point3f> iterator()
	{
		return this.points.iterator();
	}
}
