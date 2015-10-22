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

package ch.swaechter.webcms.core.components.view;

import ch.swaechter.webcms.core.components.container.Container;
import ch.swaechter.webcms.core.plugin.Plugin;
import ch.swaechter.webcms.core.router.Route;

/**
 * This class represents a view that can redirect to an internal site.
 *
 * @author Simon Wächter
 */
public class InternalRedirectView extends View
{
	/**
	 * Redirect URL of the view.
	 */
	private final String redirecturl;

	/**
	 * Constructor with a redirect URL.
	 *
	 * @param redirecturl Redirect URL.
	 */
	public InternalRedirectView(String redirecturl)
	{
		this.redirecturl = redirecturl;
	}

	/**
	 * Process the redirect based on the given stream.
	 */
	@Override
	public void processRoute(Plugin plugin, Route route, Container container) throws Exception
	{
		route.getResponse().sendRedirect(route.getRequest().getContextPath() + redirecturl);
	}
}
