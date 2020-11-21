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

import org.barghos.math.BarghosMath;
import org.barghos.math.point.Point2f;
import org.barghos.math.vec2.Vec2f;

/**
 * @author picatrix1899
 *
 */
public class Rectangle2f
{
	protected final Point2f center = new Point2f();
	protected final Vec2f halfExtend = new Vec2f();
	
	public Rectangle2f() { }
	
	public Rectangle2f(Rectangle2f rect)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rect == null) throw new ArgumentNullException("rect");
		}
		
		set(rect);
	}
	
	public Rectangle2f(Tup2fR center, Tup2fR he)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
			if(he == null) throw new ArgumentNullException("he");
		}
		
		set(center, he);
	}
	
	public Rectangle2f(Tup2fR center, float heX, float heY)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
		}
		
		set(center, heX, heY);
	}
	
	public Rectangle2f(float cX, float cY, Tup2fR he)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(he == null) throw new ArgumentNullException("he");
		}
		
		set(cX, cY, he);
	}
	
	public Rectangle2f(float cX, float cY, float heX, float heY)
	{
		set(cX, cY, heX, heY);
	}
	
	public Rectangle2f set(Rectangle2f rect)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(rect == null) throw new ArgumentNullException("rect");
		}
		
		rect.getCenter(this.center);
		rect.getHalfExtend(this.halfExtend);
		
		return this;
	}
	
	public Rectangle2f set(Tup2fR center, Tup2fR he)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
			if(he == null) throw new ArgumentNullException("he");
		}
		
		return setCenter(center).setHalfExtend(he);
	}
	
	public Rectangle2f set(Tup2fR center, float heX, float heY)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
		}
		
		return setCenter(center).setHalfExtend(heX, heY);
	}
	
	public Rectangle2f set(float cX, float cY, Tup2fR he)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(he == null) throw new ArgumentNullException("he");
		}
		
		return setCenter(cX, cY).setHalfExtend(he);
	}
	
	public Rectangle2f set(float cX, float cY, float heX, float heY)
	{
		return setCenter(cX, cY).setHalfExtend(heX, heY);
	}
	
	public Rectangle2f setCenter(Tup2fR center)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(center == null) throw new ArgumentNullException("center");
		}
		
		this.center.set(center);
		
		return this;
	}
	
	public Rectangle2f setCenter(float x, float y)
	{
		this.center.set(x, y);
		
		return this;
	}
	
	public Rectangle2f setHalfExtend(Tup2fR he)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(he == null) throw new ArgumentNullException("he");
		}
		
		this.halfExtend.set(he);
		
		return this;
	}
	
	public Rectangle2f setHalfExtend(float x, float y)
	{
		this.halfExtend.set(x, y);
		
		return this;
	}
	
	public Point2f getCenter()
	{
		return this.center.clone();
	}
	
	public Point2f getCenter(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.center);
	}
	
	public Vec2f getHalfExtend()
	{
		return this.halfExtend.clone();
	}
	
	public Vec2f getHalfExtend(Vec2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.halfExtend);
	}
	
	public float getCircumfence()
	{
		return (this.halfExtend.getX() * 2.0f + this.halfExtend.getY() * 2.0f) * 2.0f;
	}
	
	public float getArea()
	{
		return (this.halfExtend.getX() * 2.0f) * (this.halfExtend.getY() * 2.0f);
	}
	
	public String toString()
	{
		return "rectangle2f(center=" + this.center + ", halfExtend=" + this.halfExtend + ")";
	}
	
	public Rectangle2f clone()
	{
		return new Rectangle2f(this);
	}
}
