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
import org.barghos.core.util.Nullable;
import org.barghos.math.Maths;

/**
 * @author picatrix1899
 *
 * Represents a 2-dimensional mathematical vector in euclidean space.
 * This is a full featured version with common operations.
 */
public class Vec2 implements Vec2R, Tup2fW
{
		protected float x;
		protected float y;
		
		public Vec2() { set(0.0f, 0.0f); }
		
		public Vec2(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); set(t.getX(), t.getY()); }

		public Vec2(float x, float y) { set(x, y); }
		
		public float getX() { return this.x; }
		public float getY() { return this.y; }
		
		public Vec2 setX(float x) { this.x = x; return this; }
		public Vec2 setY(float y) { this.y = y; return this; }
		
		public Vec2 set(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return set(t.getX(), t.getY()); }
		public Vec2 set(float value) { return setX(value).setY(value); }
		public Vec2 set(float x, float y) { return setX(x).setY(y); }

		public Vec2 add(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return add(t.getX(), t.getY()); }
		public Vec2 add(float scalar) { return add(scalar, scalar); }
		public Vec2 add(float x, float y) { return set(this.x + x, this.y + y); }
		public Vec2 add(Tup2fR t, @Nullable Vec2 res) { if(t == null) throw new ArgumentNullException("t"); return add(t.getX(), t.getY(), res); }
		public Vec2 add(float scalar, @Nullable Vec2 res) { return add(scalar, scalar, res); }
		public Vec2 add(float x, float y, @Nullable Vec2 res) { if(res == null) res = new Vec2(); return res.set(this.x + x, this.y + y);  }
		
		public Vec2 sub(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return sub(t.getX(), t.getY()); }
		public Vec2 sub(float scalar) { return sub(scalar, scalar); }
		public Vec2 sub(float x, float y) { return set(this.x - x, this.y - y); }
		public Vec2 sub(Tup2fR t, @Nullable Vec2 res) { if(t == null) throw new ArgumentNullException("t"); return sub(t.getX(), t.getY(), res); }
		public Vec2 sub(float scalar, @Nullable Vec2 res) { return sub(scalar, scalar, res); }
		public Vec2 sub(float x, float y, @Nullable Vec2 res) { if(res == null) res = new Vec2(); return res.set(this.x - x, this.y - y); }

		public Vec2 mul(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return mul(t.getX(), t.getY()); }
		public Vec2 mul(float scalar) { return mul(scalar, scalar); }
		public Vec2 mul(float x, float y) { return set(this.x * x, this.y * y); }
		public Vec2 mul(Tup2fR t, @Nullable Vec2 res) { if(t == null) throw new ArgumentNullException("t"); return mul(x, y, res); }
		public Vec2 mul(float scalar, @Nullable Vec2 res) { return mul(scalar, scalar, res); }
		public Vec2 mul(float x, float y, @Nullable Vec2 res) { if(res == null) res = new Vec2(); return res.set(this.x * x, this.y * y); }

		public Vec2 div(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return div(t.getX(), t.getY()); }
		public Vec2 div(float scalar) { return div(scalar, scalar); }
		public Vec2 div(float x, float y) { return set(this.x / x, this.y / y); }
		public Vec2 div(Tup2fR t, @Nullable Vec2 res) { if(t == null) throw new ArgumentNullException("t"); return div(t.getX(), t.getY(), res); }
		public Vec2 div(float scalar, @Nullable Vec2 res) { return div(scalar, scalar, res); }
		public Vec2 div(float x, float y, @Nullable Vec2 res) { if(res == null) res = new Vec2(); return res.set(this.x / x, this.y / y); }

		public float length() { return (float)Maths.sqrt(squaredLength()); }

		public float lengthSafe() { return isZero() ? 0.0f : length(); }
		public float lengthSafe(float tolerance) { if(tolerance < 0) throw new IllegalArgumentException(); return isZero(tolerance) ? 0.0f : length(); }

		public float reciprocalLength() { return 1.0f / length(); }

		public float reciprocalLengthSafe() { return isZero() ? 0.0f : reciprocalLength();  }
		public float reciprocalLengthSafe(float tolerance) { if(tolerance < 0) throw new IllegalArgumentException(); return isZero(tolerance) ? 0.0f : reciprocalLength();  }

		public float squaredLength() { return this.x * this.x + this.y * this.y; }

		public Vec2 normal() { return div(length()); }
		public Vec2 normal(@Nullable Vec2 res) { return div(length(), res); }

		public Vec2 normalSafe() { return isZero() ? set(0.0f, 0.0f) : normal(); }
		public Vec2 normalSafe(float tolerance) { if(tolerance < 0) throw new IllegalArgumentException(); return isZero(tolerance) ? set(0.0f, 0.0f) : normal(); }
		public Vec2 normalSafe(@Nullable Vec2 res) { return isZero() ? res.set(0.0f, 0.0f) : normal(res); }
		public Vec2 normalSafe(float tolerance, @Nullable Vec2 res) { if(tolerance < 0) throw new IllegalArgumentException(); return isZero(tolerance) ? res.set(0.0f, 0.0f) : normal(res); }

		public float dot(Tup2fR t) { if(t == null) throw new ArgumentNullException("t"); return dot(t.getX(), t.getY()); }
		public float dot(float x, float y) { return this.x * x + this.y * y; }

		public Vec2 snapToGrid(Tup2fR grid) { if(grid == null) throw new ArgumentNullException("grid"); return snapToGrid(grid.getX(), grid.getY()); }
		public Vec2 snapToGrid(float gx, float gy) { return set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy)); }
		public Vec2 snapToGrid(Tup2fR grid, Vec2 res) { if(grid == null) throw new ArgumentNullException("grid"); return snapToGrid(grid.getX(), grid.getY(), res); }
		public Vec2 snapToGrid(float gx, float gy, @Nullable Vec2 res) { if(res == null) res = new Vec2(); return res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy)); }

		public Vec2 invert() { return set(-this.x, -this.y); }
		public Vec2 invert(@Nullable Vec2 res) { if(res == null) res = new Vec2(); return res.set(-this.x, -this.y); }
		
		public boolean isZero() { return this.x == 0.0f && this.y == 0.0f; }
		public boolean isZero(float tolerance) { return (Math.abs(this.x) <= tolerance) && (Math.abs(this.y) <= tolerance); }
		
		public boolean isFinite() { return Float.isFinite(this.x) && Float.isFinite(this.y); }
		
		public String toString()
		{
			return "vec2(" + this.x + ", " + this.y + ")";
		}
		
		public boolean equals(Object obj)
		{
			if(obj == null) return false;
			if(!(obj instanceof Vec2)) return false;
			Vec2 v = (Vec2)obj;
			
			if(this.x != v.x) return false;
			if(this.y != v.y) return false;
			
			return true;
		}
		
		public Vec2 clone()
		{
			return new Vec2(this);
		}
}
