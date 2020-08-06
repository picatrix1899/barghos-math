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

package org.barghos.math.vector.vec4;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.tuple4.api.Tup4fR;
import org.barghos.core.tuple4.api.Tup4fW;
import org.barghos.core.util.Nullable;
import org.barghos.math.Maths;

/**
 * @author picatrix1899
 *
 * Represents a 4-dimensional mathematical vector in euclidean space.
 * This is a full featured version with common operations.
 */
public class Vec4 implements Vec4R, Tup4fW
{
	protected float x;
	protected float y;
	protected float z;
	protected float w;
	
	public Vec4() { set(0.0f, 0.0f, 0.0f, 0.0f); }
	
	public Vec4(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); set(t.getX(), t.getY(), t.getZ(), t.getW()); }
	
	public Vec4(float x, float y, float z, float w) { set(x, y, z, w); }
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }
	public float getW() { return this.w; }
	
	public Vec4 setX(float x) { this.x = x; return this; }
	public Vec4 setY(float y) { this.y = y; return this; }
	public Vec4 setZ(float z) { this.z = z; return this; }
	public Vec4 setW(float w) { this.w = w; return this; }
	
	public Vec4 set(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); return set(t.getX(), t.getY(), t.getZ(), t.getW()); }
	public Vec4 set(float value) { return setX(value).setY(value).setZ(value).setW(value); }
	public Vec4 set(float x, float y, float z, float w) { return setX(x).setY(y).setZ(z).setW(w); }
	
	public Vec4 add(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); return add(t.getX(), t.getY(), t.getZ(), t.getW()); }
	public Vec4 add(float scalar) { return add(scalar, scalar, scalar, scalar); }
	public Vec4 add(float x, float y, float z, float w) { return set(this.x + x, this.y + y, this.z + z, this.w + w); }
	public Vec4 add(Tup4fR t, @Nullable Vec4 res) { if(t == null) throw new ArgumentNullException("t"); return add(t.getX(), t.getY(), t.getZ(), t.getW(), res); }
	public Vec4 add(float scalar, @Nullable Vec4 res) { return add(scalar, scalar, scalar, scalar, res); }
	public Vec4 add(float x, float y, float z, float w, @Nullable Vec4 res) { if(res == null) res = new Vec4(); return res.set(this.x + x, this.y + y, this.z + z, this.w + w); }
	
	public Vec4 sub(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); return sub(t.getX(), t.getY(), t.getZ(), t.getW()); }
	public Vec4 sub(float scalar) { return sub(scalar, scalar, scalar, scalar); }
	public Vec4 sub(float x, float y, float z, float w) { return set(this.x - x, this.y - y, this.z - z, this.w - w); }
	public Vec4 sub(Tup4fR t, @Nullable Vec4 res) { if(t == null) throw new ArgumentNullException("t"); return sub(t.getX(), t.getY(), t.getZ(), t.getW(), res); }
	public Vec4 sub(float scalar, @Nullable Vec4 res) { return sub(scalar, scalar, scalar, scalar, res); }
	public Vec4 sub(float x, float y, float z, float w, @Nullable Vec4 res) { if(res == null) res = new Vec4(); return res.set(this.x - x, this.y - y, this.z - z, this.w - w); }
	
	public Vec4 mul(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); return mul(t.getX(), t.getY(), t.getZ(), t.getW()); }
	public Vec4 mul(float scalar) { return mul(scalar, scalar, scalar, scalar); }
	public Vec4 mul(float x, float y, float z, float w) { return set(this.x * x, this.y * y, this.z * z, this.w * w); }
	public Vec4 mul(Tup4fR t, @Nullable Vec4 res) { if(t == null) throw new ArgumentNullException("t"); return mul(t.getX(), t.getY(), t.getZ(), t.getW(), res); }
	public Vec4 mul(float scalar, @Nullable Vec4 res) { return mul(scalar, scalar, scalar, scalar, res); }
	public Vec4 mul(float x, float y, float z, float w, @Nullable Vec4 res) { if(res == null) res = new Vec4(); return res.set(this.x * x, this.y * y, this.z * z, this.w * w); }
	
	public Vec4 div(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); return div(t.getX(), t.getY(), t.getZ(), t.getW()); }
	public Vec4 div(float scalar) { return div(scalar, scalar, scalar, scalar); }
	public Vec4 div(float x, float y, float z, float w) { return set(this.x / x, this.y / y, this.z / z, this.w / w); }
	public Vec4 div(Tup4fR t, @Nullable Vec4 res) { if(t == null) throw new ArgumentNullException("t"); return div(t.getX(), t.getY(), t.getZ(), t.getW(), res); }
	public Vec4 div(float scalar, @Nullable Vec4 res) { return div(scalar, scalar, scalar, scalar, res); }
	public Vec4 div(float x, float y, float z, float w, @Nullable Vec4 res) { if(res == null) res = new Vec4(); return res.set(this.x / x, this.y / y, this.z / z, this.w / w); }
	
	public float length() { return (float)Maths.sqrt(squaredLength()); }
	
	public float lengthSafe() { return isZero() ? 0.0f : length(); }
	public float lengthSafe(float tolerance) { return isZero(tolerance) ? 0.0f : length(); }
	
	public float reciprocalLength() { return 1.0f / length(); }

	public float reciprocalLengthSafe() { return isZero() ? 0.0f : reciprocalLength(); }
	public float reciprocalLengthSafe(float tolerance) { return isZero(tolerance) ? 0.0f : reciprocalLength(); }
	
	public float squaredLength() { return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w; }

	public Vec4 normal() { return div(length()); }
	public Vec4 normal(@Nullable Vec4 res) { return div(length(), res); }
	
	public Vec4 normalSafe() { return isZero() ? set(0.0f, 0.0f, 0.0f, 0.0f) : normal(); }
	public Vec4 normalSafe(float tolerance) { if(tolerance < 0) throw new IllegalArgumentException(); return isZero(tolerance) ? set(0.0f, 0.0f, 0.0f, 0.0f) : normal(); }
	public Vec4 normalSafe(@Nullable Vec4 res) { if(res == null) res = new Vec4(); return isZero() ? res.set(0.0f, 0.0f, 0.0f, 0.0f) : normal(); }
	public Vec4 normalSafe(float tolerance, @Nullable Vec4 res) { if(tolerance < 0) throw new IllegalArgumentException(); if(res == null) res = new Vec4(); return isZero(tolerance) ? res.set(0.0f, 0.0f, 0.0f, 0.0f) : normal(); }
	
	public Vec4 invert() { return set(-this.x, -this.y, -this.z, -this.w); }
	public Vec4 invert(@Nullable Vec4 res) { if(res == null) res = new Vec4(); return res.set(-this.x, -this.y, -this.z, -this.w); }
	
	public double dot(Tup4fR t) { if(t == null) throw new ArgumentNullException("t"); return dot(t.getX(), t.getY(), t.getZ(), t.getW()); }
	public double dot(float x, float y, float z, float w) { return this.x * x + this.y * y + this.z * z + this.w * w; }
	
	public Vec4 snapToGrid(Tup4fR grid) { if(grid == null) throw new ArgumentNullException("t"); return snapToGrid(grid.getX(), grid.getY(), grid.getZ(), grid.getW()); }
	public Vec4 snapToGrid(float gx, float gy, float gz, float gw) { return set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz), Maths.gridSnap(this.w, gw)); }
	public Vec4 snapToGrid(Tup4fR grid, @Nullable Vec4 res) { if(grid == null) throw new ArgumentNullException("t"); return snapToGrid(grid.getX(), grid.getY(), grid.getZ(), grid.getW(), res); }
	public Vec4 snapToGrid(float gx, float gy, float gz, float gw, @Nullable Vec4 res) { if(res == null) res = new Vec4(); return res.set(Maths.gridSnap(this.x, gx), Maths.gridSnap(this.y, gy), Maths.gridSnap(this.z, gz), Maths.gridSnap(this.w, gw)); }

	public boolean isZero() { return this.x == 0.0f && this.y == 0.0f && this.z == 0.0f && this.w == 0.0f; }
	public boolean isZero(float tolerance) { return Math.abs(this.x) <= tolerance && Math.abs(this.y) <= tolerance && Math.abs(this.z) <= tolerance && Math.abs(this.w) <= tolerance; }
	
	public boolean isFinite() { return Float.isFinite(this.x) && Float.isFinite(this.y) && Float.isFinite(this.z) && Float.isFinite(this.w); }
	
	public String toString()
	{
		return "vec4(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Vec4)) return false;
		Vec4 v = (Vec4)obj;
		
		if(this.x != v.x) return false;
		if(this.y != v.y) return false;
		if(this.z != v.z) return false;
		if(this.w != v.w) return false;
		
		return true;
	}
}
