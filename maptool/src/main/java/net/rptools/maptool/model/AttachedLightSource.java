/*
 * This software copyright by various authors including the RPTools.net
 * development team, and licensed under the LGPL Version 3 or, at your option,
 * any later version.
 *
 * Portions of this software were originally covered under the Apache Software
 * License, Version 1.1 or Version 2.0.
 *
 * See the file LICENSE elsewhere in this distribution for license details.
 */

package net.rptools.maptool.model;

public class AttachedLightSource {

	private GUID lightSourceId;
	private String direction;

	public AttachedLightSource() {
		// for serialization
	}

	public AttachedLightSource(LightSource source, Direction direction) {
		lightSourceId = source.getId();
		this.direction = direction.name();
	}

	public Direction getDirection() {
		return direction != null ? Direction.valueOf(direction) : Direction.CENTER;
	}

	public GUID getLightSourceId() {
		return lightSourceId;
	}
}
