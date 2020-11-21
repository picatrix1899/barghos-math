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
import org.barghos.core.tuple3.api.Tup3fR;
import org.barghos.math.vec3.Vec3f;

public class Point3f extends Vec3f
{
	public Point3f() { super(); }
	public Point3f(Tup3fR t) { super(t); }
	public Point3f(float x, float y, float z) { super(x, y, z); }
	

	public Point3f setX(float x) { super.setX(x); return this; }
	public Point3f setY(float y) { super.setY(y); return this; }
	public Point3f setZ(float z) { super.setZ(z); return this; }
	
	public Point3f set(Tup3fR t) { if(t == null) throw new ArgumentNullException("t"); return set(t.getX(), t.getY(), t.getZ()); }
	public Point3f set(float x, float y, float z) { return setX(x).setY(y).setZ(z); }

	public String toString()
	{
		return "point3(" + this.x + "f, " + this.y +"f, " + this.z + "f)";
	}

	public Point3f clone()
	{
		return new Point3f(this);
	}
}
