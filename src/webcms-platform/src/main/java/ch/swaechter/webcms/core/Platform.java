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

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.swaechter.webcms.core.dispatcher.Dispatcher;
import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.router.Route;
import ch.swaechter.webcms.core.router.Router;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This class represents the WebCMS platform.
 *
 * @author Simon Wächter
 */
public class Platform
{
	private final PluginManager pluginmanager;

	private final Router router;

	private final Dispatcher dispatcher;

	public Platform(ArrayList<Plugin> plugins, Settings settings)
	{
		pluginmanager = new PluginManager(plugins);
		router = new Router();
		dispatcher = new Dispatcher(pluginmanager, settings);
	}
	
	public void handleRequest(ServletContext context, HttpServletRequest request, HttpServletResponse response)
	{
		Route route = router.getRoute(context, request, response);
		try
		{
			dispatcher.dispatchRoute(route);
		}
		catch(Exception exception)
		{
			dispatcher.dispatchFallbackRoute(route);
		}
	}
}
