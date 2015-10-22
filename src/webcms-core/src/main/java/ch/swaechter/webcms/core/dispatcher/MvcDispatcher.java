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

package ch.swaechter.webcms.core.dispatcher;

import java.lang.reflect.Method;

import ch.swaechter.webcms.core.Globals;
import ch.swaechter.webcms.core.Util;
import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.components.controller.Controller;
import ch.swaechter.webcms.core.components.view.View;
import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.router.Route;

/**
 * This class represents a dispatcher that handles all MVC component.
 *
 * @author Simon Wächter
 */
public class MvcDispatcher extends Dispatcher
{
	/**
	 * This method handles the given route. In case of a match the method should return true and the router
	 * will stop looking for the next dispatcher - otherwise return false and the router continues.
	 */
	@Override
	public boolean dispatchRoute(Route route, Container container) throws Exception
	{
		return dispatchMvcRoute(route, container);
	}

	/**
	 * Handle a MVC route.
	 *
	 * @param route Route
	 * @param container Container with the data
	 * @return Status of the dispatch process
	 * @throws Exception An exception in case of a critical system failure
	 */
	private boolean dispatchMvcRoute(Route route, Container container) throws Exception
	{
		String path =  Util.trimFirstCharacters(route.getPath(), Globals.DIRECTORY_SEPARATOR);
		String[] parameters = path.split(Globals.DIRECTORY_SEPARATOR);
		String controllerstring = (parameters.length > 0) ? parameters[0] : new String();
		String actionstring = (parameters.length > 1) ? parameters[1] : new String();
		String controllername = controllerstring + Globals.CONTROLLER_CLASS_SUFFIX;
		for(Plugin plugin : container.getPluginManager().getPlugins())
		{
			for(Controller controller : plugin.getControllers(container))
			{
				if(controller.getClass().getSimpleName().equalsIgnoreCase(controllername))
				{
					try
					{
						Method method = controller.getClass().getDeclaredMethod(actionstring);
						View view = (View) method.invoke(controller);
						view.processRoute(plugin, route, container);
						return true;
					}
					catch(Exception exception)
					{
						if(!route.getPath().equals(container.getSettings().getDefaultRoute()))
						{
							return dispatchMvcRoute(new Route(container.getSettings().getDefaultRoute(), route.getContext(), route.getRequest(), route.getResponse()), container);
						}
						else
						{
							throw new Exception();
						}
					}
				}
			}
		}
		return dispatchMvcRoute(new Route(container.getSettings().getDefaultRoute(), route.getContext(), route.getRequest(), route.getResponse()), container);
	}
}
