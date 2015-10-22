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

import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.router.Route;

/**
 * This class represents a dispatcher that handles URL aliases.
 *
 * @author Simon Wächter
 */
public class AliasDispatcher extends Dispatcher
{
	/**
	 * This method handles the given route. In case of a match the method should return true and the router
	 * will stop looking for the next dispatcher - otherwise return false and the router continues.
	 */
	@Override
	public boolean dispatchRoute(Route route, Container container) throws Exception
	{
		return false;
	}
}
