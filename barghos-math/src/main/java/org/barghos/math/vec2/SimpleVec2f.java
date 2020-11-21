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

package org.barghos.math.vec2;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.math.BarghosMath;
import org.barghos.math.vec2.api.Vec2fR;

/**
 * Represents a simplified 2-dimensional mathematical float vector in euclidean space.
 * This is a bare minimum version without most operations.
 * 
 *  @author picatrix1899
 *  
 *  @since 1.0
 */
public class SimpleVec2f implements Vec2fR, Tup2fW
{
	/**
	 * The x component of the vector.
	 * 
	 * @since 1.0
	 */
	protected float x;
	
	/**
	 * The y component of the vector.
	 * 
	 * @since 1.0
	 */
	protected float y;
	
	/**
	 * Creates a new instance of {@link SimpleVec2f} with the components set to 0.0f.
	 * 
	 * @since 1.0
	 */
	public SimpleVec2f()
	{
		set(0.0f, 0.0f);
	}
	
	/**
	 * Creates a new instance of {@link SimpleVec2f} with the components adopted from t.
	 * 
	 * @param t An instance of {@link Tup2fR} to adopt the components from.
	 * 
	 * @since 1.0
	 */
	public SimpleVec2f(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		set(t.getX(), t.getY());
	}

	/**
	 * Creates a new instance of {@link SimpleVec2f} with the components set to the corresponding parameters.
	 * 
	 * @param x The value for the x component.
	 * @param y The value for the y component.
	 * 
	 * @since 1.0
	 */
	public SimpleVec2f(float x, float y)
	{
		set(x, y);
	}
	
	@Override
	public float getX()
	{
		return this.x;
	}
	
	@Override
	public float getY()
	{
		return this.y;
	}
	
	@Override
	public SimpleVec2f setX(float x)
	{
		this.x = x;
		
		return this;
	}
	
	@Override
	public SimpleVec2f setY(float y)
	{
		this.y = y;
		
		return this;
	}
	
	@Override
	public SimpleVec2f set(Tup2fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return set(t.getX(), t.getY());
	}
	
	@Override
	public SimpleVec2f set(float value)
	{
		return setX(value).setY(value);
	}
	
	@Override
	public SimpleVec2f set(float x, float y)
	{
		return setX(x).setY(y);
	}
	
	/**
	 * Checks if the components are exactly positive zero and returns the result.
	 * 
	 * @return True if both components are exactly positive zero. False otherwise.
	 * 
	 * @since 1.0
	 */
	public boolean isZero()
	{
		return this.x == 0.0f && this.y == 0.0f;
	}
	
	/**
	 * Checks if the components are within the tolerance around zero and
	 * are therefore considered as zero and returns the result.
	 * 
	 * @param tolerance Defines the range before and after zero that should still be considered as zero.
	 * 
	 * @return True if both components are within tolerance around zero. False otherwise.
	 * 
	 * @since 1.0
	 */
	public boolean isZero(float tolerance)
	{
		return (Math.abs(this.x) <= tolerance) && (Math.abs(this.y) <= tolerance);
	}
	
	/**
	 * Checks if the components are finite values and returns the result.
	 * 
	 * @return True if both components are finite numbers. False otherwise.
	 * 
	 * @since 1.0
	 */
	public boolean isFinite()
	{
		return Float.isFinite(this.x) && Float.isFinite(this.y);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(getX());
		result = prime * result + Float.floatToIntBits(getY());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj == null) return false;
		if(!(obj instanceof Tup2fR)) return false;
		
		Tup2fR other = (Tup2fR) obj;
		if(Float.floatToIntBits(getX()) != Float.floatToIntBits(other.getX())) return false;
		if(Float.floatToIntBits(getY()) != Float.floatToIntBits(other.getY())) return false;
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return "simplevec2f(x=" + this.x + ", y=" + this.y + ")";
	}
	
	@Override
	public SimpleVec2f clone()
	{
		return new SimpleVec2f(this);
	}
}
