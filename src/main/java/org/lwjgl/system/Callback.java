/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package org.lwjgl.system;

public abstract class Callback extends Pointer.Default implements NativeResource {

	protected Callback(long address) {
		super(address);
	}

	protected Callback() {
		super(0);
	}

	@Override
	public void free() {
	}

	/**
	 * Returns a human-readable callback description.
	 */
	@Override
	public String toString() {
		return String.format("%s callback [0x%X]", getClass().getSimpleName(), address);
	}

	/**
	 * Creates a callback from the specified function interface.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends CallbackI> T create(T callbackI) {
		return callbackI;
	}

	/**
	 * Frees a callback. Safe to call with null.
	 */
	public static void free(CallbackI callback) {
		if (callback instanceof Callback) {
			((Callback) callback).free();
		}
	}
}
