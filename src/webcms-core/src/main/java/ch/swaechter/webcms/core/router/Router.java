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

package ch.swaechter.webcms.core.router;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.dispatcher.AliasDispatcher;
import ch.swaechter.webcms.core.dispatcher.Dispatcher;
import ch.swaechter.webcms.core.dispatcher.MvcDispatcher;
import ch.swaechter.webcms.core.dispatcher.ResourceDispatcher;
import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.settings.Settings;


/**
 * This class is responsible for checking the input request and creating and executing the correct
 * route.
 *
 * @author Simon Wächter
 */
public class Router
{
	/**
	 * Container that provides all data.
	 */
	private final Container container;

	/**
	 * Constructor with the plugin manager an dthe settings.
	 *
	 * @param pluginmanager Plugin manager
	 * @param settings Settings
	 */
	public Router(PluginManager pluginmanager, Settings settings)
	{
		this.container = new Container(pluginmanager, settings);
	}

	/**
	 * Get the route based on all parameters.
	 *
	 * @param context Servlet context
	 * @param request HTTP request
	 * @param response HTTP response
	 * @return Route
	 */
	public Route getRoute(ServletContext context, HttpServletRequest request, HttpServletResponse response)
	{
		String path = request.getRequestURI().substring(request.getContextPath().length());
		return new Route(path, context, request, response);
	}

	/**
	 * Process the route and throw an exception in case of a problem.
	 *
	 * @param route Route
	 * @throws Exception An exception that can occur in case of a misconfiguration or an internal problem
	 */
	public void executeRoute(Route route) throws Exception
	{
		ArrayList<Dispatcher> dispatchers = new ArrayList<>();
		dispatchers.add(new AliasDispatcher());
		dispatchers.add(new ResourceDispatcher());
		dispatchers.add(new MvcDispatcher());
		for(Dispatcher dispatcher : dispatchers)
		{
			if(dispatcher.dispatchRoute(route, container))
			{
				break;
			}
		}
	}

	/**
	 * Display a message in case a critical system failure occurs.
	 *
	 * @param message Error message
	 * @param route Route
	 * @throws IOException An exception that can occur in case of a stream problem
	 */
	public void executeFailureRoute(String message, Route route) throws IOException
	{
		PrintWriter writer = route.getResponse().getWriter();
		writer.println(message);
		writer.close();
	}
}
