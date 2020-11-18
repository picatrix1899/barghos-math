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

import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.util.Nullable;
import org.barghos.math.point.Point3f;

/**
 * @author picatrix1899
 *
 */
public class Sphere3f implements InfiniteGeometricObject3
{
	protected final Point3f center = new Point3f();
	protected float radius;
	
	public Sphere3f()
	{
		set(0.0f, 0.0f, 0.0f, 1.0f);
	}
	
	public Sphere3f(Sphere3f s)
	{
		set(s);
	}
	
	public Sphere3f(Tup3fR center, float radius)
	{
		set(center, radius);
	}
	
	public Sphere3f(float x, float y, float z, float radius)
	{
		set(x, y, z, radius);
	}
	
	public Sphere3f set(Sphere3f s)
	{
		s.getCenter(this.center);
		setRadius(s.getRadius());
		return this;
	}
	
	public Sphere3f set(Tup3fR center, float radius)
	{
		return setCenter(center).setRadius(radius);
	}

	public Sphere3f set(float x, float y, float z, float radius)
	{
		return setCenter(x, y, z).setRadius(radius);
	}
	
	public Sphere3f setCenter(Tup3fR center)
	{
		this.center.set(center); return this;
	}

	public Sphere3f setCenter(float x, float y, float z)
	{
		this.center.set(x, y, z); return this;
	}
	
	public Sphere3f setRadius(float radius)
	{
		this.radius = radius; return this;
	}
	
	public Point3f getCenter()
	{
		return new Point3f(this.center);
	}
	
	public Point3f getCenter(@Nullable Point3f res)
	{
		if(res == null) res = new Point3f();
		return res.set(this.center);
	}
	
	public float getRadius()
	{
		return this.radius;
	}
	
	public String toString()
	{
		return "sphere3(center=" + this.center + ", radius=" + this.radius + "f)";
	}
}
