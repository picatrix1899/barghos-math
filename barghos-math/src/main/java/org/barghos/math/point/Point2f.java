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

package org.barghos.math.point;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.math.BarghosMath;
import org.barghos.math.vec2.Vec2f;

/**
 * Represents a 2-dimensional point with float components in euclidean space.
 * This is used for an absolute representation of a point in space.
 * 
 * @author picatrix1899
 * 
 * @since 1.0
 */
public class Point2f extends Vec2f
{
	/**
	 * Creates a new instance of {@link Point2f} with both components set to 0.0f.
	 * 
	 * @since 1.0
	 */
	public Point2f()
	{
		super();
	}
	
	/**
	 * Creates a new instance of {@link Point2f} with the components adopted from t.
	 * 
	 * @param t An instance of {@link Tup2fR} to adopt the components from.
	 * 
	 * @since 1.0
	 */
	public Point2f(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY());
	}
	
	/**
	 * Creates a new instance of {@link Point2i} with the components set to the corresponding parameters.
	 * 
	 * @param x The value for the x component.
	 * @param y The value for the y component.
	 * 
	 * @since 1.0
	 */
	public Point2f(float x, float y)
	{
		super(x, y);
	}
	
	@Override
	public Point2f setX(float x)
	{
		super.setX(x); return this;
	}
	
	@Override
	public Point2f setY(float y)
	{
		super.setY(y); return this;
	}
	
	@Override
	public Point2f set(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY());
	}
	
	@Override
	public Point2f set(float x, float y)
	{
		return setX(x).setY(y);
	}
	
	@Override
	public String toString()
	{
		return "point2f(x=" + this.x + "f, x=" + this.y + "f)";
	}
	
	@Override
	public Point2f clone()
	{
		return new Point2f(this);
	}
}
