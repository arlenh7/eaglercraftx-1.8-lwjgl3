/*
 * Minimal stub for LWJGL STBIWriteCallback.
 */
package org.lwjgl.stb;

import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;

public abstract class STBIWriteCallback {

	protected STBIWriteCallback() {
	}

	public abstract void invoke(long context, long data, int size);

	protected ByteBuffer getData(long data, int size) {
		return MemoryUtil.memByteBuffer(data, size);
	}

	public void free() {

	}
}
