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

package org.barghos.math.vec3.pool;

import org.barghos.core.exception.ArgumentNullException;
import org.barghos.core.pool.DequePool;
import org.barghos.core.pool.api.Pool;
import org.barghos.core.tuple3.api.Tup3fRW;
import org.barghos.math.BarghosMath;
import org.barghos.math.vec3.Vec3fWrapper;


public final class Vec3fWrapperPool
{
	private static Pool<Vec3fWrapper> pool = new DequePool<>(Vec3fWrapper.class);
	
	private Vec3fWrapperPool() { }
	
	/**
	 * Returns an instance of {@link Vec3fWrapper} from the pool and does not reset it.
	 * This function is useful for reducing unneccessary calls and operations if a value is
	 * applied to to the tuple anyway before it is used.
	 * 
	 * @return A stored instance.
	 * 
	 * @since 1.0.0.0
	 */
	public static Vec3fWrapper getPlain()
	{
		return pool.get();
	}
	
	public static Vec3fWrapper get(Tup3fRW v)
	{	
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(v == null) throw new ArgumentNullException("v");
		}
		
		return pool.get().set(v);
	}
	
	public static void ensure(int count)
	{ 
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(count < 0) throw new IllegalArgumentException();
		}
		
		pool.ensure(count);
	}
	
	public static void store(Vec3fWrapper... instances)
	{
		for(Vec3fWrapper instance : instances)
			instance.setInternal(null);
		
		pool.store(instances);
	}
	
	public static void setInternalPool(Pool<Vec3fWrapper> pool)
	{
		if(BarghosMath.BUILD_FLAG__PARAMETER_CHECKS)
		{
			if(pool == null) throw new ArgumentNullException("pool");
		}
		
		Vec3fWrapperPool.pool = pool; 
	}
	
	public static Pool<Vec3fWrapper> getInternalPool()
	{
		return pool;
	}
}
