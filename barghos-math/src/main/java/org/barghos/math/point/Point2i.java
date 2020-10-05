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
import org.barghos.core.tuple2.api.Tup2iR;
import org.barghos.math.BarghosMath;
import org.barghos.math.vector.vec2.Vec2i;

public class Point2i extends Vec2i
{
	/**
	 * Creates a new instance of {@link Point2i} with both components set to 0.
	 */
	public Point2i()
	{
		super();
	}
	
	/**
	 * Creates a new instance of {@link Point2i} with the components adopted from t
	 * 
	 * @param t An instance of {@link Tup2iR} to adopt the components from.
	 */
	public Point2i(Tup2iR t)
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
	public Point2i(int x, int y)
	{
		super(x, y);
	}
	
	@Override
	public Point2i setX(int x)
	{
		super.setX(x);
		
		return this;
	}
	
	@Override
	public Point2i setY(int y)
	{
		super.setY(y);
		
		return this;
	}
	
	@Override
	public Point2i set(Tup2iR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY());
	}
	
	@Override
	public Point2i set(int x, int y)
	{
		return setX(x).setY(y);
	}
	
	@Override
	public String toString()
	{
		return "point2i(x=" + this.x + ", x=" + this.y + ")";
	}
	
	@Override
	public Point2i clone()
	{
		return new Point2i(this);
	}
}
