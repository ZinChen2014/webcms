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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is responsible for checking the input request and creating and executing the correct
 * route.
 *
 * @author Simon Wächter
 */
public class Router
{
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
}
