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

package ch.swaechter.webcms.core.dispatcher.mvc;

import java.lang.reflect.Method;

import ch.swaechter.webcms.core.Globals;
import ch.swaechter.webcms.core.Util;
import ch.swaechter.webcms.core.dispatcher.Dispatcher;
import ch.swaechter.webcms.core.dispatcher.mvc.controller.Controller;
import ch.swaechter.webcms.core.dispatcher.mvc.view.View;
import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.router.Route;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This class represents a dispatcher that handles all MVC component.
 *
 * @author Simon Wächter
 */
public class MvcDispatcher implements Dispatcher
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
	public MvcDispatcher(PluginManager pluginmanager, Settings settings)
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
		String path =  Util.trimFirstCharacters(route.getPath(), Globals.DIRECTORY_SEPARATOR);
		String[] parameters = path.split(Globals.DIRECTORY_SEPARATOR);
		String controllerstring = (parameters.length > 0) ? parameters[0] : new String();
		String actionstring = (parameters.length > 1) ? parameters[1] : new String();
		String controllername = controllerstring + Globals.CONTROLLER_CLASS_SUFFIX;
		for(Plugin plugin : pluginmanager.getPlugins())
		{
			for(Controller controller : plugin.getControllers(pluginmanager, settings))
			{
				if(controller.getClass().getSimpleName().equalsIgnoreCase(controllername))
				{
					try
					{
						Method method = controller.getClass().getDeclaredMethod(actionstring);
						View view = (View) method.invoke(controller);
						view.processRoute(plugin, route);
						return true;
					}
					catch(Exception exception)
					{
						if(!route.getPath().equals(settings.getDefaultRoute()))
						{
							return dispatchRoute(new Route(settings.getDefaultRoute(), route.getContext(), route.getRequest(), route.getResponse()));
						}
						else
						{
							throw new Exception();
						}
					}
				}
			}
		}
		return dispatchRoute(new Route(settings.getDefaultRoute(), route.getContext(), route.getRequest(), route.getResponse()));
	}
}
