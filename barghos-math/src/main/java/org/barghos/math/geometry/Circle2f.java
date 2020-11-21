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

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.util.Nullable;

import org.barghos.math.BarghosMath;
import org.barghos.math.boundary.AABB2f;
import org.barghos.math.matrix.Mat3f;
import org.barghos.math.point.Point2f;
import org.barghos.math.utils.Maths;
import org.barghos.math.utils.api.ITransform2f;
import org.barghos.math.vec2.Vec2f;
import org.barghos.math.vec2.pool.Vec2fPool;

/**
 * @author picatrix1899
 *
 */
public class Circle2f
{
	protected final Point2f center = new Point2f();
	
	protected float radius;
	
	public Circle2f()
	{
		set(0.0f, 0.0f, 1.0f);
	}
	
	public Circle2f(Circle2f c)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(c == null) throw new ArgumentNullException("c");
		}
		
		set(c);
	}

	public Circle2f(float x, float y, float radius)
	{
		set(x, y, radius);
	}
	
	public Circle2f(Tup2fR center, float radius)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
		}
		
		set(center, radius);
	}
	
	public Circle2f set(Circle2f c)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(c == null) throw new ArgumentNullException("c");
		}
		
		c.getCenter(this.center);
		
		setRadius(c.getRadius());
		
		return this;
	}

	public Circle2f set(Tup2fR center, float radius)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
		}
		
		return setCenter(center).setRadius(radius);
	}
	
	public Circle2f set(float x, float y, float radius)
	{
		return setCenter(x,y).setRadius(radius);
	}
	
	public Circle2f setCenter(Tup2fR center)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
		}
		
		this.center.set(center);
		
		return this;
	}
	
	public Circle2f setCenter(float x, float y)
	{
		this.center.set(x, y);
		
		return this;
	}

	public Circle2f setRadius(float radius)
	{
		this.radius = radius;
		
		return this;
	}
	
	public Point2f getCenter()
	{
		return this.center.clone();
	}
	
	public Point2f getCenter(@Nullable Point2f res)
	{
		if(res == null) res = new Point2f();
		
		return res.set(this.center);
	}
	
	public float getRadius()
	{
		return this.radius;
	}
	
	public Circle2f transform(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return transform(t, this);
	}
	
	public Circle2f transform(ITransform2f t, Circle2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		Mat3f m = Mat3f.transform2D(t);
		
		Vec2f v = Vec2fPool.get(this.radius, 0);
		Point2f p = this.center.clone();
		
		m.transform(p);
		m.transform(v);

		res.set(p, v.length());
		
		Vec2fPool.store(v);
		
		return res;
	}
	
	public AABB2f getAABB()
	{
		Vec2f min = this.center.sub(this.radius, Vec2fPool.get());
		Vec2f max = this.center.add(this.radius, Vec2fPool.get());
		
		AABB2f aabb = new AABB2f(min, max);
		
		Vec2fPool.store(min, max);
		
		return aabb;
	}
	
	public AABB2f getAABB(AABB2f res)
	{
		Vec2f min = this.center.sub(this.radius, Vec2fPool.get());
		Vec2f max = this.center.add(this.radius, Vec2fPool.get());
		
		res.set(min, max);
		
		Vec2fPool.store(min, max);
		
		return res;
	}
	
	public AABB2f getTransformedAABB(ITransform2f t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return getTransformedAABB(t, new AABB2f());
	}
	
	public AABB2f getTransformedAABB(ITransform2f t, AABB2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
			if(res == null) throw new ArgumentNullException("res");
		}
		
		Mat3f m = Mat3f.transform2D(t);
		
		Vec2f v = Vec2fPool.get(this.radius, 0);
		Point2f p = this.center.clone();
		
		m.transform(p);
		m.transform(v);
		
		Vec2f min = v.sub(this.radius, Vec2fPool.get());
		Vec2f max = v.add(this.radius, Vec2fPool.get());
		
		res.set(min, max);
		
		Vec2fPool.store(v, min, max);
		
		return res;
	}
	
	public float getCircumfence()
	{
		return this.radius * Maths.PIf * 2.0f;
	}
	
	public float getArea()
	{
		return this.radius * this.radius * Maths.PIf;
	}
	
	public String toString()
	{
		return "circle2f(center=" + this.center + ", radius=" + this.radius + ")";
	}
	
	public Circle2f clone()
	{
		return new Circle2f(this);
	}
}
