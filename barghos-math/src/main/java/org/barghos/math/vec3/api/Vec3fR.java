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

package org.barghos.math.vec3.api;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.math.BarghosMath;
import org.barghos.math.utils.Maths;

/**
 * This interface grants readonly access to a 3-dimensional mathematical float vector.
 * 
 * @author picatrix1899
 * 
 * @since 1.0.0.0
 */
public interface Vec3fR extends Tup3fR
{
	default float length()
	{
		return Maths.sqrt(squaredLength());
	}
	
	default float lengthSafe()
	{
		return isZero() ? 0.0f : length();
	}
	
	default float lengthSafe(float tolerance)
	{
		return isZero(tolerance) ? 0.0f : length();
	}
	
	default float reciprocalLength()
	{
		return 1.0f / length();
	}

	default float reciprocalLengthSafe()
	{
		return isZero() ? 0.0f : reciprocalLength();
	}
	
	default float reciprocalLengthSafe(float tolerance)
	{
		return isZero(tolerance) ? 0.0f : reciprocalLength();
	}

	default float squaredLength()
	{
		return getX() * getX() + getY() * getY() + getZ() * getZ();
	}
	
	default float dot(Tup3fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return dot(t.getX(), t.getY(), t.getZ());
	}
	
	default float dot(float x, float y, float z)
	{
		return getX() * x + getY() * y + getZ() * z;
	}
	
	default float dot(Tup4fR t)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(t == null) throw new ArgumentNullException("t");
		}
		
		return dot(t.getX(), t.getY(), t.getZ(), t.getW());
	}
	
	default float dot(float x, float y, float z, float w)
	{
		return getX() * x + getY() * y + getZ() * z;
	}
	
	default boolean isZero()
	{
		return getX() == 0.0f && getY() == 0.0f && getZ() == 0.0f;
	}
	
	default boolean isZero(float tolerance)
	{
		return (Math.abs(getX()) <= tolerance) && (Math.abs(getY()) <= tolerance) && (Math.abs(getZ()) <= tolerance);
	}
	
	default boolean isFinite()
	{
		return Float.isFinite(getX()) && Float.isFinite(getY()) && Float.isFinite(getZ());
	}
}
