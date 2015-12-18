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

package ch.swaechter.webcms.core.dispatcher.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ch.swaechter.webcms.core.Globals;
import ch.swaechter.webcms.core.Util;
import ch.swaechter.webcms.core.dispatcher.Dispatcher;
import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.router.Route;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This class represents the resource dispatcher that handles JAR system resources.
 *
 * @author Simon Wächter
 */
public class JarResourceDispatcher implements Dispatcher
{
	/**
	 * Plugin manager who is responsible for all plugins.
	 */
	private final PluginManager pluginmanager;

	/**
	 * Settings of the system.
	 */
	private final Settings settings;

	/**
	 * Constructor with the plugin manager and the settings.
	 *
	 * @param pluginmanager Plugin manager
	 * @param settings Settings
	 */
	public JarResourceDispatcher(PluginManager pluginmanager, Settings settings)
	{
		this.pluginmanager = pluginmanager;
		this.settings = settings;
	}

	/**
	 * This method handles the given route. In case of a match the method should return true and the router
	 * will stop looking for the next dispatcher - otherwise return false and the router continues.
	 */
	@Override
	public boolean dispatchRoute(Route route) throws Exception
	{
		return dispatchResourceRoute(route);
	}

	/**
	 * Handle a resource route.
	 *
	 * @param route Route
	 * @return Status of the dispatch process
	 * @throws Exception An exception in case of a critical system failure
	 */
	private boolean dispatchResourceRoute(Route route) throws Exception
	{
		String uripath = route.getRequest().getRequestURI().substring(route.getRequest().getContextPath().length());
		String filepath = Util.trimFirstCharacters(uripath, Globals.DIRECTORY_SEPARATOR + settings.getResourcePrefix());
		if(!filepath.equals(Globals.DIRECTORY_SEPARATOR))
		{
			for(Plugin plugin : pluginmanager.getPlugins())
			{
				InputStream plugininputstream = plugin.getClass().getResourceAsStream(filepath);
				if(plugininputstream != null)
				{
					try
					{
						copyStream(plugininputstream, route.getResponse().getOutputStream());
						return true;
					}
					catch(IOException exception)
					{
						return false;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Copy the input stream to the output stream.
	 *
	 * @param inputstream Input stream
	 * @param outputstream Output stream
	 * @throws IOException An exception in case of an IO problem
	 */
	private void copyStream(InputStream inputstream, OutputStream outputstream) throws IOException
	{
		int counter = 0;
		byte buffer[] = new byte[8192];
		while((counter = inputstream.read(buffer, 0, buffer.length)) > 0)
		{
			outputstream.write(buffer, 0, counter);
			outputstream.flush();
		}
		outputstream.close();
		inputstream.close();
	}
}
