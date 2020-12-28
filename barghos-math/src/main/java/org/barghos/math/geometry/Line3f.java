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
import org.barghos.math.point.Point3f;
import org.barghos.math.utils.Maths;
import org.barghos.math.vec3.Vec3f;
import org.barghos.math.vec3.pool.Vec3fPool;

public class Line3f implements FiniteGeometricObject3f
{
	protected final Point3f p1 = new Point3f();
	protected final Point3f p2 = new Point3f();

	public Line3f() { }
	
	public Line3f(Line3f l)
	{
		set(l);
	}
	
	public Line3f(Tup3fR p1, Tup3fR p2)
	{
		set(p1, p2);
	}
	
	public Line3f(Tup3fR p1, float x2, float y2, float z2)
	{
		set(p1, x2, y2, z2);
	}
	
	public Line3f(float x1, float y1, float z1, Tup3fR p2)
	{
		set(x1, y1, z1, p2);
	}
	public Line3f(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		set(x1, y1, z1, x2, y2, z2);
	}
	
	public Point3f getP1(Point3f res)
	{
		res.normal();
		
		if(res == null) res = new Point3f();
		return res.set(this.p1);
	}
	
	public Vec3f getP1(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		return res.set(this.p1);
	}
	
	public Point3f getP2(Point3f res)
	{
		if(res == null) res = new Point3f();
		return res.set(this.p2);
	}
	
	public Vec3f getP2(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		return res.set(this.p2);
	}
	
	public float getX1()
	{
		return this.p1.getX();
	}
	
	public float getX2()
	{
		return this.p2.getX();
	}
	
	public float getY1()
	{
		return this.p1.getY();
	}
	
	public float getY2()
	{
		return this.p2.getY();
	}
	
	public float getZ1()
	{
		return this.p1.getZ();
	}
	
	public float getZ2()
	{
		return this.p2.getZ();
	}
	
	public Line3f set(Line3f l)
	{
		l.getP1(this.p1);
		l.getP2(this.p2);
		return this;
	}
	
	public Line3f set(Tup3fR p1, Tup3fR p2)
	{
		return setP1(p1).setP2(p2);
	}
	
	public Line3f set(Tup3fR p1, float x2, float y2, float z2)
	{
		return setP1(p1).setP2(x2, y2, z2);
	}
	
	public Line3f set(float x1, float y1, float z1, Tup3fR p2)
	{
		return setP1(x1, y1, z1).setP2(p2);
	}
	
	public Line3f set(float x1, float y1, float z1, float x2, float y2, float z2)
	{
		return setP1(x1, y1, z1).setP2(x2, y2, z2);
	}
	
	public Line3f setP1(Tup3fR p)
	{
		this.p1.set(p);
		return this;
	}
	
	public Line3f setP1(float x, float y, float z)
	{
		return this;
	}
	
	public Line3f setP2(Tup3fR p)
	{
		this.p2.set(p);
		return this;
	}
	
	public Line3f setP2(float x, float y, float z)
	{
		this.p2.set(x, y, z);
		return this;
	}
	
	public float squaredLength()
	{
		Vec3f v = this.p2.sub(this.p1, Vec3fPool.get());
		float sqlength = v.squaredLength();
		Vec3fPool.store(v);
		
		return sqlength;
	}
	
	public float length()
	{
		return (float)Maths.sqrt(squaredLength());
	}
	
	public Vec3f vector(Vec3f res)
	{
		if(res == null) res = new Vec3f();
		
		return this.p2.sub(this.p1, res);
	}
	
	public Vec3f vector()
	{
		return this.p2.subN(this.p1);
	}

	@Override
	public Point3f[] getPoints()
	{
		return new Point3f[] {
			new Point3f(p1),
			new Point3f(p2)
		};
	}
	
	public String toString()
	{
		return "line3(p1=" + this.p1 + ", p2=" + this.p2 + ")";
	}
}
