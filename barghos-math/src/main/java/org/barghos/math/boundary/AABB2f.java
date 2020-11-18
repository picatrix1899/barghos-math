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

package org.barghos.math.boundary;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.math.BarghosMath;
import org.barghos.math.point.Point2f;

/**
 * @author picatrix1899
 *
 */
public class AABB2f
{
	protected final Point2f min = new Point2f();
	protected final Point2f max = new Point2f();

	public AABB2f() { }
	
	public AABB2f(AABB2f aabb)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(aabb == null) throw new ArgumentNullException("aabb");
		}
		
		set(aabb);
	}
	
	public AABB2f(Tup2fR min, Tup2fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
			if(max == null) throw new ArgumentNullException("max");
		}
		
		set(min, max);
	}
	
	public AABB2f(float minX, float minY, Tup2fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(max == null) throw new ArgumentNullException("max");
		}
		
		set(minX, minY, max);
	}
	
	public AABB2f(Tup2fR min, float maxX, float maxY)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
		}
		
		set(min, maxX, maxY);
	}
	
	public AABB2f(float minX, float minY, float maxX, float maxY)
	{
		set(minX, minY, maxX, maxY);
	}
	
	public AABB2f set(AABB2f aabb)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(aabb == null) throw new ArgumentNullException("aabb");
		}
		
		aabb.getMin(this.min);
		aabb.getMax(this.max);
		
		return this;
	}
	
	public AABB2f set(Tup2fR min, Tup2fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
			if(max == null) throw new ArgumentNullException("max");
		}
		
		return setMin(min).setMax(max);
	}
	
	public AABB2f set(float minX, float minY, Tup2fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(max == null) throw new ArgumentNullException("max");
		}
		
		return setMin(minX, minY).setMax(max);
	}
	
	public AABB2f set(Tup2fR min, float maxX, float maxY)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
		}
		
		return setMin(min).setMax(maxX, maxY);
	}
	
	public AABB2f set(float minX, float minY, float maxX, float maxY)
	{
		return setMin(minX, minY).setMax(maxX, maxY);
	}
	
	public AABB2f setMin(Tup2fR min)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(min == null) throw new ArgumentNullException("min");
		}
		
		this.min.set(min);
		
		return this;
	}
	
	public AABB2f setMin(float x, float y)
	{
		this.min.set(x, y);
		
		return this;
	}
	
	public AABB2f setMax(Tup2fR max)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(max == null) throw new ArgumentNullException("max");
		}
		
		this.max.set(max);
		
		return this;
	}
	
	public AABB2f setMax(float x, float y)
	{
		this.max.set(x, y);
		
		return this;
	}
	
	public Point2f getMin()
	{
		return this.min.clone();
	}
	
	public Point2f getMin(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.min);
	}
	
	public float getMinX()
	{
		return this.min.getX();
	}
	
	public float getMinY()
	{
		return this.min.getY();
	}
	
	public Point2f getMax()
	{
		return this.max.clone();
	}
	
	public Point2f getMax(Point2f res)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(res == null) throw new ArgumentNullException("res");
		}
		
		return res.set(this.max);
	}
	
	public float getMaxX()
	{
		return this.max.getX();
	}
	
	public float getMaxY()
	{
		return this.max.getY();
	}
	
	public String toString()
	{
		return "aabb2f(min=" + this.min + ", max=" + this.max + ")";
	}
	
	public AABB2f clone()
	{
		return new AABB2f(this);
	}
}
