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

package org.barghos.math.vector.quat;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.pool.api.Pool;
import org.barghos.math.BarghosMath;
import org.barghos.core.pool.DequePool;

public final class QuatdPool
{
	private static Pool<Quatd> pool = new DequePool<>(Quatd.class);
	
	private QuatdPool() { }
	
	public static Quatd get()
	{
		return pool.get().set(1.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public static Quatd get(Quatd q)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(q == null) throw new ArgumentNullException("q");
		}
		
		return pool.get().set(q);
	}
	public static Quatd get(float x, float y, float z, float w)
	{
		return pool.get().set(x ,y ,z ,w);
	}
	
	public static void store(Quatd... instances)
	{
		pool.store(instances);
	}
	
	public static void ensure(int count)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(count < 0) throw new IllegalArgumentException();
		}
		
		pool.ensure(count);
	}
	
	public static void setInternalPool(Pool<Quatd> pool)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pool == null) throw new ArgumentNullException("pool");
		}
		
		QuatdPool.pool = pool;
	}
	
	public static Pool<Quatd> getInternalPool()
	{
		return pool;
	}
}
