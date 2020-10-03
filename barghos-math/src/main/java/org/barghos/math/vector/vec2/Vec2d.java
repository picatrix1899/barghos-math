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
import org.barghos.core.tuple2.api.Tup2dR;
import org.barghos.core.tuple2.api.Tup2dW;
import org.barghos.math.BarghosMath;
import org.barghos.math.Maths;

/**
 * Represents a 2-dimensional mathematical float vector in euclidean space.
 * This is a full featured version with common operations.
 * 
 *  @author picatrix1899
 */
public class Vec2d extends SimpleVec2d
{
		public Vec2d() { set(0.0, 0.0); }
		
		public Vec2d(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			set(t.getX(), t.getY());
		}

		public Vec2d(double x, double y) { set(x, y); }
		
		@Override
		public Vec2d setX(double x) { super.setX(x); return this; }
		
		@Override
		public Vec2d setY(double y) { super.setY(y); return this; }
		
		@Override
		public Vec2d set(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			super.set(t);
			
			return this;
		}
		
		@Override
		public Vec2d set(double value) { return setX(value).setY(value); }
		
		@Override
		public Vec2d set(double x, double y) { super.set(x, y); return this; }

		public Vec2d add(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return add(t.getX(), t.getY());
		}
		public Vec2d add(double scalar) { return add(scalar, scalar); }
		public Vec2d add(double x, double y) { return set(this.x + x, this.y + y); }
		
		public <T extends Tup2dW> T add(Tup2dR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return add(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2dW> T add(double scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return add(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T add(double x, double y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x + x, this.y + y);
		}
		
		public Vec2d sub(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return sub(t.getX(), t.getY());
		}
		public Vec2d sub(double scalar) { return sub(scalar, scalar); }
		public Vec2d sub(double x, double y) { return set(this.x - x, this.y - y); }
		
		public <T extends Tup2dW> T sub(Tup2dR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return sub(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2dW> T sub(double scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return sub(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T sub(double x, double y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x - x, this.y - y);
		}

		public Vec2d mul(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return mul(t.getX(), t.getY());
		}
		
		public Vec2d mul(double scalar) { return mul(scalar, scalar); }
		public Vec2d mul(double x, double y) { return set(this.x * x, this.y * y); }
		
		public <T extends Tup2dW> T mul(Tup2dR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return mul(x, y, res);
		}
		
		public <T extends Tup2dW> T mul(double scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return mul(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T mul(double x, double y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x * x, this.y * y);
		}

		public Vec2d div(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return div(t.getX(), t.getY());
		}
		public Vec2d div(double scalar) { return div(scalar, scalar); }
		public Vec2d div(double x, double y) { return set(this.x / x, this.y / y); }
		
		public <T extends Tup2dW> T div(Tup2dR t, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(t.getX(), t.getY(), res);
		}
		
		public <T extends Tup2dW> T div(double scalar, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(scalar, scalar, res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T div(double x, double y, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(this.x / x, this.y / y);
		}

		public double length() { return (float)Maths.sqrt(squaredLength()); }

		public double lengthSafe() { return isZero() ? 0.0 : length(); }
		public double lengthSafe(double tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? 0.0 : length();
		}

		public double reciprocalLength() { return 1.0 / length(); }

		public double reciprocalLengthSafe() { return isZero() ? 0.0 : reciprocalLength();  }
		public double reciprocalLengthSafe(double tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? 0.0 : reciprocalLength();
		}

		public double squaredLength() { return this.x * this.x + this.y * this.y; }

		public Vec2d normal() { return div(length()); }
		public <T extends Tup2dW> T normal(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return div(length(), res);
		}

		public Vec2d normalSafe() { return isZero() ? set(0.0, 0.0) : normal(); }
		public Vec2d normalSafe(double tolerance)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
			}
			
			return isZero(tolerance) ? set(0.0, 0.0) : normal();
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T normalSafe(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return isZero() ? (T)res.set(0.0, 0.0) : normal(res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T normalSafe(double tolerance, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(tolerance < 0) throw new IllegalArgumentException();
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return isZero(tolerance) ? (T)res.set(0.0, 0.0) : normal(res);
		}

		public double dot(Tup2dR t)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(t == null) throw new ArgumentNullException("t");
			}
			
			return dot(t.getX(), t.getY());
		}
		public double dot(double x, double y) { return this.x * x + this.y * y; }

		public Vec2d snapToGrid(Tup2dR grid)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
			}
			
			return snapToGrid(grid.getX(), grid.getY());
		}
		
		public Vec2d snapToGrid(double gx, double gy) { return set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy)); }
		public <T extends Tup2dW> T snapToGrid(Tup2dR grid, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(grid == null) throw new ArgumentNullException("grid");
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return snapToGrid(grid.getX(), grid.getY(), res);
		}
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T snapToGrid(double gx, double gy, T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			
			return (T)res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy));
		}

		public Vec2d invert() { return set(-this.x, -this.y); }
		
		@SuppressWarnings("unchecked")
		public <T extends Tup2dW> T invert(T res)
		{
			if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
			{
				if(res == null) throw new ArgumentNullException("res");
			}
			return (T)res.set(-this.x, -this.y);
		}
		
		@Override
		public String toString()
		{
			return "vec2d(x=" + this.x + ", y=" + this.y + ")";
		}
		
		@Override
		public Vec2d clone()
		{
			return new Vec2d(this);
		}
}
