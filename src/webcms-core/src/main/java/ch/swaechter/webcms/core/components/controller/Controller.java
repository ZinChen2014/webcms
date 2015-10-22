/**
 * WebCMS - A content management system (CMS) based on Java
 * Copyright (C) 2015 Simon Wächter (waechter.simon@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/
 */

package ch.swaechter.webcms.core.components.controller;

import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This class represents a controller who is responsible for the interaction between a model
 * and a view. A controller contains several methods and each method represents a site action.
 *
 * @author Simon Wächter
 */
public abstract class Controller
{
	/**
	 * Container that provides all data.
	 */
	private final Container container;

	/**
	 * Constructor with the container.
	 *
	 * @param container Container that provides all data
	 */
	public Controller(Container container)
	{
		this.container = container;
	}

	/**
	 * Get the container.
	 *
	 * @return Container that provides all data
	 */
	public Container getContainer()
	{
		return container;
	}

	/**
	 * Get the settings of the system.
	 *
	 * @return Settings
	 */
	public Settings getSettings()
	{
		return container.getSettings();
	}
}
