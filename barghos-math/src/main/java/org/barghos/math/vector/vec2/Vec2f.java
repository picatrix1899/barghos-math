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

package org.barghos.math.vector.vec2;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple2.api.Tup2fR;
import org.barghos.core.tuple2.api.Tup2fW;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;

/**
 * Represents a 2-dimensional mathematical float vector in euclidean space.
 * This is a full featured version with common operations.
 * 
 *  @author picatrix1899
 */
public class Vec2f extends SimpleVec2f
{
		public Vec2f()
		{
			set(0.0f);
		}
		
		public Vec2f(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			set(t.getX(), t.getY());
		}

		public Vec2f(float x, float y)
		{
			set(x, y);
		}
		
		@Override
		public Vec2f setX(float x)
		{
			super.setX(x);
			
			return this;
		}
		
		@Override
		public Vec2f setY(float y)
		{
			super.setY(y);
			
			return this;
		}
		
		@Override
		public Vec2f set(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			super.set(t);
			
			return this;
		}
		
		@Override
		public Vec2f set(float value)
		{
			super.set(value);
			
			return this;
		}
		
		@Override
		public Vec2f set(float x, float y)
		{
			super.set(x, y);
			
			return this;
		}

		public Vec2f add(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return add(t.getX(), t.getY());
		}
		
		public Vec2f add(float scalar)
		{
			return add(scalar, scalar);
		}
		
		public Vec2f add(float x, float y)
		{
			return set(this.x + x, this.y + y);
		}
		
		public <T extends Tup2fW> T add(Tup2fR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return add(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2fW> T add(float scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return add(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T add(float x, float y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x + x, this.y + y);
		}
		
		public Vec2f addN(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return clone().add(t.getX(), t.getY());
		}
		
		public Vec2f addN(float scalar)
		{
			return clone().add(scalar, scalar);
		}
		
		public Vec2f addN(float x, float y)
		{
			return clone().add(x, y);
		}
		
		public Vec2f sub(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return sub(t.getX(), t.getY());
		}
		
		public Vec2f sub(float scalar)
		{
			return sub(scalar, scalar);
		}
		
		public Vec2f sub(float x, float y)
		{
			return set(this.x - x, this.y - y);
		}
		
		public <T extends Tup2fW> T sub(Tup2fR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return sub(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2fW> T sub(float scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return sub(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T sub(float x, float y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x - x, this.y - y);
		}

		public Vec2f subN(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return clone().sub(t.getX(), t.getY());
		}
		
		public Vec2f subN(float scalar)
		{
			return clone().sub(scalar, scalar);
		}
		
		public Vec2f subN(float x, float y)
		{
			return clone().sub(x, y);
		}
		
		public Vec2f mul(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return mul(t.getX(), t.getY());
		}
		
		public Vec2f mul(float scalar)
		{
			return mul(scalar, scalar);
		}
		
		public Vec2f mul(float x, float y)
		{
			return set(this.x * x, this.y * y);
		}
		
		public <T extends Tup2fW> T mul(Tup2fR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return mul(x, y, res);
		}
		
		public <T extends Tup2fW> T mul(float scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return mul(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T mul(float x, float y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x * x, this.y * y);
		}

		public Vec2f mulN(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return clone().mul(t.getX(), t.getY());
		}
		
		public Vec2f mulN(float scalar)
		{
			return clone().mul(scalar, scalar);
		}
		
		public Vec2f mulN(float x, float y)
		{
			return clone().mul(x, y);
		}
		
		public Vec2f div(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return div(t.getX(), t.getY());
		}
		
		public Vec2f div(float scalar)
		{
			return div(scalar, scalar);
		}
		
		public Vec2f div(float x, float y)
		{
			return set(this.x / x, this.y / y);
		}
		
		public <T extends Tup2fW> T div(Tup2fR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2fW> T div(float scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T div(float x, float y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x / x, this.y / y);
		}

		public Vec2f divN(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return clone().div(t.getX(), t.getY());
		}
		
		public Vec2f divN(float scalar)
		{
			return clone().div(scalar, scalar);
		}
		
		public Vec2f divN(float x, float y)
		{
			return clone().div(x, y);
		}
		
		public float length()
		{
			return (float)Maths.sqrt(squaredLength());
		}

		public float lengthSafe()
		{
			return isZero() ? 0.0f : length();
		}
		
		public float lengthSafe(float tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? 0.0f : length();
		}

		public float reciprocalLength()
		{
			return 1.0f / length();
		}

		public float reciprocalLengthSafe()
		{
			return isZero() ? 0.0f : reciprocalLength();
		}
		
		public float reciprocalLengthSafe(float tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? 0.0f : reciprocalLength();
		}

		public float squaredLength()
		{
			return this.x * this.x + this.y * this.y;
		}

		public Vec2f normal()
		{
			return div(length());
		}
		
		public <T extends Tup2fW> T normal(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(length(), res);
		}

		public Vec2f normalN()
		{
			return clone().div(length());
		}
		
		public Vec2f normalSafe()
		{
			return isZero() ? set(0.0f, 0.0f) : normal();
		}
		
		public Vec2f normalSafe(float tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? set(0.0f, 0.0f) : normal();
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T normalSafe(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return isZero() ? (T)res.set(0.0f, 0.0f) : normal(res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T normalSafe(float tolerance, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return isZero(tolerance) ? (T)res.set(0.0f, 0.0f) : normal(res);
		}

		public Vec2f normalSafeN()
		{
			return isZero() ? clone().set(0.0f, 0.0f) : clone().normal();
		}
		
		public Vec2f normalSafeN(float tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? clone().set(0.0f, 0.0f) : clone().normal();
		}
		
		public float dot(Tup2fR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return dot(t.getX(), t.getY());
		}
		public float dot(float x, float y)
		{
			return this.x * x + this.y * y;
		}

		public Vec2f snapToGrid(Tup2fR grid)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
			}
			
			return snapToGrid(grid.getX(), grid.getY());
		}
		
		public Vec2f snapToGrid(float gx, float gy)
		{
			return set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy));
		}
		
		public <T extends Tup2fW> T snapToGrid(Tup2fR grid, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return snapToGrid(grid.getX(), grid.getY(), res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T snapToGrid(float gx, float gy, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy));
		}

		public Vec2f snapToGridN(Tup2fR grid)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
			}
			
			return clone().snapToGrid(grid.getX(), grid.getY());
		}
		
		public Vec2f snapToGridN(float gx, float gy)
		{
			return clone().snapToGrid(gx, gy);
		}
		
		public Vec2f invert()
		{
			return set(-this.x, -this.y);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2fW> T invert(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			return (T)res.set(-this.x, -this.y);
		}
		
		public Vec2f invertN()
		{
			return clone().invert();
		}
		
		@Override
		public String toString()
		{
			return "vec2f(x=" + this.x + ", y=" + this.y + ")";
		}
		
		@Override
		public Vec2f clone()
		{
			return new Vec2f(this);
		}
}
