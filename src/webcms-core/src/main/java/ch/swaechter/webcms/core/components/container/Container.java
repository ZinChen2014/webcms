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

package ch.swaechter.webcms.core.components.container;

import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This class represents a container that is accessible from the plugins and provides all
 * components.
 *
 * @author Simon Wächter
 */
public class Container
{
	/**
	 * Plugin manager that is responsible for the plugins.
	 */
	private final PluginManager pluginmanager;

	/**
	 * Settings that are used in the plugins.
	 */
	private final Settings settings;

	/**
	 * Constructor with the settings.
	 *
	 * @param settings Settings
	 */
	public Container(PluginManager pluginmanager, Settings settings)
	{
		this.pluginmanager = pluginmanager;
		this.settings = settings;
	}

	/**
	 * Get the plugin manager.
	 *
	 * @return Plugin manager
	 */
	public PluginManager getPluginManager()
	{
		return pluginmanager;
	}

	/**
	 * Get the settings.
	 *
	 * @return Settings
	 */
	public Settings getSettings()
	{
		return settings;
	}
}
