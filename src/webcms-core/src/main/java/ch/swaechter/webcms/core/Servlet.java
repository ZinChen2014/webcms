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

package ch.swaechter.webcms.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.router.Route;
import ch.swaechter.webcms.core.router.Router;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This class is responsible for the servlet management and uses the platform for the task.
 * A CMS application has to subclass this class to set all required information.
 *
 * @author Simon Wächter
 */
public class Servlet extends HttpServlet
{
	/**
	 * Generated serialization ID.
	 */
	private final static long serialVersionUID = 1L;

	/**
	 * Plugin manager who is responsible for all plugins.
	 */
	private final PluginManager pluginmanager;

	/**
	 * Router that is responsible for the routing.
	 */
	private final Router router;

	/**
	 * Constructor with the required settings.
	 *
	 * @param settings Settings of the CMS
	 * @param plugins Plugins that should be loaded
	 */
	public Servlet(Settings settings, ArrayList<Plugin> plugins)
	{
		pluginmanager = new PluginManager(plugins);
		router = new Router(pluginmanager, settings);
	}

	/**
	 * Handle a GET request and send the generated content as response.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Route route = router.getRoute(getServletContext(), request, response);
			router.executeRoute(route);
		}
		catch(Exception exception)
		{
			router.executeFailureRoute("A critical system failure occured!", response);
		}
	}

	/**
	 * Handle a POST request and send the generated content as response.
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Route route = router.getRoute(getServletContext(), request, response);
			router.executeRoute(route);
		}
		catch(Exception exception)
		{
			router.executeFailureRoute("A critical system failure occured!", response);
		}
	}
}
