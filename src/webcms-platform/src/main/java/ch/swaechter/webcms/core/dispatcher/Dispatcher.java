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

import java.util.ArrayList;

import ch.swaechter.webcms.core.dispatcher.alias.AliasDispatcher;
import ch.swaechter.webcms.core.dispatcher.mvc.MvcDispatcher;
import ch.swaechter.webcms.core.dispatcher.resource.ResourceDispatcher;
import ch.swaechter.webcms.core.plugin.PluginManager;
import ch.swaechter.webcms.core.router.Route;
import ch.swaechter.webcms.core.settings.Settings;

/**
 * This interface dispatches a route and calls the correct dispatcher engine.
 *
 * @author Simon Wächter
 */
public class Dispatcher
{
	private ArrayList<Engine> engines;

	public Dispatcher(PluginManager pluginmanager, Settings settings)
	{
		engines = new ArrayList<>();
		engines.add(new AliasDispatcher());
		engines.add(new ResourceDispatcher(pluginmanager, settings));
		engines.add(new MvcDispatcher(pluginmanager, settings));
	}
	
	public void dispatchRoute(Route route) throws Exception
	{
		for(Engine engine : engines)
		{
			if(engine.dispatchRoute(route))
			{
				break;
			}
		}
	}
	
	public void dispatchFallbackRoute(Route route)
	{
	}
}
